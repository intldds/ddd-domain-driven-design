package com.finance.project.domainLayer.domainEntities.vosShared;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * AccountID VOtest
 */

class AccountIDTest {

    //Constructor with PersonID
    @Test
    @DisplayName("Test the AccountID constructor with PersonID")
    public void testTheAccountIDConstructorPerson() {

        //Arrange
        String accountDenomination = "AnaAccount";
        Denomination expectedDenomination = Denomination.createDenomination(accountDenomination);
        String email = "email@hotmail.com";
        PersonID personID = PersonID.createPersonID(email);

        //Act
        AccountID anaAccount = AccountID.createAccountID(accountDenomination, personID);

        //Assert
        assertEquals(expectedDenomination, anaAccount.getDenomination());
        Assertions.assertEquals(personID, anaAccount.getOwnerID());
    }


    //Constructor with GroupID
    @Test
    @DisplayName("Test the AccountID constructor with GroupID")
    public void testTheAccountIDConstructorGroup() {

        //Arrange
        String accountDenomination = "TenisAccount";
        String groupDenomination = "Tenis";
        Denomination expectedDenomination = Denomination.createDenomination(accountDenomination);
        GroupID groupID = GroupID.createGroupID(groupDenomination);

        //Act
        AccountID tenisAccount = AccountID.createAccountID(accountDenomination, groupID);

        //Assert
        assertEquals(expectedDenomination, tenisAccount.getDenomination());
        Assertions.assertEquals(groupID, tenisAccount.getOwnerID());
    }


    //Test exceptions PersonID

    @Test
    @DisplayName("testExceptionDenominationNull")
    public void testExceptionDenominationNull() {

        //Arrange
        String accountDenomination = "AnaAccount";
        String email = "abc@gmail.com";
        PersonID personID = PersonID.createPersonID(email);

        try {
            AccountID.createAccountID (null, personID) ;
            assertTrue(false, "AccountID not created due to the fact that the denomination parameter hasn't a valid argument");
        } catch (Exception e) {

        }
    }


    @Test
    @DisplayName("testExceptionPersonIDNull")
    public void testExceptionPersonIDNull() {

        //Arrange
        String accountDenomination = "AnaAccount";
        String email = "abc@gmail.com";
        PersonID personID = PersonID.createPersonID(email);

        try {
            AccountID.createAccountID (accountDenomination, null) ;
            assertTrue(false, "AccountID not created due to the fact that the personID parameter hasn't a valid argument");
        } catch (Exception e) {

        }
    }


    //Test exceptions GroupID

    @Test
    @DisplayName("Test Exception Denomination is Null")
    public void testExceptionDenominationNull_byGroup() {

        //Arrange
        String accountDenomination = "TenisAccount";
        String groupDenomination = "Tenis";
        GroupID groupID = GroupID.createGroupID(groupDenomination);

        try {
            AccountID.createAccountID (null, groupID) ;
            assertTrue(false, "AccountID not created due to the fact that the denomination parameter hasn't a valid argument");
        } catch (Exception e) {

        }
    }


    @Test
    @DisplayName("Test Exception Denomination is Null")
    public void testException_byGroupGroupIDisNull() {

        //Arrange
        String accountDenomination = "TenisAccount";
        String groupDenomination = "Tenis";
        GroupID groupID = GroupID.createGroupID(groupDenomination);

        try {
            AccountID.createAccountID (accountDenomination, null) ;
            assertTrue(false, "AccountID not created due to the fact that the denomination parameter hasn't a valid argument");
        } catch (Exception e) {

        }
    }


    //Test Equals

    @Test
    @DisplayName("Test the AccountID Equals- HappyCase same Object")
    public void testEquals_True_SameObject() {

        //Arrange
        String accountDenomination = "TenisAccount";
        String groupDenomination = "Tenis";
        GroupID groupID = GroupID.createGroupID(groupDenomination);

        //Act
        AccountID tenisAccount = AccountID.createAccountID(accountDenomination, groupID);

        //Assert

        assertEquals(tenisAccount.equals(tenisAccount), tenisAccount.equals(tenisAccount));
        assertTrue(tenisAccount.equals(tenisAccount));
    }

    @Test
    @DisplayName("Test the AccountID Equals- SadCase NotInstanceOf")
    public void testEquals_False_NotInstanceOf() {

        //Arrange
        String accountDenomination = "TenisAccount";
        String groupDenomination = "Tenis";
        GroupID groupID = GroupID.createGroupID(groupDenomination);


        //Act
        AccountID tenisAccount = AccountID.createAccountID(accountDenomination, groupID);

        //Assert

        assertFalse(tenisAccount.equals(groupID));
    }

