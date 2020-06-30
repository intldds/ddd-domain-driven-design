package com.finance.project.controllerLayer.unitTests;

import com.finance.project.applicationLayer.applicationServices.personServices.CreatePersonCategoryService;
import com.finance.project.controllerLayer.controllersREST.personControllers.CreatePersonCategoryControllerREST;
import com.finance.project.controllerLayer.integrationTests.AbstractTest;
import com.finance.project.domainLayer.exceptions.InvalidArgumentsBusinessException;
import com.finance.project.domainLayer.exceptions.NotFoundArgumentsBusinessException;
import com.finance.project.dtos.dtosAssemblers.CreatePersonCategoryDTOAssembler;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import com.finance.project.dtos.dtos.CreatePersonCategoryDTO;
import com.finance.project.dtos.dtos.NewPersonCategoryInfoDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CreatePersonCategoryControllerRESTTest extends AbstractTest {

    @Mock
    private CreatePersonCategoryService service;
    @Autowired
    private CreatePersonCategoryControllerREST controller;

    // SUCCESS

//    @Test
//    public void whenPersonCategoryIsCreated_MsgIsSuccess() {
//
//        // Arrange
//
//        // Arrange Person
//        final String personEmail = "ilda@gmail.com";
//        final String personName = "Fontes";
//        final LocalDate personBirthdate = LocalDate.of(1964, 02, 16);
//        final String personBirthplace = "Vila Nova de Gaia";
//
//        // Arrange Category
//        final String categoryDenomination = "Basket";
//
//        // Expected result
//        Email email = Email.createEmail(personEmail);
//        Name name = Name.createName(personName);
//        Birthdate birthdate = Birthdate.createBirthdate(personBirthdate);
//        Birthplace birthplace = Birthplace.createBirthplace(personBirthplace);
//        PersonID fatherID = null;
//        PersonID motherID = null;
//
//        PersonDTO isCategoryCreatedExpected = PersonDTOAssembler.createDTOFromDomainObject(email, name, birthdate, birthplace, fatherID, motherID);
//
//        NewPersonCategoryInfoDTO newPersonCategoryInfoDTO = new NewPersonCategoryInfoDTO(categoryDenomination);
//
//        CreatePersonCategoryDTO createPersonCategoryDTO = CreatePersonCategoryDTOAssembler.createDTOFromPrimitiveTypes(personEmail, categoryDenomination);
//
//        // Expected Response Entity
//        ResponseEntity<Object> expectedResponse = new ResponseEntity<>(isCategoryCreatedExpected, HttpStatus.CREATED);
//
//        // Mock the behaviour of the service's createCategory method
//        Mockito.when(service.createCategory(createPersonCategoryDTO)).thenReturn(isCategoryCreatedExpected);
//
//        // Act
//        ResponseEntity<Object> isCategoryCreated = controller.createPersonCategory(newPersonCategoryInfoDTO, personEmail);
//
//        // Assert
//        assertEquals(expectedResponse, isCategoryCreated);
//    }

    /*

    // CATEGORY_ALREADY_EXIST

    @Test
    public void whenPersonCategoryIsCreated_MsgIsCategoryAlreadyExists() {

        // Arrange

        // Arrange Person
        final String personEmail = "maria@gmail.com";
        final String categoryDenomination = "Netflix";

        NewPersonCategoryInfoDTO newPersonCategoryInfoDTO = new NewPersonCategoryInfoDTO(categoryDenomination);

        CreatePersonCategoryDTO createPersonCategoryDTO = CreatePersonCategoryDTOAssembler.createDTOFromPrimitiveTypes(personEmail, categoryDenomination);

        // Mock the behaviour of the service's createCategory method
        Mockito.when(service.createCategory(createPersonCategoryDTO)).thenThrow(new InvalidArgumentsBusinessException(CreatePersonCategoryService.CATEGORY_ALREADY_EXIST));

        // Act
        Throwable thrown = assertThrows(InvalidArgumentsBusinessException.class, () -> controller.createPersonCategory(newPersonCategoryInfoDTO, personEmail));

        // Assert
        assertEquals(thrown.getMessage(), CreatePersonCategoryService.CATEGORY_ALREADY_EXIST);
    }

     */

    // PERSON_DOES_NOT_EXIST

    @Test
    public void whenPersonCategoryIsCreated_MsgIsPersonDoesNotExists() {

        // Arrange
        String personEmail = "lebron@gmail.com";
        String categoryDenomination = "Basket";

        NewPersonCategoryInfoDTO newPersonCategoryInfoDTO = new NewPersonCategoryInfoDTO(categoryDenomination);

        CreatePersonCategoryDTO createPersonCategoryDTO = CreatePersonCategoryDTOAssembler.createDTOFromPrimitiveTypes(personEmail, categoryDenomination);

        // Mock the behaviour of the service's createCategory method, so it does not depend on other parts (e.g. DB)
        Mockito.when(service.createCategory(createPersonCategoryDTO)).thenThrow(new NotFoundArgumentsBusinessException(CreatePersonCategoryService.PERSON_DOES_NOT_EXIST));

        // Act
        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () -> controller.createPersonCategory(newPersonCategoryInfoDTO, personEmail));

        //Assert
        assertEquals(thrown.getMessage(), CreatePersonCategoryService.PERSON_DOES_NOT_EXIST);
    }
}