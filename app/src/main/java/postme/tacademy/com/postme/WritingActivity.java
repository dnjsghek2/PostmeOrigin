package postme.tacademy.com.postme;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
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
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

import postme.tacademy.com.postme.data.NetworkResult;
import postme.tacademy.com.postme.data.NetworkResultTemp;
import postme.tacademy.com.postme.dialog.WritingDialog;
import postme.tacademy.com.postme.manager.NetworkManager;
import postme.tacademy.com.postme.request.NetworkRequest;
import postme.tacademy.com.postme.request.WritingRequest;

/**
 * Created by wonhochoi on 2016. 8. 29..
 */
public class WritingActivity extends AppCompatActivity implements
        OnMapReadyCallback {

    boolean snapshotcheck = false;
    final int REQ_CODE_SELECT_IMAGE = 100;
    TextView feeling_btn, situation_btn;
    RadioGroup feeling_radio_group;
    RadioGroup situation_radio_group;
    ImageView pictureview;
    FrameLayout picturelayout;
    EditText contentstext;
    Location location;
    RadioButton joy, pleasure, sorrow, anger;
    RadioButton primavera, verano, otono, invierno, bienlugar, matzip, pasajero, natural;
    Bitmap image_bitmap;

    boolean feeling_btn_check = false;
    boolean feeling_item_Checked = false;
    boolean situation_btn_Check = false;
    boolean situation_item_Checked = false;
    String feeling_item = "0";
    String situation_item = "0";
    File file;
    private GoogleMap googleMap;
    MapFragment fragment;
    LinearLayout maplayout;
    File snapshotfile;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_writing);
        setSupportActionBar((Toolbar) findViewById(R.id.writing_toolbar));
        maplayout = (LinearLayout) findViewById(R.id.map_layout);
        fragment = (MapFragment) this.getFragmentManager()
                .findFragmentById(R.id.writing_location_map);
        fragment.getMapAsync(this);

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
                        feeling_btn.setBackgroundResource(R.drawable.radio);
                        feeling_item_Checked = true;
                        feeling_item = "1";
                        break;

                    case R.id.anger:
                        feeling_btn.setBackgroundResource(R.drawable.radio1);
                        feeling_item_Checked = true;
                        feeling_item = "2";
                        break;

                    case R.id.sorrow:
                        feeling_btn.setBackgroundResource(R.drawable.radio2);
                        feeling_item_Checked = true;
                        feeling_item = "3";
                        break;

                    case R.id.pleasure:
                        feeling_btn.setBackgroundResource(R.drawable.radio3);
                        feeling_item = "4";
                        feeling_item_Checked = true;
                        break;
                }
            }
        });


        primavera = (RadioButton) findViewById(R.id.state_primavera);
        verano = (RadioButton) findViewById(R.id.state_verano);
        otono = (RadioButton) findViewById(R.id.state_otono);
        invierno = (RadioButton) findViewById(R.id.state_invierno);
        bienlugar = (RadioButton) findViewById(R.id.state_bienlugar);
        matzip = (RadioButton) findViewById(R.id.state_matzip);
        pasajero = (RadioButton) findViewById(R.id.state_pasajero);
        natural = (RadioButton) findViewById(R.id.state_natural);

        situation_radio_group = (RadioGroup) findViewById(R.id.situation_radio);
        situation_radio_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.state_primavera:
                        primavera.setBackgroundResource(R.drawable.state_primavera_onclick);
                        verano.setBackgroundResource(R.drawable.state_verano);
                        otono.setBackgroundResource(R.drawable.state_otono);
                        invierno.setBackgroundResource(R.drawable.state_invierno);
                        bienlugar.setBackgroundResource(R.drawable.state_bienlugar);
                        matzip.setBackgroundResource(R.drawable.state_matzip);
                        pasajero.setBackgroundResource(R.drawable.state_pasajero);
                        natural.setBackgroundResource(R.drawable.state_natural);
                        situation_btn.setBackgroundResource(R.drawable.state_primavera_onclick);
                        situation_item_Checked = true;
                        situation_item = "1";
                        break;
                    case R.id.state_verano:
                        primavera.setBackgroundResource(R.drawable.state_primavera);
                        verano.setBackgroundResource(R.drawable.state_verano_onclick);
                        otono.setBackgroundResource(R.drawable.state_otono);
                        invierno.setBackgroundResource(R.drawable.state_invierno);
                        bienlugar.setBackgroundResource(R.drawable.state_bienlugar);
                        matzip.setBackgroundResource(R.drawable.state_matzip);
                        pasajero.setBackgroundResource(R.drawable.state_pasajero);
                        natural.setBackgroundResource(R.drawable.state_natural);
                        situation_btn.setBackgroundResource(R.drawable.state_verano_onclick);
                        situation_item_Checked = true;
                        situation_item = "2";
                        break;
                    case R.id.state_otono:
                        primavera.setBackgroundResource(R.drawable.state_primavera);
                        verano.setBackgroundResource(R.drawable.state_verano);
                        otono.setBackgroundResource(R.drawable.state_otono_onclick);
                        invierno.setBackgroundResource(R.drawable.state_invierno);
                        bienlugar.setBackgroundResource(R.drawable.state_bienlugar);
                        matzip.setBackgroundResource(R.drawable.state_matzip);
                        pasajero.setBackgroundResource(R.drawable.state_pasajero);
                        natural.setBackgroundResource(R.drawable.state_natural);
                        situation_btn.setBackgroundResource(R.drawable.state_otono_onclick);
                        situation_item_Checked = true;
                        situation_item = "3";
                        break;
                    case R.id.state_invierno:
                        primavera.setBackgroundResource(R.drawable.state_primavera);
                        verano.setBackgroundResource(R.drawable.state_verano);
                        otono.setBackgroundResource(R.drawable.state_otono);
                        invierno.setBackgroundResource(R.drawable.state_invierno_onclick);
                        bienlugar.setBackgroundResource(R.drawable.state_bienlugar);
                        matzip.setBackgroundResource(R.drawable.state_matzip);
                        pasajero.setBackgroundResource(R.drawable.state_pasajero);
                        natural.setBackgroundResource(R.drawable.state_natural);
                        situation_btn.setBackgroundResource(R.drawable.state_invierno_onclick);
                        situation_item_Checked = true;
                        situation_item = "4";
                        break;
                    case R.id.state_bienlugar:
                        primavera.setBackgroundResource(R.drawable.state_primavera);
                        verano.setBackgroundResource(R.drawable.state_verano);
                        otono.setBackgroundResource(R.drawable.state_otono);
                        invierno.setBackgroundResource(R.drawable.state_invierno);
                        bienlugar.setBackgroundResource(R.drawable.state_bienlugar_onclick);
                        matzip.setBackgroundResource(R.drawable.state_matzip);
                        pasajero.setBackgroundResource(R.drawable.state_pasajero);
                        natural.setBackgroundResource(R.drawable.state_natural);
                        situation_btn.setBackgroundResource(R.drawable.state_bienlugar_onclick);
                        situation_item_Checked = true;
                        situation_item = "5";
                        break;
                    case R.id.state_matzip:
                        primavera.setBackgroundResource(R.drawable.state_primavera);
                        verano.setBackgroundResource(R.drawable.state_verano);
                        otono.setBackgroundResource(R.drawable.state_otono);
                        invierno.setBackgroundResource(R.drawable.state_invierno);
                        bienlugar.setBackgroundResource(R.drawable.state_bienlugar);
                        matzip.setBackgroundResource(R.drawable.state_matzip_onclick);
                        pasajero.setBackgroundResource(R.drawable.state_pasajero);
                        natural.setBackgroundResource(R.drawable.state_natural);
                        situation_btn.setBackgroundResource(R.drawable.state_matzip_onclick);
                        situation_item_Checked = true;
                        situation_item = "6";
                        break;
                    case R.id.state_pasajero:
                        primavera.setBackgroundResource(R.drawable.state_primavera);
                        verano.setBackgroundResource(R.drawable.state_verano);
                        otono.setBackgroundResource(R.drawable.state_otono);
                        invierno.setBackgroundResource(R.drawable.state_invierno);
                        bienlugar.setBackgroundResource(R.drawable.state_bienlugar);
                        matzip.setBackgroundResource(R.drawable.state_matzip);
                        pasajero.setBackgroundResource(R.drawable.state_pasajero_onclick);
                        natural.setBackgroundResource(R.drawable.state_natural);
                        situation_btn.setBackgroundResource(R.drawable.state_pasajero_onclick);
                        situation_item_Checked = true;
                        situation_item = "7";
                        break;
                    case R.id.state_natural:
                        primavera.setBackgroundResource(R.drawable.state_primavera);
                        verano.setBackgroundResource(R.drawable.state_verano);
                        otono.setBackgroundResource(R.drawable.state_otono);
                        invierno.setBackgroundResource(R.drawable.state_invierno);
                        bienlugar.setBackgroundResource(R.drawable.state_bienlugar);
                        matzip.setBackgroundResource(R.drawable.state_matzip);
                        pasajero.setBackgroundResource(R.drawable.state_pasajero);
                        natural.setBackgroundResource(R.drawable.state_natural_onclick);
                        situation_btn.setBackgroundResource(R.drawable.state_natural_onclick);
                        situation_item_Checked = true;
                        situation_item = "8";
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

        final Button location_btn = (Button) findViewById(R.id.writing_location);
        location_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (maplayout.getVisibility() == View.VISIBLE) {
                    onSnapshot();
                    maplayout.setVisibility(View.GONE);
                    location_btn.setBackgroundResource(R.drawable.location_bottom);

                } else {
                    maplayout.setVisibility(View.VISIBLE);
                    location_btn.setBackgroundResource(R.drawable.location_top);
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
                    Bitmap reimage_bitmap;
                    file = new File(getCacheDir(), "image.jpeg");
                    file.createNewFile();
                    OutputStream os = new FileOutputStream(file);
                    image_bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
                    Log.d("image_bitmap", "" + image_bitmap.getByteCount());

                    if (image_bitmap.getWidth() > 1024 && image_bitmap.getHeight() > 1024) {
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        if (image_bitmap.getWidth() > 1024) {
                            options.inSampleSize = image_bitmap.getWidth() / 1024;
                        } else if (image_bitmap.getHeight() > 1024) {
                            options.inSampleSize = image_bitmap.getHeight() / 1024;
                        }
                        image_bitmap = BitmapFactory.decodeFile(file.getPath(), options);
                        file.delete();
                        os = new FileOutputStream(file);
                        image_bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
                    }

                    Log.d("image_bitmap", "" + image_bitmap.getByteCount());
                    os.flush();
                    os.close();
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
                } finally {

                }
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.writing_ok:
                onSnapshot();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        LSB lsb = new LSB(WritingActivity.this);
                        location = lsb.onLcation();
                        String lat = String.valueOf(location.getLatitude());
                        String lon = String.valueOf(location.getLongitude());
                        onPost(contentstext.getText().toString(), file, feeling_item, "1", lat, lon);
                    }
                }, 1000);
                break;
            case R.id.writing_cancel:
                final WritingDialog writingDialog = new WritingDialog(this);
                writingDialog.show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onPost(String content, File image, String feeling,
                       String state, String latitude, String longitude) {
        WritingRequest request;
        if (image != null) {
            request = new WritingRequest(WritingActivity.this, content, image, snapshotfile, feeling,
                    state, latitude, longitude);
        } else {
            request = new WritingRequest(WritingActivity.this, content, snapshotfile, feeling,
                    state, latitude, longitude);
        }
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<NetworkResultTemp>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<NetworkResultTemp>> request, NetworkResult<NetworkResultTemp> result) {
                Toast.makeText(WritingActivity.this, "" + result.getResult().getMessage() + result.getResult().getCok_id(), Toast.LENGTH_SHORT).show();
                if (file != null) {
                    file.delete();
                    snapshotfile.delete();
                }
                finish();
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<NetworkResultTemp>> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(WritingActivity.this, errorMessage.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            return;
        }

        this.googleMap = googleMap;
        UiSettings uiSettings = googleMap.getUiSettings();
        uiSettings.setZoomGesturesEnabled(false);   //줌 컨트롤
        uiSettings.setScrollGesturesEnabled(false); //스크롤 컨트롤
        uiSettings.setTiltGesturesEnabled(false);   //틸트??
        uiSettings.setRotateGesturesEnabled(false); //회전 컨트롤
        uiSettings.setMapToolbarEnabled(false);     //길찾기, 현재위치 등등 툴바 컨트롤
        LSB lsb = new LSB(this);
        Location location = lsb.onLcation();
        Log.d("location", "" + location.getLatitude() + "  " + location.getLongitude());
        final LatLng SEOUL = new LatLng(location.getLatitude(), location.getLongitude());
        Marker seoul = googleMap.addMarker(new MarkerOptions().position(SEOUL)
                .title("이글의 위치"));

        this.googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SEOUL, 15));
        this.googleMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
    }

    void onSnapshot() {
        if (maplayout.getVisibility() != View.GONE) {
            googleMap.snapshot(new GoogleMap.SnapshotReadyCallback() {
                @Override
                public void onSnapshotReady(Bitmap snapshot) {
                    if (snapshot == null)
                        Toast.makeText(WritingActivity.this, "null", Toast.LENGTH_SHORT).show();
                    else {
                        snapshotfile = new File(getCacheDir(), "location.jpeg");
                        OutputStream out = null;
                        try {
                            snapshotfile.createNewFile();
                            out = new FileOutputStream(snapshotfile);
                            snapshot.compress(Bitmap.CompressFormat.JPEG, 100, out);
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            try {
                                out.close();
                                snapshotcheck = true;
                                Toast.makeText(WritingActivity.this, "saved.",
                                        Toast.LENGTH_SHORT).show();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            });
        }
    }
}
