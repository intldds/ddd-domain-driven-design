package com.finance.project.domainLayer.domainEntities.vosShared;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Ala Matos
 */
class DenominationTest {

    @Test
    @DisplayName("Test the Denomination - Constructor")
    public void testDenomination_Constructor() {
        //Arrange
        String testingDenomination = "Groceries of the week";

        //Act
        Denomination firstDenomination = Denomination.createDenomination(testingDenomination);

        //Assert
        assertEquals(testingDenomination, firstDenomination.getDenomination());
    }

    @Test
    @DisplayName("Test the Denomination - getDenomination()")
    public void testDenomination_getDenomination() {
        //Arrange
        String testingDenomination = "Groceries of the week";
        Denomination firstDenomination = Denomination.createDenomination(testingDenomination);

        //Act
        String result = firstDenomination.getDenomination();

        //Assert
        assertEquals(testingDenomination, result);
    }

    @Test
    @DisplayName("Test the Denomination - equals() - True")
    public void testDenomination_equals_True() {
        //Arrange
        String testingDenomination = "Groceries of the week";
        Denomination firstDenomination = Denomination.createDenomination(testingDenomination);
        Denomination secondDenomination = Denomination.createDenomination(testingDenomination);


        //Act
        boolean result = firstDenomination.equals(secondDenomination);

        //Assert
        assertEquals(true,result);
    }

    @Test
    @DisplayName("Test the Denomination - equals() - False")
    public void testDenomination_equals_False() {
        //Arrange
        String firstTestingDenomination = "Groceries of the week";
        Denomination firstDenomination = Denomination.createDenomination(firstTestingDenomination);

        String secondTestingDenomination = "Groceries of the month";
        Denomination secondDenomination = Denomination.createDenomination(secondTestingDenomination);

        //Act
        boolean result = firstDenomination.equals(secondDenomination);

        //Assert
        assertEquals(false,result);
    }

    @Test
    @DisplayName("Test the Denomination - equals() - True | Same Object")
    public void testDenominationEqualsSameObject() {

        //Arrange
        String testingDenomination = "Groceries of the week";
        Denomination denomination = Denomination.createDenomination(testingDenomination);

        //Act
        boolean result = denomination.equals(denomination);

        //Assert
        assertEquals(true, result);
    }

    @Test
    @DisplayName("Test the Denomination - equals() - False | Different Class")
    public void testDenominationEqualsDifferentClass() {

        //Arrange
        String testingDenomination = "Groceries of the week";
        Denomination denomination = Denomination.createDenomination(testingDenomination);

        String string = "Bug killer";

        //Act
        boolean result = denomination.equals(string);

        //Assert
        assertEquals(false, result);
    }

    @Test
    @DisplayName("Test the Denomination - equals() - False | Null")
    public void testDenominationNull() {

        //Arrange
        String testingDenomination = "Groceries of the week";
        Denomination denomination = Denomination.createDenomination(testingDenomination);

        //Act
        boolean result = denomination.equals(null);

        //Assert
        assertEquals(false, result);
    }

    @Test
    @DisplayName("Test the Denomination - hashCode() - True")
    public void testDenomination_hashCode_True() {
        //Arrange
        String testingDenomination = "Groceries of the week";
        Denomination firstDenomination = Denomination.createDenomination(testingDenomination);
        Denomination secondDenomination = Denomination.createDenomination(testingDenomination);

        //Act
        int firstHash = firstDenomination.hashCode();
        int secondHash = secondDenomination.hashCode();

        //Assert
        assertEquals(firstHash,secondHash);
    }

    @Test
    @DisplayName("Test the Denomination - hashCode() - False")
    public void testDenomination_hashCode_False() {
        //Arrange
        String firstTestingDenomination = "Groceries of the week";
        Denomination firstDenomination = Denomination.createDenomination(firstTestingDenomination);

        String secondTestingDenomination = "Groceries of the month";
        Denomination secondDenomination = Denomination.createDenomination(secondTestingDenomination);

        //Act
        int firstHash = firstDenomination.hashCode();
        int secondHash = secondDenomination.hashCode();

        //Assert
        assertNotEquals(firstHash,secondHash);
    }

    @Test
    @DisplayName("Test the Denomination - Constructor - Exception: denomination null")
    void testDenominationConstructorExceptionDenominationNull() {
        //Arrange
        String testingDenomination = null;

        //Act
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> Denomination.createDenomination(testingDenomination));

        //Assert
        assertEquals("Denomination not created due to the fact that the denomination parameter hasn't a valid argument", thrown.getMessage());
    }

    @Test
    @DisplayName("Test the Denomination - Constructor - Exception: denomination empty")
    void testDenominationConstructorExceptionDenominationEmpty() {
        //Arrange
        String testingDenomination = "";

        //Act
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> Denomination.createDenomination(testingDenomination));

        //Assert
        assertEquals("Denomination not created due to the fact that the denomination parameter hasn't a valid argument", thrown.getMessage());
    }
}