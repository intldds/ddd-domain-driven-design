package com.finance.project.dtos.dtos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SiblingsDTOTest {

    @Test
    @DisplayName("SiblingsDTO - Test Constructor with Parameters")
    void siblingsDTO_ConstructorWithParametersTest() {

        //Arrange
        String ruiEmail = "rui@gmail.com";
        String henriqueEmail = "henrique@gmail.com";

        List<String> siblings = new ArrayList<>();
        siblings.add(ruiEmail);
        siblings.add(henriqueEmail);

        //Act
        SiblingsDTO siblingsDTO = new SiblingsDTO(siblings);

        //Assert
        assertEquals(siblings, siblingsDTO.getSiblings());
    }

    @Test
    @DisplayName("SiblingsDTO - Test Constructor without Parameters")
    void siblingsDTO_ConstructorWithoutParametersTest() {

        //Arrange
        String ruiEmail = "rui@gmail.com";
        String henriqueEmail = "henrique@gmail.com";

        List<String> siblings = new ArrayList<>();
        siblings.add(ruiEmail);
        siblings.add(henriqueEmail);


        //Act
        SiblingsDTO siblingsDTO = new SiblingsDTO();
        siblingsDTO.setSiblings(siblings);

        //Assert
        assertEquals(siblings, siblingsDTO.getSiblings());
    }

    @Test
    @DisplayName("SiblingsDTO - Test Equals || Same Object")
    void siblingsDTO_EqualsTest_SameObject() {

        //Arrange
        String ruiEmail = "rui@gmail.com";
        String henriqueEmail = "henrique@gmail.com";

        List<String> siblings = new ArrayList<>();
        siblings.add(ruiEmail);
        siblings.add(henriqueEmail);

        //Act
        SiblingsDTO siblingsDTO1 = new SiblingsDTO(siblings);
        SiblingsDTO siblingsDTO2 = new SiblingsDTO(siblings);

        //Assert
        assertTrue(siblingsDTO1.equals(siblingsDTO2));
        assertTrue(siblingsDTO1.equals(siblingsDTO1)); //the object is equal to itself
    }

    @Test
    @DisplayName("SiblingsDTO - Test Equals || Different Object")
    void siblingsDTO_EqualsTest_DifferentObject() {

        //Arrange
        String ruiEmail = "rui@gmail.com";
        String henriqueEmail = "henrique@gmail.com";

        List<String> siblings1 = new ArrayList<>();
        siblings1.add(ruiEmail);
        siblings1.add(henriqueEmail);


        List<String> siblings2 = new ArrayList<>();

        String bugKiller = "Bug Killer";

        //Act
        SiblingsDTO siblingsDTO1 = new SiblingsDTO(siblings1);
        SiblingsDTO siblingsDTO2 = new SiblingsDTO(siblings2);
        SiblingsDTO siblingsDTO3 = null;

        //Assert
        assertFalse(siblingsDTO1.equals(siblingsDTO2));
        assertFalse(siblingsDTO1.equals(bugKiller)); //not same instance
        assertFalse(siblingsDTO1.equals(siblingsDTO3)); //object is null
    }

    @Test
    @DisplayName("SiblingsDTO - Test Hash Code")
    void siblingsDTO_HashCodeTest() {

        //Arrange
        String ruiEmail = "rui@gmail.com";
        String henriqueEmail = "henrique@gmail.com";

        List<String> siblings = new ArrayList<>();
        siblings.add(ruiEmail);
        siblings.add(henriqueEmail);

        //Act
        SiblingsDTO siblingsDTO = new SiblingsDTO(siblings);

        int siblingsDTOHashcode = siblingsDTO.hashCode();
        int expectedHashCode = -1304682252;

        //Assert
        assertEquals(expectedHashCode, siblingsDTOHashcode);
    }
    
}