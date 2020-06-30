package com.finance.project.domainLayer.domainEntities.vosShared;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonIDTest {

    @Test
    @DisplayName("Test the PersonID - Constructor")
    public void testPersonID_Constructor() {

        //Arrange
        String email = "xikinho@isep.ipp.pt";
        Email expectedEmail = Email.createEmail(email);

        //Act
        PersonID firstPersonID = PersonID.createPersonID(email);

        //Assert
        assertEquals(expectedEmail, firstPersonID.getEmail());

    }

    @Test
    @DisplayName("Test the PersonID - getPersonID()")
    public void testPersonID_getPersonID() {

        //Arrange
        String email = "xikinho@isep.ipp.pt";
        Email expectedEmail = Email.createEmail(email);
        PersonID firstPersonID = PersonID.createPersonID(email);

        //Act
        Email result = firstPersonID.getEmail();

        //Assert
        assertEquals(expectedEmail, result);
    }

    @Test
    @DisplayName("Test the PersonID - equals() - True")
    public void testPersonID_equals_True() {

        //Arrange
        String email = "xikinho@isep.ipp.pt";
        PersonID firstPersonID = PersonID.createPersonID(email);
        PersonID secondPersonID = PersonID.createPersonID(email);


        //Act
        boolean result = firstPersonID.equals(secondPersonID);

        //Assert
        assertEquals(true,result);
    }

    @Test
    @DisplayName("Test the PersonID - equals() - False")
    public void testPersonID_equals_False() {
        //Arrange
        String firstEmail = "xikinho@isep.ipp.pt";
        PersonID firstPersonID = PersonID.createPersonID(firstEmail);

        String secondEmail = "xikinho@gmail.pt";
        PersonID secondPersonID = PersonID.createPersonID(secondEmail);

        //Act
        boolean result = firstPersonID.equals(secondPersonID);

        //Assert
        assertEquals(false,result);
    }

    @Test
    @DisplayName("Test the PersonID - equals() - False | Different Class")
    public void testPersonIDEqualsDifferentClass() {

        //Arrange
        String email = "xikinho@isep.ipp.pt";
        PersonID personID = PersonID.createPersonID(email);

        String string = "Bug killer";

        //Act
        boolean result = personID.equals(string);

        //Assert
        assertEquals(false, result);
    }

    @Test
    @DisplayName("Test the PersonID - equals() - False | Null")
    public void testPersonIDNull() {

        //Arrange
        String email = "xikinho@isep.ipp.pt";
        PersonID personID = PersonID.createPersonID(email);

        //Act
        boolean result = personID.equals(null);

        //Assert
        assertEquals(false, result);
    }

    @Test
    @DisplayName("Test the PersonID - hashCode() - True")
    public void testPersonID_hashCode_True() {
        //Arrange
        String email = "xikinho@isep.ipp.pt";
        PersonID firstPersonID = PersonID.createPersonID(email);
        PersonID secondPersonID = PersonID.createPersonID(email);

        //Act
        int firstHash = firstPersonID.hashCode();
        int secondHash = secondPersonID.hashCode();

        //Assert
        assertEquals(firstHash,secondHash);
    }

    @Test
    @DisplayName("Test the PersonID - hashCode() - False")
    public void testPersonID_hashCode_False() {
        //Arrange
        String firstEmail = "xikinho@isep.ipp.pt";
        PersonID firstPersonID = PersonID.createPersonID(firstEmail);

        String secondEmail = "xikinho@gmail.pt";
        PersonID secondPersonID = PersonID.createPersonID(secondEmail);

        //Act
        int firstHash = firstPersonID.hashCode();
        int secondHash = secondPersonID.hashCode();

        //Assert
        assertNotEquals(firstHash,secondHash);
    }

    @Test
    @DisplayName("Test the PersonID - Constructor - Exception: email null")
    void testPersonID_Constructor_Exception_Email_null() {
        //Arrange
        String email = null;

        //Act
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> PersonID.createPersonID(email));

        //Assert
        assertEquals("PersonID not created due to the fact that the email parameter hasn't a valid argument", thrown.getMessage());
    }
}