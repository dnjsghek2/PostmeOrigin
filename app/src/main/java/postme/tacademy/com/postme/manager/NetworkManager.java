package postme.tacademy.com.postme.manager;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManagerFactory;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import postme.tacademy.com.postme.PostMeApplication;
import postme.tacademy.com.postme.R;
import postme.tacademy.com.postme.request.NetworkRequest;

/**
 * Created by Administrator on 2016-08-09.
 */
public class NetworkManager {
    private static NetworkManager instance;

    public static NetworkManager getInstance() {  //생성된 NetworkManager 가 있는지 확인후 없으면 생성(싱글)
        if (instance == null) {
            instance = new NetworkManager();
        }
        return instance;
    }

    OkHttpClient client;

    private NetworkManager() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();  //클라이언트의 빌더 객체 생성
        Context context = PostMeApplication.getInstance();               //NetworkManager를 호출한 Context의 정보를 얻기
        disableCertificateValidation(context, builder);
        ClearableCookieJar cookieJar =                              //쿠키 관리 메소드 생성
                new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(context));
        builder.cookieJar(cookieJar);                               //쿠키 설정

        File cachDir = new File(context.getCacheDir(), "network");  //현재 실행되고 있는 앱의 위치에 파일을 path를 지정
        if (!cachDir.exists()) {                                    //파일이 있는지 검사(메모리상 파일o 실제 파일x)
            cachDir.mkdir();                                        //파일이 없다면 파일 생성
        }

        Cache cache = new Cache(cachDir, 10 * 1024 * 1024);         //10*1024*1024크기의 캐시 생성
        builder.cache(cache);                                       //빌더에 캐시 설정

        builder.connectTimeout(30, TimeUnit.SECONDS);               //빌더에 타임아웃 설정
        builder.readTimeout(10, TimeUnit.SECONDS);
        builder.writeTimeout(10, TimeUnit.SECONDS);

        client = builder.build();                                   // client라는 OkHttpClient객체에 빌더 설정;

    }

    public OkHttpClient getClient() {
        return client;
    }

    private static final int MESSAGE_SUCCESS = 1;                   //네트워크 통신시 성공과 실패 플래그(서버에서 성공은 1, 실패는 2 리턴됨)
    private static final int MESSAGE_FAIL = 2;

    Handler mHandler = new Handler(Looper.getMainLooper()) {        //백그라운드를 위해 핸들러 생성(네트워크 통신시 )
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            NetworkRequest<?> request = (NetworkRequest<?>) msg.obj;
            switch (msg.what) {                                     //성공 실패 여부 확인
                case MESSAGE_SUCCESS:                               //통신 성공시 실행
                    request.sendSuccess();
                    break;
                case MESSAGE_FAIL:                                  //통신 실패시 실행
                    request.sendFail();
                    break;
            }
        }
    };

    public interface OnResultListener<T> {                          // 옵져버 적용을 위한 인터페이스
        public void onSuccess(NetworkRequest<T> request, T result);

        public void onFail(NetworkRequest<T> request, int errorCode, String errorMessage, Throwable e);
    }

    public void sendSuccess(NetworkRequest<?> request) {
        Message msg = mHandler.obtainMessage(MESSAGE_SUCCESS, request); //http://promobile.tistory.com/214 참고
        mHandler.sendMessage(msg);
    }

    public void sendFail(NetworkRequest<?> request) {                      //request에 실패시
        Message msg = mHandler.obtainMessage(MESSAGE_FAIL, request);
        mHandler.sendMessage(msg);
    }

    public <T> void getNetworkData(NetworkRequest<T> request, OnResultListener<T> listener) {
        request.setOnResultListener(listener);
        request.process(client);                        //request라는 NetworkRequset의 process 메소드 호출
    }

    public void cancelAll() {
        client.dispatcher().cancelAll();
    }

    public void cancelAll(Object tag) {
        Dispatcher dispatcher = client.dispatcher();
        List<Call> list = dispatcher.queuedCalls();
        for (Call call : list) {
            if (call.request().tag().equals(tag)) {
                call.cancel();
            }
        }
        list = dispatcher.runningCalls();
        for (Call call : list) {
            if (call.request().tag().equals(tag)) {
                call.cancel();
            }
        }
    }

    static void disableCertificateValidation(Context context, OkHttpClient.Builder builder) {
        try {
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            InputStream caInput = context.getResources().openRawResource(R.raw.site);
            Certificate ca;
            try {
                ca = cf.generateCertificate(caInput);
                System.out.print("ca=" + ((X509Certificate) ca).getSubjectDN());
            } finally {
                caInput.close();
            }
            String keyStoreType = KeyStore.getDefaultType();
            KeyStore keyStore = KeyStore.getInstance(keyStoreType);
            keyStore.load(null, null);
            keyStore.setCertificateEntry("ca", ca);
            String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
            tmf.init(keyStore);

            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, tmf.getTrustManagers(), null);
            HostnameVerifier hv = new HostnameVerifier() {
                @Override
                public boolean verify(String s, SSLSession sslSession) {return true;}
            };
            sc.init(null, tmf.getTrustManagers(), null);
            builder.sslSocketFactory(sc.getSocketFactory());
            builder.hostnameVerifier(hv);
        }
        catch (IOException e) {e.printStackTrace();}
        catch (CertificateException e) {e.printStackTrace();}
        catch (NoSuchAlgorithmException e) {e.printStackTrace();}
        catch (KeyStoreException e) {e.printStackTrace();}
        catch (KeyManagementException e) {e.printStackTrace();}
    }

}