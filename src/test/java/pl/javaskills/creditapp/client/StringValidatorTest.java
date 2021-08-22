package pl.javaskills.creditapp.client;

import org.junit.jupiter.api.Test;
import pl.javaskills.creditapp.core.Constants;

import static org.junit.jupiter.api.Assertions.*;

class StringValidatorTest {

    @Test
    public void test1(){
        //given
        String testinput = "Aluś";
        String regex1 = Constants.NAME_REGEX;
        //when
        boolean testresult = StringValidator.validate(testinput, regex1);
        //then
        assertEquals(true,testresult);
    }

    @Test
    public void test2(){
        //given
        String testinput = "AluśkaPokryjomupali";
        String regex1 = Constants.NAME_REGEX;
        //when
        boolean testresult = StringValidator.validate(testinput, regex1);
        //then
        assertEquals(false,testresult);
    }
    @Test
    public void test3(){
        //given
        String testinput = "Annielski-Anansiński";
        String regex1 = Constants.LAST_NAME_REGEX;
        String regex2 = Constants.LAST_NAME_REGEX2;
        //when
        boolean testresult = StringValidator.validate(testinput, regex1,regex2);
        //then
        assertEquals(true,testresult);
    }
    @Test
    public void test4(){
        //given
        String testinput = "Annielski-Anansińskii";
        String regex1 = Constants.LAST_NAME_REGEX;
        String regex2 = Constants.LAST_NAME_REGEX2;
        //when
        boolean testresult = StringValidator.validate(testinput, regex1,regex2);
        //then
        assertEquals(false,testresult);
    }
    @Test
    public void test5(){
        //given
        String testinput = "Annielski-anansiński";
        String regex1 = Constants.LAST_NAME_REGEX;
        String regex2 = Constants.LAST_NAME_REGEX2;
        //when
        boolean testresult = StringValidator.validate(testinput, regex1,regex2);
        //then
        assertEquals(false,testresult);
    }
    @Test
    public void test6(){
        //given
        String testinput = "Adam@adamski.pl";
        String regex1 = Constants.EMAIL_REGEX;
        //when
        boolean testresult = StringValidator.validate(testinput, regex1);
        //then
        assertEquals(true,testresult);
    }
    @Test
    public void test7(){
        //given
        String testinput = "Adam@adamskipl";
        String regex1 = Constants.EMAIL_REGEX;
        //when
        boolean testresult = StringValidator.validate(testinput, regex1);
        //then
        assertEquals(false,testresult);
    }
}