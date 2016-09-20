package postme.tacademy.com.postme.data;

/**
 * Created by Monkey on 2016. 9. 20..
 */

public class Push
{
    private String message;

    public String getMessage ()
    {
        return message;
    }

    public void setMessage (String message)
    {
        this.message = message;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [message = "+message+"]";
    }
}