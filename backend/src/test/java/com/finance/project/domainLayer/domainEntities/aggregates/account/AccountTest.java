package com.finance.project.domainLayer.domainEntities.aggregates.account;


import com.finance.project.domainLayer.domainEntities.vosShared.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    //--Constructor & get methods
    @Test
    @DisplayName("Test the account constructor- PersonID")
    public void testTheAccountConstructor_withPerson() {

        //Arrange
        String description = "Supermarket";
        String denomination = "JonAccount";
        String personEmail = "abcd@efg.com";
        PersonID personID = PersonID.createPersonID(personEmail);
        AccountID accountID = AccountID.createAccountID(denomination, personID);


        //Act
        Account jonAccount = Account.createAccount(description, denomination, personID);

        //Assert

        assertEquals(accountID, jonAccount.getAccountID());
    }

    @Test
    @DisplayName("Test the account constructor- GroupID")
    public void testTheAccountConstructor_withGroup() {

        //Arrange
        String description = "TenisExpenses";
        String denomination = "TenisAccount";
        GroupID groupID = GroupID.createGroupID("Tenis");
        AccountID accountID = AccountID.createAccountID(denomination, groupID);


        //Act
        Account tenisAccount = Account.createAccount(description, denomination, groupID);

        //Assert

        assertEquals(accountID, tenisAccount.getAccountID());
    }

    //Test Get AccountDescription

    @Test
    @DisplayName("Test the account constructor- GroupID")
    public void testTheAccountDescription() {

        //Arrange
        String description = "TenisExpenses";
        String denomination = "TenisAccount";
        GroupID groupID = GroupID.createGroupID("Tenis");
        AccountID accountID = AccountID.createAccountID(denomination, groupID);


        //Act
        Account tenisAccount = Account.createAccount(description, denomination, groupID);

        //Assert

        assertEquals(description, tenisAccount.getDescription().getDescription());
    }



    //Test exceptions for PersonID

     @Test
    @DisplayName("Test the Exception- PersonID")
    public void testTheException_descriptionNull() {

        //Arrange
        String description = "Supermarket";
        String denomination = "JonAccount";
        String personEmail = "abcd@efg.com";
        PersonID personID = PersonID.createPersonID(personEmail);
        AccountID accountID = AccountID.createAccountID(denomination, personID);

        try {
            Account.createAccount (null,denomination, personID) ;
            assertTrue(false, "Account not created due to the fact that the description parameter hasn't a valid argument");
        } catch (Exception e) {

        }
    }

    @Test
    @DisplayName("Test the Exception- PersonID")
    public void testTheException_denominationNull() {

        //Arrange
        String description = "Supermarket";
        String denomination = "JonAccount";
        String personEmail = "abcd@efg.com";
        PersonID personID = PersonID.createPersonID(personEmail);
        AccountID accountID = AccountID.createAccountID(denomination, personID);

        try {
            Account.createAccount (description,null, personID) ;
            assertTrue(false, "Account not created due to the fact that the denomination parameter hasn't a valid argument");
        } catch (Exception e) {

        }
    }

    @Test
    @DisplayName("Test the Exception- PersonID")
    public void testTheException_PersonIDNull() {

        //Arrange
        String description = "Supermarket";
        String denomination = "JonAccount";
        String personEmail = "abcd@efg.com";
        PersonID personID = PersonID.createPersonID(personEmail);
        AccountID accountID = AccountID.createAccountID(denomination, personID);

        try {
            Account.createAccount (description,denomination, null) ;
            assertTrue(false, "Account not created due to the fact that the personID parameter hasn't a valid argument");
        } catch (Exception e) {

        }
    }

    //Test exceptions for GroupID

    @Test
    @DisplayName("Test the exceptions- GroupID")
    public void testExceptionGroupIDDescriptionNull() {

        //Arrange
        String description = "TenisExpenses";
        String denomination = "TenisAccount";
        GroupID groupID = GroupID.createGroupID("Tenis");
        AccountID accountID = AccountID.createAccountID(denomination, groupID);

        try {
            Account.createAccount (null,denomination, groupID) ;
            assertTrue(false, "Account not created due to the fact that the description parameter hasn't a valid argument");
        } catch (Exception e) {

        }
    }

    @Test
    @DisplayName("Test the exceptions- GroupID")
    public void testExceptionGroupIDDenominationNull() {

        //Arrange
        String description = "TenisExpenses";
        String denomination = "TenisAccount";
        GroupID groupID = GroupID.createGroupID("Tenis");
        AccountID accountID = AccountID.createAccountID(denomination, groupID);

        try {
            Account.createAccount (description,null, groupID) ;
            assertTrue(false, "Account not created due to the fact that the denomination parameter hasn't a valid argument");
        } catch (Exception e) {

        }
    }


    @Test
    @DisplayName("Test the exceptions- GroupID")
    public void testExceptionGroupIDGroupIDNull() {

        //Arrange
        String description = "TenisExpenses";
        String denomination = "TenisAccount";
        GroupID groupID = GroupID.createGroupID("Tenis");
        AccountID accountID = AccountID.createAccountID(denomination, groupID);

        try {
            Account.createAccount(description,denomination, null) ;
            assertTrue(false, "Account not created due to the fact that the groupID parameter hasn't a valid argument");
        } catch (Exception e) {

        }
    }

    /*
    //Check AccountID


    @Test
    @DisplayName("Test the account constructor- GroupID")
    public void testCheckAccountID() {

        //Arrange
        String description = "TenisExpenses";
        String denomination = "TenisAccount";
        GroupID groupID = GroupID.createGroupID("Tenis");
        Account newAccount = Account.createAccount(description, denomination, groupID);
        AccountID accountID = AccountID.createAccountID(denomination, groupID);

        //Act
        boolean result = newAccount.checkAccountID(accountID);

        //Assert

        assertTrue(result);
    }


     */
    //Test equals

    @Test
    @DisplayName("Verify equals method | Happy Case ")
    void checkEquals() {

        //Arrange
        String description = "TenisExpenses";
        String denomination = "TenisAccount";
        GroupID groupID = GroupID.createGroupID("Tenis");

        //Act
        Account tenisAccount = Account.createAccount(description, denomination, groupID);
        Account otherAccount = Account.createAccount(description, denomination, groupID);

        boolean result = tenisAccount.equals(otherAccount);

        //Assert
        assertEquals(true, result);
    }

    @Test
    @DisplayName("Verify equals method | Sad Case ")
    void checkEquals_False() {

        //Arrange
        String description = "TenisExpenses";
        String denomination = "TenisAccount";
        GroupID groupID = GroupID.createGroupID("Tenis");
        GroupID anotherGroupID = GroupID.createGroupID("Footbal");

        //Act
        Account tenisAccount = Account.createAccount(description, denomination, groupID);
        Account otherAccount = Account.createAccount(description, denomination, anotherGroupID);

        boolean result = tenisAccount.equals(otherAccount);

        //Assert
        assertEquals(false, result);
    }

    @Test
    @DisplayName("Verify equals method | Happy Case - Same Object ")
    void checkEquals_SameObject() {

        //Arrange
        String description = "TenisExpenses";
        String denomination = "TenisAccount";
        GroupID groupID = GroupID.createGroupID("Tenis");

        //Act
        Account tenisAccount = Account.createAccount(description, denomination, groupID);

        boolean result = tenisAccount.equals(tenisAccount);

        //Assert
        assertEquals(true, result);
    }


    @Test
    @DisplayName("Verify equals method | Sad Case - Not Instance Of ")
    void checkEquals_NotInstance() {

        //Arrange
        String description = "TenisExpenses";
        String denomination = "TenisAccount";
        GroupID groupID = GroupID.createGroupID("Tenis");

        //Act
        Account tenisAccount = Account.createAccount(description, denomination, groupID);

        boolean result = tenisAccount.equals(groupID);

        //Assert
        assertEquals(false, result);
    }

    //hashCode

    @Test
    @DisplayName("Verify hashcode method | Happy Case ")
    void checkHashCode() {

        //Arrange
        String description = "TenisExpenses";
        String denomination = "TenisAccount";
        GroupID groupID = GroupID.createGroupID("Tenis");

        //Act
        Account tenisAccount = Account.createAccount(description, denomination, groupID);
        Account otherAccount = Account.createAccount(description, denomination, groupID);


        //Assert
        assertTrue(tenisAccount.hashCode() == otherAccount.hashCode());
        assertTrue(tenisAccount.hashCode() == tenisAccount.hashCode());
    }

    @Test
    @DisplayName("Verify hashCode method | different hashCodes ")
    void checkHashCodeDifferent() {

        // Arrange
        String description = "TennisExpenses";
        String denomination = "TennisAccount";
        String email = "tomas@manel.com";

        String otherDescription = "Swimming lessons";
        String otherDenomination = "Sports";
        String email2 = "ricardo@ribeiro.com";

        PersonID personID1 = PersonID.createPersonID(email);
        PersonID personID2 = PersonID.createPersonID(email2);


        LedgerID friendsLedgerID = LedgerID.createLedgerID();
        LedgerID runnersLedgerID = LedgerID.createLedgerID();

        // Act
        Account tenisAccount = Account.createAccount(description, denomination, personID1);
        Account otherAccount = Account.createAccount(otherDescription, otherDenomination, personID2);

        // Assert
        assertEquals(false, tenisAccount.hashCode() == otherAccount.hashCode());
    }
