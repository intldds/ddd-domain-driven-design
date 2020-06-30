package com.finance.project.domainLayer.domainEntities.vosShared;

import com.finance.project.domainLayer.entitiesInterfaces.ValueObject;

import java.util.Objects;

/**
 * The type Amount.
 */
public class Amount implements ValueObject {

    private double amount;

    //Constructor

    /**
     * Create amount amount.
     *
     * @param amount the amount
     * @return the amount
     */
    public static Amount createAmount(double amount){
        return new Amount(amount);
    }

    private Amount (double amount) {
        this.amount= amount;
    }

    //get amount

    /**
     * Gets amount.
     *
     * @return the amount
     */
    public double getAmount() {
        return amount;
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
        Amount amount1 = (Amount) o;
        return Double.compare(amount1.amount, amount) == 0;
    }

    //HashCode

    /**
     * Hash code int.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
