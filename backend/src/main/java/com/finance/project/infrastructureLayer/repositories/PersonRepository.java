package com.finance.project.infrastructureLayer.repositories;

import com.finance.project.dataModel.dataAssemblers.PersonDomainDataAssembler;
import com.finance.project.dataModel.dataModel.PersonJpa;
import com.finance.project.dataModel.dataModel.SiblingJpa;
import com.finance.project.persistenceLayer.repositoriesJPA.AddressJpaRepository;
import com.finance.project.persistenceLayer.repositoriesJPA.PersonJpaRepository;
import com.finance.project.domainLayer.repositoriesInterfaces.IPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.finance.project.domainLayer.domainEntities.aggregates.person.Person;
import com.finance.project.domainLayer.domainEntities.vosShared.AccountID;
import com.finance.project.domainLayer.domainEntities.vosShared.PersonID;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Repository
public class PersonRepository implements IPersonRepository {

    @Autowired
    PersonJpaRepository personJpaRepository;
    @Autowired
    AddressJpaRepository addressJpaRepository;
    @Autowired
    PersonDomainDataAssembler personAssembler;


    // Constructor
    public PersonRepository() {
    }


    @Transactional
    public Person save(Person person) {
        PersonJpa personJpa = personAssembler.toData(person);

        PersonJpa savedPersonJpa = personJpaRepository.save(personJpa);

        return personAssembler.toDomain(savedPersonJpa);
    }

    @Transactional
    public Optional<Person> findById(PersonID id) {
        Optional<PersonJpa> opPersonJpa = personJpaRepository.findById(id);

        if (opPersonJpa.isPresent()) {
            PersonJpa personJpa = opPersonJpa.get();

            Person person = personAssembler.toDomain(personJpa);
            return Optional.of(person);
        } else
            return Optional.empty();
    }

    public boolean addAndSaveAddress(Person person) {
        PersonJpa personJpa = personAssembler.toData(person);

        personJpaRepository.save(personJpa);

        return true;
    }

    @Transactional
    public boolean addAndSaveLedger(Person person) {
        PersonJpa personJpa = personAssembler.toData(person);

        personJpaRepository.save(personJpa);

        return true;
    }

    public boolean addAndSaveMother(Person person) {
        PersonJpa personJpa = personAssembler.toData(person);

        Optional<PersonJpa> optPersonJpaMother = personJpaRepository.findById(person.getMother());

        if (optPersonJpaMother.isPresent()) {
            PersonJpa personJpaMother = optPersonJpaMother.get();

            personJpa.setMother(personJpaMother);
        }
        personJpaRepository.save(personJpa);
        return true;
    }

    public boolean addAndSaveFather(Person person) {
        PersonJpa personJpa = personAssembler.toData(person);

        Optional<PersonJpa> optPersonJpaFather = personJpaRepository.findById(person.getFather());

        if (optPersonJpaFather.isPresent()) {
            PersonJpa personJpaFather = optPersonJpaFather.get();

            personJpa.setFather(personJpaFather);
        }
        personJpaRepository.save(personJpa);
        return true;
    }

    @Transactional
    public boolean addAndSaveSibling(Person person, PersonID siblingID) {
        PersonJpa personJpa = personAssembler.toData(person);

        personJpa.addSibling(siblingID);

        personJpaRepository.save(personJpa);

        return true;
    }

    @Transactional
    public List<PersonID> findSiblingsById(PersonID id) {
        Optional<PersonJpa> opPersonJpa = personJpaRepository.findById(id);

        if (opPersonJpa.isPresent()) {
            PersonJpa personJpa = opPersonJpa.get();

            List<SiblingJpa> siblingsJpa = personJpa.getSiblings();
            List<PersonID> siblingsID = new ArrayList<PersonID>();
            for (SiblingJpa siblingJpa : siblingsJpa) {
                siblingsID.add(siblingJpa.getPersonID());
            }
            return siblingsID;
        } else
            return null;
    }

    public boolean addAndSaveCategory(Person person) {
        PersonJpa personJpa = personAssembler.toData(person);

        personJpaRepository.save(personJpa);

        return true;
    }

    public boolean addAndSaveAccount(Person person, String description) {
        PersonJpa personJpa = personAssembler.toData(person);

        AccountID accountID = person.getListOfAccounts().get(person.getListOfAccounts().size() - 1);

        personJpa.addAccount(person.getPersonID().getEmail().getEmail(), accountID.getDenomination().getDenomination(), description);

        personJpaRepository.save(personJpa);

        return true;
    }

    public boolean exists(PersonID id) {
        return personJpaRepository.existsById(id);
    }

    public long count() {
        return personJpaRepository.count();
    }

    @Transactional
    public List<Person> findAll() {
        List<Person> newPersonRepo = new ArrayList<>();

        List<PersonJpa> listPersonJpa = personJpaRepository.findAll();

        for (PersonJpa personJpa : listPersonJpa) {
            Person person = personAssembler.toDomain(personJpa);
            newPersonRepo.add(person);
        }
        return newPersonRepo;
    }

    public void delete(Person person) {

//        Pass person to personJpa
        PersonJpa personJpa = personAssembler.toData(person);

//        Delete personJpa from personJpaRepository
        personJpaRepository.delete(personJpa);

    }
}
