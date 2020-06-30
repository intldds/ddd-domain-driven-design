package com.finance.project.domainLayer.domainEntities.aggregates.scheduling;

import com.finance.project.domainLayer.domainEntities.vosShared.Denomination;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * @author Ala Matos
 */
class TriggerDateTest {
    @Test
    @DisplayName("Test the TriggerDateVO constructor")
    public void testTheTriggerDateVO() {
        //Arrange
        LocalDate testingTriggerDate = LocalDate.of(2020, 3, 4);

        //Act
        TriggerDate firstTriggerDate = TriggerDate.createTriggerDate(testingTriggerDate);

        //Assert
        assertEquals(testingTriggerDate, firstTriggerDate.getTriggerDate());
    }

    @Test
    @DisplayName("Test the Equals TriggerDateVO || Happy Case")
    public void testEqualsTriggerDateVO () {

        //Arrange
        TriggerDate triggerDateTest = TriggerDate.createTriggerDate(LocalDate.of(2020, 3, 4));

        //Act
        TriggerDate triggerDate = TriggerDate.createTriggerDate(LocalDate.of(2020, 3, 4));

        //Assert
        assertEquals(triggerDateTest, triggerDate);
    }

    @Test
    @DisplayName("Verify Equals of TriggerDateVO || Happy case: Same object")
    public void testEqualsTriggerDateVOSameObject () {

        //Act
        TriggerDate triggerDate = TriggerDate.createTriggerDate(LocalDate.of(2020, 3, 4));

        //Assert
        assertEquals(triggerDate, triggerDate );
    }

    @Test
    @DisplayName("Test Equals of TriggerDateVO || Sad Case: Different TriggerDate")
    public void differentTriggerDateVO () {

        //Arrange
        LocalDate testingTriggerDate = LocalDate.of(2020, 3, 4);

        //Act
        TriggerDate firstTriggerDate = TriggerDate.createTriggerDate(LocalDate.of(2019, 3, 4));
        boolean result = firstTriggerDate.equals(testingTriggerDate);

        //Assert
        assertEquals(false, result);
    }

    @Test
    @DisplayName("Test Equals of TriggerDateVO || Sad case: object null")
    public void differentTriggerDateVOObjectNull () {

        //Arrange
        TriggerDate triggerDateTest = null;

        //Act
        TriggerDate triggerDate = TriggerDate.createTriggerDate(LocalDate.of(2020, 3, 4));
        boolean result = triggerDate.equals(triggerDateTest);

        //Assert
        assertEquals(false, result);
    }

    @Test
    @DisplayName("Verify Equals of TriggerDateVO || Sad case: Different objects class'")

    public void differentTriggerDateVODifferentClass () {
        //Arrange
        Denomination denomination = Denomination.createDenomination("Groceries");

        //Act
        TriggerDate triggerDate = TriggerDate.createTriggerDate(LocalDate.of(2020, 3, 4));

        //Assert
        assertNotEquals(triggerDate, denomination );
    }

    @Test
    @DisplayName("Verify Hash Code of TriggerDateVO")
    public void testTriggerDateVOHashCode() {

        //Act
        TriggerDate triggerDate = TriggerDate.createTriggerDate(LocalDate.of(2020, 3, 4));
        double hashcode = triggerDate.hashCode();

        //Assert
        assertEquals(triggerDate.hashCode(), hashcode);
    }

}