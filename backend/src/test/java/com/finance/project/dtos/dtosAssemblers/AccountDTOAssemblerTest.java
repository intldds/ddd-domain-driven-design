package com.finance.project.dtos.dtosAssemblers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.finance.project.dtos.dtos.AccountDTO;

import static org.junit.jupiter.api.Assertions.*;

class AccountDTOAssemblerTest {

    @Test
    @DisplayName("AccountDTOAssembler - Test create AccountDTODTO from Primitive Types")
    void AccountDTOAssembler_createDTOFromPrimitiveTypes() {

        //Arrange

        String companyDenomination = "Company";
        String companyDescription = "Company account";

        //Arrange
        AccountDTOAssembler accountDTOAssembler = new AccountDTOAssembler();
        AccountDTO accountDTO = accountDTOAssembler.createDTOFromPrimitiveTypes(companyDenomination, companyDescription);

        //Expected
        AccountDTO accountDTOExpected = new AccountDTO(companyDenomination, companyDescription);

        //Assert
        assertEquals(accountDTOExpected, accountDTO);
    }

}