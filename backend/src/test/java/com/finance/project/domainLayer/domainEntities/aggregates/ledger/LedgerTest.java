package com.finance.project.domainLayer.domainEntities.aggregates.ledger;

import com.finance.project.domainLayer.domainEntities.aggregates.account.Account;
import com.finance.project.domainLayer.domainEntities.aggregates.category.Category;
import com.finance.project.domainLayer.domainEntities.aggregates.group.Group;
import com.finance.project.domainLayer.domainEntities.vosShared.*;
import com.finance.project.dtos.dtosAssemblers.CreatePersonTransactionDTOAssembler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.finance.project.dtos.dtos.CreatePersonTransactionDTO;
import com.finance.project.dtos.dtos.TransactionDTOout;
import com.finance.project.dtos.dtosAssemblers.TransactionDTOoutAssembler;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LedgerTest {

    //Tests for the class’ constructor Ledger

    @Test
    @DisplayName("Verify if the Ledger have been created")
    public void testConstructorLedger() {
        //Act
        Ledger ledger = Ledger.createLedger();
        ArrayList<Transaction> ledgerRegists = ledger.getRecords();

        ArrayList<Transaction> expectedRegists = new ArrayList<Transaction>();

        //Assert
        assertEquals(expectedRegists, ledgerRegists);
    }

    //Tests for the get method

    @Test
    @DisplayName("Verify is get method it's working | Both regists empty ")
    public void testGetMethodEmptyResgists() {
        //Act
        Ledger ledger = Ledger.createLedger();
        ArrayList<Transaction> regists = new ArrayList<>();

        //Assert
        assertEquals(true, ledger.getRecords().equals(regists));
    }

    @Test
    @DisplayName("Verify is get method it's working | Different regists")
    public void testGetMethodDifferentRegists() {

        //Arrange PersonID

        PersonID joaoPersonID = PersonID.createPersonID("joao@gmail.com");

        //Arrange of Transaction
        String transactionType = "Credit";
        String transactionDescription = "Dez 2019 Salary";
        double transactionAmount = 1000.00;
        LocalDate date = LocalDate.of(2010, 12, 25);

        //Arrange of Debit Account
        AccountID creditAccountID = AccountID.createAccountID("EmployerSA", joaoPersonID);

        //Arrange of Credit Account
        AccountID debitAccountID = AccountID.createAccountID("João Salary Account", joaoPersonID);

        //Arrange of Category
        CategoryID categoryIDSalary = CategoryID.createCategoryID("Salary", joaoPersonID);

        //Act
        Ledger ledgerJoao = Ledger.createLedger();
        ArrayList<Transaction> regists = new ArrayList<>();

        ledgerJoao.createAndAddTransactionWithDate(categoryIDSalary, transactionType, transactionDescription, transactionAmount, date, debitAccountID, creditAccountID);

        //Assert
        assertEquals(false, ledgerJoao.getRecords().equals(regists));
    }

    //Tests for Create Transaction

    @Test
    @DisplayName("Verify if one transaction have been created and added to Ledger | Happy Case")
    public void testCreateAndAddTransation() {

        //Arrange PersonID

        PersonID joaoPersonID = PersonID.createPersonID("joao@gmail.com");

        //Arrange of Transaction
        String transactionType = "Credit";
        String transactionDescription = "Dez 2019 Salary";
        double transactionAmount = 1000.00;

        //Arrange of Debit Account
        AccountID creditAccountID = AccountID.createAccountID("EmployerSA", joaoPersonID);

        //Arrange of Credit Account
        AccountID debitAccountID = AccountID.createAccountID("João Salary Account", joaoPersonID);

        //Arrange of Category
        CategoryID categoryIDSalary = CategoryID.createCategoryID("Salary", joaoPersonID);

        Ledger ledger = Ledger.createLedger();
        Boolean ledgerTransaction = ledger.createAndAddTransaction(categoryIDSalary, transactionType, transactionDescription, transactionAmount, debitAccountID, creditAccountID);

        //Assert;
        assertEquals(true, ledgerTransaction);
    }

    //Tests for Added Transaction

    @Test
    @DisplayName("Verify if one transaction have been created and added to ledger | Get transaction")
    public void testGetTransactionAdded() {

        //Arrange PersonID

        PersonID joaoPersonID = PersonID.createPersonID("joao@gmail.com");

        //Arrange of Transaction
        String transactionType = "Credit";
        String transactionDescription = "Dez 2019 Salary";
        double transactionAmount = 1000.00;
        LocalDate date = LocalDate.of(2010, 12, 25);

        //Arrange of Debit Account
        AccountID creditAccountID = AccountID.createAccountID("EmployerSA", joaoPersonID);

        //Arrange of Credit Account
        AccountID debitAccountID = AccountID.createAccountID("João Salary Account", joaoPersonID);

        //Arrange of Category
        CategoryID categoryIDSalary = CategoryID.createCategoryID("Salary", joaoPersonID);

        //Arrange expected

        ArrayList<Transaction> expectedRegists = new ArrayList<>();

        //Act

        //Crate Transaction
        Transaction newTransaction = Transaction.createTransaction(categoryIDSalary, transactionType, transactionDescription, transactionAmount, date, debitAccountID, creditAccountID);

        Ledger ledgerJoao = Ledger.createLedger();

        ledgerJoao.addTransaction(newTransaction);

        //Expected

        expectedRegists.add(newTransaction);

        int transactionIndex = ledgerJoao.getRecords().size() - 1;

        //Assert;
        assertEquals(expectedRegists, ledgerJoao.getRecords()); //Compara o arraylist com o registo das transações
        assertEquals(newTransaction, ledgerJoao.getRecords().get(transactionIndex)); //Compara a transação que foi adicionada
    }

    @Test
    @DisplayName("Verify if ledger has transaction | Success")
    public void testLedgerHasTransaction_Success() {

        //Arrange PersonID

        String email = "joao@gmail.com";
        PersonID joaoPersonID = PersonID.createPersonID(email);

        //Arrange of Transaction

        String transactionType = "Credit";
        String transactionDescription = "Dez 2019 Salary";
        double transactionAmount = 1000.00;
        String date = "2019-12-25";

        //Arrange of Debit Account

        String denominationCredAccount = "EmployerSA";
        AccountID creditAccountID = AccountID.createAccountID(denominationCredAccount, joaoPersonID);

        //Arrange of Credit Account

        String denominationDebAccount = "João Salary Account";
        AccountID debitAccountID = AccountID.createAccountID(denominationDebAccount, joaoPersonID);

        //Arrange of Category

        String denominationCategory = "Salary";
        CategoryID categoryIDSalary = CategoryID.createCategoryID(denominationCategory, joaoPersonID);

        // DTO

        CreatePersonTransactionDTO expectedTransaction = CreatePersonTransactionDTOAssembler.createDTOFromPrimitiveTypes(email, denominationCategory, transactionType, transactionDescription, transactionAmount, denominationDebAccount, denominationCredAccount, date);


        //Arrange of Transaction

        Ledger ledger = Ledger.createLedger();
        Boolean ledgerTransaction = ledger.createAndAddTransaction(categoryIDSalary, transactionType, transactionDescription, transactionAmount, debitAccountID, creditAccountID);

        //Act

        Boolean ledgerHasTransaction = ledger.ledgerHasTransaction(expectedTransaction);

        //Assert;
        assertEquals(true, ledgerTransaction);
        assertEquals(true, ledgerHasTransaction);
    }

    @Test
    @DisplayName("Verify if ledger has transaction | Fail")
    public void testLedgerHasTransaction_Fail() {

        //Arrange PersonID

        String email = "joao@gmail.com";
        PersonID joaoPersonID = PersonID.createPersonID(email);

        String emailVegeta = "vegeta@gmail.com";

        //Arrange of Transaction

        String transactionType = "Credit";
        String transactionDescription = "Dez 2019 Salary";
        double transactionAmount = 1000.00;
        String date = "2019-12-25";

        //Arrange of Debit Account

        String denominationCredAccount = "EmployerSA";
        AccountID creditAccountID = AccountID.createAccountID(denominationCredAccount, joaoPersonID);

        //Arrange of Credit Account

        String denominationDebAccount = "João Salary Account";
        AccountID debitAccountID = AccountID.createAccountID(denominationDebAccount, joaoPersonID);

        //Arrange of Category

        String denominationCategory = "Salary";
        CategoryID categoryIDSalary = CategoryID.createCategoryID(denominationCategory, joaoPersonID);

        // DTO

        CreatePersonTransactionDTO expectedTransaction = CreatePersonTransactionDTOAssembler.createDTOFromPrimitiveTypes(emailVegeta, denominationCategory, transactionType, transactionDescription, transactionAmount, denominationDebAccount, denominationCredAccount, date);

        //Arrange of Transaction

        Ledger ledger = Ledger.createLedger();
        Boolean ledgerTransaction = ledger.createAndAddTransaction(categoryIDSalary, transactionType, transactionDescription, transactionAmount, debitAccountID, creditAccountID);

        //Act

        Boolean ledgerHasTransaction = ledger.ledgerHasTransaction(expectedTransaction);

        //Assert;
        assertEquals(true, ledgerTransaction);
        assertEquals(false, ledgerHasTransaction);
    }

    //Get list of Transactions between two dates
    @Test
    @DisplayName("Test getRecordsBetweenTwoDates() method - Happy path")
    void getRecordsBetweenTwoDates_HappyPath() {
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
        ledgerHouse.createAndAddTransactionWithDate(categoryExpensesID, typeTransaction1House, descriptionTransaction1House, amountTransaction1House, dateTransaction1House, houseWalletID, houseEdpID);

        //Transaction 2 on Group House
        String typeTransaction2House = "Debit";
        String descriptionTransaction2House = "EDP bill from February/2020";
        LocalDate dateTransaction2House = LocalDate.of(2020, 03, 01);
        double amountTransaction2House = 45.00;
        ledgerHouse.createAndAddTransactionWithDate(categoryExpensesID, typeTransaction2House, descriptionTransaction2House, amountTransaction2House, dateTransaction2House, houseWalletID, houseEdpID);

        //Expected lst of Transactions
        ArrayList<Transaction> expectedListTransactions = new ArrayList<>();
        expectedListTransactions.add(Transaction.createTransaction(categoryExpensesID, typeTransaction1House, descriptionTransaction1House, amountTransaction1House, dateTransaction1House, houseWalletID, houseEdpID));
        expectedListTransactions.add(Transaction.createTransaction(categoryExpensesID, typeTransaction2House, descriptionTransaction2House, amountTransaction2House, dateTransaction2House, houseWalletID, houseEdpID));

        //Get list of Transactions period
        LocalDate startDate = LocalDate.of(2020, 01, 01);
        LocalDate endDate = LocalDate.of(2020, 03, 10);

        //Act
        List<Transaction> result = ledgerHouse.getRecordsBetweenTwoDates(startDate, endDate);

        //Assert
        assertEquals(expectedListTransactions, result);
    }

    @Test
    @DisplayName("Test getRecordsBetweenTwoDates() method - Happy path (start and end date are the same as the dates of the transactions)")
    void getRecordsBetweenTwoDates_HappyPathDatesEdge() {
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
        ledgerHouse.createAndAddTransactionWithDate(categoryExpensesID, typeTransaction1House, descriptionTransaction1House, amountTransaction1House, dateTransaction1House, houseWalletID, houseEdpID);

        //Transaction 2 on Group House
        String typeTransaction2House = "Debit";
        String descriptionTransaction2House = "EDP bill from February/2020";
        LocalDate dateTransaction2House = LocalDate.of(2020, 03, 01);
        double amountTransaction2House = 45.00;
        ledgerHouse.createAndAddTransactionWithDate(categoryExpensesID, typeTransaction2House, descriptionTransaction2House, amountTransaction2House, dateTransaction2House, houseWalletID, houseEdpID);

        //Expected lst of Transactions
        ArrayList<Transaction> expectedListTransactions = new ArrayList<>();
        expectedListTransactions.add(Transaction.createTransaction(categoryExpensesID, typeTransaction1House, descriptionTransaction1House, amountTransaction1House, dateTransaction1House, houseWalletID, houseEdpID));
        expectedListTransactions.add(Transaction.createTransaction(categoryExpensesID, typeTransaction2House, descriptionTransaction2House, amountTransaction2House, dateTransaction2House, houseWalletID, houseEdpID));

        //Get list of Transactions period
        LocalDate startDate = LocalDate.of(2020, 02, 01);
        LocalDate endDate = LocalDate.of(2020, 03, 01);

        //Act
        List<Transaction> result = ledgerHouse.getRecordsBetweenTwoDates(startDate, endDate);

        //Assert
        assertEquals(expectedListTransactions, result);
    }

    @Test
    @DisplayName("Test getRecordsBetweenTwoDates() method - Happy path (only some transactions)")
    void getRecordsBetweenTwoDates_HappyPathSomeTransactions() {
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
        ledgerHouse.createAndAddTransactionWithDate(categoryExpensesID, typeTransaction1House, descriptionTransaction1House, amountTransaction1House, dateTransaction1House, houseWalletID, houseEdpID);

        //Transaction 2 on Group House
        String typeTransaction2House = "Debit";
        String descriptionTransaction2House = "EDP bill from February/2020";
        LocalDate dateTransaction2House = LocalDate.of(2020, 03, 01);
        double amountTransaction2House = 45.00;
        ledgerHouse.createAndAddTransactionWithDate(categoryExpensesID, typeTransaction2House, descriptionTransaction2House, amountTransaction2House, dateTransaction2House, houseWalletID, houseEdpID);

        //Expected lst of Transactions
        ArrayList<Transaction> expectedListTransactions = new ArrayList<>();
        expectedListTransactions.add(Transaction.createTransaction(categoryExpensesID, typeTransaction2House, descriptionTransaction2House, amountTransaction2House, dateTransaction2House, houseWalletID, houseEdpID));

        //Get list of Transactions period
        LocalDate startDate = LocalDate.of(2020, 02, 20);
        LocalDate endDate = LocalDate.of(2020, 03, 01);

        //Act
        List<Transaction> result = ledgerHouse.getRecordsBetweenTwoDates(startDate, endDate);

        //Assert
        assertEquals(expectedListTransactions, result);
    }

    @Test
    @DisplayName("Test getRecordsBetweenTwoDates() method - Happy path (no transactions are returned)")
    void getRecordsBetweenTwoDates_HappyPathNoTransactions() {
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
        ledgerHouse.createAndAddTransactionWithDate(categoryExpensesID, typeTransaction1House, descriptionTransaction1House, amountTransaction1House, dateTransaction1House, houseWalletID, houseEdpID);

        //Transaction 2 on Group House
        String typeTransaction2House = "Debit";
        String descriptionTransaction2House = "EDP bill from February/2020";
        LocalDate dateTransaction2House = LocalDate.of(2020, 03, 01);
        double amountTransaction2House = 45.00;
        ledgerHouse.createAndAddTransactionWithDate(categoryExpensesID, typeTransaction2House, descriptionTransaction2House, amountTransaction2House, dateTransaction2House, houseWalletID, houseEdpID);

        //Expected lst of Transactions
        ArrayList<Transaction> expectedListTransactions = new ArrayList<>();

        //Get list of Transactions period
        LocalDate startDate = LocalDate.of(2020, 03, 10);
        LocalDate endDate = LocalDate.of(2020, 03, 20);

        //Act
        List<Transaction> result = ledgerHouse.getRecordsBetweenTwoDates(startDate, endDate);

        //Assert
        assertEquals(expectedListTransactions, result);
    }

    @Test
    @DisplayName("Test getRecordsBetweenTwoDates() method - Exception (start date given is later than end date)")
    void getRecordsBetweenTwoDates_Exception() {
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
        ledgerHouse.createAndAddTransactionWithDate(categoryExpensesID, typeTransaction1House, descriptionTransaction1House, amountTransaction1House, dateTransaction1House, houseWalletID, houseEdpID);

        //Transaction 2 on Group House
        String typeTransaction2House = "Debit";
        String descriptionTransaction2House = "EDP bill from February/2020";
        LocalDate dateTransaction2House = LocalDate.of(2020, 03, 01);
        double amountTransaction2House = 45.00;
        ledgerHouse.createAndAddTransactionWithDate(categoryExpensesID, typeTransaction2House, descriptionTransaction2House, amountTransaction2House, dateTransaction2House, houseWalletID, houseEdpID);

        //Expected lst of Transactions
        ArrayList<Transaction> expectedListTransactions = new ArrayList<>();

        //Get list of Transactions period
        LocalDate startDate = LocalDate.of(2020, 03, 10);
        LocalDate endDate = LocalDate.of(2020, 01, 10);

        //Act
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> ledgerHouse.getRecordsBetweenTwoDates(startDate, endDate));

        //Assert
        assertEquals("Check the start and end dates for the period, since start date cannot be later than end date", thrown.getMessage());
    }


    //Update Group Transaction

    @Test
    @DisplayName("Update Group Transaction")
    public void updateGroupTransaction() {

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
        int transaction_numberOne = 0;
        String typeTransaction1House = "Debit";
        String descriptionTransaction1House = "EDP bill from January/2020";
        LocalDate dateTransaction1House = LocalDate.of(2020, 02, 01);
        double amountTransaction1House = 40.00;
        Transaction transactionOne = Transaction.createTransactionWithSystemDate(categoryExpensesID, typeTransaction1House, descriptionTransaction1House, amountTransaction1House, houseWalletID, houseEdpID);

        //Transaction 2 on Group House
        int transaction_numberTwo = 1;
        String typeTransaction2House = "Debit";
        String descriptionTransaction2House = "EDP bill from February/2020";
        LocalDate dateTransaction2House = LocalDate.of(2020, 03, 01);
        double amountTransaction2House = 45.00;
        Transaction transactionTwo = Transaction.createTransactionWithSystemDate(categoryExpensesID, typeTransaction2House, descriptionTransaction2House, amountTransaction2House, houseWalletID, houseEdpID);

        List<Transaction> records = new ArrayList<>();
        records.add(transactionOne);
        records.add(transactionTwo);

        ledgerHouse.createAndAddTransaction(categoryExpensesID, typeTransaction1House, descriptionTransaction1House, amountTransaction1House, houseWalletID, houseEdpID);

        boolean result = ledgerHouse.updateTransaction(transaction_numberTwo, categoryExpensesID, typeTransaction2House, descriptionTransaction2House, amountTransaction2House, houseWalletID, houseEdpID);


        //Assert

        assertEquals(true, result);

    }


    //Update Person Transaction

    @Test
    @DisplayName("Update Person Transaction")
    public void updatePersonTransaction() {

        //Arrange
        //Person

        String email = "joao@gmail.com";
        PersonID personID = PersonID.createPersonID(email);

        Ledger ledgerHouse = Ledger.createLedger();
        LedgerID ledgerIDHouse = ledgerHouse.getLedgerID();


        //Category Electricity Expenses (Group House)
        String denominationExpenses = "Electricity Expenses";
        Category categoryExpenses = Category.createCategory(denominationExpenses, personID);
        CategoryID categoryExpensesID = categoryExpenses.getCategoryID();

        //Account House Wallet (Group House)
        String houseWalletAccountDenomination = "House Funds";
        String houseWalletAccountDescription = "Money to spend with House expenses";
        Account houseWallet = Account.createAccount(houseWalletAccountDescription, houseWalletAccountDenomination, personID);
        AccountID houseWalletID = houseWallet.getAccountID();

        //Account EDP (Group House)
        String houseEdpAccountDenomination = "EDP";
        String houseEdpAccountDescription = "Electricity expenses from EDP";
        Account houseEdp = Account.createAccount(houseEdpAccountDescription, houseEdpAccountDenomination, personID);
        AccountID houseEdpID = houseEdp.getAccountID();

        //Transaction 1 on Group House
        int transaction_numberOne = 0;
        String typeTransaction1House = "Debit";
        String descriptionTransaction1House = "EDP bill from January/2020";
        double amountTransaction1House = 40.00;
        Transaction transactionOne = Transaction.createTransactionWithSystemDate(categoryExpensesID, typeTransaction1House, descriptionTransaction1House, amountTransaction1House, houseWalletID, houseEdpID);

        //Transaction 2 on Group House
        int transaction_numberTwo = 1;
        String typeTransaction2House = "Debit";
        String descriptionTransaction2House = "EDP bill from February/2020";
        double amountTransaction2House = 45.00;
        Transaction transactionTwo = Transaction.createTransactionWithSystemDate(categoryExpensesID, typeTransaction2House, descriptionTransaction2House, amountTransaction2House, houseWalletID, houseEdpID);

        List<Transaction> records = new ArrayList<>();
        records.add(transactionOne);
        records.add(transactionTwo);

        ledgerHouse.createAndAddTransaction(categoryExpensesID, typeTransaction1House, descriptionTransaction1House, amountTransaction1House, houseWalletID, houseEdpID);

        boolean result = ledgerHouse.updateTransaction(transaction_numberTwo, categoryExpensesID, typeTransaction2House, descriptionTransaction2House, amountTransaction2House, houseWalletID, houseEdpID);


        //Assert

        assertEquals(true, result);

    }

    //Test ledger constructor with parameters
    @Test
    @DisplayName("Test constructor method - Happy path")
    void ledgerConstructor_HappyPath() {
        //ARRANGE
        //Person
        String personEmail = "joaquina@gmail.com";
        PersonID personID = PersonID.createPersonID(personEmail);

        //Ledger ID
        LedgerID ledgerID = LedgerID.createLedgerID();

        //Category Electricity Expenses (Group House)
        String denominationEDPCat = "Electricity Expenses";
        Category categoryEDP = Category.createCategory(denominationEDPCat, personID);
        CategoryID categoryEdpID = categoryEDP.getCategoryID();

        //Category Water Expenses (Group House)
        String denominationWaterCat = "Water Expenses";
        Category categoryWater = Category.createCategory(denominationWaterCat, personID);
        CategoryID categoryWaterID = categoryWater.getCategoryID();

        //Account House Wallet (Group House)
        String personWalletAccountDenomination = "House Funds";
        String personWalletAccountDescription = "Money to spend with House expenses";
        Account personWallet = Account.createAccount(personWalletAccountDescription, personWalletAccountDenomination, personID);
        AccountID personWalletID = personWallet.getAccountID();

        //Account EDP (Group House)
        String personEdpAccountDenomination = "EDP";
        String personEdpAccountDescription = "Electricity expenses from EDP";
        Account personEdp = Account.createAccount(personEdpAccountDescription, personEdpAccountDenomination, personID);
        AccountID personEdpID = personEdp.getAccountID();

        //Account Water (Group House)
        String personWaterAccountDenomination = "Water";
        String personWaterAccountDescription = "Water expenses from AdP";
        Account personWater = Account.createAccount(personWaterAccountDescription, personWaterAccountDenomination, personID);
        AccountID personWaterID = personWater.getAccountID();

        //Transaction 1 on Group House - EDP - Debit
        String typeTransaction1House = "Debit";
        String descriptionTransaction1House = "EDP bill from January/2020";
        LocalDate dateTransaction1House = LocalDate.of(2020, 02, 03);
        double amountTransaction1House = 40.00;
        Transaction transaction1 = Transaction.createTransaction(categoryEdpID, typeTransaction1House, descriptionTransaction1House, amountTransaction1House, dateTransaction1House, personWalletID, personEdpID);

        //Transaction 2 on Group House - EDP - Debit
        String typeTransaction2House = "Debit";
        String descriptionTransaction2House = "EDP bill from February/2020";
        LocalDate dateTransaction2House = LocalDate.of(2020, 03, 03);
        double amountTransaction2House = 45.00;
        Transaction transaction2 = Transaction.createTransaction(categoryEdpID, typeTransaction2House, descriptionTransaction2House, amountTransaction2House, dateTransaction2House, personWalletID, personEdpID);

        //Expected list of Transactions, for the houseEdp Account, within the search period
        List<Transaction> expectedLedgerTransactions = new ArrayList<>();
        expectedLedgerTransactions.add(transaction1);
        expectedLedgerTransactions.add(transaction2);


        //ACT
        Ledger ledger = new Ledger(ledgerID);
        ledger.addTransaction(transaction1);
        ledger.addTransaction(transaction2);


        //ASSERT
        assertEquals(expectedLedgerTransactions, ledger.getRecords());
        assertEquals(ledgerID, ledger.getLedgerID());
    }

    //Get records as DTO
    @Test
    @DisplayName("Test getRecordsAsDTO() method - Happy path")
    void getRecordsAsDTO_HappyPath() {
        //ARRANGE
        //Person
        String personEmail = "joaquina@gmail.com";
        PersonID personID = PersonID.createPersonID(personEmail);
        Ledger ledger = Ledger.createLedger();
        LedgerID ledgerID = ledger.getLedgerID();

        //Category Electricity Expenses
        String denominationCatEDP = "Electricity Expenses";
        Category categoryEDP = Category.createCategory(denominationCatEDP, personID);
        CategoryID categoryEdpID = categoryEDP.getCategoryID();

        //Category Water Expenses
        String denominationCatWater = "Water Expenses";
        Category categoryWater = Category.createCategory(denominationCatWater, personID);
        CategoryID categoryWaterID = categoryWater.getCategoryID();

        //Account House Wallet
        String walletAccountDenomination = "House Funds";
        String walletAccountDescription = "Money to spend with expenses";
        Account walletAccount = Account.createAccount(walletAccountDescription, walletAccountDenomination, personID);
        AccountID walletAccountID = walletAccount.getAccountID();

        //Account EDP
        String edpAccountDenomination = "EDP";
        String edpAccountDescription = "Electricity expenses from EDP";
        Account edpAccount = Account.createAccount(edpAccountDescription, edpAccountDenomination, personID);
        AccountID edpAccountID = edpAccount.getAccountID();

        //Account Water
        String waterAccountDenomination = "Water";
        String waterAccountDescription = "Water expenses from AdP";
        Account waterAccount = Account.createAccount(waterAccountDescription, waterAccountDenomination, personID);
        AccountID waterAccountID = waterAccount.getAccountID();

        //Transaction 1 - EDP - Debit
        String typeTransaction1 = "Debit";
        String descriptionTransaction1 = "EDP bill from January/2020";
        LocalDate dateTransaction1 = LocalDate.of(2020, 02, 03);
        double amountTransaction1 = 40.00;
        Transaction transaction1 = Transaction.createTransaction(categoryEdpID, typeTransaction1, descriptionTransaction1, amountTransaction1, dateTransaction1, walletAccountID, edpAccountID);
        ledger.addTransaction(transaction1);

        //Transaction 2 - EDP - Debit
        String typeTransaction2 = "Debit";
        String descriptionTransaction2 = "EDP bill from February/2020";
        LocalDate dateTransaction2 = LocalDate.of(2020, 03, 03);
        double amountTransaction2 = 45.00;
        Transaction transaction2 = Transaction.createTransaction(categoryEdpID, typeTransaction2, descriptionTransaction2, amountTransaction2, dateTransaction2, walletAccountID, edpAccountID);
        ledger.addTransaction(transaction2);

        //Transaction 3 - EDP - Credit
        String typeTransaction3 = "Credit";
        String descriptionTransaction3 = "EDP bill from March/2020 - settlement - overcharge";
        LocalDate dateTransaction3 = LocalDate.of(2020, 04, 03);
        double amountTransaction3 = 15.00;
        Transaction transaction3 = Transaction.createTransaction(categoryEdpID, typeTransaction3, descriptionTransaction3, amountTransaction3, dateTransaction3, edpAccountID, walletAccountID);
        ledger.addTransaction(transaction3);

        //Transaction 4 - Water - Debit
        String typeTransaction4 = "Debit";
        String descriptionTransaction4 = "Water bill from January/2020";
        LocalDate dateTransaction4 = LocalDate.of(2020, 02, 01);
        double amountTransaction4 = 45.00;
        Transaction transaction4 = Transaction.createTransaction(categoryWaterID, typeTransaction4, descriptionTransaction4, amountTransaction4, dateTransaction4, walletAccountID, waterAccountID);
        ledger.addTransaction(transaction4);

        //Expected list of Transactions, for the houseEdp Account, within the search period
        ArrayList<TransactionDTOout> expectedRecordsAsDTO = new ArrayList<>();
        expectedRecordsAsDTO.add(TransactionDTOoutAssembler.createTransactionDTOout(transaction1));
        expectedRecordsAsDTO.add(TransactionDTOoutAssembler.createTransactionDTOout(transaction2));
        expectedRecordsAsDTO.add(TransactionDTOoutAssembler.createTransactionDTOout(transaction3));
        expectedRecordsAsDTO.add(TransactionDTOoutAssembler.createTransactionDTOout(transaction4));


        //ACT
        List<TransactionDTOout> result = ledger.getRecordsAsDTO();


        //ASSERT
        assertEquals(expectedRecordsAsDTO, result);
    }

    //Get list of Transactions, for a given account, between two dates
    @Test
    @DisplayName("Test getAccountRecordsBetweenTwoDates() method - Happy path")
    void getAccountRecordsBetweenTwoDates_HappyPath() {
        //ARRANGE
        //Person
        String personEmail = "joaquina@gmail.com";
        PersonID personID = PersonID.createPersonID(personEmail);
        Ledger ledger = Ledger.createLedger();
        LedgerID ledgerID = ledger.getLedgerID();

        //Category Electricity Expenses
        String denominationCatEDP = "Electricity Expenses";
        Category categoryEDP = Category.createCategory(denominationCatEDP, personID);
        CategoryID categoryEdpID = categoryEDP.getCategoryID();

        //Category Water Expenses
        String denominationCatWater = "Water Expenses";
        Category categoryWater = Category.createCategory(denominationCatWater, personID);
        CategoryID categoryWaterID = categoryWater.getCategoryID();

        //Account House Wallet
        String walletAccountDenomination = "House Funds";
        String walletAccountDescription = "Money to spend with expenses";
        Account walletAccount = Account.createAccount(walletAccountDescription, walletAccountDenomination, personID);
        AccountID walletAccountID = walletAccount.getAccountID();

        //Account EDP
        String edpAccountDenomination = "EDP";
        String edpAccountDescription = "Electricity expenses from EDP";
        Account edpAccount = Account.createAccount(edpAccountDescription, edpAccountDenomination, personID);
        AccountID edpAccountID = edpAccount.getAccountID();

        //Account Water
        String waterAccountDenomination = "Water";
        String waterAccountDescription = "Water expenses from AdP";
        Account waterAccount = Account.createAccount(waterAccountDescription, waterAccountDenomination, personID);
        AccountID waterAccountID = waterAccount.getAccountID();

        //Transaction 1 - EDP - Debit
        String typeTransaction1 = "Debit";
        String descriptionTransaction1 = "EDP bill from January/2020";
        LocalDate dateTransaction1 = LocalDate.of(2020, 02, 03);
        double amountTransaction1 = 40.00;
        Transaction transaction1 = Transaction.createTransaction(categoryEdpID, typeTransaction1, descriptionTransaction1, amountTransaction1, dateTransaction1, walletAccountID, edpAccountID);
        ledger.addTransaction(transaction1);

        //Transaction 2 - EDP - Debit
        String typeTransaction2 = "Debit";
        String descriptionTransaction2 = "EDP bill from February/2020";
        LocalDate dateTransaction2 = LocalDate.of(2020, 03, 03);
        double amountTransaction2 = 45.00;
        Transaction transaction2 = Transaction.createTransaction(categoryEdpID, typeTransaction2, descriptionTransaction2, amountTransaction2, dateTransaction2, walletAccountID, edpAccountID);
        ledger.addTransaction(transaction2);

        //Transaction 3 - EDP - Credit
        String typeTransaction3 = "Credit";
        String descriptionTransaction3 = "EDP bill from March/2020 - settlement - overcharge";
        LocalDate dateTransaction3 = LocalDate.of(2020, 04, 03);
        double amountTransaction3 = 15.00;
        Transaction transaction3 = Transaction.createTransaction(categoryEdpID, typeTransaction3, descriptionTransaction3, amountTransaction3, dateTransaction3, edpAccountID, walletAccountID);
        ledger.addTransaction(transaction3);

        //Transaction 4 - Water - Debit
        String typeTransaction4 = "Debit";
        String descriptionTransaction4 = "Water bill from January/2020";
        LocalDate dateTransaction4 = LocalDate.of(2020, 02, 01);
        double amountTransaction4 = 45.00;
        Transaction transaction4 = Transaction.createTransaction(categoryWaterID, typeTransaction4, descriptionTransaction4, amountTransaction4, dateTransaction4, walletAccountID, waterAccountID);
        ledger.addTransaction(transaction4);

        //Period to search
        LocalDate startDate = LocalDate.of(2020, 01, 10);
        LocalDate endDate = LocalDate.of(2020, 04, 20);

        //Expected list of Transactions, for the edpAccount, within the search period
        ArrayList<Transaction> expectedListTransactions = new ArrayList<>();
        expectedListTransactions.add(transaction1);
        expectedListTransactions.add(transaction2);
        expectedListTransactions.add(transaction3);


        //ACT
        List<Transaction> result = ledger.getAccountRecordsBetweenTwoDates(edpAccountID, startDate, endDate);


        //ASSERT
        assertEquals(expectedListTransactions, result);
    }

    @Test
    @DisplayName("Test getAccountRecordsBetweenTwoDates() method - Happy path (search period endpoints included)")
    void getAccountRecordsBetweenTwoDates_HappyPath_DatesAtEndpoints() {
        //ARRANGE
        //Person
        String personEmail = "joaquina@gmail.com";
        PersonID personID = PersonID.createPersonID(personEmail);
        Ledger ledger = Ledger.createLedger();
        LedgerID ledgerID = ledger.getLedgerID();

        //Category Electricity Expenses
        String denominationCatEDP = "Electricity Expenses";
        Category categoryEDP = Category.createCategory(denominationCatEDP, personID);
        CategoryID categoryEdpID = categoryEDP.getCategoryID();

        //Category Water Expenses
        String denominationCatWater = "Water Expenses";
        Category categoryWater = Category.createCategory(denominationCatWater, personID);
        CategoryID categoryWaterID = categoryWater.getCategoryID();

        //Account House Wallet
        String walletAccountDenomination = "House Funds";
        String walletAccountDescription = "Money to spend with expenses";
        Account walletAccount = Account.createAccount(walletAccountDescription, walletAccountDenomination, personID);
        AccountID walletAccountID = walletAccount.getAccountID();

        //Account EDP
        String edpAccountDenomination = "EDP";
        String edpAccountDescription = "Electricity expenses from EDP";
        Account edpAccount = Account.createAccount(edpAccountDescription, edpAccountDenomination, personID);
        AccountID edpAccountID = edpAccount.getAccountID();

        //Account Water
        String waterAccountDenomination = "Water";
        String waterAccountDescription = "Water expenses from AdP";
        Account waterAccount = Account.createAccount(waterAccountDescription, waterAccountDenomination, personID);
        AccountID waterAccountID = waterAccount.getAccountID();

        //Transaction 1 - EDP - Debit
        String typeTransaction1 = "Debit";
        String descriptionTransaction1 = "EDP bill from January/2020";
        LocalDate dateTransaction1 = LocalDate.of(2020, 02, 03);
        double amountTransaction1 = 40.00;
        Transaction transaction1 = Transaction.createTransaction(categoryEdpID, typeTransaction1, descriptionTransaction1, amountTransaction1, dateTransaction1, walletAccountID, edpAccountID);
        ledger.addTransaction(transaction1);

        //Transaction 2 - EDP - Debit
        String typeTransaction2 = "Debit";
        String descriptionTransaction2 = "EDP bill from February/2020";
        LocalDate dateTransaction2 = LocalDate.of(2020, 03, 03);
        double amountTransaction2 = 45.00;
        Transaction transaction2 = Transaction.createTransaction(categoryEdpID, typeTransaction2, descriptionTransaction2, amountTransaction2, dateTransaction2, walletAccountID, edpAccountID);
        ledger.addTransaction(transaction2);

        //Transaction 3 - EDP - Credit
        String typeTransaction3 = "Credit";
        String descriptionTransaction3 = "EDP bill from March/2020 - settlement - overcharge";
        LocalDate dateTransaction3 = LocalDate.of(2020, 04, 03);
        double amountTransaction3 = 15.00;
        Transaction transaction3 = Transaction.createTransaction(categoryEdpID, typeTransaction3, descriptionTransaction3, amountTransaction3, dateTransaction3, edpAccountID, walletAccountID);
        ledger.addTransaction(transaction3);

        //Transaction 4 - Water - Debit
        String typeTransaction4 = "Debit";
        String descriptionTransaction4 = "Water bill from January/2020";
        LocalDate dateTransaction4 = LocalDate.of(2020, 02, 01);
        double amountTransaction4 = 45.00;
        Transaction transaction4 = Transaction.createTransaction(categoryWaterID, typeTransaction4, descriptionTransaction4, amountTransaction4, dateTransaction4, walletAccountID, waterAccountID);
        ledger.addTransaction(transaction4);

        //Period to search
        LocalDate startDate = LocalDate.of(2020, 02, 01);
        LocalDate endDate = LocalDate.of(2020, 04, 03);

        //Expected list of Transactions, for the houseEdp Account, within the search period
        ArrayList<Transaction> expectedListTransactions = new ArrayList<>();
        expectedListTransactions.add(transaction1);
        expectedListTransactions.add(transaction2);
        expectedListTransactions.add(transaction3);


        //ACT
        List<Transaction> result = ledger.getAccountRecordsBetweenTwoDates(edpAccountID, startDate, endDate);


        //ASSERT
        assertEquals(expectedListTransactions, result);
    }


    @Test
    @DisplayName("Test getAccountRecordsBetweenTwoDates() method - Happy path (only some transactions)")
    void getAccountRecordsBetweenTwoDates_HappyPath_SomeTransactions() {
        //ARRANGE
        //Person
        String personEmail = "joaquina@gmail.com";
        PersonID personID = PersonID.createPersonID(personEmail);
        Ledger ledger = Ledger.createLedger();
        LedgerID ledgerID = ledger.getLedgerID();

        //Category Electricity Expenses
        String denominationCatEDP = "Electricity Expenses";
        Category categoryEDP = Category.createCategory(denominationCatEDP, personID);
        CategoryID categoryEdpID = categoryEDP.getCategoryID();

        //Category Water Expenses
        String denominationCatWater = "Water Expenses";
        Category categoryWater = Category.createCategory(denominationCatWater, personID);
        CategoryID categoryWaterID = categoryWater.getCategoryID();

        //Account House Wallet
        String walletAccountDenomination = "House Funds";
        String walletAccountDescription = "Money to spend with expenses";
        Account walletAccount = Account.createAccount(walletAccountDescription, walletAccountDenomination, personID);
        AccountID walletAccountID = walletAccount.getAccountID();

        //Account EDP
        String edpAccountDenomination = "EDP";
        String edpAccountDescription = "Electricity expenses from EDP";
        Account edpAccount = Account.createAccount(edpAccountDescription, edpAccountDenomination, personID);
        AccountID edpAccountID = edpAccount.getAccountID();

        //Account Water
        String waterAccountDenomination = "Water";
        String waterAccountDescription = "Water expenses from AdP";
        Account waterAccount = Account.createAccount(waterAccountDescription, waterAccountDenomination, personID);
        AccountID waterAccountID = waterAccount.getAccountID();

        //Transaction 1 - EDP - Debit
        String typeTransaction1 = "Debit";
        String descriptionTransaction1 = "EDP bill from January/2020";
        LocalDate dateTransaction1 = LocalDate.of(2020, 02, 03);
        double amountTransaction1 = 40.00;
        Transaction transaction1 = Transaction.createTransaction(categoryEdpID, typeTransaction1, descriptionTransaction1, amountTransaction1, dateTransaction1, walletAccountID, edpAccountID);
        ledger.addTransaction(transaction1);

        //Transaction 2 - EDP - Debit
        String typeTransaction2 = "Debit";
        String descriptionTransaction2 = "EDP bill from February/2020";
        LocalDate dateTransaction2 = LocalDate.of(2020, 03, 03);
        double amountTransaction2 = 45.00;
        Transaction transaction2 = Transaction.createTransaction(categoryEdpID, typeTransaction2, descriptionTransaction2, amountTransaction2, dateTransaction2, walletAccountID, edpAccountID);
        ledger.addTransaction(transaction2);

        //Transaction 3 - EDP - Credit
        String typeTransaction3 = "Credit";
        String descriptionTransaction3 = "EDP bill from March/2020 - settlement - overcharge";
        LocalDate dateTransaction3 = LocalDate.of(2020, 04, 03);
        double amountTransaction3 = 15.00;
        Transaction transaction3 = Transaction.createTransaction(categoryEdpID, typeTransaction3, descriptionTransaction3, amountTransaction3, dateTransaction3, edpAccountID, walletAccountID);
        ledger.addTransaction(transaction3);

        //Transaction 4 - Water - Debit
        String typeTransaction4 = "Debit";
        String descriptionTransaction4 = "Water bill from January/2020";
        LocalDate dateTransaction4 = LocalDate.of(2020, 02, 01);
        double amountTransaction4 = 45.00;
        Transaction transaction4 = Transaction.createTransaction(categoryWaterID, typeTransaction4, descriptionTransaction4, amountTransaction4, dateTransaction4, walletAccountID, waterAccountID);
        ledger.addTransaction(transaction4);

        //Period to search
        LocalDate startDate = LocalDate.of(2020, 03, 03);
        LocalDate endDate = LocalDate.of(2020, 04, 03);

        //Expected list of Transactions, for the houseEdp Account, within the search period
        ArrayList<Transaction> expectedListTransactions = new ArrayList<>();
        expectedListTransactions.add(transaction2);
        expectedListTransactions.add(transaction3);


        //ACT
        List<Transaction> result = ledger.getAccountRecordsBetweenTwoDates(edpAccountID, startDate, endDate);


        //ASSERT
        assertEquals(expectedListTransactions, result);
    }

    @Test
    @DisplayName("Test getAccountRecordsBetweenTwoDates() method - Happy path (transactions within period but none for the given account)")
    void getAccountRecordsBetweenTwoDates_HappyPath_NoTransactionsForAccount() {
        //ARRANGE
        //Person
        String personEmail = "joaquina@gmail.com";
        PersonID personID = PersonID.createPersonID(personEmail);
        Ledger ledger = Ledger.createLedger();
        LedgerID ledgerID = ledger.getLedgerID();

        //Category Electricity Expenses
        String denominationCatEDP = "Electricity Expenses";
        Category categoryEDP = Category.createCategory(denominationCatEDP, personID);
        CategoryID categoryEdpID = categoryEDP.getCategoryID();

        //Category Water Expenses
        String denominationCatWater = "Water Expenses";
        Category categoryWater = Category.createCategory(denominationCatWater, personID);
        CategoryID categoryWaterID = categoryWater.getCategoryID();

        //Account House Wallet
        String walletAccountDenomination = "House Funds";
        String walletAccountDescription = "Money to spend with expenses";
        Account walletAccount = Account.createAccount(walletAccountDescription, walletAccountDenomination, personID);
        AccountID walletAccountID = walletAccount.getAccountID();

        //Account EDP
        String edpAccountDenomination = "EDP";
        String edpAccountDescription = "Electricity expenses from EDP";
        Account edpAccount = Account.createAccount(edpAccountDescription, edpAccountDenomination, personID);
        AccountID edpAccountID = edpAccount.getAccountID();

        //Account Water
        String waterAccountDenomination = "Water";
        String waterAccountDescription = "Water expenses from AdP";
        Account waterAccount = Account.createAccount(waterAccountDescription, waterAccountDenomination, personID);
        AccountID waterAccountID = waterAccount.getAccountID();

        //Transaction 1 - EDP - Debit
        String typeTransaction1 = "Debit";
        String descriptionTransaction1 = "EDP bill from January/2020";
        LocalDate dateTransaction1 = LocalDate.of(2020, 02, 03);
        double amountTransaction1 = 40.00;
        Transaction transaction1 = Transaction.createTransaction(categoryEdpID, typeTransaction1, descriptionTransaction1, amountTransaction1, dateTransaction1, walletAccountID, edpAccountID);
        ledger.addTransaction(transaction1);

        //Transaction 2 - EDP - Debit
        String typeTransaction2 = "Debit";
        String descriptionTransaction2 = "EDP bill from February/2020";
        LocalDate dateTransaction2 = LocalDate.of(2020, 03, 03);
        double amountTransaction2 = 45.00;
        Transaction transaction2 = Transaction.createTransaction(categoryEdpID, typeTransaction2, descriptionTransaction2, amountTransaction2, dateTransaction2, walletAccountID, edpAccountID);
        ledger.addTransaction(transaction2);

        //Transaction 3 - EDP - Credit
        String typeTransaction3 = "Credit";
        String descriptionTransaction3 = "EDP bill from March/2020 - settlement - overcharge";
        LocalDate dateTransaction3 = LocalDate.of(2020, 04, 03);
        double amountTransaction3 = 15.00;
        Transaction transaction3 = Transaction.createTransaction(categoryEdpID, typeTransaction3, descriptionTransaction3, amountTransaction3, dateTransaction3, edpAccountID, walletAccountID);
        ledger.addTransaction(transaction3);

        //Transaction 4 - Water - Debit
        String typeTransaction4 = "Debit";
        String descriptionTransaction4 = "Water bill from January/2020";
        LocalDate dateTransaction4 = LocalDate.of(2020, 02, 01);
        double amountTransaction4 = 45.00;
        Transaction transaction4 = Transaction.createTransaction(categoryWaterID, typeTransaction4, descriptionTransaction4, amountTransaction4, dateTransaction4, walletAccountID, waterAccountID);
        ledger.addTransaction(transaction4);

        //Period to search
        LocalDate startDate = LocalDate.of(2020, 01, 01);
        LocalDate endDate = LocalDate.of(2020, 02, 02);

        //Expected message
        String expectedMessage = "Ledger has no records associated with this account";

        //ACT
        Throwable thrown = assertThrows(IllegalStateException.class, () -> ledger.getAccountRecordsBetweenTwoDates(edpAccountID, startDate, endDate));

        //ASSERT
        assertEquals(expectedMessage, thrown.getMessage());
    }

    @Test
    @DisplayName("Test getAccountRecordsBetweenTwoDates() method - Happy path (all transactions out of the period)")
    void getAccountRecordsBetweenTwoDates_HappyPath_NoTransactions() {
        //ARRANGE
        //Person
        String personEmail = "joaquina@gmail.com";
        PersonID personID = PersonID.createPersonID(personEmail);
        Ledger ledger = Ledger.createLedger();
        LedgerID ledgerID = ledger.getLedgerID();

        //Category Electricity Expenses
        String denominationCatEDP = "Electricity Expenses";
        Category categoryEDP = Category.createCategory(denominationCatEDP, personID);
        CategoryID categoryEdpID = categoryEDP.getCategoryID();

        //Category Water Expenses
        String denominationCatWater = "Water Expenses";
        Category categoryWater = Category.createCategory(denominationCatWater, personID);
        CategoryID categoryWaterID = categoryWater.getCategoryID();

        //Account House Wallet
        String walletAccountDenomination = "House Funds";
        String walletAccountDescription = "Money to spend with expenses";
        Account walletAccount = Account.createAccount(walletAccountDescription, walletAccountDenomination, personID);
        AccountID walletAccountID = walletAccount.getAccountID();

        //Account EDP
        String edpAccountDenomination = "EDP";
        String edpAccountDescription = "Electricity expenses from EDP";
        Account edpAccount = Account.createAccount(edpAccountDescription, edpAccountDenomination, personID);
        AccountID edpAccountID = edpAccount.getAccountID();

        //Account Water
        String waterAccountDenomination = "Water";
        String waterAccountDescription = "Water expenses from AdP";
        Account waterAccount = Account.createAccount(waterAccountDescription, waterAccountDenomination, personID);
        AccountID waterAccountID = waterAccount.getAccountID();

        //Transaction 1 - EDP - Debit
        String typeTransaction1 = "Debit";
        String descriptionTransaction1 = "EDP bill from January/2020";
        LocalDate dateTransaction1 = LocalDate.of(2020, 02, 03);
        double amountTransaction1 = 40.00;
        Transaction transaction1 = Transaction.createTransaction(categoryEdpID, typeTransaction1, descriptionTransaction1, amountTransaction1, dateTransaction1, walletAccountID, edpAccountID);
        ledger.addTransaction(transaction1);

        //Transaction 2 - EDP - Debit
        String typeTransaction2 = "Debit";
        String descriptionTransaction2 = "EDP bill from February/2020";
        LocalDate dateTransaction2 = LocalDate.of(2020, 03, 03);
        double amountTransaction2 = 45.00;
        Transaction transaction2 = Transaction.createTransaction(categoryEdpID, typeTransaction2, descriptionTransaction2, amountTransaction2, dateTransaction2, walletAccountID, edpAccountID);
        ledger.addTransaction(transaction2);

        //Transaction 3 - EDP - Credit
        String typeTransaction3 = "Credit";
        String descriptionTransaction3 = "EDP bill from March/2020 - settlement - overcharge";
        LocalDate dateTransaction3 = LocalDate.of(2020, 04, 03);
        double amountTransaction3 = 15.00;
        Transaction transaction3 = Transaction.createTransaction(categoryEdpID, typeTransaction3, descriptionTransaction3, amountTransaction3, dateTransaction3, edpAccountID, walletAccountID);
        ledger.addTransaction(transaction3);

        //Transaction 4 - Water - Debit
        String typeTransaction4 = "Debit";
        String descriptionTransaction4 = "Water bill from January/2020";
        LocalDate dateTransaction4 = LocalDate.of(2020, 02, 01);
        double amountTransaction4 = 45.00;
        Transaction transaction4 = Transaction.createTransaction(categoryWaterID, typeTransaction4, descriptionTransaction4, amountTransaction4, dateTransaction4, walletAccountID, waterAccountID);
        ledger.addTransaction(transaction4);

        //Period to search
        LocalDate startDate = LocalDate.of(2020, 01, 01);
        LocalDate endDate = LocalDate.of(2020, 01, 10);

        //Expected message
        String expectedMessage = "Ledger has no records associated with this account";

        //ACT
        Throwable thrown = assertThrows(IllegalStateException.class, () -> ledger.getAccountRecordsBetweenTwoDates(edpAccountID, startDate, endDate));

        //ASSERT
        assertEquals(expectedMessage, thrown.getMessage());
    }

    @Test
    @DisplayName("Test getAccountRecordsBetweenTwoDates() method - Exception (start date given is later than end date)")
    void getAccountRecordsBetweenTwoDates_Exception() {
        //ARRANGE
        //Person
        String personEmail = "joaquina@gmail.com";
        PersonID personID = PersonID.createPersonID(personEmail);
        Ledger ledger = Ledger.createLedger();
        LedgerID ledgerID = ledger.getLedgerID();

        //Category Electricity Expenses
        String denominationCatEDP = "Electricity Expenses";
        Category categoryEDP = Category.createCategory(denominationCatEDP, personID);
        CategoryID categoryEdpID = categoryEDP.getCategoryID();

        //Category Water Expenses
        String denominationCatWater = "Water Expenses";
        Category categoryWater = Category.createCategory(denominationCatWater, personID);
        CategoryID categoryWaterID = categoryWater.getCategoryID();

        //Account House Wallet
        String walletAccountDenomination = "House Funds";
        String walletAccountDescription = "Money to spend with expenses";
        Account walletAccount = Account.createAccount(walletAccountDescription, walletAccountDenomination, personID);
        AccountID walletAccountID = walletAccount.getAccountID();

        //Account EDP
        String edpAccountDenomination = "EDP";
        String edpAccountDescription = "Electricity expenses from EDP";
        Account edpAccount = Account.createAccount(edpAccountDescription, edpAccountDenomination, personID);
        AccountID edpAccountID = edpAccount.getAccountID();

        //Account Water
        String waterAccountDenomination = "Water";
        String waterAccountDescription = "Water expenses from AdP";
        Account waterAccount = Account.createAccount(waterAccountDescription, waterAccountDenomination, personID);
        AccountID waterAccountID = waterAccount.getAccountID();

        //Transaction 1 - EDP - Debit
        String typeTransaction1 = "Debit";
        String descriptionTransaction1 = "EDP bill from January/2020";
        LocalDate dateTransaction1 = LocalDate.of(2020, 02, 03);
        double amountTransaction1 = 40.00;
        Transaction transaction1 = Transaction.createTransaction(categoryEdpID, typeTransaction1, descriptionTransaction1, amountTransaction1, dateTransaction1, walletAccountID, edpAccountID);
        ledger.addTransaction(transaction1);

        //Transaction 2 - EDP - Debit
        String typeTransaction2 = "Debit";
        String descriptionTransaction2 = "EDP bill from February/2020";
        LocalDate dateTransaction2 = LocalDate.of(2020, 03, 03);
        double amountTransaction2 = 45.00;
        Transaction transaction2 = Transaction.createTransaction(categoryEdpID, typeTransaction2, descriptionTransaction2, amountTransaction2, dateTransaction2, walletAccountID, edpAccountID);
        ledger.addTransaction(transaction2);

        //Transaction 3 - EDP - Credit
        String typeTransaction3 = "Credit";
        String descriptionTransaction3 = "EDP bill from March/2020 - settlement - overcharge";
        LocalDate dateTransaction3 = LocalDate.of(2020, 04, 03);
        double amountTransaction3 = 15.00;
        Transaction transaction3 = Transaction.createTransaction(categoryEdpID, typeTransaction3, descriptionTransaction3, amountTransaction3, dateTransaction3, edpAccountID, walletAccountID);
        ledger.addTransaction(transaction3);

        //Transaction 4 - Water - Debit
        String typeTransaction4 = "Debit";
        String descriptionTransaction4 = "Water bill from January/2020";
        LocalDate dateTransaction4 = LocalDate.of(2020, 02, 01);
        double amountTransaction4 = 45.00;
        Transaction transaction4 = Transaction.createTransaction(categoryWaterID, typeTransaction4, descriptionTransaction4, amountTransaction4, dateTransaction4, walletAccountID, waterAccountID);
        ledger.addTransaction(transaction4);

        //Period to search
        LocalDate startDate = LocalDate.of(2020, 04, 03);
        LocalDate endDate = LocalDate.of(2020, 02, 01);


        //ACT
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> ledger.getAccountRecordsBetweenTwoDates(edpAccountID, startDate, endDate));


        //ASSERT
        assertEquals("Check the start and end dates for the period, since start date cannot be later than end date", thrown.getMessage());
    }

    //Test retrieving all unique accounts ID from ledger records, sorted alphabetically
    @Test
    @DisplayName("Test getRecordsAccountsSorted() method - Ledger has records")
    void etRecordsAccountsSorted_ledgerHasRecords() {
        //ARRANGE
        //Person
        String personEmail = "joaquina@gmail.com";
        PersonID personID = PersonID.createPersonID(personEmail);
        Ledger ledger = Ledger.createLedger();
        LedgerID ledgerID = ledger.getLedgerID();

        //Category Electricity Expenses
        String denominationCatEDP = "Electricity Expenses";
        Category categoryEDP = Category.createCategory(denominationCatEDP, personID);
        CategoryID categoryEdpID = categoryEDP.getCategoryID();

        //Category Water Expenses
        String denominationCatWater = "Water Expenses";
        Category categoryWater = Category.createCategory(denominationCatWater, personID);
        CategoryID categoryWaterID = categoryWater.getCategoryID();

        //Account House Wallet
        String walletAccountDenomination = "House Funds";
        String walletAccountDescription = "Money to spend with expenses";
        Account walletAccount = Account.createAccount(walletAccountDescription, walletAccountDenomination, personID);
        AccountID walletAccountID = walletAccount.getAccountID();

        //Account EDP
        String edpAccountDenomination = "EDP";
        String edpAccountDescription = "Electricity expenses from EDP";
        Account edpAccount = Account.createAccount(edpAccountDescription, edpAccountDenomination, personID);
        AccountID edpAccountID = edpAccount.getAccountID();

        //Account Water
        String waterAccountDenomination = "Water";
        String waterAccountDescription = "Water expenses from AdP";
        Account waterAccount = Account.createAccount(waterAccountDescription, waterAccountDenomination, personID);
        AccountID waterAccountID = waterAccount.getAccountID();

        //Transaction 1 - EDP - Debit
        String typeTransaction1 = "Debit";
        String descriptionTransaction1 = "EDP bill from January/2020";
        LocalDate dateTransaction1 = LocalDate.of(2020, 02, 03);
        double amountTransaction1 = 40.00;
        Transaction transaction1 = Transaction.createTransaction(categoryEdpID, typeTransaction1, descriptionTransaction1, amountTransaction1, dateTransaction1, walletAccountID, edpAccountID);

        //Transaction 2 - EDP - Debit
        String typeTransaction2 = "Debit";
        String descriptionTransaction2 = "EDP bill from February/2020";
        LocalDate dateTransaction2 = LocalDate.of(2020, 03, 03);
        double amountTransaction2 = 45.00;
        Transaction transaction2 = Transaction.createTransaction(categoryEdpID, typeTransaction2, descriptionTransaction2, amountTransaction2, dateTransaction2, walletAccountID, edpAccountID);

        //Transaction 3 - EDP - Credit
        String typeTransaction3 = "Credit";
        String descriptionTransaction3 = "EDP bill from March/2020 - settlement - overcharge";
        LocalDate dateTransaction3 = LocalDate.of(2020, 04, 03);
        double amountTransaction3 = 15.00;
        Transaction transaction3 = Transaction.createTransaction(categoryEdpID, typeTransaction3, descriptionTransaction3, amountTransaction3, dateTransaction3, edpAccountID, walletAccountID);

        //Transaction 4 - Water - Debit
        String typeTransaction4 = "Debit";
        String descriptionTransaction4 = "Water bill from January/2020";
        LocalDate dateTransaction4 = LocalDate.of(2020, 02, 01);
        double amountTransaction4 = 45.00;
        Transaction transaction4 = Transaction.createTransaction(categoryWaterID, typeTransaction4, descriptionTransaction4, amountTransaction4, dateTransaction4, walletAccountID, waterAccountID);

        //Add transactions to ledger
        ledger.addTransaction(transaction4);
        ledger.addTransaction(transaction3);
        ledger.addTransaction(transaction2);
        ledger.addTransaction(transaction1);

        //Expected list of Accounts
        List<AccountID> expectedList = new ArrayList<>();
        expectedList.add(edpAccountID);
        expectedList.add(walletAccountID);
        expectedList.add(waterAccountID);


        //ACT
        List<AccountID> result = ledger.getAccountsOfRecordsSorted();


        //ASSERT
        assertEquals(expectedList, result);
    }

    @Test
    @DisplayName("Test getRecordsAccountsSorted() method - Ledger does not have records")
    void etRecordsAccountsSorted_ledgerHasNoRecords() {
        //ARRANGE
        //Person
        String personEmail = "joaquina@gmail.com";
        Ledger ledger = Ledger.createLedger();

        //Expected message
        String expectedMessage = "Ledger is empty (no accounts to report)";

        //ACT
        Throwable thrown = assertThrows(IllegalStateException.class, () -> ledger.getAccountsOfRecordsSorted());

        //ASSERT
        assertEquals(expectedMessage, thrown.getMessage());
    }

    //Get list of Transactions, for a given account, as of a given date
    @Test
    @DisplayName("Test getAccountRecordsAsOfDate() method - Happy path")
    void getAccountRecordsAsOfDate_HappyPath() {
        //ARRANGE
        //Person
        String personEmail = "joaquina@gmail.com";
        PersonID personID = PersonID.createPersonID(personEmail);
        Ledger ledger = Ledger.createLedger();
        LedgerID ledgerID = ledger.getLedgerID();

        //Category Electricity Expenses
        String denominationCatEDP = "Electricity Expenses";
        Category categoryEDP = Category.createCategory(denominationCatEDP, personID);
        CategoryID categoryEdpID = categoryEDP.getCategoryID();

        //Category Water Expenses
        String denominationCatWater = "Water Expenses";
        Category categoryWater = Category.createCategory(denominationCatWater, personID);
        CategoryID categoryWaterID = categoryWater.getCategoryID();

        //Account House Wallet
        String walletAccountDenomination = "House Funds";
        String walletAccountDescription = "Money to spend with expenses";
        Account walletAccount = Account.createAccount(walletAccountDescription, walletAccountDenomination, personID);
        AccountID walletAccountID = walletAccount.getAccountID();

        //Account EDP
        String edpAccountDenomination = "EDP";
        String edpAccountDescription = "Electricity expenses from EDP";
        Account edpAccount = Account.createAccount(edpAccountDescription, edpAccountDenomination, personID);
        AccountID edpAccountID = edpAccount.getAccountID();

        //Account Water
        String waterAccountDenomination = "Water";
        String waterAccountDescription = "Water expenses from AdP";
        Account waterAccount = Account.createAccount(waterAccountDescription, waterAccountDenomination, personID);
        AccountID waterAccountID = waterAccount.getAccountID();

        //Transaction 1 - EDP - Debit
        String typeTransaction1 = "Debit";
        String descriptionTransaction1 = "EDP bill from January/2020";
        LocalDate dateTransaction1 = LocalDate.of(2020, 02, 03);
        double amountTransaction1 = 40.00;
        Transaction transaction1 = Transaction.createTransaction(categoryEdpID, typeTransaction1, descriptionTransaction1, amountTransaction1, dateTransaction1, walletAccountID, edpAccountID);
        ledger.addTransaction(transaction1);

        //Transaction 2 - EDP - Debit
        String typeTransaction2 = "Debit";
        String descriptionTransaction2 = "EDP bill from February/2020";
        LocalDate dateTransaction2 = LocalDate.of(2020, 03, 03);
        double amountTransaction2 = 45.00;
        Transaction transaction2 = Transaction.createTransaction(categoryEdpID, typeTransaction2, descriptionTransaction2, amountTransaction2, dateTransaction2, walletAccountID, edpAccountID);
        ledger.addTransaction(transaction2);

        //Transaction 3 - EDP - Credit
        String typeTransaction3 = "Credit";
        String descriptionTransaction3 = "EDP bill from March/2020 - settlement - overcharge";
        LocalDate dateTransaction3 = LocalDate.of(2020, 04, 03);
        double amountTransaction3 = 15.00;
        Transaction transaction3 = Transaction.createTransaction(categoryEdpID, typeTransaction3, descriptionTransaction3, amountTransaction3, dateTransaction3, edpAccountID, walletAccountID);
        ledger.addTransaction(transaction3);

        //Transaction 4 - Water - Debit
        String typeTransaction4 = "Debit";
        String descriptionTransaction4 = "Water bill from January/2020";
        LocalDate dateTransaction4 = LocalDate.of(2020, 02, 01);
        double amountTransaction4 = 45.00;
        Transaction transaction4 = Transaction.createTransaction(categoryWaterID, typeTransaction4, descriptionTransaction4, amountTransaction4, dateTransaction4, walletAccountID, waterAccountID);
        ledger.addTransaction(transaction4);

        //Date to search
        LocalDate startDate = LocalDate.of(2020, 03, 3);

        //Expected list of Transactions, for the edpAccount, within the search period
        ArrayList<Transaction> expectedListTransactions = new ArrayList<>();
        expectedListTransactions.add(transaction2);
        expectedListTransactions.add(transaction3);


        //ACT
        List<Transaction> result = ledger.getAccountRecordsAsOfDate(edpAccountID, startDate);


        //ASSERT
        assertEquals(expectedListTransactions, result);
    }

    @Test
    @DisplayName("Test getAccountRecordsAsOfDate() method - No records")
    void getAccountRecordsAsOfDate_NoRecords() {
        //ARRANGE
        //Person
        String personEmail = "joaquina@gmail.com";
        PersonID personID = PersonID.createPersonID(personEmail);
        Ledger ledger = Ledger.createLedger();
        LedgerID ledgerID = ledger.getLedgerID();

        //Category Electricity Expenses
        String denominationCatEDP = "Electricity Expenses";
        Category categoryEDP = Category.createCategory(denominationCatEDP, personID);
        CategoryID categoryEdpID = categoryEDP.getCategoryID();

        //Category Water Expenses
        String denominationCatWater = "Water Expenses";
        Category categoryWater = Category.createCategory(denominationCatWater, personID);
        CategoryID categoryWaterID = categoryWater.getCategoryID();

        //Account House Wallet
        String walletAccountDenomination = "House Funds";
        String walletAccountDescription = "Money to spend with expenses";
        Account walletAccount = Account.createAccount(walletAccountDescription, walletAccountDenomination, personID);
        AccountID walletAccountID = walletAccount.getAccountID();

        //Account EDP
        String edpAccountDenomination = "EDP";
        String edpAccountDescription = "Electricity expenses from EDP";
        Account edpAccount = Account.createAccount(edpAccountDescription, edpAccountDenomination, personID);
        AccountID edpAccountID = edpAccount.getAccountID();

        //Account Water
        String waterAccountDenomination = "Water";
        String waterAccountDescription = "Water expenses from AdP";
        Account waterAccount = Account.createAccount(waterAccountDescription, waterAccountDenomination, personID);
        AccountID waterAccountID = waterAccount.getAccountID();

        //Transaction 1 - EDP - Debit
        String typeTransaction1 = "Debit";
        String descriptionTransaction1 = "EDP bill from January/2020";
        LocalDate dateTransaction1 = LocalDate.of(2020, 02, 03);
        double amountTransaction1 = 40.00;
        Transaction transaction1 = Transaction.createTransaction(categoryEdpID, typeTransaction1, descriptionTransaction1, amountTransaction1, dateTransaction1, walletAccountID, edpAccountID);
        ledger.addTransaction(transaction1);

        //Transaction 2 - EDP - Debit
        String typeTransaction2 = "Debit";
        String descriptionTransaction2 = "EDP bill from February/2020";
        LocalDate dateTransaction2 = LocalDate.of(2020, 03, 03);
        double amountTransaction2 = 45.00;
        Transaction transaction2 = Transaction.createTransaction(categoryEdpID, typeTransaction2, descriptionTransaction2, amountTransaction2, dateTransaction2, walletAccountID, edpAccountID);
        ledger.addTransaction(transaction2);

        //Transaction 3 - EDP - Credit
        String typeTransaction3 = "Credit";
        String descriptionTransaction3 = "EDP bill from March/2020 - settlement - overcharge";
        LocalDate dateTransaction3 = LocalDate.of(2020, 04, 03);
        double amountTransaction3 = 15.00;
        Transaction transaction3 = Transaction.createTransaction(categoryEdpID, typeTransaction3, descriptionTransaction3, amountTransaction3, dateTransaction3, edpAccountID, walletAccountID);
        ledger.addTransaction(transaction3);

        //Transaction 4 - Water - Debit
        String typeTransaction4 = "Debit";
        String descriptionTransaction4 = "Water bill from January/2020";
        LocalDate dateTransaction4 = LocalDate.of(2020, 02, 01);
        double amountTransaction4 = 45.00;
        Transaction transaction4 = Transaction.createTransaction(categoryWaterID, typeTransaction4, descriptionTransaction4, amountTransaction4, dateTransaction4, walletAccountID, waterAccountID);
        ledger.addTransaction(transaction4);

        //Date to search
        LocalDate startDate = LocalDate.of(2020, 05, 1);

        //Expected message
        String expectedMessage = "Ledger has no records associated with this account";


        //ACT
        Throwable thrown = assertThrows(IllegalStateException.class, () -> ledger.getAccountRecordsAsOfDate(edpAccountID, startDate));


        //ASSERT
        assertEquals(expectedMessage, thrown.getMessage());
    }

    //Get earliest record date
    @Test
    @DisplayName("Test getEarliestTransactionDate() method - Happy path")
    void earliestRecordDate_HappyPath() {
        //ARRANGE
        //Person
        String personEmail = "joaquina@gmail.com";
        PersonID personID = PersonID.createPersonID(personEmail);
        Ledger ledger = Ledger.createLedger();
        LedgerID ledgerID = ledger.getLedgerID();

        //Category Electricity Expenses
        String denominationCatEDP = "Electricity Expenses";
        Category categoryEDP = Category.createCategory(denominationCatEDP, personID);
        CategoryID categoryEdpID = categoryEDP.getCategoryID();

        //Category Water Expenses
        String denominationCatWater = "Water Expenses";
        Category categoryWater = Category.createCategory(denominationCatWater, personID);
        CategoryID categoryWaterID = categoryWater.getCategoryID();

        //Account House Wallet
        String walletAccountDenomination = "House Funds";
        String walletAccountDescription = "Money to spend with expenses";
        Account walletAccount = Account.createAccount(walletAccountDescription, walletAccountDenomination, personID);
        AccountID walletAccountID = walletAccount.getAccountID();

        //Account EDP
        String edpAccountDenomination = "EDP";
        String edpAccountDescription = "Electricity expenses from EDP";
        Account edpAccount = Account.createAccount(edpAccountDescription, edpAccountDenomination, personID);
        AccountID edpAccountID = edpAccount.getAccountID();

        //Account Water
        String waterAccountDenomination = "Water";
        String waterAccountDescription = "Water expenses from AdP";
        Account waterAccount = Account.createAccount(waterAccountDescription, waterAccountDenomination, personID);
        AccountID waterAccountID = waterAccount.getAccountID();

        //Transaction 1 - EDP - Debit
        String typeTransaction1 = "Debit";
        String descriptionTransaction1 = "EDP bill from January/2020";
        LocalDate dateTransaction1 = LocalDate.of(2020, 02, 03);
        double amountTransaction1 = 40.00;
        Transaction transaction1 = Transaction.createTransaction(categoryEdpID, typeTransaction1, descriptionTransaction1, amountTransaction1, dateTransaction1, walletAccountID, edpAccountID);
        ledger.addTransaction(transaction1);

        //Transaction 2 - EDP - Debit
        String typeTransaction2 = "Debit";
        String descriptionTransaction2 = "EDP bill from February/2020";
        LocalDate dateTransaction2 = LocalDate.of(2020, 03, 03);
        double amountTransaction2 = 45.00;
        Transaction transaction2 = Transaction.createTransaction(categoryEdpID, typeTransaction2, descriptionTransaction2, amountTransaction2, dateTransaction2, walletAccountID, edpAccountID);
        ledger.addTransaction(transaction2);

        //Transaction 3 - EDP - Credit
        String typeTransaction3 = "Credit";
        String descriptionTransaction3 = "EDP bill from March/2020 - settlement - overcharge";
        LocalDate dateTransaction3 = LocalDate.of(2020, 01, 10);
        double amountTransaction3 = 15.00;
        Transaction transaction3 = Transaction.createTransaction(categoryEdpID, typeTransaction3, descriptionTransaction3, amountTransaction3, dateTransaction3, edpAccountID, walletAccountID);
        ledger.addTransaction(transaction3);

        //Transaction 4 - Water - Debit
        String typeTransaction4 = "Debit";
        String descriptionTransaction4 = "Water bill from January/2020";
        LocalDate dateTransaction4 = LocalDate.of(2020, 02, 01);
        double amountTransaction4 = 45.00;
        Transaction transaction4 = Transaction.createTransaction(categoryWaterID, typeTransaction4, descriptionTransaction4, amountTransaction4, dateTransaction4, walletAccountID, waterAccountID);
        ledger.addTransaction(transaction4);

        //Expected earliest date
        LocalDate expectedDate = LocalDate.of(2020, 01, 10);


        //ACT
        LocalDate result = ledger.getEarliestTransactionDate();


        //ASSERT
        assertEquals(expectedDate, result);
    }

    @Test
    @DisplayName("Test getEarliestTransactionDate() method - Empty ledger")
    void earliestRecordDate_emptyLedger() {
        //ARRANGE
        Ledger ledger = Ledger.createLedger();

        //Expected message
        String expectedMessage = "Ledger is empty (no transactions)";

        //ACT
        Throwable thrown = assertThrows(IllegalStateException.class, () -> ledger.getEarliestTransactionDate());

        //ASSERT
        assertEquals(expectedMessage, thrown.getMessage());
    }

    //Get latest record date
    @Test
    @DisplayName("Test getLatestTransactionDate() method - Happy path")
    void latestRecordDate_HappyPath() {
        //ARRANGE
        //Person
        String personEmail = "joaquina@gmail.com";
        PersonID personID = PersonID.createPersonID(personEmail);
        Ledger ledger = Ledger.createLedger();
        LedgerID ledgerID = ledger.getLedgerID();

        //Category Electricity Expenses
        String denominationCatEDP = "Electricity Expenses";
        Category categoryEDP = Category.createCategory(denominationCatEDP, personID);
        CategoryID categoryEdpID = categoryEDP.getCategoryID();

        //Category Water Expenses
        String denominationCatWater = "Water Expenses";
        Category categoryWater = Category.createCategory(denominationCatWater, personID);
        CategoryID categoryWaterID = categoryWater.getCategoryID();

        //Account House Wallet
        String walletAccountDenomination = "House Funds";
        String walletAccountDescription = "Money to spend with expenses";
        Account walletAccount = Account.createAccount(walletAccountDescription, walletAccountDenomination, personID);
        AccountID walletAccountID = walletAccount.getAccountID();

        //Account EDP
        String edpAccountDenomination = "EDP";
        String edpAccountDescription = "Electricity expenses from EDP";
        Account edpAccount = Account.createAccount(edpAccountDescription, edpAccountDenomination, personID);
        AccountID edpAccountID = edpAccount.getAccountID();

        //Account Water
        String waterAccountDenomination = "Water";
        String waterAccountDescription = "Water expenses from AdP";
        Account waterAccount = Account.createAccount(waterAccountDescription, waterAccountDenomination, personID);
        AccountID waterAccountID = waterAccount.getAccountID();

        //Transaction 1 - EDP - Debit
        String typeTransaction1 = "Debit";
        String descriptionTransaction1 = "EDP bill from January/2020";
        LocalDate dateTransaction1 = LocalDate.of(2020, 02, 03);
        double amountTransaction1 = 40.00;
        Transaction transaction1 = Transaction.createTransaction(categoryEdpID, typeTransaction1, descriptionTransaction1, amountTransaction1, dateTransaction1, walletAccountID, edpAccountID);
        ledger.addTransaction(transaction1);

        //Transaction 2 - EDP - Debit
        String typeTransaction2 = "Debit";
        String descriptionTransaction2 = "EDP bill from February/2020";
        LocalDate dateTransaction2 = LocalDate.of(2020, 03, 03);
        double amountTransaction2 = 45.00;
        Transaction transaction2 = Transaction.createTransaction(categoryEdpID, typeTransaction2, descriptionTransaction2, amountTransaction2, dateTransaction2, walletAccountID, edpAccountID);
        ledger.addTransaction(transaction2);

        //Transaction 3 - EDP - Credit
        String typeTransaction3 = "Credit";
        String descriptionTransaction3 = "EDP bill from March/2020 - settlement - overcharge";
        LocalDate dateTransaction3 = LocalDate.of(2020, 04, 03);
        double amountTransaction3 = 15.00;
        Transaction transaction3 = Transaction.createTransaction(categoryEdpID, typeTransaction3, descriptionTransaction3, amountTransaction3, dateTransaction3, edpAccountID, walletAccountID);
        ledger.addTransaction(transaction3);

        //Transaction 4 - Water - Debit
        String typeTransaction4 = "Debit";
        String descriptionTransaction4 = "Water bill from January/2020";
        LocalDate dateTransaction4 = LocalDate.of(2020, 02, 01);
        double amountTransaction4 = 45.00;
        Transaction transaction4 = Transaction.createTransaction(categoryWaterID, typeTransaction4, descriptionTransaction4, amountTransaction4, dateTransaction4, walletAccountID, waterAccountID);
        ledger.addTransaction(transaction4);

        //Expected earliest date
        LocalDate expectedDate = LocalDate.of(2020, 04, 03);


        //ACT
        LocalDate result = ledger.getLatestTransactionDate();


        //ASSERT
        assertEquals(expectedDate, result);
    }

    @Test
    @DisplayName("Test getLatestTransactionDate() method - Empty ledger")
    void latestRecordDate_emptyLedger() {
        //ARRANGE
        Ledger ledger = Ledger.createLedger();

        //Expected message
        String expectedMessage = "Ledger is empty (no transactions)";

        //ACT
        Throwable thrown = assertThrows(IllegalStateException.class, () -> ledger.getLatestTransactionDate());

        //ASSERT
        assertEquals(expectedMessage, thrown.getMessage());
    }

    //Get list of all Transactions, for a given account, sorted by date
    @Test
    @DisplayName("Test getAccountRecords() method - Happy path")
    void getAccountRecords_HappyPath() {
        //ARRANGE
        //Person
        String personEmail = "joaquina@gmail.com";
        PersonID personID = PersonID.createPersonID(personEmail);
        Ledger ledger = Ledger.createLedger();

        //Category Electricity Expenses
        String denominationCatEDP = "Electricity Expenses";
        Category categoryEDP = Category.createCategory(denominationCatEDP, personID);
        CategoryID categoryEdpID = categoryEDP.getCategoryID();

        //Category Water Expenses
        String denominationCatWater = "Water Expenses";
        Category categoryWater = Category.createCategory(denominationCatWater, personID);
        CategoryID categoryWaterID = categoryWater.getCategoryID();

        //Account House Wallet
        String walletAccountDenomination = "House Funds";
        String walletAccountDescription = "Money to spend with expenses";
        Account walletAccount = Account.createAccount(walletAccountDescription, walletAccountDenomination, personID);
        AccountID walletAccountID = walletAccount.getAccountID();

        //Account EDP
        String edpAccountDenomination = "EDP";
        String edpAccountDescription = "Electricity expenses from EDP";
        Account edpAccount = Account.createAccount(edpAccountDescription, edpAccountDenomination, personID);
        AccountID edpAccountID = edpAccount.getAccountID();

        //Account Water
        String waterAccountDenomination = "Water";
        String waterAccountDescription = "Water expenses from AdP";
        Account waterAccount = Account.createAccount(waterAccountDescription, waterAccountDenomination, personID);
        AccountID waterAccountID = waterAccount.getAccountID();

        //Transaction 1 - EDP - Debit
        String typeTransaction1 = "Debit";
        String descriptionTransaction1 = "EDP bill from January/2020";
        LocalDate dateTransaction1 = LocalDate.of(2020, 02, 03);
        double amountTransaction1 = 40.00;
        Transaction transaction1 = Transaction.createTransaction(categoryEdpID, typeTransaction1, descriptionTransaction1, amountTransaction1, dateTransaction1, walletAccountID, edpAccountID);

        //Transaction 2 - EDP - Debit
        String typeTransaction2 = "Debit";
        String descriptionTransaction2 = "EDP bill from February/2020";
        LocalDate dateTransaction2 = LocalDate.of(2020, 03, 03);
        double amountTransaction2 = 45.00;
        Transaction transaction2 = Transaction.createTransaction(categoryEdpID, typeTransaction2, descriptionTransaction2, amountTransaction2, dateTransaction2, walletAccountID, edpAccountID);

        //Transaction 3 - EDP - Credit
        String typeTransaction3 = "Credit";
        String descriptionTransaction3 = "EDP bill from March/2020 - settlement - overcharge";
        LocalDate dateTransaction3 = LocalDate.of(2020, 04, 03);
        double amountTransaction3 = 15.00;
        Transaction transaction3 = Transaction.createTransaction(categoryEdpID, typeTransaction3, descriptionTransaction3, amountTransaction3, dateTransaction3, edpAccountID, walletAccountID);

        //Transaction 4 - Water - Debit
        String typeTransaction4 = "Debit";
        String descriptionTransaction4 = "Water bill from January/2020";
        LocalDate dateTransaction4 = LocalDate.of(2020, 02, 01);
        double amountTransaction4 = 45.00;
        Transaction transaction4 = Transaction.createTransaction(categoryWaterID, typeTransaction4, descriptionTransaction4, amountTransaction4, dateTransaction4, walletAccountID, waterAccountID);

        //Add transactions to ledger
        ledger.addTransaction(transaction4);
        ledger.addTransaction(transaction2);
        ledger.addTransaction(transaction3);
        ledger.addTransaction(transaction1);

        //Expected ordered list of Transactions, for the edpAccount
        ArrayList<Transaction> expectedListTransactions = new ArrayList<>();
        expectedListTransactions.add(transaction1);
        expectedListTransactions.add(transaction2);
        expectedListTransactions.add(transaction3);


        //ACT
        List<Transaction> result = ledger.getAccountRecords(edpAccountID);


        //ASSERT
        assertEquals(expectedListTransactions, result);
    }

    @Test
    @DisplayName("Test getAccountRecords() method - No records for the account")
    void getAccountRecords_noRecordsForAccount() {
        //ARRANGE
        //Person
        String personEmail = "joaquina@gmail.com";
        PersonID personID = PersonID.createPersonID(personEmail);
        Ledger ledger = Ledger.createLedger();

        //Category Water Expenses
        String denominationCatWater = "Water Expenses";
        Category categoryWater = Category.createCategory(denominationCatWater, personID);
        CategoryID categoryWaterID = categoryWater.getCategoryID();

        //Account House Wallet
        String walletAccountDenomination = "House Funds";
        String walletAccountDescription = "Money to spend with expenses";
        Account walletAccount = Account.createAccount(walletAccountDescription, walletAccountDenomination, personID);
        AccountID walletAccountID = walletAccount.getAccountID();

        //Account EDP
        String edpAccountDenomination = "EDP";
        String edpAccountDescription = "Electricity expenses from EDP";
        Account edpAccount = Account.createAccount(edpAccountDescription, edpAccountDenomination, personID);
        AccountID edpAccountID = edpAccount.getAccountID();

        //Account Water
        String waterAccountDenomination = "Water";
        String waterAccountDescription = "Water expenses from AdP";
        Account waterAccount = Account.createAccount(waterAccountDescription, waterAccountDenomination, personID);
        AccountID waterAccountID = waterAccount.getAccountID();

        //Transaction 4 - Water - Debit
        String typeTransaction4 = "Debit";
        String descriptionTransaction4 = "Water bill from January/2020";
        LocalDate dateTransaction4 = LocalDate.of(2020, 02, 01);
        double amountTransaction4 = 45.00;
        Transaction transaction4 = Transaction.createTransaction(categoryWaterID, typeTransaction4, descriptionTransaction4, amountTransaction4, dateTransaction4, walletAccountID, waterAccountID);

        //Add transactions to ledger
        ledger.addTransaction(transaction4);

        //Expected message
        String expectedMessage = "Ledger has no records associated with this account";

        //ACT
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> ledger.getAccountRecords(edpAccountID));

        //ASSERT
        assertEquals(expectedMessage, thrown.getMessage());
    }

    @Test
    @DisplayName("Test getAccountRecords() method - Empty ledger")
    void getAccountRecords_emptyLedger() {
        //ARRANGE
        //Person
        String personEmail = "joaquina@gmail.com";
        PersonID personID = PersonID.createPersonID(personEmail);
        Ledger ledger = Ledger.createLedger();

        //Account EDP
        String edpAccountDenomination = "EDP";
        String edpAccountDescription = "Electricity expenses from EDP";
        Account edpAccount = Account.createAccount(edpAccountDescription, edpAccountDenomination, personID);
        AccountID edpAccountID = AccountID.createAccountID(edpAccountDenomination, personID);

        //Expected message
        String expectedMessage = "Ledger has no records associated with this account";

        //ACT
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> ledger.getAccountRecords(edpAccountID));

        //ASSERT
        assertEquals(expectedMessage, thrown.getMessage());
    }

    //Get the earliest date of records of a given account
    @Test
    @DisplayName("Test getAccountRecordsEarliestDate() method - Happy path")
    void getAccountRecordsEarliestDate_HappyPath() {
        //ARRANGE
        //Person
        String personEmail = "joaquina@gmail.com";
        PersonID personID = PersonID.createPersonID(personEmail);
        Ledger ledger = Ledger.createLedger();

        //Category Electricity Expenses
        String denominationCatEDP = "Electricity Expenses";
        Category categoryEDP = Category.createCategory(denominationCatEDP, personID);
        CategoryID categoryEdpID = categoryEDP.getCategoryID();

        //Category Water Expenses
        String denominationCatWater = "Water Expenses";
        Category categoryWater = Category.createCategory(denominationCatWater, personID);
        CategoryID categoryWaterID = categoryWater.getCategoryID();

        //Account House Wallet
        String walletAccountDenomination = "House Funds";
        String walletAccountDescription = "Money to spend with expenses";
        Account walletAccount = Account.createAccount(walletAccountDescription, walletAccountDenomination, personID);
        AccountID walletAccountID = walletAccount.getAccountID();

        //Account EDP
        String edpAccountDenomination = "EDP";
        String edpAccountDescription = "Electricity expenses from EDP";
        Account edpAccount = Account.createAccount(edpAccountDescription, edpAccountDenomination, personID);
        AccountID edpAccountID = edpAccount.getAccountID();

        //Account Water
        String waterAccountDenomination = "Water";
        String waterAccountDescription = "Water expenses from AdP";
        Account waterAccount = Account.createAccount(waterAccountDescription, waterAccountDenomination, personID);
        AccountID waterAccountID = waterAccount.getAccountID();

        //Transaction 1 - EDP - Debit
        String typeTransaction1 = "Debit";
        String descriptionTransaction1 = "EDP bill from January/2020";
        LocalDate dateTransaction1 = LocalDate.of(2020, 02, 03);
        double amountTransaction1 = 40.00;
        Transaction transaction1 = Transaction.createTransaction(categoryEdpID, typeTransaction1, descriptionTransaction1, amountTransaction1, dateTransaction1, walletAccountID, edpAccountID);

        //Transaction 2 - EDP - Debit
        String typeTransaction2 = "Debit";
        String descriptionTransaction2 = "EDP bill from February/2020";
        LocalDate dateTransaction2 = LocalDate.of(2020, 03, 03);
        double amountTransaction2 = 45.00;
        Transaction transaction2 = Transaction.createTransaction(categoryEdpID, typeTransaction2, descriptionTransaction2, amountTransaction2, dateTransaction2, walletAccountID, edpAccountID);

        //Transaction 3 - EDP - Credit
        String typeTransaction3 = "Credit";
        String descriptionTransaction3 = "EDP bill from March/2020 - settlement - overcharge";
        LocalDate dateTransaction3 = LocalDate.of(2020, 04, 03);
        double amountTransaction3 = 15.00;
        Transaction transaction3 = Transaction.createTransaction(categoryEdpID, typeTransaction3, descriptionTransaction3, amountTransaction3, dateTransaction3, edpAccountID, walletAccountID);

        //Transaction 4 - Water - Debit
        String typeTransaction4 = "Debit";
        String descriptionTransaction4 = "Water bill from January/2020";
        LocalDate dateTransaction4 = LocalDate.of(2020, 02, 01);
        double amountTransaction4 = 45.00;
        Transaction transaction4 = Transaction.createTransaction(categoryWaterID, typeTransaction4, descriptionTransaction4, amountTransaction4, dateTransaction4, walletAccountID, waterAccountID);

        //Add transactions to ledger
        ledger.addTransaction(transaction4);
        ledger.addTransaction(transaction2);
        ledger.addTransaction(transaction3);
        ledger.addTransaction(transaction1);

        //Expected earliest date for records related with EDP account
        LocalDate expectedEarliestDate = LocalDate.of(2020, 02, 03);


        //ACT
        LocalDate result = ledger.getAccountRecordsEarliestDate(edpAccountID);


        //ASSERT
        assertEquals(expectedEarliestDate, result);
    }

    @Test
    @DisplayName("Test getAccountRecordsEarliestDate() method - No records")
    void getAccountRecordsEarliestDate_noRecords() {
        //ARRANGE
        //Person
        String personEmail = "joaquina@gmail.com";
        PersonID personID = PersonID.createPersonID(personEmail);
        Ledger ledger = Ledger.createLedger();

        //Category Water Expenses
        String denominationCatWater = "Water Expenses";
        Category categoryWater = Category.createCategory(denominationCatWater, personID);
        CategoryID categoryWaterID = categoryWater.getCategoryID();

        //Account House Wallet
        String walletAccountDenomination = "House Funds";
        String walletAccountDescription = "Money to spend with expenses";
        Account walletAccount = Account.createAccount(walletAccountDescription, walletAccountDenomination, personID);
        AccountID walletAccountID = walletAccount.getAccountID();

        //Account EDP
        String edpAccountDenomination = "EDP";
        String edpAccountDescription = "Electricity expenses from EDP";
        Account edpAccount = Account.createAccount(edpAccountDescription, edpAccountDenomination, personID);
        AccountID edpAccountID = edpAccount.getAccountID();

        //Account Water
        String waterAccountDenomination = "Water";
        String waterAccountDescription = "Water expenses from AdP";
        Account waterAccount = Account.createAccount(waterAccountDescription, waterAccountDenomination, personID);
        AccountID waterAccountID = waterAccount.getAccountID();

        //Transaction 4 - Water - Debit
        String typeTransaction4 = "Debit";
        String descriptionTransaction4 = "Water bill from January/2020";
        LocalDate dateTransaction4 = LocalDate.of(2020, 02, 01);
        double amountTransaction4 = 45.00;
        Transaction transaction4 = Transaction.createTransaction(categoryWaterID, typeTransaction4, descriptionTransaction4, amountTransaction4, dateTransaction4, walletAccountID, waterAccountID);

        //Add transactions to ledger
        ledger.addTransaction(transaction4);

        //Expected message
        String expectedMessage = "Ledger has no records associated with this account";


        //ACT
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> ledger.getAccountRecordsEarliestDate(edpAccountID));


        //ASSERT
        assertEquals(expectedMessage, thrown.getMessage());
    }

    //Get the latest date of records of a given account
    @Test
    @DisplayName("Test getAccountRecordsLatestDate() method - Happy path")
    void getAccountRecordsLatestDate_HappyPath() {
        //ARRANGE
        //Person
        String personEmail = "joaquina@gmail.com";
        PersonID personID = PersonID.createPersonID(personEmail);
        Ledger ledger = Ledger.createLedger();

        //Category Electricity Expenses
        String denominationCatEDP = "Electricity Expenses";
        Category categoryEDP = Category.createCategory(denominationCatEDP, personID);
        CategoryID categoryEdpID = categoryEDP.getCategoryID();

        //Category Water Expenses
        String denominationCatWater = "Water Expenses";
        Category categoryWater = Category.createCategory(denominationCatWater, personID);
        CategoryID categoryWaterID = categoryWater.getCategoryID();

        //Account House Wallet
        String walletAccountDenomination = "House Funds";
        String walletAccountDescription = "Money to spend with expenses";
        Account walletAccount = Account.createAccount(walletAccountDescription, walletAccountDenomination, personID);
        AccountID walletAccountID = walletAccount.getAccountID();

        //Account EDP
        String edpAccountDenomination = "EDP";
        String edpAccountDescription = "Electricity expenses from EDP";
        Account edpAccount = Account.createAccount(edpAccountDescription, edpAccountDenomination, personID);
        AccountID edpAccountID = edpAccount.getAccountID();

        //Account Water
        String waterAccountDenomination = "Water";
        String waterAccountDescription = "Water expenses from AdP";
        Account waterAccount = Account.createAccount(waterAccountDescription, waterAccountDenomination, personID);
        AccountID waterAccountID = waterAccount.getAccountID();

        //Transaction 1 - EDP - Debit
        String typeTransaction1 = "Debit";
        String descriptionTransaction1 = "EDP bill from January/2020";
        LocalDate dateTransaction1 = LocalDate.of(2020, 02, 03);
        double amountTransaction1 = 40.00;
        Transaction transaction1 = Transaction.createTransaction(categoryEdpID, typeTransaction1, descriptionTransaction1, amountTransaction1, dateTransaction1, walletAccountID, edpAccountID);

        //Transaction 2 - EDP - Debit
        String typeTransaction2 = "Debit";
        String descriptionTransaction2 = "EDP bill from February/2020";
        LocalDate dateTransaction2 = LocalDate.of(2020, 03, 03);
        double amountTransaction2 = 45.00;
        Transaction transaction2 = Transaction.createTransaction(categoryEdpID, typeTransaction2, descriptionTransaction2, amountTransaction2, dateTransaction2, walletAccountID, edpAccountID);

        //Transaction 3 - EDP - Credit
        String typeTransaction3 = "Credit";
        String descriptionTransaction3 = "EDP bill from March/2020 - settlement - overcharge";
        LocalDate dateTransaction3 = LocalDate.of(2020, 04, 03);
        double amountTransaction3 = 15.00;
        Transaction transaction3 = Transaction.createTransaction(categoryEdpID, typeTransaction3, descriptionTransaction3, amountTransaction3, dateTransaction3, edpAccountID, walletAccountID);

        //Transaction 4 - Water - Debit
        String typeTransaction4 = "Debit";
        String descriptionTransaction4 = "Water bill from January/2020";
        LocalDate dateTransaction4 = LocalDate.of(2020, 02, 01);
        double amountTransaction4 = 45.00;
        Transaction transaction4 = Transaction.createTransaction(categoryWaterID, typeTransaction4, descriptionTransaction4, amountTransaction4, dateTransaction4, walletAccountID, waterAccountID);

        //Add transactions to ledger
        ledger.addTransaction(transaction4);
        ledger.addTransaction(transaction2);
        ledger.addTransaction(transaction3);
        ledger.addTransaction(transaction1);

        //Expected earliest date for records related with EDP account
        LocalDate expectedEarliestDate = LocalDate.of(2020, 04, 03);


        //ACT
        LocalDate result = ledger.getAccountRecordsLatestDate(edpAccountID);


        //ASSERT
        assertEquals(expectedEarliestDate, result);
    }

    @Test
    @DisplayName("Test getAccountRecordsLatestDate() method - No records")
    void getAccountRecordsLatestDate_noRecords() {
        //ARRANGE
        //Person
        String personEmail = "joaquina@gmail.com";
        PersonID personID = PersonID.createPersonID(personEmail);
        Ledger ledger = Ledger.createLedger();

        //Category Water Expenses
        String denominationCatWater = "Water Expenses";
        Category categoryWater = Category.createCategory(denominationCatWater, personID);
        CategoryID categoryWaterID = categoryWater.getCategoryID();

        //Account House Wallet
        String walletAccountDenomination = "House Funds";
        String walletAccountDescription = "Money to spend with expenses";
        Account walletAccount = Account.createAccount(walletAccountDescription, walletAccountDenomination, personID);
        AccountID walletAccountID = walletAccount.getAccountID();

        //Account EDP
        String edpAccountDenomination = "EDP";
        String edpAccountDescription = "Electricity expenses from EDP";
        Account edpAccount = Account.createAccount(edpAccountDescription, edpAccountDenomination, personID);
        AccountID edpAccountID = edpAccount.getAccountID();

        //Account Water
        String waterAccountDenomination = "Water";
        String waterAccountDescription = "Water expenses from AdP";
        Account waterAccount = Account.createAccount(waterAccountDescription, waterAccountDenomination, personID);
        AccountID waterAccountID = waterAccount.getAccountID();

        //Transaction 4 - Water - Debit
        String typeTransaction4 = "Debit";
        String descriptionTransaction4 = "Water bill from January/2020";
        LocalDate dateTransaction4 = LocalDate.of(2020, 02, 01);
        double amountTransaction4 = 45.00;
        Transaction transaction4 = Transaction.createTransaction(categoryWaterID, typeTransaction4, descriptionTransaction4, amountTransaction4, dateTransaction4, walletAccountID, waterAccountID);

        //Add transactions to ledger
        ledger.addTransaction(transaction4);

        //Expected message
        String expectedMessage = "Ledger has no records associated with this account";


        //ACT
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> ledger.getAccountRecordsLatestDate(edpAccountID));


        //ASSERT
        assertEquals(expectedMessage, thrown.getMessage());
    }

