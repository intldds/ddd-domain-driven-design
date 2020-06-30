package com.finance.project.dtos.dtos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Ala Matos
 */
class PrepareInfoToCheckIfSiblingsDTOTest {

    @Test
    @DisplayName("Test the Prepare Info To Check If Siblings DTO | Happy path")
    public void testCheckSiblingsDTO() {

        //Arrange
        String hulkEmail = "hulk@marvel.com";
        String wolverineEmail = "wolverine@marvel.com";

        //Act
        PrepareInfoToCheckIfSiblingsDTO prepareInfoToCheckIfSiblingsDTO = new PrepareInfoToCheckIfSiblingsDTO(hulkEmail, wolverineEmail);

        //Assert
        assertEquals(hulkEmail, prepareInfoToCheckIfSiblingsDTO.getEmail());
        assertEquals(wolverineEmail, prepareInfoToCheckIfSiblingsDTO.getSiblingEmail());

    }

    @Test
    @DisplayName("Test the US01_DTO | Wrong sibling")
    public void testCheckSiblingsDTOWrongSibling() {

        //Arrange
        String hulkEmail = "hulk@marvel.com";
        String wolverineEmail = "wolverine@marvel.com";
        String ironManEmail = "ironman@marvel.com";

        //Act
        PrepareInfoToCheckIfSiblingsDTO prepareInfoToCheckIfSiblingsDTO  = new PrepareInfoToCheckIfSiblingsDTO(hulkEmail, wolverineEmail);

        //Assert

        assertNotEquals(ironManEmail, prepareInfoToCheckIfSiblingsDTO.getSiblingEmail());

    }

    @Test
    @DisplayName("Test the set method")
    public void testCheckSiblingsDTOSetMethod () {
        //Arrange
        String hulkEmail = "hulk@marvel.com";
        String wolverineEmail = "wolverine@marvel.com";
        String ironManEmail = "ironman@marvel.com";

        //Act
        PrepareInfoToCheckIfSiblingsDTO prepareInfoToCheckIfSiblingsDTO  = new PrepareInfoToCheckIfSiblingsDTO();
        prepareInfoToCheckIfSiblingsDTO.setEmail(wolverineEmail);
        prepareInfoToCheckIfSiblingsDTO.setSiblingEmail(ironManEmail);

        //Assert

        assertEquals(wolverineEmail, prepareInfoToCheckIfSiblingsDTO.getEmail());
        assertEquals(ironManEmail, prepareInfoToCheckIfSiblingsDTO.getSiblingEmail());

    }

    @Test
    @DisplayName("Test the equals () | Happy path |")
    public void testCheckSiblingsDTOEqualsMethod() {

        //Arrange
        String hulkEmail = "hulk@marvel.com";
        String wolverineEmail = "wolverine@marvel.com";

        //Act
        PrepareInfoToCheckIfSiblingsDTO hulkDTO = new PrepareInfoToCheckIfSiblingsDTO(hulkEmail, wolverineEmail);
        PrepareInfoToCheckIfSiblingsDTO expectedDTO = new PrepareInfoToCheckIfSiblingsDTO(hulkEmail, wolverineEmail);

        //Assert
        assertEquals(expectedDTO, hulkDTO);

    }
    @Test
    @DisplayName("Test the equals () | Same object")
    public void testCheckSiblingsDTOEqualsMethodSameObject() {

        //Arrange
        String hulkEmail = "hulk@marvel.com";
        String wolverineEmail = "wolverine@marvel.com";

        //Act
        PrepareInfoToCheckIfSiblingsDTO hulkDTO = new PrepareInfoToCheckIfSiblingsDTO(hulkEmail, wolverineEmail);
        Boolean result = hulkDTO.equals(hulkDTO);


        //Assert
        assertTrue(result);
        assertEquals(hulkDTO, hulkDTO);

    }
    @Test
    @DisplayName("Test the equals () | Fail | Different object")
    public void testCheckSiblingsDTOEqualsMethodDifferentObject() {

        //Arrange
        String hulkEmail = "hulk@marvel.com";
        String wolverineEmail = "wolverine@marvel.com";

        //Act
        PrepareInfoToCheckIfSiblingsDTO hulkDTO = new PrepareInfoToCheckIfSiblingsDTO(hulkEmail, wolverineEmail);

        //Assert

        assertNotEquals(hulkDTO, wolverineEmail);

    }

