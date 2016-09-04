package postme.tacademy.com.postme.data;

/**
 * Created by wonhochoi on 16. 9. 4..
 */
public class JjimList
{
    private String itemPerPage;

    private String currentPage;

    private String totalPage;

    private Jjim[] jjim;

    public String getItemPerPage ()
    {
        return itemPerPage;
    }

    public void setItemPerPage (String itemPerPage)
    {
        this.itemPerPage = itemPerPage;
    }

    public String getCurrentPage ()
    {
        return currentPage;
    }

    public void setCurrentPage (String currentPage)
    {
        this.currentPage = currentPage;
    }

    public String getTotalPage ()
    {
        return totalPage;
    }

    public void setTotalPage (String totalPage)
    {
        this.totalPage = totalPage;
    }

    public Jjim[] getJjim ()
    {
        return jjim;
    }

    public void setJjim (Jjim[] jjim)
    {
        this.jjim = jjim;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [itemPerPage = "+itemPerPage+", currentPage = "+currentPage+", totalPage = "+totalPage+", jjim = "+jjim+"]";
    }
}