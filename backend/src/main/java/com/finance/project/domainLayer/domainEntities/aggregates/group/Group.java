package com.finance.project.domainLayer.domainEntities.aggregates.group;

import com.finance.project.domainLayer.domainEntities.vosShared.*;
import com.finance.project.domainLayer.entitiesInterfaces.Entity;
import com.finance.project.domainLayer.entitiesInterfaces.Owner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The type Group.
 */
public class Group implements Entity, Owner {

    private GroupID groupID;
    private List<PersonID> peopleInCharge = new ArrayList<>();
    private List<PersonID> members = new ArrayList<>();
    private Description description;
    private DateOfCreation dateOfCreation;
    private List<CategoryID> categories = new ArrayList<>();
    private List<AccountID> accounts = new ArrayList<>();
    private LedgerID ledgerID;
    private List<ScheduleID> schedulings = new ArrayList<>();

    // Constructor (Group creation with Creator)

    /**
     * Create group as person in charge group.
     *
     * @param denomination   the denomination
     * @param personInCharge the person in charge
     * @param description    the description
     * @param dateOfCreation the date of creation
     * @param ledgerID       the ledger id
     * @return the group
     */
    public static Group createGroupAsPersonInCharge(String denomination, PersonID personInCharge, String description, LocalDate dateOfCreation, LedgerID ledgerID) {
        return new Group(denomination, personInCharge, description, dateOfCreation, ledgerID);
    }

    private Group(String denomination, PersonID personInCharge, String description, LocalDate dateOfCreation, LedgerID ledgerID) {

        if (personInCharge == null) {
            throw new NullPointerException("Group not created. Person in charge can't be Null");
        } else {
            this.groupID = GroupID.createGroupID(denomination);
            peopleInCharge.add(personInCharge);
            this.description = Description.createDescription(description);
            this.dateOfCreation = DateOfCreation.createDateOfCreation(dateOfCreation);
            this.ledgerID = ledgerID;
        }
    }

    // Constructor (Group creation without Creator)

    /**
     * Create group group.
     *
     * @param denomination   the denomination
     * @param peopleInCharge the people in charge
     * @param members        the members
     * @param description    the description
     * @param dateOfCreation the date of creation
     * @param ledgerID       the ledger id
     * @return the group
     */
    public static Group createGroup(String denomination, List<PersonID> peopleInCharge, List<PersonID> members, String description, LocalDate dateOfCreation, LedgerID ledgerID) {
        return new Group(denomination, peopleInCharge, members, description, dateOfCreation, ledgerID);
    }

    private Group(String denomination, List<PersonID> peopleInCharge, List<PersonID> members, String description, LocalDate dateOfCreation, LedgerID ledgerID) {
        if (peopleInCharge == null) {
            throw new NullPointerException("Group not created. People in charge can't be Null");
        } else {
            this.peopleInCharge = peopleInCharge;
        }
        if (members == null) {
            throw new NullPointerException("Group not created. Members can't be Null");
        } else {
            this.members = members;
        }
        this.description = Description.createDescription(description);
        this.dateOfCreation = DateOfCreation.createDateOfCreation(dateOfCreation);
        this.groupID = GroupID.createGroupID(denomination);
        this.ledgerID = ledgerID;
    }

    //Check GroupID of Group

    /**
     * Check group id boolean.
     *
     * @param groupID the group id
     * @return the boolean
     */
    public boolean checkGroupID(GroupID groupID) {
        return this.groupID.equals(groupID);
    }


    // Get methods

