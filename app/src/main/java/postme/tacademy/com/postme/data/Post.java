package postme.tacademy.com.postme.data;

/**
 * Created by wonhochoi on 16. 9. 2..
 */
public class Post
{
    private String body;

    private String nickname;

    private String feeling;

    private String state;

    private String image;

    private String post_id;

    private String ctime;

    public String getBody ()
    {
        return body;
    }

    public void setBody (String body)
    {
        this.body = body;
    }

    public String getNickname ()
    {
        return nickname;
    }

    public void setNickname (String nickname)
    {
        this.nickname = nickname;
    }

    public String getFeeling ()
    {
        return feeling;
    }

    public void setFeeling (String feeling)
    {
        this.feeling = feeling;
    }

    public String getState ()
    {
        return state;
    }

    public void setState (String state)
    {
        this.state = state;
    }

    public String getImage ()
    {
        return image;
    }

    public void setImage (String image)
    {
        this.image = image;
    }

    public String getPost_id ()
    {
        return post_id;
    }

    public void setPost_id (String post_id)
    {
        this.post_id = post_id;
    }

    public String getCtime ()
    {
        return ctime;
    }

    public void setCtime (String ctime)
    {
        this.ctime = ctime;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [body = "+body+", nickname = "+nickname+", feeling = "+feeling+", state = "+state+", image = "+image+", post_id = "+post_id+", ctime = "+ctime+"]";
    }
}