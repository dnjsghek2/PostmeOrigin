package postme.tacademy.com.postme.fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.InputFilter;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.ClusterRenderer;
import com.skp.Tmap.TMapAddressInfo;
import com.skp.Tmap.TMapData;
import com.skp.Tmap.TMapTapi;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.xml.parsers.ParserConfigurationException;

import postme.tacademy.com.postme.EditFilter;
import postme.tacademy.com.postme.LSB;
import postme.tacademy.com.postme.MainActivity;
import postme.tacademy.com.postme.data.Cok;
import postme.tacademy.com.postme.data.CokList;
import postme.tacademy.com.postme.data.CokRenderer;
import postme.tacademy.com.postme.data.Message;
import postme.tacademy.com.postme.data.NetworkResult;
import postme.tacademy.com.postme.data.RendererItem;
import postme.tacademy.com.postme.dialog.CokCreateDialog;
import postme.tacademy.com.postme.dialog.MapDialog;
import postme.tacademy.com.postme.PostlistActivity;
import postme.tacademy.com.postme.R;
import postme.tacademy.com.postme.WritingActivity;
import postme.tacademy.com.postme.manager.Geopence;
import postme.tacademy.com.postme.manager.NetworkManager;
import postme.tacademy.com.postme.request.CokcheckRequest;
import postme.tacademy.com.postme.request.Map_Request;
import postme.tacademy.com.postme.request.MapsearchRequest;
import postme.tacademy.com.postme.request.NetworkRequest;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;


/**
 * Created by wonhochoi on 2016. 8. 4..
 */
