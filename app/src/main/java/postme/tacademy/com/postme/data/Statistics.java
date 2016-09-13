package postme.tacademy.com.postme.data;

/**
 * Created by Monkey on 2016. 9. 12..
 */
public class Statistics
{
    private StatisticsAge age;

    private StatisticsGender gender;

    public StatisticsAge getAge ()
    {
        return age;
    }

    public void setAge (StatisticsAge age)
    {
        this.age = age;
    }

    public StatisticsGender getGender ()
    {
        return gender;
    }

    public void setGender (StatisticsGender gender)
    {
        this.gender = gender;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [age = "+age+", gender = "+gender+"]";
    }
}