package com.finance.project.dtos.dtosAssemblers;

import com.finance.project.dtos.dtos.CreateGroupCategoryDTO;

/**
 * The type Create group category dto assembler.
 */
public class CreateGroupCategoryDTOAssembler {

    /**
     * Create dto from primitive types create group category dto.
     *
     * @param personEmail          the person email
     * @param groupDenomination    the group denomination
     * @param categoryDenomination the category denomination
     * @return the create group category dto
     */
    public static CreateGroupCategoryDTO createDTOFromPrimitiveTypes(String personEmail, String groupDenomination, String categoryDenomination) {
        CreateGroupCategoryDTO createGroupCategoryDTO = new CreateGroupCategoryDTO(personEmail, groupDenomination, categoryDenomination);
        return createGroupCategoryDTO;
    }
}
