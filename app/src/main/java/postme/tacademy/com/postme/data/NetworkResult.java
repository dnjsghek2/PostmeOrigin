package postme.tacademy.com.postme.data;

public class NetworkResult<T> {
    private T result;
    private int code;
    private String message;
    private T user;

    public T getUser() {return user;} //local확인을 위한 로컬 제네릭

    public String getMessage() {return message;}
    public T getResult() {
        return this.result;
    }
    public int getCode() {
        return this.code;
    }
}
