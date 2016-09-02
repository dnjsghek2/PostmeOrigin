package postme.tacademy.com.postme.data;

/**
 * Created by wonhochoi on 16. 9. 2..
 */
public class PostList
{
    private Post[] post;

    private String itemPerPage;

    private String currentPage;

    private String totalPage;

    public Post[] getPost ()
    {
        return post;
    }

    public void setPost (Post[] post)
    {
        this.post = post;
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
        return "ClassPojo [post = "+post+", itemPerPage = "+itemPerPage+", currentPage = "+currentPage+", totalPage = "+totalPage+"]";
    }
}