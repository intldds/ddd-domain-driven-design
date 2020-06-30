package com.finance.project.domainLayer.domainEntities.aggregates.ledger;

import com.finance.project.domainLayer.domainEntities.vosShared.AccountID;
import com.finance.project.domainLayer.domainEntities.vosShared.CategoryID;
import com.finance.project.domainLayer.domainEntities.vosShared.Date;
import com.finance.project.domainLayer.domainEntities.vosShared.LedgerID;
import com.finance.project.domainLayer.domainEntities.vosShared.PersonID;
import com.finance.project.domainLayer.entitiesInterfaces.Entity;
import com.finance.project.dtos.dtos.CreatePersonTransactionDTO;
import com.finance.project.dtos.dtos.TransactionDTOout;
import com.finance.project.dtos.dtosAssemblers.TransactionDTOoutAssembler;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * The type Ledger.
 */
public class Ledger implements Entity {

    private LedgerID ledgerID;
    private List<Transaction> records;

    //Constructor for Ledger

    //Person

    /**
     * Create ledger ledger.
     *
     * @return the ledger
     */
    public static Ledger createLedger() {
        return new Ledger();
    }

    private Ledger() {
        this.ledgerID = LedgerID.createLedgerID();
        this.records = new ArrayList<Transaction>();
    }

    public Ledger(LedgerID ledgerID) {
        this.ledgerID = ledgerID;
        this.records = new ArrayList<Transaction>();
    }

    //get LedgerID

    /**
     * Gets ledger id.
     *
     * @return the ledger id
     */
    public LedgerID getLedgerID() {
        return this.ledgerID;
    }

    //Update transaction

    /**
     * Update transaction boolean.
     *
     * @param categoryID      the category id
     * @param type            the type
     * @param description     the description
     * @param amount          the amount
     * @param debitAccountID  the debit account id
     * @param creditAccountID the credit account id
     * @return the boolean
     */
    public boolean updateTransaction(int transactionNumber, CategoryID categoryID, String type, String description, double amount, AccountID debitAccountID, AccountID creditAccountID) {
        Transaction newTransaction = Transaction.createTransactionWithSystemDate(categoryID, type, description, amount, debitAccountID, creditAccountID);
        records.set(transactionNumber - 1, newTransaction);
        return true;
    }

    //Create transaction

    /**
     * Create and add transaction boolean.
     *
     * @param categoryID      the category id
     * @param type            the type
     * @param description     the description
     * @param amount          the amount
     * @param debitAccountID  the debit account id
     * @param creditAccountID the credit account id
     * @return the boolean
     */
    public boolean createAndAddTransaction(CategoryID categoryID, String type, String description, double amount, AccountID debitAccountID, AccountID creditAccountID) {
        Transaction newTransaction = Transaction.createTransactionWithSystemDate(categoryID, type, description, amount, debitAccountID, creditAccountID);
        return records.add(newTransaction);
    }

    /**
     * Create and add transaction with date boolean.
     *
     * @param categoryID      the category id
     * @param type            the type
     * @param description     the description
     * @param amount          the amount
     * @param date            the date
     * @param debitAccountID  the debit account id
     * @param creditAccountID the credit account id
     * @return the boolean
     */
//Constructor for Ledger
    public boolean createAndAddTransactionWithDate(CategoryID categoryID, String type, String description, double amount, LocalDate date, AccountID debitAccountID, AccountID creditAccountID) {
        Transaction newTransaction = Transaction.createTransaction(categoryID, type, description, amount, date, debitAccountID, creditAccountID);
        return records.add(newTransaction);
    }

    //Add transaction to ledger

    /**
     * Add transaction.
     *
     * @param transaction the transaction
     */
    public void addTransaction(Transaction transaction) {
        if (transaction != null) {
            this.records.add(transaction);
        }
    }

    //Get Transactions

    /**
     * Gets records.
     *
     * @return the records
     */
    public ArrayList<Transaction> getRecords() {
        ArrayList<Transaction> recordsClone = new ArrayList<Transaction>();

        for (Transaction transaction : records) {
            recordsClone.add(transaction);
        }
        return recordsClone;
    }

    public List<TransactionDTOout> getRecordsAsDTO() {
        List<TransactionDTOout> records = new ArrayList<>();

        for (Transaction transaction : this.records) {
            records.add(TransactionDTOoutAssembler.createTransactionDTOout(transaction));
        }
        return records;
    }

    //Check if ledger Has Transaction

