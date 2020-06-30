package com.finance.project.controllerLayer.unitTests;

import com.finance.project.applicationLayer.applicationServices.personServices.CreatePersonService;
import com.finance.project.controllerLayer.controllersREST.personControllers.CreatePersonControllerREST;
import com.finance.project.controllerLayer.integrationTests.AbstractTest;
import com.finance.project.domainLayer.domainEntities.aggregates.person.Birthdate;
import com.finance.project.domainLayer.domainEntities.aggregates.person.Birthplace;
import com.finance.project.domainLayer.domainEntities.aggregates.person.Name;
import com.finance.project.domainLayer.domainEntities.vosShared.CategoryID;
import com.finance.project.domainLayer.domainEntities.vosShared.Email;
import com.finance.project.domainLayer.domainEntities.vosShared.PersonID;
import com.finance.project.domainLayer.entitiesInterfaces.OwnerID;
import com.finance.project.domainLayer.exceptions.InvalidArgumentsBusinessException;
import com.finance.project.domainLayer.exceptions.NotFoundArgumentsBusinessException;
import com.finance.project.dtos.dtos.*;
import com.finance.project.dtos.dtosAssemblers.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CreatePersonControllerRESTTest extends AbstractTest {

    // Not related to a particular US ---- similar to US002_1, but for Person

    @Mock
    private CreatePersonService service;
    @Autowired
    private CreatePersonControllerREST controller;


    // Happy Case: Person Created

    @Test
    public void createPerson_SUCCESS() {

        // Arrange
        String personEmail = "trivino@gmail.com";
        String personName = "Trivino";
        LocalDate personBirthdate = LocalDate.of(1999, 02, 25);
        String personBirthdateString = "1999-02-25";
        String personBirthplace = "Porto";

        // Expected CreatePerson DTO
        Email email = Email.createEmail(personEmail);
        Name name = Name.createName(personName);
        Birthdate birthdate = Birthdate.createBirthdate(personBirthdate);
        Birthplace birthplace = Birthplace.createBirthplace(personBirthplace);
        PersonID fatherID = null;
        PersonID motherID = null;

        PersonDTO isPersonCreatedExpected = PersonDTOAssembler.createDTOFromDomainObject(email, name, birthdate, birthplace, fatherID, motherID);

        // Info DTO
        NewCreatePersonInfoDTO newCreatePersonInfoDTO = new NewCreatePersonInfoDTO(personEmail, personName, personBirthdateString, personBirthplace);

        // DTO
        CreatePersonDTO createPersonDTO = CreatePersonDTOAssembler.createDTOFromPrimitiveTypes(personEmail, personName, personBirthdateString, personBirthplace);

        // Expected Response
        ResponseEntity<Object> expectedResponse = new ResponseEntity<>(isPersonCreatedExpected, HttpStatus.CREATED);

        // Act
        Mockito.when(service.createPerson(createPersonDTO)).thenReturn(isPersonCreatedExpected);
        ResponseEntity<Object> isPersonCreated = controller.createPerson(newCreatePersonInfoDTO);

        // Assert
        assertEquals(expectedResponse, isPersonCreated);
    }


    // Person already exist ---- check Bootstrapping

    @Test
    public void createPerson_NOTCreated_PersonAlreadyExist() {

        // Arrange
        String personEmail = "maria@gmail.com";
        String personName = "Maria Silva";
        LocalDate personBirthdate = LocalDate.of(1973, 07, 25);
        String personBirthdateString = "1973-07-25";
        String personBirthplace = "Braga";

        // Expected CreatePerson DTO
        Email email = Email.createEmail(personEmail);
        Name name = Name.createName(personName);
        Birthdate birthdate = Birthdate.createBirthdate(personBirthdate);
        Birthplace birthplace = Birthplace.createBirthplace(personBirthplace);
        PersonID fatherID = null;
        PersonID motherID = null;

        PersonDTO isPersonCreatedExpected = PersonDTOAssembler.createDTOFromDomainObject(email, name, birthdate, birthplace, fatherID, motherID);

        // Info DTO
        NewCreatePersonInfoDTO newCreatePersonInfoDTO = new NewCreatePersonInfoDTO(personEmail, personName, personBirthdateString, personBirthplace);

        // DTO
        CreatePersonDTO createPersonDTO = CreatePersonDTOAssembler.createDTOFromPrimitiveTypes(personEmail, personName, personBirthdateString, personBirthplace);

        Mockito.when(service.createPerson(createPersonDTO)).thenThrow(new InvalidArgumentsBusinessException(CreatePersonService.PERSON_ALREADY_EXIST));

        // Act
        Throwable thrown = assertThrows(InvalidArgumentsBusinessException.class, () -> controller.createPerson(newCreatePersonInfoDTO));

        //Assert
        assertEquals(thrown.getMessage(), CreatePersonService.PERSON_ALREADY_EXIST);
    }


    /*
    @Test
    public void getPersonByEmail_SUCCESS() {

        // Arrange
        String personEmail = "maria@gmail.com";
        PersonEmailDTO personEmailDTO = PersonEmailDTOAssembler.createPersonEmailDTO(personEmail);
        String personName = "Maria Silva";
        LocalDate personBirthdate = LocalDate.of(1973, 07, 25);
        String personBirthplace = "Braga";

        // Expected Person DTO
        Email email = Email.createEmail(personEmail);
        Name name = Name.createName(personName);
        Birthdate birthdate = Birthdate.createBirthdate(personBirthdate);
        Birthplace birthplace = Birthplace.createBirthplace(personBirthplace);
        PersonID fatherID = null;
        PersonID motherID = null;

        PersonDTO personExpected = PersonDTOAssembler.createDTOFromDomainObject(email, name, birthdate, birthplace, fatherID, motherID);

        // Expected Response
        ResponseEntity<Object> expectedResponse = new ResponseEntity<>(personExpected, HttpStatus.OK);

        // Act
        Mockito.when(service.getPersonByEmail(personEmailDTO)).thenReturn(personExpected);
        ResponseEntity<Object> person = controller.getPersonByEmail(personEmail);

        // Assert
        assertEquals(expectedResponse, person);
    }

     */


    @Test
    public void getPersonByEmail_PersonDoesNotExist() {

        // Arrange
        String personEmail = "fonseca@lol.com";
        PersonEmailDTO personEmailDTO = PersonEmailDTOAssembler.createPersonEmailDTO(personEmail);

        Mockito.when(service.getPersonByEmail(personEmailDTO)).thenThrow(new InvalidArgumentsBusinessException(CreatePersonService.PERSON_DOES_NOT_EXIST));

        // Act
        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () -> controller.getPersonByEmail(personEmail));

        // Assert
        assertEquals(thrown.getMessage(), CreatePersonService.PERSON_DOES_NOT_EXIST);
    }


    @Test
    public void getPersonAccounts_SUCCESS() {

        // Arrange
        String personEmail = "miguel@gmail.com";
        PersonEmailDTO personEmailDTO = PersonEmailDTOAssembler.createPersonEmailDTO(personEmail);

        String personName = "Alexandre Bragança";
        LocalDate personBirthdate = LocalDate.of(1969, 10, 23);
        String personBirthplace = "Vila Nova de Gaia";

        // Expected Accounts DTO
        String companyDenomination = "Company";
        String companyDescription = "Company account";
        String bankAccountDenomination = "Bank Account";
        String bankAccountDescription = "Personal bank account";
        AccountDTO accountDTO1 = AccountDTOAssembler.createDTOFromPrimitiveTypes(companyDenomination, companyDescription);
        AccountDTO accountDTO2 = AccountDTOAssembler.createDTOFromPrimitiveTypes(bankAccountDenomination, bankAccountDescription);

        List<AccountDTO> accounts = new ArrayList<>();
        accounts.add(accountDTO1);
        accounts.add(accountDTO2);

        AccountsDTO expectedAccountsDTO = AccountsDTOAssembler.createDTOFromDomainObject(accounts);

        // Expected Response
        ResponseEntity<Object> expectedResponse = new ResponseEntity<>(expectedAccountsDTO, HttpStatus.OK);

        //Act
        Mockito.when(service.getPersonAccounts(personEmailDTO)).thenReturn(expectedAccountsDTO);
        ResponseEntity<Object> personAccounts = controller.getPersonAccounts(personEmail);

//        Expected results
        Object personAccountsStatusCodeValue = personAccounts.getStatusCodeValue();
        Object personAccountsHeaders = personAccounts.getHeaders().toString();
        Object personAccountsResponseBody = personAccounts.getBody().toString();
        Object expectedStatusCodeValue = expectedResponse.getStatusCodeValue();
        Object expectedHeaders = expectedResponse.getHeaders().toString();
        Object expectedResponseBody = expectedResponse.getBody().toString();

        //Assert
        assertEquals(expectedStatusCodeValue,personAccountsStatusCodeValue);
        assertEquals(personAccountsHeaders,expectedHeaders);
        assertEquals(personAccountsResponseBody,expectedResponseBody);
    }


    @Test
    public void getPersonAccounts_PersonDoesNotExist() {

        // Arrange
        String personEmail = "fonseca@yahoo.com";
        PersonEmailDTO personEmailDTO = PersonEmailDTOAssembler.createPersonEmailDTO(personEmail);

        Mockito.when(service.getPersonAccounts(personEmailDTO)).thenThrow(new InvalidArgumentsBusinessException(CreatePersonService.PERSON_DOES_NOT_EXIST));

        // Act
        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () -> controller.getPersonAccounts(personEmail));

        // Assert
        assertEquals(thrown.getMessage(), CreatePersonService.PERSON_DOES_NOT_EXIST);
    }

    /*

    @Test
    public void getPersonCategories_SUCCESS() {

        // Arrange
        String personEmail = "alexandre@gmail.com";
        PersonEmailDTO personEmailDTO = PersonEmailDTOAssembler.createPersonEmailDTO(personEmail);

        String personName = "Alexandre Bragança";
        LocalDate personBirthdate = LocalDate.of(1969, 10, 23);
        String personBirthplace = "Vila Nova de Gaia";

        // Expected Categories DTO
        String denomination1 = "Company";
        String denomination2 = "Bank Account";

        OwnerID ownerID = PersonID.createPersonID(personEmail);
        CategoryID categoryID1 = CategoryID.createCategoryID(denomination1, ownerID);
        CategoryID categoryID2 = CategoryID.createCategoryID(denomination2, ownerID);

        List<CategoryID> categories = new ArrayList<>();
        categories.add(categoryID1);
        categories.add(categoryID2);

        CategoriesDTO expectedCategoriesDTO = CategoriesDTOAssembler.createDTOFromDomainObject(categories);

        // Expected Response
        ResponseEntity<Object> expectedResponse = new ResponseEntity<>(expectedCategoriesDTO, HttpStatus.OK);

        // Act
        Mockito.when(service.getPersonCategories(personEmailDTO)).thenReturn(expectedCategoriesDTO);
        ResponseEntity<Object> personCategories = controller.getPersonCategories(personEmail);

        //        Expected results
        Object personCategoriesStatusCodeValue = personCategories.getStatusCodeValue();
        Object personCategoriesHeaders = personCategories.getHeaders().toString();
        Object personCategoriesResponseBody = personCategories.getBody().toString();
        Object expectedStatusCodeValue = expectedResponse.getStatusCodeValue();
        Object expectedHeaders = expectedResponse.getHeaders().toString();
        Object expectedResponseBody = expectedResponse.getBody().toString();

        //Assert
        assertEquals(expectedStatusCodeValue,personCategoriesStatusCodeValue);
        assertEquals(expectedHeaders,personCategoriesHeaders);
        assertEquals(expectedResponseBody,personCategoriesResponseBody);
    }

     */



    @Test
    public void getPersonCategories_PersonDoesNotExist() {

        //Arrange
        String personEmail = "vale@iol.pt";
        PersonEmailDTO personEmailDTO = PersonEmailDTOAssembler.createPersonEmailDTO(personEmail);

        Mockito.when(service.getPersonCategories(personEmailDTO)).thenThrow(new InvalidArgumentsBusinessException(CreatePersonService.PERSON_DOES_NOT_EXIST));

        //Act
        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () -> controller.getPersonCategories(personEmail));

        //Assert
        assertEquals(thrown.getMessage(), CreatePersonService.PERSON_DOES_NOT_EXIST);
    }



