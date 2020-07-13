package com.finance.project.applicationLayer.applicationServices.personServices;

import com.finance.project.controllerLayer.integrationTests.AbstractTest;
import com.finance.project.domainLayer.domainEntities.aggregates.ledger.Ledger;
import com.finance.project.domainLayer.domainEntities.aggregates.ledger.Transaction;
import com.finance.project.domainLayer.domainEntities.aggregates.person.*;
import com.finance.project.domainLayer.domainEntities.vosShared.*;
import com.finance.project.domainLayer.exceptions.NotFoundArgumentsBusinessException;
import com.finance.project.domainLayer.repositoriesInterfaces.IAccountRepository;
import com.finance.project.domainLayer.repositoriesInterfaces.ICategoryRepository;
import com.finance.project.domainLayer.repositoriesInterfaces.ILedgerRepository;
import com.finance.project.domainLayer.repositoriesInterfaces.IPersonRepository;
import com.finance.project.dtos.dtosAssemblers.CreatePersonTransactionDTOAssembler;
import com.finance.project.dtos.dtosAssemblers.DeletePersonTransactionDTOAssembler;
import com.finance.project.dtos.dtosAssemblers.PersonDTOAssembler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import com.finance.project.dtos.dtos.CreatePersonTransactionDTO;
import com.finance.project.dtos.dtos.DeletePersonTransactionDTO;
import com.finance.project.dtos.dtos.PersonDTO;
import com.finance.project.dtos.dtos.UpdatePersonTransactionDTO;
import com.finance.project.dtos.dtosAssemblers.UpdatePersonTransactionDTOAssembler;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


class CreatePersonTransactionServiceTest extends AbstractTest {

    @Mock
    private IPersonRepository personRepository;
    @Mock
    private ICategoryRepository categoryRepository;
    @Mock
    private IAccountRepository accountRepository;
    @Mock
    private ILedgerRepository ledgerRepository;

    private CreatePersonTransactionService createPersonTransactionService;
    private Person personPaulo;
    private PersonID pauloPersonID;
    private Ledger ledger;
    private LedgerID ledgerID;

    // BeforeEach

    @BeforeEach
    public void init() {

        // Ledger
        ledger = Ledger.createLedger();
        ledgerID = ledger.getLedgerID();

        // Person Paulo

        // Address
        String portoStreet = "Rua Direita do Viso";
        String portoDoorNumber = "59";
        String portoPostCode = "4250 - 198";
        String portoCity = "Porto";
        String portoCountry = "Portugal";

        Address porto = Address.createAddress(portoStreet, portoDoorNumber, portoPostCode, portoCity, portoCountry);

        // Data
        String pauloEmail = "paulo@gmail.com";
        String pauloName = "Paulo Fontes";
        LocalDate pauloBirthdate = LocalDate.of(1993, 03, 15);
        String pauloBirthplace = "Vila Nova de Gaia";

        pauloPersonID = PersonID.createPersonID(pauloEmail);

        personPaulo = Person.createPersonWithoutParents(pauloEmail, pauloName, pauloBirthdate, pauloBirthplace, porto, ledgerID);

        // Category Salary
        String salaryDenomination = "Salary";
        CategoryID salaryID = CategoryID.createCategoryID(salaryDenomination, pauloPersonID);
        personPaulo.addCategory(salaryID);

        // Account Company
        String companyDenomination = "Company";
        AccountID companyID = AccountID.createAccountID(companyDenomination, pauloPersonID);
        personPaulo.addAccount(companyID);

        // Account Bank Account
        String bankAccountDenomination = "Bank Account";
        AccountID bankAccountID = AccountID.createAccountID(bankAccountDenomination, pauloPersonID);
        personPaulo.addAccount(bankAccountID);

        // Salary January
        String credit = "credit";
        String salaryJanuaryDescription = "January salary";
        double salaryJanuaryAmount = 1500;
        LocalDate salaryJanuaryDate = LocalDate.of(2020, 01, 21);
        Transaction salaryJanuary = Transaction.createTransaction(salaryID, credit, salaryJanuaryDescription, salaryJanuaryAmount, salaryJanuaryDate, companyID, bankAccountID);

        ledger.addTransaction(salaryJanuary);
    }

    // Create Transaction

