package com.finance.project.dtos.dtos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeletePersonTransactionDTOTest {

    //Constructor

    @Test
    @DisplayName("Test For DeletePersonTransaction Constructor ")
    void DeletePersonTransaction_Constructor() {

        //Arrange

        int transactionNumber = 3;
        String email = "francisco@gmail.com";

        //Act

        DeletePersonTransactionDTO deletePersonTransactionDTO = new DeletePersonTransactionDTO(transactionNumber, email);

        //Assert

        assertEquals(transactionNumber, deletePersonTransactionDTO.getTransactionNumber());
        assertEquals(email, deletePersonTransactionDTO.getEmail());

    }

    //Equals

    @Test
    @DisplayName("Test For DeletePersonTransaction Equals - Same Object ")
    void DeletePersonTransaction_EqualsSameObject() {

        //Arrange

        int transactionNumber = 3;
        String email = "francisco@gmail.com";

        //Act

        DeletePersonTransactionDTO deletePersonTransactionDTO = new DeletePersonTransactionDTO(transactionNumber, email);

        //Assert

        assertTrue(deletePersonTransactionDTO.equals(deletePersonTransactionDTO));

    }

    @Test
    @DisplayName("Test For DeletePersonTransaction Equals - Different Object - Same information")
    void DeletePersonTransaction_EqualsDifferentObjectSameInformation() {

        //Arrange

        int transactionNumber1 = 3;
        String email1 = "francisco@gmail.com";

        int transactionNumber2 = 3;
        String email2 = "francisco@gmail.com";

        //Act

        DeletePersonTransactionDTO deletePersonTransactionDTO1 = new DeletePersonTransactionDTO(transactionNumber1, email1);
        DeletePersonTransactionDTO deletePersonTransactionDTO2 = new DeletePersonTransactionDTO(transactionNumber2, email2);

        //Assert

        assertTrue(deletePersonTransactionDTO1.equals(deletePersonTransactionDTO2));

    }

    @Test
    @DisplayName("Test For DeletePersonTransaction Equals - Different Object - Different information")
    void DeletePersonTransaction_EqualsDifferentObjectDifferentInformation() {

        //Arrange

        int transactionNumber1 = 1;
        String email1 = "francisco@gmail.com";

        int transactionNumber2 = 2;
        String email2 = "paulo@gmail.com";

        //Act

        DeletePersonTransactionDTO deletePersonTransactionDTO1 = new DeletePersonTransactionDTO(transactionNumber1, email1);
        DeletePersonTransactionDTO deletePersonTransactionDTO2 = new DeletePersonTransactionDTO(transactionNumber2, email2);

        boolean result = deletePersonTransactionDTO1.equals(deletePersonTransactionDTO2);

        //Assert

        assertEquals(false, result);
        assertFalse(deletePersonTransactionDTO1.equals(deletePersonTransactionDTO2));
        assertFalse(deletePersonTransactionDTO1.equals(email1)); //not instance of
        assertFalse(deletePersonTransactionDTO1.equals(null)); //null


    }

    @Test
    @DisplayName("Test For DeletePersonTransaction Equals - Different Object - Different number")
    void DeletePersonTransaction_EqualsDifferentObjectDifferentNumber() {

        //Arrange

        int transactionNumber1 = 1;
        String email1 = "francisco@gmail.com";

        int transactionNumber2 = 2;

        //Act

        DeletePersonTransactionDTO deletePersonTransactionDTO1 = new DeletePersonTransactionDTO(transactionNumber1, email1);
        DeletePersonTransactionDTO deletePersonTransactionDTO2 = new DeletePersonTransactionDTO(transactionNumber2, email1);

        boolean result = deletePersonTransactionDTO1.equals(deletePersonTransactionDTO2);

        //Assert

        assertEquals(false, result);
        assertFalse(deletePersonTransactionDTO1.equals(deletePersonTransactionDTO2));

    }

    @Test
    @DisplayName("Test For DeletePersonTransaction Equals - Different Object - Different person")
    void DeletePersonTransaction_EqualsDifferentObjectDifferentPerson() {

        //Arrange

        int transactionNumber1 = 1;
        String email1 = "francisco@gmail.com";

        String email2 = "paulo@gmail.com";

        //Act

        DeletePersonTransactionDTO deletePersonTransactionDTO1 = new DeletePersonTransactionDTO(transactionNumber1, email1);
        DeletePersonTransactionDTO deletePersonTransactionDTO2 = new DeletePersonTransactionDTO(transactionNumber1, email2);

        boolean result = deletePersonTransactionDTO1.equals(deletePersonTransactionDTO2);

        //Assert

        assertEquals(false, result);
        assertFalse(deletePersonTransactionDTO1.equals(deletePersonTransactionDTO2));

    }

    //HashCode

    @Test
    @DisplayName("Test For DeletePersonTransaction HashCode")
    void DeleteersonTransaction_HashCode() {

        //Arrange

        int transactionNumber = 2;
        String email = "francisco@gmail.com";

        //Act

        DeletePersonTransactionDTO deletePersonTransactionDTO = new DeletePersonTransactionDTO(transactionNumber, email);
        int hashCode = deletePersonTransactionDTO.hashCode();
        int expectedHashCode = -1845935022;

        //Assert

        assertEquals(expectedHashCode, hashCode);

    }

}