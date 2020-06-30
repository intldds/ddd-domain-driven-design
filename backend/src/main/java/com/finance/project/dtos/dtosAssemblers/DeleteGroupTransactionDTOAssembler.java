package com.finance.project.dtos.dtosAssemblers;

import com.finance.project.dtos.dtos.DeleteGroupTransactionDTO;

/**
 * The type Create group transaction dto assembler.
 *
 * @author SWitCH 2019/2020 Group 3
 * @author Joana Correia
 * @version %I%, %G% <p> CreateGroupTransactionByMemberDTOAssembler class defines the DTO responsible for: - transfer data required to create a group transaction by a member of the group
 */
public class DeleteGroupTransactionDTOAssembler {

    /**
     * Create dto from primitive types create group transaction dto.
     *
     * @param groupDenomination      Name of the group where the transaction refers to
     * @param personGroupMemberEmail Email of the member of the group that wants to add the transaction
     * @return CreateGroupTransactionByMemberDTO createGroupTransactionByMemberDTO
     */
    public static DeleteGroupTransactionDTO createDTOFromPrimitiveTypes(int transactionNumber, String groupDenomination, String personGroupMemberEmail) {
        DeleteGroupTransactionDTO deleteGroupTransactionDTO = new DeleteGroupTransactionDTO(transactionNumber, groupDenomination, personGroupMemberEmail);
        return deleteGroupTransactionDTO;
    }
}
