package postme.tacademy.com.postme.request;

import android.content.Context;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import okhttp3.HttpUrl;
import okhttp3.Request;
import postme.tacademy.com.postme.data.CokList;
import postme.tacademy.com.postme.data.NetworkResult;
import postme.tacademy.com.postme.data.Statistics;

/**
 * Created by Monkey on 2016. 9. 12..
 */
public class StatisticsRequest extends AbstractRequest<NetworkResult<Statistics>>{
    Request request;
    public StatisticsRequest(Context context, int cokid){
        HttpUrl url = getBaseUrlBuilder()
                .build();
        String getUrl = "coks/" + cokid + "/statistics";
        request = new Request.Builder()
                .url(url+getUrl)
                .build();
    }


    @Override
    protected Type getType() {
        return new TypeToken<NetworkResult<Statistics>>(){}.getType();
    }

    @Override
    public Request getRequest() {
        return request;
    }
}