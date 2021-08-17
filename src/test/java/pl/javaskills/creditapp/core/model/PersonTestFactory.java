package pl.javaskills.creditapp.core.model;

public class PersonTestFactory {

    public static Person create(MaritalStatus maritalStatus) {
        PersonalData personalData = new PersonalData("test", "test", "test");
        personalData.setTotalMonthlyIncomePLN(5000.0);
        personalData.setNumOfFamilyDependants(2);
        personalData.setEducation(Education.MIDDLE);
        personalData.setMaritalStatus(maritalStatus);

        return new Person(personalData, null);
    }


    public static Person create(double totalMonthlyIncomePLN, byte numOfFamilyDependants) {
        PersonalData personalData = new PersonalData("test", "test", "test");
        personalData.setTotalMonthlyIncomePLN(totalMonthlyIncomePLN);
        personalData.setNumOfFamilyDependants(numOfFamilyDependants);
        personalData.setEducation(Education.MIDDLE);
        personalData.setMaritalStatus(MaritalStatus.SEPARATED);

        return new Person(personalData, null);
    }

    public static Person create(Education education) {
        PersonalData personalData = new PersonalData("test", "test", "test");
        personalData.setTotalMonthlyIncomePLN(5000.0);
        personalData.setNumOfFamilyDependants(2);
        personalData.setEducation(education);
        personalData.setMaritalStatus(MaritalStatus.SEPARATED);

        return new Person(personalData, null);
    }

    public static Person create() {
        PersonalData personalData = new PersonalData("test", "test", "test");
        personalData.setTotalMonthlyIncomePLN(5000.0);
        personalData.setNumOfFamilyDependants(2);
        personalData.setEducation(Education.MIDDLE);
        personalData.setMaritalStatus(MaritalStatus.SEPARATED);

        return new Person(personalData, null);
    }

        public static Person create(double totalMonthlyIncomePLN, int numOfFamilyDependants, Education education, MaritalStatus maritalStatus){
        PersonalData personalData = new PersonalData("test", "test", "test");
        personalData.setTotalMonthlyIncomePLN(totalMonthlyIncomePLN);
        personalData.setNumOfFamilyDependants(numOfFamilyDependants);
        personalData.setEducation(education);
        personalData.setMaritalStatus(maritalStatus);

        return new Person(personalData, null);
    }
}
