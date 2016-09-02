package postme.tacademy.com.postme;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import postme.tacademy.com.postme.data.Message;
import postme.tacademy.com.postme.data.NetworkResult;
import postme.tacademy.com.postme.manager.NetworkManager;
import postme.tacademy.com.postme.request.NetworkRequest;
import postme.tacademy.com.postme.request.UserInfoRequest;

/**
 * Created by Monkey on 2016. 8. 29..
 */
public class UserInfoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_userinfo);

        //Spinner
        Spinner spinner;
        spinner = (Spinner)findViewById(R.id.spinner_uinfo);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(getApplicationContext(),
                R.array.nacimiento_array, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                Log.i("Tag","index" + position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        
        //Bottom Button
        Button btn_userinfo = (Button)findViewById(R.id.btn_userinfo);
        btn_userinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLogin();
                /*Intent intent = new Intent(UserInfoActivity.this, TabActivity.class);
                startActivity(intent);
                finish();*/
            }
        });
        
        //Radio Select
        findViewById(R.id.radio_man_uinfo).setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                printChecked(view);
            }
        });
    }

    private void printChecked(View view) {
        RadioButton radiouinfo = (RadioButton)view;
        String resultText = "";
        if (radiouinfo.isChecked()) {
            resultText = radiouinfo.getText().toString();
        }
        TextView tv = (TextView)findViewById(R.id.text_uinfo_select);
        tv.setText(resultText);
    }

    public void onLogin() {

        UserInfoRequest request = new UserInfoRequest(this, "wonho-choi", "man", "1991-10-10", "didimdol@didimdol.com");
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<Message>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<Message>> request, NetworkResult<Message> result) {
                Toast.makeText(UserInfoActivity.this, "Message : "+result.getResult().getMessage(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(UserInfoActivity.this, TabActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<Message>> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(UserInfoActivity.this, "error : " + errorMessage, Toast.LENGTH_SHORT).show();
                Log.e("TAG_E", errorMessage);
            }
        });
    }
}