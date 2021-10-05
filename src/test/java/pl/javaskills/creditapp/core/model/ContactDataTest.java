package pl.javaskills.creditapp.core.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ContactDataTest {

    @Test
    @DisplayName("Should set Optional.empty correspondence address, when home address is the same")
    public void test1(){
        //given & when
        Address homeAddress = Address.Builder
                .create()
                .withStreet("Gdańska")
                .withHouseNumber("25/5")
                .withZipCode("80-300")
                .withCity("Gdańsk")
                .withState("Pomorskie")
                .build();

        Address correspondeceAddress = Address.Builder
                .create()
                .withStreet("Gdańska")
                .withHouseNumber("25/5")
                .withZipCode("80-300")
                .withCity("Gdańsk")
                .withState("Pomorskie")
                .build();

        ContactData contactData = ContactData.Builder
                .create()
                .withHomeAddress(homeAddress)
                .withCorrespondeceAddress(correspondeceAddress)
                .build();
        //then
        assertTrue(contactData.getCorrespondenceAddress().isEmpty());
    }

    @Test
    @DisplayName("Should set Optional.of correspondence address, when home address is the same")
    public void test2(){
        //given
        Address homeAddress = Address.Builder
                .create()
                .withStreet("Gdańska")
                .withHouseNumber("25/5")
                .withZipCode("80-300")
                .withCity("Gdańsk")
                .withState("Pomorskie")
                .build();

        Address correspondeceAddress = Address.Builder
                .create()
                .withStreet("Gdyńska")
                .withHouseNumber("24/5")
                .withZipCode("81-300")
                .withCity("Gdynia")
                .withState("Pomorskie")
                .build();

        //when
        ContactData contactData = ContactData.Builder
                .create()
                .withHomeAddress(homeAddress)
                .withCorrespondeceAddress(correspondeceAddress)
                .build();
        //then
        assertTrue(contactData.getCorrespondenceAddress().isPresent());
        assertEquals(correspondeceAddress, contactData.getCorrespondenceAddress().get());
    }

}