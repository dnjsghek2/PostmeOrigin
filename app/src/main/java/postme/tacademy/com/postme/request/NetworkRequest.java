package postme.tacademy.com.postme.request;

import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import postme.tacademy.com.postme.manager.NetworkManager;

/**
 * Created by Administrator on 2016-08-09.
 */
public abstract class NetworkRequest<T> implements Callback {
    T result;
    int code;
    String errorMessage;
    Throwable exception;
    NetworkManager.OnResultListener<T> listener;

    public void setOnResultListener(NetworkManager.OnResultListener<T> listener) {
        this.listener = listener;
    }
    public abstract Request getRequest();             //Okhttp3의 Request 메소드 추상화
    protected abstract T parse(ResponseBody body) throws IOException;
    Call call;
    public void process(OkHttpClient client){
        Request request = getRequest();
        call = client.newCall(request);              //클라이언트에 콜백 틍록
        call.enqueue(this);                          //비동기식 통신으로 실행
    }

    public T processSync(OkHttpClient client) throws IOException {          //동기식 통신메서드
        Request request = getRequest();
        call = client.newCall(request);
        Response response = call.execute();
        if (response.isSuccessful()) {
            T result = parse(response.body());
            return result;
        } else {
            throw new IOException("code : " + response.code() + ",message : " + response.message());
        }
    }

    @Override
    public void onFailure(Call call, IOException e) { //통신실패시 실행 메소드
        sendError(-1, e.getMessage(), e);
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {  //통신후 콜백을 위한 메소드
        if (response.isSuccessful()) {
            try {
                sendSuccess(parse(response.body()));        //response받아온 메서드를 파싱시킴
            } catch (IOException e) {
                sendError(-1, e.getMessage(), e);
            }
        } else {
            sendError(response.code(), response.message(), null);
        }
    }

    public void cancel() {
        if (call != null) {
            call.cancel();
        }
    }

    public boolean isCancel() {
        if (call == null) return false;
        return call.isCanceled();
    }

    private void sendSuccess(T result) {
        this.result = result;
        NetworkManager.getInstance().sendSuccess(this);     //NetworkRequest 자기자신
    }

    public void sendSuccess() {
        if (listener != null) {
            listener.onSuccess(this, result);//NetworkRequest 자기자신과 Type을 보냄
        }
    }

    protected void sendError(int code, String errorMessage, Throwable exception) {
        this.code = code;
        this.errorMessage = errorMessage;
        this.exception = exception;
        NetworkManager.getInstance().sendFail(this);
    }

    public void sendFail() {
        if (listener != null) {
            listener.onFail(this, code, errorMessage, exception);
        }
    }
}