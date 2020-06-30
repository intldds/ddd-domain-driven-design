package com.finance.project.dtos.dtos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonIDDTOTest {

    @Test
    @DisplayName("PersonIDDTO - Test Constructor with Parameters")
    void personIDDTO_ConstructorWithParametersTest() {

        //Arrange
        String mariaEmail = "maria@gmail.com";
        //Act
        PersonIDDTO personIDDTO = new PersonIDDTO(mariaEmail);

        //Assert
        assertEquals(mariaEmail, personIDDTO.getEmail());
    }

    @Test
    @DisplayName("PersonIDDTO - Test Equals || Same Object")
    void personIDDTO_EqualsTest_SameObject() {

        //Arrange
        String mariaEmail = "maria@gmail.com";

        //Act
        PersonIDDTO personIDDTO1 = new PersonIDDTO(mariaEmail);
        PersonIDDTO personIDDTO2 = new PersonIDDTO(mariaEmail);

        //Assert
        assertTrue(personIDDTO1.equals(personIDDTO2));
        assertTrue(personIDDTO1.equals(personIDDTO1)); //the object is equal to itself
    }

    @Test
    @DisplayName("PersonIDDTO - Test Equals || Different Object")
    void personIDDTO_EqualsTest_DifferentObject() {

        //Arrange
            //Person Maria Silva
        String mariaEmail = "maria@gmail.com";

            //Person Rui Silva
        String ruiEmail = "rui@gmail.com";

            //Bug Killer
        String bugKiller = "Bug Killer";

        //Act
        PersonIDDTO personIDDTO1 = new PersonIDDTO(mariaEmail);
        PersonIDDTO personIDDTO2 = new PersonIDDTO(ruiEmail);
        PersonIDDTO personIDDTO3 = null;

        //Assert
        assertFalse(personIDDTO1.equals(personIDDTO2));
        assertFalse(personIDDTO1.equals(bugKiller)); //not same instance
        assertFalse(personIDDTO1.equals(personIDDTO3)); //one object is null
    }

    @Test
    @DisplayName("PersonIDDTO - Hash Code")
    void personIDDTO_HashCodeTest() {

        //Arrange
        String mariaEmail = "maria@gmail.com";

        //Act
        PersonIDDTO personIDDTO = new PersonIDDTO(mariaEmail);

        int personIDDTOHashcode = personIDDTO.hashCode();
        int expectedHashCode = 1502201222;

        //Assert
        assertEquals(expectedHashCode, personIDDTOHashcode);
    }

}