package postme.tacademy.com.postme.data;

/**
 * Created by wonhochoi on 16. 8. 31..
 */
public class Cok {
    private int cok_id;
    private String cok_name;
    private String address;
    private double longitude;
    private double latitude;
    private int post_count;

    public int getPost_count() {return post_count;}

    public int getCok_id() {
        return cok_id;
    }

    public void setCok_id(int cok_id) {
        this.cok_id = cok_id;
    }

    public String getCok_name() {
        return cok_name;
    }

    public void setCok_name(String cok_name) {
        this.cok_name = cok_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "ClassPojo [cok_id = " + cok_id + ", cok_name = " + cok_name + ", address = " + address + ", longitude = " + longitude + ", latitude = " + latitude + "]";
    }
}
