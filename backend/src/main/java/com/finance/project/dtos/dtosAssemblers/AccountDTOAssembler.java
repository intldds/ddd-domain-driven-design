package com.finance.project.dtos.dtosAssemblers;

import com.finance.project.dtos.dtos.AccountDTO;

public class AccountDTOAssembler {

    public static AccountDTO createDTOFromPrimitiveTypes(String denomination, String description) {

        AccountDTO accountsDTO = new AccountDTO(denomination, description);

        return accountsDTO;
    }
}
