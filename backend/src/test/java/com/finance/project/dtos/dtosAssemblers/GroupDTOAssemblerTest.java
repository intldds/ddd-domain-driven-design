package com.finance.project.dtos.dtosAssemblers;

import com.finance.project.domainLayer.domainEntities.vosShared.DateOfCreation;
import com.finance.project.domainLayer.domainEntities.vosShared.Denomination;
import com.finance.project.domainLayer.domainEntities.vosShared.Description;
import com.finance.project.domainLayer.domainEntities.vosShared.PersonID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.finance.project.dtos.dtos.GroupDTO;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GroupDTOAssemblerTest {

    @Test
    @DisplayName("GroupDTOAssembler - Test create data transfer objects from Domain Object || Happy case")
    void groupDTOAssembler_CreateDTOFromDomainObjectTest() {

        //Arrange GroupDTO

        //GroupDTOAssembler
        String sundayRunnersDenomination = "Sunday Runners";
        String sundayRunnersDescription = "All members from Sunday Runners group";
        LocalDate sundayRunnersDateOfCreation = LocalDate.now();

        Denomination groupDenomination = Denomination.createDenomination(sundayRunnersDenomination);
        Description groupDescription = Description.createDescription(sundayRunnersDescription);
        DateOfCreation groupDateOfCreation = DateOfCreation.createDateOfCreation(sundayRunnersDateOfCreation);

        //GroupDTO
        String dateOfCreationSundayRunners = LocalDate.now().toString();

        //Expected
        GroupDTO groupDTOExpected = new GroupDTO(sundayRunnersDenomination, sundayRunnersDescription, dateOfCreationSundayRunners);

        //Act
        GroupDTO groupDTO = GroupDTOAssembler.createDTOFromDomainObject(groupDenomination, groupDescription, groupDateOfCreation);

        //Assert
        assertEquals(groupDTOExpected, groupDTO);
    }

    @Test
    @DisplayName("GroupDTOAssembler - Test create data transfer objects from Domain Object 2 || Happy case")
    void groupDTOAssembler_CreateDTOFromDomain2ObjectTest() {

        //Arrange GroupDTO

        //GroupDTOAssembler
        String sundayRunnersDenomination = "Sunday Runners";
        String henriqueName = "Henrique";
        String sundayRunnersDescription = "All members from Sunday Runners group";
        LocalDate sundayRunnersDateOfCreation = LocalDate.now();

        Denomination groupDenomination = Denomination.createDenomination(sundayRunnersDenomination);
        PersonID peopleInCharge = PersonID.createPersonID(henriqueName);
        Description groupDescription = Description.createDescription(sundayRunnersDescription);
        DateOfCreation groupDateOfCreation = DateOfCreation.createDateOfCreation(sundayRunnersDateOfCreation);

        //GroupDTO
        String dateOfCreationSundayRunners = LocalDate.now().toString();

        //Expected
        GroupDTO groupDTOExpected = new GroupDTO(sundayRunnersDenomination, sundayRunnersDescription, dateOfCreationSundayRunners);

        //Act
        GroupDTO groupDTO = GroupDTOAssembler.createDTOFromDomainObject(groupDenomination, peopleInCharge, groupDescription, groupDateOfCreation);

        //Assert
        assertEquals(groupDTOExpected, groupDTO);
    }

    @Test
    @DisplayName("GroupDTOAssembler - Test create data transfer objects from Domain Object 3 || Happy case")
    void groupDTOAssembler_CreateDTOFromDomain3ObjectTest() {

        //Arrange GroupDTO

        //GroupDTOAssembler
        String sundayRunnersDenomination = "Sunday Runners";
        String henriqueName = "Henrique";
        String sundayRunnersDescription = "All members from Sunday Runners group";
        LocalDate sundayRunnersDateOfCreation = LocalDate.now();

        //GroupDTO
        String dateOfCreationSundayRunners = LocalDate.now().toString();

        //Expected
        GroupDTO groupDTOExpected = new GroupDTO(sundayRunnersDenomination, sundayRunnersDescription, dateOfCreationSundayRunners);

        //Act

        GroupDTOAssembler groupDTOAssembler = new GroupDTOAssembler();
        GroupDTO groupDTO = groupDTOAssembler.createDTOFromDomainObjectPT(sundayRunnersDenomination,henriqueName,sundayRunnersDescription,dateOfCreationSundayRunners);

        //Assert
        assertEquals(groupDTOExpected, groupDTO);
    }

}