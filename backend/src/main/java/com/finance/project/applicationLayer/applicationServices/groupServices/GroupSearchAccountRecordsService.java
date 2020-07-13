package com.finance.project.applicationLayer.applicationServices.groupServices;

import com.finance.project.domainLayer.exceptions.InvalidArgumentsBusinessException;
import com.finance.project.domainLayer.exceptions.NotFoundArgumentsBusinessException;
import com.finance.project.domainLayer.repositoriesInterfaces.IAccountRepository;
import com.finance.project.domainLayer.repositoriesInterfaces.IGroupRepository;
import com.finance.project.domainLayer.repositoriesInterfaces.ILedgerRepository;
import com.finance.project.dtos.dtosAssemblers.SearchAccountRecordsOutDTOAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.finance.project.dtos.dtos.GroupSearchAccountRecordsInDTO;
import com.finance.project.dtos.dtos.PersonSearchAccountRecordsOutDTO;
import com.finance.project.domainLayer.domainEntities.aggregates.group.Group;
import com.finance.project.domainLayer.domainEntities.aggregates.ledger.Ledger;
import com.finance.project.domainLayer.domainEntities.aggregates.ledger.Transaction;
import com.finance.project.domainLayer.domainEntities.vosShared.AccountID;
import com.finance.project.domainLayer.domainEntities.vosShared.GroupID;
import com.finance.project.domainLayer.domainEntities.vosShared.LedgerID;
import com.finance.project.domainLayer.domainEntities.vosShared.PersonID;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class GroupSearchAccountRecordsService {

    @Autowired
    private IGroupRepository groupRepository;
    @Autowired
    private IAccountRepository accountRepository;
    @Autowired
    private ILedgerRepository ledgerRepository;


    // Return messages

    /**
     * The constant GROUP_DOES_NOT_EXIST.
     */
    public final static String GROUP_DOES_NOT_EXIST = "Group does not exist in the system";
    /**
     * The constant PERSON_NOT_MEMBER.
     */
    public final static String PERSON_NOT_MEMBER = "Person is not member of the group";
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


    public GroupSearchAccountRecordsService(IGroupRepository groupRepository, IAccountRepository accountRepository, ILedgerRepository ledgerRepository) {
        this.groupRepository = groupRepository;
        this.accountRepository = accountRepository;
        this.ledgerRepository = ledgerRepository;
    }


    // Private method to assemble groupID
    private GroupID getGroupID(GroupSearchAccountRecordsInDTO groupSearchAccountRecordsInDTO) {
        return GroupID.createGroupID(groupSearchAccountRecordsInDTO.getGroupDenomination());
    }

    // Getters

    public Optional<Group> getOptGroup(GroupSearchAccountRecordsInDTO groupSearchAccountRecordsInDTO) {

        GroupID groupID = getGroupID(groupSearchAccountRecordsInDTO);
        Optional<Group> optGroup = groupRepository.findById(groupID);

        // If group does not exist, account transactions cannot be reported
        if (!optGroup.isPresent()) {
            throw new InvalidArgumentsBusinessException(GROUP_DOES_NOT_EXIST);

        } else {

            // If person is not member of the group, account records report cannot be retrieved
            // Member of the group means that person may be person in charge, or merely member
            PersonID personID = PersonID.createPersonID(groupSearchAccountRecordsInDTO.getPersonEmail());
            boolean isPersonGroupMember = optGroup.get().isPersonAlreadyMember(personID);

            if (!isPersonGroupMember) {
                throw new InvalidArgumentsBusinessException(PERSON_NOT_MEMBER);

            } else {
                return optGroup;
            }
        }
    }


    public AccountID getAccountID(GroupSearchAccountRecordsInDTO groupSearchAccountRecordsInDTO) {

        String accountNameField = groupSearchAccountRecordsInDTO.getAccountDenomination();

        if (accountNameField.isEmpty()) {
            throw new NotFoundArgumentsBusinessException(ACCOUNT_NAME_FIELD_MISSING);

        } else {

            AccountID accountID = AccountID.createAccountID(groupSearchAccountRecordsInDTO.getAccountDenomination(), getGroupID(groupSearchAccountRecordsInDTO));
            boolean accountExistsInRepo = accountRepository.existsById(accountID);

            //If account does not exist, account transactions cannot be reported
            if (!accountExistsInRepo) {
                throw new InvalidArgumentsBusinessException(ACCOUNT_DOES_NOT_EXIST);

            } else {
                return accountID;
            }
        }
    }

    public LocalDate getStartDate(GroupSearchAccountRecordsInDTO groupSearchAccountRecordsInDTO) {

        String startDateField = groupSearchAccountRecordsInDTO.getStartDate();

        if (startDateField.isEmpty()) {
            throw new NotFoundArgumentsBusinessException(START_DATE_FIELD_MISSING);

        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(groupSearchAccountRecordsInDTO.getStartDate(), formatter);
        }
    }


    public LocalDate getEndDate(GroupSearchAccountRecordsInDTO groupSearchAccountRecordsInDTO) {

        String endDateField = groupSearchAccountRecordsInDTO.getEndDate();

        if (endDateField.isEmpty()) {
            throw new NotFoundArgumentsBusinessException(END_DATE_FIELD_MISSING);

        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(groupSearchAccountRecordsInDTO.getEndDate(), formatter);
        }
    }


    public Optional<Ledger> getOptLedger(GroupSearchAccountRecordsInDTO groupSearchAccountRecordsInDTO) {

        // On getOptGroup is checked if group exists and if person is member, and whether these fields are empty
        Optional<Group> group = getOptGroup(groupSearchAccountRecordsInDTO);

        // On getAccountID is checked if account exists and if the field is empty
        AccountID accountID = getAccountID(groupSearchAccountRecordsInDTO);

        // On getStartDate and getEndDate is checked if the fields are empty
        LocalDate startDateLD = getStartDate(groupSearchAccountRecordsInDTO);
        LocalDate endDateLD = getEndDate(groupSearchAccountRecordsInDTO);

        // If group exists, it will have a ledger
        LedgerID ledgerID = group.get().getLedgerID();
        Optional<Ledger> optLedger = ledgerRepository.findById(ledgerID);

        // To alert about the dates being used in the search: start date cannot be after end date
        if (startDateLD.isAfter(endDateLD)) {
            throw new InvalidArgumentsBusinessException(DATES_IN_REVERSE_ORDER);
        }

        // Check if ledger is empty
        if (optLedger.get().getRecords().isEmpty()) {
            throw new NotFoundArgumentsBusinessException(EMPTY_LEDGER);
        }

        // Check if ledger transactions dates are within the search dates
        boolean recordsOutOfSearchRange = (endDateLD.isBefore(optLedger.get().getEarliestTransactionDate()) || startDateLD.isAfter(optLedger.get().getLatestTransactionDate()));
        if (recordsOutOfSearchRange) {
            throw new NotFoundArgumentsBusinessException(TIME_PERIOD_OUTSIDE_OF_RECORDS_RANGE);
        }

        // Check if ledger has transactions to report for the searched period
        try {
            optLedger.get().getAccountRecordsBetweenTwoDates(accountID, startDateLD, endDateLD);

        } catch (IllegalStateException e) {
            throw new NotFoundArgumentsBusinessException(NO_TRANSACTIONS_TO_REPORT);
        }
        return optLedger;
    }


    // Search Account Records

    public PersonSearchAccountRecordsOutDTO getGroupAccountTransactionsWithinPeriod(GroupSearchAccountRecordsInDTO groupSearchAccountRecordsInDTO) {

        //If no exception was thrown from getOptLedger(), the ledger can be retrieved
        //getOptLedger will ensure to go through the checks regarding the existance of the
        // group, and whether person is member of the group

        Optional<Ledger> optionalLedger = getOptLedger(groupSearchAccountRecordsInDTO);

        AccountID accountID = getAccountID(groupSearchAccountRecordsInDTO);
        LocalDate startDateLD = getStartDate(groupSearchAccountRecordsInDTO);
        LocalDate endDateLD = getEndDate(groupSearchAccountRecordsInDTO);

        List<Transaction> accountRecordsWithinPeriod = optionalLedger.get().getAccountRecordsBetweenTwoDates(accountID, startDateLD, endDateLD);

        PersonSearchAccountRecordsOutDTO personSearchAccountRecordsOutDTO =
                SearchAccountRecordsOutDTOAssembler.accountTransactionsOutDTO((ArrayList<Transaction>) accountRecordsWithinPeriod);

        return personSearchAccountRecordsOutDTO;
    }
}
