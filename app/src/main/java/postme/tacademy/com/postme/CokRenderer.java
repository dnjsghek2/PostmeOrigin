package postme.tacademy.com.postme;

import android.content.Context;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;

import postme.tacademy.com.postme.data.RendererItem;

/**
 * Created by wonhochoi on 2016. 9. 21..
 */

public class CokRenderer extends DefaultClusterRenderer<RendererItem>{
    public CokRenderer(Context context, GoogleMap map, ClusterManager<RendererItem> clusterManager) {
        super(context, map, clusterManager);
    }

    @Override
    protected void onBeforeClusterItemRendered(RendererItem item, MarkerOptions markerOptions) {
        super.onBeforeClusterItemRendered(item, markerOptions);
        markerOptions.position(new LatLng(item.getCok().getLatitude(), item.getCok().getLongitude()));
        markerOptions.anchor(0.5f, 1);
        markerOptions.title(item.getCok().getCok_name());
    }

    @Override
    protected void onClusterItemRendered(RendererItem clusterItem, Marker marker) {
        super.onClusterItemRendered(clusterItem, marker);
        marker.setTag(clusterItem.getCok().getCok_id());
    }
}