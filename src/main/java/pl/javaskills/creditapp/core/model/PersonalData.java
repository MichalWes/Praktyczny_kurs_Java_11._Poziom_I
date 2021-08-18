package pl.javaskills.creditapp.core.model;

public class PersonalData {

    private final String name;
    private final String lastName;
    private final String mothersMaidenName;
    private MaritalStatus maritalStatus;
    private Education education;
    private int numOfFamilyDependants;

    public PersonalData(String name, String lastName, String mothersMaidenName) {
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

    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public Education getEducation() {
        return education;
    }

    public void setEducation(Education education) {
        this.education = education;
    }

    public int getNumOfFamilyDependants() {
        return numOfFamilyDependants;
    }

    public void setNumOfFamilyDependants(int numOfFamilyDependants) {
        this.numOfFamilyDependants = numOfFamilyDependants;
    }
}
