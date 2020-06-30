package com.finance.project.dtos.dtosAssemblers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.finance.project.dtos.dtos.CreateGroupCategoryDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateGroupCategoryDTOAssemblerTest {

    @Test
    @DisplayName("Test For createDataTransferObject_Primitives()")
    void createDataTransferObject_Primitives() {

        // Arrange

        String personEmail = "paulo@gmail.com";
        String groupDenomination = "Runners";
        String categoryDenomination = "Equipment";

        CreateGroupCategoryDTO expectedCreateGroupCategoryDTO = new CreateGroupCategoryDTO(personEmail, groupDenomination, categoryDenomination);

        // Act

        CreateGroupCategoryDTOAssembler createGroupCategoryDTOAssembler = new CreateGroupCategoryDTOAssembler();
        CreateGroupCategoryDTO createGroupCategoryDTO = createGroupCategoryDTOAssembler.createDTOFromPrimitiveTypes(personEmail, groupDenomination, categoryDenomination);

        // Assert

        assertEquals(expectedCreateGroupCategoryDTO, createGroupCategoryDTO);
        assertEquals(personEmail, createGroupCategoryDTO.getPersonEmail());
        assertEquals(groupDenomination, createGroupCategoryDTO.getGroupDenomination());
        assertEquals(categoryDenomination, createGroupCategoryDTO.getCategoryDenomination());
    }
}