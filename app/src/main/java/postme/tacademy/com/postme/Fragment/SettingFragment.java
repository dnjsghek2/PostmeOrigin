package postme.tacademy.com.postme.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import gpslocation.wonho.example.com.postmetest.R;

/**
 * Created by wonhochoi on 2016. 8. 24..
 */
public class SettingFragment extends Fragment {

    public SettingFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.f_setting, container, false);
        return view;
    }
}