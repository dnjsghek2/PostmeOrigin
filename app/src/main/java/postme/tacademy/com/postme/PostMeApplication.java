package postme.tacademy.com.postme;

import android.app.Application;
import android.content.Context;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

/**
 * Created by wonhochoi on 2016. 8. 28..
 */
public class PostMeApplication extends Application {
    static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        FacebookSdk.sdkInitialize(this);
        AppEventsLogger.activateApp(this);
        context = this;
    }

    public static Context getContext() {
        return context;
    }
}
