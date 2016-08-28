package postme.tacademy.com.postme.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import postme.tacademy.com.postme.R;


/**
 * Created by wonhochoi on 2016. 8. 24..
 */
public class CubbyholeFrament extends Fragment {
    FragmentTabHost tabHost;

    public CubbyholeFrament() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.f_cubbyhole, container, false);
        tabHost = (FragmentTabHost)view.findViewById(R.id.cubbyhole_tabhost);
        tabHost.setup(getContext(), getChildFragmentManager(), android.R.id.tabcontent);

        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("찜 목록"), CH_PicksFragment.class, null);
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("알림"), CH_PushFragment.class, null);
        return view;
    }
}
