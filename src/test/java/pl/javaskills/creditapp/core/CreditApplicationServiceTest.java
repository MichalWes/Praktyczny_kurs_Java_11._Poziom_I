package pl.javaskills.creditapp.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.javaskills.creditapp.core.model.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static pl.javaskills.creditapp.core.DecisionType.*;

@ExtendWith(MockitoExtension.class)
class CreditApplicationServiceTest {
    @InjectMocks
    private CreditApplicationService cut;

    @Mock
    private PersonScoringCalculator calculatorMock;

    @Test
    @DisplayName("Should return NEGATIVE_SCORING when scoring is < 300")
    public void test1(){
        //given
        LoanApplication loanApplication = CreditApplicationServiceTestFactory.create();
        BDDMockito.given(calculatorMock.calculate(eq(loanApplication.getPerson()))).willReturn(100);
        //when
        DecisionType decisionType = cut.getDecision(loanApplication);
        //then
        System.out.println(decisionType);
        assertEquals(NEGATIVE_SCORING, decisionType);
    }
    @Test
    @DisplayName("Should return CONTACT_REQUIRED when scoring berween 300 and 400")
    public void test2(){
        //given
        LoanApplication loanApplication = CreditApplicationServiceTestFactory.create();
        BDDMockito.given(calculatorMock.calculate(eq(loanApplication.getPerson()))).willReturn(350);

        //when
        DecisionType decisionType = cut.getDecision(loanApplication);
        //then
        System.out.println(decisionType);
        assertEquals(CONTACT_REQUIRED, decisionType);
    }
    @Test
    @DisplayName("Should return NEGATIVE_CREDIT_RATING when scoring above 400 and credit rating below amount requested")
    public void test3(){
        //given
        LoanApplication loanApplication = CreditApplicationServiceTestFactory.create(Type.MORTGAGE, 300000, (byte)30, 5000, 2);
        BDDMockito.given(calculatorMock.calculate(eq(loanApplication.getPerson()))).willReturn(450);

        //when
        DecisionType decisionType = cut.getDecision(loanApplication);
        //then
        System.out.println(decisionType);
        assertEquals(NEGATIVE_CREDIT_RATING, decisionType);
    }
    @Test
    @DisplayName("Should return POSITIVE when scoring above 400 and credit rating ok")
    public void test4(){
        //given
        LoanApplication loanApplication = CreditApplicationServiceTestFactory.create(Type.MORTGAGE, 100000, (byte)30, 5000, 2);
        BDDMockito.given(calculatorMock.calculate(eq(loanApplication.getPerson()))).willReturn(450);
        //when
        DecisionType decisionType = cut.getDecision(loanApplication);
        //then
        System.out.println(decisionType);
        assertEquals(POSITIVE, decisionType);
    }
}