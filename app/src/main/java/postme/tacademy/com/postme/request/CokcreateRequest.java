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

/**
 * Created by wonhochoi on 16. 9. 6..
 */
public class CokcreateRequest extends AbstractRequest<NetworkResult<Message>> {
    Request request;

    public CokcreateRequest(Context context, String cokname, String address, String Latitude, String Longitude) {
        HttpUrl url = getBaseUrlBuilder()
                .addEncodedPathSegments("coks")
                .build();
        RequestBody body = new FormBody.Builder()
                .add("address", address)
                .add("cokname", cokname)
                .add("latitude", Latitude)
                .add("longitude", Longitude)
                .build();
        request = new Request.Builder()
                .url(url)
                .post(body)
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
