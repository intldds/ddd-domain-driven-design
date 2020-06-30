package com.finance.project.dtos.dtosAssemblers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.finance.project.dtos.dtos.CreateGroupTransactionDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateGroupTransactionDTOAssemblerTest {

    @Test
    @DisplayName("Test for create group transaction DTO assembler - DTO from primitives")
    public void createGroupTransactionDTOAssembler_HappyPath() {
        String groupDenomination = "School friends";
        String personGroupMemberEmail = "maria@gmail.com";
        String categoryDenomination = "Gatherings";
        String accountToDebitName = "School friends funds";
        String accountToCreditName = "Restaurant La vita è bella";
        double transactionAmount = 250.00;
        String transactionType = "Debit";
        String transactionDescription = "Dinner party";
        String transactionDate = "2020-06-18";

        CreateGroupTransactionDTO expectedCreateGroupTransactionDTO = new CreateGroupTransactionDTO(groupDenomination, personGroupMemberEmail, categoryDenomination, accountToDebitName, accountToCreditName, transactionAmount, transactionType, transactionDescription, transactionDate);

        //Act
        CreateGroupTransactionDTOAssembler createGroupTransactionDTOAssembler = new CreateGroupTransactionDTOAssembler();
        CreateGroupTransactionDTO createGroupTransactionDTO = createGroupTransactionDTOAssembler.createDTOFromPrimitiveTypes(groupDenomination, personGroupMemberEmail, categoryDenomination, accountToDebitName, accountToCreditName, transactionAmount, transactionType, transactionDescription, transactionDate);

        //Assert
        assertEquals(expectedCreateGroupTransactionDTO, createGroupTransactionDTO);
        assertEquals(groupDenomination, createGroupTransactionDTO.getGroupDenomination());
        assertEquals(personGroupMemberEmail, createGroupTransactionDTO.getPersonGroupMemberEmail());
        assertEquals(categoryDenomination, createGroupTransactionDTO.getCategoryDenomination());
        assertEquals(accountToDebitName, createGroupTransactionDTO.getAccountToDebitName());
        assertEquals(accountToCreditName, createGroupTransactionDTO.getAccountToCreditName());
        assertEquals(transactionAmount, createGroupTransactionDTO.getTransactionAmount());
        assertEquals(transactionType, createGroupTransactionDTO.getTransactionType());
        assertEquals(transactionDescription, createGroupTransactionDTO.getTransactionDescription());
        assertEquals(transactionDate, createGroupTransactionDTO.getDate());
    }
}