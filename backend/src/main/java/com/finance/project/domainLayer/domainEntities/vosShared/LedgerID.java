package com.finance.project.domainLayer.domainEntities.vosShared;

import com.finance.project.domainLayer.entitiesInterfaces.ValueObject;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * create LedgerVO
 */
@Embeddable
public class LedgerID implements ValueObject, Serializable {

    private String id;

    //Constructor

    /**
     * Create ledger id ledger id.
     *
     * @return the ledger id
     */
    public static LedgerID createLedgerID() {
        return new LedgerID();
    }

    public LedgerID() {
        UUID id = UUID.randomUUID();
        this.id = id.toString();
    }

    public LedgerID(String id) {
        this.id = id;
    }

    //get methods

    /**
     * Gets ledger id.
     *
     * @return the ledger id
     */
    public String getLedgerID() {
        return id;
    }

    public void setLedgerID(String id) {
        this.id = id;
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
        LedgerID ledgerID = (LedgerID) o;
        return Objects.equals(id, ledgerID.id);
    }

    //HashCode

    /**
     * Hash code int.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "LedgerID{" +
                "id='" + id + '\'' +
                '}';
    }
}
