package com.finance.project.dtos.dtos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountDTOTest {

    @Test
    @DisplayName("Test For AccountDTO()")
    void AccountDTOConstructor() {

        // Arrange

        String companyDenomination = "Company";
        String companyDescription = "Company account";

        // Act

        AccountDTO accountDTO = new AccountDTO(companyDenomination, companyDescription);

        // Assert

        assertEquals(companyDenomination, accountDTO.getDenomination());
        assertEquals(companyDescription, accountDTO.getDescription());
    }



    @Test
    @DisplayName("Test For equals() | Success")
    void equalsSuccess() {

        // Arrange

        String companyDenomination = "Company";
        String companyDescription = "Company account";

        // Act

        AccountDTO accountDTO = new AccountDTO(companyDenomination, companyDescription);
        AccountDTO accountDTO2 = new AccountDTO(companyDenomination, companyDescription);

        boolean result = accountDTO.equals(accountDTO2);

        // Assert

        assertEquals(true, result);
        assertEquals(accountDTO.equals(accountDTO2), accountDTO2.equals(accountDTO));
    }

    @Test
    @DisplayName("Test For equals() | Success | Same Object")
    void equalsSuccessSameObject() {


        // Arrange

        String companyDenomination = "Company";
        String companyDescription = "Company account";

        // Act

        AccountDTO accountDTO = new AccountDTO(companyDenomination, companyDescription);


        boolean result = accountDTO.equals(accountDTO);

        // Assert

        assertEquals(true, result);
    }

    @Test
    @DisplayName("Test For equals() | Fail | Denomination")
    void equalsFailDenomination() {


        // Arrange

        String companyDenomination = "Company";
        String companyDescription = "Company account";

        //Arrange Second GroupID

        String bankDenomination = "Bank";
        String bankDescription = "Company account";

        // Act

        AccountDTO accountDTO = new AccountDTO(companyDenomination, companyDescription);
        AccountDTO accountDTO2 = new AccountDTO(bankDenomination, bankDescription);

        boolean result = accountDTO.equals(accountDTO2);

        // Assert

        assertEquals(false, result);
    }

    @Test
    @DisplayName("Test For equals() | Fail | Description")
    void equalsFailDescription() {


        // Arrange

        String companyDenomination = "Company";
        String companyDescription = "Company account";

        //Arrange Second AccountDTO

        String bankDenomination = "Company";
        String bankDescription = "Bank account";

        // Act

        AccountDTO accountDTO = new AccountDTO(companyDenomination, companyDescription);
        AccountDTO accountDTO2 = new AccountDTO(bankDenomination, bankDescription);

        boolean result = accountDTO.equals(accountDTO2);

        // Assert

        assertEquals(false, result);
    }


    @Test
    @DisplayName("Test For equals() | Fail | Different Class")
    void equalsFailDifferentClass() {

        // Arrange

        String companyDenomination = "Company";
        String companyDescription = "Company account";

        // Act

        AccountDTO accountDTO = new AccountDTO(companyDenomination, companyDescription);


        boolean result = accountDTO.equals(companyDenomination);


        // Assert

        assertEquals(false, result);
    }

    @Test
    @DisplayName("Test For equals() | Fail | Null")
    void equalsFailNull() {

        // Arrange

        String companyDenomination = "Company";
        String companyDescription = "Company account";

        // Act

        AccountDTO accountDTO = new AccountDTO(companyDenomination, companyDescription);


        boolean result = accountDTO.equals(null);


        // Assert

        assertEquals(false, result);
    }

    @Test
    @DisplayName("Test For HahCode | Success")
    void hashCodeSuccess() {

        // Arrange

        String companyDenomination = "Company";
        String companyDescription = "Company account";

        // Act

        AccountDTO accountDTO = new AccountDTO(companyDenomination, companyDescription);
        AccountDTO accountDTO2 = new AccountDTO(companyDenomination, companyDescription);


        // Act

        boolean hashCodeResult = accountDTO.hashCode()== accountDTO2.hashCode();


        // Assert

        assertEquals(true, hashCodeResult);
        assertTrue(accountDTO.hashCode()== accountDTO.hashCode());
        assertEquals(true, accountDTO.hashCode()== accountDTO.hashCode() );
        assertTrue(accountDTO.hashCode()== accountDTO2.hashCode());
    }

    @Test
    @DisplayName("Test For HashCode | Fail")
    void hashCodeFail() {

        // Arrange

        String companyDenomination = "Company";
        String companyDescription = "Company account";

        //Arrange Second AccountDTO

        String bankDenomination = "Company";
        String bankDescription = "Bank account";

        // Act

        AccountDTO accountDTO = new AccountDTO(companyDenomination, companyDescription);
        AccountDTO accountDTO2 = new AccountDTO(bankDenomination, bankDescription);


        // Act

        boolean hashCodeResult = accountDTO.hashCode()== accountDTO2.hashCode();


        // Assert

        assertEquals(false, hashCodeResult);
        assertFalse(accountDTO.hashCode()== accountDTO2.hashCode());
    }

}