package com.finance.project.dtos.dtosAssemblers;

import com.finance.project.dtos.dtos.AccountDTO;
import com.finance.project.dtos.dtos.AccountsDTO;

import java.util.List;

public class AccountsDTOAssembler {

    public static AccountsDTO createDTOFromDomainObject(List<AccountDTO> accountDTOS) {

        AccountsDTO accountsDTO = new AccountsDTO(accountDTOS);

        return accountsDTO;
    }
}
