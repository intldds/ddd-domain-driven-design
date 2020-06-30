package com.finance.project.dtos.dtos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Elisabete_Cavaleiro
 */

class AddPersonToGroupDTOTest {

    //Constructor with parameters

    @Test
    @DisplayName("Test For US03_DTO()")
    void US03_DTO() {

        // Arrange

        String personEmail = "cavaleiro@gmail.com";
        String groupDenomination = "Dance";

        // Act

        AddPersonToGroupDTO addPersonToGroupDTO = new AddPersonToGroupDTO(personEmail, groupDenomination);


        // Assert

        assertEquals(personEmail, addPersonToGroupDTO.getEmail());
        assertEquals(groupDenomination, addPersonToGroupDTO.getDenomination());
    }


    //Test for constructor without parameters

    @Test
    @DisplayName("Test For US03_DTO(): Empty constructor")
    void US03_DTO_Empty_Constructor() {

        // Arrange


        // Act

        AddPersonToGroupDTO addPersonToGroupDTO = new AddPersonToGroupDTO();
        addPersonToGroupDTO.setEmail("joana@gmail.com");
        addPersonToGroupDTO.setDenomination("Dance");

        // Assert

        assertEquals("joana@gmail.com", addPersonToGroupDTO.getEmail());
        assertEquals("Dance", addPersonToGroupDTO.getDenomination());
    }

    //Sets

    @Test
    @DisplayName("Test For US03_DTO : Set Email")
    void US03_DTO_SetEmail() {

        // Arrange

        String personEmail = "cavaleiro@gmail.com";
        String groupDenomination = "Dance";

        // Act

        AddPersonToGroupDTO addPersonToGroupDTO = new AddPersonToGroupDTO(personEmail, groupDenomination);
        addPersonToGroupDTO.setEmail("joana@gmail.com");

        // Assert

        assertEquals("joana@gmail.com", addPersonToGroupDTO.getEmail());

    }

    @Test
    @DisplayName("Test For US03_DTO : Set Group Denomination")
    void US03_DTO_SetGroupDenomination() {

        // Arrange

        String personEmail = "cavaleiro@gmail.com";
        String groupDenomination = "Dance";

        // Act

        AddPersonToGroupDTO addPersonToGroupDTO = new AddPersonToGroupDTO(personEmail, groupDenomination);
        addPersonToGroupDTO.setDenomination("Gym");

        // Assert

        assertEquals("Gym", addPersonToGroupDTO.getDenomination());

    }



    //Gets

    @Test
    @DisplayName("Test For getGroupDenomination()")
    void getGroupDenomination() {

        // Arrange

        String personEmail = "cavaleiro@gmail.com";
        String groupDenomination = "Dance";

        // Act

        AddPersonToGroupDTO addPersonToGroupDTO = new AddPersonToGroupDTO(personEmail, groupDenomination);


        // Assert

        assertEquals(groupDenomination, addPersonToGroupDTO.getDenomination());
    }

    @Test
    @DisplayName("Test For getPersonEmail()")
    void getPersonEmail() {

        // Arrange

        String personEmail = "cavaleiro@gmail.com";
        String groupDenomination = "Dance";

        // Act

        AddPersonToGroupDTO addPersonToGroupDTO = new AddPersonToGroupDTO(personEmail, groupDenomination);


        // Assert

        assertEquals(personEmail, addPersonToGroupDTO.getEmail());
    }

//Test equals

    @Test
    @DisplayName("Test For equals() | Success")
    void equalsSuccess() {

        // Arrange

        //First DTO

        String firstPersonEmail = "joana@gmail.com";
        String firstGroupDenomination = "Tennis";
        AddPersonToGroupDTO firstAddPersonToGroupDTO = new AddPersonToGroupDTO(firstPersonEmail, firstGroupDenomination);

        //Second DTO
        String secondPersonEmail = "joana@gmail.com";
        String secondGroupDenomination = "Tennis";
        AddPersonToGroupDTO secondAddPersonToGroupDTO = new AddPersonToGroupDTO(secondPersonEmail, secondGroupDenomination);

        // Act

        boolean result = firstAddPersonToGroupDTO.equals(secondAddPersonToGroupDTO);

        // Assert

        assertEquals(true, result);
    }

    @Test
    @DisplayName("Test For equals() | Success | Same Object")
    void equalsSuccessSameObject() {

        // Arrange

        //First DTO

        String firstPersonEmail = "joana@gmail.com";
        String firstGroupDenomination = "Tennis";
        AddPersonToGroupDTO firstAddPersonToGroupDTO = new AddPersonToGroupDTO(firstPersonEmail, firstGroupDenomination);

        // Act

        boolean result = firstAddPersonToGroupDTO.equals(firstAddPersonToGroupDTO);

        // Assert

        assertEquals(true, result);
    }

