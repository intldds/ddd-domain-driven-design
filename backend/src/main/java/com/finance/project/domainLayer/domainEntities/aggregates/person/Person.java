package com.finance.project.domainLayer.domainEntities.aggregates.person;

import com.finance.project.domainLayer.domainEntities.vosShared.*;
import com.finance.project.domainLayer.entitiesInterfaces.Entity;
import com.finance.project.domainLayer.entitiesInterfaces.Owner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The type Person.
 */
public class Person implements Entity, Owner {

    private PersonID personID;
    private Name name;
    private Email email;
    private Birthdate birthdate;
    private Birthplace birthplace;
    private Address address;
    private PersonID mother;
    private PersonID father;
    private List<PersonID> listOfSiblings;
    private List<AccountID> listOfAccounts;
    private List<CategoryID> listOfCategories;
    private LedgerID ledgerID;
    private List<ScheduleID> listOfSchedulings;
    private List<GroupID> listOfGroups;

    // Constructor

    /**
     * Create person without parents person.
     *
     * @param email      the email
     * @param name       the name
     * @param birthdate  the birthdate
     * @param birthplace the birthplace
     * @param address    the address
     * @param ledgerID   the ledger id
     * @return the person
     */

    public static Person createPersonWithoutParents(String email, String name, LocalDate birthdate, String birthplace, Address address, LedgerID ledgerID) {
        return new Person(email, name, birthdate, birthplace, address, ledgerID);
    }

    // New Constructor - create person without parents, address and ledger

    /**
     * Create person.
     *
     * @param email      the email
     * @param name       the name
     * @param birthdate  the birthdate
     * @param birthplace the birthplace
     * @return the person
     */

    public static Person createPerson(String email, String name, LocalDate birthdate, String birthplace) {
        return new Person(email, name, birthdate, birthplace);
    }

    /**
     * Create person with parents person.
     *
     * @param email      the email
     * @param name       the name
     * @param birthdate  the birthdate
     * @param mother     the mother
     * @param father     the father
     * @param birthplace the birthplace
     * @param address    the address
     * @param ledgerID   the ledger id
     * @return the person
     */

    public static Person createPersonWithParents(String email, String name, LocalDate birthdate, PersonID mother, PersonID father, String birthplace, Address address, LedgerID ledgerID) {
        return new Person(email, name, birthdate, mother, father, birthplace, address, ledgerID);
    }

    private Person(String email, String name, LocalDate birthdate, PersonID mother, PersonID father, String birthplace, Address address, LedgerID ledgerID) {
        auxPerson(email, name, birthdate, birthplace, address, ledgerID);
        aux2Person(mother, father);
    }

    private Person(String email, String name, LocalDate birthdate, String birthplace, Address address, LedgerID ledgerID) {
        auxPerson(email, name, birthdate, birthplace, address, ledgerID);
    }

    private Person(String email, String name, LocalDate birthdate, String birthplace) {
        auxPerson3(email, name, birthdate, birthplace);
    }


    private void auxPerson(String email, String name, LocalDate birthdate, String birthplace, Address address, LedgerID ledgerID) {
        if (name == null || name.equals("")) {
            throw new IllegalArgumentException("Person not created due to the fact that the name parameter hasn't a valid argument");
        } else if (birthdate == null) {
            throw new IllegalArgumentException("Person not created due to the fact that the birthdate parameter hasn't a valid argument");
        } else if (email == null || email.equals("")) {
            throw new IllegalArgumentException("Person not created due to the fact that the email parameter hasn't a valid argument");
        } else if (birthplace == null || birthplace.equals("")) {
            throw new IllegalArgumentException("Person not created due to the fact that the birthplace parameter hasn't a valid argument");
        } else if (address == null) {
            throw new IllegalArgumentException("Person not created due to the fact that the address parameter hasn't a valid argument");
        } else if (ledgerID == null) {
            throw new IllegalArgumentException("Person not created due to the fact that the ledgerID parameter hasn't a valid argument");
        }
        this.personID = PersonID.createPersonID(email);
        this.name = Name.createName(name);
        this.email = Email.createEmail(email);
        this.birthdate = Birthdate.createBirthdate(birthdate);
        this.birthplace = Birthplace.createBirthplace(birthplace);
        this.address = address;
        this.ledgerID = ledgerID;
        this.listOfSiblings = new ArrayList<>();
        this.listOfAccounts = new ArrayList<>();
        this.listOfCategories = new ArrayList<>();
        this.listOfSchedulings = new ArrayList<>();
    }

    private void aux2Person(PersonID mother, PersonID father) {
        this.mother = mother;
        this.father = father;
    }


