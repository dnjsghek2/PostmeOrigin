package postme.tacademy.com.postme.request;

import android.content.Context;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;
import postme.tacademy.com.postme.data.NetworkResult;
import postme.tacademy.com.postme.data.User;

/**
 * Created by wonhochoi on 2016. 8. 30..
 */
public class LoginRequest extends AbstractRequest<NetworkResult<User>> {
    Request request;
    public LoginRequest(Context context, String email, String password, String name){
        HttpUrl url = getBaseUrlBuilder()
                .addPathSegments("auth/local")
                .build();
        RequestBody body = new FormBody.Builder()
                .add("email", email)
                .add("password", password)
                .add("name", name)
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
        return new TypeToken<NetworkResult<User>>(){}.getType();
    }
}
