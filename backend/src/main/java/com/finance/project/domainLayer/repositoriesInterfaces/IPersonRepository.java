package com.finance.project.domainLayer.repositoriesInterfaces;

import org.springframework.stereotype.Repository;
import com.finance.project.domainLayer.domainEntities.aggregates.person.Person;
import com.finance.project.domainLayer.domainEntities.vosShared.PersonID;

import java.util.List;
import java.util.Optional;

/**
 * The interface Person repository.
 */
@Repository
public interface IPersonRepository {
    //--------------------------------- NOVO   -------------------------------//

    Person save(Person person);

    Optional<Person> findById(PersonID id);

    boolean addAndSaveAddress(Person person);

    boolean addAndSaveLedger(Person person);

    boolean addAndSaveMother(Person person);

    boolean addAndSaveFather(Person person);

    boolean addAndSaveSibling(Person person, PersonID siblingID);

    public List<PersonID> findSiblingsById(PersonID id);

    boolean addAndSaveCategory(Person person);

    boolean addAndSaveAccount(Person person, String description);

    boolean exists(PersonID id);

    long count();

    List<Person> findAll();

    void delete(Person person);
}
