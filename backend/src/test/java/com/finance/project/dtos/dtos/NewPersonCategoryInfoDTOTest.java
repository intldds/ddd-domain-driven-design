package com.finance.project.dtos.dtos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class NewPersonCategoryInfoDTOTest {

    @Test
    @DisplayName("Test for NewPersonCategoryInfoDTO()")
    void newPersonCategoryInfoDTO_ConstructorWithParameters() {

        //Arrange
        String denomination = "Basket";

        //Act
        NewPersonCategoryInfoDTO newPersonCategoryInfoDTO = new NewPersonCategoryInfoDTO(denomination);

        //Assert
        assertEquals(denomination, newPersonCategoryInfoDTO.getDenomination());
    }

    @Test
    @DisplayName("Test for NewPersonCategoryInfoDTO() - Empty Constructor")
    void newPersonCategoryInfoDTO_ConstructorWithoutParameters() {

        //Arrange

        //Act
        NewPersonCategoryInfoDTO newPersonCategoryInfoDTO = new NewPersonCategoryInfoDTO();

        //Assert
        assertEquals(null, newPersonCategoryInfoDTO.getDenomination());
    }

    @Test
    @DisplayName("Test for setCategoryDenomination()")
    void setCategoryDenomination() {

        //Arrange
        String denomination = "Basket";

        //Act
        NewPersonCategoryInfoDTO newPersonCategoryInfoDTO = new NewPersonCategoryInfoDTO(denomination);
        newPersonCategoryInfoDTO.setDenomination("Football");

        //Assert
        assertEquals("Football", newPersonCategoryInfoDTO.getDenomination());
    }

    @Test
    @DisplayName("Test for getCategoryDenomination()")
    void getCategoryDenomination() {

        //Arrange
        String denomination = "Basket";

        //Act
        NewPersonCategoryInfoDTO newPersonCategoryInfoDTO = new NewPersonCategoryInfoDTO(denomination);

        //Assert
        assertEquals(denomination, newPersonCategoryInfoDTO.getDenomination());
    }

    //Equals

    @Test
    @DisplayName("Test for equals() | Success")
    void equalsSuccess() {

        //Arrange

        //First DTO

        String firstDenomination = "Basket";

        NewPersonCategoryInfoDTO firstPersonCategoryInfoDTO = new NewPersonCategoryInfoDTO(firstDenomination);

        //Second DTO
        String secondDenomination = "Basket";

        NewPersonCategoryInfoDTO secondPersonCategoryInfoDTO = new NewPersonCategoryInfoDTO(secondDenomination);

        //Act
        boolean result = firstPersonCategoryInfoDTO.equals(secondPersonCategoryInfoDTO);

        //Assert
        assertEquals(true, result);
    }

    @Test
    @DisplayName("Test for equals() | Success | Same Object")
    void equalsSuccessSameObject() {

        //Arrange

        //DTO
        String firstDenomination = "Basket";

        //Act
        NewPersonCategoryInfoDTO newPersonCategoryInfoDTO = new NewPersonCategoryInfoDTO(firstDenomination);

        boolean result = newPersonCategoryInfoDTO.equals(newPersonCategoryInfoDTO);

        //Assert
        assertEquals(true, result);
    }

    @Test
    @DisplayName("Test For equals() | Fail | Different Class")
    void equalsFailDifferenClass() {

        //Arrange

        //DTO
        String denomination = "Basket";

        NewPersonCategoryInfoDTO newPersonCategoryInfoDTO = new NewPersonCategoryInfoDTO(denomination);

        //Bug Killer
        String bugKiller = "Bug Killer";

        //Act
        boolean result = newPersonCategoryInfoDTO.equals(bugKiller);

        //Assert
        assertEquals(false, result);
    }

    @Test
    @DisplayName("Test For equals() | Fail | Different Category Denomination")
    void equalsFailDifferentCategoryDenomination() {

        //Arrange

        //First DTO
        String firstDenomination = "Basket";

        //Second DTO
        String secondDenomination = "Football";

        //Act
        NewPersonCategoryInfoDTO secondNewPersonCategoryInfoDTO = new NewPersonCategoryInfoDTO(secondDenomination);
        NewPersonCategoryInfoDTO firstNewPersonCategoryInfoDTO = new NewPersonCategoryInfoDTO(firstDenomination);

        //Act
        boolean result = firstNewPersonCategoryInfoDTO.equals(secondNewPersonCategoryInfoDTO);

        //Assert
        assertEquals(false, result);
    }

    @Test
    @DisplayName("Test For equals() | Fail | Null")
    void equalsFailNull() {

        //Arrange

        //DTO
        String denomination = "Basket";

        //Act
        NewPersonCategoryInfoDTO newPersonCategoryInfoDTO = new NewPersonCategoryInfoDTO(denomination);

        boolean result = newPersonCategoryInfoDTO.equals(null);

        //Arrange
        assertEquals(false, result);
    }

    //Hashcode

    @Test
    @DisplayName("Test For hashcode() | Success")
    void hashcodeSuccess() {

        //Arrange

        //First DTO
        String firstDenomination = "Basket";

        //Second DTO
        String secondDenomination = "Basket";

        //Act
        NewPersonCategoryInfoDTO firstNewPersonCategoryInfoDTO = new NewPersonCategoryInfoDTO(firstDenomination);
        NewPersonCategoryInfoDTO secondNewPersonCategoryInfoDTO = new NewPersonCategoryInfoDTO(secondDenomination);

        int firstHashcode = firstNewPersonCategoryInfoDTO.hashCode();
        int secondHashcode = secondNewPersonCategoryInfoDTO.hashCode();

        //Assert
        assertEquals(firstHashcode, secondHashcode);
    }

    @Test
    @DisplayName("Test For hashcode() | Success - expected hashcode")
    void hashcodeSuccessExpectedHashcode() {

        // Arrange

        //DTO
        String denomination = "Basket";

        // Act
        NewPersonCategoryInfoDTO newPersonCategoryInfoDTO = new NewPersonCategoryInfoDTO(denomination);

        int hashcode = newPersonCategoryInfoDTO.hashCode();
        int expectedHashcode = 1982637573;

        // Assert
        assertEquals(expectedHashcode, hashcode);
    }

    @Test
    @DisplayName("Test For hashcode() | Different")
    void hashcodeDifferent() {

        // Arrange

        // First DTO
        String firstDenomination = "Basket";

        // Second DTO
        String secondDenomination = "Basketball";

        // Act
        NewPersonCategoryInfoDTO firstNewPersonCategoryInfoDTO = new NewPersonCategoryInfoDTO(firstDenomination);
        NewPersonCategoryInfoDTO secondNewPersonCategoryInfoDTO = new NewPersonCategoryInfoDTO(secondDenomination);

        int firstHashcode = firstNewPersonCategoryInfoDTO.hashCode();
        int secondHashcode = secondNewPersonCategoryInfoDTO.hashCode();

        // Assert
        assertNotEquals(firstHashcode, secondHashcode);
    }
}