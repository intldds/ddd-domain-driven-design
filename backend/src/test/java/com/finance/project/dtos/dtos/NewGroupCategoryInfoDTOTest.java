package com.finance.project.dtos.dtos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class NewGroupCategoryInfoDTOTest {

    //Test for constructor with parameters

    @Test
    @DisplayName("Test For NewGroupCategoryInfoDTO()")
    void NewGroupCategoryInfoDTO() {

        // Arrange

        String categoryDenomination = "Allowance";

        // Act

        NewGroupCategoryInfoDTO newGroupCategoryInfoDTO = new NewGroupCategoryInfoDTO(categoryDenomination);

        // Assert

        assertEquals(categoryDenomination, newGroupCategoryInfoDTO.getCategoryDenomination());
    }

    //Test for constructor withou parameters

    @Test
    @DisplayName("Test For NewGroupCategoryInfoDTO() - Empty Constructor")
    void NewGroupCategoryInfoDTO_EmptyConstructor() {

        // Arrange

        // Act

        NewGroupCategoryInfoDTO newGroupCategoryInfoDTO = new NewGroupCategoryInfoDTO();

        // Assert

        assertEquals(null, newGroupCategoryInfoDTO.getCategoryDenomination());
    }

    //Setters

    @Test
    @DisplayName("Test For setCategoryDenomination()")
    void setCategoryDenomination() {

        // Arrange

        String categoryDenomination = "Allowance";

        NewGroupCategoryInfoDTO newGroupCategoryInfoDTO = new NewGroupCategoryInfoDTO(categoryDenomination);

        // Act

        String newCategoryDenomination = "Clothes";
        newGroupCategoryInfoDTO.setCategoryDenomination(newCategoryDenomination);

        // Assert

        assertEquals(newCategoryDenomination, newGroupCategoryInfoDTO.getCategoryDenomination());
    }

    //Getters

    @Test
    @DisplayName("Test For getCategoryDenomination()")
    void getCategoryDenomination() {

        // Arrange

        String categoryDenomination = "Allowance";

        // Act

        NewGroupCategoryInfoDTO newGroupCategoryInfoDTO = new NewGroupCategoryInfoDTO(categoryDenomination);

        // Assert

        assertEquals(categoryDenomination, newGroupCategoryInfoDTO.getCategoryDenomination());
    }

    //Equals

    @Test
    @DisplayName("Test For equals() | Success")
    void equalsSuccess() {

        // Arrange

        //First DTO

        String firstCategoryDenomination = "Allowance";
        NewGroupCategoryInfoDTO firstNewGroupCategoryInfoDTO = new NewGroupCategoryInfoDTO(firstCategoryDenomination);

        //Second DTO

        String secondCategoryDenomination = "Allowance";
        NewGroupCategoryInfoDTO secondNewGroupCategoryInfoDTO = new NewGroupCategoryInfoDTO(secondCategoryDenomination);

        // Act

        boolean result = firstNewGroupCategoryInfoDTO.equals(secondNewGroupCategoryInfoDTO);

        // Assert

        assertEquals(true, result);
    }

    @Test
    @DisplayName("Test For equals() | categoryDenomination Different")
    void equalsCategoryDenominationDifferent() {

        // Arrange

        //First DTO

        String firstCategoryDenomination = "Allowance";
        NewGroupCategoryInfoDTO firstNewGroupCategoryInfoDTO = new NewGroupCategoryInfoDTO(firstCategoryDenomination);

        //Second DTO

        String secondCategoryDenomination = "Clothes";
        NewGroupCategoryInfoDTO secondNewGroupCategoryInfoDTO = new NewGroupCategoryInfoDTO(secondCategoryDenomination);

        // Act

        boolean result = firstNewGroupCategoryInfoDTO.equals(secondNewGroupCategoryInfoDTO);

        // Assert

        assertEquals(false, result);
    }

    @Test
    @DisplayName("Test For equals() | Same Object")
    void equalsSameObject() {

        // Arrange

        //DTO

        String categoryDenomination = "Allowance";
        NewGroupCategoryInfoDTO newGroupCategoryInfoDTO = new NewGroupCategoryInfoDTO(categoryDenomination);

        // Act

        boolean result = newGroupCategoryInfoDTO.equals(newGroupCategoryInfoDTO);

        // Assert

        assertEquals(true, result);
    }

    @Test
    @DisplayName("Test For equals() | Null")
    void equalsNull() {

        // Arrange

        //DTO

        String categoryDenomination = "Allowance";
        NewGroupCategoryInfoDTO newGroupCategoryInfoDTO = new NewGroupCategoryInfoDTO(categoryDenomination);

        // Act

        boolean result = newGroupCategoryInfoDTO.equals(null);

        // Assert

        assertEquals(false, result);
    }

    @Test
    @DisplayName("Test For equals() | Different Class")
    void equalsDifferentClass() {

        // Arrange

        //DTO

        String categoryDenomination = "Allowance";
        NewGroupCategoryInfoDTO newGroupCategoryInfoDTO = new NewGroupCategoryInfoDTO(categoryDenomination);

        String bugKiller = "Bug Killer";

        // Act

        boolean result = newGroupCategoryInfoDTO.equals(bugKiller);

        // Assert

        assertEquals(false, result);
    }

    //Hashcode

    @Test
    @DisplayName("Test For hashcode() | Success")
    void hashcodeSuccess() {

        // Arrange

        //First DTO

        String firstCategoryDenomination = "Allowance";
        NewGroupCategoryInfoDTO firstNewGroupCategoryInfoDTO = new NewGroupCategoryInfoDTO(firstCategoryDenomination);

        //Second DTO

        String secondCategoryDenomination = "Allowance";
        NewGroupCategoryInfoDTO secondNewGroupCategoryInfoDTO = new NewGroupCategoryInfoDTO(secondCategoryDenomination);

        // Act

        int firstHashcode = firstNewGroupCategoryInfoDTO.hashCode();
        int secondHashcode = secondNewGroupCategoryInfoDTO.hashCode();

        // Assert

        assertEquals(firstHashcode, secondHashcode);
    }

    @Test
    @DisplayName("Test For hashcode() | Success - expected hashcode")
    void hashcodeSuccessExpectedHashcode() {

        // Arrange

        //DTO

        String categoryDenomination = "Allowance";
        NewGroupCategoryInfoDTO newGroupCategoryInfoDTO = new NewGroupCategoryInfoDTO(categoryDenomination);

        // Act

        int hashcode = newGroupCategoryInfoDTO.hashCode();
        int expectedHashcode = -1918584809;

        // Assert

        assertEquals(expectedHashcode, hashcode);
    }

    @Test
    @DisplayName("Test For hashcode() | Different")
    void hashcodeDifferent() {

        // Arrange

        //First DTO

        String firstCategoryDenomination = "Allowance";
        NewGroupCategoryInfoDTO firstNewGroupCategoryInfoDTO = new NewGroupCategoryInfoDTO(firstCategoryDenomination);

        //Second DTO

        String secondCategoryDenomination = "Clothes";
        NewGroupCategoryInfoDTO secondNewGroupCategoryInfoDTO = new NewGroupCategoryInfoDTO(secondCategoryDenomination);

        // Act

        int firstHashcode = firstNewGroupCategoryInfoDTO.hashCode();
        int secondHashcode = secondNewGroupCategoryInfoDTO.hashCode();

        // Assert

        assertNotEquals(firstHashcode, secondHashcode);
    }
}