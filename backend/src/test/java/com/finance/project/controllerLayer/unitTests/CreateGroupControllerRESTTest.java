package com.finance.project.controllerLayer.unitTests;

import com.finance.project.applicationLayer.applicationServices.groupServices.CreateGroupService;
import com.finance.project.controllerLayer.controllersREST.groupControllers.CreateGroupControllerREST;
import com.finance.project.controllerLayer.integrationTests.AbstractTest;
import com.finance.project.domainLayer.domainEntities.aggregates.ledger.Transaction;
import com.finance.project.domainLayer.domainEntities.vosShared.*;
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
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

class CreateGroupControllerRESTTest extends AbstractTest {

    //US002.1 Como utilizador, quero criar grupo, tornando-me administrador do grupo.

    /*

    @Mock
    private CreateGroupService service;
    @Autowired
    private CreateGroupControllerREST controller;

    //Happy Case: Group Created!

    @Test
    public void createGroupAsPersonInCharge_SUCCESS() {

        //Arrange
        String personEmail = "rita@gmail.com";
        String groupDenomination = "Friday FootPlayers";
        String groupDescription = "All members from Friday Football Players group";

        //Expected Boolean Dto
        Denomination denomination = Denomination.createDenomination(groupDenomination);
        Description description = Description.createDescription(groupDescription);
        DateOfCreation dateOfCreation = DateOfCreation.createDateOfCreation(LocalDate.now());
        GroupDTO isGroupCreatedExpected = GroupDTOAssembler.createDTOFromDomainObject(denomination, description, dateOfCreation);

        //Info DTO
        NewCreateGroupInfoDTO newCreateGroupInfoDTO = new NewCreateGroupInfoDTO(personEmail, groupDenomination, groupDescription);

        //DTO
        CreateGroupDTO createGroupDTO = CreateGroupDTOAssembler.createDTOFromPrimitiveTypes(personEmail, groupDenomination, groupDescription);

        //Expected Response
        ResponseEntity<Object> expectedResponse = new ResponseEntity<>(isGroupCreatedExpected, HttpStatus.CREATED);

        //Act
        Mockito.when(service.createGroupAsPersonInCharge(createGroupDTO)).thenReturn(isGroupCreatedExpected);
        ResponseEntity<Object> isGroupCreated = controller.createGroupAsPersonInCharge(newCreateGroupInfoDTO);

        //Assert
        assertEquals(expectedResponse, isGroupCreated);
    }

    //Sad Case: Group already exist

    @Test
    public void createGroupAsPersonInCharge_NOTCreated_GroupAlreadyExist() {

        //Arrange
        String personEmail = "rita@gmail.com";
        String groupDenomination = "Silva Family";
        String groupDescription = "All members of Silva Family";

        //Info DTO
        NewCreateGroupInfoDTO newCreateGroupInfoDTO = new NewCreateGroupInfoDTO(personEmail, groupDenomination, groupDescription);

        //DTO
        CreateGroupDTO createGroupDTO = CreateGroupDTOAssembler.createDTOFromPrimitiveTypes(personEmail, groupDenomination, groupDescription);

        Mockito.when(service.createGroupAsPersonInCharge(createGroupDTO)).thenThrow(new InvalidArgumentsBusinessException(CreateGroupService.GROUP_ALREADY_EXISTS));

        //Act
        Throwable thrown = assertThrows(InvalidArgumentsBusinessException.class, () -> controller.createGroupAsPersonInCharge(newCreateGroupInfoDTO));

        //Assert
        assertEquals(thrown.getMessage(), CreateGroupService.GROUP_ALREADY_EXISTS);
    }

    //Sad Case: Person does not exist

    @Test
    public void createGroupAsPersonInCharge_NOTCreated_PersonDoesNotExist() {

        //Arrange
        String personEmail = "telmo@gmail.com";
        String groupDenomination = "Amaral Family";
        String groupDescription = "All members of Amaral Family";

        //Info DTO
        NewCreateGroupInfoDTO newCreateGroupInfoDTO = new NewCreateGroupInfoDTO(personEmail, groupDenomination, groupDescription);

        //DTO
        CreateGroupDTO createGroupDTO = CreateGroupDTOAssembler.createDTOFromPrimitiveTypes(personEmail, groupDenomination, groupDescription);

        Mockito.when(service.createGroupAsPersonInCharge(createGroupDTO)).thenThrow(new NotFoundArgumentsBusinessException(CreateGroupService.PERSON_DOES_NOT_EXIST));

        //Act
        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () -> controller.createGroupAsPersonInCharge(newCreateGroupInfoDTO));

        //Assert
        assertEquals(thrown.getMessage(), CreateGroupService.PERSON_DOES_NOT_EXIST);
    }

    @Test
    public void getGroupByDenomination_SUCCESS() {

        //Arrange
        String groupDenomination = "Fontes Family";
        String groupDescription = "All members from Fontes family";

        //Expected Boolean Dto
        Denomination denomination = Denomination.createDenomination(groupDenomination);
        Description description = Description.createDescription(groupDescription);
        DateOfCreation dateOfCreation = DateOfCreation.createDateOfCreation(LocalDate.now());
        GroupDTO groupExpected = GroupDTOAssembler.createDTOFromDomainObject(denomination, description, dateOfCreation);

        //Expected Response
        ResponseEntity<Object> expectedResponse = new ResponseEntity<>(groupExpected, HttpStatus.OK);

        //Act
        Mockito.when(service.getGroupByDenomination(groupDenomination)).thenReturn(groupExpected);
        ResponseEntity<Object> group = controller.getGroupByDenomination(groupDenomination);

        //Assert
        assertEquals(expectedResponse, group);
    }


    @Test
    public void getGroupByDenomination_GroupDoesNotExist() {

        //Arrange
        String groupDenomination = "Vale Family";

        Mockito.when(service.getGroupByDenomination(groupDenomination)).thenThrow(new NotFoundArgumentsBusinessException(CreateGroupService.GROUP_DOES_NOT_EXISTS));

        //Act
        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () -> controller.getGroupByDenomination(groupDenomination));

        //Assert
        assertEquals(thrown.getMessage(), CreateGroupService.GROUP_DOES_NOT_EXISTS);
    }

    @Test
    public void getGroupAdmins_SUCCESS() {

        //Arrange
        String personEmail = "francis@gmail.com";
        String groupDenomination = "Sunday Runners";
        String groupDescription = "All members from Sunday Runners group";

        //Expected Boolean Dto
        List<PersonID> peopleInCharge = new ArrayList<>();
        PersonID personID = PersonID.createPersonID(personEmail);
        peopleInCharge.add(personID);
        GroupAdminsDTO expectedGroupAdminsDTO = GroupAdminsDTOAssembler.createDTOFromDomainObject(peopleInCharge);


        //Expected Response
        ResponseEntity<Object> expectedResponse = new ResponseEntity<>(expectedGroupAdminsDTO, HttpStatus.OK);

        //Act
        Mockito.when(service.getGroupAdmins(groupDenomination)).thenReturn(expectedGroupAdminsDTO);
        ResponseEntity<Object> groupAdmins = controller.getGroupAdmins(groupDenomination);

        //       Expected results
        Object groupAdminsStatusCodeValue = groupAdmins.getStatusCodeValue();
        Object groupAdminsHeaders = groupAdmins.getHeaders().toString();
        Object groupAdminsResponseBody = groupAdmins.getBody().toString();
        Object expectedStatusCodeValue = expectedResponse.getStatusCodeValue();
        Object expectedHeaders = expectedResponse.getHeaders().toString();
        Object expectedResponseBody = expectedResponse.getBody().toString();

        //Assert
        assertEquals(expectedStatusCodeValue, groupAdminsStatusCodeValue);
        assertEquals(expectedHeaders, groupAdminsHeaders);
        assertEquals(expectedResponseBody, groupAdminsResponseBody);
    }


    @Test
    public void getGroupAdmins_GroupDoesNotExist() {

        //Arrange
        String groupDenomination = "Vale Family";

        Mockito.when(service.getGroupAdmins(groupDenomination)).thenThrow(new NotFoundArgumentsBusinessException(CreateGroupService.GROUP_DOES_NOT_EXISTS));

        //Act
        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () -> controller.getGroupAdmins(groupDenomination));

        //Assert
        assertEquals(thrown.getMessage(), CreateGroupService.GROUP_DOES_NOT_EXISTS);
    }

    @Test
    public void getGroupMembers_SUCCESS() {

        //Arrange
        String personEmail = "paulo@gmail.com";
        String groupDenomination = "Sunday Runners";
        String groupDescription = "All members from Sunday Runners group";

        //Expected Boolean Dto
        String member1 = "rita@gmail.com";
        String member2 = "henrique@gmail.com";
        PersonID personID1 = PersonID.createPersonID(member1);
        PersonID personID2 = PersonID.createPersonID(member2);
        List<PersonID> peopleInCharge = new ArrayList<>();
        peopleInCharge.add(personID1);
        peopleInCharge.add(personID2);
        GroupMembersDTO expectedGroupMembersDTO = GroupMembersDTOAssembler.createDTOFromDomainObject(peopleInCharge);


        //Expected Response
        ResponseEntity<Object> expectedResponse = new ResponseEntity<>(expectedGroupMembersDTO, HttpStatus.OK);

        //Act
        Mockito.when(service.getGroupMembers(groupDenomination)).thenReturn(expectedGroupMembersDTO);
        ResponseEntity<Object> groupMembers = controller.getGroupMembers(groupDenomination);

        //       Expected results
        Object groupMembersStatusCodeValue = groupMembers.getStatusCodeValue();
        Object groupMembersHeaders = groupMembers.getHeaders().toString();
        Object groupMembersResponseBody = groupMembers.getBody().toString();
        Object expectedStatusCodeValue = expectedResponse.getStatusCodeValue();
        Object expectedHeaders = expectedResponse.getHeaders().toString();
        Object expectedResponseBody = expectedResponse.getBody().toString();

        //Assert
        assertEquals(expectedStatusCodeValue, groupMembersStatusCodeValue);
        assertEquals(expectedHeaders, groupMembersHeaders);
        assertEquals(expectedResponseBody, groupMembersResponseBody);
    }


    @Test
    public void getGroupMembers_GroupDoesNotExist() {

        //Arrange
        String groupDenomination = "Vale Family";

        Mockito.when(service.getGroupMembers(groupDenomination)).thenThrow(new NotFoundArgumentsBusinessException(CreateGroupService.GROUP_DOES_NOT_EXISTS));

        //Act
        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () -> controller.getGroupMembers(groupDenomination));

        //Assert
        assertEquals(thrown.getMessage(), CreateGroupService.GROUP_DOES_NOT_EXISTS);
    }

    @Test
    public void getGroupAccounts_SUCCESS() {

        //Arrange
        String personEmail = "manuel@gmail.com";
        String groupDenomination = "Sunday Runners";
        String groupDescription = "All members from Sunday Runners group";

        //Expected Boolean Dto
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

        //Expected Response
        ResponseEntity<Object> expectedResponse = new ResponseEntity<>(expectedAccountsDTO, HttpStatus.OK);

        //Act
        Mockito.when(service.getGroupAccounts(groupDenomination)).thenReturn(expectedAccountsDTO);
        ResponseEntity<Object> groupAccounts = controller.getGroupAccounts(personEmail, groupDenomination);

        //       Expected results
        Object groupAccountsStatusCodeValue = groupAccounts.getStatusCodeValue();
        Object groupAccountsHeaders = groupAccounts.getHeaders().toString();
        Object groupAccountsResponseBody = groupAccounts.getBody().toString();
        Object expectedStatusCodeValue = expectedResponse.getStatusCodeValue();
        Object expectedHeaders = expectedResponse.getHeaders().toString();
        Object expectedResponseBody = expectedResponse.getBody().toString();

        //Assert
        assertEquals(expectedStatusCodeValue, groupAccountsStatusCodeValue);
        assertEquals(expectedHeaders, groupAccountsHeaders);
        assertEquals(expectedResponseBody, groupAccountsResponseBody);
    }


    @Test
    public void getGroupAccounts_GroupDoesNotExist() {

        //Arrange
        String personEmail = "paulo@gmail.com";
        String groupDenomination = "Vale Family";

        Mockito.when(service.getGroupAccounts(groupDenomination)).thenThrow(new NotFoundArgumentsBusinessException(CreateGroupService.GROUP_DOES_NOT_EXISTS));

        //Act
        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () -> controller.getGroupAccounts(personEmail, groupDenomination));

        //Assert
        assertEquals(thrown.getMessage(), CreateGroupService.GROUP_DOES_NOT_EXISTS);
    }


    @Test
    public void getGroupCategories_SUCCESS() {

        //Arrange
        String personEmail = "pedro@gmail.com";
        String groupDenomination = "Sunday Runners";
        String groupDescription = "All members from Sunday Runners group";

        //Expected Boolean Dto
        String category = "Salary";
        OwnerID ownerID = PersonID.createPersonID(personEmail);
        CategoryID categoryID = CategoryID.createCategoryID(category, ownerID);
        List<CategoryID> categories = new ArrayList<>();
        categories.add(categoryID);
        CategoriesDTO expectedCategoriesDTO = CategoriesDTOAssembler.createDTOFromDomainObject(categories);


        //Expected Response
        ResponseEntity<Object> expectedResponse = new ResponseEntity<>(expectedCategoriesDTO, HttpStatus.OK);

        //Act
        Mockito.when(service.getGroupCategories(groupDenomination)).thenReturn(expectedCategoriesDTO);
        ResponseEntity<Object> groupCategories = controller.getGroupCategories(personEmail, groupDenomination);

        //       Expected results
        Object groupCategoriesStatusCodeValue = groupCategories.getStatusCodeValue();
        Object groupCategoriesHeaders = groupCategories.getHeaders().toString();
        Object groupCategoriesResponseBody = groupCategories.getBody().toString();
        Object expectedStatusCodeValue = expectedResponse.getStatusCodeValue();
        Object expectedHeaders = expectedResponse.getHeaders().toString();
        Object expectedResponseBody = expectedResponse.getBody().toString();

        //Assert
        assertEquals(expectedStatusCodeValue, groupCategoriesStatusCodeValue);
        assertEquals(expectedHeaders, groupCategoriesHeaders);
        assertEquals(expectedResponseBody, groupCategoriesResponseBody);
    }


    @Test
    public void getGroupCategories_GroupDoesNotExist() {

        //Arrange
        String personEmail = "paulo@gmail.com";
        String groupDenomination = "Vale Family";

        Mockito.when(service.getGroupCategories(groupDenomination)).thenThrow(new NotFoundArgumentsBusinessException(CreateGroupService.GROUP_DOES_NOT_EXISTS));

        //Act
        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () -> controller.getGroupCategories(personEmail, groupDenomination));

        //Assert
        assertEquals(thrown.getMessage(), CreateGroupService.GROUP_DOES_NOT_EXISTS);
    }

    @Test
    public void getGroupLedger_SUCCESS() {

        //Arrange
        String personEmail = "francis@gmail.com";
        String groupDenomination = "Sunday Runners";
        String groupDescription = "All members from Sunday Runners group";

        //Expected Boolean Dto
        String category = "Salary";
        String account1 = "Company";
        String account2 = "Bank Account";
        OwnerID ownerID = PersonID.createPersonID(personEmail);
        AccountID accountID1 = AccountID.createAccountID(account1, ownerID);
        AccountID accountID2 = AccountID.createAccountID(account2, ownerID);
        CategoryID categoryID = CategoryID.createCategoryID(category, ownerID);

        Transaction transaction = Transaction.createTransaction(categoryID, "credit", "January salary", 1500.0, LocalDate.of(2020, 01, 21), accountID1, accountID2);
        TransactionDTOout transactionDTOout = TransactionDTOoutAssembler.createTransactionDTOout(transaction);
        List<TransactionDTOout> transactionDTOouts = new ArrayList<>();
        transactionDTOouts.add(transactionDTOout);

        TransactionsDTO expectedTransactionsDTO = TransactionsDTOAssembler.createDTOFromPrimitiveTypes(transactionDTOouts);


        //Expected Response
        ResponseEntity<Object> expectedResponse = new ResponseEntity<>(expectedTransactionsDTO, HttpStatus.OK);

        //Act
        Mockito.when(service.getGroupLedger(groupDenomination)).thenReturn(expectedTransactionsDTO);
        ResponseEntity<Object> groupTransactions = controller.getGroupLedger(groupDenomination);

        //       Expected results
        Object groupTransactionsStatusCodeValue = groupTransactions.getStatusCodeValue();
        Object groupTransactionsHeaders = groupTransactions.getHeaders().toString();
        Object groupTransactionsResponseBody = groupTransactions.getBody().toString();
        Object expectedStatusCodeValue = expectedResponse.getStatusCodeValue();
        Object expectedHeaders = expectedResponse.getHeaders().toString();
        Object expectedResponseBody = expectedResponse.getBody().toString();

        //Assert
        assertEquals(expectedStatusCodeValue, groupTransactionsStatusCodeValue);
        assertEquals(expectedHeaders, groupTransactionsHeaders);
        assertEquals(expectedResponseBody, groupTransactionsResponseBody);
    }


    @Test
    public void getGroupLedger_GroupDoesNotExist() {

        //Arrange
        String groupDenomination = "Vale Family";

        Mockito.when(service.getGroupLedger(groupDenomination)).thenThrow(new NotFoundArgumentsBusinessException(CreateGroupService.GROUP_DOES_NOT_EXISTS));

        //Act
        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () -> controller.getGroupLedger(groupDenomination));

        //Assert
        assertEquals(thrown.getMessage(), CreateGroupService.GROUP_DOES_NOT_EXISTS);
    }

     */
}
