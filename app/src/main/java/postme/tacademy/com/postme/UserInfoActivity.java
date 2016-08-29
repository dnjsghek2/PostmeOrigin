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
                Toast.makeText(getApplicationContext(), "실행", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UserInfoActivity.this, TabActivity.class);
                startActivity(intent);
                finish();
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

}