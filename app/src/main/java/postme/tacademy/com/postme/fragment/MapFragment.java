package postme.tacademy.com.postme.fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
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
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import postme.tacademy.com.postme.LSB;
import postme.tacademy.com.postme.data.Cok;
import postme.tacademy.com.postme.data.CokList;
import postme.tacademy.com.postme.data.Message;
import postme.tacademy.com.postme.data.NetworkResult;
import postme.tacademy.com.postme.dialog.CokCreateDialog;
import postme.tacademy.com.postme.dialog.MapDialog;
import postme.tacademy.com.postme.PostlistActivity;
import postme.tacademy.com.postme.R;
import postme.tacademy.com.postme.WritingActivity;
import postme.tacademy.com.postme.manager.NetworkManager;
import postme.tacademy.com.postme.request.CokcheckRequest;
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
        GoogleMap.OnMarkerDragListener
{

    private static MapFragment ourInstance = new MapFragment();

    public static MapFragment getInstance() {
        return ourInstance;
    }

    Location location;
    static public Menu menu;
    static public MenuInflater menuInflater;
    static public FloatingActionButton fab;
    static public SupportMapFragment fragment;
    static final LatLng SEOUL = new LatLng(37.56, 126.97);
    private GoogleMap googleMap;
    View relativeLayout;
    View view;
    float zoomLevel = 0;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (null == this.getChildFragmentManager().findFragmentById(R.id.map_fragment)) {
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
            case R.id.map_search:           //콕을 검색하는 툴바 아이템
                relativeLayout.setVisibility(view.VISIBLE);
                MapFragment.menu.getItem(0).setVisible(false);
                MapFragment.menu.getItem(1).setVisible(false);
                MapFragment.menu.getItem(2).setVisible(false);
                MapFragment.menu.getItem(3).setVisible(true);
                fab.hide();
                break;
            case R.id.map_ok:               //콕 생성 툴바 아이템
                fab.show();
                MapFragment.menu.getItem(0).setVisible(true);
                MapFragment.menu.getItem(1).setVisible(false);
                MapFragment.menu.getItem(2).setVisible(false);
                MapFragment.menu.getItem(3).setVisible(false);
                cokCheck();
                break;
            case R.id.map_cancel:           //글 작성 취소 아이템
                final MapDialog mapDialog = new MapDialog(getContext());
                mapDialog.show();
                break;
            case R.id.map_search_cancel:    //검색 취소 아이템
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
        //LOCATION 퍼미션 획득 과정

        if (ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            requestLocationPermission();
        }
        googleMap = Map;
        googleMap.setMyLocationEnabled(true);

        //구글 맵의 카메라의 변화가 있을시에 호출됨
        googleMap.setOnCameraMoveCanceledListener(new GoogleMap.OnCameraMoveCanceledListener() {
            @Override
            public void onCameraMoveCanceled() {
                zoomLevel = googleMap.getCameraPosition().zoom;
                Log.d("zoomLevel", ""+zoomLevel);
            }
        });

        //구글맵 회전 OFF
        UiSettings uiSettings = googleMap.getUiSettings();
        uiSettings.setRotateGesturesEnabled(false);

        //현재 좌표 얻기
        LSB lsb = new LSB(getContext());
        location = lsb.onLcation();

        //어플 실행시 현재 위치의 주변 콕 검색
        beginningCok(location);

        //콕 타이틀바 선택시 안에 있는 포스트 불러오기
        googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Intent intent = new Intent(getContext(), PostlistActivity.class);
                intent.putExtra("COK_INFO", (int) marker.getTag());
                intent.putExtra("COK_NAME", marker.getTitle());
                startActivity(intent);
            }
        });
        SEOUL.hashCode();
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SEOUL, 15));
        zoomLevel = googleMap.getCameraPosition().zoom;
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


    //어플 실행시 현재 위치의 주변 콕들을 검색하는 메서드(3km)
    private void beginningCok(Location location) {
        Map_Request request = new Map_Request(getContext(),
                String.valueOf(location.getLatitude()),
                String.valueOf(location.getLongitude()), 0, 10);
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


    //지정 위치와 키워드로 콕을 검색하는 메서드
    private void searchCok() {
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

    @Override
    public void onResume() {
        super.onResume();
    }

    private void cokCheck() {
        LSB lsb = new LSB(getContext());
        location = lsb.onLcation();

        CokcheckRequest request = new CokcheckRequest(getContext(), String.valueOf(location.getLatitude()),
                String.valueOf(location.getLongitude()));
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<Message>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<Message>> request, NetworkResult<Message> result) {
                if (result.getResult().getMessage().equals("1")) {
                    Toast.makeText(getContext(), "소속될 콕이 없습니다. 콕을 생성 합니다..", Toast.LENGTH_SHORT).show();
                    String lat = String.valueOf(location.getLatitude());
                    String lon = String.valueOf(location.getLongitude());

                    String address = getAddress(getContext(), location.getLatitude(), location.getLongitude());

                    final CokCreateDialog cokCreateDialog = new CokCreateDialog(getContext(), address, lat, lon);
                    cokCreateDialog.show();

                } else if (result.getResult().getMessage().equals("2")) {
                    Toast.makeText(getContext(), "가까운 콕에 포스트가 작성 됩니다.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getContext(), WritingActivity.class);
                    getContext().startActivity(intent);
                }
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<Message>> request, int errorCode, String errorMessage, Throwable e) {

            }
        });
    }
    public static String getAddress(Context mContext, double lat, double lng) {
        String nowAddress = "현재 위치를 확인 할 수 없습니다.";
        Geocoder geocoder = new Geocoder(mContext, Locale.KOREA);
        List<Address> address;
        try {
            if (geocoder != null) {
                //세번째 파라미터는 좌표에 대해 주소를 리턴 받는 갯수로
                //한좌표에 대해 두개이상의 이름이 존재할수있기에 주소배열을 리턴받기 위해 최대갯수 설정
                address = geocoder.getFromLocation(lat, lng, 1);

                if (address != null && address.size() > 0) {
                    // 주소 받아오기
                    String currentLocationAddress = address.get(0).getAddressLine(0).toString();
                    nowAddress = currentLocationAddress;

                }
            }

        } catch (IOException e) {
            Toast.makeText(mContext, "주소를 가져 올 수 없습니다.", Toast.LENGTH_LONG).show();
            e.printStackTrace();}
        return nowAddress;
    }

}