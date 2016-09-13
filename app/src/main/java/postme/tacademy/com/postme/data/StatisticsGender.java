package postme.tacademy.com.postme.data;

/**
 * Created by Monkey on 2016. 9. 12..
 */
public class StatisticsGender
{
    private String female;

    private String male;

    public String getFemale ()
    {
        return female;
    }

    public void setFemale (String female)
    {
        this.female = female;
    }

    public String getMale ()
    {
        return male;
    }

    public void setMale (String male)
    {
        this.male = male;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [female = "+female+", male = "+male+"]";
    }
}
