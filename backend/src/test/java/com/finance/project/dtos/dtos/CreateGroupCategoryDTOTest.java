package com.finance.project.dtos.dtos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateGroupCategoryDTOTest {

    @Test
    @DisplayName("Test For US05_1_DTO()")
    void US05_1_DTO() {

        // Arrange

        String personEmail = "paulo@gmail.com";
        String groupDenomination = "Runners";
        String categoryDenomination = "Equipment";

        // Act

        CreateGroupCategoryDTO createGroupCategoryDTO = new CreateGroupCategoryDTO(personEmail, groupDenomination, categoryDenomination);


        // Assert

        assertEquals(personEmail, createGroupCategoryDTO.getPersonEmail());
        assertEquals(groupDenomination, createGroupCategoryDTO.getGroupDenomination());
        assertEquals(categoryDenomination, createGroupCategoryDTO.getCategoryDenomination());
    }

    @Test
    @DisplayName("Test For getCategoryDenomination()")
    void getCategoryDenomination() {

        // Arrange

        String personEmail = "paulo@gmail.com";
        String groupDenomination = "Runners";
        String categoryDenomination = "Equipment";

        // Act

        CreateGroupCategoryDTO createGroupCategoryDTO = new CreateGroupCategoryDTO(personEmail, groupDenomination, categoryDenomination);


        // Assert

        assertEquals(categoryDenomination, createGroupCategoryDTO.getCategoryDenomination());
    }

    @Test
    @DisplayName("Test For getGroupDenomination()")
    void getGroupDenomination() {

        // Arrange

        String personEmail = "paulo@gmail.com";
        String groupDenomination = "Runners";
        String categoryDenomination = "Equipment";

        // Act

        CreateGroupCategoryDTO createGroupCategoryDTO = new CreateGroupCategoryDTO(personEmail, groupDenomination, categoryDenomination);


        // Assert

        assertEquals(groupDenomination, createGroupCategoryDTO.getGroupDenomination());
    }

    @Test
    @DisplayName("Test For getPersonEmail()")
    void getPersonEmail() {

        // Arrange

        String personEmail = "paulo@gmail.com";
        String groupDenomination = "Runners";
        String categoryDenomination = "Equipment";

        // Act

        CreateGroupCategoryDTO createGroupCategoryDTO = new CreateGroupCategoryDTO(personEmail, groupDenomination, categoryDenomination);


        // Assert

        assertEquals(personEmail, createGroupCategoryDTO.getPersonEmail());
    }

    @Test
    @DisplayName("Test For equals() | Success")
    void equalsSuccess() {

        // Arrange

        //First DTO

        String firstPersonEmail = "paulo@gmail.com";
        String firstGroupDenomination = "Runners";
        String firstCategoryDenomination = "Equipment";
        CreateGroupCategoryDTO firstCreateGroupCategoryDTO = new CreateGroupCategoryDTO(firstPersonEmail, firstGroupDenomination, firstCategoryDenomination);

        //Second DTO
        String secondPersonEmail = "paulo@gmail.com";
        String secondGroupDenomination = "Runners";
        String secondCategoryDenomination = "Equipment";
        CreateGroupCategoryDTO secondCreateGroupCategoryDTO = new CreateGroupCategoryDTO(secondPersonEmail, secondGroupDenomination, secondCategoryDenomination);

        // Act

        boolean result = firstCreateGroupCategoryDTO.equals(secondCreateGroupCategoryDTO);

        // Assert

        assertEquals(true, result);
    }

    @Test
    @DisplayName("Test For equals() | Success | Same Object")
    void equalsSuccessSameObject() {

        // Arrange

        //First DTO

        String firstPersonEmail = "paulo@gmail.com";
        String firstGroupDenomination = "Runners";
        String firstCategoryDenomination = "Equipment";
        CreateGroupCategoryDTO firstCreateGroupCategoryDTO = new CreateGroupCategoryDTO(firstPersonEmail, firstGroupDenomination, firstCategoryDenomination);

        // Act

        boolean result = firstCreateGroupCategoryDTO.equals(firstCreateGroupCategoryDTO);

        // Assert

        assertEquals(true, result);
    }

    @Test
    @DisplayName("Test For equals() | Fail | Email")
    void equalsFailEmail() {

        // Arrange

        //First DTO

        String firstPersonEmail = "paulo@gmail.com";
        String firstGroupDenomination = "Runners";
        String firstCategoryDenomination = "Equipment";
        CreateGroupCategoryDTO firstCreateGroupCategoryDTO = new CreateGroupCategoryDTO(firstPersonEmail, firstGroupDenomination, firstCategoryDenomination);

        //Second DTO
        String secondPersonEmail = "joao@gmail.com";
        String secondGroupDenomination = "Runners";
        String secondCategoryDenomination = "Equipment";
        CreateGroupCategoryDTO secondCreateGroupCategoryDTO = new CreateGroupCategoryDTO(secondPersonEmail, secondGroupDenomination, secondCategoryDenomination);

        // Act

        boolean result = firstCreateGroupCategoryDTO.equals(secondCreateGroupCategoryDTO);

        // Assert

        assertEquals(false, result);
    }

    @Test
    @DisplayName("Test For equals() | Fail | Group Denomination")
    void equalsFailGroupDenomination() {

        // Arrange

        //First DTO

        String firstPersonEmail = "paulo@gmail.com";
        String firstGroupDenomination = "Runners";
        String firstCategoryDenomination = "Equipment";
        CreateGroupCategoryDTO firstCreateGroupCategoryDTO = new CreateGroupCategoryDTO(firstPersonEmail, firstGroupDenomination, firstCategoryDenomination);

        //Second DTO
        String secondPersonEmail = "paulo@gmail.com";
        String secondGroupDenomination = "Food";
        String secondCategoryDenomination = "Equipment";
        CreateGroupCategoryDTO secondCreateGroupCategoryDTO = new CreateGroupCategoryDTO(secondPersonEmail, secondGroupDenomination, secondCategoryDenomination);

        // Act

        boolean result = firstCreateGroupCategoryDTO.equals(secondCreateGroupCategoryDTO);

        // Assert

        assertEquals(false, result);
    }

    @Test
    @DisplayName("Test For equals() | Fail | Category Denomination")
    void equalsFailCategoryDenomination() {

        // Arrange

        //First DTO

        String firstPersonEmail = "paulo@gmail.com";
        String firstGroupDenomination = "Runners";
        String firstCategoryDenomination = "Equipment";
        CreateGroupCategoryDTO firstCreateGroupCategoryDTO = new CreateGroupCategoryDTO(firstPersonEmail, firstGroupDenomination, firstCategoryDenomination);

        //Second DTO
        String secondPersonEmail = "paulo@gmail.com";
        String secondGroupDenomination = "Runners";
        String secondCategoryDenomination = "Materials";
        CreateGroupCategoryDTO secondCreateGroupCategoryDTO = new CreateGroupCategoryDTO(secondPersonEmail, secondGroupDenomination, secondCategoryDenomination);

        // Act

        boolean result = firstCreateGroupCategoryDTO.equals(secondCreateGroupCategoryDTO);

        // Assert

        assertEquals(false, result);
    }

    @Test
    @DisplayName("Test For equals() | Fail | Different Class")
    void equalsFailDifferentClass() {

        // Arrange

        //First DTO

        String firstPersonEmail = "paulo@gmail.com";
        String firstGroupDenomination = "Runners";
        String firstCategoryDenomination = "Equipment";
        CreateGroupCategoryDTO firstCreateGroupCategoryDTO = new CreateGroupCategoryDTO(firstPersonEmail, firstGroupDenomination, firstCategoryDenomination);

        //Second DTO
        String bugKiller = "Bug killer";

        // Act

        boolean result = firstCreateGroupCategoryDTO.equals(bugKiller);

        // Assert

        assertEquals(false, result);
    }

    @Test
    @DisplayName("Test For equals() | Fail | Null")
    void equalsFailNull() {

        // Arrange

        //First DTO

        String firstPersonEmail = "paulo@gmail.com";
        String firstGroupDenomination = "Runners";
        String firstCategoryDenomination = "Equipment";
        CreateGroupCategoryDTO firstCreateGroupCategoryDTO = new CreateGroupCategoryDTO(firstPersonEmail, firstGroupDenomination, firstCategoryDenomination);

        // Act

        boolean result = firstCreateGroupCategoryDTO.equals(null);

        // Assert

        assertEquals(false, result);
    }

    @Test
    @DisplayName("Test For US05_1_DTO() | Success")
    void hashCodeSuccess() {

        // Arrange

        //Runners

        String runnersPersonEmail = "paulo@gmail.com";
        String runnersGroupDenomination = "Runners";
        String runnersCategoryDenomination = "Equipment";

        //DTO
        CreateGroupCategoryDTO runnersCreateGroupCategoryDTO = new CreateGroupCategoryDTO(runnersPersonEmail, runnersGroupDenomination, runnersCategoryDenomination);


        // Act

        int runnersHash = runnersCreateGroupCategoryDTO.hashCode();
        int expectedHash = -1150789790;

        // Assert

        assertEquals(expectedHash, runnersHash);
    }

}