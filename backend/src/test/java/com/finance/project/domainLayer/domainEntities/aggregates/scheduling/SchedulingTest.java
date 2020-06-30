package com.finance.project.domainLayer.domainEntities.aggregates.scheduling;

import com.finance.project.domainLayer.domainEntities.aggregates.ledger.Ledger;
import com.finance.project.domainLayer.domainEntities.vosShared.AccountID;
import com.finance.project.domainLayer.domainEntities.vosShared.CategoryID;
import com.finance.project.domainLayer.domainEntities.vosShared.PersonID;
import com.finance.project.domainLayer.domainEntities.vosShared.ScheduleID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static org.junit.jupiter.api.Assertions.*;

class SchedulingTest {

    /*

    //SCHEDULING

    @Test
    @DisplayName("Test For Scheduling() - Workings Days - Starting On Monday - 8 Transactions")
    public void schedulingWorkingDaysStartingOnMonday() throws InterruptedException {
        //Day Unit 200ms
        //Monday - Tuesday - Wednesday - Thursday - Friday - x - x - Monday - Tuesday - Wednesday = 8

        //Arrange Account Nr.1


        String debAccountDenomination = "EmployerSA";
        PersonID hulkID = PersonID.createPersonID("hulk@marvel.com");
        AccountID hulkAccountID = AccountID.createAccountID(debAccountDenomination, hulkID);


        //Arrange Account Nr.2


        String debAccountDenominationA = "Allowance Money";
        PersonID wolverineID = PersonID.createPersonID("wolverine@marvel.com");
        AccountID wolverineAccountID = AccountID.createAccountID(debAccountDenomination, wolverineID);

        //Arrange Category
        CategoryID hulkCategoryID = CategoryID.createCategoryID("Allowance", hulkID);

        //Arrange Triggers/Timers
        LocalDate triggerDate = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        long timeToSleep = (ChronoUnit.DAYS.between(LocalDate.now(), triggerDate) * 200) + 1900;

        //Arrange Ledger
        Ledger ledger = Ledger.createLedger();

        //Arrange ThreadPool
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);


        //Act
        Scheduling.createScheduling(executor, ledger, "Credit", 50.0, hulkAccountID, wolverineAccountID, hulkCategoryID, "Allowance", triggerDate, "Working Days");
        Thread.sleep(timeToSleep);

        //Assert
        assertEquals(8, ledger.getRecords().size());
    }

    @Test
    @DisplayName("Test For Scheduling() - Daily - Starting Today - 10 Transactions")
    public void schedulingDailyStartingToday() throws InterruptedException {
        //Day Unit 200ms
        //2s = 10 Transactions

        //Arrange Account Nr.1


        String debAccountDenomination = "EmployerSA";
        PersonID hulkID = PersonID.createPersonID("hulk@marvel.com");
        AccountID hulkAccountID = AccountID.createAccountID(debAccountDenomination, hulkID);


        //Arrange Account Nr.2


        String debAccountDenominationA = "Allowance Money";
        PersonID wolverineID = PersonID.createPersonID("wolverine@marvel.com");
        AccountID wolverineAccountID = AccountID.createAccountID(debAccountDenomination, wolverineID);

        //Arrange Category
        CategoryID hulkCategoryID = CategoryID.createCategoryID("Allowance", hulkID);


        //Arrange Triggers/Timers
        LocalDate triggerDate = LocalDate.now();
        long timeToSleep = 1950;

        //Arrange Ledger
        Ledger ledger = Ledger.createLedger();

        //Arrange ThreadPool
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);


        //Act
        Scheduling.createScheduling(executor, ledger, "Credit", 50.0, hulkAccountID, wolverineAccountID, hulkCategoryID, "Allowance", triggerDate, "Daily");
        Thread.sleep(timeToSleep);

        //Assert
        assertEquals(10, ledger.getRecords().size());
    }


     */


