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
    //calculatorMock = Mockito.mock(PersonScoringCalculator.class);
    @InjectMocks
    private CreditApplicationService cut;

    @Mock
    private PersonScoringCalculator calculatorMock;
    @Mock
    private CreditRatingCalculator creditRatingCalculatorMock;


    @Test
    @DisplayName("Should return NEGATIVE_SCORING when scoring is < 300")
    public void test1(){
        //given
        LoanApplication loanApplication = CreditApplicationServiceTestFactory.create();
        BDDMockito.given(calculatorMock.calculate(eq(loanApplication.getPerson()))).willReturn(100);
        //when
        CreditApplicationDecision decision = cut.getDecision(loanApplication);
        //then
        assertEquals(NEGATIVE_SCORING, decision.getDecisionType());
    }
    @Test
    @DisplayName("Should return CONTACT_REQUIRED when scoring berween 300 and 400")
    public void test2(){
        //given
        LoanApplication loanApplication = CreditApplicationServiceTestFactory.create();
        BDDMockito.given(calculatorMock.calculate(eq(loanApplication.getPerson()))).willReturn(350);
        //when
        CreditApplicationDecision decision = cut.getDecision(loanApplication);
        //then
        assertEquals(CONTACT_REQUIRED, decision.getDecisionType());
    }
    @Test
    @DisplayName("Should return NEGATIVE_CREDIT_RATING when scoring above 400 and credit rating below amount requested")
    public void test3(){
        //given
        LoanApplication loanApplication = CreditApplicationServiceTestFactory.create(300000.0);
        BDDMockito.given(calculatorMock.calculate(eq(loanApplication.getPerson()))).willReturn(450);
        BDDMockito.given(creditRatingCalculatorMock.getCreditRating(eq(loanApplication))).willReturn(250000.0);
        //when
        CreditApplicationDecision decision = cut.getDecision(loanApplication);
        //then
        assertEquals(NEGATIVE_CREDIT_RATING, decision.getDecisionType());
    }
    @Test
    @DisplayName("Should return POSITIVE when scoring above 400 and credit rating ok")
    public void test4(){
        //given
        LoanApplication loanApplication = CreditApplicationServiceTestFactory.create(100000.0);
        BDDMockito.given(calculatorMock.calculate(eq(loanApplication.getPerson()))).willReturn(450);
        BDDMockito.given(creditRatingCalculatorMock.getCreditRating(eq(loanApplication))).willReturn(250000.0);
        //when
        CreditApplicationDecision decision = cut.getDecision(loanApplication);
        //then
        assertEquals(POSITIVE, decision.getDecisionType());
    }
}