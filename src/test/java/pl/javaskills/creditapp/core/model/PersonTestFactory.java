package pl.javaskills.creditapp.core.model;

public class PersonTestFactory {

    public static Person create(MaritalStatus maritalStatus) {
        PersonalData personalData = new PersonalData("test", "test", "test");
        personalData.setNumOfFamilyDependants(2);
        personalData.setEducation(Education.MIDDLE);
        personalData.setMaritalStatus(maritalStatus);
        SourceOfIncome source1 = new SourceOfIncome();
        SourceOfIncome source2 = new SourceOfIncome();
        SourceOfIncome source3 = new SourceOfIncome();
        source1.setNetMontlyIncome(3000);
        source2.setNetMontlyIncome(1000);
        source3.setNetMontlyIncome(1000);
        FinanceData financeData = FinanceData.Builder
                .create()
                .withSourcesOfIncome(source1, source2, source3)
                .build();

        return new Person(personalData, financeData, null);
    }


    public static Person create(double monthlyIncomePLN1, double monthlyIncomePLN2, double monthlyIncomePLN3 ,byte numOfFamilyDependants) {
        PersonalData personalData = new PersonalData("test", "test", "test");
        personalData.setNumOfFamilyDependants(numOfFamilyDependants);
        personalData.setEducation(Education.MIDDLE);
        personalData.setMaritalStatus(MaritalStatus.SEPARATED);
        SourceOfIncome source1 = new SourceOfIncome();
        SourceOfIncome source2 = new SourceOfIncome();
        SourceOfIncome source3 = new SourceOfIncome();
        source1.setNetMontlyIncome(monthlyIncomePLN1);
        source2.setNetMontlyIncome(monthlyIncomePLN2);
        source3.setNetMontlyIncome(monthlyIncomePLN3);
        FinanceData financeData = FinanceData.Builder
                .create()
                .withSourcesOfIncome(source1, source2, source3)
                .build();

        return new Person(personalData, financeData, null);
    }

    public static Person create(Education education) {
        PersonalData personalData = new PersonalData("test", "test", "test");
        personalData.setNumOfFamilyDependants(2);
        personalData.setEducation(education);
        personalData.setMaritalStatus(MaritalStatus.SEPARATED);
        SourceOfIncome source1 = new SourceOfIncome();
        SourceOfIncome source2 = new SourceOfIncome();
        SourceOfIncome source3 = new SourceOfIncome();
        source1.setNetMontlyIncome(3000);
        source2.setNetMontlyIncome(1000);
        source3.setNetMontlyIncome(1000);
        FinanceData financeData = FinanceData.Builder
                .create()
                .withSourcesOfIncome(source1, source2, source3)
                .build();

        return new Person(personalData, financeData, null);
    }

    public static Person create() {
        PersonalData personalData = new PersonalData("test", "test", "test");
        personalData.setNumOfFamilyDependants(2);
        personalData.setEducation(Education.MIDDLE);
        personalData.setMaritalStatus(MaritalStatus.SEPARATED);
        SourceOfIncome source1 = new SourceOfIncome();
        SourceOfIncome source2 = new SourceOfIncome();
        SourceOfIncome source3 = new SourceOfIncome();
        source1.setNetMontlyIncome(3000);
        source2.setNetMontlyIncome(1000);
        source3.setNetMontlyIncome(1000);
        FinanceData financeData = FinanceData.Builder
                .create()
                .withSourcesOfIncome(source1, source2, source3)
                .build();

        return new Person(personalData, financeData, null);
    }

        public static Person create(double monthlyIncomePLN1, double monthlyIncomePLN2, double monthlyIncomePLN3, int numOfFamilyDependants, Education education, MaritalStatus maritalStatus){
        PersonalData personalData = new PersonalData("test", "test", "test");
        personalData.setNumOfFamilyDependants(numOfFamilyDependants);
        personalData.setEducation(education);
        personalData.setMaritalStatus(maritalStatus);
        SourceOfIncome source1 = new SourceOfIncome();
        SourceOfIncome source2 = new SourceOfIncome();
        SourceOfIncome source3 = new SourceOfIncome();
        source1.setNetMontlyIncome(monthlyIncomePLN1);
        source2.setNetMontlyIncome(monthlyIncomePLN2);
        source3.setNetMontlyIncome(monthlyIncomePLN3);
            FinanceData financeData = FinanceData.Builder
                    .create()
                    .withSourcesOfIncome(source1, source2, source3)
                    .build();
        return new Person(personalData, financeData, null);
    }
}
