package postme.tacademy.com.postme.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import postme.tacademy.com.postme.data.Cok;
import postme.tacademy.com.postme.data.CokList;
import postme.tacademy.com.postme.data.NetworkResult;
import postme.tacademy.com.postme.dialog.MapDialog;
import postme.tacademy.com.postme.PostlistActivity;
import postme.tacademy.com.postme.R;
import postme.tacademy.com.postme.WritingActivity;
import postme.tacademy.com.postme.manager.NetworkManager;
import postme.tacademy.com.postme.request.Map_Request;
import postme.tacademy.com.postme.request.MapsearchRequest;
import postme.tacademy.com.postme.request.NetworkRequest;


/**
 * Created by wonhochoi on 2016. 8. 4..
 */
public class MapFragment extends Fragment implements
        OnMapReadyCallback, GoogleMap.OnCameraMoveListener,
        GoogleMap.OnMapClickListener,         //맵 클릭시 이벤트 처리
        GoogleMap.OnMarkerClickListener,      //마커 클릭시 이벤트 처리
        GoogleMap.OnInfoWindowClickListener,  //Infowindow 클릭시 이벤트 처리
        GoogleMap.OnMarkerDragListener {

    private static MapFragment ourInstance = new MapFragment();

    public static MapFragment getInstance() {
        return ourInstance;
    }

    static public Menu menu;
    static public MenuInflater menuInflater;
    static View view;
    static public FloatingActionButton fab;
    static public SupportMapFragment fragment;
    static final LatLng SEOUL = new LatLng(37.56, 126.97);
    private GoogleMap googleMap;
    View relativeLayout;


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.f_map, container, false); //비어있는 layout 생성
            relativeLayout = view.findViewById(R.id.search_layout);
            fragment =
                    (SupportMapFragment) this.getChildFragmentManager()
                            .findFragmentById(R.id.map_fragment);
            fragment.getMapAsync(this);
            setHasOptionsMenu(true);
            fab = (FloatingActionButton) view.findViewById(R.id.fab_click);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    menu.getItem(0).setVisible(false);
                    menu.getItem(1).setVisible(true);
                    menu.getItem(2).setVisible(true);
                    menu.getItem(3).setVisible(false);
                    fab.hide();
                }
            });

            Button searchbtn = (Button) view.findViewById(R.id.search_btn);

            searchbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    searchCok();
                    MapFragment.menu.getItem(0).setVisible(true);
                    MapFragment.menu.getItem(1).setVisible(false);
                    MapFragment.menu.getItem(2).setVisible(false);
                    MapFragment.menu.getItem(3).setVisible(false);
                    relativeLayout.setVisibility(view.GONE);
                    fab.show();
                }


            });
        }
        return view; //완성된 VIEW return
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        this.menuInflater = inflater;
        this.menu = menu;
        inflater.inflate(R.menu.menu_map, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        int id = item.getItemId();
        switch (id) {
            case R.id.map_search:
                relativeLayout.setVisibility(view.VISIBLE);
                MapFragment.menu.getItem(0).setVisible(false);
                MapFragment.menu.getItem(1).setVisible(false);
                MapFragment.menu.getItem(2).setVisible(false);
                MapFragment.menu.getItem(3).setVisible(true);
                fab.hide();
                break;

            case R.id.map_ok:
                fab.show();
                MapFragment.menu.getItem(0).setVisible(true);
                MapFragment.menu.getItem(1).setVisible(false);
                MapFragment.menu.getItem(2).setVisible(false);
                MapFragment.menu.getItem(3).setVisible(false);
                intent = new Intent(getContext(), WritingActivity.class);
                getContext().startActivity(intent);
                break;

            case R.id.map_cancel:
                final MapDialog mapDialog = new MapDialog(getContext());
                mapDialog.show();
                break;
            case R.id.map_search_cancel:
                MapFragment.menu.getItem(0).setVisible(true);
                MapFragment.menu.getItem(1).setVisible(false);
                MapFragment.menu.getItem(2).setVisible(false);
                MapFragment.menu.getItem(3).setVisible(false);
                relativeLayout.setVisibility(view.GONE);
                fab.show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCameraMove() {

    }

    @Override
    public void onMapClick(LatLng latLng) {

    }

    @Override
    public void onInfoWindowClick(Marker marker) {
    }

    @Override
    public void onMapReady(GoogleMap Map) {
        googleMap = Map;

        beginningCok();
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestLocationPermission();
        }
        googleMap.setMyLocationEnabled(true);

        googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Toast.makeText(getContext(), ""+marker.getTag(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), PostlistActivity.class);
                intent.putExtra("COK_INFO", (int)marker.getTag());
                intent.putExtra("COK_NAME", marker.getTitle());
                startActivity(intent);
            }
        });
        SEOUL.hashCode();
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SEOUL, 15));

        googleMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
    }

    private void requestLocationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)) {

        }

        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, RC_LOCATION_PERMISSION);
    }

    private static final int RC_LOCATION_PERMISSION = 100;

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

    @Override
    public void onMarkerDragStart(Marker marker) {
    }

    @Override
    public void onMarkerDrag(Marker marker) {
    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
    }

    private void beginningCok() {
        Map_Request request = new Map_Request(getContext(), "37.4771480", "126.9619530", 0, 10);
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<CokList>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<CokList>> request, NetworkResult<CokList> result) {
                Cok coks[] = result.getResult().getCok();

                for (Cok cok : coks) {
                    addMarker(cok);
                }
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<CokList>> request, int errorCode, String errorMessage, Throwable e) {

            }
        });

    }
    private void searchCok(){
        MapsearchRequest request = new MapsearchRequest(getContext(), "어딘가", "검색중", 0, 10);
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<CokList>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<CokList>> request, NetworkResult<CokList> result) {
                googleMap.clear();
                Cok coks[] = result.getResult().getCok();
                for (Cok cok : coks) {
                    searchMarker(cok);
                }
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<CokList>> request, int errorCode, String errorMessage, Throwable e) {

            }
        });

    }
    private void addMarker(Cok cok) {
        MarkerOptions options = new MarkerOptions();
        options.position(new LatLng(cok.getLatitude(), cok.getLongitude()));
        options.anchor(0.5f, 1);
        options.title(cok.getCok_name());
        googleMap.addMarker(options).setTag(cok.getCok_id());
    }

    public void searchMarker(Cok cok) {
        MarkerOptions options = new MarkerOptions();
        options.position(new LatLng(cok.getLatitude(), cok.getLongitude()));
        options.anchor(0.5f, 1);
        options.title(cok.getCok_name());
        googleMap.addMarker(options).setTag(cok.getCok_id());
    }


}