package com.finance.project.domainLayer.domainEntities.vosShared;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * @author Ala Matos
 */
class TransactionTypeTest {
    @Test
    @DisplayName("Test of the constructor transaction type VO")
    public void testOfTheTransactionTypeVO() {
        //Arrange
        String testingTransactionType = "Groceries";
        //Act
        TransactionType firstTransactionType = TransactionType.createTransactionType(testingTransactionType);
        //Assert
        assertEquals(testingTransactionType, firstTransactionType.getTransactionType());
    }

    @Test
    @DisplayName("Test the Equals TransactionTypeVO || Happy Case")
    public void testEqualsTransactionTypeVO() {

        //Expected
        TransactionType transactionTypeTest = TransactionType.createTransactionType("Groceries");

        //Act
        TransactionType transactionType = TransactionType.createTransactionType("Groceries");

        //Assert
        assertEquals(transactionTypeTest, transactionType);
    }

    @Test
    @DisplayName("Verify Equals of TransactionTypeVO || Happy case: Same object")
    public void testEqualsTransactionTypeVOSameObject() {

        //Act
        TransactionType transactionType = TransactionType.createTransactionType("Groceries");

        //Assert
        assertEquals(transactionType, transactionType);
    }

    @Test
    @DisplayName("Test Equals of TransactionTypeVO || Sad Case: Different TransactionType")
    public void differentTransactionTypeVO() {

        //Arrange
        TransactionType transactionTypeTest = TransactionType.createTransactionType("NotGroceries");

        //Act
        TransactionType transactionType = TransactionType.createTransactionType("Groceries");
        boolean result = transactionType.equals(transactionTypeTest);

        //Assert
        assertEquals(false, result);
    }

    @Test
    @DisplayName("Test Equals of TransactionTypeVO || Sad case: object null")
    public void differentTransactionTypeVOObjectNull() {

        //Arrange
        TransactionType transactionTypeTest = null;

        //Act
        TransactionType transactionType = TransactionType.createTransactionType("Groceries");
        boolean result = transactionType.equals(transactionTypeTest);

        //Assert
        assertEquals(false, result);
    }

    @Test
    @DisplayName("Verify Equals of TransactionTypeVO || Sad case: Different objects class'")

    public void differentTransactionTypeVODifferentClass() {
        //Arrange
        Denomination denomination = Denomination.createDenomination("Groceries");

        //Act
        TransactionType transactionType = TransactionType.createTransactionType("Groceries");

        //Assert
        assertNotEquals(transactionType, denomination);
    }

    @Test
    @DisplayName("Verify Hash Code of TransactionTypeVO")
    public void testTransactionTypeVOHashCode() {

        //Act
        TransactionType transactionType = TransactionType.createTransactionType("Groceries");
        double hashcode = transactionType.hashCode();

        //Assert
        assertEquals(transactionType.hashCode(), hashcode);
    }

}