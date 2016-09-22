package postme.tacademy.com.postme.request;

import android.content.Context;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import okhttp3.HttpUrl;
import okhttp3.Request;
import postme.tacademy.com.postme.data.JjimList;
import postme.tacademy.com.postme.data.Message;
import postme.tacademy.com.postme.data.NetworkResult;

/**
 * Created by wonhochoi on 2016. 9. 21..
 */

public class PostdeleteRequest extends AbstractRequest<NetworkResult<Message>> {

    Request request;

    public PostdeleteRequest(Context context, String postid) {
        HttpUrl url = getBaseUrlBuilder()
                .build();
        String getUrl = "users/me/posts";
        request = new Request.Builder()
                .url(url + getUrl+"/" + postid)
                .delete()
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
