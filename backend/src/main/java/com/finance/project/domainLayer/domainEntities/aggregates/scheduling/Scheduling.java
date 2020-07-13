package com.finance.project.domainLayer.domainEntities.aggregates.scheduling;

import com.finance.project.domainLayer.domainEntities.aggregates.ledger.Ledger;
import com.finance.project.domainLayer.domainEntities.vosShared.*;
import com.finance.project.domainLayer.entitiesInterfaces.Entity;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class Scheduling implements Entity {

    private Ledger ledger;
    private AccountID creditAccountID;
    private AccountID debitAccountID;
    private CategoryID categoryID;
    private Amount amount;
    private ScheduleID scheduleID;
    private ScheduledExecutorService executor;
    private DateOfCreation creationDate;

    public static Scheduling createScheduling(ScheduledExecutorService executor, Ledger ledger, String transactionType, double amount,
                                              AccountID creditAccount, AccountID debitAccount, CategoryID category, String description,
                                              LocalDate triggerDate, String periodicity) {
        return new Scheduling(executor, ledger, transactionType, amount, creditAccount, debitAccount, category, description, triggerDate, periodicity);
    }

    private Scheduling(ScheduledExecutorService executor, Ledger ledger, String transactionType, double amount, AccountID creditAccount, AccountID debitAccount, CategoryID categoryID, String description, LocalDate triggerDate, String periodicity) {
        this.ledger = ledger;
        this.scheduleID = ScheduleID.createScheduleID(description, triggerDate, periodicity, transactionType);
        this.amount = Amount.createAmount(amount);
        this.creditAccountID = creditAccount;
        this.debitAccountID = debitAccount;
        this.categoryID = categoryID;
        this.creationDate = DateOfCreation.createDateOfCreationAutomatic();
        this.executor = executor;

        // Day Unit is 100ms
        if (periodicity.equals("Working Days") && ((triggerDate.getDayOfWeek() == DayOfWeek.SATURDAY) || (triggerDate.getDayOfWeek() == DayOfWeek.SUNDAY))) {
            throw new IllegalArgumentException("The chosen day is not a working day");
        }
        executor.schedule(new SchedulingTask(executor, ledger, transactionType, amount, creditAccount, debitAccount, categoryID, description, triggerDate, periodicity), getTriggerDate(triggerDate), TimeUnit.MILLISECONDS);
    }


    private int getTriggerDate(LocalDate triggerDate) {
        LocalDate now = LocalDate.now();
        Period period = Period.between(now, triggerDate);

        long dayUnit = 200;
        long diff = period.getDays() * dayUnit;


        return (int) diff;
    }


    public boolean checkSchedulingID(ScheduleID scheduleID) {
        return this.scheduleID.equals(scheduleID);
    }


    // Equals and hashCode

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (this == o) {
            return true;
        } else {
            Scheduling scheduling = (Scheduling) o;

            if (!this.creditAccountID.equals(scheduling.creditAccountID)) {
                return false;
            }

            if (!this.debitAccountID.equals(scheduling.debitAccountID)) {
                return false;
            }

            if (!this.categoryID.equals(scheduling.categoryID)) {
                return false;
            }
            if (!this.scheduleID.equals(scheduling.scheduleID)) {
                return false;
            }

            if (!this.amount.equals(scheduling.amount)) {
                return false;
            }

            if (this.ledger != null && scheduling.ledger != null) {
                if (!this.ledger.equals(scheduling.ledger)) {
                    return false;
                }
            }

            return true;
        }
    }


    @Override
    public int hashCode() {
        return Objects.hash(creditAccountID, debitAccountID, categoryID, scheduleID, amount, ledger);
    }
}
