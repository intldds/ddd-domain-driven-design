package com.finance.project.dtos.dtosAssemblers;

import com.finance.project.dtos.dtos.CreateGroupAccountDTO;

/**
 * The type Create group account dto assembler.
 */
public class CreateGroupAccountDTOAssembler {

    /**
     * Create dto from primitive types create group account dto.
     *
     * @param personEmail         the person email
     * @param groupDenomination   the group denomination
     * @param accountDescription  the account description
     * @param accountDenomination the account denomination
     * @return the create group account dto
     */
    public static CreateGroupAccountDTO createDTOFromPrimitiveTypes(String personEmail, String groupDenomination, String accountDescription, String accountDenomination) {
        CreateGroupAccountDTO createGroupAccountDTO = new CreateGroupAccountDTO(personEmail, groupDenomination, accountDescription, accountDenomination);
        return createGroupAccountDTO;
    }

}
