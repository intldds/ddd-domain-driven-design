package com.finance.project.dtos.dtos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NewCreatePersonInfoDTOTest {

    @Test
    @DisplayName("NewCreatePersonInfoDTO - Test Constructor with Parameters")
    void newCreatePersonInfoDTO_ConstructorWithParametersTest() {

        //Arrange
        String mariaEmail = "maria@gmail.com";
        String mariaName = "Maria Silva";
        String mariaBirthdate = "1973-07-25";
        String mariaBirthplace = "Braga";

        //Act
        NewCreatePersonInfoDTO newCreatePersonInfoDTO = new NewCreatePersonInfoDTO(mariaEmail, mariaName, mariaBirthdate, mariaBirthplace);

        //Assert
        assertEquals(mariaEmail, newCreatePersonInfoDTO.getEmail());
        assertEquals(mariaName, newCreatePersonInfoDTO.getName());
        assertEquals(mariaBirthdate, newCreatePersonInfoDTO.getBirthdate());
        assertEquals(mariaBirthplace, newCreatePersonInfoDTO.getBirthplace());
    }

    @Test
    @DisplayName("NewCreatePersonInfoDTO - Test Constructor without Parameters")
    void newCreatePersonInfoDTO_TestConstructorWithoutParametersTest() {

        //Arrange
        String mariaEmail = "maria@gmail.com";
        String mariaName = "Maria Silva";
        String mariaBirthdate = "1973-07-25";
        String mariaBirthplace = "Braga";

        //Act
        NewCreatePersonInfoDTO newCreatePersonInfoDTO = new NewCreatePersonInfoDTO();
        newCreatePersonInfoDTO.setEmail(mariaEmail);
        newCreatePersonInfoDTO.setName(mariaName);
        newCreatePersonInfoDTO.setBirthdate(mariaBirthdate);
        newCreatePersonInfoDTO.setBirthplace(mariaBirthplace);

        //Assert
        assertEquals(mariaEmail, newCreatePersonInfoDTO.getEmail());
        assertEquals(mariaName, newCreatePersonInfoDTO.getName());
        assertEquals(mariaBirthdate, newCreatePersonInfoDTO.getBirthdate());
        assertEquals(mariaBirthplace, newCreatePersonInfoDTO.getBirthplace());
    }

    @Test
    @DisplayName("NewCreatePersonInfoDTO - Test Equals || Same Object")
    void newCreatePersonInfoDTO_EqualsTest_SameObject() {

        //Arrange
        String mariaEmail = "maria@gmail.com";
        String mariaName = "Maria Silva";
        String mariaBirthdate = "1973-07-25";
        String mariaBirthplace = "Braga";


        //Act
        NewCreatePersonInfoDTO newCreatePersonInfoDTO1 = new NewCreatePersonInfoDTO(mariaEmail, mariaName, mariaBirthdate, mariaBirthplace);
        NewCreatePersonInfoDTO newCreatePersonInfoDTO2 = new NewCreatePersonInfoDTO(mariaEmail, mariaName, mariaBirthdate, mariaBirthplace);

        //Assert
        assertTrue(newCreatePersonInfoDTO1.equals(newCreatePersonInfoDTO2));
        assertTrue(newCreatePersonInfoDTO1.equals(newCreatePersonInfoDTO1)); //the object is equal to itself
    }

    @Test
    @DisplayName("NewCreatePersonInfoDTO - Test Equals || Different Object - Same information")
    void newCreatePersonInfoDTO_EqualsTest_DifferentObjectSameInformation() {

        //Arrange

        //Person Maria Silva
        String mariaEmail1 = "maria@gmail.com";
        String mariaName1 = "Maria Silva";
        String mariaBirthdate1 = "1973-07-25";
        String mariaBirthplace1 = "Braga";

        //Person Maria Silva
        String mariaEmail2 = "maria@gmail.com";
        String mariaName2 = "Maria Silva";
        String mariaBirthdate2 = "1973-07-25";
        String mariaBirthplace2 = "Braga";

        //Act
        NewCreatePersonInfoDTO newCreatePersonInfoDTO1 = new NewCreatePersonInfoDTO(mariaEmail1, mariaName1, mariaBirthdate1, mariaBirthplace1);
        NewCreatePersonInfoDTO newCreatePersonInfoDTO2 = new NewCreatePersonInfoDTO(mariaEmail2, mariaName2, mariaBirthdate2, mariaBirthplace2);

        //Assert
        assertTrue(newCreatePersonInfoDTO1.equals(newCreatePersonInfoDTO2));

    }

    @Test
    @DisplayName("NewCreatePersonInfoDTO - Test Equals || Different Email")
    void newCreatePersonInfoDTO_EqualsTest_DifferentEmail() {

        //Arrange

            //Person Maria Silva
        String mariaEmail = "maria@gmail.com";
        String mariaName = "Maria Silva";
        String mariaBirthdate = "1973-07-25";
        String mariaBirthplace = "Braga";

            //Person Rui Silva
        String ruiEmail = "rui@gmail.com";


        //Act
        NewCreatePersonInfoDTO newCreatePersonInfoDTO1 = new NewCreatePersonInfoDTO(mariaEmail, mariaName, mariaBirthdate, mariaBirthplace);
        NewCreatePersonInfoDTO newCreatePersonInfoDTO2 = new NewCreatePersonInfoDTO(ruiEmail, mariaName, mariaBirthdate, mariaBirthplace);

        NewCreatePersonInfoDTO newCreatePersonInfoDTO3 = null;

        boolean result = newCreatePersonInfoDTO1.equals(newCreatePersonInfoDTO2);

        //Assert

        assertEquals(false, result);
        assertFalse(newCreatePersonInfoDTO1.equals(newCreatePersonInfoDTO2));


    }

    @Test
    @DisplayName("NewCreatePersonInfoDTO - Test Equals || Different Name")
    void newCreatePersonInfoDTO_EqualsTest_DifferentName() {

        //Arrange

        //Person Maria Silva
        String mariaEmail = "maria@gmail.com";
        String mariaName = "Maria Silva";
        String mariaBirthdate = "1973-07-25";
        String mariaBirthplace = "Braga";

        //Person Rui Silva
        String ruiName = "Rui Silva";



        //Act
        NewCreatePersonInfoDTO newCreatePersonInfoDTO1 = new NewCreatePersonInfoDTO(mariaEmail, mariaName, mariaBirthdate, mariaBirthplace);
        NewCreatePersonInfoDTO newCreatePersonInfoDTO2 = new NewCreatePersonInfoDTO(mariaEmail, ruiName, mariaBirthdate, mariaBirthplace);


        boolean result = newCreatePersonInfoDTO1.equals(newCreatePersonInfoDTO2);

        //Assert

        assertEquals(false, result);
        assertFalse(newCreatePersonInfoDTO1.equals(newCreatePersonInfoDTO2));

    }

    @Test
    @DisplayName("NewCreatePersonInfoDTO - Test Equals || Different Birthdate")
    void newCreatePersonInfoDTO_EqualsTest_DifferentBirthdate() {

        //Arrange

        //Person Maria Silva
        String mariaEmail = "maria@gmail.com";
        String mariaName = "Maria Silva";
        String mariaBirthdate = "1973-07-25";
        String mariaBirthplace = "Braga";

        //Person Rui Silva
        String ruiBirthdate = "1991-04-02";


        //Act
        NewCreatePersonInfoDTO newCreatePersonInfoDTO1 = new NewCreatePersonInfoDTO(mariaEmail, mariaName, mariaBirthdate, mariaBirthplace);
        NewCreatePersonInfoDTO newCreatePersonInfoDTO2 = new NewCreatePersonInfoDTO(mariaEmail, mariaName, ruiBirthdate, mariaBirthplace);


        boolean result = newCreatePersonInfoDTO1.equals(newCreatePersonInfoDTO2);

        //Assert

        assertEquals(false, result);
        assertFalse(newCreatePersonInfoDTO1.equals(newCreatePersonInfoDTO2));

    }

    @Test
    @DisplayName("NewCreatePersonInfoDTO - Test Equals || Different Bithplace")
    void newCreatePersonInfoDTO_EqualsTest_DifferentBithplace() {

        //Arrange

        //Person Maria Silva
        String mariaEmail = "maria@gmail.com";
        String mariaName = "Maria Silva";
        String mariaBirthdate = "1973-07-25";
        String mariaBirthplace = "Braga";

        //Person Rui Silva
        String ruiBirthplace = "Lisboa";


        //Act
        NewCreatePersonInfoDTO newCreatePersonInfoDTO1 = new NewCreatePersonInfoDTO(mariaEmail, mariaName, mariaBirthdate, mariaBirthplace);
        NewCreatePersonInfoDTO newCreatePersonInfoDTO2 = new NewCreatePersonInfoDTO(mariaEmail, mariaName, mariaBirthdate, ruiBirthplace);


        boolean result = newCreatePersonInfoDTO1.equals(newCreatePersonInfoDTO2);

        //Assert

        assertEquals(false, result);
        assertFalse(newCreatePersonInfoDTO1.equals(newCreatePersonInfoDTO2));

    }

    @Test
    @DisplayName("NewCreatePersonInfoDTO - Test Equals || Different Object")
    void newCreatePersonInfoDTO_EqualsTest_DifferentObject() {

        //Arrange

        //Person Maria Silva
        String mariaEmail = "maria@gmail.com";
        String mariaName = "Maria Silva";
        String mariaBirthdate = "1973-07-25";
        String mariaBirthplace = "Braga";

        //Person Rui Silva
        String ruiEmail = "rui@gmail.com";
        String ruiName = "Rui Silva";
        String ruiBirthdate = "1991-04-02";
        String ruiBirthplace = "Lisboa";

        //Bug Killer
        String bugKiller = "Bug Killer";

        //Act
        NewCreatePersonInfoDTO newCreatePersonInfoDTO1 = new NewCreatePersonInfoDTO(mariaEmail, mariaName, mariaBirthdate, mariaBirthplace);
        NewCreatePersonInfoDTO newCreatePersonInfoDTO2 = new NewCreatePersonInfoDTO(ruiEmail, ruiName, ruiBirthdate, ruiBirthplace);


        boolean result = newCreatePersonInfoDTO1.equals(newCreatePersonInfoDTO2);

        //Assert

        assertEquals(false, result);
        assertFalse(newCreatePersonInfoDTO1.equals(newCreatePersonInfoDTO2));

    }

    @Test
    @DisplayName("NewCreatePersonInfoDTO - Test Equals || Null")
    void newCreatePersonInfoDTO_EqualsTest_Null() {

        //Arrange

        //Person Maria Silva
        String mariaEmail = "maria@gmail.com";
        String mariaName = "Maria Silva";
        String mariaBirthdate = "1973-07-25";
        String mariaBirthplace = "Braga";



        //Act
        NewCreatePersonInfoDTO newCreatePersonInfoDTO1 = new NewCreatePersonInfoDTO(mariaEmail, mariaName, mariaBirthdate, mariaBirthplace);
        NewCreatePersonInfoDTO newCreatePersonInfoDTO2 = null;


        boolean result = newCreatePersonInfoDTO1.equals(newCreatePersonInfoDTO2);

        //Assert

        assertEquals(false, result);
        assertFalse(newCreatePersonInfoDTO1.equals(newCreatePersonInfoDTO2));

    }


    @Test
    @DisplayName("NewCreatePersonInfoDTO - Test Equals || No Instance Of")
    void newCreatePersonInfoDTO_EqualsTest_No_InstanceOf() {

        //Arrange

        //Person Maria Silva
        String mariaEmail = "maria@gmail.com";
        String mariaName = "Maria Silva";
        String mariaBirthdate = "1973-07-25";
        String mariaBirthplace = "Braga";



        //Act
        NewCreatePersonInfoDTO newCreatePersonInfoDTO1 = new NewCreatePersonInfoDTO(mariaEmail, mariaName, mariaBirthdate, mariaBirthplace);



        boolean result = newCreatePersonInfoDTO1.equals(mariaEmail);

        //Assert

        assertEquals(false, result);
        assertFalse(newCreatePersonInfoDTO1.equals(mariaEmail));

    }

    @Test
    @DisplayName("NewCreatePersonInfoDTO - Test Hash Code")
    void newCreatePersonInfoDTO_HashCode() {

        //Arrange
        String mariaEmail = "maria@gmail.com";
        String mariaName = "Maria Silva";
        String mariaBirthdate = "1973-07-25";
        String mariaBirthplace = "Braga";

        //Act
        NewCreatePersonInfoDTO newCreatePersonInfoDTO = new NewCreatePersonInfoDTO(mariaEmail, mariaName, mariaBirthdate, mariaBirthplace);

        int runnersHash = newCreatePersonInfoDTO.hashCode();
        int expectedHash = -278647332;

        // Assert
        assertEquals(expectedHash, runnersHash);
    }
}