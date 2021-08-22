package pl.javaskills.creditapp.client;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PhoneValidatorTest {

    @Test
    public void test1(){
        //given
        String input = "+48694233413";
        //when
        boolean testResult = PhoneValidator.validate(input);
        //then
        assertEquals(true,testResult);
    }
    @Test
    public void test2(){
        //given
        String input = "48694233413";
        //when
        boolean testResult = PhoneValidator.validate(input);
        //then
        assertEquals(false,testResult);
    }
    @Test
    public void test3(){
        //given
        String input = "694233413";
        //when
        boolean testResult = PhoneValidator.validate(input);
        //then
        assertEquals(true,testResult);
    }

}