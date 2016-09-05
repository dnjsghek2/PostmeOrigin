package postme.tacademy.com.postme.request;

import android.content.Context;
import android.util.Log;

import com.facebook.AccessToken;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;
import postme.tacademy.com.postme.data.Message;
import postme.tacademy.com.postme.data.NetworkResult;
import postme.tacademy.com.postme.data.NetworkResultTemp;

/**
 * Created by wonhochoi on 16. 9. 1..
 */
public class FacebookLoginRequest extends AbstractRequest<NetworkResult<Message>> {
    Request request;
    public FacebookLoginRequest(Context context, AccessToken token){
        HttpUrl url = getBaseUrlBuilder()
                .addPathSegments("auth/facebook/token")
                .build();
        RequestBody body = new FormBody.Builder()
                .add("access_token", token.getToken())
                .build();
        request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

    }
    @Override
    public Request getRequest() {
        return request;
    }

    @Override
    protected Type getType() {
        return new TypeToken<NetworkResult<Message>>(){}.getType();
    }
}