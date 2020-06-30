package com.finance.project.dtos.dtosAssemblers;

import com.finance.project.dtos.dtos.SearchAccountRecordsOutDTO;
import com.finance.project.dtos.dtos.TransactionDTOout;
import com.finance.project.domainLayer.domainEntities.aggregates.ledger.Transaction;

import java.util.ArrayList;

/**
 * DTO Assembler out - Info for delivering the list of transactions of a person, for a given account, within a given period.
 */

public class SearchAccountRecordsOutDTOAssembler {

    private SearchAccountRecordsOutDTOAssembler() {
    }

    /**
     * Creates a DTO (out) for delivering a person's transactions, for a given account, within a given period.
     *
     * @param transactions           the list of transactions
     * @return Assembles the DTO (out) for delivering a person's transactions, for a given account, within a given period
     */
    public static SearchAccountRecordsOutDTO accountTransactionsOutDTO(ArrayList<Transaction> transactions) {

        SearchAccountRecordsOutDTO searchAccountRecordsOutDTO = new SearchAccountRecordsOutDTO();

        for (Transaction transaction : transactions) {
            TransactionDTOout transactionDTOout = TransactionDTOoutAssembler.createTransactionDTOout(transaction);
            searchAccountRecordsOutDTO.getTransactions().add(transactionDTOout);
        }
        return searchAccountRecordsOutDTO;
    }
}