    private void auxPerson3(String email, String name, LocalDate birthdate, String birthplace) {
        if (name == null || name.equals("")) {
            throw new IllegalArgumentException("Person not created due to the fact that the name parameter hasn't a valid argument");
        } else if (birthdate == null) {
            throw new IllegalArgumentException("Person not created due to the fact that the birthdate parameter hasn't a valid argument");
        } else if (email == null || email.equals("")) {
            throw new IllegalArgumentException("Person not created due to the fact that the email parameter hasn't a valid argument");
        } else if (birthplace == null || birthplace.equals("")) {
            throw new IllegalArgumentException("Person not created due to the fact that the birthplace parameter hasn't a valid argument");
        }

        this.personID = PersonID.createPersonID(email);
        this.name = Name.createName(name);
        this.email = Email.createEmail(email);
        this.birthdate = Birthdate.createBirthdate(birthdate);
        this.birthplace = Birthplace.createBirthplace(birthplace);

        this.listOfSiblings = new ArrayList<>();
        this.listOfAccounts = new ArrayList<>();
        this.listOfCategories = new ArrayList<>();
        this.listOfSchedulings = new ArrayList<>();
    }

    //Name

    /**
     * Gets name.
     *
     * @return the name
     */

    public Name getName() {
        return name;
    }


    //Email

    /**
     * Gets email.
     *
     * @return the email
     */

    public Email getEmail() {
        return email;
    }

    //Birthdate

    /**
     * Gets birthdate.
     *
     * @return the birthdate
     */

    public Birthdate getBirthdate() {
        return birthdate;
    }

    //Birthplace

    /**
     * Gets birthplace.
     *
     * @return the birthplace
     */

    public Birthplace getBirthplace() {
        return birthplace;
    }

    //Address

    /**
     * Gets address.
     *
     * @return the address
     */

    public Address getAddress() {
        return address;
    }

    /**
     * Sets address.
     *
     * @param address the address
     */

    public void setAddress(Address address) {
        this.address = address;
    }

    //PersonID

    /**
     * Gets person id.
     *
     * @return the person id
     */

    public PersonID getPersonID() {
        return personID;
    }

    /**
     * Check person id boolean.
     *
     * @param personID the person id
     * @return the boolean
     */

    public boolean checkPersonID(PersonID personID) {
        return this.personID.equals(personID);
    }

    //Siblings

    /**
     * Gets list of siblings.
     *
     * @return the list of siblings
     */

    public List<PersonID> getListOfSiblings() {
        List<PersonID> copyList = new ArrayList<>(listOfSiblings);
        return copyList;
    }

    /**
     * Add sibling boolean.
     *
     * @param personID the person id
     * @return the boolean
     */

    public boolean addSibling(PersonID personID) {
        if (!listOfSiblings.contains(personID)) {
            return listOfSiblings.add(personID);
        }
        return false;
    }

    /**
     * Verify siblings or half siblings boolean.
     *
     * @param personP the person p
     * @return the boolean
     */

    public boolean verifySiblingsOrHalfSiblings(Person personP) {
        if (this == personP) {
            return false;
        }
        if (this.mother != null && personP.mother != null && this.mother.equals(personP.mother)) {
            return true;
        }

        if (this.father != null && personP.father != null && this.father.equals(personP.father)) {
            return true;
        }

        if (this.listOfSiblings.contains(personP.personID) || personP.listOfSiblings.contains(this.personID)) {
            return true;
        }
        return false;
    }

    /**
     * Verify same siblings boolean.
     *
     * @param personP the person p
     * @return the boolean
     */

    public boolean verifySameSiblings(Person personP) {

        List<PersonID> personCopyThis = new ArrayList<>(this.listOfSiblings);

        List<PersonID> personCopyPersonP = new ArrayList<>(personP.listOfSiblings);

        if (this == personP) {
            return false;
        }
        if (this.listOfSiblings.size() != personP.listOfSiblings.size()) {
            return false;
        }
        if (this.listOfSiblings.equals(personP.listOfSiblings)) {
            return true;
        }

        personCopyPersonP.remove(this.personID);
        personCopyThis.remove(personP.personID);


        if (personCopyThis.equals(personCopyPersonP)) {
            return true;
        }

        return false;
    }

    //Mother

    /**
     * Gets mother.
     *
     * @return the mother
     */

    public PersonID getMother() {
        return mother;
    }

    //Father

    /**
     * Gets father.
     *
     * @return the father
     */
    public PersonID getFather() {
        return father;
    }

    //Categories

    /**
     * Gets list of categories.
     *
     * @return the list of categories
     */

    public List<CategoryID> getListOfCategories() {
        List<CategoryID> copyList = new ArrayList<>(listOfCategories);
        return copyList;
    }

    public boolean addCategory(CategoryID categoryID) {
        if (!listOfCategories.contains(categoryID)) {
            return listOfCategories.add(categoryID);
        }
        return false;
    }

    //Accounts

    /**
     * Gets list of accounts.
     *
     * @return the list of accounts
     */

    public List<AccountID> getListOfAccounts() {
        List<AccountID> copyList = new ArrayList<>(listOfAccounts);
        return copyList;
    }

