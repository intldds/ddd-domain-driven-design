package com.finance.project.dtos.dtosAssemblers;

import com.finance.project.dtos.dtos.PersonSearchAccountRecordsInDTO;

/**
 * DTO Assembler in - For retrieving the list of transactions of a person, for a given account, within a given period.
 */

public class PersonSearchAccountRecordsInDTOAssembler {

    private PersonSearchAccountRecordsInDTOAssembler() {
    }

    /**
     * Creates a DTO (in) for retrieving a person's transactions, for a given account, within a given period.
     *
     * @param personEmail         the person email
     * @param accountDenomination the denomination of the account to search
     * @param startDate           the start date of the period to search
     * @param endDate             the end date of the period to search
     * @return Assembles the DTO (in) for retrieving a person's transactions, for a given account, within a given period
     */
    public static PersonSearchAccountRecordsInDTO personAccountTransactionsInDTO(String personEmail, String accountDenomination, String startDate, String endDate) {
        PersonSearchAccountRecordsInDTO personSearchAccountRecordsInDTO = new PersonSearchAccountRecordsInDTO(personEmail, accountDenomination, startDate, endDate);
        return personSearchAccountRecordsInDTO;
    }

}
