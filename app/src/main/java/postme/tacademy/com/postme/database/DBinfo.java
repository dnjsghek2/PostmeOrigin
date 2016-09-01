package postme.tacademy.com.postme.database;

/**
 * Created by Monkey on 2016. 9. 1..
 */
public class DBinfo {
    int push_id;
    String content;
    String create_date;

    public int getPush_id() {
        return push_id;
    }

    public void setPush_id(int push_id) {
        this.push_id = push_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }
}
