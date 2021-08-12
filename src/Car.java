public class Car {
    private String brand;
    private int prodDate;
    private double mileage;
    private Person owner;

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", prodDate=" + prodDate +
                ", mileage=" + mileage +
                ", owner=" + owner +
                '}';
    }

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
