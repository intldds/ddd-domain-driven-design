package com.finance.project.domainLayer.domainEntities.aggregates.ledger;

import com.finance.project.domainLayer.domainEntities.vosShared.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {
    /**
     * create test to Transaction as VO
     */

    // Test constructor method withDate

    @Test
    @DisplayName("Constructor")
    void checkConstructor() {

        //Arrange

        //Arrange CategoryID
        String denominationCat = "HairStylist";
        String email = "abc@gmail.com";
        PersonID personID = PersonID.createPersonID(email);
        CategoryID categoryID = CategoryID.createCategoryID(denominationCat, personID);

        String type = "debit";
        Type typeA = Type.createType(type);
        String description = "Pente0";
        Description descriptionA = Description.createDescription(description);
        double amount = 150.0;
        Amount amountA = Amount.createAmount(amount);
        LocalDate date = LocalDate.of(2010,12,25);
        Date dateA = Date.createDate(date);

        //Arrange CreditAccountID

        String denominationAccountC = "CredditAccountJon";
        AccountID creditAccountID = AccountID.createAccountID(denominationAccountC, personID);

        //Arrange DebittAccountID

        String denominationAccountD = "DebitAccountJon";
        AccountID debitAccountID = AccountID.createAccountID(denominationAccountC, personID);


        //Act

        Transaction transaction = Transaction.createTransaction(categoryID, type, description, amount, date, debitAccountID, creditAccountID);

        //Assert

        assertEquals(typeA, transaction.getType());
        assertEquals(descriptionA, transaction.getDescription());
        assertEquals(amountA, transaction.getAmount());
        assertEquals(dateA, transaction.getDate());
        assertEquals(categoryID, transaction.getCategoryID());
        assertEquals(debitAccountID, transaction.getDebitAccountID());
        assertEquals(creditAccountID, transaction.getCreditAccountID());


    }

    // Test constructor method withDate

    @Test
    @DisplayName("Constructor")
    void checkConstructor_withSystemDate() {

        //Arrange

        //Arrange CategoryID
        String denominationCat = "HairStylist";
        String email = "abc@gmail.com";
        PersonID personID = PersonID.createPersonID(email);
        CategoryID categoryID = CategoryID.createCategoryID(denominationCat, personID);

        String type = "debit";
        Type typeA = Type.createType(type);
        String description = "Pente0";
        Description descriptionA = Description.createDescription(description);
        double amount = 150.0;
        Amount amountA = Amount.createAmount(amount);
        LocalDate date = LocalDate.now();
        Date dateA = Date.createDate(LocalDate.now());

        //Arrange CreditAccountID

        String denominationAccountC = "CredditAccountJon";
        AccountID creditAccountID = AccountID.createAccountID(denominationAccountC, personID);

        //Arrange DebittAccountID

        String denominationAccountD = "DebitAccountJon";
        AccountID debitAccountID = AccountID.createAccountID(denominationAccountC, personID);


        //Act

        Transaction transaction = Transaction.createTransactionWithSystemDate(categoryID, type, description, amount, debitAccountID, creditAccountID);

        //Assert

        assertEquals(typeA, transaction.getType());
        assertEquals(descriptionA, transaction.getDescription());
        assertEquals(amountA, transaction.getAmount());
        assertEquals(dateA, transaction.getDate());
        assertEquals(categoryID, transaction.getCategoryID());
        assertEquals(debitAccountID, transaction.getDebitAccountID());
        assertEquals(creditAccountID, transaction.getCreditAccountID());


    }

    //Exceptions for method with date


    @Test
    @DisplayName("testExceptionCategoryIDNull")
    public void testExceptionCategoryIDNull() {

        //Arrange

        //Arrange CategoryID
        String denominationCat = "HairStylist";
        String email = "abc@gmail.com";
        PersonID personID = PersonID.createPersonID(email);
        CategoryID categoryID = CategoryID.createCategoryID(denominationCat, personID);

        String type = "debit";
        Type typeA = Type.createType(type);
        String description = "Pente0";
        Description descriptionA = Description.createDescription(description);
        double amount = 150.0;
        Amount amountA = Amount.createAmount(amount);
        LocalDate date = LocalDate.of(2010,12,25);
        Date dateA = Date.createDate(date);

        //Arrange CreditAccountID

        String denominationAccountC = "CredditAccountJon";
        AccountID creditAccountID = AccountID.createAccountID(denominationAccountC, personID);

        //Arrange DebittAccountID

        String denominationAccountD = "DebitAccountJon";
        AccountID debitAccountID = AccountID.createAccountID(denominationAccountC, personID);

        Transaction transaction = Transaction.createTransaction(categoryID, type, description, amount, date, debitAccountID, creditAccountID);


        try {
            Transaction.createTransaction (null, type, description, amount, date, debitAccountID, creditAccountID) ;
            assertTrue(false, "Transaction not created due to the fact that the categoryID parameter hasn't a valid argument");
        } catch (Exception e) {

        }
    }

    @Test
    @DisplayName("testExceptionTypeNull")
    public void testExceptionTypeNull() {

        //Arrange

        //Arrange CategoryID
        String denominationCat = "HairStylist";
        String email = "abc@gmail.com";
        PersonID personID = PersonID.createPersonID(email);
        CategoryID categoryID = CategoryID.createCategoryID(denominationCat, personID);

        String type = "debit";
        Type typeA = Type.createType(type);
        String description = "Pente0";
        Description descriptionA = Description.createDescription(description);
        double amount = 150.0;
        Amount amountA = Amount.createAmount(amount);
        LocalDate date = LocalDate.of(2010,12,25);
        Date dateA = Date.createDate(date);

        //Arrange CreditAccountID

        String denominationAccountC = "CredditAccountJon";
        AccountID creditAccountID = AccountID.createAccountID(denominationAccountC, personID);

        //Arrange DebittAccountID

        String denominationAccountD = "DebitAccountJon";
        AccountID debitAccountID = AccountID.createAccountID(denominationAccountC, personID);

        Transaction transaction = Transaction.createTransaction(categoryID, type, description, amount, date, debitAccountID, creditAccountID);


        try {
            Transaction.createTransaction (categoryID, null, description, amount, date, debitAccountID, creditAccountID) ;
            assertTrue(false, "Transaction not created due to the fact that the type parameter hasn't a valid argument");
        } catch (Exception e) {

        }
    }

    @Test
    @DisplayName("testExceptionDescriptionNull")
    public void testExceptionDescriptionNull() {

        //Arrange

        //Arrange CategoryID
        String denominationCat = "HairStylist";
        String email = "abc@gmail.com";
        PersonID personID = PersonID.createPersonID(email);
        CategoryID categoryID = CategoryID.createCategoryID(denominationCat, personID);

        String type = "debit";
        Type typeA = Type.createType(type);
        String description = "Pente0";
        Description descriptionA = Description.createDescription(description);
        double amount = 150.0;
        Amount amountA = Amount.createAmount(amount);
        LocalDate date = LocalDate.of(2010,12,25);
        Date dateA = Date.createDate(date);

        //Arrange CreditAccountID

        String denominationAccountC = "CredditAccountJon";
        AccountID creditAccountID = AccountID.createAccountID(denominationAccountC, personID);

        //Arrange DebittAccountID

        String denominationAccountD = "DebitAccountJon";
        AccountID debitAccountID = AccountID.createAccountID(denominationAccountC, personID);

        Transaction transaction = Transaction.createTransaction(categoryID, type, description, amount, date, debitAccountID, creditAccountID);


        try {
            Transaction.createTransaction (categoryID, type, null, amount, date, debitAccountID, creditAccountID) ;
            assertTrue(false, "Transaction not created due to the fact that the description parameter hasn't a valid argument");
        } catch (Exception e) {

        }
    }


    @Test
    @DisplayName("testExceptionDateNull")
    public void testExceptionDateNull() {

        //Arrange

        //Arrange CategoryID
        String denominationCat = "HairStylist";
        String email = "abc@gmail.com";
        PersonID personID = PersonID.createPersonID(email);
        CategoryID categoryID = CategoryID.createCategoryID(denominationCat, personID);

        String type = "debit";
        Type typeA = Type.createType(type);
        String description = "Pente0";
        Description descriptionA = Description.createDescription(description);
        double amount = 150.0;
        Amount amountA = Amount.createAmount(amount);
        LocalDate date = LocalDate.of(2010,12,25);
        Date dateA = Date.createDate(date);

        //Arrange CreditAccountID

        String denominationAccountC = "CredditAccountJon";
        AccountID creditAccountID = AccountID.createAccountID(denominationAccountC, personID);

        //Arrange DebittAccountID

        String denominationAccountD = "DebitAccountJon";
        AccountID debitAccountID = AccountID.createAccountID(denominationAccountC, personID);

        Transaction transaction = Transaction.createTransaction(categoryID, type, description, amount, date, debitAccountID, creditAccountID);


        try {
            Transaction.createTransaction (categoryID, type, description, amount, null, debitAccountID, creditAccountID) ;
            assertTrue(false, "Transaction not created due to the fact that the date parameter hasn't a valid argument");
        } catch (Exception e) {

        }
    }


    @Test
    @DisplayName("testExceptionDebitAccounIDNull")
    public void testExceptionDebitAccountIDNull() {

        //Arrange

        //Arrange CategoryID
        String denominationCat = "HairStylist";
        String email = "abc@gmail.com";
        PersonID personID = PersonID.createPersonID(email);
        CategoryID categoryID = CategoryID.createCategoryID(denominationCat, personID);

        String type = "debit";
        Type typeA = Type.createType(type);
        String description = "Pente0";
        Description descriptionA = Description.createDescription(description);
        double amount = 150.0;
        Amount amountA = Amount.createAmount(amount);
        LocalDate date = LocalDate.of(2010,12,25);
        Date dateA = Date.createDate(date);

        //Arrange CreditAccountID

        String denominationAccountC = "CredditAccountJon";
        AccountID creditAccountID = AccountID.createAccountID(denominationAccountC, personID);

        //Arrange DebittAccountID

        String denominationAccountD = "DebitAccountJon";
        AccountID debitAccountID = AccountID.createAccountID(denominationAccountC, personID);

        Transaction transaction = Transaction.createTransaction(categoryID, type, description, amount, date, debitAccountID, creditAccountID);


        try {
            Transaction.createTransaction (categoryID, type, description, amount, date, null, creditAccountID) ;
            assertTrue(false, "Transaction not created due to the fact that the debitAccountID parameter hasn't a valid argument");
        } catch (Exception e) {

        }
    }

    @Test
    @DisplayName("testExceptionCreditAccounIDNull")
    public void testExceptionCreditAccountIDNull() {

        //Arrange

        //Arrange CategoryID
        String denominationCat = "HairStylist";
        String email = "abc@gmail.com";
        PersonID personID = PersonID.createPersonID(email);
        CategoryID categoryID = CategoryID.createCategoryID(denominationCat, personID);

        String type = "debit";
        Type typeA = Type.createType(type);
        String description = "Pente0";
        Description descriptionA = Description.createDescription(description);
        double amount = 150.0;
        Amount amountA = Amount.createAmount(amount);
        LocalDate date = LocalDate.of(2010,12,25);
        Date dateA = Date.createDate(date);

        //Arrange CreditAccountID

        String denominationAccountC = "CredditAccountJon";
        AccountID creditAccountID = AccountID.createAccountID(denominationAccountC, personID);

        //Arrange DebittAccountID

        String denominationAccountD = "DebitAccountJon";
        AccountID debitAccountID = AccountID.createAccountID(denominationAccountC, personID);

        Transaction transaction = Transaction.createTransaction(categoryID, type, description, amount, date, debitAccountID, creditAccountID);


        try {
            Transaction.createTransaction (categoryID, type, description, amount, date, debitAccountID, null) ;
            assertTrue(false, "Transaction not created due to the fact that the creditAccountID parameter hasn't a valid argument");
        } catch (Exception e) {

        }
    }

    //Exceptions for method with systemDate


    @Test
    @DisplayName("testExceptionCategoryIDNull")
    public void testExceptionCategoryIDNullSD() {

        //Arrange

        //Arrange CategoryID
        String denominationCat = "HairStylist";
        String email = "abc@gmail.com";
        PersonID personID = PersonID.createPersonID(email);
        CategoryID categoryID = CategoryID.createCategoryID(denominationCat, personID);

        String type = "debit";
        Type typeA = Type.createType(type);
        String description = "Pente0";
        Description descriptionA = Description.createDescription(description);
        double amount = 150.0;
        Amount amountA = Amount.createAmount(amount);
        LocalDate date = LocalDate.of(2010,12,25);
        Date dateA = Date.createDate(date);

        //Arrange CreditAccountID

        String denominationAccountC = "CredditAccountJon";
        AccountID creditAccountID = AccountID.createAccountID(denominationAccountC, personID);

        //Arrange DebittAccountID

        String denominationAccountD = "DebitAccountJon";
        AccountID debitAccountID = AccountID.createAccountID(denominationAccountC, personID);



        try {
            Transaction.createTransactionWithSystemDate (null, type, description, amount, debitAccountID, creditAccountID) ;
            assertTrue(false, "Transaction not created due to the fact that the categoryID parameter hasn't a valid argument");
        } catch (Exception e) {

        }
    }

    @Test
    @DisplayName("testExceptionTypeNull")
    public void testExceptionTypeNullSD() {

        //Arrange

        //Arrange CategoryID
        String denominationCat = "HairStylist";
        String email = "abc@gmail.com";
        PersonID personID = PersonID.createPersonID(email);
        CategoryID categoryID = CategoryID.createCategoryID(denominationCat, personID);

        String type = "debit";
        Type typeA = Type.createType(type);
        String description = "Pente0";
        Description descriptionA = Description.createDescription(description);
        double amount = 150.0;
        Amount amountA = Amount.createAmount(amount);
        LocalDate date = LocalDate.of(2010,12,25);
        Date dateA = Date.createDate(date);

        //Arrange CreditAccountID

        String denominationAccountC = "CredditAccountJon";
        AccountID creditAccountID = AccountID.createAccountID(denominationAccountC, personID);

        //Arrange DebittAccountID

        String denominationAccountD = "DebitAccountJon";
        AccountID debitAccountID = AccountID.createAccountID(denominationAccountC, personID);



        try {
            Transaction.createTransactionWithSystemDate (categoryID, null, description, amount, debitAccountID, creditAccountID) ;
            assertTrue(false, "Transaction not created due to the fact that the type parameter hasn't a valid argument");
        } catch (Exception e) {

        }
    }

    @Test
    @DisplayName("testExceptionDescriptionNull")
    public void testExceptionDescriptionNullSD() {

        //Arrange

        //Arrange CategoryID
        String denominationCat = "HairStylist";
        String email = "abc@gmail.com";
        PersonID personID = PersonID.createPersonID(email);
        CategoryID categoryID = CategoryID.createCategoryID(denominationCat, personID);

        String type = "debit";
        Type typeA = Type.createType(type);
        String description = "Pente0";
        Description descriptionA = Description.createDescription(description);
        double amount = 150.0;
        Amount amountA = Amount.createAmount(amount);
        LocalDate date = LocalDate.of(2010,12,25);
        Date dateA = Date.createDate(date);

        //Arrange CreditAccountID

        String denominationAccountC = "CredditAccountJon";
        AccountID creditAccountID = AccountID.createAccountID(denominationAccountC, personID);

        //Arrange DebittAccountID

        String denominationAccountD = "DebitAccountJon";
        AccountID debitAccountID = AccountID.createAccountID(denominationAccountC, personID);



        try {
            Transaction.createTransactionWithSystemDate (categoryID, type, null, amount, debitAccountID, creditAccountID) ;
            assertTrue(false, "Transaction not created due to the fact that the description parameter hasn't a valid argument");
        } catch (Exception e) {

        }
    }


    @Test
    @DisplayName("testExceptionDebitAccounIDNull")
    public void testExceptionDebitAccountIDNullSD() {

        //Arrange

        //Arrange CategoryID
        String denominationCat = "HairStylist";
        String email = "abc@gmail.com";
        PersonID personID = PersonID.createPersonID(email);
        CategoryID categoryID = CategoryID.createCategoryID(denominationCat, personID);

        String type = "debit";
        Type typeA = Type.createType(type);
        String description = "Pente0";
        Description descriptionA = Description.createDescription(description);
        double amount = 150.0;
        Amount amountA = Amount.createAmount(amount);
        LocalDate date = LocalDate.of(2010,12,25);
        Date dateA = Date.createDate(date);

        //Arrange CreditAccountID

        String denominationAccountC = "CredditAccountJon";
        AccountID creditAccountID = AccountID.createAccountID(denominationAccountC, personID);

        //Arrange DebittAccountID

        String denominationAccountD = "DebitAccountJon";
        AccountID debitAccountID = AccountID.createAccountID(denominationAccountC, personID);


        try {
            Transaction.createTransactionWithSystemDate (categoryID, type, description, amount, null, creditAccountID) ;
            assertTrue(false, "Transaction not created due to the fact that the debitAccountID parameter hasn't a valid argument");
        } catch (Exception e) {

        }
    }

    @Test
    @DisplayName("testExceptionCreditAccounIDNull")
    public void testExceptionCreditAccountIDNullSD() {

        //Arrange

        //Arrange CategoryID
        String denominationCat = "HairStylist";
        String email = "abc@gmail.com";
        PersonID personID = PersonID.createPersonID(email);
        CategoryID categoryID = CategoryID.createCategoryID(denominationCat, personID);

        String type = "debit";
        Type typeA = Type.createType(type);
        String description = "Pente0";
        Description descriptionA = Description.createDescription(description);
        double amount = 150.0;
        Amount amountA = Amount.createAmount(amount);
        LocalDate date = LocalDate.of(2010,12,25);
        Date dateA = Date.createDate(date);

        //Arrange CreditAccountID

        String denominationAccountC = "CredditAccountJon";
        AccountID creditAccountID = AccountID.createAccountID(denominationAccountC, personID);

        //Arrange DebittAccountID

        String denominationAccountD = "DebitAccountJon";
        AccountID debitAccountID = AccountID.createAccountID(denominationAccountC, personID);



        try {
            Transaction.createTransactionWithSystemDate (categoryID, type, description, amount, debitAccountID, null) ;
            assertTrue(false, "Transaction not created due to the fact that the creditAccountID parameter hasn't a valid argument");
        } catch (Exception e) {

        }
    }

    //Test Equals

    @Test
    @DisplayName("Test Equals - HappyCase")
    void checkEquals_True() {

        //Arrange

        //Arrange CategoryID
        String denominationCat = "HairStylist";
        String email = "abc@gmail.com";
        PersonID personID = PersonID.createPersonID(email);
        CategoryID categoryID = CategoryID.createCategoryID(denominationCat, personID);

        String type = "debit";
        Type typeA = Type.createType(type);
        String description = "Pente0";
        Description descriptionA = Description.createDescription(description);
        double amount = 150.0;
        Amount amountA = Amount.createAmount(amount);
        LocalDate date = LocalDate.of(2010,12,25);
        Date dateA = Date.createDate(date);

        //Arrange CreditAccountID

        String denominationAccountC = "CredditAccountJon";
        AccountID creditAccountID = AccountID.createAccountID(denominationAccountC, personID);

        //Arrange DebittAccountID

        String denominationAccountD = "DebitAccountJon";
        AccountID debitAccountID = AccountID.createAccountID(denominationAccountC, personID);


        //Act

        Transaction transaction = Transaction.createTransaction(categoryID, type, description, amount, date, debitAccountID, creditAccountID);
        Transaction otherTransaction = Transaction.createTransaction(categoryID, type, description, amount, date, debitAccountID, creditAccountID);

        boolean result = transaction.equals(otherTransaction);

        //Assert

        assertEquals(true, result);

    }


    @Test
    @DisplayName("Test Equals - HappyCase- Same Object")
    void checkEquals_HappyCase_SameObject() {

        //Arrange

        //Arrange CategoryID
        String denominationCat = "HairStylist";
        String email = "abc@gmail.com";
        PersonID personID = PersonID.createPersonID(email);
        CategoryID categoryID = CategoryID.createCategoryID(denominationCat, personID);

        String type = "debit";
        Type typeA = Type.createType(type);
        String description = "Pente0";
        Description descriptionA = Description.createDescription(description);
        double amount = 150.0;
        Amount amountA = Amount.createAmount(amount);
        LocalDate date = LocalDate.of(2010,12,25);
        Date dateA = Date.createDate(date);

        //Arrange CreditAccountID

        String denominationAccountC = "CredditAccountJon";
        AccountID creditAccountID = AccountID.createAccountID(denominationAccountC, personID);

        //Arrange DebittAccountID

        String denominationAccountD = "DebitAccountJon";
        AccountID debitAccountID = AccountID.createAccountID(denominationAccountC, personID);


        //Act

        Transaction transaction = Transaction.createTransaction(categoryID, type, description, amount, date, debitAccountID, creditAccountID);

        boolean result = transaction.equals(transaction);

        //Assert

        assertEquals(true, result);

    }

    @Test
    @DisplayName("Test Equals - SadCase")
    void checkEquals_False_DifferentType() {

        //Arrange

        //Arrange CategoryID
        String denominationCat = "HairStylist";
        String email = "abc@gmail.com";
        PersonID personID = PersonID.createPersonID(email);
        CategoryID categoryID = CategoryID.createCategoryID(denominationCat, personID);

        String type = "debit";
        String typeOther = "credit";
        Type typeA = Type.createType(type);
        String description = "Pente0";
        Description descriptionA = Description.createDescription(description);
        double amount = 150.0;
        Amount amountA = Amount.createAmount(amount);
        LocalDate date = LocalDate.of(2010,12,25);
        Date dateA = Date.createDate(date);

        //Arrange CreditAccountID

        String denominationAccountC = "CredditAccountJon";
        AccountID creditAccountID = AccountID.createAccountID(denominationAccountC, personID);

        //Arrange DebittAccountID

        String denominationAccountD = "DebitAccountJon";
        AccountID debitAccountID = AccountID.createAccountID(denominationAccountC, personID);


        //Act

        Transaction transaction = Transaction.createTransaction(categoryID, type, description, amount, date, debitAccountID, creditAccountID);
        Transaction otherTransaction = Transaction.createTransaction(categoryID, typeOther, description, amount, date, debitAccountID, creditAccountID);

        boolean result = transaction.equals(otherTransaction);

        //Assert

        assertEquals(false, result);

    }

    @Test
    @DisplayName("Test Equals - SadCase")
    void checkEquals_FalseDifferentCategoryID() {

        //Arrange

        //Arrange CategoryID
        String denominationCat = "HairStylist";
        String email = "abc@gmail.com";
        PersonID personID = PersonID.createPersonID(email);
        CategoryID categoryID = CategoryID.createCategoryID(denominationCat, personID);

        //Arrange Other CategoryID
        String denominationCatOther = "HairStylist";
        String emailOther = "jjj@gmail.com";
        PersonID personIDOther = PersonID.createPersonID(emailOther);
        CategoryID categoryIDOther = CategoryID.createCategoryID(denominationCatOther, personIDOther);


        String type = "debit";
        Type typeA = Type.createType(type);
        String description = "Pente0";
        Description descriptionA = Description.createDescription(description);
        double amount = 150.0;
        Amount amountA = Amount.createAmount(amount);
        LocalDate date = LocalDate.of(2010,12,25);
        Date dateA = Date.createDate(date);

        //Arrange CreditAccountID

        String denominationAccountC = "CredditAccountJon";
        AccountID creditAccountID = AccountID.createAccountID(denominationAccountC, personID);

        //Arrange DebittAccountID

        String denominationAccountD = "DebitAccountJon";
        AccountID debitAccountID = AccountID.createAccountID(denominationAccountC, personID);


        //Act

        Transaction transaction = Transaction.createTransaction(categoryID, type, description, amount, date, debitAccountID, creditAccountID);
        Transaction otherTransaction = Transaction.createTransaction(categoryIDOther, type, description, amount, date, debitAccountID, creditAccountID);

        boolean result = transaction.equals(otherTransaction);

        //Assert

        assertEquals(false, result);

    }

    @Test
    @DisplayName("Test Equals - SadCase")
    void checkEquals_False_DifferentAmount() {

        //Arrange

        //Arrange CategoryID
        String denominationCat = "HairStylist";
        String email = "abc@gmail.com";
        PersonID personID = PersonID.createPersonID(email);
        CategoryID categoryID = CategoryID.createCategoryID(denominationCat, personID);

        String type = "debit";
        Type typeA = Type.createType(type);
        String description = "Pente0";
        Description descriptionA = Description.createDescription(description);
        double amount = 150.0;
        double amountOther = 100.0;
        Amount amountA = Amount.createAmount(amount);
        LocalDate date = LocalDate.of(2010,12,25);
        Date dateA = Date.createDate(date);

        //Arrange CreditAccountID

        String denominationAccountC = "CredditAccountJon";
        AccountID creditAccountID = AccountID.createAccountID(denominationAccountC, personID);

        //Arrange DebittAccountID

        String denominationAccountD = "DebitAccountJon";
        AccountID debitAccountID = AccountID.createAccountID(denominationAccountC, personID);


        //Act

        Transaction transaction = Transaction.createTransaction(categoryID, type, description, amount, date, debitAccountID, creditAccountID);
        Transaction otherTransaction = Transaction.createTransaction(categoryID, type, description, amountOther, date, debitAccountID, creditAccountID);

        boolean result = transaction.equals(otherTransaction);

        //Assert

        assertEquals(false, result);

    }


    @Test
    @DisplayName("Test Equals - NotInstanceOf")
    void checkEquals_NotInstanceOf() {

        //Arrange

        //Arrange CategoryID
        String denominationCat = "HairStylist";
        String email = "abc@gmail.com";
        PersonID personID = PersonID.createPersonID(email);
        CategoryID categoryID = CategoryID.createCategoryID(denominationCat, personID);

        String type = "debit";
        Type typeA = Type.createType(type);
        String description = "Pente0";
        Description descriptionA = Description.createDescription(description);
        double amount = 150.0;
        Amount amountA = Amount.createAmount(amount);
        LocalDate date = LocalDate.of(2010,12,25);
        Date dateA = Date.createDate(date);

        //Arrange CreditAccountID

        String denominationAccountC = "CredditAccountJon";
        AccountID creditAccountID = AccountID.createAccountID(denominationAccountC, personID);

        //Arrange DebittAccountID

        String denominationAccountD = "DebitAccountJon";
        AccountID debitAccountID = AccountID.createAccountID(denominationAccountC, personID);


        //Act

        Transaction transaction = Transaction.createTransaction(categoryID, type, description, amount, date, debitAccountID, creditAccountID);
        Transaction otherTransaction = Transaction.createTransaction(categoryID, type, description, amount, date, debitAccountID, creditAccountID);

        boolean result = transaction.equals(typeA);

        //Assert

        assertEquals(false, result);

    }


    @Test
    @DisplayName("Test Equals - SadCase")
    void checkEquals_False_DifferentDescription() {

        //Arrange

        //Arrange CategoryID
        String denominationCat = "HairStylist";
        String email = "abc@gmail.com";
        PersonID personID = PersonID.createPersonID(email);
        CategoryID categoryID = CategoryID.createCategoryID(denominationCat, personID);

        String type = "debit";
        Type typeA = Type.createType(type);
        String description = "Pente0";
        String descriptionOther = "noHAIR";
        Description descriptionA = Description.createDescription(description);
        double amount = 150.0;
        Amount amountA = Amount.createAmount(amount);
        LocalDate date = LocalDate.of(2010,12,25);
        Date dateA = Date.createDate(date);

        //Arrange CreditAccountID

        String denominationAccountC = "CredditAccountJon";
        AccountID creditAccountID = AccountID.createAccountID(denominationAccountC, personID);

        //Arrange DebittAccountID

        String denominationAccountD = "DebitAccountJon";
        AccountID debitAccountID = AccountID.createAccountID(denominationAccountC, personID);


        //Act

        Transaction transaction = Transaction.createTransaction(categoryID, type, description, amount, date, debitAccountID, creditAccountID);
        Transaction otherTransaction = Transaction.createTransaction(categoryID, type, descriptionOther, amount, date, debitAccountID, creditAccountID);

        boolean result = transaction.equals(otherTransaction);

        //Assert

        assertEquals(false, result);

    }

    @Test
    @DisplayName("Test Equals - SadCase")
    void checkEquals_FalseDifferentDate() {

        //Arrange

        //Arrange CategoryID
        String denominationCat = "HairStylist";
        String email = "abc@gmail.com";
        PersonID personID = PersonID.createPersonID(email);
        CategoryID categoryID = CategoryID.createCategoryID(denominationCat, personID);

        String type = "debit";
        Type typeA = Type.createType(type);
        String description = "Pente0";
        Description descriptionA = Description.createDescription(description);
        double amount = 150.0;
        Amount amountA = Amount.createAmount(amount);
        LocalDate date = LocalDate.of(2010,12,25);
        LocalDate dateOther = LocalDate.of(2011,12,25);
        Date dateA = Date.createDate(date);

        //Arrange CreditAccountID

        String denominationAccountC = "CredditAccountJon";
        AccountID creditAccountID = AccountID.createAccountID(denominationAccountC, personID);

        //Arrange DebittAccountID

        String denominationAccountD = "DebitAccountJon";
        AccountID debitAccountID = AccountID.createAccountID(denominationAccountC, personID);


        //Act

        Transaction transaction = Transaction.createTransaction(categoryID, type, description, amount, date, debitAccountID, creditAccountID);
        Transaction otherTransaction = Transaction.createTransaction(categoryID, type, description, amount, dateOther, debitAccountID, creditAccountID);

        boolean result = transaction.equals(otherTransaction);

        //Assert

        assertEquals(false, result);

    }

    @Test
    @DisplayName("Test Equals - SadCase")
    void checkEquals_False_DifferentDebitAccount() {

        //Arrange

        //Arrange CategoryID
        String denominationCat = "HairStylist";
        String email = "abc@gmail.com";
        PersonID personID = PersonID.createPersonID(email);
        CategoryID categoryID = CategoryID.createCategoryID(denominationCat, personID);

        String type = "debit";
        Type typeA = Type.createType(type);
        String description = "Pente0";
        Description descriptionA = Description.createDescription(description);
        double amount = 150.0;
        Amount amountA = Amount.createAmount(amount);
        LocalDate date = LocalDate.of(2010,12,25);
        Date dateA = Date.createDate(date);

        //Arrange CreditAccountID

        String denominationAccountC = "CredditAccountJon";
        AccountID creditAccountID = AccountID.createAccountID(denominationAccountC, personID);

        //Arrange DebittAccountID

        String denominationAccountD = "DebitAccountJon";
        String denominationAccountIDOther = "DebitAccountAna";
        AccountID debitAccountID = AccountID.createAccountID(denominationAccountC, personID);
        AccountID debitAccountIDOther = AccountID.createAccountID(denominationAccountIDOther, personID);


        //Act

        Transaction transaction = Transaction.createTransaction(categoryID, type, description, amount, date, debitAccountID, creditAccountID);
        Transaction otherTransaction = Transaction.createTransaction(categoryID, type, description, amount, date, debitAccountIDOther, creditAccountID);

        boolean result = transaction.equals(otherTransaction);

        //Assert

        assertEquals(false, result);

    }
    @Test
    @DisplayName("Test Equals - SadCase")
    void checkEquals_DifferentCreditAccount() {

        //Arrange

        //Arrange CategoryID
        String denominationCat = "HairStylist";
        String email = "abc@gmail.com";
        PersonID personID = PersonID.createPersonID(email);
        CategoryID categoryID = CategoryID.createCategoryID(denominationCat, personID);

        String type = "debit";
        Type typeA = Type.createType(type);
        String description = "Pente0";
        Description descriptionA = Description.createDescription(description);
        double amount = 150.0;
        Amount amountA = Amount.createAmount(amount);
        LocalDate date = LocalDate.of(2010,12,25);
        Date dateA = Date.createDate(date);

        //Arrange CreditAccountID

        String denominationAccountC = "CredditAccountJon";
        String denominationAccountCOther = "CredditAccountAna";
        AccountID creditAccountID = AccountID.createAccountID(denominationAccountC, personID);
        AccountID creditAccountIDOther = AccountID.createAccountID(denominationAccountCOther, personID);

        //Arrange DebittAccountID

        String denominationAccountD = "DebitAccountJon";
        AccountID debitAccountID = AccountID.createAccountID(denominationAccountC, personID);


        //Act

        Transaction transaction = Transaction.createTransaction(categoryID, type, description, amount, date, debitAccountID, creditAccountID);
        Transaction otherTransaction = Transaction.createTransaction(categoryID, type, description, amount, date, debitAccountID, creditAccountIDOther);

        boolean result = transaction.equals(otherTransaction);

        //Assert

        assertEquals(false, result);
        assertFalse(result);

    }



    //Hashcode

    @Test
    @DisplayName("Test HashCode - HappyCase")
    void checkHashCode_True() {

        //Arrange

        //Arrange CategoryID
        String denominationCat = "HairStylist";
        String email = "abc@gmail.com";
        PersonID personID = PersonID.createPersonID(email);
        CategoryID categoryID = CategoryID.createCategoryID(denominationCat, personID);

        String type = "debit";
        Type typeA = Type.createType(type);
        String description = "Pente0";
        Description descriptionA = Description.createDescription(description);
        double amount = 150.0;
        Amount amountA = Amount.createAmount(amount);
        LocalDate date = LocalDate.of(2010,12,25);
        Date dateA = Date.createDate(date);

        //Arrange CreditAccountID

        String denominationAccountC = "CredditAccountJon";
        AccountID creditAccountID = AccountID.createAccountID(denominationAccountC, personID);

        //Arrange DebittAccountID

        String denominationAccountD = "DebitAccountJon";
        AccountID debitAccountID = AccountID.createAccountID(denominationAccountC, personID);


        //Act

        Transaction transaction = Transaction.createTransaction(categoryID, type, description, amount, date, debitAccountID, creditAccountID);
        Transaction otherTransaction = Transaction.createTransaction(categoryID, type, description, amount, date, debitAccountID, creditAccountID);


        boolean result = transaction.hashCode()==transaction.hashCode();

        //Assert

        assertEquals(true, result);
        assertTrue(transaction.hashCode()==otherTransaction.hashCode());
        assertTrue(transaction.hashCode()==transaction.hashCode());

    }
    @Test
    @DisplayName("Test HashCode - SadCase")
    void checkHashCode_True_DifferentObjects() {

        //Arrange

        //Arrange Transaction
        //Arrange CategoryID
        String denominationCat = "HairStylist";
        String email = "abc@gmail.com";
        PersonID personID = PersonID.createPersonID(email);
        CategoryID categoryID = CategoryID.createCategoryID(denominationCat, personID);

        String type = "debit";
        String description = "Pente0";
        double amount = 150.0;
        LocalDate date = LocalDate.of(2010,12,25);

        //Arrange CreditAccountID

        String denominationAccountC = "CredditAccountJon";
        AccountID creditAccountID = AccountID.createAccountID(denominationAccountC, personID);

        //Arrange DebittAccountID

        String denominationAccountD = "DebitAccountJon";
        AccountID debitAccountID = AccountID.createAccountID(denominationAccountC, personID);

        //Arrange TransactionOther

        //Arrange CategoryID
        String denominationCatOther = "HairStylist";
        String emailOther = "abc@gmail.com";
        PersonID personIDOther = PersonID.createPersonID(emailOther);
        CategoryID categoryIDOther = CategoryID.createCategoryID(denominationCatOther, personIDOther);

        String typeOther = "debit";
        String descriptionOther = "Pente0";
        double amountOther = 150.0;
        LocalDate dateOther = LocalDate.of(2010,12,25);

        //Arrange CreditAccountID

        String denominationAccountCOther = "CredditAccountJon";
        AccountID creditAccountIDOther = AccountID.createAccountID(denominationAccountCOther, personIDOther);

        //Arrange DebittAccountID

        String denominationAccountDOther = "DebitAccountJon";
        AccountID debitAccountIDOther = AccountID.createAccountID(denominationAccountDOther, personIDOther);


        //Act

        Transaction transaction = Transaction.createTransaction(categoryID, type, description, amount, date, debitAccountID, creditAccountID);
        Transaction otherTransaction = Transaction.createTransaction(categoryIDOther, typeOther, descriptionOther, amountOther, dateOther, debitAccountIDOther, creditAccountIDOther);


        boolean result = transaction.hashCode()==otherTransaction.hashCode();

        //Assert

        assertEquals(false, result);
        assertFalse(transaction.hashCode()==otherTransaction.hashCode());

    }




    @Test
    @DisplayName("Test HashCode - HappyCase")
    void checkHashCode_False() {

        //Arrange

        //Arrange CategoryID
        String denominationCat = "HairStylist";
        String email = "abc@gmail.com";
        PersonID personID = PersonID.createPersonID(email);
        CategoryID categoryID = CategoryID.createCategoryID(denominationCat, personID);

        String type = "debit";
        Type typeA = Type.createType(type);
        String description = "Pente0";
        Description descriptionA = Description.createDescription(description);
        double amount = 150.0;
        Amount amountA = Amount.createAmount(amount);
        LocalDate date = LocalDate.of(2010,12,25);
        Date dateA = Date.createDate(date);

        //Arrange CreditAccountID

        String denominationAccountC = "CredditAccountJon";
        AccountID creditAccountID = AccountID.createAccountID(denominationAccountC, personID);

        //Arrange DebittAccountID

        String denominationAccountD = "DebitAccountJon";
        AccountID debitAccountID = AccountID.createAccountID(denominationAccountC, personID);


        //Act

        Transaction transaction = Transaction.createTransaction(categoryID, type, description, amount, date, debitAccountID, creditAccountID);
        Transaction otherTransaction = Transaction.createTransaction(categoryID, type, description, amount, date, debitAccountID, creditAccountID);



        //Assert

        assertFalse(transaction.hashCode()==debitAccountID.hashCode());

    }

/*
    // Test constructor method

    @Test
    @DisplayName("Constructor")
    void checkConstructor() {

        // Arrange
        Category category = new Category("Haircut");
        String type = "true";
        String description = "Pente0";
        double amount = 150.0;
        LocalDate date = LocalDate.of(2010,12,25);
        //Arrange of Debit Account
        Description debAccountDescription = new Description("EmployerSA Account");
        Denomination debAccountDenomination = new Denomination("EmployerSA");

        Account debitAccount = new Account(debAccountDescription, debAccountDenomination);

        //Arrange of Credit Account
        Description credAccountDescription = new Description("Margarida Salary Account");
        Denomination credAccountDenomination = new Denomination("Salary");

        Account creditAccount = new Account(credAccountDescription, credAccountDenomination);

        // Act
        Transaction transaction = new Transaction(category, type, description, amount, date, debitAccount, creditAccount);

        // Assert
        assertEquals(category, transaction.getCategory());
        assertEquals(type, transaction.getType());
        assertEquals(description, transaction.getDescription());
        assertEquals(amount, transaction.getAmount());
        assertEquals(date, transaction.getDate());
        assertEquals(debitAccount, transaction.getDebitAccount());
        assertEquals(creditAccount, transaction.getCreditAccount());
    }


    @Test
    @DisplayName("Constructor")
    void checkConstructorWithSysDate() {

        // Arrange
        Category category = new Category("Haircut");
        String type = "true";
        String description = "Pente0";
        double amount = 150.0;
        LocalDate date = LocalDate.now();

        //Arrange of Debit Account
        Description debAccountDescription = new Description("EmployerSA Account");
        Denomination debAccountDenomination = new Denomination("EmployerSA");

        Account debitAccount = new Account(debAccountDescription, debAccountDenomination);

        //Arrange of Credit Account
        Description credAccountDescription = new Description("Margarida Salary Account");
        Denomination credAccountDenomination = new Denomination("Salary");

        Account creditAccount = new Account(credAccountDescription, credAccountDenomination);

        // Act
        Transaction transaction = new Transaction(category, type, description, amount, debitAccount, creditAccount);

        // Assert
        assertEquals(category, transaction.getCategory());
        assertEquals(type, transaction.getType());
        assertEquals(description, transaction.getDescription());
        assertEquals(amount, transaction.getAmount());
        assertEquals(date, transaction.getDate());
        assertEquals(debitAccount, transaction.getDebitAccount());
        assertEquals(creditAccount, transaction.getCreditAccount());

    }

    // Test get and set methods

    @Test
    @DisplayName("Get category")
    void getCategory() {

        // Arrange
        Category category = new Category("Haircut");
        String type = "true";
        String description = "Pente0";
        double amount = 150.0;
        LocalDate date = LocalDate.of(2010,12,25);;
        //Arrange of Debit Account
        Description debAccountDescription = new Description("EmployerSA Account");
        Denomination debAccountDenomination = new Denomination("EmployerSA");

        Account debitAccount = new Account(debAccountDescription, debAccountDenomination);

        //Arrange of Credit Account
        Description credAccountDescription = new Description("Margarida Salary Account");
        Denomination credAccountDenomination = new Denomination("Salary");

        Account creditAccount = new Account(credAccountDescription, credAccountDenomination);

        // Act
        Transaction transaction = new Transaction(category, type, description, amount, date, debitAccount, creditAccount);
        Category result = transaction.getCategory();

        // Assert
        assertEquals(category, result);
    }

    @Test
    @DisplayName("Get Type returns true")
    void getTypeTrue() {

        // Arrange
        Category category = new Category("Haircut");
        String type = "true";
        String description = "Haircut description";
        double amount = 120.0;
        LocalDate date = LocalDate.of(2010,12,25);

        //Arrange of Debit Account
        Description debAccountDescription = new Description("EmployerSA Account");
        Denomination debAccountDenomination = new Denomination("EmployerSA");

        Account debitAccount = new Account(debAccountDescription, debAccountDenomination);

        //Arrange of Credit Account
        Description credAccountDescription = new Description("Margarida Salary Account");
        Denomination credAccountDenomination = new Denomination("Salary");

        Account creditAccount = new Account(credAccountDescription, credAccountDenomination);

        // Act
        Transaction transaction = new Transaction(category, type, description, amount, date, debitAccount, creditAccount);
        transaction.setType("true");
        String result = transaction.getType();

        // Assert
        assertEquals("true", result);
    }


    @Test
    @DisplayName("Get description")
    void getDescription() {

        // Arrange
        Category category = new Category("Haircut");
        String type = "true";
        String description = "Pente0";
        double amount = 150.0;
        LocalDate date = LocalDate.of(2010,12,25);

        //Arrange of Debit Account
        Description debAccountDescription = new Description("EmployerSA Account");
        Denomination debAccountDenomination = new Denomination("EmployerSA");

        Account debitAccount = new Account(debAccountDescription, debAccountDenomination);

        //Arrange of Credit Account
        Description credAccountDescription = new Description("Margarida Salary Account");
        Denomination credAccountDenomination = new Denomination("Salary");

        Account creditAccount = new Account(credAccountDescription, credAccountDenomination);

        // Act
        Transaction transaction = new Transaction(category, type, description, amount, date, debitAccount, creditAccount);
        transaction.setDescription("Pente0");
        String result = transaction.getDescription();

        // Assert
        assertEquals("Pente0", result);
    }


    @Test
    @DisplayName("Verify if getAmount method is correct")
    void getAmount() {

        // Arrange
        Category category = new Category("Haircut");
        String type = "true";
        String description = "Pente0";
        double amount = 150.0;
        LocalDate date = LocalDate.of(2010,12,25);

        //Arrange of Debit Account
        Description debAccountDescription = new Description("EmployerSA Account");
        Denomination debAccountDenomination = new Denomination("EmployerSA");

        Account debitAccount = new Account(debAccountDescription, debAccountDenomination);

        //Arrange of Credit Account
        Description credAccountDescription = new Description("Margarida Salary Account");
        Denomination credAccountDenomination = new Denomination("Salary");

        Account creditAccount = new Account(credAccountDescription, credAccountDenomination);

        // Act
        Transaction transaction = new Transaction(category, type, description, amount, date, debitAccount, creditAccount);
        transaction.setAmount(150.0);
        double result = transaction.getAmount();

        // Assert
        assertEquals(150.0, result);
    }


    @Test
    @DisplayName("Verify if getDate method is correct")
    void getDate() {

        // Arrange
        Category category = new Category("Haircut");
        String type = "true";
        String description = "Pente0";
        double amount = 150.0;
        LocalDate date = LocalDate.of(2010,12,25);

        //Arrange of Debit Account
        Description debAccountDescription = new Description("EmployerSA Account");
        Denomination debAccountDenomination = new Denomination("EmployerSA");

        Account debitAccount = new Account(debAccountDescription, debAccountDenomination);

        //Arrange of Credit Account
        Description credAccountDescription = new Description("Margarida Salary Account");
        Denomination credAccountDenomination = new Denomination("Salary");

        Account creditAccount = new Account(credAccountDescription, credAccountDenomination);

        // Act
        Transaction transaction = new Transaction(category, type, description, amount, date, debitAccount, creditAccount);
        LocalDate result = transaction.getDate();

        // Assert
        assertEquals(date, result);
    }


    // Test clone

    @Test
    @DisplayName("Check a clone")
    public void makeAClone() {
        // Arrange
        Category category = new Category("Haircut");
        String type = "true";
        String description = "Pente0";
        double amount = 150.0;
        LocalDate date = LocalDate.of(2010,12,25);

        //Arrange of Debit Account
        Description debAccountDescription = new Description("EmployerSA Account");
        Denomination debAccountDenomination = new Denomination("EmployerSA");

        Account debitAccount = new Account(debAccountDescription, debAccountDenomination);

        //Arrange of Credit Account
        Description credAccountDescription = new Description("Margarida Salary Account");
        Denomination credAccountDenomination = new Denomination("Salary");

        Account creditAccount = new Account(credAccountDescription, credAccountDenomination);

        // Act
        Transaction transaction = new Transaction(category, type, description, amount, date, debitAccount, creditAccount);
        Transaction transaction2 = transaction.clone();

        // Assert
        assertEquals(transaction, transaction2);
    }

    @Test
    @DisplayName("Check a clone - With Category null")
    public void makeACloneCategoryNull() {
        // Arrange
        Category category = null;
        String type = "true";
        String description = "Pente0";
        double amount = 150.0;
        LocalDate date = LocalDate.of(2010,12,25);

        //Arrange of Debit Account
        Description debAccountDescription = new Description("EmployerSA Account");
        Denomination debAccountDenomination = new Denomination("EmployerSA");

        Account debitAccount = new Account(debAccountDescription, debAccountDenomination);

        //Arrange of Credit Account
        Description credAccountDescription = new Description("Margarida Salary Account");
        Denomination credAccountDenomination = new Denomination("Salary");

        Account creditAccount = new Account(credAccountDescription, credAccountDenomination);

        // Act
        Transaction transaction = new Transaction(category, type, description, amount, date, debitAccount, creditAccount);
        Transaction transaction2 = transaction.clone();

        // Assert
        assertEquals(transaction, transaction2);
    }

    @Test
    @DisplayName("Check a clone - With DebitAccount null")
    public void makeACloneDebitAccountNull() {
        // Arrange
        Category category = new Category("Haircut");
        String type = "true";
        String description = "Pente0";
        double amount = 150.0;
        LocalDate date = LocalDate.of(2010,12,25);

        Account debitAccount = null;

        //Arrange of Credit Account
        Description credAccountDescription = new Description("Margarida Salary Account");
        Denomination credAccountDenomination = new Denomination("Salary");

        Account creditAccount = new Account(credAccountDescription, credAccountDenomination);
        // Act
        Transaction transaction = new Transaction(category, type, description, amount, date, debitAccount, creditAccount);
        Transaction transaction2 = transaction.clone();

        // Assert
        assertEquals(transaction, transaction2);
    }

    @Test
    @DisplayName("Check a clone - With CreditAccount null")
    public void makeACloneCreditAccountNull() {
        // Arrange
        Category category = new Category("Haircut");
        String type = "true";
        String description = "Pente0";
        double amount = 150.0;
        LocalDate date = LocalDate.of(2010,12,25);
        //Arrange of Debit Account
        Description debAccountDescription = new Description("EmployerSA Account");
        Denomination debAccountDenomination = new Denomination("EmployerSA");

        Account debitAccount = new Account(debAccountDescription, debAccountDenomination);

        Account creditAccount = null;

        // Act
        Transaction transaction = new Transaction(category, type, description, amount, date, debitAccount, creditAccount);
        Transaction transaction2 = transaction.clone();

        // Assert
        assertEquals(transaction, transaction2);
    }


    //Tests for equals() and hashCode() methods.

    @Test
    @DisplayName("Equals: Verify if two schedulings are the same | Case true - Same Parameters")
    void testEqualsSameParameters() {
        // Arrange
        Category category1 = new Category("Haircut");
        Category category2 = new Category("Haircut");
        String type1 = "true", type2 = "true";
        String description1 = "Pente0", description2 = "Pente0";
        double amount1 = 150.0, amount2 = 150.0;
        LocalDate date1 = LocalDate.of(2010,12,25), date2 = LocalDate.of(2010,12,25);

        //Arrange of Debit Account
        Description debAccountDescription = new Description("EmployerSA Account");
        Denomination debAccountDenomination = new Denomination("EmployerSA");

        Account debitAccount = new Account(debAccountDescription, debAccountDenomination);

        //Arrange of Credit Account
        Description credAccountDescription = new Description("Margarida Salary Account");
        Denomination credAccountDenomination = new Denomination("Salary");

        Account creditAccount = new Account(credAccountDescription, credAccountDenomination);

        // Act
        Transaction transaction1 = new Transaction(category1, type1, description1, amount1, date1, debitAccount, creditAccount);
        Transaction transaction2 = new Transaction(category2, type2, description2, amount2, date2, debitAccount, creditAccount);

        // Assert
        assertEquals(transaction1, transaction2);
    }

    @Test
    @DisplayName("Equals: Verify if two schedulings are the same | Case true - Object Null")
    void testEqualsObjectNull() {
        // Arrange
        Category category1 = new Category("Haircut");
        Category category2 = new Category("Haircut");
        String type1 = "true", type2 = "true";
        String description1 = "Pente0", description2 = "Pente0";
        double amount1 = 150.0, amount2 = 150.0;
        LocalDate date1 = LocalDate.of(2010,12,25), date2 = LocalDate.of(2010,12,25);

        //Arrange of Debit Account
        Description debAccountDescription = new Description("EmployerSA Account");
        Denomination debAccountDenomination = new Denomination("EmployerSA");

        Account debitAccount = new Account(debAccountDescription, debAccountDenomination);

        //Arrange of Credit Account
        Description credAccountDescription = new Description("Margarida Salary Account");
        Denomination credAccountDenomination = new Denomination("Salary");

        Account creditAccount = new Account(credAccountDescription, credAccountDenomination);

        // Act
        Transaction transaction1 = null;
        Transaction transaction2 = new Transaction(category2, type2, description2, amount2, date2, debitAccount, creditAccount);

        // Assert
        assertNotEquals(transaction1, transaction2);
    }

    @Test
    @DisplayName("Equals: Verify if two schedulings are the same | Case true - Different Get Class")
    void testEqualsDifferentGetClass() {
        // Arrange
        Category category1 = new Category("Haircut");
        Category category2 = new Category("Haircut");
        String type1 = "true", type2 = "true";
        String description1 = "Pente0", description2 = "Pente0";
        double amount1 = 150.0, amount2 = 150.0;
        LocalDate date1 = LocalDate.of(2010,12,25), date2 = LocalDate.of(2010,12,25);

        //Arrange of Debit Account
        Description debAccountDescription = new Description("EmployerSA Account");
        Denomination debAccountDenomination = new Denomination("EmployerSA");

        Account debitAccount = new Account(debAccountDescription, debAccountDenomination);

        //Arrange of Credit Account
        Description credAccountDescription = new Description("Margarida Salary Account");
        Denomination credAccountDenomination = new Denomination("Salary");

        Account creditAccount = new Account(credAccountDescription, credAccountDenomination);

        // Act
        Transaction transaction1 = new Transaction(category1, type1, description1, amount1, date1, debitAccount, creditAccount);
        Transaction transaction2 = new Transaction(category2, type2, description2, amount2, date2, debitAccount, creditAccount);

        // Assert
        assertNotEquals(transaction1, category2);
    }

    @Test
    @DisplayName("Equals: Verify if two schedulings are the same | Case true - Same Memory Position")
    void testEqualsSameMemoryPosition() {
        // Arrange
        Category category = new Category("Haircut");
        String type = "true";
        String description = "Pente0";
        double amount = 150.0;
        LocalDate date = LocalDate.of(2010,12,25);

        //Arrange of Debit Account
        Description debAccountDescription = new Description("EmployerSA Account");
        Denomination debAccountDenomination = new Denomination("EmployerSA");

        Account debitAccount = new Account(debAccountDescription, debAccountDenomination);

        //Arrange of Credit Account
        Description credAccountDescription = new Description("Margarida Salary Account");
        Denomination credAccountDenomination = new Denomination("Salary");

        Account creditAccount = new Account(credAccountDescription, credAccountDenomination);

        // Act
        Transaction transaction = new Transaction(category, type, description, amount, date, debitAccount, creditAccount);

        // Assert
        assertEquals(transaction, transaction);
    }

    @Test
    @DisplayName("Equals: Verify if two schedulings are the same | Case false")
    void testNotEquals() {
        // Arrange
        Category category1 = new Category("Haircut");
        Category category2 = new Category("Haircut");
        String type1 = "true", type2 = "true";
        String description1 = "Pente0", description2 = "Carrots";
        double amount1 = 150.0, amount2 = 150.0;
        LocalDate date1 = LocalDate.of(2010,12,25), date2 = LocalDate.of(2010,12,25);

        //Arrange of Debit Account
        Description debAccountDescription = new Description("EmployerSA Account");
        Denomination debAccountDenomination = new Denomination("EmployerSA");

        Account debitAccount = new Account(debAccountDescription, debAccountDenomination);

        //Arrange of Credit Account
        Description credAccountDescription = new Description("Margarida Salary Account");
        Denomination credAccountDenomination = new Denomination("Salary");

        Account creditAccount = new Account(credAccountDescription, credAccountDenomination);

        // Act
        Transaction transaction1 = new Transaction(category1, type1, description1, amount1, date1, debitAccount, creditAccount);
        Transaction transaction2 = new Transaction(category2, type2, description2, amount2, date2, debitAccount, creditAccount);

        // Assert
        assertNotEquals(transaction1, transaction2);
    }


    @Test
    @DisplayName("Verify if two transactions have the same hascode | Not same hashcode")
    void testHashCode() {
        // Arrange
        Category category1 = new Category("Haircut");
        Category category2 = new Category("Grossery");
        String type1 = "true", type2 = "true";
        String description1 = "Pente0", description2 = "Carrots";
        double amount1 = 150.0, amount2 = 150.0;
        LocalDate date1 = LocalDate.of(2010,12,25), date2 = LocalDate.of(2010,12,25);

        //Arrange of Debit Account
        Description debAccountDescription = new Description("EmployerSA Account");
        Denomination debAccountDenomination = new Denomination("EmployerSA");

        Account debitAccount = new Account(debAccountDescription, debAccountDenomination);

        //Arrange of Credit Account
        Description credAccountDescription = new Description("Margarida Salary Account");
        Denomination credAccountDenomination = new Denomination("Salary");

        Account creditAccount = new Account(credAccountDescription, credAccountDenomination);

        // Act
        Transaction transaction1 = new Transaction(category1, type1, description1, amount1, date1, debitAccount, creditAccount);
        Transaction transaction2 = new Transaction(category2, type2, description2, amount2, date2, debitAccount, creditAccount);

        // Assert
        assertEquals(false, transaction1.hashCode() == transaction2.hashCode());

    }

*/
}