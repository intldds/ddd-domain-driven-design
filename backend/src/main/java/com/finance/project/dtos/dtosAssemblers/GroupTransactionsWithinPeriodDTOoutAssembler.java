package com.finance.project.dtos.dtosAssemblers;

import com.finance.project.dtos.dtos.GroupTransactionsWithinPeriodDTOout;
import com.finance.project.dtos.dtos.TransactionDTOout;
import com.finance.project.domainLayer.domainEntities.aggregates.ledger.Transaction;

import java.util.ArrayList;


public final class GroupTransactionsWithinPeriodDTOoutAssembler {

    private GroupTransactionsWithinPeriodDTOoutAssembler() {
    }


    public static GroupTransactionsWithinPeriodDTOout getGroupTransactionsWithinPeriodDTOout(ArrayList<Transaction> transactions) {

        GroupTransactionsWithinPeriodDTOout groupTransactionsWithinPeriodDTOout = new GroupTransactionsWithinPeriodDTOout();
        for (Transaction transaction : transactions) {
            TransactionDTOout transactionDTOout = TransactionDTOoutAssembler.createTransactionDTOout(transaction);
            groupTransactionsWithinPeriodDTOout.getTransactionsList().add(transactionDTOout);
        }
        return groupTransactionsWithinPeriodDTOout;
    }
}
