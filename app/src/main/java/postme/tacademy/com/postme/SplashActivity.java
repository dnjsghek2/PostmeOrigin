package postme.tacademy.com.postme;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;
import postme.tacademy.com.postme.indicator.SampleActivity;


/**
 * Created by Monkey on 2016. 8. 23..
 */

public class SplashActivity extends AppCompatActivity {

    SharedPreferences setting;
    String getToken;
    boolean getATA;
    boolean getUserInfo;
    Intent intent;
    // //1000은 1초, 2000은 2초, 3000은 3초...
    private static int SPLASH_TIME_OUT = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_splash);

        setting = getSharedPreferences("setting", 0);
        setting.getString("Token", getToken);
        setting.getBoolean("ATA", getATA);
        setting.getBoolean("UserInfo", getUserInfo);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                if (getToken == null) {
                    intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    if (getATA) {
                        if (getUserInfo) {
                            intent = new Intent(SplashActivity.this, TabActivity.class);
                            finish();
                        } else {
                            intent = new Intent(SplashActivity.this, ATActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                }
            }
        }, SPLASH_TIME_OUT);
/*
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);
*/
    }
}

