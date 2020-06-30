package com.finance.project.dtos.dtosAssemblers;

import com.finance.project.domainLayer.domainEntities.aggregates.account.Account;
import com.finance.project.domainLayer.domainEntities.aggregates.category.Category;
import com.finance.project.domainLayer.domainEntities.aggregates.group.Group;
import com.finance.project.domainLayer.domainEntities.aggregates.ledger.Ledger;
import com.finance.project.domainLayer.domainEntities.vosShared.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.finance.project.dtos.dtos.TransactionDTOout;
import com.finance.project.dtos.dtos.TransactionsDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TransactionsDTOAssemblerTest {

    @Test
    @DisplayName("TransactionsDTOAssembler - Test create AccountsDTO from domain objects")
    void transactionsDTOAssembler_createDTOFromDomainObject(){

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
        double amountTransaction1House = 40.00;

            //TransactionDTO out
        TransactionDTOout transactionDTOout = new TransactionDTOout(denominationExpenses, typeTransaction1House, descriptionTransaction1House, amountTransaction1House, dateTransaction1House.toString(), houseWalletAccountDenomination, houseEdpAccountDenomination);

        List<TransactionDTOout> transactions = new ArrayList<>();
        transactions.add(transactionDTOout);

        //Act
        TransactionsDTOAssembler transactionsDTOAssembler = new TransactionsDTOAssembler();
        TransactionsDTO transactionsDTO = transactionsDTOAssembler.createDTOFromPrimitiveTypes(transactions);
        
        TransactionsDTO transactionsDTOExpected = new TransactionsDTO(transactions);

        //Expected

        //Assert
        assertEquals(transactionsDTOExpected , transactionsDTO);
    }

}