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
    RelativeLayout relativeLayout0201;
    RelativeLayout relativeLayout0301;
    RelativeLayout relativeLayout0401;
    TextView textView01;
    TextView textView02;
    TextView textView03;
    TextView textView04;
    Button button01;
    Button button02;
    Button button03;
    Button button04;
    RelativeLayout relativeLayout0102;
    RelativeLayout relativeLayout0202;
    RelativeLayout relativeLayout0302;
    RelativeLayout relativeLayout0402;
    ImageView imageView01;
    ImageView imageView02;
    ImageView imageView03;
    ImageView imageView04;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.f_setting_help);

        relativeLayout0102 = (RelativeLayout) findViewById(R.id.f_setting_help_relativelayout0102);
        button01 = (Button) findViewById(R.id.f_setting_help_btn01);
        button01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(relativeLayout0102.getVisibility() == View.GONE) {
                    relativeLayout0102.setVisibility(View.VISIBLE);
                } else {
                    relativeLayout0102.setVisibility(View.GONE);
                }
            }
        });
        relativeLayout0202 = (RelativeLayout) findViewById(R.id.f_setting_help_relativelayout0202);
        button02 = (Button) findViewById(R.id.f_setting_help_btn02);
        button02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(relativeLayout0202.getVisibility() == View.GONE) {
                    relativeLayout0202.setVisibility(View.VISIBLE);
                } else {
                    relativeLayout0202.setVisibility(View.GONE);
                }
            }
        });
        relativeLayout0302 = (RelativeLayout) findViewById(R.id.f_setting_help_relativelayout0302);
        button03 = (Button) findViewById(R.id.f_setting_help_btn03);
        button03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(relativeLayout0302.getVisibility() == View.GONE) {
                    relativeLayout0302.setVisibility(View.VISIBLE);
                } else {
                    relativeLayout0302.setVisibility(View.GONE);
                }
            }
        });
        relativeLayout0402 = (RelativeLayout) findViewById(R.id.f_setting_help_relativelayout0402);
        button04 = (Button) findViewById(R.id.f_setting_help_btn04);
        button04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(relativeLayout0402.getVisibility() == View.GONE) {
                    relativeLayout0402.setVisibility(View.VISIBLE);
                } else {
                    relativeLayout0402.setVisibility(View.GONE);
                }
            }
        });


    }
}