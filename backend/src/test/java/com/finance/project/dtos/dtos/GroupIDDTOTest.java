package com.finance.project.dtos.dtos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 */
class GroupIDDTOTest {

    @Test
    @DisplayName("Test For GroupIDDTO()")
    void IDDTOConstructor() {

        // Arrange

        String denomination = "dance";

        // Act

        GroupIDDTO groupIDDTO = new GroupIDDTO(denomination);

        // Assert

        assertEquals(denomination, groupIDDTO.getDenomination());
    }



    @Test
    @DisplayName("Test For equals() | Success")
    void equalsSuccess() {

        // Arrange

        String denomination = "dance";

        // Act

        GroupIDDTO groupIDDTO = new GroupIDDTO(denomination);
        GroupIDDTO groupIDDTO1 = new GroupIDDTO(denomination);

        boolean result = groupIDDTO.equals(groupIDDTO1);

        // Assert

        assertEquals(true, result);
        assertEquals(groupIDDTO.equals(groupIDDTO1), groupIDDTO1.equals(groupIDDTO));
        assertTrue(result);
    }

    @Test
    @DisplayName("Test For equals() | Success | Same Object")
    void equalsSuccessSameObject() {


        // Arrange

        String denomination = "dance";

        // Act

        GroupIDDTO groupIDDTO = new GroupIDDTO(denomination);


        boolean result = groupIDDTO.equals(groupIDDTO);

        // Assert

        assertEquals(true, result);
        assertEquals(groupIDDTO.equals(groupIDDTO), groupIDDTO.equals(groupIDDTO));
        assertTrue(result);
    }

    @Test
    @DisplayName("Test For equals() | Fail | Email")
    void equalsFailEmail() {


        // Arrange

        String denomination = "dance";

        //Arrange Second GroupID

        String denominationSecond = "ballet";

        // Act

        GroupIDDTO groupIDDTO = new GroupIDDTO(denomination);
        GroupIDDTO groupIDDTOSecond = new GroupIDDTO(denominationSecond);

        boolean result = groupIDDTO.equals(groupIDDTOSecond);

        // Assert

        assertEquals(false, result);
        assertFalse(result);
    }


    @Test
    @DisplayName("Test For equals() | Fail | Different Class")
    void equalsFailDifferentClass() {

        // Arrange

        String denomination = "dance";

        // Act

        GroupIDDTO groupIDDTO = new GroupIDDTO(denomination);


        boolean result = groupIDDTO.equals(denomination);


        // Assert

        assertEquals(false, result);
    }

    @Test
    @DisplayName("Test For equals() | Fail | Null")
    void equalsFailNull() {

        // Arrange

        String denomination = "dance";

        // Act

        GroupIDDTO groupIDDTO = new GroupIDDTO(denomination);


        boolean result = groupIDDTO.equals(null);


    // Assert

        assertEquals(false, result);
    }

    @Test
    @DisplayName("Test For HahCode | Success")
    void hashCodeSuccess() {

        // Arrange

        String denomination = "dance";

        // Act

        GroupIDDTO groupIDDTO = new GroupIDDTO(denomination);
        GroupIDDTO groupIDDTO1 = new GroupIDDTO(denomination);


    // Act

        boolean hashCodeResult = groupIDDTO.hashCode()== groupIDDTO1.hashCode();


        // Assert

        assertEquals(true, hashCodeResult);
        assertTrue(groupIDDTO.hashCode()== groupIDDTO.hashCode());
        assertEquals(true, groupIDDTO.hashCode()== groupIDDTO.hashCode() );
        assertTrue(groupIDDTO.hashCode()== groupIDDTO1.hashCode());
    }

    @Test
    @DisplayName("Test For HashCode | Fail")
    void hashCodeFail() {

        // Arrange

        String denomination = "dance";

        //Arrange Second GroupID

        String denominationSecond = "ballet";

        // Act

        GroupIDDTO groupIDDTO = new GroupIDDTO(denomination);
        GroupIDDTO groupIDDTSecond = new GroupIDDTO(denominationSecond);


        // Act

        boolean hashCodeResult = groupIDDTO.hashCode()== groupIDDTSecond.hashCode();


        // Assert

        assertEquals(false, hashCodeResult);
        assertFalse(groupIDDTO.hashCode()== groupIDDTSecond.hashCode());
    }


}