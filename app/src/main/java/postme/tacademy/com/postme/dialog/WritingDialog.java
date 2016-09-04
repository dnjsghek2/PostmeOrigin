package postme.tacademy.com.postme.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import postme.tacademy.com.postme.R;
import postme.tacademy.com.postme.WritingActivity;

/**
 * Created by wonhochoi on 2016. 8. 29..
 */
public class WritingDialog extends Dialog {

    String contents;
    Context context;
    public WritingDialog(Context context) {
        super(context);
        this.context = context;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.d_writing);
        Button ok_btn = (Button) findViewById(R.id.writingOk);
        ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((WritingActivity)context).finish();
            }
        });
        Button Cancel_btn = (Button) findViewById(R.id.writingCancel);
        Cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            dismiss();
            }
        });
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}
