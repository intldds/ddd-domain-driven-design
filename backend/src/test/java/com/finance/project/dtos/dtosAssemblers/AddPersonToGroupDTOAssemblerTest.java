package com.finance.project.dtos.dtosAssemblers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.finance.project.dtos.dtos.AddPersonToGroupDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @Elisabete_Cavaleiro
 */

class AddPersonToGroupDTOAssemblerTest {

    @Test
    @DisplayName("Test For createDataTransferObject_Primitives_addPersonToGroup()")
    void createDataTransferObject_Primitives() {

        // Arrange

        String personEmail = "cavaleiro@gmail.com";
        String groupDenomination = "Dance";

        AddPersonToGroupDTO expectedAddPersonToGroupDTO = new AddPersonToGroupDTO(personEmail, groupDenomination);

        // Act
        AddPersonToGroupDTOAssembler addPersonToGroupDTOAssembler = new AddPersonToGroupDTOAssembler();
        AddPersonToGroupDTO addPersonToGroupDTO = addPersonToGroupDTOAssembler.createDataTransferObject_Primitives(personEmail, groupDenomination);

        // Assert

        assertEquals(expectedAddPersonToGroupDTO, addPersonToGroupDTO);
        assertEquals(personEmail, addPersonToGroupDTO.getEmail());
        assertEquals(groupDenomination, addPersonToGroupDTO.getDenomination());
    }

}
