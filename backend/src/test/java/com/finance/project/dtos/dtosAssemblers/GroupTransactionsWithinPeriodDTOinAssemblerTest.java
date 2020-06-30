package com.finance.project.dtos.dtosAssemblers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.finance.project.dtos.dtos.GroupTransactionsWithinPeriodDTOin;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GroupTransactionsWithinPeriodDTOinAssemblerTest {
    @Test
    @DisplayName("Test DTO Constructor")
    void constructorTest() {
        //Arrange
        String personEmail = "paulo@gmail.com";
        String groupDenomination = "House";
        LocalDate startDate = LocalDate.of(2020, 01, 05);
        LocalDate endDate = LocalDate.of(2020, 02, 10);
        GroupTransactionsWithinPeriodDTOin expectedGroupTransactionsWithinPeriodDTOin = new GroupTransactionsWithinPeriodDTOin(personEmail, groupDenomination, startDate, endDate);

        //Act
        GroupTransactionsWithinPeriodDTOinAssembler groupTransactionsWithinPeriodDTOinAssembler = new GroupTransactionsWithinPeriodDTOinAssembler();
        GroupTransactionsWithinPeriodDTOin assemblerDTOin = groupTransactionsWithinPeriodDTOinAssembler.createGroupTransactionsWithinPeriodDTOin(personEmail, groupDenomination, startDate, endDate);

        //Assert
        assertEquals(expectedGroupTransactionsWithinPeriodDTOin, assemblerDTOin);
        assertEquals(groupDenomination, assemblerDTOin.getGroupDenomination());
        assertEquals(startDate, assemblerDTOin.getStartDate());
        assertEquals(endDate, assemblerDTOin.getEndDate());
    }
}