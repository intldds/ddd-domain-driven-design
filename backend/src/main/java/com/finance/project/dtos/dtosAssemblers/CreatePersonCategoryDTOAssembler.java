package com.finance.project.dtos.dtosAssemblers;

import com.finance.project.dtos.dtos.CreatePersonCategoryDTO;

/**
 * The type Create person category dto assembler.
 */
public class CreatePersonCategoryDTOAssembler {

    /**
     * Create dto from primitve types create person category dto.
     *
     * @param email        the email
     * @param denomination the denomination
     * @return the create person category dto
     */
    public static CreatePersonCategoryDTO createDTOFromPrimitiveTypes(String email,String denomination) {
        CreatePersonCategoryDTO createPersonCategoryDTO = new CreatePersonCategoryDTO(email, denomination);
        return createPersonCategoryDTO;
    }


}
