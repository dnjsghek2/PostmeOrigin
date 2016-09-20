package postme.tacademy.com.postme.data;

/**
 * Created by wonhochoi on 16. 9. 4..
 */
public class Jjim {
    private String body;
    private String cok_id;
    private String feeling;
    private String state;
    private String image;
    private String post_id;
    private String jjim_id;
    private String ctime;
    private String nickname;
    private String map;
    private int jjim;
    private int jjimcount;

    public int getJjimcount() {
        return jjimcount;
    }

    public void setJjimcount(int jjimcount) {
        this.jjimcount = jjimcount;
    }

    public int getJjim() {
        return jjim;
    }

    public void setJjim(int jjim) {
        this.jjim = jjim;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public String getNickname() {
        return nickname;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getCok_id() {
        return cok_id;
    }

    public void setCok_id(String cok_id) {
        this.cok_id = cok_id;
    }

    public String getFeeling() {
        return feeling;
    }

    public void setFeeling(String feeling) {
        this.feeling = feeling;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public String getJjim_id() {
        return jjim_id;
    }

    public void setJjim_id(String jjim_id) {
        this.jjim_id = jjim_id;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }


    @Override
    public String toString() {
        return "ClassPojo [body = " + body + ", cok_id = " + cok_id + ", feeling = " + feeling + ", state = " + state + ", image = " + image + ", post_id = " + post_id + ", jjim_id = " + jjim_id + ", ctime = " + ctime + "]";
    }
}