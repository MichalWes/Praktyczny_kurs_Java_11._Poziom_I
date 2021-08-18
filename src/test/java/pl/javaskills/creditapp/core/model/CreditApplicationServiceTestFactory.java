package pl.javaskills.creditapp.core.model;

public class CreditApplicationServiceTestFactory {

    public static LoanApplication create(){
        Person person = PersonTestFactory.create(3000.0, 1000.0, 1000.0, 2, Education.MIDDLE, MaritalStatus.SEPARATED);
        LoanApplication loanApplication = new LoanApplication(person,null);
        return loanApplication;
    }
    public static LoanApplication create(double amount){
        Person person = PersonTestFactory.create(3000.0, 1000.0, 1000.0, 2, Education.MIDDLE, MaritalStatus.SEPARATED);
        PurposeOfLoan purposeOfLoan = new PurposeOfLoan();
        purposeOfLoan.setAmount(amount);
        LoanApplication loanApplication = new LoanApplication(person,purposeOfLoan);
        return loanApplication;
    }
}