    @Test
    @DisplayName("Test For equals() | Fail | Email")
    void equalsFailEmail() {

        // Arrange

        //First DTO

        String firstPersonEmail = "joana@gmail.com";
        String firstGroupDenomination = "Tennis";
        AddPersonToGroupDTO firstAddPersonToGroupDTO = new AddPersonToGroupDTO(firstPersonEmail, firstGroupDenomination);

        //Second DTO
        String secondPersonEmail = "j@gmail.com";
        String secondGroupDenomination = "Tennis";
        AddPersonToGroupDTO secondAddPersonToGroupDTO = new AddPersonToGroupDTO(secondPersonEmail, secondGroupDenomination);

        // Act

        boolean result = firstAddPersonToGroupDTO.equals(secondAddPersonToGroupDTO);

        // Assert

        assertEquals(false, result);
    }

    @Test
    @DisplayName("Test For equals() | Fail | Group Denomination")
    void equalsFailGroupDenomination() {

        // Arrange

        //First DTO

        String firstPersonEmail = "joana@gmail.com";
        String firstGroupDenomination = "Tennis";
        AddPersonToGroupDTO firstAddPersonToGroupDTO = new AddPersonToGroupDTO(firstPersonEmail, firstGroupDenomination);

        //Second DTO
        String secondPersonEmail = "joana@gmail.com";
        String secondGroupDenomination = "Dance";
        AddPersonToGroupDTO secondAddPersonToGroupDTO = new AddPersonToGroupDTO(secondPersonEmail, secondGroupDenomination);

        // Act

        boolean result = firstAddPersonToGroupDTO.equals(secondAddPersonToGroupDTO);

        // Assert

        assertEquals(false, result);
    }


    @Test
    @DisplayName("Test For equals() | Fail | Different Class")
    void equalsFailDifferentClass() {

        // Arrange

        //First DTO

        String firstPersonEmail = "joana@gmail.com";
        String firstGroupDenomination = "Tennis";
        AddPersonToGroupDTO firstAddPersonToGroupDTO = new AddPersonToGroupDTO(firstPersonEmail, firstGroupDenomination);

        String name = "Joana";



        // Act

        boolean result = firstAddPersonToGroupDTO.equals(name);

        // Assert

        assertEquals(false, result);
    }

    @Test
    @DisplayName("Test For equals() | Fail | Null")
    void equalsFailNull() {

        // Arrange

        //First DTO

        String firstPersonEmail = "joana@gmail.com";
        String firstGroupDenomination = "Tennis";
        AddPersonToGroupDTO firstAddPersonToGroupDTO = new AddPersonToGroupDTO(firstPersonEmail, firstGroupDenomination);



        // Act

        boolean result = firstAddPersonToGroupDTO.equals(null);

        // Assert

        assertEquals(false, result);
    }

    @Test
    @DisplayName("Test For US03_DTO() | Success")
    void hashCodeSuccess() {

        // Arrange

        //Runners

        String firstPersonEmail = "joana@gmail.com";
        String firstGroupDenomination = "Tennis";
        AddPersonToGroupDTO firstAddPersonToGroupDTO = new AddPersonToGroupDTO(firstPersonEmail, firstGroupDenomination);

        AddPersonToGroupDTO secondAddPersonToGroupDTO = new AddPersonToGroupDTO(firstPersonEmail, firstGroupDenomination);



        // Act

        boolean hashCodeResult = firstAddPersonToGroupDTO.hashCode()== secondAddPersonToGroupDTO.hashCode();


        // Assert

        assertEquals(true, hashCodeResult);
        assertTrue(firstAddPersonToGroupDTO.hashCode()== firstAddPersonToGroupDTO.hashCode());
        assertEquals(true, firstAddPersonToGroupDTO.hashCode()== firstAddPersonToGroupDTO.hashCode() );
        assertTrue(firstAddPersonToGroupDTO.hashCode()== secondAddPersonToGroupDTO.hashCode());
    }

    @Test
    @DisplayName("Test For US03_DTO() | Fail")
    void hashCodeFail() {

        // Arrange

        //Runners

        String firstPersonEmail = "joana@gmail.com";
        String firstGroupDenomination = "Tennis";
        String secondGroupDenomination = "Dance";
        AddPersonToGroupDTO firstAddPersonToGroupDTO = new AddPersonToGroupDTO(firstPersonEmail, firstGroupDenomination);

        AddPersonToGroupDTO secondAddPersonToGroupDTO = new AddPersonToGroupDTO(firstPersonEmail, secondGroupDenomination);



        // Act

        boolean hashCodeResult = firstAddPersonToGroupDTO.hashCode()== secondAddPersonToGroupDTO.hashCode();


        // Assert

        assertEquals(false, hashCodeResult);
        assertFalse(firstAddPersonToGroupDTO.hashCode()== secondAddPersonToGroupDTO.hashCode());
    }

}