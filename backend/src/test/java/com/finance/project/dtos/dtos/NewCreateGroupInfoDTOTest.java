package com.finance.project.dtos.dtos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NewCreateGroupInfoDTOTest {

    @Test
    @DisplayName("NewCreateGroupInfoDTO - Test Constructor with Parameters")
    void newCreateGroupInfoDTO_ConstructorWithParametersTest() {

        //Arrange
        String emailMaria = "maria@gmail.com";
        String groupDenomination = "Sunday Runners";
        String groupDescription = "All members from Sunday Runners group";

        //Act
        NewCreateGroupInfoDTO newCreateGroupInfoDTO = new NewCreateGroupInfoDTO(emailMaria, groupDenomination, groupDescription);

        //Assert
        assertEquals(emailMaria, newCreateGroupInfoDTO.getEmail());
        assertEquals(groupDenomination, newCreateGroupInfoDTO.getDenomination());
        assertEquals(groupDescription, newCreateGroupInfoDTO.getDescription());
    }

    @Test
    @DisplayName("NewCreateGroupInfoDTO - Test Constructor without Parameters")
    void newCreateGroupInfoDTO_TestConstructorWithoutParametersTest() {

        //Arrange
        String emailMaria = "maria@gmail.com";
        String groupDenomination = "Sunday Runners";
        String groupDescription = "All members from Sunday Runners group";

        //Act
        NewCreateGroupInfoDTO newCreateGroupInfoDTO = new NewCreateGroupInfoDTO();
        newCreateGroupInfoDTO.setEmail(emailMaria);
        newCreateGroupInfoDTO.setDenomination(groupDenomination);
        newCreateGroupInfoDTO.setDescription(groupDescription);

        //Assert
        assertEquals(emailMaria, newCreateGroupInfoDTO.getEmail());
        assertEquals(groupDenomination, newCreateGroupInfoDTO.getDenomination());
        assertEquals(groupDescription, newCreateGroupInfoDTO.getDescription());
    }

    @Test
    @DisplayName("NewCreateGroupInfoDTO - Test Equals || Same Object")
    void newCreateGroupInfoDTO_EqualsTest_SameObject() {

        //Arrange
        String emailMaria = "maria@gmail.com";
        String groupDenomination = "Sunday Runners";
        String groupDescription = "All members from Sunday Runners group";


        //Act
        NewCreateGroupInfoDTO newCreateGroupInfoDTO1 = new NewCreateGroupInfoDTO(emailMaria, groupDenomination, groupDescription);
        NewCreateGroupInfoDTO newCreateGroupInfoDTO2 = new NewCreateGroupInfoDTO(emailMaria, groupDenomination, groupDescription);

        //Assert
        assertTrue(newCreateGroupInfoDTO1.equals(newCreateGroupInfoDTO2));
        assertTrue(newCreateGroupInfoDTO1.equals(newCreateGroupInfoDTO1)); //the object is equal to itself
    }

    @Test
    @DisplayName("NewCreateGroupInfoDTO - Test Equals || Different Object")
    void newCreateGroupInfoDTO_EqualsTest_DifferentObject() {

        //Arrange

            //Group Sunday Runners
        String emailMaria = "maria@gmail.com";
        String groupDenomination = "Sunday Runners";
        String groupDescription = "All members from Sunday Runners group";

            //Group Silva Family
        String silvaFamilyDenomination = "Silva Family";
        String silvaFamilyDescription = "All members from Silva family";

        String bugKiller = "Bug Killer";

        //Act

        NewCreateGroupInfoDTO newCreateGroupInfoDTO1 = new NewCreateGroupInfoDTO(emailMaria, groupDenomination, groupDescription);
        NewCreateGroupInfoDTO newCreateGroupInfoDTO2 = new NewCreateGroupInfoDTO(emailMaria, silvaFamilyDenomination, silvaFamilyDescription);

        NewCreateGroupInfoDTO newCreateGroupInfoDTO3 = null;

        //Assert
        assertFalse(newCreateGroupInfoDTO1.equals(newCreateGroupInfoDTO2));
        assertFalse(newCreateGroupInfoDTO1.equals(bugKiller));  //not same instance
        assertFalse(newCreateGroupInfoDTO1.equals(newCreateGroupInfoDTO3)); //object is null

    }

    @Test
    @DisplayName("NewCreateGroupInfoDTO - Test Equals || Null")
    void newCreateGroupInfoDTO_EqualsTest_Null() {

        //Arrange

        //Group Sunday Runners
        String emailMaria = "maria@gmail.com";
        String groupDenomination = "Sunday Runners";
        String groupDescription = "All members from Sunday Runners group";



        //Act

        NewCreateGroupInfoDTO newCreateGroupInfoDTO1 = new NewCreateGroupInfoDTO(emailMaria, groupDenomination, groupDescription);

        NewCreateGroupInfoDTO newCreateGroupInfoDTO3 = null;

        boolean result = newCreateGroupInfoDTO1.equals(newCreateGroupInfoDTO3);

        //Assert
        assertEquals(false, result);
        assertFalse(newCreateGroupInfoDTO1.equals(newCreateGroupInfoDTO3)); //object is null

    }

    @Test
    @DisplayName("NewCreateGroupInfoDTO - Test Equals || No Instance of")
    void newCreateGroupInfoDTO_EqualsTest_No_InstanceOf() {

        //Arrange

        //Group Sunday Runners
        String emailMaria = "maria@gmail.com";
        String groupDenomination = "Sunday Runners";
        String groupDescription = "All members from Sunday Runners group";



        //Act

        NewCreateGroupInfoDTO newCreateGroupInfoDTO1 = new NewCreateGroupInfoDTO(emailMaria, groupDenomination, groupDescription);


        boolean result = newCreateGroupInfoDTO1.equals(emailMaria);

        //Assert
        assertEquals(false, result);
        assertFalse(newCreateGroupInfoDTO1.equals(emailMaria));

    }


    @Test
    @DisplayName("NewCreateGroupInfoDTO - Test Hash Code")
    void newCreateGroupInfoDTO_HashCode() {

        //Arrange
        String emailMaria = "maria@gmail.com";
        String groupDenomination = "Sunday Runners";
        String groupDescription = "All members from Sunday Runners group";

        //Act
        NewCreateGroupInfoDTO newCreateGroupInfoDTO = new NewCreateGroupInfoDTO(emailMaria, groupDenomination, groupDescription);

        int runnersHash = newCreateGroupInfoDTO.hashCode();
        int expectedHash = 1767194133;

        // Assert
        assertEquals(expectedHash, runnersHash);
    }

}