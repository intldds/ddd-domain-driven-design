package com.finance.project.applicationLayer.applicationServices.personServices;

import com.finance.project.domainLayer.exceptions.InvalidArgumentsBusinessException;
import com.finance.project.domainLayer.exceptions.NotFoundArgumentsBusinessException;
import com.finance.project.domainLayer.repositoriesInterfaces.IAccountRepository;
import com.finance.project.domainLayer.repositoriesInterfaces.ILedgerRepository;
import com.finance.project.domainLayer.repositoriesInterfaces.IPersonRepository;
import com.finance.project.dtos.dtos.PersonSearchAccountRecordsInDTO;
import com.finance.project.dtos.dtos.PersonSearchAccountRecordsOutDTO;
import com.finance.project.dtos.dtosAssemblers.SearchAccountRecordsOutDTOAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.finance.project.domainLayer.domainEntities.aggregates.ledger.Ledger;
import com.finance.project.domainLayer.domainEntities.aggregates.ledger.Transaction;
import com.finance.project.domainLayer.domainEntities.aggregates.person.Person;
import com.finance.project.domainLayer.domainEntities.vosShared.AccountID;
import com.finance.project.domainLayer.domainEntities.vosShared.LedgerID;
import com.finance.project.domainLayer.domainEntities.vosShared.PersonID;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class PersonSearchAccountRecordsService {

    @Autowired
    private final IPersonRepository personRepository;
    @Autowired
    private final IAccountRepository accountRepository;
    @Autowired
    private final ILedgerRepository ledgerRepository;


    // Return messages

    /**
     * The constant PERSON_DOES_NOT_EXIST.
     */
    public final static String PERSON_DOES_NOT_EXIST = "Person does not exist in the system";
    /**
     * The constant ACCOUNT_DOES_NOT_EXIST.
     */
    public final static String ACCOUNT_DOES_NOT_EXIST = "Account does not exist in the system";
    /**
     * The constant TIME_PERIOD_OUTSIDE_OF_RECORDS_RANGE.
     */
    public final static String TIME_PERIOD_OUTSIDE_OF_RECORDS_RANGE = "The time period provided falls outside the range of the ledger records";
    /**
     * The constant NO_TRANSACTIONS_TO_REPORT.
     */
    public static final String NO_TRANSACTIONS_TO_REPORT = "Ledger has no transactions within the searched period";
    /**
     * The constant DATES_IN_REVERSE_ORDER.
     */
    public final static String DATES_IN_REVERSE_ORDER = "Check the start and end dates for the period, since start date cannot be later than end date";
    /**
     * The constant EMPTY_LEDGER.
     */
    public final static String EMPTY_LEDGER = "Ledger is empty";
    /**
     * The constant ACCOUNT_NAME_FIELD_MISSING.
     */
    public final static String ACCOUNT_NAME_FIELD_MISSING = "Search results cannot be displayed: account name is missing";
    /**
     * The constant START_DATE_FIELD_MISSING.
     */
    public final static String START_DATE_FIELD_MISSING = "Search results cannot be displayed: start date is missing";
    /**
     * The constant END_DATE_FIELD_MISSING.
     */
    public final static String END_DATE_FIELD_MISSING = "Search results cannot be displayed: end date is missing";


    public PersonSearchAccountRecordsService(IPersonRepository personRepository, IAccountRepository accountRepository, ILedgerRepository ledgerRepository) {
        this.personRepository = personRepository;
        this.accountRepository = accountRepository;
        this.ledgerRepository = ledgerRepository;
    }

    // Getters

    private PersonID getPersonID(PersonSearchAccountRecordsInDTO personSearchAccountRecordsInDTO) {
        return PersonID.createPersonID(personSearchAccountRecordsInDTO.getPersonEmail());
    }

    public Optional<Person> getOptPerson(PersonSearchAccountRecordsInDTO personSearchAccountRecordsInDTO) {

        PersonID personID = getPersonID(personSearchAccountRecordsInDTO);
        Optional<Person> optPerson = personRepository.findById(personID);

        //If person does not exist, account transactions cannot be reported
        if (!optPerson.isPresent()) {
            throw new InvalidArgumentsBusinessException(PERSON_DOES_NOT_EXIST);

        } else {
            return optPerson;
        }
    }

    public AccountID getAccountID(PersonSearchAccountRecordsInDTO personSearchAccountRecordsInDTO) {

        String accountNameField = personSearchAccountRecordsInDTO.getAccountDenomination();

        if (accountNameField.isEmpty()) {
            throw new NotFoundArgumentsBusinessException(ACCOUNT_NAME_FIELD_MISSING);

        } else {

            AccountID accountID = AccountID.createAccountID(personSearchAccountRecordsInDTO.getAccountDenomination(), getPersonID(personSearchAccountRecordsInDTO));
            boolean accountExistsInRepo = accountRepository.existsById(accountID);

            //If account does not exist, account transactions cannot be reported
            if (!accountExistsInRepo) {
                throw new InvalidArgumentsBusinessException(ACCOUNT_DOES_NOT_EXIST);

            } else {
                return accountID;
            }
        }
    }

    public Optional<Ledger> getOptLedger(PersonSearchAccountRecordsInDTO personSearchAccountRecordsInDTO) {

        Optional<Person> person = getOptPerson(personSearchAccountRecordsInDTO);
        LocalDate startDateLD = getStartDate(personSearchAccountRecordsInDTO);
        LocalDate endDateLD = getEndDate(personSearchAccountRecordsInDTO);

        // If person exists, it will have a ledger
        LedgerID ledgerID = person.get().getLedgerID();
        Optional<Ledger> optLedger = ledgerRepository.findById(ledgerID);


        // To alert about the dates being used in the search: start date cannot be after end date
        if (startDateLD.isAfter(endDateLD)) {
            throw new InvalidArgumentsBusinessException(DATES_IN_REVERSE_ORDER);
        }

        // Check if ledger transactions dates are within the search dates
        if (optLedger.get().getRecords().isEmpty()) {
            throw new NotFoundArgumentsBusinessException(EMPTY_LEDGER);
        }

        boolean recordsOutOfSearchRange = (endDateLD.isBefore(optLedger.get().getEarliestTransactionDate()) || startDateLD.isAfter(optLedger.get().getLatestTransactionDate()));
        if (recordsOutOfSearchRange) {
            throw new NotFoundArgumentsBusinessException(TIME_PERIOD_OUTSIDE_OF_RECORDS_RANGE);
        }

        return optLedger;
    }


    public LocalDate getStartDate(PersonSearchAccountRecordsInDTO personSearchAccountRecordsInDTO) {

        String startDateField = personSearchAccountRecordsInDTO.getStartDate();

        if (startDateField.isEmpty()) {
            throw new NotFoundArgumentsBusinessException(START_DATE_FIELD_MISSING);
        } else {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            return LocalDate.parse(personSearchAccountRecordsInDTO.getStartDate(), formatter);
        }
    }

    public LocalDate getEndDate(PersonSearchAccountRecordsInDTO personSearchAccountRecordsInDTO) {

        String endDateField = personSearchAccountRecordsInDTO.getEndDate();

        if (endDateField.isEmpty()) {
            throw new NotFoundArgumentsBusinessException(END_DATE_FIELD_MISSING);
        } else {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            return LocalDate.parse(personSearchAccountRecordsInDTO.getEndDate(), formatter);
        }
    }


    // Search Person Account Records service

    public PersonSearchAccountRecordsOutDTO getPersonAccountTransactionsWithinPeriod(PersonSearchAccountRecordsInDTO personSearchAccountRecordsInDTO) {

        // If no exception was thrown from getOptLedger(), the ledger can be retrieved
        Optional<Ledger> optionalLedger = getOptLedger(personSearchAccountRecordsInDTO);

        // If a ledger has been retrieved, person and account exist
        AccountID accountID = getAccountID(personSearchAccountRecordsInDTO);

        // Retrieve all transactions related with the account, within a given period
        LocalDate startDateLD = getStartDate(personSearchAccountRecordsInDTO);
        LocalDate endDateLD = getEndDate(personSearchAccountRecordsInDTO);

        // Deals with exception on Ledger method
        try {
            optionalLedger.get().getAccountRecordsBetweenTwoDates(accountID, startDateLD, endDateLD);

        } catch (IllegalStateException e) {
            throw new NotFoundArgumentsBusinessException(NO_TRANSACTIONS_TO_REPORT);
        }

        List<Transaction> accountRecordsWithinPeriod = optionalLedger.get().getAccountRecordsBetweenTwoDates(accountID, startDateLD, endDateLD);

        return SearchAccountRecordsOutDTOAssembler.accountTransactionsOutDTO((ArrayList<Transaction>) accountRecordsWithinPeriod);
    }

}