    @Test
    @DisplayName("Test for createTransactionAsPerson() | Success")
    void createTransactionAsPerson_Success() {

        // Arrange

        // Person info
        String pauloEmail = "paulo@gmail.com";
        String pauloName = "Paulo Fontes";
        LocalDate pauloBirthdate = LocalDate.of(1993, 03, 15);
        String pauloBirthplace = "Vila Nova de Gaia";

        // Transaction info
        final String denominationCategory = "Salary";
        final String type = "debit";
        final String description = "May Salary";
        final double amount = 1500.00;
        final String denominationAccountDeb = "Company";
        final String denominationAccountCred = "Bank Account";
        final String date = "2020-05-26";

        // To Search
        CategoryID categoryID = CategoryID.createCategoryID(denominationCategory, pauloPersonID);
        AccountID debAccountID = AccountID.createAccountID(denominationAccountDeb, pauloPersonID);
        AccountID credAccountID = AccountID.createAccountID(denominationAccountCred, pauloPersonID);

        // Returning an Optional<Person> Paulo
        Mockito.when(personRepository.findById(pauloPersonID)).thenReturn(Optional.of(personPaulo));

        // Returning True (Category exist)
        Mockito.when(categoryRepository.existsById(categoryID)).thenReturn(true);

        // Returning True (Deb Account exist)
        Mockito.when(accountRepository.existsById(debAccountID)).thenReturn(true);

        // Returning True (Cred Account exist)
        Mockito.when(accountRepository.existsById(credAccountID)).thenReturn(true);

        // Returning an Optional<Ledger> Paulo Fontes Ledger
        Mockito.when(ledgerRepository.findById(ledgerID)).thenReturn(Optional.of(ledger));

        // DTO
        CreatePersonTransactionDTO createPersonTransactionDTO = CreatePersonTransactionDTOAssembler.createDTOFromPrimitiveTypes(pauloEmail, denominationCategory, type, description, amount, denominationAccountDeb, denominationAccountCred, date);

        // Expected PersonDTO
        PersonDTO expectedPersonDTO = PersonDTOAssembler.createDTOFromDomainObject(Email.createEmail(pauloEmail), ledgerID, Name.createName(pauloName), Birthdate.createBirthdate(pauloBirthdate), Birthplace.createBirthplace(pauloBirthplace), null, null);

        // Service
        createPersonTransactionService = new CreatePersonTransactionService(personRepository, accountRepository, ledgerRepository, categoryRepository);

        // Act
        PersonDTO result = createPersonTransactionService.createTransaction(createPersonTransactionDTO);

        // Assert
        assertEquals(expectedPersonDTO, result);
    }


    // Person does not exist

    @Test
    @DisplayName("Test for createTransactionAsPerson() | Fail | Person Does Not Exist")
    void createTransactionAsPerson_Fail_PersonDoesNotExist() {

        //Arrange

        //Person info
        String pauloEmail = "paulo@gmail.com";

        //Transaction info
        final String denominationCategory = "Salary";
        final String type = "debit";
        final String description = "May Salary";
        final double amount = 1500.00;
        final String denominationAccountDeb = "Company";
        final String denominationAccountCred = "Bank Account";
        final String date = "2020-05-26";

        //To Search
        CategoryID categoryID = CategoryID.createCategoryID(denominationCategory, pauloPersonID);
        AccountID debAccountID = AccountID.createAccountID(denominationAccountDeb, pauloPersonID);
        AccountID credAccountID = AccountID.createAccountID(denominationAccountCred, pauloPersonID);

        //Returning an Optional<Person> Paulo Fontes
        Mockito.when(personRepository.findById(pauloPersonID)).thenReturn(Optional.empty());

        //Returning True (Category exist)
        Mockito.when(categoryRepository.existsById(categoryID)).thenReturn(true);

        //Returning True (Account exist)
        Mockito.when(accountRepository.existsById(debAccountID)).thenReturn(true);

        //Returning True (Account exist)
        Mockito.when(accountRepository.existsById(credAccountID)).thenReturn(true);

        //Returning an Optional<Ledger> Paulo Fontes Ledger
        Mockito.when(ledgerRepository.findById(ledgerID)).thenReturn(Optional.of(ledger));

        //DTO
        CreatePersonTransactionDTO createPersonTransactionDTO = CreatePersonTransactionDTOAssembler.createDTOFromPrimitiveTypes(pauloEmail, denominationCategory, type, description, amount, denominationAccountDeb, denominationAccountCred, date);

        //Service
        createPersonTransactionService = new CreatePersonTransactionService(personRepository, accountRepository, ledgerRepository, categoryRepository);

        //Act
        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () -> createPersonTransactionService.createTransaction(createPersonTransactionDTO));

