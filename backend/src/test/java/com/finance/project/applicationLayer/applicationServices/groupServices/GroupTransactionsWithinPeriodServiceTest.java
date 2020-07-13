package com.finance.project.applicationLayer.applicationServices.groupServices;

import com.finance.project.controllerLayer.integrationTests.AbstractTest;
import com.finance.project.domainLayer.domainEntities.aggregates.group.Group;
import com.finance.project.domainLayer.domainEntities.aggregates.ledger.Ledger;
import com.finance.project.domainLayer.domainEntities.aggregates.ledger.Transaction;
import com.finance.project.domainLayer.domainEntities.vosShared.*;
import com.finance.project.domainLayer.exceptions.InvalidArgumentsBusinessException;
import com.finance.project.domainLayer.exceptions.NotFoundArgumentsBusinessException;
import com.finance.project.domainLayer.repositoriesInterfaces.IGroupRepository;
import com.finance.project.domainLayer.repositoriesInterfaces.ILedgerRepository;
import com.finance.project.dtos.dtosAssemblers.GroupTransactionsWithinPeriodDTOinAssembler;
import com.finance.project.dtos.dtosAssemblers.GroupTransactionsWithinPeriodDTOoutAssembler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import com.finance.project.dtos.dtos.GroupTransactionsWithinPeriodDTOin;
import com.finance.project.dtos.dtos.GroupTransactionsWithinPeriodDTOout;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class GroupTransactionsWithinPeriodServiceTest extends AbstractTest {

    @Mock
    private IGroupRepository groupRepository;
    @Mock
    private ILedgerRepository ledgerRepository;

    private GroupTransactionsWithinPeriodService groupTransactionsWithinPeriodService;

    private Group house;
    private GroupID houseID;
    private Ledger ledger;
    private LedgerID ledgerID;


    @BeforeEach
    public void init() {

        //Paulo
        String pauloEmail = "paulo@gmail.com";
        PersonID pauloPersonID = PersonID.createPersonID(pauloEmail);

        //Maria
        String mariaEmail = "maria@gmail.com";
        PersonID mariaPersonID = PersonID.createPersonID(mariaEmail);

        //Ana
        String anaEmail = "ana@gmail.com";
        PersonID anaPersonID = PersonID.createPersonID(anaEmail);


        //Group House
        //Categories ->     Electricity Expenses
        //Accounts ->       House Wallet / EDP
        //Transactions ->   EDP bill Jan-2020 / EDP bill Feb-2020


        //Ledger
        ledger = Ledger.createLedger();
        ledgerID = ledger.getLedgerID();

        //Group
        String houseDenomination = "House";
        String houseDescription = "People who share house";
        LocalDate houseDateOfCreation = LocalDate.of(2020, 01, 01);

        this.house = Group.createGroupAsPersonInCharge(houseDenomination, pauloPersonID, houseDescription, houseDateOfCreation, ledgerID);
        this.houseID = GroupID.createGroupID(houseDenomination);
        house.addMember(mariaPersonID);
        house.addMember(anaPersonID);

        //Category Electricity Expenses
        String denominationExpenses = "Electricity Expenses";
        CategoryID electricityExpensesID = CategoryID.createCategoryID(denominationExpenses, houseID);
        house.addCategory(electricityExpensesID);

        //Account House Wallet
        String houseWalletDenomination = "House Funds";
        String houseWalletDescription = "Money to spend with House expenses";
        AccountID houseWalletID = AccountID.createAccountID(houseWalletDenomination, houseID);
        house.addAccount(houseWalletID);

        //Account EDP (Group House)
        String edpDenomination = "EDP";
        String edpDescription = "Electricity expenses from EDP";
        AccountID edpID = AccountID.createAccountID(edpDenomination, houseID);
        house.addAccount(edpID);

        //Transaction 1 on Group House
        String edpBillJanuaryType = "Debit";
        String edpBillJanuaryDescription = "EDP bill from January/2020";
        LocalDate edpBillJanuaryDate = LocalDate.of(2020, 02, 05);
        double edpBillJanuaryAmount = 40.00;
        Transaction edpBillJanuary = Transaction.createTransaction(electricityExpensesID, edpBillJanuaryType, edpBillJanuaryDescription, edpBillJanuaryAmount, edpBillJanuaryDate, houseWalletID, edpID);
        ledger.addTransaction(edpBillJanuary);

        //Transaction 2 on Group House
        String edpBillFebruaryType = "Debit";
        String edpBillFebruaryDescription = "EDP bill from February/2020";
        LocalDate edpBillFebruaryDate = LocalDate.of(2020, 03, 05);
        double edpBillFebruaryAmount = 40.00;
        Transaction edpBillFebruary = Transaction.createTransaction(electricityExpensesID, edpBillFebruaryType, edpBillFebruaryDescription, edpBillFebruaryAmount, edpBillFebruaryDate, houseWalletID, edpID);
        ledger.addTransaction(edpBillFebruary);
    }


    // TESTS

    @Test
    @DisplayName("Test for service method getGroupTransactionsWithinPeriod() - Success")
    void getGroupTransactionsWithinPeriod_Success() throws NotFoundArgumentsBusinessException {

        //ARRANGE

        //Dates to search
        LocalDate startDate = LocalDate.of(2020, 01, 15);
        LocalDate endDate = LocalDate.of(2020, 03, 15);

        //Arrange person info - group member
        String personGroupMemberEmail = "paulo@gmail.com";

        //Arrange group info
        String groupDenomination = "House";

        //Arrange category
        String denominationExpenses = "Electricity Expenses";
        CategoryID categoryExpensesID = CategoryID.createCategoryID(denominationExpenses, houseID);

        //Arrange accounts
        //Account House Wallet
        String houseWalletAccountDenomination = "House Funds";
        AccountID houseWalletAccountID = AccountID.createAccountID(houseWalletAccountDenomination, houseID);

        //Account EDP
        String houseEdpAccountDenomination = "EDP";
        AccountID houseEdpID = AccountID.createAccountID(houseEdpAccountDenomination, houseID);

        //Arrange transactions
        //Transaction 1 on Group House
        String typeTransaction1House = "Debit";
        String descriptionTransaction1House = "EDP bill from January/2020";
        LocalDate dateTransaction1House = LocalDate.of(2020, 02, 05);
        double amountTransaction1House = 40.00;
        Transaction houseTransaction1 = Transaction.createTransaction(categoryExpensesID, typeTransaction1House, descriptionTransaction1House, amountTransaction1House, dateTransaction1House, houseWalletAccountID, houseEdpID);

        //Transaction 2 on Group House
        String typeTransaction2House = "Debit";
        String descriptionTransaction2House = "EDP bill from February/2020";
        LocalDate dateTransaction2House = LocalDate.of(2020, 03, 05);
        double amountTransaction2House = 40.00;
        Transaction houseTransaction2 = Transaction.createTransaction(categoryExpensesID, typeTransaction2House, descriptionTransaction2House, amountTransaction2House, dateTransaction2House, houseWalletAccountID, houseEdpID);

        //Arrange DTO in
        GroupTransactionsWithinPeriodDTOin groupTransactionsWithinPeriodDTOin = GroupTransactionsWithinPeriodDTOinAssembler.createGroupTransactionsWithinPeriodDTOin(personGroupMemberEmail, groupDenomination, startDate, endDate);

        //Expected DTO out
        ArrayList<Transaction> expectedTransactions = new ArrayList<>();
        expectedTransactions.add(houseTransaction1);
        expectedTransactions.add(houseTransaction2);

        GroupTransactionsWithinPeriodDTOout expectedGroupTransactionsWithinPeriodDTOout = GroupTransactionsWithinPeriodDTOoutAssembler.getGroupTransactionsWithinPeriodDTOout(expectedTransactions);

        //Mock the behaviour of groupRepository, returning an Optional<Group> house
        Mockito.when(groupRepository.findById(houseID)).thenReturn(Optional.of(house));

        //Mock the behaviour of ledgerRepository, returning a Optional<Ledger> ledger
        Mockito.when(ledgerRepository.findById(ledgerID)).thenReturn(Optional.of(ledger));

        //ACT
        groupTransactionsWithinPeriodService = new GroupTransactionsWithinPeriodService(groupRepository, ledgerRepository);
        GroupTransactionsWithinPeriodDTOout dtoResult = groupTransactionsWithinPeriodService.getGroupTransactionsWithinPeriod(groupTransactionsWithinPeriodDTOin);


        //ASSERT
        assertEquals(expectedGroupTransactionsWithinPeriodDTOout, dtoResult);
    }

    @Test
    @DisplayName("Test for service method getGroupTransactionsWithinPeriod() - orElseThrow - Success")
    void getGroupTransactionsWithinPeriod_orElseThrow_Success() {

        //ARRANGE

        //Dates to search
        LocalDate startDate = LocalDate.of(2020, 01, 15);
        LocalDate endDate = LocalDate.of(2020, 01, 20);

        //Arrange person info - group member
        String personGroupMemberEmail = "paulo@gmail.com";

        //Arrange group info
        String groupDenomination = "House";

        //Arrange DTO in
        GroupTransactionsWithinPeriodDTOin groupTransactionsWithinPeriodDTOin = GroupTransactionsWithinPeriodDTOinAssembler.createGroupTransactionsWithinPeriodDTOin(personGroupMemberEmail, groupDenomination, startDate, endDate);

        //Mock the behaviour of groupRepository, returning an Optional<Group> house
        Mockito.when(groupRepository.findById(houseID)).thenReturn(Optional.of(house));

        //Mock the behaviour of ledgerRepository, returning a Optional<Ledger> ledger
        Mockito.when(ledgerRepository.findById(ledgerID)).thenReturn(Optional.of(ledger));

        //Expected message
        String expectedMessage = "Within the given period, there are no transactions to report";

        //ACT
        groupTransactionsWithinPeriodService = new GroupTransactionsWithinPeriodService(groupRepository, ledgerRepository);
        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () -> groupTransactionsWithinPeriodService.getGroupTransactionsWithinPeriod(groupTransactionsWithinPeriodDTOin));

        //ASSERT
        assertEquals(expectedMessage, thrown.getMessage());
    }

    @Test
    @DisplayName("Test for service method getGroupTransactionsWithinPeriod() - Fail (group does not exist)")
    void getGroupTransactionsWithinPeriod_Fail_GroupDoesNotExist() {

        //ARRANGE

        //Dates to search
        LocalDate startDate = LocalDate.of(2020, 01, 15);
        LocalDate endDate = LocalDate.of(2020, 03, 15);

        //Arrange person info - group member
        String personGroupMemberEmail = "paulo@gmail.com";

        //Arrange group info
        String groupDenomination = "Runners";

        //Arrange DTO in
        GroupTransactionsWithinPeriodDTOin groupTransactionsWithinPeriodDTOin = GroupTransactionsWithinPeriodDTOinAssembler.createGroupTransactionsWithinPeriodDTOin(personGroupMemberEmail, groupDenomination, startDate, endDate);

        //Mock the behaviour of groupRepository, returning an Optional<Group> house
        Mockito.when(groupRepository.findById(houseID)).thenReturn(Optional.of(house));

        //Expected message
        String expectedMessage = "Group does not exist in the system";

        //ACT
        groupTransactionsWithinPeriodService = new GroupTransactionsWithinPeriodService(groupRepository, ledgerRepository);
        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () -> groupTransactionsWithinPeriodService.getGroupTransactionsWithinPeriod(groupTransactionsWithinPeriodDTOin));

        //ASSERT
        assertEquals(expectedMessage, thrown.getMessage());
    }

    @Test
    @DisplayName("Test for service method getGroupTransactionsWithinPeriod() - Fail (person is not a member)")
    void getGroupTransactionsWithinPeriod_Fail_PersonIsNotMember() {

        //ARRANGE

        //Dates to search
        LocalDate startDate = LocalDate.of(2020, 01, 15);
        LocalDate endDate = LocalDate.of(2020, 03, 15);

        //Arrange person info - group member
        String personGroupMemberEmail = "joaquina@gmail.com";

        //Arrange group info
        String groupDenomination = "House";

        //Arrange DTO in
        GroupTransactionsWithinPeriodDTOin groupTransactionsWithinPeriodDTOin = GroupTransactionsWithinPeriodDTOinAssembler.createGroupTransactionsWithinPeriodDTOin(personGroupMemberEmail, groupDenomination, startDate, endDate);

        //Mock the behaviour of groupRepository, returning an Optional<Group> house
        Mockito.when(groupRepository.findById(houseID)).thenReturn(Optional.of(house));

        //Expected message
        String expectedMessage = "Person is not member of the group";

        //ACT
        groupTransactionsWithinPeriodService = new GroupTransactionsWithinPeriodService(groupRepository, ledgerRepository);
        Throwable thrown = assertThrows(InvalidArgumentsBusinessException.class, () -> groupTransactionsWithinPeriodService.getGroupTransactionsWithinPeriod(groupTransactionsWithinPeriodDTOin));

        //ASSERT
        assertEquals(expectedMessage, thrown.getMessage());
    }
}