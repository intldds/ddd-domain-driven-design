package com.finance.project.domainLayer.domainEntities.vosShared;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Ala Matos
 */
class ScheduleIDTest {
    @Test
    @DisplayName("Test the constructor")
    public void testTheConstructor () {
        //Arrange
        String description = "food";
        LocalDate triggerDate = LocalDate.of(2020,3,16);
        String periodicity = "2";
        String transactionType = "Credit";
        //Act
        ScheduleID newScheduling =ScheduleID.createScheduleID(description, triggerDate, periodicity, transactionType);
        //Assert
        assertEquals(description, newScheduling.getDescription().getDescription());
        Assertions.assertEquals(triggerDate, newScheduling.getTriggerDate().getTriggerDate());
        Assertions.assertEquals(periodicity, newScheduling.getPeriodicity().getPeriodicity());
        assertEquals(transactionType, newScheduling.getTransactionType().getTransactionType());

    }
    @Test
    @DisplayName("Test the constructor - Description exception")
    public void testTheConstructorDescriptionExceptionEmpty () {
        //Arrange
        String description = "";
        LocalDate triggerDate = LocalDate.of(2020,3,16);
        String periodicity = "2";
        String transactionType = "Credit";
        String ExceptionMessage = "The schedule wasn't created due to the fact that the description is invalid";
        //Act
        Throwable thrown = assertThrows(IllegalArgumentException.class, () ->ScheduleID.createScheduleID(description, triggerDate, periodicity, transactionType));

        //Assert
        assertEquals(ExceptionMessage, thrown.getMessage());
    }

    @Test
    @DisplayName("Test the constructor - Description exception")
    public void testTheConstructorDescriptionException_Null () {
        //Arrange
        String description = null;
        LocalDate triggerDate = LocalDate.of(2020,3,16);
        String periodicity = "2";
        String transactionType = "Credit";
        String ExceptionMessage = "The schedule wasn't created due to the fact that the description is invalid";
        //Act
        Throwable thrown = assertThrows(IllegalArgumentException.class, () ->ScheduleID.createScheduleID(description, triggerDate, periodicity, transactionType));

        //Assert
        assertEquals(ExceptionMessage, thrown.getMessage());
    }

    @Test
    @DisplayName("Test the constructor - Triggerdate exception")
    public void testTheConstructorTriggerDateExceptionNull () {
        //Arrange
        String description = "food";
        LocalDate triggerDate = null;
        String periodicity = "2";
        String transactionType = "Credit";
        String ExceptionMessage = "The schedule wasn't created due to the fact that the triggerdate is invalid";
        //Act
        Throwable thrown = assertThrows(IllegalArgumentException.class, () ->ScheduleID.createScheduleID(description, triggerDate, periodicity, transactionType));

        //Assert
        assertEquals(ExceptionMessage, thrown.getMessage());
    }


    @Test
    @DisplayName("Test the constructor - Periodicity exception")
    public void testTheConstructorPeriodicityExceptionNull () {
        //Arrange
        String description = "food";
        LocalDate triggerDate = LocalDate.of(2020,3,16);
        String periodicity = null;
        String transactionType = "Credit";
        String ExceptionMessage = "The schedule wasn't created due to the fact that the periodicity is invalid";
        //Act
        Throwable thrown = assertThrows(IllegalArgumentException.class, () ->ScheduleID.createScheduleID(description, triggerDate, periodicity, transactionType));

        //Assert
        assertEquals(ExceptionMessage, thrown.getMessage());
    }

    @Test
    @DisplayName("Test the constructor - Periodicity exception")
    public void testTheConstructorPeriodicityExceptionEmpty () {
        //Arrange
        String description = "food";
        LocalDate triggerDate = LocalDate.of(2020,3,16);
        String periodicity = "";
        String transactionType = "Credit";
        String ExceptionMessage = "The schedule wasn't created due to the fact that the periodicity is invalid";
        //Act
        Throwable thrown = assertThrows(IllegalArgumentException.class, () ->ScheduleID.createScheduleID(description, triggerDate, periodicity, transactionType));

        //Assert
        assertEquals(ExceptionMessage, thrown.getMessage());
    }

