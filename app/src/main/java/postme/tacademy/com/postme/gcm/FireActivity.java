package postme.tacademy.com.postme.gcm;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import postme.tacademy.com.postme.R;

public class FireActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gcm);

        Button btn = (Button)findViewById(R.id.btn_subs);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContextCompat.getNoBackupFilesDir(FireActivity.this);
                FirebaseMessaging.getInstance().subscribeToTopic("news");
            }
        });
        if (PropertyManager.getInstance().getRegistrationToken().equals("")) {
            registerToken();
        } else {
            doRealStart();
        }
    }

    private void registerToken() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                String refreshedToken = FirebaseInstanceId.getInstance().getToken();
                PropertyManager.getInstance().setRegistrationToken(refreshedToken);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                doRealStart();
            }
        }.execute();
    }

    private void doRealStart() {

    }
}
