package com.finance.project.domainLayer.domainEntities.aggregates.person;

import com.finance.project.domainLayer.entitiesInterfaces.ValueObject;

import java.time.LocalDate;
import java.util.Objects;

/**
 * The type Birthdate.
 */
public class Birthdate implements ValueObject {

    private LocalDate birthdate;

    //Constructor

    /**
     * Create birthdate birthdate.
     *
     * @param birthdate the birthdate
     * @return the birthdate
     */
    public static Birthdate createBirthdate(LocalDate birthdate) {
        return new Birthdate(birthdate);
    }

    private Birthdate(LocalDate birthdate) {
        if (birthdate == null) {
            throw new IllegalArgumentException("Birthdate not created due to the fact that the birthdate parameter hasn't a valid argument");
        }
        this.birthdate = birthdate;
    }

    //get Birthdate

    /**
     * Gets birthdate.
     *
     * @return the birthdate
     */
    public LocalDate getBirthdate() {
        return birthdate;
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
        Birthdate birthdate1 = (Birthdate) o;
        return Objects.equals(birthdate, birthdate1.birthdate);
    }

    //HashCode

    /**
     * Hash code int.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return Objects.hash(birthdate);
    }
}
