package pl.javaskills.creditapp.core.model;

public class FamilyMember implements Comparable<FamilyMember> {
    private final String name;
    private final Integer age;

    private FamilyMember(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public static class Builder {
        private String name;
        private Integer age;

        private Builder() {
        }

        public static Builder create() {
            return new Builder();
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withAge(int age) {
            this.age = age;
            return this;
        }

        public FamilyMember build() {
            return new FamilyMember(name, age);
        }

    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    @Override
    public String toString() {
        return name + ", " + age;
    }

    @Override
    public int compareTo(FamilyMember o) {
        return o.age.compareTo(age);
    }
}
