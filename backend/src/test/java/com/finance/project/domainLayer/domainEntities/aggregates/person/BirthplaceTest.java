package com.finance.project.domainLayer.domainEntities.aggregates.person;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Ala Matos
 */

class BirthplaceTest {

    @Test
    @DisplayName("Test the Birthplace - Constructor")
    public void testBirthplace_Constructor() {
        //Arrange
        String testingBirthPlace = "Deep sea";

        //Act
        Birthplace firstBirthplace = Birthplace.createBirthplace(testingBirthPlace);

        //Assert
        assertEquals(testingBirthPlace, firstBirthplace.getBirthplace());
    }

    @Test
    @DisplayName("Test the Birthplace - getBirthplace()")
    public void testBirthplace_getBirthplace() {
        //Arrange
        String testingBirthPlace = "Deep sea";
        Birthplace firstBirthplace = Birthplace.createBirthplace(testingBirthPlace);

        //Act
        String result = firstBirthplace.getBirthplace();

        //Assert
        assertEquals(testingBirthPlace, result);
    }

    @Test
    @DisplayName("Test the Birthplace - equals() - True")
    public void testBirthplace_equals_True() {
        //Arrange
        String testingBirthPlace = "Deep sea";
        Birthplace firstBirthplace = Birthplace.createBirthplace(testingBirthPlace);
        Birthplace secondBirthplace = Birthplace.createBirthplace(testingBirthPlace);

        //Act
        boolean result = firstBirthplace.equals(secondBirthplace);

        //Assert
        assertEquals(true,result);
    }

    @Test
    @DisplayName("Test the Birthplace - equals() - False")
    public void testBirthplace_equals_False() {
        //Arrange
        String firstTestingBirthPlace = "Deep sea";
        Birthplace firstBirthplace = Birthplace.createBirthplace(firstTestingBirthPlace);

        String secondTestingBirthPlace = "Earth";
        Birthplace secondBirthplace = Birthplace.createBirthplace(secondTestingBirthPlace);

        //Act
        boolean result = firstBirthplace.equals(secondBirthplace);

        //Assert
        assertEquals(false,result);
    }

    @Test
    @DisplayName("Test the Birthplace - equals() - True | Same Object")
    public void testBirthplaceEqualsSameObject() {
        //Arrange
        String testingBirthPlace = "Deep sea";
        Birthplace birthplace = Birthplace.createBirthplace(testingBirthPlace);

        //Act
        boolean result = birthplace.equals(birthplace);

        //Assert
        assertEquals(true, result);
    }

    @Test
    @DisplayName("Test the Birthplace - equals() - False | Different Class")
    public void testBirthplaceEqualsDifferentClass() {

        //Arrange
        String testingBirthPlace = "Deep sea";
        Birthplace firstBirthplace = Birthplace.createBirthplace(testingBirthPlace);

        String string = "Bug killer";

        //Act
        boolean result = firstBirthplace.equals(string);

        //Assert
        assertEquals(false, result);
    }

    @Test
    @DisplayName("Test the Birthplace - equals() - False | Null")
    public void testBirthplaceNull() {

        //Arrange
        String testingBirthPlace = "Deep sea";
        Birthplace firstBirthplace = Birthplace.createBirthplace(testingBirthPlace);

        //Act
        boolean result = firstBirthplace.equals(null);

        //Assert
        assertEquals(false, result);
    }

    @Test
    @DisplayName("Test the Birthplace - hashCode() - True")
    public void testBirthplace_hashCode_True() {
        //Arrange
        String testingBirthPlace = "Deep sea";
        Birthplace firstBirthplace = Birthplace.createBirthplace(testingBirthPlace);
        Birthplace secondBirthplace = Birthplace.createBirthplace(testingBirthPlace);

        //Act
        int firstHash = firstBirthplace.hashCode();
        int secondHash = secondBirthplace.hashCode();

        //Assert
        assertEquals(firstHash,secondHash);
    }

    @Test
    @DisplayName("Test the Birthplace - hashCode() - False")
    public void testBirthplace_hashCode_False() {
        //Arrange
        String firstTestingBirthPlace = "Deep sea";
        Birthplace firstBirthplace = Birthplace.createBirthplace(firstTestingBirthPlace);

        String secondTestingBirthPlace = "Earth";
        Birthplace secondBirthplace = Birthplace.createBirthplace(secondTestingBirthPlace);

        //Act
        int firstHash = firstBirthplace.hashCode();
        int secondHash = secondBirthplace.hashCode();

        //Assert
        assertNotEquals(firstHash,secondHash);
    }

    @Test
    @DisplayName("Test the Birthplace - Constructor - Exception: birthplace null")
    void testBirthplace_Constructor_Exception_Birthplace_null() {
        //Arrange
        String testingBirthPlace = null;

        //Act
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> Birthplace.createBirthplace(testingBirthPlace));

        //Assert
        assertEquals("Birthplace not created due to the fact that the birthplace parameter hasn't a valid argument", thrown.getMessage());
    }
}