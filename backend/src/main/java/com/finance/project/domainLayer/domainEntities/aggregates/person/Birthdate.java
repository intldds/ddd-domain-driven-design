package com.finance.project.domainLayer.domainEntities.aggregates.person;

import com.finance.project.domainLayer.entitiesInterfaces.ValueObject;

import java.time.LocalDate;
import java.util.Objects;


public class Birthdate implements ValueObject {

    private LocalDate birthdate;

    // Constructor

    public static Birthdate createBirthdate(LocalDate birthdate) {
        return new Birthdate(birthdate);
    }

    private Birthdate(LocalDate birthdate) {
        if (birthdate == null) {
            throw new IllegalArgumentException("Birthdate not created due to the fact that the birthdate parameter hasn't a valid argument");
        }
        this.birthdate = birthdate;
    }

    // Getters

    public LocalDate getBirthdate() {
        return birthdate;
    }


    // Equals & hashCode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Birthdate birthdate1 = (Birthdate) o;
        return Objects.equals(birthdate, birthdate1.birthdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(birthdate);
    }
}
