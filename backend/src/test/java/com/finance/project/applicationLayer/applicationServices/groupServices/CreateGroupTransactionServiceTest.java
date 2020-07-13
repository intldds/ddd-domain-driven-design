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


    // POST

    @Test
    @DisplayName("Test for service method createGroupTransaction() - Success")
    void createGroupTransaction_success() {

        // Act
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

        //      Arrange Debit Account
        String runnersSupermarketAccountDenomination = "Supermarket A";
        String runnersSupermarketAccountDescription = "Money spent on Supermarket A";

        //        Arrange expectedGroupDTO

        Denomination groupHouseDenomination = Denomination.createDenomination(groupDenomination);
        Description groupHouseDescription = Description.createDescription(groupDescription);
        DateOfCreation groupHouseDateOfCreation = DateOfCreation.createDateOfCreation(groupCreation);
        CategoryID groupHouseCategoryID = CategoryID.createCategoryID(categoryDenomination, groupID);

        // Mock the behaviour of groupRepository, returning an Optional<Group> groupHouse
        Mockito.when(groupRepository.findById(groupID))
                .thenReturn(Optional.of(groupHouse));

        // Mock the behaviour of categoryRepository, returning a boolean result (true)
        Mockito.when(categoryRepository.existsById(groupHouseCategoryID)).thenReturn(true);

        AccountID credAccountID = AccountID.createAccountID(houseWalletAccountDenomination, groupID);

        // Mock the behaviour of accountRepository, returning a boolean result (true)
        Mockito.when(accountRepository.existsById(credAccountID))
                .thenReturn(true);

        AccountID debAccountID = AccountID.createAccountID(runnersSupermarketAccountDenomination, groupID);

        // Mock the behaviour of accountRepository, returning a boolean result (true)
        Mockito.when(accountRepository.existsById(debAccountID)).thenReturn(true);

        // Mock the behaviour of ledgerRepository, returning a Optional<Ledger> groupLedger
        Mockito.when(ledgerRepository.findById(groupLedgerID)).thenReturn(Optional.of(groupLedger));

        // Expected transaction
        Transaction expectedTransaction = Transaction.createTransaction(groupHouseCategoryID, transactionType,
                transactionDescription, transactionAmount, LocalDate.parse(transactionDate), debAccountID, credAccountID);

        // Mock the behaviour of ledgerRepository, returning a Ledger groupLedger
        Mockito.when(ledgerRepository.findById(groupHouse.getLedgerID())).thenReturn(Optional.of(groupLedger));

        // DTO
        CreateGroupTransactionDTO createGroupTransactionDTO = CreateGroupTransactionDTOAssembler.createDTOFromPrimitiveTypes(groupDenomination,
                personGroupMemberEmail, categoryDenomination, runnersSupermarketAccountDenomination, houseWalletAccountDenomination, transactionAmount, transactionType, transactionDescription, transactionDate);

        // Act
        GroupDTO result = createGroupTransactionService.createGroupTransaction(createGroupTransactionDTO);

        // Verify if the expected transaction exists in the groupHouse ledger
        boolean checkIfContainsTransaction = groupLedger.getRecords().contains(expectedTransaction);

        // Expected GroupDTO
        GroupDTO expectedGroupDTO = GroupDTOAssembler.createDTOFromDomainObject(groupHouseDenomination, groupHouseDescription, groupHouseDateOfCreation, groupLedgerID);

        // Assert
        assertEquals(expectedGroupDTO, result);
        assertTrue(checkIfContainsTransaction);
    }


}
