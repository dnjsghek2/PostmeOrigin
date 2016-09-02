package postme.tacademy.com.postme.request;


import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;


import okhttp3.HttpUrl;
import okhttp3.ResponseBody;
import postme.tacademy.com.postme.data.NetworkResult;
import postme.tacademy.com.postme.data.NetworkResultTemp;
import postme.tacademy.com.postme.fragment.MapFragment;

/**
 * Created by Administrator on 2016-08-09.
 */
public abstract class AbstractRequest<T> extends NetworkRequest<T> {
    String TAG_D = "AbstractRequest";
    protected HttpUrl.Builder getBaseUrlBuilder() {
        HttpUrl.Builder builder = new HttpUrl.Builder();
        builder.scheme("https");
        builder.host("ec2-52-78-91-199.ap-northeast-2.compute.amazonaws.com"); //연결될 서버
        return builder;
    }

    @Override
    protected T parse(ResponseBody body) throws IOException {
        String text = body.string();
        Log.d(TAG_D, text);
        Gson gson = new Gson();
        NetworkResultTemp temp = gson.fromJson(text, NetworkResultTemp.class);
        if (temp.getMessage() != null) {
            T result = gson.fromJson(text, getType());
            return result;
        } else if (temp.getError() == null) {
            T result = gson.fromJson(text, getType());
            return result;
        } else {
            Type type = new TypeToken<NetworkRequest<String>>() {
            }.getType();
            NetworkResult<String> result = gson.fromJson(text, type);
            throw new IOException(result.getResult());

        }
    }
    protected abstract Type getType();
}