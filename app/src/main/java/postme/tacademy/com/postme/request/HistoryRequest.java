package postme.tacademy.com.postme.request;

import android.content.Context;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import okhttp3.HttpUrl;
import okhttp3.Request;
import postme.tacademy.com.postme.data.HistoryList;
import postme.tacademy.com.postme.data.NetworkResult;

/**
 * Created by Monkey on 2016. 9. 4..
 */
public class HistoryRequest extends AbstractRequest<NetworkResult<HistoryList>>{
    Request request;
    public HistoryRequest(Context context, int currentPage, int itemPerPage){
        HttpUrl url = getBaseUrlBuilder()
                .build();
        String getUrl = "users/me/posts?"
                +"&currentPage="+currentPage +"&itemPerPage="+itemPerPage;
        request = new Request.Builder()
                .url(url+getUrl)
                .build();
    }


    @Override
    protected Type getType() {
        return new TypeToken<NetworkResult<HistoryList>>(){}.getType();
    }

    @Override
    public Request getRequest() {
        return request;
    }
}
