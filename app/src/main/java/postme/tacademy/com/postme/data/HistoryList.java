package postme.tacademy.com.postme.data;

/**
 * Created by Monkey on 2016. 9. 4..
 */
public class HistoryList
{
    private History[] history;

    private int itemPerPage;

    private int currentPage;

    private int totalpage;

    public History[] getHistory ()
    {
        return history;
    }

    public void setHistory (History[] history)
    {
        this.history = history;
    }

    public int getItemPerPage ()
    {
        return itemPerPage;
    }

    public void setItemPerPage (int itemPerPage)
    {
        this.itemPerPage = itemPerPage;
    }

    public int getCurrentPage ()
    {
        return currentPage;
    }

    public void setCurrentPage (int currentPage)
    {
        this.currentPage = currentPage;
    }

    public int getTotalPage ()
    {
        return totalpage;
    }

    public void setTotalPage (int totalPage)
    {
        this.totalpage = totalPage;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [history = "+history+", itemPerPage = "+itemPerPage+", currentPage = "+currentPage+", totalPage = "+totalpage+"]";
    }
}