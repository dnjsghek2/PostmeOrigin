package postme.tacademy.com.postme.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import postme.tacademy.com.postme.R;

/**
 * Created by Monkey on 2016. 9. 2..
 */
public class SettingFragmentHelp extends AppCompatActivity {

    RelativeLayout relativeLayout0101;
    TextView textView01;
    Button button01;
    RelativeLayout relativeLayout0102;
    ImageView imageView01;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.f_setting_help);

        relativeLayout0102 = (RelativeLayout) findViewById(R.id.f_setting_help_relativelayout0102);
        button01 = (Button) findViewById(R.id.f_setting_help_btn01);
        button01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.button01) {
                    relativeLayout0102.setVisibility(View.VISIBLE);
                } else {
                    relativeLayout0102.setVisibility(View.GONE);

                }
            }
        });


    }
}