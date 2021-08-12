public class Main {

    public static void main(String[] args) {
        Person person = new Person("Jane");
        person.setAge(18);

        Car car = new Car("Opel Vivaro", 2020);
        car.setMileage(1000);
        car.setOwner(person);
        System.out.println(car);

    }
}