/*

    //Get Amount of records between 2 dates

    @Test
    @DisplayName("Get Records Amount between 2 dates | Happy Case")
    public void testGetAmountBetween2dates() {

        //Arrange of Category
        String categoryDenomination = "Salary";
        Category categorySalary = new Category(categoryDenomination);

        //Arrange of Debit Account
        Description debAccountDescription = new Description("EmployerSA Account");
        Denomination debAccountDenomination = new Denomination("EmployerSA");

        Account accountDebit = new Account(debAccountDescription, debAccountDenomination);

        //Arrange of Credit Account
        Description credAccountDescription = new Description("Margarida Salary Account");
        Denomination credAccountDenomination = new Denomination("Salary");

        Account accountCredit = new Account(credAccountDescription, credAccountDenomination);

        //Arrange of Transaction

        //Transaction A
        String transactionType = "Credit";
        String transactionDescription = "Dez 2019 Salary";
        double transactionAmount = 1000.00;
        LocalDate date = LocalDate.of(2019, 12, 25);

        //TransactionB
        String transactionTypeB = "Credit";
        String transactionDescriptionB = "Jan 2020 Salary";
        double transactionAmountB = 1500.00;
        LocalDate dateB = LocalDate.of(2020, 01, 01);


        Ledger ledger = new Ledger();
        ledger.createAndAddTransactionWithDate(categorySalary, transactionType, transactionDescription, transactionAmount, date, accountDebit, accountCredit);
        ledger.createAndAddTransactionWithDate(categorySalary, transactionTypeB, transactionDescriptionB, transactionAmountB, dateB, accountDebit, accountCredit);

        //Act
        double expectedAmount = 2500.00;

        double searchResult = ledger.getAmountBetweenTwoDates(LocalDate.of(2019, 01, 01), LocalDate.of(2020, 01, 25));

        //Assert

        assertEquals(expectedAmount, searchResult);
    }



    @Test
    @DisplayName("Get Amount between 2 dates - out of range| HappyCase")
    public void testAmountBetween2Dates_dateOutOfRange() {

        //Arrange of Category
        String categoryDenomination = "Salary";
        Category categorySalary = new Category(categoryDenomination);

        //Arrange of Debit Account
        Description debAccountDescription = new Description("EmployerSA Account");
        Denomination debAccountDenomination = new Denomination("EmployerSA");

        Account accountDebit = new Account(debAccountDescription, debAccountDenomination);

        //Arrange of Credit Account
        Description credAccountDescription = new Description("Margarida Salary Account");
        Denomination credAccountDenomination = new Denomination("Salary");

        Account accountCredit = new Account(credAccountDescription, credAccountDenomination);

        //Arrange of Transaction

        //Transaction A
        String transactionType = "Credit";
        String transactionDescription = "Dez 2017 Salary";
        double transactionAmount = 1000.00;
        LocalDate date = LocalDate.of(2017, 12, 25);

        //TransactionB
        String transactionTypeB = "Credit";
        String transactionDescriptionB = "Jan 2017 Salary";
        double transactionAmountB = 1500.00;
        LocalDate dateB = LocalDate.of(2017, 01, 01);


        Ledger ledger = new Ledger();
        ledger.createAndAddTransactionWithDate(categorySalary, transactionType, transactionDescription, transactionAmount, date, accountDebit, accountCredit);
        ledger.createAndAddTransactionWithDate(categorySalary, transactionTypeB, transactionDescriptionB, transactionAmountB, dateB, accountDebit, accountCredit);


        //Act

        double expectedAmount = 0.00;

        double searchResult = ledger.getAmountBetweenTwoDates(LocalDate.of(2019, 01, 01), LocalDate.of(2020, 01, 25));

        //Assert

        assertEquals(expectedAmount, searchResult);
    }


    @Test
    @DisplayName("Get Amount between 2 dates | Sad Case")
    public void testGetAmountBetween2Dates_dateOutOfRange() {

        //Arrange of Category
        String categoryDenomination = "Salary";
        Category categorySalary = new Category(categoryDenomination);

        //Arrange of Debit Account
        Description debAccountDescription = new Description("EmployerSA Account");
        Denomination debAccountDenomination = new Denomination("EmployerSA");

        Account accountDebit = new Account(debAccountDescription, debAccountDenomination);

        //Arrange of Credit Account
        Description credAccountDescription = new Description("Margarida Salary Account");
        Denomination credAccountDenomination = new Denomination("Salary");

        Account accountCredit = new Account(credAccountDescription, credAccountDenomination);

        //Arrange of Transaction

        //Transaction A
        String transactionType = "Credit";
        String transactionDescription = "Dez 2019 Salary";
        double transactionAmount = 1000.00;
        LocalDate date = LocalDate.of(2019, 12, 25);

        //TransactionB
        String transactionTypeB = "Credit";
        String transactionDescriptionB = "Jan 2020 Salary";
        double transactionAmountB = 1500.00;
        LocalDate dateB = LocalDate.of(2020, 01, 01);

        //TransactionB
        String transactionTypeC = "Credit";
        String transactionDescriptionC = "Jan 2018 Salary";
        double transactionAmountC = 800.00;
        LocalDate dateC = LocalDate.of(2018, 01, 01);


        Ledger ledger = new Ledger();
        ledger.createAndAddTransactionWithDate(categorySalary, transactionType, transactionDescription, transactionAmount, date, accountDebit, accountCredit);
        ledger.createAndAddTransactionWithDate(categorySalary, transactionTypeB, transactionDescriptionB, transactionAmountB, dateB, accountDebit, accountCredit);
        ledger.createAndAddTransactionWithDate(categorySalary, transactionTypeC, transactionDescriptionC, transactionAmountC, dateC, accountDebit, accountCredit);


        //Act


        double expectedAmount = 3300.00;

        double searchResult = ledger.getAmountBetweenTwoDates(LocalDate.of(2019, 01, 01), LocalDate.of(2020, 01, 25));

        //Assert

        assertNotEquals(expectedAmount, searchResult);
    }

*/
    //Tests for Equals

    @Test
    @DisplayName("Verify if two ledgers are the same | Different ledger: Different transaction")
    public void testNotEqualsDifferentLedger() {

        //Arrange PersonID

        PersonID joaoPersonID = PersonID.createPersonID("joao@gmail.com");

        PersonID joanaPersonID = PersonID.createPersonID("joana@gmail.com");

        //Arrange of Transaction

        //Arrange of Transaction Ledger 1

        String transactionType = "Credit";
        String transactionDescription = "Dez 2019 Salary";
        double transactionAmount = 1000.00;
        LocalDate date = LocalDate.of(2010, 12, 25);

        //Arrange of Transaction Ledger 2

        String transactionTypeL2 = "Credit";
        String transactionDescriptionL2 = "Jan 2020 Salary";
        double transactionAmountL2 = 1000.00;
        LocalDate transactionDateL2 = LocalDate.of(2020, 02, 01);

        //Arrange of Debit Account

        AccountID creditAccountIDJoao = AccountID.createAccountID("EmployerSA", joaoPersonID);

        AccountID creditAccountIDJoana = AccountID.createAccountID("EmployerSA", joanaPersonID);

        //Arrange of Credit Account

        AccountID debitAccountIDJoao = AccountID.createAccountID("João Salary Account", joaoPersonID);

        AccountID debitAccountIDJoana = AccountID.createAccountID("João Salary Account", joanaPersonID);

        //Arrange of Category

        CategoryID categoryIDSalaryJoao = CategoryID.createCategoryID("Salary", joaoPersonID);

        CategoryID categoryIDSalaryJoana = CategoryID.createCategoryID("Salary", joanaPersonID);

        //Act Ledger1

        Ledger ledgerJoao = Ledger.createLedger();

        ledgerJoao.createAndAddTransactionWithDate(categoryIDSalaryJoao, transactionType, transactionDescription, transactionAmount, date, debitAccountIDJoao, creditAccountIDJoao);

        //Act Ledger2

        Ledger ledgerJoana = Ledger.createLedger();

        ledgerJoana.createAndAddTransactionWithDate(categoryIDSalaryJoana, transactionTypeL2, transactionDescriptionL2, transactionAmountL2, transactionDateL2, debitAccountIDJoana, creditAccountIDJoana);

        //Assert;

        assertNotEquals(ledgerJoao, ledgerJoana);
    }

    @Test
    @DisplayName("Verify if two ledgers are the same | Different ledger: One ledger null")
    public void testNotEqualsLedgerNull() {

        //Arrange PersonID

        PersonID joaoPersonID = PersonID.createPersonID("joao@gmail.com");

        //Arrange of Transaction

        //Arrange of Transaction Ledger 1

        String transactionType = "Credit";
        String transactionDescription = "Dez 2019 Salary";
        double transactionAmount = 1000.00;
        LocalDate date = LocalDate.of(2010, 12, 25);

        //Arrange of Debit Account

        AccountID creditAccountIDJoao = AccountID.createAccountID("EmployerSA", joaoPersonID);

        //Arrange of Credit Account

        AccountID debitAccountIDJoao = AccountID.createAccountID("João Salary Account", joaoPersonID);

        //Arrange of Category

        CategoryID categoryIDSalaryJoao = CategoryID.createCategoryID("Salary", joaoPersonID);

        //Act Ledger1

        Ledger ledgerJoao = Ledger.createLedger();

        ledgerJoao.createAndAddTransactionWithDate(categoryIDSalaryJoao, transactionType, transactionDescription, transactionAmount, date, debitAccountIDJoao, creditAccountIDJoao);

        //Act Ledger2

        Ledger ledgerJoana = null;

        //Assert;
        assertEquals(false, ledgerJoao.equals(ledgerJoana));
    }

    @Test
    @DisplayName("Test equals Ledger | Not equals: Not two ledgers")
    public void testNotEqualsDifferentClasses() {

        //Arrange PersonID

        PersonID joaoPersonID = PersonID.createPersonID("joao@gmail.com");

        PersonID joanaPersonID = PersonID.createPersonID("joana@gmail.com");

        //Arrange of Transaction

        //Arrange of Transaction Ledger 1

        String transactionType = "Credit";
        String transactionDescription = "Dez 2019 Salary";
        double transactionAmount = 1000.00;
        LocalDate date = LocalDate.of(2010, 12, 25);

        //Arrange of Debit Account

        AccountID creditAccountIDJoao = AccountID.createAccountID("EmployerSA", joaoPersonID);

        //Arrange of Credit Account

        AccountID debitAccountIDJoao = AccountID.createAccountID("João Salary Account", joaoPersonID);

        //Arrange of Category

        CategoryID categoryIDSalaryJoao = CategoryID.createCategoryID("Salary", joaoPersonID);

        //Act Ledger1

        Ledger ledgerJoao = Ledger.createLedger();

        boolean result = ledgerJoao.createAndAddTransactionWithDate(categoryIDSalaryJoao, transactionType, transactionDescription, transactionAmount, date, debitAccountIDJoao, creditAccountIDJoao);

        //Assert

        assertTrue(result);
        assertEquals(false, ledgerJoao.equals(joanaPersonID));
    }

    @Test
    @DisplayName("Verify if two ledgers are the same | Same object, Same ledger")
    public void testEqualsSameObjectSameLedger() {

        //Arrange PersonID

        PersonID joaoPersonID = PersonID.createPersonID("joao@gmail.com");

        //Arrange of Transaction

        //Arrange of Transaction Ledger 1

        String transactionType = "Credit";
        String transactionDescription = "Dez 2019 Salary";
        double transactionAmount = 1000.00;
        LocalDate date = LocalDate.of(2010, 12, 25);

        //Arrange of Debit Account

        AccountID creditAccountIDJoao = AccountID.createAccountID("EmployerSA", joaoPersonID);

        //Arrange of Credit Account

        AccountID debitAccountIDJoao = AccountID.createAccountID("João Salary Account", joaoPersonID);

        //Arrange of Category

        CategoryID categoryIDSalaryJoao = CategoryID.createCategoryID("Salary", joaoPersonID);

        //Act Ledger1

        Ledger ledgerJoao = Ledger.createLedger();

        ledgerJoao.createAndAddTransactionWithDate(categoryIDSalaryJoao, transactionType, transactionDescription, transactionAmount, date, debitAccountIDJoao, creditAccountIDJoao);

        //Assert;
        assertEquals(true, ledgerJoao.equals(ledgerJoao));
    }

    @Test
    @DisplayName("Verify same object | Same Hashcode")
    public void testEqualsDifferentHashCode() {

        //Arrange PersonID

        PersonID joaoPersonID = PersonID.createPersonID("joao@gmail.com");

        PersonID joanaPersonID = PersonID.createPersonID("joana@gmail.com");

        //Arrange of Transaction

        //Arrange of Transaction Ledger 1

        String transactionType = "Credit";
        String transactionDescription = "Dez 2019 Salary";
        double transactionAmount = 1000.00;
        LocalDate date = LocalDate.of(2010, 12, 25);

        //Arrange of Transaction Ledger 2

        String transactionTypeL2 = "Credit";
        String transactionDescriptionL2 = "Jan 2020 Salary";
        double transactionAmountL2 = 1000.00;
        LocalDate transactionDateL2 = LocalDate.of(2020, 02, 01);

        //Arrange of Debit Account

        AccountID creditAccountIDJoao = AccountID.createAccountID("EmployerSA", joaoPersonID);

        AccountID creditAccountIDJoana = AccountID.createAccountID("EmployerSA", joanaPersonID);

        //Arrange of Credit Account

        AccountID debitAccountIDJoao = AccountID.createAccountID("João Salary Account", joaoPersonID);

        AccountID debitAccountIDJoana = AccountID.createAccountID("João Salary Account", joanaPersonID);

        //Arrange of Category

        CategoryID categoryIDSalaryJoao = CategoryID.createCategoryID("Salary", joaoPersonID);

        CategoryID categoryIDSalaryJoana = CategoryID.createCategoryID("Salary", joanaPersonID);

        //Act Ledger1

        Ledger ledgerJoao = Ledger.createLedger();

        ledgerJoao.createAndAddTransactionWithDate(categoryIDSalaryJoao, transactionType, transactionDescription, transactionAmount, date, debitAccountIDJoao, creditAccountIDJoao);

        //Act Ledger2

        Ledger ledgerJoana = Ledger.createLedger();

        ledgerJoana.createAndAddTransactionWithDate(categoryIDSalaryJoana, transactionTypeL2, transactionDescriptionL2, transactionAmountL2, transactionDateL2, debitAccountIDJoana, creditAccountIDJoana);

        //Assert

        assertEquals(ledgerJoao.hashCode(), ledgerJoao.hashCode());
    }

}