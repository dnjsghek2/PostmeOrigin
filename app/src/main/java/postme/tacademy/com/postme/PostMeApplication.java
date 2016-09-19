package postme.tacademy.com.postme;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.kakao.auth.KakaoSDK;

import postme.tacademy.com.postme.adapter.KakaoSDKAdapter;

/**
 * Created by wonhochoi on 2016. 8. 28..
 */
public class PostMeApplication extends Application {
    static PostMeApplication instance;
    private static volatile Activity currentActivity = null;


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        KakaoSDK.init(new KakaoSDKAdapter());
        FacebookSdk.sdkInitialize(this);
        AppEventsLogger.activateApp(this);
    }

    public static Activity getCurrentActivity() {
        return currentActivity;
    }

    public static void setCurrentActivity(Activity currentActivity) {
        instance.currentActivity = currentActivity;
    }

    public static PostMeApplication getInstance() {
        if (instance == null)
            throw new IllegalStateException("this application does not inherit com.kakao.GlobalApplication");
        return instance;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        instance = null;
    }
}
