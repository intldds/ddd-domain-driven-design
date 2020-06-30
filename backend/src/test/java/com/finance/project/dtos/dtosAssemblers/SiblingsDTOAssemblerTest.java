package com.finance.project.dtos.dtosAssemblers;

import com.finance.project.domainLayer.domainEntities.vosShared.PersonID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.finance.project.dtos.dtos.SiblingsDTO;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SiblingsDTOAssemblerTest {

    @Test
    @DisplayName("SiblingsDTOssembler - Test create data transfer objects from Domain Object || Happy case")
    void siblingsDTOAssembler_CreateDTOFromDomainObjectTest() {

        //Arrange
        String emailRui = "rui@gmail.com";
        PersonID idRui = PersonID.createPersonID(emailRui);
        String emailhenrique = "henrique@gmail.com";
        PersonID idHenrique = PersonID.createPersonID(emailhenrique);

        List<PersonID> personIDs = new ArrayList<>();
        personIDs.add(idRui);
        personIDs.add(idHenrique);

        //Arrange
        SiblingsDTOAssembler siblingsDTOAssembler = new SiblingsDTOAssembler();
        SiblingsDTO groupMembersDTO = siblingsDTOAssembler.createDTOFromDomainObject(personIDs);

        //Expected
        List<String> persons = new ArrayList<>();
        persons.add(emailRui);
        persons.add(emailhenrique);

        SiblingsDTO groupMembersDTOExpected = new SiblingsDTO(persons);

        //Assert
        assertEquals(groupMembersDTOExpected, groupMembersDTO);
    }

}