/*

    // check AccountID Person

    @Test
    @DisplayName("Verify checkAccountID method for Person | same accountID")
    void checkAccountID() {

        // Arrange
        String description = "TenisExpenses";
        String denomination = "TenisAccount";
        String email = "borjan@krick.com";

        PersonID personID = PersonID.createPersonID(email);
        AccountID accountID = AccountID.createAccountID(denomination, personID);

        // Act
        Account tennisAccount = Account.createAccount(description, denomination, personID);
        Account otherAccount = Account.createAccount(description, denomination, personID);

        boolean expected = true;
        boolean result = tennisAccount.checkAccountID(accountID);


        // Assert
        assertEquals(expected, result);
    }




    @Test
    @DisplayName("Verify checkAccountID method for Person | different accountID")
    void checkAccountDifferentID() {

        // Arrange
        String description = "TenisExpenses";
        String denomination = "TenisAccount";
        String email = "borjan@krick.com";
        String otherEmail = "john@yahoo.com";

        PersonID personID = PersonID.createPersonID(email);
        PersonID otherPersonID = PersonID.createPersonID(otherEmail);
        AccountID accountID = AccountID.createAccountID(denomination, otherPersonID);

        // Act
        Account tennisAccount = Account.createAccount(description, denomination, personID);
        Account otherAccount = Account.createAccount(description, denomination, personID);

        boolean expected = false;
        boolean result = tennisAccount.checkAccountID(accountID);

        // Assert
        assertEquals(expected, result);
    }


    // check AccountID Group
    @Test
    @DisplayName("Verify checkAccountID method for Group | same accountID")
    void checkAccountIDGroup() {

        // Arrange
        String description = "TenisExpenses";
        String denomination = "TenisAccount";
        String email = "borjan@krick.com";

        GroupID groupID = GroupID.createGroupID(denomination);
        AccountID accountID = AccountID.createAccountID(denomination, groupID);

        // Act
        Account tennisAccount = Account.createAccount(description, denomination, groupID);
        Account otherAccount = Account.createAccount(description, denomination, groupID);

        boolean expected = true;
        boolean result = tennisAccount.checkAccountID(accountID);


        // Assert
        assertEquals(expected, result);
    }


    @Test
    @DisplayName("Verify checkAccountID method for Person | different accountID")
    void checkAccountDifferentIDGroup() {

        // Arrange
        String description = "TennisExpenses";
        String denomination = "TennisAccount";
        String otherDenomination = "SwimmingTeam";
        String otherEmail = "john@yahoo.com";

        GroupID groupID = GroupID.createGroupID(denomination);
        GroupID otherGroupID = GroupID.createGroupID(otherDenomination);
        AccountID accountID = AccountID.createAccountID(denomination, groupID);

        // Act
        Account tennisAccount = Account.createAccount(description, denomination, otherGroupID);

        boolean expected = false;
        boolean result = tennisAccount.checkAccountID(accountID);

        // Assert
        assertEquals(expected, result);
    }



 */
    // --equals() and hashCode() methods


    @Test
    @DisplayName("Test equals() and hashCode methods - 2 objects are equal")
    public void equalsAndHasCode_HappyCase() {

        //Arrange
        String account1Denomination = "Utilities";
        String account1Description = "Expenses with electricity, gas and water";
        String email1 = "jack@dorsey.com";

        String account2Denomination = "Utilities";
        String account2Description = "Expenses with electricity, gas and water";
        String email2 = "jack@dorsey.com";

        PersonID personID = PersonID.createPersonID(email1);

        //Act
        Account account1 = Account.createAccount(account1Description, account1Denomination, personID);
        Account account2 = Account.createAccount(account2Description, account2Denomination, personID);

        //Assert
        assertEquals(account1, account2);
        assertTrue(account1.hashCode() == account2.hashCode());
    }


    @Test
    @DisplayName("Test hashCode - 2 objects are different")
    public void hashCode_DifferentObjects() {

        //Arrange
        String account1Denomination = "Utilities";
        String account1Description = "Expenses with electricity, gas and water";
        String email1 = "jack@dorsey.com";

        String account2Denomination = "Travelling";
        String account2Description = "Trip to Paris";
        String email2 = "elon@musk.com";

        PersonID personID1 = PersonID.createPersonID(email1);
        PersonID personID2 = PersonID.createPersonID(email2);

        //Act
        Account account1 = Account.createAccount(account1Description, account1Denomination, personID1);
        Account account2 = Account.createAccount(account2Description, account2Denomination, personID2);

        //Assert
        assertFalse(account1.hashCode() == account2.hashCode());
    }


    @Test
    @DisplayName("Test equals() method - 2 equal objects")
    public void testEqualsMethodTwoEqualObjects() {

        // Arrange
        String account1Denomination = "Utilities";
        String account1Description = "Expenses with electricity, gas and water";
        String email1 = "jack@dorsey.com";

        String account2Denomination = "Utilities";
        String account2Description = "Expenses with electricity, gas and water";
        String email2 = "jack@dorsey.com";

        PersonID personID = PersonID.createPersonID(email1);

        // Act
        Account utilities = Account.createAccount(account1Description, account1Denomination, personID);
        Account company = Account.createAccount(account2Description, account2Denomination, personID);

        // Assert
        assertEquals(utilities, company);
    }


    @Test
    @DisplayName("Test equals() method - 2 objects with the same class but with different info")
    public void testEqualsMethodDifferentDenominationAndDescription() {

        // Arrange
        String account1Denomination = "Utilities";
        String account1Description = "Expenses with electricity, gas and water";
        String email1 = "jack@dorsey.com";

        String account2Denomination = "Paris";
        String account2Description = "Expenses with Paris travel";
        String email2 = "quentin@tarantino.com";

        PersonID personID1 = PersonID.createPersonID(email1);
        PersonID personID2 = PersonID.createPersonID(email2);

        // Act
        Account utilities = Account.createAccount(account1Description, account1Denomination, personID1);
        Account paris = Account.createAccount(account2Description, account2Denomination, personID2);

        //Assert
        assertNotEquals(utilities, paris);
    }


    @Test
    @DisplayName("Test equals() method - 2 different objects, different denomination")
    public void testEqualsMethodDifferentDenomination() {

        // Arrange
        String account1Denomination = "Utilities";
        String account1Description = "Expenses with electricity, gas and water";
        String email1 = "jack@dorsey.com";

        String account2Denomination = "Paris";
        String account2Description = "Expenses with electricity, gas and water";
        String email2 = "jack@dorsey.com";

        PersonID personID1 = PersonID.createPersonID(email1);
        PersonID personID2 = PersonID.createPersonID(email2);

        // Act
        Account utilities = Account.createAccount(account1Description, account1Denomination, personID1);
        Account paris = Account.createAccount(account2Description, account2Denomination, personID2);

        //Assert
        assertNotEquals(utilities, paris);
    }


    @Test
    @DisplayName("Test equals() method - 2 different objects, different description")
    public void testEqualsMethodDifferentDescription() {

        // Arrange
        String account1Denomination = "Utilities";
        String account1Description = "Expenses with electricity";
        String email1 = "jack@dorsey.com";

        String account2Denomination = "Utilities";
        String account2Description = "Expenses with gas and water";
        String email2 = "elon@musk.com";

        PersonID personID1 = PersonID.createPersonID(email1);
        PersonID personID2 = PersonID.createPersonID(email2);

        // Act
        Account utilities = Account.createAccount(account1Description, account1Denomination, personID1);
        Account moreUtilities = Account.createAccount(account2Description, account2Denomination, personID2);

        // Assert
        assertNotEquals(utilities, moreUtilities);
    }


    @Test
    @DisplayName("Test equals() method - 2 different objects")
    public void testEqualsMethodTwoDifferentObjects() {

        // Arrange
        String account1Denomination = "Utilities";
        String account1Description = "Expenses with electricity, gas and water";
        String email1 = "jack@dorsey.com";

        String account2Denomination = "Paris";
        String account2Description = "Expenses with trip to France";
        String email2 = "elon@musk.com";

        PersonID personID1 = PersonID.createPersonID(email1);
        PersonID personID2 = PersonID.createPersonID(email2);

        // Act
        Account utilities = Account.createAccount(account1Description, account1Denomination, personID1);
        Account paris = Account.createAccount(account2Description, account2Denomination, personID2);

        // Assert
        assertNotEquals(utilities, paris);
    }


    @Test
    @DisplayName("Test equals() method - 1 object doesn't have description and denomination")
    public void testEqualsMethodObjectWithoutDescriptionAndDenomination() {

        // Arrange
        String denomination = null;
        String description = null;
        String email = "jack@dorsey.com";

        PersonID personID = PersonID.createPersonID(email);

        // Act
        try {
            Account account = Account.createAccount(description, denomination, personID);
            assertTrue(false, "Account not created due to the fact that the denomination parameter hasn't a valid argument");
        } catch (Exception e) {
        }
    }

