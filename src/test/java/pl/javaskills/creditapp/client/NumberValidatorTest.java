package pl.javaskills.creditapp.client;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumberValidatorTest {

    @Test
    public void test1(){
        //given
        String input = "10.0";
        double min = 0.0;
        double max = Double.MAX_VALUE;
        //when
        boolean testResult = NumberValidator.validateDouble(input, min, max);
        //then
        assertEquals(true, testResult);
    }

    @Test
    public void test2(){
        //given
        String input = "9.0";
        double min = 10.0;
        double max = Double.MAX_VALUE;
        //when
        boolean testResult = NumberValidator.validateDouble(input, min, max);
        //then
        assertEquals(false, testResult);
    }
    @Test
    public void test3(){
        //given
        String input = "10";
        int min = 0;
        int max = 5;
        //when
        boolean testResult = NumberValidator.validateInteger(input, min, max);
        //then
        assertEquals(false, testResult);
    }
    @Test
    public void test4(){
        //given
        String input = "10";
        //when
        boolean testResult = NumberValidator.validateInteger(input,1,2,3,4,5,6,7,8,9,10);
        //then
        assertEquals(true, testResult);
    }
    @Test
    public void test5(){
        //given
        String input = "11";
        //when
        boolean testResult = NumberValidator.validateInteger(input,1,2,3,4,5,6,7,8,9,10);
        //then
        assertEquals(false, testResult);
    }
}