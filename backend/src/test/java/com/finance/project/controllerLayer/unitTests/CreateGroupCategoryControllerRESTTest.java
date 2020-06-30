package com.finance.project.controllerLayer.unitTests;

import com.finance.project.applicationLayer.applicationServices.groupServices.CreateGroupCategoryService;
import com.finance.project.controllerLayer.controllersREST.groupControllers.CreateGroupCategoryControllerREST;
import com.finance.project.controllerLayer.integrationTests.AbstractTest;
import com.finance.project.domainLayer.exceptions.InvalidArgumentsBusinessException;
import com.finance.project.domainLayer.exceptions.NotFoundArgumentsBusinessException;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import com.finance.project.dtos.dtos.CreateGroupCategoryDTO;
import com.finance.project.dtos.dtos.NewGroupCategoryInfoDTO;
import com.finance.project.dtos.dtosAssemblers.CreateGroupCategoryDTOAssembler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CreateGroupCategoryControllerRESTTest extends AbstractTest {

    @Mock
    private CreateGroupCategoryService service;
    @Autowired
    private CreateGroupCategoryControllerREST controller;


    //SUCCESS

//    @Test
//    public void whenGroupCategoryIsCreated_thenRetrievedMsgIsSuccess() {
//        //Arrange
//
//        String personEmail = "manuel@gmail.com";
//        String groupDenomination = "Fontes Family";
//        String groupDescription = "All members from Fontes family";
//        String categoryDenomination = "Allowance";
//
//        //Expected result
//        Denomination denomination = Denomination.createDenomination(groupDenomination);
//        Description description = Description.createDescription(groupDescription);
//        DateOfCreation dateOfCreation = DateOfCreation.createDateOfCreation(LocalDate.now());
//        GroupDTO isCategoryCreatedExpected = GroupDTOAssembler.createDTOFromDomainObject(denomination, description, dateOfCreation);
//
//        NewGroupCategoryInfoDTO newGroupCategoryInfoDTO = new NewGroupCategoryInfoDTO(categoryDenomination);
//
//        CreateGroupCategoryDTO createGroupCategoryDTO = CreateGroupCategoryDTOAssembler.createDTOFromPrimitiveTypes(personEmail, groupDenomination, categoryDenomination);
//
//        //Expected Response Entity
//        ResponseEntity<Object> expectedResponse = new ResponseEntity<>(isCategoryCreatedExpected, HttpStatus.CREATED);
//
//        // Mock the behaviour of the service's createCategoryAsPersonInCharge method,
//        // so it does not depend on other parts (e.g. DB)
//        Mockito.when(service.createCategoryAsPeopleInCharge(createGroupCategoryDTO)).thenReturn(isCategoryCreatedExpected);
//
//        //Act
//
//        ResponseEntity<Object> isCategoryCreated = controller.createGroupCategory(newGroupCategoryInfoDTO, personEmail, groupDenomination);
//
//        //Assert
//        assertEquals(expectedResponse, isCategoryCreated);
//    }


    /*

    //PERSON_NOT_IN_CHARGE

    @Test
    public void whenGroupCategoryIsCreated_thenRetrievedMsgIsPersonNotInCharge() {
        //Arrange

        String personEmail = "miguel@gmail.com";
        String groupDenomination = "Fontes Family";
        String categoryDenomination = "Allowance";

        NewGroupCategoryInfoDTO newGroupCategoryInfoDTO = new NewGroupCategoryInfoDTO(categoryDenomination);

        CreateGroupCategoryDTO createGroupCategoryDTO = CreateGroupCategoryDTOAssembler.createDTOFromPrimitiveTypes(personEmail, groupDenomination, categoryDenomination);

        // Mock the behaviour of the service's createCategoryAsPersonInCharge method,
        // so it does not depend on other parts (e.g. DB)
        Mockito.when(service.createCategoryAsPeopleInCharge(createGroupCategoryDTO)).thenThrow(new InvalidArgumentsBusinessException(CreateGroupCategoryService.PERSON_NOT_IN_CHARGE));

        //Act

        Throwable thrown = assertThrows(InvalidArgumentsBusinessException.class, () -> controller.createGroupCategory(newGroupCategoryInfoDTO, personEmail, groupDenomination));

        //Assert
        assertEquals(thrown.getMessage(), CreateGroupCategoryService.PERSON_NOT_IN_CHARGE);
    }

     */

    //CATEGORY_ALREADY_EXIST

//    @Test
//    public void whenGroupCategoryIsCreated_thenRetrievedMsgIsCategoryAlreadyExists() {
//        //Arrange
//
//        String personEmail = "manuel@gmail.com";
//        String groupDenomination = "Fontes Family";
//        String categoryDenomination = "Salary";
//
//        boolean booleanResult = false;
//        String msgResult = US005_1CreateGroupCategoryService.CATEGORY_ALREADY_EXIST;
//        BooleanDTO isCategoryCreatedExpected = new BooleanDTO(booleanResult, msgResult);
//
//        NewGroupCategoryInfoDTO newGroupCategoryInfoDTO = new NewGroupCategoryInfoDTO(categoryDenomination);
//
//        CreateGroupCategoryDTO createGroupCategoryDTO = CreateGroupCategoryDTOAssembler.createDTOFromPrimitiveTypes(personEmail, groupDenomination, categoryDenomination);
//
//        // Mock the behaviour of the service's createCategoryAsPersonInCharge method,
//        // so it does not depend on other parts (e.g. DB)
//        Mockito.when(service.createCategoryAsPeopleInCharge(createGroupCategoryDTO)).thenThrow(new InvalidArgumentsBusinessException(US005_1CreateGroupCategoryService.CATEGORY_ALREADY_EXIST));
//
//        //Act
//
//        Throwable thrown = assertThrows(InvalidArgumentsBusinessException.class, () -> controller.createGroupCategory(newGroupCategoryInfoDTO, personEmail, groupDenomination));
//
//        //Assert
//        assertEquals(thrown.getMessage(), US005_1CreateGroupCategoryService.CATEGORY_ALREADY_EXIST);
//    }

    //GROUP_DOES_NOT_EXIST

    @Test
    public void whenGroupCategoryIsCreated_thenRetrievedMsgIsGroupDoesNotExists() {
        //Arrange

        String personEmail = "manuel@gmail.com";
        String groupDenomination = "Santos Family";
        String categoryDenomination = "Allowance";

        NewGroupCategoryInfoDTO newGroupCategoryInfoDTO = new NewGroupCategoryInfoDTO(categoryDenomination);

        CreateGroupCategoryDTO createGroupCategoryDTO = CreateGroupCategoryDTOAssembler.createDTOFromPrimitiveTypes(personEmail, groupDenomination, categoryDenomination);

        // Mock the behaviour of the service's createCategoryAsPersonInCharge method,
        // so it does not depend on other parts (e.g. DB)
        Mockito.when(service.createCategoryAsPeopleInCharge(createGroupCategoryDTO)).thenThrow(new NotFoundArgumentsBusinessException(CreateGroupCategoryService.GROUP_DOES_NOT_EXIST));

        //Act

        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () -> controller.createGroupCategory(newGroupCategoryInfoDTO, personEmail, groupDenomination));

        //Assert
        assertEquals(thrown.getMessage(), CreateGroupCategoryService.GROUP_DOES_NOT_EXIST);
    }
}
