package com.finance.project.applicationLayer.applicationServices.personServices;

import com.finance.project.domainLayer.domainEntities.aggregates.person.*;
import com.finance.project.domainLayer.domainEntities.vosShared.*;
import com.finance.project.domainLayer.exceptions.InvalidArgumentsBusinessException;
import com.finance.project.domainLayer.exceptions.NotFoundArgumentsBusinessException;
import com.finance.project.domainLayer.repositoriesInterfaces.*;
import com.finance.project.dtos.dtos.*;
import com.finance.project.dtos.dtosAssemblers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.finance.project.domainLayer.domainEntities.aggregates.account.Account;
import com.finance.project.domainLayer.domainEntities.aggregates.category.Category;
import com.finance.project.domainLayer.domainEntities.aggregates.group.Group;
import com.finance.project.domainLayer.domainEntities.aggregates.ledger.Ledger;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class CreatePersonService {

    @Autowired
    private IPersonRepository personRepository;
    @Autowired
    private ILedgerRepository ledgerRepository;
    @Autowired
    private ICategoryRepository categoryRepository;
    @Autowired
    private IAccountRepository accountRepository;
    @Autowired
    private IGroupRepository groupRepository;

    /**
     * The constant SUCCESS.
     */
    public final static String SUCCESS = "Account created and added";
    /**
     * The constant ADDRESS_ALREADY_EXIST.
     */
    public final static String ADDRESS_ALREADY_EXIST = "Address already exists";
    /**
     * The constant ADDRESS_ALREADY_EXIST.
     */
    public final static String MOTHER_ALREADY_EXIST = "Mother already exists";
    /**
     * The constant ADDRESS_ALREADY_EXIST.
     */
    public final static String FATHER_ALREADY_EXIST = "Father already exists";
    /**
     * The constant ADDRESS_ALREADY_EXIST.
     */
    public final static String SIBLING_ALREADY_EXIST = "Sibling already exists";
    /**
     * The constant ACCOUNT_ALREADY_EXIST.
     */
    public final static String ACCOUNT_ALREADY_EXIST = "Account already exists";
    /**
     * The constant CATEGORY_ALREADY_EXIST.
     */
    public final static String CATEGORY_ALREADY_EXIST = "Category already exists";
    /**
     * The constant PERSON_DOES_NOT_EXIST.
     */
    public final static String PERSON_DOES_NOT_EXIST = "Person does not exist";
    /**
     * The constant PERSON_ALREADY_EXIST.
     */
    public final static String PERSON_ALREADY_EXIST = "Person already exists";
    /**
     * The constant ACCOUNT_DOES_NOT_EXIST.
     */
    public final static String ACCOUNT_DOES_NOT_EXIST = "Account does not exist";
    /**
     * The constant CATEGORY_DOES_NOT_EXIST.
     */
    public final static String CATEGORY_DOES_NOT_EXIST = "Category does not exist";
    /**
     * The constant CATEGORY_DOES_NOT_EXIST.
     */
    public final static String LEDGER_DOES_NOT_EXIST = "Ledger does not exist";
    /**
     * The constant CATEGORY_DOES_NOT_EXIST.
     */
    public final static String TRANSACTION_ALREADY_EXIST = "Transaction already exist";


    public CreatePersonService(IPersonRepository personRepository, ILedgerRepository ledgerRepository, ICategoryRepository categoryRepository, IAccountRepository accountRepository) {
        this.personRepository = personRepository;
        this.ledgerRepository = ledgerRepository;
        this.categoryRepository = categoryRepository;
        this.accountRepository = accountRepository;
    }


    public PersonDTO createPerson(CreatePersonDTO createPersonDTO) {
        PersonID personID = PersonID.createPersonID(createPersonDTO.getEmail());
        Optional<Person> optPerson = personRepository.findById(personID);

        if (optPerson.isPresent()) {

            throw new InvalidArgumentsBusinessException(PERSON_ALREADY_EXIST);

        } else {

            Person newPerson = Person.createPerson(createPersonDTO.getEmail(), createPersonDTO.getName(), createPersonDTO.getBirthdate(), createPersonDTO.getBirthplace());
            Person newSavedPerson = personRepository.save(newPerson);

            PersonDTO personDTO = PersonDTOAssembler.createDTOFromDomainObject(
                    newSavedPerson.getPersonID().getEmail(),
                    newSavedPerson.getName(),
                    newSavedPerson.getBirthdate(),
                    newSavedPerson.getBirthplace(),
                    newSavedPerson.getFather(),
                    newSavedPerson.getMother());

            return personDTO;
        }
    }


    // Getters

    public PersonDTO getPersonByEmail(PersonEmailDTO personEmailDTO) {
        PersonID personID = PersonID.createPersonID(personEmailDTO.getEmail());
        Optional<Person> optPerson = personRepository.findById(personID);

        if (!optPerson.isPresent()) {

            throw new NotFoundArgumentsBusinessException(PERSON_DOES_NOT_EXIST);

        } else {

            Person person = optPerson.get();

            Email personEmail = person.getPersonID().getEmail();
            Name personName = person.getName();
            Birthdate personBirthdate = person.getBirthdate();
            Birthplace personBirthplace = person.getBirthplace();
            PersonID fatherID = person.getFather();
            PersonID motherID = person.getMother();

            return PersonDTOAssembler.createDTOFromDomainObject(personEmail, personName, personBirthdate, personBirthplace, fatherID, motherID);
        }
    }

    @Transactional
    public TransactionsDTO getPersonLedger(PersonEmailDTO personEmailDTO) {
        PersonID personID = PersonID.createPersonID(personEmailDTO.getEmail());
        Optional<Person> optPerson = personRepository.findById(personID);

        if (!optPerson.isPresent()) {

            throw new NotFoundArgumentsBusinessException(PERSON_DOES_NOT_EXIST);

        } else {

            Person person = optPerson.get();

            LedgerID ledgerID = person.getLedgerID();

            Optional<Ledger> optLedger = ledgerRepository.findById(ledgerID);

            Ledger ledger = optLedger.get();

            List<TransactionDTOout> transactions = ledger.getRecordsAsDTO();

            return TransactionsDTOAssembler.createDTOFromPrimitiveTypes(transactions);
        }
    }

    @Transactional
    public AccountsDTO getPersonAccounts(PersonEmailDTO personEmailDTO) {

        List<AccountDTO> accountsDTO = new ArrayList<>();

        PersonID personID = PersonID.createPersonID(personEmailDTO.getEmail());
        Optional<Person> optPerson = personRepository.findById(personID);

        if (!optPerson.isPresent()) {

            throw new NotFoundArgumentsBusinessException(PERSON_DOES_NOT_EXIST);

        } else {

            Person person = optPerson.get();

            List<AccountID> accounts = person.getListOfAccounts();

            for (AccountID accountID : accounts) {
                Optional<Account> optAccount = accountRepository.findById(personEmailDTO.getEmail(), accountID.getDenomination().getDenomination());

                Account account = optAccount.get();

                AccountDTO accountDTO = AccountDTOAssembler.createDTOFromPrimitiveTypes(account.getAccountID().getDenomination().getDenomination(), account.getDescription().getDescription());

                accountsDTO.add(accountDTO);

            }

            return AccountsDTOAssembler.createDTOFromDomainObject(accountsDTO);
        }
    }

    @Transactional
    public CategoriesDTO getPersonCategories(PersonEmailDTO personEmailDTO) {
        PersonID personID = PersonID.createPersonID(personEmailDTO.getEmail());
        Optional<Person> optPerson = personRepository.findById(personID);

        if (!optPerson.isPresent()) {

            throw new NotFoundArgumentsBusinessException(PERSON_DOES_NOT_EXIST);

        } else {

            Person person = optPerson.get();

            List<CategoryID> categories = person.getListOfCategories();

            return CategoriesDTOAssembler.createDTOFromDomainObject(categories);
        }
    }

    public SiblingsDTO getPersonSiblings(PersonEmailDTO personEmailDTO) {
        PersonID personID = PersonID.createPersonID(personEmailDTO.getEmail());
        Optional<Person> optPerson = personRepository.findById(personID);

        if (!optPerson.isPresent()) {

            throw new NotFoundArgumentsBusinessException(PERSON_DOES_NOT_EXIST);

        } else {

            Person person = optPerson.get();

            List<PersonID> siblings = person.getListOfSiblings();

            return SiblingsDTOAssembler.createDTOFromDomainObject(siblings);
        }
    }

    @Transactional
    public List<GroupDTO> getPersonGroups(PersonEmailDTO personEmailDTO) {
        PersonID personID = PersonID.createPersonID(personEmailDTO.getEmail());
        Optional<Person> optPerson = personRepository.findById(personID);

        if (!optPerson.isPresent()) {

            throw new NotFoundArgumentsBusinessException(PERSON_DOES_NOT_EXIST);

        } else {
            List<Group> listGroups = this.groupRepository.findAll();

            List<GroupDTO> listToReturn = new ArrayList<>();

            for (Group group : listGroups) {
                if (group.getAllMembers().contains(personID)) {
                    listToReturn.add(GroupDTOAssembler.createDTOFromDomainObject(group.getGroupID().getDenomination(), group.getDescription(), group.getDateOfCreation()));
                }
            }
            return listToReturn;
        }
    }

    //-----------------------------------------   New   -------------------------------------------//


    public CreatePersonDTO createAndSavePerson(CreatePersonDTO createPersonDTO) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        Person newPerson = Person.createPerson
                (createPersonDTO.getEmail(),
                        createPersonDTO.getName(),
                        LocalDate.parse(createPersonDTO.getBirthdate().toString()),
                        createPersonDTO.getBirthplace());

        Person newPersonSaved = personRepository.save(newPerson);

        CreatePersonDTO personDTO = CreatePersonDTOAssembler.createDTOFromPrimitiveTypes
                (newPersonSaved.getEmail().getEmail(),
                        newPersonSaved.getName().getName(),
                        newPersonSaved.getBirthdate().getBirthdate().format(formatter),
                        newPersonSaved.getBirthplace().getBirthplace());

        return personDTO;
    }


    @Transactional
    public boolean addAddressToPerson(PersonID id, Address address) {

        Optional<Person> opPerson = personRepository.findById(id);
        if (!opPerson.isPresent())
            throw new NotFoundArgumentsBusinessException(PERSON_DOES_NOT_EXIST);

        Person person = opPerson.get();
        boolean success = person.addAddress(address.getStreet(), address.getDoorNumber(), address.getPostCode(), address.getCity(), address.getCountry());
        if (success) {
            return personRepository.addAndSaveAddress(person);
        } else
            throw new NotFoundArgumentsBusinessException(ADDRESS_ALREADY_EXIST);
    }


    @Transactional
    public boolean addMotherToPerson(PersonID id, PersonID mother) {

        Optional<Person> opPerson = personRepository.findById(id);
        if (!opPerson.isPresent())
            throw new NotFoundArgumentsBusinessException(PERSON_DOES_NOT_EXIST);

        Optional<Person> opPersonMother = personRepository.findById(mother);
        if (!opPersonMother.isPresent())
            throw new NotFoundArgumentsBusinessException(PERSON_DOES_NOT_EXIST);

        Person person = opPerson.get();
        boolean success = person.addMother(mother);
        if (success) {
            return personRepository.addAndSaveMother(person);
        } else
            throw new NotFoundArgumentsBusinessException(MOTHER_ALREADY_EXIST);
    }

    @Transactional
    public boolean addFatherToPerson(PersonID id, PersonID father) {

        Optional<Person> opPerson = personRepository.findById(id);
        if (!opPerson.isPresent())
            throw new NotFoundArgumentsBusinessException(PERSON_DOES_NOT_EXIST);

        Optional<Person> opPersonFather = personRepository.findById(father);
        if (!opPersonFather.isPresent())
            throw new NotFoundArgumentsBusinessException(PERSON_DOES_NOT_EXIST);

        Person person = opPerson.get();
        boolean success = person.addFather(father);
        if (success) {
            return personRepository.addAndSaveFather(person);
        } else
            throw new NotFoundArgumentsBusinessException(FATHER_ALREADY_EXIST);
    }

    @Transactional
    public boolean addSiblingToPerson(PersonID personID, PersonID siblingID) {

        Optional<Person> opPerson = personRepository.findById(personID);
        if (!opPerson.isPresent())
            throw new NotFoundArgumentsBusinessException(PERSON_DOES_NOT_EXIST);

        Optional<Person> opSibling = personRepository.findById(siblingID);
        if (!opSibling.isPresent())
            throw new NotFoundArgumentsBusinessException(PERSON_DOES_NOT_EXIST);

        Person person = opPerson.get();
        boolean success = person.addSibling(siblingID);
        if (success) {
            return personRepository.addAndSaveSibling(person, siblingID);
        } else
            throw new NotFoundArgumentsBusinessException(SIBLING_ALREADY_EXIST);
    }

    @Transactional
    public boolean addCategoryToPerson(CreatePersonCategoryDTO createPersonCategoryDTO) {

        // Create PersonID
        PersonID personID = PersonID.createPersonID(createPersonCategoryDTO.getEmail());

        // Assign the denomination argument to a variable
        String denomination = createPersonCategoryDTO.getDenomination();

        Optional<Person> opPerson = personRepository.findById(personID);
        if (!opPerson.isPresent())
            throw new NotFoundArgumentsBusinessException(PERSON_DOES_NOT_EXIST);

        Optional<Category> opCategory = categoryRepository.findById(personID.getEmail().getEmail(), denomination);
        if (opCategory.isPresent())
            throw new NotFoundArgumentsBusinessException(CATEGORY_ALREADY_EXIST);

        Person person = opPerson.get();
        boolean success = person.addCategory(CategoryID.createCategoryID(denomination, personID));
        if (success) {
            return personRepository.addAndSaveCategory(person);
        } else
            throw new NotFoundArgumentsBusinessException(CATEGORY_ALREADY_EXIST);
    }

    @Transactional
    public boolean addAccountToPerson(CreatePersonAccountDTO createPersonAccountDTO) {

        // Create PersonID
        PersonID personID = PersonID.createPersonID(createPersonAccountDTO.getEmail());

        // Assign the denomination argument to a variable
        String denomination = createPersonAccountDTO.getDenomination();

        // Assign the denomination argument to a variable
        String description = createPersonAccountDTO.getDescription();

        Optional<Person> opPerson = personRepository.findById(personID);
        if (!opPerson.isPresent())
            throw new NotFoundArgumentsBusinessException(PERSON_DOES_NOT_EXIST);

        Optional<Account> opAccount = accountRepository.findById(personID.getEmail().getEmail(), denomination);
        if (opAccount.isPresent())
            throw new NotFoundArgumentsBusinessException(ACCOUNT_ALREADY_EXIST);

        Person person = opPerson.get();
        boolean success = person.addAccount(AccountID.createAccountID(denomination, personID));
        if (success) {
            personRepository.addAndSaveAccount(person, description);
            return true;
        } else
            throw new NotFoundArgumentsBusinessException(ACCOUNT_ALREADY_EXIST);
    }


    @Transactional
    public boolean addPersonTransaction(CreatePersonTransactionDTO createPersonTransactionDTO) {

        // Create PersonID
        PersonID personID = PersonID.createPersonID(createPersonTransactionDTO.getEmail());

        // Assign the category argument to a variable
        String category = createPersonTransactionDTO.getDenominationCategory();

        // Assign the categoryID argument to a variable
        CategoryID categoryID = CategoryID.createCategoryID(category, personID);

        // Assign the credAccount argument to a variable
        String credAccount = createPersonTransactionDTO.getDenominationAccountCred();

        // Assign the credAccountID argument to a variable
        AccountID creditAccountID = AccountID.createAccountID(credAccount, personID);

        // Assign the debAccount argument to a variable
        String debAccount = createPersonTransactionDTO.getDenominationAccountDeb();

        // Assign the dedAccountID argument to a variable
        AccountID debitAccountID = AccountID.createAccountID(debAccount, personID);

        // Assign the description argument to a variable
        String description = createPersonTransactionDTO.getDescription();

        // Assign the type argument to a variable
        String type = createPersonTransactionDTO.getType();

        // Assign the amount argument to a variable
        double amount = createPersonTransactionDTO.getAmount();

        Optional<Person> opPerson = personRepository.findById(personID);
        if (!opPerson.isPresent())
            throw new NotFoundArgumentsBusinessException(PERSON_DOES_NOT_EXIST);

        Optional<Category> opCategory = categoryRepository.findById(personID.getEmail().getEmail(), category);
        if (!opCategory.isPresent())
            throw new NotFoundArgumentsBusinessException(CATEGORY_DOES_NOT_EXIST);

        Optional<Account> opDebAccount = accountRepository.findById(personID.getEmail().getEmail(), debAccount);
        if (!opDebAccount.isPresent())
            throw new NotFoundArgumentsBusinessException(ACCOUNT_DOES_NOT_EXIST);

        Optional<Account> opCredAccount = accountRepository.findById(personID.getEmail().getEmail(), credAccount);
        if (!opCredAccount.isPresent())
            throw new NotFoundArgumentsBusinessException(ACCOUNT_DOES_NOT_EXIST);

        Person person = opPerson.get();

        Optional<Ledger> opLedger = ledgerRepository.findById(person.getLedgerID());
        if (!opLedger.isPresent())
            throw new NotFoundArgumentsBusinessException(LEDGER_DOES_NOT_EXIST);

        // Define the date of the transaction
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate date = LocalDate.parse(createPersonTransactionDTO.getDate(), formatter);

        Ledger ledger = opLedger.get();
        boolean success = ledger.createAndAddTransactionWithDate(categoryID, type, description, amount, date,
                debitAccountID, creditAccountID);

        if (success) {
            ledgerRepository.addAndSaveTransaction(ledger);
            return true;
        } else
            return false;
    }
}