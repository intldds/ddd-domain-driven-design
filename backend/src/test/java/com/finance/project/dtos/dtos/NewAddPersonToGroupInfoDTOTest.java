package com.finance.project.dtos.dtos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NewAddPersonToGroupInfoDTOTest {


    //Test for constructor with parameters

    @Test
    @DisplayName("Test For US03_DTO()")
    void US03_DTO() {

        // Arrange

        String personEmail = "cavaleiro@gmail.com";
        String groupDenomination = "Dance";

        // Act

        NewAddPersonToGroupInfoDTO newAddPersonToGroupInfoDTO = new NewAddPersonToGroupInfoDTO(personEmail);


        // Assert

        assertEquals(personEmail, newAddPersonToGroupInfoDTO.getEmail());
    }


    //Test for constructor without parameters

    @Test
    @DisplayName("Test For US03_DTO(): Empty constructor")
    void US03_DTO_Empty_Constructor() {

        // Arrange


        // Act

        NewAddPersonToGroupInfoDTO newAddPersonToGroupInfoDTO = new NewAddPersonToGroupInfoDTO();
        newAddPersonToGroupInfoDTO.setEmail("joana@gmail.com");

        // Assert

        assertEquals("joana@gmail.com", newAddPersonToGroupInfoDTO.getEmail());
    }

    //Sets

    @Test
    @DisplayName("Test For US03_DTOInfo : Set Email")
    void US03_DTO_SetEmail() {

        // Arrange

        String personEmail = "cavaleiro@gmail.com";
        String groupDenomination = "Dance";

        // Act

        NewAddPersonToGroupInfoDTO newAddPersonToGroupInfoDTO = new NewAddPersonToGroupInfoDTO(personEmail);
        newAddPersonToGroupInfoDTO.setEmail("joana@gmail.com");

        // Assert

        assertEquals("joana@gmail.com", newAddPersonToGroupInfoDTO.getEmail());

    }


    //Gets



    @Test
    @DisplayName("Test For getPersonEmail()")
    void getPersonEmail() {

        // Arrange

        String personEmail = "cavaleiro@gmail.com";
        String groupDenomination = "Dance";

        // Act

        NewAddPersonToGroupInfoDTO newAddPersonToGroupInfoDTO = new NewAddPersonToGroupInfoDTO(personEmail);


        // Assert

        assertEquals(personEmail, newAddPersonToGroupInfoDTO.getEmail());
    }

    //Equals

    @Test
    @DisplayName("Test For equals() | Success")
    void equalsSuccess() {

        // Arrange

        //First DTO

        String firstPersonEmail = "joana@gmail.com";
        String firstGroupDenomination = "Tennis";
        NewAddPersonToGroupInfoDTO firstNewAddPersonToGroupInfoDTO = new NewAddPersonToGroupInfoDTO(firstPersonEmail);

        //Second DTO
        String secondPersonEmail = "joana@gmail.com";
        String secondGroupDenomination = "Tennis";
        NewAddPersonToGroupInfoDTO secondNewAddPersonToGroupInfoDTO = new NewAddPersonToGroupInfoDTO(secondPersonEmail);

        // Act

        boolean result = firstNewAddPersonToGroupInfoDTO.equals(secondNewAddPersonToGroupInfoDTO);

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
        NewAddPersonToGroupInfoDTO firstNewAddPersonToGroupInfoDTO = new NewAddPersonToGroupInfoDTO(firstPersonEmail);

        // Act

        boolean result = firstNewAddPersonToGroupInfoDTO.equals(firstNewAddPersonToGroupInfoDTO);

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
        NewAddPersonToGroupInfoDTO firstNewAddPersonToGroupInfoDTO = new NewAddPersonToGroupInfoDTO(firstPersonEmail);

        //Second DTO
        String secondPersonEmail = "j@gmail.com";
        String secondGroupDenomination = "Tennis";
        NewAddPersonToGroupInfoDTO secondNewAddPersonToGroupInfoDTO = new NewAddPersonToGroupInfoDTO(secondPersonEmail);

        // Act

        boolean result = firstNewAddPersonToGroupInfoDTO.equals(secondNewAddPersonToGroupInfoDTO);

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
        NewAddPersonToGroupInfoDTO firstNewAddPersonToGroupInfoDTO = new NewAddPersonToGroupInfoDTO(firstPersonEmail);

        String name = "Joana";



        // Act

        boolean result = firstNewAddPersonToGroupInfoDTO.equals(name);

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
        NewAddPersonToGroupInfoDTO firstNewAddPersonToGroupInfoDTO = new NewAddPersonToGroupInfoDTO(firstPersonEmail);



        // Act

        boolean result = firstNewAddPersonToGroupInfoDTO.equals(null);

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
        NewAddPersonToGroupInfoDTO firstNewAddPersonToGroupInfoDTO = new NewAddPersonToGroupInfoDTO(firstPersonEmail);

        NewAddPersonToGroupInfoDTO secondNewAddPersonToGroupInfoDTO = new NewAddPersonToGroupInfoDTO(firstPersonEmail);



        // Act

        boolean hashCodeResult = firstNewAddPersonToGroupInfoDTO.hashCode()== secondNewAddPersonToGroupInfoDTO.hashCode();


        // Assert

        assertEquals(true, hashCodeResult);
        assertTrue(firstNewAddPersonToGroupInfoDTO.hashCode()== firstNewAddPersonToGroupInfoDTO.hashCode());
        assertEquals(true, firstNewAddPersonToGroupInfoDTO.hashCode()== firstNewAddPersonToGroupInfoDTO.hashCode() );
        assertTrue(firstNewAddPersonToGroupInfoDTO.hashCode()== secondNewAddPersonToGroupInfoDTO.hashCode());
    }

    @Test
    @DisplayName("Test For US03_DTO() | Fail")
    void hashCodeFail() {

        // Arrange

        //Runners

        String firstPersonEmail = "joana@gmail.com";
        String secondEmail = "paulo@gmail.com";

        NewAddPersonToGroupInfoDTO firstNewAddPersonToGroupInfoDTO = new NewAddPersonToGroupInfoDTO(firstPersonEmail);
        NewAddPersonToGroupInfoDTO secondNewAddPersonToGroupInfoDTO = new NewAddPersonToGroupInfoDTO(secondEmail);



        // Act

        boolean hashCodeResult = firstNewAddPersonToGroupInfoDTO.hashCode()== secondNewAddPersonToGroupInfoDTO.hashCode();


        // Assert

        assertEquals(false, hashCodeResult);
        assertFalse(firstNewAddPersonToGroupInfoDTO.hashCode()== secondNewAddPersonToGroupInfoDTO.hashCode());
    }


}