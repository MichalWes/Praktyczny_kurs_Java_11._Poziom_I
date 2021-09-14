package pl.javaskills.creditapp.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.javaskills.creditapp.core.exception.RequirementNotMetException;
import pl.javaskills.creditapp.core.exception.ValidationException;
import pl.javaskills.creditapp.core.model.*;
import pl.javaskills.creditapp.core.scoring.CompoundScoringCalculator;
import pl.javaskills.creditapp.core.validation.CompoundPostValidator;
import pl.javaskills.creditapp.core.validation.CreditApplicationValidator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static pl.javaskills.creditapp.core.DecisionType.*;

@ExtendWith(MockitoExtension.class)
class CreditApplicationServiceTest {

    @InjectMocks
    private CreditApplicationService cut;

    @Mock
    private CompoundScoringCalculator calculatorMock;

    @Mock
    private CreditApplicationValidator creditApplicationValidatorMock;

    @Mock
    private PersonScoringCalculatorFactory personScoringCalculatorFactoryMock;

    @Mock
    private CreditRatingCalculator creditRatingCalculatorMock;

    @Mock
    private CompoundPostValidator compoundPostValidatorMock;

    @BeforeEach
    public void init() throws ValidationException, RequirementNotMetException {
        BDDMockito.given(personScoringCalculatorFactoryMock.getCalculator(any(Person.class))).willReturn(calculatorMock);
        BDDMockito.doNothing().when(creditApplicationValidatorMock).validate(any(CreditApplication.class));
        BDDMockito.doNothing().when(compoundPostValidatorMock).validate(any(CreditApplication.class), anyInt(), anyDouble());
    }


    @Test
    @DisplayName("Should return NEGATIVE_SCORING when scoring is < 300")
    public void test1(){
        //given
        CreditApplication creditApplication = CreditApplicationServiceTestFactory.create();
        BDDMockito.given(calculatorMock.calculate(eq(creditApplication))).willReturn(100);
        //when
        CreditApplicationDecision decision = cut.getDecision(creditApplication);
        //then
        assertEquals(NEGATIVE_SCORING, decision.getDecisionType());
    }
    @Test
    @DisplayName("Should return CONTACT_REQUIRED when scoring berween 300 and 400")
    public void test2(){
        //given
        CreditApplication creditApplication = CreditApplicationServiceTestFactory.create();
        BDDMockito.given(calculatorMock.calculate(eq(creditApplication))).willReturn(350);
        //when
        CreditApplicationDecision decision = cut.getDecision(creditApplication);
        //then
        assertEquals(CONTACT_REQUIRED, decision.getDecisionType());
    }
    @Test
    @DisplayName("Should return NEGATIVE_CREDIT_RATING when scoring above 400 and credit rating below amount requested")
    public void test3(){
        //given
        CreditApplication creditApplication = CreditApplicationServiceTestFactory.create(300000.0);
        BDDMockito.given(calculatorMock.calculate(eq(creditApplication))).willReturn(450);
        BDDMockito.given(creditRatingCalculatorMock.getCreditRating(eq(creditApplication))).willReturn(250000.0);
        //when
        CreditApplicationDecision decision = cut.getDecision(creditApplication);
        //then
        assertEquals(NEGATIVE_CREDIT_RATING, decision.getDecisionType());
    }
    @Test
    @DisplayName("Should return POSITIVE when scoring above 400 and credit rating ok")
    public void test4(){
        //given
        CreditApplication creditApplication = CreditApplicationServiceTestFactory.create(100000.0);
        BDDMockito.given(calculatorMock.calculate(eq(creditApplication))).willReturn(450);
        BDDMockito.given(creditRatingCalculatorMock.getCreditRating(eq(creditApplication))).willReturn(250000.0);
        //when
        CreditApplicationDecision decision = cut.getDecision(creditApplication);
        //then
        assertEquals(POSITIVE, decision.getDecisionType());
    }
}