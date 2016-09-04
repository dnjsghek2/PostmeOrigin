package postme.tacademy.com.postme.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

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
        Button ok_btn = (Button) findViewById(R.id.postOk);
        ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MapFragment.fab.show();
                MapFragment.menu.getItem(0).setVisible(true);
                MapFragment.menu.getItem(1).setVisible(false);
                MapFragment.menu.getItem(2).setVisible(false);
                MapDialog.this.dismiss();
            }
        });
        Button Cancel_btn = (Button) findViewById(R.id.postCancel);
        Cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MapDialog.this.dismiss();
            }
        });
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}
