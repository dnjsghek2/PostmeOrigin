package postme.tacademy.com.postme.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import postme.tacademy.com.postme.fragment.MapFragment;
import postme.tacademy.com.postme.R;

public class MapDialog extends Dialog {

    String contents;

    public MapDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.d_map);
        TextView ok_btn = (TextView)findViewById(R.id.postOk);
        ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MapFragment.fab.show();
                MapFragment.checkitem = new boolean[] {true, false, false, false};
                MapFragment.menu.getItem(0).setVisible(MapFragment.checkitem[0]);
                MapFragment.menu.getItem(1).setVisible(MapFragment.checkitem[1]);
                MapFragment.menu.getItem(2).setVisible(MapFragment.checkitem[2]);
                MapFragment.menu.getItem(2).setVisible(MapFragment.checkitem[3]);
                MapDialog.this.dismiss();
            }
        });
        TextView Cancel_btn = (TextView) findViewById(R.id.postCancel);
        Cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MapDialog.this.dismiss();
            }
        });
    }
}
