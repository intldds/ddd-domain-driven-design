package com.finance.project.dtos.dtosAssemblers;

import com.finance.project.dtos.dtos.UpdatePersonTransactionDTO;

public class UpdatePersonTransactionDTOAssembler {

    private UpdatePersonTransactionDTOAssembler() {
    }

    public static UpdatePersonTransactionDTO createDTOFromPrimitiveTypes(int transactionNumber, String email, String denominationCategory, String type, String description, double amount, String denominationAccountDeb, String denominationAccountCred) {
        UpdatePersonTransactionDTO updatePersonTransactionDTO = new UpdatePersonTransactionDTO(transactionNumber, email, denominationCategory, type, description, amount, denominationAccountDeb, denominationAccountCred);
        return updatePersonTransactionDTO;

    }

}