    @Test
    @DisplayName("Test the constructor - TransactionType exception")
    public void testTheConstructorTransactionTypeException () {
        //Arrange
        String description = "food";
        LocalDate triggerDate = LocalDate.of(2020,3,16);
        String periodicity = "2";
        String transactionType = null;
        String ExceptionMessage = "The schedule wasn't created due to the fact that the transactionType is invalid";
        //Act
        Throwable thrown = assertThrows(IllegalArgumentException.class, () ->ScheduleID.createScheduleID(description, triggerDate, periodicity, transactionType));

        //Assert
        assertEquals(ExceptionMessage, thrown.getMessage());
    }

    @Test
    @DisplayName("Test the constructor - TransactionType exception")
    public void testTheConstructorTransactionTypeExceptionEmpty () {
        //Arrange
        String description = "food";
        LocalDate triggerDate = LocalDate.of(2020,3,16);
        String periodicity = "2";
        String transactionType = "";
        String ExceptionMessage = "The schedule wasn't created due to the fact that the transactionType is invalid";
        //Act
        Throwable thrown = assertThrows(IllegalArgumentException.class, () ->ScheduleID.createScheduleID(description, triggerDate, periodicity, transactionType));

        //Assert
        assertEquals(ExceptionMessage, thrown.getMessage());
    }


    @Test
    @DisplayName("Test the equals method")
    public void testEqualsMethod() {
        //Arrange
        String hulkDescription = "food";
        LocalDate hulkTriggerDate = LocalDate.of(2020,3,16);
        String hulkPeriodicity = "2";
        String hulkTransactionType = "Credit";

        String wolverineDescription = "food";
        LocalDate wolverineTriggerDate = LocalDate.of(2020,3,16);
        String wolverinePeriodicity = "2";
        String wolverineTransactionType = "Credit";
        // Act
        ScheduleID hulkSchedule= ScheduleID.createScheduleID(hulkDescription, hulkTriggerDate, hulkPeriodicity,
                hulkTransactionType);

        ScheduleID wolverineSchedule=ScheduleID.createScheduleID(wolverineDescription, wolverineTriggerDate, wolverinePeriodicity,
                wolverineTransactionType);
        //Assert
        assertEquals(hulkSchedule, wolverineSchedule);
    }

