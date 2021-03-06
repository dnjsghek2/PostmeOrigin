package postme.tacademy.com.postme.request;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;

import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.lang.reflect.Type;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
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

    public WritingRequest(Context context, String content, File image, File map, String feeling,
                          String state, String latitude, String longitude) {
        HttpUrl url = getBaseUrlBuilder()
                .addPathSegments("posts")
                .build();
        MultipartBody.Builder multipartBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("body", content)
                .addFormDataPart("image", map.getName(),(RequestBody.create(MediaType.parse("location/jpeg"), map)))
                .addFormDataPart("image", image.getName(), RequestBody.create(MediaType.parse("image/jpeg"), image))
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

    public WritingRequest(Context context, String content, File map, String feeling,
                          String state, String latitude, String longitude) {
        HttpUrl url = getBaseUrlBuilder()
                .addPathSegments("posts")
                .build();
        MultipartBody.Builder multipartBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("body", content)
                .addFormDataPart("image", map.getName(),(RequestBody.create(MediaType.parse("location/jpeg"), map)))
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
        return new TypeToken<NetworkResult<NetworkResultTemp>>() {
        }.getType();
    }
}
