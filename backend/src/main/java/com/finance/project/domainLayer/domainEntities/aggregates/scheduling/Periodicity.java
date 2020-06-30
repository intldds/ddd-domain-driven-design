package com.finance.project.domainLayer.domainEntities.aggregates.scheduling;

import com.finance.project.domainLayer.entitiesInterfaces.ValueObject;

import java.util.Objects;

/**
 * The type Periodicity.
 */
public class Periodicity implements ValueObject {

    private String periodicity;

    //Constructor

    /**
     * Create periodicity periodicity.
     *
     * @param periodicity the periodicity
     * @return the periodicity
     */
    public static Periodicity createPeriodicity(String periodicity){
        return new Periodicity(periodicity);
    }

    private Periodicity (String periodicity) {
        this.periodicity= periodicity;
    }

    //get periodicity

    /**
     * Gets periodicity.
     *
     * @return the periodicity
     */
    public String getPeriodicity() {
        return periodicity;
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
        Periodicity that = (Periodicity) o;
        return Objects.equals(periodicity, that.periodicity);
    }

    //Hashcode

    /**
     * Hash code int.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return Objects.hash(periodicity);
    }
}
