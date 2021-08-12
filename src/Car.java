public class Car {
    private final String brand;
    private final int prodDate;
    private double mileage;
    private Person owner;

    public Car(String brand, int prodDate) {
        this.brand = brand;
        this.prodDate = prodDate;
    }

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

    public int getProdDate() {
        return prodDate;
    }

    public double getMileage() {
        return mileage;
    }

    public void setMileage(double mileage) {
        this.mileage = mileage;
    }
}
