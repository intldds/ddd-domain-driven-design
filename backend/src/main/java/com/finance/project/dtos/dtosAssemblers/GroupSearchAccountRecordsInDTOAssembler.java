package com.finance.project.dtos.dtosAssemblers;

import com.finance.project.dtos.dtos.GroupSearchAccountRecordsInDTO;

/**
 * DTO Assembler in - For retrieving the list of transactions of the group, by a member,
 * for a given account, within a given period.
 */
public class GroupSearchAccountRecordsInDTOAssembler {

    /**
     * Instantiates a new Group search account records in dto assembler.
     */
    public GroupSearchAccountRecordsInDTOAssembler() {
    }

    /**
     * Creates a DTO (in) for retrieving the group transactions, by a member,
     * for a given account, within a given period.
     *
     * @param personEmail         the person email (group member)
     * @param groupDenomination   the group denomination
     * @param accountDenomination the denomination of the account to search
     * @param startDate           the start date of the period to search
     * @param endDate             the end date of the period to search
     * @return the DTO (in) for retrieving the group transactions, by a member,
     * for a given account, within a given period
     */
    public static GroupSearchAccountRecordsInDTO groupSearchAccountRecordsInDTO(String personEmail, String groupDenomination, String accountDenomination, String startDate, String endDate) {
        GroupSearchAccountRecordsInDTO groupSearchAccountRecordsInDTO = new GroupSearchAccountRecordsInDTO(personEmail, groupDenomination, accountDenomination, startDate, endDate);
        return groupSearchAccountRecordsInDTO;
    }
}