        //Assert
        assertEquals(thrown.getMessage(), CreatePersonTransactionService.PERSON_DOES_NOT_EXIST);
    }


    // Update Transaction

    @Test
    @DisplayName("Test for updatePersonTransaction() | Success")
    void updatePersonTransaction_Success() {

        // Arrange

        // Person info
        String pauloEmail = "paulo@gmail.com";
        String pauloName = "Paulo Fontes";
        LocalDate pauloBirthdate = LocalDate.of(1993, 03, 15);
        String pauloBirthplace = "Vila Nova de Gaia";

        // Transaction info
        final String denominationCategory = "Salary";
        final String type = "debit";
        final String description = "May Salary";
        final double amount = 1500.00;
        final String denominationAccountDeb = "Company";
        final String denominationAccountCred = "Bank Account";

        // To Search
        CategoryID categoryID = CategoryID.createCategoryID(denominationCategory, pauloPersonID);
        AccountID debAccountID = AccountID.createAccountID(denominationAccountDeb, pauloPersonID);
        AccountID credAccountID = AccountID.createAccountID(denominationAccountCred, pauloPersonID);

        // Returning an Optional<Person> Paulo Fontes
        Mockito.when(personRepository.findById(pauloPersonID)).thenReturn(Optional.of(personPaulo));

        // Returning True (Category exist)
        Mockito.when(categoryRepository.existsById(categoryID)).thenReturn(true);

        // Returning True (Account exist)
        Mockito.when(accountRepository.existsById(debAccountID)).thenReturn(true);

        // Returning True (Account exist)
        Mockito.when(accountRepository.existsById(credAccountID)).thenReturn(true);

        // Returning an Optional<Ledger> Paulo Fontes Ledger
        Mockito.when(ledgerRepository.findById(ledgerID)).thenReturn(Optional.of(ledger));

        // DTO
        UpdatePersonTransactionDTO updatePersonTransactionDTO = UpdatePersonTransactionDTOAssembler.createDTOFromPrimitiveTypes(1, pauloEmail, denominationCategory, type, description, amount, denominationAccountDeb, denominationAccountCred);

        // Expected PersonDTO
        PersonDTO expectedPersonDTO = PersonDTOAssembler.createDTOFromDomainObject(Email.createEmail(pauloEmail), ledgerID, Name.createName(pauloName), Birthdate.createBirthdate(pauloBirthdate), Birthplace.createBirthplace(pauloBirthplace), null, null);

        // Service
        createPersonTransactionService = new CreatePersonTransactionService(personRepository, accountRepository, ledgerRepository, categoryRepository);

        // Act
        PersonDTO result = createPersonTransactionService.updateTransaction(updatePersonTransactionDTO);

        // Assert
        assertEquals(expectedPersonDTO, result);
    }


    // Delete Transaction

    @Test
    @DisplayName("Test for deletePersonTransaction() | Success")
    void deletePersonTransaction_Success() {

        // Arrange

        // Person info
        String pauloEmail = "paulo@gmail.com";
        String pauloName = "Paulo Fontes";
        LocalDate pauloBirthdate = LocalDate.of(1993, 03, 15);
        String pauloBirthplace = "Vila Nova de Gaia";

        // Returning an Optional<Person> Paulo Fontes
        Mockito.when(personRepository.findById(pauloPersonID)).thenReturn(Optional.of(personPaulo));

        // Returning an Optional<Ledger> Paulo Fontes Ledger
        Mockito.when(ledgerRepository.findById(ledgerID)).thenReturn(Optional.of(ledger));

        // DTO
        DeletePersonTransactionDTO deletePersonTransactionDTO = DeletePersonTransactionDTOAssembler.createDTOFromPrimitiveTypes(1, pauloEmail);

        // Expected PersonDTO
        PersonDTO expectedPersonDTO = PersonDTOAssembler.createDTOFromDomainObject(Email.createEmail(pauloEmail), ledgerID, Name.createName(pauloName), Birthdate.createBirthdate(pauloBirthdate), Birthplace.createBirthplace(pauloBirthplace), null, null);

        // Service
        createPersonTransactionService = new CreatePersonTransactionService(personRepository, accountRepository, ledgerRepository, categoryRepository);

        // Act
        PersonDTO result = createPersonTransactionService.deleteTransaction(deletePersonTransactionDTO);

        // Assert
        assertEquals(expectedPersonDTO, result);

    }

}