    public boolean addAccount(AccountID accountID) {
        if (!listOfAccounts.contains(accountID)) {
            return listOfAccounts.add(accountID);
        }
        return false;
    }

    //Ledger

    /**
     * Gets ledger id.
     *
     * @return the ledger id
     */

    public LedgerID getLedgerID() {
        return ledgerID;
    }

    //Schedule

    /**
     * Add schedule boolean.
     *
     * @param scheduleID the schedule id
     * @return the boolean
     */

    public boolean addSchedule(ScheduleID scheduleID) {
        if (!listOfSchedulings.contains(scheduleID)) {
            return listOfSchedulings.add(scheduleID);
        }
        return false;
    }

    /**
     * Gets list of schedulings.
     *
     * @return the list of schedulings
     */

    public List<ScheduleID> getListOfSchedulings() {
        List<ScheduleID> copyList = new ArrayList<>(listOfSchedulings);
        return copyList;
    }

    //HashCode

    /**
     * Hash code int.
     *
     * @return the int
     */

    @Override
    public int hashCode() {
        return Objects.hash(personID);
    }

    //Equals

    /**
     * Equals boolean.
     *
     * @param o the o
     * @return the boolean
     */

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (this == o) {
            return true;
        } else {
            Person person2 = (Person) o;

            if (!this.personID.equals(person2.personID)) {
                return false;
            }
            return true;
        }
    }


    //Check if Person has AccountID

    /**
     * Check if person has account boolean.
     *
     * @param accountID the account id
     * @return the boolean
     */
    public boolean checkIfPersonHasAccount(AccountID accountID) {
        for (int i = 0; i < listOfAccounts.size(); i++) {
            if (listOfAccounts.get(i).equals(accountID))
                return true;
        }
        return false;
    }

    //Check if Person has CategoryID

    /**
     * Check if person has category boolean.
     *
     * @param categoryID the category id
     * @return the boolean
     */

    public boolean checkIfPersonHasCategory(CategoryID categoryID) {
        for (int i = 0; i < listOfCategories.size(); i++) {
            if (listOfCategories.get(i).equals(categoryID))
                return true;
        }
        return false;
    }


    //--------------------------------------------   NOVO   ----------------------------------------//

    public boolean addAddress(String street, String doorNumber, String postCode, String city, String country) {
        if (this.address == null) {
            this.address = Address.createAddress(street, doorNumber, postCode, city, country);
            return true;
        }
        return false;
    }

    public boolean addLedgerID(LedgerID ledgerID) {
        this.ledgerID = ledgerID;

        return true;
    }

    public boolean addMother(PersonID personID) {
        if (this.mother == null) {
            this.mother = personID;
            return true;
        }
        return false;
    }

    public boolean addFather(PersonID personID) {
        if (this.father == null) {
            this.father = personID;

            return true;
        }
        return false;
    }

}


/*  PARA JONAS VER


    //Create Scheduling

    public boolean createScheduling(String transactionType, double amount, Account creditAccount, Account debitAccount, Category category, String description, LocalDate triggerDate, String periodicity) {
        return listOfSchedulings.createScheduling(this.ledgerID, transactionType, amount, creditAccount, debitAccount, category, description, triggerDate, periodicity);
    }

    //Check if schedulingList has a specific Scheduling

    public boolean hasScheduling(Scheduling scheduling) {
        return listOfSchedulings.hasScheduling(scheduling);
    }

    //Check schedulingList size

    public int schedulingListSize() {
        return listOfSchedulings.schedulingListSize();
    }
 */



/* COISAS A APAGAR!

    //create group

    public boolean createGroupAsPeopleInCharge(GroupID groupID, Person personIncharge, Description description, DateOfCreation dateOfCreation) {
        return listOfMyGroups.createGroupAsPeopleInCharge(groupID, personIncharge, description, dateOfCreation);
    }

    //create account

    public void createAccount( Description description, Denomination denomination) {
        Boolean account = listOfAccounts.createAccount(description,denomination);
        //listOfAccounts.addAccount(account);
    }

    //create transaction

    public boolean createAndAddTransaction(Category category, String type, String description, double amount, Account debitAccount, Account creditAccount) {
        return ledger.createAndAddTransaction(category, type, description, amount, debitAccount, creditAccount);
    }

    public boolean createAndAddTransactionWithDate(Category category, String type, String description, double amount, LocalDate date, Account debitAccount, Account creditAccount) {
        return ledger.createAndAddTransactionWithDate(category, type, description, amount, date, debitAccount, creditAccount);
    }

    //make a person

    public Person(Person p) {
        this.name = p.name;
        this.birthdate = p.birthdate;
        this.birthplace = p.birthplace;
        this.address = p.address;

        if (p.mother != null && p.father != null) {
            this.mother = new Person(p.mother);
            this.father = new Person(p.father);
        } else if (p.mother != null) {
            this.mother = new Person(p.mother);
        } else if (p.father != null) {
            this.father = new Person(p.father);
        }

    }

 */
