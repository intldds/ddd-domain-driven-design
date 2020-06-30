package com.finance.project.domainLayer.domainEntities.vosShared;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * @author Ala Matos
 */

class AmountTest {

    @Test
    @DisplayName("Test the Amount - Constructor")
    public void testAmount_Constructor() {
        //Arrange
        double testingAmount = 12.2;

        //Act
        Amount firstAmount = Amount.createAmount(testingAmount);

        //Assert
        assertEquals(testingAmount, firstAmount.getAmount());
    }

    @Test
    @DisplayName("Test the Amount - getAmount()")
    public void testAmount_getAmount() {
        //Arrange
        double testingAmount = 12.2;
        Amount firstAmount = Amount.createAmount(testingAmount);

        //Act
        double result = firstAmount.getAmount();

        //Assert
        assertEquals(testingAmount, result);
    }

    @Test
    @DisplayName("Test the Amount - equals() - True")
    public void testAmount_equals_True() {
        //Arrange
        double testingAmount = 12.2;
        Amount firstAmount = Amount.createAmount(testingAmount);
        Amount secondAmount = Amount.createAmount(testingAmount);

        //Act
        boolean result = firstAmount.equals(secondAmount);

        //Assert
        assertEquals(true,result);
    }

    @Test
    @DisplayName("Test the Amount - equals() - False")
    public void testAmount_equals_False() {
        //Arrange
        double firstTestingAmount = 12.2;
        Amount firstAmount = Amount.createAmount(firstTestingAmount);

        double secondTestingAmount = 14.1;
        Amount secondAmount = Amount.createAmount(secondTestingAmount);

        //Act
        boolean result = firstAmount.equals(secondAmount);

        //Assert
        assertEquals(false,result);
    }

    @Test
    @DisplayName("Test the Amount - equals() - True | Same Object")
    public void testAmountEqualsSameObject() {

        //Arrange
        double testingAmount = 12.2;
        Amount amount = Amount.createAmount(testingAmount);

        //Act
        boolean result = amount.equals(amount);

        //Assert
        assertEquals(true, result);
    }

    @Test
    @DisplayName("Test the Amount - equals() - False | Different Class")
    public void testAmountEqualsDifferentClass() {

        //Arrange
        double testingAmount = 12.2;
        Amount amount = Amount.createAmount(testingAmount);

        String string = "Bug killer";

        //Act
        boolean result = amount.equals(string);

        //Assert
        assertEquals(false, result);
    }

    @Test
    @DisplayName("Test the Amount - equals() - False | Null")
    public void testAmountNull() {

        //Arrange
        double testingAmount = 12.2;
        Amount amount = Amount.createAmount(testingAmount);

        //Act
        boolean result = amount.equals(null);

        //Assert
        assertEquals(false, result);
    }

    @Test
    @DisplayName("Test the Amount - hashCode() - True")
    public void testAmount_hashCode_True() {
        //Arrange
        double testingAmount = 12.2;
        Amount firstAmount = Amount.createAmount(testingAmount);
        Amount secondAmount = Amount.createAmount(testingAmount);

        //Act
        int firstHash = firstAmount.hashCode();
        int secondHash = secondAmount.hashCode();

        //Assert
        assertEquals(firstHash,secondHash);
    }

    @Test
    @DisplayName("Test the Amount - hashCode() - False")
    public void testAmount_hashCode_False() {
        //Arrange
        double firstTestingAmount = 12.2;
        Amount firstAmount = Amount.createAmount(firstTestingAmount);

        double secondTestingAmount = 14.1;
        Amount secondAmount = Amount.createAmount(secondTestingAmount);

        //Act
        int firstHash = firstAmount.hashCode();
        int secondHash = secondAmount.hashCode();

        //Assert
        assertNotEquals(firstHash,secondHash);
    }

}