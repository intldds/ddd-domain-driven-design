package com.finance.project.dtos.dtos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.finance.project.dtos.dtosAssemblers.AccountDTOAssembler;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccountsDTOTest {

    @Test
    @DisplayName("AccountsDTO - Test Constructor with Parameters")
    void accountsDTO_ConstructorWithParametersTest() {

        //Arrange
        String accountDenomination = "Supermarket Account";
        String accountDescription = "Supermarket expenses";

        AccountDTO accountDTO = AccountDTOAssembler.createDTOFromPrimitiveTypes(accountDenomination,accountDescription);

        List<AccountDTO> accountsList = new ArrayList<>();
        accountsList.add(accountDTO);

        //Act
        AccountsDTO accountsDTO = new AccountsDTO(accountsList);

        //Assert
        assertEquals(accountsList, accountsDTO.getAccounts());
    }

    @Test
    @DisplayName("AccountsDTO - Test setAccounts")
    void accountsDTO_setAccountsTest() {

        //Arrange
        String accountDenomination = "Supermarket Account";
        String accountDescription = "Supermarket expenses";

        AccountDTO accountDTO = AccountDTOAssembler.createDTOFromPrimitiveTypes(accountDenomination,accountDescription);

        List<AccountDTO> accountsList = new ArrayList<>();
        accountsList.add(accountDTO);

        //Act
        AccountsDTO accountsDTO = new AccountsDTO();
        accountsDTO.setAccounts(accountsList);

        //Assert
        assertEquals(accountsList, accountsDTO.getAccounts());
    }

    @Test
    @DisplayName("AccountsDTO - Test Constructor without Parameters")
    void accountsDTO_ConstructorWithoutParametersTest() {

        //Arrange

        //Act
        AccountsDTO accountsDTO = new AccountsDTO();

        //Assert
        assertEquals(null, accountsDTO.getAccounts());
    }

    @Test
    @DisplayName("AccountsDTO - Test Equals || Same Object")
    void accountsDTO_EqualsTest_SameObject() {

        //Arrange
        String accountSupermarketDenomination = "Supermarket Account";
        String accountSupermarketDescription = "Supermarket expenses";

        AccountDTO accountSupermarketDTO = AccountDTOAssembler.createDTOFromPrimitiveTypes(accountSupermarketDenomination,accountSupermarketDescription);

        String accountBankDenomination = "Bank Account";
        String accountBankDescription = "Bank Account expenses";

        AccountDTO accountBankDTO = AccountDTOAssembler.createDTOFromPrimitiveTypes(accountBankDenomination,accountBankDescription);

        List<AccountDTO> accountsList = new ArrayList<>();
        accountsList.add(accountSupermarketDTO);
        accountsList.add(accountBankDTO);

        //Act
        AccountsDTO accountsDTO1 = new AccountsDTO(accountsList);
        AccountsDTO accountsDTO2 = new AccountsDTO(accountsList);

        //Assert
        assertTrue(accountsDTO1.equals(accountsDTO2));
        assertTrue(accountsDTO1.equals(accountsDTO1)); //the object is equal to itself
    }

    @Test
    @DisplayName("AccountsDTO - Test Equals || Different Object")
    void accountsDTO_EqualsTest_DifferentObject() {

        //Arrange
        String accountDenomination = "Supermarket Account";
        String accountDescription = "Supermarket expenses";

        AccountDTO accountDTO = AccountDTOAssembler.createDTOFromPrimitiveTypes(accountDenomination,accountDescription);

        List<AccountDTO> accountsList1 = new ArrayList<>();
        accountsList1.add(accountDTO);

        List<AccountDTO> accountsList2 = new ArrayList<>();

        String bugKiller = "Bug Killer";

        //Act
        AccountsDTO accountsDTO1 = new AccountsDTO(accountsList1);
        AccountsDTO accountsDTO2 = new AccountsDTO(accountsList2);
        AccountsDTO accountsDTO3 = null;

        //Assert
        assertFalse(accountsDTO1.equals(accountsDTO2));
        assertFalse(accountsDTO1.equals(bugKiller)); //not same instance
        assertFalse(accountsDTO1.equals(accountsDTO3));
    }

    @Test
    @DisplayName("AccountsDTO - Hash Code")
    void accountsDTO_HashCodeTest() {

        //Arrange
        String accountDenomination = "Supermarket Account";
        String accountDescription = "Supermarket expenses";

        AccountDTO accountDTO = AccountDTOAssembler.createDTOFromPrimitiveTypes(accountDenomination,accountDescription);

        List<AccountDTO> accountsList = new ArrayList<>();
        accountsList.add(accountDTO);

        //Act
        AccountsDTO accountsDTO = new AccountsDTO(accountsList);

        int accountsDTOHashcode = accountsDTO.hashCode();
        int expectedHashCode = 1201670080;

        //Assert
        assertEquals(expectedHashCode, accountsDTOHashcode);
    }

}