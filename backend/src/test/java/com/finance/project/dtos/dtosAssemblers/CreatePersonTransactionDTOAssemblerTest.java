package com.finance.project.dtos.dtosAssemblers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.finance.project.dtos.dtos.CreatePersonTransactionDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreatePersonTransactionDTOAssemblerTest {

    @Test
    @DisplayName("Test For createDataTransferObject_Primitives()")
    void createDTOFromPrimitiveTypes() {

        // Arrange

        String email = "paulo@gmail.com";
        String denominationCategory = "HairStylist";
        String type = "debit";
        String description = "Pente0";
        double amount = 150.0;
        String denominationAccountDeb = "DebitAccountJon";
        String denominationAccountCred = "CredditAccountJon";
        String date = "2020-03-05";

        CreatePersonTransactionDTO expected_CreatePersonTransactionDTO = new CreatePersonTransactionDTO(email, denominationCategory, type, description, amount, denominationAccountDeb, denominationAccountCred, date);

        // Act

        CreatePersonTransactionDTO createPersonTransactionDTO = CreatePersonTransactionDTOAssembler.createDTOFromPrimitiveTypes(email, denominationCategory, type, description, amount, denominationAccountDeb, denominationAccountCred, date);

        // Assert

        assertEquals(expected_CreatePersonTransactionDTO, createPersonTransactionDTO);
        assertEquals(email, createPersonTransactionDTO.getEmail());
        assertEquals(denominationCategory, createPersonTransactionDTO.getDenominationCategory());
        assertEquals(type, createPersonTransactionDTO.getType());
        assertEquals(description, createPersonTransactionDTO.getDescription());
        assertEquals(amount, createPersonTransactionDTO.getAmount());
        assertEquals(denominationAccountDeb, createPersonTransactionDTO.getDenominationAccountDeb());
        assertEquals(denominationAccountCred, createPersonTransactionDTO.getDenominationAccountCred());

    }
}