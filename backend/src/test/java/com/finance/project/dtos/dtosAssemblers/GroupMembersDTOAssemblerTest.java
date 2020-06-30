package com.finance.project.dtos.dtosAssemblers;

import com.finance.project.domainLayer.domainEntities.vosShared.PersonID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.finance.project.dtos.dtos.GroupMembersDTO;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GroupMembersDTOAssemblerTest {

    @Test
    @DisplayName("GroupMembersDTOAssembler - Test create GroupMembersDTO from domain objects")
    void groupMembersDTOAssembler_createDTOFromDomainObject() {

        //Arrange
        String emailMaria = "maria@gmail.com";
        PersonID idMaria = PersonID.createPersonID(emailMaria);

        List<PersonID> personIDs = new ArrayList<>();
        personIDs.add(idMaria);

        //Arrange
        GroupMembersDTOAssembler groupMembersDTOAssembler = new GroupMembersDTOAssembler();
        GroupMembersDTO groupMembersDTO = groupMembersDTOAssembler.createDTOFromDomainObject(personIDs);

        //Expected
        List<String> persons = new ArrayList<>();
        persons.add(emailMaria);
        GroupMembersDTO groupMembersDTOExpected = new GroupMembersDTO(persons);

        //Assert
        assertEquals(groupMembersDTOExpected, groupMembersDTO);
    }

}