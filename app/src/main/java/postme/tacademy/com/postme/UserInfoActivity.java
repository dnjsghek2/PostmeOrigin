package postme.tacademy.com.postme;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
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
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.skp.Tmap.TMapAddressInfo;
import com.skp.Tmap.TMapData;
import com.skp.Tmap.TMapTapi;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.regex.Pattern;

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
    String nicknameok, birth, gender;
    boolean checknick = false;
    EditFilter editFilter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_userinfo);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.a_at_userinfo);
        ActionBar.LayoutParams p = new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        p.gravity = Gravity.CENTER;
        this.getSupportActionBar().setElevation(0);
        check = (TextView) findViewById(R.id.check);

        editFilter = new EditFilter();
        nickname = (EditText) findViewById(R.id.nicknameedt);
        nickname.setFilters(new InputFilter[] {editFilter.filterAlphaNum});
        nickname.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checknick = false;
                check.setText("아이디 중복확인을 해주십시요.");
                // 입력되는 텍스트에 변화가 있을 때
            }


            @Override
            public void afterTextChanged(Editable arg0) {
                // 입력이 끝났을 때
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // 입력하기 전에
            }
        });

        Button checkbtn = (Button)findViewById(R.id.checkbtn);
        checkbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAsyntask = new checkAsyntask();
                checknick = false;
                checkAsyntask.execute(nickname.getText().toString());
            }
        });
        //Spinner
        Spinner spinner;
        spinner = (Spinner) findViewById(R.id.spinner_uinfo);
//        스피너.setAdapter(new ArrayAdapter<String>(컨텍스트, R.layout.위소스의주소, new String[]{"가나다라","테스트2"}));
        final ArrayAdapter adapter = ArrayAdapter.createFromResource(getApplicationContext(),
                R.array.nacimiento_array, R.layout.spinner_items);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                birth = adapter.getItem(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        RadioGroup genderGroup = (RadioGroup) findViewById(R.id.radiogroup_select);
        genderGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio_man_uinfo) {
                    gender = "male";
                } else if (checkedId == R.id.radio_woman_uinfo) {
                    gender = "female";
                } else {
                    return;
                }
            }
        });


        //Bottom Button
        Button btn_userinfo = (Button) findViewById(R.id.btn_userinfo);
        btn_userinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nicknameok != null && birth != null && gender != null) {
                    onLogin();
                } else {
                    Toast.makeText(UserInfoActivity.this, "닉네임, 생년, 성별를 모두 입력해주세요.", Toast.LENGTH_SHORT);
                }
            }
        });

    }

    public class checkAsyntask extends AsyncTask<Object, Void, String> {

        @Override
        public String doInBackground(Object... params) {
            setNicknamecheck((String) params[0]);
            return null;
        }
        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }

    public void setNicknamecheck(final String nick) {

        IdcheckRequest request = new IdcheckRequest(this, nick);
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<Message>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<Message>> request, NetworkResult<Message> result) {
                if (result.getResult().getMessage().equals("1")) {
                    checknick = true;
                    nicknameok = nick;
                    check.setText("사용가능한 아이디");
                }else if (result.getResult().getMessage().equals("0")){
                    checknick = false;
                    check.setText("중복된 아이디");
                }
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<Message>> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(UserInfoActivity.this, "error : " + errorMessage, Toast.LENGTH_SHORT).show();
                Log.e("TAG_E", errorMessage);
            }
        });
    }


    public void onLogin() {

        UserInfoRequest request = new UserInfoRequest(this, nicknameok, gender, birth);
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

    protected InputFilter filterAlphaNum = new InputFilter() {
        public CharSequence filter(CharSequence source, int start, int end,
                                   Spanned dest, int dstart, int dend) {

            Pattern ps = Pattern.compile("^[a-zA-Z0-9]+$");
            if (!ps.matcher(source).matches()) {
                return "";
            }
            return null;
        }
    };
}