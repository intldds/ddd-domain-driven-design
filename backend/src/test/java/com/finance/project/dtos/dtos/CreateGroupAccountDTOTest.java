package com.finance.project.dtos.dtos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CreateGroupAccountDTOTest {

    @Test
    @DisplayName("Test For US07_DTO()")
    void US07_DTO() {

        // Arrange
        String personEmail = "lebron@gmail.com";
        String groupDenomination = "Lakers";
        String accountDenomination = "LakersAccount";
        String accountDescription = "Lakers Expenses";

        // Act
        CreateGroupAccountDTO createGroupAccountDTO = new CreateGroupAccountDTO(personEmail, groupDenomination, accountDescription, accountDenomination);

        // Assert
        assertEquals(personEmail, createGroupAccountDTO.getPersonEmail());
        assertEquals(groupDenomination, createGroupAccountDTO.getGroupDenomination());
        assertEquals(accountDenomination, createGroupAccountDTO.getAccountDenomination());
        assertEquals(accountDescription, createGroupAccountDTO.getAccountDescription());
    }

    @Test
    @DisplayName("Test for empty constructor")
    void Empty_Constructor() {

        //Arrange

        //Act
        CreateGroupAccountDTO createGroupAccountDTO = new CreateGroupAccountDTO();

        //Assert
        assertEquals(null, createGroupAccountDTO.getAccountDenomination());
        assertEquals(null, createGroupAccountDTO.getAccountDescription());
        assertEquals(null, createGroupAccountDTO.getGroupDenomination());
        assertEquals(null, createGroupAccountDTO.getPersonEmail());
    }

    @Test
    @DisplayName("Test For getAccountDenomination()")
    void getAccountDenomination() {

        // Arrange
        String personEmail = "lebron@gmail.com";
        String groupDenomination = "Lakers";
        String accountDenomination = "LakersAccount";
        String accountDescription = "Lakers Expenses";

        // Act
        CreateGroupAccountDTO createGroupAccountDTO = new CreateGroupAccountDTO(personEmail, groupDenomination, accountDescription, accountDenomination);

        // Assert
        assertEquals(accountDenomination, createGroupAccountDTO.getAccountDenomination());
    }

    @Test
    @DisplayName("Test For getAccountDescription()")
    void getAccountDescription() {

        // Arrange
        String personEmail = "lebron@gmail.com";
        String groupDenomination = "Lakers";
        String accountDenomination = "LakersAccount";
        String accountDescription = "Lakers Expenses";

        // Act
        CreateGroupAccountDTO createGroupAccountDTO = new CreateGroupAccountDTO(personEmail, groupDenomination, accountDescription, accountDenomination);

        // Assert
        assertEquals(accountDescription, createGroupAccountDTO.getAccountDescription());
    }

    @Test
    @DisplayName("Test For getGroupDenomination()")
    void getGroupDenomination() {

        // Arrange
        String personEmail = "lebron@gmail.com";
        String groupDenomination = "Lakers";
        String accountDenomination = "LakersAccount";
        String accountDescription = "Lakers Expenses";

        // Act
        CreateGroupAccountDTO createGroupAccountDTO = new CreateGroupAccountDTO(personEmail, groupDenomination, accountDescription, accountDenomination);

        // Assert
        assertEquals(groupDenomination, createGroupAccountDTO.getGroupDenomination());
    }

    @Test
    @DisplayName("Test For getPersonEmail()")
    void getPersonEmail() {

        // Arrange
        String personEmail = "lebron@gmail.com";
        String groupDenomination = "Lakers";
        String accountDenomination = "LakersAccount";
        String accountDescription = "Lakers Expenses";

        // Act
        CreateGroupAccountDTO createGroupAccountDTO = new CreateGroupAccountDTO(personEmail, groupDenomination, accountDescription, accountDenomination);

        // Assert
        assertEquals(personEmail, createGroupAccountDTO.getPersonEmail());
    }

    //Equals

    @Test
    @DisplayName("Test For equals() | Success")
    void equalsSuccess() {

        // Arrange

        //First DTO
        String firstPersonEmail = "lebron@gmail.com";
        String firstGroupDenomination = "Lakers";
        String firstAccountDenomination = "LakersAccount";
        String firstAccountDescription = "Lakers Expenses";

        CreateGroupAccountDTO firstCreateGroupAccountDTO = new CreateGroupAccountDTO(firstPersonEmail, firstGroupDenomination, firstAccountDescription, firstAccountDenomination);

        //Second DTO
        String secondPersonEmail = "lebron@gmail.com";
        String secondGroupDenomination = "Lakers";
        String secondAccountDenomination = "LakersAccount";
        String secondAccountDescription = "Lakers Expenses";

        CreateGroupAccountDTO secondCreateGroupAccountDTO = new CreateGroupAccountDTO(secondPersonEmail, secondGroupDenomination, secondAccountDescription, secondAccountDenomination);

        // Act
        boolean result = firstCreateGroupAccountDTO.equals(secondCreateGroupAccountDTO);

        // Assert
        assertEquals(true, result);
    }

    @Test
    @DisplayName("Test For equals() | Success | Same Object")
    void equalsSuccessSameObject() {

        // Arrange

        //First DTO
        String firstPersonEmail = "lebron@gmail.com";
        String firstGroupDenomination = "Lakers";
        String firstAccountDenomination = "LakersAccount";
        String firstAccountDescription = "Lakers Expenses";

        CreateGroupAccountDTO firstCreateGroupAccountDTO = new CreateGroupAccountDTO(firstPersonEmail, firstGroupDenomination, firstAccountDescription, firstAccountDenomination);

        // Act
        boolean result = firstCreateGroupAccountDTO.equals(firstCreateGroupAccountDTO);

        // Assert
        assertEquals(true, result);
    }

    @Test
    @DisplayName("Test For equals() | Fail | Email")
    void equalsFailEmail() {

        // Arrange

        //First DTO
        String firstPersonEmail = "lebron@gmail.com";
        String firstGroupDenomination = "Lakers";
        String firstAccountDenomination = "LakersAccount";
        String firstAccountDescription = "Lakers Expenses";

        CreateGroupAccountDTO firstCreateGroupAccountDTO = new CreateGroupAccountDTO(firstPersonEmail, firstGroupDenomination, firstAccountDescription, firstAccountDenomination);

        //Second DTO
        String secondPersonEmail = "davis@gmail.com";
        String secondGroupDenomination = "Lakers";
        String secondAccountDenomination = "LakersAccount";
        String secondAccountDescription = "Lakers Expenses";

        CreateGroupAccountDTO secondCreateGroupAccountDTO = new CreateGroupAccountDTO(secondPersonEmail, secondGroupDenomination, secondAccountDescription, secondAccountDenomination);

        // Act
        boolean result = firstCreateGroupAccountDTO.equals(secondCreateGroupAccountDTO);

        // Assert
        assertEquals(false, result);
    }

    @Test
    @DisplayName("Test For equals() | Fail | Group Denomination")
    void equalsFailGroupDenomination() {

        // Arrange

        //First DTO
        String firstPersonEmail = "lebron@gmail.com";
        String firstGroupDenomination = "Lakers";
        String firstAccountDenomination = "LakersAccount";
        String firstAccountDescription = "Lakers Expenses";

        CreateGroupAccountDTO firstCreateGroupAccountDTO = new CreateGroupAccountDTO(firstPersonEmail, firstGroupDenomination, firstAccountDescription, firstAccountDenomination);

        //Second DTO
        String secondPersonEmail = "lebron@gmail.com";
        String secondGroupDenomination = "Cavaliers";
        String secondAccountDenomination = "LakersAccount";
        String secondAccountDescription = "Lakers Expenses";

        CreateGroupAccountDTO secondCreateGroupAccountDTO = new CreateGroupAccountDTO(secondPersonEmail, secondGroupDenomination, secondAccountDescription, secondAccountDenomination);

        // Act
        boolean result = firstCreateGroupAccountDTO.equals(secondCreateGroupAccountDTO);

        // Assert
        assertEquals(false, result);
    }

    @Test
    @DisplayName("Test For equals() | Fail | Account Denomination")
    void equalsFailAccountDenomination() {

        // Arrange

        //First DTO
        String firstPersonEmail = "lebron@gmail.com";
        String firstGroupDenomination = "Lakers";
        String firstAccountDenomination = "LakersAccount";
        String firstAccountDescription = "Lakers Expenses";

        CreateGroupAccountDTO firstCreateGroupAccountDTO = new CreateGroupAccountDTO(firstPersonEmail, firstGroupDenomination, firstAccountDescription, firstAccountDenomination);

        //Second DTO
        String secondPersonEmail = "lebron@gmail.com";
        String secondGroupDenomination = "Lakers";
        String secondAccountDenomination = "CavaliersAccount";
        String secondAccountDescription = "Lakers Expenses";

        CreateGroupAccountDTO secondCreateGroupAccountDTO = new CreateGroupAccountDTO(secondPersonEmail, secondGroupDenomination, secondAccountDescription, secondAccountDenomination);

        // Act
        boolean result = firstCreateGroupAccountDTO.equals(secondCreateGroupAccountDTO);

        // Assert
        assertEquals(false, result);
    }

    @Test
    @DisplayName("Test For equals() | Fail | Account Description")
    void equalsFailAccountDescription() {

        // Arrange

        //First DTO
        String firstPersonEmail = "lebron@gmail.com";
        String firstGroupDenomination = "Lakers";
        String firstAccountDenomination = "LakersAccount";
        String firstAccountDescription = "Lakers Expenses";

        CreateGroupAccountDTO firstCreateGroupAccountDTO = new CreateGroupAccountDTO(firstPersonEmail, firstGroupDenomination, firstAccountDescription, firstAccountDenomination);

        //Second DTO
        String secondPersonEmail = "lebron@gmail.com";
        String secondGroupDenomination = "Lakers";
        String secondAccountDenomination = "LakersAccount";
        String secondAccountDescription = "Cavaliers Expenses";

        CreateGroupAccountDTO secondCreateGroupAccountDTO = new CreateGroupAccountDTO(secondPersonEmail, secondGroupDenomination, secondAccountDescription, secondAccountDenomination);

        // Act
        boolean result = firstCreateGroupAccountDTO.equals(secondCreateGroupAccountDTO);

        // Assert
        assertEquals(false, result);
    }

    @Test
    @DisplayName("Test For equals() | Fail | Different Class")
    void equalsFailDifferentClass() {

        // Arrange

        //First DTO
        String firstPersonEmail = "lebron@gmail.com";
        String firstGroupDenomination = "Lakers";
        String firstAccountDenomination = "LakersAccount";
        String firstAccountDescription = "Lakers Expenses";

        CreateGroupAccountDTO firstCreateGroupAccountDTO = new CreateGroupAccountDTO(firstPersonEmail, firstGroupDenomination, firstAccountDescription, firstAccountDenomination);

        //Second DTO
        String bugKiller = "Bug killer";

        // Act
        boolean result = firstCreateGroupAccountDTO.equals(bugKiller);

        // Assert
        assertEquals(false, result);
    }

    @Test
    @DisplayName("Test For equals() | Fail | Null")
    void equalsFailNull() {

        // Arrange

        //First DTO
        String firstPersonEmail = "lebron@gmail.com";
        String firstGroupDenomination = "Lakers";
        String firstAccountDenomination = "LakersAccount";
        String firstAccountDescription = "Lakers Expenses";

        CreateGroupAccountDTO firstCreateGroupAccountDTO = new CreateGroupAccountDTO(firstPersonEmail, firstGroupDenomination, firstAccountDescription, firstAccountDenomination);

        // Act
        boolean result = firstCreateGroupAccountDTO.equals(null);

        // Assert
        assertEquals(false, result);
    }

    //Hashcode

    @Test
    @DisplayName("Test For hashcode() | Success - expected hashcode ")
    void hashCodeSuccess() {

        // Arrange

        //Lakers
        String lakersPersonEmail = "lebron@gmail.com";
        String lakersGroupDenomination = "Lakers";
        String lakersAccountDenomination = "LakersAccount";
        String lakersAccountDescription = "Lakers Expenses";

        //DTO
        CreateGroupAccountDTO lakersCreateGroupAccountDTO = new CreateGroupAccountDTO(lakersPersonEmail, lakersGroupDenomination, lakersAccountDenomination, lakersAccountDescription);

        // Act
        int lakersHash = lakersCreateGroupAccountDTO.hashCode();
        int expectedHash = -904210934;

        // Assert
        assertEquals(expectedHash, lakersHash);
    }

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
