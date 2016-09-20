package postme.tacademy.com.postme.request;

import android.content.Context;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import okhttp3.HttpUrl;
import okhttp3.Request;
import postme.tacademy.com.postme.data.CokList;
import postme.tacademy.com.postme.data.NetworkResult;
import postme.tacademy.com.postme.data.PostList;

/**
 * Created by wonhochoi on 16. 9. 2..
 */
public class MapsearchRequest extends AbstractRequest<NetworkResult<CokList>>{
    Request request;
    public MapsearchRequest(Context context, String area, String  keyword){
        HttpUrl url = getBaseUrlBuilder()
                .build();
        String getUrl = "coks?"
                +"area="+area+"&keyword="+keyword;
        request = new Request.Builder()
                .url(url+getUrl)
                .build();
    }


    @Override
    protected Type getType() {
        return new TypeToken<NetworkResult<CokList>>(){}.getType();
    }

    @Override
    public Request getRequest() {
        return request;
    }
}