package com.finance.project.dtos.dtosAssemblers;

import com.finance.project.dtos.dtos.DeletePersonTransactionDTO;

public class DeletePersonTransactionDTOAssembler {

    private DeletePersonTransactionDTOAssembler() {
    }

    public static DeletePersonTransactionDTO createDTOFromPrimitiveTypes(int transactionNumber, String email) {
        DeletePersonTransactionDTO deletePersonTransactionDTO = new DeletePersonTransactionDTO(transactionNumber, email);
        return deletePersonTransactionDTO;

    }

}
