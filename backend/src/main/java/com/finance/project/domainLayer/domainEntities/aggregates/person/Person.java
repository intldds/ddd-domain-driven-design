package com.finance.project.domainLayer.domainEntities.aggregates.person;

import com.finance.project.domainLayer.domainEntities.vosShared.*;
import com.finance.project.domainLayer.entitiesInterfaces.Entity;
import com.finance.project.domainLayer.entitiesInterfaces.Owner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


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

    // Constructor

    public static Person createPersonWithoutParents(String email, String name, LocalDate birthdate, String birthplace, Address address, LedgerID ledgerID) {
        return new Person(email, name, birthdate, birthplace, address, ledgerID);
    }

    // New Constructor - create person without parents, address and ledger

    public static Person createPerson(String email, String name, LocalDate birthdate, String birthplace) {
        return new Person(email, name, birthdate, birthplace);
    }

    // Create person with parents person.

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

    // Getters

    public Name getName() {
        return name;
    }

    public Email getEmail() {
        return email;
    }

    public Birthdate getBirthdate() {
        return birthdate;
    }

    public Birthplace getBirthplace() {
        return birthplace;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public PersonID getPersonID() {
        return personID;
    }

    public boolean checkPersonID(PersonID personID) {
        return this.personID.equals(personID);
    }

    public List<PersonID> getListOfSiblings() {
        List<PersonID> copyList = new ArrayList<>(listOfSiblings);
        return copyList;
    }

    public boolean addSibling(PersonID personID) {
        if (!listOfSiblings.contains(personID)) {
            return listOfSiblings.add(personID);
        }
        return false;
    }

    // Verify siblings or half siblings boolean.

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

    // Verify same siblings boolean.

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

    // Getters - Mother, Father, Catgories, Accounts

    public PersonID getMother() {
        return mother;
    }

    public PersonID getFather() {
        return father;
    }

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

    public LedgerID getLedgerID() {
        return ledgerID;
    }

    public boolean addSchedule(ScheduleID scheduleID) {
        if (!listOfSchedulings.contains(scheduleID)) {
            return listOfSchedulings.add(scheduleID);
        }
        return false;
    }

    public List<ScheduleID> getListOfSchedulings() {
        List<ScheduleID> copyList = new ArrayList<>(listOfSchedulings);
        return copyList;
    }

    // hashCode & equals

    @Override
    public int hashCode() {
        return Objects.hash(personID);
    }


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


    // Check if Person has AccountID

    public boolean checkIfPersonHasAccount(AccountID accountID) {
        for (int i = 0; i < listOfAccounts.size(); i++) {
            if (listOfAccounts.get(i).equals(accountID))
                return true;
        }
        return false;
    }

    public boolean checkIfPersonHasCategory(CategoryID categoryID) {
        for (int i = 0; i < listOfCategories.size(); i++) {
            if (listOfCategories.get(i).equals(categoryID))
                return true;
        }
        return false;
    }


    // Adders

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