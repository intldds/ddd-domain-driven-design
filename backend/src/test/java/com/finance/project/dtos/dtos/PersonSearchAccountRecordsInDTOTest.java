package com.finance.project.dtos.dtos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PersonSearchAccountRecordsInDTOTest {

    @Test
    @DisplayName("Test DTO in constructor")
    void constructorTest() {

        // Arrange
        String personEmail = "paulo@gmail.com";
        String denominationAccount = "EDP";
        String startDate = "2020-01-05";
        String endDate = "2020-02-10";

        // Act
        PersonSearchAccountRecordsInDTO personSearchAccountRecordsInDTO = new PersonSearchAccountRecordsInDTO(personEmail, denominationAccount, startDate, endDate);

        // Assert
        assertEquals(personEmail, personSearchAccountRecordsInDTO.getPersonEmail());
        assertEquals(denominationAccount, personSearchAccountRecordsInDTO.getAccountDenomination());
        assertEquals(startDate, personSearchAccountRecordsInDTO.getStartDate());
        assertEquals(endDate, personSearchAccountRecordsInDTO.getEndDate());
    }

    @Test
    @DisplayName("Test DTO in getters - getPersonEmail()")
    void getPersonEmailTest() {

        // Arrange
        String personEmail = "paulo@gmail.com";
        String denominationAccount = "EDP";
        String startDate = "2020-01-05";
        String endDate = "2020-02-10";

        // Act
        PersonSearchAccountRecordsInDTO personSearchAccountRecordsInDTO = new PersonSearchAccountRecordsInDTO(personEmail, denominationAccount, startDate, endDate);

        // Assert
        assertEquals(personEmail, personSearchAccountRecordsInDTO.getPersonEmail());
    }

    @Test
    @DisplayName("Test DTO in getters - getAccountDenomination()")
    void getAccountDenominationTest() {

        // Arrange
        String personEmail = "paulo@gmail.com";
        String denominationAccount = "EDP";
        String startDate = "2020-01-05";
        String endDate = "2020-02-10";

        // Act
        PersonSearchAccountRecordsInDTO personSearchAccountRecordsInDTO = new PersonSearchAccountRecordsInDTO(personEmail, denominationAccount, startDate, endDate);

        // Assert
        assertEquals(denominationAccount, personSearchAccountRecordsInDTO.getAccountDenomination());
    }

    @Test
    @DisplayName("Test DTO in getters - getStartDate()")
    void getStartDateTest() {

        // Arrange
        String personEmail = "paulo@gmail.com";
        String denominationAccount = "EDP";
        String startDate = "2020-01-05";
        String endDate = "2020-02-10";

        // Act
        PersonSearchAccountRecordsInDTO personSearchAccountRecordsInDTO = new PersonSearchAccountRecordsInDTO(personEmail, denominationAccount, startDate, endDate);

        // Assert
        assertEquals(startDate, personSearchAccountRecordsInDTO.getStartDate());
    }

    @Test
    @DisplayName("Test DTO getters - getEndDate()")
    void getEndDateTest() {

        // Arrange
        String personEmail = "paulo@gmail.com";
        String denominationAccount = "EDP";
        String startDate = "2020-01-05";
        String endDate = "2020-02-10";

        // Act
        PersonSearchAccountRecordsInDTO personSearchAccountRecordsInDTO = new PersonSearchAccountRecordsInDTO(personEmail, denominationAccount, startDate, endDate);

        //Assert
        assertEquals(endDate, personSearchAccountRecordsInDTO.getEndDate());
    }

    @Test
    @DisplayName("Test for equals() and hasCode() - Happy path")
    void testEqualsAndHashCode_HappyPath() {

        // Arrange
        String personEmail1 = "paulo@gmail.com";
        String denominationAccount1 = "EDP";
        String startDate1 = "2020-01-05";
        String endDate1 = "2020-02-10";
        PersonSearchAccountRecordsInDTO personSearchAccountRecordsInDTO1 = new PersonSearchAccountRecordsInDTO(personEmail1, denominationAccount1, startDate1, endDate1);

        String personEmail2 = "paulo@gmail.com";
        String denominationAccount2 = "EDP";
        String startDate2 = "2020-01-05";
        String endDate2 = "2020-02-10";
        PersonSearchAccountRecordsInDTO personSearchAccountRecordsInDTO2 = new PersonSearchAccountRecordsInDTO(personEmail2, denominationAccount2, startDate2, endDate2);

        // Act
        boolean resultEquals = personSearchAccountRecordsInDTO1.equals(personSearchAccountRecordsInDTO2);
        boolean resultHashCode = (personSearchAccountRecordsInDTO1.hashCode() == personSearchAccountRecordsInDTO2.hashCode());

        // Assert
        assertEquals(true, resultEquals);
        assertEquals(true, resultHashCode);
    }

    @Test
    @DisplayName("Test for equals() and hasCode() - Same Object")
    void testEqualsAndHashCode_SameObject() {

        // Arrange
        String personEmail = "paulo@gmail.com";
        String denominationAccount = "EDP";
        String startDate = "2020-01-05";
        String endDate = "2020-02-10";
        PersonSearchAccountRecordsInDTO personSearchAccountRecordsInDTO = new PersonSearchAccountRecordsInDTO(personEmail, denominationAccount, startDate, endDate);

        //Act
        boolean resultEquals = personSearchAccountRecordsInDTO.equals(personSearchAccountRecordsInDTO);
        boolean resultHashCode = (personSearchAccountRecordsInDTO.hashCode() == personSearchAccountRecordsInDTO.hashCode());

        //Assert
        assertEquals(true, resultEquals);
        assertEquals(true, resultHashCode);
    }

    @Test
    @DisplayName("Test for equals()  - Different Object - Same information")
    void testEqualsAndHashCode_DifferentObjectSameInformation() {

        // Arrange
        String personEmail = "paulo@gmail.com";
        String denominationAccount = "EDP";
        String startDate = "2020-01-05";
        String endDate = "2020-02-10";

        //Act
        PersonSearchAccountRecordsInDTO personSearchAccountRecordsInDTO1 = new PersonSearchAccountRecordsInDTO(personEmail, denominationAccount, startDate, endDate);
        PersonSearchAccountRecordsInDTO personSearchAccountRecordsInDTO2 = new PersonSearchAccountRecordsInDTO(personEmail, denominationAccount, startDate, endDate);

        //Assert
        assertTrue(personSearchAccountRecordsInDTO1.equals(personSearchAccountRecordsInDTO2));
    }

    @Test
    @DisplayName("Test for equals() - Fail (null object)")
    void testEquals_FailNullObject() {

        // Arrange
        String personEmail = "paulo@gmail.com";
        String denominationAccount = "EDP";
        String startDate = "2020-01-05";
        String endDate = "2020-02-10";

        PersonSearchAccountRecordsInDTO personSearchAccountRecordsInDTO1 = new PersonSearchAccountRecordsInDTO(personEmail, denominationAccount, startDate, endDate);

        PersonSearchAccountRecordsInDTO personSearchAccountRecordsInDTO2 = null;

        //Act
        boolean resultEquals = personSearchAccountRecordsInDTO1.equals(personSearchAccountRecordsInDTO2);

        //Assert
        assertEquals(false, resultEquals);
    }

    @Test
    @DisplayName("Test for equals() and hasCode() - Fail (different class)")
    void testEqualsAndHashCode_FailDiffClass() {

        // Arrange
        String personEmail = "paulo@gmail.com";
        String denominationAccount = "EDP";
        String startDate = "2020-01-05";
        String endDate = "2020-02-10";

        PersonSearchAccountRecordsInDTO personSearchAccountRecordsInDTO = new PersonSearchAccountRecordsInDTO(personEmail, denominationAccount, startDate, endDate);

        String object = "Object from class String";

        // Act
        boolean resultEquals = personSearchAccountRecordsInDTO.equals(object);
        boolean resultHashCode = (personSearchAccountRecordsInDTO.hashCode() == object.hashCode());

        // Assert
        assertEquals(false, resultEquals);
        assertEquals(false, resultHashCode);
    }

    @Test
    @DisplayName("Test for equals() and hasCode() - Fail (different personEmail)")
    void testEqualsAndHashCode_DifferentPersonEmail() {

        // Arrange
        String personEmail = "paulo@gmail.com";
        String newPersonEmail = "pp@gmail.com";

        String denominationAccount = "EDP";
        String startDate = "2020-01-05";
        String endDate = "2020-02-10";


        PersonSearchAccountRecordsInDTO personSearchAccountRecordsInDTO = new PersonSearchAccountRecordsInDTO(personEmail, denominationAccount, startDate, endDate);
        PersonSearchAccountRecordsInDTO newPersonSearchAccountRecordsInDTO = new PersonSearchAccountRecordsInDTO(newPersonEmail, denominationAccount, startDate, endDate);


        // Act
        boolean resultEquals = personSearchAccountRecordsInDTO.equals(newPersonSearchAccountRecordsInDTO);


        // Assert
        assertEquals(false, resultEquals);

    }

    @Test
    @DisplayName("Test for equals() and hasCode() - Fail (different account denomination)")
    void testEqualsAndHashCode_FailDiffAccount() {

        // Arrange
        String denominationAccount1 = "EDP";
        String denominationAccount2 = "Water Expenses";

        String personEmail = "paulo@gmail.com";
        String startDate = "2020-01-05";
        String endDate = "2020-02-10";

        PersonSearchAccountRecordsInDTO personSearchAccountRecordsInDTO1 = new PersonSearchAccountRecordsInDTO(personEmail, denominationAccount1, startDate, endDate);
        PersonSearchAccountRecordsInDTO personSearchAccountRecordsInDTO2 = new PersonSearchAccountRecordsInDTO(personEmail, denominationAccount2, startDate, endDate);

        //Act
        boolean resultEquals = personSearchAccountRecordsInDTO1.equals(personSearchAccountRecordsInDTO2);
        boolean resultHashCode = (personSearchAccountRecordsInDTO1.hashCode() == personSearchAccountRecordsInDTO2.hashCode());

        //Assert
        assertEquals(false, resultEquals);
        assertEquals(false, resultHashCode);
    }

    @Test
    @DisplayName("Test for equals() and hasCode() - Fail (different start date)")
    void testEqualsAndHashCode_FailDiffStartDate() {

        // Arrange

        String startDate1 = "2020-01-05";
        String startDate2 = "2020-01-22";



        String personEmail = "paulo@gmail.com";
        String denominationAccount = "EDP";
        String endDate = "2020-02-10";

        PersonSearchAccountRecordsInDTO personSearchAccountRecordsInDTO1 = new PersonSearchAccountRecordsInDTO(personEmail, denominationAccount, startDate1, endDate);
        PersonSearchAccountRecordsInDTO personSearchAccountRecordsInDTO2 = new PersonSearchAccountRecordsInDTO(personEmail, denominationAccount, startDate2, endDate);

        //Act
        boolean resultEquals = personSearchAccountRecordsInDTO1.equals(personSearchAccountRecordsInDTO2);
        boolean resultHashCode = (personSearchAccountRecordsInDTO1.hashCode() == personSearchAccountRecordsInDTO2.hashCode());

        //Assert
        assertEquals(false, resultEquals);
        assertEquals(false, resultHashCode);
    }

    @Test
    @DisplayName("Test for equals() and hasCode() - Fail (different end date)")
    void testEqualsAndHashCode_FailDiffEndDate() {

        // Arrange
        String endDate1 = "2020-02-10";
        String endDate2 = "2020-03-10";

        String personEmail = "paulo@gmail.com";
        String denominationAccount = "EDP";
        String startDate = "2020-01-05";

        PersonSearchAccountRecordsInDTO personSearchAccountRecordsInDTO1 = new PersonSearchAccountRecordsInDTO(personEmail, denominationAccount, startDate, endDate1);
        PersonSearchAccountRecordsInDTO personSearchAccountRecordsInDTO2 = new PersonSearchAccountRecordsInDTO(personEmail, denominationAccount, startDate, endDate2);

        // Act
        boolean resultEquals = personSearchAccountRecordsInDTO1.equals(personSearchAccountRecordsInDTO2);
        boolean resultHashCode = (personSearchAccountRecordsInDTO1.hashCode() == personSearchAccountRecordsInDTO2.hashCode());

        //Assert
        assertEquals(false, resultEquals);
        assertEquals(false, resultHashCode);
    }
}