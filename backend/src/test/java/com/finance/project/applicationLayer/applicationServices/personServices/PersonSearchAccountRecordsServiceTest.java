package com.finance.project.applicationLayer.applicationServices.personServices;

import com.finance.project.controllerLayer.integrationTests.AbstractTest;
import com.finance.project.domainLayer.domainEntities.aggregates.ledger.Ledger;
import com.finance.project.domainLayer.domainEntities.aggregates.ledger.Transaction;
import com.finance.project.domainLayer.domainEntities.aggregates.person.Address;
import com.finance.project.domainLayer.domainEntities.aggregates.person.Person;
import com.finance.project.domainLayer.domainEntities.vosShared.AccountID;
import com.finance.project.domainLayer.domainEntities.vosShared.CategoryID;
import com.finance.project.domainLayer.domainEntities.vosShared.LedgerID;
import com.finance.project.domainLayer.domainEntities.vosShared.PersonID;
import com.finance.project.domainLayer.exceptions.InvalidArgumentsBusinessException;
import com.finance.project.domainLayer.exceptions.NotFoundArgumentsBusinessException;
import com.finance.project.domainLayer.repositoriesInterfaces.IAccountRepository;
import com.finance.project.domainLayer.repositoriesInterfaces.ILedgerRepository;
import com.finance.project.domainLayer.repositoriesInterfaces.IPersonRepository;
import com.finance.project.dtos.dtos.PersonSearchAccountRecordsInDTO;
import com.finance.project.dtos.dtos.PersonSearchAccountRecordsOutDTO;
import com.finance.project.dtos.dtosAssemblers.PersonSearchAccountRecordsInDTOAssembler;
import com.finance.project.dtos.dtosAssemblers.SearchAccountRecordsOutDTOAssembler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class PersonSearchAccountRecordsServiceTest extends AbstractTest {

    @Mock
    private IPersonRepository personRepository;
    @Mock
    private IAccountRepository accountRepository;
    @Mock
    private ILedgerRepository ledgerRepository;

    private PersonSearchAccountRecordsService personSearchAccountRecordsService;

    private Person person;
    private PersonID personID;
    private Ledger ledger;
    private LedgerID ledgerID;


    @BeforeEach
    public void init() {

        // Ledger
        ledger = Ledger.createLedger();
        ledgerID = ledger.getLedgerID();

        // Person
        String personEmail = "joaquina@gmail.com";
        String personName = "Joaquina Maria";
        LocalDate personBirthdate = LocalDate.of(1980, 05, 25);
        String personBirthplace = "Coimbra";

        String street = "Rua do Alem";
        String doorNumber = "12";
        String postCode = "1234-123";
        String city = "Porto";
        String country = "Portugal";
        Address address = Address.createAddress(street, doorNumber, postCode, city, country);

        person = Person.createPersonWithoutParents(personEmail, personName, personBirthdate, personBirthplace, address, ledgerID);
        personID = person.getPersonID();

        //Category Electricity Expenses
        String denominationCatEDP = "Electricity Expenses";
        CategoryID categoryEdpID = CategoryID.createCategoryID(denominationCatEDP, personID);
        person.addCategory(categoryEdpID);

        //Category Water Expenses
        String denominationCatWater = "Water Expenses";
        CategoryID categoryWaterID = CategoryID.createCategoryID(denominationCatWater, personID);
        person.addCategory(categoryWaterID);

        //Account House Wallet
        String walletAccountDenomination = "House Wallet Funds";
        String walletAccountDescription = "Money to spend with house expenses";
        AccountID walletAccountID = AccountID.createAccountID(walletAccountDenomination, personID);
        person.addAccount(walletAccountID);

        //Account EDP
        String edpAccountDenomination = "EDP";
        String edpAccountDescription = "Electricity expenses from EDP";
        AccountID edpAccountID = AccountID.createAccountID(edpAccountDenomination, personID);
        person.addAccount(edpAccountID);

        //Account Water
        String waterAccountDenomination = "Water";
        String waterAccountDescription = "Water expenses from AdP";
        AccountID waterAccountID = AccountID.createAccountID(waterAccountDenomination, personID);
        person.addAccount(waterAccountID);

        //Transaction 1 - EDP - Debit
        String typeTransaction1 = "Debit";
        String descriptionTransaction1 = "EDP bill from January/2020";
        LocalDate dateTransaction1 = LocalDate.of(2020, 02, 03);
        double amountTransaction1 = 40.00;
        Transaction transaction1 = Transaction.createTransaction(categoryEdpID, typeTransaction1, descriptionTransaction1, amountTransaction1, dateTransaction1, walletAccountID, edpAccountID);
        ledger.addTransaction(transaction1);

        //Transaction 2 - EDP - Debit
        String typeTransaction2 = "Debit";
        String descriptionTransaction2 = "EDP bill from February/2020";
        LocalDate dateTransaction2 = LocalDate.of(2020, 03, 03);
        double amountTransaction2 = 45.00;
        Transaction transaction2 = Transaction.createTransaction(categoryEdpID, typeTransaction2, descriptionTransaction2, amountTransaction2, dateTransaction2, walletAccountID, edpAccountID);
        ledger.addTransaction(transaction2);

        //Transaction 3 - EDP - Credit
        String typeTransaction3 = "Credit";
        String descriptionTransaction3 = "EDP bill from March/2020 - settlement - overcharge";
        LocalDate dateTransaction3 = LocalDate.of(2020, 04, 03);
        double amountTransaction3 = 15.00;
        Transaction transaction3 = Transaction.createTransaction(categoryEdpID, typeTransaction3, descriptionTransaction3, amountTransaction3, dateTransaction3, edpAccountID, walletAccountID);
        ledger.addTransaction(transaction3);

        //Transaction 4 - Water - Debit
        String typeTransaction4 = "Debit";
        String descriptionTransaction4 = "Water bill from January/2020";
        LocalDate dateTransaction4 = LocalDate.of(2020, 02, 01);
        double amountTransaction4 = 45.00;
        Transaction transaction4 = Transaction.createTransaction(categoryWaterID, typeTransaction4, descriptionTransaction4, amountTransaction4, dateTransaction4, walletAccountID, waterAccountID);
        ledger.addTransaction(transaction4);

        //Transaction 5 - Water - Debit
        String typeTransaction5 = "Debit";
        String descriptionTransaction5 = "Water bill from February/2020";
        LocalDate dateTransaction5 = LocalDate.of(2020, 03, 01);
        double amountTransaction5 = 45.00;
        Transaction transaction5 = Transaction.createTransaction(categoryWaterID, typeTransaction5, descriptionTransaction5, amountTransaction5, dateTransaction5, walletAccountID, waterAccountID);
        ledger.addTransaction(transaction5);
    }


    // Tests

    @Test
    @DisplayName("Test for service method getPersonAccountTransactionsWithinPeriod() - Success")
    void getAccountTransactionsWithinPeriod_success() {

        //ARRANGE
        //Person to search
        String personEmail = "joaquina@gmail.com";

        //Account to search - EDP
        String accountDenomination = "EDP";

        //Dates to search
        String startDate = "2020-03-03";
        String endDate = "2020-04-03";

        //Arrange category and accounts for expected transactions
        //Category Electricity Expenses
        String denominationCategoryEDP = "Electricity Expenses";
        CategoryID categoryIDEdp = CategoryID.createCategoryID(denominationCategoryEDP, PersonID.createPersonID(personEmail));

        //Account EDP
        AccountID accountIDEdp = AccountID.createAccountID(accountDenomination, PersonID.createPersonID(personEmail));

        //Account Wallet - House expenses
        String walletAccountDenomination = "House Wallet Funds";
        AccountID accountIDWallet = AccountID.createAccountID(walletAccountDenomination, PersonID.createPersonID(personEmail));

        //Arrange expected transactions
        //Transaction 2 - EDP - Debit
        String typeTransaction2 = "Debit";
        String descriptionTransaction2 = "EDP bill from February/2020";
        LocalDate dateTransaction2 = LocalDate.of(2020, 03, 03);
        double amountTransaction2 = 45.00;
        Transaction transaction2 = Transaction.createTransaction(categoryIDEdp, typeTransaction2, descriptionTransaction2, amountTransaction2, dateTransaction2, accountIDWallet, accountIDEdp);

        //Transaction 3 - EDP - Credit
        String typeTransaction3 = "Credit";
        String descriptionTransaction3 = "EDP bill from March/2020 - settlement - overcharge";
        LocalDate dateTransaction3 = LocalDate.of(2020, 04, 03);
        double amountTransaction3 = 15.00;
        Transaction transaction3 = Transaction.createTransaction(categoryIDEdp, typeTransaction3, descriptionTransaction3, amountTransaction3, dateTransaction3, accountIDEdp, accountIDWallet);

        //Arrange DTO in
        PersonSearchAccountRecordsInDTO dtoIn = PersonSearchAccountRecordsInDTOAssembler.personAccountTransactionsInDTO(personEmail, accountDenomination, startDate, endDate);

        //Expected DTO out
        ArrayList<Transaction> expectedTransactions = new ArrayList<>();
        expectedTransactions.add(transaction2);
        expectedTransactions.add(transaction3);

        PersonSearchAccountRecordsOutDTO expectedDTOout = SearchAccountRecordsOutDTOAssembler.accountTransactionsOutDTO(expectedTransactions);

        //Mock the behaviour of personRepository, returning person
        Mockito.when(personRepository.findById(personID)).thenReturn(Optional.of(person));

        //Mock the behaviour of accountRepository, returning account
        Mockito.when(accountRepository.existsById(accountIDEdp)).thenReturn(true);

        //Mock the behaviour of ledgerRepository, returning a Optional<Ledger> ledger
        Mockito.when(ledgerRepository.findById(ledgerID)).thenReturn(Optional.of(ledger));


        //ACT
        personSearchAccountRecordsService = new PersonSearchAccountRecordsService(personRepository, accountRepository, ledgerRepository);
        PersonSearchAccountRecordsOutDTO result = personSearchAccountRecordsService.getPersonAccountTransactionsWithinPeriod(dtoIn);


        //ASSERT
        assertEquals(expectedDTOout, result);
    }

    @Test
    @DisplayName("Test for service method getPersonAccountTransactionsWithinPeriod() - Exception - No transactions to report")
    void getAccountTransactionsWithinPeriod_exception_noTransactionsToReport() {
        //ARRANGE
        //Person to search
        String personEmail = "joaquina@gmail.com";

        //Account to search - EDP
        String accountDenomination = "EDP";

        //Dates to search
        String startDate = "2020-02-04";
        String endDate = "2020-03-02";

        //Arrange DTO in
        PersonSearchAccountRecordsInDTO dtoIn = PersonSearchAccountRecordsInDTOAssembler.personAccountTransactionsInDTO(personEmail, accountDenomination, startDate, endDate);

        //Mock the behaviour of personRepository, returning person
        Mockito.when(personRepository.findById(personID)).thenReturn(Optional.of(person));

        //Mock the behaviour of accountRepository, returning account
        Mockito.when(accountRepository.existsById(AccountID.createAccountID(accountDenomination, PersonID.createPersonID(personEmail)))).thenReturn(true);

        //Mock the behaviour of ledgerRepository, returning a Optional<Ledger> ledger
        Mockito.when(ledgerRepository.findById(ledgerID)).thenReturn(Optional.of(ledger));

        //Expected message
        String expectedMessage = "Ledger has no transactions within the searched period";


        //ACT
        personSearchAccountRecordsService = new PersonSearchAccountRecordsService(personRepository, accountRepository, ledgerRepository);
        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () -> personSearchAccountRecordsService.getPersonAccountTransactionsWithinPeriod(dtoIn));


        //ASSERT
        assertEquals(expectedMessage, thrown.getMessage());
    }

    @Test
    @DisplayName("Test for service method getPersonAccountTransactionsWithinPeriod() - Fail - Person does not exist")
    void getAccountTransactionsWithinPeriod_fail_personDoesNotExist() {
        //ARRANGE
        //Person to search
        String personEmail = "isolina@gmail.com";

        //Account to search - EDP
        String accountDenomination = "EDP";

        //Dates to search
        String startDate = "2020-03-01";
        String endDate = "2020-04-03";

        //Arrange DTO in
        PersonSearchAccountRecordsInDTO dtoIn = PersonSearchAccountRecordsInDTOAssembler.personAccountTransactionsInDTO(personEmail, accountDenomination, startDate, endDate);

        //Mock the behaviour of personRepository, returning person
        Mockito.when(personRepository.findById(personID)).thenReturn(Optional.of(person));

        //Expected message
        String expectedMessage = "Person does not exist in the system";


        //ACT
        personSearchAccountRecordsService = new PersonSearchAccountRecordsService(personRepository, accountRepository, ledgerRepository);
        Throwable thrown = assertThrows(InvalidArgumentsBusinessException.class, () -> personSearchAccountRecordsService.getPersonAccountTransactionsWithinPeriod(dtoIn));


        //ASSERT
        assertEquals(expectedMessage, thrown.getMessage());
    }

    @Test
    @DisplayName("Test for service method getPersonAccountTransactionsWithinPeriod() - Fail - Account does not exist")
    void getAccountTransactionsWithinPeriod_fail_accountDoesNotExist() {
        //ARRANGE
        //Person to search
        String personEmail = "joaquina@gmail.com";

        //Account to search - EDP
        String accountDenomination = "Fast food";

        //Dates to search
        String startDate = "2020-03-01";
        String endDate = "2020-04-03";

        //Arrange DTO in
        PersonSearchAccountRecordsInDTO dtoIn = PersonSearchAccountRecordsInDTOAssembler.personAccountTransactionsInDTO(personEmail, accountDenomination, startDate, endDate);

        //Mock the behaviour of personRepository, returning person
        Mockito.when(personRepository.findById(personID)).thenReturn(Optional.of(person));

        //Mock the behaviour of accountRepository, returning account
        Mockito.when(accountRepository.existsById(AccountID.createAccountID(accountDenomination, PersonID.createPersonID(personEmail)))).thenReturn(false);

        //Mock the behaviour of ledgerRepository, returning a Optional<Ledger> ledger
        Mockito.when(ledgerRepository.findById(ledgerID)).thenReturn(Optional.of(ledger));

        //Expected message
        String expectedMessage = "Account does not exist in the system";


        //ACT
        personSearchAccountRecordsService = new PersonSearchAccountRecordsService(personRepository, accountRepository, ledgerRepository);
        Throwable thrown = assertThrows(InvalidArgumentsBusinessException.class, () -> personSearchAccountRecordsService.getPersonAccountTransactionsWithinPeriod(dtoIn));


        //ASSERT
        assertEquals(expectedMessage, thrown.getMessage());
    }

    @Test
    @DisplayName("Test for service method getPersonAccountTransactionsWithinPeriod() - Fail - Search dates in reverse order")
    void getAccountTransactionsWithinPeriod_fail_startDateAfterEndDate() {
        //ARRANGE
        //Person to search
        String personEmail = "joaquina@gmail.com";

        //Account to search - EDP
        String accountDenomination = "EDP";

        //Dates to search
        String startDate = "2020-05-01";
        String endDate = "2020-04-03";

        //Arrange DTO in
        PersonSearchAccountRecordsInDTO dtoIn = PersonSearchAccountRecordsInDTOAssembler.personAccountTransactionsInDTO(personEmail, accountDenomination, startDate, endDate);

        //Mock the behaviour of personRepository, returning person
        Mockito.when(personRepository.findById(personID)).thenReturn(Optional.of(person));

        //Mock the behaviour of accountRepository, returning account
        Mockito.when(accountRepository.existsById(AccountID.createAccountID(accountDenomination, PersonID.createPersonID(personEmail)))).thenReturn(true);

        //Mock the behaviour of ledgerRepository, returning a Optional<Ledger> ledger
        Mockito.when(ledgerRepository.findById(ledgerID)).thenReturn(Optional.of(ledger));

        //Expected message
        String expectedMessage = "Check the start and end dates for the period, since start date cannot be later than end date";


        //ACT
        personSearchAccountRecordsService = new PersonSearchAccountRecordsService(personRepository, accountRepository, ledgerRepository);
        Throwable thrown = assertThrows(InvalidArgumentsBusinessException.class, () -> personSearchAccountRecordsService.getPersonAccountTransactionsWithinPeriod(dtoIn));


        //ASSERT
        assertEquals(expectedMessage, thrown.getMessage());
    }

    @Test
    @DisplayName("Test for service method getPersonAccountTransactionsWithinPeriod() - Exception - Empty ledger")
    void getAccountTransactionsWithinPeriod_exception_emptyLedger() {
        //ARRANGE
        //Person to search
        String personEmail = "joaquina@gmail.com";

        //Account to search - EDP
        String accountDenomination = "EDP";

        //Dates to search
        String startDate = "2020-01-01";
        String endDate = "2020-04-03";

        //Arrange DTO in
        PersonSearchAccountRecordsInDTO dtoIn = PersonSearchAccountRecordsInDTOAssembler.personAccountTransactionsInDTO(personEmail, accountDenomination, startDate, endDate);

        //Mock the behaviour of personRepository, returning person
        Mockito.when(personRepository.findById(personID)).thenReturn(Optional.of(person));

        //Mock the behaviour of accountRepository, returning account
        Mockito.when(accountRepository.existsById(AccountID.createAccountID(accountDenomination, PersonID.createPersonID(personEmail)))).thenReturn(true);

        //Mock the behaviour of ledgerRepository, returning a Optional<Ledger> ledger
        Mockito.when(ledgerRepository.findById(ledgerID)).thenReturn(Optional.of(Ledger.createLedger()));

        //Expected message
        String expectedMessage = "Ledger is empty";


        //ACT
        personSearchAccountRecordsService = new PersonSearchAccountRecordsService(personRepository, accountRepository, ledgerRepository);
        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () -> personSearchAccountRecordsService.getPersonAccountTransactionsWithinPeriod(dtoIn));


        //ASSERT
        assertEquals(expectedMessage, thrown.getMessage());
    }

    @Test
    @DisplayName("Test for service method getPersonAccountTransactionsWithinPeriod() - Exception - Search period before ledger records time range")
    void getAccountTransactionsWithinPeriod_exception_searchDatesBeforeLedgerRecords() {
        //ARRANGE
        //Person to search
        String personEmail = "joaquina@gmail.com";

        //Account to search - EDP
        String accountDenomination = "EDP";

        //Dates to search
        String startDate = "2019-02-04";
        String endDate = "2019-03-02";

        //Arrange DTO in
        PersonSearchAccountRecordsInDTO dtoIn = PersonSearchAccountRecordsInDTOAssembler.personAccountTransactionsInDTO(personEmail, accountDenomination, startDate, endDate);

        //Mock the behaviour of personRepository, returning person
        Mockito.when(personRepository.findById(personID)).thenReturn(Optional.of(person));

        //Mock the behaviour of accountRepository, returning account
        Mockito.when(accountRepository.existsById(AccountID.createAccountID(accountDenomination, PersonID.createPersonID(personEmail)))).thenReturn(true);

        //Mock the behaviour of ledgerRepository, returning a Optional<Ledger> ledger
        Mockito.when(ledgerRepository.findById(ledgerID)).thenReturn(Optional.of(ledger));

        //Expected message
        String expectedMessage = "The time period provided falls outside the range of the ledger records";


        //ACT
        personSearchAccountRecordsService = new PersonSearchAccountRecordsService(personRepository, accountRepository, ledgerRepository);
        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () -> personSearchAccountRecordsService.getPersonAccountTransactionsWithinPeriod(dtoIn));


        //ASSERT
        assertEquals(expectedMessage, thrown.getMessage());
    }

    @Test
    @DisplayName("Test for service method getPersonAccountTransactionsWithinPeriod() - Exception - Search period after ledger records time range")
    void getAccountTransactionsWithinPeriod_exception_searchDatesAfterLedgerRecords() {
        //ARRANGE
        //Person to search
        String personEmail = "joaquina@gmail.com";

        //Account to search - EDP
        String accountDenomination = "EDP";

        //Dates to search
        String startDate = "2019-02-04";
        String endDate = "2019-03-02";

        //Arrange DTO in
        PersonSearchAccountRecordsInDTO dtoIn = PersonSearchAccountRecordsInDTOAssembler.personAccountTransactionsInDTO(personEmail, accountDenomination, startDate, endDate);

        //Mock the behaviour of personRepository, returning person
        Mockito.when(personRepository.findById(personID)).thenReturn(Optional.of(person));

        //Mock the behaviour of accountRepository, returning account
        Mockito.when(accountRepository.existsById(AccountID.createAccountID(accountDenomination, PersonID.createPersonID(personEmail)))).thenReturn(true);

        //Mock the behaviour of ledgerRepository, returning a Optional<Ledger> ledger
        Mockito.when(ledgerRepository.findById(ledgerID)).thenReturn(Optional.of(ledger));

        //Expected message
        String expectedMessage = "The time period provided falls outside the range of the ledger records";


        //ACT
        personSearchAccountRecordsService = new PersonSearchAccountRecordsService(personRepository, accountRepository, ledgerRepository);
        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () -> personSearchAccountRecordsService.getPersonAccountTransactionsWithinPeriod(dtoIn));


        //ASSERT
        assertEquals(expectedMessage, thrown.getMessage());
    }

    @Test
    @DisplayName("Test for service method getAccountID() - Exception - Account name missing")
    void getAccountID_exception_accountNameMissing() {
        //ARRANGE
        //Person to search
        String personEmail = "joaquina@gmail.com";

        //Account to search - EDP
        String accountDenomination = "";

        //Dates to search
        String startDate = "2019-02-04";
        String endDate = "2019-03-02";

        //Arrange DTO in
        PersonSearchAccountRecordsInDTO dtoIn = PersonSearchAccountRecordsInDTOAssembler.personAccountTransactionsInDTO(personEmail, accountDenomination, startDate, endDate);

        //Expected message
        String expectedMessage = "Search results cannot be displayed: account name is missing";

        //ACT
        personSearchAccountRecordsService = new PersonSearchAccountRecordsService(personRepository, accountRepository, ledgerRepository);
        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () -> personSearchAccountRecordsService.getAccountID(dtoIn));

        //ASSERT
        assertEquals(expectedMessage, thrown.getMessage());
    }


    @Test
    @DisplayName("Test for service method getStartDate() - Exception - Start date missing")
    void getStartDate_exception_startDateMissing() {
        //ARRANGE
        //Person to search
        String personEmail = "joaquina@gmail.com";

        //Account to search - EDP
        String accountDenomination = "EDP";

        //Dates to search
        String startDate = "";
        String endDate = "2019-03-02";

        //Arrange DTO in
        PersonSearchAccountRecordsInDTO dtoIn = PersonSearchAccountRecordsInDTOAssembler.personAccountTransactionsInDTO(personEmail, accountDenomination, startDate, endDate);

        //Expected message
        String expectedMessage = "Search results cannot be displayed: start date is missing";

        //ACT
        personSearchAccountRecordsService = new PersonSearchAccountRecordsService(personRepository, accountRepository, ledgerRepository);
        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () -> personSearchAccountRecordsService.getStartDate(dtoIn));

        //ASSERT
        assertEquals(expectedMessage, thrown.getMessage());
    }


    @Test
    @DisplayName("Test for service method getEndDate() - Exception - End date missing")
    void getEndDate_exception_endDateMissing() {
        //ARRANGE
        //Person to search
        String personEmail = "joaquina@gmail.com";

        //Account to search - EDP
        String accountDenomination = "EDP";

        //Dates to search
        String startDate = "2019-02-04";
        String endDate = "";

        //Arrange DTO in
        PersonSearchAccountRecordsInDTO dtoIn = PersonSearchAccountRecordsInDTOAssembler.personAccountTransactionsInDTO(personEmail, accountDenomination, startDate, endDate);

        //Expected message
        String expectedMessage = "Search results cannot be displayed: end date is missing";

        //ACT
        personSearchAccountRecordsService = new PersonSearchAccountRecordsService(personRepository, accountRepository, ledgerRepository);
        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () -> personSearchAccountRecordsService.getEndDate(dtoIn));

        //ASSERT
        assertEquals(expectedMessage, thrown.getMessage());
    }

}