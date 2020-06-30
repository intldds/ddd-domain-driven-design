package com.finance.project.dtos.dtosAssemblers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.finance.project.dtos.dtos.CreatePersonDTO;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreatePersonDTOAssemblerTest {

    @Test
    @DisplayName("CreatePersonDTOAssembler - Test create Data Transfer Object with Primitives || Happy case")
    void createPersonDTOAssembler_CreateDataTransferObjectWithPrimitivesTest() {

        //Arrange
        String mariaEmail = "maria@gmail.com";
        String mariaName = "Maria Silva";
        LocalDate mariaBirthdate = LocalDate.of(1973, 07, 25);
        String mariaBirthdateString = "1973-07-25";

        String mariaBirthplace = "Braga";

        //Expected

        CreatePersonDTO createPersonDTOExpected = new CreatePersonDTO(mariaEmail, mariaName, mariaBirthdate, mariaBirthplace);

        //Act
        CreatePersonDTOAssembler createPersonDTOAssembler = new CreatePersonDTOAssembler();
        CreatePersonDTO createPersonDTO = createPersonDTOAssembler.createDTOFromPrimitiveTypes(mariaEmail, mariaName, mariaBirthdateString, mariaBirthplace);

        //Assert
        assertEquals(createPersonDTOExpected, createPersonDTO);
        assertEquals(mariaEmail, createPersonDTO.getEmail());
        assertEquals(mariaName, createPersonDTO.getName());
        assertEquals(mariaBirthdate, createPersonDTO.getBirthdate());
        assertEquals(mariaBirthplace, createPersonDTO.getBirthplace());
    }

}