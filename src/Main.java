public class Main {

    public static void main(String[] args) {
        Person person = new Person();
        person.setName("John");
        person.setAge(18);

        Car car = new Car();
        car.setProdDate(2020);
        car.setBrand("Opel Vivaro");
        car.setMileage(1000);
        car.setOwner(person);



        System.out.println(car);

    }
}
