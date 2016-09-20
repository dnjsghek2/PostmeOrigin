package postme.tacademy.com.postme;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.skp.Tmap.TMapAddressInfo;
import com.skp.Tmap.TMapData;
import com.skp.Tmap.TMapTapi;

import org.xml.sax.SAXException;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import postme.tacademy.com.postme.data.Message;
import postme.tacademy.com.postme.data.NetworkResult;
import postme.tacademy.com.postme.data.User;
import postme.tacademy.com.postme.dialog.CokCreateDialog;
import postme.tacademy.com.postme.manager.NetworkManager;
import postme.tacademy.com.postme.request.IdcheckRequest;
import postme.tacademy.com.postme.request.NetworkRequest;
import postme.tacademy.com.postme.request.UserInfoRequest;

/**
 * Created by Monkey on 2016. 8. 29..
 */
public class UserInfoActivity extends AppCompatActivity {
    EditText nickname;
    TextView check;
    checkAsyntask checkAsyntask;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_userinfo);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.a_at_userinfo);
        ActionBar.LayoutParams p = new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        p.gravity = Gravity.CENTER;
        this.getSupportActionBar().setElevation(0);
        check = (TextView)findViewById(R.id.check);
        checkAsyntask= new checkAsyntask();
        nickname = (EditText)findViewById(R.id.nicknameedt);
        nickname.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                checkAsyntask.execute(nickname.getText().toString());
                return false;
            }
        });

        //Spinner
        Spinner spinner;
        spinner = (Spinner) findViewById(R.id.spinner_uinfo);
//        스피너.setAdapter(new ArrayAdapter<String>(컨텍스트, R.layout.위소스의주소, new String[]{"가나다라","테스트2"}));
        ArrayAdapter adapter = ArrayAdapter.createFromResource(getApplicationContext(),
                R.array.nacimiento_array, R.layout.spinner_items);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.i("Tag", "index" + position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //Bottom Button
        Button btn_userinfo = (Button) findViewById(R.id.btn_userinfo);
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
/*
        findViewById(R.id.radiogroup_select).setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                printChecked(view);
            }
        });
*/
    }
/*
    private void printChecked(View view) {
        RadioButton radiouinfo = (RadioButton)view;
        String resultText = "";
        if (radiouinfo.isChecked()) {
            resultText = radiouinfo.getText().toString();
        }
        TextView tv = (TextView)findViewById(R.id.text_uinfo_select);
        tv.setText(resultText);
    }
*/

    public void onLogin() {

        UserInfoRequest request = new UserInfoRequest(this, "wonho-choi", "man", "1991-10-10");
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<Message>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<Message>> request, NetworkResult<Message> result) {
                Toast.makeText(UserInfoActivity.this, "Message : " + result.getResult().getMessage(), Toast.LENGTH_SHORT).show();

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


    public class checkAsyntask extends AsyncTask<Object, Void, String> {
        checkAsyntask Instance;

        public checkAsyntask checkAsyntask() {
        if (Instance == null){
            Instance = new checkAsyntask();
        }
            return Instance;
        }

        @Override
        public String doInBackground(Object... params) {
        setNicknamecheck();

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s == "ok"){

            }
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

    }

    public void setNicknamecheck() {

        IdcheckRequest request = new IdcheckRequest(this, "1991-10-10");
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<Message>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<Message>> request, NetworkResult<Message> result) {

            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<Message>> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(UserInfoActivity.this, "error : " + errorMessage, Toast.LENGTH_SHORT).show();
                Log.e("TAG_E", errorMessage);
            }
        });
    }


}