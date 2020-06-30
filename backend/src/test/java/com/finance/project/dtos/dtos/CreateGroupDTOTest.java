package com.finance.project.dtos.dtos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CreateGroupDTOTest {

    @Test
    @DisplayName("Test US02_1_DTO constructor || Happy case")
    void testUS02_1_DTConstructor() {

        //Arrange
        String email = "maria@gmail.com";
        String denomination = "Friends";
        String description = "Old friends from school";

        //Act

        CreateGroupDTO createGroupDTO = new CreateGroupDTO(email, denomination, description);

        //Assert

        assertEquals(email, createGroupDTO.getEmail());
        assertEquals(denomination, createGroupDTO.getDenomination());
        assertEquals(description, createGroupDTO.getDescription());
        assertEquals(createGroupDTO, createGroupDTO);
        assertNotEquals(email, createGroupDTO);
    }

    @Test
    @DisplayName("Test US02_1_DTO constructor2|| Happy case")
    void testUS02_1_DTConstructor2() {

        //Arrange
        String email = "maria@gmail.com";
        String denomination = "Friends";
        String personInCharge = "joana@gmail.com";
        String description = "Old friends from school";

        //Act

        CreateGroupDTO createGroupDTO = new CreateGroupDTO(email, denomination, personInCharge, description);

        String ledgerID = createGroupDTO.getLedgerID();
        String dateOfCreation = createGroupDTO.getDateOfCreation();

        //Assert

        assertEquals(email, createGroupDTO.getEmail());
        assertEquals(denomination, createGroupDTO.getDenomination());
        assertEquals(personInCharge,createGroupDTO.getPersonInCharge());
        assertEquals(dateOfCreation,createGroupDTO.getDateOfCreation());
        assertEquals(ledgerID,createGroupDTO.getLedgerID());
        assertEquals(description, createGroupDTO.getDescription());
        assertEquals(createGroupDTO, createGroupDTO);
        assertNotEquals(email, createGroupDTO);
    }

    @Test
    @DisplayName("Test US02_1_DTO constructor2|| Get dateOfCreation and LedgerID")
    void testUS02_1_GetDateOfCreationAndLedgerID() {

        //Arrange
        String email = "maria@gmail.com";
        String denomination = "Friends";
        String personInCharge = "joana@gmail.com";
        String description = "Old friends from school";
        String dateOfCreation = LocalDate.now().toString();
        String ledgerID = UUID.randomUUID().toString();


        //Act

        CreateGroupDTO createGroupDTO = new CreateGroupDTO(email, denomination, personInCharge, description);
        createGroupDTO.setDateOfCreation(dateOfCreation);
        createGroupDTO.setLedgerID(ledgerID);

        //Assert

        assertEquals(email, createGroupDTO.getEmail());
        assertEquals(denomination, createGroupDTO.getDenomination());
        assertEquals(personInCharge,createGroupDTO.getPersonInCharge());
        assertEquals(dateOfCreation,createGroupDTO.getDateOfCreation());
        assertEquals(ledgerID,createGroupDTO.getLedgerID());
        assertEquals(description, createGroupDTO.getDescription());
        assertEquals(createGroupDTO, createGroupDTO);
        assertNotEquals(email, createGroupDTO);
    }


    @Test
    @DisplayName("Equals || Same object")
    void testEquals_SameObject() {

        //Arrange
        String email = "maria@gmail.com";
        String denomination = "Friends";
        String description = "Old friends from school";

        //Act

        CreateGroupDTO createGroupDTO = new CreateGroupDTO(email, denomination, description);

        //Assert

        assertTrue(createGroupDTO.equals(createGroupDTO));
    }

    @Test
    @DisplayName("Equals || Not Instance Of")
    void testEquals_NotInstanceOf() {

        //Arrange
        String email = "maria@gmail.com";
        String denomination = "Friends";
        String description = "Old friends from school";

        //Act

        CreateGroupDTO createGroupDTO = new CreateGroupDTO(email, denomination, description);

        //

        String bugKiller = "Bug Killer";

        //Assert

        assertFalse(createGroupDTO.equals(bugKiller));

    }

    @Test
    @DisplayName("Equals || Different Objects - Same Information")
    void testEquals_DifferentObjects_Same_Information() {

        //Arrange
        String email = "maria@gmail.com";
        String denomination = "Friends";
        String description = "Old friends from school";

        //Act

        CreateGroupDTO createGroupDTO = new CreateGroupDTO(email, denomination, description);
        CreateGroupDTO createGroupDTO1 = new CreateGroupDTO(email, denomination, description);

        //Assert

        boolean result = createGroupDTO.equals(createGroupDTO1);

        assertTrue(createGroupDTO.equals(createGroupDTO1));
        assertEquals(true, result);

    }

    @Test
    @DisplayName("Equals || Different Objects - Sucess")
    void testEquals_DifferentObjects_Sucess() {

        //Arrange GroupA
        String email = "maria@gmail.com";
        String denomination = "Friends";
        String description = "Old friends from school";


        //Arrange GroupB
        String emailB = "maria@gmail.com";
        String denominationB = "Friends";
        String descriptionB = "Old friends from school";

        //Act

        CreateGroupDTO createGroupDTO = new CreateGroupDTO(email, denomination, description);
        CreateGroupDTO createGroupDTO1 = new CreateGroupDTO(emailB, denominationB, descriptionB);

        //Assert

        boolean result = createGroupDTO.equals(createGroupDTO1);

        assertTrue(createGroupDTO.equals(createGroupDTO1));
        assertEquals(true, result);

    }

    @Test
    @DisplayName("Equals || Different Objects - Different Information- description")
    void testEquals_DifferentObjects_Different_Information_description() {

        //Arrange
        String email = "joana@gmail.com";
        String denomination = "Family";
        String description = "Old friends from school";

        String emailTest = "joana@gmail.com";
        String denominationTest = "Family";
        String descriptionTest = "The best family";

        //Act

        CreateGroupDTO createGroupDTO = new CreateGroupDTO(email, denomination, description);
        CreateGroupDTO createGroupDTO1 = new CreateGroupDTO(emailTest, denominationTest, descriptionTest);

        //Assert

        assertFalse(createGroupDTO.equals(createGroupDTO1));

    }

    @Test
    @DisplayName("Equals || Different Objects - Different Information-email")
    void testEquals_DifferentObjects_Different_Information_email() {

        //Arrange
        String email = "maria@gmail.com";
        String denomination = "Friends";
        String description = "Old friends from school";

        String emailTest = "joana@gmail.com";
        String denominationTest = "Friends";
        String descriptionTest = "Old friends from school";

        //Act

        CreateGroupDTO createGroupDTO = new CreateGroupDTO(email, denomination, description);
        CreateGroupDTO createGroupDTO1 = new CreateGroupDTO(emailTest, denominationTest, descriptionTest);

        //Assert

        assertFalse(createGroupDTO.equals(createGroupDTO1));

    }

    @Test
    @DisplayName("Equals || Different Objects - Different Information - denomination")
    void testEquals_DifferentObjects_Different_Information_denomination() {

        //Arrange
        String email = "joana@gmail.com";
        String denomination = "Friends";
        String description = "The best family";

        String emailTest = "joana@gmail.com";
        String denominationTest = "Family";
        String descriptionTest = "The best family";

        //Act

        CreateGroupDTO createGroupDTO = new CreateGroupDTO(email, denomination, description);
        CreateGroupDTO createGroupDTO1 = new CreateGroupDTO(emailTest, denominationTest, descriptionTest);

        //Assert

        assertFalse(createGroupDTO.equals(createGroupDTO1));

    }

    @Test
    @DisplayName("Equals || Different Objects - Null")
    void testEquals_DifferentObjects_Null() {

        //Arrange
        String email = "maria@gmail.com";
        String denomination = "Friends";
        String description = "Old friends from school";


        //Act

        CreateGroupDTO createGroupDTO = new CreateGroupDTO(email, denomination, description);
        CreateGroupDTO createGroupDTO1 = null;

        boolean result = createGroupDTO.equals(createGroupDTO1);

        //Assert

        assertFalse(createGroupDTO.equals(createGroupDTO1));
        assertEquals(false, result);

    }

    //HashCode

    @Test
    @DisplayName("Test For US05_1_DTO() | Success")
    void hashCodeSuccess() {

        //Arrange
        String email = "maria@gmail.com";
        String denomination = "Friends";
        String description = "Old friends from school";

        //Act

        CreateGroupDTO createGroupDTO = new CreateGroupDTO(email, denomination, description);

        // Act

        int runnersHash = createGroupDTO.hashCode();
        int expectedHash = -620734409;

        // Assert

        assertEquals(expectedHash, runnersHash);
    }
}