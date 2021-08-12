public class Person {
    private String name;
    private int age;

    public Person(){

    }

    public String toString(){
        return name + "," + age;
    }

    public static void StaticMethod(){

    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        name = newName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
