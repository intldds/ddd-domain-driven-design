package com.finance.project.domainLayer.domainEntities.aggregates.ledger;


import com.finance.project.domainLayer.domainEntities.vosShared.*;
import com.finance.project.domainLayer.entitiesInterfaces.ValueObject;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Create Transaction as VO
 */
public class Transaction implements ValueObject {


    // Constructor

    private CategoryID categoryID;
    private Type type;
    private Description description;
    private Amount amount;
    private Date date;
    private AccountID debitAccountID;
    private AccountID creditAccountID;


    /**
     * Create transaction transaction.
     *
     * @param categoryID      the category id
     * @param type            the type
     * @param description     the description
     * @param amount          the amount
     * @param date            the date
     * @param debitAccountID  the debit account id
     * @param creditAccountID the credit account id
     * @return the transaction
     */
//Constructor
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

    /**
     * Create transaction with system date transaction.
     *
     * @param categoryID      the category id
     * @param type            the type
     * @param description     the description
     * @param amount          the amount
     * @param debitAccountID  the debit account id
     * @param creditAccountID the credit account id
     * @return the transaction
     */
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


    // Get methods


    /**
     * Gets category id.
     *
     * @return the category id
     */
    public CategoryID getCategoryID() {
        return categoryID;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public Type getType() {
        return type;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public Description getDescription() {
        return description;
    }

    /**
     * Gets amount.
     *
     * @return the amount
     */
    public Amount getAmount() {
        return amount;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Gets debit account id.
     *
     * @return the debit account id
     */
    public AccountID getDebitAccountID() {
        return debitAccountID;
    }

    /**
     * Gets credit account id.
     *
     * @return the credit account id
     */
    public AccountID getCreditAccountID() {
        return creditAccountID;
    }

    /**
     * Equals boolean.
     *
     * @param o the o
     * @return the boolean
     */
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


    // HashCode

    /**
     * Hash code int.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return Objects.hash(categoryID, type, description, amount, date, debitAccountID, creditAccountID);
    }

        /*
    // Equals

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction)) return false;
        Transaction transaction = (Transaction) o;
        if (this.type != null && transaction.type != null) {
            if (!this.type.equals(transaction.type)) {
                return false;
            }
        }
        if (this.categoryID != null && transaction.categoryID != null) {
            if (!this.categoryID.equals(transaction.categoryID)) {
                return false;
            }
        }

        if (this.description != null && transaction.description != null) {
            if (!this.description.equals(transaction.description)) {
                return false;
            }
        }
        if (this.amount != null && transaction.amount != null) {
            if (!this.amount.equals(transaction.amount)) {
                return false;
            }
        }

        if (this.date != null && transaction.date != null) {
            if (!this.date.equals(transaction.date)) {
                return false;
            }
        }
        if (this.debitAccountID != null && transaction.debitAccountID != null) {
            if (!this.debitAccountID.equals(transaction.debitAccountID)) {
                return false;
            }
        }

        if ((this.creditAccountID != null && transaction.creditAccountID != null) && (!this.creditAccountID.equals(transaction.creditAccountID))) {
            return false;
        }
        return true;
    }


 */

    /*
    // Clone method
    @Override
    public Transaction clone() {
        CategoryID cloneCategoryID = null;
        AccountID cloneCreditAccountID = null;
        AccountID cloneDebitAccountID = null;
        Type cloneType = null;
        Description cloneDescription = null;
        Amount cloneAmount = null;
        Date cloneDate = null;

        if (this.categoryID != null) {
            cloneCategoryID = this.categoryID;
        }

        if (this.debitAccountID != null) {
            cloneDebitAccountID = this.debitAccountID;
        }

        if (this.creditAccountID != null) {
            cloneCreditAccountID = this.creditAccountID;
        }

        if (this.type!= null) {
           cloneType = this.type;
        }


        Transaction newTransaction = Transaction.createTransaction(categoryID, this.type, this.description, this.amount, this.date, debitAccountID, creditAccountID);

        return newTransaction;
    }

    /*

    // Clone method
    @Override
    public Transaction clone() {
        Category cloneCategory = null;
        Account cloneCreditAccount = null;
        Account cloneDebitAccount = null;

        if (this.category != null) {
            cloneCategory = this.category.clone();
        }

        if (this.debitAccount != null) {
            cloneDebitAccount = this.debitAccount.clone();
        }

        if (this.creditAccount != null) {
            cloneCreditAccount = this.creditAccount.clone();
        }

        Transaction newTransaction;

        newTransaction = new Transaction(cloneCategory, this.type, this.description, this.amount, this.date, cloneDebitAccount, cloneCreditAccount);

        return newTransaction;
    }

    /*ELIMINAR

    public void setType(String type) {
        this.type = type;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }



     */


}
