public class Person {
    private final String name;
    private int age;

    public Person(String name){
        this.name = name;
    }

    public String toString(){
        return name + "," + age;
    }

    public static void StaticMethod(){

    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
