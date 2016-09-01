package postme.tacademy.com.postme.request;

import android.content.Context;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import okhttp3.HttpUrl;
import okhttp3.Request;
import postme.tacademy.com.postme.data.CokList;
import postme.tacademy.com.postme.data.NetworkResult;

/**
 * Created by wonhochoi on 16. 8. 31..
 */
public class Map_Request extends AbstractRequest<NetworkResult<CokList>>{

    Request request;
    public Map_Request(Context context, String latitude, String  longitude, int currentPage, int itemPerPage){
        HttpUrl url = getBaseUrlBuilder()
                .build();
        String getUrl = "coks?"
                +"latitude="+latitude+"&longitude="+longitude
                +"&currentPage="+currentPage +"&itemPerPage="+itemPerPage;
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
