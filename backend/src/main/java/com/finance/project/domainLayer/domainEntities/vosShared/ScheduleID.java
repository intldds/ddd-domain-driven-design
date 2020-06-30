package com.finance.project.domainLayer.domainEntities.vosShared;

import com.finance.project.domainLayer.entitiesInterfaces.ValueObject;
import com.finance.project.domainLayer.domainEntities.aggregates.scheduling.Periodicity;
import com.finance.project.domainLayer.domainEntities.aggregates.scheduling.TriggerDate;

import java.time.LocalDate;
import java.util.Objects;

/**
 * The type Schedule id.
 *
 * @author Ala Matos
 */
/*
 * Ala Matos created on 16/03/2020
 * inside the PACKAGE switch2019.project.model.valueObjects
 */
public class ScheduleID implements ValueObject {
    private Description description;
    private TriggerDate triggerDate;
    private Periodicity periodicity;
    private TransactionType transactionType;

    /**
     * Create schedule id schedule id.
     *
     * @param description     the description
     * @param triggerDate     the trigger date
     * @param periodicity     the periodicity
     * @param transactionType the transaction type
     * @return the schedule id
     */
    public static ScheduleID createScheduleID(String description, LocalDate triggerDate,
                                              String periodicity, String transactionType) {

        return new ScheduleID(description, triggerDate, periodicity, transactionType);
    }

    private ScheduleID(String description, LocalDate triggerDate, String periodicity, String transactionType) {
        if (description == null || description.equals("")) {
            throw new IllegalArgumentException("The schedule wasn't created due to the fact that the description is invalid");
        } else if (triggerDate == null) {
            throw new IllegalArgumentException("The schedule wasn't created due to the fact that the triggerdate is invalid");
        } else if (periodicity == null || periodicity.equals("")) {
            throw new IllegalArgumentException("The schedule wasn't created due to the fact that the periodicity is invalid");
        } else if (transactionType == null || transactionType.equals("")) {
            throw new IllegalArgumentException("The schedule wasn't created due to the fact that the transactionType is invalid");
        } else {
            this.description = Description.createDescription(description);
            this.triggerDate = TriggerDate.createTriggerDate(triggerDate);
            this.periodicity = Periodicity.createPeriodicity(periodicity);
            this.transactionType = TransactionType.createTransactionType(transactionType);

        }
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public Description getDescription() {
        return description;
    }

    /**
     * Gets trigger date.
     *
     * @return the trigger date
     */
    public TriggerDate getTriggerDate() {
        return triggerDate;
    }

    /**
     * Gets periodicity.
     *
     * @return the periodicity
     */
    public Periodicity getPeriodicity() {
        return periodicity;
    }

    /**
     * Gets transaction type.
     *
     * @return the transaction type
     */
    public TransactionType getTransactionType() {
        return transactionType;
    }

    /**
     * Equals boolean.
     *
     * @param o the o
     * @return the boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ScheduleID)) {
            return false;
        } else {
            ScheduleID that = (ScheduleID) o;
                if (!this.description.equals(that.description)) {
                    return false;
                }
                if (!this.triggerDate.equals(that.triggerDate)) {
                    return false;
                }
                if (!this.periodicity.equals(that.periodicity)) {
                    return false;
                }
                if (!this.transactionType.equals(that.transactionType)) {
                    return false;
                }
            return true;
        }
    }

    /**
     * Hash code int.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return Objects.hash(description, triggerDate, periodicity, transactionType);
    }
}
