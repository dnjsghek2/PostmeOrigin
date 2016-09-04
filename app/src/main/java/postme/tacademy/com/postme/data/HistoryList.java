package postme.tacademy.com.postme.data;

/**
 * Created by Monkey on 2016. 9. 4..
 */
public class HistoryList
{
    private History[] history;

    private String itemPerPage;

    private String currentPage;

    private String totalPage;

    public History[] getHistory ()
    {
        return history;
    }

    public void setHistory (History[] history)
    {
        this.history = history;
    }

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

    @Override
    public String toString()
    {
        return "ClassPojo [history = "+history+", itemPerPage = "+itemPerPage+", currentPage = "+currentPage+", totalPage = "+totalPage+"]";
    }
}