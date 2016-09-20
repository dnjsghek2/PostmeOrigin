package postme.tacademy.com.postme.gcm;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import postme.tacademy.com.postme.PostMeApplication;

/**
 * Created by Administrator on 2016-09-19.
 */
public class PropertyManager {
    private static PropertyManager instance;
    public static PropertyManager getInstance() {
        if (instance == null) {
            instance =new PropertyManager();
        }
        return instance;
    }
    SharedPreferences mPrefs;
    SharedPreferences.Editor mEditor;
    private PropertyManager() {
        mPrefs = PreferenceManager.getDefaultSharedPreferences(PostMeApplication.getInstance());
        mEditor = mPrefs.edit();
    }

    private static final String PROPERTY_REG_TOKEN = "regtoken";
    public void setRegistrationToken(String token) {
        mEditor.putString(PROPERTY_REG_TOKEN, token);
        mEditor.commit();
    }
    public String getRegistrationToken() {
        return mPrefs.getString(PROPERTY_REG_TOKEN, "");
    }
}
