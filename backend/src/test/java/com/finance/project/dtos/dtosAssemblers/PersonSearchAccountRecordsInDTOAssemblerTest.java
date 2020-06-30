package com.finance.project.dtos.dtosAssemblers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.finance.project.dtos.dtos.PersonSearchAccountRecordsInDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonSearchAccountRecordsInDTOAssemblerTest {

    @Test
    @DisplayName("Test DTO Constructor")
    void constructorTest() {

        // Arrange
        String personEmail = "paulo@gmail.com";
        String denominationAccount = "EDP";
        String startDate = "2020-01-05";
        String endDate = "2020-02-10";

        PersonSearchAccountRecordsInDTO expectedPersonAccountTransactionsWihtinPeriodDTOin = new PersonSearchAccountRecordsInDTO(personEmail, denominationAccount, startDate, endDate);

        // Act
        PersonSearchAccountRecordsInDTO assemblerDTOin = PersonSearchAccountRecordsInDTOAssembler.personAccountTransactionsInDTO(personEmail, denominationAccount, startDate, endDate);

        // Assert
        assertEquals(expectedPersonAccountTransactionsWihtinPeriodDTOin, assemblerDTOin);
        assertEquals(personEmail, assemblerDTOin.getPersonEmail());
        assertEquals(denominationAccount, assemblerDTOin.getAccountDenomination());
        assertEquals(startDate, assemblerDTOin.getStartDate());
        assertEquals(endDate, assemblerDTOin.getEndDate());
    }
}