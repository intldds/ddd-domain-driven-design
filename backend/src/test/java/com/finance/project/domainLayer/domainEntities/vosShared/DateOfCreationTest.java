package com.finance.project.domainLayer.domainEntities.vosShared;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Ala Matos
 */
class DateOfCreationTest {

    @Test
    @DisplayName("Test the DateOfCreation - Constructor")
    public void testDateOfCreation_Constructor() {
        //Arrange
        LocalDate testingDateOfCreation = LocalDate.of(2020, 3, 4);

        //Act
        DateOfCreation firstDateOfCreation = DateOfCreation.createDateOfCreation(testingDateOfCreation);

        //Assert
        assertEquals(testingDateOfCreation, firstDateOfCreation.getDateOfCreation());
    }

    @Test
    @DisplayName("Test the DateOfCreation - getDateOfCreation()")
    public void testDateOfCreation_getDateOfCreation() {
        //Arrange
        LocalDate testingDateOfCreation = LocalDate.of(2020, 3, 4);
        DateOfCreation firstDateOfCreation = DateOfCreation.createDateOfCreation(testingDateOfCreation);

        //Act
        LocalDate result = firstDateOfCreation.getDateOfCreation();

        //Assert
        assertEquals(testingDateOfCreation, result);
    }

    @Test
    @DisplayName("Test the DateOfCreation - equals() - True")
    public void testDateOfCreation_equals_True() {
        //Arrange
        LocalDate testingDateOfCreation = LocalDate.of(2020, 3, 4);
        DateOfCreation firstDateOfCreation = DateOfCreation.createDateOfCreation(testingDateOfCreation);
        DateOfCreation secondDateOfCreation = DateOfCreation.createDateOfCreation(testingDateOfCreation);


        //Act
        boolean result = firstDateOfCreation.equals(secondDateOfCreation);

        //Assert
        assertEquals(true,result);
    }

    @Test
    @DisplayName("Test the DateOfCreation - equals() - False")
    public void testDateOfCreation_equals_False() {
        //Arrange
        LocalDate firstTestingDateOfCreation = LocalDate.of(2020, 3, 4);
        DateOfCreation firstDateOfCreation = DateOfCreation.createDateOfCreation(firstTestingDateOfCreation);

        LocalDate secondTestingDateOfCreation = LocalDate.of(2020, 5, 4);
        DateOfCreation secondDateOfCreation = DateOfCreation.createDateOfCreation(secondTestingDateOfCreation);

        //Act
        boolean result = firstDateOfCreation.equals(secondDateOfCreation);

        //Assert
        assertEquals(false,result);
    }

    @Test
    @DisplayName("Test the DateOfCreation - equals() - True | Same Object")
    public void testDateOfCreationEqualsSameObject() {

        //Arrange
        LocalDate testingDateOfCreation = LocalDate.of(2020, 3, 4);
        DateOfCreation dateOfCreation = DateOfCreation.createDateOfCreation(testingDateOfCreation);

        //Act
        boolean result = dateOfCreation.equals(dateOfCreation);

        //Assert
        assertEquals(true, result);
    }

    @Test
    @DisplayName("Test the DateOfCreation - equals() - False | Different Class")
    public void testDateOfCreationEqualsDifferentClass() {

        //Arrange
        LocalDate testingDateOfCreation = LocalDate.of(2020, 3, 4);
        DateOfCreation dateOfCreation = DateOfCreation.createDateOfCreation(testingDateOfCreation);

        String string = "Bug killer";

        //Act
        boolean result = dateOfCreation.equals(string);

        //Assert
        assertEquals(false, result);
    }

    @Test
    @DisplayName("Test the DateOfCreation - equals() - False | Null")
    public void testDateOfCreationNull() {

        //Arrange
        LocalDate testingDateOfCreation = LocalDate.of(2020, 3, 4);
        DateOfCreation dateOfCreation = DateOfCreation.createDateOfCreation(testingDateOfCreation);

        //Act
        boolean result = dateOfCreation.equals(null);

        //Assert
        assertEquals(false, result);
    }

    @Test
    @DisplayName("Test the DateOfCreation - hashCode() - True")
    public void testDateOfCreation_hashCode_True() {
        //Arrange
        LocalDate testingDateOfCreation = LocalDate.of(2020, 3, 4);
        DateOfCreation firstDateOfCreation = DateOfCreation.createDateOfCreation(testingDateOfCreation);
        DateOfCreation secondDateOfCreation = DateOfCreation.createDateOfCreation(testingDateOfCreation);

        //Act
        int firstHash = firstDateOfCreation.hashCode();
        int secondHash = secondDateOfCreation.hashCode();

        //Assert
        assertEquals(firstHash,secondHash);
    }

    @Test
    @DisplayName("Test the DateOfCreation - hashCode() - False")
    public void testDateOfCreation_hashCode_False() {
        //Arrange
        LocalDate firstTestingDateOfCreation = LocalDate.of(2020, 3, 4);
        DateOfCreation firstDateOfCreation = DateOfCreation.createDateOfCreation(firstTestingDateOfCreation);

        LocalDate secondTestingDateOfCreation = LocalDate.of(2020, 5, 4);
        DateOfCreation secondDateOfCreation = DateOfCreation.createDateOfCreation(secondTestingDateOfCreation);

        //Act
        int firstHash = firstDateOfCreation.hashCode();
        int secondHash = secondDateOfCreation.hashCode();

        //Assert
        assertNotEquals(firstHash,secondHash);
    }

    @Test
    @DisplayName("Test the DateOfCreation - Constructor - Exception: dateOfCreation null")
    void testDateOfCreation_Constructor_Exception_DateOfCreation_null() {
        //Arrange
        LocalDate testingDateOfCreation = null;

        //Act
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> DateOfCreation.createDateOfCreation(testingDateOfCreation));

        //Assert
        assertEquals("DateOfCreation not created due to the fact that the dateOfCreation parameter hasn't a valid argument", thrown.getMessage());
    }

    @Test
    @DisplayName("Test the DateOfCreation - Constructor - Exception: dateOfCreation null")
    void testDateOfCreationCreateDateOfCreationAutomatic() {
        //Arrange
        DateOfCreation automaticDate= DateOfCreation.createDateOfCreationAutomatic();
        DateOfCreation expectedDate = DateOfCreation.createDateOfCreation (LocalDate.now());

        //Act


        //Assert
        assertEquals(expectedDate, automaticDate);
    }
}