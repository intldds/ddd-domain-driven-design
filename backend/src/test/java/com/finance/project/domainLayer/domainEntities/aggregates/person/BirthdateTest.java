package com.finance.project.domainLayer.domainEntities.aggregates.person;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Ala Matos
 */
class BirthdateTest {

    @Test
    @DisplayName("Test the Birthdate - Constructor")
    public void testBirthdate_Constructor() {
        //Arrange
        LocalDate testingBirthdate = LocalDate.of(2020, 3, 4);

        //Act
        Birthdate firstBirthdate = Birthdate.createBirthdate(testingBirthdate);

        //Assert
        assertEquals(testingBirthdate, firstBirthdate.getBirthdate());
    }

    @Test
    @DisplayName("Test the Birthdate - getBirthdate()")
    public void testBirthdate_getBirthdate() {
        //Arrange
        LocalDate testingBirthdate = LocalDate.of(2020, 3, 4);
        Birthdate firstBirthdate = Birthdate.createBirthdate(testingBirthdate);

        //Act
        LocalDate result = firstBirthdate.getBirthdate();

        //Assert
        assertEquals(testingBirthdate, result);
    }

    @Test
    @DisplayName("Test the Birthdate - equals() - True")
    public void testBirthdate_equals_True() {
        //Arrange
        LocalDate testingBirthdate = LocalDate.of(2020, 3, 4);
        Birthdate firstBirthdate = Birthdate.createBirthdate(testingBirthdate);
        Birthdate secondBirthdate = Birthdate.createBirthdate(testingBirthdate);

        //Act
        boolean result = firstBirthdate.equals(secondBirthdate);

        //Assert
        assertEquals(true, result);
    }

    @Test
    @DisplayName("Test the Birthdate - equals() - False")
    public void testBirthdate_equals_False() {
        //Arrange
        LocalDate firstTestingBirthdate = LocalDate.of(2020, 3, 4);
        Birthdate firstBirthdate = Birthdate.createBirthdate(firstTestingBirthdate);

        LocalDate secondTestingBirthdate = LocalDate.of(2020, 5, 4);
        Birthdate secondBirthdate = Birthdate.createBirthdate(secondTestingBirthdate);

        //Act
        boolean result = firstBirthdate.equals(secondBirthdate);

        //Assert
        assertEquals(false, result);
    }

    @Test
    @DisplayName("Test the Birthdate - equals() - True | Same Object")
    public void testBirthdateEqualsSameObject() {
        //Arrange
        LocalDate testingBirthdate = LocalDate.of(2020, 3, 4);
        Birthdate birthdate = Birthdate.createBirthdate(testingBirthdate);

        //Act
        boolean result = birthdate.equals(birthdate);

        //Assert
        assertEquals(true, result);
    }

    @Test
    @DisplayName("Test the Birthdate - equals() - False | Different Class")
    public void testBirthdateEqualsDifferentClass() {
        //Arrange
        LocalDate testingBirthdate = LocalDate.of(2020, 3, 4);
        Birthdate birthdate = Birthdate.createBirthdate(testingBirthdate);

        String string = "Bug killer";

        //Act
        boolean result = birthdate.equals(string);

        //Assert
        assertEquals(false, result);
    }

    @Test
    @DisplayName("Test the Birthdate - equals() - False | Null")
    public void testBirthdateNull() {
        //Arrange
        LocalDate testingBirthdate = LocalDate.of(2020, 3, 4);
        Birthdate birthdate = Birthdate.createBirthdate(testingBirthdate);

        //Act
        boolean result = birthdate.equals(null);

        //Assert
        assertEquals(false, result);
    }

    @Test
    @DisplayName("Test the Birthdate - hashCode() - True")
    public void testBirthdate_hashCode_True() {
        //Arrange
        LocalDate testingBirthdate = LocalDate.of(2020, 3, 4);
        Birthdate firstBirthdate = Birthdate.createBirthdate(testingBirthdate);
        Birthdate secondBirthdate = Birthdate.createBirthdate(testingBirthdate);

        //Act
        int firstHash = firstBirthdate.hashCode();
        int secondHash = secondBirthdate.hashCode();

        //Assert
        assertEquals(firstHash, secondHash);
    }

    @Test
    @DisplayName("Test the Birthdate - hashCode() - False")
    public void testBirthdate_hashCode_False() {
        //Arrange
        LocalDate firstTestingBirthdate = LocalDate.of(2020, 3, 4);
        Birthdate firstBirthdate = Birthdate.createBirthdate(firstTestingBirthdate);

        LocalDate secondTestingBirthdate = LocalDate.of(2020, 5, 4);
        Birthdate secondBirthdate = Birthdate.createBirthdate(secondTestingBirthdate);

        //Act
        int firstHash = firstBirthdate.hashCode();
        int secondHash = secondBirthdate.hashCode();

        //Assert
        assertNotEquals(firstHash, secondHash);
    }

    @Test
    @DisplayName("Test the Birthdate - Constructor - Exception: birthdate null")
    void testBirthdate_Constructor_Exception_Birthdate_null() {
        //Arrange
        LocalDate testingBirthdate = null;

        //Act
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> Birthdate.createBirthdate(testingBirthdate));

        //Assert
        assertEquals("Birthdate not created due to the fact that the birthdate parameter hasn't a valid argument", thrown.getMessage());
    }
}