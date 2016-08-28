package postme.tacademy.com.postme;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by wonhochoi on 2016. 8. 26..
 */
public class Dialog extends android.app.Dialog{
    String Contents;
    LayoutInflater inflater;

    public Dialog(Context context) {
        super(context);
    }

    public Dialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected Dialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public void DialogBuilder(Context context, String Contents){
        inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(view);
        builder.setNegativeButton("", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                return;
            }
        });
    }

}
