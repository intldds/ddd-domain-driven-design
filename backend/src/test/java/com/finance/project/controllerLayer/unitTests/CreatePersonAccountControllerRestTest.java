package com.finance.project.controllerLayer.unitTests;


import com.finance.project.applicationLayer.applicationServices.personServices.CreatePersonAccountService;
import com.finance.project.controllerLayer.controllersREST.personControllers.CreatePersonAccountControllerREST;
import com.finance.project.controllerLayer.integrationTests.AbstractTest;
import com.finance.project.domainLayer.domainEntities.aggregates.person.Birthdate;
import com.finance.project.domainLayer.domainEntities.aggregates.person.Birthplace;
import com.finance.project.domainLayer.domainEntities.aggregates.person.Name;
import com.finance.project.domainLayer.domainEntities.vosShared.Email;
import com.finance.project.domainLayer.domainEntities.vosShared.PersonID;
import com.finance.project.domainLayer.exceptions.InvalidArgumentsBusinessException;
import com.finance.project.domainLayer.exceptions.NotFoundArgumentsBusinessException;
import com.finance.project.dtos.dtosAssemblers.CreatePersonAccountDTOAssembler;
import com.finance.project.dtos.dtosAssemblers.PersonDTOAssembler;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.finance.project.dtos.dtos.CreatePersonAccountDTO;
import com.finance.project.dtos.dtos.NewPersonAccountInfoDTO;
import com.finance.project.dtos.dtos.PersonDTO;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CreatePersonAccountControllerRestTest extends AbstractTest {

    @Mock
    private CreatePersonAccountService service;
    @Autowired
    private CreatePersonAccountControllerREST controller;

    // SUCCESS

    @Test
    public void whenPersonAccountIsCreated_thenRetrievedMsgIsSuccess() {

        // Arrange

            // Arrange Person
        final String personEmail = "pedro@gmail.com";
        final String personName = "Fontes";
        final LocalDate personBirthdate = LocalDate.of(1964, 02, 16);
        final String personBirthplace = "Vila Nova de Gaia";

            // Arrange Account
        final String accountDescription = "Tickets for Roland Garros";
        final String accountDenomination = "Tennis";

        // Expected result
        Email email = Email.createEmail(personEmail);
        Name name = Name.createName(personName);
        Birthdate birthdate = Birthdate.createBirthdate(personBirthdate);
        Birthplace birthplace = Birthplace.createBirthplace(personBirthplace);
        PersonID fatherID = null;
        PersonID motherID = null;

        PersonDTO isAccountCreatedExpected = PersonDTOAssembler.createDTOFromDomainObject(email, name, birthdate, birthplace, fatherID, motherID);

        NewPersonAccountInfoDTO newPersonAccountInfoDTO = new NewPersonAccountInfoDTO(accountDescription, accountDenomination);

        CreatePersonAccountDTO createPersonAccountDTO = CreatePersonAccountDTOAssembler.createDTOFromPrimitiveTypes(personEmail, accountDescription, accountDenomination);

        // Expected Response Entity
        ResponseEntity<Object> expectedResponse = new ResponseEntity<>(isAccountCreatedExpected, HttpStatus.CREATED);

        // Mock the behaviour of the service's createAccount method, so it does not depend on other parts (e.g. DB)
        Mockito.when(service.createAccount(createPersonAccountDTO)).thenReturn(isAccountCreatedExpected);

        // Act
        ResponseEntity<Object> isAccountCreated = controller.createPersonAccount(newPersonAccountInfoDTO, personEmail);

        //       Expected results
        Object isAccountCreatedStatusCodeValue = isAccountCreated.getStatusCodeValue();
        Object isAccountCreatedHeaders = isAccountCreated.getHeaders().toString();
        Object expectedStatusCodeValue = expectedResponse.getStatusCodeValue();
        Object expectedHeaders = expectedResponse.getHeaders().toString();

        // Assert
        assertEquals(expectedStatusCodeValue,isAccountCreatedStatusCodeValue);
        assertEquals(expectedHeaders,isAccountCreatedHeaders);
    }



    // ACCOUNT_ALREADY_EXIST exception - using account denomination & description from "Bootstrapping"

    @Test
    public void whenPersonAccountIsCreated_thenRetrievedMsgIsAccountAlreadyExists() {

        // Arrange

            // Arrange Person
        final String personEmail = "francis@gmail.com";
        final String accountDescription = "Personal bank account";
        final String accountDenomination = "Bank Account";

        NewPersonAccountInfoDTO newPersonAccountInfoDTO = new NewPersonAccountInfoDTO(accountDescription, accountDenomination);

        CreatePersonAccountDTO createPersonAccountDTO = CreatePersonAccountDTOAssembler.createDTOFromPrimitiveTypes(personEmail, accountDescription, accountDenomination);

        // Mock the behaviour of the service's createAccount method, so it does not depend on other parts (e.g. DB)
        Mockito.when(service.createAccount(createPersonAccountDTO)).thenThrow(new InvalidArgumentsBusinessException(CreatePersonAccountService.ACCOUNT_ALREADY_EXIST));

        // Act
        Throwable thrown = assertThrows(InvalidArgumentsBusinessException.class, () -> controller.createPersonAccount(newPersonAccountInfoDTO, personEmail));

        // Assert
        assertEquals(thrown.getMessage(), CreatePersonAccountService.ACCOUNT_ALREADY_EXIST);
    }


    // PERSON_DOES_NOT_EXIST exception

    @Test
    public void whenPersonAccountIsCreated_thenRetrievedMsgIsPersonDoesNotExists() {

        // Arrange
        String personEmail = "lemos@yahoo.com";
        String accountDescription = "Lemos Family sports expenses";
        String accountDenomination = "Tennis";

        NewPersonAccountInfoDTO newPersonAccountInfoDTO = new NewPersonAccountInfoDTO(accountDescription, accountDenomination);

        CreatePersonAccountDTO createPersonAccountDTO = CreatePersonAccountDTOAssembler.createDTOFromPrimitiveTypes(personEmail, accountDescription, accountDenomination);

        // Mock the behaviour of the service's createAccount method, so it does not depend on other parts (e.g. DB)
        Mockito.when(service.createAccount(createPersonAccountDTO)).thenThrow(new NotFoundArgumentsBusinessException(CreatePersonAccountService.PERSON_DOES_NOT_EXIST));

        // Act
        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () -> controller.createPersonAccount(newPersonAccountInfoDTO, personEmail));

        //Assert
        assertEquals(thrown.getMessage(), CreatePersonAccountService.PERSON_DOES_NOT_EXIST);
    }

}

