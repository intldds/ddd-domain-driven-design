package com.finance.project.dataModel.dataAssemblers;

import com.finance.project.controllerLayer.integrationTests.AbstractTest;
import com.finance.project.dataModel.dataModel.*;
import com.finance.project.persistenceLayer.repositoriesJPA.AccountJpaRepository;
import com.finance.project.persistenceLayer.repositoriesJPA.GroupJpaRepository;
import com.finance.project.persistenceLayer.repositoriesJPA.LedgerJpaRepository;
import com.finance.project.domainLayer.domainEntities.aggregates.group.Group;
import com.finance.project.domainLayer.domainEntities.aggregates.ledger.Ledger;
import com.finance.project.domainLayer.domainEntities.aggregates.ledger.Transaction;
import com.finance.project.domainLayer.domainEntities.vosShared.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


class GroupDomainDataAssemblerTest extends AbstractTest {

    @Mock
    private GroupJpaRepository groupJpaRepository;

    @Mock
    private AccountJpaRepository accountJpaRepository;

    @Mock
    private LedgerJpaRepository ledgerJpaRepository;

    private GroupDomainDataAssembler groupDomainDataAssembler;

    private Group sundayRunners;
    private GroupID sundayRunnersID;
    private Ledger ledger;
    private LedgerID ledgerID;

    private GroupJpa sundayRunnersJpa;
    private AccountJpa companyAccountJpa;
    private AccountJpa bankAccountJpa;
    private LedgerJpa ledgerJpa;

    @BeforeEach
    public void init() {

        //Group Domain

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

        //Group JPA

        //Date of creation

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        //SundayRunnersJpa

        this.sundayRunnersJpa = new GroupJpa(sundayRunnersDenomination, sundayRunnersDescription, dateOfCreation.format(formatter));

        //Ledger

        this.ledgerJpa = new LedgerJpa(ledgerID);
        this.sundayRunnersJpa.setLedger(ledgerJpa);

        //Admins

        this.sundayRunnersJpa.addAdministrator(pauloPersonID);

        //Members

        this.sundayRunnersJpa.addMember(ritaPersonID);
        this.sundayRunnersJpa.addMember(henriquePersonID);

        //Accounts

        this.companyAccountJpa = new AccountJpa(sundayRunnersDenomination, companyDenomination, companyDescription);
        this.bankAccountJpa = new AccountJpa(sundayRunnersDenomination, bankAccountDenomination, bankAccountDescription);

        List<AccountJpa> accountJpaList = new ArrayList<>();
        accountJpaList.add(companyAccountJpa);
        accountJpaList.add(bankAccountJpa);

        this.sundayRunnersJpa.setAccounts(accountJpaList);


        //Categories

        CategoryJpa categoryJpa = new CategoryJpa(sundayRunnersDenomination, salaryDenomination);

        List<CategoryJpa> categoryJpaList = new ArrayList<>();
        categoryJpaList.add(categoryJpa);

        this.sundayRunnersJpa.setCategories(categoryJpaList);

        //Transaction

        TransactionJpa salaryJanuaryJpa = new TransactionJpa(categoryJpa, credit, salaryJanuaryDescription, salaryJanuaryAmount, salaryJanuaryDate.format(formatter), companyAccountJpa, bankAccountJpa, ledgerJpa);

        List<TransactionJpa> transactionJpaList = new ArrayList<>();
        transactionJpaList.add(salaryJanuaryJpa);
    }


    @Test
    @DisplayName("GroupDomainDataAssembler - Test transform GroupJpa to domain")
    void groupDomainDataAssemblerTest_toDomain() {


        //Arrange

        String denomination = "Sunday Runners";
        String description = "All members from Sunday Runners group";
        String dateOfCreation = LocalDate.of(2020, 06, 01).toString();

        GroupID groupID = GroupID.createGroupID(denomination);

        List<PersonID> admins = sundayRunners.getPeopleInCharge();
        List<PersonID> members = sundayRunners.getMembers();

        List<AccountID> accountIDS = sundayRunners.getAccounts();

        List<CategoryID> categoryIDS = sundayRunners.getCategories();


        //Act

        GroupDomainDataAssembler groupDomainDataAssembler = new GroupDomainDataAssembler(groupJpaRepository, ledgerJpaRepository, accountJpaRepository);
        Group groupResulted = groupDomainDataAssembler.toDomain(sundayRunnersJpa);

        //Assert

        Assertions.assertEquals(groupID, groupResulted.getGroupID());
        Assertions.assertEquals(description, groupResulted.getDescription().getDescription());
        Assertions.assertEquals(dateOfCreation, groupResulted.getDateOfCreation().getDateOfCreation().toString());
        assertEquals(admins, groupResulted.getPeopleInCharge());
        assertEquals(members, groupResulted.getMembers());
        assertEquals(accountIDS, groupResulted.getAccounts());
        assertEquals(categoryIDS, groupResulted.getCategories());
        Assertions.assertEquals(ledgerID, groupResulted.getLedgerID());
    }


    @Test
    @DisplayName("GroupDomainDataAssembler - Test transform Group to data")
    void groupDomainDataAssemblerTest_toData() {

        String groupDenomination = "Sunday Runners";
        String groupDescription = "All members from Sunday Runners group";
        String groupDateOfCreation = LocalDate.of(2020, 06, 01).toString();

        String companyDenomination = "Company";

        String bankAccountDenomination = "Bank Account";

        AbstractIdJpa companyAccountAbstractIdJpa = new AbstractIdJpa(groupDenomination, companyDenomination);
        AbstractIdJpa bankAccountAbstractIdJpa = new AbstractIdJpa(groupDenomination, bankAccountDenomination);

        //Admins

        List<AdminJpa> admins = sundayRunnersJpa.getAdministrators();

        //Members

        List<MemberJpa> members = sundayRunnersJpa.getMembers();

        //Accounts

        List<AccountJpa> accountJpas = sundayRunnersJpa.getAccounts();

        // Categories

        List<CategoryJpa> categoryJpas = sundayRunnersJpa.getCategories();

        //Returning an Optional<Group> Sunday Runners
        Mockito.when(groupJpaRepository.findById(sundayRunnersID)).thenReturn(Optional.of(sundayRunnersJpa));

        //Returning True (Account exist)
        Mockito.when(accountJpaRepository.findById(companyAccountAbstractIdJpa)).thenReturn(Optional.of(companyAccountJpa));

        //Returning True (Account exist)
        Mockito.when(accountJpaRepository.findById(bankAccountAbstractIdJpa)).thenReturn(Optional.of(bankAccountJpa));

        //Returning an Optional<Ledger> Sunday Runners
        Mockito.when(ledgerJpaRepository.findById(ledgerID)).thenReturn(Optional.of(ledgerJpa));

        groupDomainDataAssembler = new GroupDomainDataAssembler(groupJpaRepository, ledgerJpaRepository, accountJpaRepository);
        GroupJpa groupJpaResulted = groupDomainDataAssembler.toData(sundayRunners);


        Assertions.assertEquals(groupDenomination, groupJpaResulted.getId().getDenomination().getDenomination());
        assertEquals(groupDescription, groupJpaResulted.getDescription());
        assertEquals(groupDateOfCreation, groupJpaResulted.getDateOfCreation());
        assertEquals(admins, groupJpaResulted.getAdministrators());
        assertEquals(members, groupJpaResulted.getMembers());
        assertEquals(accountJpas, groupJpaResulted.getAccounts());
        assertEquals(ledgerJpa, groupJpaResulted.getLedger());

    }

}