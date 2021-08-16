package pl.javaskills.creditapp.core.model;

public class CreditApplicationServiceTestFactory {

    public static LoanApplication create(){
        Person person = PersonTestFactory.create(5000.0, 2, Education.MIDDLE, MaritalStatus.SEPARATED);
        LoanApplication loanApplication = new LoanApplication(person,null);
        return loanApplication;
    }
    public static LoanApplication create(Type type, double amount, byte period, double totalMonthlyIncomePLN, int numOfFamilyDependants){
        Person person = PersonTestFactory.create(totalMonthlyIncomePLN, numOfFamilyDependants, Education.MIDDLE, MaritalStatus.SEPARATED);
        PurposeOfLoan purposeOfLoan = new PurposeOfLoan();
        purposeOfLoan.setType(type);
        purposeOfLoan.setAmount(amount);
        purposeOfLoan.setPeriod(period);
        LoanApplication loanApplication = new LoanApplication(person,purposeOfLoan);
        return loanApplication;
    }
}
