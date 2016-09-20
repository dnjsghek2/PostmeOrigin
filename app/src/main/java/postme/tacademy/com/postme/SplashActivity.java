package postme.tacademy.com.postme;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import postme.tacademy.com.postme.data.Message;
import postme.tacademy.com.postme.data.NetworkResult;
import postme.tacademy.com.postme.manager.NetworkManager;
import postme.tacademy.com.postme.manager.PropertyManager;
import postme.tacademy.com.postme.request.FacebookLoginRequest;
import postme.tacademy.com.postme.request.NetworkRequest;


/**
 * Created by Monkey on 2016. 8. 23..
 */

public class SplashActivity extends AppCompatActivity {

    // //1000은 1초, 2000은 2초, 3000은 3초...
    private static int SPLASH_TIME_OUT = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_splash);

/*
        loginManager = LoginManager.getInstance();
        callbackManager = CallbackManager.Factory.create();


        loginSharedPreference();
*/

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
/*
    private void loginSharedPreference() {
        if (isFacebookLogin()) {
            processFacebookLogin();
        } else {
            moveLoginActivity();
        }
    }

    LoginManager loginManager;
    CallbackManager callbackManager;

    private void processFacebookLogin() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if (accessToken != null) {
            String token = accessToken.getToken();
            FacebookLoginRequest request = new FacebookLoginRequest(this, token);
            NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<Message>>() {
                @Override
                public void onSuccess(NetworkRequest<NetworkResult<Message>> request, NetworkResult<Message> result) {
                    moveMainActivity();
                }

                @Override
                public void onFail(NetworkRequest<NetworkResult<Message>> request, int errorCode, String errorMessage, Throwable e) {
                    loginManager.logOut();
                    facebookLogin();
                }
            });
        } else {
            facebookLogin();
        }
    }

    private void facebookLogin() {
        loginManager.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult result) {
                AccessToken accessToken = AccessToken.getCurrentAccessToken();
                if (!accessToken.getUserId().equals(PropertyManager.getInstance().getFacebookId())) {
                    resetFacebookAndMoveLoginActivity();
                    return;
                }
                FacebookLoginRequest request = new FacebookLoginRequest(SplashActivity.this, accessToken.getToken());

                NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<Message>>() {
                    @Override
                    public void onSuccess(NetworkRequest<NetworkResult<Message>> request, NetworkResult<Message> result) {
                        moveMainActivity();
                    }

                    @Override
                    public void onFail(NetworkRequest<NetworkResult<Message>> request, int errorCode, String errorMessage, Throwable e) {
                        resetFacebookAndMoveLoginActivity();
                    }
                });

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
        loginManager.logInWithReadPermissions(this, null);
    }

    private void resetFacebookAndMoveLoginActivity() {
        loginManager.logOut();
        PropertyManager.getInstance().setFacebookId("");
        moveLoginActivity();
    }

    private boolean isFacebookLogin() {
        if (!TextUtils.isEmpty(PropertyManager.getInstance().getFacebookId())) {
            return true;
        }
        return false;
    }

    private void moveMainActivity() {
        Intent mainIntent = new Intent(this, ATActivity.class);
        startActivity(mainIntent);
        finish();
    }

    private void moveLoginActivity() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }, 1000);
    }

    Handler mHandler = new Handler(Looper.getMainLooper());*/
}

