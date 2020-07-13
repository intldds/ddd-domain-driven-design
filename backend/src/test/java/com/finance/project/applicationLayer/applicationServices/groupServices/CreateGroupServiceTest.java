package com.finance.project.applicationLayer.applicationServices.groupServices;

import com.finance.project.controllerLayer.integrationTests.AbstractTest;
import com.finance.project.dataModel.dataModel.PersonJpa;
import com.finance.project.domainLayer.domainEntities.aggregates.account.Account;
import com.finance.project.domainLayer.domainEntities.aggregates.category.Category;
import com.finance.project.domainLayer.domainEntities.aggregates.group.Group;
import com.finance.project.domainLayer.domainEntities.aggregates.ledger.Ledger;
import com.finance.project.domainLayer.domainEntities.aggregates.person.Address;
import com.finance.project.domainLayer.domainEntities.aggregates.person.Person;
import com.finance.project.domainLayer.domainEntities.vosShared.*;
import com.finance.project.domainLayer.exceptions.InvalidArgumentsBusinessException;
import com.finance.project.domainLayer.exceptions.NotFoundArgumentsBusinessException;
import com.finance.project.domainLayer.repositoriesInterfaces.*;
import com.finance.project.dtos.dtos.*;
import com.finance.project.dtos.dtosAssemblers.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class CreateGroupServiceTest extends AbstractTest {

    @Mock
    private IGroupRepository groupRepository;

    @Mock
    private IPersonRepository personRepository;

    @Mock
    private ILedgerRepository ledgerRepository;

    @Mock
    private ICategoryRepository categoryRepository;

    @Mock
    private IAccountRepository accountRepository;

    private CreateGroupService createGroupService;
    private Person personMaria;
    private Person personManuel;
    private Group schoolFriends;
    private Ledger schoolFriendsLedger;
    private LedgerID schoolFriendsLedgerID;
    private Account accountFood;
    private Account accountSalary;
    private Category categoryFastFood;


    // Data in memory

    @BeforeEach
    public void init() {

        // PERSON MARIA

        String mariaEmail = "maria@gmail.com";
        PersonID mariaID = PersonID.createPersonID(mariaEmail);

        String mariaName = "Maria";
        LocalDate mariaBirthdate = LocalDate.of(1983, 6, 25);
        String mariaBirthplace = "Porto";
        Ledger mariaLedger = Ledger.createLedger();
        LedgerID mariaLedgerID = mariaLedger.getLedgerID();

        String mariaStreet = "Rua das Amélias";
        String mariaDoorNumber = "23";
        String mariaPostCode = "4300-123";
        String mariaCity = "Porto";
        String mariaCountry = "Portugal";
        Address mariaAddress = Address.createAddress(mariaStreet, mariaDoorNumber, mariaPostCode, mariaCity, mariaCountry);

        personMaria = Person.createPersonWithoutParents(mariaEmail, mariaName, mariaBirthdate, mariaBirthplace, mariaAddress, mariaLedgerID);
        PersonJpa personJpa = new PersonJpa(mariaEmail, mariaName, mariaBirthplace, mariaBirthplace);
        personRepository.save(personMaria);
        ledgerRepository.save(mariaLedger);

        // PERSON MANUEL

        String manuelEmail = "manuel@gmail.com";
        PersonID manuelID = PersonID.createPersonID(manuelEmail);

        String manuelName = "Manuel";
        LocalDate manuelBirthdate = LocalDate.of(1983, 6, 25);
        String manuelBirthplace = "Belmonte";
        Ledger manuelLedger = Ledger.createLedger();
        LedgerID manuelLedgerID = manuelLedger.getLedgerID();

        String manuelStreet = "Rua das Marias";
        String manuelDoorNumber = "123";
        String manuelPostCode = "1000-456";
        String manuelCity = "Lisboa";
        String manuelCountry = "Portugal";
        Address manuelAddress = Address.createAddress(manuelStreet, manuelDoorNumber, manuelPostCode, manuelCity, manuelCountry);

        personManuel = Person.createPersonWithoutParents(manuelEmail, manuelName, manuelBirthdate, manuelBirthplace, manuelAddress, manuelLedgerID);
        personRepository.save(personManuel);
        ledgerRepository.save(manuelLedger);


        // GROUP SCHOOL FRIENDS

        String schoolFriendsDenomination = "School Friends";
        String schoolFriendsDescription = "Best friends from School";
        LocalDate schoolFriendsDateOfCreation = LocalDate.of(2018, 12, 1);
        schoolFriendsLedger = Ledger.createLedger();
        schoolFriendsLedgerID = schoolFriendsLedger.getLedgerID();

        schoolFriends = Group.createGroupAsPersonInCharge(schoolFriendsDenomination, mariaID, schoolFriendsDescription, schoolFriendsDateOfCreation, schoolFriendsLedgerID);
        GroupID schoolFriendsID = GroupID.createGroupID(schoolFriendsDenomination);
        schoolFriends.addMember(manuelID);
        groupRepository.save(schoolFriends);
        ledgerRepository.save(schoolFriendsLedger);

        // Account Food

        String accountDescription = "Things you eat";
        String accountDenomination = "Food";
        accountFood = Account.createAccount(accountDescription, accountDenomination, schoolFriendsID);
        accountRepository.save(accountFood);
        schoolFriends.addAccount(accountFood.getAccountID());

        // Account Salary

        String accountDescriptionSalary = "Money from company";
        String accountDenominationSalary = "Salary";
        accountSalary = Account.createAccount(accountDescriptionSalary, accountDenominationSalary, schoolFriendsID);
        accountRepository.save(accountSalary);
        schoolFriends.addAccount(accountSalary.getAccountID());

        // Category Fast Food

        String denominationFastFood = "Fast Food";
        categoryFastFood = Category.createCategory(denominationFastFood, schoolFriendsID);
        categoryRepository.save(categoryFastFood);
        schoolFriends.addCategory(categoryFastFood.getCategoryID());

        // Transaction

        CategoryID categoryID = categoryFastFood.getCategoryID();
        AccountID accountIDFood = accountFood.getAccountID();
        AccountID accountIDSalary = accountSalary.getAccountID();
        schoolFriendsLedger.createAndAddTransactionWithDate(categoryID, "debit", "Test Transaction", 100, LocalDate.now(), accountIDFood, accountIDSalary);

        // GROUP HIGH SCHOOL FRIENDS

        String highSchoolFriendsDenomination = "High School Friends";
        String highSchoolFriendsDescription = "Best friends from High School";
        LocalDate highSchoolFriendsDateOfCreation = LocalDate.of(2018, 12, 1);
        Ledger highSchoolFriendsLedger = Ledger.createLedger();
        LedgerID highSchoolFriendsLedgerID = highSchoolFriendsLedger.getLedgerID();

        Group highSchoolFriends = Group.createGroupAsPersonInCharge(highSchoolFriendsDenomination, manuelID, highSchoolFriendsDescription, highSchoolFriendsDateOfCreation, highSchoolFriendsLedgerID);
        highSchoolFriends.addMember(mariaID);
        groupRepository.save(highSchoolFriends);
        ledgerRepository.save(highSchoolFriendsLedger);
    }


    //Tests

    @Test
    @DisplayName("As a person (user), I want to create a group and become its Person In Charge || Happy path")
    public void createGroupAsPersonInCharge_HappyPath() {

        // Arrange

        String emailMaria = "maria@gmail.com";
        String denomination = "Friends";
        String description = "Old friends from school";

        // To search

        PersonID mariaID = PersonID.createPersonID(emailMaria);

        Ledger mariaGroupLedger = Ledger.createLedger();
        LedgerID mariaGroupLedgerID = mariaGroupLedger.getLedgerID();

        GroupID expectedGroupID = GroupID.createGroupID(denomination);
        Group expectedGroup = Group.createGroupAsPersonInCharge(denomination, mariaID, description, LocalDate.now(), mariaGroupLedgerID);

        // Person repository must return a person

        Mockito.when(personRepository.findById(mariaID)).thenReturn(Optional.of(personMaria));

        // Group repository must return an Optional Group

        Mockito.when(groupRepository.findById(expectedGroupID)).thenReturn(Optional.empty());

        // Group repository must return a Group

        Mockito.when(groupRepository.save(expectedGroup)).thenReturn(expectedGroup);

        // DTO

        CreateGroupDTO createGroupDTO = CreateGroupDTOAssembler.createDTOFromPrimitiveTypes(emailMaria, denomination, description);

        // Expected GroupDTO

        GroupDTO expectedGroupDTO = GroupDTOAssembler.createDTOFromDomainObject(expectedGroup.getGroupID().getDenomination(), expectedGroup.getDescription(), expectedGroup.getDateOfCreation());

        // Service

        createGroupService = new CreateGroupService(personRepository, groupRepository, ledgerRepository, accountRepository);

        // Act

        GroupDTO result = this.createGroupService.createGroupAsPersonInCharge(createGroupDTO);

        // Assert

        assertEquals(expectedGroupDTO, result);
    }


    @Test
    @DisplayName("As a person (user), create group where one is in charge - Sad path (attempt to create group that already exists)")
    public void createGroupAsPersonInCharge_GroupAlreadyExists() throws InvalidArgumentsBusinessException {

        // Arrange

        String emailMaria = "maria@gmail.com";
        String denomination = "School Friends";
        String description = "Best friends from School";

        // To search

        PersonID mariaID = PersonID.createPersonID(emailMaria);

        Ledger mariaGroupLedger = Ledger.createLedger();
        LedgerID mariaGroupLedgerID = mariaGroupLedger.getLedgerID();

        GroupID expectedGroupID = GroupID.createGroupID(denomination);
        Group expectedGroup = Group.createGroupAsPersonInCharge(denomination, mariaID, description, LocalDate.now(), mariaGroupLedgerID);

        // Person repository must return a person

        Mockito.when(personRepository.findById(mariaID)).thenReturn(Optional.of(personMaria));

        // Group repository must return an Optional Group

        Mockito.when(groupRepository.findById(expectedGroupID)).thenReturn(Optional.of(expectedGroup));


        // DTO

        CreateGroupDTO createGroupDTO = CreateGroupDTOAssembler.createDTOFromPrimitiveTypes(emailMaria, denomination, description);

        // Service

        createGroupService = new CreateGroupService(personRepository, groupRepository, ledgerRepository, accountRepository);

        // Act

        Throwable thrown = assertThrows(InvalidArgumentsBusinessException.class, () -> createGroupService.createGroupAsPersonInCharge(createGroupDTO));

        // Assert

        assertEquals(thrown.getMessage(), CreateGroupService.GROUP_ALREADY_EXISTS);
    }


    @Test
    @DisplayName("As a person (user), create group where one is in charge - Sad path (person does not exist in repository)")
    public void createGroupAsPersonInCharge_PersonDoesNotExists() throws NotFoundArgumentsBusinessException {

        // Arrange

        String emailAna = "ana@gmail.com";
        String denomination = "School Friends";
        String description = "My old friends from school";

        // To search

        PersonID anaID = PersonID.createPersonID(emailAna);

        // Person repository must return a person

        Mockito.when(personRepository.findById(anaID)).thenReturn(Optional.empty());

        // DTO

        CreateGroupDTO createGroupDTO = CreateGroupDTOAssembler.createDTOFromPrimitiveTypes(emailAna, denomination, description);

        // Service

        createGroupService = new CreateGroupService(personRepository, groupRepository, ledgerRepository, accountRepository);

        // Act

        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () -> createGroupService.createGroupAsPersonInCharge(createGroupDTO));

        // Assert

        assertEquals(thrown.getMessage(), CreateGroupService.PERSON_DOES_NOT_EXIST);
    }


    // getGroupByDenomination


    @Test
    @DisplayName("getGroupByDenomination | Success | Happy Path")
    public void getGroupByDenomination_HappyPath() {

        // Arrange

        String denomination = "School Friends";
        String description = "Best friends from School";
        LocalDate dateOfCreation = LocalDate.of(2018, 12, 1);

        // To search

        GroupID expectedGroupID = GroupID.createGroupID(denomination);

        // Expected GroupDTO

        GroupDTO expectedGroupDTO = GroupDTOAssembler.createDTOFromDomainObject(Denomination.createDenomination(denomination),
                Description.createDescription(description), DateOfCreation.createDateOfCreation(dateOfCreation), schoolFriendsLedgerID);

        // Group repository must return an Optional Group

        Mockito.when(groupRepository.findById(expectedGroupID)).thenReturn(Optional.of(schoolFriends));

        // Service

        createGroupService = new CreateGroupService(personRepository, groupRepository, ledgerRepository, accountRepository);
        GroupDTO result = this.createGroupService.getGroupByDenomination(denomination);

        // Assert
        assertEquals(expectedGroupDTO, result);
    }

    @Test
    @DisplayName("getGroupByDenomination | Fail | Group Does Not Exist")
    public void getGroupByDenomination_GroupDoesNotExist() {

        // Arrange
        String denomination = "Friends";

        // To search

        GroupID expectedGroupID = GroupID.createGroupID(denomination);

        // Group repository must return an Optional Empty

        Mockito.when(groupRepository.findById(expectedGroupID)).thenReturn(Optional.empty());

        // Service

        createGroupService = new CreateGroupService(personRepository, groupRepository, ledgerRepository, accountRepository);

        // Act

        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () -> createGroupService.getGroupByDenomination(denomination));

        // Assert

        assertEquals(thrown.getMessage(), CreateGroupService.GROUP_DOES_NOT_EXISTS);
    }


    // getGroupAdmins

    @Test
    @DisplayName("getGroupAdmins | Success | Happy Path")
    public void getGroupAdmins_HappyPath() {

        // Arrange

        String emailMaria = "maria@gmail.com";
        String denomination = "School Friends";

        // To search

        PersonID mariaID = PersonID.createPersonID(emailMaria);

        GroupID schoolFriendsID = GroupID.createGroupID(denomination);

        // Expected People In Charge

        List<PersonID> peopleInCharge = new ArrayList<>();
        peopleInCharge.add(mariaID);

        // Expected GroupAdminsDTO

        GroupAdminsDTO expectedGroupAdminsDTO = GroupAdminsDTOAssembler.createDTOFromDomainObject(peopleInCharge);

        // Group repository must return an Optional Group

        Mockito.when(groupRepository.findById(schoolFriendsID)).thenReturn(Optional.of(schoolFriends));


        // Service

        createGroupService = new CreateGroupService(personRepository, groupRepository, ledgerRepository, accountRepository);

        // Act

        GroupAdminsDTO result = createGroupService.getGroupAdmins(denomination);

        // Assert

        assertEquals(expectedGroupAdminsDTO, result);
    }


    @Test
    @DisplayName("getGroupAdmins | Fail | Group Does Not Exist")
    public void getGroupAdmins_GroupDoesNotExist() {

        // Arrange

        String denomination = "Friends";

        // To search

        GroupID expectedGroupID = GroupID.createGroupID(denomination);


        // Group repository must return an Optional Empty

        Mockito.when(groupRepository.findById(expectedGroupID)).thenReturn(Optional.empty());

        // Service

        createGroupService = new CreateGroupService(personRepository, groupRepository, ledgerRepository, accountRepository);

        // Act

        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () -> createGroupService.getGroupAdmins(denomination));

        // Assert

        assertEquals(thrown.getMessage(), CreateGroupService.GROUP_DOES_NOT_EXISTS);
    }


    // getGroupMembers

    @Test
    @DisplayName("getGroupMembers | Success | Happy Path")
    public void getGroupMembers_HappyPath() {

        // Arrange

        String emailManuel = "manuel@gmail.com";

        String denomination = "School Friends";

        // To search

        PersonID manuelID = PersonID.createPersonID(emailManuel);

        GroupID schoolFriendsID = GroupID.createGroupID(denomination);

        // Expected Members

        List<PersonID> members = new ArrayList<PersonID>();
        members.add(manuelID);

        //Expected GroupMembersDTO

        GroupMembersDTO expectedGroupMembersDTO = GroupMembersDTOAssembler.createDTOFromDomainObject(members);

        // Group repository must return an Optional Group

        Mockito.when(groupRepository.findById(schoolFriendsID)).thenReturn(Optional.of(schoolFriends));

        // Service

        createGroupService = new CreateGroupService(personRepository, groupRepository, ledgerRepository, accountRepository);

        // Act

        GroupMembersDTO result = createGroupService.getGroupMembers(denomination);

        //Assert
        assertEquals(expectedGroupMembersDTO, result);
    }


    @Test
    @DisplayName("getGroupMembers | Fail | Group Does Not Exist")
    public void getGroupMembers_GroupDoesNotExist() {

        // Arrange

        String denomination = "Friends";

        // To search

        GroupID expectedGroupID = GroupID.createGroupID(denomination);

        // Group repository must return an Optional Empty
        Mockito.when(groupRepository.findById(expectedGroupID)).thenReturn(Optional.empty());

        // Service
        createGroupService = new CreateGroupService(personRepository, groupRepository, ledgerRepository, accountRepository);

        // Act
        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () -> createGroupService.getGroupMembers(denomination));

        // Assert
        assertEquals(thrown.getMessage(), CreateGroupService.GROUP_DOES_NOT_EXISTS);
    }


    @Test
    @DisplayName("getGroupAccounts | Success | Happy Path")
    public void getGroupAccounts_HappyPath() {

        // Arrange

        String denomination = "School Friends";

        // To search

        GroupID schoolFriendsID = GroupID.createGroupID(denomination);

        // Expected accounts

        String accountDenomination = "Food";
        String accountDescription = "Things you eat";

        Account account = Account.createAccount(accountDescription,accountDenomination,schoolFriendsID);

        String accountDenominationSalary = "Salary";
        String accountDescriptionSalary = "Money from company";

        Account accountSalary = Account.createAccount(accountDescriptionSalary,accountDenominationSalary,schoolFriendsID);

        AccountDTO accountDTO1 = AccountDTOAssembler.createDTOFromPrimitiveTypes(accountDenomination, accountDescription);
        AccountDTO accountDTO2 = AccountDTOAssembler.createDTOFromPrimitiveTypes(accountDenominationSalary, accountDescriptionSalary);

        List<AccountDTO> accounts = new ArrayList<>();
        accounts.add(accountDTO1);
        accounts.add(accountDTO2);

        // Expected AccountsDTO

        AccountsDTO expectedAccountsDTO = AccountsDTOAssembler.createDTOFromDomainObject(accounts);

        // Group repository must return an Optional Group

        Mockito.when(groupRepository.findById(schoolFriendsID)).thenReturn(Optional.of(schoolFriends));

        // Account repository must return an Optional Account

        Mockito.when(accountRepository.findById(denomination, accountDenomination)).thenReturn(Optional.of(account));
        Mockito.when(accountRepository.findById(denomination, accountDenominationSalary)).thenReturn(Optional.of(accountSalary));


        // Service

        createGroupService = new CreateGroupService(personRepository, groupRepository, ledgerRepository, accountRepository);

        // Act

        AccountsDTO result = createGroupService.getGroupAccounts(denomination);

        // Assert

        assertEquals(expectedAccountsDTO, result);
    }


    @Test
    @DisplayName("getGroupAccounts | Fail | Group Does Not Exist")
    public void getGroupAccounts_GroupDoesNotExist() {

        // Arrange

        String denomination = "Friends";

        // To search

        GroupID expectedGroupID = GroupID.createGroupID(denomination);

        // Group repository must return an Optional Empty

        Mockito.when(groupRepository.findById(expectedGroupID)).thenReturn(Optional.empty());

        // Service

        createGroupService = new CreateGroupService(personRepository, groupRepository, ledgerRepository, accountRepository);

        // Act expected object
        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () -> createGroupService.getGroupAccounts(denomination));


        // Assert
        assertEquals(thrown.getMessage(), CreateGroupService.GROUP_DOES_NOT_EXISTS);
    }


    @Test
    @DisplayName("getGroupCategories | Success | Happy Path")
    public void getGroupCategories_HappyPath() {

        // Arrange
        String denomination = "School Friends";

        CategoryID categoryFastFoodID = categoryFastFood.getCategoryID();

        // To search

        GroupID schoolFriendsID = GroupID.createGroupID(denomination);

        // Expected categories

        List<CategoryID> categories = new ArrayList<>();
        categories.add(categoryFastFoodID);

        // Expected CategoriesDTO

        CategoriesDTO expectedCategoriesDTO = CategoriesDTOAssembler.createDTOFromDomainObject(categories);

        // Group repository must return an Optional Group

        Mockito.when(groupRepository.findById(schoolFriendsID)).thenReturn(Optional.of(schoolFriends));

        // Service

        createGroupService = new CreateGroupService(personRepository, groupRepository, ledgerRepository, accountRepository);

        // Act

        CategoriesDTO result = createGroupService.getGroupCategories(denomination);

        // Assert

        assertEquals(expectedCategoriesDTO, result);
    }


    @Test
    @DisplayName("getGroupCategories | Fail | Group Does Not Exist")
    public void getGroupCategories_GroupDoesNotExist() {

        // Arrange

        String denomination = "Friends";

        // To search

        GroupID expectedGroupID = GroupID.createGroupID(denomination);

        // Group repository must return an Optional Empty

        Mockito.when(groupRepository.findById(expectedGroupID)).thenReturn(Optional.empty());

        // Service

        createGroupService = new CreateGroupService(personRepository, groupRepository, ledgerRepository, accountRepository);

        // Act
        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () -> createGroupService.getGroupCategories(denomination));


        // Assert

        assertEquals(thrown.getMessage(), CreateGroupService.GROUP_DOES_NOT_EXISTS);
    }


    @Test
    @DisplayName("getGroupLedger | Success | Happy Path")
    public void getGroupLedger_HappyPath() {

        // Arrange

        String denomination = "School Friends";

        TransactionDTOout transactionDTOout = TransactionDTOoutAssembler.createTransactionDTOout(schoolFriendsLedger.getRecords().get(0));

        // To search

        GroupID schoolFriendsID = GroupID.createGroupID(denomination);

        // Expected transactions

        List<TransactionDTOout> transactions = new ArrayList<>();
        transactions.add(transactionDTOout);

        // Expected TransactionsDTO

        TransactionsDTO expectedTransactionsDTO = TransactionsDTOAssembler.createDTOFromPrimitiveTypes(transactions);

        // Group repository must return an Optional Group

        Mockito.when(groupRepository.findById(schoolFriendsID)).thenReturn(Optional.of(schoolFriends));

        // Ledger repository must return an Optional Ledger

        Mockito.when(ledgerRepository.findById(schoolFriendsLedgerID)).thenReturn(Optional.of(schoolFriendsLedger));

        // Service

        createGroupService = new CreateGroupService(personRepository, groupRepository, ledgerRepository, accountRepository);

        // Act

        TransactionsDTO result = createGroupService.getGroupLedger(denomination);

        // Assert
        assertEquals(expectedTransactionsDTO, result);
    }


    @Test
    @DisplayName("getGroupLedger | Fail | Group Does Not Exist")
    public void getGroupLedger_GroupDoesNotExist() {

        // Arrange
        String denomination = "Friends";

        // To search
        GroupID expectedGroupID = GroupID.createGroupID(denomination);

        // Group repository must return an Optional Empty
        Mockito.when(groupRepository.findById(expectedGroupID)).thenReturn(Optional.empty());

        // Service
        createGroupService = new CreateGroupService(personRepository, groupRepository, ledgerRepository, accountRepository);

        // Act
        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () -> createGroupService.getGroupLedger(denomination));


        // Assert
        assertEquals(thrown.getMessage(), CreateGroupService.GROUP_DOES_NOT_EXISTS);
    }


}

