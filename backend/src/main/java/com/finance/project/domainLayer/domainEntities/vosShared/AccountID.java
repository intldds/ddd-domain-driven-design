package com.finance.project.domainLayer.domainEntities.vosShared;

import com.finance.project.domainLayer.entitiesInterfaces.OwnerID;
import com.finance.project.domainLayer.entitiesInterfaces.ValueObject;

import java.util.Objects;


public class AccountID implements ValueObject {


    private final Denomination denomination;
    private final OwnerID ownerID;


    //Constructor

    /**
     * Create account id account id.
     *
     * @param denomination the denomination
     * @param ownerID      the owner id
     * @return the account id
     */
    public static AccountID createAccountID(String denomination, OwnerID ownerID) {
        return new AccountID(denomination, ownerID);
    }


    private AccountID(String denomination, OwnerID ownerID) {
        if (denomination == null) {
            throw new IllegalArgumentException("AccountID not created due to the fact that the denomination parameter hasn't a valid argument");
        } else if (ownerID == null) {
            throw new IllegalArgumentException("AccountID not created due to the fact that the ownerID parameter hasn't a valid argument");
        }
        this.denomination = Denomination.createDenomination(denomination);
        this.ownerID = ownerID;
    }

    //get denomination

    /**
     * Gets denomination.
     *
     * @return the denomination
     */
    public Denomination getDenomination() {
        return denomination;
    }

    //get ownerID

    /**
     * Gets owner id.
     *
     * @return the owner id
     */
    public OwnerID getOwnerID() {
        return ownerID;
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
        if (!(o instanceof AccountID)) return false;
        AccountID accountID = (AccountID) o;
        return denomination.equals(accountID.denomination) &&
                ownerID.equals(accountID.ownerID);
    }

    //hashCode

    /**
     * Hash code int.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return Objects.hash(denomination, ownerID);
    }
}
