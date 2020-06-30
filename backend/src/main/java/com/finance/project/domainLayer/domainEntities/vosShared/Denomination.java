package com.finance.project.domainLayer.domainEntities.vosShared;

import com.finance.project.domainLayer.entitiesInterfaces.ValueObject;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * The type Denomination.
 */
@Embeddable
public class Denomination implements ValueObject, Serializable {

    private String denomination;

    //Constructor

    /**
     * Create denomination denomination.
     *
     * @param denomination the denomination
     * @return the denomination
     */
    public static Denomination createDenomination(String denomination){
        return new Denomination(denomination);
    }

    private Denomination(String denomination) {
        if (denomination == null || denomination.equals("")) {
            throw new IllegalArgumentException("Denomination not created due to the fact that the denomination parameter hasn't a valid argument");
        }
        this.denomination = denomination;
    }

    public Denomination() {
    }

    //get denomination

    /**
     * Gets denomination.
     *
     * @return the denomination
     */
    public String getDenomination() {
        return denomination;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
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
        Denomination that = (Denomination) o;
        return Objects.equals(denomination, that.denomination);
    }

    //Hashcode

    /**
     * Hash code int.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return Objects.hash(denomination);
    }

    @Override
    public String toString() {
        return "Denomination{" +
                "denomination='" + denomination + '\'' +
                '}';
    }
}
