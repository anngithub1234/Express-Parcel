import java.util.Objects;

public class Customer {
    private int cusId;
    private String cusName;
    private String distri;
    private float locX;
    private float locY;


    public Customer(int cusId, String cusName, String distri, float locX, float locY) {
        this.cusId = cusId;
        this.cusName = cusName;
        this.distri = distri;
        this.locX = locX;
        this.locY = locY;
    }

    public int getCusId() {
        return cusId;
    }

    public String getCusName() {
        return cusName;
    }

    public String getDistri() {
        return distri;
    }

    public float getLocX() {
        return locX;
    }

    public float getLocY() {
        return locY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return cusId == customer.cusId && Float.compare(customer.locX, locX) == 0 && Float.compare(customer.locY, locY) == 0 && Objects.equals(cusName, customer.cusName) && Objects.equals(distri, customer.distri);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cusId, cusName, distri, locX, locY);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "cusId=" + cusId +
                ", cusName='" + cusName + '\'' +
                ", distri='" + distri + '\'' +
                ", locX=" + locX +
                ", locY=" + locY +
                '}';
    }
}

