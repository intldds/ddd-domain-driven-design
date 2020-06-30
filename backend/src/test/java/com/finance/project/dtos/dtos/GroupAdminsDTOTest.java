package com.finance.project.dtos.dtos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GroupAdminsDTOTest {

    @Test
    @DisplayName("GroupAdminsDTO - Test Constructor with Parameters")
    void groupAdminsDTO_ConstructorWithParametersTest() {

        //Arrange
        String mariaEmail = "maria@gmail.com";

        List<String> peopleInCharge = new ArrayList<>();
        peopleInCharge.add(mariaEmail);

        //Act
        GroupAdminsDTO groupAdminsDTO = new GroupAdminsDTO(peopleInCharge);

        //Assert
        assertEquals(peopleInCharge, groupAdminsDTO.getPeopleInCharge());
    }

    @Test
    @DisplayName("GroupAdminsDTO - Test Constructor without Parameters")
    void groupAdminsDTO_ConstructorWithoutParametersTest() {

        //Arrange
        String mariaEmail = "maria@gmail.com";

        List<String> peopleInCharge = new ArrayList<>();
        peopleInCharge.add(mariaEmail);

        //Act
        GroupAdminsDTO groupAdminsDTO = new GroupAdminsDTO();
        groupAdminsDTO.setPeopleInCharge(peopleInCharge);

        //Assert
        assertEquals(peopleInCharge, groupAdminsDTO.getPeopleInCharge());
    }

    @Test
    @DisplayName("GroupAdminsDTO - Test Equals || Same Object")
    void groupAdminsDTO_EqualsTest_SameObject() {

        //Arrange
        String mariaEmail = "maria@gmail.com";
        String ritaEmail = "rita@gmail.com";

        List<String> peopleInCharge = new ArrayList<>();
        peopleInCharge.add(mariaEmail);
        peopleInCharge.add(ritaEmail);

        //Act
        GroupAdminsDTO groupAdminsDTO1 = new GroupAdminsDTO(peopleInCharge);
        GroupAdminsDTO groupAdminsDTO2 = new GroupAdminsDTO(peopleInCharge);

        //Assert
        assertTrue(groupAdminsDTO1.equals(groupAdminsDTO2));
        assertTrue(groupAdminsDTO1.equals(groupAdminsDTO1)); //the object is equal to itself
    }

    @Test
    @DisplayName("GroupAdminsDTO - Test Equals || Different Object")
    void groupAdminsDTO_EqualsTest_DifferentObject() {

        //Arrange
        String mariaEmail = "maria@gmail.com";

        List<String> peopleInCharge1 = new ArrayList<>();
        peopleInCharge1.add(mariaEmail);

        List<String> peopleInCharge2 = new ArrayList<>();

        String bugKiller = "Bug Killer";

        //Act
        GroupAdminsDTO groupAdminsDTO1 = new GroupAdminsDTO(peopleInCharge1);
        GroupAdminsDTO groupAdminsDTO2 = new GroupAdminsDTO(peopleInCharge2);
        GroupAdminsDTO groupAdminsDTO3 = null;

        //Assert
        assertFalse(groupAdminsDTO1.equals(groupAdminsDTO2));
        assertFalse(groupAdminsDTO1.equals(bugKiller)); //not same instance
        assertFalse(groupAdminsDTO1.equals(groupAdminsDTO3)); //object is null
    }

    @Test
    @DisplayName("GroupAdminsDTO - Test Hash Code")
    void groupAdminsDTO_HashCodeTest() {

        //Arrange
        String mariaEmail = "maria@gmail.com";

        List<String> peopleInCharge = new ArrayList<>();
        peopleInCharge.add(mariaEmail);

        //Act
        GroupAdminsDTO groupAdminsDTO = new GroupAdminsDTO(peopleInCharge);

        int groupAdminsDTOHashcode = groupAdminsDTO.hashCode();
        int expectedHashCode = 1502202214;

        //Assert
        assertEquals(expectedHashCode, groupAdminsDTOHashcode);
    }
}