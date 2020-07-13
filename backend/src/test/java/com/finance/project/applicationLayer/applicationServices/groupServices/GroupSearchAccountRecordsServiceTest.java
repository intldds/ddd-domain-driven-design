package com.finance.project.applicationLayer.applicationServices.groupServices;

import com.finance.project.controllerLayer.integrationTests.AbstractTest;
import com.finance.project.domainLayer.domainEntities.aggregates.group.Group;
import com.finance.project.domainLayer.domainEntities.aggregates.ledger.Ledger;
import com.finance.project.domainLayer.domainEntities.aggregates.ledger.Transaction;
import com.finance.project.domainLayer.domainEntities.vosShared.*;
import com.finance.project.domainLayer.exceptions.InvalidArgumentsBusinessException;
import com.finance.project.domainLayer.exceptions.NotFoundArgumentsBusinessException;
import com.finance.project.domainLayer.repositoriesInterfaces.IAccountRepository;
import com.finance.project.domainLayer.repositoriesInterfaces.IGroupRepository;
import com.finance.project.domainLayer.repositoriesInterfaces.ILedgerRepository;
import com.finance.project.dtos.dtosAssemblers.SearchAccountRecordsOutDTOAssembler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import com.finance.project.dtos.dtos.GroupSearchAccountRecordsInDTO;
import com.finance.project.dtos.dtos.PersonSearchAccountRecordsOutDTO;
import com.finance.project.dtos.dtosAssemblers.GroupSearchAccountRecordsInDTOAssembler;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class GroupSearchAccountRecordsServiceTest extends AbstractTest {

    @Mock
    private IGroupRepository groupRepository;
    @Mock
    private IAccountRepository accountRepository;
    @Mock
    private ILedgerRepository ledgerRepository;

    private GroupSearchAccountRecordsService groupSearchService;

    private Group group;
    private GroupID groupID;
    private Ledger ledger;
    private LedgerID ledgerID;


    @BeforeEach
    public void init() {

        //Ledger
        ledger = Ledger.createLedger();
        ledgerID = ledger.getLedgerID();

        //Group - Admin
        String joaquinaEmail = "joaquina@gmail.com";
        PersonID joaquinaPersonID = PersonID.createPersonID(joaquinaEmail);

        //Group - members
        String mariaEmail = "maria@gmail.com";
        PersonID mariaPersonID = PersonID.createPersonID(mariaEmail);

        String anaEmail = "ana@gmail.com";
        PersonID anaPersonID = PersonID.createPersonID(anaEmail);

        //Group
        String groupDenomination = "House mattes";
        String groupDescription = "People sharing house expenses";
        LocalDate groupDateCreation = LocalDate.of(1980, 05, 29);
        group = Group.createGroupAsPersonInCharge(groupDenomination, joaquinaPersonID, groupDescription, groupDateCreation, ledgerID);

        group.addMember(mariaPersonID);
        group.addMember(anaPersonID);

        groupID = group.getGroupID();

        //Group Categories
        //Category Electricity Expenses
        String denominationCatEDP = "Electricity Expenses";
        CategoryID categoryEdpID = CategoryID.createCategoryID(denominationCatEDP, groupID);
        group.addCategory(categoryEdpID);

        //Category Water Expenses
        String denominationCatWater = "Water Expenses";
        CategoryID categoryWaterID = CategoryID.createCategoryID(denominationCatWater, groupID);
        group.addCategory(categoryWaterID);

        //Group Accounts
        //Account House Wallet
        String walletAccountDenomination = "House Wallet Funds";
        String walletAccountDescription = "Money to spend with house expenses";
        AccountID walletAccountID = AccountID.createAccountID(walletAccountDenomination, groupID);
        group.addAccount(walletAccountID);

        //Account EDP
        String edpAccountDenomination = "EDP";
        String edpAccountDescription = "Electricity expenses from EDP";
        AccountID edpAccountID = AccountID.createAccountID(edpAccountDenomination, groupID);
        group.addAccount(edpAccountID);

        //Account Water
        String waterAccountDenomination = "Water";
        String waterAccountDescription = "Water expenses from AdP";
        AccountID waterAccountID = AccountID.createAccountID(waterAccountDenomination, groupID);
        group.addAccount(waterAccountID);

        //Group transactions
        //Transaction 1 - Water - Debit
        String typeTransaction1 = "Debit";
        String descriptionTransaction1 = "Water bill from February/2020";
        LocalDate dateTransaction1 = LocalDate.of(2020, 03, 01);
        double amountTransaction1 = 45.00;
        Transaction transaction1 = Transaction.createTransaction(categoryWaterID, typeTransaction1, descriptionTransaction1, amountTransaction1, dateTransaction1, walletAccountID, waterAccountID);
        ledger.addTransaction(transaction1);

        //Transaction 2 - EDP - Debit
        String typeTransaction2 = "Debit";
        String descriptionTransaction2 = "EDP bill from February/2020";
        LocalDate dateTransaction2 = LocalDate.of(2020, 03, 03);
        double amountTransaction2 = 45.00;
        Transaction transaction2 = Transaction.createTransaction(categoryEdpID, typeTransaction2, descriptionTransaction2, amountTransaction2, dateTransaction2, walletAccountID, edpAccountID);
        ledger.addTransaction(transaction2);

        //Transaction 3 - Water - Debit
        String typeTransaction3 = "Debit";
        String descriptionTransaction3 = "Water bill from March/2020";
        LocalDate dateTransaction3 = LocalDate.of(2020, 04, 01);
        double amountTransaction3 = 45.00;
        Transaction transaction3 = Transaction.createTransaction(categoryWaterID, typeTransaction3, descriptionTransaction3, amountTransaction3, dateTransaction3, walletAccountID, waterAccountID);
        ledger.addTransaction(transaction3);

        //Transaction 4 - EDP - Credit
        String typeTransaction4 = "Credit";
        String descriptionTransaction4 = "EDP bill from March/2020 - settlement - overcharge";
        LocalDate dateTransaction4 = LocalDate.of(2020, 04, 03);
        double amountTransaction4 = 15.00;
        Transaction transaction4 = Transaction.createTransaction(categoryEdpID, typeTransaction4, descriptionTransaction4, amountTransaction4, dateTransaction4, edpAccountID, walletAccountID);
        ledger.addTransaction(transaction4);

        //Transaction 5 - Water - Debit
        String typeTransaction5 = "Debit";
        String descriptionTransaction5 = "Water bill from April/2020";
        LocalDate dateTransaction5 = LocalDate.of(2020, 05, 01);
        double amountTransaction5 = 45.00;
        Transaction transaction5 = Transaction.createTransaction(categoryWaterID, typeTransaction5, descriptionTransaction5, amountTransaction5, dateTransaction5, walletAccountID, waterAccountID);
        ledger.addTransaction(transaction5);
    }


    @Test
    @DisplayName("Test getGroupAccountTransactionsWithinPeriod - Success")
    void getGroupAccountTransactionsWithinPeriod_success() {

        //ARRANGE
        //Info for DTO in
        String personEmail = "maria@gmail.com";
        String groupDenomination = "House mattes";
        String accountDenomination = "EDP";
        String startDate = "2020-03-03";
        String endDate = "2020-04-03";

        //DTO in
        GroupSearchAccountRecordsInDTO dtoIn = GroupSearchAccountRecordsInDTOAssembler.groupSearchAccountRecordsInDTO(personEmail, groupDenomination, accountDenomination, startDate, endDate);

        //Arrange category and accounts for expected transactions
        //Category Electricity Expenses
        String denominationCategoryEDP = "Electricity Expenses";
        CategoryID categoryIDEdp = CategoryID.createCategoryID(denominationCategoryEDP, GroupID.createGroupID(groupDenomination));

        //Account EDP
        AccountID accountIDEdp = AccountID.createAccountID(accountDenomination, GroupID.createGroupID(groupDenomination));

        //Account Wallet - House expenses
        String walletAccountDenomination = "House Wallet Funds";
        AccountID accountIDWallet = AccountID.createAccountID(walletAccountDenomination, GroupID.createGroupID(groupDenomination));

        //Arrange expected transactions
        //Transaction 2 - EDP - Debit
        String typeTransaction2 = "Debit";
        String descriptionTransaction2 = "EDP bill from February/2020";
        LocalDate dateTransaction2 = LocalDate.of(2020, 03, 03);
        double amountTransaction2 = 45.00;
        Transaction transaction2 = Transaction.createTransaction(categoryIDEdp, typeTransaction2, descriptionTransaction2, amountTransaction2, dateTransaction2, accountIDWallet, accountIDEdp);

        //Transaction 4 - EDP - Credit
        String typeTransaction4 = "Credit";
        String descriptionTransaction4 = "EDP bill from March/2020 - settlement - overcharge";
        LocalDate dateTransaction4 = LocalDate.of(2020, 04, 03);
        double amountTransaction4 = 15.00;
        Transaction transaction4 = Transaction.createTransaction(categoryIDEdp, typeTransaction4, descriptionTransaction4, amountTransaction4, dateTransaction4, accountIDEdp, accountIDWallet);

        //Expected DTO out
        ArrayList<Transaction> expectedTransactions = new ArrayList<>();
        expectedTransactions.add(transaction2);
        expectedTransactions.add(transaction4);

        PersonSearchAccountRecordsOutDTO expectedOutDTO = SearchAccountRecordsOutDTOAssembler.accountTransactionsOutDTO(expectedTransactions);

        //Mock the behaviour of groupRepository, returning group
        Mockito.when(groupRepository.findById(groupID)).thenReturn(Optional.of(group));

        //Mock the behaviour of accountRepository, returning existence of account
        Mockito.when(accountRepository.existsById(accountIDEdp)).thenReturn(true);

        //Mock the behaviour of ledgerRepository, returning ledger
        Mockito.when(ledgerRepository.findById(ledgerID)).thenReturn(Optional.of(ledger));

        //Instantiate service
        groupSearchService = new GroupSearchAccountRecordsService(groupRepository, accountRepository, ledgerRepository);

        //ACT
        PersonSearchAccountRecordsOutDTO result = groupSearchService.getGroupAccountTransactionsWithinPeriod(dtoIn);

        //ASSERT
        assertEquals(expectedOutDTO, result);

    }

    @Test
    @DisplayName("Test for getGroupAccountTransactionsWithinPeriod() - Exception - Group does not exist")
    void getGroupAccountTransactionsWithinPeriod_exception_groupDoesntExist() {

        //ARRANGE
        //Info for DTO in
        String personEmail = "maria@gmail.com";
        String groupDenomination = "Swimming mattes";
        String accountDenomination = "EDP";
        String startDate = "2020-03-03";
        String endDate = "2020-04-03";

        //DTO in
        GroupSearchAccountRecordsInDTO dtoIn = GroupSearchAccountRecordsInDTOAssembler.groupSearchAccountRecordsInDTO(personEmail, groupDenomination, accountDenomination, startDate, endDate);

        String expectedMessage = "Group does not exist in the system";

        //Mock the behaviour of groupRepository, returning group
        Mockito.when(groupRepository.findById(GroupID.createGroupID(groupDenomination))).thenReturn(Optional.empty());

        //Instantiate service
        groupSearchService = new GroupSearchAccountRecordsService(groupRepository, accountRepository, ledgerRepository);

        //ACT
        Throwable thrown = assertThrows(InvalidArgumentsBusinessException.class, () -> groupSearchService.getGroupAccountTransactionsWithinPeriod(dtoIn));

        //ASSERT
        assertEquals(expectedMessage, thrown.getMessage());

    }

    @Test
    @DisplayName("Test for getGroupAccountTransactionsWithinPeriod() - Exception - Person is not member")
    void getGroupAccountTransactionsWithinPeriod_exception_personNotMember() {

        //ARRANGE
        //Info for DTO in
        String personEmail = "josefina@gmail.com";
        String groupDenomination = "House mattes";
        String accountDenomination = "EDP";
        String startDate = "2020-03-03";
        String endDate = "2020-04-03";

        //DTO in
        GroupSearchAccountRecordsInDTO dtoIn = GroupSearchAccountRecordsInDTOAssembler.groupSearchAccountRecordsInDTO(personEmail, groupDenomination, accountDenomination, startDate, endDate);

        String expectedMessage = "Person is not member of the group";

        //Mock the behaviour of groupRepository, returning group
        Mockito.when(groupRepository.findById(groupID)).thenReturn(Optional.of(group));

        //Instantiate service
        groupSearchService = new GroupSearchAccountRecordsService(groupRepository, accountRepository, ledgerRepository);

        //ACT
        Throwable thrown = assertThrows(InvalidArgumentsBusinessException.class, () -> groupSearchService.getGroupAccountTransactionsWithinPeriod(dtoIn));

        //ASSERT
        assertEquals(expectedMessage, thrown.getMessage());

    }

    @Test
    @DisplayName("Test for getGroupAccountTransactionsWithinPeriod() - Exception - Account does not exist")
    void getGroupAccountTransactionsWithinPeriod_exception_accountDoesntExist() {

        //ARRANGE
        //Info for DTO in
        String personEmail = "maria@gmail.com";
        String groupDenomination = "House mattes";
        String accountDenomination = "Pool";
        String startDate = "2020-03-03";
        String endDate = "2020-04-03";

        //DTO in
        GroupSearchAccountRecordsInDTO dtoIn = GroupSearchAccountRecordsInDTOAssembler.groupSearchAccountRecordsInDTO(personEmail, groupDenomination, accountDenomination, startDate, endDate);

        //Expected message
        String expectedMessage = "Account does not exist in the system";

        //Mock the behaviour of groupRepository, returning group
        Mockito.when(groupRepository.findById(groupID)).thenReturn(Optional.of(group));

        //Mock the behaviour of accountRepository, returning existence of account
        Mockito.when(accountRepository.existsById(AccountID.createAccountID(accountDenomination, groupID))).thenReturn(false);

        //Instantiate service
        groupSearchService = new GroupSearchAccountRecordsService(groupRepository, accountRepository, ledgerRepository);

        //ACT
        Throwable thrown = assertThrows(InvalidArgumentsBusinessException.class, () -> groupSearchService.getGroupAccountTransactionsWithinPeriod(dtoIn));

        //ASSERT
        assertEquals(expectedMessage, thrown.getMessage());

    }

    @Test
    @DisplayName("Test for getGroupAccountTransactionsWithinPeriod() - Exception - Start and end dates order")
    void getGroupAccountTransactionsWithinPeriod_exception_searchDatesOrder() {

        //ARRANGE
        //Info for DTO in
        String personEmail = "maria@gmail.com";
        String groupDenomination = "House mattes";
        String accountDenomination = "EDP";
        String startDate = "2020-05-03";
        String endDate = "2020-04-03";

        //DTO in
        GroupSearchAccountRecordsInDTO dtoIn = GroupSearchAccountRecordsInDTOAssembler.groupSearchAccountRecordsInDTO(personEmail, groupDenomination, accountDenomination, startDate, endDate);

        //Mock the behaviour of groupRepository, returning group
        Mockito.when(groupRepository.findById(groupID)).thenReturn(Optional.of(group));

        //Mock the behaviour of accountRepository, returning existence of account
        Mockito.when(accountRepository.existsById(AccountID.createAccountID(accountDenomination, groupID))).thenReturn(true);

        //Mock the behaviour of ledgerRepository, returning ledger
        Mockito.when(ledgerRepository.findById(ledgerID)).thenReturn(Optional.of(ledger));

        //Instantiate service
        groupSearchService = new GroupSearchAccountRecordsService(groupRepository, accountRepository, ledgerRepository);

        //Expected message
        String expectedMessage = "Check the start and end dates for the period, since start date cannot be later than end date";

        //ACT
        Throwable thrown = assertThrows(InvalidArgumentsBusinessException.class, () -> groupSearchService.getGroupAccountTransactionsWithinPeriod(dtoIn));

        //ASSERT
        assertEquals(expectedMessage, thrown.getMessage());

    }

    @Test
    @DisplayName("Test for getGroupAccountTransactionsWithinPeriod() - Exception - Search period prior to ledger records")
    void getGroupAccountTransactionsWithinPeriod_exception_searchBeforeLedgerRecords() {

        //ARRANGE
        //Info for DTO in
        String personEmail = "maria@gmail.com";
        String groupDenomination = "House mattes";
        String accountDenomination = "EDP";
        String startDate = "2020-01-01";
        String endDate = "2020-01-20";

        //DTO in
        GroupSearchAccountRecordsInDTO dtoIn = GroupSearchAccountRecordsInDTOAssembler.groupSearchAccountRecordsInDTO(personEmail, groupDenomination, accountDenomination, startDate, endDate);

        //Mock the behaviour of groupRepository, returning group
        Mockito.when(groupRepository.findById(groupID)).thenReturn(Optional.of(group));

        //Mock the behaviour of accountRepository, returning existence of account
        Mockito.when(accountRepository.existsById(AccountID.createAccountID(accountDenomination, groupID))).thenReturn(true);

        //Mock the behaviour of ledgerRepository, returning ledger
        Mockito.when(ledgerRepository.findById(ledgerID)).thenReturn(Optional.of(ledger));

        //Instantiate service
        groupSearchService = new GroupSearchAccountRecordsService(groupRepository, accountRepository, ledgerRepository);

        //Expected message
        String expectedMessage = "The time period provided falls outside the range of the ledger records";

        //ACT
        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () -> groupSearchService.getGroupAccountTransactionsWithinPeriod(dtoIn));

        //ASSERT
        assertEquals(expectedMessage, thrown.getMessage());

    }

    @Test
    @DisplayName("Test for getGroupAccountTransactionsWithinPeriod() - Exception - Search period after ledger records")
    void getGroupAccountTransactionsWithinPeriod_exception_searchAfterLedgerRecords() {

        //ARRANGE
        //Info for DTO in
        String personEmail = "maria@gmail.com";
        String groupDenomination = "House mattes";
        String accountDenomination = "EDP";
        String startDate = "2020-06-01";
        String endDate = "2020-06-10";

        //DTO in
        GroupSearchAccountRecordsInDTO dtoIn = GroupSearchAccountRecordsInDTOAssembler.groupSearchAccountRecordsInDTO(personEmail, groupDenomination, accountDenomination, startDate, endDate);

        //Mock the behaviour of groupRepository, returning group
        Mockito.when(groupRepository.findById(groupID)).thenReturn(Optional.of(group));

        //Mock the behaviour of accountRepository, returning existence of account
        Mockito.when(accountRepository.existsById(AccountID.createAccountID(accountDenomination, groupID))).thenReturn(true);

        //Mock the behaviour of ledgerRepository, returning ledger
        Mockito.when(ledgerRepository.findById(ledgerID)).thenReturn(Optional.of(ledger));

        //Instantiate service
        groupSearchService = new GroupSearchAccountRecordsService(groupRepository, accountRepository, ledgerRepository);

        //Expected message
        String expectedMessage = "The time period provided falls outside the range of the ledger records";

        //ACT
        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () -> groupSearchService.getGroupAccountTransactionsWithinPeriod(dtoIn));

        //ASSERT
        assertEquals(expectedMessage, thrown.getMessage());

    }

    @Test
    @DisplayName("Test for getGroupAccountTransactionsWithinPeriod() - Exception - No transactions within the search period")
    void getGroupAccountTransactionsWithinPeriod_exception_noTransactionsInSearchPeriod() {

        //ARRANGE
        //Info for DTO in
        String personEmail = "maria@gmail.com";
        String groupDenomination = "House mattes";
        String accountDenomination = "EDP";
        String startDate = "2020-03-04";
        String endDate = "2020-04-02";

        //DTO in
        GroupSearchAccountRecordsInDTO dtoIn = GroupSearchAccountRecordsInDTOAssembler.groupSearchAccountRecordsInDTO(
                personEmail, groupDenomination, accountDenomination, startDate, endDate);

        //Mock the behaviour of groupRepository, returning group
        Mockito.when(groupRepository.findById(groupID)).thenReturn(Optional.of(group));

        //Mock the behaviour of accountRepository, returning existence of account
        Mockito.when(accountRepository.existsById(AccountID.createAccountID(accountDenomination, groupID))).thenReturn(true);

        //Mock the behaviour of ledgerRepository, returning ledger
        Mockito.when(ledgerRepository.findById(ledgerID)).thenReturn(Optional.of(ledger));

        //Instantiate service
        groupSearchService = new GroupSearchAccountRecordsService(groupRepository, accountRepository, ledgerRepository);

        //Expected message
        String expectedMessage = "Ledger has no transactions within the searched period";

        //ACT
        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () -> groupSearchService.getGroupAccountTransactionsWithinPeriod(dtoIn));

        //ASSERT
        assertEquals(expectedMessage, thrown.getMessage());

    }

    @Test
    @DisplayName("Test for getGroupAccountTransactionsWithinPeriod() - Exception - Ledger empty")
    void getGroupAccountTransactionsWithinPeriod_exception_emptyLedger() {

        //ARRANGE
        //Info for DTO in
        String personEmail = "maria@gmail.com";
        String groupDenomination = "House mattes";
        String accountDenomination = "EDP";
        String startDate = "2020-03-04";
        String endDate = "2020-04-02";

        //DTO in
        GroupSearchAccountRecordsInDTO dtoIn = GroupSearchAccountRecordsInDTOAssembler.groupSearchAccountRecordsInDTO(personEmail, groupDenomination, accountDenomination, startDate, endDate);

        //Mock the behaviour of groupRepository, returning group
        Mockito.when(groupRepository.findById(groupID)).thenReturn(Optional.of(group));

        //Mock the behaviour of accountRepository, returning existence of account
        Mockito.when(accountRepository.existsById(AccountID.createAccountID(accountDenomination, groupID))).thenReturn(true);

        //Mock the behaviour of ledgerRepository, returning ledger
        Mockito.when(ledgerRepository.findById(ledgerID)).thenReturn(Optional.of(Ledger.createLedger()));

        //Instantiate service
        groupSearchService = new GroupSearchAccountRecordsService(groupRepository, accountRepository, ledgerRepository);

        //Expected message
        String expectedMessage = "Ledger is empty";

        //ACT
        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () -> groupSearchService.getGroupAccountTransactionsWithinPeriod(dtoIn));

        //ASSERT
        assertEquals(expectedMessage, thrown.getMessage());

    }

    @Test
    @DisplayName("Test for getGroupAccountTransactionsWithinPeriod() - Exception - Account denomination is missing")
    void getGroupAccountTransactionsWithinPeriod_exception_accountNameMissing() {

        //ARRANGE
        //Info for DTO in
        String personEmail = "maria@gmail.com";
        String groupDenomination = "House mattes";
        String accountDenomination = "";
        String startDate = "2020-03-03";
        String endDate = "2020-04-03";

        //DTO in
        GroupSearchAccountRecordsInDTO dtoIn = GroupSearchAccountRecordsInDTOAssembler.groupSearchAccountRecordsInDTO(personEmail, groupDenomination, accountDenomination, startDate, endDate);

        //Mock the behaviour of groupRepository, returning group
        Mockito.when(groupRepository.findById(groupID)).thenReturn(Optional.of(group));

        //Mock the behaviour of ledgerRepository, returning ledger
        Mockito.when(ledgerRepository.findById(ledgerID)).thenReturn(Optional.of(Ledger.createLedger()));

        //Instantiate service
        groupSearchService = new GroupSearchAccountRecordsService(groupRepository, accountRepository, ledgerRepository);

        //Expected message
        String expectedMessage = "Search results cannot be displayed: account name is missing";

        //ACT
        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () -> groupSearchService.getGroupAccountTransactionsWithinPeriod(dtoIn));

        //ASSERT
        assertEquals(expectedMessage, thrown.getMessage());

    }

    @Test
    @DisplayName("Test for getGroupAccountTransactionsWithinPeriod() - Exception - Start date is missing")
    void getGroupAccountTransactionsWithinPeriod_exception_startDateMissing() {

        //ARRANGE
        //Info for DTO in
        String personEmail = "maria@gmail.com";
        String groupDenomination = "House mattes";
        String accountDenomination = "EDP";
        String startDate = "";
        String endDate = "2020-04-03";

        //DTO in
        GroupSearchAccountRecordsInDTO dtoIn = GroupSearchAccountRecordsInDTOAssembler.groupSearchAccountRecordsInDTO(personEmail, groupDenomination, accountDenomination, startDate, endDate);

        //Mock the behaviour of groupRepository, returning group
        Mockito.when(groupRepository.findById(groupID)).thenReturn(Optional.of(group));

        //Mock the behaviour of accountRepository, returning existence of account
        Mockito.when(accountRepository.existsById(AccountID.createAccountID(accountDenomination, groupID))).thenReturn(true);

        //Mock the behaviour of ledgerRepository, returning ledger
        Mockito.when(ledgerRepository.findById(ledgerID)).thenReturn(Optional.of(ledger));

        //Instantiate service
        groupSearchService = new GroupSearchAccountRecordsService(groupRepository, accountRepository, ledgerRepository);

        //Expected message
        String expectedMessage = "Search results cannot be displayed: start date is missing";

        //ACT
        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () -> groupSearchService.getGroupAccountTransactionsWithinPeriod(dtoIn));

        //ASSERT
        assertEquals(expectedMessage, thrown.getMessage());

    }

    @Test
    @DisplayName("Test for getGroupAccountTransactionsWithinPeriod() - Exception - End date is missing")
    void getGroupAccountTransactionsWithinPeriod_exception_endDateMissing() {

        //ARRANGE
        //Info for DTO in
        String personEmail = "maria@gmail.com";
        String groupDenomination = "House mattes";
        String accountDenomination = "EDP";
        String startDate = "2020-03-03";
        String endDate = "";


        //DTO in
        GroupSearchAccountRecordsInDTO dtoIn = GroupSearchAccountRecordsInDTOAssembler.groupSearchAccountRecordsInDTO(personEmail, groupDenomination, accountDenomination, startDate, endDate);

        //Mock the behaviour of groupRepository, returning group
        Mockito.when(groupRepository.findById(groupID)).thenReturn(Optional.of(group));

        //Mock the behaviour of accountRepository, returning existence of account
        Mockito.when(accountRepository.existsById(AccountID.createAccountID(accountDenomination, groupID))).thenReturn(true);

        //Mock the behaviour of ledgerRepository, returning ledger
        Mockito.when(ledgerRepository.findById(ledgerID)).thenReturn(Optional.of(ledger));

        //Instantiate service
        groupSearchService = new GroupSearchAccountRecordsService(groupRepository, accountRepository, ledgerRepository);

        //Expected message
        String expectedMessage = "Search results cannot be displayed: end date is missing";

        //ACT
        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () -> groupSearchService.getGroupAccountTransactionsWithinPeriod(dtoIn));

        //ASSERT
        assertEquals(expectedMessage, thrown.getMessage());
    }


}