package com.finance.project.domainLayer.domainEntities.vosShared;

import com.finance.project.domainLayer.entitiesInterfaces.ValueObject;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * The type Date of creation.
 */
@Embeddable
public class DateOfCreation implements ValueObject, Serializable {

    private LocalDate dateOfCreation;

    //Constructor

    /**
     * Create date of creation date of creation.
     *
     * @param dateOfCreation the date of creation
     * @return the date of creation
     */
    public static DateOfCreation createDateOfCreation(LocalDate dateOfCreation){
        return  new DateOfCreation(dateOfCreation);
    }

    /**
     * Create date of creation automatic date of creation.
     *
     * @return the date of creation
     */
    public static DateOfCreation createDateOfCreationAutomatic() {
        return new DateOfCreation();
    }

    private DateOfCreation(LocalDate dateOfCreation) {
        if (dateOfCreation == null) {
            throw new IllegalArgumentException("DateOfCreation not created due to the fact that the dateOfCreation parameter hasn't a valid argument");
        }
        this.dateOfCreation = dateOfCreation;
    }

    private DateOfCreation () {
       this.dateOfCreation= LocalDate.now();
    }

    //get DateOfCreation

    /**
     * Gets date of creation.
     *
     * @return the date of creation
     */
    public LocalDate getDateOfCreation() {
        return dateOfCreation;
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
        DateOfCreation that = (DateOfCreation) o;
        return Objects.equals(dateOfCreation, that.dateOfCreation);
    }

    //HashCode

    /**
     * Hash code int.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return Objects.hash(dateOfCreation);
    }
}
