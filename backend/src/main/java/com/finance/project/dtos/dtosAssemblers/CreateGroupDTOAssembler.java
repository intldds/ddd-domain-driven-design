package com.finance.project.dtos.dtosAssemblers;

import com.finance.project.dtos.dtos.CreateGroupDTO;

/**
 * The type Create group dto assembler.
 */
public class CreateGroupDTOAssembler {

    /**
     * Create dto from primitve types create group dto.
     *
     * @param email        the email
     * @param denomination the denomination
     * @param description  the description
     * @return the create group dto
     */
    public static CreateGroupDTO createDTOFromPrimitiveTypes(String email, String denomination, String description) {
        return new CreateGroupDTO(email, denomination, description);
    }
}
