package com.finance.project.dtos.dtosAssemblers;

import com.finance.project.dtos.dtos.UpdateGroupTransactionDTO;

/**
 * The type Create group transaction dto assembler.
 *
 * @author SWitCH 2019/2020 Group 3
 * @author Joana Correia
 * @version %I%, %G% <p> CreateGroupTransactionByMemberDTOAssembler class defines the DTO responsible for: - transfer data required to create a group transaction by a member of the group
 */
public class UpdateGroupTransactionDTOAssembler {

    private UpdateGroupTransactionDTOAssembler() {
    }

    /**
     * Create dto from primitive types create group transaction dto.
     *
     * @param groupDenomination      Name of the group where the transaction refers to
     * @param personGroupMemberEmail Email of the member of the group that wants to add the transaction
     * @param categoryDenomination   Category that classifies the transaction
     * @param accountToDebitName     Denomination of the account where amount will be debited (subtracted)
     * @param accountToCreditName    Denomination of the account where amount will be credited (added)
     * @param transactionAmount      Amount of money transferred from account to debit and account to credit
     * @param transactionType        Type of transaction (credit, between accountToDebit to accountToCredit; debit between accountToCredit to accountToDebit)
     * @param transactionDescription Description related with the scope of the transaction
     * @return CreateGroupTransactionByMemberDTO createGroupTransactionByMemberDTO
     */
    public static UpdateGroupTransactionDTO createDTOFromPrimitiveTypes(int transactionNumber, String groupDenomination, String personGroupMemberEmail, String categoryDenomination, String accountToDebitName, String accountToCreditName, double transactionAmount, String transactionType, String transactionDescription) {
        UpdateGroupTransactionDTO updateGroupTransactionDTO = new UpdateGroupTransactionDTO(transactionNumber, groupDenomination, personGroupMemberEmail, categoryDenomination, accountToDebitName, accountToCreditName, transactionAmount, transactionType, transactionDescription);
        return updateGroupTransactionDTO;
    }
}
