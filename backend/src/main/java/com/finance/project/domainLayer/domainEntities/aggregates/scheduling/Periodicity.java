package com.finance.project.domainLayer.domainEntities.aggregates.scheduling;

import com.finance.project.domainLayer.entitiesInterfaces.ValueObject;

import java.util.Objects;


public class Periodicity implements ValueObject {

    private String periodicity;

    // Constructor

    public static Periodicity createPeriodicity(String periodicity){
        return new Periodicity(periodicity);
    }

    private Periodicity (String periodicity) {
        this.periodicity= periodicity;
    }


    // Getters

    public String getPeriodicity() {
        return periodicity;
    }


    // Equals & hashCode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Periodicity that = (Periodicity) o;
        return Objects.equals(periodicity, that.periodicity);
    }


    @Override
    public int hashCode() {
        return Objects.hash(periodicity);
    }
}
