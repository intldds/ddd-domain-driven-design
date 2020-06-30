package com.finance.project.dtos.dtosAssemblers;

import com.finance.project.domainLayer.domainEntities.vosShared.GroupID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.finance.project.dtos.dtos.GroupIDDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GroupIDDTOAssemblerTest {

    @Test
    @DisplayName("Test create Data Transfer Object with Primitives - GroupID || Happy case")
    void testCreateDataTransferObjectWithPrimitives() {

        String denomination = "Dance";
        GroupID groupID = GroupID.createGroupID(denomination);


        GroupIDDTO createGroupIDTOExpected = new GroupIDDTO(denomination);

        GroupIDDTOAssembler groupIDDTOAssembler = new GroupIDDTOAssembler();

        GroupIDDTO groupIDDTO = groupIDDTOAssembler.createDTOFromDomainObject(groupID);

        assertEquals(createGroupIDTOExpected, groupIDDTO);

    }

}