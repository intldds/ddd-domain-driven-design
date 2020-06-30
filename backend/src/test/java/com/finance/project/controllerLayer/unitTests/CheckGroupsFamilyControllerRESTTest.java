package com.finance.project.controllerLayer.unitTests;

import com.finance.project.applicationLayer.applicationServices.otherServices.CheckGroupsFamilyService;
import com.finance.project.controllerLayer.controllersREST.otherControllers.CheckGroupsFamilyControllerREST;
import com.finance.project.controllerLayer.integrationTests.AbstractTest;
import com.finance.project.domainLayer.domainEntities.vosShared.DateOfCreation;
import com.finance.project.domainLayer.domainEntities.vosShared.Denomination;
import com.finance.project.domainLayer.domainEntities.vosShared.Description;
import com.finance.project.domainLayer.domainEntities.vosShared.PersonID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.finance.project.dtos.dtos.GroupDTO;
import com.finance.project.dtos.dtos.GroupMembersDTO;
import com.finance.project.dtos.dtosAssemblers.GroupDTOAssembler;
import com.finance.project.dtos.dtosAssemblers.GroupMembersDTOAssembler;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class CheckGroupsFamilyControllerRESTTest extends AbstractTest {

    @Mock
    private CheckGroupsFamilyService serviceUS004;

    @Autowired
    private CheckGroupsFamilyControllerREST controllerRESTUS004;

//    @Test
//    @DisplayName("GetMapping - Success")
//    public void returnGroupsThatAreFamilySuccessGetMapping() {
//
//        //Arrange
//
//        String familiaFontes = "Fontes Family";
//        String familiaSilva = "Silva Family";
//        String familiaPereira = "Pereira Family";
//
//        GroupIDDTO groupIDDTOFontes = new GroupIDDTO(familiaFontes);
//        GroupIDDTO groupIDDTOSilva = new GroupIDDTO(familiaSilva);
//        GroupIDDTO groupIDDTOPereira = new GroupIDDTO(familiaPereira);
//
//        List<GroupIDDTO> groupsThatAreFamily = new ArrayList<>();
//        groupsThatAreFamily.add(groupIDDTOFontes);
//        groupsThatAreFamily.add(groupIDDTOSilva);
//        groupsThatAreFamily.add(groupIDDTOPereira);
//
//        GroupsThatAreFamilyDTO groupsThatAreFamilyDTOExpected = GroupsThatAreFamilyDTOAssembler.createDTOFromDomainObject(groupsThatAreFamily);
//
//        ResponseEntity<Object> expectedResponse = new ResponseEntity<>(groupsThatAreFamilyDTOExpected, HttpStatus.OK);
//
//        Mockito.when(serviceUS004.groupsThatAreFamily()).thenReturn(groupsThatAreFamilyDTOExpected);
//
//        //Act
//
//        ResponseEntity<Object> areTheGroupsThatAreFamily = controllerRESTUS004.getGroupsThatAreFamily();
//
//        //       Expected results
//        Object areTheGroupsThatAreFamilyStatusCodeValue = areTheGroupsThatAreFamily.getStatusCodeValue();
//        Object areTheGroupsThatAreFamilyHeaders = areTheGroupsThatAreFamily.getHeaders().toString();
//        Object areTheGroupsThatAreFamilyResponseBody = areTheGroupsThatAreFamily.getBody().toString();
//        Object expectedStatusCodeValue = expectedResponse.getStatusCodeValue();
//        Object expectedHeaders = expectedResponse.getHeaders().toString();
//        Object expectedResponseBody = expectedResponse.getBody().toString();
//
//        //Assert
//        assertEquals(expectedStatusCodeValue,areTheGroupsThatAreFamilyStatusCodeValue);
//        assertEquals(expectedHeaders,areTheGroupsThatAreFamilyHeaders);
//        assertEquals(expectedResponseBody,areTheGroupsThatAreFamilyResponseBody);
//
//    }

    /*
    @Test
    @DisplayName("GetGroupByDenomination")
    public void getGroupByDenomination() {

        //Arrange
        String groupDenomination = "Fontes Family";
        String groupDescription = "All members from Fontes family";

        //Expected GroupDTO Dto
        Denomination denomination = Denomination.createDenomination(groupDenomination);
        Description description = Description.createDescription(groupDescription);
        DateOfCreation dateOfCreation = DateOfCreation.createDateOfCreation(LocalDate.now());
        GroupDTO groupExpected = GroupDTOAssembler.createDTOFromDomainObject(denomination, description, dateOfCreation);

        //Expected Response
        ResponseEntity<Object> expectedResponse = new ResponseEntity<>(groupExpected, HttpStatus.OK);

        //Act
        Mockito.when(serviceUS004.getGroupByDenomination(groupDenomination)).thenReturn(groupExpected);

        ResponseEntity<Object> group = controllerRESTUS004.getGroupByDenomination(groupDenomination);

        //Assert
        assertEquals(expectedResponse, group);
    }

    @Test
    @DisplayName("GetGroupAllMembers")
    public void getGroupAllMembers() {

        //Arrange
        String groupDenomination = "Fontes Family";

        PersonID personIDPaulo = PersonID.createPersonID("paulo@gmail.com");
        PersonID personIDElisabete = PersonID.createPersonID("elisabete@gmail.com");
        PersonID personIDJoao = PersonID.createPersonID("joao@gmail.com");
        PersonID personIDJoana = PersonID.createPersonID("joana@gmail.com");

        List<PersonID> expectedAllMembers = new ArrayList<>();
        expectedAllMembers.add(personIDPaulo);
        expectedAllMembers.add(personIDElisabete);
        expectedAllMembers.add(personIDJoao);
        expectedAllMembers.add(personIDJoana);

        //Expected GroupMembers Dto

        GroupMembersDTO expectedGroupMembersDTO = GroupMembersDTOAssembler.createDTOFromDomainObject(expectedAllMembers);


        //Expected Response
        ResponseEntity<Object> expectedResponse = new ResponseEntity<>(expectedGroupMembersDTO, HttpStatus.OK);

        //Act
        Mockito.when(serviceUS004.getGroupAllMembers(groupDenomination)).thenReturn(expectedGroupMembersDTO);

        ResponseEntity<Object> allMembers = controllerRESTUS004.getGroupAllMembers(groupDenomination);

//       Expected results
        Object allMembersStatusCodeValue = allMembers.getStatusCodeValue();
        Object allMembersHeaders = allMembers.getHeaders().toString();
        Object allMembersResponseBody = allMembers.getBody().toString();
        Object expectedStatusCodeValue = expectedResponse.getStatusCodeValue();
        Object expectedHeaders = expectedResponse.getHeaders().toString();
        Object expectedResponseBody = expectedResponse.getBody().toString();

        //Assert
        assertEquals(expectedStatusCodeValue,allMembersStatusCodeValue);
        assertEquals(expectedHeaders,allMembersHeaders);
        assertEquals(expectedResponseBody,allMembersResponseBody);
    }

     */
}

