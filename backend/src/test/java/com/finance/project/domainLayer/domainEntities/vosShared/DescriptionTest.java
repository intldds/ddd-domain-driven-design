package com.finance.project.domainLayer.domainEntities.vosShared;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * @author Ala Matos
 */
class DescriptionTest {
    @Test
    @DisplayName("Test the constructor description VO || Happy Case")
    public void testTheDescriptionVO () {

        //Arrange
        String testingDescription = "Groceries";
        //Act
        Description firstDescription = Description.createDescription("Groceries");
        //Assert
        assertEquals(firstDescription.getDescription(), testingDescription);
    }

    @Test
    @DisplayName("Test the Equals description VO || Happy Case")
    public void testEqualsDescriptionVO () {

        //Arrange
        Description testingDescription = Description.createDescription("Groceries");
        //Act
        Description firstDescription = Description.createDescription("Groceries");
        //Assert
        assertEquals(firstDescription, testingDescription);
    }

    @Test
    @DisplayName("Verify Equals of DescriptionVO || Happy case: Same object")
    public void testEqualsDescriptionVOSameObject () {

        //Act
        Description firstDescription = Description.createDescription("Groceries");
        //Assert
        assertEquals(firstDescription, firstDescription );
    }

    @Test
    @DisplayName("Test Equals of description VO || Sad Case: Different Description")
    public void differentDescriptionVO () {

        //Arrange
        String testingDescription = "NotGroceries";
        //Act
        Description firstDescription = Description.createDescription("Groceries");
        boolean result = firstDescription.equals(testingDescription);

        //Assert
        assertEquals(false, result);
    }

    @Test
    @DisplayName("Test Equals of description VO || Sad case: object null")
    public void differentDescriptionVOObjectNull () {

        //Arrange
        String testingDescription = null;
        //Act
        Description firstDescription = Description.createDescription("Groceries");
        boolean result = firstDescription.equals(testingDescription);

        //Assert
        assertEquals(false, result);
    }

    @Test
    @DisplayName("Verify Equals of DescriptionVO || Sad case: Different objects class'")
    public void differentDescriptionVODifferentClass () {
        //Arrange
        Denomination denomination = Denomination.createDenomination("Groceries");
        //Act
        Description firstDescription = Description.createDescription("Groceries");
        //Assert
        assertNotEquals(firstDescription, denomination );
    }

    @Test
    @DisplayName("Verify Hash Code of DescriptionVO")

    public void testDescriptionVOHashCode() {
        //Act
        Description firstDescription = Description.createDescription("Groceries");
        double hashcode = firstDescription.hashCode();

        //Assert
        assertEquals(firstDescription.hashCode(), hashcode);
    }
}