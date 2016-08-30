package postme.tacademy.com.postme.data;

public class NetworkResult<T> {
    private T result;
    private int code;
    private String message;

    public String getMessage() {return message;}
    public T getResult() {
        return this.result;
    }
    public int getCode() {
        return this.code;
    }
}
