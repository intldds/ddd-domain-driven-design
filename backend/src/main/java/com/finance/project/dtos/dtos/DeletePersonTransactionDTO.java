package com.finance.project.dtos.dtos;

import java.util.Objects;

/**
 * The type Create person transaction dto.
 *
 * @author Francisco Gaspar
 */
public class DeletePersonTransactionDTO {

    private final int transactionNumber;
    private final String email;

    /**
     * Instantiates a new Create person transaction dto.
     *
     * @param transactionNumber       the transaction number
     * @param email                   the email
     */
    public DeletePersonTransactionDTO(int transactionNumber, String email) {
        this.transactionNumber = transactionNumber;
        this.email = email;
    }

    /**
     * Gets transactionNumber.
     *
     * @return the transactionNumber
     */
    public int getTransactionNumber() {
        return transactionNumber;
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
     * Equals boolean.
     *
     * @param o the o
     * @return the boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeletePersonTransactionDTO that = (DeletePersonTransactionDTO) o;
        return Objects.equals(email, that.email) &&
                Objects.equals(transactionNumber, that.transactionNumber);
    }

    /**
     * Hash code int.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return Objects.hash(email, transactionNumber);
    }


}
