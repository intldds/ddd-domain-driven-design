package com.finance.project.infrastructureLayer.repositories;

import com.finance.project.domainLayer.domainEntities.aggregates.ledger.Ledger;
import com.finance.project.domainLayer.domainEntities.aggregates.scheduling.Scheduling;
import com.finance.project.domainLayer.domainEntities.vosShared.AccountID;
import com.finance.project.domainLayer.domainEntities.vosShared.CategoryID;
import com.finance.project.domainLayer.domainEntities.vosShared.PersonID;
import com.finance.project.domainLayer.domainEntities.vosShared.ScheduleID;
import com.finance.project.domainLayer.repositoriesInterfaces.IScheduleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static org.junit.jupiter.api.Assertions.*;


class ScheduleRepositoryTest {

    @Test
    @DisplayName("Test the constructor")
    void createScheduleRepository() {
        //Arrange

        // Act
        IScheduleRepository firstScheduleRepo = ScheduleRepository.createScheduleRepository();
        ArrayList<Scheduling> expectedScheduleRepo = new ArrayList<Scheduling>();
        //Assert
        assertEquals(expectedScheduleRepo, firstScheduleRepo.getSchedulings());
    }

    @Test
    @DisplayName("Test the find scheduling by schedule ID method - Happy case")
    void testFindSchedulingByScheduleID() {
        //Day Unit 200ms
        //Monday - Tuesday - Wednesday - Thursday - Friday - x - x - Monday - Tuesday - Wednesday = 8

        //Arrange Ledger
        Ledger ledger = Ledger.createLedger();

        //Arrange TransactionType
        String firstTransactionType = "Food";

        //Arrange Amount
        Double firstAmount = 20.2;

        //Arrange AccountID Nr.1

        String credAccountDenomination = "EmployerSA";
        PersonID hulkID = PersonID.createPersonID("hulk@marvel.com");
        AccountID hulkAccountID = AccountID.createAccountID(credAccountDenomination, hulkID);

        //Arrange AccountID Nr.2

        String debAccountDenomination = "Allowance Money";
        PersonID wolverineID = PersonID.createPersonID("wolverine@marvel.com");
        AccountID wolverineAccountID = AccountID.createAccountID(debAccountDenomination, wolverineID);

        //Arrange CategoryID
        CategoryID hulkCategoryID = CategoryID.createCategoryID("Allowance", hulkID);

        //Arrange Description
        String firstDescription = "groceries";

        //Arrange Periodicity
        String firstPeriodicity = "Working Days";

        //Arrange Triggers/Timers
        LocalDate triggerDate = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        long timeToSleep = (ChronoUnit.DAYS.between(LocalDate.now(), triggerDate) * 200) + 1900;

        //Arrange ThreadPool
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        //Act ScheduleRepository
        IScheduleRepository firstScheduleRepo = ScheduleRepository.createScheduleRepository();
        Scheduling firstScheduling = Scheduling.createScheduling(executor, ledger, firstTransactionType, firstAmount, hulkAccountID,
                wolverineAccountID, hulkCategoryID, firstDescription, triggerDate, firstPeriodicity);
        firstScheduleRepo.saveScheduling(firstScheduling);

        //Act expected
        ScheduleID expectedScheduleID = ScheduleID.createScheduleID(firstDescription, triggerDate, firstPeriodicity, firstTransactionType);
        Scheduling expectedScheduling = Scheduling.createScheduling(executor, ledger, firstTransactionType, firstAmount, hulkAccountID,
                wolverineAccountID, hulkCategoryID, firstDescription, triggerDate, firstPeriodicity);
//
        //Assert
        Assertions.assertEquals(expectedScheduling, firstScheduleRepo.findSchedulingByScheduleID(expectedScheduleID));

    }


