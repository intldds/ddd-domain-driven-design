package com.finance.project.controllerLayer.unitTests;

import com.finance.project.applicationLayer.applicationServices.groupServices.CreateGroupAccountService;
import com.finance.project.controllerLayer.controllersREST.groupControllers.CreateGroupAccountControllerREST;
import com.finance.project.controllerLayer.integrationTests.AbstractTest;
import com.finance.project.domainLayer.exceptions.InvalidArgumentsBusinessException;
import com.finance.project.domainLayer.exceptions.NotFoundArgumentsBusinessException;
import com.finance.project.dtos.dtosAssemblers.CreateGroupAccountDTOAssembler;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import com.finance.project.dtos.dtos.CreateGroupAccountDTO;
import com.finance.project.dtos.dtos.NewGroupAccountInfoDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CreateGroupAccountControllerRESTTest extends AbstractTest {

    @Mock
    private CreateGroupAccountService service;
    @Autowired
    private CreateGroupAccountControllerREST controller;

    //SUCCESS

    /*

//    @Test
//    public void whenGroupAccountIsCreated_MsgSuccess() {
//
//        //Arrange
//        String personEmail = "ilda@gmail.com";
//        String groupDenomination = "Fontes Family";
//        String groupDescription = "All members from Fontes family";
//        String accountDenomination = "LakersAccount";
//        String accountDescription = "Lakers Expenses";
//
//        //Expected result
//        Denomination denomination = Denomination.createDenomination(groupDenomination);
//        Description description = Description.createDescription(groupDescription);
//        DateOfCreation dateOfCreation = DateOfCreation.createDateOfCreation(LocalDate.now());
//
//        GroupDTO isAccountCreatedExpected = GroupDTOAssembler.createDTOFromDomainObject(denomination, description, dateOfCreation);
//
//        NewGroupAccountInfoDTO newGroupAccountInfoDTO = new NewGroupAccountInfoDTO(accountDescription, accountDenomination);
//
//        CreateGroupAccountDTO createGroupAccountDTO = CreateGroupAccountDTOAssembler.createDTOFromPrimitiveTypes(personEmail, groupDenomination, accountDescription, accountDenomination);
//
//        //Expected ResponseEntity
//        ResponseEntity<Object> expectedResponse = new ResponseEntity<>(isAccountCreatedExpected, HttpStatus.CREATED);
//
//        // Mock the behaviour of the service's createAccountAsPeopleInCharge method
//        Mockito.when(service.createAccountAsPeopleInCharge(createGroupAccountDTO)).thenReturn(isAccountCreatedExpected);
//
//        //Act
//        ResponseEntity<Object> isAccountCreated = controller.createGroupAccount(newGroupAccountInfoDTO, personEmail, groupDenomination);
//
//        //Assert
//        assertEquals(expectedResponse, isAccountCreated);
//    }

    //PERSON_NOT_IN_CHARGE

    @Test
    public void whenGroupAccountIsCreated_MsgPersonNotInCharge() {

        //Arrange
        String personEmail = "francis@gmail.com";
        String groupDenomination = "Fontes Family";
        String accountDenomination = "LakersAccount";
        String accountDescription = "Lakers Expenses";

        NewGroupAccountInfoDTO newGroupAccountInfoDTO = new NewGroupAccountInfoDTO(accountDescription, accountDenomination);

        CreateGroupAccountDTO createGroupAccountDTO = CreateGroupAccountDTOAssembler.createDTOFromPrimitiveTypes(personEmail, groupDenomination, accountDescription, accountDenomination);

        // Mock the behaviour of the service's createAccountAsPeopleInCharge method
        Mockito.when(service.createAccountAsPeopleInCharge(createGroupAccountDTO)).thenThrow(new InvalidArgumentsBusinessException(CreateGroupAccountService.PERSON_NOT_IN_CHARGE));

        //Act
        Throwable thrown = assertThrows(InvalidArgumentsBusinessException.class, () -> controller.createGroupAccount(newGroupAccountInfoDTO, personEmail, groupDenomination));

        //Assert
        assertEquals(thrown.getMessage(), CreateGroupAccountService.PERSON_NOT_IN_CHARGE);
    }

    //ACCOUNT_ALREADY_EXIST

//    @Test
//    public void whenGroupAccountIsCreated_MsgAccountAlreadyExists() {
//
//        //Arrange
//        String personEmail = "ilda@gmail.com";
//        String groupDenomination = "Fontes Family";
//        String accountDenomination = "Company";
//        String accountDescription = "Company Expenses";
//
//        NewGroupAccountInfoDTO newGroupAccountInfoDTO = new NewGroupAccountInfoDTO(accountDescription, accountDenomination);
//
//        CreateGroupAccountDTO createGroupAccountDTO = CreateGroupAccountDTOAssembler.createDTOFromPrimitiveTypes(personEmail, groupDenomination, accountDescription, accountDenomination);
//
//        // Mock the behaviour of the service's createAccountAsPeopleInCharge method
//        Mockito.when(service.createAccountAsPeopleInCharge(createGroupAccountDTO)).thenThrow(new InvalidArgumentsBusinessException(US007CreateGroupAccountService.ACCOUNT_ALREADY_EXIST));
//
//        //Act
//        Throwable thrown = assertThrows(InvalidArgumentsBusinessException.class, () -> controller.createGroupAccount(newGroupAccountInfoDTO, personEmail, groupDenomination));
//
//        //Assert
//        assertEquals(thrown.getMessage(), US007CreateGroupAccountService.ACCOUNT_ALREADY_EXIST);
//    }

    //GROUP_DOES_NOT_EXIST

    @Test
    public void whenGroupAccountIsCreated_MsgGroupDoesNotExist() {

        //Arrange
        String personEmail = "ilda@gmail.com";
        String groupDenomination = "Lakers Family";
        final String accountDenomination = "LakersAccount";
        final String accountDescription = "Lakers Expenses";

        NewGroupAccountInfoDTO newGroupAccountInfoDTO = new NewGroupAccountInfoDTO(accountDescription, accountDenomination);

        CreateGroupAccountDTO createGroupAccountDTO = CreateGroupAccountDTOAssembler.createDTOFromPrimitiveTypes(personEmail, groupDenomination, accountDescription, accountDenomination);

        // Mock the behaviour of the service's createAccountAsPeopleInCharge method
        Mockito.when(service.createAccountAsPeopleInCharge(createGroupAccountDTO)).thenThrow(new NotFoundArgumentsBusinessException(CreateGroupAccountService.GROUP_DOES_NOT_EXIST));

        //Act
        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () -> controller.createGroupAccount(newGroupAccountInfoDTO, personEmail, groupDenomination));

        //Assert
        assertEquals(thrown.getMessage(), CreateGroupAccountService.GROUP_DOES_NOT_EXIST);
    }

     */
}