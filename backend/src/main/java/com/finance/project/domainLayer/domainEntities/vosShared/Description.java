package com.finance.project.domainLayer.domainEntities.vosShared;

import com.finance.project.domainLayer.entitiesInterfaces.ValueObject;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * The type Description.
 */
@Embeddable
public class Description implements ValueObject, Serializable {

    private String description;

    //Constructor

    /**
     * Create description description.
     *
     * @param description the description
     * @return the description
     */
    public static Description createDescription(String description){
        return new Description(description);
    }

    private Description(String description) {
        this.description = description;
    }

    //get Description

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    public Description() {
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
        Description that = (Description) o;
        return Objects.equals(description.toUpperCase(), that.description.toUpperCase());
    }

    //Hashcode

    /**
     * Hash code int.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return Objects.hash(description);
    }
}
