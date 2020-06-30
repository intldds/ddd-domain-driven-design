package com.finance.project.applicationLayer.applicationServices.groupServices;


import com.finance.project.controllerLayer.integrationTests.AbstractTest;
import com.finance.project.domainLayer.domainEntities.aggregates.account.Account;
import com.finance.project.domainLayer.domainEntities.aggregates.category.Category;
import com.finance.project.domainLayer.domainEntities.aggregates.group.Group;
import com.finance.project.domainLayer.domainEntities.aggregates.ledger.Ledger;
import com.finance.project.domainLayer.domainEntities.aggregates.ledger.Transaction;
import com.finance.project.domainLayer.domainEntities.vosShared.*;
import com.finance.project.domainLayer.exceptions.InvalidArgumentsBusinessException;
import com.finance.project.domainLayer.exceptions.NotFoundArgumentsBusinessException;
import com.finance.project.domainLayer.repositoriesInterfaces.IAccountRepository;
import com.finance.project.domainLayer.repositoriesInterfaces.ICategoryRepository;
import com.finance.project.domainLayer.repositoriesInterfaces.IGroupRepository;
import com.finance.project.domainLayer.repositoriesInterfaces.ILedgerRepository;
import com.finance.project.dtos.dtosAssemblers.CreateGroupTransactionDTOAssembler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import com.finance.project.dtos.dtos.CreateGroupTransactionDTO;
import com.finance.project.dtos.dtos.DeleteGroupTransactionDTO;
import com.finance.project.dtos.dtos.GroupDTO;
import com.finance.project.dtos.dtos.UpdateGroupTransactionDTO;
import com.finance.project.dtos.dtosAssemblers.DeleteGroupTransactionDTOAssembler;
import com.finance.project.dtos.dtosAssemblers.GroupDTOAssembler;
import com.finance.project.dtos.dtosAssemblers.UpdateGroupTransactionDTOAssembler;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CreateGroupTransactionServiceTest extends AbstractTest {

    @Mock
    private IGroupRepository groupRepository;
    @Mock
    private ICategoryRepository categoryRepository;
    @Mock
    private IAccountRepository accountRepository;
    @Mock
    private ILedgerRepository ledgerRepository;
    private CreateGroupTransactionService createGroupTransactionService;



    //Para o UPDATE / DELETE

    private Group sundayRunners;
    private GroupID sundayRunnersID;
    private Ledger ledger;
    private LedgerID ledgerID;

    @BeforeEach
    public void init() {

        //Paulo
        String pauloEmail = "paulo@gmail.com";
        PersonID pauloPersonID = PersonID.createPersonID(pauloEmail);

        //Rita
        String ritaEmail = "rita@gmail.com";
        PersonID ritaPersonID = PersonID.createPersonID(ritaEmail);

        //Henrique
        String henriqueEmail = "henrique@gmail.com";
        PersonID henriquePersonID = PersonID.createPersonID(henriqueEmail);


        //Sunday Runners
        //Accounts ->            Company / Bank Account
        //Categories ->          Salary

        //Ledger
        ledger = Ledger.createLedger();
        ledgerID = ledger.getLedgerID();

        LocalDate dateOfCreation = LocalDate.of(2020, 06, 01);
        String sundayRunnersDenomination = "Sunday Runners";
        String sundayRunnersDescription = "All members from Sunday Runners group";

        this.sundayRunners = Group.createGroupAsPersonInCharge(sundayRunnersDenomination, pauloPersonID, sundayRunnersDescription, dateOfCreation, ledgerID);
        this.sundayRunnersID = GroupID.createGroupID(sundayRunnersDenomination);
        sundayRunners.addMember(ritaPersonID);
        sundayRunners.addMember(henriquePersonID);

        //Category Salary
        String salaryDenomination = "Salary";
        CategoryID salaryID = CategoryID.createCategoryID(salaryDenomination, sundayRunnersID);
        sundayRunners.addCategory(salaryID);

        //Account Company
        String companyDenomination = "Company";
        String companyDescription = "Company account";
        AccountID companyID = AccountID.createAccountID(companyDenomination, sundayRunnersID);
        sundayRunners.addAccount(companyID);

        //Account Bank Account
        String bankAccountDenomination = "Bank Account";
        String bankAccountDescription = "Personal bank account";
        AccountID bankAccountID = AccountID.createAccountID(bankAccountDenomination, sundayRunnersID);
        sundayRunners.addAccount(bankAccountID);

        //Salary January

        String credit = "credit";
        String salaryJanuaryDescription = "January salary";
        double salaryJanuaryAmount = 1500;
        LocalDate salaryJanuaryDate = LocalDate.of(2020, 01, 21);
        Transaction salaryJanuary = Transaction.createTransaction(salaryID, credit, salaryJanuaryDescription, salaryJanuaryAmount, salaryJanuaryDate, companyID, bankAccountID);

        ledger.addTransaction(salaryJanuary);
    }


    //POST


    @Test
    @DisplayName("Test for service method createGroupTransaction() - Success")
    void createGroupTransaction_success() {

        //Act
        createGroupTransactionService = new CreateGroupTransactionService(groupRepository, accountRepository,
                categoryRepository, ledgerRepository);

//        Arrange Group
        String groupDenomination = "House";
        String groupDescription = "Bills";
        LocalDate groupCreation = LocalDate.of(1980, 11, 15);
        Ledger groupLedger = Ledger.createLedger();
        LedgerID groupLedgerID = groupLedger.getLedgerID();

//      Member of the group that wants to add the transaction
        String personGroupMemberEmail = "joao@gmail.com";

//        ID's (Group and Group member)
        GroupID groupID = GroupID.createGroupID(groupDenomination);
        PersonID groupMemberID = PersonID.createPersonID(personGroupMemberEmail);

//        Create Group
        Group groupHouse = Group.createGroupAsPersonInCharge(groupDenomination, groupMemberID, groupDescription,
                groupCreation, groupLedgerID);

//        Arrange Category
        String categoryDenomination = "Electricity Expenses";

//        Arrange transaction
        double transactionAmount = 45.00;
        String transactionType = "Debit";
        String transactionDescription = "EDP bill from March/2020";
        String transactionDate = "2020-06-18";

//      Arrange Credit Account
        String houseWalletAccountDenomination = "House Funds";
        String houseWalletAccountDescription = "Money to spend with House expenses";
        Account houseWallet = Account.createAccount(houseWalletAccountDescription, houseWalletAccountDenomination, groupID);

//      Arrange Debit Account
        String runnersSupermarketAccountDenomination = "Supermarket A";
        String runnersSupermarketAccountDescription = "Money spent on Supermarket A";
        Account runnersSupermarket = Account.createAccount(runnersSupermarketAccountDescription, runnersSupermarketAccountDenomination, groupID);

//        Arrange expectedGroupDTO

//        Create Denomination
        Denomination groupHouseDenomination = Denomination.createDenomination(groupDenomination);
//        Create Description
        Description groupHouseDescription = Description.createDescription(groupDescription);
//        Create DateOfCreation
        DateOfCreation groupHouseDateOfCreation = DateOfCreation.createDateOfCreation(groupCreation);
//        Create CategoryID
        CategoryID groupHouseCategoryID = CategoryID.createCategoryID(categoryDenomination, groupID);
//        Create Category
        Category groupHouseCategory = Category.createCategory(categoryDenomination, groupID);

//        Act
//          Mock the behaviour of groupRepository
//        Returning an Optional<Group> groupHouse
        Mockito.when(groupRepository.findById(groupID))
                .thenReturn(Optional.of(groupHouse));

//        Mock the behaviour of categoryRepository
//        Returning a boolean result (true)
        Mockito.when(categoryRepository.existsById(groupHouseCategoryID))
                .thenReturn(true);

        AccountID credAccountID = AccountID.createAccountID(houseWalletAccountDenomination, groupID);

//        Mock the behaviour of accountRepository
//        Returning a boolean result (true)
        Mockito.when(accountRepository.existsById(credAccountID))
                .thenReturn(true);

        AccountID debAccountID = AccountID.createAccountID(runnersSupermarketAccountDenomination, groupID);

//        Mock the behaviour of accountRepository
//        Returning a boolean result (true)
        Mockito.when(accountRepository.existsById(debAccountID))
                .thenReturn(true);

//        Mock the behaviour of ledgerRepository
//        Returning a Optional<Ledger> groupLedger
        Mockito.when(ledgerRepository.findById(groupLedgerID))
                .thenReturn(Optional.of(groupLedger));

//        Expected transaction
        Transaction expectedTransaction = Transaction.createTransaction(groupHouseCategoryID, transactionType,
                transactionDescription, transactionAmount, LocalDate.parse(transactionDate), debAccountID, credAccountID);

//        Mock the behaviour of ledgerRepository
//        Returning a Ledger groupLedger
        Mockito.when(ledgerRepository.findById(groupHouse.getLedgerID()))
                .thenReturn(Optional.of(groupLedger));

//        DTO
        CreateGroupTransactionDTO createGroupTransactionDTO = CreateGroupTransactionDTOAssembler.createDTOFromPrimitiveTypes(groupDenomination,
                personGroupMemberEmail, categoryDenomination, runnersSupermarketAccountDenomination, houseWalletAccountDenomination, transactionAmount, transactionType, transactionDescription, transactionDate);

//        Act
        GroupDTO result = createGroupTransactionService.createGroupTransaction(createGroupTransactionDTO);

//      Verify if the expected transaction exists in the groupHouse ledger
        boolean checkIfContainsTransaction = groupLedger.getRecords().contains(expectedTransaction);

//      Expected GroupDTO
        GroupDTO expectedGroupDTO = GroupDTOAssembler.createDTOFromDomainObject(groupHouseDenomination, groupHouseDescription, groupHouseDateOfCreation, groupLedgerID);

//      Assert
        assertEquals(expectedGroupDTO, result);
        assertTrue(checkIfContainsTransaction);
    }

    @Test
    @DisplayName("Test for service method createGroupTransaction() - Different transaction")
    void createGroupTransaction_DifferentTransaction() {

        //Act
        createGroupTransactionService = new CreateGroupTransactionService(groupRepository, accountRepository,
                categoryRepository, ledgerRepository);

//        Arrange Group
        String groupDenomination = "House";
        String groupDescription = "Bills";
        LocalDate groupCreation = LocalDate.of(1980, 11, 15);
        Ledger groupLedger = Ledger.createLedger();
        LedgerID groupLedgerID = groupLedger.getLedgerID();

//      Member of the group that wants to add the transaction
        String personGroupMemberEmail = "joao@gmail.com";

//        ID's (Group and Group member)
        GroupID groupID = GroupID.createGroupID(groupDenomination);
        PersonID groupMemberID = PersonID.createPersonID(personGroupMemberEmail);

//        Create Group
        Group groupHouse = Group.createGroupAsPersonInCharge(groupDenomination, groupMemberID, groupDescription,
                groupCreation, groupLedgerID);

//        Arrange Category
        String categoryDenomination = "Electricity Expenses";

//        Arrange transaction
        double transactionAmount = 45.00;
        String transactionType = "Debit";
        String transactionDescription = "EDP bill from March/2020";


//        Arrange transaction
        double anotherTransactionAmount = 54.00;
        String anotherTransactionType = "credit";
        String anotherTransactionDescription = "Dividends";
        String anotherTransactionDate = "2020-06-17";


//      Arrange Credit Account
        String houseWalletAccountDenomination = "House Funds";
        String houseWalletAccountDescription = "Money to spend with House expenses";
        Account houseWallet = Account.createAccount(houseWalletAccountDescription, houseWalletAccountDenomination, groupID);

//      Arrange Debit Account
        String runnersSupermarketAccountDenomination = "Supermarket A";
        String runnersSupermarketAccountDescription = "Money spent on Supermarket A";
        Account runnersSupermarket = Account.createAccount(runnersSupermarketAccountDescription, runnersSupermarketAccountDenomination, groupID);

//        Arrange expectedGroupDTO

//        Create Denomination
        Denomination groupHouseDenomination = Denomination.createDenomination(groupDenomination);
//        Create Description
        Description groupHouseDescription = Description.createDescription(groupDescription);
//        Create DateOfCreation
        DateOfCreation groupHouseDateOfCreation = DateOfCreation.createDateOfCreation(groupCreation);
//        Create CategoryID
        CategoryID groupHouseCategoryID = CategoryID.createCategoryID(categoryDenomination, groupID);

//        Act
//          Mock the behaviour of groupRepository
//        Returning an Optional<Group> groupHouse
        Mockito.when(groupRepository.findById(groupID))
                .thenReturn(Optional.of(groupHouse));

//        Mock the behaviour of categoryRepository
//        Returning a boolean result (true)
        Mockito.when(categoryRepository.existsById(groupHouseCategoryID))
                .thenReturn(true);

        AccountID credAccountID = AccountID.createAccountID(houseWalletAccountDenomination, groupID);

//        Mock the behaviour of accountRepository
//        Returning a boolean result (true)
        Mockito.when(accountRepository.existsById(credAccountID))
                .thenReturn(true);

        AccountID debAccountID = AccountID.createAccountID(runnersSupermarketAccountDenomination, groupID);

//        Mock the behaviour of accountRepository
//        Returning a boolean result (true)
        Mockito.when(accountRepository.existsById(debAccountID))
                .thenReturn(true);

//        Mock the behaviour of ledgerRepository
//        Returning a Optional<Ledger> groupLedger
        Mockito.when(ledgerRepository.findById(groupLedgerID))
                .thenReturn(Optional.of(groupLedger));

//        Expected transaction
        Transaction expectedTransaction = Transaction.createTransactionWithSystemDate(groupHouseCategoryID, transactionType,
                transactionDescription, transactionAmount, debAccountID, credAccountID);

//        Mock the behaviour of ledgerRepository
//        Returning a Ledger groupLedger
        Mockito.when(ledgerRepository.findById(groupHouse.getLedgerID()))
                .thenReturn(Optional.of(groupLedger));

//        DTO
        CreateGroupTransactionDTO createGroupTransactionDTO = CreateGroupTransactionDTOAssembler.createDTOFromPrimitiveTypes(groupDenomination,
                personGroupMemberEmail, categoryDenomination, runnersSupermarketAccountDenomination, houseWalletAccountDenomination,
                anotherTransactionAmount, anotherTransactionType, anotherTransactionDescription, anotherTransactionDate);

//        Act
        GroupDTO result = createGroupTransactionService.createGroupTransaction(createGroupTransactionDTO);

//      Verify if the expected transaction exists in the groupHouse ledger
        boolean checkIfContainsTransaction = groupLedger.getRecords().contains(expectedTransaction);

        //Expected GroupDTO
        GroupDTO expectedGroupDTO = GroupDTOAssembler.createDTOFromDomainObject(groupHouseDenomination, groupHouseDescription, groupHouseDateOfCreation, groupLedgerID);

        //Assert
        assertEquals(expectedGroupDTO, result);
        assertFalse(checkIfContainsTransaction);
    }


    @Test
    @DisplayName("Test for service method createGroupTransaction() - Fail (group does not exist)")
    void createGroupTransaction_failGroupDoesNotExist() {

        //Act
        createGroupTransactionService = new CreateGroupTransactionService(groupRepository, accountRepository,
                categoryRepository, ledgerRepository);

//        Arrange Group
        String groupDenomination = "House";
        String groupDescription = "Bills";
        LocalDate groupCreation = LocalDate.of(1980, 11, 15);
        Ledger groupLedger = Ledger.createLedger();
        LedgerID groupLedgerID = groupLedger.getLedgerID();

//      Member of the group that wants to add the transaction
        String personGroupMemberEmail = "joao@gmail.com";

//        ID's (Group and Group member)
        GroupID groupID = GroupID.createGroupID(groupDenomination);
        PersonID groupMemberID = PersonID.createPersonID(personGroupMemberEmail);

//        Create Group
        Group groupHouse = Group.createGroupAsPersonInCharge(groupDenomination, groupMemberID, groupDescription,
                groupCreation, groupLedgerID);

//        Arrange Category
        String categoryDenomination = "Electricity Expenses";

//        Arrange transaction
        double transactionAmount = 45.00;
        String transactionType = "Debit";
        String transactionDescription = "EDP bill from March/2020";
        String transactionDate = "2020-06-18";


//      Arrange Credit Account
        String houseWalletAccountDenomination = "House Funds";
        String houseWalletAccountDescription = "Money to spend with House expenses";
        Account houseWallet = Account.createAccount(houseWalletAccountDescription, houseWalletAccountDenomination, groupID);

//      Arrange Debit Account
        String runnersSupermarketAccountDenomination = "Supermarket A";
        String runnersSupermarketAccountDescription = "Money spent on Supermarket A";
        Account runnersSupermarket = Account.createAccount(runnersSupermarketAccountDescription, runnersSupermarketAccountDenomination, groupID);


//        Act
//          Mock the behaviour of groupRepository
//        Returning an Optional<Group> groupHouse
        Mockito.when(groupRepository.findById(groupID))
                .thenReturn(Optional.empty());

//        DTO
        CreateGroupTransactionDTO createGroupTransactionDTO = CreateGroupTransactionDTOAssembler.createDTOFromPrimitiveTypes(groupDenomination,
                personGroupMemberEmail, categoryDenomination, runnersSupermarketAccountDenomination, houseWalletAccountDenomination, transactionAmount, transactionType, transactionDescription, transactionDate);

//        Act
        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () -> createGroupTransactionService.createGroupTransaction(createGroupTransactionDTO));

        //Expected exception message
        String expectedMessage = "Group does not exist in the system";

        //Assert
        assertEquals(expectedMessage, thrown.getMessage());
    }

    @Test
    @DisplayName("Test for service method createGroupTransaction() - Fail (person is not a member)")
    void createGroupTransaction_failPersonIsNotMember() {

        //Act
        createGroupTransactionService = new CreateGroupTransactionService(groupRepository, accountRepository,
                categoryRepository, ledgerRepository);

//        Arrange Group
        String groupDenomination = "House";
        String groupDescription = "Bills";
        LocalDate groupCreation = LocalDate.of(1980, 11, 15);
        Ledger groupLedger = Ledger.createLedger();
        LedgerID groupLedgerID = groupLedger.getLedgerID();

//      Member of the group that wants to add the transaction
        String personGroupMemberEmail = "joao@gmail.com";

//      A random person that wants to add the transaction
        String randomPersonEmail = "bluebear@gmail.com";

//        ID's (Group and Group member)
        GroupID groupID = GroupID.createGroupID(groupDenomination);
        PersonID groupMemberID = PersonID.createPersonID(personGroupMemberEmail);

//        Create Group
        Group groupHouse = Group.createGroupAsPersonInCharge(groupDenomination, groupMemberID, groupDescription,
                groupCreation, groupLedgerID);

//        Arrange Category
        String categoryDenomination = "Electricity Expenses";

//        Arrange transaction
        double transactionAmount = 45.00;
        String transactionType = "Debit";
        String transactionDescription = "EDP bill from March/2020";
        String transactionDate = "2020-06-18";


//      Arrange Credit Account
        String houseWalletAccountDenomination = "House Funds";
        String houseWalletAccountDescription = "Money to spend with House expenses";
        Account houseWallet = Account.createAccount(houseWalletAccountDescription, houseWalletAccountDenomination, groupID);

//      Arrange Debit Account
        String runnersSupermarketAccountDenomination = "Supermarket A";
        String runnersSupermarketAccountDescription = "Money spent on Supermarket A";
        Account runnersSupermarket = Account.createAccount(runnersSupermarketAccountDescription, runnersSupermarketAccountDenomination, groupID);

//        Act
//          Mock the behaviour of groupRepository
//        Returning an Optional<Group> groupHouse
        Mockito.when(groupRepository.findById(groupID))
                .thenReturn(Optional.of(groupHouse));

//        DTO
        CreateGroupTransactionDTO createGroupTransactionDTO = CreateGroupTransactionDTOAssembler.createDTOFromPrimitiveTypes(groupDenomination,
                randomPersonEmail, categoryDenomination, runnersSupermarketAccountDenomination, houseWalletAccountDenomination, transactionAmount, transactionType, transactionDescription, transactionDate);

//        Act
        Throwable thrown = assertThrows(InvalidArgumentsBusinessException.class, () -> createGroupTransactionService.createGroupTransaction(createGroupTransactionDTO));

        //Expected exception message
        String expectedMessage = "Person is not member of the group";

        //Assert
        assertEquals(expectedMessage, thrown.getMessage());
    }

    @Test
    @DisplayName("Test for service method createGroupTransaction() - Fail (category is not created)")
    void createGroupTransaction_failCategoryIsNotCreated() {

        //Act
        createGroupTransactionService = new CreateGroupTransactionService(groupRepository, accountRepository,
                categoryRepository, ledgerRepository);

//        Arrange Group
        String groupDenomination = "House";
        String groupDescription = "Bills";
        LocalDate groupCreation = LocalDate.of(1980, 11, 15);
        Ledger groupLedger = Ledger.createLedger();
        LedgerID groupLedgerID = groupLedger.getLedgerID();

//      Member of the group that wants to add the transaction
        String personGroupMemberEmail = "joao@gmail.com";

//        ID's (Group and Group member)
        GroupID groupID = GroupID.createGroupID(groupDenomination);
        PersonID groupMemberID = PersonID.createPersonID(personGroupMemberEmail);

//        Create Group
        Group groupHouse = Group.createGroupAsPersonInCharge(groupDenomination, groupMemberID, groupDescription,
                groupCreation, groupLedgerID);

//        Arrange Category
        String categoryDenomination = "Electricity Expenses";

//        Arrange transaction
        double transactionAmount = 45.00;
        String transactionType = "Debit";
        String transactionDescription = "EDP bill from March/2020";
        String transactionDate = "2020-06-18";


//      Arrange Credit Account
        String houseWalletAccountDenomination = "House Funds";
        String houseWalletAccountDescription = "Money to spend with House expenses";
        Account houseWallet = Account.createAccount(houseWalletAccountDescription, houseWalletAccountDenomination, groupID);

//      Arrange Debit Account
        String runnersSupermarketAccountDenomination = "Supermarket A";
        String runnersSupermarketAccountDescription = "Money spent on Supermarket A";
        Account runnersSupermarket = Account.createAccount(runnersSupermarketAccountDescription, runnersSupermarketAccountDenomination, groupID);

//        Create CategoryID
        CategoryID groupHouseCategoryID = CategoryID.createCategoryID(categoryDenomination, groupID);

//        Act
//          Mock the behaviour of groupRepository
//        Returning an Optional<Group> groupHouse
        Mockito.when(groupRepository.findById(groupID))
                .thenReturn(Optional.of(groupHouse));

//        Mock the behaviour of categoryRepository
//        Returning a boolean result (true), which will trigger a
//        NotFoundArgumentsBusinessException("Category does not exist; it needs to be created")
        Mockito.when(categoryRepository.existsById(groupHouseCategoryID))
                .thenReturn(false);

//        DTO
        CreateGroupTransactionDTO createGroupTransactionDTO = CreateGroupTransactionDTOAssembler.createDTOFromPrimitiveTypes(groupDenomination,
                personGroupMemberEmail, categoryDenomination, runnersSupermarketAccountDenomination, houseWalletAccountDenomination, transactionAmount, transactionType, transactionDescription, transactionDate);

//        Act
        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () -> createGroupTransactionService.createGroupTransaction(createGroupTransactionDTO));

        //Expected exception message
        String expectedMessage = "Category does not exist; it needs to be created";

        //Assert
        assertEquals(expectedMessage, thrown.getMessage());
    }

    @Test
    @DisplayName("Test for service method createGroupTransaction() - Fail (account to credit is not created)")
    void createGroupTransaction_failCreditAccountIsNotCreated() {

        //Act
        createGroupTransactionService = new CreateGroupTransactionService(groupRepository, accountRepository,
                categoryRepository, ledgerRepository);

//        Arrange Group
        String groupDenomination = "House";
        String groupDescription = "Bills";
        LocalDate groupCreation = LocalDate.of(1980, 11, 15);
        Ledger groupLedger = Ledger.createLedger();
        LedgerID groupLedgerID = groupLedger.getLedgerID();

//      Member of the group that wants to add the transaction
        String personGroupMemberEmail = "joao@gmail.com";

//        ID's (Group and Group member)
        GroupID groupID = GroupID.createGroupID(groupDenomination);
        PersonID groupMemberID = PersonID.createPersonID(personGroupMemberEmail);

//        Create Group
        Group groupHouse = Group.createGroupAsPersonInCharge(groupDenomination, groupMemberID, groupDescription,
                groupCreation, groupLedgerID);

//        Arrange Category
        String categoryDenomination = "Electricity Expenses";

//        Arrange transaction
        double transactionAmount = 45.00;
        String transactionType = "Debit";
        String transactionDescription = "EDP bill from March/2020";
        String transactionDate = "2020-06-18";


//      Arrange Credit Account
        String houseWalletAccountDenomination = "House Funds";
        String houseWalletAccountDescription = "Money to spend with House expenses";
        Account houseWallet = Account.createAccount(houseWalletAccountDescription, houseWalletAccountDenomination, groupID);

//      Arrange Debit Account
        String runnersSupermarketAccountDenomination = "Supermarket A";
        String runnersSupermarketAccountDescription = "Money spent on Supermarket A";
        Account runnersSupermarket = Account.createAccount(runnersSupermarketAccountDescription, runnersSupermarketAccountDenomination, groupID);

//        Arrange expectedGroupDTO


//        Create CategoryID
        CategoryID groupHouseCategoryID = CategoryID.createCategoryID(categoryDenomination, groupID);

//        Act
//          Mock the behaviour of groupRepository
//        Returning an Optional<Group> groupHouse
        Mockito.when(groupRepository.findById(groupID))
                .thenReturn(Optional.of(groupHouse));

//        Mock the behaviour of categoryRepository
//        Returning a boolean result (true)
        Mockito.when(categoryRepository.existsById(groupHouseCategoryID))
                .thenReturn(true);

        AccountID debAccountID = AccountID.createAccountID(runnersSupermarketAccountDenomination, groupID);

//        Mock the behaviour of accountRepository
//        Returning a boolean result (true)
        Mockito.when(accountRepository.existsById(debAccountID))
                .thenReturn(true);

        AccountID credAccountID = AccountID.createAccountID(houseWalletAccountDenomination, groupID);

//        Mock the behaviour of accountRepository
//        Returning a boolean result (true), which will trigger a
//        NotFoundArgumentsBusinessException("Account to be credited does not exist; it needs to be created")
        Mockito.when(accountRepository.existsById(credAccountID))
                .thenReturn(false);

//        DTO
        CreateGroupTransactionDTO createGroupTransactionDTO = CreateGroupTransactionDTOAssembler.createDTOFromPrimitiveTypes(groupDenomination,
                personGroupMemberEmail, categoryDenomination, runnersSupermarketAccountDenomination, houseWalletAccountDenomination, transactionAmount, transactionType, transactionDescription, transactionDate);

//        Act
        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () -> createGroupTransactionService.createGroupTransaction(createGroupTransactionDTO));

        //Expected exception message
        String expectedMessage = "Account to be credited does not exist; it needs to be created";

        //Assert
        assertEquals(expectedMessage, thrown.getMessage());
    }


    @Test
    @DisplayName("Test for service method createGroupTransaction() - Fail (account to debit is not created)")
    void createGroupTransaction_failDebitAccountIsNotCreated() {

        //Act
        createGroupTransactionService = new CreateGroupTransactionService(groupRepository, accountRepository,
                categoryRepository, ledgerRepository);

//        Arrange Group
        String groupDenomination = "House";
        String groupDescription = "Bills";
        LocalDate groupCreation = LocalDate.of(1980, 11, 15);
        Ledger groupLedger = Ledger.createLedger();
        LedgerID groupLedgerID = groupLedger.getLedgerID();

//      Member of the group that wants to add the transaction
        String personGroupMemberEmail = "joao@gmail.com";

//        ID's (Group and Group member)
        GroupID groupID = GroupID.createGroupID(groupDenomination);
        PersonID groupMemberID = PersonID.createPersonID(personGroupMemberEmail);

//        Create Group
        Group groupHouse = Group.createGroupAsPersonInCharge(groupDenomination, groupMemberID, groupDescription,
                groupCreation, groupLedgerID);

//        Arrange Category
        String categoryDenomination = "Electricity Expenses";

//        Arrange transaction
        double transactionAmount = 45.00;
        String transactionType = "Debit";
        String transactionDescription = "EDP bill from March/2020";
        String transactionDate = "2020-06-18";


//      Arrange Credit Account
        String houseWalletAccountDenomination = "House Funds";
        String houseWalletAccountDescription = "Money to spend with House expenses";
        Account houseWallet = Account.createAccount(houseWalletAccountDescription, houseWalletAccountDenomination, groupID);

//      Arrange Debit Account
        String runnersSupermarketAccountDenomination = "Supermarket A";
        String runnersSupermarketAccountDescription = "Money spent on Supermarket A";
        Account runnersSupermarket = Account.createAccount(runnersSupermarketAccountDescription, runnersSupermarketAccountDenomination, groupID);

//        Create CategoryID
        CategoryID groupHouseCategoryID = CategoryID.createCategoryID(categoryDenomination, groupID);

//        Act
//          Mock the behaviour of groupRepository
//        Returning an Optional<Group> groupHouse
        Mockito.when(groupRepository.findById(groupID))
                .thenReturn(Optional.of(groupHouse));

//        Mock the behaviour of categoryRepository
//        Returning a boolean result (true)
        Mockito.when(categoryRepository.existsById(groupHouseCategoryID))
                .thenReturn(true);

        AccountID credAccountID = AccountID.createAccountID(houseWalletAccountDenomination, groupID);

//        Mock the behaviour of accountRepository
//        Returning a boolean result (true)
        Mockito.when(accountRepository.existsById(credAccountID))
                .thenReturn(true);

        AccountID debAccountID = AccountID.createAccountID(runnersSupermarketAccountDenomination, groupID);

//        Mock the behaviour of accountRepository
//        Returning a boolean result (true), which will trigger a
////        NotFoundArgumentsBusinessException("Account to be debited does not exist; it needs to be created")
        Mockito.when(accountRepository.existsById(debAccountID))
                .thenReturn(false);

//        DTO
        CreateGroupTransactionDTO createGroupTransactionDTO = CreateGroupTransactionDTOAssembler.createDTOFromPrimitiveTypes(groupDenomination,
                personGroupMemberEmail, categoryDenomination, runnersSupermarketAccountDenomination, houseWalletAccountDenomination,
                transactionAmount, transactionType, transactionDescription, transactionDate);

//        Act

        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () ->
                createGroupTransactionService.createGroupTransaction(createGroupTransactionDTO));

        //Expected exception message
        String expectedMessage = "Account to be debited does not exist; it needs to be created";

        //Assert
        assertEquals(expectedMessage, thrown.getMessage());
    }




    //UPDATE




    @Test
    @DisplayName("Test For updateGroupTransaction() | Sunday Runners | Success")
    void updateGroupTransaction_SundayRunners_Success() {

        // Arrange

        //Group info
        String personEmail = "paulo@gmail.com";
        String groupDenomination = "Sunday Runners";
        String groupDescription = "All members from Sunday Runners group";
        LocalDate dateOfCreation = LocalDate.of(2020, 06, 01);

        //Transaction info
        final String denominationCategory = "Salary";
        final String type = "debit";
        final String description = "May Salary";
        final double amount = 1500.0;
        final String denominationAccountDeb = "Company";
        final String denominationAccountCred = "Bank Account";

        //To Search
        CategoryID categoryID = CategoryID.createCategoryID(denominationCategory, sundayRunnersID);
        AccountID debAccountID = AccountID.createAccountID(denominationAccountDeb, sundayRunnersID);
        AccountID credAccountID = AccountID.createAccountID(denominationAccountCred, sundayRunnersID);

        //Returning an Optional<Group> Fontes Family
        Mockito.when(groupRepository.findById(sundayRunnersID)).thenReturn(Optional.of(sundayRunners));

        //Returning True (Category exist)
        Mockito.when(categoryRepository.existsById(categoryID)).thenReturn(true);

        //Returning True (Account exist)
        Mockito.when(accountRepository.existsById(debAccountID)).thenReturn(true);

        //Returning True (Account exist)
        Mockito.when(accountRepository.existsById(credAccountID)).thenReturn(true);

        //Returning an Optional<Ledger> Sunday Runners
        Mockito.when(ledgerRepository.findById(ledgerID)).thenReturn(Optional.of(ledger));

        //DTO
        UpdateGroupTransactionDTO updateGroupTransactionDTO = UpdateGroupTransactionDTOAssembler.createDTOFromPrimitiveTypes(1, groupDenomination, personEmail, denominationCategory, denominationAccountDeb, denominationAccountCred, amount, type, description);

        //Expected GroupDTO
        GroupDTO expectedGroupDTO = GroupDTOAssembler.createDTOFromDomainObject(Denomination.createDenomination(groupDenomination), Description.createDescription(groupDescription), DateOfCreation.createDateOfCreation(dateOfCreation));

        //Service
        createGroupTransactionService = new CreateGroupTransactionService(groupRepository, accountRepository, categoryRepository, ledgerRepository);


        // Act

        GroupDTO result = createGroupTransactionService.updateGroupTransaction(updateGroupTransactionDTO);


        // Assert

        assertEquals(expectedGroupDTO, result);
    }

    @Test
    @DisplayName("Test For updateGroupTransaction() | Sunday Runners | Group Does Not Exist")
    void updateGroupTransaction_SundayRunners_GroupDoesNotExist() {

        // Arrange

        //Group info
        String personEmail = "paulo@gmail.com";
        String groupDenomination = "Saturday Runners";

        //Transaction info
        final String denominationCategory = "Salary";
        final String type = "debit";
        final String description = "May Salary";
        final double amount = 1500.0;
        final String denominationAccountDeb = "Company";
        final String denominationAccountCred = "Bank Account";

        //To Search
        CategoryID categoryID = CategoryID.createCategoryID(denominationCategory, sundayRunnersID);
        AccountID debAccountID = AccountID.createAccountID(denominationAccountDeb, sundayRunnersID);
        AccountID credAccountID = AccountID.createAccountID(denominationAccountCred, sundayRunnersID);

        //Returning an Optional<Group> Fontes Family
        Mockito.when(groupRepository.findById(sundayRunnersID)).thenReturn(Optional.empty());

        //Returning True (Category exist)
        Mockito.when(categoryRepository.existsById(categoryID)).thenReturn(true);

        //Returning True (Account exist)
        Mockito.when(accountRepository.existsById(debAccountID)).thenReturn(true);

        //Returning True (Account exist)
        Mockito.when(accountRepository.existsById(credAccountID)).thenReturn(true);

        //Returning an Optional<Ledger> Sunday Runners
        Mockito.when(ledgerRepository.findById(ledgerID)).thenReturn(Optional.of(ledger));

        //DTO
        UpdateGroupTransactionDTO updateGroupTransactionDTO = UpdateGroupTransactionDTOAssembler.createDTOFromPrimitiveTypes(1, groupDenomination, personEmail, denominationCategory, denominationAccountDeb, denominationAccountCred, amount, type, description);

        //Service
        createGroupTransactionService = new CreateGroupTransactionService(groupRepository, accountRepository, categoryRepository, ledgerRepository);


        // Act

        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () -> createGroupTransactionService.updateGroupTransaction(updateGroupTransactionDTO));


        //Assert

        assertEquals(thrown.getMessage(), CreateGroupTransactionService.GROUP_DOES_NOT_EXIST);
    }

    @Test
    @DisplayName("Test For updateGroupTransaction() | Sunday Runners | Person Not Member")
    void updateGroupTransaction_SundayRunners_PersonNotMember() {

        // Arrange

        //Group info
        String personEmail = "manuel@gmail.com";
        String groupDenomination = "Sunday Runners";

        //Transaction info
        final String denominationCategory = "Salary";
        final String type = "debit";
        final String description = "May Salary";
        final double amount = 1500.0;
        final String denominationAccountDeb = "Company";
        final String denominationAccountCred = "Bank Account";

        //To Search
        CategoryID categoryID = CategoryID.createCategoryID(denominationCategory, sundayRunnersID);
        AccountID debAccountID = AccountID.createAccountID(denominationAccountDeb, sundayRunnersID);
        AccountID credAccountID = AccountID.createAccountID(denominationAccountCred, sundayRunnersID);

        //Returning an Optional<Group> Fontes Family
        Mockito.when(groupRepository.findById(sundayRunnersID)).thenReturn(Optional.of(sundayRunners));

        //Returning True (Category exist)
        Mockito.when(categoryRepository.existsById(categoryID)).thenReturn(true);

        //Returning True (Account exist)
        Mockito.when(accountRepository.existsById(debAccountID)).thenReturn(true);

        //Returning True (Account exist)
        Mockito.when(accountRepository.existsById(credAccountID)).thenReturn(true);

        //Returning an Optional<Ledger> Sunday Runners
        Mockito.when(ledgerRepository.findById(ledgerID)).thenReturn(Optional.of(ledger));

        //DTO
        UpdateGroupTransactionDTO updateGroupTransactionDTO = UpdateGroupTransactionDTOAssembler.createDTOFromPrimitiveTypes(1, groupDenomination, personEmail, denominationCategory, denominationAccountDeb, denominationAccountCred, amount, type, description);

        //Service
        createGroupTransactionService = new CreateGroupTransactionService(groupRepository, accountRepository, categoryRepository, ledgerRepository);


        // Act

        Throwable thrown = assertThrows(InvalidArgumentsBusinessException.class, () -> createGroupTransactionService.updateGroupTransaction(updateGroupTransactionDTO));


        //Assert

        assertEquals(thrown.getMessage(), CreateGroupTransactionService.PERSON_NOT_MEMBER);
    }

    @Test
    @DisplayName("Test For updateGroupTransaction() | Sunday Runners | Need To Create Category")
    void updateGroupTransaction_SundayRunners_NeedToCreateCategory() {

        // Arrange

        //Group info
        String personEmail = "paulo@gmail.com";
        String groupDenomination = "Sunday Runners";

        //Transaction info
        final String denominationCategory = "Netflix";
        final String type = "debit";
        final String description = "May Salary";
        final double amount = 1500.0;
        final String denominationAccountDeb = "Company";
        final String denominationAccountCred = "Bank Account";

        //To Search
        CategoryID categoryID = CategoryID.createCategoryID(denominationCategory, sundayRunnersID);
        AccountID debAccountID = AccountID.createAccountID(denominationAccountDeb, sundayRunnersID);
        AccountID credAccountID = AccountID.createAccountID(denominationAccountCred, sundayRunnersID);

        //Returning an Optional<Group> Fontes Family
        Mockito.when(groupRepository.findById(sundayRunnersID)).thenReturn(Optional.of(sundayRunners));

        //Returning False (Category does not exist)
        Mockito.when(categoryRepository.existsById(categoryID)).thenReturn(false);

        //Returning True (Account exist)
        Mockito.when(accountRepository.existsById(debAccountID)).thenReturn(true);

        //Returning True (Account exist)
        Mockito.when(accountRepository.existsById(credAccountID)).thenReturn(true);

        //Returning an Optional<Ledger> Sunday Runners
        Mockito.when(ledgerRepository.findById(ledgerID)).thenReturn(Optional.of(ledger));

        //DTO
        UpdateGroupTransactionDTO updateGroupTransactionDTO = UpdateGroupTransactionDTOAssembler.createDTOFromPrimitiveTypes(1, groupDenomination, personEmail, denominationCategory, denominationAccountDeb, denominationAccountCred, amount, type, description);

        //Service
        createGroupTransactionService = new CreateGroupTransactionService(groupRepository, accountRepository, categoryRepository, ledgerRepository);


        // Act

        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () -> createGroupTransactionService.updateGroupTransaction(updateGroupTransactionDTO));


        //Assert

        assertEquals(thrown.getMessage(), CreateGroupTransactionService.NEED_TO_CREATE_CATEGORY);
    }

    @Test
    @DisplayName("Test For updateGroupTransaction() | Sunday Runners | Need To Create Account To Debit")
    void updateGroupTransaction_SundayRunners_NeedToCreateAccountToDebit() {

        // Arrange

        //Group info
        String personEmail = "paulo@gmail.com";
        String groupDenomination = "Sunday Runners";

        //Transaction info
        final String denominationCategory = "Salary";
        final String type = "debit";
        final String description = "May Salary";
        final double amount = 1500.0;
        final String denominationAccountDeb = "State";
        final String denominationAccountCred = "Bank Account";

        //To Search
        CategoryID categoryID = CategoryID.createCategoryID(denominationCategory, sundayRunnersID);
        AccountID debAccountID = AccountID.createAccountID(denominationAccountDeb, sundayRunnersID);
        AccountID credAccountID = AccountID.createAccountID(denominationAccountCred, sundayRunnersID);

        //Returning an Optional<Group> Fontes Family
        Mockito.when(groupRepository.findById(sundayRunnersID)).thenReturn(Optional.of(sundayRunners));

        //Returning True (Category exist)
        Mockito.when(categoryRepository.existsById(categoryID)).thenReturn(true);

        //Returning False (Account does not exist)
        Mockito.when(accountRepository.existsById(debAccountID)).thenReturn(false);

        //Returning True (Account exist)
        Mockito.when(accountRepository.existsById(credAccountID)).thenReturn(true);

        //Returning an Optional<Ledger> Sunday Runners
        Mockito.when(ledgerRepository.findById(ledgerID)).thenReturn(Optional.of(ledger));

        //DTO
        UpdateGroupTransactionDTO updateGroupTransactionDTO = UpdateGroupTransactionDTOAssembler.createDTOFromPrimitiveTypes(1, groupDenomination, personEmail, denominationCategory, denominationAccountDeb, denominationAccountCred, amount, type, description);

        //Service
        createGroupTransactionService = new CreateGroupTransactionService(groupRepository, accountRepository, categoryRepository, ledgerRepository);


        // Act

        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () -> createGroupTransactionService.updateGroupTransaction(updateGroupTransactionDTO));


        //Assert

        assertEquals(thrown.getMessage(), CreateGroupTransactionService.NEED_TO_CREATE_ACCOUNT_TO_DEBIT);
    }

    @Test
    @DisplayName("Test For updateGroupTransaction() | Sunday Runners | Need To Create Account To Credit")
    void updateGroupTransaction_SundayRunners_NeedToCreateAccountToCredit() {

        // Arrange

        //Group info
        String personEmail = "paulo@gmail.com";
        String groupDenomination = "Sunday Runners";

        //Transaction info
        final String denominationCategory = "Salary";
        final String type = "debit";
        final String description = "May Salary";
        final double amount = 1500.0;
        final String denominationAccountDeb = "Company";
        final String denominationAccountCred = "State";

        //To Search
        CategoryID categoryID = CategoryID.createCategoryID(denominationCategory, sundayRunnersID);
        AccountID debAccountID = AccountID.createAccountID(denominationAccountDeb, sundayRunnersID);
        AccountID credAccountID = AccountID.createAccountID(denominationAccountCred, sundayRunnersID);

        //Returning an Optional<Group> Fontes Family
        Mockito.when(groupRepository.findById(sundayRunnersID)).thenReturn(Optional.of(sundayRunners));

        //Returning True (Category exist)
        Mockito.when(categoryRepository.existsById(categoryID)).thenReturn(true);

        //Returning True (Account exist)
        Mockito.when(accountRepository.existsById(debAccountID)).thenReturn(true);

        //Returning False (Account does not exist)
        Mockito.when(accountRepository.existsById(credAccountID)).thenReturn(false);

        //Returning an Optional<Ledger> Sunday Runners
        Mockito.when(ledgerRepository.findById(ledgerID)).thenReturn(Optional.of(ledger));

        //DTO
        UpdateGroupTransactionDTO updateGroupTransactionDTO = UpdateGroupTransactionDTOAssembler.createDTOFromPrimitiveTypes(1, groupDenomination, personEmail, denominationCategory, denominationAccountDeb, denominationAccountCred, amount, type, description);

        //Service
        createGroupTransactionService = new CreateGroupTransactionService(groupRepository, accountRepository, categoryRepository, ledgerRepository);


        // Act

        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () -> createGroupTransactionService.updateGroupTransaction(updateGroupTransactionDTO));


        //Assert

        assertEquals(thrown.getMessage(), CreateGroupTransactionService.NEED_TO_CREATE_ACCOUNT_TO_CREDIT);
    }




    //DELETE




    @Test
    @DisplayName("Test For deleteGroupTransaction() | Sunday Runners | Success")
    void deleteGroupTransaction_SundayRunners_Success() {

        // Arrange

        //Group info
        String personEmail = "paulo@gmail.com";
        String groupDenomination = "Sunday Runners";
        String groupDescription = "All members from Sunday Runners group";
        LocalDate dateOfCreation = LocalDate.of(2020, 06, 01);

        //Returning an Optional<Group> Fontes Family
        Mockito.when(groupRepository.findById(sundayRunnersID)).thenReturn(Optional.of(sundayRunners));

        //Returning an Optional<Ledger> Sunday Runners
        Mockito.when(ledgerRepository.findById(ledgerID)).thenReturn(Optional.of(ledger));

        //DTO
        DeleteGroupTransactionDTO deleteGroupTransactionDTO = DeleteGroupTransactionDTOAssembler.createDTOFromPrimitiveTypes(1, groupDenomination, personEmail);

        //Expected GroupDTO
        GroupDTO expectedGroupDTO = GroupDTOAssembler.createDTOFromDomainObject(Denomination.createDenomination(groupDenomination), Description.createDescription(groupDescription), DateOfCreation.createDateOfCreation(dateOfCreation));

        //Service
        createGroupTransactionService = new CreateGroupTransactionService(groupRepository, accountRepository, categoryRepository, ledgerRepository);


        // Act

        GroupDTO result = createGroupTransactionService.deleteGroupTransaction(deleteGroupTransactionDTO);


        // Assert

        assertEquals(expectedGroupDTO, result);
    }

    @Test
    @DisplayName("Test For deleteGroupTransaction() | Sunday Runners | Group Does Not Exist")
    void deleteGroupTransaction_SundayRunners_GroupDoesNotExist() {

        // Arrange

        //Group info
        String personEmail = "paulo@gmail.com";
        String groupDenomination = "Saturday Runners";

        //Returning an Optional<Group> Fontes Family
        Mockito.when(groupRepository.findById(sundayRunnersID)).thenReturn(Optional.empty());

        //Returning an Optional<Ledger> Sunday Runners
        Mockito.when(ledgerRepository.findById(ledgerID)).thenReturn(Optional.of(ledger));

        //DTO
        DeleteGroupTransactionDTO deleteGroupTransactionDTO = DeleteGroupTransactionDTOAssembler.createDTOFromPrimitiveTypes(1, groupDenomination, personEmail);

        //Service
        createGroupTransactionService = new CreateGroupTransactionService(groupRepository, accountRepository, categoryRepository, ledgerRepository);


        // Act

        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () -> createGroupTransactionService.deleteGroupTransaction(deleteGroupTransactionDTO));


        //Assert

        assertEquals(thrown.getMessage(), CreateGroupTransactionService.GROUP_DOES_NOT_EXIST);
    }

    @Test
    @DisplayName("Test For deleteGroupTransaction() | Sunday Runners | Person Not Member")
    void deleteGroupTransaction_SundayRunners_PersonNotMember() {

        // Arrange

        //Group info
        String personEmail = "manuel@gmail.com";
        String groupDenomination = "Sunday Runners";

        //Returning an Optional<Group> Fontes Family
        Mockito.when(groupRepository.findById(sundayRunnersID)).thenReturn(Optional.of(sundayRunners));

        //Returning an Optional<Ledger> Sunday Runners
        Mockito.when(ledgerRepository.findById(ledgerID)).thenReturn(Optional.of(ledger));

        //DTO
        DeleteGroupTransactionDTO deleteGroupTransactionDTO = DeleteGroupTransactionDTOAssembler.createDTOFromPrimitiveTypes(1, groupDenomination, personEmail);

        //Service
        createGroupTransactionService = new CreateGroupTransactionService(groupRepository, accountRepository, categoryRepository, ledgerRepository);


        // Act

        Throwable thrown = assertThrows(InvalidArgumentsBusinessException.class, () -> createGroupTransactionService.deleteGroupTransaction(deleteGroupTransactionDTO));


        //Assert

        assertEquals(thrown.getMessage(), CreateGroupTransactionService.PERSON_NOT_MEMBER);
    }
}
