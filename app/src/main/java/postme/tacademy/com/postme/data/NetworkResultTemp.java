package postme.tacademy.com.postme.data;

/**
 * Created by Administrator on 2016-08-09.
 */
public class NetworkResultTemp {
    private int code;
    private String error;
    String message;
    String cok_id;

    public String getMessage() {
        return message;
    }
    public String getError() {return error;}
    public String getCok_id() {return cok_id;}
    public int getCode() {return code;}
}