    @Test
    @DisplayName("Test the save scheduling method - Happy case")
    void testSaveScheduling() {
        //Day Unit 200ms
        //Monday - Tuesday - Wednesday - Thursday - Friday - x - x - Monday - Tuesday - Wednesday = 8

        //Arrange Ledger
        Ledger ledger = Ledger.createLedger();

        //Arrange TransactionType
        String firstTransactionType = "Food";

        //Arrange Amount
        Double firstAmount = 20.2;

        //Arrange AccountID Nr.1

        String credAccountDenomination = "EmployerSA";
        PersonID hulkID = PersonID.createPersonID("hulk@marvel.com");
        AccountID hulkAccountID = AccountID.createAccountID(credAccountDenomination, hulkID);

        //Arrange AccountID Nr.2

        String debAccountDenomination = "Allowance Money";
        PersonID wolverineID = PersonID.createPersonID("wolverine@marvel.com");
        AccountID wolverineAccountID = AccountID.createAccountID(debAccountDenomination, wolverineID);

        //Arrange CategoryID
        CategoryID hulkCategoryID = CategoryID.createCategoryID("Allowance", hulkID);

        //Arrange Description
        String firstDescription = "groceries";

        //Arrange Periodicity
        String firstPeriodicity = "Working Days";

        //Arrange Triggers/Timers
        LocalDate triggerDate = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        long timeToSleep = (ChronoUnit.DAYS.between(LocalDate.now(), triggerDate) * 200) + 1900;

        //Arrange ThreadPool
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        //Act ScheduleRepository
        IScheduleRepository firstScheduleRepo = ScheduleRepository.createScheduleRepository();
        Scheduling firstScheduling = Scheduling.createScheduling(executor, ledger, firstTransactionType, firstAmount, hulkAccountID,
                wolverineAccountID, hulkCategoryID, firstDescription, triggerDate, firstPeriodicity);
        ScheduleID scheduleID = ScheduleID.createScheduleID(firstDescription, triggerDate, firstPeriodicity, firstTransactionType);

        boolean saveResult = firstScheduleRepo.saveScheduling(firstScheduling);


        //Act expected
        ScheduleID expectedScheduleID = ScheduleID.createScheduleID(firstDescription, triggerDate, firstPeriodicity, firstTransactionType);
        Scheduling expectedScheduling = Scheduling.createScheduling(executor, ledger, firstTransactionType, firstAmount, hulkAccountID,
                wolverineAccountID, hulkCategoryID, firstDescription, triggerDate, firstPeriodicity);


        //Assert
        assertEquals(true, saveResult);
        assertEquals(1, firstScheduleRepo.countSchedulings());
        assertEquals(true, firstScheduleRepo.checkIfScheduleIDExists(scheduleID));

    }

    @Test
    @DisplayName("Test the save scheduling method - SadCase")
    void testSaveSchedulingDuplicate() {
        //Day Unit 200ms
        //Monday - Tuesday - Wednesday - Thursday - Friday - x - x - Monday - Tuesday - Wednesday = 8

        //Arrange Ledger
        Ledger ledger = Ledger.createLedger();

        //Arrange TransactionType
        String firstTransactionType = "Food";

        //Arrange Amount
        Double firstAmount = 20.2;

        //Arrange AccountID Nr.1

        String credAccountDenomination = "EmployerSA";
        PersonID hulkID = PersonID.createPersonID("hulk@marvel.com");
        AccountID hulkAccountID = AccountID.createAccountID(credAccountDenomination, hulkID);

        //Arrange AccountID Nr.2

        String debAccountDenomination = "Allowance Money";
        PersonID wolverineID = PersonID.createPersonID("wolverine@marvel.com");
        AccountID wolverineAccountID = AccountID.createAccountID(debAccountDenomination, wolverineID);

        //Arrange CategoryID
        CategoryID hulkCategoryID = CategoryID.createCategoryID("Allowance", hulkID);

        //Arrange Description
        String firstDescription = "groceries";

        //Arrange Periodicity
        String firstPeriodicity = "Working Days";

        //Arrange Triggers/Timers
        LocalDate triggerDate = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        long timeToSleep = (ChronoUnit.DAYS.between(LocalDate.now(), triggerDate) * 200) + 1900;

        //Arrange ThreadPool
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        //Act ScheduleRepository
        IScheduleRepository firstScheduleRepo = ScheduleRepository.createScheduleRepository();
        Scheduling firstScheduling = Scheduling.createScheduling(executor, ledger, firstTransactionType, firstAmount, hulkAccountID,
                wolverineAccountID, hulkCategoryID, firstDescription, triggerDate, firstPeriodicity);
        ScheduleID scheduleID = ScheduleID.createScheduleID(firstDescription, triggerDate, firstPeriodicity, firstTransactionType);

        firstScheduleRepo.saveScheduling(firstScheduling);

        boolean saveResultDuplicate = firstScheduleRepo.saveScheduling(firstScheduling);


        //Assert
        assertEquals(false, saveResultDuplicate);
        assertEquals(1, firstScheduleRepo.countSchedulings());

    }


