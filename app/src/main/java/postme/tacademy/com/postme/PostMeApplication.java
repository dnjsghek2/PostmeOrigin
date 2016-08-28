package postme.tacademy.com.postme;

import android.app.Application;
import android.content.Context;

/**
 * Created by wonhochoi on 2016. 8. 28..
 */
public class PostMeApplication extends Application {
    static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static Context getContext() {
        return context;
    }
}
