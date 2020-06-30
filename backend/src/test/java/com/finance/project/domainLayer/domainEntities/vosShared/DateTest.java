package com.finance.project.domainLayer.domainEntities.vosShared;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


class DateTest {

    @Test
    @DisplayName("Test the Date - Constructor")
    public void testDate_Constructor() {
        //Arrange
        LocalDate testingDate = LocalDate.of(2020,3,4);

        //Act
        Date firstDate = Date.createDate(testingDate);

        //Assert
        assertEquals(testingDate, firstDate.getDate());
    }

    @Test
    @DisplayName("Test the Date - getDate()")
    public void testDate_getDate() {
        //Arrange
        LocalDate testingDate = LocalDate.of(2020,3,4);
        Date firstDate = Date.createDate(testingDate);

        //Act
        LocalDate result = firstDate.getDate();

        //Assert
        assertEquals(testingDate, result);
    }

    @Test
    @DisplayName("Test the Date - equals() - True")
    public void testDate_equals_True() {
        //Arrange
        LocalDate testingDate = LocalDate.of(2020,3,4);
        Date firstDate = Date.createDate(testingDate);
        Date secondDate = Date.createDate(testingDate);


        //Act
        boolean result = firstDate.equals(secondDate);

        //Assert
        assertEquals(true,result);
    }

    @Test
    @DisplayName("Test the Date - equals() - False")
    public void testDate_equals_False() {
        //Arrange
        LocalDate firstTestingDate = LocalDate.of(2020,3,4);
        Date firstDate = Date.createDate(firstTestingDate);

        LocalDate secondTestingDate = LocalDate.of(2020,5,4);
        Date secondDate = Date.createDate(secondTestingDate);

        //Act
        boolean result = firstDate.equals(secondDate);

        //Assert
        assertEquals(false,result);
    }

    @Test
    @DisplayName("Test the Date - equals() - True | Same Object")
    public void testDateEqualsSameObject() {

        //Arrange
        LocalDate testingDate = LocalDate.of(2020,3,4);
        Date date = Date.createDate(testingDate);

        //Act
        boolean result = date.equals(date);

        //Assert
        assertEquals(true, result);
    }

    @Test
    @DisplayName("Test the Date - equals() - False | Different Class")
    public void testDateEqualsDifferentClass() {

        //Arrange
        LocalDate testingDate = LocalDate.of(2020,3,4);
        Date date = Date.createDate(testingDate);

        String string = "Bug killer";

        //Act
        boolean result = date.equals(string);

        //Assert
        assertEquals(false, result);
    }

    @Test
    @DisplayName("Test the Date - equals() - False | Null")
    public void testDateNull() {

        //Arrange
        LocalDate testingDate = LocalDate.of(2020,3,4);
        Date date = Date.createDate(testingDate);

        //Act
        boolean result = date.equals(null);

        //Assert
        assertEquals(false, result);
    }

    @Test
    @DisplayName("Test the Date - hashCode() - True")
    public void testDate_hashCode_True() {
        //Arrange
        LocalDate testingDate = LocalDate.of(2020,3,4);
        Date firstDate = Date.createDate(testingDate);
        Date secondDate = Date.createDate(testingDate);

        //Act
        int firstHash = firstDate.hashCode();
        int secondHash = secondDate.hashCode();

        //Assert
        assertEquals(firstHash,secondHash);
    }

    @Test
    @DisplayName("Test the Date - hashCode() - False")
    public void testDate_hashCode_False() {
        //Arrange
        LocalDate firstTestingDate = LocalDate.of(2020,3,4);
        Date firstDate = Date.createDate(firstTestingDate);

        LocalDate secondTestingDate = LocalDate.of(2020,5,4);
        Date secondDate = Date.createDate(secondTestingDate);

        //Act
        int firstHash = firstDate.hashCode();
        int secondHash = secondDate.hashCode();

        //Assert
        assertNotEquals(firstHash,secondHash);
    }

    @Test
    @DisplayName("Test the Date - Constructor - Exception: date null")
    void testDate_Constructor_Exception_Date_null() {
        //Arrange
        LocalDate testingDate = null;

        //Act
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> Date.createDate(testingDate));

        //Assert
        assertEquals("Date not created due to the fact that the date parameter hasn't a valid argument", thrown.getMessage());
    }

}