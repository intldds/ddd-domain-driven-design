package com.finance.project.dtos.dtosAssemblers;

import com.finance.project.dtos.dtos.CreatePersonTransactionDTO;

/**
 * The type Create person transaction dto assembler.
 */
public class CreatePersonTransactionDTOAssembler {

    private CreatePersonTransactionDTOAssembler() {
    }

    /**
     * Create dto from primitve types create person transaction dto.
     *
     * @param email                   the email
     * @param denominationCategory    the denomination category
     * @param type                    the type
     * @param description             the description
     * @param amount                  the amount
     * @param denominationAccountDeb  the denomination account deb
     * @param denominationAccountCred the denomination account cred
     * @return the create person transaction dto
     */
    public static CreatePersonTransactionDTO createDTOFromPrimitiveTypes(String email, String denominationCategory, String type, String description, double amount, String denominationAccountDeb, String denominationAccountCred, String date) {
        CreatePersonTransactionDTO createPersonTransactionDTO = new CreatePersonTransactionDTO(email, denominationCategory, type, description, amount, denominationAccountDeb, denominationAccountCred, date);
        return createPersonTransactionDTO;
    }

}