    @Test
    @DisplayName("Test the save scheduling method - two schedulings added")
    void testSave2Scheduling() {
        //Day Unit 200ms
        //Monday - Tuesday - Wednesday - Thursday - Friday - x - x - Monday - Tuesday - Wednesday = 8

        //Arrange Ledger
        Ledger ledger = Ledger.createLedger();

        //Arrange TransactionType
        String firstTransactionType = "Food";
        String secondTransactionType = "Car";

        //Arrange Amount
        Double firstAmount = 20.2;
        Double secondAmount = 50.7;

        //Arrange AccountID Nr.1

        String credAccountDenomination = "EmployerSA";
        PersonID hulkID = PersonID.createPersonID("hulk@marvel.com");
        AccountID hulkAccountID = AccountID.createAccountID(credAccountDenomination, hulkID);

        //Arrange AccountID Nr.2

        String debAccountDenomination = "Allowance Money";
        PersonID wolverineID = PersonID.createPersonID("wolverine@marvel.com");
        AccountID wolverineAccountID = AccountID.createAccountID(debAccountDenomination, wolverineID);

        //Arrange CategoryID
        CategoryID hulkCategoryID = CategoryID.createCategoryID("Allowance", hulkID);

        //Arrange Description
        String firstDescription = "groceries";
        String secondDescription = "games";

        //Arrange Periodicity
        String firstPeriodicity = "Working Days";
        String secondPeriodicity = "Every two days";

        //Arrange Triggers/Timers
        LocalDate triggerDate = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        LocalDate secondTriggerDate = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.TUESDAY));
        long timeToSleep = (ChronoUnit.DAYS.between(LocalDate.now(), triggerDate) * 200) + 1900;

        //Arrange ThreadPool
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        //Act ScheduleRepository
        IScheduleRepository firstScheduleRepo = ScheduleRepository.createScheduleRepository();

        Scheduling firstScheduling = Scheduling.createScheduling(executor, ledger, firstTransactionType, firstAmount, hulkAccountID,
                wolverineAccountID, hulkCategoryID, firstDescription, triggerDate, firstPeriodicity);

        Scheduling theSameScheduling = Scheduling.createScheduling(executor, ledger, secondTransactionType, secondAmount, hulkAccountID,
                wolverineAccountID, hulkCategoryID, secondDescription, secondTriggerDate, secondPeriodicity);

        firstScheduleRepo.saveScheduling(firstScheduling);
        firstScheduleRepo.saveScheduling(theSameScheduling);


        //Act expected
        ScheduleID expectedScheduleID = ScheduleID.createScheduleID(firstDescription, triggerDate, firstPeriodicity, firstTransactionType);
        Scheduling expectedScheduling = Scheduling.createScheduling(executor, ledger, firstTransactionType, firstAmount, hulkAccountID,
                wolverineAccountID, hulkCategoryID, firstDescription, triggerDate, firstPeriodicity);

        Scheduling secondExpectedScheduling = Scheduling.createScheduling(executor, ledger, secondTransactionType, secondAmount, hulkAccountID,
                wolverineAccountID, hulkCategoryID, secondDescription, secondTriggerDate, secondPeriodicity);

        IScheduleRepository expectedScheduleRepo = ScheduleRepository.createScheduleRepository();

        expectedScheduleRepo.saveScheduling(expectedScheduling);
        expectedScheduleRepo.saveScheduling(secondExpectedScheduling);
