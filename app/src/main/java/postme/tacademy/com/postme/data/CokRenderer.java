package postme.tacademy.com.postme.data;

import android.content.Context;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;

import postme.tacademy.com.postme.R;

/**
 * Created by wonhochoi on 2016. 9. 21..
 */

public class CokRenderer extends DefaultClusterRenderer<RendererItem> {

    public CokRenderer(Context context, GoogleMap map, ClusterManager clusterManager) {
        super(context, map, clusterManager);
    }



    @Override
    protected void onBeforeClusterItemRendered(RendererItem person, MarkerOptions markerOptions) {
        // Draw a single person.
        // Set the info window to show their name.
        markerOptions.anchor(0.5f, 1);
        markerOptions.title(person.getCok().getCok_name());
        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.cok_img));
    }

    @Override
    protected void onClusterItemRendered(RendererItem clusterItem, Marker marker) {
        super.onClusterItemRendered(clusterItem, marker);
        marker.setTag(clusterItem.getCok().getCok_id());

    }

/*
    @Override
    protected boolean shouldRenderAsCluster(Cluster cluster) {
        // Always render clusters.
        return cluster.getSize() > 1;
    }
*/


}
