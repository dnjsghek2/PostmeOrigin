package postme.tacademy.com.postme.Fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import postme.tacademy.com.postme.PostMeDialog;
import postme.tacademy.com.postme.R;


/**
 * Created by wonhochoi on 2016. 8. 4..
 */
public class MapFragment extends Fragment implements
        OnMapReadyCallback, GoogleMap.OnCameraMoveListener,
        GoogleMap.OnMapClickListener,         //맵 클릭시 이벤트 처리
        GoogleMap.OnMarkerClickListener,      //마커 클릭시 이벤트 처리
        GoogleMap.OnInfoWindowClickListener,  //Infowindow 클릭시 이벤트 처리
        GoogleMap.OnMarkerDragListener {
    Menu menu;
    MenuInflater menuInflater;
    static View view;
    FloatingActionButton fab;
    public MapFragment() {

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.f_map, container, false); //비어있는 layout 생성
            SupportMapFragment fragment =
                    (SupportMapFragment) this.getChildFragmentManager()
                            .findFragmentById(R.id.map_fragment);
            fragment.getMapAsync(this);
            setHasOptionsMenu(true);
            fab = (FloatingActionButton) view.findViewById(R.id.fab_click);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    menu.removeItem(R.id.Search);
                    menuInflater.inflate(R.menu.menu_fba, menu);
                    fab.hide();
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
        int id = item.getItemId();
        switch (id) {
            case R.id.Search:
                Toast.makeText(getContext(), "실행됨", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ok:
                final PostMeDialog postMeDialog = new PostMeDialog(getContext());
                postMeDialog.setContents("서울대 샤로수 콕이 생성되었습니다. 포스트 작성 화면으로 이동하겠습니다");
                postMeDialog.show();
                break;
            case R.id.cancel:
                menu.removeItem(R.id.ok);
                menu.removeItem(R.id.cancel);
                menuInflater.inflate(R.menu.menu_map, menu);
                fab.show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCameraMove() {

    }

    @Override
    public void onInfoWindowClick(Marker marker) {

    }

    @Override
    public void onMapClick(LatLng latLng) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        GoogleMap map;                            //구글맵 변수
        map = googleMap;
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        map.setMyLocationEnabled(true);
        map.getUiSettings().setCompassEnabled(true);
        map.getUiSettings().setZoomControlsEnabled(true);
        map.setOnCameraMoveListener(this);
        map.setOnMapClickListener(this);
        map.setOnMarkerClickListener(this);
        map.setOnInfoWindowClickListener(this);
        map.setOnMarkerDragListener(this);
    }

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
}