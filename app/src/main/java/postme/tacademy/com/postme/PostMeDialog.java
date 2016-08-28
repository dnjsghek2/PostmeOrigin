package postme.tacademy.com.postme;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

public class PostMeDialog extends Dialog {

    String contents;
    public PostMeDialog(Context context) {
        super(context);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog);
        Button ok_btn = (Button)findViewById(R.id.postOk);
        ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        Button Cancel_btn = (Button)findViewById(R.id.postCancel);
        Cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PostMeDialog.this.dismiss();
            }
        });

        Toast.makeText(getContext(), contents, Toast.LENGTH_SHORT).show();
    }

    public void setContents(String contents){
        this.contents = contents;
    }
}
