package postme.tacademy.com.postme;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.TabHost;

import postme.tacademy.com.postme.fragment.CubbyholeFrament;
import postme.tacademy.com.postme.fragment.HistoryFragment;
import postme.tacademy.com.postme.fragment.MapFragment;
import postme.tacademy.com.postme.fragment.SettingFragment;


public class TabActivity extends AppCompatActivity {

    FragmentTabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_tab);
        setSupportActionBar((Toolbar) findViewById(R.id.tab_toolbar));

        tabHost = (FragmentTabHost) findViewById(R.id.tabhost);                     //프레그먼트 탭호스트 정의
        tabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);  //.setup(context, Manager, layout)
        tabHost.addTab(tabHost.newTabSpec("map").setIndicator("", getResources().getDrawable(R.drawable.map_tab_icon))
                , MapFragment.class, null);
        tabHost.addTab(tabHost.newTabSpec("history").setIndicator("", getResources().getDrawable(R.drawable.history_icon))
                , HistoryFragment.class, null);
        tabHost.addTab(tabHost.newTabSpec("cubbyhole").setIndicator("", getResources().getDrawable(R.drawable.cubbyhole_icon))
                , CubbyholeFrament.class, null);
        tabHost.addTab(tabHost.newTabSpec("setting").setIndicator("", getResources().getDrawable(R.drawable.setting_icon))
                , SettingFragment.class, null);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
