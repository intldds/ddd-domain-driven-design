package com.finance.project.dtos.dtos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreatePersonCategoryDTOTest {

    @Test
    @DisplayName("test constructor US005_DTO")
    void US005_DTO_Constructor() {

        // Arrange
        String email = "lebron@gmail.com";
        String denomination = "Basket";

        // Act
        CreatePersonCategoryDTO createPersonCategoryDTO = new CreatePersonCategoryDTO(email, denomination);

        // Assert
        assertEquals(email, createPersonCategoryDTO.getEmail());
        assertEquals(denomination, createPersonCategoryDTO.getDenomination());
    }


    @Test
    @DisplayName("Test getEmail")
    void getEmail() {

        // Arrange
        String email = "lebron@gmail.com";
        String denomination = "Basket";


        // Act
        CreatePersonCategoryDTO createPersonCategoryDTO = new CreatePersonCategoryDTO(email, denomination);

        // Assert
        assertEquals(email, createPersonCategoryDTO.getEmail());
    }


    @Test
    @DisplayName("Test getDenomination")
    void getDenomination() {

        // Arrange
        String email = "lebron@gmail.com";
        String denomination = "Basket";

        // Act
        CreatePersonCategoryDTO createPersonCategoryDTO = new CreatePersonCategoryDTO(email, denomination);

        // Assert
        assertEquals(denomination, createPersonCategoryDTO.getDenomination());
    }
    

    //Test Equals


    @Test
    @DisplayName("Test Equals - Same Object")
    void Equals_SameObject() {

        // Arrange
        String email = "lebron@gmail.com";
        String denomination = "Basket";

        // Act
        CreatePersonCategoryDTO createPersonCategoryDTO = new CreatePersonCategoryDTO(email, denomination);

        boolean result = createPersonCategoryDTO.equals(createPersonCategoryDTO);

        // Assert
        assertEquals(true, result);
        assertTrue(result);
    }

    @Test
    @DisplayName("Test Equals - Same Information")
    void Equals_DifferentObjectsSameInformation() {

        // Arrange
        String email = "lebron@gmail.com";
        String denomination = "Basket";

        // Act
        CreatePersonCategoryDTO createPersonCategoryDTO = new CreatePersonCategoryDTO(email, denomination);
        CreatePersonCategoryDTO createPersonCategoryDTO1 = new CreatePersonCategoryDTO(email, denomination);

        boolean result = createPersonCategoryDTO.equals(createPersonCategoryDTO1);

        // Assert
        assertEquals(createPersonCategoryDTO.equals(createPersonCategoryDTO1), createPersonCategoryDTO1.equals(createPersonCategoryDTO));
        assertEquals(true, result);
        assertTrue(result);
    }


    @Test
    @DisplayName("Test Equals - Same Data")
    void Equals_DifferentObjectsSameData() {

        // Arrange A
        String emailA = "lebron@gmail.com";
        String denominationA = "Basket";


        // Arrange B
        String emailB = "lebron@gmail.com";
        String denominationB = "Basket";

        // Act
        CreatePersonCategoryDTO createPersonCategoryDTOA = new CreatePersonCategoryDTO(emailA, denominationA);
        CreatePersonCategoryDTO createPersonCategoryDTOB = new CreatePersonCategoryDTO(emailB, denominationB);

        boolean result = createPersonCategoryDTOA.equals(createPersonCategoryDTOB);

        // Assert
        assertEquals(createPersonCategoryDTOA.equals(createPersonCategoryDTOB), createPersonCategoryDTOA.equals(createPersonCategoryDTOB));
        assertEquals(true, result);
        assertTrue(result);
    }

    @Test
    @DisplayName("Test Equals - Same Data - get")
    void Equals_DifferentObjectsSameData_get() {

        // Arrange A
        String emailA = "lebron@gmail.com";
        String denominationA = "Basket";

        // Arrange B
        String emailB = "lebron@gmail.com";
        String denominationB = "Basket";

        // Act
        CreatePersonCategoryDTO createPersonCategoryDTOA = new CreatePersonCategoryDTO(emailA, denominationA);
        CreatePersonCategoryDTO createPersonCategoryDTOB = new CreatePersonCategoryDTO(emailB, denominationB);

        boolean result = createPersonCategoryDTOA.equals(createPersonCategoryDTOB);
        boolean resultEmail = createPersonCategoryDTOA.getEmail().equals(createPersonCategoryDTOB.getEmail());
        boolean resultDenomination = createPersonCategoryDTOA.getDenomination().equals(createPersonCategoryDTOB.getDenomination());

        // Assert
        assertEquals(true, result);
        assertEquals(createPersonCategoryDTOA.equals(createPersonCategoryDTOB), createPersonCategoryDTOA.equals(createPersonCategoryDTOB));
        assertEquals(true, resultDenomination);
        assertEquals(true, resultEmail);

    }

    @Test
    @DisplayName("Test Equals -Different Information Information - Email")
    void Equals_DifferentObjectsDifferentInformation_Email() {

        // Arrange
        String email = "lebron@gmail.com";
        String denomination = "Basket";

        // Arrange A

        String emailA = "lebronjames@gmail.com";
        String denominationA = "Basket";;

        // Act
        CreatePersonCategoryDTO createPersonCategoryDTO = new CreatePersonCategoryDTO(email, denomination);
        CreatePersonCategoryDTO createPersonCategoryDTOA = new CreatePersonCategoryDTO(emailA, denominationA);

        boolean result = createPersonCategoryDTO.equals(createPersonCategoryDTOA);

        // Assert
        assertEquals(false, result);
        assertFalse(result);
    }

    @Test
    @DisplayName("Test Equals -Different Information Information - Denomination")
    void Equals_DifferentObjectsDifferentInformation_Denomination() {

        // Arrange
        String email = "lebron@gmail.com";
        String denomination = "Basket";

        // Arrange A

        String emailA = "lebron@gmail.com";
        String denominationA = "Basket_Finals";;

        // Act
        CreatePersonCategoryDTO createPersonCategoryDTO = new CreatePersonCategoryDTO(email, denomination);
        CreatePersonCategoryDTO createPersonCategoryDTOA = new CreatePersonCategoryDTO(emailA, denominationA);

        boolean result = createPersonCategoryDTO.equals(createPersonCategoryDTOA);

        // Assert
        assertEquals(false, result);
        assertFalse(result);
    }

    @Test
    @DisplayName("Test Equals - NotIstanceOf")
    void Equals_NotInstanceOf() {

        // Arrange
        String email = "lebron@gmail.com";
        String denomination = "Basket";

        // Act
        CreatePersonCategoryDTO createPersonCategoryDTO = new CreatePersonCategoryDTO(email, denomination);


        boolean result = createPersonCategoryDTO.equals(email);

        // Assert

        assertEquals(false, result);
        assertFalse(result);
    }

    @Test
    @DisplayName("Test Equals - Null")
    void Equals_Null() {

        // Arrange
        String email = "lebron@gmail.com";
        String denomination = "Basket";

        // Arrange A

        String emailA = "lebronjames@gmail.com";
        String denominationA = "Basket_Finals";

        // Act
        CreatePersonCategoryDTO createPersonCategoryDTO = new CreatePersonCategoryDTO(email, denomination);
        CreatePersonCategoryDTO createPersonCategoryDTOA = null;

        boolean result = createPersonCategoryDTO.equals(createPersonCategoryDTOA);

        // Assert

        assertEquals(false, result);
        assertFalse(result);
    }

    //hashcode

    @Test
    @DisplayName("Test HashCode - True")
    void HashCode_True() {

        // Arrange
        String email = "lebron@gmail.com";
        String denomination = "Basket";

        // Act
        CreatePersonCategoryDTO createPersonCategoryDTO = new CreatePersonCategoryDTO(email, denomination);
        CreatePersonCategoryDTO createPersonCategoryDTOA = new CreatePersonCategoryDTO(email, denomination);

        boolean result = createPersonCategoryDTO.hashCode()==createPersonCategoryDTOA.hashCode();

        // Assert
        assertTrue(createPersonCategoryDTO.hashCode()==createPersonCategoryDTOA.hashCode());
        assertEquals(true, result);
        assertTrue(result);
    }

    @Test
    @DisplayName("Test HashCode - False")
    void hashCode_false() {

        // Arrange
        String email = "lebron@gmail.com";
        String denomination = "Basket";

        // Arrange A

        String emailA = "lebronjames@gmail.com";
        String denominationA = "Basket_Finals";

        // Act
        CreatePersonCategoryDTO createPersonCategoryDTO = new CreatePersonCategoryDTO(email, denomination);
        CreatePersonCategoryDTO createPersonCategoryDTOA = new CreatePersonCategoryDTO(emailA, denominationA);

        boolean result = createPersonCategoryDTO.hashCode()==createPersonCategoryDTOA.hashCode();

        // Assert
        assertFalse(createPersonCategoryDTO.hashCode()==createPersonCategoryDTOA.hashCode());
        assertEquals(false, result);
        assertFalse(result);
    }

}