    @Test
    @DisplayName("Test For Scheduling() - Weekly - Starting Today - 2 Transactions")
    public void schedulingWeeklyStartingToday() throws InterruptedException {
        //Day Unit 200ms
        //2s = 10 Transactions

        //Arrange Account Nr.1


        String debAccountDenomination = "EmployerSA";
        PersonID hulkID = PersonID.createPersonID("hulk@marvel.com");
        AccountID hulkAccountID = AccountID.createAccountID(debAccountDenomination, hulkID);


        //Arrange Account Nr.2


        String debAccountDenominationA = "Allowance Money";
        PersonID wolverineID = PersonID.createPersonID("wolverine@marvel.com");
        AccountID wolverineAccountID = AccountID.createAccountID(debAccountDenomination, wolverineID);

        //Arrange Category
        CategoryID hulkCategoryID = CategoryID.createCategoryID("Allowance", hulkID);


        //Arrange Triggers/Timers
        LocalDate triggerDate = LocalDate.now();
        long timeToSleep = 2150;

        //Arrange Ledger
        Ledger ledger = Ledger.createLedger();

        //Arrange ThreadPool
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);


        //Act
        Scheduling.createScheduling(executor, ledger, "Credit", 50.0, hulkAccountID, wolverineAccountID, hulkCategoryID, "Allowance", triggerDate, "Weekly");
        Thread.sleep(timeToSleep);

