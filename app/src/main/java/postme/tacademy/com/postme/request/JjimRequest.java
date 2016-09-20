package postme.tacademy.com.postme.request;

import android.content.Context;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;
import postme.tacademy.com.postme.data.NetworkResult;
import postme.tacademy.com.postme.data.NetworkResultTemp;

/**
 * Created by wonhochoi on 16. 9. 11..
 */

public class JjimRequest extends AbstractRequest<NetworkResult<NetworkResultTemp>> {
    Request request;
    HttpUrl url;
    public JjimRequest(Context context, String post_id, boolean check) {
        if (check == true) {
            url = getBaseUrlBuilder()
                    .addEncodedPathSegment("jjims")
                    .build();
            RequestBody body = new FormBody.Builder()
                    .add("post_id", post_id)
                    .build();
            request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();
        } else {
            int postid = Integer.valueOf(post_id);
            url = getBaseUrlBuilder()
                    .addEncodedPathSegment("jjims")
                    .build();
            request = new Request.Builder()
                    .url(url+"/"+postid)
                    .delete()
                    .build();
        }
    }

    @Override
    protected Type getType() {
        return new TypeToken<NetworkResult<NetworkResultTemp>>() {
        }.getType();
    }

    @Override
    public Request getRequest() {
        return request;
    }
}

