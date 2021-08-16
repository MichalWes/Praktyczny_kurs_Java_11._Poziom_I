package pl.javaskills.creditapp.core.model;

public class PurposeOfLoanTestFactory {
    public static PurposeOfLoan create(Type type, double amount, byte period){
        PurposeOfLoan purposeOfLoan = new PurposeOfLoan();
        purposeOfLoan.setType(type);
        purposeOfLoan.setAmount(amount);
        purposeOfLoan.setPeriod(period);
        return purposeOfLoan;
    }
}
