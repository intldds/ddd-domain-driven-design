package com.finance.project.dtos.dtosAssemblers;

import com.finance.project.domainLayer.domainEntities.aggregates.ledger.Transaction;
import com.finance.project.domainLayer.domainEntities.vosShared.AccountID;
import com.finance.project.domainLayer.domainEntities.vosShared.CategoryID;
import com.finance.project.domainLayer.domainEntities.vosShared.PersonID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.finance.project.dtos.dtos.PersonSearchAccountRecordsOutDTO;
import com.finance.project.dtos.dtos.TransactionDTOout;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonSearchAccountRecordsOutDTOAssemblerTest {

    @Test
    @DisplayName("Test accountTransactionsOutDTO() method")
    void getAccountTransactionsWithinPeriodDTOout() {
        // ARRANGE
        //Person
        String personEmail = "joaquina@gmail.com";
        PersonID personID = PersonID.createPersonID(personEmail);

        // Category Electricity Expenses
        String denominationExpenses = "Electricity Expenses";
        CategoryID categoryID = CategoryID.createCategoryID(denominationExpenses, personID);

        // Account Wallet
        String walletAccountDenomination = "House Funds";
        AccountID walletAccountID = AccountID.createAccountID(walletAccountDenomination, personID);

        // Account EDP
        String edpAccountDenomination = "EDP";
        AccountID edpAccountID = AccountID.createAccountID(edpAccountDenomination, personID);

        // Transaction 1
        String typeTransaction1 = "Debit";
        String descriptionTransaction1 = "EDP bill from January/2020";
        LocalDate dateTransaction1 = LocalDate.of(2020, 02, 01);
        String dateTransaction1String = "2020-02-01";
        double amountTransaction1 = 40.00;
        Transaction transaction1 = Transaction.createTransaction(categoryID, typeTransaction1, descriptionTransaction1, amountTransaction1, dateTransaction1, walletAccountID, edpAccountID);

        // Transaction 2
        String typeTransaction2 = "Credit";
        String descriptionTransaction2 = "EDP bill from February/2020 - settlement - overcharge";
        LocalDate dateTransaction2 = LocalDate.of(2020, 03, 01);
        String dateTransaction2String = "2020-03-01";
        double amountTransaction2 = 15.00;
        Transaction transaction2 = Transaction.createTransaction(categoryID, typeTransaction2, descriptionTransaction2, amountTransaction2, dateTransaction2, edpAccountID, walletAccountID);


        // List of transactions
        ArrayList<Transaction> expectedTransactions = new ArrayList<>();
        expectedTransactions.add(transaction1);
        expectedTransactions.add(transaction2);

        // Expect DTO out
        TransactionDTOout transactionDTOout1 = new TransactionDTOout(denominationExpenses, typeTransaction1, descriptionTransaction1, amountTransaction1, dateTransaction1String, walletAccountDenomination, edpAccountDenomination);
        TransactionDTOout transactionDTOout2 = new TransactionDTOout(denominationExpenses, typeTransaction2, descriptionTransaction2, amountTransaction2, dateTransaction2String, edpAccountDenomination, walletAccountDenomination);
        ArrayList<TransactionDTOout> expectedListTransactionOut = new ArrayList<>();
        expectedListTransactionOut.add(transactionDTOout1);
        expectedListTransactionOut.add(transactionDTOout2);
        PersonSearchAccountRecordsOutDTO expectedPersonSearchAccountRecordsOutDTO = new PersonSearchAccountRecordsOutDTO(expectedListTransactionOut);

        // ACT
        PersonSearchAccountRecordsOutDTO actualResult = SearchAccountRecordsOutDTOAssembler.accountTransactionsOutDTO(expectedTransactions);

        // ASSERT
        assertEquals(expectedPersonSearchAccountRecordsOutDTO, actualResult);
    }
}