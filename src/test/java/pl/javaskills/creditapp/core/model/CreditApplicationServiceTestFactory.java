package pl.javaskills.creditapp.core.model;

public class CreditApplicationServiceTestFactory {

   public static LoanApplication create(Person person, PurposeOfLoan purposeOfLoan) {
       return LoanApplication.Builder
               .create()
               .withPerson(person)
               .withPurposeOfLoan(purposeOfLoan)
               .build();
   }

    public static LoanApplication create(){
        Person person = PersonTestFactory.create(3000.0, 1000.0, 1000.0, 2, Education.MIDDLE, MaritalStatus.SEPARATED);
        LoanApplication loanApplication = LoanApplication.Builder
                .create()
                .withPerson(person)
                .withPurposeOfLoan(null)
                .build();
        return loanApplication;
    }

    public static LoanApplication create(double amount){
        Person person = PersonTestFactory.create(3000.0, 1000.0, 1000.0, 2, Education.MIDDLE, MaritalStatus.SEPARATED);
        PurposeOfLoan purposeOfLoan = PurposeOfLoan.Builder.create()
                .withAmount(amount)
                .build();
        LoanApplication loanApplication = LoanApplication.Builder
                .create()
                .withPerson(person)
                .withPurposeOfLoan(purposeOfLoan)
                .build();
        return loanApplication;
    }
}
