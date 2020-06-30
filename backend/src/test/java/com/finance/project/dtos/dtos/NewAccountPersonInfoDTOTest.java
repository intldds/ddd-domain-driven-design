package com.finance.project.dtos.dtos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class NewAccountPersonInfoDTOTest {

    // Test constructor with parameters

    @Test
    @DisplayName("Test For NewPersonAccountInfoDTO()")
    void NewPersonAccountInfoDTO() {

        // Arrange
        String email = "santi@gmail.com";
        String denomination = "Tennis";
        String description = "Tickets for Wimbledon 2021";

        // Act
        NewPersonAccountInfoDTO newPersonAccountInfoDTO = new NewPersonAccountInfoDTO(description, denomination);

        // Assert
        assertEquals(denomination, newPersonAccountInfoDTO.getDenomination());
        assertEquals(description, newPersonAccountInfoDTO.getDescription());
    }


    // Test constructor without parameters

    @Test
    @DisplayName("Test For NewGroupCategoryInfoDTO() - Empty Constructor")
    void NewGroupCategoryInfoDTO_EmptyConstructor() {

        // Arrange

        // Act
        NewPersonAccountInfoDTO newPersonAccountInfoDTO = new NewPersonAccountInfoDTO();

        // Assert
        assertEquals(null, newPersonAccountInfoDTO.getDenomination());
        assertEquals(null, newPersonAccountInfoDTO.getDescription());
    }


    // Setters


    @Test
    @DisplayName("Test For setPersonDenomination()")
    void setPersonDenomination() {

        // Arrange
        String email = "santi@gmail.com";
        String denomination = "Tennis";
        String description = "Tickets for Wimbledon 2021";

        NewPersonAccountInfoDTO newPersonAccountInfoDTO = new NewPersonAccountInfoDTO(description, denomination);

        // Act
        String newDenomination = "Football";
        newPersonAccountInfoDTO.setDenomination(newDenomination);

        // Assert
        assertEquals(newDenomination, newPersonAccountInfoDTO.getDenomination());
        assertEquals(description, newPersonAccountInfoDTO.getDescription());
    }


    @Test
    @DisplayName("Test for setAccountDescription()")
    void setAccountDescription() {

        // Arrange
        String email = "santi@gmail.com";
        String denomination = "Tennis";
        String description = "Tickets for Wimbledon 2021";

        NewPersonAccountInfoDTO newPersonAccountInfoDTO = new NewPersonAccountInfoDTO(description, denomination);

        // Act
        String newDescription = "Tickets for AO 2022";
        newPersonAccountInfoDTO.setDescription(newDescription);

        // Assert
        assertEquals(denomination, newPersonAccountInfoDTO.getDenomination());
        assertEquals(newDescription, newPersonAccountInfoDTO.getDescription());
    }


    // Getters


    @Test
    @DisplayName("Test For getAccountDenomination()")
    void getAccountDenomination() {

        // Arrange
        String email = "santi@gmail.com";
        String denomination = "Tennis";
        String description = "Tickets for Wimbledon 2021";

        // Act
        NewPersonAccountInfoDTO newPersonAccountInfoDTO = new NewPersonAccountInfoDTO(description, denomination);

        // Assert
        assertEquals(denomination, newPersonAccountInfoDTO.getDenomination());
    }


    @Test
    @DisplayName("Test For getAccountDescription()")
    void getAccountDescription() {

        // Arrange
        String email = "santi@gmail.com";
        String denomination = "Tennis";
        String description = "Tickets for Wimbledon 2021";

        // Act
        NewPersonAccountInfoDTO newPersonAccountInfoDTO = new NewPersonAccountInfoDTO(description, denomination);

        // Assert
        assertEquals(description, newPersonAccountInfoDTO.getDescription());
    }


    // Equals

    @Test
    @DisplayName("Test For equals() | Success")
    void equalsSuccess() {

        // Arrange

        // First DTO

        String firstDenomination = "Tennis";
        String firstDescription = "Tickets for Wimbledon 2021";

            // Act first DTO
            NewPersonAccountInfoDTO firstNewPersonAccountInfoDTO = new NewPersonAccountInfoDTO(firstDenomination, firstDescription);

        // Second DTO
        String secondDenomination = "Tennis";
        String secondDescription = "Tickets for Wimbledon 2021";

            // Act second DTO
            NewPersonAccountInfoDTO secondNewPersonAccountInfoDTO = new NewPersonAccountInfoDTO(secondDenomination, secondDescription);

        // Act
        boolean result = firstNewPersonAccountInfoDTO.equals(secondNewPersonAccountInfoDTO);

        // Assert
        assertEquals(true, result);
    }


    @Test
    @DisplayName("Test For equals() | different denomination")
    void equalsAccountDenominationDifferent() {

        // Arrange

        // First DTO

        String firstDenomination = "Tennis";
        String firstDescription = "Tickets for Wimbledon 2021";

        // Act first DTO
        NewPersonAccountInfoDTO firstNewPersonAccountInfoDTO = new NewPersonAccountInfoDTO(firstDenomination, firstDescription);

        // Second DTO
        String secondDenomination = "Tennis & Football";
        String secondDescription = "Tickets for Wimbledon 2021";

        // Act second DTO
        NewPersonAccountInfoDTO secondNewPersonAccountInfoDTO = new NewPersonAccountInfoDTO(secondDenomination, secondDescription);

        // Act
        boolean result = firstNewPersonAccountInfoDTO.equals(secondNewPersonAccountInfoDTO);

        // Assert
        assertEquals(false, result);
    }


    @Test
    @DisplayName("Test For equals() | description of account different")
    void equalsAccountDescriptionDifferent() {

        // Arrange

        // First DTO

        String firstDenomination = "Tennis";
        String firstDescription = "Tickets for Wimbledon 2021";

        // Act first DTO
        NewPersonAccountInfoDTO firstNewPersonAccountInfoDTO = new NewPersonAccountInfoDTO(firstDenomination, firstDescription);

        // Second DTO
        String secondDenomination = "Tennis";
        String secondDescription = "Tickets for Roland Garros 2021";

        // Act second DTO
        NewPersonAccountInfoDTO secondNewPersonAccountInfoDTO = new NewPersonAccountInfoDTO(secondDenomination, secondDescription);

        // Act
        boolean result = firstNewPersonAccountInfoDTO.equals(secondNewPersonAccountInfoDTO);

        // Assert
        assertEquals(false, result);
    }


    @Test
    @DisplayName("Test For equals() | Same Object")
    void equalsSameObject() {

        // Arrange

        //DTO
        String denomination = "Tennis";
        String description = "Tickets for Roland Garros 2021";

        NewPersonAccountInfoDTO newPersonAccountInfoDTO = new NewPersonAccountInfoDTO(denomination, description);

        // Act
        boolean result = newPersonAccountInfoDTO.equals(newPersonAccountInfoDTO);

        // Assert
        assertEquals(true, result);
    }


    @Test
    @DisplayName("Test For equals() | Null")
    void equalsNull() {

        // Arrange

        //DTO
        String denomination = "Tennis";
        String description = "Tickets for Roland Garros 2021";

        NewPersonAccountInfoDTO newPersonAccountInfoDTO = new NewPersonAccountInfoDTO(denomination, description);

        // Act

        boolean result = newPersonAccountInfoDTO.equals(null);

        // Assert

        assertEquals(false, result);
    }


    @Test
    @DisplayName("Test For equals() | Different Class")
    void equalsDifferentClass() {

        // Arrange

        //DTO
        String denomination = "Tennis";
        String description = "Tickets for Roland Garros 2021";

        NewPersonAccountInfoDTO newPersonAccountInfoDTO = new NewPersonAccountInfoDTO(denomination, description);

        String random = "random";

        // Act

        boolean result = newPersonAccountInfoDTO.equals(random);

        // Assert

        assertEquals(false, result);
    }


    // HashCode

    @Test
    @DisplayName("Test For hashcode() | Success")
    void hashcodeSuccess() {

        // Arrange

        // First DTO

        String firstDenomination = "Tennis";
        String firstDescription = "Tickets for Wimbledon 2021";

        // Act first DTO
        NewPersonAccountInfoDTO firstNewPersonAccountInfoDTO = new NewPersonAccountInfoDTO(firstDenomination, firstDescription);

        // Second DTO
        String secondDenomination = "Tennis";
        String secondDescription = "Tickets for Wimbledon 2021";

        // Act second DTO
        NewPersonAccountInfoDTO secondNewPersonAccountInfoDTO = new NewPersonAccountInfoDTO(secondDenomination, secondDescription);

        // Act

        int firstHashcode = firstNewPersonAccountInfoDTO.hashCode();
        int secondHashcode = secondNewPersonAccountInfoDTO.hashCode();

        // Assert

        assertEquals(firstHashcode, secondHashcode);
    }


    @Test
    @DisplayName("Test For hashcode() | Success - expected hashcode")
    void hashcodeSuccessExpectedHashcode() {

        // Arrange

        //DTO
        String denomination = "Tennis";
        String description = "Tickets for Roland Garros 2021";

        NewPersonAccountInfoDTO newPersonAccountInfoDTO = new NewPersonAccountInfoDTO(description, denomination);

        // Act

        int hashcode = newPersonAccountInfoDTO.hashCode();
        int expectedHashcode = -2089440835;

        // Assert

        assertEquals(expectedHashcode, hashcode);
    }



    @Test
    @DisplayName("Test For hashcode() | Different")
    void hashcodeDifferent() {

        // Arrange

        // First DTO

        String firstDenomination = "Tennis";
        String firstDescription = "Tickets for Wimbledon 2021";

        // Act first DTO
        NewPersonAccountInfoDTO firstNewPersonAccountInfoDTO = new NewPersonAccountInfoDTO(firstDenomination, firstDescription);

        // Second DTO
        String secondDenomination = "Tennis";
        String secondDescription = "Tickets for Roland Garros 2021";

        // Act second DTO
        NewPersonAccountInfoDTO secondNewPersonAccountInfoDTO = new NewPersonAccountInfoDTO(secondDenomination, secondDescription);

        // Act

        int firstHashcode = firstNewPersonAccountInfoDTO.hashCode();
        int secondHashcode = secondNewPersonAccountInfoDTO.hashCode();

        // Assert

        assertNotEquals(firstHashcode, secondHashcode);
    }



}
