package com.finance.project.dtos.dtos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CreatePersonAccountDTOTest {


    @Test
    @DisplayName("test constructor US06_DTO")
    void US06_DTO_Constructor() {

        // Arrange
        String email = "santi@gmail.com";
        String denomination = "Tennis";
        String description = "Tickets for Roland Garros 2020";

        // Act
        CreatePersonAccountDTO createPersonAccountDTO = new CreatePersonAccountDTO(email, description, denomination);

        // Assert
        assertEquals(email, createPersonAccountDTO.getEmail());
        assertEquals(denomination, createPersonAccountDTO.getDenomination());
        assertEquals(description, createPersonAccountDTO.getDescription());
    }


    @Test
    @DisplayName("Test getEmail")
    void getEmail() {

        // Arrange
        String email = "santi@gmail.com";
        String denomination = "Tennis";
        String description = "Tickets for Roland Garros 2020";

        // Act
        CreatePersonAccountDTO createPersonAccountDTO = new CreatePersonAccountDTO(email, denomination, description);

        // Assert
        assertEquals(email, createPersonAccountDTO.getEmail());
    }


    @Test
    @DisplayName("Test getDenomination")
    void getDenomination() {

        // Arrange
        String email = "santi@gmail.com";
        String denomination = "Tennis";
        String description = "Tickets for Roland Garros 2020";

        // Act
        CreatePersonAccountDTO createPersonAccountDTO = new CreatePersonAccountDTO(email, description, denomination);

        // Assert
        assertEquals(denomination, createPersonAccountDTO.getDenomination());
    }


    @Test
    @DisplayName("Test getDescription")
    void getDescription() {

        // Arrange
        String email = "santi@gmail.com";
        String denomination = "Tennis";
        String description = "Tickets for Roland Garros 2020";

        // Act
        CreatePersonAccountDTO createPersonAccountDTO = new CreatePersonAccountDTO(email, description, denomination);

        // Assert
        assertEquals(description, createPersonAccountDTO.getDescription());
    }


    //Test Equals


    @Test
    @DisplayName("Test Equals - Same Object")
    void Equals_SameObject() {

        // Arrange
        String email = "santi@gmail.com";
        String denomination = "Tennis";
        String description = "Tickets for Roland Garros 2020";

        // Act
        CreatePersonAccountDTO createPersonAccountDTO = new CreatePersonAccountDTO(email, denomination, description);

        boolean result = createPersonAccountDTO.equals(createPersonAccountDTO);

        // Assert
        assertEquals(true, result);
        assertTrue(result);
    }

    @Test
    @DisplayName("Test Equals - Same Information")
    void Equals_DifferentObjectsSameInformation() {

        // Arrange
        String email = "santi@gmail.com";
        String denomination = "Tennis";
        String description = "Tickets for Roland Garros 2020";

        // Act
        CreatePersonAccountDTO createPersonAccountDTO = new CreatePersonAccountDTO(email, denomination, description);
        CreatePersonAccountDTO createPersonAccountDTO1 = new CreatePersonAccountDTO(email, denomination, description);

        boolean result = createPersonAccountDTO.equals(createPersonAccountDTO1);

        // Assert
        assertEquals(createPersonAccountDTO.equals(createPersonAccountDTO1), createPersonAccountDTO1.equals(createPersonAccountDTO));
        assertEquals(true, result);
        assertTrue(result);
    }


    @Test
    @DisplayName("Test Equals - Same Data")
    void Equals_DifferentObjectsSameData() {

        // Arrange A
        String emailA = "santi@gmail.com";
        String denominationA = "Tennis";
        String descriptionA = "Tickets for Roland Garros 2020";


        // Arrange B
        String emailB = "santi@gmail.com";
        String denominationB = "Tennis";
        String descriptionB = "Tickets for Roland Garros 2020";

        // Act
        CreatePersonAccountDTO createPersonAccountDTOA = new CreatePersonAccountDTO(emailA, denominationA, descriptionA);
        CreatePersonAccountDTO createPersonAccountDTOB = new CreatePersonAccountDTO(emailB, denominationB, descriptionB);

        boolean result = createPersonAccountDTOA.equals(createPersonAccountDTOB);

        // Assert
        assertEquals(createPersonAccountDTOA.equals(createPersonAccountDTOB), createPersonAccountDTOA.equals(createPersonAccountDTOB));
        assertEquals(true, result);
        assertTrue(result);
    }

    @Test
    @DisplayName("Test Equals - Same Data-gets")
    void Equals_DifferentObjectsSameData_gets() {

        // Arrange A
        String emailA = "santi@gmail.com";
        String denominationA = "Tennis";
        String descriptionA = "Tickets for Roland Garros 2020";


        // Arrange B
        String emailB = "santi@gmail.com";
        String denominationB = "Tennis";
        String descriptionB = "Tickets for Roland Garros 2020";

        // Act
        CreatePersonAccountDTO createPersonAccountDTOA = new CreatePersonAccountDTO(emailA, denominationA, descriptionA);
        CreatePersonAccountDTO createPersonAccountDTOB = new CreatePersonAccountDTO(emailB, denominationB, descriptionB);

        boolean result = createPersonAccountDTOA.equals(createPersonAccountDTOB);
        boolean resultEmail = createPersonAccountDTOA.getEmail().equals(createPersonAccountDTOB.getEmail());
        boolean resultDenomination = createPersonAccountDTOA.getDenomination().equals(createPersonAccountDTOB.getDenomination());
        boolean resultDescription = createPersonAccountDTOA.getDescription().equals(createPersonAccountDTOB.getDescription());

        // Assert
        assertEquals(true, result);
        assertEquals(createPersonAccountDTOA.equals(createPersonAccountDTOB), createPersonAccountDTOA.equals(createPersonAccountDTOB));
        assertEquals(true, resultDenomination);
        assertEquals(true, resultEmail);
        assertEquals(true,resultDescription);

    }

    @Test
    @DisplayName("Test Equals -Different Information Information-email")
    void Equals_DifferentObjectsDifferentInformation_Email() {

        // Arrange
        String email = "santi@gmail.com";
        String denomination = "Tennis";
        String description = "Tickets for Roland Garros 2021";;

        // Arrange A

        String emailA = "santiago@gmail.com";
        String denominationA = "Tennis";;
        String descriptionA = "Tickets for Roland Garros 2021";

        // Act
        CreatePersonAccountDTO createPersonAccountDTO = new CreatePersonAccountDTO(email, denomination, description);
        CreatePersonAccountDTO createPersonAccountDTOA = new CreatePersonAccountDTO(emailA, denominationA, descriptionA);

        boolean result = createPersonAccountDTO.equals(createPersonAccountDTOA);

        // Assert
        assertEquals(false, result);
        assertFalse(result);
    }

    @Test
    @DisplayName("Test Equals -Different Information Information-denomination")
    void Equals_DifferentObjectsDifferentInformation_Denomination() {

        // Arrange
        String email = "santi@gmail.com";
        String denomination = "Tennis";
        String description = "Tickets for Roland Garros 2021";

        // Arrange A

        String emailA = "santi@gmail.com";
        String denominationA = "TennisWeek";;
        String descriptionA = "Tickets for Roland Garros 2021";

        // Act
        CreatePersonAccountDTO createPersonAccountDTO = new CreatePersonAccountDTO(email, denomination, description);
        CreatePersonAccountDTO createPersonAccountDTOA = new CreatePersonAccountDTO(emailA, denominationA, descriptionA);

        boolean result = createPersonAccountDTO.equals(createPersonAccountDTOA);

        // Assert
        assertEquals(false, result);
        assertFalse(result);
    }

    @Test
    @DisplayName("Test Equals -Different Information Information-description")
    void Equals_DifferentObjectsDifferentInformation_Description() {

        // Arrange
        String email = "santi@gmail.com";
        String denomination = "Tennis";
        String description = "Tennis";;

        // Arrange A

        String emailA = "santi@gmail.com";
        String denominationA = "Tennis";;
        String descriptionA = "Tickets for Roland Garros 2020";

        // Act
        CreatePersonAccountDTO createPersonAccountDTO = new CreatePersonAccountDTO(email, denomination, description);
        CreatePersonAccountDTO createPersonAccountDTOA = new CreatePersonAccountDTO(emailA, denominationA, descriptionA);

        boolean result = createPersonAccountDTO.equals(createPersonAccountDTOA);

        // Assert
        assertEquals(false, result);
        assertFalse(result);
    }

    @Test
    @DisplayName("Test Equals -NotIstanceOf")
    void Equals_NotInstanceOf() {

        // Arrange
        String email = "santi@gmail.com";
        String denomination = "Tennis";
        String description = "Tickets for Roland Garros 2020";

        // Act
        CreatePersonAccountDTO createPersonAccountDTO = new CreatePersonAccountDTO(email, denomination, description);


        boolean result = createPersonAccountDTO.equals(email);

        // Assert

        assertEquals(false, result);
        assertFalse(result);
    }

    @Test
    @DisplayName("Test Equals -Null")
    void Equals_Null() {

        // Arrange
        String email = "santi@gmail.com";
        String denomination = "Tennis";
        String description = "Tickets for Roland Garros 2020";

        // Arrange A

        String emailA = "santiago@gmail.com";
        String denominationA = "Tennis_Week";
        String descriptionA = "Tickets for Roland Garros 2021";

        // Act
        CreatePersonAccountDTO createPersonAccountDTO = new CreatePersonAccountDTO(email, denomination, description);
        CreatePersonAccountDTO createPersonAccountDTOA = null;

        boolean result = createPersonAccountDTO.equals(createPersonAccountDTOA);

        // Assert

        assertEquals(false, result);
        assertFalse(result);
    }

    //hashcode

    @Test
    @DisplayName("Test HashCode -True")
    void HashCode_True() {

        // Arrange
        String email = "santi@gmail.com";
        String denomination = "Tennis";
        String description = "Tickets for Roland Garros 2020";

        // Act
        CreatePersonAccountDTO createPersonAccountDTO = new CreatePersonAccountDTO(email, denomination, description);
        CreatePersonAccountDTO createPersonAccountDTOA = new CreatePersonAccountDTO(email, denomination, description);

        boolean result = createPersonAccountDTO.hashCode()==createPersonAccountDTOA.hashCode();

        // Assert
        assertTrue(createPersonAccountDTO.hashCode()==createPersonAccountDTOA.hashCode());
        assertEquals(true, result);
        assertTrue(result);
    }

    @Test
    @DisplayName("Test HashCodde -false")
    void hashCode_false() {

        // Arrange
        String email = "santi@gmail.com";
        String denomination = "Tennis";
        String description = "Tickets for Roland Garros 2020";

        // Arrange A

        String emailA = "santiago@gmail.com";
        String denominationA = "Tennis_Week";
        String descriptionA = "Tickets for Roland Garros 2021";

        // Act
        CreatePersonAccountDTO createPersonAccountDTO = new CreatePersonAccountDTO(email, denomination, description);
        CreatePersonAccountDTO createPersonAccountDTOA = new CreatePersonAccountDTO(emailA, denominationA, descriptionA);

        boolean result = createPersonAccountDTO.hashCode()==createPersonAccountDTOA.hashCode();

        // Assert
        assertFalse(createPersonAccountDTO.hashCode()==createPersonAccountDTOA.hashCode());
        assertEquals(false, result);
        assertFalse(result);
    }


}