        //Assert
        assertEquals(2, ledger.getRecords().size());
    }

    @Test
    @DisplayName("Test For Scheduling() - Monthly - Starting Today - 2 Transactions")
    public void schedulingMonthlyStartingToday() throws InterruptedException {
        //Day Unit 200ms
        //2s = 10 Transactions

        //Arrange Account Nr.1


        String debAccountDenomination = "EmployerSA";
        PersonID hulkID = PersonID.createPersonID("hulk@marvel.com");
        AccountID hulkAccountID = AccountID.createAccountID(debAccountDenomination, hulkID);


        //Arrange Account Nr.2


        String debAccountDenominationA = "Allowance Money";
        PersonID wolverineID = PersonID.createPersonID("wolverine@marvel.com");
        AccountID wolverineAccountID = AccountID.createAccountID(debAccountDenomination, wolverineID);

        //Arrange Category
        CategoryID hulkCategoryID = CategoryID.createCategoryID("Allowance", hulkID);


        //Arrange Triggers/Timers
        LocalDate triggerDate = LocalDate.now();
        long timeToSleep = 6300;

        //Arrange Ledger
        Ledger ledger = Ledger.createLedger();

        //Arrange ThreadPool
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);


        //Act
        Scheduling.createScheduling(executor, ledger, "Credit", 50.0, hulkAccountID, wolverineAccountID, hulkCategoryID, "Allowance", triggerDate, "Monthly");
        Thread.sleep(timeToSleep);

        //Assert
        assertEquals(2, ledger.getRecords().size());
    }

    @Test
    @DisplayName("Test For Scheduling() - Monthly - Starting 5 Days From Now - 1 Transaction")
    public void schedulingMonthlyStartingFiveDaysFromNow() throws InterruptedException {
        //Day Unit 200ms
        //2s = 10 Transactions

        //Arrange Account Nr.1


        String debAccountDenomination = "EmployerSA";
        PersonID hulkID = PersonID.createPersonID("hulk@marvel.com");
        AccountID hulkAccountID = AccountID.createAccountID(debAccountDenomination, hulkID);


        //Arrange Account Nr.2


        String debAccountDenominationA = "Allowance Money";
        PersonID wolverineID = PersonID.createPersonID("wolverine@marvel.com");
        AccountID wolverineAccountID = AccountID.createAccountID(debAccountDenomination, wolverineID);

        //Arrange Category
        CategoryID hulkCategoryID = CategoryID.createCategoryID("Allowance", hulkID);


        //Arrange Triggers/Timers
        LocalDate triggerDate = LocalDate.now().plusDays(5);
        long timeToSleep = 6100;

        //Arrange Ledger
        Ledger ledger = Ledger.createLedger();

        //Arrange ThreadPool
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);


        //Act
        Scheduling.createScheduling(executor, ledger, "Credit", 50.0, hulkAccountID, wolverineAccountID, hulkCategoryID, "Allowance", triggerDate, "Monthly");
        Thread.sleep(timeToSleep);

        //Assert
        assertEquals(1, ledger.getRecords().size());
    }

    @Test
    @DisplayName("Test For Scheduling() - Working Days - Saturday - Exception")
    public void schedulingWorkingDays_Exception() {

        //Day Unit 200ms
        //2s = 10 Transactions

        //Arrange Account Nr.1


        String debAccountDenomination = "EmployerSA";
        PersonID hulkID = PersonID.createPersonID("hulk@marvel.com");
        AccountID hulkAccountID = AccountID.createAccountID(debAccountDenomination, hulkID);


        //Arrange Account Nr.2


        String debAccountDenominationA = "Allowance Money";
        PersonID wolverineID = PersonID.createPersonID("wolverine@marvel.com");
        AccountID wolverineAccountID = AccountID.createAccountID(debAccountDenomination, wolverineID);

        //Arrange Category
        CategoryID hulkCategoryID = CategoryID.createCategoryID("Allowance", hulkID);


        //Arrange Triggers/Timers
        LocalDate triggerDate = LocalDate.of(2020, 02, 8);
        long timeToSleep = 1950;

        //Arrange Ledger
        Ledger ledger = Ledger.createLedger();

        //Arrange ThreadPool
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);


        //Act
        Scheduling.createScheduling(executor, ledger, "Credit", 50.0, hulkAccountID, wolverineAccountID, hulkCategoryID, "Allowance", triggerDate, "Daily");

        //Act

        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> Scheduling.createScheduling(executor, ledger, "Credit", 50.0, hulkAccountID, wolverineAccountID, hulkCategoryID, "Allowance", triggerDate, "Working Days"));

        //Assert

        assertEquals(thrown.getMessage(), "The chosen day is not a working day");
    }

    //CHECK SCHEDULE ID
    @Test
    @DisplayName("Attest ScheduleID - Happy path")
    public void checkSchedulingID_HappyPath() throws InterruptedException {
        //Day Unit 200ms
        //2s = 10 Transactions

        //Arrange Account Nr.1


        String debAccountDenomination = "EmployerSA";
        PersonID hulkID = PersonID.createPersonID("hulk@marvel.com");
        AccountID hulkAccountID = AccountID.createAccountID(debAccountDenomination, hulkID);


        //Arrange Account Nr.2


        String credAccountDenomination = "Allowance Money";
        PersonID wolverineID = PersonID.createPersonID("wolverine@marvel.com");
        AccountID wolverineAccountID = AccountID.createAccountID(credAccountDenomination, wolverineID);

        //Arrange Category
        CategoryID hulkCategoryID = CategoryID.createCategoryID("Allowance", hulkID);


        //Arrange Triggers/Timers
        LocalDate triggerDate = LocalDate.now();
        long timeToSleep = 2100;

        //Arrange Ledger
        Ledger ledger = Ledger.createLedger();

        //Arrange ThreadPool
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        //Arrange Expected ScheduleID
        ScheduleID expectedScheduleID = ScheduleID.createScheduleID("Allowance", triggerDate, "Daily", "Credit");


        //Act
        Scheduling scheduling = Scheduling.createScheduling(executor, ledger, "Credit", 50.0, wolverineAccountID, hulkAccountID, hulkCategoryID, "Allowance", triggerDate, "Daily");
        Thread.sleep(timeToSleep);
        boolean schedulingResult = scheduling.checkSchedulingID(expectedScheduleID);

        //Assert
        assertEquals(true, schedulingResult);
    }

    @Test
    @DisplayName("Attest ScheduleID - Sad path")
    public void checkSchedulingID_SadPath() throws InterruptedException {
        //Day Unit 200ms
        //2s = 10 Transactions

        //Arrange Account Nr.1


        String debAccountDenomination = "EmployerSA";
        PersonID hulkID = PersonID.createPersonID("hulk@marvel.com");
        AccountID hulkAccountID = AccountID.createAccountID(debAccountDenomination, hulkID);


        //Arrange Account Nr.2


        String credAccountDenomination = "Allowance Money";
        PersonID wolverineID = PersonID.createPersonID("wolverine@marvel.com");
        AccountID wolverineAccountID = AccountID.createAccountID(credAccountDenomination, wolverineID);

        //Arrange Category
        CategoryID hulkCategoryID = CategoryID.createCategoryID("Allowance", hulkID);


        //Arrange Triggers/Timers
        LocalDate triggerDate = LocalDate.now();
        long timeToSleep = 2100;

        //Arrange Ledger
        Ledger ledger = Ledger.createLedger();

        //Arrange ThreadPool
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        //Arrange Expected ScheduleID
        ScheduleID expectedScheduleID = ScheduleID.createScheduleID("Gift", triggerDate, "Daily", "Credit");


        //Act
        Scheduling scheduling = Scheduling.createScheduling(executor, ledger, "Credit", 50.0, wolverineAccountID, hulkAccountID, hulkCategoryID, "Allowance", triggerDate, "Daily");
        Thread.sleep(timeToSleep);
        boolean schedulingResult = scheduling.checkSchedulingID(expectedScheduleID);

        //Assert
        assertEquals(false, schedulingResult);
    }

