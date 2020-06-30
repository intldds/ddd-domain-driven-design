package com.finance.project.domainLayer.domainEntities.aggregates.group;

import com.finance.project.domainLayer.domainEntities.vosShared.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GroupTest {

    //Tests for the class’ constructor ::: Create Group as Person In Charge

    @Test
    @DisplayName("Verify group parameters || Happy case: All parameters are matching")
    public void membersValidationConstructorGroupWithOnePerson() {

        // Arrange
        PersonID personMaria = PersonID.createPersonID("maria@gmail.com");
        LedgerID ledgerID = LedgerID.createLedgerID();

        //Expected
        List<PersonID> peopleInCharge = new ArrayList<>();
        peopleInCharge.add(personMaria);

        GroupID groupID = GroupID.createGroupID("Friends");
        Description description = Description.createDescription("Old friends from school");
        DateOfCreation dateOfCreation = DateOfCreation.createDateOfCreation(LocalDate.of(2019, 12, 18));

        // Act
        Group groupFriends = Group.createGroupAsPersonInCharge("Friends", personMaria, "Old friends from school", LocalDate.of(2019, 12, 18), ledgerID);

        // Assert
        assertEquals(groupFriends.getPeopleInCharge(), peopleInCharge);
        Assertions.assertEquals(groupFriends.getGroupID(), groupID);
        Assertions.assertEquals(groupFriends.getDescription(), description);
        Assertions.assertEquals(groupFriends.getDateOfCreation(), dateOfCreation);
        Assertions.assertEquals(groupFriends.getLedgerID(), ledgerID);
    }


    @Test
    @DisplayName("Verify group parameters || Sad case: Responsible members can not be null ")
    public void responsiblePeopleValidationNullCase() {

        //Arrange
        LedgerID ledgerID = LedgerID.createLedgerID();

        //Act
        try {
            Group group = Group.createGroupAsPersonInCharge("Friends", null, "Old friends from school", LocalDate.of(2019, 12, 18), ledgerID);
            assertTrue(false, "Group not created. Person in charge can't be Null");
        } catch (Exception e) {
        }
    }


    //Tests to check GroupID

    @Test
    @DisplayName("Verify Group ID|| Happy case: Same GroupID")
    public void checkSameGroupID() {

        // Arrange
        PersonID personMaria = PersonID.createPersonID("maria@gmail.com");
        LedgerID ledgerID = LedgerID.createLedgerID();

        //Expected
        GroupID groupIDExpected = GroupID.createGroupID("Friends");

        //Act
        Group groupFriends = Group.createGroupAsPersonInCharge("Friends", personMaria, "Old friends from school", LocalDate.of(2019, 12, 18), ledgerID);
        boolean result = groupFriends.checkGroupID(groupIDExpected);

        // Assert
        assertEquals(true, result);
    }


    @Test
    @DisplayName("Verify Group ID|| Sad case: Different GroupID")
    public void checkDifferentGroupID() {

        // Arrange
        PersonID personMaria = PersonID.createPersonID("maria@gmail.com");
        LedgerID ledgerID = LedgerID.createLedgerID();

        //Expected
        GroupID groupIDExpected = GroupID.createGroupID("Friends");

        //Act
        Group groupFriends = Group.createGroupAsPersonInCharge("Runners", personMaria, "Old friends from school", LocalDate.of(2019, 12, 18), ledgerID);
        boolean result = groupFriends.checkGroupID(groupIDExpected);

        // Assert
        assertEquals(false, result);
    }


    //Tests for the class’ constructor ::: Create Group with list of People In Charge and with list of Members

    @Test
    @DisplayName("Verify group parameters || Happy case: group Members are matching")
    public void membersValidation() {

        // Arrange
        PersonID personMariaID = PersonID.createPersonID("maria@gmail.com");
        PersonID personManuelID = PersonID.createPersonID("manuel@gmail.com");
        PersonID personMiguelID = PersonID.createPersonID("miguel@gmail.com");

        LedgerID ledgerID = LedgerID.createLedgerID();

        List<PersonID> peopleInCharge = new ArrayList<>();
        peopleInCharge.add(personMariaID);
        peopleInCharge.add(personManuelID);

        List<PersonID> members = new ArrayList<>();
        members.add(personMiguelID);

        //Expected
        List<PersonID> expectedMembers = new ArrayList<>();
        expectedMembers.add(personMiguelID);

        // Act
        Group groupFriends = Group.createGroup("Friends", peopleInCharge, members, "Old friends from school", LocalDate.of(2019, 12, 18), ledgerID);

        // Assert
        assertEquals(groupFriends.getMembers(), expectedMembers);
    }

    @Test
    @DisplayName("Verify group parameters | Sad Case: Group not created, group Members can not be null")
    public void membersValidationNullArray() {

        //Arrange
        PersonID personMariaID = PersonID.createPersonID("maria@gmail.com");

        LedgerID ledgerID = LedgerID.createLedgerID();

        List<PersonID> peopleInCharge = new ArrayList<>();
        peopleInCharge.add(personMariaID);

        //Act
        try {
            Group group = Group.createGroup("Friends", peopleInCharge, null, "Old friends from school", LocalDate.of(2019, 12, 18), ledgerID);
            assertTrue(false, "Group not created. Members can't be Null");
        } catch (Exception e) {
        }
    }

    @Test
    @DisplayName("Verify group parameters | Sad Case: Group not created, People In Charge can not be null")
    public void peopleInChargeValidationNullArray() {

        //Arrange
        PersonID personMariaID = PersonID.createPersonID("maria@gmail.com");

        LedgerID ledgerID = LedgerID.createLedgerID();

        List<PersonID> members = new ArrayList<>();
        members.add(personMariaID);

        //Act
        try {
            Group group = Group.createGroup("Friends", null, members, "Old friends from school", LocalDate.of(2019, 12, 18), ledgerID);
            assertTrue(false, "Group not created. Members can't be Null");
        } catch (Exception e) {
        }
    }

    @Test
    @DisplayName("Verify group parameters | Group members can be an empty array")
    public void membersValidationEmptyArray() {

        PersonID personMariaID = PersonID.createPersonID("maria@gmail.com");
        LedgerID ledgerID = LedgerID.createLedgerID();

        List<PersonID> peopleInCharge = new ArrayList<>();
        peopleInCharge.add(personMariaID);

        List<PersonID> members = new ArrayList<>();

        // Act
        Group groupFriends = Group.createGroup("Friends", peopleInCharge, members, "Old friends from school", LocalDate.of(2019, 12, 18), ledgerID);

        // Assert
        assertEquals(groupFriends.getMembers(), members);
    }

    @Test
    @DisplayName("Verify isPersonPeopleInCharge | Success")
    public void isPersonPeopleInCharge_Success() {

        // Arrange
        PersonID personMariaID = PersonID.createPersonID("maria@gmail.com");

        LedgerID ledgerID = LedgerID.createLedgerID();

        // Act
        Group groupFriends = Group.createGroupAsPersonInCharge("Friends", personMariaID, "Old friends from school", LocalDate.of(2019, 12, 18), ledgerID);

        // Assert
        assertEquals(true, groupFriends.isPersonPeopleInCharge(personMariaID));
    }

    @Test
    @DisplayName("Verify isPersonPeopleInCharge | Fail")
    public void isPersonPeopleInCharge_Fail() {

        // Arrange
        PersonID personMariaID = PersonID.createPersonID("maria@gmail.com");
        PersonID personManuelID = PersonID.createPersonID("manuel@gmail.com");

        LedgerID ledgerID = LedgerID.createLedgerID();

        // Act
        Group groupFriends = Group.createGroupAsPersonInCharge("Friends", personMariaID, "Old friends from school", LocalDate.of(2019, 12, 18), ledgerID);

        // Assert
        assertEquals(false, groupFriends.isPersonPeopleInCharge(personManuelID));
    }

    @Test
    @DisplayName("Verify group parameters | Responsible members are matching")
    public void responsiblePeopleValidation() {

        // Arrange
        PersonID personMariaID = PersonID.createPersonID("maria@gmail.com");
        PersonID personManuelID = PersonID.createPersonID("manuel@gmail.com");
        PersonID personMiguelID = PersonID.createPersonID("miguel@gmail.com");

        LedgerID ledgerID = LedgerID.createLedgerID();

        List<PersonID> peopleInCharge = new ArrayList<>();
        peopleInCharge.add(personMariaID);
        peopleInCharge.add(personManuelID);

        List<PersonID> members = new ArrayList<>();
        peopleInCharge.add(personMiguelID);

        // Act
        Group groupFriends = Group.createGroup("Friends", peopleInCharge, members, "Old friends from school", LocalDate.of(2019, 12, 18), ledgerID);

        // Assert
        assertEquals(groupFriends.getPeopleInCharge(), peopleInCharge);
    }

    @Test
    @DisplayName("Verify group parameters | Responsible members can be an empty array")
    public void responsiblePeopleValidationEmptyArray() {

        // Arrange
        PersonID personMiguelID = PersonID.createPersonID("miguel@gmail.com");
        LedgerID ledgerID = LedgerID.createLedgerID();

        List<PersonID> peopleInCharge = new ArrayList<>();

        List<PersonID> members = new ArrayList<>();
        peopleInCharge.add(personMiguelID);

        // Act
        Group groupFriends = Group.createGroup("Friends", peopleInCharge, members, "Old friends from school", LocalDate.of(2019, 12, 18), ledgerID);

        // Assert
        assertEquals(groupFriends.getPeopleInCharge(), peopleInCharge);
    }


    @Test
    @DisplayName("Verify group parameters | GroupID are matching")
    public void denominationValidation() {

        // Arrange
        PersonID personMariaID = PersonID.createPersonID("maria@gmail.com");
        PersonID personManuelID = PersonID.createPersonID("manuel@gmail.com");
        PersonID personMiguelID = PersonID.createPersonID("miguel@gmail.com");

        LedgerID ledgerID = LedgerID.createLedgerID();

        List<PersonID> peopleInCharge = new ArrayList<>();
        peopleInCharge.add(personMariaID);
        peopleInCharge.add(personManuelID);

        List<PersonID> members = new ArrayList<>();
        members.add(personMiguelID);

        GroupID expectedGroupID = GroupID.createGroupID("Friends");

        // Act
        Group groupFriends = Group.createGroup("Friends", peopleInCharge, members, "Old friends from school", LocalDate.of(2019, 12, 18), ledgerID);

        // Assert
        Assertions.assertEquals(groupFriends.getGroupID(), expectedGroupID);
    }


    @Test
    @DisplayName("Verify group parameters | Description are matching")
    public void descriptionValidation() {

        // Arrange
        PersonID personMariaID = PersonID.createPersonID("maria@gmail.com");
        PersonID personManuelID = PersonID.createPersonID("manuel@gmail.com");
        PersonID personMiguelID = PersonID.createPersonID("miguel@gmail.com");

        LedgerID ledgerID = LedgerID.createLedgerID();

        List<PersonID> peopleInCharge = new ArrayList<>();
        peopleInCharge.add(personMariaID);
        peopleInCharge.add(personManuelID);

        List<PersonID> members = new ArrayList<>();
        members.add(personMiguelID);

        Description friendsDescription = Description.createDescription("Old friends from school");

        // Act
        Group groupFriends = Group.createGroup("Friends", peopleInCharge, members, "Old friends from school", LocalDate.of(2019, 12, 18), ledgerID);

        // Assert
        Assertions.assertEquals(groupFriends.getDescription(), friendsDescription);
    }


    @Test
    @DisplayName("Verify group parameters | Date of creation are matching")
    public void dateOfCreationValidation() {

        // Arrange
        PersonID personMariaID = PersonID.createPersonID("maria@gmail.com");
        PersonID personManuelID = PersonID.createPersonID("manuel@gmail.com");
        PersonID personMiguelID = PersonID.createPersonID("miguel@gmail.com");

        LedgerID ledgerID = LedgerID.createLedgerID();

        List<PersonID> peopleInCharge = new ArrayList<>();
        peopleInCharge.add(personMariaID);
        peopleInCharge.add(personManuelID);

        List<PersonID> members = new ArrayList<>();
        members.add(personMiguelID);

        DateOfCreation friendsDate = DateOfCreation.createDateOfCreation(LocalDate.of(2019, 12, 18));

        // Act
        Group groupFriends = Group.createGroup("Friends", peopleInCharge, members, "Old friends from school", LocalDate.of(2019, 12, 18), ledgerID);

        // Assert
        Assertions.assertEquals(groupFriends.getDateOfCreation(), friendsDate);
    }


    @Test
    @DisplayName("Verify if one person is already a member | The person already exist in group as Member")
    public void personIsAlreadyInGroup() {

        // Arrange
        PersonID personMariaID = PersonID.createPersonID("maria@gmail.com");
        PersonID personManuelID = PersonID.createPersonID("manuel@gmail.com");
        PersonID personMiguelID = PersonID.createPersonID("miguel@gmail.com");

        LedgerID ledgerID = LedgerID.createLedgerID();

        List<PersonID> peopleInCharge = new ArrayList<>();
        peopleInCharge.add(personMariaID);
        peopleInCharge.add(personManuelID);

        List<PersonID> members = new ArrayList<>();
        members.add(personMiguelID);

        // Act
        Group groupFriends = Group.createGroup("Friends", peopleInCharge, members, "Old friends from school", LocalDate.of(2019, 12, 18), ledgerID);
        boolean result = groupFriends.isPersonAlreadyMember(personMiguelID);

        // Assert
        assertEquals(true, result);
    }

    @Test
    @DisplayName("Verify if one person is already a member | The person already exist in group as People In Charge")
    public void personAlreadyMember() {

        // Arrange
        PersonID personMariaID = PersonID.createPersonID("maria@gmail.com");
        PersonID personManuelID = PersonID.createPersonID("manuel@gmail.com");
        PersonID personMiguelID = PersonID.createPersonID("miguel@gmail.com");

        LedgerID ledgerID = LedgerID.createLedgerID();

        List<PersonID> peopleInCharge = new ArrayList<>();
        peopleInCharge.add(personMariaID);
        peopleInCharge.add(personManuelID);

        List<PersonID> members = new ArrayList<>();
        members.add(personMiguelID);

        // Act
        Group groupFriends = Group.createGroup("Friends", peopleInCharge, members, "Old friends from school", LocalDate.of(2019, 12, 18), ledgerID);
        boolean result = groupFriends.isPersonAlreadyMember(personMariaID);

        // Assert
        assertEquals(true, result);
    }


    @Test
    @DisplayName("Verify if one person is not a member yet| The person doesn't exist in group")
    public void personNotExistInGroup() {

        // Arrange
        PersonID personMariaID = PersonID.createPersonID("maria@gmail.com");
        PersonID personManuelID = PersonID.createPersonID("manuel@gmail.com");
        PersonID personMiguelID = PersonID.createPersonID("miguel@gmail.com");

        LedgerID ledgerID = LedgerID.createLedgerID();

        List<PersonID> peopleInCharge = new ArrayList<>();
        peopleInCharge.add(personMariaID);
        peopleInCharge.add(personManuelID);

        List<PersonID> members = new ArrayList<>();

        // Act
        Group groupFriends = Group.createGroup("Friends", peopleInCharge, members, "Old friends from school", LocalDate.of(2019, 12, 18), ledgerID);
        boolean result = groupFriends.isPersonAlreadyMember(personMiguelID);

        // Assert
        assertEquals(false, result);
    }

    @Test
    @DisplayName("Verify if one member can be added to one group | Success case")
    public void addMembersTest() {

        // Arrange
        PersonID personMariaID = PersonID.createPersonID("maria@gmail.com");
        PersonID personManuelID = PersonID.createPersonID("manuel@gmail.com");
        PersonID personMiguelID = PersonID.createPersonID("miguel@gmail.com");

        LedgerID ledgerID = LedgerID.createLedgerID();

        List<PersonID> peopleInCharge = new ArrayList<>();
        peopleInCharge.add(personMariaID);

        List<PersonID> members = new ArrayList<>();
        members.add(personManuelID);
        members.add(personMiguelID);

        // Act
        Group groupFriends = Group.createGroup("Friends", peopleInCharge, members, "Old friends from school", LocalDate.of(2019, 12, 18), ledgerID);
        groupFriends.addMember(personMiguelID);

        List<PersonID> expectedResult = new ArrayList<>();
        expectedResult.add(personMariaID);
        expectedResult.add(personManuelID);
        expectedResult.add(personMiguelID);

        // Assert
        assertEquals(expectedResult, groupFriends.getAllMembers());
    }


    @Test
    @DisplayName("Verify if one member can be added to one group | False")
    public void addMembersTest_False() {

        // Arrange
        PersonID personMariaID = PersonID.createPersonID("maria@gmail.com");
        PersonID personManuelID = PersonID.createPersonID("manuel@gmail.com");
        PersonID personMiguelID = PersonID.createPersonID("miguel@gmail.com");

        LedgerID ledgerID = LedgerID.createLedgerID();

        List<PersonID> peopleInCharge = new ArrayList<>();
        peopleInCharge.add(personMariaID);

        List<PersonID> members = new ArrayList<>();
        members.add(personManuelID);
        members.add(personMiguelID);
        members.add(personMariaID);

        // Act
        Group groupFriends = Group.createGroup("Friends", peopleInCharge, members, "Old friends from school", LocalDate.of(2019, 12, 18), ledgerID);
        groupFriends.addMember(personMiguelID);

        List<PersonID> expectedResult = new ArrayList<>();
        expectedResult.add(personMariaID);
        expectedResult.add(personManuelID);
        expectedResult.add(personMiguelID);

        boolean result = groupFriends.addMember(personMariaID);

        // Assert

        assertFalse(result);
        assertNotEquals(expectedResult, groupFriends.getAllMembers());
    }


    @Test
    @DisplayName("Verify if one member can be added to one group | True")
    public void addMembersTest_True() {

        // Arrange
        PersonID personMariaID = PersonID.createPersonID("maria@gmail.com");
        PersonID personManuelID = PersonID.createPersonID("manuel@gmail.com");
        PersonID personMiguelID = PersonID.createPersonID("miguel@gmail.com");

        LedgerID ledgerID = LedgerID.createLedgerID();

        List<PersonID> peopleInCharge = new ArrayList<>();
        peopleInCharge.add(personMariaID);

        List<PersonID> members = new ArrayList<>();
        members.add(personManuelID);


        // Act
        Group groupFriends = Group.createGroup("Friends", peopleInCharge, members, "Old friends from school", LocalDate.of(2019, 12, 18), ledgerID);
        boolean result = groupFriends.addMember(personMiguelID);

        List<PersonID> expectedResult = new ArrayList<>();
        expectedResult.add(personMariaID);
        expectedResult.add(personManuelID);
        expectedResult.add(personMiguelID);


        // Assert
        assertTrue(result);

        assertEquals(expectedResult, groupFriends.getAllMembers());
    }
    // Add Person In Charge Test

    @Test
    @DisplayName("Verify if one member can be added to one group || Happy case: Member added")
    public void addPersonInChargeTest() {

        // Arrange
        PersonID personMariaID = PersonID.createPersonID("maria@gmail.com");
        PersonID personManuelID = PersonID.createPersonID("manuel@gmail.com");

        LedgerID ledgerID = LedgerID.createLedgerID();

        // Act
        Group groupFriends = Group.createGroupAsPersonInCharge("Friends", personMariaID, "Old friends from school", LocalDate.of(2019, 12, 18), ledgerID);
        groupFriends.addPersonInCharge(personManuelID);

        List<PersonID> expectedResult = new ArrayList<>();
        expectedResult.add(personMariaID);
        expectedResult.add(personManuelID);

        // Assert
        assertEquals(expectedResult, groupFriends.getPeopleInCharge());
    }


    @Test
    @DisplayName("Verify if one member can be added to one group || Sad case: Person already exist in group")
    public void addPersonInChargeTestFail() {

        // Arrange
        PersonID personMariaID = PersonID.createPersonID("maria@gmail.com");

        LedgerID ledgerID = LedgerID.createLedgerID();

        // Act
        Group groupFriends = Group.createGroupAsPersonInCharge("Friends", personMariaID, "Old friends from school", LocalDate.of(2019, 12, 18), ledgerID);
        groupFriends.addPersonInCharge(personMariaID);

        List<PersonID> expectedResult = new ArrayList<>();
        expectedResult.add(personMariaID);

        // Assert
        assertEquals(expectedResult, groupFriends.getPeopleInCharge());
    }


    @Test
    @DisplayName("Verify if one group doesn't admit repeated members | Member not added, already exist in group")
    public void repeatedMembersNotAdmitted() {

        // Arrange
        PersonID personMariaID = PersonID.createPersonID("maria@gmail.com");
        PersonID personManuelID = PersonID.createPersonID("manuel@gmail.com");
        PersonID personMiguelID = PersonID.createPersonID("miguel@gmail.com");

        LedgerID ledgerID = LedgerID.createLedgerID();

        // Act
        Group groupFriends = Group.createGroupAsPersonInCharge("Friends", personMariaID, "Old friends from school", LocalDate.of(2019, 12, 18), ledgerID);
        groupFriends.addPersonInCharge(personManuelID);
        groupFriends.addMember(personMiguelID);
        groupFriends.addMember(personMiguelID);

        List<PersonID> expectedResult = new ArrayList<>();
        expectedResult.add(personMariaID);
        expectedResult.add(personManuelID);
        expectedResult.add(personMiguelID);

        // Assert
        assertEquals(expectedResult, groupFriends.getAllMembers());
    }

    // Hash Code tests

    @Test
    @DisplayName("Verify if group and another group have the same hashcode | Different groups: different hashcode")
    public void differentHashCode() {

        // Arrange Person
        PersonID personMariaID = PersonID.createPersonID("maria@gmail.com");
        PersonID personManuelID = PersonID.createPersonID("manuel@gmail.com");

        LedgerID friendsLedgerID = LedgerID.createLedgerID();
        LedgerID runnersLedgerID = LedgerID.createLedgerID();

        // Act
        Group groupFriends = Group.createGroupAsPersonInCharge("Friends", personMariaID, "Old friends from school", LocalDate.of(2019, 12, 18), friendsLedgerID);
        Group groupRunners = Group.createGroupAsPersonInCharge("Runners", personManuelID, "People who come together to run", LocalDate.of(2019, 12, 18), runnersLedgerID);

        // Assert
        assertEquals(false, groupFriends.hashCode() == groupRunners.hashCode());
    }

    // Equals tests

    @Test
    @DisplayName("Equals: Verify if two groups are the same | Happy Case: Same group, same ID")
    public void isTheSameGroup() {

        // Arrange
        PersonID personMariaID = PersonID.createPersonID("maria@gmail.com");
        PersonID personManuelID = PersonID.createPersonID("manuel@gmail.com");
        PersonID personMiguelID = PersonID.createPersonID("miguel@gmail.com");

        LedgerID friendsLedgerID = LedgerID.createLedgerID();
        LedgerID runnersLedgerID = LedgerID.createLedgerID();

        // Act
        Group groupFriends = Group.createGroupAsPersonInCharge("Friends", personMariaID, "Old friends from school", LocalDate.of(2019, 12, 18), friendsLedgerID);
        groupFriends.addPersonInCharge(personManuelID);
        ;
        groupFriends.addMember(personMiguelID);
        Group groupRunners = Group.createGroupAsPersonInCharge("Friends", personMariaID, "Friends", LocalDate.of(2019, 12, 18), runnersLedgerID);

        boolean result = groupFriends.equals(groupRunners);

        // Assert
        assertEquals(true, result);
        //assertEquals(groupFriends, groupRunners);
    }

    @Test
    @DisplayName("Equals: Verify if two groups are the same | A group is equal to itself")
    public void isGroupEqualToItSelf() {

        // Arrange
        PersonID personMariaID = PersonID.createPersonID("maria@gmail.com");

        LedgerID friendsLedgerID = LedgerID.createLedgerID();

        // Act
        Group groupFriends = Group.createGroupAsPersonInCharge("Friends", personMariaID, "Old friends from school", LocalDate.of(2019, 12, 18), friendsLedgerID);

        boolean result = groupFriends.equals(groupFriends);

        // Assert
        assertEquals(true, result);
    }

    @Test
    @DisplayName("Equals: Verify if two groups are the same | Sad Case: Different groups, different ID")
    public void notTheSameGroup() {

        // Arrange
        PersonID personMariaID = PersonID.createPersonID("maria@gmail.com");
        PersonID personManuelID = PersonID.createPersonID("manuel@gmail.com");
        PersonID personMiguelID = PersonID.createPersonID("miguel@gmail.com");

        LedgerID friendsLedgerID = LedgerID.createLedgerID();
        LedgerID runnersLedgerID = LedgerID.createLedgerID();

        // Act
        Group groupFriends = Group.createGroupAsPersonInCharge("Friends", personMariaID, "Old friends from school", LocalDate.of(2019, 12, 18), friendsLedgerID);
        groupFriends.addPersonInCharge(personManuelID);
        ;
        groupFriends.addMember(personMiguelID);
        Group groupRunners = Group.createGroupAsPersonInCharge("Runners", personMariaID, "Old friends from school", LocalDate.of(2019, 12, 18), runnersLedgerID);

        boolean result = groupFriends.equals(groupRunners);

        // Assert
        assertEquals(false, result);
    }

    @Test
    @DisplayName("Equals: Verify if two groups are the same | Sad case: one group null")
    public void isNotTheSameGroupObjectNull() {

        // Arrange
        PersonID personMariaID = PersonID.createPersonID("maria@gmail.com");

        LedgerID ledgerID = LedgerID.createLedgerID();

        // Act
        Group groupFriends = Group.createGroupAsPersonInCharge("Friends", personMariaID, "Old friends from school", LocalDate.of(2019, 12, 18), ledgerID);
        Group groupRunners = null;

        // Assert
        assertNotEquals(groupFriends, groupRunners);
    }

    @Test
    @DisplayName("Equals: Verify if two groups are the same | Sad case: Object of different class's")
    public void isNotTheSameGroupDifferentClass() {

        // Arrange
        PersonID personMariaID = PersonID.createPersonID("maria@gmail.com");

        LedgerID ledgerID = LedgerID.createLedgerID();

        // Act
        Group groupFriends = Group.createGroupAsPersonInCharge("Friends", personMariaID, "Old friends from school", LocalDate.of(2019, 12, 18), ledgerID);

        // Assert
        assertNotEquals(groupFriends, personMariaID);
    }

    // Add categoryID tests

    @Test
    @DisplayName("Verify if one CategoryID can be added to Categories List || Happy case: CategoryID added")
    public void categoryIDAddedToCategoriesList() {

        // Arrange
        PersonID personMariaID = PersonID.createPersonID("maria@gmail.com");

        LedgerID ledgerID = LedgerID.createLedgerID();

        GroupID groupID = GroupID.createGroupID("Friends");
        CategoryID categoryID = CategoryID.createCategoryID("Food", groupID);


        // Act
        Group groupFriends = Group.createGroupAsPersonInCharge("Friends", personMariaID, "Old friends from school", LocalDate.of(2019, 12, 18), ledgerID);
        groupFriends.addCategory(categoryID);

        // Expected
        List<CategoryID> categories = new ArrayList<>();
        boolean result = categories.add(categoryID);

        // Assert
        assertEquals(true, result);
        assertEquals(groupFriends.getCategories(), categories);
    }

    @Test
    @DisplayName("Verify if one CategoryID can be added to Categories List || Sad case: CategoryID already exist")
    public void categoryIDNotAddedToCategoriesList() {

        // Arrange
        PersonID personMariaID = PersonID.createPersonID("maria@gmail.com");

        LedgerID ledgerID = LedgerID.createLedgerID();


        GroupID groupID = GroupID.createGroupID("Friends");
        CategoryID categoryID = CategoryID.createCategoryID("Food", groupID);


        // Act
        Group groupFriends = Group.createGroupAsPersonInCharge("Friends", personMariaID, "Old friends from school", LocalDate.of(2019, 12, 18), ledgerID);
        boolean result1 = groupFriends.addCategory(categoryID);
        boolean result2 = groupFriends.addCategory(categoryID);

        // Expected
        List<CategoryID> categories = new ArrayList<>();
        categories.add(categoryID);

        // Assert
        assertEquals(true, result1);
        assertEquals(false, result2);
        assertEquals(groupFriends.getCategories(), categories);
    }

    @Test
    @DisplayName("Verify checkIfGroupHasCategory() || Success")
    public void checkIfGroupHasCategory_Success() {

        // Arrange
        PersonID personMariaID = PersonID.createPersonID("maria@gmail.com");

        LedgerID ledgerID = LedgerID.createLedgerID();

        GroupID groupID = GroupID.createGroupID("Friends");
        CategoryID categoryID = CategoryID.createCategoryID("Food", groupID);


        // Act
        Group groupFriends = Group.createGroupAsPersonInCharge("Friends", personMariaID, "Old friends from school", LocalDate.of(2019, 12, 18), ledgerID);
        groupFriends.addCategory(categoryID);

        // Assert
        assertEquals(true, groupFriends.checkIfGroupHasCategory(categoryID));
    }

    @Test
    @DisplayName("Verify checkIfGroupHasCategory() || Fail")
    public void checkIfGroupHasCategory_Fail() {

        // Arrange
        PersonID personMariaID = PersonID.createPersonID("maria@gmail.com");

        LedgerID ledgerID = LedgerID.createLedgerID();

        GroupID groupID = GroupID.createGroupID("Friends");
        CategoryID categoryID = CategoryID.createCategoryID("Food", groupID);
        CategoryID categoryIDExtra = CategoryID.createCategoryID("Gas", groupID);


        // Act
        Group groupFriends = Group.createGroupAsPersonInCharge("Friends", personMariaID, "Old friends from school", LocalDate.of(2019, 12, 18), ledgerID);
        groupFriends.addCategory(categoryID);

        // Assert
        assertEquals(false, groupFriends.checkIfGroupHasCategory(categoryIDExtra));
    }

    @Test
    @DisplayName("Verify checkIfGroupHasAccount() || Success")
    public void checkIfGroupHasAccount_Success() {

        // Arrange
        PersonID personMariaID = PersonID.createPersonID("maria@gmail.com");

        LedgerID ledgerID = LedgerID.createLedgerID();

        GroupID groupID = GroupID.createGroupID("Friends");
        AccountID accountID = AccountID.createAccountID("FriendsAccount", groupID);


        // Act
        Group groupFriends = Group.createGroupAsPersonInCharge("Friends", personMariaID, "Old friends from school", LocalDate.of(2019, 12, 18), ledgerID);
        groupFriends.addAccount(accountID);

        // Assert
        assertEquals(true, groupFriends.checkIfGroupHasAccount(accountID));
    }

    @Test
    @DisplayName("Verify checkIfGroupHasAccount() || Fail")
    public void checkIfGroupHasAccount_Fail() {

        // Arrange
        PersonID personMariaID = PersonID.createPersonID("maria@gmail.com");

        LedgerID ledgerID = LedgerID.createLedgerID();

        GroupID groupID = GroupID.createGroupID("Friends");
        AccountID accountID = AccountID.createAccountID("FriendsAccount", groupID);
        AccountID accountIDExtra = AccountID.createAccountID("SomeOtherFriendsAccount", groupID);


        // Act
        Group groupFriends = Group.createGroupAsPersonInCharge("Friends", personMariaID, "Old friends from school", LocalDate.of(2019, 12, 18), ledgerID);
        groupFriends.addAccount(accountID);

        // Assert
        assertEquals(false, groupFriends.checkIfGroupHasAccount(accountIDExtra));
    }

    // Add accountID tests

    @Test
    @DisplayName("Verify if one AccountID can be added to Accounts List || Happy case: AccountID added")
    public void accountIDAddedToAccountsList() {

        // Arrange
        PersonID personMariaID = PersonID.createPersonID("maria@gmail.com");

        LedgerID ledgerID = LedgerID.createLedgerID();

        GroupID groupID = GroupID.createGroupID("Friends");
        AccountID accountID = AccountID.createAccountID("Supermarket", groupID);


        // Act
        Group groupFriends = Group.createGroupAsPersonInCharge("Friends", personMariaID, "Old friends from school", LocalDate.of(2019, 12, 18), ledgerID);
        groupFriends.addAccount(accountID);

        // Expected
        List<AccountID> accounts = new ArrayList<>();
        boolean result = accounts.add(accountID);

        // Assert
        assertEquals(true, result);
        assertEquals(groupFriends.getAccounts(), accounts);
    }

    @Test
    @DisplayName("Verify if one AccountID can be added to Accounts List || Sad case: AccountID already exist")
    public void accountIDNotAddedToAccountsList() {

        // Arrange
        PersonID personMariaID = PersonID.createPersonID("maria@gmail.com");

        LedgerID ledgerID = LedgerID.createLedgerID();

        GroupID groupID = GroupID.createGroupID("Friends");
        AccountID accountID = AccountID.createAccountID("Supermarket", groupID);


        // Act
        Group groupFriends = Group.createGroupAsPersonInCharge("Friends", personMariaID, "Old friends from school", LocalDate.of(2019, 12, 18), ledgerID);
        boolean result1 = groupFriends.addAccount(accountID);
        boolean result2 = groupFriends.addAccount(accountID);

        // Expected
        List<AccountID> accounts = new ArrayList<>();
        accounts.add(accountID);

        // Assert
        assertEquals(true, result1);
        assertEquals(false, result2);
        assertEquals(groupFriends.getAccounts(), accounts);
    }


    // Add schedulingID tests

    @Test
    @DisplayName("Verify if one SchedulingID can be added to Accounts List || Happy case: SchedulingID added")
    public void schedulingIDAddedToSchedulingList() {

        // Arrange
        PersonID personMariaID = PersonID.createPersonID("maria@gmail.com");

        LedgerID ledgerID = LedgerID.createLedgerID();

        ScheduleID scheduleID = ScheduleID.createScheduleID("Food", LocalDate.of(2020, 3, 16), "2", "Credit");

        // Act
        Group groupFriends = Group.createGroupAsPersonInCharge("Friends", personMariaID, "Old friends from school", LocalDate.of(2019, 12, 18), ledgerID);
        groupFriends.addScheduling(scheduleID);

        // Expected
        List<ScheduleID> schedulings = new ArrayList<>();
        schedulings.add(scheduleID);

        // Assert
        assertEquals(groupFriends.getSchedulings(), schedulings);
    }

    @Test
    @DisplayName("Verify if one SchedulingID can be added to Accounts List || Sad case: SchedulingID already exist")
    public void schedulingIDNotAddedToSchedulingIDList() {

        // Arrange
        PersonID personMariaID = PersonID.createPersonID("maria@gmail.com");

        LedgerID ledgerID = LedgerID.createLedgerID();

        ScheduleID scheduleID = ScheduleID.createScheduleID("Food", LocalDate.of(2020, 3, 16), "2", "Credit");

        // Act
        Group groupFriends = Group.createGroupAsPersonInCharge("Friends", personMariaID, "Old friends from school", LocalDate.of(2019, 12, 18), ledgerID);
        groupFriends.addScheduling(scheduleID);
        groupFriends.addScheduling(scheduleID);

        // Expected
        List<ScheduleID> schedulings = new ArrayList<>();
        schedulings.add(scheduleID);

        // Assert
        assertEquals(groupFriends.getSchedulings(), schedulings);

    }

    /*--------------------------------PARA APAGAR--------------------------------


 // Tests for checking whether a group is a family

    @Test
    @DisplayName("Verify if one group is a family | Success Case")
    public void isFamily() {

        // Arrange
        Person motherMaria = new Person("Maria Ramos", LocalDate.of(1990, 9, 21));
        Person fatherManuel = new Person("Manuel Ramos", LocalDate.of(1980, 9, 21));
        Person kidMiguel = new Person("Miguel Ramos", LocalDate.of(2010, 9, 21));
        Person kidPedro = new Person("Pedro Ramos", LocalDate.of(2015, 9, 21));

        ArrayList<Person> ramosFamilyPeopleInCharge = new ArrayList<Person>();
        ramosFamilyPeopleInCharge.add(fatherManuel);

        ArrayList<Person> ramosFamilyMembers = new ArrayList<Person>();
        ramosFamilyMembers.add(motherMaria);
        ramosFamilyMembers.add(kidMiguel);
        ramosFamilyMembers.add(kidPedro);

        Denomination ramosFamilyDenomination = new Denomination("Ramos Family");
        GroupID ramosFamilyID = new GroupID(ramosFamilyDenomination);
        Description ramosFamilyDescription = new Description("Persons that belongs to Ramos family");
        DateOfCreation ramosFamilyDate = new DateOfCreation(LocalDate.of(2019, 12, 18));

        // Act
        kidMiguel.setMother(motherMaria);
        kidPedro.setFather(fatherManuel);
        Group expectedRamosFamily = new Group(ramosFamilyID, ramosFamilyPeopleInCharge, ramosFamilyMembers, ramosFamilyDescription, ramosFamilyDate);
        boolean result = expectedRamosFamily.isFamily();

        // Assert
        assertEquals(true, result);
    }

    @Test
    @DisplayName("Verify if one group is a family | False case")
    public void isNotFamily() {

        // Arrange
        Person father = new Person("Leandro Lima", LocalDate.of(1980, 9, 21));
        Person kidMiguel = new Person("Miguel Lima", LocalDate.of(2010, 9, 21));
        Person kidPedro = new Person("Pedro Lima", LocalDate.of(2015, 9, 21));

        ArrayList<Person> limaFamilyPeopleInCharge = new ArrayList<Person>();

        ArrayList<Person> members = new ArrayList<Person>();
        members.add(father);
        members.add(kidMiguel);
        members.add(kidPedro);

        Denomination limaFamilyDenomination = new Denomination("Lima Family");
        GroupID limaFamilyID = new GroupID(limaFamilyDenomination);
        Description limaFamilyDescription = new Description("Persons that belongs to Lima family");
        DateOfCreation limaFamilyDate = new DateOfCreation(LocalDate.of(2019, 12, 18));

        // Act
        kidPedro.setFather(father);
        Group groupFriends = new Group(limaFamilyID, limaFamilyPeopleInCharge, members, limaFamilyDescription, limaFamilyDate);
        boolean result = groupFriends.isFamily();

        // Assert
        assertEquals(false, result);
    }


    @Test
    @DisplayName("Verify if one group are not a family | Not Family: Two persons, no kids")
    public void isNotFamilyNoKids() {

        // Arrange
        Person mother = new Person("Maria", LocalDate.of(1990, 9, 21));
        Person father = new Person("Manuel", LocalDate.of(1980, 9, 21));

        ArrayList<Person> houseMPeopleInCharge = new ArrayList<Person>();
        houseMPeopleInCharge.add(father);

        ArrayList<Person> houseMMembers = new ArrayList<Person>();
        houseMMembers.add(mother);

        Denomination houseMDenomination = new Denomination("House M");
        GroupID houseMID = new GroupID(houseMDenomination);
        Description houseMDescription = new Description("Persons that belongs to House M");
        DateOfCreation houseMDate = new DateOfCreation(LocalDate.of(2019, 12, 18));

        // Act
        Group groupFriends = new Group(houseMID, houseMPeopleInCharge, houseMMembers, houseMDescription, houseMDate);
        boolean result = groupFriends.isFamily();

        // Assert
        assertEquals(false, result);
    }


    @Test
    @DisplayName("Verify if one group are not a family | Not Family: Has two fathers")
    public void isNotFamilyTwoFathers() {

        // Arrange
        Person motherMaria = new Person("Maria", LocalDate.of(1990, 9, 21));
        Person fatherManuel = new Person("Manuel", LocalDate.of(1980, 9, 21));
        Person fatherTiago = new Person("Tiago", LocalDate.of(1983, 9, 21));
        Person kidMiguel = new Person("Miguel", LocalDate.of(2010, 9, 21));
        Person kidPedro = new Person("Pedro", LocalDate.of(2015, 9, 21));

        ArrayList<Person> friendsPeopleInCharge = new ArrayList<Person>();
        friendsPeopleInCharge.add(fatherManuel);

        ArrayList<Person> friendsMembers = new ArrayList<Person>();
        friendsMembers.add(motherMaria);
        friendsMembers.add(kidMiguel);
        friendsMembers.add(kidPedro);
        friendsMembers.add(fatherTiago);

        Denomination friendsDenomination = new Denomination("Friends");
        GroupID friendsID = new GroupID(friendsDenomination);
        Description friendsDescription = new Description("Old friends from school");
        DateOfCreation friendsDate = new DateOfCreation(LocalDate.of(2019, 12, 18));

        // Act
        kidMiguel.setMother(motherMaria);
        kidMiguel.setFather(fatherManuel);
        kidPedro.setFather(fatherTiago);
        Group groupFriends = new Group(friendsID, friendsPeopleInCharge, friendsMembers, friendsDescription, friendsDate);
        boolean result = groupFriends.isFamily();

        // Assert
        assertEquals(false, result);
    }


    @Test
    @DisplayName("Verify if one group are not a family | Not Family: Has two mothers")
    public void isNotFamilyTwoMothers() {

        // Arrange
        Person motherMaria = new Person("Maria", LocalDate.of(1993, 9, 21));
        Person motherJoana = new Person("Joana", LocalDate.of(1992, 9, 21));
        Person fatherManuel = new Person("Manuel", LocalDate.of(1991, 9, 21));
        Person kidMiguel = new Person("Miguel", LocalDate.of(2010, 9, 21));
        Person kidPedro = new Person("Pedro", LocalDate.of(2015, 9, 21));


        ArrayList<Person> friendsPeopleInCharge = new ArrayList<Person>();
        friendsPeopleInCharge.add(motherMaria);

        ArrayList<Person> friendsMembers = new ArrayList<Person>();
        friendsMembers.add(motherJoana);
        friendsMembers.add(kidMiguel);
        friendsMembers.add(kidPedro);
        friendsMembers.add(fatherManuel);

        Denomination friendsDenomination = new Denomination("Friends");
        GroupID friendsID = new GroupID(friendsDenomination);
        Description friendsDescription = new Description("Old friends from school");
        DateOfCreation friendsDate = new DateOfCreation(LocalDate.of(2019, 12, 18));

        // Act
        kidMiguel.setMother(motherMaria);
        kidPedro.setMother(motherJoana);
        kidMiguel.setFather(fatherManuel);
        Group groupFriends = new Group(friendsID, friendsPeopleInCharge, friendsMembers, friendsDescription, friendsDate);
        boolean result = groupFriends.isFamily();

        // Assert
        assertEquals(false, result);
    }


    @Test
    @DisplayName("Verify if one group are not a family | Not Family: Has no mother")
    public void isNotFamilyMotherDoNotExist() {

        // Arrange
        Person father = new Person("Manuel", LocalDate.of(1991, 9, 21));
        Person kidMiguel = new Person("Miguel", LocalDate.of(2010, 9, 21));
        Person kidPedro = new Person("Pedro", LocalDate.of(2015, 9, 21));


        ArrayList<Person> friendsPeopleInCharge = new ArrayList<Person>();
        friendsPeopleInCharge.add(father);

        ArrayList<Person> friendsMembers = new ArrayList<Person>();
        friendsMembers.add(kidMiguel);
        friendsMembers.add(kidPedro);

        Denomination friendsDenomination = new Denomination("Friends");
        GroupID friendsID = new GroupID(friendsDenomination);
        Description friendsDescription = new Description("Old friends from school");
        DateOfCreation friendsDate = new DateOfCreation(LocalDate.of(2019, 12, 18));

        // Act
        kidMiguel.setFather(father);
        Group groupFriends = new Group(friendsID, friendsPeopleInCharge, friendsMembers, friendsDescription, friendsDate);
        boolean result = groupFriends.isFamily();

        // Assert
        assertEquals(false, result);
    }


    @Test
    @DisplayName("Verify if one group are not a family | Not Family: Has no father")
    public void isNotFamilyFatherDoNotExist() {

        // Arrange
        Person mother = new Person("Maria", LocalDate.of(1991, 9, 21));
        Person kidMiguel = new Person("Miguel", LocalDate.of(2010, 9, 21));
        Person kidPedro = new Person("Pedro", LocalDate.of(2015, 9, 21));

        ArrayList<Person> friendsPeopleInCharge = new ArrayList<Person>();
        friendsPeopleInCharge.add(mother);

        ArrayList<Person> friendsMembers = new ArrayList<Person>();
        friendsMembers.add(kidMiguel);
        friendsMembers.add(kidPedro);

        Denomination friendsDenomination = new Denomination("Friends");
        GroupID friendsID = new GroupID(friendsDenomination);
        Description friendsDescription = new Description("Old friends from school");
        DateOfCreation friendsDate = new DateOfCreation(LocalDate.of(2019, 12, 18));

        // Act
        kidMiguel.setMother(mother);
        Group groupFriends = new Group(friendsID, friendsPeopleInCharge, friendsMembers, friendsDescription, friendsDate);
        boolean result = groupFriends.isFamily();

        // Assert
        assertEquals(false, result);
    }


    @Test
    @DisplayName("Verify if one group are not a family | Not Family: Person without parents in group")
    public void isNotFamilyWithoutParentsInGroup() {

        // Arrange
        Person mother = new Person("Maria", LocalDate.of(1990, 9, 21));
        Person father = new Person("Manuel", LocalDate.of(1980, 9, 21));
        Person kidMiguel = new Person("Miguel", LocalDate.of(2010, 9, 21));
        Person kidPedro = new Person("Pedro", LocalDate.of(2015, 9, 21));
        Person kid3 = new Person("Luis", LocalDate.of(2018, 9, 21));

        ArrayList<Person> friendsPeopleInCharge = new ArrayList<Person>();
        friendsPeopleInCharge.add(father);
        friendsPeopleInCharge.add(mother);

        ArrayList<Person> friendsMembers = new ArrayList<Person>();
        friendsMembers.add(kidMiguel);
        friendsMembers.add(kidPedro);
        friendsMembers.add(kid3);

        Denomination friendsDenomination = new Denomination("Friends");
        GroupID friendsID = new GroupID(friendsDenomination);
        Description friendsDescription = new Description("Old friends from school");
        DateOfCreation friendsDate = new DateOfCreation(LocalDate.of(2019, 12, 18));

        // Act
        kidMiguel.setMother(mother);
        kidPedro.setFather(father);
        Group groupFriends = new Group(friendsID, friendsPeopleInCharge, friendsMembers, friendsDescription, friendsDate);
        boolean result = groupFriends.isFamily();

        // Assert
        assertEquals(false, result);
    }

    // Create and add account

    @Test
    @DisplayName("Verify if one account can be created and added to group account list")
    public void createAccount() {

        // Arrange Group
        Person personMaria = new Person("Maria", LocalDate.of(1998, 9, 21));
        ArrayList<Person> peopleInCharge = new ArrayList<Person>();
        peopleInCharge.add(personMaria);

        ArrayList<Person> runnersMembers = new ArrayList<Person>();

        Denomination runnersDenomination = new Denomination("Runners");
        GroupID runnersID = new GroupID(runnersDenomination);
        Description runnersDescription = new Description("People who come together to run");
        DateOfCreation runnersDate = new DateOfCreation(LocalDate.of(2019, 12, 18));

        // Arrange Account
        Description accountDescription = new Description("Margarida Salary Account");
        Denomination accountDenomination = new Denomination("Salary");

        // Act
        Group groupRunners = new Group(runnersID, peopleInCharge, runnersMembers, runnersDescription, runnersDate);
        groupRunners.createAccount(accountDescription, accountDenomination);

        AccountList expectedAccounts = new AccountList();
        boolean newAccount = expectedAccounts.createAccount(accountDescription, accountDenomination);
        //expectedAccounts.addAccount(newAccount);

        // Assert
        assertEquals(expectedAccounts, groupRunners.getAccounts());
    }


    // Create and add ledger to group

    @Test
    @DisplayName("Verify if group has ledger when created | Get empty ledger")
    public void testLedgerExists() {

        // Arrange Persons
        Person personMaria = new Person("Maria", LocalDate.of(1998, 9, 21));

        // Arrange group Runners

        Denomination runnersDenomination = new Denomination("Runners");
        GroupID runnersID = new GroupID(runnersDenomination);
        Description runnersDescription = new Description("People who come together to run");
        DateOfCreation runnersDate = new DateOfCreation(LocalDate.of(2019, 12, 18));

        Ledger runnersLedger = new Ledger();

        // Act

        Group groupRunners = new Group(runnersID, personMaria, runnersDescription, runnersDate);

        // Assert
        assertEquals(groupRunners.getLedger(), runnersLedger);
    }


    @Test
    @DisplayName("Verify if one ledger is created when one transaction is added to the group")
    public void testAddTransactionAndCreateLedger() {

        // Arrange Persons
        Person personMaria = new Person("Maria", LocalDate.of(1998, 9, 21));

        // Arrange group Runners
        ArrayList<Person> runnersPeopleInCharge = new ArrayList<Person>();
        runnersPeopleInCharge.add(personMaria);

        ArrayList<Person> runnersMembers = new ArrayList<Person>();

        Denomination groupDenomination = new Denomination("Runners");
        GroupID groupID = new GroupID(groupDenomination);
        Description groupDescription = new Description("People who come together to run");
        DateOfCreation date = new DateOfCreation(LocalDate.of(2019, 12, 18));

        // Arrange Transaction
        String transactionType = "Debit";
        String transactionDescription = "Gym monthly payment";
        double transactionAmount = 30.00;
        LocalDate transactionDate = LocalDate.of(2020, 01, 01);

        // Arrange Category
        String categoryDenomination = "Health";

        // Arrange Debit Account
        Denomination debAccountDenomination = new Denomination("Health scuffles");
        Description debAccountDescription = new Description("Health and welfare burdens");

        // Arrange Credit Account
        Denomination credAccountDenomination = new Denomination("GYM.PT");
        Description credAccountDescription = new Description("Gym account");

        // Act
        Group groupRunners = new Group(groupID, runnersPeopleInCharge, runnersMembers, groupDescription, date);

        Category health = new Category(categoryDenomination);

        Account debitAccount = new Account(debAccountDescription, debAccountDenomination);
        Account creditAccount = new Account(credAccountDescription, credAccountDenomination);

        groupRunners.createAndAddTransactionWithDate(health, transactionType, transactionDescription, transactionAmount, transactionDate, debitAccount, creditAccount);

        Ledger expectedLedger = new Ledger();

        expectedLedger.createAndAddTransactionWithDate(health, transactionType, transactionDescription, transactionAmount, transactionDate, debitAccount, creditAccount);

        // Assert
        assertEquals(groupRunners.getLedger(), expectedLedger);
    }


    @Test
    @DisplayName("Verify if one ledger is created when one transaction is added to the group")
    public void testAddTransactionAndCreateLedgerWithoutDate() {

        // Arrange Persons
        Person personMaria = new Person("Maria", LocalDate.of(1998, 9, 21));

        // Arrange group Runners
        ArrayList<Person> runnersPeopleInCharge = new ArrayList<Person>();
        runnersPeopleInCharge.add(personMaria);

        ArrayList<Person> runnersMembers = new ArrayList<Person>();

        Denomination groupDenomination = new Denomination("Runners");
        GroupID groupID = new GroupID(groupDenomination);
        Description groupDescription = new Description("People who come together to run");
        DateOfCreation date = new DateOfCreation(LocalDate.of(2019, 12, 18));

        // Arrange Transaction
        String transactionType = "Debit";
        String transactionDescription = "Gym monthly payment";
        double transactionAmount = 30.00;
        LocalDate transactionDate = LocalDate.of(2020, 01, 01);

        // Arrange Category
        String categoryDenomination = "Health";

        // Arrange Debit Account
        Denomination debAccountDenomination = new Denomination("Health scuffles");
        Description debAccountDescription = new Description("Health and welfare burdens");

        // Arrange Credit Account
        Denomination credAccountDenomination = new Denomination("GYM.PT");
        Description credAccountDescription = new Description("Gym account");

        // Act
        Group groupRunners = new Group(groupID, runnersPeopleInCharge, runnersMembers, groupDescription, date);

        Category health = new Category(categoryDenomination);

        Account debitAccount = new Account(debAccountDescription, debAccountDenomination);
        Account creditAccount = new Account(credAccountDescription, credAccountDenomination);

        boolean result = groupRunners.createAndAddTransaction(health, transactionType, transactionDescription, transactionAmount, debitAccount, creditAccount);


        Ledger expectedLedger = new Ledger();

        expectedLedger.createAndAddTransaction(health, transactionType, transactionDescription, transactionAmount, debitAccount, creditAccount);

        // Assert
        assertTrue(result);
        assertEquals(groupRunners.getLedger(), expectedLedger);
    }

    @Test
    @DisplayName("Get Records between 2 dates | Happy Case")
    public void testGetRecordBetween2Dates() {

        //Arrange of Persons
        Person personHenrique = new Person("Henrique", LocalDate.of(1987, 11, 19));

        //Arrange of Category
        String categoryDenomination = "Salary";
        Category categorySalary = new Category(categoryDenomination);

        //Arrange of Debit Account
        Description debAccountDescription = new Description("EmployerSA Account");
        Denomination debAccountDenomination = new Denomination("EmployerSA");

        Account accountDebit = new Account(debAccountDescription, debAccountDenomination);

        //Arrange of Credit Account
        Description credAccountDescription = new Description("Henrique Salary Account");
        Denomination credAccountDenomination = new Denomination("Salary");

        Account accountCredit = new Account(credAccountDescription, credAccountDenomination);

        //Arrange of Transaction

        //Transaction A
        String transactionType = "Credit";
        String transactionDescription = "Dez 2019 Salary";
        double transactionAmount = 1000.00;
        LocalDate date = LocalDate.of(2019, 12, 25);

        //TransactionB
        String transactionTypeB = "Credit";
        String transactionDescriptionB = "Jan 2020 Salary";
        double transactionAmountB = 1500.00;
        LocalDate dateB = LocalDate.of(2020, 01, 01);


        //Act
        Denomination denominationHenriqueGroup = new Denomination("HenriqueGroup");
        GroupID idHenriqueGroup = new GroupID(denominationHenriqueGroup);
        Description descriptionHenriqueGroup = new Description("SalaryHenrique");
        DateOfCreation dateHenriqueGroup = new DateOfCreation(LocalDate.of(2019, 1, 26));


        Group groupHenriqueSalary = new Group(idHenriqueGroup, personHenrique, descriptionHenriqueGroup, dateHenriqueGroup);
        groupHenriqueSalary.createAndAddTransactionWithDate(categorySalary, transactionType, transactionDescription, transactionAmount, date, accountDebit, accountCredit);
        groupHenriqueSalary.createAndAddTransactionWithDate(categorySalary, transactionTypeB, transactionDescriptionB, transactionAmountB, dateB, accountDebit, accountCredit);

        //Act
        ArrayList<Transaction> expectedRegists = new ArrayList<>();
        Transaction expectedTransaction1 = new Transaction(categorySalary, transactionType, transactionDescription, transactionAmount, date, accountDebit, accountCredit);
        Transaction expectedTransaction2 = new Transaction(categorySalary, transactionTypeB, transactionDescriptionB, transactionAmountB, dateB, accountDebit, accountCredit);
        expectedRegists.add(expectedTransaction1);
        expectedRegists.add(expectedTransaction2);

        ArrayList<Transaction> searchResult = groupHenriqueSalary.getRecordsBetweenTwoDates(groupHenriqueSalary, LocalDate.of(2019, 12, 24), LocalDate.of(2020, 01, 28));

        //Assert

        assertEquals(expectedRegists, searchResult);
    }

    @Test
    @DisplayName("Get Records between 2 dates - noOrder | Happy Case")
    public void testGetRecordBetween2Dates_noOrder() {

        //Arrange of Persons
        Person personHenrique = new Person("Henrique", LocalDate.of(1987, 11, 19));

        //Arrange of Category
        String categoryDenomination = "Salary";
        Category categorySalary = new Category(categoryDenomination);

        //Arrange of Debit Account
        Description debAccountDescription = new Description("EmployerSA Account");
        Denomination debAccountDenomination = new Denomination("EmployerSA");

        Account accountDebit = new Account(debAccountDescription, debAccountDenomination);

        //Arrange of Credit Account
        Description credAccountDescription = new Description("Henrique Salary Account");
        Denomination credAccountDenomination = new Denomination("Salary");

        Account accountCredit = new Account(credAccountDescription, credAccountDenomination);

        //Arrange of Transaction

        //Transaction A
        String transactionType = "Credit";
        String transactionDescription = "Dez 2019 Salary";
        double transactionAmount = 1000.00;
        LocalDate date = LocalDate.of(2019, 12, 25);

        //TransactionB
        String transactionTypeB = "Credit";
        String transactionDescriptionB = "Jan 2020 Salary";
        double transactionAmountB = 1500.00;
        LocalDate dateB = LocalDate.of(2020, 01, 01);


        //Act
        Denomination denominationHenriqueGroup = new Denomination("HenriqueGroup");
        GroupID idHenriqueGroup = new GroupID(denominationHenriqueGroup);
        Description descriptionHenriqueGroup = new Description("SalaryHenrique");
        DateOfCreation dateHenriqueGroup = new DateOfCreation(LocalDate.of(2019, 1, 26));


        Group groupHenriqueSalary = new Group(idHenriqueGroup, personHenrique, descriptionHenriqueGroup, dateHenriqueGroup);
        groupHenriqueSalary.createAndAddTransactionWithDate(categorySalary, transactionType, transactionDescription, transactionAmount, date, accountDebit, accountCredit);
        groupHenriqueSalary.createAndAddTransactionWithDate(categorySalary, transactionTypeB, transactionDescriptionB, transactionAmountB, dateB, accountDebit, accountCredit);

        //Act
        ArrayList<Transaction> expectedRegists = new ArrayList<>();
        Transaction expectedTransaction1 = new Transaction(categorySalary, transactionType, transactionDescription, transactionAmount, date, accountDebit, accountCredit);
        Transaction expectedTransaction2 = new Transaction(categorySalary, transactionTypeB, transactionDescriptionB, transactionAmountB, dateB, accountDebit, accountCredit);
        expectedRegists.add(expectedTransaction1);
        expectedRegists.add(expectedTransaction2);

        ArrayList<Transaction> searchResult = groupHenriqueSalary.getRecordsBetweenTwoDates(groupHenriqueSalary, LocalDate.of(2020, 01, 31), LocalDate.of(2019, 01, 31));

        //Assert

        assertEquals(expectedRegists, searchResult);
    }


    //test to get amount in a period of time

    @Test
    @DisplayName("Get Amount between 2 dates | Happy Case")
    public void testGetAmountBetween2Dates() {

        //Arrange of Persons
        Person personHenrique = new Person("Henrique", LocalDate.of(1987, 11, 19));

        //Arrange of Category
        String categoryDenomination = "Salary";
        Category categorySalary = new Category(categoryDenomination);

        //Arrange of Debit Account
        Description debAccountDescription = new Description("EmployerSA Account");
        Denomination debAccountDenomination = new Denomination("EmployerSA");

        Account accountDebit = new Account(debAccountDescription, debAccountDenomination);

        //Arrange of Credit Account
        Description credAccountDescription = new Description("Henrique Salary Account");
        Denomination credAccountDenomination = new Denomination("Salary");

        Account accountCredit = new Account(credAccountDescription, credAccountDenomination);

        //Arrange of Transaction

        //Transaction A
        String transactionType = "Credit";
        String transactionDescription = "Dez 2019 Salary";
        double transactionAmount = 1000.00;
        LocalDate date = LocalDate.of(2019, 12, 25);

        //TransactionB
        String transactionTypeB = "Credit";
        String transactionDescriptionB = "Jan 2020 Salary";
        double transactionAmountB = 1500.00;
        LocalDate dateB = LocalDate.of(2020, 01, 01);


        //Act

        Denomination denominationHenriqueGroup = new Denomination("HenriqueGroup");
        GroupID idHenriqueGroup = new GroupID(denominationHenriqueGroup);
        Description descriptionHenriqueGroup = new Description("SalaryHenrique");
        DateOfCreation dateHenriqueGroup = new DateOfCreation(LocalDate.of(2019, 1, 26));


        Group groupHenriqueSalary = new Group(idHenriqueGroup, personHenrique, descriptionHenriqueGroup, dateHenriqueGroup);
        groupHenriqueSalary.createAndAddTransactionWithDate(categorySalary, transactionType, transactionDescription, transactionAmount, date, accountDebit, accountCredit);
        groupHenriqueSalary.createAndAddTransactionWithDate(categorySalary, transactionTypeB, transactionDescriptionB, transactionAmountB, dateB, accountDebit, accountCredit);

        //Act

        Double expectedAmount = 2500.00;

        Double amount = groupHenriqueSalary.getAmountBetweenTwoDates(groupHenriqueSalary, LocalDate.of(2019, 12, 24), LocalDate.of(2020, 01, 28));

        //Assert

        assertEquals(expectedAmount, amount);
    }


    @Test
    @DisplayName("Get Amount between 2 dates | Happy Case")
    public void testGetAmountBetween2Dates_noOrderDate() {

        //Arrange of Persons
        Person personHenrique = new Person("Henrique", LocalDate.of(1987, 11, 19));

        //Arrange of Category
        String categoryDenomination = "Salary";
        Category categorySalary = new Category(categoryDenomination);

        //Arrange of Debit Account
        Description debAccountDescription = new Description("EmployerSA Account");
        Denomination debAccountDenomination = new Denomination("EmployerSA");

        Account accountDebit = new Account(debAccountDescription, debAccountDenomination);

        //Arrange of Credit Account
        Description credAccountDescription = new Description("Henrique Salary Account");
        Denomination credAccountDenomination = new Denomination("Salary");

        Account accountCredit = new Account(credAccountDescription, credAccountDenomination);

        //Arrange of Transaction

        //Transaction A
        String transactionType = "Credit";
        String transactionDescription = "Dez 2019 Salary";
        double transactionAmount = 1000.00;
        LocalDate date = LocalDate.of(2019, 12, 25);

        //TransactionB
        String transactionTypeB = "Credit";
        String transactionDescriptionB = "Jan 2020 Salary";
        double transactionAmountB = 1500.00;
        LocalDate dateB = LocalDate.of(2020, 01, 01);


        //Act

        Denomination denominationHenriqueGroup = new Denomination("HenriqueGroup");
        GroupID idHenriqueGroup = new GroupID(denominationHenriqueGroup);
        Description descriptionHenriqueGroup = new Description("SalaryHenrique");
        DateOfCreation dateHenriqueGroup = new DateOfCreation(LocalDate.of(2019, 1, 26));


        Group groupHenriqueSalary = new Group(idHenriqueGroup, personHenrique, descriptionHenriqueGroup, dateHenriqueGroup);
        groupHenriqueSalary.createAndAddTransactionWithDate(categorySalary, transactionType, transactionDescription, transactionAmount, date, accountDebit, accountCredit);
        groupHenriqueSalary.createAndAddTransactionWithDate(categorySalary, transactionTypeB, transactionDescriptionB, transactionAmountB, dateB, accountDebit, accountCredit);

        //Act

        Double expectedAmount = 2500.00;

        Double amount = groupHenriqueSalary.getAmountBetweenTwoDates(groupHenriqueSalary, LocalDate.of(2020, 01, 31), LocalDate.of(2019, 01, 01));

        //Assert

        assertEquals(expectedAmount, amount);
    }


    //get records of a account in a determined period of time

    @Test
    @DisplayName("Get all the transactions of a account in a determined period of time (all the movements) | Happy Case")
    public void testGetAllTheTransactionsOfAccountWithinACertainPeriodOfTime() {
        //Arrange Person
        String personName = "Wolverine";
        LocalDate personBirthDate = LocalDate.of(1985, 2, 15);
        Person aquaman = new Person(personName, personBirthDate);

        //Arrange Group
        Denomination groupDenomination = new Denomination("Aquaman Group");
        GroupID groupID = new GroupID(groupDenomination);
        Person responsible = aquaman;
        Description groupDescription = new Description("Aquaman group");
        DateOfCreation creationDate = new DateOfCreation(LocalDate.of(2019, 9, 16));

        //Arrange Category
        String categoryDenomination = "Salary";
        Category categorySalary = new Category(categoryDenomination);

        //Arrange Accounts

        //DebitAccount
        Description debAccountDescription = new Description("EmployerSA Account");
        Denomination debAccountDenomination = new Denomination("EmployerSA");
        Account debitAccount = new Account(debAccountDescription, debAccountDenomination);

        //CreditAccount
        Description credAccountDescription = new Description("Margarida Salary Account");
        Denomination credAccountDenomination = new Denomination("Salary");
        Account creditAccount = new Account(credAccountDescription, credAccountDenomination);

        //Arrange Transaction

        //Transaction 1
        String firstTransactionType = "Credit";
        String firstTransactionDescription = "Dez 2019 Salary";
        double firstTransactionAmount = 1000.00;
        LocalDate firstDate = LocalDate.of(2019, 12, 25);

        //Transaction 2
        String secondTransactionType = "Credit";
        String secondTransactionDescription = "Jan 2020 Salary";
        double secondTransactionAmount = 1500.00;
        LocalDate secondDate = LocalDate.of(2020, 1, 8);

        //Transaction 3
        String thirdTransactionType = "Credit";
        String thirdTransactionDescription = "Jan 2018 Salary";
        double thirdTransactionAmount = 800.00;
        LocalDate thirddate = LocalDate.of(2018, 1, 22);

        //Act

        Group aquamanGroup = new Group(groupID, responsible, groupDescription, creationDate);

        aquamanGroup.createAndAddTransactionWithDate(categorySalary, firstTransactionType,
                firstTransactionDescription, firstTransactionAmount, firstDate, debitAccount, creditAccount);

        aquamanGroup.createAndAddTransactionWithDate(categorySalary, secondTransactionType,
                secondTransactionDescription, secondTransactionAmount, secondDate, debitAccount, creditAccount);

        aquamanGroup.createAndAddTransactionWithDate(categorySalary, thirdTransactionType,
                thirdTransactionDescription, thirdTransactionAmount, thirddate, debitAccount, creditAccount);


        //Expected
        ArrayList<Transaction> expectedTransaction = new ArrayList<>();
        Transaction firstTransaction = new Transaction(categorySalary, firstTransactionType,
                firstTransactionDescription, firstTransactionAmount, firstDate, debitAccount, creditAccount);

        Transaction secondTransaction = new Transaction(categorySalary, secondTransactionType,
                secondTransactionDescription, secondTransactionAmount, secondDate, debitAccount, creditAccount);

        Transaction thirdTransaction = new Transaction(categorySalary, thirdTransactionType,
                thirdTransactionDescription, thirdTransactionAmount, thirddate, debitAccount, creditAccount);

        expectedTransaction.add(firstTransaction);
        expectedTransaction.add(secondTransaction);
        expectedTransaction.add(thirdTransaction);

        ArrayList<Transaction> RecordsBetweenTwoDatesOfADeterminedAccount = aquamanGroup.getAllTheTransactionsOfAccountWithinACertainPeriodOfTime(debitAccount,
                LocalDate.of(2016, 1, 1), LocalDate.of(2020, 1, 28));

        //Assert
        assertEquals(expectedTransaction, RecordsBetweenTwoDatesOfADeterminedAccount);
    }

    @Test
    @DisplayName("Get records of a account in a determined period of time (no movements)")
    public void testGetRecordsBetweenTwoDatesOfADeterminedAccountNoneTransactionsAreValid() {
        //Arrange Person
        String personName = "Wolverine";
        LocalDate personBirthDate = LocalDate.of(1985, 2, 15);
        Person aquaman = new Person(personName, personBirthDate);

        //Arrange Group
        Denomination groupDenomination = new Denomination("Aquaman Group");
        GroupID groupID = new GroupID(groupDenomination);
        Person responsible = aquaman;
        Description groupDescription = new Description("Aquaman group");
        DateOfCreation creationDate = new DateOfCreation(LocalDate.of(2019, 9, 16));

        //Arrange Category
        String categoryDenomination = "Salary";
        Category categorySalary = new Category(categoryDenomination);

        //Arrange Accounts

        //DebitAccount
        Description debAccountDescription = new Description("EmployerSA Account");
        Denomination debAccountDenomination = new Denomination("EmployerSA");
        Account debitAccount = new Account(debAccountDescription, debAccountDenomination);

        //CreditAccount
        Description credAccountDescription = new Description("Margarida Salary Account");
        Denomination credAccountDenomination = new Denomination("Salary");
        Account creditAccount = new Account(credAccountDescription, credAccountDenomination);

        //Arrange Transaction

        //Transaction 1
        String firstTransactionType = "Credit";
        String firstTransactionDescription = "Dez 2019 Salary";
        double firstTransactionAmount = 1000.00;
        LocalDate firstDate = LocalDate.of(2019, 12, 25);

        //Transaction 2
        String secondTransactionType = "Credit";
        String secondTransactionDescription = "Jan 2020 Salary";
        double secondTransactionAmount = 1500.00;
        LocalDate secondDate = LocalDate.of(2020, 1, 8);

        //Transaction 3
        String thirdTransactionType = "Credit";
        String thirdTransactionDescription = "Jan 2018 Salary";
        double thirdTransactionAmount = 800.00;
        LocalDate thirddate = LocalDate.of(2018, 1, 22);

        //Act

        //Ledger

        Group aquamanGroup = new Group(groupID, responsible, groupDescription, creationDate);
        aquamanGroup.createAndAddTransactionWithDate(categorySalary, firstTransactionType,
                firstTransactionDescription, firstTransactionAmount, firstDate, debitAccount, creditAccount);

        aquamanGroup.createAndAddTransactionWithDate(categorySalary, secondTransactionType,
                secondTransactionDescription, secondTransactionAmount, secondDate, debitAccount, creditAccount);

        aquamanGroup.createAndAddTransactionWithDate(categorySalary, thirdTransactionType,
                thirdTransactionDescription, thirdTransactionAmount, thirddate, debitAccount, creditAccount);


        //Expected
        ArrayList<Transaction> expectedTransaction = new ArrayList<>();
        Transaction firstTransaction = new Transaction(categorySalary, firstTransactionType,
                firstTransactionDescription, firstTransactionAmount, firstDate, debitAccount, creditAccount);

        Transaction secondTransaction = new Transaction(categorySalary, secondTransactionType,
                secondTransactionDescription, secondTransactionAmount, secondDate, debitAccount, creditAccount);

        Transaction thirdTransaction = new Transaction(categorySalary, thirdTransactionType,
                thirdTransactionDescription, thirdTransactionAmount, thirddate, debitAccount, creditAccount);


        ArrayList<Transaction> RecordsBetweenTwoDatesOfADeterminedAccount = aquamanGroup.getAllTheTransactionsOfAccountWithinACertainPeriodOfTime(debitAccount,
                LocalDate.of(2006, 1, 1), LocalDate.of(2010, 1, 28));

        //Assert
        assertEquals(expectedTransaction, RecordsBetweenTwoDatesOfADeterminedAccount);
    }

    @Test
    @DisplayName("Get records of a account in a determined period of time (all the movements) | Sad Case")
    public void testGetRecordsBetweenTwoDatesOfADeterminedAccountBeingAllTheTransactionsAreValid() {
        //Arrange Person
        String personName = "Wolverine";
        LocalDate personBirthDate = LocalDate.of(1985, 2, 15);
        Person aquaman = new Person(personName, personBirthDate);

        //Arrange Group
        Denomination groupDenomination = new Denomination("Aquaman Group");
        GroupID groupID = new GroupID(groupDenomination);
        Person responsible = aquaman;
        Description groupDescription = new Description("Aquaman group");
        DateOfCreation creationDate = new DateOfCreation(LocalDate.of(2019, 9, 16));

        //Arrange Category
        String categoryDenomination = "Salary";
        Category categorySalary = new Category(categoryDenomination);

        //Arrange Accounts

        //DebitAccount
        Description debAccountDescription = new Description("EmployerSA Account");
        Denomination debAccountDenomination = new Denomination("EmployerSA");
        Account debitAccount = new Account(debAccountDescription, debAccountDenomination);

        //CreditAccount
        Description credAccountDescription = new Description("Margarida Salary Account");
        Denomination credAccountDenomination = new Denomination("Salary");
        Account creditAccount = new Account(credAccountDescription, credAccountDenomination);

        //Arrange Transaction

        //Transaction 1
        String firstTransactionType = "Credit";
        String firstTransactionDescription = "Dez 2019 Salary";
        double firstTransactionAmount = 1000.00;
        LocalDate firstDate = LocalDate.of(2019, 12, 25);

        //Transaction 2
        String secondTransactionType = "Credit";
        String secondTransactionDescription = "Jan 2020 Salary";
        double secondTransactionAmount = 1500.00;
        LocalDate secondDate = LocalDate.of(2020, 1, 8);

        //Transaction 3
        String thirdTransactionType = "Credit";
        String thirdTransactionDescription = "Jan 2018 Salary";
        double thirdTransactionAmount = 800.00;
        LocalDate thirddate = LocalDate.of(2018, 1, 22);

        //Act

        //Ledger

        Group aquamanGroup = new Group(groupID, responsible, groupDescription, creationDate);
        aquamanGroup.createAndAddTransactionWithDate(categorySalary, firstTransactionType,
                firstTransactionDescription, firstTransactionAmount, firstDate, debitAccount, creditAccount);

        aquamanGroup.createAndAddTransactionWithDate(categorySalary, secondTransactionType,
                secondTransactionDescription, secondTransactionAmount, secondDate, debitAccount, creditAccount);

        aquamanGroup.createAndAddTransactionWithDate(categorySalary, thirdTransactionType,
                thirdTransactionDescription, thirdTransactionAmount, thirddate, debitAccount, creditAccount);


        //Expected
        ArrayList<Transaction> expectedTransaction = new ArrayList<>();
        Transaction firstTransaction = new Transaction(categorySalary, firstTransactionType,
                firstTransactionDescription, firstTransactionAmount, firstDate, debitAccount, creditAccount);

        Transaction secondTransaction = new Transaction(categorySalary, secondTransactionType,
                secondTransactionDescription, secondTransactionAmount, secondDate, debitAccount, creditAccount);


        expectedTransaction.add(firstTransaction);
        expectedTransaction.add(secondTransaction);


        ArrayList<Transaction> RecordsBetweenTwoDatesOfADeterminedAccount = aquamanGroup.getAllTheTransactionsOfAccountWithinACertainPeriodOfTime(debitAccount,
                LocalDate.of(2016, 1, 1), LocalDate.of(2020, 1, 28));

        //Assert
        assertNotEquals(expectedTransaction, RecordsBetweenTwoDatesOfADeterminedAccount);
    }

    @Test
    @DisplayName("Get records of a account in a determined period of time (only half the transactions are valid) | Happy Case")
    public void testGetRecordsBetweenTwoDatesOfADeterminedAccountOnlyHalfTheTransactionsAreValid() {
        //Arrange Person
        String personName = "Wolverine";
        LocalDate personBirthDate = LocalDate.of(1985, 2, 15);
        Person aquaman = new Person(personName, personBirthDate);

        //Arrange Group
        Denomination groupDenomination = new Denomination("Aquaman Group");
        GroupID groupID = new GroupID(groupDenomination);
        Person responsible = aquaman;
        Description groupDescription = new Description("Aquaman group");
        DateOfCreation creationDate = new DateOfCreation(LocalDate.of(2019, 9, 16));

        //Arrange Category
        String categoryDenomination = "Salary";
        Category categorySalary = new Category(categoryDenomination);

        //Arrange Accounts

        //DebitAccount
        Description debAccountDescription = new Description("EmployerSA Account");
        Denomination debAccountDenomination = new Denomination("EmployerSA");
        Account debitAccount = new Account(debAccountDescription, debAccountDenomination);

        //CreditAccount
        Description credAccountDescription = new Description("Margarida Salary Account");
        Denomination credAccountDenomination = new Denomination("Salary");
        Account creditAccount = new Account(credAccountDescription, credAccountDenomination);

        //Arrange Transaction

        //Transaction 1
        String firstTransactionType = "Credit";
        String firstTransactionDescription = "Dez 2019 Salary";
        double firstTransactionAmount = 1000.00;
        LocalDate firstDate = LocalDate.of(2016, 12, 25);

        //Transaction 2
        String secondTransactionType = "Credit";
        String secondTransactionDescription = "Jan 2020 Salary";
        double secondTransactionAmount = 1500.00;
        LocalDate secondDate = LocalDate.of(2020, 1, 8);

        //Transaction 3
        String thirdTransactionType = "Credit";
        String thirdTransactionDescription = "Jan 2018 Salary";
        double thirdTransactionAmount = 800.00;
        LocalDate thirddate = LocalDate.of(2018, 1, 22);

        //Act

        //Ledger

        Group aquamanGroup = new Group(groupID, responsible, groupDescription, creationDate);
        aquamanGroup.createAndAddTransactionWithDate(categorySalary, firstTransactionType,
                firstTransactionDescription, firstTransactionAmount, firstDate, debitAccount, creditAccount);

        aquamanGroup.createAndAddTransactionWithDate(categorySalary, secondTransactionType,
                secondTransactionDescription, secondTransactionAmount, secondDate, debitAccount, creditAccount);

        aquamanGroup.createAndAddTransactionWithDate(categorySalary, thirdTransactionType,
                thirdTransactionDescription, thirdTransactionAmount, thirddate, debitAccount, creditAccount);


        //Expected
        ArrayList<Transaction> expectedTransaction = new ArrayList<>();

        Transaction secondTransaction = new Transaction(categorySalary, secondTransactionType,
                secondTransactionDescription, secondTransactionAmount, secondDate, debitAccount, creditAccount);

        Transaction thirdTransaction = new Transaction(categorySalary, thirdTransactionType,
                thirdTransactionDescription, thirdTransactionAmount, thirddate, debitAccount, creditAccount);


        expectedTransaction.add(secondTransaction);
        expectedTransaction.add(thirdTransaction);


        ArrayList<Transaction> RecordsBetweenTwoDatesOfADeterminedAccount = aquamanGroup.getAllTheTransactionsOfAccountWithinACertainPeriodOfTime(debitAccount,
                LocalDate.of(2018, 1, 1), LocalDate.of(2020, 1, 28));

        //Assert
        assertEquals(expectedTransaction, RecordsBetweenTwoDatesOfADeterminedAccount);
    }

    @Test
    @DisplayName("Get records of a account in a determined period of time (only one movement are valid due to the use of a different account)| Happy Case (different account)")
    public void testGetRecordsBetweenTwoDatesOfADeterminedAccountSingleMovement() {
        //Arrange Person
        String personName = "Wolverine";
        LocalDate personBirthDate = LocalDate.of(1985, 2, 15);
        Person aquaman = new Person(personName, personBirthDate);

        //Arrange Group
        Denomination groupDenomination = new Denomination("Aquaman Group");
        GroupID groupID = new GroupID(groupDenomination);
        Person responsible = aquaman;
        Description groupDescription = new Description("Aquaman group");
        DateOfCreation creationDate = new DateOfCreation(LocalDate.of(2019, 9, 16));

        //Arrange Category
        String categoryDenomination = "Salary";
        Category categorySalary = new Category(categoryDenomination);

        //Arrange Accounts

        //DebitAccount
        Description debAccountDescription = new Description("EmployerSA Account");
        Denomination debAccountDenomination = new Denomination("EmployerSA");
        Account debitAccount = new Account(debAccountDescription, debAccountDenomination);

        //CreditAccount
        Description credAccountDescription = new Description("Margarida Salary Account");
        Denomination credAccountDenomination = new Denomination("Salary");
        Account creditAccount = new Account(credAccountDescription, credAccountDenomination);


        //Another DebitAccount
        Description anotherDebAccountDescription = new Description("CompanySA Account");
        Denomination anotherDebAccountDenomination = new Denomination("CompanySA");
        Account anotherDebitAccount = new Account(anotherDebAccountDescription, anotherDebAccountDenomination);

        //Arrange Transaction

        //Transaction 1
        String firstTransactionType = "Credit";
        String firstTransactionDescription = "Dez 2019 Salary";
        double firstTransactionAmount = 1000.00;
        LocalDate firstDate = LocalDate.of(2019, 12, 25);

        //Transaction 2
        String secondTransactionType = "Credit";
        String secondTransactionDescription = "Jan 2020 Salary";
        double secondTransactionAmount = 1500.00;
        LocalDate secondDate = LocalDate.of(2020, 1, 8);

        //Transaction 3
        String thirdTransactionType = "Credit";
        String thirdTransactionDescription = "Jan 2018 Salary";
        double thirdTransactionAmount = 800.00;
        LocalDate thirddate = LocalDate.of(2018, 1, 22);

        //Transaction 4
        String fourthTransactionType = "Debit";
        String fourthTransactionDescription = "Mar 2017 Salary";
        double fourthTransactionAmount = 548.00;
        LocalDate fourthdate = LocalDate.of(2017, 3, 12);

        //Act

        //Ledger

        Group aquamanGroup = new Group(groupID, responsible, groupDescription, creationDate);
        aquamanGroup.createAndAddTransactionWithDate(categorySalary, firstTransactionType,
                firstTransactionDescription, firstTransactionAmount, firstDate, anotherDebitAccount, creditAccount);

        aquamanGroup.createAndAddTransactionWithDate(categorySalary, secondTransactionType,
                secondTransactionDescription, secondTransactionAmount, secondDate, debitAccount, creditAccount);

        aquamanGroup.createAndAddTransactionWithDate(categorySalary, thirdTransactionType,
                thirdTransactionDescription, thirdTransactionAmount, thirddate, debitAccount, creditAccount);

        aquamanGroup.createAndAddTransactionWithDate(categorySalary, fourthTransactionType,
                fourthTransactionDescription, fourthTransactionAmount, fourthdate, debitAccount, creditAccount);

        //Expected
        ArrayList<Transaction> expectedTransaction = new ArrayList<>();
        Transaction firstTransaction = new Transaction(categorySalary, firstTransactionType,
                firstTransactionDescription, firstTransactionAmount, firstDate, debitAccount, creditAccount);

        Transaction secondTransaction = new Transaction(categorySalary, secondTransactionType,
                secondTransactionDescription, secondTransactionAmount, secondDate, debitAccount, creditAccount);

        Transaction thirdTransaction = new Transaction(categorySalary, thirdTransactionType,
                thirdTransactionDescription, thirdTransactionAmount, thirddate, debitAccount, creditAccount);

        Transaction fourthTransaction = new Transaction(categorySalary, fourthTransactionType,
                fourthTransactionDescription, fourthTransactionAmount, fourthdate, debitAccount, creditAccount);
        expectedTransaction.add(firstTransaction);

        ArrayList<Transaction> RecordsBetweenTwoDatesOfADeterminedAccount = aquamanGroup.getAllTheTransactionsOfAccountWithinACertainPeriodOfTime(anotherDebitAccount,
                LocalDate.of(2016, 1, 1), LocalDate.of(2020, 1, 28));

        //Assert
        assertEquals(expectedTransaction, RecordsBetweenTwoDatesOfADeterminedAccount);
    }

    @Test
    @DisplayName("Get records of a account in a determined period of time (only one movement are valid due to the use of a different account)| Happy Case (different account)")
    public void testGetRecordsBetweenTwoDatesOfADeterminedAccountDebitAndCredit() {
        //Arrange Person
        String personName = "Wolverine";
        LocalDate personBirthDate = LocalDate.of(1985, 2, 15);
        Person aquaman = new Person(personName, personBirthDate);

        //Arrange Group
        Denomination groupDenomination = new Denomination("Aquaman Group");
        GroupID groupID = new GroupID(groupDenomination);
        Person responsible = aquaman;
        Description groupDescription = new Description("Aquaman group");
        DateOfCreation creationDate = new DateOfCreation(LocalDate.of(2019, 9, 16));

        //Arrange Category
        String categoryDenomination = "Salary";
        Category categorySalary = new Category(categoryDenomination);

        //Arrange Accounts

        //DebitAccount
        Description debAccountDescription = new Description("EmployerSA Account");
        Denomination debAccountDenomination = new Denomination("EmployerSA");
        Account debitAccount = new Account(debAccountDescription, debAccountDenomination);

        //CreditAccount
        Description credAccountDescription = new Description("Margarida Salary Account");
        Denomination credAccountDenomination = new Denomination("Salary");
        Account creditAccount = new Account(credAccountDescription, credAccountDenomination);


        //Another DebitAccount
        Description anotherDebAccountDescription = new Description("CompanySA Account");
        Denomination anotherDebAccountDenomination = new Denomination("CompanySA");
        Account anotherDebitAccount = new Account(anotherDebAccountDescription, anotherDebAccountDenomination);

        //Another CreditAccount
        String anotherCreditAccountDescription = "CompanySA Account";
        String anotherCreditAccountDenomination = "CompanySA";
        Account anotherCreditAccount = new Account(anotherDebAccountDescription, anotherDebAccountDenomination);

        //Arrange Transaction


        //Transaction 2
        String secondTransactionType = "Credit";
        String secondTransactionDescription = "Jan 2020 Salary";
        double secondTransactionAmount = 1500.00;
        LocalDate secondDate = LocalDate.of(2020, 1, 8);

        //Transaction 3
        String thirdTransactionType = "Debit";
        String thirdTransactionDescription = "Jan 2018 Mortgage";
        double thirdTransactionAmount = -800.00;
        LocalDate thirddate = LocalDate.of(2018, 1, 22);

        //Transaction 4
        String fourthTransactionType = "Debit";
        String fourthTransactionDescription = "Mar 2017 Mortgage";
        double fourthTransactionAmount = -548.00;
        LocalDate fourthdate = LocalDate.of(2017, 3, 12);

        //Act

        //Ledger

        Group aquamanGroup = new Group(groupID, responsible, groupDescription, creationDate);

        aquamanGroup.createAndAddTransactionWithDate(categorySalary, secondTransactionType,
                secondTransactionDescription, secondTransactionAmount, secondDate, debitAccount, creditAccount);

        aquamanGroup.createAndAddTransactionWithDate(categorySalary, thirdTransactionType,
                thirdTransactionDescription, thirdTransactionAmount, thirddate, debitAccount, creditAccount);

        aquamanGroup.createAndAddTransactionWithDate(categorySalary, fourthTransactionType,
                fourthTransactionDescription, fourthTransactionAmount, fourthdate, debitAccount, creditAccount);

        //Expected
        ArrayList<Transaction> expectedTransaction = new ArrayList<>();

        Transaction secondTransaction = new Transaction(categorySalary, secondTransactionType,
                secondTransactionDescription, secondTransactionAmount, secondDate, debitAccount, creditAccount);

        Transaction thirdTransaction = new Transaction(categorySalary, thirdTransactionType,
                thirdTransactionDescription, thirdTransactionAmount, thirddate, debitAccount, creditAccount);

        Transaction fourthTransaction = new Transaction(categorySalary, fourthTransactionType,
                fourthTransactionDescription, fourthTransactionAmount, fourthdate, debitAccount, creditAccount);
        expectedTransaction.add(secondTransaction);
        expectedTransaction.add(thirdTransaction);
        expectedTransaction.add(fourthTransaction);

        ArrayList<Transaction> RecordsBetweenTwoDatesOfADeterminedAccount = aquamanGroup.getAllTheTransactionsOfAccountWithinACertainPeriodOfTime(debitAccount,
                LocalDate.of(2016, 1, 1), LocalDate.of(2020, 1, 28));

        //Assert
        assertEquals(expectedTransaction, RecordsBetweenTwoDatesOfADeterminedAccount);
    }

    //SCHEDULINGS

    @Test
    @DisplayName("Test For createScheduling() - Success Case")
    void createScheduling() {
        //Arrange Person Nr.1
        Person personMiguel = new Person("Miguel", LocalDate.of(2000, 10, 25));

        //Arrange Group Nr.1

        Denomination denominationGroup = new Denomination("Runners");
        GroupID groupID = new GroupID(denominationGroup);
        Description descriptionGroup = new Description("Friends");
        DateOfCreation dateOfCreationGroup = new DateOfCreation(LocalDate.of(2020, 01, 02));
        Group groupFriends = new Group(groupID, personMiguel, descriptionGroup, dateOfCreationGroup);

        //Arrange Account Nr.1

        Description debAccountDescription = new Description("EmployerSA Account");
        Denomination debAccountDenomination = new Denomination("EmployerSA");
        Account accountBanco = new Account(debAccountDescription, debAccountDenomination);


        //Arrange Account Nr.2

        Description debAccountDescriptionA = new Description("John Allowance");
        Denomination debAccountDenominationA = new Denomination("Allowance Money");
        Account accountJohn = new Account(debAccountDescriptionA, debAccountDenominationA);

        //Arrange Category Nr.1
        Category category = new Category("Allowance");

        //Arrange Transaction
        LocalDate triggerDate = LocalDate.now();

        //Act
        boolean result = groupFriends.createScheduling("Credit", 50.0, accountBanco, accountJohn, category, "Allowance", triggerDate, "Daily");

        //Assert
        assertEquals(true, result);
        assertEquals(true, groupFriends.hasScheduling(new Scheduling("Credit", 50.0, accountBanco, accountJohn, category, "Allowance", triggerDate, "Daily")));
        assertEquals(1, groupFriends.schedulingListSize());
    }

    @Test
    @DisplayName("Test For createScheduling() - Create Two Schedules")
    void createSchedulingTwice() {
        //Arrange Person Nr.1
        Person personMiguel = new Person("Miguel", LocalDate.of(2000, 10, 25));

        //Arrange Group Nr.1
        Denomination denominationGroup = new Denomination("Runners");
        GroupID groupID = new GroupID(denominationGroup);
        Description descriptionGroup = new Description("Friends");
        DateOfCreation dateOfCreationGroup = new DateOfCreation(LocalDate.of(2020, 01, 02));
        Group groupFriends = new Group(groupID, personMiguel, descriptionGroup, dateOfCreationGroup);

        //Arrange Account Nr.1

        Description debAccountDescription = new Description("EmployerSA Account");
        Denomination debAccountDenomination = new Denomination("EmployerSA");
        Account accountBanco = new Account(debAccountDescription, debAccountDenomination);


        //Arrange Account Nr.2

        Description debAccountDescriptionA = new Description("John Allowance");
        Denomination debAccountDenominationA = new Denomination("Allowance Money");
        Account accountJohn = new Account(debAccountDescriptionA, debAccountDenominationA);

        //Arrange Category Nr.1
        Category category = new Category("Allowance");

        //Arrange Transaction
        LocalDate triggerDate = LocalDate.now();

        //Act
        groupFriends.createScheduling("Credit", 50.0, accountBanco, accountJohn, category, "Allowance", triggerDate, "Daily");
        boolean result = groupFriends.createScheduling("Credit", 50.0, accountBanco, accountJohn, category, "Allowance", triggerDate, "Weekly");

        //Assert
        assertEquals(true, result);
        assertEquals(true, groupFriends.hasScheduling(new Scheduling("Credit", 50.0, accountBanco, accountJohn, category, "Allowance", triggerDate, "Daily")));
        assertEquals(2, groupFriends.schedulingListSize());
    }

    @Test
    @DisplayName("Test For createScheduling() - Create Same Two Schedules")
    void createSameSchedulingTwice() {
        //Arrange Person Nr.1
        Person personMiguel = new Person("Miguel", LocalDate.of(2000, 10, 25));

        //Arrange Group Nr.1
        Denomination denominationGroup = new Denomination("Runners");
        GroupID groupID = new GroupID(denominationGroup);
        Description descriptionGroup = new Description("Friends");
        DateOfCreation dateOfCreationGroup = new DateOfCreation(LocalDate.of(2020, 01, 02));
        Group groupFriends = new Group(groupID, personMiguel, descriptionGroup, dateOfCreationGroup);

        //Arrange Account Nr.1

        Description debAccountDescription = new Description("EmployerSA Account");
        Denomination debAccountDenomination = new Denomination("EmployerSA");
        Account accountBanco = new Account(debAccountDescription, debAccountDenomination);


        //Arrange Account Nr.2

        Description debAccountDescriptionA = new Description("John Allowance");
        Denomination debAccountDenominationA = new Denomination("Allowance Money");
        Account accountJohn = new Account(debAccountDescriptionA, debAccountDenominationA);


        //Arrange Category Nr.1
        Category category = new Category("Allowance");

        //Arrange Transaction
        LocalDate triggerDate = LocalDate.now();

        //Act
        groupFriends.createScheduling("Credit", 50.0, accountBanco, accountJohn, category, "Allowance", triggerDate, "Daily");
        boolean result = groupFriends.createScheduling("Credit", 50.0, accountBanco, accountJohn, category, "Allowance", triggerDate, "Daily");

        //Assert
        assertEquals(false, result);
        assertEquals(true, groupFriends.hasScheduling(new Scheduling("Credit", 50.0, accountBanco, accountJohn, category, "Allowance", triggerDate, "Daily")));
        assertEquals(1, groupFriends.schedulingListSize());
    }

    --------------------------------PARA APAGAR--------------------------------*/
}
