package com.finance.project.dtos.dtosAssemblers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.finance.project.dtos.dtos.CreateGroupDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateGroupDTOAssemblerTest {

    @Test
    @DisplayName("Test create Data Transfer Object with Primitives || Happy case")
    void testCreateDataTransferObjectWithPrimitives(){

        //Arrange
        String email = "maria@gmail.com";
        String denomination = "Friends";
        String description = "Old friends from school";

        //Expected
        CreateGroupDTO createGroupDTOExpected = new CreateGroupDTO(email, denomination, description);

        //Act
        CreateGroupDTOAssembler createGroupDTOAssembler = new CreateGroupDTOAssembler();
        CreateGroupDTO createGroupDTO = createGroupDTOAssembler.createDTOFromPrimitiveTypes(email, denomination, description);

        //Assert
        assertEquals(createGroupDTOExpected, createGroupDTO);
        assertEquals(email, createGroupDTO.getEmail());
        assertEquals(denomination, createGroupDTO.getDenomination());
        assertEquals(description, createGroupDTO.getDescription());
    }
}