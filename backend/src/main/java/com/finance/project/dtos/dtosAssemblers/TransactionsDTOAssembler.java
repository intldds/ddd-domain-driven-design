package com.finance.project.dtos.dtosAssemblers;

import com.finance.project.dtos.dtos.TransactionDTOout;
import com.finance.project.dtos.dtos.TransactionsDTO;

import java.util.List;

public class TransactionsDTOAssembler {

    public static TransactionsDTO createDTOFromPrimitiveTypes(List<TransactionDTOout> transactions) {

        TransactionsDTO transactionsDTO = new TransactionsDTO(transactions);
        return transactionsDTO;
    }
}
