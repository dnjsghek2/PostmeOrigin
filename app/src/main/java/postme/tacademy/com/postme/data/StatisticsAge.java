package postme.tacademy.com.postme.data;

/**
 * Created by Monkey on 2016. 9. 12..
 */
public class StatisticsAge
{
    private String ages_10;

    private String ages_40;

    private String ages_50;

    private String ages_30;

    private String ages_20;

    public String getAges_10 ()
    {
        return ages_10;
    }

    public void setAges_10 (String ages_10)
    {
        this.ages_10 = ages_10;
    }

    public String getAges_40 ()
    {
        return ages_40;
    }

    public void setAges_40 (String ages_40)
    {
        this.ages_40 = ages_40;
    }

    public String getAges_50 ()
    {
        return ages_50;
    }

    public void setAges_50 (String ages_50)
    {
        this.ages_50 = ages_50;
    }

    public String getAges_30 ()
    {
        return ages_30;
    }

    public void setAges_30 (String ages_30)
    {
        this.ages_30 = ages_30;
    }

    public String getAges_20 ()
    {
        return ages_20;
    }

    public void setAges_20 (String ages_20)
    {
        this.ages_20 = ages_20;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [ages_10 = "+ages_10+", ages_40 = "+ages_40+", ages_50 = "+ages_50+", ages_30 = "+ages_30+", ages_20 = "+ages_20+"]";
    }
}