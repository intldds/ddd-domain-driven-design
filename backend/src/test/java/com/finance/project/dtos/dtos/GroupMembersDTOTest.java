package com.finance.project.dtos.dtos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GroupMembersDTOTest {

    @Test
    @DisplayName("GroupMembersDTO - Test Constructor with Parameters")
    void groupMembersDTO_ConstructorWithParametersTest() {

        //Arrange
        String mariaEmail = "maria@gmail.com";

        List<String> members = new ArrayList<>();
        members.add(mariaEmail);

        //Act
        GroupMembersDTO groupMembersDTO = new GroupMembersDTO(members);

        //Assert
        assertEquals(members, groupMembersDTO.getMembers());
    }

    @Test
    @DisplayName("GroupMembersDTO - Test Constructor without Parameters")
    void groupMembersDTO_ConstructorWithoutParametersTest() {

        //Arrange
        String mariaEmail = "maria@gmail.com";

        List<String> members = new ArrayList<>();
        members.add(mariaEmail);

        //Act
        GroupMembersDTO groupMembersDTO = new GroupMembersDTO();
        groupMembersDTO.setMembers(members);

        //Assert
        assertEquals(members, groupMembersDTO.getMembers());
    }

    @Test
    @DisplayName("GroupMembersDTO - Test Equals || Same Object")
    void groupMembersDTO_EqualsTest_SameObject() {

        //Arrange
        String mariaEmail = "maria@gmail.com";
        String ritaEmail = "rita@gmail.com";

        List<String> members = new ArrayList<>();
        members.add(mariaEmail);
        members.add(ritaEmail);

        //Act
        GroupMembersDTO groupMembersDTO1 = new GroupMembersDTO(members);
        GroupMembersDTO groupMembersDTO2 = new GroupMembersDTO(members);

        //Assert
        assertTrue(groupMembersDTO1.equals(groupMembersDTO2));
        assertTrue(groupMembersDTO1.equals(groupMembersDTO1)); //the object is equal to itself
    }

    @Test
    @DisplayName("GroupMembersDTO - Test Equals || Different Object")
    void groupMembersDTO_EqualsTest_DifferentObject() {

        //Arrange
        String mariaEmail = "maria@gmail.com";

        List<String> members1 = new ArrayList<>();
        members1.add(mariaEmail);

        List<String> members2 = new ArrayList<>();

        String bugKiller = "Bug Killer";

        //Act
        GroupMembersDTO groupMembersDTO1 = new GroupMembersDTO(members1);
        GroupMembersDTO groupMembersDTO2 = new GroupMembersDTO(members2);
        GroupMembersDTO groupMembersDTO3 = null;

        //Assert
        assertFalse(groupMembersDTO1.equals(groupMembersDTO2));
        assertFalse(groupMembersDTO1.equals(bugKiller)); //not same instance
        assertFalse(groupMembersDTO1.equals(groupMembersDTO3)); //object is null
    }

    @Test
    @DisplayName("GroupMembersDTO - Test Hash Code")
    void groupMembersDTO_HashCodeTest() {

        //Arrange
        String mariaEmail = "maria@gmail.com";

        List<String> members = new ArrayList<>();
        members.add(mariaEmail);

        //Act
        GroupMembersDTO groupMembersDTO = new GroupMembersDTO(members);

        int groupMembersDTOHashcode = groupMembersDTO.hashCode();
        int expectedHashCode = 1502202214;

        //Assert
        assertEquals(expectedHashCode, groupMembersDTOHashcode);
    }

}