package com.finance.project.domainLayer.domainEntities.vosShared;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Ala Matos
 */
class TypeTest {

    @Test
    @DisplayName("Test the Type - Constructor")
    public void testType_Constructor() {
        //Arrange
        String testingType = "Coding type";

        //Act
        Type firstType = Type.createType(testingType);

        //Assert
        assertEquals(testingType, firstType.getType());
    }

    @Test
    @DisplayName("Test the Type - getType()")
    public void testType_getType() {
        //Arrange
        String testingType = "Coding type";
        Type firstType = Type.createType(testingType);

        //Act
        String result = firstType.getType();

        //Assert
        assertEquals(testingType, result);
    }

    @Test
    @DisplayName("Test the Type - equals() - True")
    public void testType_equals_True() {
        //Arrange
        String testingType = "Coding type";
        Type firstType = Type.createType(testingType);
        Type secondType = Type.createType(testingType);


        //Act
        boolean result = firstType.equals(secondType);

        //Assert
        assertEquals(true, result);
    }

    @Test
    @DisplayName("Test the Type - equals() - False")
    public void testType_equals_False() {
        //Arrange
        String firstTestingType = "Coding type";
        Type firstType = Type.createType(firstTestingType);

        String secondTestingType = "Language type";
        Type secondType = Type.createType(secondTestingType);

        //Act
        boolean result = firstType.equals(secondType);

        //Assert
        assertEquals(false, result);
    }

    @Test
    @DisplayName("Test the Type - equals() - True | Same Object")
    public void testTypeEqualsSameObject() {

        //Arrange
        String testingType = "Coding type";
        Type type = Type.createType(testingType);

        //Act
        boolean result = type.equals(type);

        //Assert
        assertEquals(true, result);
    }

    @Test
    @DisplayName("Test the Type - equals() - False | Different Class")
    public void testTypeEqualsDifferentClass() {

        //Arrange
        String testingType = "Coding type";
        Type type = Type.createType(testingType);

        String string = "Bug killer";

        //Act
        boolean result = type.equals(string);

        //Assert
        assertEquals(false, result);
    }

    @Test
    @DisplayName("Test the Type - equals() - False | Null")
    public void testTypeNull() {

        //Arrange
        String testingType = "Coding type";
        Type type = Type.createType(testingType);

        //Act
        boolean result = type.equals(null);

        //Assert
        assertEquals(false, result);
    }

    @Test
    @DisplayName("Test the Type - hashCode() - True")
    public void testType_hashCode_True() {
        //Arrange
        String testingType = "Coding type";
        Type firstType = Type.createType(testingType);
        Type secondType = Type.createType(testingType);

        //Act
        int firstHash = firstType.hashCode();
        int secondHash = secondType.hashCode();

        //Assert
        assertEquals(firstHash, secondHash);
    }

    @Test
    @DisplayName("Verify hashCode | Happy Case ")
    void testHashCode() {

        //Arrange
        String testingType = "Coding type";
        Type type = Type.createType(testingType);

        //Expected
        int expectedHash = -697059793;

        //Act
        int hashType = type.hashCode();

        //Assert

        assertEquals(expectedHash, hashType);

    }

    @Test
    @DisplayName("Test the Type - hashCode() - False")
    public void testType_hashCode_False() {
        //Arrange
        String firstTestingType = "Coding type";
        Type firstType = Type.createType(firstTestingType);

        String secondTestingType = "Language type";
        Type secondType = Type.createType(secondTestingType);

        //Act
        int firstHash = firstTestingType.hashCode();
        int secondHash = secondTestingType.hashCode();

        //Assert
        assertNotEquals(firstHash, secondHash);
    }

    @Test
    @DisplayName("Test the Type - Constructor - Exception: type null")
    void testTypeConstructorExceptionTypeNull() {
        //Arrange
        String testingType = null;

        //Act
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> Type.createType(testingType));

        //Assert
        assertEquals("Type not created due to the fact that the type parameter hasn't a valid argument", thrown.getMessage());
    }

    @Test
    @DisplayName("Test the Type - Constructor - Exception: type empty")
    void testTypeConstructorExceptionTypeEmpty() {
        //Arrange
        String testingType = "";

        //Act
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> Type.createType(testingType));

        //Assert
        assertEquals("Type not created due to the fact that the type parameter hasn't a valid argument", thrown.getMessage());
    }
}