//    EQUALS

    @Test
    @DisplayName("Test For Equals() and hashCode() - Same schedule")
    public void equalsAndHashCode_SameSchedule() throws InterruptedException {
        //Day Unit 200ms
        //2s = 10 Transactions

        //Arrange Account Nr.1


        String debAccountDenomination = "EmployerSA";
        PersonID hulkID = PersonID.createPersonID("hulk@marvel.com");
        AccountID hulkAccountID = AccountID.createAccountID(debAccountDenomination, hulkID);


        //Arrange Account Nr.2


        String debAccountDenominationA = "Allowance Money";
        PersonID wolverineID = PersonID.createPersonID("wolverine@marvel.com");
        AccountID wolverineAccountID = AccountID.createAccountID(debAccountDenomination, wolverineID);

        //Arrange Category
        CategoryID hulkCategoryID = CategoryID.createCategoryID("Allowance", hulkID);


        //Arrange Triggers/Timers
        LocalDate triggerDate = LocalDate.now();
        long timeToSleep = 2100;

        //Arrange Ledger
        Ledger ledger = Ledger.createLedger();

        //Arrange ThreadPool
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);


        //Act
        Scheduling scheduling1 = Scheduling.createScheduling(executor, ledger, "Credit", 50.0, hulkAccountID, wolverineAccountID, hulkCategoryID, "Allowance", triggerDate, "Daily");
        Scheduling scheduling2 = Scheduling.createScheduling(executor, ledger, "Credit", 50.0, hulkAccountID, wolverineAccountID, hulkCategoryID, "Allowance", triggerDate, "Daily");
        Thread.sleep(timeToSleep);

        //Assert
        assertEquals(scheduling1, scheduling2);
        assertEquals(true, scheduling1.hashCode() == scheduling2.hashCode());
    }

    @Test
    @DisplayName("Test For Equals() and hashCode() - Different Ledger")
    public void equalsAndHashCode_DifferentLedger() throws InterruptedException {
        //Day Unit 200ms
        //2s = 10 Transactions

        //Arrange Account Nr.1


        String debAccountDenomination = "EmployerSA";
        PersonID hulkID = PersonID.createPersonID("hulk@marvel.com");
        AccountID hulkAccountID = AccountID.createAccountID(debAccountDenomination, hulkID);


        //Arrange Account Nr.2


        String debAccountDenominationA = "Allowance Money";
        PersonID wolverineID = PersonID.createPersonID("wolverine@marvel.com");
        AccountID wolverineAccountID = AccountID.createAccountID(debAccountDenomination, wolverineID);

        //Arrange Category
        CategoryID hulkCategoryID = CategoryID.createCategoryID("Allowance", hulkID);

        //Arrange Triggers/Timers
        LocalDate triggerDate = LocalDate.now();
        long timeToSleep = 2100;

        //Arrange Ledger Nr.1
        Ledger ledger1 = Ledger.createLedger();

        //Arrange Ledger Nr.2
        Ledger ledger2 = Ledger.createLedger();
        ledger2.createAndAddTransaction(hulkCategoryID, "Credit", "Allowance", 50.0, hulkAccountID, wolverineAccountID);

        //Arrange ThreadPool
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);


        //Act
        Scheduling scheduling1 = Scheduling.createScheduling(executor, ledger1, "Credit", 50.0, hulkAccountID, wolverineAccountID, hulkCategoryID, "Allowance", triggerDate, "Daily");
        Scheduling scheduling2 = Scheduling.createScheduling(executor, ledger2, "Credit", 50.0, hulkAccountID, wolverineAccountID, hulkCategoryID, "Allowance", triggerDate, "Daily");
        Thread.sleep(timeToSleep);

        //Assert
        assertNotEquals(scheduling1, scheduling2);
        assertEquals(false, scheduling1.hashCode() == scheduling2.hashCode());
    }

    @Test
    @DisplayName("Test For Equals() and hashCode() - Different Transaction Type")
    public void equalsAndHashCode_DifferentTransactionType() throws InterruptedException {
        //Day Unit 200ms
        //2s = 10 Transactions

        //Arrange Account Nr.1


        String debAccountDenomination = "EmployerSA";
        PersonID hulkID = PersonID.createPersonID("hulk@marvel.com");
        AccountID hulkAccountID = AccountID.createAccountID(debAccountDenomination, hulkID);


        //Arrange Account Nr.2


        String debAccountDenominationA = "Allowance Money";
        PersonID wolverineID = PersonID.createPersonID("wolverine@marvel.com");
        AccountID wolverineAccountID = AccountID.createAccountID(debAccountDenomination, wolverineID);

        //Arrange Category
        CategoryID hulkCategoryID = CategoryID.createCategoryID("Allowance", hulkID);


        //Arrange Triggers/Timers
        LocalDate triggerDate = LocalDate.now();
        long timeToSleep = 2100;

        //Arrange Ledger
        Ledger ledger = Ledger.createLedger();

        //Arrange ThreadPool
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);


        //Act
        Scheduling scheduling1 = Scheduling.createScheduling(executor, ledger, "Credit", 50.0, hulkAccountID, wolverineAccountID, hulkCategoryID, "Allowance", triggerDate, "Daily");
        Scheduling scheduling2 = Scheduling.createScheduling(executor, ledger, "Debit", 50.0, hulkAccountID, wolverineAccountID, hulkCategoryID, "Allowance", triggerDate, "Daily");
        Thread.sleep(timeToSleep);

        //Assert
        assertNotEquals(scheduling1, scheduling2);
        assertEquals(false, scheduling1.hashCode() == scheduling2.hashCode());
    }

    @Test
    @DisplayName("Test For Equals() and hashCode() - Different Amount")
    public void equalsAndHashCode_DifferentAmount() throws InterruptedException {
        //Day Unit 200ms
        //2s = 10 Transactions

        //Arrange Account Nr.1


        String debAccountDenomination = "EmployerSA";
        PersonID hulkID = PersonID.createPersonID("hulk@marvel.com");
        AccountID hulkAccountID = AccountID.createAccountID(debAccountDenomination, hulkID);


        //Arrange Account Nr.2


        String debAccountDenominationA = "Allowance Money";
        PersonID wolverineID = PersonID.createPersonID("wolverine@marvel.com");
        AccountID wolverineAccountID = AccountID.createAccountID(debAccountDenomination, wolverineID);

        //Arrange Category
        CategoryID hulkCategoryID = CategoryID.createCategoryID("Allowance", hulkID);


        //Arrange Triggers/Timers
        LocalDate triggerDate = LocalDate.now();
        long timeToSleep = 2100;

        //Arrange Ledger
        Ledger ledger = Ledger.createLedger();

        //Arrange ThreadPool
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);


        //Act
        Scheduling scheduling1 = Scheduling.createScheduling(executor, ledger, "Credit", 50.0, hulkAccountID, wolverineAccountID, hulkCategoryID, "Allowance", triggerDate, "Daily");
        Scheduling scheduling2 = Scheduling.createScheduling(executor, ledger, "Credit", 60.0, hulkAccountID, wolverineAccountID, hulkCategoryID, "Allowance", triggerDate, "Daily");
        Thread.sleep(timeToSleep);

        //Assert
        assertNotEquals(scheduling1, scheduling2);
        assertEquals(false, scheduling1.hashCode() == scheduling2.hashCode());
    }

    @Test
    @DisplayName("Test For Equals() and hashCode() - Different Debit Account")
    public void equalsAndHashCode_DifferentDebitAccount() throws InterruptedException {
        //Day Unit 200ms
        //2s = 10 Transactions

        //Arrange Account Nr.1


        String debAccountDenomination = "EmployerSA";
        PersonID hulkID = PersonID.createPersonID("hulk@marvel.com");
        AccountID hulkAccountID = AccountID.createAccountID(debAccountDenomination, hulkID);


        //Arrange Account Nr.2


        String debAccountDenominationA = "Allowance Money";
        PersonID wolverineID = PersonID.createPersonID("wolverine@marvel.com");
        AccountID wolverineAccountID = AccountID.createAccountID(debAccountDenominationA, wolverineID);


        //Arrange Account Nr.3

        String debAccountDenominationB = "Main Account";
        PersonID wonderWomanID = PersonID.createPersonID("wonderwoman@marvel.com");
        AccountID accountBancoTotta = AccountID.createAccountID(debAccountDenominationB, wonderWomanID);

        //Arrange Category
        CategoryID hulkCategoryID = CategoryID.createCategoryID("Allowance", hulkID);


        //Arrange Triggers/Timers
        LocalDate triggerDate = LocalDate.now();
        long timeToSleep = 2100;

        //Arrange Ledger
        Ledger ledger = Ledger.createLedger();

        //Arrange ThreadPool
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);


        //Act
        Scheduling scheduling1 = Scheduling.createScheduling(executor, ledger, "Credit", 50.0, hulkAccountID, wolverineAccountID, hulkCategoryID, "Allowance", triggerDate, "Daily");
        Scheduling scheduling2 = Scheduling.createScheduling(executor, ledger, "Credit", 50.0, hulkAccountID, accountBancoTotta, hulkCategoryID, "Allowance", triggerDate, "Daily");
        Thread.sleep(timeToSleep);

        //Assert
        assertNotEquals(scheduling1, scheduling2);
        assertEquals(false, scheduling1.hashCode() == scheduling2.hashCode());
    }

    @Test
    @DisplayName("Test For Equals() and hashCode() - Different Credit Account")
    public void equalsAndHashCode_DifferentCreditAccount() throws InterruptedException {
        //Day Unit 200ms
        //2s = 10 Transactions

        //Arrange Account Nr.1


        String debAccountDenomination = "EmployerSA";
        PersonID hulkID = PersonID.createPersonID("hulk@marvel.com");
        AccountID hulkAccountID = AccountID.createAccountID(debAccountDenomination, hulkID);


        //Arrange Account Nr.2


        String debAccountDenominationA = "Allowance Money";
        PersonID wolverineID = PersonID.createPersonID("wolverine@marvel.com");
        AccountID wolverineAccountID = AccountID.createAccountID(debAccountDenomination, wolverineID);


        //Arrange Account Nr.3

        String debAccountDenominationB = "Main Account";
        PersonID wonderWomanID = PersonID.createPersonID("wonderwoman@marvel.com");
        AccountID accountBancoTotta = AccountID.createAccountID(debAccountDenominationB, wonderWomanID);

        //Arrange Category
        CategoryID hulkCategoryID = CategoryID.createCategoryID("Allowance", hulkID);


        //Arrange Triggers/Timers
        LocalDate triggerDate = LocalDate.now();
        long timeToSleep = 2100;

        //Arrange Ledger
        Ledger ledger = Ledger.createLedger();

        //Arrange ThreadPool
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);


        //Act
        Scheduling scheduling1 = Scheduling.createScheduling(executor, ledger, "Credit", 50.0, hulkAccountID, wolverineAccountID, hulkCategoryID, "Allowance", triggerDate, "Daily");
        Scheduling scheduling2 = Scheduling.createScheduling(executor, ledger, "Credit", 50.0, accountBancoTotta, wolverineAccountID, hulkCategoryID, "Allowance", triggerDate, "Daily");
        Thread.sleep(timeToSleep);

        //Assert
        assertNotEquals(scheduling1, scheduling2);
        assertEquals(false, scheduling1.hashCode() == scheduling2.hashCode());
    }

    @Test
    @DisplayName("Test For Equals() and hashCode() - Different Description")
    public void equalsAndHashCode_DifferentDescription() throws InterruptedException {
        //Day Unit 200ms
        //2s = 10 Transactions

        //Arrange Account Nr.1


        String debAccountDenomination = "EmployerSA";
        PersonID hulkID = PersonID.createPersonID("hulk@marvel.com");
        AccountID hulkAccountID = AccountID.createAccountID(debAccountDenomination, hulkID);


        //Arrange Account Nr.2


        String debAccountDenominationA = "Allowance Money";
        PersonID wolverineID = PersonID.createPersonID("wolverine@marvel.com");
        AccountID wolverineAccountID = AccountID.createAccountID(debAccountDenomination, wolverineID);

        //Arrange Category
        CategoryID hulkCategoryID = CategoryID.createCategoryID("Allowance", hulkID);


        //Arrange Triggers/Timers
        LocalDate triggerDate = LocalDate.now();
        long timeToSleep = 2100;

        //Arrange Ledger
        Ledger ledger = Ledger.createLedger();

        //Arrange ThreadPool
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);


        //Act
        Scheduling scheduling1 = Scheduling.createScheduling(executor, ledger, "Credit", 50.0, hulkAccountID, wolverineAccountID, hulkCategoryID, "Allowance", triggerDate, "Daily");
        Scheduling scheduling2 = Scheduling.createScheduling(executor, ledger, "Credit", 50.0, hulkAccountID, wolverineAccountID, hulkCategoryID, "Rent", triggerDate, "Daily");
        Thread.sleep(timeToSleep);

        //Assert
        assertNotEquals(scheduling1, scheduling2);
        assertEquals(false, scheduling1.hashCode() == scheduling2.hashCode());
    }

    @Test
    @DisplayName("Test For Equals() and hashCode() - Different Category")
    public void equalsAndHashCode_DifferentCategory() throws InterruptedException {
        //Day Unit 200ms
        //2s = 10 Transactions

        //Arrange Account Nr.1


        String debAccountDenomination = "EmployerSA";
        PersonID hulkID = PersonID.createPersonID("hulk@marvel.com");
        AccountID hulkAccountID = AccountID.createAccountID(debAccountDenomination, hulkID);


        //Arrange Account Nr.2


        String debAccountDenominationA = "Allowance Money";
        PersonID wolverineID = PersonID.createPersonID("wolverine@marvel.com");
        AccountID wolverineAccountID = AccountID.createAccountID(debAccountDenomination, wolverineID);

        //Arrange Category
        CategoryID hulkCategoryID = CategoryID.createCategoryID("Allowance", hulkID);


        //Arrange Category Nr.2
        CategoryID categoryRent = CategoryID.createCategoryID("Rent", hulkID);

        //Arrange Triggers/Timers
        LocalDate triggerDate = LocalDate.now();
        long timeToSleep = 2100;

        //Arrange Ledger
        Ledger ledger = Ledger.createLedger();

        //Arrange ThreadPool
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);


        //Act
        Scheduling scheduling1 = Scheduling.createScheduling(executor, ledger, "Credit", 50.0, hulkAccountID, wolverineAccountID, hulkCategoryID, "Allowance", triggerDate, "Daily");
        Scheduling scheduling2 = Scheduling.createScheduling(executor, ledger, "Credit", 50.0, hulkAccountID, wolverineAccountID, categoryRent, "Allowance", triggerDate, "Daily");
        Thread.sleep(timeToSleep);

        //Assert
        assertNotEquals(scheduling1, scheduling2);
        assertEquals(false, scheduling1.hashCode() == scheduling2.hashCode());
    }

    @Test
    @DisplayName("Test For Equals() and hashCode() - Different Trigger")
    public void equalsAndHashCode_DifferentTrigger() throws InterruptedException {
        //Day Unit 200ms
        //2s = 10 Transactions
        //Arrange Account Nr.1


        String debAccountDenomination = "EmployerSA";
        PersonID hulkID = PersonID.createPersonID("hulk@marvel.com");
        AccountID hulkAccountID = AccountID.createAccountID(debAccountDenomination, hulkID);


        //Arrange Account Nr.2


        String debAccountDenominationA = "Allowance Money";
        PersonID wolverineID = PersonID.createPersonID("wolverine@marvel.com");
        AccountID wolverineAccountID = AccountID.createAccountID(debAccountDenomination, wolverineID);

        //Arrange Category
        CategoryID hulkCategoryID = CategoryID.createCategoryID("Allowance", hulkID);


        //Arrange Triggers/Timers
        LocalDate triggerDate = LocalDate.now();
        long timeToSleep = 2100;

        //Arrange Ledger
        Ledger ledger = Ledger.createLedger();

        //Arrange ThreadPool
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);


        //Act
        Scheduling scheduling1 = Scheduling.createScheduling(executor, ledger, "Credit", 50.0, hulkAccountID, wolverineAccountID, hulkCategoryID, "Allowance", triggerDate, "Daily");
        Scheduling scheduling2 = Scheduling.createScheduling(executor, ledger, "Credit", 50.0, hulkAccountID, wolverineAccountID, hulkCategoryID, "Allowance", triggerDate.plusDays(1), "Daily");
        Thread.sleep(timeToSleep);

        //Assert
        assertNotEquals(scheduling1, scheduling2);
        assertEquals(false, scheduling1.hashCode() == scheduling2.hashCode());
    }

    @Test
    @DisplayName("Test For Equals() and hashCode() - Different Periodicity")
    public void equalsAndHashCode_DifferentPeriodicity() throws InterruptedException {
        //Day Unit 200ms
        //2s = 10 Transactions

        //Arrange Account Nr.1


        String debAccountDenomination = "EmployerSA";
        PersonID hulkID = PersonID.createPersonID("hulk@marvel.com");
        AccountID hulkAccountID = AccountID.createAccountID(debAccountDenomination, hulkID);


        //Arrange Account Nr.2


        String debAccountDenominationA = "Allowance Money";
        PersonID wolverineID = PersonID.createPersonID("wolverine@marvel.com");
        AccountID wolverineAccountID = AccountID.createAccountID(debAccountDenomination, wolverineID);

        //Arrange Category
        CategoryID hulkCategoryID = CategoryID.createCategoryID("Allowance", hulkID);


        //Arrange Triggers/Timers
        LocalDate triggerDate = LocalDate.now();
        long timeToSleep = 2100;

        //Arrange Ledger
        Ledger ledger = Ledger.createLedger();

        //Arrange ThreadPool
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);


        //Act
        Scheduling scheduling1 = Scheduling.createScheduling(executor, ledger, "Credit", 50.0, hulkAccountID, wolverineAccountID, hulkCategoryID, "Allowance", triggerDate, "Daily");
        Scheduling scheduling2 = Scheduling.createScheduling(executor, ledger, "Credit", 50.0, hulkAccountID, wolverineAccountID, hulkCategoryID, "Allowance", triggerDate, "Weekly");
        Thread.sleep(timeToSleep);

        //Assert
        assertNotEquals(scheduling1, scheduling2);
        assertEquals(false, scheduling1.hashCode() == scheduling2.hashCode());
    }

    @Test
    @DisplayName("Test For Equals() and hashCode() - Same Object")
    public void equalsAndHashCode_SameObject() throws InterruptedException {
        //Day Unit 200ms
        //2s = 10 Transactions

        //Arrange Account Nr.1


        String debAccountDenomination = "EmployerSA";
        PersonID hulkID = PersonID.createPersonID("hulk@marvel.com");
        AccountID hulkAccountID = AccountID.createAccountID(debAccountDenomination, hulkID);


        //Arrange Account Nr.2


        String debAccountDenominationA = "Allowance Money";
        PersonID wolverineID = PersonID.createPersonID("wolverine@marvel.com");
        AccountID wolverineAccountID = AccountID.createAccountID(debAccountDenomination, wolverineID);

        //Arrange Category
        CategoryID hulkCategoryID = CategoryID.createCategoryID("Allowance", hulkID);


        //Arrange Triggers/Timers
        LocalDate triggerDate = LocalDate.now();
        long timeToSleep = 2100;

        //Arrange Ledger
        Ledger ledger = Ledger.createLedger();

        //Arrange ThreadPool
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);


        //Act
        Scheduling scheduling = Scheduling.createScheduling(executor, ledger, "Credit", 50.0, hulkAccountID, wolverineAccountID, hulkCategoryID, "Allowance", triggerDate, "Daily");
        Thread.sleep(timeToSleep);

        boolean result = scheduling.equals(scheduling);

        //Assert
        assertEquals(true, result);
        assertEquals(true, scheduling.hashCode() == scheduling.hashCode());
    }

    @Test
    @DisplayName("Test For Equals() and hashCode() - Different Class")
    public void equalsAndHashCode_DifferentClass() throws InterruptedException {
        //Day Unit 200ms
        //2s = 10 Transactions

        //Arrange Account Nr.1


        String debAccountDenomination = "EmployerSA";
        PersonID hulkID = PersonID.createPersonID("hulk@marvel.com");
        AccountID hulkAccountID = AccountID.createAccountID(debAccountDenomination, hulkID);


        //Arrange Account Nr.2


        String debAccountDenominationA = "Allowance Money";
        PersonID wolverineID = PersonID.createPersonID("wolverine@marvel.com");
        AccountID wolverineAccountID = AccountID.createAccountID(debAccountDenomination, wolverineID);

        //Arrange Category
        CategoryID hulkCategoryID = CategoryID.createCategoryID("Allowance", hulkID);


        //Arrange Triggers/Timers
        LocalDate triggerDate = LocalDate.now();
        long timeToSleep = 2100;

        //Arrange Ledger
        Ledger ledger = Ledger.createLedger();

        //Arrange ThreadPool
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);


        //Act
        Scheduling scheduling = Scheduling.createScheduling(executor, ledger, "Credit", 50.0, hulkAccountID, wolverineAccountID, hulkCategoryID, "Allowance", triggerDate, "Daily");
        Thread.sleep(timeToSleep);

        boolean result = scheduling.equals(hulkCategoryID);

        //Assert
        assertEquals(false, result);
        assertEquals(false, scheduling.hashCode() == hulkCategoryID.hashCode());
    }
}