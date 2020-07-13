package com.finance.project.applicationLayer.applicationServices.personServices;

import com.finance.project.applicationLayer.applicationServices.personServices.CreatePersonService;
import com.finance.project.controllerLayer.integrationTests.AbstractTest;
import com.finance.project.domainLayer.domainEntities.aggregates.account.Account;
import com.finance.project.domainLayer.domainEntities.aggregates.category.Category;
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
import com.finance.project.dtos.dtos.*;
import com.finance.project.dtos.dtosAssemblers.*;
import com.finance.project.infrastructureLayer.repositories.AccountRepository;
import com.finance.project.infrastructureLayer.repositories.CategoryRepository;
import com.finance.project.infrastructureLayer.repositories.LedgerRepository;
import com.finance.project.infrastructureLayer.repositories.PersonRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


public class CreatePersonServiceTest extends AbstractTest {

    @Mock
    private PersonRepository personRepository;
    @Mock
    private LedgerRepository ledgerRepository;
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private AccountRepository accountRepository;

    // Tests

    // Success

    @Test
    @DisplayName("create person | happy path")
    public void createPerson() {

        CreatePersonService createPersonService = new CreatePersonService(personRepository, ledgerRepository, categoryRepository, accountRepository);

        // Arrange
        String emailMaria = "maria@gmail.com";
        String nameMaria = "Maria";
        LocalDate birthdateMaria = LocalDate.of(1000, 10, 5);
        String birthplaceMaria = "Gaia";

        // DTO
        CreatePersonDTO createPersonDTO = CreatePersonDTOAssembler.createDTOFromPrimitiveTypes(emailMaria, nameMaria, birthdateMaria.toString(), birthplaceMaria);
        Person personMaria = Person.createPerson(emailMaria, nameMaria, birthdateMaria, birthplaceMaria);

        // Mock the behaviour of the service's personRepository findPersonByID method, so it does not depend on other parts (e.g. DB)
        Mockito.when(personRepository.findById(personMaria.getPersonID())).thenReturn(Optional.empty());

        // Mock the behaviour of the service's personRepository findPersonByID method, so it does not depend on other parts (e.g. DB)
        Mockito.when(personRepository.save(personMaria)).thenReturn(personMaria);

        // Act
        PersonDTO expectedPersonDTO = PersonDTOAssembler.createDTOFromDomainObject(personMaria.getPersonID().getEmail(), personMaria.getName(), personMaria.getBirthdate(), personMaria.getBirthplace(), personMaria.getFather(), personMaria.getMother());

        PersonDTO personDTO = createPersonService.createPerson(createPersonDTO);

        //Assert
        assertEquals(expectedPersonDTO, personDTO);
    }

    // Person already exists - exception

    @Test
    @DisplayName("Create person - person already exists)")
    public void createPersonAlreadyExists() throws InvalidArgumentsBusinessException {

        CreatePersonService createPersonService = new CreatePersonService(personRepository, ledgerRepository, categoryRepository, accountRepository);

        // Arrange
        String emailMaria = "maria@gmail.com";
        String nameMaria = "Maria";
        LocalDate birthdateMaria = LocalDate.of(1000, 10, 5);
        String birthplaceMaria = "Gaia";

        // DTO
        CreatePersonDTO createPersonDTO = CreatePersonDTOAssembler.createDTOFromPrimitiveTypes(emailMaria, nameMaria, birthdateMaria.toString(), birthplaceMaria);
        Person personMaria = Person.createPerson(emailMaria, nameMaria, birthdateMaria, birthplaceMaria);

        // Act
        // Mock the behaviour of the service's personRepository findPersonByID method, so it does not depend on other parts (e.g. DB)
        Mockito.when(personRepository.findById(personMaria.getPersonID())).thenReturn(Optional.of(personMaria));


        // Act expected object
        Throwable thrown = assertThrows(InvalidArgumentsBusinessException.class, () -> createPersonService.createPerson(createPersonDTO));

        // Assert
        assertEquals(thrown.getMessage(), CreatePersonService.PERSON_ALREADY_EXIST);
    }

    // Get Person by e-mail

    @Test
    @DisplayName("getPersonByEmail | Success | Happy Path")
    public void getPersonByEmail_HappyPath() {


        CreatePersonService createPersonService = new CreatePersonService(personRepository, ledgerRepository, categoryRepository, accountRepository);

        // Arrange
        String emailMaria = "maria@gmail.com";
        String nameMaria = "Maria";
        LocalDate birthdateMaria = LocalDate.of(1000, 10, 5);
        String birthplaceMaria = "Gaia";

        Person personMaria = Person.createPerson(emailMaria, nameMaria, birthdateMaria, birthplaceMaria);

        // DTO
        PersonEmailDTO personEmailDTOMaria = new PersonEmailDTO(personMaria.getEmail().getEmail());

        // Mock the behaviour of the service's personRepository findPersonByID method, so it does not depend on other parts (e.g. DB)
        Mockito.when(personRepository.findById(personMaria.getPersonID())).thenReturn(Optional.of(personMaria));

        // Act
        PersonDTO expectedPersonDTO = PersonDTOAssembler.createDTOFromDomainObject
                        (personMaria.getPersonID().getEmail(),
                        personMaria.getName(),
                        personMaria.getBirthdate(),
                        personMaria.getBirthplace(),
                        personMaria.getFather(),
                        personMaria.getMother());

        PersonDTO personDTO = createPersonService.getPersonByEmail(personEmailDTOMaria);

        //Assert
        assertEquals(expectedPersonDTO, personDTO);

    }


    @Test
    @DisplayName("getPersonByEmail | Person Does Not Exist")
    public void getPersonByEmail_PersonNotExist() {

        CreatePersonService createPersonService = new CreatePersonService(personRepository, ledgerRepository, categoryRepository, accountRepository);

        // Arrange
        String emailMaria = "maria@gmail.com";
        String nameMaria = "Maria";
        LocalDate birthdateMaria = LocalDate.of(1000, 10, 5);
        String birthplaceMaria = "Gaia";

        Person personMaria = Person.createPerson(emailMaria, nameMaria, birthdateMaria, birthplaceMaria);

        PersonEmailDTO personEmailDTO = new PersonEmailDTO(emailMaria);

        // Mock the behaviour of the service's personRepository findPersonByID method, so it does not depend on other parts (e.g. DB)
        Mockito.when(personRepository.findById(personMaria.getPersonID())).thenReturn(Optional.empty());

        // Act expected object

        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () -> createPersonService.getPersonByEmail(personEmailDTO));

