package com.finance.project.dtos.dtosAssemblers;

import com.finance.project.dtos.dtos.GroupTransactionsWithinPeriodDTOout;
import com.finance.project.dtos.dtos.TransactionDTOout;
import com.finance.project.domainLayer.domainEntities.aggregates.ledger.Transaction;

import java.util.ArrayList;

/**
 * The type Group transactions within period dt oout assembler.
 */
public final class GroupTransactionsWithinPeriodDTOoutAssembler {

    private GroupTransactionsWithinPeriodDTOoutAssembler() {
    }

    /**
     * Gets group transactions within period dt oout.
     *
     * @param transactions           the transactions
     * @return the group transactions within period dt oout
     */
    public static GroupTransactionsWithinPeriodDTOout getGroupTransactionsWithinPeriodDTOout(ArrayList<Transaction> transactions) {

        GroupTransactionsWithinPeriodDTOout groupTransactionsWithinPeriodDTOout = new GroupTransactionsWithinPeriodDTOout();
        for (Transaction transaction : transactions) {
            TransactionDTOout transactionDTOout = TransactionDTOoutAssembler.createTransactionDTOout(transaction);
            groupTransactionsWithinPeriodDTOout.getTransactionsList().add(transactionDTOout);
        }
        return groupTransactionsWithinPeriodDTOout;
    }
}
