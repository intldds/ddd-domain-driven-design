package com.finance.project.domainLayer.domainEntities.aggregates.scheduling;

import com.finance.project.domainLayer.entitiesInterfaces.ValueObject;

import java.time.LocalDate;
import java.util.Objects;

/**
 * The type Trigger date.
 */
public class TriggerDate implements ValueObject {

    private LocalDate triggerDate;

    //Constructor

    /**
     * Create trigger date trigger date.
     *
     * @param triggerDate the trigger date
     * @return the trigger date
     */
    public static TriggerDate createTriggerDate(LocalDate triggerDate){
        return new TriggerDate(triggerDate);
    }

    private TriggerDate (LocalDate triggerDate) {
        this.triggerDate= triggerDate;
    }

    //get triggerDate

    /**
     * Gets trigger date.
     *
     * @return the trigger date
     */
    public LocalDate getTriggerDate() {
        return triggerDate;
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
        TriggerDate that = (TriggerDate) o;
        return Objects.equals(triggerDate, that.triggerDate);
    }

    //Hashcode

    /**
     * Hash code int.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return Objects.hash(triggerDate);
    }
}
