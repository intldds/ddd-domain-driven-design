package com.finance.project.dtos.dtos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Ala Matos
 */
class PersonEmailDTOTest {


    @Test
    @DisplayName("Test for PersonEmailDTO constructor | Happy path")
    void createPersonEmailDTO() {

//        Arrange the email
        String email = "hulk@gmarvel.com";

//        Act
        PersonEmailDTO personEmailDTO = new PersonEmailDTO(email);

//        Assert
        assertEquals(email, personEmailDTO.getEmail());

    }

    @Test
    @DisplayName("Test for PersonEmailDTO constructor | Sad path")
    void createPersonEmailDTOSadPath() {

//        Arrange the email
        String email = "hulk@gmarvel.com";
        String secondaryEmail = "wolverine@gmarvel.com";

//        Act
        PersonEmailDTO personEmailDTO = new PersonEmailDTO(secondaryEmail);

//        Assert
        assertNotEquals(email, personEmailDTO.getEmail());

    }

    @Test
    @DisplayName("Test equals method | Same object")
    void testEqualsSameObject() {

//        Arrange the email
        String email = "hulk@gmarvel.com";

//        Act
        PersonEmailDTO firstPersonEmailDTO = new PersonEmailDTO(email);

//        Assert
        assertTrue(firstPersonEmailDTO.equals(firstPersonEmailDTO));
    }


    @Test
    @DisplayName("Test equals method | Null")
    void testEqualsNull() {

//        Arrange the email
        String email = "hulk@gmarvel.com";

//        Act
        PersonEmailDTO firstPersonEmailDTO = new PersonEmailDTO(email);
        PersonEmailDTO secondPersonEmailDTO= null;

        boolean result = firstPersonEmailDTO.equals(secondPersonEmailDTO);

//        Assert
        assertEquals(false, result);
        assertTrue(firstPersonEmailDTO.equals(firstPersonEmailDTO));
    }


    @Test
    @DisplayName("Test equals method | No Instance Of")
    void testEqualsNoInstanceOf() {

//        Arrange the email
        String email = "hulk@gmarvel.com";

//        Act
        PersonEmailDTO firstPersonEmailDTO = new PersonEmailDTO(email);

        boolean result = firstPersonEmailDTO.equals(email);

//        Assert

        assertEquals(false, result);
        assertFalse(firstPersonEmailDTO.equals(email));
    }


    @Test
    @DisplayName("Test equals method | Different object - Same information")
    void testEqualsDifferentObjectSameInformation() {

//        Arrange the email
        String email = "hulk@gmarvel.com";

//        Act
        PersonEmailDTO firstPersonEmailDTO = new PersonEmailDTO(email);
        PersonEmailDTO firstPersonEmailDTO2 = new PersonEmailDTO(email);

//        Assert
        assertTrue(firstPersonEmailDTO.equals(firstPersonEmailDTO2));
    }

    @Test
    @DisplayName("Test equals method | Different Object ")
    void testEqualsDifferentObject() {

//        Arrange the email
        String email = "hulk@gmarvel.com";
        String secondaryEmail = "wolverine@gmarvel.com";

//        Act
        PersonEmailDTO firstPersonEmailDTO = new PersonEmailDTO(email);
        PersonEmailDTO secondPersonEmailDTO = new PersonEmailDTO(secondaryEmail);

//        Assert
        assertFalse(firstPersonEmailDTO.equals(secondPersonEmailDTO));
    }

    @Test
    @DisplayName("Test hashcode")
    void testHashCode() {

//        Arrange the email
        String email = "hulk@gmarvel.com";

//        Act
        PersonEmailDTO firstPersonEmailDTO = new PersonEmailDTO(email);

        int hashCode = firstPersonEmailDTO.hashCode();
        int expectedHashCode = 189140324;

//        Assert
        assertEquals(expectedHashCode,hashCode);
    }
}