    /**
     * Ledger has transaction boolean.
     *
     * @param createPersonTransactionDTO the create person transaction dto
     * @return the boolean
     */
    public boolean ledgerHasTransaction(CreatePersonTransactionDTO createPersonTransactionDTO) {

        PersonID personID = PersonID.createPersonID(createPersonTransactionDTO.getEmail());
        CategoryID categoryID = CategoryID.createCategoryID(createPersonTransactionDTO.getDenominationCategory(), personID);
        String type = createPersonTransactionDTO.getType();
        String description = createPersonTransactionDTO.getDescription();
        double amount = createPersonTransactionDTO.getAmount();
        AccountID debitAccountID = AccountID.createAccountID(createPersonTransactionDTO.getDenominationAccountDeb(), personID);
        AccountID creditAccountID = AccountID.createAccountID(createPersonTransactionDTO.getDenominationAccountCred(), personID);

        Transaction transaction = Transaction.createTransactionWithSystemDate(categoryID, type, description, amount, debitAccountID, creditAccountID);

        if (records.contains(transaction)) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * Gets records between two dates.
     *
     * @param dateOfBeginning the date of beginning
     * @param dateOfEnding    the date of ending
     * @return the records between two dates
     */
    //Get list of Transactions between 2 dates
    public List<Transaction> getRecordsBetweenTwoDates(LocalDate dateOfBeginning, LocalDate dateOfEnding) {
        List<Transaction> transactions = new ArrayList<>();

        if (dateOfBeginning.isAfter(dateOfEnding)) {
            throw new IllegalArgumentException("Check the start and end dates for the period, since start date cannot be later than end date");
        }

        for (Transaction transaction : records) {
            if ((transaction.getDate().getDate().isAfter(dateOfBeginning) && transaction.getDate().getDate().isBefore(dateOfEnding)) || transaction.getDate().equals(com.finance.project.domainLayer.domainEntities.vosShared.Date.createDate(dateOfBeginning)) || transaction.getDate().equals(com.finance.project.domainLayer.domainEntities.vosShared.Date.createDate(dateOfEnding))) {
                transactions.add(transaction);
            }
        }

        return transactions;
    }

    /**
     * Earliest record date.
     *
     * @return the earliest date
     */
    public LocalDate getEarliestTransactionDate() {
        LocalDate earliestDate;

        if (records.isEmpty()) {
            throw new IllegalStateException("Ledger is empty (no transactions)");

        } else {
            records.sort(Comparator.comparing(earlierDate -> earlierDate.getDate().getDate()));
            earliestDate = records.get(0).getDate().getDate();
        }

        return earliestDate;
    }

    /**
     * Latest record date.
     *
     * @return the latest record date
     */
    public LocalDate getLatestTransactionDate() {
        LocalDate latestDate;

        if (records.isEmpty()) {
            throw new IllegalStateException("Ledger is empty (no transactions)");

        } else {
            records.sort(Comparator.comparing(laterDate -> laterDate.getDate().getDate()));
            latestDate = records.get(records.size() - 1).getDate().getDate();
        }

        return latestDate;
    }

    /**
     * Gets all accounts ID from records, sorted alphabetically by account denomination.
     *
     * @return the accounts of records sorted alphabetically
     */
    public List<AccountID> getAccountsOfRecordsSorted() {

        List<AccountID> legerDebitAccounts = records.stream().map(Transaction::getDebitAccountID).collect(Collectors.toList());
        List<AccountID> legerCreditAccounts = records.stream().map(Transaction::getCreditAccountID).collect(Collectors.toList());

        Set<AccountID> ledgerAccounts = new HashSet<>();
        ledgerAccounts.addAll(legerDebitAccounts);
        ledgerAccounts.addAll(legerCreditAccounts);

        List<AccountID> ledgerAllAccounts = new ArrayList<>(ledgerAccounts);
        ledgerAllAccounts.sort(Comparator.comparing(accID -> accID.getDenomination().getDenomination()));

        if (ledgerAccounts.isEmpty()) {
            throw new IllegalStateException("Ledger is empty (no accounts to report)");
        }
        return ledgerAllAccounts;
    }

    /**
     * Gets all transactions associated with a given account, sorted by date.
     *
     * @param accountID the account id
     * @return the account transactions
     */
    public List<Transaction> getAccountRecords(AccountID accountID) {

        List<Transaction> accountAllTransactions = new ArrayList<>();

        for (Transaction transaction : records) {
            if (transaction.getDebitAccountID().equals(accountID) || transaction.getCreditAccountID().equals(accountID)) {
                accountAllTransactions.add(transaction);
            }
        }
        if (accountAllTransactions.isEmpty()) {
            throw new IllegalArgumentException("Ledger has no records associated with this account");
        }

        accountAllTransactions.sort(Comparator.comparing(date -> date.getDate().getDate()));

        return accountAllTransactions;
    }

    /**
     * Gets the earliest date of the account records.
     *
     * @param accountID the account id
     * @return the account records earliest date
     */
    public LocalDate getAccountRecordsEarliestDate(AccountID accountID) {

        try {
            getAccountRecords(accountID);
        } catch (IllegalArgumentException e) {
            throw e;
        }

        List<Transaction> allAccountTransactions = getAccountRecords(accountID);

        LocalDate earliestAccountRecordDate = allAccountTransactions.get(0).getDate().getDate();

        return earliestAccountRecordDate;
    }

    /**
     * Gets the latest date of the account records.
     *
     * @param accountID the account id
     * @return the account records latest date
     */
    public LocalDate getAccountRecordsLatestDate(AccountID accountID) {

        try {
            getAccountRecords(accountID);
        } catch (IllegalArgumentException e) {
            throw e;
        }

        List<Transaction> allAccountTransactions = getAccountRecords(accountID);

        LocalDate latestAccountRecordDate = allAccountTransactions.get(allAccountTransactions.size() - 1).getDate().getDate();

        return latestAccountRecordDate;
    }

    /**
     * Retrieves, from the ledger, the transactions for a given account, within a given period (endpoint included)
     *
     * @param accountID The ID of the account being searched within the ledger's records
     * @param startDate The start date of the period being searched
     * @return the ledger's transactions of a given account, for a given period with its endpoint included
     */
    public List<Transaction> getAccountRecordsAsOfDate(AccountID accountID, LocalDate startDate) {
        List<Transaction> accountTransactions = new ArrayList<>();

        for (Transaction transaction : records) {
            if ((transaction.getDebitAccountID().equals(accountID) || transaction.getCreditAccountID().equals(accountID)) && (transaction.getDate().getDate().isAfter(startDate) || transaction.getDate().equals(com.finance.project.domainLayer.domainEntities.vosShared.Date.createDate(startDate)))) {
                accountTransactions.add(transaction);
            }
        }

        if (accountTransactions.isEmpty()) {
            throw new IllegalStateException("Ledger has no records associated with this account");

        } else {
            accountTransactions.sort(Comparator.comparing(date -> date.getDate().getDate()));
        }

        return accountTransactions;
    }

    /**
     * Retrieves, from the ledger, the transactions for a given account, within a given period (endpoints included)
     *
     * @param accountID The ID of the account being searched within the ledger's records
     * @param startDate The start date of the period being searched
     * @param endDate   The start date of the period being searched
     * @return the ledger's transactions of a given account, for a given period with its endpoints included
     */
    public List<Transaction> getAccountRecordsBetweenTwoDates(AccountID accountID, LocalDate startDate, LocalDate endDate) {
        List<Transaction> accountTransactions = new ArrayList<>();

        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Check the start and end dates for the period, since start date cannot be later than end date");
        }

        for (Transaction transaction : records) {
            if ((transaction.getDebitAccountID().equals(accountID) || transaction.getCreditAccountID().equals(accountID)) && ((transaction.getDate().getDate().isAfter(startDate) && transaction.getDate().getDate().isBefore(endDate)) || transaction.getDate().equals(com.finance.project.domainLayer.domainEntities.vosShared.Date.createDate(startDate)) || transaction.getDate().equals(Date.createDate(endDate)))) {
                accountTransactions.add(transaction);
            }
        }

        if (accountTransactions.isEmpty()) {
            throw new IllegalStateException("Ledger has no records associated with this account");

        } else {
            accountTransactions.sort(Comparator.comparing(date -> date.getDate().getDate()));
        }

        return accountTransactions;
    }


/*
    //Get Amount of records between 2 dates

    public double getAmountBetweenTwoDates(LocalDate dateOfBeginning, LocalDate dateOfEnding) {
        ArrayList<Transaction> transactions;
        double amount = 0;

        transactions = getRecordsBetweenTwoDates(dateOfBeginning,dateOfEnding);

        for(Transaction transaction: transactions){
            amount = amount + transaction.getAmount();
        }
        return amount;
    }


*/
    //Equals

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ledger ledger = (Ledger) o;
        return Objects.equals(ledgerID, ledger.ledgerID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ledgerID);
    }

}