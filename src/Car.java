import java.time.Period;

public class Car {
    private String brand;
    private int prodDate;
    private double mileage;
    private Person owner;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getProdDate() {
        return prodDate;
    }

    public void setProdDate(int prodDate) {
        this.prodDate = prodDate;
    }

    public double getMileage() {
        return mileage;
    }

    public void setMileage(double mileage) {
        this.mileage = mileage;
    }
}
