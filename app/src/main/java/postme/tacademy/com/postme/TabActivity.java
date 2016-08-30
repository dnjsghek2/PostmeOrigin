package postme.tacademy.com.postme;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

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
        tabHost.addTab(tabHost.newTabSpec("id1").setIndicator("지도")
                , MapFragment.class, null);
        tabHost.addTab(tabHost.newTabSpec("id2").setIndicator("히스토리")
                , HistoryFragment.class, null);
        tabHost.addTab(tabHost.newTabSpec("id3").setIndicator("보관함")
                , CubbyholeFrament.class, null);
        tabHost.addTab(tabHost.newTabSpec("id4").setIndicator("설정")
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
