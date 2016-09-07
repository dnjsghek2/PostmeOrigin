package postme.tacademy.com.postme;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.location.Location;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.FileNotFoundException;
import java.io.IOException;

import postme.tacademy.com.postme.data.NetworkResult;
import postme.tacademy.com.postme.data.NetworkResultTemp;
import postme.tacademy.com.postme.dialog.WritingDialog;
import postme.tacademy.com.postme.manager.NetworkManager;
import postme.tacademy.com.postme.request.NetworkRequest;
import postme.tacademy.com.postme.request.WritingRequest;

/**
 * Created by wonhochoi on 2016. 8. 29..
 */
public class WritingActivity extends AppCompatActivity {

    final int REQ_CODE_SELECT_IMAGE = 100;
    TextView feeling_btn, situation_btn;
    RadioGroup feeling_radio_group;
    RadioGroup situation_radio_group;
    ImageView pictureview;
    FrameLayout picturelayout;
    EditText contentstext;
    Location location;
    RadioButton joy, pleasure, sorrow, anger;
    Bitmap image_bitmap;

    boolean feeling_btn_check = false;
    boolean feeling_item_Checked = false;
    boolean situation_btn_Check = false;
    boolean situation_item_Checked = false;
    String feeling_item = "0";
    String situation_item = "0";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_writing);
        setSupportActionBar((Toolbar) findViewById(R.id.writing_toolbar));


        final ImageView writing_location_map = (ImageView) findViewById(R.id.writing_location_map);

        picturelayout = (FrameLayout) findViewById(R.id.picture_layout);
        contentstext = (EditText) findViewById(R.id.contents_text);
        joy = (RadioButton) findViewById(R.id.joy);
        pleasure = (RadioButton) findViewById(R.id.pleasure);
        sorrow = (RadioButton) findViewById(R.id.sorrow);
        anger = (RadioButton) findViewById(R.id.anger);

        feeling_radio_group = (RadioGroup) findViewById(R.id.feeling_radio);
        feeling_radio_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.joy:
                        joy.setBackgroundResource(R.drawable.joy);
                        anger.setBackgroundResource(R.drawable.anger_noclick);
                        sorrow.setBackgroundResource(R.drawable.sorrow_noclick);
                        pleasure.setBackgroundResource(R.drawable.pleasure_noclick);
                        feeling_btn.setBackgroundResource(R.drawable.radio);
                        feeling_item_Checked = true;
                        feeling_item = "1";
                        break;

                    case R.id.anger:
                        joy.setBackgroundResource(R.drawable.joy_noclick);
                        anger.setBackgroundResource(R.drawable.anger);
                        sorrow.setBackgroundResource(R.drawable.sorrow_noclick);
                        pleasure.setBackgroundResource(R.drawable.pleasure_noclick);
                        feeling_btn.setBackgroundResource(R.drawable.radio1);
                        feeling_item_Checked = true;
                        feeling_item = "2";
                        break;

                    case R.id.sorrow:
                        joy.setBackgroundResource(R.drawable.joy_noclick);
                        anger.setBackgroundResource(R.drawable.anger_noclick);
                        sorrow.setBackgroundResource(R.drawable.sorrow);
                        pleasure.setBackgroundResource(R.drawable.pleasure_noclick);
                        feeling_btn.setBackgroundResource(R.drawable.radio2);
                        feeling_item_Checked = true;
                        feeling_item = "3";
                        break;

                    case R.id.pleasure:
                        joy.setBackgroundResource(R.drawable.joy_noclick);
                        anger.setBackgroundResource(R.drawable.anger_noclick);
                        sorrow.setBackgroundResource(R.drawable.sorrow_noclick);
                        pleasure.setBackgroundResource(R.drawable.pleasure);
                        feeling_btn.setBackgroundResource(R.drawable.radio3);
                        feeling_item = "4";
                        feeling_item_Checked = true;
                        break;
                }
            }
        });


        situation_radio_group = (RadioGroup) findViewById(R.id.situation_radio);
        situation_radio_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.joy:

                        break;
                    case R.id.anger:

                        break;
                    case R.id.sorrow:

                        break;
                    case R.id.pleasure:
                        break;
                }
            }
        });
        feeling_btn = (TextView) findViewById(R.id.feeling);
        feeling_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feeling_btn_check = !feeling_btn_check;
                situation_btn_Check = false;
                ToggleCheck();
            }
        });
        situation_btn = (TextView) findViewById(R.id.situation);
        situation_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                situation_btn_Check = !situation_btn_Check;

                feeling_btn_check = false;
                ToggleCheck();
            }
        });
        ToggleButton location_btn = (ToggleButton) findViewById(R.id.writing_location);
        location_btn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    writing_location_map.setVisibility(View.VISIBLE);
                } else {
                    writing_location_map.setVisibility(View.GONE);
                }
            }
        });

        pictureview = (ImageView) findViewById(R.id.picture_view);

        Button picturebtn = (Button) findViewById(R.id.picture_btn);
        picturebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
                intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQ_CODE_SELECT_IMAGE);
            }
        });

        Button picturedelete = (Button) findViewById(R.id.picture_delete);
        picturedelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pictureview.setImageBitmap(null);
                picturelayout.setVisibility(View.GONE);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_writing, menu);
        return super.onCreateOptionsMenu(menu);
    }

    void ToggleCheck() {

        if (!feeling_item_Checked) { //아이템 선택이 안된경우 실행
            if (feeling_btn_check) { //거짓이라면 on으로 바꿔줌
                feeling_btn.setBackgroundResource(R.drawable.feeling_radio_group_on);
            } else if (!feeling_btn_check) { //참이라면 거짓으로 바꿔줌
                feeling_btn.setBackgroundResource(R.drawable.feeling_radio_group_off);
            }
        }

        if (!situation_item_Checked) { //아이템 선택이 안된경우 실행
            if (situation_btn_Check) { //on으로 바꿔줌
                situation_btn.setBackgroundResource(R.drawable.situation_radio_group_on);
            } else if (!situation_btn_Check) { //참이라면 거짓으로 바꿔줌
                situation_btn.setBackgroundResource(R.drawable.situation_radio_group_off);
            }
        }

        if (feeling_btn_check) {
            feeling_radio_group.setVisibility(View.VISIBLE);
            situation_radio_group.setVisibility(View.GONE);
        } else if (situation_btn_Check) {
            feeling_radio_group.setVisibility(View.GONE);
            situation_radio_group.setVisibility(View.VISIBLE);
        } else if (!feeling_btn_check && !situation_btn_Check) {
            feeling_radio_group.setVisibility(View.GONE);
            situation_radio_group.setVisibility(View.GONE);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQ_CODE_SELECT_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                try {
                    //Uri에서 이미지 이름을 얻어온다.
                    //String name_Str = getImageNameToUri(data.getData());

                    //이미지 데이터를 비트맵으로 받아온다.
                    image_bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());

                    pictureview = (ImageView) findViewById(R.id.picture_view);
                    //배치해놓은 ImageView에 set
                    pictureview.setImageBitmap(image_bitmap);
                    picturelayout.setVisibility(View.VISIBLE);
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public String getImageNameToUri(Uri data)//이미지 파일의 uri를 가져오는 메서드
    {
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(data, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

        cursor.moveToFirst();

        String imgPath = cursor.getString(column_index);
        String imgName = imgPath.substring(imgPath.lastIndexOf("/") + 1);

        return imgName;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.writing_ok:
                LSB lsb = new LSB(WritingActivity.this);
                location = lsb.onLcation();
                String lat = String.valueOf(location.getLatitude());
                String lon = String.valueOf(location.getLongitude());
                onPost(contentstext.getText().toString(), null, feeling_item, "1", lat, lon);
                break;
            case R.id.writing_cancel:
                final WritingDialog writingDialog = new WritingDialog(this);
                writingDialog.show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    public void onPost(String content, Bitmap image, String feeling,
                       String state, String latitude, String longitude) {

        WritingRequest request = new WritingRequest(WritingActivity.this, content, "", feeling,
                state, latitude, longitude);
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<NetworkResultTemp>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<NetworkResultTemp>> request, NetworkResult<NetworkResultTemp> result) {
                Toast.makeText(WritingActivity.this, "" + result.getResult().getMessage()+result.getResult().getCok_id(), Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<NetworkResultTemp>> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(WritingActivity.this, errorMessage.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
