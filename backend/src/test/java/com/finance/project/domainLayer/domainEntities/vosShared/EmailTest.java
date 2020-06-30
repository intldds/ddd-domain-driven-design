package com.finance.project.domainLayer.domainEntities.vosShared;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailTest {

    @Test
    @DisplayName("Test the Email - Constructor")
    public void testEmail_Constructor() {

        //Arrange
        String testingEmail = "xikinho@isep.ipp.pt";

        //Act
        Email firstEmail = Email.createEmail(testingEmail);

        //Assert
        assertEquals(testingEmail,firstEmail.getEmail());
    }

    @Test
    @DisplayName("Test the Email - getEmail()")
    public void testEmail_getEmail() {

        //Arrange
        String testingEmail = "xikinho@isep.ipp.pt";
        Email firstEmail = Email.createEmail(testingEmail);

        //Act
        String result = firstEmail.getEmail();

        //Assert
        assertEquals(testingEmail, result);
    }

    @Test
    @DisplayName("Test the Email - equals() - True")
    public void testEmail_equals_True() {

        //Arrange
        String testingEmail = "xikinho@isep.ipp.pt";
        Email firstEmail = Email.createEmail(testingEmail);
        Email secondEmail = Email.createEmail(testingEmail);


        //Act
        boolean result = firstEmail.equals(secondEmail);

        //Assert
        assertEquals(true,result);
    }

    @Test
    @DisplayName("Test the Email - equals() - False")
    public void testEmail_equals_False() {
        //Arrange
        String firstTestingEmail = "xikinho@isep.ipp.pt";
        Email firstEmail = Email.createEmail(firstTestingEmail);

        String secondTestingEmail = "xikinho@gmail.com";
        Email secondEmail = Email.createEmail(secondTestingEmail);

        //Act
        boolean result = firstEmail.equals(secondEmail);

        //Assert
        assertEquals(false,result);
    }

    @Test
    @DisplayName("Test the Email - equals() - True | Same Object")
    public void testEmailEqualsSameObject() {
        //Arrange
        String testingEmail = "xikinho@isep.ipp.pt";
        Email email = Email.createEmail(testingEmail);

        //Act
        boolean result = email.equals(email);

        //Assert
        assertEquals(true, result);
    }

    @Test
    @DisplayName("Test the Email - equals() - False | Different Class")
    public void testEmailEqualsDifferentClass() {

        //Arrange
        String testingEmail = "xikinho@isep.ipp.pt";
        Email email = Email.createEmail(testingEmail);

        String string = "Bug killer";

        //Act
        boolean result = email.equals(string);

        //Assert
        assertEquals(false, result);
    }

    @Test
    @DisplayName("Test the Email - equals() - False | Null")
    public void testEmailNull() {

        //Arrange
        String testingEmail = "xikinho@isep.ipp.pt";
        Email email = Email.createEmail(testingEmail);

        //Act
        boolean result = email.equals(null);

        //Assert
        assertEquals(false, result);
    }

    @Test
    @DisplayName("Test the Email - hashCode() - True")
    public void testEmail_hashCode_True() {
        //Arrange
        String testingEmail = "xikinho@isep.ipp.pt";
        Email firstEmail = Email.createEmail(testingEmail);
        Email secondEmail = Email.createEmail(testingEmail);

        //Act
        int firstHash = firstEmail.hashCode();
        int secondHash = secondEmail.hashCode();

        //Assert
        assertEquals(firstHash,secondHash);
    }

    @Test
    @DisplayName("Test the Email - hashCode() - False")
    public void testEmail_hashCode_False() {
        //Arrange
        String firstTestingEmail = "xikinho@isep.ipp.pt";
        Email firstEmail = Email.createEmail(firstTestingEmail);

        String secondTestingEmail = "xikinho@gmail.com";
        Email secondEmail = Email.createEmail(secondTestingEmail);

        //Act
        int firstHash = firstEmail.hashCode();
        int secondHash = secondEmail.hashCode();

        //Assert
        assertNotEquals(firstHash,secondHash);
    }

    @Test
    @DisplayName("Test the Email - Constructor - Exception: email null")
    void testEmailConstructorExceptionEmailNull() {
        //Arrange
        String testingEmail = null;

        //Act
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> Email.createEmail(testingEmail));

        //Assert
        assertEquals("Email not created due to the fact that the email parameter hasn't a valid argument", thrown.getMessage());
    }

    @Test
    @DisplayName("Test the Email - Constructor - Exception: email empty")
    void testEmailConstructorExceptionEmailEmpty() {
        //Arrange
        String testingEmail = "";

        //Act
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> Email.createEmail(testingEmail));

        //Assert
        assertEquals("Email not created due to the fact that the email parameter hasn't a valid argument", thrown.getMessage());
    }
}