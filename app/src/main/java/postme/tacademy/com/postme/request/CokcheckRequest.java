package postme.tacademy.com.postme.request;

import android.content.Context;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import okhttp3.HttpUrl;
import okhttp3.Request;
import postme.tacademy.com.postme.data.HistoryList;
import postme.tacademy.com.postme.data.Message;
import postme.tacademy.com.postme.data.NetworkResult;

/**
 * Created by wonhochoi on 16. 9. 6..
 */
public class CokcheckRequest extends AbstractRequest<NetworkResult<Message>>{
    Request request;
    public CokcheckRequest(Context context, String Latitude, String Longitude){
        HttpUrl url = getBaseUrlBuilder()
                .build();
        String getUrl = "posts?"
                +"latitude="+Latitude +"&longitude="+Longitude;
        request = new Request.Builder()
                .url(url+getUrl)
                .build();
    }

    @Override
    protected Type getType() {
        return new TypeToken<NetworkResult<Message>>(){}.getType();
    }

    @Override
    public Request getRequest() {
        return request;
    }
}
