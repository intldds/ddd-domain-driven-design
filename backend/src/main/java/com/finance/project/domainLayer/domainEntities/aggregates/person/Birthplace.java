package com.finance.project.domainLayer.domainEntities.aggregates.person;

import com.finance.project.domainLayer.entitiesInterfaces.ValueObject;

import java.util.Objects;


public class Birthplace implements ValueObject {

    private String birthplace;

    // Constructor

    public static Birthplace createBirthplace(String birthplace){
        return new Birthplace(birthplace);
    }

    private Birthplace (String birthplace) {
        if (birthplace == null) {
            throw new IllegalArgumentException("Birthplace not created due to the fact that the birthplace parameter hasn't a valid argument");
        }
        this.birthplace = birthplace;
    }

    // Getters

    public String getBirthplace() {
        return birthplace;
    }

    // Equals

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Birthplace that = (Birthplace) o;
        return Objects.equals(birthplace.toUpperCase(), that.birthplace.toUpperCase());
    }

    // hashCode

    @Override
    public int hashCode() {
        return Objects.hash(birthplace);
    }
}