        // Assert
        assertEquals(thrown.getMessage(), CreatePersonService.PERSON_DOES_NOT_EXIST);
    }

    // get Person ledger

    @Test
    @DisplayName("getPersonLedger | happy path")
    public void getPersonLedger() {

        CreatePersonService createPersonService = new CreatePersonService(personRepository, ledgerRepository, categoryRepository, accountRepository);

        // Arrange
        String emailMaria = "maria@gmail.com";
        String nameMaria = "Maria";
        LocalDate birthdateMaria = LocalDate.of(1000, 10, 5);
        String birthplaceMaria = "Gaia";

        PersonEmailDTO personMariaEmailDTO = new PersonEmailDTO(emailMaria);
        Person personMaria = Person.createPerson(emailMaria, nameMaria, birthdateMaria, birthplaceMaria);

        LedgerID ledgerIDMaria = personMaria.getLedgerID();

        Ledger ledgerMaria = new Ledger(ledgerIDMaria);

        // Arrange CategoryID
        String denominationCat = "HairStylist";
        CategoryID categoryID = CategoryID.createCategoryID(denominationCat, personMaria.getPersonID());

        String type = "debit";
        String description = "Pente0";
        double amount = 150.0;
        LocalDate date = LocalDate.of(2010, 12, 25);

        // Arrange CreditAccountID
        String denominationAccountC = "hairdresserCredit";
        AccountID creditAccountID = AccountID.createAccountID(denominationAccountC, personMaria.getPersonID());

        // Arrange DebitAccountID
        String denominationAccountD = "DebitAccountMaria";
        AccountID debitAccountID = AccountID.createAccountID(denominationAccountD, personMaria.getPersonID());

        // Act
        Transaction transaction = Transaction.createTransaction(categoryID, type, description, amount, date, debitAccountID, creditAccountID);
        ledgerMaria.addTransaction(transaction);

        // Mock the behaviour of the service's personRepository findPersonByID method, so it does not depend on other parts (e.g. DB)
        Mockito.when(personRepository.findById(personMaria.getPersonID())).thenReturn(Optional.of(personMaria));

        // Mock the behaviour of the service's personRepository findPersonByID method, so it does not depend on other parts (e.g. DB)
        Mockito.when(ledgerRepository.findById(ledgerIDMaria)).thenReturn(Optional.of(ledgerMaria));

        // Expected
        TransactionDTOout expectedTransactionDTOout = new TransactionDTOout(denominationCat, type, description, amount, date.toString(), denominationAccountD, denominationAccountC);

        // Act
        List<TransactionDTOout> expectedListTransactionDTO = new ArrayList<>();
        expectedListTransactionDTO.add(expectedTransactionDTOout);
        TransactionsDTO expectedTransactionsDTO = TransactionsDTOAssembler.createDTOFromPrimitiveTypes(expectedListTransactionDTO);

        // Act
        TransactionsDTO result = createPersonService.getPersonLedger(personMariaEmailDTO);

        // Assert
        assertEquals(expectedTransactionsDTO, result);

    }

    @Test
    @DisplayName("getPersonLedger | | Person Does Not Exist")
    public void getPersonLedger_PersonDoesNotExist() {

        CreatePersonService createPersonService = new CreatePersonService(personRepository, ledgerRepository, categoryRepository, accountRepository);

        // Arrange
        String emailMaria = "maria@gmail.com";
        String nameMaria = "Maria";
        LocalDate birthdateMaria = LocalDate.of(1000, 10, 5);
        String birthplaceMaria = "Gaia";

        PersonEmailDTO personMariaEmailDTO = new PersonEmailDTO(emailMaria);
        Person personMaria = Person.createPerson(emailMaria, nameMaria, birthdateMaria, birthplaceMaria);

        // Mock the behaviour of the service's personRepository findPersonByID method, so it does not depend on other parts (e.g. DB)
        Mockito.when(personRepository.findById(personMaria.getPersonID())).thenReturn(Optional.empty());

        // Act expected object
        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () -> createPersonService.getPersonLedger(personMariaEmailDTO));


        //Assert
        assertEquals(thrown.getMessage(), createPersonService.PERSON_DOES_NOT_EXIST);

    }

    @Test
    @DisplayName("getPersonAccounts | happy path")
    public void getPersonAccounts() {

        CreatePersonService createPersonService = new CreatePersonService(personRepository, ledgerRepository, categoryRepository, accountRepository);

        // Arrange
        String emailMaria = "maria@gmail.com";
        String nameMaria = "Maria";
        LocalDate birthdateMaria = LocalDate.of(1000, 10, 5);
        String birthplaceMaria = "Gaia";

        PersonEmailDTO personMariaEmailDTO = new PersonEmailDTO(emailMaria);
        Person personMaria = Person.createPerson(emailMaria, nameMaria, birthdateMaria, birthplaceMaria);
        PersonID personID = PersonID.createPersonID(emailMaria);

        //Arrange CreditAccount

        String denominationAccountC = "hairdresserCredit";
        String descriptionAccountC = "hairdresserCredit description";

        AccountID creditAccountID = AccountID.createAccountID(denominationAccountC, personMaria.getPersonID());

        Account account = Account.createAccount(descriptionAccountC, denominationAccountC, personID);

        AccountDTO accountDTO = AccountDTOAssembler.createDTOFromPrimitiveTypes(denominationAccountC, descriptionAccountC);


        List<AccountDTO> listOfAccounts = new ArrayList<>();
        listOfAccounts.add(accountDTO);

        personMaria.addAccount(creditAccountID);

        //Act

        // Mock the behaviour of the service's personRepository findPersonByID method, so it does not depend on other parts (e.g. DB)
        Mockito.when(personRepository.findById(personMaria.getPersonID())).thenReturn(Optional.of(personMaria));

        // Account repository must return an Optional Account

        Mockito.when(accountRepository.findById(emailMaria, denominationAccountC)).thenReturn(Optional.of(account));

        //Expected

        AccountsDTO expectedAccountDTO = AccountsDTOAssembler.createDTOFromDomainObject(listOfAccounts);

        // Act

        AccountsDTO result = createPersonService.getPersonAccounts(personMariaEmailDTO);

        // Assert
        assertEquals(expectedAccountDTO, result);

    }

    @Test
    @DisplayName("getPersonAccounts | | Person Does Not Exist")
    public void getPersonAccounts_PersonDoesNotExist() {

        CreatePersonService createPersonService = new CreatePersonService(personRepository, ledgerRepository, categoryRepository, accountRepository);

        // Arrange
        String emailMaria = "maria@gmail.com";
        String nameMaria = "Maria";
        LocalDate birthdateMaria = LocalDate.of(1000, 10, 5);
        String birthplaceMaria = "Gaia";

        PersonEmailDTO personMariaEmailDTO = new PersonEmailDTO(emailMaria);
        Person personMaria = Person.createPerson(emailMaria, nameMaria, birthdateMaria, birthplaceMaria);

        // Mock the behaviour of the service's personRepository findPersonByID method, so it does not depend on other parts (e.g. DB)
        Mockito.when(personRepository.findById(personMaria.getPersonID())).thenReturn(Optional.empty());

        // Act expected object
        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () -> createPersonService.getPersonAccounts(personMariaEmailDTO));


        //Assert
        assertEquals(thrown.getMessage(), createPersonService.PERSON_DOES_NOT_EXIST);

    }

    @Test
    @DisplayName("getPersonCategories | happy path")
    public void getPersonCategories() {

        CreatePersonService createPersonService = new CreatePersonService(personRepository, ledgerRepository, categoryRepository, accountRepository);

        // Arrange
        String emailMaria = "maria@gmail.com";
        String nameMaria = "Maria";
        LocalDate birthdateMaria = LocalDate.of(1000, 10, 5);
        String birthplaceMaria = "Gaia";

        PersonEmailDTO personMariaEmailDTO = new PersonEmailDTO(emailMaria);
        Person personMaria = Person.createPerson(emailMaria, nameMaria, birthdateMaria, birthplaceMaria);


        //Arrange CategoryID
        String denominationCat = "HairStylist";
        CategoryID categoryID = CategoryID.createCategoryID(denominationCat, personMaria.getPersonID());

        List<CategoryID> listOfCategories = new ArrayList<>();
        listOfCategories.add(categoryID);

        personMaria.addCategory(categoryID);

        //Act

        // Mock the behaviour of the service's personRepository findPersonByID method, so it does not depend on other parts (e.g. DB)
        Mockito.when(personRepository.findById(personMaria.getPersonID())).thenReturn(Optional.of(personMaria));

        //Expected

        CategoriesDTO expectedCategories = CategoriesDTOAssembler.createDTOFromDomainObject(listOfCategories);

        // Act

        CategoriesDTO result = createPersonService.getPersonCategories(personMariaEmailDTO);

        // Assert
        assertEquals(expectedCategories, result);

    }

    @Test
    @DisplayName("getPersonACategories || Person Does Not Exist")
    public void getPersonCategories_PersonDoesNotExist() {

        CreatePersonService createPersonService = new CreatePersonService(personRepository, ledgerRepository, categoryRepository, accountRepository);

        // Arrange
        String emailMaria = "maria@gmail.com";
        String nameMaria = "Maria";
        LocalDate birthdateMaria = LocalDate.of(1000, 10, 5);
        String birthplaceMaria = "Gaia";

        PersonEmailDTO personMariaEmailDTO = new PersonEmailDTO(emailMaria);
        Person personMaria = Person.createPerson(emailMaria, nameMaria, birthdateMaria, birthplaceMaria);

        // Mock the behaviour of the service's personRepository findPersonByID method, so it does not depend on other parts (e.g. DB)
        Mockito.when(personRepository.findById(personMaria.getPersonID())).thenReturn(Optional.empty());

        // Act expected object
        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () -> createPersonService.getPersonCategories(personMariaEmailDTO));


        //Assert
        assertEquals(thrown.getMessage(), createPersonService.PERSON_DOES_NOT_EXIST);

    }

    @Test
    @DisplayName("getPersonSiblings | happy path")
    public void getPersonSiblings() {

        CreatePersonService createPersonService = new CreatePersonService(personRepository, ledgerRepository, categoryRepository, accountRepository);

        // Arrange
        String emailMaria = "maria@gmail.com";
        String nameMaria = "Maria";
        LocalDate birthdateMaria = LocalDate.of(1000, 10, 5);
        String birthplaceMaria = "Gaia";

        PersonEmailDTO personMariaEmailDTO = new PersonEmailDTO(emailMaria);
        Person personMaria = Person.createPerson(emailMaria, nameMaria, birthdateMaria, birthplaceMaria);


        //Arrange Siblings - PersonsID
        PersonID personIDsibling1 = PersonID.createPersonID("joana@gmail.com");
        PersonID personIDsibling2 = PersonID.createPersonID("joao@gmail.com");

        personMaria.addSibling(personIDsibling1);
        personMaria.addSibling(personIDsibling2);

        List<PersonID> siblings = new ArrayList<>();
        siblings.add(personIDsibling1);
        siblings.add(personIDsibling2);

        //Act

        // Mock the behaviour of the service's personRepository findPersonByID method, so it does not depend on other parts (e.g. DB)
        Mockito.when(personRepository.findById(personMaria.getPersonID())).thenReturn(Optional.of(personMaria));

        //Expected

        SiblingsDTO expectedSiblingsDTO = SiblingsDTOAssembler.createDTOFromDomainObject(siblings);

        // Act

        SiblingsDTO result = createPersonService.getPersonSiblings(personMariaEmailDTO);

        // Assert
        assertEquals(expectedSiblingsDTO, result);

    }

    @Test
    @DisplayName("getPersonSiblings | | Person Does Not Exist")
    public void getPersonSiblings_PersonDoesNotExist() {

        CreatePersonService createPersonService = new CreatePersonService(personRepository, ledgerRepository, categoryRepository, accountRepository);

        // Arrange
        String emailMaria = "maria@gmail.com";
        String nameMaria = "Maria";
        LocalDate birthdateMaria = LocalDate.of(1000, 10, 5);
        String birthplaceMaria = "Gaia";

        PersonEmailDTO personMariaEmailDTO = new PersonEmailDTO(emailMaria);
        Person personMaria = Person.createPerson(emailMaria, nameMaria, birthdateMaria, birthplaceMaria);

        // Mock the behaviour of the service's personRepository findPersonByID method, so it does not depend on other parts (e.g. DB)
        Mockito.when(personRepository.findById(personMaria.getPersonID())).thenReturn(Optional.empty());

        // Act expected object
        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () -> createPersonService.getPersonSiblings(personMariaEmailDTO));


        //Assert
        assertEquals(thrown.getMessage(), createPersonService.PERSON_DOES_NOT_EXIST);

    }

    @Test
    @DisplayName("CreateAndSavePerson | happy path")
    public void createAndSavePerson() {

        CreatePersonService createPersonService = new CreatePersonService(personRepository, ledgerRepository, categoryRepository, accountRepository);

        // Arrange
        String emailMaria = "maria@gmail.com";
        String nameMaria = "Maria";
        LocalDate birthdateMaria = LocalDate.of(1000, 10, 5);
        String birthplaceMaria = "Gaia";

        Person personMaria = Person.createPerson(emailMaria, nameMaria, birthdateMaria, birthplaceMaria);

        CreatePersonDTO createPersonDTOMaria = new CreatePersonDTO(emailMaria, nameMaria, birthdateMaria, birthplaceMaria);

        //Act

        // Mock the behaviour of the service's personRepository findPersonByID method, so it does not depend on other parts (e.g. DB)
        Mockito.when(personRepository.save(personMaria)).thenReturn(personMaria);

        //Expected

        CreatePersonDTO expectedCreatePersonDTO = CreatePersonDTOAssembler.createDTOFromPrimitiveTypes(emailMaria, nameMaria, birthdateMaria.toString(), birthplaceMaria);

        // Act

        CreatePersonDTO result = createPersonService.createAndSavePerson(createPersonDTOMaria);

        // Assert
        assertEquals(expectedCreatePersonDTO, result);

    }

    @Test
    @DisplayName("AddAddressToPerson | happy path")
    public void addAddressToPerson() {

        CreatePersonService createPersonService = new CreatePersonService(personRepository, ledgerRepository, categoryRepository, accountRepository);

        // Arrange
        String emailMaria = "maria@gmail.com";
        String nameMaria = "Maria";
        LocalDate birthdateMaria = LocalDate.of(1000, 10, 5);
        String birthplaceMaria = "Gaia";

        Person personMaria = Person.createPerson(emailMaria, nameMaria, birthdateMaria, birthplaceMaria);

        //Arrange
        String street = "rua da saudade";
        String doorNumber = "12";
        String postCode = "4000-121";
        String city = "Porto";
        String country = "Portugal";

        //Act
        Address address = Address.createAddress(street, doorNumber, postCode, city, country);

        //Act

        // Mock the behaviour of the service's personRepository findPersonByID method, so it does not depend on other parts (e.g. DB)
        Mockito.when(personRepository.findById(personMaria.getPersonID())).thenReturn(Optional.of(personMaria));

        // Mock the behaviour of the service's personRepository addAAddress method, so it does not depend on other parts (e.g. DB)
        Mockito.when(personRepository.addAndSaveAddress(personMaria)).thenReturn(true);

        // Act

        boolean result = createPersonService.addAddressToPerson(personMaria.getPersonID(), address);

        // Assert
        assertEquals(true, result);
        assertTrue(result);
    }

    @Test
    @DisplayName("addAddressToPerson | | Person Does Not Exist")
    public void addAddressToPerson_PersonDoesNotExist() {

        CreatePersonService createPersonService = new CreatePersonService(personRepository, ledgerRepository, categoryRepository, accountRepository);

        // Arrange
        String emailMaria = "maria@gmail.com";
        String nameMaria = "Maria";
        LocalDate birthdateMaria = LocalDate.of(1000, 10, 5);
        String birthplaceMaria = "Gaia";

        Person personMaria = Person.createPerson(emailMaria, nameMaria, birthdateMaria, birthplaceMaria);

        //Arrange
        String street = "rua da saudade";
        String doorNumber = "12";
        String postCode = "4000-121";
        String city = "Porto";
        String country = "Portugal";

        //Act
        Address address = Address.createAddress(street, doorNumber, postCode, city, country);

        // Mock the behaviour of the service's personRepository findPersonByID method, so it does not depend on other parts (e.g. DB)
        Mockito.when(personRepository.findById(personMaria.getPersonID())).thenReturn(Optional.empty());

        // Act expected object
        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () -> createPersonService.addAddressToPerson(personMaria.getPersonID(), address));


        //Assert
        assertEquals(thrown.getMessage(), createPersonService.PERSON_DOES_NOT_EXIST);

    }

    @Test
    @DisplayName("addAddressToPerson | | Not added")
    public void addAddressToPerson_NotAdded() {

        CreatePersonService createPersonService = new CreatePersonService(personRepository, ledgerRepository, categoryRepository, accountRepository);

        // Arrange
        String emailMaria = "maria@gmail.com";
        String nameMaria = "Maria";
        LocalDate birthdateMaria = LocalDate.of(1000, 10, 5);
        String birthplaceMaria = "Gaia";

        Person personMaria = Person.createPerson(emailMaria, nameMaria, birthdateMaria, birthplaceMaria);

        //Arrange
        String street = "rua da saudade";
        String doorNumber = "12";
        String postCode = "4000-121";
        String city = "Porto";
        String country = "Portugal";

        //Arrange
        String street1 = "rua das flores";
        String doorNumber1 = "14";
        String postCode1 = "4004-121";
        String city1 = "Lisboa";
        String country1 = "Portugal";

        Address address1 = Address.createAddress(street1, doorNumber1, postCode1, city1, country1);

        personMaria.addAddress(street, doorNumber, postCode, city, country);

        //Act

        // Mock the behaviour of the service's personRepository findPersonByID method, so it does not depend on other parts (e.g. DB)
        Mockito.when(personRepository.findById(personMaria.getPersonID())).thenReturn(Optional.of(personMaria));


        // Act expected object
        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () -> createPersonService.addAddressToPerson(personMaria.getPersonID(), address1));


        //Assert
        assertEquals(thrown.getMessage(), createPersonService.ADDRESS_ALREADY_EXIST);

    }

    @Test
    @DisplayName("AddMotherToPerson | happy path")
    public void addMotherToPerson() {

        CreatePersonService createPersonService = new CreatePersonService(personRepository, ledgerRepository, categoryRepository, accountRepository);

        // Arrange
        String emailMaria = "maria@gmail.com";
        String nameMaria = "Maria";
        LocalDate birthdateMaria = LocalDate.of(1000, 10, 5);
        String birthplaceMaria = "Gaia";

        Person personMaria = Person.createPerson(emailMaria, nameMaria, birthdateMaria, birthplaceMaria);

        // Arrange
        String emailMother = "Ana@gmail.com";
        String nameMother = "Ana";
        LocalDate birthdateMother = LocalDate.of(1000, 10, 5);
        String birthplaceMother = "Lisboa";

        Person personMother = Person.createPerson(emailMother, nameMother, birthdateMother, birthplaceMother);

        //Act

        // Mock the behaviour of the service's personRepository findPersonByID method, so it does not depend on other parts (e.g. DB)
        Mockito.when(personRepository.findById(personMaria.getPersonID())).thenReturn(Optional.of(personMaria));

        // Mock the behaviour of the service's personRepository findPersonByID method, so it does not depend on other parts (e.g. DB)
        Mockito.when(personRepository.findById(personMother.getPersonID())).thenReturn(Optional.of(personMother));

        // Mock the behaviour of the service's personRepository addAAddress method, so it does not depend on other parts (e.g. DB)
        Mockito.when(personRepository.addAndSaveMother(personMaria)).thenReturn(true);

        // Act

        boolean result = createPersonService.addMotherToPerson(personMaria.getPersonID(), personMother.getPersonID());

        // Assert
        assertEquals(true, result);
        assertTrue(result);
    }

    @Test
    @DisplayName("addMotherToPerson | | Person Does Not Exist")
    public void addMotherToPerson_PersonDoesNotExist() {

        CreatePersonService createPersonService = new CreatePersonService(personRepository, ledgerRepository, categoryRepository, accountRepository);

        // Arrange
        String emailMaria = "maria@gmail.com";
        String nameMaria = "Maria";
        LocalDate birthdateMaria = LocalDate.of(1000, 10, 5);
        String birthplaceMaria = "Gaia";

        Person personMaria = Person.createPerson(emailMaria, nameMaria, birthdateMaria, birthplaceMaria);

        // Arrange
        String emailMother = "maria@gmail.com";
        String nameMother = "Maria";
        LocalDate birthdateMother = LocalDate.of(1000, 10, 5);
        String birthplaceMother = "Gaia";

        Person personMother = Person.createPerson(emailMother, nameMother, birthdateMother, birthplaceMother);

        //Act

        // Mock the behaviour of the service's personRepository findPersonByID method, so it does not depend on other parts (e.g. DB)
        Mockito.when(personRepository.findById(personMaria.getPersonID())).thenReturn(Optional.empty());

        // Act expected object
        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () -> createPersonService.addMotherToPerson(personMaria.getPersonID(), personMother.getPersonID()));


        //Assert
        assertEquals(thrown.getMessage(), createPersonService.PERSON_DOES_NOT_EXIST);
    }

    @Test
    @DisplayName("addMotherToPerson | | Person Mother Does Not Exist")
    public void addMotherToPerson_PersonMotherDoesNotExist() {

        CreatePersonService createPersonService = new CreatePersonService(personRepository, ledgerRepository, categoryRepository, accountRepository);

        // Arrange
        String emailMaria = "maria@gmail.com";
        String nameMaria = "Maria";
        LocalDate birthdateMaria = LocalDate.of(1000, 10, 5);
        String birthplaceMaria = "Gaia";

        Person personMaria = Person.createPerson(emailMaria, nameMaria, birthdateMaria, birthplaceMaria);

        // Arrange
        String emailMother = "maria@gmail.com";
        String nameMother = "Maria";
        LocalDate birthdateMother = LocalDate.of(1000, 10, 5);
        String birthplaceMother = "Gaia";

        Person personMother = Person.createPerson(emailMother, nameMother, birthdateMother, birthplaceMother);

        //Act

        // Mock the behaviour of the service's personRepository findPersonByID method, so it does not depend on other parts (e.g. DB)
        Mockito.when(personRepository.findById(personMaria.getPersonID())).thenReturn(Optional.of(personMaria));

        // Mock the behaviour of the service's personRepository findPersonByID method, so it does not depend on other parts (e.g. DB)
        Mockito.when(personRepository.findById(personMother.getPersonID())).thenReturn(Optional.empty());

        // Act expected object
        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () -> createPersonService.addMotherToPerson(personMaria.getPersonID(), personMother.getPersonID()));


        //Assert
        assertEquals(thrown.getMessage(), createPersonService.PERSON_DOES_NOT_EXIST);
    }

    @Test
    @DisplayName("addMotherToPerson | | NotAdded")
    public void addMotherToPerson_NotAdded() {

        CreatePersonService createPersonService = new CreatePersonService(personRepository, ledgerRepository, categoryRepository, accountRepository);

        // Arrange
        String emailMaria = "maria@gmail.com";
        String nameMaria = "Maria";
        LocalDate birthdateMaria = LocalDate.of(1000, 10, 5);
        String birthplaceMaria = "Gaia";

        Person personMaria = Person.createPerson(emailMaria, nameMaria, birthdateMaria, birthplaceMaria);

        // Arrange
        String emailMother = "isabel@gmail.com";
        String nameMother = "Isabel";
        LocalDate birthdateMother = LocalDate.of(1000, 9, 5);
        String birthplaceMother = "Porto";

        Person personMother = Person.createPerson(emailMother, nameMother, birthdateMother, birthplaceMother);

        // Arrange second mother - t
        String emailMother2 = "joana@gmail.com";
        String nameMother2 = "Joana";
        LocalDate birthdateMother2 = LocalDate.of(1000, 10, 5);
        String birthplaceMother2 = "Porto";

        Person personMother2 = Person.createPerson(emailMother2, nameMother2, birthdateMother2, birthplaceMother2);

        personMaria.addMother(personMother2.getPersonID());

        //Act

        // Mock the behaviour of the service's personRepository findPersonByID method, so it does not depend on other parts (e.g. DB)
        Mockito.when(personRepository.findById(personMaria.getPersonID())).thenReturn(Optional.of(personMaria));

        // Mock the behaviour of the service's personRepository findPersonByID method, so it does not depend on other parts (e.g. DB)
        Mockito.when(personRepository.findById(personMother.getPersonID())).thenReturn(Optional.of(personMother));

        // Act expected object
        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () -> createPersonService.addMotherToPerson(personMaria.getPersonID(), personMother.getPersonID()));


        //Assert
        assertEquals(thrown.getMessage(), createPersonService.MOTHER_ALREADY_EXIST);
    }

    @Test
    @DisplayName("AddFatherToPerson | happy path")
    public void addFatherToPerson() {

        CreatePersonService createPersonService = new CreatePersonService(personRepository, ledgerRepository, categoryRepository, accountRepository);

        // Arrange
        String emailMaria = "maria@gmail.com";
        String nameMaria = "Maria";
        LocalDate birthdateMaria = LocalDate.of(1000, 10, 5);
        String birthplaceMaria = "Gaia";

        Person personMaria = Person.createPerson(emailMaria, nameMaria, birthdateMaria, birthplaceMaria);

        // Arrange
        String emailFather = "jaoo@gmail.com";
        String nameFather = "Joao";
        LocalDate birthdateFather = LocalDate.of(1000, 10, 5);
        String birthplaceFather = "Porto";

        Person personFather = Person.createPerson(emailFather, nameFather, birthdateFather, birthplaceFather);

        //Act

        // Mock the behaviour of the service's personRepository findPersonByID method, so it does not depend on other parts (e.g. DB)
        Mockito.when(personRepository.findById(personMaria.getPersonID())).thenReturn(Optional.of(personMaria));

        // Mock the behaviour of the service's personRepository findPersonByID method, so it does not depend on other parts (e.g. DB)
        Mockito.when(personRepository.findById(personFather.getPersonID())).thenReturn(Optional.of(personFather));

        // Mock the behaviour of the service's personRepository addAAddress method, so it does not depend on other parts (e.g. DB)
        Mockito.when(personRepository.addAndSaveFather(personMaria)).thenReturn(true);

        // Act

        boolean result = createPersonService.addFatherToPerson(personMaria.getPersonID(), personFather.getPersonID());

        // Assert
        assertEquals(true, result);
        assertTrue(result);
    }

    @Test
    @DisplayName("addFatherToPerson | | Person Does Not Exist")
    public void addFatherToPerson_PersonDoesNotExist() {

        CreatePersonService createPersonService = new CreatePersonService(personRepository, ledgerRepository, categoryRepository, accountRepository);

        // Arrange
        String emailMaria = "maria@gmail.com";
        String nameMaria = "Maria";
        LocalDate birthdateMaria = LocalDate.of(1000, 10, 5);
        String birthplaceMaria = "Gaia";

        Person personMaria = Person.createPerson(emailMaria, nameMaria, birthdateMaria, birthplaceMaria);

        // Arrange
        String emailFather = "jaoo@gmail.com";
        String nameFather = "Joao";
        LocalDate birthdateFather = LocalDate.of(1000, 10, 5);
        String birthplaceFather = "Porto";

        Person personFather = Person.createPerson(emailFather, nameFather, birthdateFather, birthplaceFather);

        //Act

        // Mock the behaviour of the service's personRepository findPersonByID method, so it does not depend on other parts (e.g. DB)
        Mockito.when(personRepository.findById(personMaria.getPersonID())).thenReturn(Optional.empty());

        // Act expected object
        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () -> createPersonService.addFatherToPerson(personMaria.getPersonID(), personFather.getPersonID()));


        //Assert
        assertEquals(thrown.getMessage(), createPersonService.PERSON_DOES_NOT_EXIST);
    }

    @Test
    @DisplayName("addFatherToPerson | | Person Father Does Not Exist")
    public void addFatherToPerson_PersonFatherDoesNotExist() {

        CreatePersonService createPersonService = new CreatePersonService(personRepository, ledgerRepository, categoryRepository, accountRepository);

        // Arrange
        String emailMaria = "maria@gmail.com";
        String nameMaria = "Maria";
        LocalDate birthdateMaria = LocalDate.of(1000, 10, 5);
        String birthplaceMaria = "Gaia";

        Person personMaria = Person.createPerson(emailMaria, nameMaria, birthdateMaria, birthplaceMaria);

        // Arrange
        String emailFather = "jaoo@gmail.com";
        String nameFather = "Joao";
        LocalDate birthdateFather = LocalDate.of(1000, 10, 5);
        String birthplaceFather = "Porto";

        Person personFather = Person.createPerson(emailFather, nameFather, birthdateFather, birthplaceFather);

        //Act

        // Mock the behaviour of the service's personRepository findPersonByID method, so it does not depend on other parts (e.g. DB)
        Mockito.when(personRepository.findById(personMaria.getPersonID())).thenReturn(Optional.of(personMaria));

        // Mock the behaviour of the service's personRepository findPersonByID method, so it does not depend on other parts (e.g. DB)
        Mockito.when(personRepository.findById(personFather.getPersonID())).thenReturn(Optional.empty());

        // Act expected object
        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () -> createPersonService.addFatherToPerson(personMaria.getPersonID(), personFather.getPersonID()));


        //Assert
        assertEquals(thrown.getMessage(), createPersonService.PERSON_DOES_NOT_EXIST);
    }

    @Test
    @DisplayName("addFatherToPerson | | NotAdded")
    public void addFatherToPerson_NotAdded() {

        CreatePersonService createPersonService = new CreatePersonService(personRepository, ledgerRepository, categoryRepository, accountRepository);

        // Arrange
        String emailMaria = "maria@gmail.com";
        String nameMaria = "Maria";
        LocalDate birthdateMaria = LocalDate.of(1000, 10, 5);
        String birthplaceMaria = "Gaia";

        Person personMaria = Person.createPerson(emailMaria, nameMaria, birthdateMaria, birthplaceMaria);

        // Arrange
        String emailFather = "jaoo@gmail.com";
        String nameFather = "Joao";
        LocalDate birthdateFather = LocalDate.of(1000, 10, 5);
        String birthplaceFather = "Porto";

        Person personFather = Person.createPerson(emailFather, nameFather, birthdateFather, birthplaceFather);

        // Arrange father 2
        String emailFather2 = "henrique@gmail.com";
        String nameFather2 = "Henrique";
        LocalDate birthdateFather2 = LocalDate.of(1000, 9, 5);
        String birthplaceFather2 = "Lisboa";

        Person personFather2 = Person.createPerson(emailFather2, nameFather2, birthdateFather2, birthplaceFather2);

        personMaria.addFather(personFather2.getPersonID());

        //Act

        // Mock the behaviour of the service's personRepository findPersonByID method, so it does not depend on other parts (e.g. DB)
        Mockito.when(personRepository.findById(personMaria.getPersonID())).thenReturn(Optional.of(personMaria));

        // Mock the behaviour of the service's personRepository findPersonByID method, so it does not depend on other parts (e.g. DB)
        Mockito.when(personRepository.findById(personFather.getPersonID())).thenReturn(Optional.of(personFather));

        // Act expected object
        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () -> createPersonService.addFatherToPerson(personMaria.getPersonID(), personFather.getPersonID()));


        //Assert
        assertEquals(thrown.getMessage(), createPersonService.FATHER_ALREADY_EXIST);
    }

    @Test
    @DisplayName("AddSiblingToPerson | happy path")
    public void addSiblingToPerson() {

        CreatePersonService createPersonService = new CreatePersonService(personRepository, ledgerRepository, categoryRepository, accountRepository);

        // Arrange
        String emailMaria = "maria@gmail.com";
        String nameMaria = "Maria";
        LocalDate birthdateMaria = LocalDate.of(1000, 10, 5);
        String birthplaceMaria = "Gaia";

        Person personMaria = Person.createPerson(emailMaria, nameMaria, birthdateMaria, birthplaceMaria);

        // Arrange
        String emailSibling = "jaoo@gmail.com";
        String nameSibling = "Joao";
        LocalDate birthdateSibling = LocalDate.of(1000, 10, 5);
        String birthplaceSibling = "Porto";

        Person personSibling = Person.createPerson(emailSibling, nameSibling, birthdateSibling, birthplaceSibling);

        //Act

        // Mock the behaviour of the service's personRepository findPersonByID method, so it does not depend on other parts (e.g. DB)
        Mockito.when(personRepository.findById(personMaria.getPersonID())).thenReturn(Optional.of(personMaria));

        // Mock the behaviour of the service's personRepository findPersonByID method, so it does not depend on other parts (e.g. DB)
        Mockito.when(personRepository.findById(personSibling.getPersonID())).thenReturn(Optional.of(personSibling));

        // Mock the behaviour of the service's personRepository addAAddress method, so it does not depend on other parts (e.g. DB)
        Mockito.when(personRepository.addAndSaveSibling(personMaria, personSibling.getPersonID())).thenReturn(true);

        // Act

        boolean result = createPersonService.addSiblingToPerson(personMaria.getPersonID(), personSibling.getPersonID());

        // Assert
        assertEquals(true, result);
        assertTrue(result);
    }

    @Test
    @DisplayName("addFSiblingToPerson | | Person Does Not Exist")
    public void addSiblingToPerson_PersonDoesNotExist() {

        CreatePersonService createPersonService = new CreatePersonService(personRepository, ledgerRepository, categoryRepository, accountRepository);

        // Arrange
        String emailMaria = "maria@gmail.com";
        String nameMaria = "Maria";
        LocalDate birthdateMaria = LocalDate.of(1000, 10, 5);
        String birthplaceMaria = "Gaia";

        Person personMaria = Person.createPerson(emailMaria, nameMaria, birthdateMaria, birthplaceMaria);

        // Arrange
        String emailSibling = "jaoo@gmail.com";
        String nameSibling = "Joao";
        LocalDate birthdateSibling = LocalDate.of(1000, 10, 5);
        String birthplaceSibling = "Porto";

        Person personSibling = Person.createPerson(emailSibling, nameSibling, birthdateSibling, birthplaceSibling);

        //Act

        // Mock the behaviour of the service's personRepository findPersonByID method, so it does not depend on other parts (e.g. DB)
        Mockito.when(personRepository.findById(personMaria.getPersonID())).thenReturn(Optional.empty());

        // Act expected object
        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () -> createPersonService.addSiblingToPerson(personMaria.getPersonID(), personSibling.getPersonID()));


        //Assert
        assertEquals(thrown.getMessage(), createPersonService.PERSON_DOES_NOT_EXIST);
    }

    @Test
    @DisplayName("addFSiblingToPerson | | Person Sibling Does Not Exist")
    public void addSiblingToPerson_PersonSiblingDoesNotExist() {

        CreatePersonService createPersonService = new CreatePersonService(personRepository, ledgerRepository, categoryRepository, accountRepository);

        // Arrange
        String emailMaria = "maria@gmail.com";
        String nameMaria = "Maria";
        LocalDate birthdateMaria = LocalDate.of(1000, 10, 5);
        String birthplaceMaria = "Gaia";

        Person personMaria = Person.createPerson(emailMaria, nameMaria, birthdateMaria, birthplaceMaria);

        // Arrange
        String emailSibling = "jaoo@gmail.com";
        String nameSibling = "Joao";
        LocalDate birthdateSibling = LocalDate.of(1000, 10, 5);
        String birthplaceSibling = "Porto";

        Person personSibling = Person.createPerson(emailSibling, nameSibling, birthdateSibling, birthplaceSibling);

        //Act

        // Mock the behaviour of the service's personRepository findPersonByID method, so it does not depend on other parts (e.g. DB)
        Mockito.when(personRepository.findById(personMaria.getPersonID())).thenReturn(Optional.of(personMaria));

        // Mock the behaviour of the service's personRepository findPersonByID method, so it does not depend on other parts (e.g. DB)
        Mockito.when(personRepository.findById(personSibling.getPersonID())).thenReturn(Optional.empty());

        // Act expected object
        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () -> createPersonService.addSiblingToPerson(personMaria.getPersonID(), personSibling.getPersonID()));


        //Assert
        assertEquals(thrown.getMessage(), createPersonService.PERSON_DOES_NOT_EXIST);
    }

    @Test
    @DisplayName("addFatherToPerson | | Person Sibling already Exist")
    public void addSiblingToPerson_PersonSiblingAlreadyExist() {

        CreatePersonService createPersonService = new CreatePersonService(personRepository, ledgerRepository, categoryRepository, accountRepository);

        // Arrange
        String emailMaria = "maria@gmail.com";
        String nameMaria = "Maria";
        LocalDate birthdateMaria = LocalDate.of(1000, 10, 5);
        String birthplaceMaria = "Gaia";

        Person personMaria = Person.createPerson(emailMaria, nameMaria, birthdateMaria, birthplaceMaria);

        // Arrange
        String emailSibling = "jaoo@gmail.com";
        String nameSibling = "Joao";
        LocalDate birthdateSibling = LocalDate.of(1000, 10, 5);
        String birthplaceSibling = "Porto";

        Person personSibling = Person.createPerson(emailSibling, nameSibling, birthdateSibling, birthplaceSibling);

        // Arrange sibling2
        String emailSibling2 = "isabel@gmail.com";
        String nameSibling2 = "Isabel";
        LocalDate birthdateSibling2 = LocalDate.of(1000, 10, 5);
        String birthplaceSibling2 = "Braga";

        Person personSibling2 = Person.createPerson(emailSibling2, nameSibling2, birthdateSibling2, birthplaceSibling2);

        personMaria.addSibling(personSibling.getPersonID());
        personMaria.addSibling(personSibling2.getPersonID());

        //Act

        // Mock the behaviour of the service's personRepository findPersonByID method, so it does not depend on other parts (e.g. DB)
        Mockito.when(personRepository.findById(personMaria.getPersonID())).thenReturn(Optional.of(personMaria));

        // Mock the behaviour of the service's personRepository findPersonByID method, so it does not depend on other parts (e.g. DB)
        Mockito.when(personRepository.findById(personSibling.getPersonID())).thenReturn(Optional.of(personSibling));

        // Act expected object
        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () -> createPersonService.addSiblingToPerson(personMaria.getPersonID(), personSibling.getPersonID()));


        //Assert
        assertEquals(thrown.getMessage(), createPersonService.SIBLING_ALREADY_EXIST);
    }

    @Test
    @DisplayName("AddCategoryToPerson | happy path")
    public void addCategoryToPerson() {

        CreatePersonService createPersonService = new CreatePersonService(personRepository, ledgerRepository, categoryRepository, accountRepository);

        // Arrange
        String emailMaria = "maria@gmail.com";
        String nameMaria = "Maria";
        LocalDate birthdateMaria = LocalDate.of(1000, 10, 5);
        String birthplaceMaria = "Gaia";

        Person personMaria = Person.createPerson(emailMaria, nameMaria, birthdateMaria, birthplaceMaria);

        String denomination = "Food";
        Category newCategory = Category.createCategory(denomination, personMaria.getPersonID());

        CreatePersonCategoryDTO createPersonCategoryDTO = CreatePersonCategoryDTOAssembler.createDTOFromPrimitiveTypes(emailMaria, denomination);

        //Act

        // Mock the behaviour of the service's personRepository findPersonByID method, so it does not depend on other parts (e.g. DB)
        Mockito.when(personRepository.findById(personMaria.getPersonID())).thenReturn(Optional.of(personMaria));

        // Mock the behaviour of the service's categoryRepository findCategoryByID method, so it does not depend on other parts (e.g. DB)
        Mockito.when(categoryRepository.findById(emailMaria, denomination)).thenReturn(Optional.empty());

        // Mock the behaviour of the service's personRepository addAAddress method, so it does not depend on other parts (e.g. DB)
        Mockito.when(personRepository.addAndSaveCategory(personMaria)).thenReturn(true);

        // Act

        boolean result = createPersonService.addCategoryToPerson(createPersonCategoryDTO);

        // Assert
        assertEquals(true, result);
        assertTrue(result);
    }

    @Test
    @DisplayName("AddCategoryToPerson | Person does not exist")
    public void addCategoryToPerson_PersonDoesNotExist() {

        CreatePersonService createPersonService = new CreatePersonService(personRepository, ledgerRepository, categoryRepository, accountRepository);

        // Arrange
        String emailMaria = "maria@gmail.com";
        String nameMaria = "Maria";
        LocalDate birthdateMaria = LocalDate.of(1000, 10, 5);
        String birthplaceMaria = "Gaia";

        Person personMaria = Person.createPerson(emailMaria, nameMaria, birthdateMaria, birthplaceMaria);

        String denomination = "Food";
        Category newCategory = Category.createCategory(denomination, personMaria.getPersonID());

        CreatePersonCategoryDTO createPersonCategoryDTO = CreatePersonCategoryDTOAssembler.createDTOFromPrimitiveTypes(emailMaria, denomination);

        //Act

        // Mock the behaviour of the service's personRepository findPersonByID method, so it does not depend on other parts (e.g. DB)
        Mockito.when(personRepository.findById(personMaria.getPersonID())).thenReturn(Optional.empty());

        // Act expected object
        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () -> createPersonService.addCategoryToPerson(createPersonCategoryDTO));

        //Assert
        assertEquals(thrown.getMessage(), createPersonService.PERSON_DOES_NOT_EXIST);
    }

    @Test
    @DisplayName("AddCategoryToPerson | Category already exist")
    public void addCategoryToPerson_CategoryAlreadyExist() {

        CreatePersonService createPersonService = new CreatePersonService(personRepository, ledgerRepository, categoryRepository, accountRepository);

        // Arrange
        String emailMaria = "maria@gmail.com";
        String nameMaria = "Maria";
        LocalDate birthdateMaria = LocalDate.of(1000, 10, 5);
        String birthplaceMaria = "Gaia";

        Person personMaria = Person.createPerson(emailMaria, nameMaria, birthdateMaria, birthplaceMaria);

        String denomination = "Food";
        Category newCategory = Category.createCategory(denomination, personMaria.getPersonID());

        CreatePersonCategoryDTO createPersonCategoryDTO = CreatePersonCategoryDTOAssembler.createDTOFromPrimitiveTypes(emailMaria, denomination);

        //Act

        // Mock the behaviour of the service's personRepository findPersonByID method, so it does not depend on other parts (e.g. DB)
        Mockito.when(personRepository.findById(personMaria.getPersonID())).thenReturn(Optional.of(personMaria));

        // Mock the behaviour of the service's categoryRepository findCategoryByID method, so it does not depend on other parts (e.g. DB)
        Mockito.when(categoryRepository.findById(emailMaria, denomination)).thenReturn(Optional.of(newCategory));

        // Act expected object
        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () -> createPersonService.addCategoryToPerson(createPersonCategoryDTO));

        //Assert
        assertEquals(thrown.getMessage(), createPersonService.CATEGORY_ALREADY_EXIST);
    }

    @Test
    @DisplayName("AddCategoryToPerson | Category already exist 2")
    public void addCategoryToPerson_CategoryAlreadyExist2() {

        CreatePersonService createPersonService = new CreatePersonService(personRepository, ledgerRepository, categoryRepository, accountRepository);

        // Arrange
        String emailMaria = "maria@gmail.com";
        String nameMaria = "Maria";
        LocalDate birthdateMaria = LocalDate.of(1000, 10, 5);
        String birthplaceMaria = "Gaia";

        Person personMaria = Person.createPerson(emailMaria, nameMaria, birthdateMaria, birthplaceMaria);

        String denomination = "Food";

        //Arrange categoryThatExists
        Category newCategory = Category.createCategory(denomination, personMaria.getPersonID());
        personMaria.addCategory(newCategory.getCategoryID());

        //Arrange DTO

        CreatePersonCategoryDTO createPersonCategoryDTO = CreatePersonCategoryDTOAssembler.createDTOFromPrimitiveTypes(emailMaria, denomination);

        //Act

        // Mock the behaviour of the service's personRepository findPersonByID method, so it does not depend on other parts (e.g. DB)
        Mockito.when(personRepository.findById(personMaria.getPersonID())).thenReturn(Optional.of(personMaria));

        // Mock the behaviour of the service's categoryRepository findCategoryByID method, so it does not depend on other parts (e.g. DB)
        Mockito.when(categoryRepository.findById(emailMaria, denomination)).thenReturn(Optional.empty());

        // Act expected object
        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () -> createPersonService.addCategoryToPerson(createPersonCategoryDTO));

        //Assert
        assertEquals(thrown.getMessage(), createPersonService.CATEGORY_ALREADY_EXIST);
    }

    @Test
    @DisplayName("AddAccountToPerson | happy path")
    public void addAccountToPerson() {

        CreatePersonService createPersonService = new CreatePersonService(personRepository, ledgerRepository, categoryRepository, accountRepository);

        // Arrange
        String emailMaria = "maria@gmail.com";
        String nameMaria = "Maria";
        LocalDate birthdateMaria = LocalDate.of(1000, 10, 5);
        String birthplaceMaria = "Gaia";

        Person personMaria = Person.createPerson(emailMaria, nameMaria, birthdateMaria, birthplaceMaria);

        //Arrange DTO

        String denomination = "Supermarket";
        String description = "Supermarket builds";

        CreatePersonAccountDTO createPersonAccountDTO = CreatePersonAccountDTOAssembler.createDTOFromPrimitiveTypes(personMaria.getPersonID().getEmail().getEmail(), description, denomination);

        //Act

        // Mock the behaviour of the service's personRepository findPersonByID method, so it does not depend on other parts (e.g. DB)
        Mockito.when(personRepository.findById(personMaria.getPersonID())).thenReturn(Optional.of(personMaria));

        // Mock the behaviour of the service's categoryRepository findCategoryByID method, so it does not depend on other parts (e.g. DB)
        Mockito.when(accountRepository.findById(emailMaria, denomination)).thenReturn(Optional.empty());

        // Mock the behaviour of the service's personRepository addAAddress method, so it does not depend on other parts (e.g. DB)
        Mockito.when(personRepository.addAndSaveAccount(personMaria, description)).thenReturn(true);

        // Act

        boolean result = createPersonService.addAccountToPerson(createPersonAccountDTO);

        // Assert
        assertEquals(true, result);
        assertTrue(result);
    }

    @Test
    @DisplayName("AddAccountToPerson | Person does not exist")
    public void addAccountToPerson_PersonDoesNotExist() {

        CreatePersonService createPersonService = new CreatePersonService(personRepository, ledgerRepository, categoryRepository, accountRepository);

        // Arrange
        String emailMaria = "maria@gmail.com";
        String nameMaria = "Maria";
        LocalDate birthdateMaria = LocalDate.of(1000, 10, 5);
        String birthplaceMaria = "Gaia";

        Person personMaria = Person.createPerson(emailMaria, nameMaria, birthdateMaria, birthplaceMaria);

        //Arrange DTO

        String denomination = "Supermarket";
        String description = "Supermarket builds";

        CreatePersonAccountDTO createPersonAccountDTO = CreatePersonAccountDTOAssembler.createDTOFromPrimitiveTypes(personMaria.getPersonID().getEmail().getEmail(), description, denomination);

        //Act

        // Mock the behaviour of the service's personRepository findPersonByID method, so it does not depend on other parts (e.g. DB)
        Mockito.when(personRepository.findById(personMaria.getPersonID())).thenReturn(Optional.empty());

        // Mock the behaviour of the service's categoryRepository findCategoryByID method, so it does not depend on other parts (e.g. DB)
        Mockito.when(accountRepository.findById(emailMaria, denomination)).thenReturn(Optional.empty());

        // Mock the behaviour of the service's personRepository addAAddress method, so it does not depend on other parts (e.g. DB)
        Mockito.when(personRepository.addAndSaveAccount(personMaria, description)).thenReturn(true);

        // Act expected object
        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () -> createPersonService.addAccountToPerson(createPersonAccountDTO));

        //Assert
        assertEquals(thrown.getMessage(), createPersonService.PERSON_DOES_NOT_EXIST);
    }

    @Test
    @DisplayName("AddAccountToPerson | Account already exist")
    public void addAccountToPerson_AccountAlreadyExist() {

        CreatePersonService createPersonService = new CreatePersonService(personRepository, ledgerRepository, categoryRepository, accountRepository);

        // Arrange
        String emailMaria = "maria@gmail.com";
        String nameMaria = "Maria";
        LocalDate birthdateMaria = LocalDate.of(1000, 10, 5);
        String birthplaceMaria = "Gaia";

        Person personMaria = Person.createPerson(emailMaria, nameMaria, birthdateMaria, birthplaceMaria);

        //Arrange DTO

        String denomination = "Supermarket";
        String description = "Supermarket builds";

        CreatePersonAccountDTO createPersonAccountDTO = CreatePersonAccountDTOAssembler.createDTOFromPrimitiveTypes(personMaria.getPersonID().getEmail().getEmail(), description, denomination);

        Account account = Account.createAccount(description, denomination, personMaria.getPersonID());
        //Act

        // Mock the behaviour of the service's personRepository findPersonByID method, so it does not depend on other parts (e.g. DB)
        Mockito.when(personRepository.findById(personMaria.getPersonID())).thenReturn(Optional.of(personMaria));

        // Mock the behaviour of the service's categoryRepository findCategoryByID method, so it does not depend on other parts (e.g. DB)
        Mockito.when(accountRepository.findById(emailMaria, denomination)).thenReturn(Optional.of(account));

        // Mock the behaviour of the service's personRepository addAAddress method, so it does not depend on other parts (e.g. DB)
        Mockito.when(personRepository.addAndSaveAccount(personMaria, description)).thenReturn(true);

        // Act expected object
        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () -> createPersonService.addAccountToPerson(createPersonAccountDTO));

        //Assert
        assertEquals(thrown.getMessage(), createPersonService.ACCOUNT_ALREADY_EXIST);
    }

    @Test
    @DisplayName("AddAccountToPerson | Account already exist2")
    public void addAccountToPerson_AccountAlreadyExist2() {

        CreatePersonService createPersonService = new CreatePersonService(personRepository, ledgerRepository, categoryRepository, accountRepository);

        // Arrange
        String emailMaria = "maria@gmail.com";
        String nameMaria = "Maria";
        LocalDate birthdateMaria = LocalDate.of(1000, 10, 5);
        String birthplaceMaria = "Gaia";

        Person personMaria = Person.createPerson(emailMaria, nameMaria, birthdateMaria, birthplaceMaria);

        //Arrange DTO

        String denomination = "Supermarket";
        String description = "Supermarket builds";

        CreatePersonAccountDTO createPersonAccountDTO = CreatePersonAccountDTOAssembler.createDTOFromPrimitiveTypes(personMaria.getPersonID().getEmail().getEmail(), description, denomination);

        Account account = Account.createAccount(description, denomination, personMaria.getPersonID());

        personMaria.addAccount(account.getAccountID());
        //Act

        // Mock the behaviour of the service's personRepository findPersonByID method, so it does not depend on other parts (e.g. DB)
        Mockito.when(personRepository.findById(personMaria.getPersonID())).thenReturn(Optional.of(personMaria));

        // Mock the behaviour of the service's categoryRepository findCategoryByID method, so it does not depend on other parts (e.g. DB)
        Mockito.when(accountRepository.findById(emailMaria, denomination)).thenReturn(Optional.empty());

        // Mock the behaviour of the service's personRepository addAAddress method, so it does not depend on other parts (e.g. DB)
        Mockito.when(personRepository.addAndSaveAccount(personMaria, description)).thenReturn(false);

        // Act expected object
        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () -> createPersonService.addAccountToPerson(createPersonAccountDTO));

        //Assert
        assertEquals(thrown.getMessage(), createPersonService.ACCOUNT_ALREADY_EXIST);
    }

    @Test
    @DisplayName("AddTransactionToPerson | happy path")
    public void addTransactionToPerson() {

        CreatePersonService createPersonService = new CreatePersonService(personRepository, ledgerRepository, categoryRepository, accountRepository);

        // Arrange
        String emailMaria = "maria@gmail.com";
        String nameMaria = "Maria";
        LocalDate birthdateMaria = LocalDate.of(1000, 10, 5);
        String birthplaceMaria = "Gaia";

        Person personMaria = Person.createPerson(emailMaria, nameMaria, birthdateMaria, birthplaceMaria);

        //Arrange DTO

        String denominationCategory = "HairStylist";
        String type = "debit";
        String description = "Pente0";
        double amount = 150.0;
        String denominationAccountDeb = "DebitAccountMaria";
        String denominationAccountCred = "CredditAccountHairStyle";
        String date = "2020-03-05";

        //Arrange Category

        Category hairStyleCategory = Category.createCategory(denominationCategory, personMaria.getPersonID());

        //Arrange DebitAccount

        String descriptionDebit = "Hairstyle builds";
        Account debitAccount = Account.createAccount(descriptionDebit, denominationAccountDeb, personMaria.getPersonID());

        //Arrange CreditAccount

        String descriptionCredit = "HairStyle Account";
        Account creditAccount = Account.createAccount(descriptionCredit, denominationAccountCred, personMaria.getPersonID());

        //Arrange Ledger Maria

        LedgerID mariaLedgerID = personMaria.getLedgerID();
        Ledger mariaLedger = new Ledger(mariaLedgerID);

        // Act

        CreatePersonTransactionDTO createPersonTransactionDTO = CreatePersonTransactionDTOAssembler.createDTOFromPrimitiveTypes(personMaria.getPersonID().getEmail().getEmail(), denominationCategory, type, description, amount, denominationAccountDeb, denominationAccountCred, date);

        //Act

        // Mock the behaviour of the service's personRepository findPersonByID method, so it does not depend on other parts (e.g. DB)
        Mockito.when(personRepository.findById(personMaria.getPersonID())).thenReturn(Optional.of(personMaria));

        // Mock the behaviour of the service's categoryRepository findCategoryByID method, so it does not depend on other parts (e.g. DB)
        Mockito.when(categoryRepository.findById(personMaria.getPersonID().getEmail().getEmail(), denominationCategory)).thenReturn(Optional.of(hairStyleCategory));

        // Mock the behaviour of the service's accountRepository , so it does not depend on other parts (e.g. DB)
        Mockito.when(accountRepository.findById(personMaria.getPersonID().getEmail().getEmail(), denominationAccountDeb)).thenReturn(Optional.of(debitAccount));

        // Mock the behaviour of the service's accountRepository , so it does not depend on other parts (e.g. DB)
        Mockito.when(accountRepository.findById(personMaria.getPersonID().getEmail().getEmail(), denominationAccountCred)).thenReturn(Optional.of(creditAccount));

        // Mock the behaviour of the service's ledgerRepository , so it does not depend on other parts (e.g. DB)
        Mockito.when(ledgerRepository.findById(personMaria.getLedgerID())).thenReturn(Optional.of(mariaLedger));

        // Mock the behaviour of the service's ledgerRepository , so it does not depend on other parts (e.g. DB)
        Mockito.when(ledgerRepository.addAndSaveTransaction(mariaLedger)).thenReturn(true);

        // Act

        boolean result = createPersonService.addPersonTransaction(createPersonTransactionDTO);

        // Assert
        assertEquals(true, result);
        assertTrue(result);
    }

    @Test
    @DisplayName("AddTransactionToPerson | Person does not exist")
    public void addTransactionToPerson_PersonDoesNotExist() {

        CreatePersonService createPersonService = new CreatePersonService(personRepository, ledgerRepository, categoryRepository, accountRepository);

        // Arrange
        String emailMaria = "maria@gmail.com";
        String nameMaria = "Maria";
        LocalDate birthdateMaria = LocalDate.of(1000, 10, 5);
        String birthplaceMaria = "Gaia";

        Person personMaria = Person.createPerson(emailMaria, nameMaria, birthdateMaria, birthplaceMaria);

        //Arrange DTO

        String denominationCategory = "HairStylist";
        String type = "debit";
        String description = "Pente0";
        double amount = 150.0;
        String denominationAccountDeb = "DebitAccountMaria";
        String denominationAccountCred = "CredditAccountHairStyle";
        String date = "2020-03-05";

        //Arrange Category

        Category hairStyleCategory = Category.createCategory(denominationCategory, personMaria.getPersonID());

        //Arrange DebitAccount

        String descriptionDebit = "Hairstyle builds";
        Account debitAccount = Account.createAccount(descriptionDebit, denominationAccountDeb, personMaria.getPersonID());

        //Arrange CreditAccount

        String descriptionCredit = "HairStyle Account";
        Account creditAccount = Account.createAccount(descriptionCredit, denominationAccountCred, personMaria.getPersonID());

        //Arrange Ledger Maria

        LedgerID mariaLedgerID = personMaria.getLedgerID();
        Ledger mariaLedger = new Ledger(mariaLedgerID);

        // Act

        CreatePersonTransactionDTO createPersonTransactionDTO = CreatePersonTransactionDTOAssembler.createDTOFromPrimitiveTypes(personMaria.getPersonID().getEmail().getEmail(), denominationCategory, type, description, amount, denominationAccountDeb, denominationAccountCred, date);

        //Act

        // Mock the behaviour of the service's personRepository findPersonByID method, so it does not depend on other parts (e.g. DB)
        Mockito.when(personRepository.findById(personMaria.getPersonID())).thenReturn(Optional.empty());

        // Mock the behaviour of the service's categoryRepository findCategoryByID method, so it does not depend on other parts (e.g. DB)
        Mockito.when(categoryRepository.findById(personMaria.getPersonID().getEmail().getEmail(), denominationCategory)).thenReturn(Optional.of(hairStyleCategory));

        // Mock the behaviour of the service's accountRepository , so it does not depend on other parts (e.g. DB)
        Mockito.when(accountRepository.findById(personMaria.getPersonID().getEmail().getEmail(), denominationAccountDeb)).thenReturn(Optional.of(debitAccount));

        // Mock the behaviour of the service's accountRepository , so it does not depend on other parts (e.g. DB)
        Mockito.when(accountRepository.findById(personMaria.getPersonID().getEmail().getEmail(), denominationAccountCred)).thenReturn(Optional.of(creditAccount));

        // Mock the behaviour of the service's ledgerRepository , so it does not depend on other parts (e.g. DB)
        Mockito.when(ledgerRepository.findById(personMaria.getLedgerID())).thenReturn(Optional.of(mariaLedger));

        // Mock the behaviour of the service's ledgerRepository , so it does not depend on other parts (e.g. DB)
        Mockito.when(ledgerRepository.addAndSaveTransaction(mariaLedger)).thenReturn(true);

        // Act expected object
        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () -> createPersonService.addPersonTransaction(createPersonTransactionDTO));

        //Assert
        assertEquals(thrown.getMessage(), createPersonService.PERSON_DOES_NOT_EXIST);
    }

    @Test
    @DisplayName("AddTransactionToPerson | Category does not exist")
    public void addTransactionToPerson_CategoryDoesNotExist() {

        CreatePersonService createPersonService = new CreatePersonService(personRepository, ledgerRepository, categoryRepository, accountRepository);

        // Arrange
        String emailMaria = "maria@gmail.com";
        String nameMaria = "Maria";
        LocalDate birthdateMaria = LocalDate.of(1000, 10, 5);
        String birthplaceMaria = "Gaia";

        Person personMaria = Person.createPerson(emailMaria, nameMaria, birthdateMaria, birthplaceMaria);

        //Arrange DTO

        String denominationCategory = "HairStylist";
        String type = "debit";
        String description = "Pente0";
        double amount = 150.0;
        String denominationAccountDeb = "DebitAccountMaria";
        String denominationAccountCred = "CredditAccountHairStyle";
        String date = "2020-03-05";

        //Arrange Category

        Category hairStyleCategory = Category.createCategory(denominationCategory, personMaria.getPersonID());

        //Arrange DebitAccount

        String descriptionDebit = "Hairstyle builds";
        Account debitAccount = Account.createAccount(descriptionDebit, denominationAccountDeb, personMaria.getPersonID());

        //Arrange CreditAccount

        String descriptionCredit = "HairStyle Account";
        Account creditAccount = Account.createAccount(descriptionCredit, denominationAccountCred, personMaria.getPersonID());

        //Arrange Ledger Maria

        LedgerID mariaLedgerID = personMaria.getLedgerID();
        Ledger mariaLedger = new Ledger(mariaLedgerID);

        // Act

        CreatePersonTransactionDTO createPersonTransactionDTO = CreatePersonTransactionDTOAssembler.createDTOFromPrimitiveTypes(personMaria.getPersonID().getEmail().getEmail(), denominationCategory, type, description, amount, denominationAccountDeb, denominationAccountCred, date);

        //Act

        // Mock the behaviour of the service's personRepository findPersonByID method, so it does not depend on other parts (e.g. DB)
        Mockito.when(personRepository.findById(personMaria.getPersonID())).thenReturn(Optional.of(personMaria));

        // Mock the behaviour of the service's categoryRepository findCategoryByID method, so it does not depend on other parts (e.g. DB)
        Mockito.when(categoryRepository.findById(personMaria.getPersonID().getEmail().getEmail(), denominationCategory)).thenReturn(Optional.empty());

        // Mock the behaviour of the service's accountRepository , so it does not depend on other parts (e.g. DB)
        Mockito.when(accountRepository.findById(personMaria.getPersonID().getEmail().getEmail(), denominationAccountDeb)).thenReturn(Optional.of(debitAccount));

        // Mock the behaviour of the service's accountRepository , so it does not depend on other parts (e.g. DB)
        Mockito.when(accountRepository.findById(personMaria.getPersonID().getEmail().getEmail(), denominationAccountCred)).thenReturn(Optional.of(creditAccount));

        // Mock the behaviour of the service's ledgerRepository , so it does not depend on other parts (e.g. DB)
        Mockito.when(ledgerRepository.findById(personMaria.getLedgerID())).thenReturn(Optional.of(mariaLedger));

        // Mock the behaviour of the service's ledgerRepository , so it does not depend on other parts (e.g. DB)
        Mockito.when(ledgerRepository.addAndSaveTransaction(mariaLedger)).thenReturn(true);

        // Act expected object
        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () -> createPersonService.addPersonTransaction(createPersonTransactionDTO));

        //Assert
        assertEquals(thrown.getMessage(), createPersonService.CATEGORY_DOES_NOT_EXIST);
    }

    @Test
    @DisplayName("AddTransactionToPerson | DebitAccount does not exist")
    public void addTransactionToPerson_DebitAccountDoesNotExist() {

        CreatePersonService createPersonService = new CreatePersonService(personRepository, ledgerRepository, categoryRepository, accountRepository);

        // Arrange
        String emailMaria = "maria@gmail.com";
        String nameMaria = "Maria";
        LocalDate birthdateMaria = LocalDate.of(1000, 10, 5);
        String birthplaceMaria = "Gaia";

        Person personMaria = Person.createPerson(emailMaria, nameMaria, birthdateMaria, birthplaceMaria);

        //Arrange DTO

        String denominationCategory = "HairStylist";
        String type = "debit";
        String description = "Pente0";
        double amount = 150.0;
        String denominationAccountDeb = "DebitAccountMaria";
        String denominationAccountCred = "CredditAccountHairStyle";
        String date = "2020-03-05";

        //Arrange Category

        Category hairStyleCategory = Category.createCategory(denominationCategory, personMaria.getPersonID());

        //Arrange DebitAccount

        String descriptionDebit = "Hairstyle builds";
        Account debitAccount = Account.createAccount(descriptionDebit, denominationAccountDeb, personMaria.getPersonID());

        //Arrange CreditAccount

        String descriptionCredit = "HairStyle Account";
        Account creditAccount = Account.createAccount(descriptionCredit, denominationAccountCred, personMaria.getPersonID());

        //Arrange Ledger Maria

        LedgerID mariaLedgerID = personMaria.getLedgerID();
        Ledger mariaLedger = new Ledger(mariaLedgerID);

        // Act

        CreatePersonTransactionDTO createPersonTransactionDTO = CreatePersonTransactionDTOAssembler.createDTOFromPrimitiveTypes(personMaria.getPersonID().getEmail().getEmail(), denominationCategory, type, description, amount, denominationAccountDeb, denominationAccountCred, date);

        //Act

        // Mock the behaviour of the service's personRepository findPersonByID method, so it does not depend on other parts (e.g. DB)
        Mockito.when(personRepository.findById(personMaria.getPersonID())).thenReturn(Optional.of(personMaria));

        // Mock the behaviour of the service's categoryRepository findCategoryByID method, so it does not depend on other parts (e.g. DB)
        Mockito.when(categoryRepository.findById(personMaria.getPersonID().getEmail().getEmail(), denominationCategory)).thenReturn(Optional.of(hairStyleCategory));

        // Mock the behaviour of the service's accountRepository , so it does not depend on other parts (e.g. DB)
        Mockito.when(accountRepository.findById(personMaria.getPersonID().getEmail().getEmail(), denominationAccountDeb)).thenReturn(Optional.empty());

        // Mock the behaviour of the service's accountRepository , so it does not depend on other parts (e.g. DB)
        Mockito.when(accountRepository.findById(personMaria.getPersonID().getEmail().getEmail(), denominationAccountCred)).thenReturn(Optional.of(creditAccount));

        // Mock the behaviour of the service's ledgerRepository , so it does not depend on other parts (e.g. DB)
        Mockito.when(ledgerRepository.findById(personMaria.getLedgerID())).thenReturn(Optional.of(mariaLedger));

        // Mock the behaviour of the service's ledgerRepository , so it does not depend on other parts (e.g. DB)
        Mockito.when(ledgerRepository.addAndSaveTransaction(mariaLedger)).thenReturn(true);

        // Act expected object
        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () -> createPersonService.addPersonTransaction(createPersonTransactionDTO));

        //Assert
        assertEquals(thrown.getMessage(), createPersonService.ACCOUNT_DOES_NOT_EXIST);
    }

    @Test
    @DisplayName("AddTransactionToPerson | CreditAccount does not exist")
    public void addTransactionToPerson_CreditAccountDoesNotExist() {

        CreatePersonService createPersonService = new CreatePersonService(personRepository, ledgerRepository, categoryRepository, accountRepository);

        // Arrange
        String emailMaria = "maria@gmail.com";
        String nameMaria = "Maria";
        LocalDate birthdateMaria = LocalDate.of(1000, 10, 5);
        String birthplaceMaria = "Gaia";

        Person personMaria = Person.createPerson(emailMaria, nameMaria, birthdateMaria, birthplaceMaria);

        //Arrange DTO

        String denominationCategory = "HairStylist";
        String type = "debit";
        String description = "Pente0";
        double amount = 150.0;
        String denominationAccountDeb = "DebitAccountMaria";
        String denominationAccountCred = "CredditAccountHairStyle";
        String date = "2020-03-05";

        //Arrange Category

        Category hairStyleCategory = Category.createCategory(denominationCategory, personMaria.getPersonID());

        //Arrange DebitAccount

        String descriptionDebit = "Hairstyle builds";
        Account debitAccount = Account.createAccount(descriptionDebit, denominationAccountDeb, personMaria.getPersonID());

        //Arrange CreditAccount

        String descriptionCredit = "HairStyle Account";
        Account creditAccount = Account.createAccount(descriptionCredit, denominationAccountCred, personMaria.getPersonID());

        //Arrange Ledger Maria

        LedgerID mariaLedgerID = personMaria.getLedgerID();
        Ledger mariaLedger = new Ledger(mariaLedgerID);

        // Act

        CreatePersonTransactionDTO createPersonTransactionDTO = CreatePersonTransactionDTOAssembler.createDTOFromPrimitiveTypes(personMaria.getPersonID().getEmail().getEmail(), denominationCategory, type, description, amount, denominationAccountDeb, denominationAccountCred, date);

        //Act

        // Mock the behaviour of the service's personRepository findPersonByID method, so it does not depend on other parts (e.g. DB)
        Mockito.when(personRepository.findById(personMaria.getPersonID())).thenReturn(Optional.of(personMaria));

        // Mock the behaviour of the service's categoryRepository findCategoryByID method, so it does not depend on other parts (e.g. DB)
        Mockito.when(categoryRepository.findById(personMaria.getPersonID().getEmail().getEmail(), denominationCategory)).thenReturn(Optional.of(hairStyleCategory));

        // Mock the behaviour of the service's accountRepository , so it does not depend on other parts (e.g. DB)
        Mockito.when(accountRepository.findById(personMaria.getPersonID().getEmail().getEmail(), denominationAccountDeb)).thenReturn(Optional.of(debitAccount));

        // Mock the behaviour of the service's accountRepository , so it does not depend on other parts (e.g. DB)
        Mockito.when(accountRepository.findById(personMaria.getPersonID().getEmail().getEmail(), denominationAccountCred)).thenReturn(Optional.empty());

        // Mock the behaviour of the service's ledgerRepository , so it does not depend on other parts (e.g. DB)
        Mockito.when(ledgerRepository.findById(personMaria.getLedgerID())).thenReturn(Optional.of(mariaLedger));

        // Mock the behaviour of the service's ledgerRepository , so it does not depend on other parts (e.g. DB)
        Mockito.when(ledgerRepository.addAndSaveTransaction(mariaLedger)).thenReturn(true);

        // Act expected object
        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () -> createPersonService.addPersonTransaction(createPersonTransactionDTO));

        //Assert
        assertEquals(thrown.getMessage(), createPersonService.ACCOUNT_DOES_NOT_EXIST);
    }

    @Test
    @DisplayName("AddTransactionToPerson | Ledger does not exist")
    public void addTransactionToPerson_LedgerDoesNotExist() {

        CreatePersonService createPersonService = new CreatePersonService(personRepository, ledgerRepository, categoryRepository, accountRepository);

        // Arrange
        String emailMaria = "maria@gmail.com";
        String nameMaria = "Maria";
        LocalDate birthdateMaria = LocalDate.of(1000, 10, 5);
        String birthplaceMaria = "Gaia";

        Person personMaria = Person.createPerson(emailMaria, nameMaria, birthdateMaria, birthplaceMaria);

        //Arrange DTO

        String denominationCategory = "HairStylist";
        String type = "debit";
        String description = "Pente0";
        double amount = 150.0;
        String denominationAccountDeb = "DebitAccountMaria";
        String denominationAccountCred = "CredditAccountHairStyle";
        String date = "2020-03-05";

        //Arrange Category

        Category hairStyleCategory = Category.createCategory(denominationCategory, personMaria.getPersonID());

        //Arrange DebitAccount

        String descriptionDebit = "Hairstyle builds";
        Account debitAccount = Account.createAccount(descriptionDebit, denominationAccountDeb, personMaria.getPersonID());

        //Arrange CreditAccount

        String descriptionCredit = "HairStyle Account";
        Account creditAccount = Account.createAccount(descriptionCredit, denominationAccountCred, personMaria.getPersonID());

        //Arrange Ledger Maria

        LedgerID mariaLedgerID = personMaria.getLedgerID();
        Ledger mariaLedger = new Ledger(mariaLedgerID);

        // Act

        CreatePersonTransactionDTO createPersonTransactionDTO = CreatePersonTransactionDTOAssembler.createDTOFromPrimitiveTypes(personMaria.getPersonID().getEmail().getEmail(), denominationCategory, type, description, amount, denominationAccountDeb, denominationAccountCred, date);

        //Act

        // Mock the behaviour of the service's personRepository findPersonByID method, so it does not depend on other parts (e.g. DB)
        Mockito.when(personRepository.findById(personMaria.getPersonID())).thenReturn(Optional.of(personMaria));

        // Mock the behaviour of the service's categoryRepository findCategoryByID method, so it does not depend on other parts (e.g. DB)
        Mockito.when(categoryRepository.findById(personMaria.getPersonID().getEmail().getEmail(), denominationCategory)).thenReturn(Optional.of(hairStyleCategory));

        // Mock the behaviour of the service's accountRepository , so it does not depend on other parts (e.g. DB)
        Mockito.when(accountRepository.findById(personMaria.getPersonID().getEmail().getEmail(), denominationAccountDeb)).thenReturn(Optional.of(debitAccount));

        // Mock the behaviour of the service's accountRepository , so it does not depend on other parts (e.g. DB)
        Mockito.when(accountRepository.findById(personMaria.getPersonID().getEmail().getEmail(), denominationAccountCred)).thenReturn(Optional.of(creditAccount));

        // Mock the behaviour of the service's ledgerRepository , so it does not depend on other parts (e.g. DB)
        Mockito.when(ledgerRepository.findById(personMaria.getLedgerID())).thenReturn(Optional.empty());

        // Mock the behaviour of the service's ledgerRepository , so it does not depend on other parts (e.g. DB)
        Mockito.when(ledgerRepository.addAndSaveTransaction(mariaLedger)).thenReturn(true);

        // Act expected object
        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () -> createPersonService.addPersonTransaction(createPersonTransactionDTO));

        //Assert
        assertEquals(thrown.getMessage(), createPersonService.LEDGER_DOES_NOT_EXIST);
    }


}





