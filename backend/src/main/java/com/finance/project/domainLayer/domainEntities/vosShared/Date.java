package com.finance.project.domainLayer.domainEntities.vosShared;

import com.finance.project.domainLayer.entitiesInterfaces.ValueObject;

import java.time.LocalDate;
import java.util.Objects;

/**
 * The type Date.
 */
public class Date implements ValueObject {
    private LocalDate date;

    //Constructor

    /**
     * Create date date.
     *
     * @param date the date
     * @return the date
     */
    public static Date createDate(LocalDate date) {
        return new Date(date);
    }

    private Date(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("Date not created due to the fact that the date parameter hasn't a valid argument");
        }
        this.date = date;
    }

    //get date

    /**
     * Gets date.
     *
     * @return the date
     */
    public LocalDate getDate() {
        return date;
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
        Date date1 = (Date) o;
        return Objects.equals(date, date1.date);
    }

    //Hashcode

    /**
     * Hash code int.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return Objects.hash(date);
    }
}
