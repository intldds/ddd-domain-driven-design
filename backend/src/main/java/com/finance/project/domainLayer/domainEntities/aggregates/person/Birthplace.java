package com.finance.project.domainLayer.domainEntities.aggregates.person;

import com.finance.project.domainLayer.entitiesInterfaces.ValueObject;

import java.util.Objects;

/**
 * The type Birthplace.
 */
public class Birthplace implements ValueObject {

    private String birthplace;

    //Constructor

    /**
     * Create birthplace birthplace.
     *
     * @param birthplace the birthplace
     * @return the birthplace
     */
    public static Birthplace createBirthplace(String birthplace){
        return new Birthplace(birthplace);
    }

    private Birthplace (String birthplace) {
        if (birthplace == null) {
            throw new IllegalArgumentException("Birthplace not created due to the fact that the birthplace parameter hasn't a valid argument");
        }
        this.birthplace = birthplace;
    }

    //get birthplace

    /**
     * Gets birthplace.
     *
     * @return the birthplace
     */
    public String getBirthplace() {
        return birthplace;
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
        Birthplace that = (Birthplace) o;
        return Objects.equals(birthplace.toUpperCase(), that.birthplace.toUpperCase());
    }

    //Hashcode

    /**
     * Hash code int.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return Objects.hash(birthplace);
    }
}
