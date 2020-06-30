package com.finance.project.domainLayer.domainEntities.vosShared;

import com.finance.project.domainLayer.entitiesInterfaces.ValueObject;

import java.util.Objects;

/**
 * The type Type.
 */
public class Type implements ValueObject {

    private String type;

    //Constructor

    /**
     * Create type type.
     *
     * @param type the type
     * @return the type
     */
    public static Type createType(String type){
        return new Type(type);
    }

    private Type (String type) {
        if (type == null || type.equals("")) {
            throw new IllegalArgumentException("Type not created due to the fact that the type parameter hasn't a valid argument");
        }
        this.type= type;
    }

    //get Type

    /**
     * Gets type.
     *
     * @return the type
     */
    public String getType() {
        return type;
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
        Type type1 = (Type) o;
        return Objects.equals(type.toUpperCase(), type1.type.toUpperCase());
    }

    //Hashcode

    /**
     * Hash code int.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return Objects.hash(type);
    }
}
