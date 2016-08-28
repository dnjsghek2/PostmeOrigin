package postme.tacademy.com.postme;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;

/**
 * Created by wonhochoi on 2016. 8. 26..
 */

public class PostMeDialog extends Dialog {

    public PostMeDialog(Context context) {
        super(context);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog);

    }
}