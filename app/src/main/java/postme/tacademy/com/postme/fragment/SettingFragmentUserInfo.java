package postme.tacademy.com.postme.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import postme.tacademy.com.postme.R;

/**
 * Created by Monkey on 2016. 8. 30..
 */
public class SettingFragmentUserInfo extends AppCompatActivity {
    Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.f_setting_userinfo);

        button = (Button)findViewById(R.id.button4);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingFragmentUserInfo.this, SettingNickName.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
