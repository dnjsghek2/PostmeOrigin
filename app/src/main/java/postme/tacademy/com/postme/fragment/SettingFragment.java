package postme.tacademy.com.postme.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import postme.tacademy.com.postme.R;


/**
 * Created by wonhochoi on 2016. 8. 24..
 */
public class SettingFragment extends Fragment implements OnClickListener{

    Button button, button2, button3;

    public SettingFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.f_setting, container, false);

        if (container == null)
            return null;

        Button button = (Button)view.findViewById(R.id.button);
        button.setOnClickListener(this);
        Button button2 = (Button)view.findViewById(R.id.button2);
        button2.setOnClickListener(this);
        Button button3 = (Button)view.findViewById(R.id.button3);
        button3.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button) {
            startActivity(new Intent(getActivity().getApplicationContext(), SettingFragmentUserInfo.class));
        }
        if (view.getId() == R.id.button2) {
            startActivity(new Intent(getActivity().getApplicationContext(), SettingFragmentHelp.class));
        }
        if (view.getId() == R.id.button3) {
            startActivity(new Intent(getActivity().getApplicationContext(), SettingFragmentInformation.class));
        }
    }

}