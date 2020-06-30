package com.finance.project.dtos.dtosAssemblers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.finance.project.dtos.dtos.BooleanDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BooleanDTOAssemblerTest {


    @Test
    @DisplayName("Test For createDataTransferObject_DomainObjects()")
    void createDataTransferObject_DomainObjects() {

        // Arrange

        boolean result = true;
        String msg = "Sucess";


        BooleanDTO expectedBooleanDTO = new BooleanDTO(result, msg);

        // Act

        BooleanDTOAssembler booleanDTOAssembler = new BooleanDTOAssembler();
        BooleanDTO booleanDTO = booleanDTOAssembler.createDTOFromPrimitiveTypes(result, msg);

        // Assert

        assertEquals(expectedBooleanDTO, booleanDTO);
        assertEquals(result, booleanDTO.getResult());
        assertEquals(msg, booleanDTO.getMsg());
    }

}