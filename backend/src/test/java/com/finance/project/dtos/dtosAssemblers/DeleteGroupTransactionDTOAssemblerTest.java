package com.finance.project.dtos.dtosAssemblers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.finance.project.dtos.dtos.DeleteGroupTransactionDTO;

import static org.junit.jupiter.api.Assertions.*;

class DeleteGroupTransactionDTOAssemblerTest {

    @Test
    @DisplayName("Test For createDataTransferObject_Primitives")
    void createDataTransferObject_Primitives() {

        //Arrange

        int transactionNumber = 3;
        String denomination = "Runners";
        String email = "pp@gmail.com";

        DeleteGroupTransactionDTO expectedDeleteGroupTransactionDTO = new DeleteGroupTransactionDTO(transactionNumber, denomination, email);

        //Act

        DeleteGroupTransactionDTO deleteGroupTransactionDTO = DeleteGroupTransactionDTOAssembler.createDTOFromPrimitiveTypes(transactionNumber, denomination, email);

        //Arrange

        assertEquals(expectedDeleteGroupTransactionDTO, deleteGroupTransactionDTO);
        assertEquals(transactionNumber, deleteGroupTransactionDTO.getTransactionNumber());
        assertEquals(denomination, deleteGroupTransactionDTO.getGroupDenomination());
        assertEquals(email, deleteGroupTransactionDTO.getPersonGroupMemberEmail());

    }

    @Test
    @DisplayName("Test For createDataTransferObject_Primitives: Instantiates Assembler")
    void createDataTransferObject_Primitives_InstatiatesAssembler() {

        //Arrange

        int transactionNumber = 3;
        String denomination = "Runners";
        String email = "pp@gmail.com";

        DeleteGroupTransactionDTO expectedDeleteGroupTransactionDTO = new DeleteGroupTransactionDTO(transactionNumber, denomination, email);

        //Act
        DeleteGroupTransactionDTOAssembler deleteGroupTransactionDTOAssembler = new DeleteGroupTransactionDTOAssembler();
        DeleteGroupTransactionDTO deleteGroupTransactionDTO = deleteGroupTransactionDTOAssembler.createDTOFromPrimitiveTypes(transactionNumber, denomination, email);

        //Arrange

        assertEquals(expectedDeleteGroupTransactionDTO, deleteGroupTransactionDTO);
        assertEquals(transactionNumber, deleteGroupTransactionDTO.getTransactionNumber());
        assertEquals(denomination, deleteGroupTransactionDTO.getGroupDenomination());
        assertEquals(email, deleteGroupTransactionDTO.getPersonGroupMemberEmail());

    }

}