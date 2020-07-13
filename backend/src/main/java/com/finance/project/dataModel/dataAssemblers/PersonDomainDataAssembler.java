package com.finance.project.dataModel.dataAssemblers;

import com.finance.project.dataModel.dataModel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.finance.project.domainLayer.domainEntities.aggregates.person.Address;
import com.finance.project.domainLayer.domainEntities.aggregates.person.Person;
import com.finance.project.domainLayer.domainEntities.vosShared.AccountID;
import com.finance.project.domainLayer.domainEntities.vosShared.CategoryID;
import com.finance.project.domainLayer.domainEntities.vosShared.PersonID;
import com.finance.project.persistenceLayer.repositoriesJPA.AccountJpaRepository;
import com.finance.project.persistenceLayer.repositoriesJPA.AddressJpaRepository;
import com.finance.project.persistenceLayer.repositoriesJPA.LedgerJpaRepository;
import com.finance.project.persistenceLayer.repositoriesJPA.PersonJpaRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonDomainDataAssembler {

    @Autowired
    PersonJpaRepository personJpaRepository;
    @Autowired
    AddressJpaRepository addressJpaRepository;
    @Autowired
    LedgerJpaRepository ledgerJpaRepository;
    @Autowired
    AccountJpaRepository accountJpaRepository;

    public PersonJpa toData(Person person) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        PersonJpa personJpa = new PersonJpa(person.getPersonID(), person.getName().getName(), person.getBirthdate().getBirthdate().format(formatter), person.getBirthplace().getBirthplace());

        Optional<PersonJpa> optPersonJpa = personJpaRepository.findById(person.getPersonID());

        //ADDRESS
        if (optPersonJpa.isPresent()) {

            PersonJpa repoPersonJpa = optPersonJpa.get();

            // No address
            if (repoPersonJpa.getAddress() == null && person.getAddress() != null) {

                AddressJpa addressJpa = new AddressJpa(person.getAddress().getStreet(), person.getAddress().getDoorNumber(), person.getAddress().getPostCode(), person.getAddress().getCity(), person.getAddress().getCountry());

                personJpa.setAddress(addressJpa);
            }

            // With address
            if (repoPersonJpa.getAddress() != null) {

                Optional<AddressJpa> optAddressJpa = addressJpaRepository.findById(repoPersonJpa.getAddress().getId());

                if (optAddressJpa.isPresent()) {

                    AddressJpa addressJpa = optAddressJpa.get();
                    personJpa.setAddress(addressJpa);

                }
            }

        }

        //LEDGER
        if (person.getLedgerID() != null) {
            Optional<LedgerJpa> optLedgerJpa = ledgerJpaRepository.findById(person.getLedgerID());

            if (optLedgerJpa.isPresent()) {
                LedgerJpa ledgerJpa = optLedgerJpa.get();

                personJpa.setLedger(ledgerJpa);
            }
        }

        //MOTHER
        if (person.getMother() != null) {
            Optional<PersonJpa> optPersonJpaMother = personJpaRepository.findById(person.getMother());

            if (optPersonJpaMother.isPresent()) {
                PersonJpa personJpaMother = optPersonJpaMother.get();

                personJpa.setMother(personJpaMother);
            }
        }

        //FATHER
        if (person.getFather() != null) {
            Optional<PersonJpa> optPersonJpaFather = personJpaRepository.findById(person.getFather());

            if (optPersonJpaFather.isPresent()) {
                PersonJpa personJpaFather = optPersonJpaFather.get();

                personJpa.setFather(personJpaFather);
            }
        }

        //SIBLINGS
        if (optPersonJpa.isPresent()) {
            PersonJpa repoPersonJpa = optPersonJpa.get();
            List<SiblingJpa> repoSiblings = repoPersonJpa.getSiblings();
            personJpa.setSiblings(repoSiblings);
        }

        //CATEGORIES
        List<CategoryJpa> categoryJpa = new ArrayList<>();
        for (CategoryID categoryID : person.getListOfCategories()) {
            categoryJpa.add(new CategoryJpa(person.getPersonID().getEmail().getEmail(), categoryID.getDenomination().getDenomination()));
            personJpa.setCategories(categoryJpa);
        }

        //ACCOUNTS
        List<AccountJpa> accountJpa = new ArrayList<>();
        for (AccountID accountID : person.getListOfAccounts()) {

            Optional<AccountJpa> optAccountJpa = accountJpaRepository.findById(new AbstractIdJpa(person.getPersonID().getEmail().getEmail(),accountID.getDenomination().getDenomination()));
            if(optAccountJpa.isPresent()){
                AccountJpa repoAccountJpa = optAccountJpa.get();
                accountJpa.add(repoAccountJpa);
            }
            personJpa.setAccounts(accountJpa);
        }

        return personJpa;
    }

    public Person toDomain(PersonJpa personJpa) {

        Person person = Person.createPerson(personJpa.getId().getEmail().getEmail(), personJpa.getName(), LocalDate.parse(personJpa.getBirthdate()), personJpa.getBirthplace());

        //ADDRESS
        if (personJpa.getAddress() != null) {
            String street = personJpa.getAddress().getStreet();
            String doorNumber = personJpa.getAddress().getDoorNumber();
            String postalCode = personJpa.getAddress().getPostCode();
            String city = personJpa.getAddress().getCity();
            String country = personJpa.getAddress().getCountry();

            Address address = Address.createAddress(street, doorNumber, postalCode, city, country);

            person.setAddress(address);
        }

        //LEDGER
        if (personJpa.getLedger() != null) {
            person.addLedgerID(personJpa.getLedger().getId());
        }

        //MOTHER
        if (personJpa.getMother() != null) {
            person.addMother(personJpa.getMother().getId());
        }

        //FATHER
        if (personJpa.getFather() != null) {
            person.addFather(personJpa.getFather().getId());
        }

        //SIBLINGS
        for (SiblingJpa siblingJpa : personJpa.getSiblings()) {
            person.addSibling(siblingJpa.getPersonID());
        }

        //CATEGORIES
        for (CategoryJpa categoryJpa : personJpa.getCategories()) {
            person.addCategory(CategoryID.createCategoryID(categoryJpa.getId().getDenomination(), PersonID.createPersonID(categoryJpa.getId().getOwnerID())));
        }

        //ACCOUNTS
        for (AccountJpa accountJpa : personJpa.getAccounts()) {
            person.addAccount(AccountID.createAccountID(accountJpa.getId().getDenomination(), PersonID.createPersonID(accountJpa.getId().getOwnerID())));
        }

        return person;
    }
}