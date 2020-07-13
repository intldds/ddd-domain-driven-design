package com.finance.project.applicationLayer.applicationServices.groupServices;

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
import com.finance.project.domainLayer.domainEntities.aggregates.person.Person;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class CreateGroupService {

    @Autowired
    private IPersonRepository personRepository;
    @Autowired
    private IGroupRepository groupRepository;
    @Autowired
    private ILedgerRepository ledgerRepository;
    @Autowired
    private ICategoryRepository categoryRepository;
    @Autowired
    private IAccountRepository accountRepository;


    /**
     * The constant SUCCESS.
     */
    public final static String SUCCESS = "Group created";
    /**
     * The constant PERSON_DOES_NOT_EXIST.
     */
    public final static String PERSON_DOES_NOT_EXIST = "Person does not exist";
    /**
     * The constant GROUP_ALREADY_EXISTS.
     */
    public final static String GROUP_ALREADY_EXISTS = "Group already exist";
    /**
     * The constant GROUP_DOES_NOT_EXIST.
     */
    public final static String GROUP_DOES_NOT_EXISTS = "Group does not exist";


    public CreateGroupService(IPersonRepository personRepository, IGroupRepository groupRepository,
                              ILedgerRepository ledgerRepository, IAccountRepository accountRepository) {
        this.personRepository = personRepository;
        this.groupRepository = groupRepository;
        this.ledgerRepository = ledgerRepository;
        this.accountRepository = accountRepository;
    }


    public GroupDTO createGroupAsPersonInCharge(CreateGroupDTO createGroupDTO) {
        PersonID personID = PersonID.createPersonID(createGroupDTO.getEmail());
        Optional<Person> opPerson = personRepository.findById(personID);

        GroupDTO groupDTO;

        if (opPerson.isPresent()) {
            GroupID groupID = GroupID.createGroupID(createGroupDTO.getDenomination());

            Optional<Group> opGroup = groupRepository.findById(groupID);

            if (!opGroup.isPresent()) {

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                Group newGroup = Group.createGroup(createGroupDTO.getDenomination(), createGroupDTO.getDescription(), LocalDate.now().format(formatter), personID);

                Group newGroupSaved = groupRepository.save(newGroup);

                groupDTO = GroupDTOAssembler.createDTOFromDomainObject(
                        newGroupSaved.getGroupID().getDenomination(),
                        newGroupSaved.getDescription(),
                        newGroup.getDateOfCreation());

            } else {
                throw new InvalidArgumentsBusinessException(GROUP_ALREADY_EXISTS);
            }

        } else {
            throw new NotFoundArgumentsBusinessException(PERSON_DOES_NOT_EXIST);
        }

        return groupDTO;
    }


    // Getters

    public GroupDTO getGroupByDenomination(String denomination) {
        GroupID groupID = GroupID.createGroupID(denomination);
        Optional<Group> optGroup = groupRepository.findById(groupID);

        if (!optGroup.isPresent()) {

            throw new NotFoundArgumentsBusinessException(GROUP_DOES_NOT_EXISTS);

        } else {

            Group group = optGroup.get();

            Denomination groupDenomination = group.getGroupID().getDenomination();
            Description groupDescription = group.getDescription();
            DateOfCreation groupDateOfCreation = group.getDateOfCreation();

            return GroupDTOAssembler.createDTOFromDomainObject(groupDenomination, groupDescription, groupDateOfCreation, group.getLedgerID());
        }
    }

    public GroupAdminsDTO getGroupAdmins(String denomination) {
        GroupID groupID = GroupID.createGroupID(denomination);
        Optional<Group> optGroup = groupRepository.findById(groupID);

        if (!optGroup.isPresent()) {

            throw new NotFoundArgumentsBusinessException(GROUP_DOES_NOT_EXISTS);

        } else {

            Group group = optGroup.get();

            List<PersonID> peopleInCharge = group.getPeopleInCharge();

            return GroupAdminsDTOAssembler.createDTOFromDomainObject(peopleInCharge);
        }
    }

    public GroupMembersDTO getGroupMembers(String denomination) {
        GroupID groupID = GroupID.createGroupID(denomination);
        Optional<Group> optGroup = groupRepository.findById(groupID);

        if (!optGroup.isPresent()) {

            throw new NotFoundArgumentsBusinessException(GROUP_DOES_NOT_EXISTS);

        } else {

            Group group = optGroup.get();

            List<PersonID> members = group.getMembers();

            return GroupMembersDTOAssembler.createDTOFromDomainObject(members);
        }
    }

    public GroupAllMembersDTO getGroupAllMembers(String denomination) {
        GroupID groupID = GroupID.createGroupID(denomination);
        Optional<Group> optGroup = groupRepository.findById(groupID);

        List<GroupMemberClearanceDTO> groupClearance = new ArrayList<>();

        if (!optGroup.isPresent()) {

            throw new NotFoundArgumentsBusinessException(GROUP_DOES_NOT_EXISTS);

        } else {

            Group group = optGroup.get();

            List<PersonID> members = group.getMembers();
            List<PersonID> admins = group.getPeopleInCharge();

            for (PersonID personID : admins) {
                GroupMemberClearanceDTO admin = GroupMemberClearanceDTOAssembler.createDTOFromDomainObject(personID.getEmail().getEmail(), "admin");
                groupClearance.add(admin);
            }

            for (PersonID personID : members) {
                GroupMemberClearanceDTO member = GroupMemberClearanceDTOAssembler.createDTOFromDomainObject(personID.getEmail().getEmail(), "member");
                groupClearance.add(member);
            }

            return GroupAllMembersDTOAssembler.createDTOFromDomainObject(groupClearance);
        }
    }

    public TransactionsDTO getGroupLedger(String denomination) {
        GroupID groupID = GroupID.createGroupID(denomination);
        Optional<Group> optGroup = groupRepository.findById(groupID);

        if (!optGroup.isPresent()) {

            throw new NotFoundArgumentsBusinessException(GROUP_DOES_NOT_EXISTS);

        } else {

            Group group = optGroup.get();

            LedgerID ledgerID = group.getLedgerID();
            Optional<Ledger> optLedger = ledgerRepository.findById(ledgerID);
            Ledger ledger = optLedger.get();
            List<TransactionDTOout> transactions = ledger.getRecordsAsDTO();

            return TransactionsDTOAssembler.createDTOFromPrimitiveTypes(transactions);
        }
    }

    public AccountsDTO getGroupAccounts(String denomination) {

        List<AccountDTO> accountsDTO = new ArrayList<>();

        GroupID groupID = GroupID.createGroupID(denomination);
        Optional<Group> optGroup = groupRepository.findById(groupID);

        if (!optGroup.isPresent()) {

            throw new NotFoundArgumentsBusinessException(GROUP_DOES_NOT_EXISTS);

        } else {

            Group group = optGroup.get();

            List<AccountID> accounts = group.getAccounts();

            for (AccountID accountID : accounts) {
                Optional<Account> optAccount = accountRepository.findById(denomination, accountID.getDenomination().getDenomination());

                Account account = optAccount.get();

                AccountDTO accountDTO = AccountDTOAssembler.createDTOFromPrimitiveTypes(account.getAccountID().getDenomination().getDenomination(), account.getDescription().getDescription());

                accountsDTO.add(accountDTO);
            }
            return AccountsDTOAssembler.createDTOFromDomainObject(accountsDTO);
        }
    }

    public boolean isAdmin(String groupDenomination, String personEmail) {

        boolean result;

        GroupID groupID = GroupID.createGroupID(groupDenomination);
        Optional<Group> optGroup = groupRepository.findById(groupID);

        if (!optGroup.isPresent()) {

            throw new NotFoundArgumentsBusinessException(GROUP_DOES_NOT_EXISTS);

        } else {

            Group group = optGroup.get();

            PersonID personID = PersonID.createPersonID(personEmail);

            result = group.isPersonPeopleInCharge(personID);

            return result;
        }
    }

    public CategoriesDTO getGroupCategories(String denomination) {
        GroupID groupID = GroupID.createGroupID(denomination);
        Optional<Group> optGroup = groupRepository.findById(groupID);

        if (!optGroup.isPresent()) {

            throw new NotFoundArgumentsBusinessException(GROUP_DOES_NOT_EXISTS);

        } else {

            Group group = optGroup.get();

            List<CategoryID> categories = group.getCategories();

            return CategoriesDTOAssembler.createDTOFromDomainObject(categories);
        }
    }


    //-------------------------------   New   -------------------------------------//

    @Transactional
    public GroupDTO createAndSaveGroup(String denomination, String description, PersonID adminId) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        Group newGroup = Group.createGroup(denomination, description, LocalDate.now().format(formatter), adminId);

        Group newGroupSaved = groupRepository.save(newGroup);

        GroupDTO groupDTO = GroupDTOAssembler.createDTOFromDomainObject(newGroupSaved.getGroupID().getDenomination(), newGroupSaved.getDescription(), newGroupSaved.getDateOfCreation());

        return groupDTO;
    }

    @Transactional
    public boolean addAdminToGroup(GroupID groupID, PersonID adminID) {

        Optional<Person> opPerson = personRepository.findById(adminID);
        if (!opPerson.isPresent()) return false;

        Optional<Group> opGroup = groupRepository.findById(groupID);
        if (!opGroup.isPresent()) return false;

        Group group = opGroup.get();
        boolean success = group.addPersonInCharge(adminID);
        if (success) {
            groupRepository.addAndSaveAdmin(group, adminID);
            return true;
        } else
            return false;
    }

    @Transactional
    public boolean addMemberToGroup(GroupID groupID, PersonID memberID) {

        Optional<Person> opPerson = personRepository.findById(memberID);
        if (!opPerson.isPresent()) return false;

        Optional<Group> opGroup = groupRepository.findById(groupID);
        if (!opGroup.isPresent()) return false;

        Group group = opGroup.get();
        boolean success = group.addMember(memberID);
        if (success) {
            groupRepository.addAndSaveMember(group, memberID);
            return true;
        } else
            return false;
    }

    @Transactional
    public boolean addCategoryToGroup(GroupID id, String denomination) {

        Optional<Group> opGroup = groupRepository.findById(id);
        if (!opGroup.isPresent()) return false; // it must throw a descriptive exception

        Optional<Category> opCategory = categoryRepository.findById(id.getDenomination().getDenomination(), denomination);
        if (opCategory.isPresent()) return false; // it must throw a descriptive exception

        Group group = opGroup.get();
        boolean success = group.addCategory(CategoryID.createCategoryID(denomination, id));
        if (success) {
            groupRepository.addAndSaveCategory(group);
            return true;
        } else
            return false; // it must throw a descriptive exception
    }

    @Transactional
    public boolean addAccountToGroup(GroupID id, String denomination, String description) {

        Optional<Group> opGroup = groupRepository.findById(id);
        if (!opGroup.isPresent()) return false;

        Optional<Account> opAccount = accountRepository.findById(id.getDenomination().getDenomination(), denomination);
        if (opAccount.isPresent()) return false;

        Group group = opGroup.get();
        boolean success = group.addAccount(AccountID.createAccountID(denomination, id));
        if (success) {
            groupRepository.addAndSaveAccount(group, description);
            return true;
        } else
            return false;
    }

    @Transactional
    public boolean addGroupTransaction(GroupID id, CategoryID categoryID, String type, String description, double amount, LocalDate date, AccountID debitAccountID, AccountID creditAccountID) {

        Optional<Group> opGroup = groupRepository.findById(id);
        if (!opGroup.isPresent()) return false;

        Optional<Category> opCategory = categoryRepository.findById(id.getDenomination().getDenomination(), categoryID.getDenomination().getDenomination());
        if (!opCategory.isPresent()) return false;

        Optional<Account> opDebAccount = accountRepository.findById(id.getDenomination().getDenomination(), debitAccountID.getDenomination().getDenomination());
        if (!opDebAccount.isPresent()) return false;

        Optional<Account> opCredAccount = accountRepository.findById(id.getDenomination().getDenomination(), creditAccountID.getDenomination().getDenomination());
        if (!opCredAccount.isPresent()) return false;

        Group group = opGroup.get();

        Optional<Ledger> opLedger = ledgerRepository.findById(group.getLedgerID());
        if (!opLedger.isPresent()) return false;

        Ledger ledger = opLedger.get();
        boolean success = ledger.createAndAddTransactionWithDate(categoryID, type, description, amount, date, debitAccountID, creditAccountID);
        if (success) {
            ledgerRepository.addAndSaveTransaction(ledger);
            return true;
        } else
            return false;
    }
}