    /**
     * Gets group id.
     *
     * @return the group id
     */
    public GroupID getGroupID() {
        return groupID;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public Description getDescription() {
        return description;
    }

    /**
     * Gets date of creation.
     *
     * @return the date of creation
     */
    public DateOfCreation getDateOfCreation() {
        return dateOfCreation;
    }

    /**
     * Gets categories.
     *
     * @return the categories
     */
    public List<CategoryID> getCategories() {
        return categories;
    }

    /**
     * Gets accounts.
     *
     * @return the accounts
     */
    public List<AccountID> getAccounts() {
        return accounts;
    }

    /**
     * Gets ledger id.
     *
     * @return the ledger id
     */
    public LedgerID getLedgerID() {
        return ledgerID;
    }

    /**
     * Gets schedulings.
     *
     * @return the schedulings
     */
    public List<ScheduleID> getSchedulings() {
        return schedulings;
    }

    // Get All Persons from group

    /**
     * Gets all members.
     *
     * @return the all members
     */
    public List<PersonID> getAllMembers() {
        List<PersonID> newAllMembers = new ArrayList<>();

        for (PersonID personID : peopleInCharge) {
            newAllMembers.add(personID);
        }

        for (PersonID personID : members) {
            newAllMembers.add(personID);
        }

        return newAllMembers;
    }


    // Get People In Charge from group

    /**
     * Gets people in charge.
     *
     * @return the people in charge
     */
    public List<PersonID> getPeopleInCharge() {
        List<PersonID> newPeopleInCharge = new ArrayList<>();

        for (PersonID personID : peopleInCharge) {
            newPeopleInCharge.add(personID);
        }
        return newPeopleInCharge;
    }

    // Get Members from group

    /**
     * Gets members.
     *
     * @return the members
     */
    public List<PersonID> getMembers() {
        List<PersonID> newMembers = new ArrayList<>();

        for (PersonID personID : members) {
            newMembers.add(personID);
        }
        return newMembers;
    }

    //Check if Person already exists in group

    /**
     * Is person already member boolean.
     *
     * @param person the person
     * @return the boolean
     */
    public boolean isPersonAlreadyMember(PersonID person) {
        for (int i = 0; i < peopleInCharge.size(); i++) {
            if (peopleInCharge.get(i).equals(person))
                return true;
        }

        for (int i = 0; i < members.size(); i++) {
            if (members.get(i).equals(person))
                return true;
        }

        return false;
    }

    //Check if Person is People In Charge

    /**
     * Is person people in charge boolean.
     *
     * @param person the person
     * @return the boolean
     */
    public boolean isPersonPeopleInCharge(PersonID person) {
        for (int i = 0; i < peopleInCharge.size(); i++) {
            if (peopleInCharge.get(i).equals(person))
                return true;
        }
        return false;
    }

    //Check if Group has CategoryID

    /**
     * Check if group has category boolean.
     *
     * @param categoryID the category id
     * @return the boolean
     */
    public boolean checkIfGroupHasCategory(CategoryID categoryID) {
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).equals(categoryID))
                return true;
        }
        return false;
    }

    //Check if Group has AccountID

    /**
     * Check if group has account boolean.
     *
     * @param accountID the account id
     * @return the boolean
     */
    public boolean checkIfGroupHasAccount(AccountID accountID) {
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).equals(accountID))
                return true;
        }
        return false;
    }

    // Add people in charge

    /**
     * Add person in charge.
     *
     * @param personInCharge the person in charge
     */
    public boolean addPersonInCharge(PersonID personInCharge) {
        if (!peopleInCharge.contains(personInCharge)) {
            return peopleInCharge.add(personInCharge);
        } else {
            return false;
        }
    }

    // Add members

    /**
     * Add member boolean.
     *
     * @param member the member
     * @return the boolean
     */
    public boolean addMember(PersonID member) {
        if (!members.contains(member)) {
            return members.add(member);
        } else return false;
    }

    // Add categoryId

    public boolean addCategory(CategoryID categoryID) {
        if (!categories.contains(categoryID)) {
            return this.categories.add(categoryID);
        }
        return false;
    }

    // Add accountId

    public boolean addAccount(AccountID accountID) {
        if (!accounts.contains(accountID)) {
            return this.accounts.add(accountID);
        }
        return false;
    }

    //Add schedulingId

    /**
     * Add scheduling.
     *
     * @param scheduleID the schedule id
     */
    public void addScheduling(ScheduleID scheduleID) {
        if (!schedulings.contains(scheduleID)) {
            this.schedulings.add(scheduleID);
        }
    }

    // Equals

    /**
     * Equals boolean.
     *
     * @param o the o
     * @return the boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return Objects.equals(groupID, group.groupID);
    }


    // hashCode

    /**
     * Hash code int.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return Objects.hash(groupID);
    }


    //-------------------------------------   NOVO   ------------------------------//

    public static Group createGroup(String denomination, String description, String dateOfCreation, PersonID adminID) {
        return new Group(denomination, description, dateOfCreation, adminID);
    }

    private Group(String denomination, String description, String dateOfCreation, PersonID adminID) {

        this.groupID = GroupID.createGroupID(denomination);
        this.description = Description.createDescription(description);
        this.dateOfCreation = DateOfCreation.createDateOfCreation(LocalDate.parse(dateOfCreation));

        this.peopleInCharge.add(adminID);
    }

    public static Group createGroup(GroupID groupID, String description, String dateOfCreation, List<PersonID> admins) {
        return new Group(groupID, description, dateOfCreation, admins);
    }

    private Group(GroupID groupID, String description, String dateOfCreation, List<PersonID> admins) {

        this.groupID = groupID;
        this.description = Description.createDescription(description);
        this.dateOfCreation = DateOfCreation.createDateOfCreation(LocalDate.parse(dateOfCreation));

        this.peopleInCharge = admins;
    }

    public boolean addLedgerID(LedgerID ledgerID){
        this.ledgerID=ledgerID;

        return true;
    }
}

/*--------------------------------PARA APAGAR--------------------------------


    // Method "isFamily"

    public boolean isFamily() {

        Person father = null;
        Person mother = null;
        ArrayList<Person> allMembers = new ArrayList<Person>();
        ArrayList<Person> allMembersTemp = new ArrayList<Person>();

        boolean isFather, isMother;

        //Join All Members

        for (Person person : this.peopleInCharge) {
            allMembers.add(person);
        }

        for (Person person : this.members) {
            allMembers.add(person);
        }

        // Find Father

        for (int i = 0; i < allMembers.size(); i++) {
            for (int j = 0; j < allMembers.size(); j++) {
                if ((i != j) && allMembers.get(i).equals(allMembers.get(j).getFather())) {
                    if (father == null) {
                        father = allMembers.get(i);
                    } else {
                        return false;
                    }
                }
            }
        }
        if (father == null) {
            return false;
        } else {
            for (Person person : allMembers) {
                if (!person.equals(father)) {
                    allMembersTemp.add(person);
                }
            }
            allMembers = allMembersTemp;
            allMembersTemp = new ArrayList<Person>();
        }

        // Find Mother

        for (int i = 0; i < allMembers.size(); i++) {
            for (int j = 0; j < allMembers.size(); j++) {
                if (i != j && allMembers.get(i).equals(allMembers.get(j).getMother())) {
                    if (mother == null) {
                        mother = allMembers.get(i);
                    } else {
                        return false;
                    }
                }
            }
        }
        if (mother == null) {
            return false;
        } else {
            for (Person person : allMembers) {
                if (!person.equals(mother)) {
                    allMembersTemp.add(person);
                }
            }
            allMembers = allMembersTemp;
        }

        // Find Kids

        for (Person person : allMembers) {
            isFather = false;
            isMother = false;

            if (person.getFather() != null) {
                isFather = person.getFather().equals(father);
            }
            if (person.getMother() != null) {
                isMother = person.getMother().equals(mother);
            }
            if (!isFather && !isMother) {
                return false;
            }
        }

        return true;
    }

    //create and add category to group

    public boolean createCategory(String denomination) {
        return categories.createCategory(denomination);
    }
    // Add account to AccountList

    public void createAccount(Description description, Denomination denomination) {
        Boolean account = accounts.createAccount(description, denomination);
        //  accounts.addAccount(account);
    }

    // Create Transaction

    public boolean createAndAddTransaction(Category category, String type, String description, double amount, Account debitAccount, Account creditAccount) {
        return ledger.createAndAddTransaction(category, type, description, amount, debitAccount, creditAccount);
    }

    public boolean createAndAddTransactionWithDate(Category category, String type, String description, double amount, LocalDate date, Account debitAccount, Account creditAccount) {
        return ledger.createAndAddTransactionWithDate(category, type, description, amount, date, debitAccount, creditAccount);
    }


    //Create Scheduling

    public boolean createScheduling(String transactionType, double amount, Account creditAccount, Account debitAccount, Category category, String description, LocalDate triggerDate, String periodicity) {
        return schedulings.createScheduling(this.ledger, transactionType, amount, creditAccount, debitAccount, category, description, triggerDate, periodicity);
    }

    //Check if schedulingList has a specific Scheduling

    public boolean hasScheduling(Scheduling scheduling) {
        return schedulings.hasScheduling(scheduling);
    }

    //Check schedulingList size

    public int schedulingListSize() {
        return schedulings.schedulingListSize();
    }

    // Method getGroup record between two dates

    public ArrayList<Transaction> getRecordsBetweenTwoDates(Group group, LocalDate dateOfBeginning, LocalDate dateOfEnding) {
        ArrayList<Transaction> transactions;

        transactions = group.ledger.getRecordsBetweenTwoDates(dateOfBeginning, dateOfEnding);
        return transactions;
    }


    //Get Amount of records between 2 dates

    public double getAmountBetweenTwoDates(Group group, LocalDate dateOfBeginning, LocalDate dateOfEnding) {
        ArrayList<Transaction> transactions;
        double amount = 0;

        transactions = getRecordsBetweenTwoDates(this, dateOfBeginning, dateOfEnding);

        for (Transaction transaction : transactions) {
            amount = amount + transaction.getAmount();
        }
        return amount;
    }


    //get records from a group account in a determined period of time

    public ArrayList<Transaction> getAllTheTransactionsOfAccountWithinACertainPeriodOfTime(Account account,
                                                                                           LocalDate dateOfBeginning, LocalDate dateOfEnding) {
        ArrayList<Transaction> transactions;
        transactions = ledger.getRecordsBetweenTwoDatesOfADeterminedAccount(account, dateOfBeginning, dateOfEnding);
        return transactions;
    }


    // Create and add get

    public Ledger getLedger() {
        Ledger newLedger = new Ledger();
        ArrayList<Transaction> transactions = ledger.getRecords();
        for (Transaction transaction : transactions) {
            newLedger.addTransaction(transaction);
        }
        return newLedger;
    }


    // Get AccountList

    public AccountList getAccounts() {
        AccountList newAccounts = new AccountList();
        for (Account account : accounts.getAccount()) {
            newAccounts.addAccount(account);
        }
        return newAccounts;
    }

}
*/