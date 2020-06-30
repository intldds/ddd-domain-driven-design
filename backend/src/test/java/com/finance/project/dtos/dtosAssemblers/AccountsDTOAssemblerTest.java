package com.finance.project.dtos.dtosAssemblers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.finance.project.dtos.dtos.AccountDTO;
import com.finance.project.dtos.dtos.AccountsDTO;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AccountsDTOAssemblerTest {

    @Test
    @DisplayName("AccountsDTOAssembler - Test create AccountsDTO from domain objects")
    void accountsDTOAssembler_createDTOFromDomainObject() {

        //Arrange

        String accountDenomination = "Supermarket Account";
        String accountDescription = "Supermarket expenses";

        AccountDTO accountDTO = AccountDTOAssembler.createDTOFromPrimitiveTypes(accountDenomination, accountDescription);

        List<AccountDTO> listAccountDTO = new ArrayList<>();
        listAccountDTO.add(accountDTO);

        //Act
        AccountsDTOAssembler accountsDTOAssembler = new AccountsDTOAssembler();
        AccountsDTO accountsDTO = accountsDTOAssembler.createDTOFromDomainObject(listAccountDTO);

        //Expected
        AccountsDTO accountsDTOExpected = new AccountsDTO(listAccountDTO);

        //Assert
        assertEquals(accountsDTOExpected, accountsDTO);
    }
}