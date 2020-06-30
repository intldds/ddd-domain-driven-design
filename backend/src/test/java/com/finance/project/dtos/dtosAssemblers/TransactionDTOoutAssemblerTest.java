package com.finance.project.dtos.dtosAssemblers;

import com.finance.project.domainLayer.domainEntities.aggregates.account.Account;
import com.finance.project.domainLayer.domainEntities.aggregates.category.Category;
import com.finance.project.domainLayer.domainEntities.aggregates.group.Group;
import com.finance.project.domainLayer.domainEntities.aggregates.ledger.Ledger;
import com.finance.project.domainLayer.domainEntities.aggregates.ledger.Transaction;
import com.finance.project.domainLayer.domainEntities.vosShared.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.finance.project.dtos.dtos.TransactionDTOout;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TransactionDTOoutAssemblerTest {
    @Test
    @DisplayName("Test DTO Assembler Constructor")
    void constructorTest() {
        //Arrange
        //Group House
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

        //Expected Transaction DTO Out
        TransactionDTOout expectedTransactionDTOout = new TransactionDTOout(denominationExpenses, typeTransaction1House, descriptionTransaction1House, amountTransaction1House, dateTransaction1HouseString, houseWalletAccountDenomination, houseEdpAccountDenomination);


        //Act
        TransactionDTOout transactionDTOoutAssembler = TransactionDTOoutAssembler.createTransactionDTOout(transaction1);


        //Assert
        assertEquals(expectedTransactionDTOout, transactionDTOoutAssembler);
        assertEquals(denominationExpenses, transactionDTOoutAssembler.getCategory());
        assertEquals(typeTransaction1House, transactionDTOoutAssembler.getType());
        assertEquals(descriptionTransaction1House, transactionDTOoutAssembler.getDescription());
        assertEquals(amountTransaction1House, transactionDTOoutAssembler.getAmount());
        assertEquals(dateTransaction1HouseString, transactionDTOoutAssembler.getDate());
        assertEquals(houseWalletAccountDenomination, transactionDTOoutAssembler.getDebitAccount());
        assertEquals(houseEdpAccountDenomination, transactionDTOoutAssembler.getCreditAccount());
    }


}