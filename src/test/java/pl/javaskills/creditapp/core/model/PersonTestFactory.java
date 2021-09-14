package pl.javaskills.creditapp.core.model;

import java.util.ArrayList;
import java.util.List;

public class PersonTestFactory {

    public static NaturalPerson create(MaritalStatus maritalStatus) {
        PersonalData personalData = PersonalData.Builder
                .create()
                .withName("test")
                .withLastName("test")
                .withMothersMaidenName("test")
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
                .buildWithoutExpenses();

        return NaturalPerson.Builder
                .create()
                .withPersonalData(personalData)
                .withFinanceData(financeData)
                .withContactData(null)
                .build();
    }


    public static NaturalPerson create(double monthlyIncomePLN1, double monthlyIncomePLN2, double monthlyIncomePLN3) {
        PersonalData personalData = PersonalData.Builder
                .create()
                .withName("test")
                .withLastName("test")
                .withMothersMaidenName("test")
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
                .buildWithoutExpenses();

        List<FamilyMember> familyMembers = new ArrayList<>();

        familyMembers.add(FamilyMember.Builder
                .create()
                .withName("Andrzej")
                .withAge(20)
                .build());

        return NaturalPerson.Builder
                .create()
                .withPersonalData(personalData)
                .withFinanceData(financeData)
                .withContactData(null)
                .withFamilyMembers(familyMembers)
                .build();
    }

    public static NaturalPerson create(Education education) {
        PersonalData personalData = PersonalData.Builder
                .create()
                .withName("test")
                .withLastName("test")
                .withMothersMaidenName("test")
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
                .buildWithoutExpenses();

        return NaturalPerson.Builder
                .create()
                .withPersonalData(personalData)
                .withFinanceData(financeData)
                .withContactData(null)
                .build();
    }

    public static NaturalPerson create() {
        PersonalData personalData = PersonalData.Builder
                .create()
                .withName("test")
                .withLastName("test")
                .withMothersMaidenName("test")
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
                .buildWithoutExpenses();

        return NaturalPerson.Builder
                .create()
                .withPersonalData(personalData)
                .withFinanceData(financeData)
                .withContactData(null)
                .build();
    }

        public static NaturalPerson create(double monthlyIncomePLN1, double monthlyIncomePLN2, double monthlyIncomePLN3, List<FamilyMember> familyMembers, Education education, MaritalStatus maritalStatus){
            PersonalData personalData = PersonalData.Builder
                    .create()
                    .withName("test")
                    .withLastName("test")
                    .withMothersMaidenName("test")
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
                .buildWithoutExpenses();

        return NaturalPerson.Builder
                .create()
                .withPersonalData(personalData)
                .withFinanceData(financeData)
                .withFamilyMembers(familyMembers)
                .withContactData(null)
                .build();
    }


}
