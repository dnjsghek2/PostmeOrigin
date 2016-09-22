package postme.tacademy.com.postme;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

/**
 * Created by wonhochoi on 16. 9. 6..
 */
public class LSB {
    Context context;
    boolean isGPSEnabled = false;
    boolean isNetworkEnabled = false;
    Location LSBlocation;
    LocationManager locationManager;

    public LSB(Context context) {
        this.context = context;
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    }

    public Location onLcation() {

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            return null;
        }
        // GPS 프로바이더 사용가능여부
        isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        // 네트워크 프로바이더 사용가능여부
        isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            public void onProviderEnabled(String provider) {
            }

            public void onProviderDisabled(String provider) {
            }
        };
        String locationNet = LocationManager.NETWORK_PROVIDER;
        String locationGPS = LocationManager.GPS_PROVIDER;
        if (isNetworkEnabled == true) {
            LSBlocation = locationManager.getLastKnownLocation(locationNet);
        } else if (isGPSEnabled == true) {
            LSBlocation = locationManager.getLastKnownLocation(locationGPS);
        } else {
            Toast.makeText(context, "좌표를 받아 올 수 없습니다.", Toast.LENGTH_SHORT).show();
        }

        // 수동으로 위치 구하기
        return LSBlocation;
    }
}
