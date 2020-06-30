package com.finance.project.domainLayer.domainEntities.vosShared;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * LedgerID VOtest
 */

class LedgerIDTest {

    //Constructor
    @Test
    @DisplayName("Test the LedgerID constructor")
    public void testTheLedgerIDConstructorPerson() {

        //Arrange

        LedgerID newLedgerID = LedgerID.createLedgerID();

        //Act

        //Assert

        assertTrue(newLedgerID.getLedgerID().matches(".{8}-.{4}-.{4}-.{4}-.{12}"));
        assertEquals(36,newLedgerID.getLedgerID().length());
    }

    //Equals

    @Test
    @DisplayName("Test the equals - same object")
    public void testTheLedgerIDEqualsSameObject() {

        //Arrange

        LedgerID newLedgerID = LedgerID.createLedgerID();

        //Act

        //Assert

        assertTrue(newLedgerID.equals(newLedgerID));
    }

    @Test
    @DisplayName("Test the equals - not instanceOf")
    public void testTheLedgerIDEqualsNotInstanceOf() {

        //Arrange

        LedgerID newLedgerID = LedgerID.createLedgerID();

        //Arrange Category

        String catDenomination = "Food";
        GroupID groupIDcat = GroupID.createGroupID("Futebol");
        CategoryID categoryFood = CategoryID.createCategoryID(catDenomination, groupIDcat);

        //Assert

        assertFalse(newLedgerID.equals(categoryFood));
    }

    @Test
    @DisplayName("Test the equals - SadCase")
    public void testTheLedgerIDEqualsSadCase() {

        //Arrange

        LedgerID newLedgerID = LedgerID.createLedgerID();
        LedgerID newLedgerIDA = LedgerID.createLedgerID();

        //Act

        //Assert

        assertFalse(newLedgerID.equals(newLedgerIDA));
    }

    @Test
    @DisplayName("Test the equals - SadCase object null")
    public void testTheLedgerIDEqualsSadCaseObjectNull() {

        //Arrange

        LedgerID newLedgerID = null;
        LedgerID expectedLedgerID = LedgerID.createLedgerID();

        //Act

        //Assert

        assertNotEquals(newLedgerID,expectedLedgerID);
    }


    @Test
    @DisplayName("Test the equals - NotInstance String")
    public void testTheLedgerIDEqualsNotInstanceString() {

        //Arrange

        String string = "Bug killer";
        LedgerID newLedgerID = LedgerID.createLedgerID();

        //Act

        boolean result = newLedgerID.equals(string);

        //Assert

        assertFalse(result);
    }

    @Test
    @DisplayName("Test the equals - Null")
    public void testTheLedgerIDEqualsNull() {

        //Arrange

        LedgerID newLedgerID = LedgerID.createLedgerID();

        //Act

        boolean result = newLedgerID.equals(null);

        //Assert

        assertFalse(result);
    }

    @Test
    @DisplayName("Test HashCode - HappyCase")
    public void testHashcode_True() {

        //Arrange

        LedgerID newLedgerID = LedgerID.createLedgerID();

        //Act

        //Assert

        assertTrue(newLedgerID.hashCode()==newLedgerID.hashCode());

    }
}