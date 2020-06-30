package com.finance.project.dtos.dtos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class NewGroupAccountInfoDTOTest {

    @Test
    @DisplayName("Test For NewGroupAccountInfoDTO()")
    void US07_DTO() {

        // Arrange
        String accountDenomination = "LakersAccount";
        String accountDescription = "Lakers Expenses";

        // Act
        NewGroupAccountInfoDTO newGroupAccountInfoDTO = new NewGroupAccountInfoDTO(accountDescription, accountDenomination);


        // Assert
        assertEquals(accountDenomination, newGroupAccountInfoDTO.getAccountDenomination());
        assertEquals(accountDescription, newGroupAccountInfoDTO.getAccountDescription());
    }

    @Test
    @DisplayName("Test for NewGroupAccountInfoDTO() - Empty Constructor")
    void NewGroupCategoryInfoDTO_EmptyConstructor() {

        // Arrange

        // Act
        NewGroupAccountInfoDTO newGroupAccountInfoDTO = new NewGroupAccountInfoDTO();

        // Assert
        assertEquals(null, newGroupAccountInfoDTO.getAccountDenomination());
        assertEquals(null, newGroupAccountInfoDTO.getAccountDescription());
    }

    @Test
    @DisplayName("Test For getAccountDenomination()")
    void getAccountDenomination() {

        // Arrange
        String accountDenomination = "LakersAccount";
        String accountDescription = "Lakers Expenses";

        // Act
        NewGroupAccountInfoDTO newGroupAccountInfoDTO = new NewGroupAccountInfoDTO(accountDescription, accountDenomination);

        // Assert
        assertEquals(accountDenomination, newGroupAccountInfoDTO.getAccountDenomination());
    }

    @Test
    @DisplayName("Test For getAccountDescription()")
    void getAccountDescription() {

        // Arrange
        String accountDenomination = "LakersAccount";
        String accountDescription = "Lakers Expenses";

        // Act
        NewGroupAccountInfoDTO newGroupAccountInfoDTO = new NewGroupAccountInfoDTO(accountDescription, accountDenomination);

        // Assert
        assertEquals(accountDescription, newGroupAccountInfoDTO.getAccountDescription());
    }

    @Test
    @DisplayName("Test For setAccountDenomination()")
    void setAccountDenomination() {

        // Arrange
        String accountDenomination = "LakersAccount";
        String accountDescription = "Lakers Expenses";

        // Act
        NewGroupAccountInfoDTO newGroupAccountInfoDTO = new NewGroupAccountInfoDTO(accountDescription, accountDenomination);
        newGroupAccountInfoDTO.setAccountDenomination("LalAccount");

        // Assert
        assertEquals("LalAccount", newGroupAccountInfoDTO.getAccountDenomination());
    }

    @Test
    @DisplayName("Test For setAccountDescription()")
    void setAccountDescription() {

        // Arrange
        String accountDenomination = "LakersAccount";
        String accountDescription = "Lakers Expenses";

        // Act
        NewGroupAccountInfoDTO newGroupAccountInfoDTO = new NewGroupAccountInfoDTO(accountDescription, accountDenomination);
        newGroupAccountInfoDTO.setAccountDescription("Lal Expenses");

        // Assert
        assertEquals("Lal Expenses", newGroupAccountInfoDTO.getAccountDescription());
    }

    //Equals

    @Test
    @DisplayName("Test For equals() | Success")
    void equalsSuccess() {

        // Arrange

        //First DTO
        String firstAccountDenomination = "LakersAccount";
        String firstAccountDescription = "Lakers Expenses";

        NewGroupAccountInfoDTO firstNewGroupAccountInfoDTO = new NewGroupAccountInfoDTO(firstAccountDescription, firstAccountDenomination);

        //Second DTO
        String secondAccountDenomination = "LakersAccount";
        String secondAccountDescription = "Lakers Expenses";

        NewGroupAccountInfoDTO secondNewGroupAccountInfoDTO = new NewGroupAccountInfoDTO(secondAccountDescription, secondAccountDenomination);

        // Act
        boolean result = firstNewGroupAccountInfoDTO.equals(secondNewGroupAccountInfoDTO);

        // Assert
        assertEquals(true, result);
    }

    @Test
    @DisplayName("Test For equals() | Success | Same Object")
    void equalsSuccessSameObject() {

        // Arrange

        //DTO
        String accountDenomination = "LakersAccount";
        String accountDescription = "Lakers Expenses";

        NewGroupAccountInfoDTO newGroupAccountInfoDTO = new NewGroupAccountInfoDTO(accountDescription, accountDenomination);

        // Act
        boolean result = newGroupAccountInfoDTO.equals(newGroupAccountInfoDTO);

        // Assert
        assertEquals(true, result);
    }

    @Test
    @DisplayName("Test For equals() | Fail | Account Denomination")
    void equalsFailAccountDenomination() {

        // Arrange

        //First DTO
        String firstAccountDenomination = "LakersAccount";
        String firstAccountDescription = "Lakers Expenses";

        NewGroupAccountInfoDTO firstNewGroupAccountInfoDTO = new NewGroupAccountInfoDTO(firstAccountDescription, firstAccountDenomination);

        //Second DTO
        String secondAccountDenomination = "CavaliersAccount";
        String secondAccountDescription = "Lakers Expenses";

        NewGroupAccountInfoDTO secondNewGroupAccountInfoDTO = new NewGroupAccountInfoDTO(secondAccountDescription, secondAccountDenomination);

        // Act
        boolean result = firstNewGroupAccountInfoDTO.equals(secondNewGroupAccountInfoDTO);

        // Assert
        assertEquals(false, result);
    }

    @Test
    @DisplayName("Test For equals() | Fail | Account Description")
    void equalsFailAccountDescription() {

        // Arrange

        //First DTO
        String firstAccountDenomination = "LakersAccount";
        String firstAccountDescription = "Lakers Expenses";

        NewGroupAccountInfoDTO firstNewGroupAccountInfoDTO = new NewGroupAccountInfoDTO(firstAccountDescription, firstAccountDenomination);

        //Second DTO
        String secondAccountDenomination = "LakersAccount";
        String secondAccountDescription = "Cavaliers Expenses";

        NewGroupAccountInfoDTO secondNewGroupAccountInfoDTO = new NewGroupAccountInfoDTO(secondAccountDescription, secondAccountDenomination);

        // Act
        boolean result = firstNewGroupAccountInfoDTO.equals(secondNewGroupAccountInfoDTO);

        // Assert
        assertEquals(false, result);
    }

    @Test
    @DisplayName("Test For equals() | Fail | Different Class")
    void equalsFailDifferentClass() {

        // Arrange

        //First DTO
        String accountDenomination = "LakersAccount";
        String accountDescription = "Lakers Expenses";

        NewGroupAccountInfoDTO newGroupAccountInfoDTO = new NewGroupAccountInfoDTO(accountDescription, accountDenomination);

        //Second DTO
        String bugKiller = "Bug killer";

        // Act
        boolean result = newGroupAccountInfoDTO.equals(bugKiller);

        // Assert
        assertEquals(false, result);
    }

    @Test
    @DisplayName("Test For equals() | Null")
    void equalsFailNull() {

        // Arrange

        //First DTO
        String accountDenomination = "LakersAccount";
        String accountDescription = "Lakers Expenses";

        NewGroupAccountInfoDTO newGroupAccountInfoDTO = new NewGroupAccountInfoDTO(accountDescription, accountDenomination);

        // Act
        boolean result = newGroupAccountInfoDTO.equals(null);

        // Assert
        assertEquals(false, result);
    }

    //Hashcode

    @Test
    @DisplayName("Test For hashcode() | Success")
    void hashcodeSuccess() {

        // Arrange

        //First DTO
        String firstAccountDenomination = "LakersAccount";
        String firstAccountDescription = "Lakers Expenses";

        NewGroupAccountInfoDTO firstNewGroupAccountInfoDTO = new NewGroupAccountInfoDTO(firstAccountDenomination, firstAccountDescription);

        //Second DTO
        String secondAccountDenomination = "LakersAccount";
        String secondAccountDescription = "Lakers Expenses";

        NewGroupAccountInfoDTO secondNewGroupAccountInfoDTO = new NewGroupAccountInfoDTO(secondAccountDenomination, secondAccountDescription);

        // Act
        int firstHashcode = firstNewGroupAccountInfoDTO.hashCode();
        int secondHashcode = secondNewGroupAccountInfoDTO.hashCode();

        // Assert
        assertEquals(firstHashcode, secondHashcode);
    }


    @Test
    @DisplayName("Test For hashcode() | Success - expected hashcode")
    void hashCodeSuccessExpectedHashcode() {

        // Arrange

        //DTO
        String accountDenomination = "LakersAccount";
        String accountDescription = "Lakers Expenses";

        NewGroupAccountInfoDTO newGroupAccountInfoDTO = new NewGroupAccountInfoDTO(accountDenomination, accountDescription);

        // Act
        int lakersHash = newGroupAccountInfoDTO.hashCode();
        int expectedHash = 10811347;

        // Assert
        assertEquals(expectedHash, lakersHash);
    }

    @Test
    @DisplayName("Test For hashcode() | Different")
    void hashcodeDifferent() {

        // Arrange

        //First DTO
        String firstAccountDenomination = "LakersAccount";
        String firstAccountDescription = "Lakers Expenses";

        NewGroupAccountInfoDTO firstNewGroupAccountInfoDTO = new NewGroupAccountInfoDTO(firstAccountDenomination, firstAccountDescription);

        //Second DTO
        String secondAccountDenomination = "BucksAccount";
        String secondAccountDescription = "Bucks Expenses";

        NewGroupAccountInfoDTO secondNewGroupAccountInfoDTO = new NewGroupAccountInfoDTO(secondAccountDenomination, secondAccountDescription);

        // Act
        int firstHashcode = firstNewGroupAccountInfoDTO.hashCode();
        int secondHashcode = secondNewGroupAccountInfoDTO.hashCode();

        // Assert
        assertNotEquals(firstHashcode, secondHashcode);
    }
}