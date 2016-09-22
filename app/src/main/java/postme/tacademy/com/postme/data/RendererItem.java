package postme.tacademy.com.postme.data;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

/**
 * Created by wonhochoi on 2016. 9. 21..
 */

public class RendererItem implements ClusterItem {
    LatLng location;
    Cok cok;

    public Cok getCok() {
        return cok;
    }

    public void setCok(Cok cok) {
        this.cok = cok;
    }

    public RendererItem(Cok cok){
        this.cok = cok;
        location = new LatLng(cok.getLatitude(), cok.getLongitude());
    }

    public LatLng getLocation() {
        return location;
    }

    public void setLocation(LatLng location) {
        this.location = location;
    }

    @Override
    public LatLng getPosition() {
        return location;
    }
}