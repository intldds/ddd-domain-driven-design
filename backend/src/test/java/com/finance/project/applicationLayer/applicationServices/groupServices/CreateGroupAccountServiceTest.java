package com.finance.project.applicationLayer.applicationServices.groupServices;

import com.finance.project.controllerLayer.integrationTests.AbstractTest;
import com.finance.project.domainLayer.domainEntities.aggregates.group.Group;
import com.finance.project.domainLayer.domainEntities.vosShared.*;
import com.finance.project.domainLayer.exceptions.InvalidArgumentsBusinessException;
import com.finance.project.domainLayer.exceptions.NotFoundArgumentsBusinessException;
import com.finance.project.domainLayer.repositoriesInterfaces.IAccountRepository;
import com.finance.project.domainLayer.repositoriesInterfaces.IGroupRepository;
import com.finance.project.dtos.dtosAssemblers.CreateGroupAccountDTOAssembler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import com.finance.project.dtos.dtos.CreateGroupAccountDTO;
import com.finance.project.dtos.dtos.GroupDTO;
import com.finance.project.dtos.dtosAssemblers.GroupDTOAssembler;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class CreateGroupAccountServiceTest extends AbstractTest {

    @Mock
    private IGroupRepository groupRepository;

    @Mock
    private IAccountRepository accountRepository;

    private CreateGroupAccountService createGroupAccountService;
    private Group group;
    private GroupID groupID;

    @BeforeEach
    public void init() {

        // Fontes Family

        // Manuel
        String manuelEmail = "manuel@gmail.com";
        PersonID manuelPersonID = PersonID.createPersonID(manuelEmail);

        // Ilda
        String ildaEmail = "ilda@gmail.com";
        PersonID ildaPersonID = PersonID.createPersonID(ildaEmail);

        // Paulo
        String pauloEmail = "paulo@gmail.com";
        PersonID pauloPersonID = PersonID.createPersonID(pauloEmail);

        // Helder
        String helderEmail = "helder@gmail.com";
        PersonID helderPersonID = PersonID.createPersonID(helderEmail);

        //Accounts:
        //Company / Bank Account / Wallet / State / Supermarket / Household Expenses / Streaming Services

        String dateOfCreation = "2020-06-01";
        String groupDenomination = "Fontes Family";
        String groupDescription = "All members from Fontes family";

        this.group = Group.createGroup(groupDenomination, groupDescription, dateOfCreation, manuelPersonID);
        this.groupID = GroupID.createGroupID(groupDenomination);
        group.addPersonInCharge(ildaPersonID);
        group.addMember(pauloPersonID);
        group.addMember(helderPersonID);

        //Company
        String companyDenomination = "Company";
        AccountID companyID = AccountID.createAccountID(companyDenomination, groupID);
        group.addAccount(companyID);

        //Bank Account
        String bankAccountDenomination = "Bank Account";
        AccountID bankAccountID = AccountID.createAccountID(bankAccountDenomination, groupID);
        group.addAccount(bankAccountID);

        //Wallet
        String walletDenomination = "Wallet";
        AccountID walletID = AccountID.createAccountID(walletDenomination, groupID);
        group.addAccount(walletID);

        //State
        String stateDenomination = "State";
        AccountID stateID = AccountID.createAccountID(stateDenomination, groupID);
        group.addAccount(stateID);

        //Supermarket
        String supermarketDenomination = "Supermarket";
        AccountID supermarketID = AccountID.createAccountID(supermarketDenomination, groupID);
        group.addAccount(supermarketID);

        //Household Expenses
        String householdExpensesDenomination = "Household Expenses";
        AccountID householdExpensesID = AccountID.createAccountID(householdExpensesDenomination, groupID);
        group.addAccount(householdExpensesID);

        //Streaming Services
        String streamingServicesDenomination = "Streaming Services";
        AccountID streamingServicesID = AccountID.createAccountID(streamingServicesDenomination, groupID);
        group.addAccount(streamingServicesID);
    }


    // Success

    @Test
    @DisplayName("Test For createAccountAsPeopleInCharge() | Success")
    void createAccountAsPeopleInCharge_Success() {

        // Arrange
        String personEmail = "manuel@gmail.com";
        String groupDenomination = "Fontes Family";
        String groupDescription = "All members from Fontes family";
        LocalDate dateOfCreation = LocalDate.of(2020, 06, 01);

        String accountDenomination = "LakersAccount";
        String accountDescription = "Lakers Expenses";

        //To Search
        AccountID accountID = AccountID.createAccountID(accountDenomination, groupID);

        //Returning an Optional <Group>
        Mockito.when(groupRepository.findById(groupID)).thenReturn(Optional.of(group));

        //Returning False
        Mockito.when(accountRepository.existsById(accountID)).thenReturn(false);

        //DTO
        CreateGroupAccountDTO createGroupAccountDTO = CreateGroupAccountDTOAssembler.createDTOFromPrimitiveTypes(
                personEmail, groupDenomination, accountDescription, accountDenomination);

        //Expected GroupDTO
        GroupDTO expectedGroupDTO = GroupDTOAssembler.createDTOFromDomainObject(
                Denomination.createDenomination(groupDenomination),
                Description.createDescription(groupDescription),
                DateOfCreation.createDateOfCreation(dateOfCreation));

        //Service
        createGroupAccountService = new CreateGroupAccountService(groupRepository, accountRepository);

        // Act
        GroupDTO result = createGroupAccountService.createAccountAsPeopleInCharge(createGroupAccountDTO);

        // Assert
        assertEquals(expectedGroupDTO, result);
    }


    // Person is not admin

    @Test
    @DisplayName("Test For createAccountAsPeopleInCharge() | Fail | Person Not In Charge")
    void createAccountAsPeopleInCharge_Fail_PeopleNotInCharge() {

        // Arrange
        String personEmail = "paulo@gmail.com";
        String groupDenomination = "Fontes Family";
        String accountDescription = "Lakers Expenses";
        String accountDenomination = "LakersAccount";

        //Returning an Optional <Group>
        Mockito.when(groupRepository.findById(groupID)).thenReturn(Optional.of(group));

        //DTO
        CreateGroupAccountDTO createGroupAccountDTO = CreateGroupAccountDTOAssembler.createDTOFromPrimitiveTypes(personEmail, groupDenomination, accountDescription, accountDenomination);

        //Service
        createGroupAccountService = new CreateGroupAccountService(groupRepository, accountRepository);

        //Act
        Throwable thrown = assertThrows(InvalidArgumentsBusinessException.class,() ->
                createGroupAccountService.createAccountAsPeopleInCharge(createGroupAccountDTO));

        // Assert
        assertEquals(thrown.getMessage(), CreateGroupAccountService.PERSON_NOT_IN_CHARGE);
    }


    // Account already exists - throw exception

    @Test
    @DisplayName("Test For createAccountAsPeopleInCharge() | Fail | Account Already Exists")
    void createAccountAsPeopleInCharge_Fail_AccountAlreadyExists() {

        // Arrange
        String personEmail = "ilda@gmail.com";
        String groupDenomination = "Fontes Family";
        String accountDescription = "Company Expenses";
        String accountDenomination = "Company";

        //To Search
        AccountID accountID = AccountID.createAccountID(accountDenomination, groupID);

        //Returning an Optional <Group>
        Mockito.when(groupRepository.findById(groupID)).thenReturn(Optional.of(group));

        //Returning True
        Mockito.when(accountRepository.existsById(accountID)).thenReturn(true);

        //DTO
        CreateGroupAccountDTO createGroupAccountDTO = CreateGroupAccountDTOAssembler.createDTOFromPrimitiveTypes(personEmail, groupDenomination, accountDescription, accountDenomination);

        //Service
        createGroupAccountService = new CreateGroupAccountService(groupRepository, accountRepository);

        // Act
        Throwable thrown = assertThrows(InvalidArgumentsBusinessException.class,() ->
                createGroupAccountService.createAccountAsPeopleInCharge(createGroupAccountDTO));

        //Assert
        assertEquals(thrown.getMessage(), CreateGroupAccountService.ACCOUNT_ALREADY_EXIST);
    }

    // Group doesn't exist - throw exception

    @Test
    @DisplayName("Test For createAccountAsPeopleInCharge() | Fail | Group Does Not Exist")
    void createAccountAsPeopleInCharge_Fail_GroupDoesNotExist() {

        // Arrange
        String personEmail = "ilda@gmail.com";
        String groupDenomination = "Clippers";
        String accountDescription = "Lakers Expenses";
        String accountDenomination = "LakersAccount";

        //Returning an Optional <Group>
        Mockito.when(groupRepository.findById(groupID)).thenReturn(Optional.of(group));

        //DTO
        CreateGroupAccountDTO createGroupAccountDTO = CreateGroupAccountDTOAssembler.createDTOFromPrimitiveTypes(personEmail, groupDenomination,accountDescription, accountDenomination);

        //Service
        createGroupAccountService = new CreateGroupAccountService(groupRepository, accountRepository);

        // Act
        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () ->
                createGroupAccountService.createAccountAsPeopleInCharge(createGroupAccountDTO));

        //Assert
        assertEquals(thrown.getMessage(), CreateGroupAccountService.GROUP_DOES_NOT_EXIST);
    }
}