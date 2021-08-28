package pl.javaskills.creditapp.core.model;

public class CreditApplicationServiceTestFactory {

   public static LoanApplication create(NaturalPerson person, PurposeOfLoan purposeOfLoan) {
       return new LoanApplication(person, purposeOfLoan);
   }

    public static LoanApplication create(){
        NaturalPerson person = PersonTestFactory.create(3000.0, 1000.0, 1000.0, 2, Education.MIDDLE, MaritalStatus.SEPARATED);
        return new LoanApplication(person, null);

    }

    public static LoanApplication create(double amount){
        NaturalPerson person = PersonTestFactory.create(3000.0, 1000.0, 1000.0, 2, Education.MIDDLE, MaritalStatus.SEPARATED);
        PurposeOfLoan purposeOfLoan = PurposeOfLoan.Builder.create()
                .withAmount(amount)
                .build();
        return new LoanApplication(person, purposeOfLoan);
    }
}
