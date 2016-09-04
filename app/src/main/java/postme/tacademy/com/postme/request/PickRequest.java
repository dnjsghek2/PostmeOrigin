package postme.tacademy.com.postme.request;

import android.content.Context;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import okhttp3.HttpUrl;
import okhttp3.Request;
import postme.tacademy.com.postme.data.JjimList;
import postme.tacademy.com.postme.data.NetworkResult;
import postme.tacademy.com.postme.data.PostList;

/**
 * Created by wonhochoi on 16. 9. 4..
 */
public class PickRequest extends AbstractRequest<NetworkResult<JjimList>> {

    Request request;

    public PickRequest(Context context, int currentPage, int itemPerPage) {
        HttpUrl url = getBaseUrlBuilder()
                .build();

        String getUrl = "jjims?"
                + "&currentPage=" + currentPage
                + "&itemPerPage=" + itemPerPage;
        request = new Request.Builder()
                .url(url + getUrl)
                .build();
    }

    @Override
    protected Type getType() {
        return new TypeToken<NetworkResult<JjimList>>() {
        }.getType();
    }

    @Override
    public Request getRequest() {
        return request;
    }
}