//
        //Assert
        assertEquals(expectedScheduleRepo, firstScheduleRepo);
        assertEquals(2, firstScheduleRepo.countSchedulings());

    }

    @Test
    @DisplayName("Test the save scheduling method - Added only one schedule | doesn't accepts duplicate schedulings")
    void testSaveSchedulingWontAddDuplicates() {
        //Day Unit 200ms
        //Monday - Tuesday - Wednesday - Thursday - Friday - x - x - Monday - Tuesday - Wednesday = 8

        //Arrange Ledger
        Ledger ledger = Ledger.createLedger();

        //Arrange TransactionType
        String firstTransactionType = "Food";

        //Arrange Amount
        Double firstAmount = 20.2;

        //Arrange AccountID Nr.1

        String credAccountDenomination = "EmployerSA";
        PersonID hulkID = PersonID.createPersonID("hulk@marvel.com");
        AccountID hulkAccountID = AccountID.createAccountID(credAccountDenomination, hulkID);

        //Arrange AccountID Nr.2

        String debAccountDenomination = "Allowance Money";
        PersonID wolverineID = PersonID.createPersonID("wolverine@marvel.com");
        AccountID wolverineAccountID = AccountID.createAccountID(debAccountDenomination, wolverineID);

        //Arrange CategoryID
        CategoryID hulkCategoryID = CategoryID.createCategoryID("Allowance", hulkID);

        //Arrange Description
        String firstDescription = "groceries";

        //Arrange Periodicity
        String firstPeriodicity = "Working Days";

        //Arrange Triggers/Timers
        LocalDate triggerDate = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        long timeToSleep = (ChronoUnit.DAYS.between(LocalDate.now(), triggerDate) * 200) + 1900;

        //Arrange ThreadPool
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        //Act ScheduleRepository
        IScheduleRepository firstScheduleRepo = ScheduleRepository.createScheduleRepository();
        Scheduling firstScheduling = Scheduling.createScheduling(executor, ledger, firstTransactionType, firstAmount, hulkAccountID,
                wolverineAccountID, hulkCategoryID, firstDescription, triggerDate, firstPeriodicity);
        Scheduling theSameScheduling = Scheduling.createScheduling(executor, ledger, firstTransactionType, firstAmount, hulkAccountID,
                wolverineAccountID, hulkCategoryID, firstDescription, triggerDate, firstPeriodicity);
        firstScheduleRepo.saveScheduling(firstScheduling);
        firstScheduleRepo.saveScheduling(theSameScheduling);

        //Act expected
        ScheduleID expectedScheduleID = ScheduleID.createScheduleID(firstDescription, triggerDate, firstPeriodicity, firstTransactionType);
        Scheduling expectedScheduling = Scheduling.createScheduling(executor, ledger, firstTransactionType, firstAmount, hulkAccountID,
                wolverineAccountID, hulkCategoryID, firstDescription, triggerDate, firstPeriodicity);
//
        //Assert
        Assertions.assertEquals(expectedScheduling, firstScheduleRepo.findSchedulingByScheduleID(expectedScheduleID));
        assertEquals(1, firstScheduleRepo.countSchedulings());

    }

    @Test
    @DisplayName("Test the find scheduling by schedule ID method - it won't return a scheduling, it'll return null")
    void testFindSchedulingByScheduleID_schedulingInexistent() {
        //Day Unit 200ms
        //Monday - Tuesday - Wednesday - Thursday - Friday - x - x - Monday - Tuesday - Wednesday = 8

        //Arrange Ledger
        Ledger ledger = Ledger.createLedger();

        //Arrange TransactionType
        String firstTransactionType = "Food";

        //Arrange Amount
        Double firstAmount = 20.2;

        //Arrange AccountID Nr.1

        String credAccountDenomination = "EmployerSA";
        PersonID hulkID = PersonID.createPersonID("hulk@marvel.com");
        AccountID hulkAccountID = AccountID.createAccountID(credAccountDenomination, hulkID);

        //Arrange AccountID Nr.2

        String debAccountDenomination = "Allowance Money";
        PersonID wolverineID = PersonID.createPersonID("wolverine@marvel.com");
        AccountID wolverineAccountID = AccountID.createAccountID(debAccountDenomination, wolverineID);

        //Arrange CategoryID
        CategoryID hulkCategoryID = CategoryID.createCategoryID("Allowance", hulkID);

        //Arrange Description
        String firstDescription = "groceries";

        String secondDescription = "cinema";

        //Arrange Periodicity
        String firstPeriodicity = "Working Days";

        //Arrange Triggers/Timers
        LocalDate triggerDate = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        long timeToSleep = (ChronoUnit.DAYS.between(LocalDate.now(), triggerDate) * 200) + 1900;

        //Arrange ThreadPool
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        //Act ScheduleRepository
        IScheduleRepository firstScheduleRepo = ScheduleRepository.createScheduleRepository();
        Scheduling firstScheduling = Scheduling.createScheduling(executor, ledger, firstTransactionType, firstAmount, hulkAccountID,
                wolverineAccountID, hulkCategoryID, firstDescription, triggerDate, firstPeriodicity);
        firstScheduleRepo.saveScheduling(firstScheduling);

        //Act expected
        ScheduleID expectedScheduleID = ScheduleID.createScheduleID(secondDescription, triggerDate, firstPeriodicity, firstTransactionType);
        Scheduling expectedScheduling = Scheduling.createScheduling(executor, ledger, firstTransactionType, firstAmount, hulkAccountID,
                wolverineAccountID, hulkCategoryID, firstDescription, triggerDate, firstPeriodicity);
//
        //Assert
        Assertions.assertNotEquals(expectedScheduling, firstScheduleRepo.findSchedulingByScheduleID(expectedScheduleID));

    }


    @Test
    @DisplayName("Test the count schedulings method - Happy case")
    void testCountSchedulings() {
        //Day Unit 200ms
        //Monday - Tuesday - Wednesday - Thursday - Friday - x - x - Monday - Tuesday - Wednesday = 8

        //Arrange Ledger
        Ledger ledger = Ledger.createLedger();

        //Arrange TransactionType
        String firstTransactionType = "Food";

        //Arrange Amount
        Double firstAmount = 20.2;

        //Arrange AccountID Nr.1

        String credAccountDenomination = "EmployerSA";
        PersonID hulkID = PersonID.createPersonID("hulk@marvel.com");
        AccountID hulkAccountID = AccountID.createAccountID(credAccountDenomination, hulkID);

        //Arrange AccountID Nr.2

        String debAccountDenomination = "Allowance Money";
        PersonID wolverineID = PersonID.createPersonID("wolverine@marvel.com");
        AccountID wolverineAccountID = AccountID.createAccountID(debAccountDenomination, wolverineID);

        //Arrange CategoryID
        CategoryID hulkCategoryID = CategoryID.createCategoryID("Allowance", hulkID);

        //Arrange Description
        String firstDescription = "groceries";

        //Arrange Periodicity
        String firstPeriodicity = "Working Days";

        //Arrange Triggers/Timers
        LocalDate triggerDate = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        long timeToSleep = (ChronoUnit.DAYS.between(LocalDate.now(), triggerDate) * 200) + 1900;

        //Arrange ThreadPool
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        //Act ScheduleRepository
        IScheduleRepository firstScheduleRepo = ScheduleRepository.createScheduleRepository();
        Scheduling firstScheduling = Scheduling.createScheduling(executor, ledger, firstTransactionType, firstAmount, hulkAccountID,
                wolverineAccountID, hulkCategoryID, firstDescription, triggerDate, firstPeriodicity);
        Scheduling secondScheduling = Scheduling.createScheduling(executor, ledger, firstTransactionType, firstAmount, hulkAccountID,
                wolverineAccountID, hulkCategoryID, firstDescription, triggerDate, firstPeriodicity);

        firstScheduleRepo.saveScheduling(firstScheduling);
        firstScheduleRepo.saveScheduling(secondScheduling);

        //Act expected
        List<Scheduling> expectedSchedulings = new ArrayList<Scheduling>();
        expectedSchedulings.add(firstScheduling);

        boolean checkSize = expectedSchedulings.equals(firstScheduleRepo.getSchedulings());
        int expectedSize = 1;

        //Assert
        assertEquals(expectedSize, firstScheduleRepo.countSchedulings());
        assertTrue(checkSize);
        assertEquals(expectedSchedulings, firstScheduleRepo.getSchedulings());
    }


    @Test
    @DisplayName("Test the check if scheduleID exists method - Happy case")
    void testCheckIfScheduleIDExists() {
        //Day Unit 200ms
        //Monday - Tuesday - Wednesday - Thursday - Friday - x - x - Monday - Tuesday - Wednesday = 8

        //Arrange Ledger
        Ledger ledger = Ledger.createLedger();

        //Arrange TransactionType
        String firstTransactionType = "Food";

        //Arrange Amount
        Double firstAmount = 20.2;

        //Arrange AccountID Nr.1

        String credAccountDenomination = "EmployerSA";
        PersonID hulkID = PersonID.createPersonID("hulk@marvel.com");
        AccountID hulkAccountID = AccountID.createAccountID(credAccountDenomination, hulkID);

        //Arrange AccountID Nr.2

        String debAccountDenomination = "Allowance Money";
        PersonID wolverineID = PersonID.createPersonID("wolverine@marvel.com");
        AccountID wolverineAccountID = AccountID.createAccountID(debAccountDenomination, wolverineID);

        //Arrange CategoryID
        CategoryID hulkCategoryID = CategoryID.createCategoryID("Allowance", hulkID);

        //Arrange Description
        String firstDescription = "groceries";

        //Arrange Periodicity
        String firstPeriodicity = "Working Days";

        //Arrange Triggers/Timers
        LocalDate triggerDate = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        long timeToSleep = (ChronoUnit.DAYS.between(LocalDate.now(), triggerDate) * 200) + 1900;

        //Arrange ThreadPool
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        //Act ScheduleRepository
        IScheduleRepository firstScheduleRepo = ScheduleRepository.createScheduleRepository();
        Scheduling firstScheduling = Scheduling.createScheduling(executor, ledger, firstTransactionType, firstAmount, hulkAccountID,
                wolverineAccountID, hulkCategoryID, firstDescription, triggerDate, firstPeriodicity);
        firstScheduleRepo.saveScheduling(firstScheduling);
        ScheduleID firstScheduleID = ScheduleID.createScheduleID(firstDescription, triggerDate, firstPeriodicity, firstTransactionType);

        //Act expectedScheduleID
        ScheduleID someScheduleID = ScheduleID.createScheduleID(firstDescription, triggerDate, firstPeriodicity, firstTransactionType);

        List<Scheduling> expectedSchedulings = new ArrayList<Scheduling>();
        expectedSchedulings.add(firstScheduling);

        boolean result = firstScheduleRepo.checkIfScheduleIDExists(firstScheduleID);

        //Assert
        assertEquals(true, result);
        /*assertEquals(expectedSchedulings, firstScheduleRepo.getSchedulings());
        assertTrue(firstScheduleRepo.checkIfScheduleIDExists(someScheduleID));

         */
    }

    @Test
    @DisplayName("Test the check if scheduleID exists method - Happy case")
    void testCheckIfScheduleIDExists_inexistentScheduleID() {
        //Day Unit 200ms
        //Monday - Tuesday - Wednesday - Thursday - Friday - x - x - Monday - Tuesday - Wednesday = 8

        //Arrange Ledger
        Ledger ledger = Ledger.createLedger();

        //Arrange TransactionType
        String firstTransactionType = "Food";

        //Arrange Amount
        Double firstAmount = 20.2;

        //Arrange AccountID Nr.1

        String credAccountDenomination = "EmployerSA";
        PersonID hulkID = PersonID.createPersonID("hulk@marvel.com");
        AccountID hulkAccountID = AccountID.createAccountID(credAccountDenomination, hulkID);

        //Arrange AccountID Nr.2

        String debAccountDenomination = "Allowance Money";
        PersonID wolverineID = PersonID.createPersonID("wolverine@marvel.com");
        AccountID wolverineAccountID = AccountID.createAccountID(debAccountDenomination, wolverineID);

        //Arrange CategoryID
        CategoryID hulkCategoryID = CategoryID.createCategoryID("Allowance", hulkID);

        //Arrange Description
        String firstDescription = "groceries";

        String secondDescription = "cinema";

        //Arrange Periodicity
        String firstPeriodicity = "Working Days";

        //Arrange Triggers/Timers
        LocalDate triggerDate = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        long timeToSleep = (ChronoUnit.DAYS.between(LocalDate.now(), triggerDate) * 200) + 1900;

        //Arrange ThreadPool
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        //Act ScheduleRepository
        IScheduleRepository firstScheduleRepo = ScheduleRepository.createScheduleRepository();
        Scheduling firstScheduling = Scheduling.createScheduling(executor, ledger, firstTransactionType, firstAmount, hulkAccountID,
                wolverineAccountID, hulkCategoryID, firstDescription, triggerDate, firstPeriodicity);
        firstScheduleRepo.saveScheduling(firstScheduling);

        //Act expectedScheduleID
        ScheduleID someScheduleID = ScheduleID.createScheduleID(secondDescription, triggerDate, firstPeriodicity, firstTransactionType);

        List<Scheduling> expectedSchedulings = new ArrayList<Scheduling>();
        expectedSchedulings.add(firstScheduling);

        //Assert
        assertEquals(expectedSchedulings, firstScheduleRepo.getSchedulings());
        assertFalse(firstScheduleRepo.checkIfScheduleIDExists(someScheduleID));
    }

    @Test
    @DisplayName("Test the equals method - Happy case")
    void testEquals() {
        //Day Unit 200ms
        //Monday - Tuesday - Wednesday - Thursday - Friday - x - x - Monday - Tuesday - Wednesday = 8

        //Arrange Ledger
        Ledger ledger = Ledger.createLedger();

        //Arrange TransactionType
        String firstTransactionType = "Food";

        //Arrange Amount
        Double firstAmount = 20.2;

        //Arrange AccountID Nr.1

        String credAccountDenomination = "EmployerSA";
        PersonID hulkID = PersonID.createPersonID("hulk@marvel.com");
        AccountID hulkAccountID = AccountID.createAccountID(credAccountDenomination, hulkID);

        //Arrange AccountID Nr.2

        String debAccountDenomination = "Allowance Money";
        PersonID wolverineID = PersonID.createPersonID("wolverine@marvel.com");
        AccountID wolverineAccountID = AccountID.createAccountID(debAccountDenomination, wolverineID);

        //Arrange CategoryID
        CategoryID hulkCategoryID = CategoryID.createCategoryID("Allowance", hulkID);

        //Arrange Description
        String firstDescription = "groceries";

        String secondDescription = "cinema";

        //Arrange Periodicity
        String firstPeriodicity = "Working Days";

        //Arrange Triggers/Timers
        LocalDate triggerDate = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        long timeToSleep = (ChronoUnit.DAYS.between(LocalDate.now(), triggerDate) * 200) + 1900;

        //Arrange ThreadPool
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        // Act
        IScheduleRepository firstScheduleRepo = ScheduleRepository.createScheduleRepository();
        Scheduling firstScheduling = Scheduling.createScheduling(executor, ledger, firstTransactionType, firstAmount, hulkAccountID,
                wolverineAccountID, hulkCategoryID, firstDescription, triggerDate, firstPeriodicity);
        IScheduleRepository expectedScheduleRepo = ScheduleRepository.createScheduleRepository();
        Scheduling expectedScheduling = Scheduling.createScheduling(executor, ledger, firstTransactionType, firstAmount, hulkAccountID,
                wolverineAccountID, hulkCategoryID, firstDescription, triggerDate, firstPeriodicity);

        firstScheduleRepo.saveScheduling(firstScheduling);
        expectedScheduleRepo.saveScheduling(expectedScheduling);
        //Assert
        assertEquals(expectedScheduleRepo, firstScheduleRepo);
    }

    @Test
    @DisplayName("Test the equals method - Same object")
    void testEqualsSameObject() {
        //Day Unit 200ms
        //Monday - Tuesday - Wednesday - Thursday - Friday - x - x - Monday - Tuesday - Wednesday = 8

        //Arrange Ledger
        Ledger ledger = Ledger.createLedger();

        //Arrange TransactionType
        String firstTransactionType = "Food";

        //Arrange Amount
        Double firstAmount = 20.2;

        //Arrange AccountID Nr.1

        String credAccountDenomination = "EmployerSA";
        PersonID hulkID = PersonID.createPersonID("hulk@marvel.com");
        AccountID hulkAccountID = AccountID.createAccountID(credAccountDenomination, hulkID);

        //Arrange AccountID Nr.2

        String debAccountDenomination = "Allowance Money";
        PersonID wolverineID = PersonID.createPersonID("wolverine@marvel.com");
        AccountID wolverineAccountID = AccountID.createAccountID(debAccountDenomination, wolverineID);

        //Arrange CategoryID
        CategoryID hulkCategoryID = CategoryID.createCategoryID("Allowance", hulkID);

        //Arrange Description
        String firstDescription = "groceries";

        String secondDescription = "cinema";

        //Arrange Periodicity
        String firstPeriodicity = "Working Days";

        //Arrange Triggers/Timers
        LocalDate triggerDate = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        long timeToSleep = (ChronoUnit.DAYS.between(LocalDate.now(), triggerDate) * 200) + 1900;

        //Arrange ThreadPool
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        // Act
        IScheduleRepository firstScheduleRepo = ScheduleRepository.createScheduleRepository();
        Scheduling firstScheduling = Scheduling.createScheduling(executor, ledger, firstTransactionType, firstAmount, hulkAccountID,
                wolverineAccountID, hulkCategoryID, firstDescription, triggerDate, firstPeriodicity);

        firstScheduleRepo.saveScheduling(firstScheduling);

        //Assert
        assertEquals(firstScheduleRepo, firstScheduleRepo);
    }


    @Test
    @DisplayName("Test the equals method - Not Instance OF")
    void testEqualsNotInstanceOf() {
        //Day Unit 200ms
        //Monday - Tuesday - Wednesday - Thursday - Friday - x - x - Monday - Tuesday - Wednesday = 8

        //Arrange Ledger
        Ledger ledger = Ledger.createLedger();

        //Arrange TransactionType
        String firstTransactionType = "Food";

        //Arrange Amount
        Double firstAmount = 20.2;

        //Arrange AccountID Nr.1

        String credAccountDenomination = "EmployerSA";
        PersonID hulkID = PersonID.createPersonID("hulk@marvel.com");
        AccountID hulkAccountID = AccountID.createAccountID(credAccountDenomination, hulkID);

        //Arrange AccountID Nr.2

        String debAccountDenomination = "Allowance Money";
        PersonID wolverineID = PersonID.createPersonID("wolverine@marvel.com");
        AccountID wolverineAccountID = AccountID.createAccountID(debAccountDenomination, wolverineID);

        //Arrange CategoryID
        CategoryID hulkCategoryID = CategoryID.createCategoryID("Allowance", hulkID);

        //Arrange Description
        String firstDescription = "groceries";

        String secondDescription = "cinema";

        //Arrange Periodicity
        String firstPeriodicity = "Working Days";

        //Arrange Triggers/Timers
        LocalDate triggerDate = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        long timeToSleep = (ChronoUnit.DAYS.between(LocalDate.now(), triggerDate) * 200) + 1900;

        //Arrange ThreadPool
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        // Act
        IScheduleRepository firstScheduleRepo = ScheduleRepository.createScheduleRepository();
        Scheduling firstScheduling = Scheduling.createScheduling(executor, ledger, firstTransactionType, firstAmount, hulkAccountID,
                wolverineAccountID, hulkCategoryID, firstDescription, triggerDate, firstPeriodicity);

        firstScheduleRepo.saveScheduling(firstScheduling);

        boolean result = firstScheduleRepo.equals(firstPeriodicity);

        //Assert
        assertFalse(result);
    }

}
