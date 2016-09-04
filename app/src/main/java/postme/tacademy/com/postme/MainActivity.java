package postme.tacademy.com.postme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.DefaultAudience;
import com.facebook.login.LoginBehavior;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import java.util.Arrays;

import postme.tacademy.com.postme.data.Message;
import postme.tacademy.com.postme.data.NetworkResult;
import postme.tacademy.com.postme.data.NetworkResultTemp;
import postme.tacademy.com.postme.manager.NetworkManager;
import postme.tacademy.com.postme.request.FacebookLoginRequest;
import postme.tacademy.com.postme.request.NetworkRequest;

public class MainActivity extends AppCompatActivity {


    CallbackManager callbackManager;
    LoginManager mLoginManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        callbackManager = CallbackManager.Factory.create();
        mLoginManager = LoginManager.getInstance();

        facebookButton = (ImageButton)findViewById(R.id.btn_login);
        facebookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isLogin()) {
                    logoutFacebook();
                } else {
                    loginFacebook();
                }
            }
        });
        setButtonLabel();

    }

    private void setButtonLabel() {
        if (isLogin()) {
            facebookButton.setImageResource(R.drawable.facebookbtn_in_img);
        } else {
            facebookButton.setImageResource(R.drawable.facebookbtn_out_img);
        }
    }

    AccessTokenTracker mTracker;

    @Override
    protected void onStart() {
        super.onStart();
        if (mTracker == null) {
            mTracker = new AccessTokenTracker() {
                @Override
                protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                    setButtonLabel();
                }
            };
        } else {
            mTracker.startTracking();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mTracker.stopTracking();
    }

    private void logoutFacebook() {
        mLoginManager.logOut();
    }

    ImageButton facebookButton;

    private void loginFacebook() {
        mLoginManager.setDefaultAudience(DefaultAudience.FRIENDS);
        mLoginManager.setLoginBehavior(LoginBehavior.NATIVE_WITH_FALLBACK);
        mLoginManager.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(MainActivity.this, "login manager...", Toast.LENGTH_SHORT).show();
                /*puttoken(AccessToken.getCurrentAccessToken());*/
                Intent intent = new Intent(MainActivity.this, ATActivity.class);
                startActivity(intent);
                finish();

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
        mLoginManager.logInWithReadPermissions(this, Arrays.asList("email"));
    }

    private boolean isLogin() {
        AccessToken token = AccessToken.getCurrentAccessToken();
        return token != null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public void puttoken(AccessToken token) {

        FacebookLoginRequest request = new FacebookLoginRequest(this, token);
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<Message>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<Message>> request, NetworkResult<Message> result) {
                Toast.makeText(MainActivity.this,"Message : "+result.getResult().getMessage(), Toast.LENGTH_SHORT);
                Intent intent = new Intent(MainActivity.this, ATActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<Message>> request, int errorCode, String errorMessage, Throwable e) {
            }
        });
    }
}