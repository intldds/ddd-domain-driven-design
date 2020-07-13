package com.finance.project.applicationLayer.applicationServices.personServices;

import com.finance.project.domainLayer.exceptions.NotFoundArgumentsBusinessException;
import com.finance.project.domainLayer.repositoriesInterfaces.IAccountRepository;
import com.finance.project.domainLayer.repositoriesInterfaces.ICategoryRepository;
import com.finance.project.domainLayer.repositoriesInterfaces.ILedgerRepository;
import com.finance.project.domainLayer.repositoriesInterfaces.IPersonRepository;
import com.finance.project.dtos.dtosAssemblers.PersonDTOAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.finance.project.dtos.dtos.CreatePersonTransactionDTO;
import com.finance.project.dtos.dtos.DeletePersonTransactionDTO;
import com.finance.project.dtos.dtos.PersonDTO;
import com.finance.project.dtos.dtos.UpdatePersonTransactionDTO;
import com.finance.project.domainLayer.domainEntities.aggregates.ledger.Ledger;
import com.finance.project.domainLayer.domainEntities.aggregates.person.Person;
import com.finance.project.domainLayer.domainEntities.vosShared.AccountID;
import com.finance.project.domainLayer.domainEntities.vosShared.CategoryID;
import com.finance.project.domainLayer.domainEntities.vosShared.LedgerID;
import com.finance.project.domainLayer.domainEntities.vosShared.PersonID;

import java.time.LocalDate;
import java.util.Optional;


@Service
public class CreatePersonTransactionService {

    @Autowired
    private IPersonRepository personRepository;
    @Autowired
    private IAccountRepository accountRepository;
    @Autowired
    private ILedgerRepository ledgerRepository;
    @Autowired
    private ICategoryRepository categoryRepository;


    // Return messages
    public final static String SUCCESS = "Transaction created and added";

    /**
     * The constant CATEGORY_DOES_NOT_EXIST.
     */
    public final static String CATEGORY_DOES_NOT_EXIST = "Category doesn't exist";
    /**
     * The constant ACCOUNT_DEB_DOES_NOT_EXIST.
     */
    public final static String ACCOUNT_DEB_DOES_NOT_EXIST = "Debit Account doesn't exist";
    /**
     * The constant ACCOUNT_CRED_DOES_NOT_EXIST.
     */
    public final static String ACCOUNT_CRED_DOES_NOT_EXIST = "Credit Account doesn't exist";
    /**
     * The constant PERSON_DOES_NOT_EXIST.
     */
    public final static String PERSON_DOES_NOT_EXIST = "Person doesn't exist";


    public CreatePersonTransactionService(IPersonRepository personRepository, IAccountRepository accountRepository, ILedgerRepository ledgerRepository, ICategoryRepository categoryRepository) {
        this.personRepository = personRepository;
        this.accountRepository = accountRepository;
        this.ledgerRepository = ledgerRepository;
        this.categoryRepository = categoryRepository;
    }


    public PersonDTO createTransaction(CreatePersonTransactionDTO createPersonTransactionDTO) {

        Person person;

        PersonID personID = PersonID.createPersonID(createPersonTransactionDTO.getEmail());
        Optional<Person> optPerson = personRepository.findById(personID);

        // If person does not exist, transaction will not be created
        if (optPerson.isPresent()) {
            person = optPerson.get();
        } else {
            throw new NotFoundArgumentsBusinessException(PERSON_DOES_NOT_EXIST);
        }

        // Category
        CategoryID categoryID = CategoryID.createCategoryID(createPersonTransactionDTO.getDenominationCategory(), personID);
        boolean categoryExistsInRepo = categoryRepository.existsById(categoryID);

        // Debit Account
        AccountID debitAccountID = AccountID.createAccountID(createPersonTransactionDTO.getDenominationAccountDeb(), personID);
        boolean accountToDebitExistsInRepo = accountRepository.existsById(debitAccountID);

        // Credit Account
        AccountID creditAccountID = AccountID.createAccountID(createPersonTransactionDTO.getDenominationAccountCred(), personID);
        boolean accountToCreditExistsInRepo = accountRepository.existsById(creditAccountID);

        // Ledger
        LedgerID ledgerID = person.getLedgerID();
        Optional<Ledger> optLedger = ledgerRepository.findById(ledgerID);

        String type = createPersonTransactionDTO.getType();
        String description = createPersonTransactionDTO.getDescription();
        double amount = createPersonTransactionDTO.getAmount();

        if (!(categoryExistsInRepo)) {
            throw new NotFoundArgumentsBusinessException(CATEGORY_DOES_NOT_EXIST);

        } else if (!(accountToDebitExistsInRepo)) {
            throw new NotFoundArgumentsBusinessException(ACCOUNT_DEB_DOES_NOT_EXIST);

        } else if (!(accountToCreditExistsInRepo)) {
            throw new NotFoundArgumentsBusinessException(ACCOUNT_CRED_DOES_NOT_EXIST);

        } else {
            LocalDate date = LocalDate.parse(createPersonTransactionDTO.getDate());

            Ledger ledger = optLedger.get();
            ledger.createAndAddTransactionWithDate(categoryID, type, description, amount, date, debitAccountID, creditAccountID);
            ledgerRepository.addAndSaveTransaction(ledger);
        }

        return PersonDTOAssembler.createDTOFromDomainObject(
                person.getPersonID().getEmail(),
                person.getLedgerID(), person.getName(),
                person.getBirthdate(),
                person.getBirthplace(),
                person.getFather(),
                person.getMother());
    }


