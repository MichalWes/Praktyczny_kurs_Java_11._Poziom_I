package pl.javaskills.creditapp.core.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    @DisplayName("Family members should be sorted by age desc")
    public void test1(){
        //given


        FamilyMember member1 = FamilyMember.Builder
                .create()
                .withName("Andrzej")
                .withBirthDate(LocalDate.of(2000, 12, 1))
                .build();

        FamilyMember member2 = FamilyMember.Builder
                .create()
                .withName("Zdzisław")
                .withBirthDate(LocalDate.of(1992, 12, 1))
                .build();

        FamilyMember member3 = FamilyMember.Builder
                .create()
                .withName("Alicja")
                .withBirthDate(LocalDate.of(1986, 12, 1))
                .build();

        FamilyMember member4 = FamilyMember.Builder
                .create()
                .withName("Monstrancja")
                .withBirthDate(LocalDate.of(1965, 12, 1))
                .build();

        List<FamilyMember> familyMembers = new ArrayList<>(Arrays.asList(member1, member2, member3, member4));

        //when

        NaturalPerson person = NaturalPerson.Builder
                .create()
                .withPersonalData(null)
                .withFinanceData(null)
                .withContactData(null)
                .withFamilyMembers(familyMembers)
                .build();

        //then
        assertNotNull(person.getFamilyMembers());
        assertTrue(person.getFamilyMembers().size() == 4);
        assertEquals(member4, person.getFamilyMembers().get(0));
        assertEquals(member3, person.getFamilyMembers().get(1));
        assertEquals(member2, person.getFamilyMembers().get(2));
        assertEquals(member1, person.getFamilyMembers().get(3));

    }

}