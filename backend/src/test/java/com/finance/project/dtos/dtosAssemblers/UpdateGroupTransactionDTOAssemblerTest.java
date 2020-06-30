package com.finance.project.dtos.dtosAssemblers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.finance.project.dtos.dtos.UpdateGroupTransactionDTO;

import static org.junit.jupiter.api.Assertions.*;

class UpdateGroupTransactionDTOAssemblerTest {

    @Test
    @DisplayName("Test for updateGroupTransactionDTOAssembler - Success")
    void updateGroupTransactionDTOAssembler_Success() {

        //ARRANGE
        //Group info
        String personEmail = "paulo@gmail.com";
        String groupDenomination = "Sunday Runners";

        //Transaction info
        int transactionNumber = 1;
        String denominationCategory = "Salary";
        String type = "debit";
        String description = "May Salary";
        double amount = 1500.0;
        String denominationAccountDeb = "Company";
        String denominationAccountCred = "Bank Account";

        //Expected Dto
        UpdateGroupTransactionDTO expectedUpdateGroupTransactionDTO = new UpdateGroupTransactionDTO(transactionNumber, groupDenomination, personEmail, denominationCategory, denominationAccountDeb, denominationAccountCred, amount, type, description);


        //ACT
        UpdateGroupTransactionDTO updateGroupTransactionDTO = UpdateGroupTransactionDTOAssembler.createDTOFromPrimitiveTypes(transactionNumber, groupDenomination, personEmail, denominationCategory, denominationAccountDeb, denominationAccountCred, amount, type, description);


        //ASSERT
        assertEquals(expectedUpdateGroupTransactionDTO, updateGroupTransactionDTO);
        assertEquals(transactionNumber, updateGroupTransactionDTO.getTransactionNumber());
        assertEquals(groupDenomination, updateGroupTransactionDTO.getGroupDenomination());
        assertEquals(personEmail, updateGroupTransactionDTO.getPersonGroupMemberEmail());
        assertEquals(denominationCategory, updateGroupTransactionDTO.getCategoryDenomination());
        assertEquals(denominationAccountDeb, updateGroupTransactionDTO.getAccountToDebitName());
        assertEquals(denominationAccountCred, updateGroupTransactionDTO.getAccountToCreditName());
        assertEquals(amount, updateGroupTransactionDTO.getTransactionAmount());
        assertEquals(type, updateGroupTransactionDTO.getTransactionType());
        assertEquals(description, updateGroupTransactionDTO.getTransactionDescription());
    }

}