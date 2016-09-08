package postme.tacademy.com.postme.data;

/**
 * Created by Monkey on 2016. 9. 4..
 */
public class History
{
    private String jjimcount;

    private String body;

    private String feeling;

    private String state;

    private String image;

    private String user_id;

    private String post_id;

    private String ctime;

    private String jjim;

    public String getJjimcount ()
    {
        return jjimcount;
    }

    public void setJjimcount (String jjimcount)
    {
        this.jjimcount = jjimcount;
    }

    public String getBody ()
    {
        return body;
    }

    public void setBody (String body)
    {
        this.body = body;
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

    public String getUser_id ()
    {
        return user_id;
    }

    public void setUser_id (String user_id)
    {
        this.user_id = user_id;
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

    public String getJjim ()
    {
        return jjim;
    }

    public void setJjim (String jjim)
    {
        this.jjim = jjim;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [jjimcount = "+jjimcount+", body = "+body+", feeling = "+feeling+", state = "+state+", image = "+image+", user_id = "+user_id+", post_id = "+post_id+", ctime = "+ctime+", jjim = "+jjim+"]";
    }
}