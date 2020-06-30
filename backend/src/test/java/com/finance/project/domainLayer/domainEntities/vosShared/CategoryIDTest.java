package com.finance.project.domainLayer.domainEntities.vosShared;

import com.finance.project.domainLayer.entitiesInterfaces.OwnerID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryIDTest {

    @Test
    @DisplayName("Test the CategoryID - Person - Constructor")
    public void testCategoryID_Constructor_Person() {

        //Arrange
        String foodDenomination = "Food";
        String xikinhoEmail = "xikinho@isep.ipp.pt";
        PersonID personID = PersonID.createPersonID(xikinhoEmail);

        Denomination expectedDenomination = Denomination.createDenomination(foodDenomination);

        //Act
        CategoryID categoryID = CategoryID.createCategoryID(foodDenomination, personID);

        //Assert
        assertEquals(expectedDenomination, categoryID.getDenomination());
        Assertions.assertEquals(personID, categoryID.getOwnerID());

    }

    @Test
    @DisplayName("Test the CategoryID - Group - Constructor")
    public void testCategoryID_Constructor_Group() {

        //Arrange
        String foodDenomination = "Food";
        String groupDenomination = "Runners";
        GroupID groupID = GroupID.createGroupID(groupDenomination);

        Denomination expectedDenomination = Denomination.createDenomination(foodDenomination);

        //Act
        CategoryID categoryID = CategoryID.createCategoryID(foodDenomination, groupID);

        //Assert
        assertEquals(expectedDenomination, categoryID.getDenomination());
        Assertions.assertEquals(groupID, categoryID.getOwnerID());

    }

    @Test
    @DisplayName("Test the CategoryID - getOwnerID()")
    public void testCategoryID_getOwnerID() {

        //Arrange
        String foodDenomination = "Food";
        String groupDenomination = "Runners";
        GroupID groupID = GroupID.createGroupID(groupDenomination);

        CategoryID categoryID = CategoryID.createCategoryID(foodDenomination, groupID);

        //Act

        OwnerID result = categoryID.getOwnerID();

        //Assert
        assertEquals(groupID, result);
    }

    @Test
    @DisplayName("Test the CategoryID - getDenomination()")
    public void testCategoryID_getDenomination() {

        //Arrange
        String foodDenomination = "Food";
        String xikinhoEmail = "xikinho@isep.ipp.pt";
        PersonID personID = PersonID.createPersonID(xikinhoEmail);

        Denomination expectedDenomination = Denomination.createDenomination(foodDenomination);
        CategoryID categoryID = CategoryID.createCategoryID(foodDenomination, personID);

        //Act
        Denomination result = categoryID.getDenomination();

        //Assert
        assertEquals(expectedDenomination, result);

    }

    @Test
    @DisplayName("Test the CategoryID - getPersonID()")
    public void testCategoryID_getPersonID() {

        //Arrange
        String foodDenomination = "Food";
        String xikinhoEmail = "xikinho@isep.ipp.pt";
        PersonID personID = PersonID.createPersonID(xikinhoEmail);

        CategoryID categoryID = CategoryID.createCategoryID(foodDenomination, personID);

        //Act
        OwnerID result = categoryID.getOwnerID();

        //Assert
        assertEquals(personID, result);
    }

    @Test
    @DisplayName("Test the CategoryID - equals() - True")
    public void testCategoryID_equals_True() {

        //Arrange
        String foodDenomination = "Food";
        String xikinhoEmail = "xikinho@isep.ipp.pt";
        PersonID personID = PersonID.createPersonID(xikinhoEmail);

        CategoryID firstCategoryID = CategoryID.createCategoryID(foodDenomination, personID);
        CategoryID secondCategoryID = CategoryID.createCategoryID(foodDenomination, personID);


        //Act
        boolean result = firstCategoryID.equals(secondCategoryID);

        //Assert
        assertEquals(true, result);
    }

    @Test
    @DisplayName("Test the CategoryID - equals() - False | PersonID")
    public void testCategoryID_equals_False_PersonID() {
        //Arrange
        String foodDenomination = "Food";
        String xikinhoEmail = "xikinho@isep.ipp.pt";
        PersonID firstPersonID = PersonID.createPersonID(xikinhoEmail);
        CategoryID firstCategoryID = CategoryID.createCategoryID(foodDenomination, firstPersonID);


        String johnEmail = "john@isep.ipp.pt";
        PersonID secondPersonID = PersonID.createPersonID(johnEmail);
        CategoryID secondCategoryID = CategoryID.createCategoryID(foodDenomination, secondPersonID);

        //Act
        boolean result = firstCategoryID.equals(secondCategoryID);

        //Assert
        assertEquals(false, result);
    }

    @Test
    @DisplayName("Test the CategoryID - equals() - False | GroupID")
    public void testCategoryID_equals_False_GroupID() {
        //Arrange
        String foodDenomination = "Food";

        String firstGroupDenomination = "Runners";
        GroupID firstGroupID = GroupID.createGroupID(firstGroupDenomination);
        CategoryID firstCategoryID = CategoryID.createCategoryID(foodDenomination, firstGroupID);


        String secondGroupDenomination = "Friends";
        GroupID secondGroupID = GroupID.createGroupID(secondGroupDenomination);
        CategoryID secondCategoryID = CategoryID.createCategoryID(foodDenomination, secondGroupID);

        //Act
        boolean result = firstCategoryID.equals(secondCategoryID);

        //Assert
        assertEquals(false, result);
    }

    @Test
    @DisplayName("Test the CategoryID - equals() - False | Denomination")
    public void testCategoryID_equals_False_Denomination() {
        //Arrange
        String foodDenomination = "Food";
        String xikinhoEmail = "xikinho@isep.ipp.pt";
        PersonID firstPersonID = PersonID.createPersonID(xikinhoEmail);
        CategoryID firstCategoryID = CategoryID.createCategoryID(foodDenomination, firstPersonID);


        String healthDenomination = "Health";
        CategoryID secondCategoryID = CategoryID.createCategoryID(healthDenomination, firstPersonID);

        //Act
        boolean result = firstCategoryID.equals(secondCategoryID);

        //Assert
        assertEquals(false, result);
    }

    @Test
    @DisplayName("Test the CategoryID - equals() - False | Not Same Class ")
    public void testCategoryIDequalsFalseNotSameClass() {

        //Arrange
        String foodDenomination = "Food";
        String xikinhoEmail = "xikinho@isep.ipp.pt";
        PersonID personID = PersonID.createPersonID(xikinhoEmail);

        CategoryID categoryID = CategoryID.createCategoryID(foodDenomination, personID);
        String string = "Bug killer";


        //Act
        boolean result = categoryID.equals(string);

        //Assert
        assertEquals(false, result);
    }

    @Test
    @DisplayName("Test the CategoryID - equals() - False | Null ")
    public void testCategoryIDequalsFalseNull() {

        //Arrange
        String foodDenomination = "Food";
        String xikinhoEmail = "xikinho@isep.ipp.pt";
        PersonID personID = PersonID.createPersonID(xikinhoEmail);

        CategoryID categoryID = CategoryID.createCategoryID(foodDenomination, personID);

        //Act
        boolean result = categoryID.equals(null);

        //Assert
        assertEquals(false, result);
    }


    @Test
    @DisplayName("Test the CategoryID - hashCode() - True")
    public void testCategoryID_hashCode_True() {
        //Arrange
        String foodDenomination = "Food";
        String xikinhoEmail = "xikinho@isep.ipp.pt";
        PersonID personID = PersonID.createPersonID(xikinhoEmail);

        CategoryID firstCategoryID = CategoryID.createCategoryID(foodDenomination, personID);
        CategoryID secondCategoryID = CategoryID.createCategoryID(foodDenomination, personID);

        //Act
        int firstHash = firstCategoryID.hashCode();
        int secondHash = secondCategoryID.hashCode();

        //Assert
        assertEquals(firstHash, secondHash);
    }

    @Test
    @DisplayName("Test the CategoryID - hashCode() - False")
    public void testCategoryID_hashCode_False() {
        //Arrange
        String foodDenomination = "Food";
        String xikinhoEmail = "xikinho@isep.ipp.pt";
        PersonID firstPersonID = PersonID.createPersonID(xikinhoEmail);
        CategoryID firstCategoryID = CategoryID.createCategoryID(foodDenomination, firstPersonID);


        String johnEmail = "john@isep.ipp.pt";
        PersonID secondPersonID = PersonID.createPersonID(johnEmail);
        CategoryID secondCategoryID = CategoryID.createCategoryID(foodDenomination, secondPersonID);

        //Act
        int firstHash = firstCategoryID.hashCode();
        int secondHash = secondCategoryID.hashCode();

        //Assert
        assertNotEquals(firstHash, secondHash);
    }

    @Test
    @DisplayName("Test the CategoryID - Constructor - Exception: PersonID null")
    void testCategoryID_Constructor_Exception_PersonID_null() {
        //Arrange
        String foodDenomination = "Food";
        PersonID personID = null;

        //Act
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> CategoryID.createCategoryID(foodDenomination, personID));

        //Assert
        assertEquals("CategoryID not created due to the fact that the ownerID parameter hasn't a valid argument", thrown.getMessage());
    }

    @Test
    @DisplayName("Test the CategoryID - Constructor - Exception: GroupID null")
    void testCategoryID_Constructor_Exception_GroupID_null() {
        //Arrange
        String foodDenomination = "Food";
        GroupID groupID = null;

        //Act
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> CategoryID.createCategoryID(foodDenomination, groupID));

        //Assert
        assertEquals("CategoryID not created due to the fact that the ownerID parameter hasn't a valid argument", thrown.getMessage());
    }

    @Test
    @DisplayName("Test the CategoryID - Constructor - Exception: Person | Denomination null")
    void testCategoryID_Constructor_Exception_DenominationPerson_null() {
        //Arrange
        String foodDenomination = null;
        String xikinhoEmail = "xikinho@isep.ipp.pt";
        PersonID personID = PersonID.createPersonID(xikinhoEmail);

        //Act
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> CategoryID.createCategoryID(foodDenomination, personID));

        //Assert
        assertEquals("CategoryID not created due to the fact that the denomination parameter hasn't a valid argument", thrown.getMessage());
    }

    @Test
    @DisplayName("Test the CategoryID - Constructor - Exception: Geoup | Denomination null")
    void testCategoryID_Constructor_Exception_DenominationGroup_null() {
        //Arrange
        String foodDenomination = null;
        String groupDenomination = "Runners";
        GroupID groupID = GroupID.createGroupID(groupDenomination);

        //Act
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> CategoryID.createCategoryID(foodDenomination, groupID));

        //Assert
        assertEquals("CategoryID not created due to the fact that the denomination parameter hasn't a valid argument", thrown.getMessage());
    }

    @Test
    @DisplayName("Test the CategoryID - equals() - False | Person Group")
    public void testCategoryIDPersonGroupEqualsFalse() {

        //Arrange

        //Person
        String foodDenomination = "Food";
        String xikinhoEmail = "xikinho@isep.ipp.pt";
        PersonID personID = PersonID.createPersonID(xikinhoEmail);

        //Group
        String groupDenomination = "Runners";
        GroupID groupID = GroupID.createGroupID(groupDenomination);

        //Category

        CategoryID personCategoryID = CategoryID.createCategoryID(foodDenomination, personID);
        CategoryID groupCategoryID = CategoryID.createCategoryID(foodDenomination, groupID);


        //Act
        boolean result = personCategoryID.equals(groupCategoryID);

        //Assert
        assertEquals(false, result);
    }

    @Test
    @DisplayName("Test the CategoryID - equals() - False | Group Person")
    public void testCategoryIDGroupPersonEqualsFalse() {

        //Arrange

        //Person
        String foodDenomination = "Food";
        String xikinhoEmail = "xikinho@isep.ipp.pt";
        PersonID personID = PersonID.createPersonID(xikinhoEmail);

        //Group
        String groupDenomination = "Runners";
        GroupID groupID = GroupID.createGroupID(groupDenomination);

        //Category

        CategoryID personCategoryID = CategoryID.createCategoryID(foodDenomination, personID);
        CategoryID groupCategoryID = CategoryID.createCategoryID(foodDenomination, groupID);


        //Act
        boolean result = groupCategoryID.equals(personCategoryID);

        //Assert
        assertEquals(false, result);
    }

}