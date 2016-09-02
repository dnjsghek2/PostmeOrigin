package postme.tacademy.com.postme;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import postme.tacademy.com.postme.data.NetworkResult;
import postme.tacademy.com.postme.data.User;
import postme.tacademy.com.postme.manager.NetworkManager;
import postme.tacademy.com.postme.request.LoginRequest;
import postme.tacademy.com.postme.request.NetworkRequest;

public class LocalLoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locallogin);
        Button login_btn = (Button)findViewById(R.id.login);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLogin();
            }
        });
    }

    public void onLogin() {

        LoginRequest request = new LoginRequest(this, "didimdol@didimdol.com", "디딤돌", "디딤돌");
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<User>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<User>> request, NetworkResult<User> result) {
                Toast.makeText(LocalLoginActivity.this,
                        "email : "+result.getUser().getEmail()
                        +", name : "+result.getUser().getName()
                , Toast.LENGTH_SHORT).show();

                Toast.makeText(getApplicationContext(), "실행", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LocalLoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<User>> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(LocalLoginActivity.this, "error : " + errorMessage, Toast.LENGTH_SHORT).show();
                Log.e("TAG_E", errorMessage);
            }
        });
    }
}