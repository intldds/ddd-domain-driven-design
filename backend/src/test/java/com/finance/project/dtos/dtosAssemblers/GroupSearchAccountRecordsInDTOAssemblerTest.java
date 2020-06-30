package com.finance.project.dtos.dtosAssemblers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.finance.project.dtos.dtos.GroupSearchAccountRecordsInDTO;

import static org.junit.jupiter.api.Assertions.*;

class GroupSearchAccountRecordsInDTOAssemblerTest {

    @Test
    @DisplayName("Test DTO assembler constructor")
    void constructorTest() {

        //Arrange
        String personEmail = "paulo@gmail.com";
        String groupDenomination = "Fontes Family";
        String accountDenomination = "Bank Account";
        String startDate = "2020-01-21";
        String endDate = "2020-01-29";

        GroupSearchAccountRecordsInDTO expectedDTO = new GroupSearchAccountRecordsInDTO(personEmail, groupDenomination, accountDenomination, startDate, endDate);

        //Act
        GroupSearchAccountRecordsInDTO resultAssemblerDTO = GroupSearchAccountRecordsInDTOAssembler.groupSearchAccountRecordsInDTO(personEmail, groupDenomination, accountDenomination, startDate, endDate);

        //Assert
        assertEquals(expectedDTO, resultAssemblerDTO);
        assertEquals(personEmail, resultAssemblerDTO.getPersonEmail());
        assertEquals(groupDenomination, resultAssemblerDTO.getGroupDenomination());
        assertEquals(accountDenomination, resultAssemblerDTO.getAccountDenomination());
        assertEquals(startDate, resultAssemblerDTO.getStartDate());
        assertEquals(endDate, resultAssemblerDTO.getEndDate());

    }

}