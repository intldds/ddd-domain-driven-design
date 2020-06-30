package com.finance.project.dataModel.dataModel;

import com.finance.project.domainLayer.domainEntities.vosShared.GroupID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class GroupJpaTest {

    //Constructors

    @Test
    @DisplayName("CreateJpa-Constructor")
    void constructor_noArguments() {

        String id = "Sunday Runners";
        String denomination = "Sunday Runners";
        String description = "All members from Sunday Runners group";
        String dateOfCreation = LocalDate.now().toString();

        GroupID groupID = GroupID.createGroupID(denomination);

        GroupJpa groupJpa = new GroupJpa();
        groupJpa.setId(groupID);
        groupJpa.setDescription(description);
        groupJpa.setDateOfCreation(dateOfCreation);

        //Asserts

        assertEquals(groupJpa.getDescription(), description);
        assertEquals(groupJpa.getDateOfCreation(), dateOfCreation);

    }


    @Test
    @DisplayName("CreateJpa-Constructor")
    void createGroupJPASucess() {

        //Arrange

        String id = "Sunday Runners";
        String denomination = "Sunday Runners";
        String description = "All members from Sunday Runners group";
        String dateOfCreation = LocalDate.now().toString();

        GroupID groupID = GroupID.createGroupID(denomination);

        //Act

        GroupJpa groupJpa = new GroupJpa(groupID, description, dateOfCreation);

        //Asserts

        Assertions.assertEquals(groupJpa.getId(), groupID);
        assertEquals(groupJpa.getDescription(), description);
        assertEquals(groupJpa.getDateOfCreation(), dateOfCreation);

    }

    @Test
    @DisplayName("CreateJpa-Constructor: id as string")
    void createGroupJPASucessConstructor() {

        //Arrange

        String denomination = "Sunday Runners";
        String description = "All members from Sunday Runners group";
        String dateOfCreation = LocalDate.now().toString();


        //Act

        GroupJpa groupJpa = new GroupJpa(denomination, description, dateOfCreation);
        GroupID groupID =GroupID.createGroupID(denomination);

        //Asserts

        Assertions.assertEquals(groupJpa.getId(), groupID);
        assertEquals(groupJpa.getDescription(), description);
        assertEquals(groupJpa.getDateOfCreation(), dateOfCreation);

    }

    //ToString

    @Test
    @DisplayName("CreateJpa-ToString")
    void createGroupJPAToString() {

        //Arrange

        String id = "Sunday Runners";
        String denomination = "Sunday Runners";
        String description = "All members from Sunday Runners group";
        String dateOfCreation = LocalDate.now().toString();

        GroupID groupID = GroupID.createGroupID(denomination);

        //Act

        GroupJpa groupJpa = new GroupJpa(groupID, description, dateOfCreation);

        //Asserts

        assertEquals("Group {id='GroupID{denomination=Denomination{denomination='Sunday Runners'}}'}",groupJpa.toString() );

    }

    //Equals

    @Test
    @DisplayName("CreateJpa-Equals- Same Object")
    void createGroupJPAEqualsSameObject() {

        //Arrange

        String id = "Sunday Runners";
        String denomination = "Sunday Runners";
        String description = "All members from Sunday Runners group";
        String dateOfCreation = LocalDate.now().toString();

        GroupID groupID = GroupID.createGroupID(denomination);

        //Act

        GroupJpa groupJpa = new GroupJpa(groupID, description, dateOfCreation);

        boolean result = groupJpa.equals(groupJpa);

        //Asserts

        assertEquals(true, result);
    }

    @Test
    @DisplayName("CreateJpa-Equals- Happy Case")
    void createGroupJPAEqualsHappyCase() {

        //Arrange

        String id = "Sunday Runners";
        String denomination = "Sunday Runners";
        String description = "All members from Sunday Runners group";
        String dateOfCreation = LocalDate.now().toString();

        GroupID groupID = GroupID.createGroupID(denomination);

        //Act

        GroupJpa groupJpa = new GroupJpa(groupID, description, dateOfCreation);
        GroupJpa newGroupJpa = new GroupJpa(groupID, description, dateOfCreation);

        boolean result = groupJpa.equals(newGroupJpa);

        //Asserts

        assertEquals(true, result);
    }

    @Test
    @DisplayName("CreateJpa-Equals- null")
    void createGroupJPAEqualsNull() {

        //Arrange

        String denomination = "Sunday Runners";
        String description = "All members from Sunday Runners group";
        String dateOfCreation = LocalDate.now().toString();

        GroupID groupID = GroupID.createGroupID(denomination);

        //Act

        GroupJpa groupJpa = new GroupJpa(groupID, description, dateOfCreation);
        GroupJpa newGroupJpa = null;

        boolean result = groupJpa.equals(newGroupJpa);

        //Asserts

        assertEquals(false, result);
    }

    @Test
    @DisplayName("CreateJpa-Equals- no Instance of")
    void createGroupJPAEqualsNoInstance() {

        //Arrange

        String denomination = "Sunday Runners";
        String description = "All members from Sunday Runners group";
        String dateOfCreation = LocalDate.now().toString();

        GroupID groupID = GroupID.createGroupID(denomination);

        //Act

        GroupJpa groupJpa = new GroupJpa(groupID, description, dateOfCreation);
        GroupJpa newGroupJpa = null;

        boolean result = groupJpa.equals(denomination);

        //Asserts

        assertEquals(false, result);
    }

    @Test
    @DisplayName("CreateJpa-Equals- Sad Case")
    void createGroupJPAEqualsSadCase() {

        //Arrange

        String denomination = "Sunday Runners";
        String description = "All members from Sunday Runners group";
        String dateOfCreation = LocalDate.now().toString();

        GroupID groupID = GroupID.createGroupID(denomination);

        String denominationFontes = "Fontes Family";
        GroupID groupIDFontes = GroupID.createGroupID(denominationFontes);


        //Act

        GroupJpa groupJpa = new GroupJpa(groupID, description, dateOfCreation);
        GroupJpa newGroupJpa = new GroupJpa(groupID, description, dateOfCreation);
        newGroupJpa.setId(groupIDFontes);

        boolean result = groupJpa.equals(newGroupJpa);

        //Asserts

        assertEquals(false, result);
    }


    //HashCode

    @Test
    @DisplayName("CreateJpa-HashCode")
    void createGroupJPAHashCode() {

        //Arrange

        String id = "Sunday Runners";
        String denomination = "Sunday Runners";
        String description = "All members from Sunday Runners group";
        String dateOfCreation = LocalDate.now().toString();

        GroupID groupID = GroupID.createGroupID(denomination);

        //Act

        GroupJpa groupJpa = new GroupJpa(groupID, description, dateOfCreation);

        GroupJpa newGroupJpa = new GroupJpa(groupID, description, dateOfCreation);

        boolean result = groupJpa.hashCode()==newGroupJpa.hashCode();

        //Asserts
        assertTrue(groupJpa.hashCode()==groupJpa.hashCode());
        assertTrue(groupJpa.hashCode()==newGroupJpa.hashCode());
        assertEquals(true, result);
    }

    //HashCode

    @Test
    @DisplayName("CreateJpa-HashCode - SadCase")
    void createGroupJPAHashCode_SadCase() {

        //Arrange

        String denomination = "Sunday Runners";
        String description = "All members from Sunday Runners group";
        String dateOfCreation = LocalDate.now().toString();

        GroupID groupID = GroupID.createGroupID(denomination);

        String denominationFontes = "Fontes Family";
        GroupID groupIDFontes = GroupID.createGroupID(denominationFontes);

        //Act

        GroupJpa groupJpa = new GroupJpa(groupID, description, dateOfCreation);
        GroupJpa newGroupJpa = new GroupJpa(groupIDFontes, description, dateOfCreation);


        boolean result = groupJpa.hashCode()==newGroupJpa.hashCode();

        //Asserts

        assertFalse(groupJpa.hashCode()==newGroupJpa.hashCode());
        assertEquals(false, result);
    }



}