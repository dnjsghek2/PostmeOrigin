package postme.tacademy.com.postme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethod;
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


import com.kakao.auth.ErrorCode;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.LoginButton;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;
import com.kakao.util.exception.KakaoException;
import com.kakao.util.helper.log.Logger;

import java.util.Arrays;

import postme.tacademy.com.postme.data.Message;
import postme.tacademy.com.postme.data.NetworkResult;
import postme.tacademy.com.postme.manager.NetworkManager;
import postme.tacademy.com.postme.manager.PropertyManager;
import postme.tacademy.com.postme.request.FacebookLoginRequest;
import postme.tacademy.com.postme.request.NetworkRequest;

public class MainActivity extends AppCompatActivity {

    /*private SessionCallback callback;*/
    CallbackManager callbackManager;
    LoginManager mLoginManager;
    ImageButton facebookButton;
    ImageButton kakaoButton;
//    LoginButton kakoLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      /*  callback = new SessionCallback();                  // 이 두개의 함수 중요함
        Session.getCurrentSession().addCallback(callback);
*/
        callbackManager = CallbackManager.Factory.create();
        mLoginManager = LoginManager.getInstance();
        kakaoButton = (ImageButton)findViewById(R.id.btn_kakao);
        facebookButton = (ImageButton)findViewById(R.id.btn_login);
//        kakoLogin = (LoginButton)findViewById(R.id.com_kakao_login);
        kakaoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                kakoLogin.callOnClick();
            }
        });
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
            facebookButton.setImageResource(R.drawable.facebook_on);
        } else {
            facebookButton.setImageResource(R.drawable.facebook_off);
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


    private void loginFacebook() {
        mLoginManager.setDefaultAudience(DefaultAudience.FRIENDS);
        mLoginManager.setLoginBehavior(LoginBehavior.NATIVE_WITH_FALLBACK);
        mLoginManager.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(MainActivity.this, "login manager...", Toast.LENGTH_SHORT).show();
                puttoken(AccessToken.getCurrentAccessToken().getToken());
                /*Intent intent = new Intent(MainActivity.this, ATActivity.class);
                startActivity(intent);
                finish();*/
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

/*
    //kakao 세션 클래스
    private class SessionCallback implements ISessionCallback {

        @Override
        public void onSessionOpened() {
            redirectSignupActivity();  // 세션 연결성공 시 redirectSignupActivity() 호출
        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            if(exception != null) {
                Logger.e(exception);
            }
            setContentView(R.layout.activity_main); // 세션 연결이 실패했을때
        }                                            // 로그인화면을 다시 불러옴
    }

    protected void redirectSignupActivity() {       //세션 연결 성공 시 SignupActivity로 넘김
        puttoken(Session.getCurrentSession().getAccessToken());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Session.getCurrentSession().removeCallback(callback);
    }
*/

    public void puttoken(String token) {

        FacebookLoginRequest request = new FacebookLoginRequest(this, token);
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<Message>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<Message>> request, NetworkResult<Message> result) {
                Toast.makeText(MainActivity.this,"Message : "+result.getResult().getMessage(), Toast.LENGTH_SHORT);
                String facebookId = AccessToken.getCurrentAccessToken().getUserId();
                PropertyManager.getInstance().setFacebookId(facebookId);
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