/*
    @Test
    @DisplayName("Test equals() method - 1 object doesn't have description")
    public void testEqualsMethodObjectNull() {

        // Arrange
        String denominationFirstAccount = "Spiral Duck";
        String descriptionFirstAccount = "Expenses with electricity, gas and water";
        String email = "elon@musk.com";

        String denominationSecondAccount = "Aquaman";
        String descriptionSecondAccount = null;
        String email2 = "jack@dorsey.com";

        PersonID personID1 = PersonID.createPersonID(email);
        PersonID personID2 = PersonID.createPersonID(email2);

        // Act
        Account firstAccount = Account.createAccountPerson(descriptionFirstAccount, denominationFirstAccount, personID1);
        Account nullAccount = Account.createAccountPerson(descriptionSecondAccount, denominationSecondAccount, personID2);

        // Assert
        assertNotEquals(nullAccount, firstAccount);

    }
    
 */

    @Test
    @DisplayName("Test the account constructor with two different blocks of info")
    public void testTheAccountConstructorTwoDifferentBlocksOfInfo() {

        // Arrange
        String description = "Operating Fund";
        String denomination = "Domestic expenses";
        String email = "elon@musk.com";

        String anotherDescription = "Films expenses";
        Denomination anotherDenomination = Denomination.createDenomination("Denomination");
        String anotherEmail = "jack@dorsey.com";

        PersonID personID1 = PersonID.createPersonID(email);
        PersonID personID2 = PersonID.createPersonID(anotherEmail);

        // Act
        Account account1 = Account.createAccount(description, denomination, personID1);

        // Assert
        assertNotEquals(denomination, anotherDenomination.getDenomination());
    }


    /*

    //--set methods
    @Test
    @DisplayName("Test for set() methods - set a different denomination, description and amount for a given account")
    public void setMethods_HappyCase() {
        //Arrange
        Denomination companyAccountDenomination = new Denomination("Spiral Duck");
        Description companyAccountDescription = new Description("Payments of freelance jobs done for the company Sipral Duck");
        Denomination newDenomination = new Denomination("Spiral Duck Lda.");
        Description newDescription = new Description("Payment of Project-A traveling expenses");

        //Act
        Account account1 = new Account(companyAccountDescription, companyAccountDenomination);

        account1.setDenomination(newDenomination);
        account1.setDescription(newDescription);

        //Assert
        assertEquals(account1.getDenomination(), newDenomination);
        assertEquals(account1.getDescription(), newDescription);
    }


*/
}

