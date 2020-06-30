package com.finance.project.dtos.dtos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CategoriesDTOTest {

    @Test
    @DisplayName("CategoriesDTO - Test Constructor with Parameters")
    void categoriesDTO_ConstructorWithParametersTest() {

        //Arrange
        String categoryDenomination = "Food";

        List<String> categoriesList = new ArrayList<>();
        categoriesList.add(categoryDenomination);

        //Act
        CategoriesDTO categoriesDTO = new CategoriesDTO(categoriesList);

        //Assert
        assertEquals(categoriesList, categoriesDTO.getCategories());
    }

    @Test
    @DisplayName("CategoriesDTO - Test Constructor without Parameters")
    void categoriesDTO_ConstructorWithoutParametersTest() {

        //Arrange
        String categoryDenomination = "Food";

        List<String> categoriesList = new ArrayList<>();
        categoriesList.add(categoryDenomination);

        //Act
        CategoriesDTO categoriesDTO = new CategoriesDTO();
        categoriesDTO.setCategories(categoriesList);

        //Assert
        assertEquals(categoriesList, categoriesDTO.getCategories());
    }

    @Test
    @DisplayName("CategoriesDTO - Test Equals || Same Object")
    void categoriesDTO_EqualsTest_SameObject() {

        //Arrange
        String categoryDenomination1 = "Food";
        String categoryDenomination2 = "Netflix";

        List<String> categoriesList = new ArrayList<>();
        categoriesList.add(categoryDenomination1);
        categoriesList.add(categoryDenomination2);

        //Act
        CategoriesDTO categoriesDTO1 = new CategoriesDTO(categoriesList);
        CategoriesDTO categoriesDTO2 = new CategoriesDTO(categoriesList);

        //Assert
        assertTrue(categoriesDTO1.equals(categoriesDTO2));
        assertTrue(categoriesDTO1.equals(categoriesDTO1)); //the object is equal to itself
    }

    @Test
    @DisplayName("CategoriesDTO - Test Equals || Different Object")
    void categoriesDTO_EqualsTest_DifferentObject() {

        //Arrange
        String categoryDenomination = "Food";

        List<String> categoriesList1 = new ArrayList<>();
        categoriesList1.add(categoryDenomination);

        List<String> categoriesList2 = new ArrayList<>();

        String bugKiller = "Bug Killer";

        //Act
        CategoriesDTO categoriesDTO1 = new CategoriesDTO(categoriesList1);
        CategoriesDTO categoriesDTO2 = new CategoriesDTO(categoriesList2);
        CategoriesDTO categoriesDTO3 = null;

        //Assert
        assertFalse(categoriesDTO1.equals(categoriesDTO2));
        assertFalse(categoriesDTO1.equals(bugKiller)); //not same instance
        assertFalse(categoriesDTO1.equals(categoriesDTO3)); //object is null
    }

    @Test
    @DisplayName("CategoriesDTO - Test Hash Code")
    void categoriesDTO_HashCodeTest() {

        //Arrange
        String categoryDenomination = "Food";

        List<String> categoriesList1 = new ArrayList<>();
        categoriesList1.add(categoryDenomination);

        //Act
        CategoriesDTO categoriesDTO = new CategoriesDTO(categoriesList1);

        int categoriesDTOHashcode = categoriesDTO.hashCode();
        int expectedHashCode = 2196605;

        //Assert
        assertEquals(expectedHashCode, categoriesDTOHashcode);
    }
}