package postme.tacademy.com.postme.data;

/**
 * Created by wonhochoi on 16. 8. 31..
 */
public class CokList {
    private Cok[] cok;
    private int itemPerPage;
    private int currentPage;
    private int totalPage;

    public Cok[] getCok() {
        return cok;
    }
    public int getItemPerPage() {
        return itemPerPage;
    }
    public int getCurrentPage() {
        return currentPage;
    }
    public int getTotalPage() {
        return totalPage;
    }

    @Override
    public String toString() {
        return "ClassPojo [cok = " + cok + ", itemPerPage = " + itemPerPage + ", currentPage = " + currentPage + ", totalPage = " + totalPage + "]";
    }
}