    @Test
    @DisplayName("Test the equals () | Fail | Different email")
    public void testCheckSiblingsDTOEqualsMethodDifferentDTOEmail() {

        //Arrange
        String hulkEmail = "hulk@marvel.com";
        String wolverineEmail = "wolverine@marvel.com";
        String ironManEmail = "ironman@marvel.com";

        //Act
        PrepareInfoToCheckIfSiblingsDTO hulkDTO = new PrepareInfoToCheckIfSiblingsDTO(hulkEmail, wolverineEmail);
        PrepareInfoToCheckIfSiblingsDTO expectedDTO = new PrepareInfoToCheckIfSiblingsDTO(  ironManEmail, wolverineEmail);

        //Assert
        assertNotEquals(expectedDTO, hulkDTO);

    }
    @Test
    @DisplayName("Test the equals () | Fail | Different sibling email")
    public void testCheckSiblingsDTOEqualsMethodDifferentDTOSiblingEmail() {

        //Arrange
        String hulkEmail = "hulk@marvel.com";
        String wolverineEmail = "wolverine@marvel.com";
        String ironManEmail = "ironman@marvel.com";

        //Act
        PrepareInfoToCheckIfSiblingsDTO   hulkDTO = new PrepareInfoToCheckIfSiblingsDTO(hulkEmail, wolverineEmail);
        PrepareInfoToCheckIfSiblingsDTO expectedDTO = new PrepareInfoToCheckIfSiblingsDTO(hulkEmail, ironManEmail);

        //Assert
        assertNotEquals(expectedDTO, hulkDTO);

    }
    @Test
    @DisplayName("Test the equals () | Fail | null object")
    public void testCheckSiblingsDTOEqualsMethodDifferentDTONullObject() {

        //Arrange
        String hulkEmail = "hulk@marvel.com";
        String wolverineEmail = "wolverine@marvel.com";

        //Act
        PrepareInfoToCheckIfSiblingsDTO hulkDTO = new PrepareInfoToCheckIfSiblingsDTO(hulkEmail, wolverineEmail);
        PrepareInfoToCheckIfSiblingsDTO expectedDTO = new PrepareInfoToCheckIfSiblingsDTO(null, null);

        //Assert
        assertNotEquals(expectedDTO, hulkDTO);

    }

    @Test
    @DisplayName("Test the equals () | Fail | null object")
    public void testCheckSiblingsDTOEqualsMethodDifferentDTONull() {

        //Arrange
        String hulkEmail = "hulk@marvel.com";
        String wolverineEmail = "wolverine@marvel.com";

        //Act
        PrepareInfoToCheckIfSiblingsDTO hulkDTO = new PrepareInfoToCheckIfSiblingsDTO(hulkEmail, wolverineEmail);
        PrepareInfoToCheckIfSiblingsDTO expectedDTO = null;

        boolean result = hulkDTO.equals(expectedDTO);

        //Assert
        assertNotEquals(expectedDTO, hulkDTO);
        assertFalse(result);
        assertEquals(false, result);

    }

    @Test
    @DisplayName("Test the hashcode | Same hashCode")
    public void testCheckSiblingsDTOTheSameHashCode() {

        //Arrange
        String hulkEmail = "hulk@marvel.com";
        String wolverineEmail = "wolverine@marvel.com";

        //Act
        PrepareInfoToCheckIfSiblingsDTO firstDTO = new PrepareInfoToCheckIfSiblingsDTO(hulkEmail, wolverineEmail);
        PrepareInfoToCheckIfSiblingsDTO secondDTO = new PrepareInfoToCheckIfSiblingsDTO(hulkEmail, wolverineEmail);

        //Assert
        assertEquals(firstDTO.hashCode(), secondDTO.hashCode());

    }
    @Test
    @DisplayName("Test the hashcode | different hashCode | Different email")
    public void testCheckSiblingsDTODifferentHashCodeEmail() {

        //Arrange
        String hulkEmail = "hulk@marvel.com";
        String wolverineEmail = "wolverine@marvel.com";

        //Act
        PrepareInfoToCheckIfSiblingsDTO firstDTO = new PrepareInfoToCheckIfSiblingsDTO(hulkEmail, wolverineEmail);
        PrepareInfoToCheckIfSiblingsDTO secondDTO = new PrepareInfoToCheckIfSiblingsDTO(wolverineEmail,hulkEmail);

        //Assert
        assertNotEquals(firstDTO.hashCode(), secondDTO.hashCode());

    }
    @Test
    @DisplayName("Test the hashcode | different hashCode | Different email")
    public void testCheckSiblingsDTODifferentHashCodeSiblingEmail() {

        //Arrange
        String hulkEmail = "hulk@marvel.com";
        String wolverineEmail = "wolverine@marvel.com";
        String ironManEmail = "ironman@marvel.com";


        //Act
        PrepareInfoToCheckIfSiblingsDTO firstDTO = new PrepareInfoToCheckIfSiblingsDTO(hulkEmail, wolverineEmail);
        PrepareInfoToCheckIfSiblingsDTO secondDTO = new PrepareInfoToCheckIfSiblingsDTO(ironManEmail,wolverineEmail);

        //Assert
        assertNotEquals(firstDTO.hashCode(), secondDTO.hashCode());

    }
    @Test
    @DisplayName("Test the hashcode | different hashCode")
    public void testCheckSiblingsDTODifferentHashCode() {

        //Arrange
        String hulkEmail = "hulk@marvel.com";
        String wolverineEmail = "wolverine@marvel.com";
        String ironManEmail = "ironman@marvel.com";

        //Act
        PrepareInfoToCheckIfSiblingsDTO firstDTO = new PrepareInfoToCheckIfSiblingsDTO(hulkEmail, wolverineEmail);
        PrepareInfoToCheckIfSiblingsDTO secondDTO = new PrepareInfoToCheckIfSiblingsDTO(hulkEmail,ironManEmail);

        //Assert
        assertNotEquals(firstDTO.hashCode(), secondDTO.hashCode());

    }

}