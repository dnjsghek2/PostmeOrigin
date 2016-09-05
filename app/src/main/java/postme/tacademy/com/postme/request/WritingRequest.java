package postme.tacademy.com.postme.request;

import android.content.Context;
import android.media.Image;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import postme.tacademy.com.postme.data.NetworkResult;
import postme.tacademy.com.postme.data.NetworkResultTemp;
import postme.tacademy.com.postme.data.User;

/**
 * Created by wonhochoi on 16. 9. 5..
 */

public class WritingRequest extends AbstractRequest<NetworkResult<NetworkResultTemp>> {
    Request request;
    public WritingRequest(Context context, String cok_id, String nickname, String content, Image image, String feeling,
                          String state, String latitude, String longitude){
        HttpUrl url = getBaseUrlBuilder()
                .addPathSegments("posts")
                .build();
/*
        RequestBody body = new FormBody.Builder()
                .add("cok_id", cok_id)
                .add("nickname", nickname)
                .add("body", content)
                */
/*.add("Image", "null")*//*

                .add("feeling", feeling)
                .add("state", state)
                .add("latitude", latitude)
                .add("longitude", longitude)
                .build();
*/

        MultipartBody.Builder multipartBody = new MultipartBody.Builder()
                .addFormDataPart("cok_id", cok_id)
                .addFormDataPart("nickname", nickname)
                .addFormDataPart("body", content)
                /*.add("Image", "null")*/
                .addFormDataPart("feeling", feeling)
                .addFormDataPart("state", state)
                .addFormDataPart("latitude", latitude)
                .addFormDataPart("longitude", longitude);

        RequestBody body = multipartBody.build();



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
        return new TypeToken<NetworkResult<NetworkResultTemp>>(){}.getType();
    }
}
