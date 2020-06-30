package com.finance.project.dtos.dtosAssemblers;

import com.finance.project.dtos.dtos.GroupTransactionsWithinPeriodDTOin;

import java.time.LocalDate;

/**
 * The type Group transactions within period dt oin assembler.
 */
public class GroupTransactionsWithinPeriodDTOinAssembler {

    /**
     * Create group transactions within period dt oin group transactions within period dt oin.
     *
     * @param personEmail       the person email
     * @param groupDenomination the group denomination
     * @param startDate         the start date
     * @param endDate           the end date
     * @return the group transactions within period dt oin
     */
    public static GroupTransactionsWithinPeriodDTOin createGroupTransactionsWithinPeriodDTOin(String personEmail, String groupDenomination, LocalDate startDate, LocalDate endDate){
        GroupTransactionsWithinPeriodDTOin groupTransactionsWithinPeriodDTOin = new GroupTransactionsWithinPeriodDTOin(personEmail, groupDenomination,startDate,endDate);
        return groupTransactionsWithinPeriodDTOin;
    }

}
