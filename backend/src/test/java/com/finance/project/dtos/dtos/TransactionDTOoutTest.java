package com.finance.project.dtos.dtos;

import com.finance.project.domainLayer.domainEntities.aggregates.account.Account;
import com.finance.project.domainLayer.domainEntities.aggregates.category.Category;
import com.finance.project.domainLayer.domainEntities.aggregates.group.Group;
import com.finance.project.domainLayer.domainEntities.aggregates.ledger.Ledger;
import com.finance.project.domainLayer.domainEntities.vosShared.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TransactionDTOoutTest {
    @Test
    @DisplayName("Test DTO Constructor")
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
        double amountTransaction1House = 40.00;


        //Act
        TransactionDTOout transactionDTOout = new TransactionDTOout(denominationExpenses, typeTransaction1House, descriptionTransaction1House, amountTransaction1House, dateTransaction1House.toString(), houseWalletAccountDenomination, houseEdpAccountDenomination);


        //Assert
        assertEquals(denominationExpenses, transactionDTOout.getCategory());
        assertEquals(typeTransaction1House, transactionDTOout.getType());
        assertEquals(descriptionTransaction1House, transactionDTOout.getDescription());
        assertEquals(amountTransaction1House, transactionDTOout.getAmount());
        assertEquals(dateTransaction1House.toString(), transactionDTOout.getDate());
        assertEquals(houseWalletAccountDenomination, transactionDTOout.getDebitAccount());
        assertEquals(houseEdpAccountDenomination, transactionDTOout.getCreditAccount());
    }

    @Test
    @DisplayName("Test empty constructor")
    void constructorEmptyTest() {
        //Arrange
        String category = "";
        String type = "";
        String description = "";
        double amount = 0;
        String date = "";
        String debitAccount = "";
        String creditAccount = "";

        //Act
        TransactionDTOout transactionDTOout = new TransactionDTOout();
        transactionDTOout.setCategory(category);
        transactionDTOout.setType(type);
        transactionDTOout.setDescription(description);
        transactionDTOout.setAmount(amount);
        transactionDTOout.setDate(date);
        transactionDTOout.setDebitAccount(debitAccount);
        transactionDTOout.setCreditAccount(creditAccount);

        //Assert
        assertEquals(category, transactionDTOout.getCategory());
        assertEquals(type, transactionDTOout.getType());
        assertEquals(description, transactionDTOout.getDescription());
        assertEquals(amount, transactionDTOout.getAmount());
        assertEquals(date, transactionDTOout.getDate());
        assertEquals(debitAccount, transactionDTOout.getDebitAccount());
        assertEquals(creditAccount, transactionDTOout.getCreditAccount());
    }

    @Test
    @DisplayName("Test DTO Getters - getCategory()")
    void getCategory() {
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


        //Act
        TransactionDTOout transactionDTOout = new TransactionDTOout(denominationExpenses, typeTransaction1House, descriptionTransaction1House, amountTransaction1House, dateTransaction1House.toString(), houseWalletAccountDenomination, houseEdpAccountDenomination);


        //Assert
        assertEquals(denominationExpenses, transactionDTOout.getCategory());
    }

    @Test
    @DisplayName("Test DTO Getters - getType()")
    void getType() {
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


        //Act
        TransactionDTOout transactionDTOout = new TransactionDTOout(denominationExpenses, typeTransaction1House, descriptionTransaction1House, amountTransaction1House, dateTransaction1House.toString(), houseWalletAccountDenomination, houseEdpAccountDenomination);


        //Assert
        assertEquals(typeTransaction1House, transactionDTOout.getType());
    }

    @Test
    @DisplayName("Test DTO Getters - getDescription()")
    void getDescription() {
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


        //Act
        TransactionDTOout transactionDTOout = new TransactionDTOout(denominationExpenses, typeTransaction1House, descriptionTransaction1House, amountTransaction1House, dateTransaction1House.toString(), houseWalletAccountDenomination, houseEdpAccountDenomination);


        //Assert
        assertEquals(descriptionTransaction1House, transactionDTOout.getDescription());
    }

    @Test
    @DisplayName("Test DTO Getters - getAmount()")
    void getAmount() {
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


        //Act
        TransactionDTOout transactionDTOout = new TransactionDTOout(denominationExpenses, typeTransaction1House, descriptionTransaction1House, amountTransaction1House, dateTransaction1House.toString(), houseWalletAccountDenomination, houseEdpAccountDenomination);


        //Assert
        assertEquals(amountTransaction1House, transactionDTOout.getAmount());
    }

    @Test
    @DisplayName("Test DTO Getters - getDate()")
    void getDate() {
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


        //Act
        TransactionDTOout transactionDTOout = new TransactionDTOout(denominationExpenses, typeTransaction1House, descriptionTransaction1House, amountTransaction1House, dateTransaction1House.toString(), houseWalletAccountDenomination, houseEdpAccountDenomination);


        //Assert
        assertEquals(dateTransaction1House.toString(), transactionDTOout.getDate());
    }

    @Test
    @DisplayName("Test DTO Getters - getDebitAccount()")
    void getDebitAccount() {
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


        //Act
        TransactionDTOout transactionDTOout = new TransactionDTOout(denominationExpenses, typeTransaction1House, descriptionTransaction1House, amountTransaction1House, dateTransaction1House.toString(), houseWalletAccountDenomination, houseEdpAccountDenomination);


        //Assert
        assertEquals(houseWalletAccountDenomination, transactionDTOout.getDebitAccount());
    }

    @Test
    @DisplayName("Test DTO Getters - getCreditAccount()")
    void getCreditAccount() {
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


        //Act
        TransactionDTOout transactionDTOout = new TransactionDTOout(denominationExpenses, typeTransaction1House, descriptionTransaction1House, amountTransaction1House, dateTransaction1House.toString(), houseWalletAccountDenomination, houseEdpAccountDenomination);


        //Assert
        assertEquals(houseEdpAccountDenomination, transactionDTOout.getCreditAccount());
    }

    @Test
    @DisplayName("Test for equals() and hasCode() - Happy path")
    void testEqualsAndHashCode_HappyPath() {
        //Arrange
        //Group House 1
        String denominationHouse1 = "House";
        String emailPersonHouse1 = "joao@gmail.com";
        PersonID peopleInChargeHouse1 = PersonID.createPersonID(emailPersonHouse1);
        String descriptionHouse1 = "People who share house";
        LocalDate dateOfCreationHouse1 = LocalDate.of(2020, 01, 01);
        Ledger ledgerHouse1 = Ledger.createLedger();
        LedgerID ledgerIDHouse1 = ledgerHouse1.getLedgerID();
        Group house1 = Group.createGroupAsPersonInCharge(denominationHouse1, peopleInChargeHouse1, descriptionHouse1, dateOfCreationHouse1, ledgerIDHouse1);
        GroupID houseID1 = house1.getGroupID();

        //Category Electricity Expenses (Group House 1)
        String denominationExpenses1 = "Electricity Expenses";
        Category categoryExpenses1 = Category.createCategory(denominationExpenses1, houseID1);
        CategoryID categoryExpensesID1 = categoryExpenses1.getCategoryID();

        //Account House Wallet (Group House 1)
        String houseWalletAccountDenomination1 = "House Funds";
        String houseWalletAccountDescription1 = "Money to spend with House expenses";
        Account houseWallet1 = Account.createAccount(houseWalletAccountDescription1, houseWalletAccountDenomination1, houseID1);
        AccountID houseWalletID1 = houseWallet1.getAccountID();

        //Account EDP (Group House 1)
        String houseEdpAccountDenomination1 = "EDP";
        String houseEdpAccountDescription1 = "Electricity expenses from EDP";
        Account houseEdp1 = Account.createAccount(houseEdpAccountDescription1, houseEdpAccountDenomination1, houseID1);
        AccountID houseEdpID1 = houseEdp1.getAccountID();

        //Transaction 1 on Group House 1
        String typeTransaction1House1 = "Debit";
        String descriptionTransaction1House1 = "EDP bill from January/2020";
        LocalDate dateTransaction1House1 = LocalDate.of(2020, 02, 01);
        double amountTransaction1House1 = 40.00;

        //Transaction DTO out 1
        TransactionDTOout transactionDTOout1 = new TransactionDTOout(denominationExpenses1, typeTransaction1House1, descriptionTransaction1House1, amountTransaction1House1, dateTransaction1House1.toString(), houseWalletAccountDenomination1, houseEdpAccountDenomination1);

        //Group House 2
        String denominationHouse2 = "House";
        String emailPersonHouse2 = "joao@gmail.com";
        PersonID peopleInChargeHouse2 = PersonID.createPersonID(emailPersonHouse2);
        String descriptionHouse2 = "People who share house";
        LocalDate dateOfCreationHouse2 = LocalDate.of(2020, 01, 01);
        Ledger ledgerHouse2 = Ledger.createLedger();
        LedgerID ledgerIDHouse2 = ledgerHouse2.getLedgerID();
        Group house2 = Group.createGroupAsPersonInCharge(denominationHouse2, peopleInChargeHouse2, descriptionHouse2, dateOfCreationHouse2, ledgerIDHouse2);
        GroupID houseID2 = house2.getGroupID();

        //Category Electricity Expenses (Group House 2)
        String denominationExpenses2 = "Electricity Expenses";
        Category categoryExpenses2 = Category.createCategory(denominationExpenses2, houseID2);
        CategoryID categoryExpensesID2 = categoryExpenses2.getCategoryID();

        //Account House Wallet (Group House 2)
        String houseWalletAccountDenomination2 = "House Funds";
        String houseWalletAccountDescription2 = "Money to spend with House expenses";
        Account houseWallet2 = Account.createAccount(houseWalletAccountDescription2, houseWalletAccountDenomination2, houseID2);
        AccountID houseWalletID2 = houseWallet2.getAccountID();

        //Account EDP (Group House 2)
        String houseEdpAccountDenomination2 = "EDP";
        String houseEdpAccountDescription2 = "Electricity expenses from EDP";
        Account houseEdp2 = Account.createAccount(houseEdpAccountDescription2, houseEdpAccountDenomination2, houseID2);
        AccountID houseEdpID2 = houseEdp2.getAccountID();

        //Transaction 1 on Group House 2
        String typeTransaction1House2 = "Debit";
        String descriptionTransaction1House2 = "EDP bill from January/2020";
        LocalDate dateTransaction1House2 = LocalDate.of(2020, 02, 01);
        double amountTransaction1House2 = 40.00;

        //Transaction DTO out 2
        TransactionDTOout transactionDTOout2 = new TransactionDTOout(denominationExpenses2, typeTransaction1House2, descriptionTransaction1House2, amountTransaction1House2, dateTransaction1House2.toString(), houseWalletAccountDenomination2, houseEdpAccountDenomination2);


        //Act
        boolean resultEquals = transactionDTOout1.equals(transactionDTOout2);
        boolean resultHashCode = (transactionDTOout1.hashCode() == transactionDTOout2.hashCode());


        //Assert
        assertEquals(true, resultEquals);
        assertEquals(true, resultHashCode);
    }

    @Test
    @DisplayName("Test for equals() and hasCode() - Same Object")
    void testEqualsAndHashCode_SameObject() {
        //Arrange
        //Group House 1
        String denominationHouse1 = "House";
        String emailPersonHouse1 = "joao@gmail.com";
        PersonID peopleInChargeHouse1 = PersonID.createPersonID(emailPersonHouse1);
        String descriptionHouse1 = "People who share house";
        LocalDate dateOfCreationHouse1 = LocalDate.of(2020, 01, 01);
        Ledger ledgerHouse1 = Ledger.createLedger();
        LedgerID ledgerIDHouse1 = ledgerHouse1.getLedgerID();
        Group house1 = Group.createGroupAsPersonInCharge(denominationHouse1, peopleInChargeHouse1, descriptionHouse1, dateOfCreationHouse1, ledgerIDHouse1);
        GroupID houseID1 = house1.getGroupID();

        //Category Electricity Expenses (Group House 1)
        String denominationExpenses1 = "Electricity Expenses";
        Category categoryExpenses1 = Category.createCategory(denominationExpenses1, houseID1);
        CategoryID categoryExpensesID1 = categoryExpenses1.getCategoryID();

        //Account House Wallet (Group House 1)
        String houseWalletAccountDenomination1 = "House Funds";
        String houseWalletAccountDescription1 = "Money to spend with House expenses";
        Account houseWallet1 = Account.createAccount(houseWalletAccountDescription1, houseWalletAccountDenomination1, houseID1);
        AccountID houseWalletID1 = houseWallet1.getAccountID();

        //Account EDP (Group House 1)
        String houseEdpAccountDenomination1 = "EDP";
        String houseEdpAccountDescription1 = "Electricity expenses from EDP";
        Account houseEdp1 = Account.createAccount(houseEdpAccountDescription1, houseEdpAccountDenomination1, houseID1);
        AccountID houseEdpID1 = houseEdp1.getAccountID();

        //Transaction 1 on Group House 1
        String typeTransaction1House1 = "Debit";
        String descriptionTransaction1House1 = "EDP bill from January/2020";
        LocalDate dateTransaction1House1 = LocalDate.of(2020, 02, 01);
        double amountTransaction1House1 = 40.00;

        //Transaction DTO out 1
        TransactionDTOout transactionDTOout1 = new TransactionDTOout(denominationExpenses1, typeTransaction1House1, descriptionTransaction1House1, amountTransaction1House1, dateTransaction1House1.toString(), houseWalletAccountDenomination1, houseEdpAccountDenomination1);


        //Act
        boolean resultEquals = transactionDTOout1.equals(transactionDTOout1);
        boolean resultHashCode = (transactionDTOout1.hashCode() == transactionDTOout1.hashCode());


        //Assert
        assertEquals(true, resultEquals);
        assertEquals(true, resultHashCode);
    }

    @Test
    @DisplayName("Test for equals() and hasCode() - Fail (null object)")
    void testEquals_FailNullObject() {
        //Arrange
        //Group House 1
        String denominationHouse1 = "House";
        String emailPersonHouse1 = "joao@gmail.com";
        PersonID peopleInChargeHouse1 = PersonID.createPersonID(emailPersonHouse1);
        String descriptionHouse1 = "People who share house";
        LocalDate dateOfCreationHouse1 = LocalDate.of(2020, 01, 01);
        Ledger ledgerHouse1 = Ledger.createLedger();
        LedgerID ledgerIDHouse1 = ledgerHouse1.getLedgerID();
        Group house1 = Group.createGroupAsPersonInCharge(denominationHouse1, peopleInChargeHouse1, descriptionHouse1, dateOfCreationHouse1, ledgerIDHouse1);
        GroupID houseID1 = house1.getGroupID();

        //Category Electricity Expenses (Group House 1)
        String denominationExpenses1 = "Electricity Expenses";
        Category categoryExpenses1 = Category.createCategory(denominationExpenses1, houseID1);
        CategoryID categoryExpensesID1 = categoryExpenses1.getCategoryID();

        //Account House Wallet (Group House 1)
        String houseWalletAccountDenomination1 = "House Funds";
        String houseWalletAccountDescription1 = "Money to spend with House expenses";
        Account houseWallet1 = Account.createAccount(houseWalletAccountDescription1, houseWalletAccountDenomination1, houseID1);
        AccountID houseWalletID1 = houseWallet1.getAccountID();

        //Account EDP (Group House 1)
        String houseEdpAccountDenomination1 = "EDP";
        String houseEdpAccountDescription1 = "Electricity expenses from EDP";
        Account houseEdp1 = Account.createAccount(houseEdpAccountDescription1, houseEdpAccountDenomination1, houseID1);
        AccountID houseEdpID1 = houseEdp1.getAccountID();

        //Transaction 1 on Group House 1
        String typeTransaction1House1 = "Debit";
        String descriptionTransaction1House1 = "EDP bill from January/2020";
        LocalDate dateTransaction1House1 = LocalDate.of(2020, 02, 01);
        double amountTransaction1House1 = 40.00;

        //Transaction DTO out 1
        TransactionDTOout transactionDTOout1 = new TransactionDTOout(denominationExpenses1, typeTransaction1House1, descriptionTransaction1House1, amountTransaction1House1, dateTransaction1House1.toString(), houseWalletAccountDenomination1, houseEdpAccountDenomination1);

        //Transaction DTO out 2
        TransactionDTOout transactionDTOout2 = null;


        //Act
        boolean resultEquals = transactionDTOout1.equals(transactionDTOout2);


        //Assert
        assertEquals(false, resultEquals);
    }

    @Test
    @DisplayName("Test for equals() and hasCode() - Fail (different class)")
    void testEqualsAndHashCode_FailDiffClass() {
        //Arrange
        //Group House 1
        String denominationHouse1 = "House";
        String emailPersonHouse1 = "joao@gmail.com";
        PersonID peopleInChargeHouse1 = PersonID.createPersonID(emailPersonHouse1);
        String descriptionHouse1 = "People who share house";
        LocalDate dateOfCreationHouse1 = LocalDate.of(2020, 01, 01);
        Ledger ledgerHouse1 = Ledger.createLedger();
        LedgerID ledgerIDHouse1 = ledgerHouse1.getLedgerID();
        Group house1 = Group.createGroupAsPersonInCharge(denominationHouse1, peopleInChargeHouse1, descriptionHouse1, dateOfCreationHouse1, ledgerIDHouse1);
        GroupID houseID1 = house1.getGroupID();

        //Category Electricity Expenses (Group House 1)
        String denominationExpenses1 = "Electricity Expenses";
        Category categoryExpenses1 = Category.createCategory(denominationExpenses1, houseID1);
        CategoryID categoryExpensesID1 = categoryExpenses1.getCategoryID();

        //Account House Wallet (Group House 1)
        String houseWalletAccountDenomination1 = "House Funds";
        String houseWalletAccountDescription1 = "Money to spend with House expenses";
        Account houseWallet1 = Account.createAccount(houseWalletAccountDescription1, houseWalletAccountDenomination1, houseID1);
        AccountID houseWalletID1 = houseWallet1.getAccountID();

        //Account EDP (Group House 1)
        String houseEdpAccountDenomination1 = "EDP";
        String houseEdpAccountDescription1 = "Electricity expenses from EDP";
        Account houseEdp1 = Account.createAccount(houseEdpAccountDescription1, houseEdpAccountDenomination1, houseID1);
        AccountID houseEdpID1 = houseEdp1.getAccountID();

        //Transaction 1 on Group House 1
        String typeTransaction1House1 = "Debit";
        String descriptionTransaction1House1 = "EDP bill from January/2020";
        LocalDate dateTransaction1House1 = LocalDate.of(2020, 02, 01);
        double amountTransaction1House1 = 40.00;

        //Transaction DTO out 1
        TransactionDTOout transactionDTOout1 = new TransactionDTOout(denominationExpenses1, typeTransaction1House1, descriptionTransaction1House1, amountTransaction1House1, dateTransaction1House1.toString(), houseWalletAccountDenomination1, houseEdpAccountDenomination1);

        String object = "Object from class String";


        //Act
        boolean resultEquals = transactionDTOout1.equals(object);
        boolean resultHashCode = (transactionDTOout1.hashCode() == object.hashCode());


        //Assert
        assertEquals(false, resultEquals);
        assertEquals(false, resultHashCode);
    }

    @Test
    @DisplayName("Test for equals() and hasCode() - Fail (different transaction category)")
    void testEqualsAndHashCode_FailDiffCategory() {
        //Arrange
        //Group House 1
        String denominationHouse1 = "House";
        String emailPersonHouse1 = "joao@gmail.com";
        PersonID peopleInChargeHouse1 = PersonID.createPersonID(emailPersonHouse1);
        String descriptionHouse1 = "People who share house";
        LocalDate dateOfCreationHouse1 = LocalDate.of(2020, 01, 01);
        Ledger ledgerHouse1 = Ledger.createLedger();
        LedgerID ledgerIDHouse1 = ledgerHouse1.getLedgerID();
        Group house1 = Group.createGroupAsPersonInCharge(denominationHouse1, peopleInChargeHouse1, descriptionHouse1, dateOfCreationHouse1, ledgerIDHouse1);
        GroupID houseID1 = house1.getGroupID();

        //Category Electricity Expenses (Group House 1)
        String denominationExpenses1 = "Water Expenses";
        Category categoryExpenses1 = Category.createCategory(denominationExpenses1, houseID1);
        CategoryID categoryExpensesID1 = categoryExpenses1.getCategoryID();

        //Account House Wallet (Group House 1)
        String houseWalletAccountDenomination1 = "House Funds";
        String houseWalletAccountDescription1 = "Money to spend with House expenses";
        Account houseWallet1 = Account.createAccount(houseWalletAccountDescription1, houseWalletAccountDenomination1, houseID1);
        AccountID houseWalletID1 = houseWallet1.getAccountID();

        //Account EDP (Group House 1)
        String houseEdpAccountDenomination1 = "EDP";
        String houseEdpAccountDescription1 = "Electricity expenses from EDP";
        Account houseEdp1 = Account.createAccount(houseEdpAccountDescription1, houseEdpAccountDenomination1, houseID1);
        AccountID houseEdpID1 = houseEdp1.getAccountID();

        //Transaction 1 on Group House 1
        String typeTransaction1House1 = "Debit";
        String descriptionTransaction1House1 = "EDP bill from January/2020";
        LocalDate dateTransaction1House1 = LocalDate.of(2020, 02, 01);
        double amountTransaction1House1 = 40.00;

        //Transaction DTO out 1
        TransactionDTOout transactionDTOout1 = new TransactionDTOout(denominationExpenses1, typeTransaction1House1, descriptionTransaction1House1, amountTransaction1House1, dateTransaction1House1.toString(), houseWalletAccountDenomination1, houseEdpAccountDenomination1);

        //Group House 2
        String denominationHouse2 = "House";
        String emailPersonHouse2 = "joao@gmail.com";
        PersonID peopleInChargeHouse2 = PersonID.createPersonID(emailPersonHouse2);
        String descriptionHouse2 = "People who share house";
        LocalDate dateOfCreationHouse2 = LocalDate.of(2020, 01, 01);
        Ledger ledgerHouse2 = Ledger.createLedger();
        LedgerID ledgerIDHouse2 = ledgerHouse2.getLedgerID();
        Group house2 = Group.createGroupAsPersonInCharge(denominationHouse2, peopleInChargeHouse2, descriptionHouse2, dateOfCreationHouse2, ledgerIDHouse2);
        GroupID houseID2 = house2.getGroupID();

        //Category Electricity Expenses (Group House 2)
        String denominationExpenses2 = "Electricity Expenses";
        Category categoryExpenses2 = Category.createCategory(denominationExpenses2, houseID2);
        CategoryID categoryExpensesID2 = categoryExpenses2.getCategoryID();

        //Account House Wallet (Group House 2)
        String houseWalletAccountDenomination2 = "House Funds";
        String houseWalletAccountDescription2 = "Money to spend with House expenses";
        Account houseWallet2 = Account.createAccount(houseWalletAccountDescription2, houseWalletAccountDenomination2, houseID2);
        AccountID houseWalletID2 = houseWallet2.getAccountID();

        //Account EDP (Group House 2)
        String houseEdpAccountDenomination2 = "EDP";
        String houseEdpAccountDescription2 = "Electricity expenses from EDP";
        Account houseEdp2 = Account.createAccount(houseEdpAccountDescription2, houseEdpAccountDenomination2, houseID2);
        AccountID houseEdpID2 = houseEdp2.getAccountID();

        //Transaction 1 on Group House 2
        String typeTransaction1House2 = "Debit";
        String descriptionTransaction1House2 = "EDP bill from January/2020";
        LocalDate dateTransaction1House2 = LocalDate.of(2020, 02, 01);
        double amountTransaction1House2 = 40.00;

        //Transaction DTO out 2
        TransactionDTOout transactionDTOout2 = new TransactionDTOout(denominationExpenses2, typeTransaction1House2, descriptionTransaction1House2, amountTransaction1House2, dateTransaction1House2.toString(), houseWalletAccountDenomination2, houseEdpAccountDenomination2);


        //Act
        boolean resultEquals = transactionDTOout1.equals(transactionDTOout2);
        boolean resultHashCode = (transactionDTOout1.hashCode() == transactionDTOout2.hashCode());


        //Assert
        assertEquals(false, resultEquals);
        assertEquals(false, resultHashCode);
    }

    @Test
    @DisplayName("Test for equals() and hasCode() - Fail (different transaction type)")
    void testEqualsAndHashCode_FailDiffType() {
        //Arrange
        //Group House 1
        String denominationHouse1 = "House";
        String emailPersonHouse1 = "joao@gmail.com";
        PersonID peopleInChargeHouse1 = PersonID.createPersonID(emailPersonHouse1);
        String descriptionHouse1 = "People who share house";
        LocalDate dateOfCreationHouse1 = LocalDate.of(2020, 01, 01);
        Ledger ledgerHouse1 = Ledger.createLedger();
        LedgerID ledgerIDHouse1 = ledgerHouse1.getLedgerID();
        Group house1 = Group.createGroupAsPersonInCharge(denominationHouse1, peopleInChargeHouse1, descriptionHouse1, dateOfCreationHouse1, ledgerIDHouse1);
        GroupID houseID1 = house1.getGroupID();

        //Category Electricity Expenses (Group House 1)
        String denominationExpenses1 = "Electricity Expenses";
        Category categoryExpenses1 = Category.createCategory(denominationExpenses1, houseID1);
        CategoryID categoryExpensesID1 = categoryExpenses1.getCategoryID();

        //Account House Wallet (Group House 1)
        String houseWalletAccountDenomination1 = "House Funds";
        String houseWalletAccountDescription1 = "Money to spend with House expenses";
        Account houseWallet1 = Account.createAccount(houseWalletAccountDescription1, houseWalletAccountDenomination1, houseID1);
        AccountID houseWalletID1 = houseWallet1.getAccountID();

        //Account EDP (Group House 1)
        String houseEdpAccountDenomination1 = "EDP";
        String houseEdpAccountDescription1 = "Electricity expenses from EDP";
        Account houseEdp1 = Account.createAccount(houseEdpAccountDescription1, houseEdpAccountDenomination1, houseID1);
        AccountID houseEdpID1 = houseEdp1.getAccountID();

        //Transaction 1 on Group House 1
        String typeTransaction1House1 = "Credit";
        String descriptionTransaction1House1 = "EDP bill from January/2020";
        LocalDate dateTransaction1House1 = LocalDate.of(2020, 02, 01);
        double amountTransaction1House1 = 40.00;

        //Transaction DTO out 1
        TransactionDTOout transactionDTOout1 = new TransactionDTOout(denominationExpenses1, typeTransaction1House1, descriptionTransaction1House1, amountTransaction1House1, dateTransaction1House1.toString(), houseWalletAccountDenomination1, houseEdpAccountDenomination1);

        //Group House 2
        String denominationHouse2 = "House";
        String emailPersonHouse2 = "joao@gmail.com";
        PersonID peopleInChargeHouse2 = PersonID.createPersonID(emailPersonHouse2);
        String descriptionHouse2 = "People who share house";
        LocalDate dateOfCreationHouse2 = LocalDate.of(2020, 01, 01);
        Ledger ledgerHouse2 = Ledger.createLedger();
        LedgerID ledgerIDHouse2 = ledgerHouse2.getLedgerID();
        Group house2 = Group.createGroupAsPersonInCharge(denominationHouse2, peopleInChargeHouse2, descriptionHouse2, dateOfCreationHouse2, ledgerIDHouse2);
        GroupID houseID2 = house2.getGroupID();

        //Category Electricity Expenses (Group House 2)
        String denominationExpenses2 = "Electricity Expenses";
        Category categoryExpenses2 = Category.createCategory(denominationExpenses2, houseID2);
        CategoryID categoryExpensesID2 = categoryExpenses2.getCategoryID();

        //Account House Wallet (Group House 2)
        String houseWalletAccountDenomination2 = "House Funds";
        String houseWalletAccountDescription2 = "Money to spend with House expenses";
        Account houseWallet2 = Account.createAccount(houseWalletAccountDescription2, houseWalletAccountDenomination2, houseID2);
        AccountID houseWalletID2 = houseWallet2.getAccountID();

        //Account EDP (Group House 2)
        String houseEdpAccountDenomination2 = "EDP";
        String houseEdpAccountDescription2 = "Electricity expenses from EDP";
        Account houseEdp2 = Account.createAccount(houseEdpAccountDescription2, houseEdpAccountDenomination2, houseID2);
        AccountID houseEdpID2 = houseEdp2.getAccountID();

        //Transaction 1 on Group House 2
        String typeTransaction1House2 = "Debit";
        String descriptionTransaction1House2 = "EDP bill from January/2020";
        LocalDate dateTransaction1House2 = LocalDate.of(2020, 02, 01);
        double amountTransaction1House2 = 40.00;

        //Transaction DTO out 2
        TransactionDTOout transactionDTOout2 = new TransactionDTOout(denominationExpenses2, typeTransaction1House2, descriptionTransaction1House2, amountTransaction1House2, dateTransaction1House2.toString(), houseWalletAccountDenomination2, houseEdpAccountDenomination2);


        //Act
        boolean resultEquals = transactionDTOout1.equals(transactionDTOout2);
        boolean resultHashCode = (transactionDTOout1.hashCode() == transactionDTOout2.hashCode());


        //Assert
        assertEquals(false, resultEquals);
        assertEquals(false, resultHashCode);
    }

    @Test
    @DisplayName("Test for equals() and hasCode() - Fail (different transaction description)")
    void testEqualsAndHashCode_FailDiffDescription() {
        //Arrange
        //Group House 1
        String denominationHouse1 = "House";
        String emailPersonHouse1 = "joao@gmail.com";
        PersonID peopleInChargeHouse1 = PersonID.createPersonID(emailPersonHouse1);
        String descriptionHouse1 = "People who share house";
        LocalDate dateOfCreationHouse1 = LocalDate.of(2020, 01, 01);
        Ledger ledgerHouse1 = Ledger.createLedger();
        LedgerID ledgerIDHouse1 = ledgerHouse1.getLedgerID();
        Group house1 = Group.createGroupAsPersonInCharge(denominationHouse1, peopleInChargeHouse1, descriptionHouse1, dateOfCreationHouse1, ledgerIDHouse1);
        GroupID houseID1 = house1.getGroupID();

        //Category Electricity Expenses (Group House 1)
        String denominationExpenses1 = "Electricity Expenses";
        Category categoryExpenses1 = Category.createCategory(denominationExpenses1, houseID1);
        CategoryID categoryExpensesID1 = categoryExpenses1.getCategoryID();

        //Account House Wallet (Group House 1)
        String houseWalletAccountDenomination1 = "House Funds";
        String houseWalletAccountDescription1 = "Money to spend with House expenses";
        Account houseWallet1 = Account.createAccount(houseWalletAccountDescription1, houseWalletAccountDenomination1, houseID1);
        AccountID houseWalletID1 = houseWallet1.getAccountID();

        //Account EDP (Group House 1)
        String houseEdpAccountDenomination1 = "EDP";
        String houseEdpAccountDescription1 = "Electricity expenses from EDP";
        Account houseEdp1 = Account.createAccount(houseEdpAccountDescription1, houseEdpAccountDenomination1, houseID1);
        AccountID houseEdpID1 = houseEdp1.getAccountID();

        //Transaction 1 on Group House 1
        String typeTransaction1House1 = "Debit";
        String descriptionTransaction1House1 = "EDP bill from January/2019";
        LocalDate dateTransaction1House1 = LocalDate.of(2020, 02, 01);
        double amountTransaction1House1 = 40.00;

        //Transaction DTO out 1
        TransactionDTOout transactionDTOout1 = new TransactionDTOout(denominationExpenses1, typeTransaction1House1, descriptionTransaction1House1, amountTransaction1House1, dateTransaction1House1.toString(), houseWalletAccountDenomination1, houseEdpAccountDenomination1);

        //Group House 2
        String denominationHouse2 = "House";
        String emailPersonHouse2 = "joao@gmail.com";
        PersonID peopleInChargeHouse2 = PersonID.createPersonID(emailPersonHouse2);
        String descriptionHouse2 = "People who share house";
        LocalDate dateOfCreationHouse2 = LocalDate.of(2020, 01, 01);
        Ledger ledgerHouse2 = Ledger.createLedger();
        LedgerID ledgerIDHouse2 = ledgerHouse2.getLedgerID();
        Group house2 = Group.createGroupAsPersonInCharge(denominationHouse2, peopleInChargeHouse2, descriptionHouse2, dateOfCreationHouse2, ledgerIDHouse2);
        GroupID houseID2 = house2.getGroupID();

        //Category Electricity Expenses (Group House 2)
        String denominationExpenses2 = "Electricity Expenses";
        Category categoryExpenses2 = Category.createCategory(denominationExpenses2, houseID2);
        CategoryID categoryExpensesID2 = categoryExpenses2.getCategoryID();

        //Account House Wallet (Group House 2)
        String houseWalletAccountDenomination2 = "House Funds";
        String houseWalletAccountDescription2 = "Money to spend with House expenses";
        Account houseWallet2 = Account.createAccount(houseWalletAccountDescription2, houseWalletAccountDenomination2, houseID2);
        AccountID houseWalletID2 = houseWallet2.getAccountID();

        //Account EDP (Group House 2)
        String houseEdpAccountDenomination2 = "EDP";
        String houseEdpAccountDescription2 = "Electricity expenses from EDP";
        Account houseEdp2 = Account.createAccount(houseEdpAccountDescription2, houseEdpAccountDenomination2, houseID2);
        AccountID houseEdpID2 = houseEdp2.getAccountID();

        //Transaction 1 on Group House 2
        String typeTransaction1House2 = "Debit";
        String descriptionTransaction1House2 = "EDP bill from January/2020";
        LocalDate dateTransaction1House2 = LocalDate.of(2020, 02, 01);
        double amountTransaction1House2 = 40.00;

        //Transaction DTO out 2
        TransactionDTOout transactionDTOout2 = new TransactionDTOout(denominationExpenses2, typeTransaction1House2, descriptionTransaction1House2, amountTransaction1House2, dateTransaction1House2.toString(), houseWalletAccountDenomination2, houseEdpAccountDenomination2);


        //Act
        boolean resultEquals = transactionDTOout1.equals(transactionDTOout2);
        boolean resultHashCode = (transactionDTOout1.hashCode() == transactionDTOout2.hashCode());


        //Assert
        assertEquals(false, resultEquals);
        assertEquals(false, resultHashCode);
    }

    @Test
    @DisplayName("Test for equals() and hasCode() - Fail (different transaction amount)")
    void testEqualsAndHashCode_FailDiffAmount() {
        //Arrange
        //Group House 1
        String denominationHouse1 = "House";
        String emailPersonHouse1 = "joao@gmail.com";
        PersonID peopleInChargeHouse1 = PersonID.createPersonID(emailPersonHouse1);
        String descriptionHouse1 = "People who share house";
        LocalDate dateOfCreationHouse1 = LocalDate.of(2020, 01, 01);
        Ledger ledgerHouse1 = Ledger.createLedger();
        LedgerID ledgerIDHouse1 = ledgerHouse1.getLedgerID();
        Group house1 = Group.createGroupAsPersonInCharge(denominationHouse1, peopleInChargeHouse1, descriptionHouse1, dateOfCreationHouse1, ledgerIDHouse1);
        GroupID houseID1 = house1.getGroupID();

        //Category Electricity Expenses (Group House 1)
        String denominationExpenses1 = "Electricity Expenses";
        Category categoryExpenses1 = Category.createCategory(denominationExpenses1, houseID1);
        CategoryID categoryExpensesID1 = categoryExpenses1.getCategoryID();

        //Account House Wallet (Group House 1)
        String houseWalletAccountDenomination1 = "House Funds";
        String houseWalletAccountDescription1 = "Money to spend with House expenses";
        Account houseWallet1 = Account.createAccount(houseWalletAccountDescription1, houseWalletAccountDenomination1, houseID1);
        AccountID houseWalletID1 = houseWallet1.getAccountID();

        //Account EDP (Group House 1)
        String houseEdpAccountDenomination1 = "EDP";
        String houseEdpAccountDescription1 = "Electricity expenses from EDP";
        Account houseEdp1 = Account.createAccount(houseEdpAccountDescription1, houseEdpAccountDenomination1, houseID1);
        AccountID houseEdpID1 = houseEdp1.getAccountID();

        //Transaction 1 on Group House 1
        String typeTransaction1House1 = "Debit";
        String descriptionTransaction1House1 = "EDP bill from January/2020";
        LocalDate dateTransaction1House1 = LocalDate.of(2020, 02, 01);
        double amountTransaction1House1 = 45.00;

        //Transaction DTO out 1
        TransactionDTOout transactionDTOout1 = new TransactionDTOout(denominationExpenses1, typeTransaction1House1, descriptionTransaction1House1, amountTransaction1House1, dateTransaction1House1.toString(), houseWalletAccountDenomination1, houseEdpAccountDenomination1);

        //Group House 2
        String denominationHouse2 = "House";
        String emailPersonHouse2 = "joao@gmail.com";
        PersonID peopleInChargeHouse2 = PersonID.createPersonID(emailPersonHouse2);
        String descriptionHouse2 = "People who share house";
        LocalDate dateOfCreationHouse2 = LocalDate.of(2020, 01, 01);
        Ledger ledgerHouse2 = Ledger.createLedger();
        LedgerID ledgerIDHouse2 = ledgerHouse2.getLedgerID();
        Group house2 = Group.createGroupAsPersonInCharge(denominationHouse2, peopleInChargeHouse2, descriptionHouse2, dateOfCreationHouse2, ledgerIDHouse2);
        GroupID houseID2 = house2.getGroupID();

        //Category Electricity Expenses (Group House 2)
        String denominationExpenses2 = "Electricity Expenses";
        Category categoryExpenses2 = Category.createCategory(denominationExpenses2, houseID2);
        CategoryID categoryExpensesID2 = categoryExpenses2.getCategoryID();

        //Account House Wallet (Group House 2)
        String houseWalletAccountDenomination2 = "House Funds";
        String houseWalletAccountDescription2 = "Money to spend with House expenses";
        Account houseWallet2 = Account.createAccount(houseWalletAccountDescription2, houseWalletAccountDenomination2, houseID2);
        AccountID houseWalletID2 = houseWallet2.getAccountID();

        //Account EDP (Group House 2)
        String houseEdpAccountDenomination2 = "EDP";
        String houseEdpAccountDescription2 = "Electricity expenses from EDP";
        Account houseEdp2 = Account.createAccount(houseEdpAccountDescription2, houseEdpAccountDenomination2, houseID2);
        AccountID houseEdpID2 = houseEdp2.getAccountID();

        //Transaction 1 on Group House 2
        String typeTransaction1House2 = "Debit";
        String descriptionTransaction1House2 = "EDP bill from January/2020";
        LocalDate dateTransaction1House2 = LocalDate.of(2020, 02, 01);
        double amountTransaction1House2 = 40.00;

        //Transaction DTO out 2
        TransactionDTOout transactionDTOout2 = new TransactionDTOout(denominationExpenses2, typeTransaction1House2, descriptionTransaction1House2, amountTransaction1House2, dateTransaction1House2.toString(), houseWalletAccountDenomination2, houseEdpAccountDenomination2);


        //Act
        boolean resultEquals = transactionDTOout1.equals(transactionDTOout2);
        boolean resultHashCode = (transactionDTOout1.hashCode() == transactionDTOout2.hashCode());


        //Assert
        assertEquals(false, resultEquals);
        assertEquals(false, resultHashCode);
    }

    @Test
    @DisplayName("Test for equals() and hasCode() - Fail (different transaction date)")
    void testEqualsAndHashCode_FailDiffDate() {
        //Arrange
        //Group House 1
        String denominationHouse1 = "House";
        String emailPersonHouse1 = "joao@gmail.com";
        PersonID peopleInChargeHouse1 = PersonID.createPersonID(emailPersonHouse1);
        String descriptionHouse1 = "People who share house";
        LocalDate dateOfCreationHouse1 = LocalDate.of(2020, 01, 01);
        Ledger ledgerHouse1 = Ledger.createLedger();
        LedgerID ledgerIDHouse1 = ledgerHouse1.getLedgerID();
        Group house1 = Group.createGroupAsPersonInCharge(denominationHouse1, peopleInChargeHouse1, descriptionHouse1, dateOfCreationHouse1, ledgerIDHouse1);
        GroupID houseID1 = house1.getGroupID();

        //Category Electricity Expenses (Group House 1)
        String denominationExpenses1 = "Electricity Expenses";
        Category categoryExpenses1 = Category.createCategory(denominationExpenses1, houseID1);
        CategoryID categoryExpensesID1 = categoryExpenses1.getCategoryID();

        //Account House Wallet (Group House 1)
        String houseWalletAccountDenomination1 = "House Funds";
        String houseWalletAccountDescription1 = "Money to spend with House expenses";
        Account houseWallet1 = Account.createAccount(houseWalletAccountDescription1, houseWalletAccountDenomination1, houseID1);
        AccountID houseWalletID1 = houseWallet1.getAccountID();

        //Account EDP (Group House 1)
        String houseEdpAccountDenomination1 = "EDP";
        String houseEdpAccountDescription1 = "Electricity expenses from EDP";
        Account houseEdp1 = Account.createAccount(houseEdpAccountDescription1, houseEdpAccountDenomination1, houseID1);
        AccountID houseEdpID1 = houseEdp1.getAccountID();

        //Transaction 1 on Group House 1
        String typeTransaction1House1 = "Debit";
        String descriptionTransaction1House1 = "EDP bill from January/2020";
        LocalDate dateTransaction1House1 = LocalDate.of(2020, 01, 15);
        double amountTransaction1House1 = 40.00;

        //Transaction DTO out 1
        TransactionDTOout transactionDTOout1 = new TransactionDTOout(denominationExpenses1, typeTransaction1House1, descriptionTransaction1House1, amountTransaction1House1, dateTransaction1House1.toString(), houseWalletAccountDenomination1, houseEdpAccountDenomination1);

        //Group House 2
        String denominationHouse2 = "House";
        String emailPersonHouse2 = "joao@gmail.com";
        PersonID peopleInChargeHouse2 = PersonID.createPersonID(emailPersonHouse2);
        String descriptionHouse2 = "People who share house";
        LocalDate dateOfCreationHouse2 = LocalDate.of(2020, 01, 01);
        Ledger ledgerHouse2 = Ledger.createLedger();
        LedgerID ledgerIDHouse2 = ledgerHouse2.getLedgerID();
        Group house2 = Group.createGroupAsPersonInCharge(denominationHouse2, peopleInChargeHouse2, descriptionHouse2, dateOfCreationHouse2, ledgerIDHouse2);
        GroupID houseID2 = house2.getGroupID();

        //Category Electricity Expenses (Group House 2)
        String denominationExpenses2 = "Electricity Expenses";
        Category categoryExpenses2 = Category.createCategory(denominationExpenses2, houseID2);
        CategoryID categoryExpensesID2 = categoryExpenses2.getCategoryID();

        //Account House Wallet (Group House 2)
        String houseWalletAccountDenomination2 = "House Funds";
        String houseWalletAccountDescription2 = "Money to spend with House expenses";
        Account houseWallet2 = Account.createAccount(houseWalletAccountDescription2, houseWalletAccountDenomination2, houseID2);
        AccountID houseWalletID2 = houseWallet2.getAccountID();

        //Account EDP (Group House 2)
        String houseEdpAccountDenomination2 = "EDP";
        String houseEdpAccountDescription2 = "Electricity expenses from EDP";
        Account houseEdp2 = Account.createAccount(houseEdpAccountDescription2, houseEdpAccountDenomination2, houseID2);
        AccountID houseEdpID2 = houseEdp2.getAccountID();

        //Transaction 1 on Group House 2
        String typeTransaction1House2 = "Debit";
        String descriptionTransaction1House2 = "EDP bill from January/2020";
        LocalDate dateTransaction1House2 = LocalDate.of(2020, 02, 01);
        double amountTransaction1House2 = 40.00;

        //Transaction DTO out 2
        TransactionDTOout transactionDTOout2 = new TransactionDTOout(denominationExpenses2, typeTransaction1House2, descriptionTransaction1House2, amountTransaction1House2, dateTransaction1House2.toString(), houseWalletAccountDenomination2, houseEdpAccountDenomination2);


        //Act
        boolean resultEquals = transactionDTOout1.equals(transactionDTOout2);
        boolean resultHashCode = (transactionDTOout1.hashCode() == transactionDTOout2.hashCode());


        //Assert
        assertEquals(false, resultEquals);
        assertEquals(false, resultHashCode);
    }

    @Test
    @DisplayName("Test for equals() and hasCode() - Fail (different transaction debit account)")
    void testEqualsAndHashCode_FailDiffDebitAccount() {
        //Arrange
        //Group House 1
        String denominationHouse1 = "House";
        String emailPersonHouse1 = "joao@gmail.com";
        PersonID peopleInChargeHouse1 = PersonID.createPersonID(emailPersonHouse1);
        String descriptionHouse1 = "People who share house";
        LocalDate dateOfCreationHouse1 = LocalDate.of(2020, 01, 01);
        Ledger ledgerHouse1 = Ledger.createLedger();
        LedgerID ledgerIDHouse1 = ledgerHouse1.getLedgerID();
        Group house1 = Group.createGroupAsPersonInCharge(denominationHouse1, peopleInChargeHouse1, descriptionHouse1, dateOfCreationHouse1, ledgerIDHouse1);
        GroupID houseID1 = house1.getGroupID();

        //Category Electricity Expenses (Group House 1)
        String denominationExpenses1 = "Electricity Expenses";
        Category categoryExpenses1 = Category.createCategory(denominationExpenses1, houseID1);
        CategoryID categoryExpensesID1 = categoryExpenses1.getCategoryID();

        //Account House Wallet (Group House 1)
        String houseWalletAccountDenomination1 = "Wallet Funds";
        String houseWalletAccountDescription1 = "Money to spend with House expenses";
        Account houseWallet1 = Account.createAccount(houseWalletAccountDescription1, houseWalletAccountDenomination1, houseID1);
        AccountID houseWalletID1 = houseWallet1.getAccountID();

        //Account EDP (Group House 1)
        String houseEdpAccountDenomination1 = "EDP";
        String houseEdpAccountDescription1 = "Electricity expenses from EDP";
        Account houseEdp1 = Account.createAccount(houseEdpAccountDescription1, houseEdpAccountDenomination1, houseID1);
        AccountID houseEdpID1 = houseEdp1.getAccountID();

        //Transaction 1 on Group House 1
        String typeTransaction1House1 = "Debit";
        String descriptionTransaction1House1 = "EDP bill from January/2020";
        LocalDate dateTransaction1House1 = LocalDate.of(2020, 02, 01);
        double amountTransaction1House1 = 40.00;

        //Transaction DTO out 1
        TransactionDTOout transactionDTOout1 = new TransactionDTOout(denominationExpenses1, typeTransaction1House1, descriptionTransaction1House1, amountTransaction1House1, dateTransaction1House1.toString(), houseWalletAccountDenomination1, houseEdpAccountDenomination1);

        //Group House 2
        String denominationHouse2 = "House";
        String emailPersonHouse2 = "joao@gmail.com";
        PersonID peopleInChargeHouse2 = PersonID.createPersonID(emailPersonHouse2);
        String descriptionHouse2 = "People who share house";
        LocalDate dateOfCreationHouse2 = LocalDate.of(2020, 01, 01);
        Ledger ledgerHouse2 = Ledger.createLedger();
        LedgerID ledgerIDHouse2 = ledgerHouse2.getLedgerID();
        Group house2 = Group.createGroupAsPersonInCharge(denominationHouse2, peopleInChargeHouse2, descriptionHouse2, dateOfCreationHouse2, ledgerIDHouse2);
        GroupID houseID2 = house2.getGroupID();

        //Category Electricity Expenses (Group House 2)
        String denominationExpenses2 = "Electricity Expenses";
        Category categoryExpenses2 = Category.createCategory(denominationExpenses2, houseID2);
        CategoryID categoryExpensesID2 = categoryExpenses2.getCategoryID();

        //Account House Wallet (Group House 2)
        String houseWalletAccountDenomination2 = "House Funds";
        String houseWalletAccountDescription2 = "Money to spend with House expenses";
        Account houseWallet2 = Account.createAccount(houseWalletAccountDescription2, houseWalletAccountDenomination2, houseID2);
        AccountID houseWalletID2 = houseWallet2.getAccountID();

        //Account EDP (Group House 2)
        String houseEdpAccountDenomination2 = "EDP";
        String houseEdpAccountDescription2 = "Electricity expenses from EDP";
        Account houseEdp2 = Account.createAccount(houseEdpAccountDescription2, houseEdpAccountDenomination2, houseID2);
        AccountID houseEdpID2 = houseEdp2.getAccountID();

        //Transaction 1 on Group House 2
        String typeTransaction1House2 = "Debit";
        String descriptionTransaction1House2 = "EDP bill from January/2020";
        LocalDate dateTransaction1House2 = LocalDate.of(2020, 02, 01);
        double amountTransaction1House2 = 40.00;

        //Transaction DTO out 2
        TransactionDTOout transactionDTOout2 = new TransactionDTOout(denominationExpenses2, typeTransaction1House2, descriptionTransaction1House2, amountTransaction1House2, dateTransaction1House2.toString(), houseWalletAccountDenomination2, houseEdpAccountDenomination2);


        //Act
        boolean resultEquals = transactionDTOout1.equals(transactionDTOout2);
        boolean resultHashCode = (transactionDTOout1.hashCode() == transactionDTOout2.hashCode());


        //Assert
        assertEquals(false, resultEquals);
        assertEquals(false, resultHashCode);
    }

    @Test
    @DisplayName("Test for equals() and hasCode() - Fail (different transaction credit)")
    void testEqualsAndHashCode_FailDiffCreditAccount() {
        //Arrange
        //Group House 1
        String denominationHouse1 = "House";
        String emailPersonHouse1 = "joao@gmail.com";
        PersonID peopleInChargeHouse1 = PersonID.createPersonID(emailPersonHouse1);
        String descriptionHouse1 = "People who share house";
        LocalDate dateOfCreationHouse1 = LocalDate.of(2020, 01, 01);
        Ledger ledgerHouse1 = Ledger.createLedger();
        LedgerID ledgerIDHouse1 = ledgerHouse1.getLedgerID();
        Group house1 = Group.createGroupAsPersonInCharge(denominationHouse1, peopleInChargeHouse1, descriptionHouse1, dateOfCreationHouse1, ledgerIDHouse1);
        GroupID houseID1 = house1.getGroupID();

        //Category Electricity Expenses (Group House 1)
        String denominationExpenses1 = "Electricity Expenses";
        Category categoryExpenses1 = Category.createCategory(denominationExpenses1, houseID1);
        CategoryID categoryExpensesID1 = categoryExpenses1.getCategoryID();

        //Account House Wallet (Group House 1)
        String houseWalletAccountDenomination1 = "House Funds";
        String houseWalletAccountDescription1 = "Money to spend with House expenses";
        Account houseWallet1 = Account.createAccount(houseWalletAccountDescription1, houseWalletAccountDenomination1, houseID1);
        AccountID houseWalletID1 = houseWallet1.getAccountID();

        //Account EDP (Group House 1)
        String houseEdpAccountDenomination1 = "Águas do Porto";
        String houseEdpAccountDescription1 = "Water expenses";
        Account houseEdp1 = Account.createAccount(houseEdpAccountDescription1, houseEdpAccountDenomination1, houseID1);
        AccountID houseEdpID1 = houseEdp1.getAccountID();

        //Transaction 1 on Group House 1
        String typeTransaction1House1 = "Debit";
        String descriptionTransaction1House1 = "EDP bill from January/2020";
        LocalDate dateTransaction1House1 = LocalDate.of(2020, 02, 01);
        double amountTransaction1House1 = 40.00;

        //Transaction DTO out 1
        TransactionDTOout transactionDTOout1 = new TransactionDTOout(denominationExpenses1, typeTransaction1House1, descriptionTransaction1House1, amountTransaction1House1, dateTransaction1House1.toString(), houseWalletAccountDenomination1, houseEdpAccountDenomination1);

        //Group House 2
        String denominationHouse2 = "House";
        String emailPersonHouse2 = "joao@gmail.com";
        PersonID peopleInChargeHouse2 = PersonID.createPersonID(emailPersonHouse2);
        String descriptionHouse2 = "People who share house";
        LocalDate dateOfCreationHouse2 = LocalDate.of(2020, 01, 01);
        Ledger ledgerHouse2 = Ledger.createLedger();
        LedgerID ledgerIDHouse2 = ledgerHouse2.getLedgerID();
        Group house2 = Group.createGroupAsPersonInCharge(denominationHouse2, peopleInChargeHouse2, descriptionHouse2, dateOfCreationHouse2, ledgerIDHouse2);
        GroupID houseID2 = house2.getGroupID();

        //Category Electricity Expenses (Group House 2)
        String denominationExpenses2 = "Electricity Expenses";
        Category categoryExpenses2 = Category.createCategory(denominationExpenses2, houseID2);
        CategoryID categoryExpensesID2 = categoryExpenses2.getCategoryID();

        //Account House Wallet (Group House 2)
        String houseWalletAccountDenomination2 = "House Funds";
        String houseWalletAccountDescription2 = "Money to spend with House expenses";
        Account houseWallet2 = Account.createAccount(houseWalletAccountDescription2, houseWalletAccountDenomination2, houseID2);
        AccountID houseWalletID2 = houseWallet2.getAccountID();

        //Account EDP (Group House 2)
        String houseEdpAccountDenomination2 = "EDP";
        String houseEdpAccountDescription2 = "Electricity expenses from EDP";
        Account houseEdp2 = Account.createAccount(houseEdpAccountDescription2, houseEdpAccountDenomination2, houseID2);
        AccountID houseEdpID2 = houseEdp2.getAccountID();

        //Transaction 1 on Group House 2
        String typeTransaction1House2 = "Debit";
        String descriptionTransaction1House2 = "EDP bill from January/2020";
        LocalDate dateTransaction1House2 = LocalDate.of(2020, 02, 01);
        double amountTransaction1House2 = 40.00;

        //Transaction DTO out 2
        TransactionDTOout transactionDTOout2 = new TransactionDTOout(denominationExpenses2, typeTransaction1House2, descriptionTransaction1House2, amountTransaction1House2, dateTransaction1House2.toString(), houseWalletAccountDenomination2, houseEdpAccountDenomination2);


        //Act
        boolean resultEquals = transactionDTOout1.equals(transactionDTOout2);
        boolean resultHashCode = (transactionDTOout1.hashCode() == transactionDTOout2.hashCode());


        //Assert
        assertEquals(false, resultEquals);
        assertEquals(false, resultHashCode);
    }

}