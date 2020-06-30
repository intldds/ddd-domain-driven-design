package com.finance.project.dtos.dtosAssemblers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.finance.project.dtos.dtos.CreatePersonAccountDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreatePersonAccountDTOAssemblerTest {

    @Test
    @DisplayName("Create DTO primitive type")
    void createDTO() {

        // Arrange
        String email = "santi@gmail.com";
        String denomination = "Tennis";
        String description = "Roland Garros 2020 tickets";

        CreatePersonAccountDTO expected = new CreatePersonAccountDTO(email, description, denomination);

        // Act

        CreatePersonAccountDTOAssembler createPersonAccountDTOAssembler = new CreatePersonAccountDTOAssembler();
        CreatePersonAccountDTO createPersonAccountDTO = createPersonAccountDTOAssembler.createDTOFromPrimitiveTypes(email, description, denomination);

        // Assert
        assertEquals(expected, createPersonAccountDTO);
        assertEquals(email, createPersonAccountDTO.getEmail());
        assertEquals(denomination, createPersonAccountDTO.getDenomination());
        assertEquals(description, createPersonAccountDTO.getDescription());
    }


}

