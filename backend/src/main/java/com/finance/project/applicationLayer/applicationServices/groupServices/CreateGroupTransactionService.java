package com.finance.project.applicationLayer.applicationServices.groupServices;

import com.finance.project.domainLayer.domainEntities.vosShared.*;
import com.finance.project.domainLayer.exceptions.InvalidArgumentsBusinessException;
import com.finance.project.domainLayer.exceptions.NotFoundArgumentsBusinessException;
import com.finance.project.domainLayer.repositoriesInterfaces.IAccountRepository;
import com.finance.project.domainLayer.repositoriesInterfaces.ICategoryRepository;
import com.finance.project.domainLayer.repositoriesInterfaces.IGroupRepository;
import com.finance.project.domainLayer.repositoriesInterfaces.ILedgerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.finance.project.dtos.dtos.CreateGroupTransactionDTO;
import com.finance.project.dtos.dtos.DeleteGroupTransactionDTO;
import com.finance.project.dtos.dtos.GroupDTO;
import com.finance.project.dtos.dtos.UpdateGroupTransactionDTO;
import com.finance.project.dtos.dtosAssemblers.GroupDTOAssembler;
import com.finance.project.domainLayer.domainEntities.aggregates.group.Group;
import com.finance.project.domainLayer.domainEntities.aggregates.ledger.Ledger;

import java.time.LocalDate;
import java.util.Optional;


@Service
public class CreateGroupTransactionService {

    @Autowired
    private IGroupRepository groupRepository;
    @Autowired
    private ICategoryRepository categoryRepository;
    @Autowired
    private IAccountRepository accountRepository;
    @Autowired
    private ILedgerRepository ledgerRepository;


    /**
     * The constant GROUP_DOES_NOT_EXIST.
     */
    public final static String GROUP_DOES_NOT_EXIST = "Group does not exist in the system";
    /**
     * The constant PERSON_NOT_MEMBER.
     */
    public final static String PERSON_NOT_MEMBER = "Person is not member of the group";
    /**
     * The constant NEED_TO_CREATE_CATEGORY.
     */
    public final static String NEED_TO_CREATE_CATEGORY = "Category does not exist; it needs to be created";
    /**
     * The constant NEED_TO_CREATE_ACCOUNT_TO_CREDIT.
     */
    public final static String NEED_TO_CREATE_ACCOUNT_TO_CREDIT = "Account to be credited does not exist; it needs to be created";
    /**
     * The constant NEED_TO_CREATE_ACCOUNT_TO_DEBIT.
     */
    public final static String NEED_TO_CREATE_ACCOUNT_TO_DEBIT = "Account to be debited does not exist; it needs to be created";


    public CreateGroupTransactionService(IGroupRepository groupRepository, IAccountRepository accountRepository,
                                         ICategoryRepository categoryRepository, ILedgerRepository ledgerRepository) {
        this.groupRepository = groupRepository;
        this.categoryRepository = categoryRepository;
        this.accountRepository = accountRepository;
        this.ledgerRepository = ledgerRepository;
    }


    public GroupDTO createGroupTransaction(CreateGroupTransactionDTO createGroupTransactionDTO) {

        Group group;

        GroupID groupID = GroupID.createGroupID(createGroupTransactionDTO.getGroupDenomination());
        Optional<Group> opGroup = groupRepository.findById(groupID);

        // If group does not exist, transaction will not be created
        if (!opGroup.isPresent()) {
            throw new NotFoundArgumentsBusinessException(GROUP_DOES_NOT_EXIST);

        } else {
            group = opGroup.get();

            //If person is not member of the group, transaction cannot be created
            //Member of the group means that person may be person in charge, or merely member
            PersonID personID = PersonID.createPersonID(createGroupTransactionDTO.getPersonGroupMemberEmail());
            boolean isPersonGroupMember = group.isPersonAlreadyMember(personID);

            //If category does not exist, needs to be created
            CategoryID categoryID = CategoryID.createCategoryID(createGroupTransactionDTO.getCategoryDenomination(), groupID);
            boolean categoryExistsInRepo = categoryRepository.existsById(categoryID);

            //If account to be debited does not exist, needs to be created
            AccountID accountToDebitID = AccountID.createAccountID(createGroupTransactionDTO.getAccountToDebitName(), groupID);
            boolean accountToDebitExistsInRepo = accountRepository.existsById(accountToDebitID);

            //If account to be credited does not exist, needs to be created
            AccountID accountToCreditID = AccountID.createAccountID(createGroupTransactionDTO.getAccountToCreditName(), groupID);
            boolean accountToCreditExistsInRepo = accountRepository.existsById(accountToCreditID);

            //If ledger does not exist
            LedgerID ledgerID = group.getLedgerID();
            Optional<Ledger> optLedger = ledgerRepository.findById(ledgerID);

            if (!isPersonGroupMember) {
                throw new InvalidArgumentsBusinessException(PERSON_NOT_MEMBER);

            } else if (!(categoryExistsInRepo)) {
                throw new NotFoundArgumentsBusinessException(NEED_TO_CREATE_CATEGORY);

            } else if (!(accountToDebitExistsInRepo)) {
                throw new NotFoundArgumentsBusinessException(NEED_TO_CREATE_ACCOUNT_TO_DEBIT);

            } else if (!(accountToCreditExistsInRepo)) {
                throw new NotFoundArgumentsBusinessException(NEED_TO_CREATE_ACCOUNT_TO_CREDIT);

            } else {
                LocalDate date = LocalDate.parse(createGroupTransactionDTO.getDate());

                // If all information required for creating a transaction is available, transaction can be created and added to the group's ledger
                Ledger ledger = optLedger.get();
                ledger.createAndAddTransactionWithDate(categoryID, createGroupTransactionDTO.getTransactionType(), createGroupTransactionDTO.getTransactionDescription(), createGroupTransactionDTO.getTransactionAmount(), date, accountToDebitID, accountToCreditID);
                ledgerRepository.addAndSaveTransaction(ledger);
            }
        }

        GroupDTO groupDTO = GroupDTOAssembler.createDTOFromDomainObject(
                group.getGroupID().getDenomination(),
                group.getDescription(),
                group.getDateOfCreation(),
                group.getLedgerID());

        return groupDTO;
    }


