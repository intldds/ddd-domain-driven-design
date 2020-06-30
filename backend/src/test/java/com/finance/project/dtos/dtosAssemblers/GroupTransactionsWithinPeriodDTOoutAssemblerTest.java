package com.finance.project.dtos.dtosAssemblers;

import com.finance.project.domainLayer.domainEntities.aggregates.account.Account;
import com.finance.project.domainLayer.domainEntities.aggregates.category.Category;
import com.finance.project.domainLayer.domainEntities.aggregates.group.Group;
import com.finance.project.domainLayer.domainEntities.aggregates.ledger.Ledger;
import com.finance.project.domainLayer.domainEntities.aggregates.ledger.Transaction;
import com.finance.project.domainLayer.domainEntities.vosShared.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.finance.project.dtos.dtos.GroupTransactionsWithinPeriodDTOout;
import com.finance.project.dtos.dtos.TransactionDTOout;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GroupTransactionsWithinPeriodDTOoutAssemblerTest {

    @Test
    @DisplayName("Test getGroupTransactionsWithinPeriodDTOout() method")
    void getGroupTransactionsWithinPeriodDTOout() {
        //Arrange
        // Group House
        String denominationHouse = "House";
        String emailPersonHouse = "joao@gmail.com";
        PersonID peopleInChargeHouse = PersonID.createPersonID(emailPersonHouse);
        String descriptionHouse = "People who share house";
        LocalDate dateOfCreationHouse = LocalDate.of(2020, 01, 01);
        Ledger ledgerHouse = Ledger.createLedger();
        LedgerID ledgerIDHouse = ledgerHouse.getLedgerID();
        Group house = Group.createGroupAsPersonInCharge(denominationHouse, peopleInChargeHouse, descriptionHouse, dateOfCreationHouse, ledgerIDHouse);
        GroupID houseID = house.getGroupID();

        //Category Electricity Expenses (Group House)
        String denominationExpenses = "Electricity Expenses";
        Category categoryExpenses = Category.createCategory(denominationExpenses, houseID);
        CategoryID categoryExpensesID = categoryExpenses.getCategoryID();

        //Account House Wallet (Group House)
        String houseWalletAccountDenomination = "House Funds";
        String houseWalletAccountDescription = "Money to spend with House expenses";
        Account houseWallet = Account.createAccount(houseWalletAccountDescription, houseWalletAccountDenomination, houseID);
        AccountID houseWalletID = houseWallet.getAccountID();

        //Account EDP (Group House)
        String houseEdpAccountDenomination = "EDP";
        String houseEdpAccountDescription = "Electricity expenses from EDP";
        Account houseEdp = Account.createAccount(houseEdpAccountDescription, houseEdpAccountDenomination, houseID);
        AccountID houseEdpID = houseEdp.getAccountID();

        //Transaction 1 on Group House
        String typeTransaction1House = "Debit";
        String descriptionTransaction1House = "EDP bill from January/2020";
        LocalDate dateTransaction1House = LocalDate.of(2020, 02, 01);
        String dateTransaction1HouseString = "2020-02-01";
        double amountTransaction1House = 40.00;
        Transaction transaction1 = Transaction.createTransaction(categoryExpensesID, typeTransaction1House, descriptionTransaction1House, amountTransaction1House, dateTransaction1House, houseWalletID, houseEdpID);

        //Transaction 2 on Group House
        String typeTransaction1House2 = "Debit";
        String descriptionTransaction1House2 = "EDP bill from February/2020";
        LocalDate dateTransaction1House2 = LocalDate.of(2020, 03, 01);
        String dateTransaction1HouseString2 = "2020-03-01";
        double amountTransaction1House2 = 45.00;
        Transaction transaction2 = Transaction.createTransaction(categoryExpensesID, typeTransaction1House2, descriptionTransaction1House2, amountTransaction1House2, dateTransaction1House2, houseWalletID, houseEdpID);

        //ArrayList of transactions
        ArrayList<Transaction> transactionsArrayList = new ArrayList<>();
        transactionsArrayList.add(transaction1);
        transactionsArrayList.add(transaction2);

        //Expected DTO output
        TransactionDTOout transactionDTOout1 = new TransactionDTOout(denominationExpenses, typeTransaction1House, descriptionTransaction1House, amountTransaction1House, dateTransaction1HouseString, houseWalletAccountDenomination, houseEdpAccountDenomination);
        TransactionDTOout transactionDTOout2 = new TransactionDTOout(denominationExpenses, typeTransaction1House2, descriptionTransaction1House2, amountTransaction1House2, dateTransaction1HouseString2, houseWalletAccountDenomination, houseEdpAccountDenomination);
        ArrayList<TransactionDTOout> expectedListTransactionOut = new ArrayList<>();
        expectedListTransactionOut.add(transactionDTOout1);
        expectedListTransactionOut.add(transactionDTOout2);

        GroupTransactionsWithinPeriodDTOout expectedGroupTransactionsWithinPeriodDTOout = new GroupTransactionsWithinPeriodDTOout(expectedListTransactionOut);


        //Act
        GroupTransactionsWithinPeriodDTOout actualResult = GroupTransactionsWithinPeriodDTOoutAssembler.getGroupTransactionsWithinPeriodDTOout(transactionsArrayList);


        //Assert
        assertEquals(expectedGroupTransactionsWithinPeriodDTOout, actualResult);
    }
}