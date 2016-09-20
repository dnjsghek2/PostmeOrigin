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
import postme.tacademy.com.postme.data.User;

/**
 * Created by wonhochoi on 16. 9. 20..
 */
public class IdcheckRequest extends AbstractRequest<NetworkResult<Message>> {
    Request request;

    public IdcheckRequest(Context context, String nick) {
        HttpUrl url = getBaseUrlBuilder()
                .addPathSegments("users")
                .build();
        request = new Request.Builder()
                .url(url+"?nickname="+nick)
                .build();
    }
    @Override
    protected Type getType() {
        return new TypeToken<NetworkResult<Message>>() {
        }.getType();
    }

    @Override
    public Request getRequest() {
        return request;
    }
}