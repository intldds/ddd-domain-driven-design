package com.finance.project.dtos.dtosAssemblers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.finance.project.dtos.dtos.CreatePersonCategoryDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreatePersonCategoryDTOAssemblerTest {

    @Test
    @DisplayName("Create DTO primitive type")
    void createDTO() {

        // Arrange
        String email = "lebron@gmail.com";
        String denomination = "Basket";

        CreatePersonCategoryDTO expected = new CreatePersonCategoryDTO(email, denomination);

        // Act

        CreatePersonCategoryDTOAssembler createPersonCategoryDTOAssembler = new CreatePersonCategoryDTOAssembler();
        CreatePersonCategoryDTO createPersonCategoryDTO = createPersonCategoryDTOAssembler.createDTOFromPrimitiveTypes(email, denomination);

        // Assert
        assertEquals(expected, createPersonCategoryDTO);
        assertEquals(email, createPersonCategoryDTO.getEmail());
        assertEquals(denomination, createPersonCategoryDTO.getDenomination());
    }
}