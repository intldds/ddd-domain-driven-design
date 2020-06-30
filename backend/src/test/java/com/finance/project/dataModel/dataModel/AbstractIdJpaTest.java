package com.finance.project.dataModel.dataModel;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AbstractIdJpaTest {

    //Constructor

    @Test
    @DisplayName("Test for abstractIdJpaEmptyConstructor()")
    public void checkCreateAbstractIdJpaEmptyConstructor() {

        //Arrange
        String ownerID = "alexandre@gmail.com";
        String denomination = "Company";

        //Arrange
        AbstractIdJpa abstractIdJpa = new AbstractIdJpa();

        abstractIdJpa.setOwnerID(ownerID);
        abstractIdJpa.setDenomination(denomination);

        //Assert
        assertEquals(ownerID, abstractIdJpa.getOwnerID());
        assertEquals(denomination, abstractIdJpa.getDenomination());
    }

    @Test
    @DisplayName("Test for abstractIdJpaConstructor()")
    public void checkCreateAbstractIdJpaConstructor() {

        //Arrange
        String ownerID = "alexandre@gmail.com";
        String denomination = "Company";

        //Act
        AbstractIdJpa abstractIdJpa = new AbstractIdJpa(ownerID, denomination);

        //Assert
        assertEquals(ownerID, abstractIdJpa.getOwnerID());
        assertEquals(denomination, abstractIdJpa.getDenomination());
    }

    //Equals

    @Test
    @DisplayName("Test for equals - abstractId - Same Object")
    public void checkCreateAbstractIdJpaEquals_SameObject() {

        //Arrange
        String ownerID = "alexandre@gmail.com";
        String denomination = "Company";

        //Act
        AbstractIdJpa abstractIdJpa = new AbstractIdJpa(ownerID, denomination);

        //Assert
        assertTrue(abstractIdJpa.equals(abstractIdJpa));
    }

    @Test
    @DisplayName("Test for equals - abstractId - Different Object - Same content")
    public void checkCreateAbstractIdJpaEquals_DifferentObjectSameContent() {

        //Arrange
        String ownerID = "alexandre@gmail.com";
        String denomination = "Company";

        //Act
        AbstractIdJpa abstractIdJpa = new AbstractIdJpa(ownerID, denomination);
        AbstractIdJpa abstractIdJpa1 = new AbstractIdJpa(ownerID, denomination);

        //Assert
        assertTrue(abstractIdJpa.equals(abstractIdJpa1));
    }

    @Test
    @DisplayName("Test for equals - abstractId - Different Object")
    public void checkCreateAbstractIdJpaEquals_DifferentObject() {

        //Arrange
        String ownerID = "alexandre@gmail.com";
        String denomination = "Company";

        //Act
        AbstractIdJpa abstractIdJpa = new AbstractIdJpa(ownerID, denomination);

        //Assert
        assertFalse(abstractIdJpa.equals(ownerID));
    }

    @Test
    @DisplayName("Test for equals - abstractId - Object null")
    public void checkCreateAbstractIdJpaEquals_Object_Null() {

        //Arrange
        String ownerID = "alexandre@gmail.com";
        String denomination = "Company";

        //Act
        AbstractIdJpa abstractIdJpa = new AbstractIdJpa(ownerID, denomination);

        //Assert
        assertFalse(abstractIdJpa.equals(null));
    }

    @Test
    @DisplayName("Test for equals - abstractId - HashCode")
    public void checkCreateAbstractIdJpaEquals_HashCode() {

        //Arrange
        String ownerID = "alexandre@gmail.com";
        String denomination = "Company";

        //Act
        AbstractIdJpa abstractIdJpa = new AbstractIdJpa(ownerID, denomination);

        //Assert
        assertFalse(abstractIdJpa.equals(null));
    }

    //HashCode

    @Test
    @DisplayName("AbstractIdJpa - Hash Code")
    void abstractIdJpa_HashCodeTest() {

        //Arrange

        String ownerID = "alexandre@gmail.com";
        String denomination = "Company";

        //Act

        AbstractIdJpa abstractIdJpa = new AbstractIdJpa(ownerID, denomination);

        int abstractIdHashcode = abstractIdJpa.hashCode();
        int expectedHashCode = 2105492961;

        //Assert
        assertEquals(expectedHashCode, abstractIdHashcode);
    }


    //ToString

    @Test
    @DisplayName("AbstractId - ToString")
    public void abstractIDTOString() {

        //Arrange
        String ownerID = "alexandre@gmail.com";
        String denomination = "Company";

        //Arrange
        AbstractIdJpa abstractIdJpa = new AbstractIdJpa();

        abstractIdJpa.setOwnerID(ownerID);
        abstractIdJpa.setDenomination(denomination);

        //Assert

        assertEquals("AbstractIdJpa{ownerID='alexandre@gmail.com', denomination='Company'}", abstractIdJpa.toString());

    }

}