    // Update Transaction

    public GroupDTO updateGroupTransaction(UpdateGroupTransactionDTO updateGroupTransactionDTO) {

        Group group;

        GroupID groupID = GroupID.createGroupID(updateGroupTransactionDTO.getGroupDenomination());
        Optional<Group> opGroup = groupRepository.findById(groupID);

        // If group does not exist, transaction will not be created
        if (!opGroup.isPresent()) {
            throw new NotFoundArgumentsBusinessException(GROUP_DOES_NOT_EXIST);

        } else {
            group = opGroup.get();

            // If person is not member of the group, transaction cannot be created
            // Member of the group means that person may be person in charge, or merely member
            PersonID personID = PersonID.createPersonID(updateGroupTransactionDTO.getPersonGroupMemberEmail());
            boolean isPersonGroupMember = group.isPersonAlreadyMember(personID);

            // If category does not exist, needs to be created
            CategoryID categoryID = CategoryID.createCategoryID(updateGroupTransactionDTO.getCategoryDenomination(), groupID);
            boolean categoryExistsInRepo = categoryRepository.existsById(categoryID);

            // If account to be debited does not exist, needs to be created
            AccountID accountToDebitID = AccountID.createAccountID(updateGroupTransactionDTO.getAccountToDebitName(), groupID);
            boolean accountToDebitExistsInRepo = accountRepository.existsById(accountToDebitID);

            // If account to be credited does not exist, needs to be created
            AccountID accountToCreditID = AccountID.createAccountID(updateGroupTransactionDTO.getAccountToCreditName(), groupID);
            boolean accountToCreditExistsInRepo = accountRepository.existsById(accountToCreditID);

            // If ledger does not exist
            LedgerID ledgerID = group.getLedgerID();
            Optional<Ledger> optLedger = ledgerRepository.findById(ledgerID);

            if (!isPersonGroupMember) {
                throw new InvalidArgumentsBusinessException(PERSON_NOT_MEMBER);

            } else if (!(categoryExistsInRepo)) {
                throw new NotFoundArgumentsBusinessException(NEED_TO_CREATE_CATEGORY);

            } else if (!(accountToDebitExistsInRepo)) {
                throw new NotFoundArgumentsBusinessException(NEED_TO_CREATE_ACCOUNT_TO_DEBIT);

            } else if (!(accountToCreditExistsInRepo)) {
                throw new NotFoundArgumentsBusinessException(NEED_TO_CREATE_ACCOUNT_TO_CREDIT);

            } else {

                // If all information required for creating a transaction is available, transaction can be created and added to the group's ledger
                Ledger ledger = optLedger.get();
                ledgerRepository.updateTransaction(ledger, updateGroupTransactionDTO);
            }
        }
        GroupDTO groupDTO = GroupDTOAssembler.createDTOFromDomainObject(
                group.getGroupID().getDenomination(),
                group.getDescription(),
                group.getDateOfCreation(),
                group.getLedgerID());

        return groupDTO;
    }


    // Delete transaction

    public GroupDTO deleteGroupTransaction(DeleteGroupTransactionDTO deleteGroupTransactionDTO) {

        Group group;

        GroupID groupID = GroupID.createGroupID(deleteGroupTransactionDTO.getGroupDenomination());
        Optional<Group> opGroup = groupRepository.findById(groupID);

        // If group does not exist, transaction will not be created
        if (!opGroup.isPresent()) {
            throw new NotFoundArgumentsBusinessException(GROUP_DOES_NOT_EXIST);

        } else {
            group = opGroup.get();

            // If person is not member of the group, transaction cannot be created
            // Member of the group means that person may be person in charge, or merely member
            PersonID personID = PersonID.createPersonID(deleteGroupTransactionDTO.getPersonGroupMemberEmail());
            boolean isPersonGroupMember = group.isPersonAlreadyMember(personID);

            // If ledger does not exist
            LedgerID ledgerID = group.getLedgerID();
            Optional<Ledger> optLedger = ledgerRepository.findById(ledgerID);

            if (!isPersonGroupMember) {
                throw new InvalidArgumentsBusinessException(PERSON_NOT_MEMBER);

            } else {
                // If all information required for creating a transaction is available, transaction can be created and added to the group's ledger
                Ledger ledger = optLedger.get();
                ledgerRepository.deleteTransaction(ledger, deleteGroupTransactionDTO);
            }
        }

        GroupDTO groupDTO = GroupDTOAssembler.createDTOFromDomainObject(
                group.getGroupID().getDenomination(),
                group.getDescription(),
                group.getDateOfCreation(),
                group.getLedgerID());

        return groupDTO;
    }
}