    @Test
    @DisplayName("Test the equals method - 2 different objects")
    public void testEqualsMethodDifferentObjects() {
        //Arrange
        String hulkDescription = "food";
        LocalDate hulkTriggerDate = LocalDate.of(2020,3,16);
        String hulkPeriodicity = "2";
        String hulkTransactionType = "Credit";

        String wolverineDescription = "car";
        LocalDate wolverineTriggerDate = LocalDate.of(2021,3,16);
        String wolverinePeriodicity = "1";
        String wolverineTransactionType = "Debit";
        // Act
        ScheduleID hulkSchedule= ScheduleID.createScheduleID(hulkDescription, hulkTriggerDate, hulkPeriodicity,
                hulkTransactionType);

        ScheduleID wolverineSchedule=ScheduleID.createScheduleID(wolverineDescription, wolverineTriggerDate, wolverinePeriodicity,
                wolverineTransactionType);
        //Assert
        assertNotEquals(hulkSchedule, wolverineSchedule);
    }
    @Test
    @DisplayName("Test the equals method - 2 different objects SheduleID vs String")
    public void testEqualsMethodWithDifferentObjects() {
        //Arrange
        String hulkDescription = "food";
        LocalDate hulkTriggerDate = LocalDate.of(2020,3,16);
        String hulkPeriodicity = "2";
        String hulkTransactionType = "Credit";

        String wolverineDescription = "car";
        LocalDate wolverineTriggerDate = LocalDate.of(2021,3,16);
        String wolverinePeriodicity = "1";
        String wolverineTransactionType = "Debit";
        // Act
        ScheduleID hulkSchedule= ScheduleID.createScheduleID(hulkDescription, hulkTriggerDate, hulkPeriodicity,
                hulkTransactionType);

        String wolverineSchedule=  wolverineDescription;
        //Assert
        assertNotEquals(hulkSchedule, wolverineSchedule);
    }
    @Test
    @DisplayName("Test the equals method - 2 different objects with different description")
    public void testEqualsMethodDifferentDescription() {
        //Arrange
        String hulkDescription = "food";
        LocalDate hulkTriggerDate = LocalDate.of(2020,3,16);
        String hulkPeriodicity = "2";
        String hulkTransactionType = "Credit";

        String wolverineDescription = "car";
        LocalDate wolverineTriggerDate = LocalDate.of(2020,3,16);
        String wolverinePeriodicity = "2";
        String wolverineTransactionType = "Credit";
        // Act
        ScheduleID hulkSchedule= ScheduleID.createScheduleID(hulkDescription, hulkTriggerDate, hulkPeriodicity,
                hulkTransactionType);

        ScheduleID wolverineSchedule=ScheduleID.createScheduleID(wolverineDescription, wolverineTriggerDate, wolverinePeriodicity,
                wolverineTransactionType);
        //Assert
        assertNotEquals(hulkSchedule, wolverineSchedule);
    }
    @Test
    @DisplayName("Test the equals method - 2 different objects with different triggerdate")
    public void testEqualsMethodDifferentTriggerDate() {
        //Arrange
        String hulkDescription = "food";
        LocalDate hulkTriggerDate = LocalDate.of(2020,3,16);
        String hulkPeriodicity = "2";
        String hulkTransactionType = "Credit";

        String wolverineDescription = "food";
        LocalDate wolverineTriggerDate = LocalDate.of(2021,3,16);
        String wolverinePeriodicity = "2";
        String wolverineTransactionType = "Credit";
        // Act
        ScheduleID hulkSchedule= ScheduleID.createScheduleID(hulkDescription, hulkTriggerDate, hulkPeriodicity,
                hulkTransactionType);

        ScheduleID wolverineSchedule=ScheduleID.createScheduleID(wolverineDescription, wolverineTriggerDate, wolverinePeriodicity,
                wolverineTransactionType);
        //Assert
        assertNotEquals(hulkSchedule, wolverineSchedule);
    }
    @Test
    @DisplayName("Test the equals method - 2 different objects with different periodicity")
    public void testEqualsMethodDifferentPeriodicity() {
        //Arrange
        String hulkDescription = "food";
        LocalDate hulkTriggerDate = LocalDate.of(2020,3,16);
        String hulkPeriodicity = "2";
        String hulkTransactionType = "Credit";

        String wolverineDescription = "food";
        LocalDate wolverineTriggerDate = LocalDate.of(2020,3,16);
        String wolverinePeriodicity = "1";
        String wolverineTransactionType = "Credit";
        // Act
        ScheduleID hulkSchedule= ScheduleID.createScheduleID(hulkDescription, hulkTriggerDate, hulkPeriodicity,
                hulkTransactionType);

        ScheduleID wolverineSchedule=ScheduleID.createScheduleID(wolverineDescription, wolverineTriggerDate, wolverinePeriodicity,
                wolverineTransactionType);
        //Assert
        assertNotEquals(hulkSchedule, wolverineSchedule);
    }
    @Test
    @DisplayName("Test the equals method - 2 different objects with different transaction type")
    public void testEqualsMethodDifferentTransactionType() {
        //Arrange
        String hulkDescription = "food";
        LocalDate hulkTriggerDate = LocalDate.of(2020,3,16);
        String hulkPeriodicity = "2";
        String hulkTransactionType = "Credit";

        String wolverineDescription = "food";
        LocalDate wolverineTriggerDate = LocalDate.of(2020,3,16);
        String wolverinePeriodicity = "2";
        String wolverineTransactionType = "Debit";
        // Act
        ScheduleID hulkSchedule= ScheduleID.createScheduleID(hulkDescription, hulkTriggerDate, hulkPeriodicity,
                hulkTransactionType);

        ScheduleID wolverineSchedule=ScheduleID.createScheduleID(wolverineDescription, wolverineTriggerDate, wolverinePeriodicity,
                wolverineTransactionType);
        //Assert
        assertNotEquals(hulkSchedule, wolverineSchedule);
    }

    @Test
    @DisplayName("Test the equals method - 2 different objects with different transaction type")
    public void testHashcode() {
        //Arrange
        String hulkDescription = "food";
        LocalDate hulkTriggerDate = LocalDate.of(2020,3,16);
        String hulkPeriodicity = "2";
        String hulkTransactionType = "Credit";

        String wolverineDescription = "food";
        LocalDate wolverineTriggerDate = LocalDate.of(2020,3,16);
        String wolverinePeriodicity = "2";
        String wolverineTransactionType = "Credit";
        // Act
        ScheduleID hulkSchedule= ScheduleID.createScheduleID(hulkDescription, hulkTriggerDate, hulkPeriodicity,
                hulkTransactionType);

        ScheduleID wolverineSchedule=ScheduleID.createScheduleID(wolverineDescription, wolverineTriggerDate, wolverinePeriodicity,
                wolverineTransactionType);
        //Assert
        assertEquals(hulkSchedule.hashCode(), wolverineSchedule.hashCode());
    }

}