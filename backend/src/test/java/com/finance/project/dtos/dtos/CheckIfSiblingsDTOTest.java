package com.finance.project.dtos.dtos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Ala Matos
 */
class CheckIfSiblingsDTOTest {

    @Test
    @DisplayName("Test the US01_DTO | Happy path")
    public void testCheckSiblingsDTO() {

        //Arrange
        String hulkEmail = "hulk@marvel.com";
        String wolverineEmail = "wolverine@marvel.com";

        //Act
        CheckIfSiblingsDTO firstDTO = new CheckIfSiblingsDTO(hulkEmail, wolverineEmail);

        //Assert
        assertEquals(hulkEmail, firstDTO.getEmail());
        assertEquals(wolverineEmail, firstDTO.getSiblingEmail());

    }

    @Test
    @DisplayName("Test the US01_DTO | Wrong sibling")
    public void testCheckSiblingsDTOWrongSibling() {

        //Arrange
        String hulkEmail = "hulk@marvel.com";
        String wolverineEmail = "wolverine@marvel.com";
        String ironManEmail = "ironman@marvel.com";

        //Act
        CheckIfSiblingsDTO firstDTO = new CheckIfSiblingsDTO(hulkEmail, wolverineEmail);

        //Assert

        assertNotEquals(ironManEmail, firstDTO.getSiblingEmail());

    }

    @Test
    @DisplayName("Test the set method")
    public void testCheckSiblingsDTOSetMethod () {
        //Arrange
        String hulkEmail = "hulk@marvel.com";
        String wolverineEmail = "wolverine@marvel.com";
        String ironManEmail = "ironman@marvel.com";

        //Act
        CheckIfSiblingsDTO firstDTO = new CheckIfSiblingsDTO();
        firstDTO.setEmail(wolverineEmail);
        firstDTO.setSiblingEmail(ironManEmail);

        //Assert

        assertEquals(wolverineEmail, firstDTO.getEmail());
        assertEquals(ironManEmail, firstDTO.getSiblingEmail());

    }

    @Test
    @DisplayName("Test the equals () | Happy path |")
    public void testCheckSiblingsDTOEqualsMethod() {

        //Arrange
        String hulkEmail = "hulk@marvel.com";
        String wolverineEmail = "wolverine@marvel.com";

        //Act
        CheckIfSiblingsDTO hulkDTO = new CheckIfSiblingsDTO(hulkEmail, wolverineEmail);
        CheckIfSiblingsDTO expectedDTO = new CheckIfSiblingsDTO(hulkEmail, wolverineEmail);

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
        CheckIfSiblingsDTO hulkDTO = new CheckIfSiblingsDTO(hulkEmail, wolverineEmail);
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
        CheckIfSiblingsDTO hulkDTO = new CheckIfSiblingsDTO(hulkEmail, wolverineEmail);

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
        CheckIfSiblingsDTO hulkDTO = new CheckIfSiblingsDTO(hulkEmail, wolverineEmail);
        CheckIfSiblingsDTO expectedDTO = new CheckIfSiblingsDTO(ironManEmail, wolverineEmail);

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
        CheckIfSiblingsDTO hulkDTO = new CheckIfSiblingsDTO(hulkEmail, wolverineEmail);
        CheckIfSiblingsDTO expectedDTO = new CheckIfSiblingsDTO(hulkEmail, ironManEmail);

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
        CheckIfSiblingsDTO hulkDTO = new CheckIfSiblingsDTO(hulkEmail, wolverineEmail);
        CheckIfSiblingsDTO expectedDTO = new CheckIfSiblingsDTO(null, null);

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
        CheckIfSiblingsDTO hulkDTO = new CheckIfSiblingsDTO(hulkEmail, wolverineEmail);
        CheckIfSiblingsDTO expectedDTO = null;

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
        CheckIfSiblingsDTO firstDTO = new CheckIfSiblingsDTO(hulkEmail, wolverineEmail);
        CheckIfSiblingsDTO secondDTO = new CheckIfSiblingsDTO(hulkEmail, wolverineEmail);

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
        CheckIfSiblingsDTO firstDTO = new CheckIfSiblingsDTO(hulkEmail, wolverineEmail);
        CheckIfSiblingsDTO secondDTO = new CheckIfSiblingsDTO(wolverineEmail,hulkEmail);

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
        CheckIfSiblingsDTO firstDTO = new CheckIfSiblingsDTO(hulkEmail, wolverineEmail);
        CheckIfSiblingsDTO secondDTO = new CheckIfSiblingsDTO(ironManEmail,wolverineEmail);

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
        CheckIfSiblingsDTO firstDTO = new CheckIfSiblingsDTO(hulkEmail, wolverineEmail);
        CheckIfSiblingsDTO secondDTO = new CheckIfSiblingsDTO(hulkEmail,ironManEmail);

        //Assert
        assertNotEquals(firstDTO.hashCode(), secondDTO.hashCode());

    }

}