package com.finance.project.dtos.dtos;

import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;

/**
 * The type Transaction dt oout.
 */
public class TransactionDTOout extends RepresentationModel<TransactionDTOout> {
    private String category;
    private String type;
    private String description;
    private double amount;
    private String date;
    private String debitAccount;
    private String creditAccount;

    /**
     * Instantiates a new Transaction dt oout.
     */
    public TransactionDTOout() {
        this.category = "";
        this.type = "";
        this.description = "";
        this.amount = 0;
        this.date = "";
        this.debitAccount = "";
        this.creditAccount = "";
    }

    /**
     * Instantiates a new Transaction dt oout.
     *
     * @param category      the category
     * @param type          the type
     * @param description   the description
     * @param amount        the amount
     * @param date          the date
     * @param debitAccount  the debit account
     * @param creditAccount the credit account
     */
    public TransactionDTOout(String category, String type, String description, double amount, String date, String debitAccount, String creditAccount) {
        this.category = category;
        this.type = type;
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.debitAccount = debitAccount;
        this.creditAccount = creditAccount;
    }

    /**
     * Gets category.
     *
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets amount.
     *
     * @return the amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * Gets debit account.
     *
     * @return the debit account
     */
    public String getDebitAccount() {
        return debitAccount;
    }

    /**
     * Gets credit account.
     *
     * @return the credit account
     */
    public String getCreditAccount() {
        return creditAccount;
    }

    /**
     * Sets category.
     *
     * @param category the category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets amount.
     *
     * @param amount the amount
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Sets debit account.
     *
     * @param debitAccount the debit account
     */
    public void setDebitAccount(String debitAccount) {
        this.debitAccount = debitAccount;
    }

    /**
     * Sets credit account.
     *
     * @param creditAccount the credit account
     */
    public void setCreditAccount(String creditAccount) {
        this.creditAccount = creditAccount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionDTOout that = (TransactionDTOout) o;
        return Double.compare(that.amount, amount) == 0 &&
                category.equals(that.category) &&
                type.equals(that.type) &&
                description.equals(that.description) &&
                date.equals(that.date) &&
                debitAccount.equals(that.debitAccount) &&
                creditAccount.equals(that.creditAccount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category, type, description, amount, date, debitAccount, creditAccount);
    }
}

