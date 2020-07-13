package com.finance.project.domainLayer.domainEntities.aggregates.ledger;


import com.finance.project.domainLayer.domainEntities.vosShared.*;
import com.finance.project.domainLayer.entitiesInterfaces.ValueObject;

import java.time.LocalDate;
import java.util.Objects;


public class Transaction implements ValueObject {


    // Constructor

    private CategoryID categoryID;
    private Type type;
    private Description description;
    private Amount amount;
    private Date date;
    private AccountID debitAccountID;
    private AccountID creditAccountID;


    // Constructor
    public static Transaction createTransaction(CategoryID categoryID, String type, String description, double amount, LocalDate date, AccountID debitAccountID, AccountID creditAccountID) {
        return new Transaction(categoryID, type, description, amount, date, debitAccountID, creditAccountID);
    }

    private Transaction(CategoryID categoryID, String type, String description, double amount, LocalDate date, AccountID debitAccountID, AccountID creditAccountID) {
        if (categoryID == null) {
            throw new IllegalArgumentException("Transaction not created due to the fact that the categoryID parameter hasn't a valid argument");
        }
        if (type == null) {
            throw new IllegalArgumentException("Transaction not created due to the fact that the type parameter hasn't a valid argument");
        }
        if (description == null) {
            throw new IllegalArgumentException("Transaction not created due to the fact that the description parameter hasn't a valid argument");
        }
        if (date == null) {
            throw new IllegalArgumentException("Transaction not created due to the fact that the date parameter hasn't a valid argument");
        }
        if (debitAccountID == null) {
            throw new IllegalArgumentException("Transaction not created due to the fact that the debitAccountID parameter hasn't a valid argument");
        } else if (creditAccountID == null) {
            throw new IllegalArgumentException("Transaction not created due to the fact that the creditAccountID parameter hasn't a valid argument");
        }
        this.categoryID = categoryID;
        this.type = Type.createType(type);
        this.description = Description.createDescription(description);
        this.amount = Amount.createAmount(amount);
        this.date = Date.createDate(date);
        this.debitAccountID = debitAccountID;
        this.creditAccountID = creditAccountID;
    }


    // Constructor with System Date
    public static Transaction createTransactionWithSystemDate(CategoryID categoryID, String type, String description, double amount, AccountID debitAccountID, AccountID creditAccountID) {
        return new Transaction(categoryID, type, description, amount, debitAccountID, creditAccountID);
    }

    private Transaction(CategoryID categoryID, String type, String description, double amount, AccountID debitAccountID, AccountID creditAccountID) {
        if (categoryID == null) {
            throw new IllegalArgumentException("Transaction not created due to the fact that the categoryID parameter hasn't a valid argument");
        }
        if (type == null) {
            throw new IllegalArgumentException("Transaction not created due to the fact that the type parameter hasn't a valid argument");
        }
        if (description == null) {
            throw new IllegalArgumentException("Transaction not created due to the fact that the description parameter hasn't a valid argument");
        }
        if (debitAccountID == null) {
            throw new IllegalArgumentException("Transaction not created due to the fact that the debitAccountID parameter hasn't a valid argument");
        } else if (creditAccountID == null) {
            throw new IllegalArgumentException("Transaction not created due to the fact that the creditAccountID parameter hasn't a valid argument");
        }
        this.categoryID = categoryID;
        this.type = Type.createType(type);
        this.description = Description.createDescription(description);
        this.amount = Amount.createAmount(amount);
        this.date = Date.createDate(LocalDate.now());
        this.debitAccountID = debitAccountID;
        this.creditAccountID = creditAccountID;
    }


    // Getters

    public CategoryID getCategoryID() {
        return categoryID;
    }

    public Type getType() {
        return type;
    }

    public Description getDescription() {
        return description;
    }

    public Amount getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }

    public AccountID getDebitAccountID() {
        return debitAccountID;
    }

    public AccountID getCreditAccountID() {
        return creditAccountID;
    }


    // Equals & hashCode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction)) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(getCategoryID(), that.getCategoryID()) &&
                Objects.equals(getType(), that.getType()) &&
                Objects.equals(getDescription(), that.getDescription()) &&
                Objects.equals(getAmount(), that.getAmount()) &&
                Objects.equals(getDate(), that.getDate()) &&
                Objects.equals(getDebitAccountID(), that.getDebitAccountID()) &&
                Objects.equals(getCreditAccountID(), that.getCreditAccountID());
    }


    @Override
    public int hashCode() {
        return Objects.hash(categoryID, type, description, amount, date, debitAccountID, creditAccountID);
    }

}
