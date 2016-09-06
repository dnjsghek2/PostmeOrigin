package postme.tacademy.com.postme;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.location.Location;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.FileNotFoundException;
import java.io.IOException;

import postme.tacademy.com.postme.data.NetworkResult;
import postme.tacademy.com.postme.data.NetworkResultTemp;
import postme.tacademy.com.postme.data.User;
import postme.tacademy.com.postme.dialog.WritingDialog;
import postme.tacademy.com.postme.manager.NetworkManager;
import postme.tacademy.com.postme.request.LoginRequest;
import postme.tacademy.com.postme.request.NetworkRequest;
import postme.tacademy.com.postme.request.WritingRequest;

/**
 * Created by wonhochoi on 2016. 8. 29..
 */
public class WritingActivity extends AppCompatActivity {

    final int REQ_CODE_SELECT_IMAGE = 100;
    ToggleButton feeling_btn;
    ToggleButton situation_btn;
    RadioGroup feeling_radio_group;
    RadioGroup situation_radio_group;
    ImageView pictureview;
    FrameLayout picturelayout;
    EditText contentstext;
    Location location;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_writing);
        setSupportActionBar((Toolbar) findViewById(R.id.writing_toolbar));


        final ImageView writing_location_map = (ImageView) findViewById(R.id.writing_location_map);

        picturelayout = (FrameLayout) findViewById(R.id.picture_layout);
        contentstext = (EditText) findViewById(R.id.contents_text);


        feeling_radio_group = (RadioGroup) findViewById(R.id.feeling_radio);
        feeling_radio_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
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

        /*

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
*/

        feeling_btn = (ToggleButton) findViewById(R.id.feeling);
        feeling_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToggleCheck();
            }
        });

        situation_btn = (ToggleButton) findViewById(R.id.situation);
        situation_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                feeling_btn.setChecked(false);
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
        if (feeling_btn.isChecked()) {
            situation_btn.setChecked(false);
            feeling_radio_group.setVisibility(View.VISIBLE);
            /*situation_radio_group.setVisibility(View.GONE);*/
        } else if (situation_btn.isChecked()) {
            feeling_btn.setChecked(false);
            feeling_radio_group.setVisibility(View.GONE);
            /*situation_radio_group.setVisibility(View.VISIBLE);*/
        } else if (!(feeling_btn.isChecked() && situation_btn.isChecked())) {
            feeling_radio_group.setVisibility(View.GONE);
            /*situation_radio_group.setVisibility(View.GONE);*/
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
                    Bitmap image_bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
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
                onPost("20", "wonho", contentstext.getText().toString(), null, "", "", "37.4771480", "126.9619530");
                break;
            case R.id.writing_cancel:
                final WritingDialog writingDialog = new WritingDialog(this);
                writingDialog.show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    public void onPost(String cok_id, String nickname, String content, Image image, String feeling,
                       String state, String latitude, String longitude){

        WritingRequest request = new WritingRequest(WritingActivity.this, cok_id, nickname, content, image, feeling,
                state, latitude, longitude);
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<NetworkResultTemp>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<NetworkResultTemp>> request, NetworkResult<NetworkResultTemp> result) {
                Toast.makeText(WritingActivity.this, ""+result.getResult().getMessage(), Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<NetworkResultTemp>> request, int errorCode, String errorMessage, Throwable e) {
            }
        });
    }
}
