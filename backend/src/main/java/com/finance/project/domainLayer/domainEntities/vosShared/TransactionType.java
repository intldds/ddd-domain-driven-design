package com.finance.project.domainLayer.domainEntities.vosShared;

import com.finance.project.domainLayer.entitiesInterfaces.ValueObject;

import java.util.Objects;

/**
 * The type Transaction type.
 */
public class TransactionType implements ValueObject {

    private String transactionType;

    //Constructor

    /**
     * Create transaction type transaction type.
     *
     * @param transactionType the transaction type
     * @return the transaction type
     */
    public static TransactionType createTransactionType(String transactionType){
        return new TransactionType(transactionType);
    }

    private TransactionType (String transactionType) {
        this.transactionType= transactionType;
    }

    //get transactionType

    /**
     * Gets transaction type.
     *
     * @return the transaction type
     */
    public String getTransactionType() {
        return transactionType;
    }

    //Equals

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
        TransactionType that = (TransactionType) o;
        return Objects.equals(transactionType, that.transactionType);
    }

    //Hashcode

    /**
     * Hash code int.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return Objects.hash(transactionType);
    }
}
