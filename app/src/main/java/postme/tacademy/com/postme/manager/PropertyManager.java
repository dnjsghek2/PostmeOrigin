package postme.tacademy.com.postme.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import postme.tacademy.com.postme.PostMeApplication;

/**
 * Created by Administrator on 2016-08-10.
 */
public class PropertyManager {
    private static PropertyManager instance;
    public static PropertyManager getInstance() {
        if (instance == null) {
            instance = new PropertyManager();
        }
        return instance;
    }

    SharedPreferences mPrefs;
    SharedPreferences.Editor mEditor;

    private static final String KEY_REGISTRATION_ID = "regid";
    private static final String KEY_FACEBOOK_ID = "facebookid";

    private PropertyManager() {
        Context context = PostMeApplication.getInstance();
        mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        mEditor = mPrefs.edit();
    }

    public void setRegistrationId(String regid) {
        mEditor.putString(KEY_REGISTRATION_ID, regid);
        mEditor.commit();
    }

    public String getRegistrationId() {
        return mPrefs.getString(KEY_REGISTRATION_ID, "");
    }

    public void setFacebookId(String facebookid) {
        mEditor.putString(KEY_FACEBOOK_ID, facebookid);
        mEditor.commit();
    }

    public String getFacebookId() {
        return mPrefs.getString(KEY_FACEBOOK_ID, "");
    }
}