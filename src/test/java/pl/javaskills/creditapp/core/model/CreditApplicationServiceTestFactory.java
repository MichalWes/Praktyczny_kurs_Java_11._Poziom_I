package pl.javaskills.creditapp.core.model;

import java.util.ArrayList;
import java.util.List;

public class CreditApplicationServiceTestFactory {

   public static CreditApplication create(NaturalPerson person, PurposeOfLoan purposeOfLoan) {
       return new CreditApplication(person, purposeOfLoan, null);
   }

    public static CreditApplication create(SelfEmployed person, PurposeOfLoan purposeOfLoan) {
        return new CreditApplication(person, purposeOfLoan, null);
    }

    public static CreditApplication create(){

        List<FamilyMember> familyMembers = new ArrayList<>();

        familyMembers.add(FamilyMember.Builder
                .create()
                .withName("Andrzej")
                .withAge(20)
                .build());

        familyMembers.add(FamilyMember.Builder
                .create()
                .withName("Zdzisław")
                .withAge(27)
                .build());

       NaturalPerson person = PersonTestFactory.create(3000.0, 1000.0, 1000.0, familyMembers, Education.MIDDLE, MaritalStatus.SEPARATED);
       return new CreditApplication(person, null, null);

    }

    public static CreditApplication create(double amount){

        List<FamilyMember> familyMembers = new ArrayList<>();

        familyMembers.add(FamilyMember.Builder
                .create()
                .withName("Andrzej")
                .withAge(20)
                .build());

        familyMembers.add(FamilyMember.Builder
                .create()
                .withName("Zdzisław")
                .withAge(27)
                .build());

       NaturalPerson person = PersonTestFactory.create(3000.0, 1000.0, 1000.0, familyMembers, Education.MIDDLE, MaritalStatus.SEPARATED);
        PurposeOfLoan purposeOfLoan = PurposeOfLoan.Builder.create()
                .withAmount(amount)
                .build();
        return new CreditApplication(person, purposeOfLoan, null);
    }


}