public class MapFragment extends Fragment implements
        OnMapReadyCallback, GoogleMap.OnCameraMoveListener,
        GoogleMap.OnMapClickListener,         //맵 클릭시 이벤트 처리
        GoogleMap.OnMarkerClickListener,      //마커 클릭시 이벤트 처리
        GoogleMap.OnInfoWindowClickListener,  //Infowindow 클릭시 이벤트 처리
        GoogleMap.OnMarkerDragListener,
        ClusterManager.OnClusterClickListener
{

    private static MapFragment ourInstance = new MapFragment();

    public static MapFragment getInstance() {
        return ourInstance;
    }
    ClusterManager<RendererItem> mClusterManager;
    ClusterRenderer clusterRenderer;
    EditFilter editFilter;
    public static boolean[] checkitem = {true, false, false, false};
    Location location;
    static public Menu menu;
    static public MenuInflater menuInflater;
    static public FloatingActionButton fab;
    static public SupportMapFragment fragment;
    static LatLng MYLOCATION;
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
                    checkitem = new boolean[]{false, true, true, false};
                    menu.getItem(0).setVisible(checkitem[0]);
                    menu.getItem(1).setVisible(checkitem[1]);
                    menu.getItem(2).setVisible(checkitem[2]);
                    menu.getItem(3).setVisible(checkitem[3]);
                    fab.hide();
                }
            });

            final EditText area_edit = (EditText) view.findViewById(R.id.edit_area);
          //  editFilter = new EditFilter();
          //   area_edit.setFilters(new InputFilter[]{editFilter.filterKor});
            final EditText keyword_edit = (EditText) view.findViewById(R.id.edit_keyword);

            ImageButton searchbtn = (ImageButton) view.findViewById(R.id.search_btn);
            searchbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkitem = new boolean[]{true, false, false, false};
                    String area = area_edit.getText().toString();
                    String keyword = keyword_edit.getText().toString();
                    searchCok(area, keyword);
                    menu.getItem(0).setVisible(checkitem[0]);
                    menu.getItem(1).setVisible(checkitem[1]);
                    menu.getItem(2).setVisible(checkitem[2]);
                    menu.getItem(3).setVisible(checkitem[3]);
                    relativeLayout.setVisibility(view.GONE);
                    fab.show();
                }


            });

            view.setFocusableInTouchMode(true);
            view.requestFocus();
            view.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        if (relativeLayout.getVisibility() == View.VISIBLE) {
                            relativeLayout.setVisibility(View.GONE);
                            checkitem = new boolean[]{true, false, false, false};
                            menu.getItem(0).setVisible(checkitem[0]);
                            menu.getItem(1).setVisible(checkitem[1]);
                            menu.getItem(2).setVisible(checkitem[2]);
                            menu.getItem(3).setVisible(checkitem[3]);
                            relativeLayout.setVisibility(view.GONE);
                            fab.show();
                        } else {

                        }
                        return true;
                    } else {
                        return false;
                    }
                }
            });
        }
        return view; //완성된 VIEW return
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        this.menuInflater = inflater;
        this.menu = menu;
        menuInflater.inflate(R.menu.menu_map, this.menu);
        menu.getItem(0).setVisible(checkitem[0]);
        menu.getItem(1).setVisible(checkitem[1]);
        menu.getItem(2).setVisible(checkitem[2]);
        menu.getItem(3).setVisible(checkitem[3]);
        super.onCreateOptionsMenu(this.menu, menuInflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        int id = item.getItemId();
        switch (id) {
            case R.id.map_search:           //콕을 검색하는 툴바 아이템
                relativeLayout.setVisibility(view.VISIBLE);
                checkitem = new boolean[]{false, false, false, true};
                menu.getItem(0).setVisible(checkitem[0]);
                menu.getItem(1).setVisible(checkitem[1]);
                menu.getItem(2).setVisible(checkitem[2]);
                menu.getItem(3).setVisible(checkitem[3]);
                fab.hide();
                break;
            case R.id.map_ok:               //콕 생성 툴바 아이템
                fab.show();
                checkitem = new boolean[]{true, false, false, false};
                menu.getItem(0).setVisible(checkitem[0]);
                menu.getItem(1).setVisible(checkitem[1]);
                menu.getItem(2).setVisible(checkitem[2]);
                menu.getItem(3).setVisible(checkitem[3]);
                cokCheck();
                break;
            case R.id.map_cancel:           //글 작성 취소 아이템
                final MapDialog mapDialog = new MapDialog(getContext());
                mapDialog.show();
                break;
            case R.id.map_search_cancel:    //검색 취소 아이템
                checkitem = new boolean[]{true, false, false, false};
                menu.getItem(0).setVisible(checkitem[0]);
                menu.getItem(1).setVisible(checkitem[1]);
                menu.getItem(2).setVisible(checkitem[2]);
                menu.getItem(3).setVisible(checkitem[3]);
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

        googleMap = Map;

        if (ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            requestLocationPermission();
        }
        googleMap.setMyLocationEnabled(true);
        //현재 좌표 얻기
        LSB lsb = new LSB(getContext());
        location = lsb.onLcation();
        MYLOCATION = new LatLng(location.getLatitude(), location.getLongitude());


        //구글맵 회전 OFF
        UiSettings uiSettings = googleMap.getUiSettings();
        uiSettings.setRotateGesturesEnabled(false);
        //어플 실행시 현재 위치의 주변 콕 검색
        beginningCok(location);
        //콕 타이틀바 선택시 안에 있는 포스트 불러오기
        googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Intent intent = new Intent(getContext(), PostlistActivity.class);
                intent.putExtra("COK_NAME", marker.getTitle());
                intent.putExtra("COK_INFO", (int) marker.getTag());
                startActivity(intent);
            }
        });
        MYLOCATION.hashCode();
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(MYLOCATION, 15));
        zoomLevel = googleMap.getCameraPosition().zoom;
        mClusterManager = new ClusterManager<RendererItem>(getContext(), googleMap);
        mClusterManager.setRenderer(new CokRenderer(getContext(), googleMap, mClusterManager));
        googleMap.setOnCameraChangeListener(mClusterManager);
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
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

    //어플 실행시 현재 위치의 주변 콕들을 검색하는 메서드(200m)
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1) {
            beginningCok(location);
        }
    }

    //지정 위치와 키워드로 콕을 검색하는 메서드
    private void searchCok(String area, String keyword) {
        MapsearchRequest request = new MapsearchRequest(getContext(), area, keyword);
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
        mClusterManager.addItem(new RendererItem(cok));
    }

    public void searchMarker(Cok cok) {
        mClusterManager.addItem(new RendererItem(cok));
/*
        MarkerOptions options = new MarkerOptions();
        options.position(new LatLng(cok.getLatitude(), cok.getLongitude()));
        options.anchor(0.5f, 1);
        options.title(cok.getCok_name());
        googleMap.addMarker(options).setTag(cok.getCok_id());
*/
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
                    Geopence geopence = new Geopence();
                    geopence.execute(getContext(), location.getLatitude(), location.getLongitude(), "A10");
                } else if (result.getResult().getMessage().equals("2")) {
                    Toast.makeText(getContext(), "가까운 콕에 포스트가 작성 됩니다.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getContext(), WritingActivity.class);
                    startActivityForResult(intent, 0);
                }
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<Message>> request, int errorCode, String errorMessage, Throwable e) {

            }
        });
    }

    @Override
    public boolean onClusterClick(Cluster cluster) {
        // Show a toast with some info when the cluster is clicked.

        // Zoom in the cluster. Need to create LatLngBounds and including all the cluster items
        // inside of bounds, then animate to center of the bounds.

        // Create the builder to collect all essential cluster items for the bounds.
        LatLngBounds.Builder builder = LatLngBounds.builder();
        // Get the LatLngBounds
        final LatLngBounds bounds = builder.build();
        Log.d("클러스터", "실행");
        // Animate camera to the bounds
        try {
            googleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 100));
            Log.d("클러스터", "실행");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    public class Geopence extends AsyncTask<Object, Void, String> {
        TMapAddressInfo tMapAddressInfo;
        TMapData tMapData;
        TMapTapi tmaptapi;

        public Geopence() {
            tMapData = new TMapData();
        }

        @Override
        public String doInBackground(Object... params) {
            tmaptapi = new TMapTapi((Context) params[0]);
            tmaptapi.setSKPMapAuthentication("91f5919e-a5b3-3cf2-a3f2-481b7f0b8c9f");
            try {
                tMapAddressInfo = tMapData.reverseGeocoding((double) params[1], (double) params[2], (String) params[3]);
                Log.d("주소", "주소변환 : " + tMapAddressInfo.strFullAddress);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            }
            return tMapAddressInfo.strFullAddress;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            final CokCreateDialog cokCreateDialog = new CokCreateDialog(
                    getContext(), s, String.valueOf(location.getLatitude()),
                    String.valueOf(location.getLongitude()), MapFragment.this);
            cokCreateDialog.show();
        }
    }
}