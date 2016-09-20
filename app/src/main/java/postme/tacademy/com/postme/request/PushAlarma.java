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
import postme.tacademy.com.postme.data.Push;

/**
 * Created by Monkey on 2016. 9. 20..
 */

public class PushAlarma extends AbstractRequest<NetworkResult<Message>> {
    Request request;

    public PushAlarma(Context context, String post_id) {
        HttpUrl url = getBaseUrlBuilder()
                .addPathSegments("alarms")
                .build();
        RequestBody body = new FormBody.Builder()
                .add("post_id", post_id)
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
        return new TypeToken<NetworkResult<Push>>() {
        }.getType();
    }
}