package com.finance.project.dtos.dtosAssemblers;

import com.finance.project.dtos.dtos.CreatePersonAccountDTO;

/**
 * The type Create person account dto assembler.
 */
public class CreatePersonAccountDTOAssembler {

    /**
     * Create dto from primitve types create person account dto.
     *
     * @param email        the email
     * @param denomination the denomination
     * @param description  the description
     * @return the create person account dto
     */
    public static CreatePersonAccountDTO createDTOFromPrimitiveTypes(String email, String description, String denomination) {
        CreatePersonAccountDTO createPersonAccountDTO = new CreatePersonAccountDTO(email, description, denomination);
        return createPersonAccountDTO;
    }

}
