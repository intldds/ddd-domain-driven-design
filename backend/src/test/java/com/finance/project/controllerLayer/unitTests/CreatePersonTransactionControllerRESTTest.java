package com.finance.project.controllerLayer.unitTests;

import com.finance.project.applicationLayer.applicationServices.personServices.CreatePersonTransactionService;
import com.finance.project.controllerLayer.controllersREST.personControllers.CreatePersonTransactionControllerREST;
import com.finance.project.controllerLayer.integrationTests.AbstractTest;
import com.finance.project.domainLayer.exceptions.NotFoundArgumentsBusinessException;
import com.finance.project.dtos.dtosAssemblers.CreatePersonTransactionDTOAssembler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import com.finance.project.dtos.dtos.CreatePersonTransactionDTO;
import com.finance.project.dtos.dtos.NewPersonTransactionInfoDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CreatePersonTransactionControllerRESTTest extends AbstractTest {

    @Mock
    private CreatePersonTransactionService service;
    @Autowired
    private CreatePersonTransactionControllerREST controller;

    //SUCCESS

//    @Test
//    @DisplayName("Test For createTransactionAsPerson() | Success")
//    void createTransactionAsPerson_Success() {
//
//        // Arrange
//        String personEmail = "ricardo@gmail.com";
//        String personName = "Ricardo";
//        LocalDate personBirthdate = LocalDate.of(2020, 05, 26);
//        String personBirthplace = "Porto";
//        String denominationCategory = "IRS";
//        String type = "debit";
//        String description = "January IRS";
//        double amount = 150.0;
//        String denominationAccountDeb = "Bank Account";
//        String denominationAccountCred = "State";
//        final String date = "2020-01-27";
//
//        // Expected CreatePerson DTO
//        Email email = Email.createEmail(personEmail);
//        Name name = Name.createName(personName);
//        Birthdate birthdate = Birthdate.createBirthdate(personBirthdate);
//        Birthplace birthplace = Birthplace.createBirthplace(personBirthplace);
//        PersonID fatherID = null;
//        PersonID motherID = null;
//
//        PersonDTO isTransactionCreatedExpected = PersonDTOAssembler.createDTOFromDomainObject(email, name, birthdate, birthplace, fatherID, motherID);
//
//        // Info DTO
//        NewPersonTransactionInfoDTO newPersonTransactionInfoDTO = new NewPersonTransactionInfoDTO(denominationCategory, type, description, amount, denominationAccountDeb, denominationAccountCred, date);
//
//        // DTO
//        CreatePersonTransactionDTO createPersonTransactionDTO = CreatePersonTransactionDTOAssembler.createDTOFromPrimitiveTypes(personEmail, denominationCategory, type, description, amount, denominationAccountDeb, denominationAccountCred, date);
//
//        // Response Entity
//        ResponseEntity<Object> expectedResponse = new ResponseEntity<>(isTransactionCreatedExpected, HttpStatus.CREATED);
//
//        // Mock the behaviour of the service's createTransactionAsPerson method,
//        // so it does not depend on other parts (e.g. DB)
//        Mockito.when(service.createTransactionAsPerson(createPersonTransactionDTO)).thenReturn(isTransactionCreatedExpected);
//
//        //Act
//        ResponseEntity<Object> isTransactionCreated = controller.createPersonTransaction(newPersonTransactionInfoDTO, personEmail);
//
//        //Assert
//        assertEquals(expectedResponse, isTransactionCreated);
//
//    }

    //DIFFERENT CATEGORY DENOMINATION

//    @Test
//    @DisplayName("Test For createTransactionAsPerson() | different Category denomination | Insuccess")
//    void createTransactionAsPersonDifferentCategoryDenomination() {
//
//        // Arrange
//        String email = "ricardo@gmail.com";
//        String denominationCategory = "IRS";
//        String type = "debit";
//        String description = "January IRS";
//        double amount = 150.0;
//        String denominationAccountDeb = "Bank Account";
//        String denominationAccountCred = "State";
//        final String date = "2020-01-27";
//
//        // Info DTO
//        NewPersonTransactionInfoDTO newPersonTransactionInfoDTO = new NewPersonTransactionInfoDTO(denominationCategory, type, description, amount, denominationAccountDeb, denominationAccountCred, date);
//
//        // DTO
//        CreatePersonTransactionDTO createPersonTransactionDTO = CreatePersonTransactionDTOAssembler.createDTOFromPrimitiveTypes(email, denominationCategory, type, description, amount, denominationAccountDeb, denominationAccountCred, date);
//
//        // Mock the behaviour of the service's createTransactionAsPerson method,
//        // so it does not depend on other parts (e.g. DB)
//        Mockito.when(service.createTransactionAsPerson(createPersonTransactionDTO)).thenThrow(new NotFoundArgumentsBusinessException(US008CreatePersonTransactionService.CATEGORY_DOES_NOT_EXIST));
//
//        //Act
//        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () -> controller.createPersonTransaction(newPersonTransactionInfoDTO, email));
//
//        //Assert
//        assertEquals(thrown.getMessage(), US008CreatePersonTransactionService.CATEGORY_DOES_NOT_EXIST);
//
//    }

    //DIFFERENT DEBIT ACCOUNT DENOMINATION

    /*

    @Test
    @DisplayName("Test For createTransactionAsPerson() | different debit account denomination | Insuccess")
    void createTransactionAsPersonDifferentAccountDebDenomination() {

        // Arrange
        String email = "miguel@gmail.com";
        String denominationCategory = "IRS";
        String type = "debit";
        String description = "January IRS";
        double amount = 150.0;
        String denominationAccountDeb = "Jon Account";
        String denominationAccountCred = "State";
        final String date = "2020-01-27";

        // Info DTO
        NewPersonTransactionInfoDTO newPersonTransactionInfoDTO = new NewPersonTransactionInfoDTO(denominationCategory, type, description, amount, denominationAccountDeb, denominationAccountCred, date);

        // DTO
        CreatePersonTransactionDTO createPersonTransactionDTO = CreatePersonTransactionDTOAssembler.createDTOFromPrimitiveTypes(email, denominationCategory, type, description, amount, denominationAccountDeb, denominationAccountCred, date);

        // Mock the behaviour of the service's createTransactionAsPerson method,
        // so it does not depend on other parts (e.g. DB)
        Mockito.when(service.createTransactionAsPerson(createPersonTransactionDTO)).thenThrow(new NotFoundArgumentsBusinessException(CreatePersonTransactionService.ACCOUNT_DEB_DOES_NOT_EXIST));

        //Act
        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () -> controller.createPersonTransaction(newPersonTransactionInfoDTO, email));

        //Assert
        assertEquals(thrown.getMessage(), CreatePersonTransactionService.ACCOUNT_DEB_DOES_NOT_EXIST);
    }



    //DIFFERENT CREDIT ACCOUNT DENOMINATION

    @Test
    @DisplayName("Test For createTransactionAsPerson() | different credit account denomination | Insuccess")
    void createTransactionAsPersonDifferentAccountCredDenomination() {

        // Arrange
        String email = "miguel@gmail.com";
        String denominationCategory = "IRS";
        String type = "debit";
        String description = "January IRS";
        double amount = 150.0;
        String denominationAccountDeb = "Bank Account";
        String denominationAccountCred = "Jon Account";
        final String date = "2020-01-27";

        // Info DTO
        NewPersonTransactionInfoDTO newPersonTransactionInfoDTO = new NewPersonTransactionInfoDTO(denominationCategory, type, description, amount, denominationAccountDeb, denominationAccountCred, date);

        // DTO
        CreatePersonTransactionDTO createPersonTransactionDTO = CreatePersonTransactionDTOAssembler.createDTOFromPrimitiveTypes(email, denominationCategory, type, description, amount, denominationAccountDeb, denominationAccountCred, date);

        // Mock the behaviour of the service's createTransactionAsPerson method,
        // so it does not depend on other parts (e.g. DB)
        Mockito.when(service.createTransactionAsPerson(createPersonTransactionDTO)).thenThrow(new NotFoundArgumentsBusinessException(CreatePersonTransactionService.ACCOUNT_CRED_DOES_NOT_EXIST));

        //Act
        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () -> controller.createPersonTransaction(newPersonTransactionInfoDTO, email));

        //Assert
        assertEquals(thrown.getMessage(), CreatePersonTransactionService.ACCOUNT_CRED_DOES_NOT_EXIST);



    }


     */
}