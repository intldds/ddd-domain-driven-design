package com.finance.project.dataModel.dataModel;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class AbstractIdJpa implements Serializable {

    private String ownerID;
    private String denomination;

    public AbstractIdJpa(String ownerID, String denomination) {
        this.ownerID = ownerID;
        this.denomination = denomination;
    }

    public AbstractIdJpa() {
    }

    public String getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(String ownerID) {
        this.ownerID = ownerID;
    }

    public String getDenomination() {
        return denomination;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    @Override
    public String toString() {
        return "AbstractIdJpa{" +
                "ownerID='" + ownerID + '\'' +
                ", denomination='" + denomination + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractIdJpa that = (AbstractIdJpa) o;
        return Objects.equals(ownerID, that.ownerID) &&
                Objects.equals(denomination, that.denomination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ownerID, denomination);
    }
}
