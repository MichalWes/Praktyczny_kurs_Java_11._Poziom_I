package pl.javaskills.creditapp.core.model;

public class PersonTestFactory {

    public static Person create(MaritalStatus maritalStatus) {
        PersonalData personalData = PersonalData.Builder
                .create()
                .withName("test")
                .withLastName("test")
                .withMothersMaidenName("test")
                .withNumOfFamilyDependants(2)
                .withEducation(Education.MIDDLE)
                .withMaritalStatus(maritalStatus)
                .build();

        SourceOfIncome source1 = SourceOfIncome.Builder
                .create()
                .withNetMontlyIncome(3000)
                .build();
        SourceOfIncome source2 = SourceOfIncome.Builder
                .create()
                .withNetMontlyIncome(1000)
                .build();
        SourceOfIncome source3 = SourceOfIncome.Builder
                .create()
                .withNetMontlyIncome(1000)
                .build();

        FinanceData financeData = FinanceData.Builder
                .create()
                .withSourcesOfIncome(source1, source2, source3)
                .build();

        return Person.Builder
                .create()
                .withPersonalData(personalData)
                .withFinanceData(financeData)
                .withContactData(null)
                .build();
    }


    public static Person create(double monthlyIncomePLN1, double monthlyIncomePLN2, double monthlyIncomePLN3 ,byte numOfFamilyDependants) {
        PersonalData personalData = PersonalData.Builder
                .create()
                .withName("test")
                .withLastName("test")
                .withMothersMaidenName("test")
                .withNumOfFamilyDependants(numOfFamilyDependants)
                .withEducation(Education.MIDDLE)
                .withMaritalStatus(MaritalStatus.SEPARATED)
                .build();

        SourceOfIncome source1 = SourceOfIncome.Builder
                .create()
                .withNetMontlyIncome(monthlyIncomePLN1)
                .build();
        SourceOfIncome source2 = SourceOfIncome.Builder
                .create()
                .withNetMontlyIncome(monthlyIncomePLN2)
                .build();
        SourceOfIncome source3 = SourceOfIncome.Builder
                .create()
                .withNetMontlyIncome(monthlyIncomePLN3)
                .build();

        FinanceData financeData = FinanceData.Builder
                .create()
                .withSourcesOfIncome(source1, source2, source3)
                .build();

        return Person.Builder
                .create()
                .withPersonalData(personalData)
                .withFinanceData(financeData)
                .withContactData(null)
                .build();
    }

    public static Person create(Education education) {
        PersonalData personalData = PersonalData.Builder
                .create()
                .withName("test")
                .withLastName("test")
                .withMothersMaidenName("test")
                .withNumOfFamilyDependants(2)
                .withEducation(education)
                .withMaritalStatus(MaritalStatus.SEPARATED)
                .build();

        SourceOfIncome source1 = SourceOfIncome.Builder
                .create()
                .withNetMontlyIncome(3000)
                .build();
        SourceOfIncome source2 = SourceOfIncome.Builder
                .create()
                .withNetMontlyIncome(1000)
                .build();
        SourceOfIncome source3 = SourceOfIncome.Builder
                .create()
                .withNetMontlyIncome(1000)
                .build();

        FinanceData financeData = FinanceData.Builder
                .create()
                .withSourcesOfIncome(source1, source2, source3)
                .build();

        return Person.Builder
                .create()
                .withPersonalData(personalData)
                .withFinanceData(financeData)
                .withContactData(null)
                .build();
    }

    public static Person create() {
        PersonalData personalData = PersonalData.Builder
                .create()
                .withName("test")
                .withLastName("test")
                .withMothersMaidenName("test")
                .withNumOfFamilyDependants(2)
                .withEducation(Education.MIDDLE)
                .withMaritalStatus(MaritalStatus.SEPARATED)
                .build();

        SourceOfIncome source1 = SourceOfIncome.Builder
                .create()
                .withNetMontlyIncome(3000)
                .build();
        SourceOfIncome source2 = SourceOfIncome.Builder
                .create()
                .withNetMontlyIncome(1000)
                .build();
        SourceOfIncome source3 = SourceOfIncome.Builder
                .create()
                .withNetMontlyIncome(1000)
                .build();

        FinanceData financeData = FinanceData.Builder
                .create()
                .withSourcesOfIncome(source1, source2, source3)
                .build();

        return Person.Builder
                .create()
                .withPersonalData(personalData)
                .withFinanceData(financeData)
                .withContactData(null)
                .build();
    }

        public static Person create(double monthlyIncomePLN1, double monthlyIncomePLN2, double monthlyIncomePLN3, int numOfFamilyDependants, Education education, MaritalStatus maritalStatus){
            PersonalData personalData = PersonalData.Builder
                    .create()
                    .withName("test")
                    .withLastName("test")
                    .withMothersMaidenName("test")
                    .withNumOfFamilyDependants(numOfFamilyDependants)
                    .withEducation(education)
                    .withMaritalStatus(maritalStatus)
                    .build();

            SourceOfIncome source1 = SourceOfIncome.Builder
                    .create()
                    .withNetMontlyIncome(monthlyIncomePLN1)
                    .build();
            SourceOfIncome source2 = SourceOfIncome.Builder
                    .create()
                    .withNetMontlyIncome(monthlyIncomePLN2)
                    .build();
            SourceOfIncome source3 = SourceOfIncome.Builder
                    .create()
                    .withNetMontlyIncome(monthlyIncomePLN3)
                    .build();

        FinanceData financeData = FinanceData.Builder
                .create()
                .withSourcesOfIncome(source1, source2, source3)
                .build();

        return Person.Builder
                .create()
                .withPersonalData(personalData)
                .withFinanceData(financeData)
                .withContactData(null)
                .build();
    }
}
