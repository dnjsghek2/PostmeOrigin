package postme.tacademy.com.postme.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import postme.tacademy.com.postme.LSB;
import postme.tacademy.com.postme.R;
import postme.tacademy.com.postme.WritingActivity;
import postme.tacademy.com.postme.data.Message;
import postme.tacademy.com.postme.data.NetworkResult;
import postme.tacademy.com.postme.fragment.MapFragment;
import postme.tacademy.com.postme.manager.NetworkManager;
import postme.tacademy.com.postme.request.CokcheckRequest;
import postme.tacademy.com.postme.request.CokcreateRequest;
import postme.tacademy.com.postme.request.NetworkRequest;

/**
 * Created by wonhochoi on 16. 9. 6..
 */
public class CokCreateDialog extends Dialog {

    String address, latitude, longitude;
    MapFragment mapFragment;

    EditText editCok;
    public CokCreateDialog(Context context, String address, String latitude, String longitude, MapFragment mapFragment) {
        super(context);
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.mapFragment = mapFragment;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.d_cokcreate);
        TextView ok_btn = (TextView) findViewById(R.id.cokOk);
        editCok = (EditText) findViewById(R.id.getCokname);
        ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MapFragment.fab.show();
                MapFragment.menu.getItem(0).setVisible(true);
                MapFragment.menu.getItem(1).setVisible(false);
                MapFragment.menu.getItem(2).setVisible(false);
                MapFragment.menu.getItem(3).setVisible(false);
                String cokname = editCok.getText().toString();
                cokCreate(cokname);
            }
        });
        TextView Cancel_btn = (TextView) findViewById(R.id.cokCancel);
        Cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CokCreateDialog.this.dismiss();
            }
        });
    }

    private void cokCreate(String cokname) {
        CokcreateRequest request = new CokcreateRequest(getContext(), cokname, address, latitude, longitude);
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<Message>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<Message>> request, NetworkResult<Message> result) {
                if (result.getResult().getMessage().equals("1")) {
                    Toast.makeText(getContext(), "콕을 생성하였습니다. 글 작성 페이지로 이동합니다.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getContext(), WritingActivity.class);
                    mapFragment.startActivityForResult(intent, 0);
                    CokCreateDialog.this.dismiss();
                }else{
                    Toast.makeText(getContext(), "다른 콕이 이미 생성 되었습니다. 글 작성 페이지로 이동됩니다.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getContext(), WritingActivity.class);
                    getContext().startActivity(intent);
                    CokCreateDialog.this.dismiss();
                }
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<Message>> request, int errorCode, String errorMessage, Throwable e) {

            }
        });
    }
}
