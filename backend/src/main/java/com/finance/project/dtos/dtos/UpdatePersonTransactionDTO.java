package com.finance.project.dtos.dtos;


import java.util.Objects;

/**
 * The type Create person transaction dto.
 *
 * @author Francisco Gaspar
 */
public class UpdatePersonTransactionDTO {

    private final int transactionNumber;
    private final String email;
    private final String denominationCategory;
    private final String type;
    private final String description;
    private final double amount;
    private final String denominationAccountDeb;
    private final String denominationAccountCred;

    /**
     * Instantiates a new Create person transaction dto.
     *
     * @param transactionNumber       the transaction number
     * @param email                   the email
     * @param denominationCategory    the denomination category
     * @param type                    the type
     * @param description             the description
     * @param amount                  the amount
     * @param denominationAccountDeb  the denomination account deb
     * @param denominationAccountCred the denomination account cred
     */
    public UpdatePersonTransactionDTO(int transactionNumber, String email, String denominationCategory, String type, String description, double amount, String denominationAccountDeb, String denominationAccountCred) {
        this.transactionNumber = transactionNumber;
        this.email = email;
        this.denominationCategory = denominationCategory;
        this.type = type;
        this.description = description;
        this.amount = amount;
        this.denominationAccountDeb = denominationAccountDeb;
        this.denominationAccountCred = denominationAccountCred;

    }

    /**
     * Gets transactionNumber.
     *
     * @return the transactionNumber
     */
    public int getTransactionNumber() { return transactionNumber; }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets denomination category.
     *
     * @return the denomination category
     */
    public String getDenominationCategory() {
        return denominationCategory;
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
     * Gets denomination account deb.
     *
     * @return the denomination account deb
     */
    public String getDenominationAccountDeb() {
        return denominationAccountDeb;
    }

    /**
     * Gets denomination account cred.
     *
     * @return the denomination account cred
     */
    public String getDenominationAccountCred() {
        return denominationAccountCred;
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
        if (o == null || getClass() != o.getClass()) return false;
        UpdatePersonTransactionDTO that = (UpdatePersonTransactionDTO) o;
        return Double.compare(that.amount, amount) == 0 &&
                Objects.equals(email, that.email) &&
                Objects.equals(denominationCategory, that.denominationCategory) &&
                Objects.equals(type, that.type) &&
                Objects.equals(description, that.description) &&
                Objects.equals(denominationAccountDeb, that.denominationAccountDeb) &&
                Objects.equals(denominationAccountCred, that.denominationAccountCred) &&
                Objects.equals(transactionNumber, that.transactionNumber);
    }

    /**
     * Hash code int.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return Objects.hash(email, denominationCategory, type, description, amount, denominationAccountDeb, denominationAccountCred, transactionNumber);
    }

}