    // Update Transaction

    public PersonDTO updateTransaction(UpdatePersonTransactionDTO updatePersonTransactionDTO) {

        Person person;

        // Person
        PersonID personID = PersonID.createPersonID(updatePersonTransactionDTO.getEmail());
        Optional<Person> optPerson = personRepository.findById(personID);

        // If person does not exist, transaction will not be created
        if (optPerson.isPresent()) {
            person = optPerson.get();

        } else {

            throw new NotFoundArgumentsBusinessException(PERSON_DOES_NOT_EXIST);
        }

        // Category
        CategoryID categoryID = CategoryID.createCategoryID(updatePersonTransactionDTO.getDenominationCategory(), personID);
        boolean categoryExistsInRepo = categoryRepository.existsById(categoryID);

        // Debit Account
        AccountID debitAccountID = AccountID.createAccountID(updatePersonTransactionDTO.getDenominationAccountDeb(), personID);
        boolean accountToDebitExistsInRepo = accountRepository.existsById(debitAccountID);

        // Credit Account
        AccountID creditAccountID = AccountID.createAccountID(updatePersonTransactionDTO.getDenominationAccountCred(), personID);
        boolean accountToCreditExistsInRepo = accountRepository.existsById(creditAccountID);

        // Ledger
        LedgerID ledgerID = person.getLedgerID();
        Optional<Ledger> optLedger = ledgerRepository.findById(ledgerID);

        if (!(categoryExistsInRepo)) {
            throw new NotFoundArgumentsBusinessException(CATEGORY_DOES_NOT_EXIST);

        } else if (!(accountToDebitExistsInRepo)) {
            throw new NotFoundArgumentsBusinessException(ACCOUNT_DEB_DOES_NOT_EXIST);


        } else if (!(accountToCreditExistsInRepo)) {
            throw new NotFoundArgumentsBusinessException(ACCOUNT_CRED_DOES_NOT_EXIST);

        } else {
            Ledger ledger = optLedger.get();
            ledgerRepository.updatePersonTransaction(ledger, updatePersonTransactionDTO);
        }

        return PersonDTOAssembler.createDTOFromDomainObject(
                person.getPersonID().getEmail(),
                person.getLedgerID(),
                person.getName(),
                person.getBirthdate(),
                person.getBirthplace(),
                person.getFather(),
                person.getMother());
    }


    // Delete Transaction

    public PersonDTO deleteTransaction(DeletePersonTransactionDTO deletePersonTransactionDTO) {

        Person person;

        PersonID personID = PersonID.createPersonID(deletePersonTransactionDTO.getEmail());
        Optional<Person> optPerson = personRepository.findById(personID);

        // If person does not exist, transaction will not be created
        if (optPerson.isPresent()) {
            person = optPerson.get();

        } else {
            throw new NotFoundArgumentsBusinessException(PERSON_DOES_NOT_EXIST);
        }

        LedgerID ledgerID = person.getLedgerID();
        Optional<Ledger> optLedger = ledgerRepository.findById(ledgerID);

        Ledger ledger = optLedger.get();
        ledgerRepository.deletePersonTransaction(ledger, deletePersonTransactionDTO);

        return PersonDTOAssembler.createDTOFromDomainObject(
                person.getPersonID().getEmail(),
                person.getLedgerID(),
                person.getName(),
                person.getBirthdate(),
                person.getBirthplace(),
                person.getFather(),
                person.getMother());
    }

}
