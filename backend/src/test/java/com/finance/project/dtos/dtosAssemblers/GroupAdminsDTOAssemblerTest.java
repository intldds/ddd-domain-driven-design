package com.finance.project.dtos.dtosAssemblers;

import com.finance.project.domainLayer.domainEntities.vosShared.PersonID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.finance.project.dtos.dtos.GroupAdminsDTO;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GroupAdminsDTOAssemblerTest {

    @Test
    @DisplayName("GroupAdminsDTOAssembler - Test create GroupMembersDTO from domain objects")
    void GroupAdminsDTOAssembler_createDTOFromDomainObject() {

        //Arrange
        String emailMaria = "maria@gmail.com";
        PersonID idMaria = PersonID.createPersonID(emailMaria);

        List<PersonID> personIDs = new ArrayList<>();
        personIDs.add(idMaria);

        //Arrange
        GroupAdminsDTOAssembler groupAdminsDTOAssembler = new GroupAdminsDTOAssembler();
        GroupAdminsDTO groupAdminsDTO = groupAdminsDTOAssembler.createDTOFromDomainObject(personIDs);

        //Expected
        List<String> persons = new ArrayList<>();
        persons.add(emailMaria);
        GroupAdminsDTO groupMembersDTOExpected = new GroupAdminsDTO(persons);

        //Assert
        assertEquals(groupMembersDTOExpected, groupAdminsDTO);
    }

}