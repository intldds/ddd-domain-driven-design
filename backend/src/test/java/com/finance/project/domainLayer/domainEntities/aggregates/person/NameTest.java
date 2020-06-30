package com.finance.project.domainLayer.domainEntities.aggregates.person;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Ala Matos
 */
class NameTest {

    @Test
    @DisplayName("Test the Name - Constructor")
    public void testName_Constructor() {
        //Arrange
        String testingName = "Hulk";

        //Act
        Name firstName = Name.createName(testingName);

        //Assert
        assertEquals(testingName, firstName.getName());
    }

    @Test
    @DisplayName("Test the Name - getName()")
    public void testName_getName() {
        //Arrange
        String testingName = "Hulk";
        Name firstName = Name.createName(testingName);

        //Act
        String result = firstName.getName();

        //Assert
        assertEquals(testingName, result);
    }

    @Test
    @DisplayName("Test the Name - equals() - True")
    public void testName_equals_True() {
        //Arrange
        String testingName = "Hulk";
        Name firstName = Name.createName(testingName);
        Name secondtName = Name.createName(testingName);


        //Act
        boolean result = firstName.equals(secondtName);

        //Assert
        assertEquals(true, result);
    }

    @Test
    @DisplayName("Test the Name - equals() - False")
    public void testName_equals_False() {
        //Arrange
        String firstTestingName = "Hulk";
        Name firstName = Name.createName(firstTestingName);

        String secondTestingName = "She-Hulk";
        Name secondtName = Name.createName(secondTestingName);

        //Act
        boolean result = firstName.equals(secondtName);

        //Assert
        assertEquals(false, result);
    }

    @Test
    @DisplayName("Test the Name - equals() - True | Same Object")
    public void testNameEqualsSameObject() {

        //Arrange
        String testingName = "Hulk";
        Name name = Name.createName(testingName);

        //Act
        boolean result = name.equals(name);

        //Assert
        assertEquals(true, result);
    }

    @Test
    @DisplayName("Test the Name - equals() - False | Different Class")
    public void testNameEqualsDifferentClass() {

        //Arrange
        String testingName = "Hulk";
        Name name = Name.createName(testingName);

        String string = "Bug killer";

        //Act
        boolean result = name.equals(string);

        //Assert
        assertEquals(false, result);
    }

    @Test
    @DisplayName("Test the Name - equals() - False | Null")
    public void testNameNull() {

        //Arrange
        String testingName = "Hulk";
        Name name = Name.createName(testingName);

        //Act
        boolean result = name.equals(null);

        //Assert
        assertEquals(false, result);
    }

    @Test
    @DisplayName("Test the Name - hashCode() - True")
    public void testName_hashCode_True() {
        //Arrange
        String testingName = "Hulk";
        Name firstName = Name.createName(testingName);
        Name secondtName = Name.createName(testingName);

        //Act
        int firstHash = firstName.hashCode();
        int secondHash = secondtName.hashCode();

        //Assert
        assertEquals(firstHash, secondHash);
    }

    @Test
    @DisplayName("Test the Name - hashCode() - False")
    public void testName_hashCode_False() {
        //Arrange
        String firstTestingName = "Hulk";
        Name firstName = Name.createName(firstTestingName);

        String secondTestingName = "She-Hulk";
        Name secondtName = Name.createName(secondTestingName);

        //Act
        int firstHash = firstName.hashCode();
        int secondHash = secondTestingName.hashCode();

        //Assert
        assertNotEquals(firstHash, secondHash);
    }

    @Test
    @DisplayName("Verify hashCode | Happy Case ")
    void testHashCode() {

        //Arrange
        String testingName = "Hulk";
        Name name = Name.createName(testingName);

        //Expected
        int expectedHash = 2260875;

        //Act
        int hashResultName = name.hashCode();

        //Assert

        assertEquals(expectedHash, hashResultName);

    }

    @Test
    @DisplayName("Test the Name - Constructor - Exception: name null")
    void testName_ConstructorExceptionNameNull() {
        //Arrange
        String testingName = null;

        //Act
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> Name.createName(testingName));

        //Assert
        assertEquals("Name not created due to the fact that the name parameter hasn't a valid argument", thrown.getMessage());
    }

    @Test
    @DisplayName("Test the Name - Constructor - Exception: name empty")
    void testNameConstructorExceptionNameEmpty() {
        //Arrange
        String testingName = "";

        //Act
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> Name.createName(testingName));

        //Assert
        assertEquals("Name not created due to the fact that the name parameter hasn't a valid argument", thrown.getMessage());
    }
}