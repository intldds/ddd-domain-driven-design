package com.finance.project.dtos.dtos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GroupTransactionsWithinPeriodDTOinTest {
    @Test
    @DisplayName("Test DTO Constructor")
    void constructorTest() {
        //Arrange
        String personEmail = "paulo@gmail.com";
        String groupDenomination = "House";
        LocalDate startDate = LocalDate.of(2020, 01, 05);
        LocalDate endDate = LocalDate.of(2020, 02, 10);

        //Act
        GroupTransactionsWithinPeriodDTOin groupTransactionsWithinPeriodDTOin = new GroupTransactionsWithinPeriodDTOin(personEmail, groupDenomination, startDate, endDate);

        //Assert
        assertEquals(groupDenomination, groupTransactionsWithinPeriodDTOin.getGroupDenomination());
        assertEquals(startDate, groupTransactionsWithinPeriodDTOin.getStartDate());
        assertEquals(endDate, groupTransactionsWithinPeriodDTOin.getEndDate());
    }

    @Test
    @DisplayName("Test DTO getters - getPersonEmail()")
    void getPersonEmailTest() {
        //Arrange
        String personEmail = "paulo@gmail.com";
        String groupDenomination = "House";
        LocalDate startDate = LocalDate.of(2020, 01, 05);
        LocalDate endDate = LocalDate.of(2020, 02, 10);

        //Act
        GroupTransactionsWithinPeriodDTOin groupTransactionsWithinPeriodDTOin = new GroupTransactionsWithinPeriodDTOin(personEmail, groupDenomination, startDate, endDate);

        //Assert
        assertEquals(personEmail, groupTransactionsWithinPeriodDTOin.getPersonEmail());
    }

    @Test
    @DisplayName("Test DTO getters - getGroupdenomination()")
    void getGroupdenominationTest() {
        //Arrange
        String personEmail = "paulo@gmail.com";
        String groupDenomination = "House";
        LocalDate startDate = LocalDate.of(2020, 01, 05);
        LocalDate endDate = LocalDate.of(2020, 02, 10);

        //Act
        GroupTransactionsWithinPeriodDTOin groupTransactionsWithinPeriodDTOin = new GroupTransactionsWithinPeriodDTOin(personEmail, groupDenomination, startDate, endDate);

        //Assert
        assertEquals(groupDenomination, groupTransactionsWithinPeriodDTOin.getGroupDenomination());
    }

    @Test
    @DisplayName("Test DTO getters - getStartDate()")
    void getStartDateTest() {
        //Arrange
        String personEmail = "paulo@gmail.com";
        String groupDenomination = "House";
        LocalDate startDate = LocalDate.of(2020, 01, 05);
        LocalDate endDate = LocalDate.of(2020, 02, 10);

        //Act
        GroupTransactionsWithinPeriodDTOin groupTransactionsWithinPeriodDTOin = new GroupTransactionsWithinPeriodDTOin(personEmail, groupDenomination, startDate, endDate);

        //Assert
        assertEquals(startDate, groupTransactionsWithinPeriodDTOin.getStartDate());
    }

    @Test
    @DisplayName("Test DTO getters - getEndDate()")
    void getEndDateTest() {
        //Arrange
        String personEmail = "paulo@gmail.com";
        String groupDenomination = "House";
        LocalDate startDate = LocalDate.of(2020, 01, 05);
        LocalDate endDate = LocalDate.of(2020, 02, 10);

        //Act
        GroupTransactionsWithinPeriodDTOin groupTransactionsWithinPeriodDTOin = new GroupTransactionsWithinPeriodDTOin(personEmail, groupDenomination, startDate, endDate);

        //Assert
        assertEquals(endDate, groupTransactionsWithinPeriodDTOin.getEndDate());
    }

    @Test
    @DisplayName("Test for equals() and hasCode() - Happy path")
    void testEqualsAndHashCode_HappyPath() {
        //Arrange
        String personEmail1 = "paulo@gmail.com";
        String groupDenomination1 = "House";
        LocalDate startDate1 = LocalDate.of(2020, 01, 05);
        LocalDate endDate1 = LocalDate.of(2020, 02, 10);
        GroupTransactionsWithinPeriodDTOin groupTransactionsWithinPeriodDTOin1 = new GroupTransactionsWithinPeriodDTOin(personEmail1, groupDenomination1, startDate1, endDate1);

        String personEmail2 = "paulo@gmail.com";
        String groupDenomination2 = "House";
        LocalDate startDate2 = LocalDate.of(2020, 01, 05);
        LocalDate endDate2 = LocalDate.of(2020, 02, 10);
        GroupTransactionsWithinPeriodDTOin groupTransactionsWithinPeriodDTOin2 = new GroupTransactionsWithinPeriodDTOin(personEmail2, groupDenomination2, startDate2, endDate2);

        //Act
        boolean resultEquals = groupTransactionsWithinPeriodDTOin1.equals(groupTransactionsWithinPeriodDTOin2);
        boolean resultHashCode = (groupTransactionsWithinPeriodDTOin1.hashCode() == groupTransactionsWithinPeriodDTOin2.hashCode());

        //Assert
        assertEquals(true, resultEquals);
        assertEquals(true, resultHashCode);
    }

    @Test
    @DisplayName("Test for equals() and hasCode() - Same Object")
    void testEqualsAndHashCode_SameObject() {
        //Arrange
        String personEmail = "paulo@gmail.com";
        String groupDenomination1 = "House";
        LocalDate startDate1 = LocalDate.of(2020, 01, 05);
        LocalDate endDate1 = LocalDate.of(2020, 02, 10);
        GroupTransactionsWithinPeriodDTOin groupTransactionsWithinPeriodDTOin1 = new GroupTransactionsWithinPeriodDTOin(personEmail, groupDenomination1, startDate1, endDate1);

        //Act
        boolean resultEquals = groupTransactionsWithinPeriodDTOin1.equals(groupTransactionsWithinPeriodDTOin1);
        boolean resultHashCode = (groupTransactionsWithinPeriodDTOin1.hashCode() == groupTransactionsWithinPeriodDTOin1.hashCode());

        //Assert
        assertEquals(true, resultEquals);
        assertEquals(true, resultHashCode);
    }

    @Test
    @DisplayName("Test for equals() - Different Object - Same information")
    void testEquals_DifferentObjectSameInformation() {
        //Arrange
        String personEmail1 = "paulo@gmail.com";
        String groupDenomination1 = "House";
        LocalDate startDate1 = LocalDate.of(2020, 01, 05);
        LocalDate endDate1 = LocalDate.of(2020, 02, 10);

        //Arrange
        String personEmail2 = "paulo@gmail.com";
        String groupDenomination2 = "House";
        LocalDate startDate2 = LocalDate.of(2020, 01, 05);
        LocalDate endDate2 = LocalDate.of(2020, 02, 10);

        //Act
        GroupTransactionsWithinPeriodDTOin groupTransactionsWithinPeriodDTOin1 = new GroupTransactionsWithinPeriodDTOin(personEmail1, groupDenomination1, startDate1, endDate1);
        GroupTransactionsWithinPeriodDTOin groupTransactionsWithinPeriodDTOin2 = new GroupTransactionsWithinPeriodDTOin(personEmail2, groupDenomination2, startDate2, endDate2);

        //Assert
        assertTrue(groupTransactionsWithinPeriodDTOin1.equals(groupTransactionsWithinPeriodDTOin2));
    }

    @Test
    @DisplayName("Test for equals() - Fail (null object)")
    void testEquals_FailNullObject() {
        //Arrange
        String personEmail = "paulo@gmail.com";
        String groupDenomination1 = "House";
        LocalDate startDate1 = LocalDate.of(2020, 01, 05);
        LocalDate endDate1 = LocalDate.of(2020, 02, 10);
        GroupTransactionsWithinPeriodDTOin groupTransactionsWithinPeriodDTOin1 = new GroupTransactionsWithinPeriodDTOin(personEmail, groupDenomination1, startDate1, endDate1);

        GroupTransactionsWithinPeriodDTOin groupTransactionsWithinPeriodDTOin2 = null;

        //Act
        boolean resultEquals = groupTransactionsWithinPeriodDTOin1.equals(groupTransactionsWithinPeriodDTOin2);

        //Assert
        assertEquals(false, resultEquals);
    }

    @Test
    @DisplayName("Test for equals() and hasCode() - Fail (different class)")
    void testEqualsAndHashCode_FailDiffClass() {
        //Arrange
        String personEmail = "paulo@gmail.com";
        String groupDenomination1 = "House";
        LocalDate startDate1 = LocalDate.of(2020, 01, 05);
        LocalDate endDate1 = LocalDate.of(2020, 02, 10);
        GroupTransactionsWithinPeriodDTOin groupTransactionsWithinPeriodDTOin1 = new GroupTransactionsWithinPeriodDTOin(personEmail, groupDenomination1, startDate1, endDate1);

        String object = "Object from class String";

        //Act
        boolean resultEquals = groupTransactionsWithinPeriodDTOin1.equals(object);
        boolean resultHashCode = (groupTransactionsWithinPeriodDTOin1.hashCode() == object.hashCode());

        //Assert
        assertEquals(false, resultEquals);
        assertEquals(false, resultHashCode);
    }

    @Test
    @DisplayName("Test for equals() and hasCode() - Fail (different personEmail)")
    void testEqualsAndHashCode_FailDiffPersonEmail() {
        //Arrange
        String personEmail1 = "paulo@gmail.com";
        String groupDenomination1 = "House";
        LocalDate startDate1 = LocalDate.of(2020, 01, 05);
        LocalDate endDate1 = LocalDate.of(2020, 02, 10);
        GroupTransactionsWithinPeriodDTOin groupTransactionsWithinPeriodDTOin1 = new GroupTransactionsWithinPeriodDTOin(personEmail1, groupDenomination1, startDate1, endDate1);

        String personEmail2 = "alexandre@gmail.com";
        String groupDenomination2 = "House";
        LocalDate startDate2 = LocalDate.of(2020, 01, 05);
        LocalDate endDate2 = LocalDate.of(2020, 02, 10);
        GroupTransactionsWithinPeriodDTOin groupTransactionsWithinPeriodDTOin2 = new GroupTransactionsWithinPeriodDTOin(personEmail2, groupDenomination2, startDate2, endDate2);

        //Act
        boolean resultEquals = groupTransactionsWithinPeriodDTOin1.equals(groupTransactionsWithinPeriodDTOin2);
        boolean resultHashCode = (groupTransactionsWithinPeriodDTOin1.hashCode() == groupTransactionsWithinPeriodDTOin2.hashCode());

        //Assert
        assertEquals(false, resultEquals);
        assertEquals(false, resultHashCode);
    }

    @Test
    @DisplayName("Test for equals() and hasCode() - Fail (different groupDenomination)")
    void testEqualsAndHashCode_FailDiffGroupName() {
        //Arrange
        String personEmail1 = "paulo@gmail.com";
        String groupDenomination1 = "House";
        LocalDate startDate1 = LocalDate.of(2020, 01, 05);
        LocalDate endDate1 = LocalDate.of(2020, 02, 10);
        GroupTransactionsWithinPeriodDTOin groupTransactionsWithinPeriodDTOin1 = new GroupTransactionsWithinPeriodDTOin(personEmail1, groupDenomination1, startDate1, endDate1);

        String personEmail2 = "paulo@gmail.com";
        String groupDenomination2 = "School";
        LocalDate startDate2 = LocalDate.of(2020, 01, 05);
        LocalDate endDate2 = LocalDate.of(2020, 02, 10);
        GroupTransactionsWithinPeriodDTOin groupTransactionsWithinPeriodDTOin2 = new GroupTransactionsWithinPeriodDTOin(personEmail2, groupDenomination2, startDate2, endDate2);

        //Act
        boolean resultEquals = groupTransactionsWithinPeriodDTOin1.equals(groupTransactionsWithinPeriodDTOin2);
        boolean resultHashCode = (groupTransactionsWithinPeriodDTOin1.hashCode() == groupTransactionsWithinPeriodDTOin2.hashCode());

        //Assert
        assertEquals(false, resultEquals);
        assertEquals(false, resultHashCode);
    }

    @Test
    @DisplayName("Test for equals() and hasCode() - Fail (different start date)")
    void testEqualsAndHashCode_FailDiffStartDate() {
        //Arrange
        String personEmail1 = "paulo@gmail.com";
        String groupDenomination1 = "House";
        LocalDate startDate1 = LocalDate.of(2020, 01, 05);
        LocalDate endDate1 = LocalDate.of(2020, 02, 10);
        GroupTransactionsWithinPeriodDTOin groupTransactionsWithinPeriodDTOin1 = new GroupTransactionsWithinPeriodDTOin(personEmail1, groupDenomination1, startDate1, endDate1);

        String personEmail2 = "paulo@gmail.com";
        String groupDenomination2 = "House";
        LocalDate startDate2 = LocalDate.of(2020, 01, 22);
        LocalDate endDate2 = LocalDate.of(2020, 02, 10);
        GroupTransactionsWithinPeriodDTOin groupTransactionsWithinPeriodDTOin2 = new GroupTransactionsWithinPeriodDTOin(personEmail2, groupDenomination2, startDate2, endDate2);

        //Act
        boolean resultEquals = groupTransactionsWithinPeriodDTOin1.equals(groupTransactionsWithinPeriodDTOin2);
        boolean resultHashCode = (groupTransactionsWithinPeriodDTOin1.hashCode() == groupTransactionsWithinPeriodDTOin2.hashCode());

        //Assert
        assertEquals(false, resultEquals);
        assertEquals(false, resultHashCode);
    }

    @Test
    @DisplayName("Test for equals() and hasCode() - Fail (different end date)")
    void testEqualsAndHashCode_FailDiffEndDate() {
        //Arrange
        String personEmail1 = "paulo@gmail.com";
        String groupDenomination1 = "House";
        LocalDate startDate1 = LocalDate.of(2020, 01, 05);
        LocalDate endDate1 = LocalDate.of(2020, 02, 10);
        GroupTransactionsWithinPeriodDTOin groupTransactionsWithinPeriodDTOin1 = new GroupTransactionsWithinPeriodDTOin(personEmail1, groupDenomination1, startDate1, endDate1);

        String personEmail2 = "paulo@gmail.com";
        String groupDenomination2 = "House";
        LocalDate startDate2 = LocalDate.of(2020, 01, 05);
        LocalDate endDate2 = LocalDate.of(2020, 03, 10);
        GroupTransactionsWithinPeriodDTOin groupTransactionsWithinPeriodDTOin2 = new GroupTransactionsWithinPeriodDTOin(personEmail2, groupDenomination2, startDate2, endDate2);

        //Act
        boolean resultEquals = groupTransactionsWithinPeriodDTOin1.equals(groupTransactionsWithinPeriodDTOin2);
        boolean resultHashCode = (groupTransactionsWithinPeriodDTOin1.hashCode() == groupTransactionsWithinPeriodDTOin2.hashCode());

        //Assert
        assertEquals(false, resultEquals);
        assertEquals(false, resultHashCode);
    }
}