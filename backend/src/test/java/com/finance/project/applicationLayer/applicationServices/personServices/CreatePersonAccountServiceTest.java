package com.finance.project.applicationLayer.applicationServices.personServices;

import com.finance.project.controllerLayer.integrationTests.AbstractTest;
import com.finance.project.domainLayer.domainEntities.aggregates.person.Person;
import com.finance.project.domainLayer.domainEntities.vosShared.AccountID;
import com.finance.project.domainLayer.domainEntities.vosShared.PersonID;
import com.finance.project.domainLayer.exceptions.InvalidArgumentsBusinessException;
import com.finance.project.domainLayer.exceptions.NotFoundArgumentsBusinessException;
import com.finance.project.domainLayer.repositoriesInterfaces.IAccountRepository;
import com.finance.project.domainLayer.repositoriesInterfaces.IPersonRepository;
import com.finance.project.dtos.dtosAssemblers.CreatePersonAccountDTOAssembler;
import com.finance.project.dtos.dtosAssemblers.PersonDTOAssembler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import com.finance.project.dtos.dtos.CreatePersonAccountDTO;
import com.finance.project.dtos.dtos.PersonDTO;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CreatePersonAccountServiceTest extends AbstractTest {

    @Mock
    private IPersonRepository personRepository;
    @Mock
    private IAccountRepository accountRepository;

    private CreatePersonAccountService createPersonAccountService;
    private Person person;
    private PersonID personId;

    @BeforeEach
    public void init() {

        // Person Manuel
        String manuelEmail = "manuel@gmail.com";
        String manuelName = "Manuel";
        LocalDate manuelBirthdate = LocalDate.of(1999, 2, 20);
        String manuelBirthplace = "Porto";

        this.person = Person.createPerson(manuelEmail, manuelName, manuelBirthdate, manuelBirthplace);
        this.personId = PersonID.createPersonID(manuelEmail);

        // Add Accounts

            // Company
        String accountDenomination = "Company";
        AccountID accountID = AccountID.createAccountID(accountDenomination, personId);
        person.addAccount(accountID);

            // Bank Account
        String accountBankDenomination = "Bank Account";
        AccountID accountBankID = AccountID.createAccountID(accountBankDenomination, personId);
        person.addAccount(accountBankID);
    }


    // Tests

    // Success

    @Test
    @DisplayName("test for createAccount() | Manuel | Success")
    void createAccount_Success() {

        // Arrange
        String personEmail = "manuel@gmail.com";
        String accountDescription = "Tools for equipment";
        String accountDenomination = "Equipment";

        // To Search
        AccountID accountID = AccountID.createAccountID(accountDenomination, personId);

        // Returning an Optional<Person> Person
        Mockito.when(personRepository.findById(personId)).thenReturn(Optional.of(person));

        // Returning False when account already exists
        Mockito.when(accountRepository.existsById(accountID)).thenReturn(false);

        // DTO
        CreatePersonAccountDTO createPersonAccountDTO = CreatePersonAccountDTOAssembler.createDTOFromPrimitiveTypes(personEmail,
                accountDescription, accountDenomination);

        // Expected PersonDTO
        PersonDTO expectedPersonDTO = PersonDTOAssembler.createDTOFromDomainObject(person.getEmail(), person.getName(),
                person.getBirthdate(), person.getBirthplace(), person.getFather(), person.getMother());

        // Act
        CreatePersonAccountService createPersonAccountService = new CreatePersonAccountService(personRepository,
                accountRepository);

        PersonDTO result = createPersonAccountService.createAccount(createPersonAccountDTO);

        // Assert
        assertEquals(expectedPersonDTO, result);
        assertEquals(true, person.checkIfPersonHasAccount(accountID));
    }


    // Account already added in BeforeEach

    @Test
    @DisplayName("test for createPerson() | Account already exists ")
    void createAccount_AccountAlreadyExists() {

        // Arrange
        String personEmail = "manuel@gmail.com";
        String accountDenomination = "Company";
        String accountDescription = "Company account";

        // To Search
        AccountID accountID = AccountID.createAccountID(accountDenomination, personId);

        // Returning an Optional<Person> person
        Mockito.when(personRepository.findById(personId)).thenReturn(Optional.of(person));

        // Returning True (account already exists)
        Mockito.when(accountRepository.existsById(accountID)).thenReturn(true);

        // DTO
        CreatePersonAccountDTO createPersonAccountDTO = CreatePersonAccountDTOAssembler.createDTOFromPrimitiveTypes(personEmail,
                accountDescription, accountDenomination);

        // Act
        CreatePersonAccountService createPersonAccountService = new CreatePersonAccountService(personRepository, accountRepository);
        Throwable thrown = assertThrows(InvalidArgumentsBusinessException.class, () -> createPersonAccountService.createAccount(createPersonAccountDTO));

        // Assert
        assertEquals(thrown.getMessage(), CreatePersonAccountService.ACCOUNT_ALREADY_EXIST);
    }


    // Person does not exist - not created in BeforeEach()

    @Test
    @DisplayName("test for createAccount() | Person does not exist")
    void createAccount_PersonDoesNotExist() {

        // Arrange
        String personEmail = "santi@gmail.com";
        String accountDenomination = "Company";
        String accountDescription = "Company account";

        // To Search
        AccountID accountID = AccountID.createAccountID(accountDenomination, personId);

        // Returning an Optional<Person> person
        Mockito.when(personRepository.findById(personId)).thenReturn(Optional.of(person));

        // Returning True (account already exists)
        Mockito.when(accountRepository.existsById(accountID)).thenReturn(true);

        // DTO
        CreatePersonAccountDTO createPersonAccountDTO = CreatePersonAccountDTOAssembler.createDTOFromPrimitiveTypes(personEmail,
                accountDescription, accountDenomination);

        // Act
        CreatePersonAccountService createPersonAccountService = new CreatePersonAccountService(personRepository, accountRepository);
        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () -> createPersonAccountService.createAccount(createPersonAccountDTO));

        // Assert
        assertEquals(thrown.getMessage(), CreatePersonAccountService.PERSON_DOES_NOT_EXIST);
    }


}
