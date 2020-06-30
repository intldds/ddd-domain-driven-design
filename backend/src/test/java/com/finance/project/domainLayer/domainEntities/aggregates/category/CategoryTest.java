package com.finance.project.domainLayer.domainEntities.aggregates.category;

import com.finance.project.domainLayer.domainEntities.vosShared.CategoryID;
import com.finance.project.domainLayer.domainEntities.vosShared.GroupID;
import com.finance.project.domainLayer.domainEntities.vosShared.PersonID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    //Test constructor

    //Person
    @Test
    @DisplayName("Verify category constructor | Person")
    void testCategoryConstructorForPerson() {

        //Arrange

        String denomination = "Food";
        String personEmail = "gohan@gmail.com";

        PersonID personID = PersonID.createPersonID(personEmail);
        CategoryID expectedCategoryID = CategoryID.createCategoryID(denomination, personID);

        //Act
        Category newCategory = Category.createCategory(denomination, personID);

        //Assert
        assertEquals(expectedCategoryID, newCategory.getCategoryID());
    }

    //Group
    @Test
    @DisplayName("Verify category constructor | group")
    void testConstructorForGroup() {

        //Arrange
        String denomination = "Food";

        GroupID groupID = GroupID.createGroupID("Futebol");
        CategoryID expectedCategoryID = CategoryID.createCategoryID(denomination, groupID);

        //Act
        Category newCategory = Category.createCategory(denomination, groupID);

        //Assert
        assertEquals(expectedCategoryID, newCategory.getCategoryID());
    }

    //Test equals

    @Test
    @DisplayName("Verify equals method | Happy Case ")
    void testEquals() {

        //Arrange
        String denominationFood = "Food";
        GroupID bunkerID = GroupID.createGroupID("Bunker");

        //Act
        Category categoryFood = Category.createCategory(denominationFood, bunkerID);
        Category categoryHealth = Category.createCategory(denominationFood, bunkerID);

        boolean result = categoryFood.equals(categoryHealth);

        //Assert
        assertEquals(true, result);
    }

    @Test
    @DisplayName("Verify equals method | Not Equals ")
    void testNotEquals() {

        //Arrange
        String denominationFood = "Food";
        String denominationHealth = "Health";
        GroupID bunkerID = GroupID.createGroupID("Bunker");
        GroupID feriasID = GroupID.createGroupID("Férias");

        //Act
        Category categoryFood = Category.createCategory(denominationFood, bunkerID);
        Category categoryHealth = Category.createCategory(denominationHealth, feriasID);

        boolean result = categoryFood.equals(categoryHealth);

        //Assert
        assertEquals(false, result);
    }


    @Test
    @DisplayName("Verify equals method ")
    void testEqualsSameObject() {

        //Arrange

        String denominationFood = "Food";
        String personEmail = "gohan@gmail.com";

        PersonID personID = PersonID.createPersonID(personEmail);

        //Act
        Category categoryFood = Category.createCategory(denominationFood, personID);

        //Assert

        assertEquals(categoryFood, categoryFood);
    }


    @Test
    @DisplayName("Verify equals Not Instance of Category ")
    void testEqualsNotEquals() {

        //Arrange

        String denominationFood = "Food";
        String personEmail = "gohan@gmail.com";

        PersonID personID = PersonID.createPersonID(personEmail);

        //Act
        Category categoryFood = Category.createCategory(denominationFood, personID);

        //Assert

        assertNotEquals(categoryFood, personID);
    }

    @Test
    @DisplayName("Verify equals Not Instance of Category | denomination ")
    void checkEqualsNotEquals_DifferentDenomination() {

        //Arrange
        String denominationFood = "Food";
        String denominationCar = "Car";
        String personEmail = "gohan@gmail.com";

        PersonID personID = PersonID.createPersonID(personEmail);

        //Act
        Category categoryFood = Category.createCategory(denominationFood, personID);
        Category categoryCar = Category.createCategory(denominationCar, personID);

        //Assert

        assertNotEquals(categoryFood, categoryCar);

    }

    @Test
    @DisplayName("Verify hashCode | Happy Case ")
    void testHashCode() {

        //Arrange
        String denominationFood = "Food";
        String personEmail = "gohan@gmail.com";

        PersonID personID = PersonID.createPersonID(personEmail);

        Category categoryFood = Category.createCategory(denominationFood, personID);

        //Expected
        int expectedHash = -1652126337;

        //Act
        int hashResultFood = categoryFood.hashCode();

        //Assert

        assertEquals(expectedHash, hashResultFood);

    }



    @Test
    @DisplayName("Test For CategoryPerson - Exception | Denomination Empty")

    public void checkPersonExceptionDenominationEmpty() {

        //Arrange
        String denominationFood = "";
        String personEmail = "gohan@gmail.com";

        PersonID personID = PersonID.createPersonID(personEmail);

        //Act
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> Category.createCategory(denominationFood, personID));

        //Assert
        assertEquals(thrown.getMessage(), "Category not created due to the fact that the denomination parameter hasn't a valid argument");
    }

    @Test
    @DisplayName("Test For CategoryPerson - Exception | Denomination Null")

    public void checkPersonExceptionDenominationNull() {

        //Arrange
        String denominationFood = null;
        String personEmail = "gohan@gmail.com";

        PersonID personID = PersonID.createPersonID(personEmail);

        //Act
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> Category.createCategory(denominationFood, personID));

        //Assert
        assertEquals(thrown.getMessage(), "Category not created due to the fact that the denomination parameter hasn't a valid argument");
    }

    @Test
    @DisplayName("Test For CategoryPerson - Exception | PersonID Null")

    public void checkPersonExceptionPersonIDNull() {

        //Arrange
        String denominationFood = "Food";

        PersonID personID = null;

        //Act
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> Category.createCategory(denominationFood, personID));

        //Assert
        assertEquals(thrown.getMessage(), "Category not created due to the fact that the ownerID parameter hasn't a valid argument");
    }

    @Test
    @DisplayName("Test For CategoryGroup - Exception | Denomination Empty")

    public void checkGroupExceptionDenominationEmpty() {

        //Arrange
        String catDenomination = "";

        String groupDenomination = "Futebol";
        GroupID groupID = GroupID.createGroupID(groupDenomination);

        //Act
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> Category.createCategory(catDenomination, groupID));

        //Assert
        assertEquals(thrown.getMessage(), "Category not created due to the fact that the denomination parameter hasn't a valid argument");
    }

    @Test
    @DisplayName("Test For CategoryGroup - Exception | Denomination Null")

    public void checkGroupExceptionDenominationNull() {

        //Arrange
        String catDenomination = null;

        String groupDenomination = "Futebol";
        GroupID groupID = GroupID.createGroupID(groupDenomination);

        //Act
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> Category.createCategory(catDenomination, groupID));

        //Assert
        assertEquals(thrown.getMessage(), "Category not created due to the fact that the denomination parameter hasn't a valid argument");
    }

    @Test
    @DisplayName("Test For CategoryGroup - Exception | PersonID Null")

    public void checkGroupExceptionPersonIDNull() {

        //Arrange
        String catDenomination = "Food";

        GroupID groupID = null;

        //Act
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> Category.createCategory(catDenomination, groupID));

        //Assert
        assertEquals(thrown.getMessage(), "Category not created due to the fact that the ownerID parameter hasn't a valid argument");
    }
}