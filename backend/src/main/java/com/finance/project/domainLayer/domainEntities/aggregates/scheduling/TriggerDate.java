package com.finance.project.domainLayer.domainEntities.aggregates.scheduling;

import com.finance.project.domainLayer.entitiesInterfaces.ValueObject;

import java.time.LocalDate;
import java.util.Objects;


public class TriggerDate implements ValueObject {

    private LocalDate triggerDate;

    // Constructor

    public static TriggerDate createTriggerDate(LocalDate triggerDate) {
        return new TriggerDate(triggerDate);
    }

    private TriggerDate(LocalDate triggerDate) {
        this.triggerDate = triggerDate;
    }

    // Getters

    public LocalDate getTriggerDate() {
        return triggerDate;
    }

    // Equals & hashCode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TriggerDate that = (TriggerDate) o;
        return Objects.equals(triggerDate, that.triggerDate);
    }


    @Override
    public int hashCode() {
        return Objects.hash(triggerDate);
    }
}