    //Equals Person


    @Test
    @DisplayName("Test Equals with PersonID - SadCase")
    public void testEqualsWithPersonID_False_DifferentAccountID() {

        //Arrange
        String accountDenomination = "AnaAccount";
        String email = "abc@gmail.com";
        PersonID personID = PersonID.createPersonID(email);

        String accountDenominationA = "Account";
        String emailA = "jjj@gmail.com";
        PersonID personIDA = PersonID.createPersonID(emailA);

        //Act
        AccountID anaAccount = AccountID.createAccountID(accountDenomination, personID);
        AccountID expectedAccount = AccountID.createAccountID(accountDenominationA, personIDA);

        //Assert
        assertFalse(anaAccount.equals(expectedAccount));
    }


    //Equals Group


    @Test
    @DisplayName("Test the AccountID Equals- HappyCase")
    public void testEquals_True_() {

        //Arrange
        String accountDenomination = "TenisAccount";
        String groupDenomination = "Tenis";
        GroupID groupID = GroupID.createGroupID(groupDenomination);

        //Act
        AccountID tenisAccount = AccountID.createAccountID(accountDenomination, groupID);
        AccountID expectedAccount = AccountID.createAccountID(accountDenomination, groupID);
        boolean result = tenisAccount.equals(expectedAccount);

        //Assert

        assertEquals(true, result);
        assertEquals(tenisAccount.equals(expectedAccount), expectedAccount.equals(tenisAccount));
    }


    @Test
    @DisplayName("Test the AccountID Equals- SadCase DifferentAccountID")
    public void testEquals_FalseDifferentAccountID() {

        //Arrange
        String accountDenomination = "TenisAccount";
        String groupDenomination = "Tenis";
        GroupID groupID = GroupID.createGroupID(groupDenomination);

        String accountDenominationB = "FootBallAccount";

        //Act
        AccountID tenisAccount = AccountID.createAccountID(accountDenomination, groupID);
        AccountID footballAccount = AccountID.createAccountID(accountDenominationB, groupID);
        boolean result = tenisAccount.equals(footballAccount);

        //Assert

        assertNotEquals(true, result);
        assertFalse(tenisAccount.equals(footballAccount));
    }


    @Test
    @DisplayName("Test the AccountID Equals- HappyCase")
    public void testEquals_False_GroupID() {

        //Arrange
        String accountDenomination = "TenisAccount";
        String groupDenomination = "Tenis";
        GroupID groupID = GroupID.createGroupID(groupDenomination);
        String groupDenominationA = "MondayTenis";
        GroupID expectedGroupID = GroupID.createGroupID(groupDenominationA);

        //Act
        AccountID tenisAccount = AccountID.createAccountID(accountDenomination, groupID);
        AccountID expectedAccount = AccountID.createAccountID(accountDenomination, expectedGroupID);
        boolean result = tenisAccount.equals(expectedAccount);

        //Assert

        assertEquals(false, result);
        assertFalse(tenisAccount.equals(expectedAccount));
    }





    //test hashcode Person

    @Test
    @DisplayName("Test HashCode with PersonID - HappyCase")
    public void testHashcode_True() {

        //Arrange
        String accountDenomination = "AnaAccount";
        String email = "abc@gmail.com";
        PersonID personID = PersonID.createPersonID(email);
        //Act
        AccountID anaAccount = AccountID.createAccountID(accountDenomination, personID);
        AccountID expectedAccount = AccountID.createAccountID(accountDenomination, personID);

        //Assert
        assertTrue(anaAccount.hashCode()==expectedAccount.hashCode());
        assertTrue(anaAccount.hashCode()==anaAccount.hashCode());

    }



    //test hashcode Group

    @Test
    @DisplayName("Test the AccountID HashCode- HappyCase")
    public void testHashTrue() {

        //Arrange
        String accountDenomination = "TenisAccount";
        String denomination = "Tenis";
        GroupID groupID = GroupID.createGroupID(denomination);

        String accountDenominationB = "TenisAccount";
        GroupID groupIDB = GroupID.createGroupID(denomination);

        //Act
        AccountID tenisAccount = AccountID.createAccountID(accountDenomination, groupID);
        AccountID expectedAccount = AccountID.createAccountID(accountDenominationB, groupIDB);

        //Assert

        assertTrue(tenisAccount.hashCode()==expectedAccount.hashCode());
        assertTrue(tenisAccount.hashCode() == tenisAccount.hashCode());
    }


}