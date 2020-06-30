package com.finance.project.dtos.dtosAssemblers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.finance.project.dtos.dtos.DeletePersonTransactionDTO;

import static org.junit.jupiter.api.Assertions.*;

class DeletePersonTransactionDTOAssemblerTest {

    @Test
    @DisplayName("Test For createDataTransferObject_Primitives()")
    void createDataTransferObject_Primitives() {

        //Arrange

        int transactionNumber = 3;
        String email = "francisco@gmail.com";

        DeletePersonTransactionDTO expectedDeletePersonTransactionDTO = new DeletePersonTransactionDTO(transactionNumber, email);

        //Act

        DeletePersonTransactionDTO deletePersonTransactionDTO = DeletePersonTransactionDTOAssembler.createDTOFromPrimitiveTypes(transactionNumber, email);

        //Arrange

        assertEquals(expectedDeletePersonTransactionDTO, deletePersonTransactionDTO);
        assertEquals(transactionNumber, deletePersonTransactionDTO.getTransactionNumber());
        assertEquals(email, deletePersonTransactionDTO.getEmail());

    }

}