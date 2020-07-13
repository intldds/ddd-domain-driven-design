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


public class Ledger implements Entity {

    private LedgerID ledgerID;
    private List<Transaction> records;

    // Constructor for Ledger - Person

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

    // Getters

    public LedgerID getLedgerID() {
        return this.ledgerID;
    }

    // Update transaction

    public boolean updateTransaction(int transactionNumber, CategoryID categoryID, String type, String description, double amount, AccountID debitAccountID, AccountID creditAccountID) {
        Transaction newTransaction = Transaction.createTransactionWithSystemDate(categoryID, type, description, amount, debitAccountID, creditAccountID);
        records.set(transactionNumber - 1, newTransaction);
        return true;
    }

    // Create and add transaction

    public boolean createAndAddTransaction(CategoryID categoryID, String type, String description, double amount, AccountID debitAccountID, AccountID creditAccountID) {
        Transaction newTransaction = Transaction.createTransactionWithSystemDate(categoryID, type, description, amount, debitAccountID, creditAccountID);
        return records.add(newTransaction);
    }


    // Constructor for Ledger with Date

    public boolean createAndAddTransactionWithDate(CategoryID categoryID, String type, String description, double amount,
                                                   LocalDate date, AccountID debitAccountID, AccountID creditAccountID) {

        Transaction newTransaction = Transaction.createTransaction(categoryID, type, description, amount, date, debitAccountID, creditAccountID);
        return records.add(newTransaction);
    }

    // Add transaction to ledger

    public void addTransaction(Transaction transaction) {
        if (transaction != null) {
            this.records.add(transaction);
        }
    }

    // Get Transactions

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

    // Check if ledger Has Transaction

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


    // Get list of Transactions between 2 dates
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


    public List<Transaction> getAccountRecordsAsOfDate(AccountID accountID, LocalDate startDate) {
        List<Transaction> accountTransactions = new ArrayList<>();

        for (Transaction transaction : records) {
            if ((transaction.getDebitAccountID().equals(accountID) || transaction.getCreditAccountID().equals(accountID)) &&
                    (transaction.getDate().getDate().isAfter(startDate) || transaction.getDate().equals(com.finance.project.domainLayer.domainEntities.vosShared.Date.createDate(startDate)))) {
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


    // Equals & hashCode

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