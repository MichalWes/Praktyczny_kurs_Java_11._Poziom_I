package pl.javaskills.creditapp.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.javaskills.creditapp.core.model.*;

import static org.junit.jupiter.api.Assertions.*;
import static pl.javaskills.creditapp.core.DecisionType.*;

@ExtendWith(MockitoExtension.class)
class CreditApplicationServiceTest {
    @InjectMocks
    private CreditApplicationService cut;

    @Test
    @DisplayName("Person Credit Decision Test 1")
    public void test1(){
        //given
        Person person = PersonTestFactory.create(5000, 2, Education.PRIMARY, MaritalStatus.MARRIED);
        PurposeOfLoan purposeOfLoan = PurposeOfLoanTestFactory.create(Type.MORTGAGE, 300000, (byte)30);
        //when
        DecisionType decisionType = cut.getDecision(new LoanApplication(person, purposeOfLoan));
        //then
        System.out.println(decisionType);
        assertEquals(NEGATIVE_SCORING, decisionType);
    }
    @Test
    @DisplayName("Person Credit Decision Test 2")
    public void test2(){
        //given
        Person person = PersonTestFactory.create(9000, 2, Education.TERTIARY, MaritalStatus.SINGLE);
        PurposeOfLoan purposeOfLoan = PurposeOfLoanTestFactory.create(Type.MORTGAGE, 300000, (byte)30);
        //when
        DecisionType decisionType = cut.getDecision(new LoanApplication(person, purposeOfLoan));
        //then
        System.out.println(decisionType);
        assertEquals(POSITIVE, decisionType);
    }
    @Test
    @DisplayName("Person Credit Decision Test 3")
    public void test3(){
        //given
        Person person = PersonTestFactory.create(5000, 2, Education.TERTIARY, MaritalStatus.SINGLE);
        PurposeOfLoan purposeOfLoan = PurposeOfLoanTestFactory.create(Type.MORTGAGE, 300000, (byte)30);
        //when
        DecisionType decisionType = cut.getDecision(new LoanApplication(person, purposeOfLoan));
        //then
        System.out.println(decisionType);
        assertEquals(CONTACT_REQUIRED, decisionType);
    }
    @Test
    @DisplayName("Person Credit Decision Test 4")
    public void test4(){
        //given
        Person person = PersonTestFactory.create(9000, 2, Education.TERTIARY, MaritalStatus.SINGLE);
        PurposeOfLoan purposeOfLoan = PurposeOfLoanTestFactory.create(Type.MORTGAGE, 800000, (byte)30);
        //when
        DecisionType decisionType = cut.getDecision(new LoanApplication(person, purposeOfLoan));
        //then
        System.out.println(decisionType);
        assertEquals(NEGATIVE_CREDIT_RATING, decisionType);
    }


}