package com.finance.project.domainLayer.domainEntities.vosShared;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GroupIDTest {

    @Test
    @DisplayName("Check groupID creator || Happy Case")
    void testGroupIDConstructorHappyCase() {

        //Act
        GroupID groupID = GroupID.createGroupID("Friends");
    }

    @Test
    @DisplayName("Check groupID creator || Sad Case: Denomination Null")
    void testGroupIDConstructorDenominationNull() {

        //Act
        try {
            GroupID groupID = GroupID.createGroupID(null);
            assertTrue(false, "GroupID not created. The denomination parameter can't be null or a empty string");
        } catch (Exception e) {
        }
    }

    @Test
    @DisplayName("Check groupID creator || Sad Case: Denomination Empty")
    void testGroupIDConstructorDenominationEmpty() {

        //Act
        try {
            GroupID groupID = GroupID.createGroupID("");
            assertTrue(false, "GroupID not created. The denomination parameter can't be null or a empty string");
        } catch (Exception e) {
        }
    }

    @Test
    @DisplayName("Verify Equals of GroupID || Happy Case: Same groupID")
    void sameGroupID() {

        GroupID groupID = GroupID.createGroupID("Friends");
        GroupID friendsGroupID = GroupID.createGroupID("Friends");

        boolean result = groupID.equals(friendsGroupID);

        assertEquals(true, result);
    }

    @Test
    @DisplayName("Verify Equals of GroupID || GroupID equals to itself")
    void groupIDEqualsToItSelf() {

        GroupID groupID = GroupID.createGroupID("Friends");

        boolean result = groupID.equals(groupID);

        assertEquals(true, result);
    }

    @Test
    @DisplayName("Verify Equals of GroupID || Sad Case: Not the same groupID")
    void differentGroupID() {

        GroupID groupID = GroupID.createGroupID("Runners");
        GroupID friendsGroupID = GroupID.createGroupID("Friends");

        boolean result = groupID.equals(friendsGroupID);

        assertEquals(false, result);
    }

    @Test
    @DisplayName("Verify Equals of GroupID || Not the same groupID: GroupID null")
    void differentGroupIDObjectNull() {

        GroupID groupID = null;
        GroupID friendsGroupID = GroupID.createGroupID("Friends");

        boolean result = friendsGroupID.equals(groupID);

        assertEquals(false, result);
    }

    @Test
    @DisplayName("Verify Equals of GroupID || Not the same groupID: Differents Class's")
    void differentGroupIDDifferentClass() {

        PersonID personID = PersonID.createPersonID("email@gmail.com");
        GroupID friendsGroupID = GroupID.createGroupID("Friends");

        boolean result = friendsGroupID.equals(personID);

        assertEquals(false, result);
    }

    @Test
    @DisplayName("Verify getDenomination || Happy Case")
    void testGetDenomination() {

        //Act
        GroupID groupID = GroupID.createGroupID("Friends");

        //Expected
        Denomination denomination = Denomination.createDenomination("Friends");

        //Assert
        assertEquals(groupID.getDenomination(), denomination);
    }

}