package pl.javaskills.creditapp.core;

public class Person {
    private final String name;
    private final String lastName;
    private final String mothersMaidenName;
    private double totalMonthlyIncomePLN;
    private boolean married;
    private int numOfFamilyDependants;

    public Person(String name, String lastName, String mothersMaidenName) {
        this.name = name;
        this.lastName = lastName;
        this.mothersMaidenName = mothersMaidenName;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMothersMaidenName() {
        return mothersMaidenName;
    }

    public double getTotalMonthlyIncomePLN() {
        return totalMonthlyIncomePLN;
    }

    public void setTotalMonthlyIncomePLN(double totalMonthlyIncomePLN) {
        this.totalMonthlyIncomePLN = totalMonthlyIncomePLN;
    }

    public boolean isMarried() {
        return married;
    }

    public void setMarried(boolean married) {
        this.married = married;
    }

    public int getNumOfFamilyDependants() {
        return numOfFamilyDependants;
    }

    public void setNumOfFamilyDependants(int numOfFamilyDependants) {
        this.numOfFamilyDependants = numOfFamilyDependants;
    }
}
