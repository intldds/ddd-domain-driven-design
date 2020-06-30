package com.finance.project.infrastructureLayer.repositories;

import com.finance.project.controllerLayer.integrationTests.AbstractTest;
import com.finance.project.domainLayer.domainEntities.aggregates.account.Account;
import com.finance.project.domainLayer.domainEntities.aggregates.category.Category;
import com.finance.project.domainLayer.domainEntities.aggregates.person.Address;
import com.finance.project.domainLayer.domainEntities.aggregates.person.Person;
import com.finance.project.domainLayer.domainEntities.vosShared.AccountID;
import com.finance.project.domainLayer.domainEntities.vosShared.CategoryID;
import com.finance.project.domainLayer.domainEntities.vosShared.LedgerID;
import com.finance.project.domainLayer.domainEntities.vosShared.PersonID;
import com.finance.project.domainLayer.repositoriesInterfaces.IPersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PersonRepositoryTest extends AbstractTest {

    @Autowired
    private IPersonRepository personRepository;

    private Person personHulk;
    private String hulkEmail;
    private PersonID hulkPersonID;
    private String hulkName;
    private LocalDate hulkBirthdate;
    private String hulkBirthplace;


    @BeforeEach
    public void init() {

//        Arrange hulk Person
        hulkEmail = "hulk@gmail.com";
        hulkPersonID = PersonID.createPersonID(hulkEmail);
        hulkName = "Hulk";
        hulkBirthdate = LocalDate.of(1983, 6, 25);
        hulkBirthplace = "Porto";
        personHulk = Person.createPerson(hulkEmail, hulkName, hulkBirthdate, hulkBirthplace);

    }

    @Test
    @DisplayName("Test personRepository - Happy path")
    public void testPersonRepository() {

        //Assert
        assertNotNull(personRepository);
    }


    @Test
    @DisplayName("Test if a person has been saved to the PersonRepository - Happy path")
    public void savePerson_HappyPath() {

//        Count all the persons available in the beginning of the test
        long initialCount = personRepository.count();

//        Save person in the personJpaRepository
        Person hulkSavedInDB = personRepository.save(personHulk);

//        Count all the persons available in the personJpaRepository
        long numberOfPersonsInDB = personRepository.count();

//        Expected result
        int expectedNumberOfPersonsInDB = (int) initialCount + 1;

//        Assert
        assertEquals(personHulk, hulkSavedInDB);
        assertEquals(expectedNumberOfPersonsInDB, numberOfPersonsInDB);
        assertEquals(true, personRepository.exists(hulkPersonID));

//        Delete the person added in this test
        personRepository.delete(hulkSavedInDB);

//        Confirm if the elimination of the person was successful
        long countAfterDelete = personRepository.count();

//        Assert after delete of person
        assertEquals(initialCount, countAfterDelete);
    }

    @Test
    @DisplayName("Test to save a person twice to the PersonRepository | Not able to add the same person twice")
    public void savePerson_Twice() {

//        Count all the persons available in the beginning of the test
        long initialCount = personRepository.count();

//        Save person in the personJpaRepository
        Person savePersonInRepoResult = personRepository.save(personHulk);

//        Save person in the personJpaRepository
        Person saveTheSamePersonInRepoResult = personRepository.save(personHulk);

//        Count all the persons available in the personJpaRepository
        long numberOfPersonsInDB = personRepository.count();

//        Expected result
        int expectedNumberOfPersonsInDB = (int) initialCount + 1;

//        Assert
        assertEquals(personHulk, savePersonInRepoResult);
        assertEquals(expectedNumberOfPersonsInDB, numberOfPersonsInDB);
        assertTrue(personRepository.exists(hulkPersonID));

//        Delete the person added in this test
        personRepository.delete(savePersonInRepoResult);

//        Confirm if the elimination of the person was successful
        long countAfterDelete = personRepository.count();

//        Assert after delete of person
        assertEquals(initialCount, countAfterDelete);
    }

    @Test
    @DisplayName("Test to find a person by ID | Happy path")
    public void findPersonByID() {

//        Count all the persons available in the beginning of the test
        long initialCount = personRepository.count();

//        Save person in the personJpaRepository
        Person savePersonInRepoResult = personRepository.save(personHulk);

//        Find person by ID
        Optional<Person> opPerson = personRepository.findById(hulkPersonID);
        Person HulkFromDB = opPerson.get();

//        Assert
        assertNotNull(opPerson);
        assertEquals(personHulk, HulkFromDB);

//        Delete the person added in this test
        personRepository.delete(savePersonInRepoResult);

//        Confirm if the elimination of the person was successful
        long countAfterDelete = personRepository.count();

//        Assert after delete of person
        assertEquals(initialCount, countAfterDelete);
    }

    @Test
    @DisplayName("Test to find a person by ID | Person doesn't exists in DB")
    public void findPersonByIDDoesntExists() {

//        Count all the persons available in the beginning of the test
        long initialCount = personRepository.count();

//        Arrange Wolverine PersonID
        String wolverineEmail = "wolverine@gmail.com";
        PersonID wolverinePersonID = PersonID.createPersonID(wolverineEmail);

//        Arrange Wolverine Person
        String wolverineName = "Wolverine";
        LocalDate wolverineBirthdate = LocalDate.of(1983, 6, 25);
        String wolverineBirthplace = "Porto";
        Person personWolverine = Person.createPerson(wolverineEmail, wolverineName, wolverineBirthdate, wolverineBirthplace);

//        Find person by ID
        Optional<Person> opPerson = personRepository.findById(wolverinePersonID);

//        Count all the persons available in the personJpaRepository
        long numberOfPersonsInDB = personRepository.count();

//        Expected result (Person not saved in the personRepository)
        int expectedNumberOfPersonsInDB = (int) initialCount;

//        Assert
        assertFalse(opPerson.isPresent());
        assertEquals(expectedNumberOfPersonsInDB, numberOfPersonsInDB);
        assertFalse(personRepository.exists(wolverinePersonID));
    }

    @Test
    @DisplayName("Test to add a address to a person | Happy path")
    public void addAddressToPerson() {

//        Count all the persons available in the beginning of the test
        long initialCount = personRepository.count();

//        Arrange address Porto
        String portoStreet = "Rua Direita do Viso";
        String portoDoorNumber = "59";
        String portoPostCode = "4250 - 198";
        String portoCity = "Porto";
        String portoCountry = "Portugal";
        Address portoExpectedAddress = Address.createAddress(portoStreet, portoDoorNumber, portoPostCode, portoCity, portoCountry);

//        Save person in the personJpaRepository
        Person hulkSavedInDB = personRepository.save(personHulk);

//        Add Address to saved person
        boolean verifyAddedAddress = personHulk.addAddress(portoStreet, portoDoorNumber, portoPostCode, portoCity, portoCountry);

//        Update person info in the personJpaRepository
        boolean saveTheSamePersonInRepoResult = personRepository.addAndSaveAddress(personHulk);

//        Find person by ID
        Optional<Person> opPerson = personRepository.findById(hulkPersonID);
        Person hulkFromDB = opPerson.get();
        Address addressFromDB = hulkFromDB.getAddress();

//        Assert
        assertEquals(personHulk, hulkFromDB);
        assertEquals(portoExpectedAddress, addressFromDB);

//        Delete the person added in this test
        personRepository.delete(hulkSavedInDB);

//        Confirm if the elimination of the person was successful
        long countAfterDelete = personRepository.count();

//        Assert after delete of person
        assertEquals(initialCount, countAfterDelete);
    }

    @Test
    @DisplayName("Test to add a address to a person | Added different address to another person")
    public void addAddressToPerson_AddedDifferentAddressToAnotherPerson() {

//        Count all the persons available in the beginning of the test
        long initialCount = personRepository.count();

//        Arrange address Porto
        String portoStreet = "Rua Direita do Viso";
        String portoDoorNumber = "59";
        String portoPostCode = "4250 - 198";
        String portoCity = "Porto";
        String portoCountry = "Portugal";
        Address portoExpectedAddress = Address.createAddress(portoStreet, portoDoorNumber, portoPostCode, portoCity, portoCountry);

//        Arrange address Braga
        String bragaStreet = "Praça Conde de Agrolongo";
        String bragaDoorNumber = "136";
        String bragaPostCode = "4700-312";
        String bragaCity = "Braga";
        String bragaCountry = "Portugal";
        Address bragaExpectedAddress = Address.createAddress(bragaStreet, bragaDoorNumber, bragaPostCode, bragaCity, bragaCountry);

//        Arrange Wolverine PersonID
        String wolverineEmail = "wolverine@gmail.com";
        PersonID wolverinePersonID = PersonID.createPersonID(wolverineEmail);

//        Arrange Wolverine Person
        String wolverineName = "Wolverine";
        LocalDate wolverineBirthdate = LocalDate.of(1983, 6, 25);
        String wolverineBirthplace = "Porto";
        Person personWolverine = Person.createPerson(wolverineEmail, wolverineName, wolverineBirthdate, wolverineBirthplace);


//        Save person in the personJpaRepository
        Person hulkSavedInDB = personRepository.save(personHulk);
        Person wolverineSavedInDB = personRepository.save(personWolverine);

//        Add Address to saved person
        boolean hulkAddedAddress = personHulk.addAddress(portoStreet, portoDoorNumber, portoPostCode, portoCity, portoCountry);
        boolean wolverinAddedAddress = personWolverine.addAddress(bragaStreet, bragaDoorNumber, bragaPostCode, bragaCity, bragaCountry);

//        Update person info in the personJpaRepository
        boolean saveHulkInRepoResult = personRepository.addAndSaveAddress(personHulk);
        boolean saveWolverineInRepoResult = personRepository.addAndSaveAddress(personWolverine);

//        Find hulk by ID
        Optional<Person> opHulk = personRepository.findById(hulkPersonID);
        Person hulkFromDB = opHulk.get();
        Address hulkAddressFromDB = hulkFromDB.getAddress();

//        Find hulk by ID
        Optional<Person> opWolverine = personRepository.findById(wolverinePersonID);
        Person wolverineFromDB = opWolverine.get();
        Address wolverineAddressFromDB = wolverineFromDB.getAddress();

//        Assert
        assertEquals(personHulk, hulkFromDB);
        assertEquals(personWolverine, wolverineFromDB);
        assertEquals(portoExpectedAddress, hulkAddressFromDB);
        assertEquals(bragaExpectedAddress, wolverineAddressFromDB);

//        Delete the person added in this test
        personRepository.delete(hulkSavedInDB);
        personRepository.delete(wolverineSavedInDB);

//        Confirm if the elimination of the person was successful
        long countAfterDelete = personRepository.count();

//        Assert after delete of persons
        assertEquals(initialCount, countAfterDelete);
    }

    @Test
    @DisplayName("Test to add a ledger to a person | Happy path")
    public void addLedgerToPerson() {

//        Count all the persons available in the beginning of the test
        long initialCount = personRepository.count();

//        Save person in the personJpaRepository
        Person hulkSavedInDB = personRepository.save(personHulk);

//        Update person info in the personJpaRepository
        boolean saveHulkAddingLedgerInRepoResult = personRepository.addAndSaveLedger(personHulk);

//        Find person by ID
        Optional<Person> opPerson = personRepository.findById(hulkPersonID);
        Person hulkFromDB = opPerson.get();
        LedgerID ledgerFromDB = hulkFromDB.getLedgerID();

//        Assert
        assertEquals(personHulk, hulkFromDB);
        assertNotNull(ledgerFromDB);

//        Delete the person added in this test
        personRepository.delete(hulkSavedInDB);

//        Confirm if the elimination of the person was successful
        long countAfterDelete = personRepository.count();

//        Assert after delete of person
        assertEquals(initialCount, countAfterDelete);
    }

    @Test
    @DisplayName("Test to add a address to a person | Added different address to another person")
    public void addLedgerToPerson_AddedDifferentLedgerToAnotherPerson() {

//        Count all the persons available in the beginning of the test
        long initialCount = personRepository.count();

//        Arrange Wolverine PersonID
        String wolverineEmail = "wolverine@gmail.com";
        PersonID wolverinePersonID = PersonID.createPersonID(wolverineEmail);

//        Arrange Wolverine Person
        String wolverineName = "Wolverine";
        LocalDate wolverineBirthdate = LocalDate.of(1983, 6, 25);
        String wolverineBirthplace = "Porto";
        Person personWolverine = Person.createPerson(wolverineEmail, wolverineName, wolverineBirthdate, wolverineBirthplace);

//        Save person in the personJpaRepository
        Person hulkSavedInDB = personRepository.save(personHulk);
        Person wolverineSavedInDB = personRepository.save(personWolverine);

//        Update person info in the personJpaRepository
        boolean saveHulkAddingLedgerInRepoResult = personRepository.addAndSaveLedger(personHulk);
        boolean saveWolverineAddingLedgerInRepoResult = personRepository.addAndSaveLedger(personWolverine);

//        Find hulk by ID
        Optional<Person> opHulk = personRepository.findById(hulkPersonID);
        Person hulkFromDB = opHulk.get();
        LedgerID hulkLedgerFromDB = hulkFromDB.getLedgerID();

//        Find hulk by ID
        Optional<Person> opWolverine = personRepository.findById(wolverinePersonID);
        Person wolverineFromDB = opWolverine.get();
        LedgerID wolverineLedgerFromDB = wolverineFromDB.getLedgerID();

//        Assert
        assertEquals(personHulk, hulkFromDB);
        assertEquals(personWolverine, wolverineFromDB);
        assertNotNull(hulkLedgerFromDB);
        assertNotNull(wolverineLedgerFromDB);

//        Delete the person added in this test
        personRepository.delete(hulkSavedInDB);
        personRepository.delete(wolverineSavedInDB);

//        Confirm if the elimination of the person was successful
        long countAfterDelete = personRepository.count();

//        Assert after delete of persons
        assertEquals(initialCount, countAfterDelete);
    }

    @Test
    @DisplayName("Test to add mother to a person | Happy path")
    public void addMotherToPerson() {

//        Count all the persons available in the beginning of the test
        long initialCount = personRepository.count();

//        Arrange hulk mother ID
        String stormEmail = "storm@gmail.com";
        PersonID stormPersonID = PersonID.createPersonID(stormEmail);

//        Arrange Storm Person
        String stormName = "Windy Storm";
        LocalDate stormBirthdate = LocalDate.of(1963, 1, 9);
        String stormBirthplace = "Vila Nova de Gaia";
        Person personStorm = Person.createPerson(stormEmail, stormName, stormBirthdate, stormBirthplace);

//        Save person in the personJpaRepository
        Person hulkSavedInDB = personRepository.save(personHulk);

//        Save mother in the personJpaRepository
        Person stormSavedInDB = personRepository.save(personStorm);

//        Add mother to hulk
        Boolean hulkWithMother = personHulk.addMother(stormPersonID);


//        Update person info in the personJpaRepository
        boolean saveHulkAddingLedgerInRepoResult = personRepository.addAndSaveMother(personHulk);

//        Find person by ID
        Optional<Person> opPerson = personRepository.findById(hulkPersonID);
        Person hulkFromDB = opPerson.get();
        PersonID hulkMotherIDFromDB = hulkFromDB.getMother();

//        Assert
        assertEquals(stormPersonID, hulkMotherIDFromDB);
        assertEquals(personHulk, hulkFromDB);

//        Delete the person added in this test
        personRepository.delete(hulkSavedInDB);
        personRepository.delete(stormSavedInDB);

//        Confirm if the elimination of the person was successful
        long countAfterDelete = personRepository.count();

//        Assert after delete of persons
        assertEquals(initialCount, countAfterDelete);
    }

    @Test
    @DisplayName("Test add to a person a mother that doesn't exists in DB")
    public void addMotherToPerson_MotherDoesntExists() {

//        Count all the persons available in the beginning of the test
        long initialCount = personRepository.count();

//        Arrange hulk mother ID
        String stormEmail = "storm@gmail.com";
        PersonID stormPersonID = PersonID.createPersonID(stormEmail);

//        Arrange Storm Person
        String stormName = "Windy Storm";
        LocalDate stormBirthdate = LocalDate.of(1963, 1, 9);
        String stormBirthplace = "Vila Nova de Gaia";
        Person personStorm = Person.createPerson(stormEmail, stormName, stormBirthdate, stormBirthplace);

//        Save person in the personJpaRepository
        Person hulkSavedInDB = personRepository.save(personHulk);

//        Add mother to hulk
        Boolean hulkWithMother = personHulk.addMother(stormPersonID);

//        Update person info in the personJpaRepository
        boolean saveHulkAddingLedgerInRepoResult = personRepository.addAndSaveMother(personHulk);

//        Find person by ID
        Optional<Person> opPerson = personRepository.findById(hulkPersonID);
        Person hulkFromDB = opPerson.get();
        PersonID hulkMotherIDFromDB = hulkFromDB.getMother();

//        Assert
        assertNull(hulkMotherIDFromDB);
        assertEquals(personHulk, hulkFromDB);

//        Delete the person added in this test
        personRepository.delete(hulkSavedInDB);

//        Confirm if the elimination of the person was successful
        long countAfterDelete = personRepository.count();

//        Assert after delete of person
        assertEquals(initialCount, countAfterDelete);
    }

    @Test
    @DisplayName("Test to add father to a person | Happy path")
    public void addFatherToPerson() {

//        Count all the persons available in the beginning of the test
        long initialCount = personRepository.count();

//        Arrange hulk father ID
        String aquamanEmail = "aquaman@gmail.com";
        PersonID aquamanPersonID = PersonID.createPersonID(aquamanEmail);

//        Arrange Storm Person
        String aquamanName = "Irish Aquaman";
        LocalDate aquamanBirthdate = LocalDate.of(1963, 1, 9);
        String aquamanBirthplace = "Vila Nova de Gaia";
        Person personAquaman = Person.createPerson(aquamanEmail, aquamanName, aquamanBirthdate, aquamanBirthplace);

//        Save person in the personJpaRepository
        Person hulkSavedInDB = personRepository.save(personHulk);

//        Save mother in the personJpaRepository
        Person aquamanSavedInDB = personRepository.save(personAquaman);

//        Add mother to hulk
        Boolean hulkWithFather = personHulk.addFather(aquamanPersonID);

//        Update person info in the personJpaRepository
        boolean saveHulkAddingLedgerInRepoResult = personRepository.addAndSaveFather(personHulk);

//        Find person by ID
        Optional<Person> opPerson = personRepository.findById(hulkPersonID);
        Person hulkFromDB = opPerson.get();
        PersonID hulkFatherIDFromDB = hulkFromDB.getFather();

//        Assert
        assertEquals(aquamanPersonID, hulkFatherIDFromDB);
        assertEquals(personHulk, hulkFromDB);

//        Delete the person added in this test
        personRepository.delete(hulkSavedInDB);
        personRepository.delete(aquamanSavedInDB);

//        Confirm if the elimination of the person was successful
        long countAfterDelete = personRepository.count();

//        Assert after delete of persons
        assertEquals(initialCount, countAfterDelete);
    }

    @Test
    @DisplayName("Test add to a person a mother that doesn't exists in DB")
    public void addFatherToPerson_FatherDoesntExists() {

//        Count all the persons available in the beginning of the test
        long initialCount = personRepository.count();

//        Arrange hulk father ID
        String aquamanEmail = "aquaman@gmail.com";
        PersonID aquamanPersonID = PersonID.createPersonID(aquamanEmail);

//        Arrange Storm Person
        String aquamanName = "Irish Aquaman";
        LocalDate aquamanBirthdate = LocalDate.of(1963, 1, 9);
        String aquamanBirthplace = "Vila Nova de Gaia";
        Person personAquaman = Person.createPerson(aquamanEmail, aquamanName, aquamanBirthdate, aquamanBirthplace);

//        Save person in the personJpaRepository
        Person hulkSavedInDB = personRepository.save(personHulk);

//        Add mother to hulk
        Boolean hulkWithFather = personHulk.addFather(aquamanPersonID);

//        Update person info in the personJpaRepository
        boolean saveHulkAddingLedgerInRepoResult = personRepository.addAndSaveFather(personHulk);

//        Find person by ID
        Optional<Person> opPerson = personRepository.findById(hulkPersonID);
        Person hulkFromDB = opPerson.get();
        PersonID hulkFatherIDFromDB = hulkFromDB.getFather();

//        Assert
        assertNull(hulkFatherIDFromDB);
        assertEquals(personHulk, hulkFromDB);

//        Delete the person added in this test
        personRepository.delete(hulkSavedInDB);

//        Confirm if the elimination of the person was successful
        long countAfterDelete = personRepository.count();

//        Assert after delete of person
        assertEquals(initialCount, countAfterDelete);
    }

    @Test
    @DisplayName("Test to add Sibling to a person | Happy path")
    public void addSiblingToPerson() {

//        Count all the persons available in the beginning of the test
        long initialCount = personRepository.count();

//        Arrange hulk sibling ID
        String ironManEmail = "irnoman@gmail.com";
        PersonID ironManPersonID = PersonID.createPersonID(ironManEmail);

//        Arrange IronMan Person
        String ironManName = "Tin Iron Man";
        LocalDate ironManBirthdate = LocalDate.of(1963, 1, 9);
        String ironManBirthplace = "Vila Nova de Gaia";
        Person personIronMan = Person.createPerson(ironManEmail, ironManName, ironManBirthdate, ironManBirthplace);

//        Save person in the personJpaRepository
        Person hulkSavedInDB = personRepository.save(personHulk);

//        Save sibling in the personJpaRepository
        Person ironManSavedInDB = personRepository.save(personIronMan);

//        Add sibling to hulk
        Boolean hulkWithFather = personHulk.addSibling(ironManPersonID);

//        Update person info in the personJpaRepository
        boolean saveHulkAddingLedgerInRepoResult = personRepository.addAndSaveSibling(personHulk, ironManPersonID);

//        Find person by ID
        Optional<Person> opPerson = personRepository.findById(hulkPersonID);
        Person hulkFromDB = opPerson.get();
        PersonID hulkSiblingIDFromDB = hulkFromDB.getListOfSiblings().get(0);

//        Assert
        assertEquals(ironManPersonID, hulkSiblingIDFromDB);
        assertEquals(personHulk, hulkFromDB);

//        Delete the person added in this test
        personRepository.delete(hulkSavedInDB);
        personRepository.delete(ironManSavedInDB);

//        Confirm if the elimination of the person was successful
        long countAfterDelete = personRepository.count();

//        Assert after delete of persons
        assertEquals(initialCount, countAfterDelete);
    }

    @Test
    @DisplayName("Test add to a person a sibling that doesn't exists in DB")
    public void addSiblingToPerson_SiblingDoesntExists() {

//        Count all the persons available in the beginning of the test
        long initialCount = personRepository.count();

//        Arrange hulk sibling ID
        String ironManEmail = "irnoman@gmail.com";
        PersonID ironManPersonID = PersonID.createPersonID(ironManEmail);

//        Arrange IronMan Person
        String ironManName = "Tin Iron Man";
        LocalDate ironManBirthdate = LocalDate.of(1963, 1, 9);
        String ironManBirthplace = "Vila Nova de Gaia";
        Person personIronMan = Person.createPerson(ironManEmail, ironManName, ironManBirthdate, ironManBirthplace);

//        Save person in the personJpaRepository
        Person hulkSavedInDB = personRepository.save(personHulk);

//        Add sibling to hulk
        Boolean hulkWithFather = personHulk.addSibling(ironManPersonID);

//        Update person info in the personJpaRepository
        boolean saveHulkAddingLedgerInRepoResult = personRepository.addAndSaveSibling(personHulk, ironManPersonID);

//        Find person by ID
        Optional<Person> opPerson = personRepository.findById(hulkPersonID);
        Person hulkFromDB = opPerson.get();
        PersonID hulkSiblingIDFromDB = hulkFromDB.getListOfSiblings().get(0);

//        Assert
        assertEquals(ironManPersonID, hulkSiblingIDFromDB);
        assertEquals(personHulk, hulkFromDB);

//        Delete the person added in this test
        personRepository.delete(hulkSavedInDB);

//        Confirm if the elimination of the person was successful
        long countAfterDelete = personRepository.count();

//        Assert after delete of person
        assertEquals(initialCount, countAfterDelete);
    }

    @Test
    @DisplayName("Test to find persons Siblings by ID | Happy path")
    public void findPersonsSiblingsByID() {

//        Count all the persons available in the beginning of the test
        long initialCount = personRepository.count();

//        Arrange hulk sibling ID
        String ironManEmail = "irnoman@gmail.com";
        PersonID ironManPersonID = PersonID.createPersonID(ironManEmail);

//        Arrange IronMan Person
        String ironManName = "Tin Iron Man";
        LocalDate ironManBirthdate = LocalDate.of(1963, 1, 9);
        String ironManBirthplace = "Vila Nova de Gaia";
        Person personIronMan = Person.createPerson(ironManEmail, ironManName, ironManBirthdate, ironManBirthplace);

//        Save person in the personJpaRepository
        Person hulkSavedInDB = personRepository.save(personHulk);

//        Save sibling in the personJpaRepository
        Person ironManSavedInDB = personRepository.save(personIronMan);

//        Add sibling to hulk
        Boolean hulkWithFather = personHulk.addSibling(ironManPersonID);

//        Update person info in the personJpaRepository
        boolean saveHulkAddingLedgerInRepoResult = personRepository.addAndSaveSibling(personHulk, ironManPersonID);

//        Find person by ID
        List<PersonID> hulkListOfAllSiblingsID = personRepository.findSiblingsById(hulkPersonID);
        int hulkSiblingsIDFromDB = hulkListOfAllSiblingsID.size();

//        Expected result
        int expectedNumberOfHulkSiblings = 1;

//        Assert
        assertEquals(expectedNumberOfHulkSiblings, hulkSiblingsIDFromDB);

//        Delete the person added in this test
        personRepository.delete(hulkSavedInDB);
        personRepository.delete(ironManSavedInDB);

//        Confirm if the elimination of the person was successful
        long countAfterDelete = personRepository.count();

//        Assert after delete of person
        assertEquals(initialCount, countAfterDelete);
    }

    @Test
    @DisplayName("Test to find persons Siblings by ID | sibling wasn't added to person")
    public void findPersonsSiblingsByIDPersonWithoutSiblings() {

//        Count all the persons available in the beginning of the test
        long initialCount = personRepository.count();

//        Arrange hulk sibling ID
        String ironManEmail = "irnoman@gmail.com";
        PersonID ironManPersonID = PersonID.createPersonID(ironManEmail);

//        Arrange IronMan Person
        String ironManName = "Tin Iron Man";
        LocalDate ironManBirthdate = LocalDate.of(1963, 1, 9);
        String ironManBirthplace = "Vila Nova de Gaia";
        Person personIronMan = Person.createPerson(ironManEmail, ironManName, ironManBirthdate, ironManBirthplace);

//        Save person in the personJpaRepository
        Person hulkSavedInDB = personRepository.save(personHulk);

//        Save sibling in the personJpaRepository
        Person ironManSavedInDB = personRepository.save(personIronMan);

//        Add sibling to hulk
        Boolean hulkWithFather = personHulk.addSibling(ironManPersonID);

//        Find person by ID
        List<PersonID> hulkListOfAllSiblingsID = personRepository.findSiblingsById(hulkPersonID);
        int hulkSiblingsIDFromDB = hulkListOfAllSiblingsID.size();

//        Expected result
        int expectedNumberOfHulkSiblings = 0;

//        Assert
        assertEquals(expectedNumberOfHulkSiblings, hulkSiblingsIDFromDB);

//        Delete the person added in this test
        personRepository.delete(hulkSavedInDB);
        personRepository.delete(ironManSavedInDB);

//        Confirm if the elimination of the person was successful
        long countAfterDelete = personRepository.count();

//        Assert after delete of person
        assertEquals(initialCount, countAfterDelete);
    }

    @Test
    @DisplayName("Test to find persons Siblings by ID | person doesn't exists in DB")
    public void findPersonsSiblingsByIDSiblingDoesntExist() {

//        Count all the persons available in the beginning of the test
        long initialCount = personRepository.count();

//        Arrange hulk sibling ID
        String ironManEmail = "irnoman@gmail.com";
        PersonID ironManPersonID = PersonID.createPersonID(ironManEmail);

//        Arrange IronMan Person
        String ironManName = "Tin Iron Man";
        LocalDate ironManBirthdate = LocalDate.of(1963, 1, 9);
        String ironManBirthplace = "Vila Nova de Gaia";
        Person personIronMan = Person.createPerson(ironManEmail, ironManName, ironManBirthdate, ironManBirthplace);

//        Find person by ID
        List<PersonID> hulkListOfAllSiblingsID = personRepository.findSiblingsById(hulkPersonID);

//        Expected result
        int expectedNumberOfHulkSiblings = 0;

//        Assert
        assertNull(hulkListOfAllSiblingsID);

    }

    @Test
    @DisplayName("Test to add category to person | Happy path")
    public void addCategoryToPerson() {

//        Count all the persons available in the beginning of the test
        long initialCount = personRepository.count();

//        Save person in the personJpaRepository
        Person hulkSavedInDB = personRepository.save(personHulk);

//        Arrange hulk category
        String denomination = "Netflix";

//        Create hulk categoryID
        CategoryID categoryID = CategoryID.createCategoryID(denomination, hulkPersonID);

//        Create hulk categoryToSave
        Category categoryToSave = Category.createCategory(denomination, hulkPersonID);

//        Save sibling in the personJpaRepository
        personHulk.addCategory(categoryID);

//        Update person info in the personJpaRepository
        boolean saveHulkAddingLedgerInRepoResult = personRepository.addAndSaveCategory(personHulk);

//        Find person by ID
        Optional<Person> opHulkFromDB = personRepository.findById(hulkPersonID);
        Person hulkFromDB = opHulkFromDB.get();

//        Get the categories of Hulk
        List<CategoryID> hulkListOfCategories = hulkFromDB.getListOfCategories();
        int hulkNumberOfCategories = hulkListOfCategories.size();
        CategoryID hulkCategoryIDFromDB = hulkListOfCategories.get(0);

//        Expected result
        int expectedNumberOfHulkSiblings = 1;

//        Assert
        assertEquals(expectedNumberOfHulkSiblings, hulkNumberOfCategories);
        assertEquals(categoryID, hulkCategoryIDFromDB);

//        Delete the person added in this test
        personRepository.delete(hulkSavedInDB);

//        Confirm if the elimination of the person was successful
        long countAfterDelete = personRepository.count();

//        Assert after delete of person
        assertEquals(initialCount, countAfterDelete);
    }

    @Test
    @DisplayName("Test to add category to person | Happy path")
    public void addWrongCategoryToPerson() {

//        Count all the persons available in the beginning of the test
        long initialCount = personRepository.count();

//        Save person in the personJpaRepository
        Person hulkSavedInDB = personRepository.save(personHulk);

//        Arrange hulk category
        String denomination = "Netflix";

//        Arrange another hulk category
        String anotherDenomination = "Gym";

//        Create hulk categoryID
        CategoryID categoryID = CategoryID.createCategoryID(denomination, hulkPersonID);

//        Create hulk anotherCategoryID
        CategoryID anotherCategoryID = CategoryID.createCategoryID(anotherDenomination, hulkPersonID);

//        Create hulk categoryToSave
        Category anotherCategoryToSave = Category.createCategory(anotherDenomination, hulkPersonID);

//        Save sibling in the personJpaRepository
        personHulk.addCategory(anotherCategoryID);

//        Update person info in the personJpaRepository
        boolean saveHulkAddingLedgerInRepoResult = personRepository.addAndSaveCategory(personHulk);

//        Find person by ID
        Optional<Person> opHulkFromDB = personRepository.findById(hulkPersonID);
        Person hulkFromDB = opHulkFromDB.get();

//        Get the categories of Hulk
        List<CategoryID> hulkListOfCategories = hulkFromDB.getListOfCategories();
        int hulkNumberOfCategories = hulkListOfCategories.size();
        CategoryID hulkCategoryIDFromDB = hulkListOfCategories.get(0);

//        Expected result
        int expectedNumberOfHulkSiblings = 1;

//        Assert
        assertEquals(expectedNumberOfHulkSiblings, hulkNumberOfCategories);
        assertEquals(anotherCategoryID, hulkCategoryIDFromDB);
        assertNotEquals(categoryID, hulkCategoryIDFromDB);

//        Delete the person added in this test
        personRepository.delete(hulkSavedInDB);

//        Confirm if the elimination of the person was successful
        long countAfterDelete = personRepository.count();

//        Assert after delete of person
        assertEquals(initialCount, countAfterDelete);
    }

    @Test
    @DisplayName("Test to add category to person | category wasn't added to person initially")
    public void addCategoryToPerson_CategoryDoesntExists() {

//        Count all the persons available in the beginning of the test
        long initialCount = personRepository.count();

//        Save person in the personJpaRepository
        Person hulkSavedInDB = personRepository.save(personHulk);

//        Arrange hulk category
        String denomination = "Netflix";

//        Create hulk categoryID
        CategoryID categoryID = CategoryID.createCategoryID(denomination, hulkPersonID);

//        Create hulk categoryToSave
        Category categoryToSave = Category.createCategory(denomination, hulkPersonID);

//        Update person info in the personJpaRepository
        boolean saveHulkAddingLedgerInRepoResult = personRepository.addAndSaveCategory(personHulk);

//        Find person by ID
        Optional<Person> opHulkFromDB = personRepository.findById(hulkPersonID);
        Person hulkFromDB = opHulkFromDB.get();

//        Get the categories of Hulk
        List<CategoryID> hulkListOfCategories = hulkFromDB.getListOfCategories();
        int hulkNumberOfCategories = hulkListOfCategories.size();

//        Expected result
        int expectedNumberOfHulkSiblings = 0;

//        Assert
        assertEquals(expectedNumberOfHulkSiblings, hulkNumberOfCategories);

//        Delete the person added in this test
        personRepository.delete(hulkSavedInDB);

//        Confirm if the elimination of the person was successful
        long countAfterDelete = personRepository.count();

//        Assert after delete of person
        assertEquals(initialCount, countAfterDelete);
    }

    @Test
    @DisplayName("Test to add account to person | Happy path")
    public void addAccountToPerson() {

//        Count all the persons available in the beginning of the test
        long initialCount = personRepository.count();

//        Save person in the personJpaRepository
        Person hulkSavedInDB = personRepository.save(personHulk);

//        Arrange hulk group account
        String hulkAccountDenomination = "Marvel Netflix";
        String hulkAccountDescription = "Marvel account";

//        Create hulk categoryID
        AccountID accountID = AccountID.createAccountID(hulkAccountDenomination, hulkPersonID);

//        Create hulk categoryToSave
        Account accountToSave = Account.createAccount(hulkAccountDescription, hulkAccountDenomination, hulkPersonID);

//        Save sibling in the personJpaRepository
        personHulk.addAccount(accountID);

//        Update person info in the personJpaRepository
        personRepository.addAndSaveAccount(personHulk, hulkAccountDescription);

//        Find person by ID
        Optional<Person> opHulkFromDB = personRepository.findById(hulkPersonID);
        Person hulkFromDB = opHulkFromDB.get();

//        Get the categories of Hulk
        List<AccountID> hulkListOfAccounts = hulkFromDB.getListOfAccounts();
        int hulkNumberOfAccounts = hulkListOfAccounts.size();
        AccountID hulkAccountIDFromDB = hulkListOfAccounts.get(0);

//        Expected result
        int expectedNumberOfHulkSiblings = 1;

//        Assert
        assertEquals(expectedNumberOfHulkSiblings, hulkNumberOfAccounts);
        assertEquals(accountID, hulkAccountIDFromDB);

//        Delete the person added in this test
        personRepository.delete(hulkSavedInDB);

//        Confirm if the elimination of the person was successful
        long countAfterDelete = personRepository.count();

//        Assert after delete of person
        assertEquals(initialCount, countAfterDelete);
    }

    @Test
    @DisplayName("Test to add wrong account to person")
    public void addWrongAccountToPerson() {

//        Count all the persons available in the beginning of the test
        long initialCount = personRepository.count();

//        Save person in the personJpaRepository
        Person hulkSavedInDB = personRepository.save(personHulk);

//        Arrange hulk account
        String hulkAccountDenomination = "Marvel Netflix";
        String hulkAccountDescription = "Marvel account";

//        Arrange hulk group account
        String hulkAnotherAccountDenomination = "Marvel Gym";
        String hulkAnotherAccountDescription = "Marvel Gym account";

//        Create hulk another AccountID
        AccountID accountID = AccountID.createAccountID(hulkAccountDenomination, hulkPersonID);

//        Create hulk another AccountID
        AccountID anotherAccountID = AccountID.createAccountID(hulkAnotherAccountDenomination, hulkPersonID);

//        Create hulk another accountToSave
        Account accountToSave = Account.createAccount(hulkAnotherAccountDescription, hulkAnotherAccountDenomination, hulkPersonID);

//        Save sibling in the personJpaRepository
        personHulk.addAccount(anotherAccountID);

//        Update person info in the personJpaRepository
        personRepository.addAndSaveAccount(personHulk, hulkAnotherAccountDenomination);

//        Find person by ID
        Optional<Person> opHulkFromDB = personRepository.findById(hulkPersonID);
        Person hulkFromDB = opHulkFromDB.get();

//        Get the categories of Hulk
        List<AccountID> hulkListOfAccounts = hulkFromDB.getListOfAccounts();
        int hulkNumberOfAccounts = hulkListOfAccounts.size();
        AccountID hulkAccountIDFromDB = hulkListOfAccounts.get(0);

//        Expected result
        int expectedNumberOfHulkSiblings = 1;

//        Assert
        assertEquals(expectedNumberOfHulkSiblings, hulkNumberOfAccounts);
        assertEquals(anotherAccountID, hulkAccountIDFromDB);
        assertNotEquals(accountID, hulkAccountIDFromDB);

//        Delete the person added in this test
        personRepository.delete(hulkSavedInDB);

//        Confirm if the elimination of the person was successful
        long countAfterDelete = personRepository.count();

//        Assert after delete of person
        assertEquals(initialCount, countAfterDelete);
    }

    @Test
    @DisplayName("Verify if a person exists in DB | Happy path")
    public void personExistsInDB() {

//        Count all the persons available in the beginning of the test
        long initialCount = personRepository.count();

//        Arrange Wolverine PersonID
        String wolverineEmail = "wolverine@gmail.com";
        PersonID wolverinePersonID = PersonID.createPersonID(hulkEmail);

//        Arrange Wolverine Person
        String wolverineName = "Wolverine";
        LocalDate wolverineBirthdate = LocalDate.of(1983, 6, 25);
        String wolverineBirthplace = "Porto";
        Person personWolverine = Person.createPerson(wolverineEmail, wolverineName, wolverineBirthdate, wolverineBirthplace);

//        Save person in the personJpaRepository
        Person hulkSavedInDB = personRepository.save(personHulk);
        Person wolverineSavedInDB = personRepository.save(personWolverine);

//        Assert
        assertTrue(personRepository.exists(hulkPersonID));
        assertTrue(personRepository.exists(wolverinePersonID));

//        Delete the person added in this test
        personRepository.delete(hulkSavedInDB);
        personRepository.delete(wolverineSavedInDB);

//        Confirm if the elimination of the person was successful
        long countAfterDelete = personRepository.count();

//        Assert after delete of persons
        assertEquals(initialCount, countAfterDelete);
    }

    @Test
    @DisplayName("Verify if a person exists in DB | No person added to DB")
    public void personExistsInDBDoesntExists() {

//        Arrange Wolverine PersonID
        String wolverineEmail = "wolverine@gmail.com";
        PersonID wolverinePersonID = PersonID.createPersonID(hulkEmail);

//        Arrange Wolverine Person
        String wolverineName = "Wolverine";
        LocalDate wolverineBirthdate = LocalDate.of(1983, 6, 25);
        String wolverineBirthplace = "Porto";
        Person personWolverine = Person.createPerson(wolverineEmail, wolverineName, wolverineBirthdate, wolverineBirthplace);

//        Expected results
        boolean hulkExistence = personRepository.exists(hulkPersonID);
        boolean wolverineExistence = personRepository.exists(wolverinePersonID);

//        Assert
        assertFalse(hulkExistence);
        assertFalse(wolverineExistence);
    }


    @Test
    @DisplayName("Test to count persons in DB | Saved 2 persons")
    public void countAllPersonsInDB_PersonsAdded() {

//        Count all the persons available in the beginning of the test
        long initialCount = personRepository.count();

//        Arrange Wolverine PersonID
        String wolverineEmail = "wolverine@gmail.com";
        PersonID wolverinePersonID = PersonID.createPersonID(hulkEmail);

//        Arrange Wolverine Person
        String wolverineName = "Wolverine";
        LocalDate wolverineBirthdate = LocalDate.of(1983, 6, 25);
        String wolverineBirthplace = "Porto";
        Person personWolverine = Person.createPerson(wolverineEmail, wolverineName, wolverineBirthdate, wolverineBirthplace);

//        Save person in the personJpaRepository
        Person hulkSavedInDB = personRepository.save(personHulk);
        Person wolverineSavedInDB = personRepository.save(personWolverine);

//        Count all the persons available in the personJpaRepository
        int numberOfPersonsInDB = (int) personRepository.count();

//        Expected result (Person not saved in the personRepository)
        int expectedNumberOfPersonsInDB = (int) initialCount + 2;

//        Assert
        assertEquals(expectedNumberOfPersonsInDB, numberOfPersonsInDB);
        assertTrue(personRepository.exists(hulkPersonID));
        assertTrue(personRepository.exists(wolverinePersonID));

//        Delete the person added in this test
        personRepository.delete(hulkSavedInDB);
        personRepository.delete(wolverineSavedInDB);

//        Confirm if the elimination of the person was successful
        long countAfterDelete = personRepository.count();

//        Assert after delete of persons
        assertEquals(initialCount, countAfterDelete);
    }

    @Test
    @DisplayName("Test to findAll persons in DB | No persons saved into DB")
    public void countAllPersonsInDB_PersonsNotAdded() {

//        Count all the persons available in the beginning of the test
        long initialCount = personRepository.count();

//        Count all the persons available in the personJpaRepository
        int numberOfPersonsInDB = (int) personRepository.count();

//        Expected result (Person not saved in the personRepository)
        int expectedNumberOfPersonsInDB = (int) initialCount;

//        Assert
        assertEquals(expectedNumberOfPersonsInDB, numberOfPersonsInDB);
    }

    @Test
    @DisplayName("Test to findAll persons in DB | Happy path")
    public void findAllPersonsInDB() {

//        Count all the persons available in the beginning of the test
        long initialCount = personRepository.count();

//        Arrange Wolverine PersonID
        String wolverineEmail = "wolverine@gmail.com";
        PersonID wolverinePersonID = PersonID.createPersonID(hulkEmail);

//        Arrange Wolverine Person
        String wolverineName = "Wolverine";
        LocalDate wolverineBirthdate = LocalDate.of(1983, 6, 25);
        String wolverineBirthplace = "Porto";
        Person personWolverine = Person.createPerson(wolverineEmail, wolverineName, wolverineBirthdate, wolverineBirthplace);

//        Save person in the personJpaRepository
        Person hulkSavedInDB = personRepository.save(personHulk);
        Person wolverineSavedInDB = personRepository.save(personWolverine);

//        Find all persons ion the DB
        List<Person> allPersonInDB = personRepository.findAll();

//        Analyze the size of the list allPersonInDB
        int sizeOfListAllPersonInDB = allPersonInDB.size();

//        Count all the persons available in the personJpaRepository
        int numberOfPersonsInDB = (int) personRepository.count();

//        Expected result (Person not saved in the personRepository)
        int expectedNumberOfPersonsInDB = (int) initialCount + 2;

//        Assert
        assertEquals(expectedNumberOfPersonsInDB, sizeOfListAllPersonInDB);
        assertEquals(expectedNumberOfPersonsInDB, numberOfPersonsInDB);
        assertTrue(personRepository.exists(hulkPersonID));
        assertTrue(personRepository.exists(wolverinePersonID));

//        Delete the person added in this test
        personRepository.delete(hulkSavedInDB);
        personRepository.delete(wolverineSavedInDB);

//        Confirm if the elimination of the person was successful
        long countAfterDelete = personRepository.count();

//        Assert after delete of persons
        assertEquals(initialCount, countAfterDelete);
    }

    @Test
    @DisplayName("Test to findAll persons in DB | No persons saved into DB")
    public void findAllPersonsInDB_EmptyDB() {

//        Count all the persons available in the beginning of the test
        long initialCount = personRepository.count();

//        Find all persons ion the DB
        List<Person> allPersonInDB = personRepository.findAll();

//        Analyze the size of the list allPersonInDB
        int sizeOfListAllPersonInDB = allPersonInDB.size();

//        Count all the persons available in the personJpaRepository
        int numberOfPersonsInDB = (int) personRepository.count();

//        Expected result (Person not saved in the personRepository)
        int expectedNumberOfPersonsInDB = (int) initialCount;

//        Assert
        assertEquals(expectedNumberOfPersonsInDB, sizeOfListAllPersonInDB);
        assertEquals(expectedNumberOfPersonsInDB, numberOfPersonsInDB);
        assertFalse(personRepository.exists(hulkPersonID));
    }
}