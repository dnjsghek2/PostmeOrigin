package postme.tacademy.com.postme.request;

import android.content.Context;

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
 * Created by wonhochoi on 16. 9. 2..
 */
public class UserInfoRequest extends AbstractRequest<NetworkResult<Message>>{
    Request request;
    public UserInfoRequest(Context context, String nickname, String  gender, String birth, String email){
        HttpUrl url = getBaseUrlBuilder()
                .addEncodedPathSegment("users")
                .build();
        RequestBody body = new FormBody.Builder()
                .add("email", email)
                .add("birth", birth)
                .add("gender", gender)
                .add("nickname",nickname)
                .build();
        request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
    }

    @Override
    protected Type getType() {
        return new TypeToken<NetworkResult<Message>>(){}.getType();
    }

    @Override
    public Request getRequest() {
        return request;
    }
}