//    @Test
//    public void getPersonLedger_SUCCESS() {
//
//        // Arrange
//        String personEmail = "alexandre@gmail.com";
//        PersonEmailDTO personEmailDTO = PersonEmailDTOAssembler.createPersonEmailDTO(personEmail);
//        String personName = "Alexandre Bragança";
//        LocalDate personBirthdate = LocalDate.of(1969, 10, 23);
//        String personBirthplace = "Vila Nova de Gaia";
//
//        //Expected Transactions Dto
//        String category = "Salary";
//        String account1 = "Company";
//        String account2 = "Bank Account";
//
//        OwnerID ownerID = PersonID.createPersonID(personEmail);
//
//        AccountID accountID1 = AccountID.createAccountID(account1, ownerID);
//        AccountID accountID2 = AccountID.createAccountID(account2, ownerID);
//
//        CategoryID categoryID = CategoryID.createCategoryID(category, ownerID);
//
//        Transaction transaction = Transaction.createTransaction(categoryID, "credit", "January salary", 1500.0, LocalDate.of(2020, 01, 21), accountID1, accountID2);
//        TransactionDTOout transactionDTOout = TransactionDTOoutAssembler.createTransactionDTOout(transaction);
//        List<TransactionDTOout> transactionDTOouts = new ArrayList<>();
//        transactionDTOouts.add(transactionDTOout);
//
//        TransactionsDTO expectedTransactionsDTO = TransactionsDTOAssembler.createDTOFromPrimitiveTypes(transactionDTOouts);
//
//
//        // Expected Response
//        ResponseEntity<Object> expectedResponse = new ResponseEntity<>(expectedTransactionsDTO, HttpStatus.OK);
//
//        //Act
//        Mockito.when(service.getPersonLedger(personEmailDTO)).thenReturn(expectedTransactionsDTO);
//        ResponseEntity<Object> personTransactions = controller.getPersonLedger(personEmail);
//
//        //        Expected results
//        Object personTransactionsStatusCodeValue = personTransactions.getStatusCodeValue();
//        Object personTransactionsHeaders = personTransactions.getHeaders().toString();
//        Object personTransactionsResponseBody = personTransactions.getBody().toString();
//        Object expectedStatusCodeValue = expectedResponse.getStatusCodeValue();
//        Object expectedHeaders = expectedResponse.getHeaders().toString();
//        Object expectedResponseBody = expectedResponse.getBody().toString();
//
//        //Assert
//        assertEquals(expectedStatusCodeValue,personTransactionsStatusCodeValue);
//        assertEquals(expectedHeaders,personTransactionsHeaders);
//        assertEquals(expectedResponseBody,personTransactionsResponseBody);
//    }


    @Test
    public void getPersonLedger_PersonDoesNotExist() {

        // Arrange
        String personEmail = "ricky@alvarez.com";
        PersonEmailDTO personEmailDTO = PersonEmailDTOAssembler.createPersonEmailDTO(personEmail);

        Mockito.when(service.getPersonLedger(personEmailDTO)).thenThrow(new InvalidArgumentsBusinessException(CreatePersonService.PERSON_DOES_NOT_EXIST));

        // Act
        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () -> controller.getPersonByEmail(personEmail));

        //Assert
        assertEquals(thrown.getMessage(), CreatePersonService.PERSON_DOES_NOT_EXIST);
    }

    /*

    @Test
    public void getPersonSiblings_SUCCESS() {

        // Arrange
        String personEmail = "paulo@gmail.com";
        PersonEmailDTO personEmailDTO = PersonEmailDTOAssembler.createPersonEmailDTO(personEmail);
        String personName = "Paulo Fontes";
        LocalDate personBirthdate = LocalDate.of(1993, 03, 15);
        String personBirthplace = "Vila Nova de Gaia";

        // Expected Siblings DTO
        String sibling = "helder@gmail.com";
        PersonID siblingID = PersonID.createPersonID(sibling);

        List<PersonID> siblings = new ArrayList<>();
        siblings.add(siblingID);

        SiblingsDTO expectedSiblingsDTO = SiblingsDTOAssembler.createDTOFromDomainObject(siblings);

        // Expected Response
        ResponseEntity<Object> expectedResponse = new ResponseEntity<>(expectedSiblingsDTO, HttpStatus.OK);

        // Act
        Mockito.when(service.getPersonSiblings(personEmailDTO)).thenReturn(expectedSiblingsDTO);
        ResponseEntity<Object> personSiblings = controller.getPersonSiblings(personEmail);

        //        Expected results
        Object personSiblingsStatusCodeValue = personSiblings.getStatusCodeValue();
        Object personSiblingsHeaders = personSiblings.getHeaders().toString();
        Object personSiblingsResponseBody = personSiblings.getBody().toString();
        Object expectedStatusCodeValue = expectedResponse.getStatusCodeValue();
        Object expectedHeaders = expectedResponse.getHeaders().toString();
        Object expectedResponseBody = expectedResponse.getBody().toString();

        //Assert
        assertEquals(expectedStatusCodeValue,personSiblingsStatusCodeValue);
        assertEquals(expectedHeaders,personSiblingsHeaders);
        assertEquals(expectedResponseBody,personSiblingsResponseBody);
    }




     */


    @Test
    public void getPersonSiblings_PersonDoesNotExist() {

        //Arrange
        String personEmail = "vale@iol.pt";
        PersonEmailDTO personEmailDTO = PersonEmailDTOAssembler.createPersonEmailDTO(personEmail);
        Mockito.when(service.getPersonCategories(personEmailDTO)).thenThrow(new InvalidArgumentsBusinessException(CreatePersonService.PERSON_DOES_NOT_EXIST));

        //Act
        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () -> controller.getPersonCategories(personEmail));

        //Assert
        assertEquals(thrown.getMessage(), CreatePersonService.PERSON_DOES_NOT_EXIST);
    }
}
