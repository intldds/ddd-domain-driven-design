package com.finance.project.domainLayer.domainEntities.aggregates.person;

import com.finance.project.domainLayer.entitiesInterfaces.ValueObject;

import java.util.Objects;

/**
 * The type Name.
 */
public class Name implements ValueObject {

    private String name;

    //Constructor

    /**
     * Create name name.
     *
     * @param name the name
     * @return the name
     */
    public static Name createName(String name){
        return new Name(name);
    }

    private Name(String name) {
        if (name == null || name.equals("")) {
            throw new IllegalArgumentException("Name not created due to the fact that the name parameter hasn't a valid argument");
        } else {
            this.name = name;
        }
    }

    //get name

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
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
        Name name1 = (Name) o;
        return Objects.equals(name.toUpperCase(), name1.name.toUpperCase());
    }

    //Hashcode

    /**
     * Hash code int.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
