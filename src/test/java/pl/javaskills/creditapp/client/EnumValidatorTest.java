package pl.javaskills.creditapp.client;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import pl.javaskills.creditapp.core.model.Education;
import pl.javaskills.creditapp.core.model.IncomeType;
import pl.javaskills.creditapp.core.model.MaritalStatus;
import pl.javaskills.creditapp.core.model.Type;

import static org.junit.jupiter.api.Assertions.*;

class EnumValidatorTest {

    @ParameterizedTest
    @EnumSource(Education.class)
    public void test1(Education education){
        //given
        String testInput = education.name();
        //when
        boolean testResult = EnumValidator.validateEducation(testInput);
        //then
        assertEquals(true,testResult);
    }
    @ParameterizedTest
    @EnumSource(IncomeType.class)
    public void test2(IncomeType incomeType){
        //given
        String testInput = incomeType.name();
        //when
        boolean testResult = EnumValidator.validateIncomeType(testInput);
        //then
        assertEquals(true,testResult);
    }
    @ParameterizedTest
    @EnumSource(MaritalStatus.class)
    public void test3(MaritalStatus maritalStatus){
        //given
        String testInput = maritalStatus.name();
        //when
        boolean testResult = EnumValidator.validateMaritalStatus(testInput);
        //then
        assertEquals(true,testResult);
    }
    @ParameterizedTest
    @EnumSource(Type.class)
    public void test4(Type type){
        //given
        String testInput = type.name();
        //when
        boolean testResult = EnumValidator.validateType(testInput);
        //then
        assertEquals(true,testResult);
    }

}