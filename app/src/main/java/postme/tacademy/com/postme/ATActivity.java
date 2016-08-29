package postme.tacademy.com.postme;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Monkey on 2016. 8. 29..
 */
public class ATActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_at);

        final Button btn = (Button)findViewById(R.id.btn_join);
        btn.setEnabled(false);
        final CheckBox yak_checkbox01 = (CheckBox)findViewById(R.id.yak_checkBox01);
        final CheckBox yak_checkbox02 = (CheckBox)findViewById(R.id.yak_checkBox02);
        final CheckBox yak_checkbox03 = (CheckBox)findViewById(R.id.yak_checkBox03);
        final CheckBox yak_checkbox04 = (CheckBox)findViewById(R.id.yak_checkBox04);
        CompoundButton.OnCheckedChangeListener checker = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (yak_checkbox01.isChecked() && yak_checkbox02.isChecked()
                        && yak_checkbox03.isChecked() && yak_checkbox04.isChecked()) {
                    btn.setEnabled(true);
                    btn.setText("Thanks Checked");
                } else if (btn.isEnabled()) {
                    btn.setEnabled(false);
                }
            }
        };
        yak_checkbox01.setOnCheckedChangeListener(checker);
        yak_checkbox02.setOnCheckedChangeListener(checker);
        yak_checkbox03.setOnCheckedChangeListener(checker);
        yak_checkbox04.setOnCheckedChangeListener(checker);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "실행", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ATActivity.this, UserInfoActivity.class);
                startActivity(intent);
            }
        });

        //약관동의 Detail
        findViewById(R.id.yak_view01).setOnClickListener(DetailLestener);
        findViewById(R.id.yak_view02).setOnClickListener(DetailLestener);
        findViewById(R.id.yak_view03).setOnClickListener(DetailLestener);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //약관동의 Detail
    TextView.OnClickListener DetailLestener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.yak_view01:
                    new AlertDialog.Builder(ATActivity.this)
                            .setTitle("얼럿팝업")
                            .setMessage("약관내용\n\nText")
                            .setNeutralButton("닫기", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dlg, int sumthin) {
                                }
                            })
                            .show(); //팝업창 보여줌
                    break;
                case R.id.yak_view02:
                    new AlertDialog.Builder(ATActivity.this)
                            .setTitle("얼럿팝업")
                            .setMessage("약관내용\n\nText")
                            .setNeutralButton("닫기", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dlg, int sumthin) {
                                }
                            })
                            .show(); //팝업창 보여줌
                    break;
                case R.id.yak_view03:
                    new AlertDialog.Builder(ATActivity.this)
                            .setTitle("얼럿팝업")
                            .setMessage("약관내용\n\nText")
                            .setNeutralButton("닫기", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dlg, int sumthin) {
                                }
                            })
                            .show(); //팝업창 보여줌
                    break;
            }
        }
    };
}


