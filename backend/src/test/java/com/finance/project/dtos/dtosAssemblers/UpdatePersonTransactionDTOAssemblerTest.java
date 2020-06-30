package com.finance.project.dtos.dtosAssemblers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.finance.project.dtos.dtos.UpdatePersonTransactionDTO;

import static org.junit.jupiter.api.Assertions.*;

class UpdatePersonTransactionDTOAssemblerTest {

    @Test
    @DisplayName("Test For createDataTransferObject_Primitives()")
    void createDTOFromPrimitiveTypes() {

        //ARRANGE
        //Person info
        String email = "paulo@gmail.com";

        //Transaction info
        int transactionNumber = 1;
        String type = "debit";
        String description = "haircut";
        String denominationCategory = "HairStylist";
        double amount = 150.0;
        String denominationAccountDeb = "DebitAccountJon";
        String denominationAccountCred = "CredditAccountJon";


        //Expected DTO
        UpdatePersonTransactionDTO expectedUpdatePersonTransactionDTO = new UpdatePersonTransactionDTO(transactionNumber, email, denominationCategory, type, description, amount, denominationAccountDeb, denominationAccountCred);


        //ACT
        UpdatePersonTransactionDTO updatePersonTransactionDTO = UpdatePersonTransactionDTOAssembler.createDTOFromPrimitiveTypes(transactionNumber, email, denominationCategory, type, description, amount, denominationAccountDeb, denominationAccountCred);


        //ASSERT
        assertEquals(expectedUpdatePersonTransactionDTO, updatePersonTransactionDTO);
        assertEquals(transactionNumber, updatePersonTransactionDTO.getTransactionNumber());
        assertEquals(email, updatePersonTransactionDTO.getEmail());
        assertEquals(denominationCategory, updatePersonTransactionDTO.getDenominationCategory());
        assertEquals(type, updatePersonTransactionDTO.getType());
        assertEquals(description, updatePersonTransactionDTO.getDescription());
        assertEquals(amount, updatePersonTransactionDTO.getAmount());
        assertEquals(denominationAccountDeb, updatePersonTransactionDTO.getDenominationAccountDeb());
        assertEquals(denominationAccountCred, updatePersonTransactionDTO.getDenominationAccountCred());
    }
}