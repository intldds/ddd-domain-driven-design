package com.finance.project.dtos.dtos;

import java.util.Objects;

/**
 * The type Create person transaction dto.
 *
 * @author Francisco Gaspar
 */
public class CreatePersonTransactionDTO {

    private final String email; // Person email
    private final String denominationCategory;     // Category denomination
    private final String type;     // Transaction type
    private final String description;     // Transaction description
    private final double amount;     // Transaction amount
    private final String denominationAccountDeb;     // Account denomination (debit)
    private final String denominationAccountCred;     // Account denomination (credit)
    private final String date; // Date


    /**
     * Instantiates a new Create person transaction dto.
     *
     * @param email                   the email
     * @param denominationCategory    the denomination category
     * @param type                    the type
     * @param description             the description
     * @param amount                  the amount
     * @param denominationAccountDeb  the denomination account deb
     * @param denominationAccountCred the denomination account cred
     */
    public CreatePersonTransactionDTO(String email, String denominationCategory, String type, String description, double amount, String denominationAccountDeb, String denominationAccountCred, String date) {
        this.email = email;
        this.denominationCategory = denominationCategory;
        this.type = type;
        this.description = description;
        this.amount = amount;
        this.denominationAccountDeb = denominationAccountDeb;
        this.denominationAccountCred = denominationAccountCred;
        this.date = date;

    }

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

    public String getDate() {
        return date;
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
        CreatePersonTransactionDTO createPersonTransactionDTO = (CreatePersonTransactionDTO) o;
        return Objects.equals(email, createPersonTransactionDTO.email) &&
                Objects.equals(denominationCategory, createPersonTransactionDTO.denominationCategory) &&
                Objects.equals(type, createPersonTransactionDTO.type) &&
                Objects.equals(description, createPersonTransactionDTO.description) &&
                Objects.equals(amount, createPersonTransactionDTO.amount) &&
                Objects.equals(denominationAccountDeb, createPersonTransactionDTO.denominationAccountDeb) &&
                Objects.equals(denominationAccountCred, createPersonTransactionDTO.denominationAccountCred) &&
                Objects.equals(date, createPersonTransactionDTO.date);
    }

    /**
     * Hash code int.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return Objects.hash(email, denominationCategory, type, description, amount, denominationAccountDeb, denominationAccountCred, date);
    }

}
