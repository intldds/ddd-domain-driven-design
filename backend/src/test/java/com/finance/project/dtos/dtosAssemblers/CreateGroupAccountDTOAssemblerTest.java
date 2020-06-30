package com.finance.project.dtos.dtosAssemblers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.finance.project.dtos.dtos.CreateGroupAccountDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateGroupAccountDTOAssemblerTest {

    @Test
    @DisplayName("Test For creating Data Transfer Objects - Primitives | Happy case")
    void createDTO_Primitives() {

        // Arrange
        String personEmail = "lebron@gmail.com";
        String groupDenomination = "Lakers";
        String accountDescription = "Lakers Expenses";
        String accountDenomination = "LakersAccount";

        CreateGroupAccountDTO expectedCreateGroupAccountDTO = new CreateGroupAccountDTO(personEmail, groupDenomination, accountDescription, accountDenomination);

        // Act
        CreateGroupAccountDTO createGroupAccountDTO = CreateGroupAccountDTOAssembler.createDTOFromPrimitiveTypes(personEmail, groupDenomination, accountDescription, accountDenomination);

        // Assert
        assertEquals(expectedCreateGroupAccountDTO, createGroupAccountDTO);
        assertEquals(personEmail, createGroupAccountDTO.getPersonEmail());
        assertEquals(groupDenomination, createGroupAccountDTO.getGroupDenomination());
        assertEquals(accountDescription, createGroupAccountDTO.getAccountDescription());
        assertEquals(accountDenomination, createGroupAccountDTO.getAccountDenomination());
    }
}
