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


    // PERSON_DOES_NOT_EXIST exception

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