package com.finance.project.dtos.dtos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CreatePersonDTOTest {

    @Test
    @DisplayName("CreatePersonDTO - Test Constructor with Parameters")
    void createPersonDTO_ConstructorWithParametersTest() {

        //Arrange
        String mariaEmail = "maria@gmail.com";
        String mariaName = "Maria Silva";
        LocalDate mariaBirthdate = LocalDate.of(1973, 07, 25);
        String mariaBirthplace = "Braga";

        //Act
        CreatePersonDTO createPersonDTO = new CreatePersonDTO(mariaEmail, mariaName, mariaBirthdate, mariaBirthplace);

        //Assert
        assertEquals(mariaEmail, createPersonDTO.getEmail());
        assertEquals(mariaName, createPersonDTO.getName());
        assertEquals(mariaBirthdate, createPersonDTO.getBirthdate());
        assertEquals(mariaBirthplace, createPersonDTO.getBirthplace());
    }

    @Test
    @DisplayName("CreatePersonDTO - Test Equals || Same Object")
    void createPersonDTO_EqualsTest_SameObject() {

        //Arrange
        String mariaEmail = "maria@gmail.com";
        String mariaName = "Maria Silva";
        LocalDate mariaBirthdate = LocalDate.of(1973, 07, 25);
        String mariaBirthplace = "Braga";

        //Act
        CreatePersonDTO createPersonDTO1 = new CreatePersonDTO(mariaEmail, mariaName, mariaBirthdate, mariaBirthplace);
        CreatePersonDTO createPersonDTO2 = new CreatePersonDTO(mariaEmail, mariaName, mariaBirthdate, mariaBirthplace);

        //Assert
        assertTrue(createPersonDTO1.equals(createPersonDTO2));
        assertTrue(createPersonDTO1.equals(createPersonDTO1)); //the object is equal to itself
    }

    @Test
    @DisplayName("CreatePersonDTO - Test Equals || Different Object")
    void createPersonDTO_EqualsTest_DifferentObject() {

        //Arrange
            //Person Maria Silva
        String mariaEmail = "maria@gmail.com";
        String mariaName = "Maria Silva";
        LocalDate mariaBirthdate = LocalDate.of(1973, 07, 25);
        String mariaBirthplace = "Braga";

            //Person Rui Silva
        String ruiEmail = "rui@gmail.com";
        String ruiName = "Rui Silva";
        LocalDate ruiBirthdate = LocalDate.of(1991, 04, 02);
        String ruiBirthplace = "Lisboa";

            //Bug Killer
        String bugKiller = "Bug Killer";

        //Act
        CreatePersonDTO createPersonDTO1 = new CreatePersonDTO(mariaEmail, mariaName, mariaBirthdate, mariaBirthplace);
        CreatePersonDTO createPersonDTO2 = new CreatePersonDTO(ruiEmail, ruiName, ruiBirthdate, ruiBirthplace);
        CreatePersonDTO createPersonDTO3 = null;

        boolean result = createPersonDTO1.equals(createPersonDTO2);

        //Assert
        assertEquals(false, result);
        assertFalse(createPersonDTO1.equals(createPersonDTO2));
        assertFalse(createPersonDTO1.equals(bugKiller)); //not same instance
        assertFalse(createPersonDTO1.equals(createPersonDTO3)); //one object is null
    }


    @Test
    @DisplayName("CreatePersonDTO - Test Equals || Different email")
    void createPersonDTO_EqualsTest_DifferentEmail() {

        //Arrange
        //Person Maria Silva
        String mariaEmail = "maria@gmail.com";
        String mariaName = "Maria Silva";
        LocalDate mariaBirthdate = LocalDate.of(1973, 07, 25);
        String mariaBirthplace = "Braga";

        //Person Rui Silva
        String ruiEmail = "rui@gmail.com";
        String ruiName = "Rui Silva";
        LocalDate ruiBirthdate = LocalDate.of(1991, 04, 02);
        String ruiBirthplace = "Braga";


        //Act
        CreatePersonDTO createPersonDTO1 = new CreatePersonDTO(mariaEmail, mariaName, mariaBirthdate, mariaBirthplace);
        CreatePersonDTO createPersonDTO2 = new CreatePersonDTO(ruiEmail, mariaName, mariaBirthdate, mariaBirthplace);

        boolean result = createPersonDTO1.equals(createPersonDTO2);


        //Assert
        assertFalse(createPersonDTO1.equals(createPersonDTO2));
        assertEquals(false, result);
    }

    @Test
    @DisplayName("CreatePersonDTO - Test Equals || Different name")
    void createPersonDTO_EqualsTest_DifferentName() {

        //Arrange
        //Person Maria Silva
        String mariaEmail = "maria@gmail.com";
        String mariaName = "Maria Silva";
        LocalDate mariaBirthdate = LocalDate.of(1973, 07, 25);
        String mariaBirthplace = "Braga";

        //Person Rui Silva
        String ruiEmail = "rui@gmail.com";
        String ruiName = "Rui Silva";
        LocalDate ruiBirthdate = LocalDate.of(1991, 04, 02);
        String ruiBirthplace = "Braga";


        //Act
        CreatePersonDTO createPersonDTO1 = new CreatePersonDTO(mariaEmail, mariaName, mariaBirthdate, mariaBirthplace);
        CreatePersonDTO createPersonDTO2 = new CreatePersonDTO(mariaEmail, ruiName, mariaBirthdate, mariaBirthplace);

        boolean result = createPersonDTO1.equals(createPersonDTO2);


        //Assert
        assertFalse(createPersonDTO1.equals(createPersonDTO2));
        assertEquals(false, result);
    }


    @Test
    @DisplayName("CreatePersonDTO - Test Equals || Different birthdate")
    void createPersonDTO_EqualsTest_DifferentBirthdate() {

        //Arrange
        //Person Maria Silva
        String mariaEmail = "maria@gmail.com";
        String mariaName = "Maria Silva";
        LocalDate mariaBirthdate = LocalDate.of(1973, 07, 25);
        String mariaBirthplace = "Braga";

        //Person Rui Silva
        String ruiEmail = "rui@gmail.com";
        String ruiName = "Rui Silva";
        LocalDate ruiBirthdate = LocalDate.of(1991, 04, 02);
        String ruiBirthplace = "Braga";


        //Act
        CreatePersonDTO createPersonDTO1 = new CreatePersonDTO(mariaEmail, mariaName, mariaBirthdate, mariaBirthplace);
        CreatePersonDTO createPersonDTO2 = new CreatePersonDTO(mariaEmail, mariaName, ruiBirthdate, mariaBirthplace);

        boolean result = createPersonDTO1.equals(createPersonDTO2);


        //Assert
        assertFalse(createPersonDTO1.equals(createPersonDTO2));
        assertEquals(false, result);
    }


    @Test
    @DisplayName("CreatePersonDTO - Test Equals || Different birthplace")
    void createPersonDTO_EqualsTest_DifferentBirthplace() {

        //Arrange
        //Person Maria Silva
        String mariaEmail = "maria@gmail.com";
        String mariaName = "Maria Silva";
        LocalDate mariaBirthdate = LocalDate.of(1973, 07, 25);
        String mariaBirthplace = "Braga";

        //Person Rui Silva
        String ruiEmail = "rui@gmail.com";
        String ruiName = "Rui Silva";
        LocalDate ruiBirthdate = LocalDate.of(1991, 04, 02);
        String ruiBirthplace = "Lisboa";


        //Act
        CreatePersonDTO createPersonDTO1 = new CreatePersonDTO(mariaEmail, mariaName, mariaBirthdate, mariaBirthplace);
        CreatePersonDTO createPersonDTO2 = new CreatePersonDTO(mariaEmail, mariaName, mariaBirthdate, ruiBirthplace);

        boolean result = createPersonDTO1.equals(createPersonDTO2);


        //Assert
        assertFalse(createPersonDTO1.equals(createPersonDTO2));
        assertEquals(false, result);
    }

    @Test
    @DisplayName("CreatePersonDTO - Hash Code")
    void createPersonDTO_HashCodeTest() {

        //Arrange
        String mariaEmail = "maria@gmail.com";
        String mariaName = "Maria Silva";
        LocalDate mariaBirthdate = LocalDate.of(1973, 07, 25);
        String mariaBirthplace = "Braga";

        //Act
        CreatePersonDTO createPersonDTO = new CreatePersonDTO(mariaEmail, mariaName, mariaBirthdate, mariaBirthplace);

        int createPersonDTOHashcode = createPersonDTO.hashCode();
        int expectedHashCode = -458341661;

        //Assert
        assertEquals(expectedHashCode, createPersonDTOHashcode);
    }

}