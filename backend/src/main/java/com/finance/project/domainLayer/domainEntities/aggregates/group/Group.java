package com.finance.project.domainLayer.domainEntities.aggregates.group;

import com.finance.project.domainLayer.domainEntities.vosShared.*;
import com.finance.project.domainLayer.entitiesInterfaces.Entity;
import com.finance.project.domainLayer.entitiesInterfaces.Owner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


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

    // Check GroupID of Group

    public boolean checkGroupID(GroupID groupID) {
        return this.groupID.equals(groupID);
    }


    // Getters

    public GroupID getGroupID() {
        return groupID;
    }

    public Description getDescription() {
        return description;
    }

    public DateOfCreation getDateOfCreation() {
        return dateOfCreation;
    }

    public List<CategoryID> getCategories() {
        return categories;
    }

    public List<AccountID> getAccounts() {
        return accounts;
    }

    public LedgerID getLedgerID() {
        return ledgerID;
    }

    public List<ScheduleID> getSchedulings() {
        return schedulings;
    }


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


    public List<PersonID> getPeopleInCharge() {
        List<PersonID> newPeopleInCharge = new ArrayList<>();

        for (PersonID personID : peopleInCharge) {
            newPeopleInCharge.add(personID);
        }
        return newPeopleInCharge;
    }


    public List<PersonID> getMembers() {
        List<PersonID> newMembers = new ArrayList<>();

        for (PersonID personID : members) {
            newMembers.add(personID);
        }
        return newMembers;
    }

    // Check if Person already exists in group

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

    // Check if Person is People In Charge

    public boolean isPersonPeopleInCharge(PersonID person) {
        for (int i = 0; i < peopleInCharge.size(); i++) {
            if (peopleInCharge.get(i).equals(person))
                return true;
        }
        return false;
    }

    // Check if Group has CategoryID

    public boolean checkIfGroupHasCategory(CategoryID categoryID) {
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).equals(categoryID))
                return true;
        }
        return false;
    }

    // Check if Group has AccountID

    public boolean checkIfGroupHasAccount(AccountID accountID) {
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).equals(accountID))
                return true;
        }
        return false;
    }

    // Add people in charge

    public boolean addPersonInCharge(PersonID personInCharge) {
        if (!peopleInCharge.contains(personInCharge)) {
            return peopleInCharge.add(personInCharge);
        } else {
            return false;
        }
    }

    // Add members

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

    public void addScheduling(ScheduleID scheduleID) {
        if (!schedulings.contains(scheduleID)) {
            this.schedulings.add(scheduleID);
        }
    }

    // Equals & hashCode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return Objects.equals(groupID, group.groupID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupID);
    }


    // New Constructor

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

    public boolean addLedgerID(LedgerID ledgerID) {
        this.ledgerID = ledgerID;

        return true;
    }
}
