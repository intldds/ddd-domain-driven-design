package com.finance.project.domainLayer.domainEntities.aggregates.scheduling;

import com.finance.project.domainLayer.domainEntities.vosShared.Denomination;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * @author Ala Matos
 */
class PeriodicityTest {
    @Test
    @DisplayName("Test the constructor periodicity VO")

    public void testOfThePeriodicityVO () {
        //Arrange
        String testingPeriodicity = "Every two days";
        //Act
        Periodicity firstPeriodicity = Periodicity.createPeriodicity(testingPeriodicity);
        //Assert
        assertEquals(testingPeriodicity, firstPeriodicity.getPeriodicity());
    }

    @Test
    @DisplayName("Test the Equals PeriodicityVO || Happy Case")
    public void testEqualsPeriodicityVO() {

        //Expected
        Periodicity periodicityTest = Periodicity.createPeriodicity("Every two days");

        //Act
        Periodicity periodicity = Periodicity.createPeriodicity("Every two days");

        //Assert
        assertEquals(periodicityTest, periodicity);
    }

    @Test
    @DisplayName("Verify Equals of PeriodicityVO || Happy case: Same object")
    public void testEqualsPeriodicityVOSameObject() {

        //Act
        Periodicity periodicity = Periodicity.createPeriodicity("Every two days");

        //Assert
        assertEquals(periodicity, periodicity);
    }

    @Test
    @DisplayName("Test Equals of PeriodicityVO || Sad Case: Different Periodicity")
    public void differentPeriodicityVO() {

        //Arrange
        Periodicity periodicityTest = Periodicity.createPeriodicity("Not Every two days");

        //Act
        Periodicity periodicity = Periodicity.createPeriodicity("Every two days");
        boolean result = periodicity.equals(periodicityTest);

        //Assert
        assertEquals(false, result);
    }

    @Test
    @DisplayName("Test Equals of PeriodicityVO || Sad case: object null")
    public void differentPeriodicityVOObjectNull() {

        //Arrange
        //Arrange
        Periodicity periodicityTest = null;

        //Act
        Periodicity periodicity = Periodicity.createPeriodicity("Every two days");
        boolean result = periodicity.equals(periodicityTest);

        //Assert
        assertEquals(false, result);
    }

    @Test
    @DisplayName("Verify Equals of PeriodicityVO || Sad case: Different objects class'")

    public void differentPeriodicityVODifferentClass() {
        //Arrange
        Denomination denomination = Denomination.createDenomination("Groceries");

        //Act
        Periodicity periodicity = Periodicity.createPeriodicity("Every two days");

        //Assert
        assertNotEquals(periodicity, denomination);
    }

    @Test
    @DisplayName("Verify Hash Code of PeriodicityVO")

    public void testPeriodicityVOHashCode() {
        //Act
        Periodicity periodicity = Periodicity.createPeriodicity("Every two days");
        double hashcode = periodicity.hashCode();

        //Assert
        assertEquals(periodicity.hashCode(), hashcode);
    }
}