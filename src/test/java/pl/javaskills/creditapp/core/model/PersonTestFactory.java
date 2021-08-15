package pl.javaskills.creditapp.core.model;

public class PersonTestFactory {
    public static Person create(double totalMonthlyIncomePLN, int numOfFamilyDependants, Education education, MaritalStatus maritalStatus){
        PersonalData personalData = new PersonalData("test", "test", "test");
        personalData.setTotalMonthlyIncomePLN(totalMonthlyIncomePLN);
        personalData.setNumOfFamilyDependants(numOfFamilyDependants);
        personalData.setEducation(education);
        personalData.setMaritalStatus(maritalStatus);

        return new Person(personalData, null);
    }
}
