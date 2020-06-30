package com.finance.project.dtos.dtos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GroupSearchAccountRecordsInDTOTest {

    //Constructor
    @Test
    @DisplayName("Test DTO in constructor")
    void constructorTest() {

        //Arrange
        String personEmail = "paulo@gmail.com";
        String groupDenomination = "Fontes Family";
        String accountDenomination = "Bank Account";
        String startDate = "2020-01-21";
        String endDate = "2020-01-29";

        //Act
        GroupSearchAccountRecordsInDTO groupSearchAccountRecordsInDTO = new GroupSearchAccountRecordsInDTO(personEmail, groupDenomination, accountDenomination, startDate, endDate);

        //Assert
        assertEquals(personEmail, groupSearchAccountRecordsInDTO.getPersonEmail());
        assertEquals(groupDenomination, groupSearchAccountRecordsInDTO.getGroupDenomination());
        assertEquals(accountDenomination, groupSearchAccountRecordsInDTO.getAccountDenomination());
        assertEquals(startDate, groupSearchAccountRecordsInDTO.getStartDate());
        assertEquals(endDate, groupSearchAccountRecordsInDTO.getEndDate());

    }

    //Getters
    @Test
    @DisplayName("Test DTO in getters - getPersonEmail()")
    void getters_getPersonEmail() {

        //Arrange
        String personEmail = "paulo@gmail.com";
        String groupDenomination = "Fontes Family";
        String accountDenomination = "Bank Account";
        String startDate = "2020-01-21";
        String endDate = "2020-01-29";

        //Act
        GroupSearchAccountRecordsInDTO groupSearchAccountRecordsInDTO = new GroupSearchAccountRecordsInDTO(personEmail, groupDenomination, accountDenomination, startDate, endDate);

        //Assert
        assertEquals(personEmail, groupSearchAccountRecordsInDTO.getPersonEmail());

    }

    @Test
    @DisplayName("Test DTO in getters - getGroupDenomination()")
    void getters_getGroupDenomination() {

        //Arrange
        String personEmail = "paulo@gmail.com";
        String groupDenomination = "Fontes Family";
        String accountDenomination = "Bank Account";
        String startDate = "2020-01-21";
        String endDate = "2020-01-29";

        //Act
        GroupSearchAccountRecordsInDTO groupSearchAccountRecordsInDTO = new GroupSearchAccountRecordsInDTO(personEmail, groupDenomination, accountDenomination, startDate, endDate);

        //Assert
        assertEquals(groupDenomination, groupSearchAccountRecordsInDTO.getGroupDenomination());

    }

    @Test
    @DisplayName("Test DTO in getters - getAccountDenomination()")
    void getters_getAccountDenomination() {

        //Arrange
        String personEmail = "paulo@gmail.com";
        String groupDenomination = "Fontes Family";
        String accountDenomination = "Bank Account";
        String startDate = "2020-01-21";
        String endDate = "2020-01-29";

        //Act
        GroupSearchAccountRecordsInDTO groupSearchAccountRecordsInDTO = new GroupSearchAccountRecordsInDTO(personEmail, groupDenomination, accountDenomination, startDate, endDate);

        //Assert
        assertEquals(accountDenomination, groupSearchAccountRecordsInDTO.getAccountDenomination());

    }

    @Test
    @DisplayName("Test DTO in getters - getStartDate()")
    void getters_getStartDate() {

        //Arrange
        String personEmail = "paulo@gmail.com";
        String groupDenomination = "Fontes Family";
        String accountDenomination = "Bank Account";
        String startDate = "2020-01-21";
        String endDate = "2020-01-29";

        //Act
        GroupSearchAccountRecordsInDTO groupSearchAccountRecordsInDTO = new GroupSearchAccountRecordsInDTO(personEmail, groupDenomination, accountDenomination, startDate, endDate);

        //Assert
        assertEquals(startDate, groupSearchAccountRecordsInDTO.getStartDate());

    }

    @Test
    @DisplayName("Test DTO in getters - getEndDate()")
    void getters_getEndDate() {

        //Arrange
        String personEmail = "paulo@gmail.com";
        String groupDenomination = "Fontes Family";
        String accountDenomination = "Bank Account";
        String startDate = "2020-01-21";
        String endDate = "2020-01-29";

        //Act
        GroupSearchAccountRecordsInDTO groupSearchAccountRecordsInDTO = new GroupSearchAccountRecordsInDTO(personEmail, groupDenomination, accountDenomination, startDate, endDate);

        //Assert
        assertEquals(endDate, groupSearchAccountRecordsInDTO.getEndDate());

    }

    //Equals and HashCode
    @Test
    @DisplayName("Test for equals() and hasCode() - Success")
    void testEqualsAndHashCode_success() {

        //Arrange
        String personEmail1 = "paulo@gmail.com";
        String groupDenomination1 = "Fontes Family";
        String accountDenomination1 = "Bank Account";
        String startDate1 = "2020-01-21";
        String endDate1 = "2020-01-29";
        GroupSearchAccountRecordsInDTO groupSearchAccountRecordsInDTO1 = new GroupSearchAccountRecordsInDTO(personEmail1, groupDenomination1, accountDenomination1, startDate1, endDate1);

        String personEmail2 = "paulo@gmail.com";
        String groupDenomination2 = "Fontes Family";
        String accountDenomination2 = "Bank Account";
        String startDate2 = "2020-01-21";
        String endDate2 = "2020-01-29";
        GroupSearchAccountRecordsInDTO groupSearchAccountRecordsInDTO2 = new GroupSearchAccountRecordsInDTO(personEmail2, groupDenomination2, accountDenomination2, startDate2, endDate2);

        //Act
        boolean resultEquals = groupSearchAccountRecordsInDTO1.equals(groupSearchAccountRecordsInDTO2);
        boolean resultHashCode = (groupSearchAccountRecordsInDTO1.hashCode() == groupSearchAccountRecordsInDTO2.hashCode());

        //Assert
        assertEquals(true, resultEquals);
        assertEquals(true, resultHashCode);

    }

    @Test
    @DisplayName("Test for equals() and hasCode() - Same Object")
    void testEqualsAndHashCode_sameObject() {

        //Arrange
        String personEmail1 = "paulo@gmail.com";
        String groupDenomination1 = "Fontes Family";
        String accountDenomination1 = "Bank Account";
        String startDate1 = "2020-01-21";
        String endDate1 = "2020-01-29";
        GroupSearchAccountRecordsInDTO groupSearchAccountRecordsInDTO1 = new GroupSearchAccountRecordsInDTO(personEmail1, groupDenomination1, accountDenomination1, startDate1, endDate1);

        //Act
        boolean resultEquals = groupSearchAccountRecordsInDTO1.equals(groupSearchAccountRecordsInDTO1);
        boolean resultHashCode = (groupSearchAccountRecordsInDTO1.hashCode() == groupSearchAccountRecordsInDTO1.hashCode());

        //Assert
        assertEquals(true, resultEquals);
        assertEquals(true, resultHashCode);

    }

    @Test
    @DisplayName("Test for equals()  - Different Object - Same information")
    void testEquals_DifferentObjectSameInformation() {

        //Arrange
        String personEmail1 = "paulo@gmail.com";
        String groupDenomination1 = "Fontes Family";
        String accountDenomination1 = "Bank Account";
        String startDate1 = "2020-01-21";
        String endDate1 = "2020-01-29";
        GroupSearchAccountRecordsInDTO groupSearchAccountRecordsInDTO1 = new GroupSearchAccountRecordsInDTO(personEmail1, groupDenomination1, accountDenomination1, startDate1, endDate1);

        GroupSearchAccountRecordsInDTO groupSearchAccountRecordsInDTO2 = new GroupSearchAccountRecordsInDTO(personEmail1, groupDenomination1, accountDenomination1, startDate1, endDate1);


        //Act
        boolean resultEquals = groupSearchAccountRecordsInDTO1.equals(groupSearchAccountRecordsInDTO2);

        //Assert
        assertEquals(true, resultEquals);

    }

    @Test
    @DisplayName("Test for equals() and hasCode() - Fail - null object")
    void testEquals_fail_nullObject() {

        //Arrange
        String personEmail1 = "paulo@gmail.com";
        String groupDenomination1 = "Fontes Family";
        String accountDenomination1 = "Bank Account";
        String startDate1 = "2020-01-21";
        String endDate1 = "2020-01-29";
        GroupSearchAccountRecordsInDTO groupSearchAccountRecordsInDTO1 = new GroupSearchAccountRecordsInDTO(personEmail1, groupDenomination1, accountDenomination1, startDate1, endDate1);

        GroupSearchAccountRecordsInDTO groupSearchAccountRecordsInDTO2 = null;

        //Act
        boolean resultEquals = groupSearchAccountRecordsInDTO1.equals(groupSearchAccountRecordsInDTO2);

        //Assert
        assertEquals(false, resultEquals);

    }

    @Test
    @DisplayName("Test for equals() and hasCode() - Fail - different class")
    void testEqualsAndHashCode_fail_diffClass() {

        //Arrange
        String personEmail1 = "paulo@gmail.com";
        String groupDenomination1 = "Fontes Family";
        String accountDenomination1 = "Bank Account";
        String startDate1 = "2020-01-21";
        String endDate1 = "2020-01-29";
        GroupSearchAccountRecordsInDTO groupSearchAccountRecordsInDTO1 = new GroupSearchAccountRecordsInDTO(personEmail1, groupDenomination1, accountDenomination1, startDate1, endDate1);

        String object = "Object from class String";

        //Act
        boolean resultEquals = groupSearchAccountRecordsInDTO1.equals(object);
        boolean resultHashCode = (groupSearchAccountRecordsInDTO1.hashCode() == object.hashCode());

        //Assert
        assertEquals(false, resultEquals);
        assertEquals(false, resultHashCode);

    }

    @Test
    @DisplayName("Test for equals() and hasCode() - Fail - different person email")
    void testEqualsAndHashCode_fail_diffPersonEmail() {

        //Arrange
        String personEmail1 = "paulo@gmail.com";
        String groupDenomination1 = "Fontes Family";
        String accountDenomination1 = "Bank Account";
        String startDate1 = "2020-01-21";
        String endDate1 = "2020-01-29";
        GroupSearchAccountRecordsInDTO groupSearchAccountRecordsInDTO1 = new GroupSearchAccountRecordsInDTO(personEmail1, groupDenomination1, accountDenomination1, startDate1, endDate1);

        String personEmail2 = "isolina@gmail.com";
        GroupSearchAccountRecordsInDTO groupSearchAccountRecordsInDTO2 = new GroupSearchAccountRecordsInDTO(personEmail2, groupDenomination1, accountDenomination1, startDate1, endDate1);

        //Act
        boolean resultEquals = groupSearchAccountRecordsInDTO1.equals(groupSearchAccountRecordsInDTO2);
        boolean resultHashCode = (groupSearchAccountRecordsInDTO1.hashCode() == groupSearchAccountRecordsInDTO2.hashCode());

        //Assert
        assertEquals(false, resultEquals);
        assertEquals(false, resultHashCode);

    }

    @Test
    @DisplayName("Test for equals() and hasCode() - Fail - different group denomination")
    void testEqualsAndHashCode_fail_diffGroupDenomination() {

        //Arrange
        String personEmail1 = "paulo@gmail.com";
        String groupDenomination1 = "Fontes Family";
        String accountDenomination1 = "Bank Account";
        String startDate1 = "2020-01-21";
        String endDate1 = "2020-01-29";
        GroupSearchAccountRecordsInDTO groupSearchAccountRecordsInDTO1 = new GroupSearchAccountRecordsInDTO(personEmail1, groupDenomination1, accountDenomination1, startDate1, endDate1);

        String groupDenomination2 = "Swimming";
        GroupSearchAccountRecordsInDTO groupSearchAccountRecordsInDTO2 = new GroupSearchAccountRecordsInDTO(personEmail1, groupDenomination2, accountDenomination1, startDate1, endDate1);

        //Act
        boolean resultEquals = groupSearchAccountRecordsInDTO1.equals(groupSearchAccountRecordsInDTO2);
        boolean resultHashCode = (groupSearchAccountRecordsInDTO1.hashCode() == groupSearchAccountRecordsInDTO2.hashCode());

        //Assert
        assertEquals(false, resultEquals);
        assertEquals(false, resultHashCode);

    }

    @Test
    @DisplayName("Test for equals() and hasCode() - Fail - different account denomination")
    void testEqualsAndHashCode_fail_diffAccountDenomination() {

        //Arrange
        String personEmail1 = "paulo@gmail.com";
        String groupDenomination1 = "Fontes Family";
        String accountDenomination1 = "Bank Account";
        String startDate1 = "2020-01-21";
        String endDate1 = "2020-01-29";
        GroupSearchAccountRecordsInDTO groupSearchAccountRecordsInDTO1 = new GroupSearchAccountRecordsInDTO(personEmail1, groupDenomination1, accountDenomination1, startDate1, endDate1);

        String accountDenomination2 = "Wallet";
        GroupSearchAccountRecordsInDTO groupSearchAccountRecordsInDTO2 = new GroupSearchAccountRecordsInDTO(personEmail1, groupDenomination1, accountDenomination2, startDate1, endDate1);

        //Act
        boolean resultEquals = groupSearchAccountRecordsInDTO1.equals(groupSearchAccountRecordsInDTO2);
        boolean resultHashCode = (groupSearchAccountRecordsInDTO1.hashCode() == groupSearchAccountRecordsInDTO2.hashCode());

        //Assert
        assertEquals(false, resultEquals);
        assertEquals(false, resultHashCode);

    }

    @Test
    @DisplayName("Test for equals() and hasCode() - Fail - different start date")
    void testEqualsAndHashCode_fail_diffStartDate() {

        //Arrange
        String personEmail1 = "paulo@gmail.com";
        String groupDenomination1 = "Fontes Family";
        String accountDenomination1 = "Bank Account";
        String startDate1 = "2020-01-21";
        String endDate1 = "2020-01-29";
        GroupSearchAccountRecordsInDTO groupSearchAccountRecordsInDTO1 = new GroupSearchAccountRecordsInDTO(personEmail1, groupDenomination1, accountDenomination1, startDate1, endDate1);

        String startDate2 = "2020-02-01";
        GroupSearchAccountRecordsInDTO groupSearchAccountRecordsInDTO2 = new GroupSearchAccountRecordsInDTO(personEmail1, groupDenomination1, accountDenomination1, startDate2, endDate1);

        //Act
        boolean resultEquals = groupSearchAccountRecordsInDTO1.equals(groupSearchAccountRecordsInDTO2);
        boolean resultHashCode = (groupSearchAccountRecordsInDTO1.hashCode() == groupSearchAccountRecordsInDTO2.hashCode());

        //Assert
        assertEquals(false, resultEquals);
        assertEquals(false, resultHashCode);

    }

    @Test
    @DisplayName("Test for equals() and hasCode() - Fail - different end date")
    void testEqualsAndHashCode_fail_diffEndDate() {

        //Arrange
        String personEmail1 = "paulo@gmail.com";
        String groupDenomination1 = "Fontes Family";
        String accountDenomination1 = "Bank Account";
        String startDate1 = "2020-01-21";
        String endDate1 = "2020-01-29";
        GroupSearchAccountRecordsInDTO groupSearchAccountRecordsInDTO1 = new GroupSearchAccountRecordsInDTO(personEmail1, groupDenomination1, accountDenomination1, startDate1, endDate1);

        String endDate2 = "2020-05-01";
        GroupSearchAccountRecordsInDTO groupSearchAccountRecordsInDTO2 = new GroupSearchAccountRecordsInDTO(personEmail1, groupDenomination1, accountDenomination1, startDate1, endDate2);

        //Act
        boolean resultEquals = groupSearchAccountRecordsInDTO1.equals(groupSearchAccountRecordsInDTO2);
        boolean resultHashCode = (groupSearchAccountRecordsInDTO1.hashCode() == groupSearchAccountRecordsInDTO2.hashCode());

        //Assert
        assertEquals(false, resultEquals);
        assertEquals(false, resultHashCode);

    }

}