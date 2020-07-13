package com.finance.project.domainLayer.domainEntities.aggregates.scheduling;

import com.finance.project.domainLayer.domainEntities.aggregates.ledger.Ledger;
import com.finance.project.domainLayer.domainEntities.vosShared.AccountID;
import com.finance.project.domainLayer.domainEntities.vosShared.CategoryID;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


class SchedulingTask implements Runnable {

    private Ledger ledger;
    private String transactionType;
    private AccountID creditAccountID;
    private AccountID debitAccountID;
    private CategoryID categoryID;
    private double amount;
    private String description;
    private String periodicity;
    private LocalDate triggerDate;
    private ScheduledExecutorService executor;


    public SchedulingTask(ScheduledExecutorService executor, Ledger ledger, String transactionType, double amount, AccountID creditAccountID,
                          AccountID debitAccountID, CategoryID categoryID, String description, LocalDate triggerDate, String periodicity) {

        this.ledger = ledger;
        this.transactionType = transactionType;
        this.amount = amount;
        this.creditAccountID = creditAccountID;
        this.debitAccountID = debitAccountID;
        this.categoryID = categoryID;
        this.description = description;
        this.periodicity = periodicity;
        this.triggerDate = triggerDate;
        this.executor = executor;

    }

    // Run

    public void run() {
        this.ledger.createAndAddTransaction(categoryID, transactionType, description, amount, creditAccountID, debitAccountID);

        if (periodicity.equals("Daily")) {
            executor.schedule(new SchedulingTask(executor, ledger, transactionType, amount, creditAccountID, debitAccountID, categoryID, description, triggerDate.plusDays(1), periodicity), getNextTriggerDate(periodicity, triggerDate), TimeUnit.MILLISECONDS);
        }
        if (periodicity.equals("Working Days") && (triggerDate.getDayOfWeek() == DayOfWeek.FRIDAY)) {
            executor.schedule(new SchedulingTask(executor, ledger, transactionType, amount, creditAccountID, debitAccountID, categoryID, description, triggerDate.plusDays(3), periodicity), getNextTriggerDate(periodicity, triggerDate), TimeUnit.MILLISECONDS);
        }
        if (periodicity.equals("Working Days") && (triggerDate.getDayOfWeek() != DayOfWeek.FRIDAY)) {
            executor.schedule(new SchedulingTask(executor, ledger, transactionType, amount, creditAccountID, debitAccountID, categoryID, description, triggerDate.plusDays(1), periodicity), getNextTriggerDate(periodicity, triggerDate), TimeUnit.MILLISECONDS);
        }
        if (periodicity.equals("Weekly")) {
            executor.schedule(new SchedulingTask(executor, ledger, transactionType, amount, creditAccountID, debitAccountID, categoryID, description, triggerDate.plusDays(7), periodicity), getNextTriggerDate(periodicity, triggerDate), TimeUnit.MILLISECONDS);
        }
        if (periodicity.equals("Monthly")) {
            LocalDate nextMonth = triggerDate.plusMonths(1);
            Period period = Period.between(nextMonth, triggerDate);
            executor.schedule(new SchedulingTask(executor, ledger, transactionType, amount, creditAccountID, debitAccountID, categoryID, description, triggerDate.plusDays(period.getDays()), periodicity), getNextTriggerDate(periodicity, triggerDate), TimeUnit.MILLISECONDS);
        }
    }


    private int getNextTriggerDate(String periodicity, LocalDate triggerDate) {
        long dayUnit = 200;
        long time = 0;

        if (periodicity.equals("Daily")) {
            time = dayUnit;
        } else if (periodicity.equals("Working Days")) {

            if (triggerDate.getDayOfWeek() == DayOfWeek.FRIDAY) {
                time = dayUnit * 3;
            } else {
                time = dayUnit;
            }
        } else if (periodicity.equals("Weekly")) {

            time = dayUnit * 7;
        } else if (periodicity.equals("Monthly")) {

            LocalDate nextMonth = triggerDate.plusMonths(1);
            long diff = ChronoUnit.DAYS.between(triggerDate, nextMonth);
            time = dayUnit * diff;
        }

        return (int) time;
    }


}