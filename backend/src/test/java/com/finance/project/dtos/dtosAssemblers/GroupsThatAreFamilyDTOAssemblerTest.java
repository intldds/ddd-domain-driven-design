package com.finance.project.dtos.dtosAssemblers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.finance.project.dtos.dtos.GroupIDDTO;
import com.finance.project.dtos.dtos.GroupsThatAreFamilyDTO;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class GroupsThatAreFamilyDTOAssemblerTest {

    @Test
    @DisplayName("Test create Data Transfer Object with Domain Objects || Happy case")
    void testCreateDataTransferObjectWithDomainObjects(){

        //Arrange

        List<GroupIDDTO> families = new ArrayList<>();

        //Act

        GroupsThatAreFamilyDTOAssembler groupsThatAreFamilyDTOAssembler = new GroupsThatAreFamilyDTOAssembler();
        GroupsThatAreFamilyDTO groupsThatAreFamilyDTO = groupsThatAreFamilyDTOAssembler.createDTOFromDomainObject(families);

        //Assert

        assertEquals(families, groupsThatAreFamilyDTO.getGroupThatAreFamily());
    }
}

