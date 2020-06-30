package com.finance.project.domainLayer.domainEntities.aggregates.account;

import com.finance.project.domainLayer.domainEntities.vosShared.AccountID;
import com.finance.project.domainLayer.domainEntities.vosShared.Description;
import com.finance.project.domainLayer.entitiesInterfaces.Entity;
import com.finance.project.domainLayer.entitiesInterfaces.OwnerID;

import java.util.Objects;

/**
 * Change Account to root
 */
//root
public class Account implements Entity {
    private final AccountID accountID;
    private final Description description;


    //Constructor

    /**
     * Create account account.
     *
     * @param description  the description
     * @param denomination the denomination
     * @param ownerID      the owner id
     * @return the account
     */
    public static Account createAccount(String description, String denomination, OwnerID ownerID) {
        return new Account(description, denomination, ownerID);
    }

    private Account(String description, String denomination, OwnerID ownerID) {
        if (description == null) {
            throw new IllegalArgumentException("Account not created due to the fact that the description parameter hasn't a valid argument");
        }
        if (denomination == null) {
            throw new IllegalArgumentException("Account not created due to the fact that the denomination parameter hasn't a valid argument");
        } else if (ownerID == null) {
            throw new IllegalArgumentException("Account not created due to the fact that the ownerID parameter hasn't a valid argument");
        }
        this.accountID = AccountID.createAccountID(denomination, ownerID);
        this.description = Description.createDescription(description);
    }
/*
    //AccountID of Account
    public boolean checkAccountID(AccountID accountID) {
        return this.accountID.equals(accountID);
    }

 */

    //Get AccountID

    /**
     * Gets account id.
     *
     * @return the account id
     */
    public AccountID getAccountID() {
        return accountID;
    }

    //Get Description

    /**
     * Gets description.
     *
     * @return the description
     */
    public Description getDescription() {
        return description;
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
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;

        if (!this.accountID.equals(account.accountID)) {
            return false;
        }
        return true;
    }


    //hashcode

    /**
     * Hash code int.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return Objects.hash(accountID);
    }

}