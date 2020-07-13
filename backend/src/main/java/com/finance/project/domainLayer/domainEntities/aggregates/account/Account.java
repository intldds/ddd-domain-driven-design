package com.finance.project.domainLayer.domainEntities.aggregates.account;

import com.finance.project.domainLayer.domainEntities.vosShared.AccountID;
import com.finance.project.domainLayer.domainEntities.vosShared.Description;
import com.finance.project.domainLayer.entitiesInterfaces.Entity;
import com.finance.project.domainLayer.entitiesInterfaces.OwnerID;

import java.util.Objects;


public class Account implements Entity {
    private final AccountID accountID;
    private final Description description;


    // Constructor

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


    // Getters

    public AccountID getAccountID() {
        return accountID;
    }

    public Description getDescription() {
        return description;
    }


    // Equals & hashCode

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

    @Override
    public int hashCode() {
        return Objects.hash(accountID);
    }

}