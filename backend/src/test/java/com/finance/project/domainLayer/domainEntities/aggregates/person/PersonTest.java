package com.finance.project.domainLayer.domainEntities.aggregates.person;

import com.finance.project.domainLayer.domainEntities.aggregates.ledger.Ledger;
import com.finance.project.domainLayer.domainEntities.vosShared.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {

    //test constructor with parameters name and birthdate

    @Test
    public void checkConstructor() {

        //Arrange

        //Person
        String emailJoana = "joana@gmail.com";
        String name = "Joana";
        LocalDate birthdate = LocalDate.of(1987, 01, 11);
        Ledger ledger = Ledger.createLedger();
        LedgerID ledgerID = ledger.getLedgerID();

        //Expected
        PersonID expectedPersonID = PersonID.createPersonID(emailJoana);
        Name expectedName = Name.createName(name);
        Birthdate expectedBirthdate = Birthdate.createBirthdate(birthdate);

        //Birthplace
        String birthplace = "Braga";

        //Address
        String street = "rua da saudade";
        String doorNumber = "12";
        String postCode = "4000-121";
        String city = "Porto";
        String country = "Portugal";
        Address address = Address.createAddress(street, doorNumber, postCode, city, country);

        //Act
        Person person = Person.createPersonWithoutParents(emailJoana, name, birthdate, birthplace, address, ledgerID);

        //Assert
        assertEquals(expectedName, person.getName());
        assertEquals(expectedBirthdate, person.getBirthdate());
        Assertions.assertEquals(expectedPersonID, person.getPersonID());
        Assertions.assertEquals(ledgerID, person.getLedgerID());
    }


    @Test
    public void checkNewConstructor() {

        //Arrange

        //Person
        String emailJoana = "joana@gmail.com";
        String name = "Joana";
        LocalDate birthdate = LocalDate.of(1987, 01, 11);
        Ledger ledger = Ledger.createLedger();
        LedgerID ledgerID = ledger.getLedgerID();

        //Birthplace
        String birthplace = "Braga";


        //Expected
        PersonID expectedPersonID = PersonID.createPersonID(emailJoana);
        Name expectedName = Name.createName(name);
        Birthdate expectedBirthdate = Birthdate.createBirthdate(birthdate);
        Birthplace expectedBirthplace = Birthplace.createBirthplace(birthplace);
        Email expectedEmail = Email.createEmail(emailJoana);


        //Address
        String street = "rua da saudade";
        String doorNumber = "12";
        String postCode = "4000-121";
        String city = "Porto";
        String country = "Portugal";
        Address address = Address.createAddress(street, doorNumber, postCode, city, country);

        //Act
        Person person = Person.createPerson(emailJoana, name, birthdate, birthplace);

        //Assert
        Assertions.assertEquals(expectedEmail, person.getEmail());
        assertEquals(expectedName, person.getName());
        assertEquals(expectedBirthdate, person.getBirthdate());
        assertEquals(expectedBirthplace, person.getBirthplace());
    }


    //test construtor with parameters name, birthdate, mother, father


    @Test
    public void checkConstrutor_WithMotherFather() {

        //Arrange

        //Person
        String emailJoana = "joana@gmail.com";
        String name = "Joana";
        LocalDate birthdate = LocalDate.of(1987, 01, 11);
        Ledger ledger = Ledger.createLedger();
        LedgerID ledgerID = ledger.getLedgerID();

        //Person Mother
        String emailMother = "ana@gmail.com";
        String nameMother = "Ana";
        LocalDate birthdateMother = LocalDate.of(1987, 01, 11);
        Ledger ledgerMother = Ledger.createLedger();
        LedgerID ledgerID_Mother = ledgerMother.getLedgerID();

        //Person Father
        String emailFather = "joao@gmail.com";
        String nameFather = "João";
        LocalDate birthdateFather = LocalDate.of(1987, 01, 11);
        Ledger ledgerFather = Ledger.createLedger();
        LedgerID ledgerID_Father = ledgerFather.getLedgerID();

        //Birthplace
        String birthplace = "Braga";

        //Address
        String street = "rua da saudade";
        String doorNumber = "12";
        String postCode = "4000-121";
        String city = "Porto";
        String country = "Portugal";
        Address address = Address.createAddress(street, doorNumber, postCode, city, country);

        //Expected
        PersonID expectedPersonID = PersonID.createPersonID(emailJoana);
        Name expectedName = Name.createName(name);
        Birthdate expectedBirthdate = Birthdate.createBirthdate(birthdate);
        PersonID expectedPersonIDMother = PersonID.createPersonID(emailMother);
        PersonID expectedPersonIDFather = PersonID.createPersonID(emailFather);


        //Act
        Person mother = Person.createPersonWithoutParents(emailMother, nameMother, birthdateMother, birthplace, address, ledgerID_Mother);
        Person father = Person.createPersonWithoutParents(emailFather, nameFather, birthdateFather, birthplace, address, ledgerID_Father);
        Person person = Person.createPersonWithParents(emailJoana, name, birthdate, mother.getPersonID(), father.getPersonID(), birthplace, address, ledgerID);

        //Assert
        assertEquals(expectedName, person.getName());
        assertEquals(expectedBirthdate, person.getBirthdate());
        Assertions.assertEquals(expectedPersonIDMother, person.getMother());
        Assertions.assertEquals(expectedPersonIDFather, person.getFather());
        Assertions.assertEquals(expectedPersonID, person.getPersonID());
        Assertions.assertEquals(ledgerID, person.getLedgerID());
    }

    //test construtor with parameters name, birthdate, mother, father, birthplace and an address

    @Test
    public void checkConstrutor_WithMotherFatherBirthplaceAdress() {

        //Arrange

        //Person
        String name = "Joana";
        LocalDate birthdate = LocalDate.of(1987, 01, 11);
        String emailJoana = "joana@gmail.com";
        Ledger ledger = Ledger.createLedger();
        LedgerID ledgerID = ledger.getLedgerID();

        //Person Mother
        String nameMother = "Ana";
        LocalDate birthdateMother = LocalDate.of(1987, 01, 11);
        String emailMother = "ana@gmail.com";
        Ledger ledgerMother = Ledger.createLedger();
        LedgerID ledgerID_Mother = ledgerMother.getLedgerID();

        //Person Father
        String nameFather = "João";
        LocalDate birthdateFather = LocalDate.of(1987, 01, 11);
        String emailFather = "joao@gmail.com";
        Ledger ledgerFather = Ledger.createLedger();
        LedgerID ledgerID_Father = ledgerFather.getLedgerID();

        //Birthplace
        String birthplace = "Braga";

        //Address
        String street = "rua da saudade";
        String doorNumber = "12";
        String postCode = "4000-121";
        String city = "Porto";
        String country = "Portugal";
        Address address = Address.createAddress(street, doorNumber, postCode, city, country);

        //Expected
        PersonID expectedPersonID = PersonID.createPersonID(emailJoana);
        Name expectedName = Name.createName(name);
        Birthdate expectedBirthdate = Birthdate.createBirthdate(birthdate);
        PersonID expectedPersonIDMother = PersonID.createPersonID(emailMother);
        PersonID expectedPersonIDFather = PersonID.createPersonID(emailFather);
        Birthplace expectedBirthplace = Birthplace.createBirthplace(birthplace);

        //Act
        Person mother = Person.createPersonWithoutParents(emailMother, nameMother, birthdateMother, birthplace, address, ledgerID_Mother);
        Person father = Person.createPersonWithoutParents(emailFather, nameFather, birthdateFather, birthplace, address, ledgerID_Father);
        Person person = Person.createPersonWithParents(emailJoana, name, birthdate, mother.getPersonID(), father.getPersonID(), birthplace, address, ledgerID);

        //Assert
        assertEquals(expectedName, person.getName());
        assertEquals(expectedBirthdate, person.getBirthdate());
        Assertions.assertEquals(expectedPersonIDMother, person.getMother());
        Assertions.assertEquals(expectedPersonIDFather, person.getFather());
        assertEquals(expectedBirthplace, person.getBirthplace());
        assertEquals(address, person.getAddress());
        Assertions.assertEquals(expectedPersonID, person.getPersonID());
        Assertions.assertEquals(ledgerID, person.getLedgerID());
    }



    // Throw exception for the person new constructor

    @Test
    @DisplayName("Test For Person- getEmailException()")

    public void checkException_email() {

        //Arrange

        //Person
        String name = "Joana";
        LocalDate birthdate = LocalDate.of(1987, 01, 11);
        String emailJoana = null;

        //Person Mother
        String nameMother = "Ana";
        LocalDate birthdateMother = LocalDate.of(1987, 01, 11);
        String emailMother = "ana@gmail.com";
        Ledger ledgerMother = Ledger.createLedger();
        LedgerID ledgerID_Mother = ledgerMother.getLedgerID();

        //Person Father
        String nameFather = "João";
        LocalDate birthdateFather = LocalDate.of(1987, 01, 11);
        String emailFather = "joao@gmail.com";
        Ledger ledgerFather = Ledger.createLedger();
        LedgerID ledgerID_Father = ledgerFather.getLedgerID();

        //Birthplace
        String birthplace = "Braga";

        //Address
        String street = "rua da saudade";
        String doorNumber = "12";
        String postCode = "4000-121";
        String city = "Porto";
        String country = "Portugal";
        Address addressP = Address.createAddress(street, doorNumber, postCode, city, country);


        //Act
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> Person.createPerson(emailJoana, name, birthdate, birthplace));

        //Assert
        assertEquals(thrown.getMessage(), "Person not created due to the fact that the email parameter hasn't a valid argument");
    }


    @Test
    @DisplayName("Test For Person- getEmailException_Empty")

    public void checkException_email_Empty() {

        //Arrange

        //Person
        String name = "Joana";
        LocalDate birthdate = LocalDate.of(1987, 01, 11);
        String emailJoana = "";

        //Person Mother
        String nameMother = "Ana";
        LocalDate birthdateMother = LocalDate.of(1987, 01, 11);
        String emailMother = "ana@gmail.com";
        Ledger ledgerMother = Ledger.createLedger();
        LedgerID ledgerID_Mother = ledgerMother.getLedgerID();

        //Person Father
        String nameFather = "João";
        LocalDate birthdateFather = LocalDate.of(1987, 01, 11);
        String emailFather = "joao@gmail.com";
        Ledger ledgerFather = Ledger.createLedger();
        LedgerID ledgerID_Father = ledgerFather.getLedgerID();

        //Birthplace
        String birthplace = "Braga";

        //Address
        String street = "rua da saudade";
        String doorNumber = "12";
        String postCode = "4000-121";
        String city = "Porto";
        String country = "Portugal";
        Address addressP = Address.createAddress(street, doorNumber, postCode, city, country);


        //Act
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> Person.createPerson(emailJoana, name, birthdate, birthplace));

        //Assert
        assertEquals(thrown.getMessage(), "Person not created due to the fact that the email parameter hasn't a valid argument");
    }


    @Test
    @DisplayName("Test For Person- getNameException()")

    public void checkException_name() {

        //Arrange

        //Person
        String name = null;
        LocalDate birthdate = LocalDate.of(1987, 01, 11);
        String emailJoana = "joana@gmail.com";

        //Person Mother
        String nameMother = "Ana";
        LocalDate birthdateMother = LocalDate.of(1987, 01, 11);
        String emailMother = "ana@gmail.com";
        Ledger ledgerMother = Ledger.createLedger();
        LedgerID ledgerID_Mother = ledgerMother.getLedgerID();

        //Person Father
        String nameFather = "João";
        LocalDate birthdateFather = LocalDate.of(1987, 01, 11);
        String emailFather = "joao@gmail.com";
        Ledger ledgerFather = Ledger.createLedger();
        LedgerID ledgerID_Father = ledgerFather.getLedgerID();

        //Birthplace
        String birthplace = "Braga";

        //Address
        String street = "rua da saudade";
        String doorNumber = "12";
        String postCode = "4000-121";
        String city = "Porto";
        String country = "Portugal";
        Address addressP = Address.createAddress(street, doorNumber, postCode, city, country);


        //Act
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> Person.createPerson(emailJoana, name, birthdate, birthplace));

        //Assert
        assertEquals(thrown.getMessage(), "Person not created due to the fact that the name parameter hasn't a valid argument");
    }

    @Test
    @DisplayName("Test For Person- getNameException_Empty")

    public void checkException_name_Empty() {

        //Arrange

        //Person
        String name = "";
        LocalDate birthdate = LocalDate.of(1987, 01, 11);
        String emailJoana = "joana@gmail.com";

        //Person Mother
        String nameMother = "Ana";
        LocalDate birthdateMother = LocalDate.of(1987, 01, 11);
        String emailMother = "ana@gmail.com";
        Ledger ledgerMother = Ledger.createLedger();
        LedgerID ledgerID_Mother = ledgerMother.getLedgerID();

        //Person Father
        String nameFather = "João";
        LocalDate birthdateFather = LocalDate.of(1987, 01, 11);
        String emailFather = "joao@gmail.com";
        Ledger ledgerFather = Ledger.createLedger();
        LedgerID ledgerID_Father = ledgerFather.getLedgerID();

        //Birthplace
        String birthplace = "Braga";

        //Address
        String street = "rua da saudade";
        String doorNumber = "12";
        String postCode = "4000-121";
        String city = "Porto";
        String country = "Portugal";
        Address addressP = Address.createAddress(street, doorNumber, postCode, city, country);


        //Act
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> Person.createPerson(emailJoana, name, birthdate, birthplace));

        //Assert
        assertEquals(thrown.getMessage(), "Person not created due to the fact that the name parameter hasn't a valid argument");
    }




    @Test
    @DisplayName("Test For Person- getBirthdateException()")

    public void checkException_birthdate() {

        //Arrange

        //Person
        String name = "Joana";
        LocalDate birthdate = null;
        String emailJoana = "joana@gmail.com";

        //Person Mother
        String nameMother = "Ana";
        LocalDate birthdateMother = LocalDate.of(1987, 01, 11);
        String emailMother = "ana@gmail.com";
        Ledger ledgerMother = Ledger.createLedger();
        LedgerID ledgerID_Mother = ledgerMother.getLedgerID();

        //Person Father
        String nameFather = "João";
        LocalDate birthdateFather = LocalDate.of(1987, 01, 11);
        String emailFather = "joao@gmail.com";
        Ledger ledgerFather = Ledger.createLedger();
        LedgerID ledgerID_Father = ledgerFather.getLedgerID();

        //Birthplace
        String birthplace = "Braga";

        //Address
        String street = "rua da saudade";
        String doorNumber = "12";
        String postCode = "4000-121";
        String city = "Porto";
        String country = "Portugal";
        Address addressP = Address.createAddress(street, doorNumber, postCode, city, country);


        //Act
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> Person.createPerson(emailJoana, name, birthdate, birthplace));

        //Assert
        assertEquals(thrown.getMessage(), "Person not created due to the fact that the birthdate parameter hasn't a valid argument");
    }




    @Test
    @DisplayName("Test For Person- getBirthplaceException()")

    public void checkException_birthplace() {

        //Arrange

        //Person
        String name = "Joana";
        LocalDate birthdate = LocalDate.of(1987, 01, 11);
        String emailJoana = "joana@gmail.com";

        //Person Mother
        String nameMother = "Ana";
        LocalDate birthdateMother = LocalDate.of(1987, 01, 11);
        String emailMother = "ana@gmail.com";
        Ledger ledgerMother = Ledger.createLedger();
        LedgerID ledgerID_Mother = ledgerMother.getLedgerID();

        //Person Father
        String nameFather = "João";
        LocalDate birthdateFather = LocalDate.of(1987, 01, 11);
        String emailFather = "joao@gmail.com";
        Ledger ledgerFather = Ledger.createLedger();
        LedgerID ledgerID_Father = ledgerFather.getLedgerID();

        //Birthplace
        String birthplace = null;

        //Address
        String street = "rua da saudade";
        String doorNumber = "12";
        String postCode = "4000-121";
        String city = "Porto";
        String country = "Portugal";
        Address addressP = Address.createAddress(street, doorNumber, postCode, city, country);


        //Act
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> Person.createPerson(emailJoana, name, birthdate, birthplace));

        //Assert
        assertEquals(thrown.getMessage(), "Person not created due to the fact that the birthplace parameter hasn't a valid argument");
    }


    @Test
    @DisplayName("Test For Person- getBirthplaceException_Empty")

    public void checkException_birthplace_Empty() {

        //Arrange

        //Person
        String name = "Joana";
        LocalDate birthdate = LocalDate.of(1987, 01, 11);
        String emailJoana = "joana@gmail.com";

        //Person Mother
        String nameMother = "Ana";
        LocalDate birthdateMother = LocalDate.of(1987, 01, 11);
        String emailMother = "ana@gmail.com";
        Ledger ledgerMother = Ledger.createLedger();
        LedgerID ledgerID_Mother = ledgerMother.getLedgerID();

        //Person Father
        String nameFather = "João";
        LocalDate birthdateFather = LocalDate.of(1987, 01, 11);
        String emailFather = "joao@gmail.com";
        Ledger ledgerFather = Ledger.createLedger();
        LedgerID ledgerID_Father = ledgerFather.getLedgerID();

        //Birthplace
        String birthplace = "";

        //Address
        String street = "rua da saudade";
        String doorNumber = "12";
        String postCode = "4000-121";
        String city = "Porto";
        String country = "Portugal";
        Address addressP = Address.createAddress(street, doorNumber, postCode, city, country);


        //Act
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> Person.createPerson(emailJoana, name, birthdate, birthplace));

        //Assert
        assertEquals(thrown.getMessage(), "Person not created due to the fact that the birthplace parameter hasn't a valid argument");
    }






    //Throw exception for the person constructor

    @Test
    @DisplayName("Test For Person- getLedgerIDException()")

    public void checkException_ledgerID() {

        //Arrange

        //Person
        String name = "Joana";
        LocalDate birthdate = LocalDate.of(1987, 01, 11);
        String emailJoana = "joana@gmail.com";
        LedgerID ledgerID = null;

        //Person Mother
        String nameMother = "Ana";
        LocalDate birthdateMother = LocalDate.of(1987, 01, 11);
        String emailMother = "ana@gmail.com";
        Ledger ledgerMother = Ledger.createLedger();
        LedgerID ledgerID_Mother = ledgerMother.getLedgerID();

        //Person Father
        String nameFather = "João";
        LocalDate birthdateFather = LocalDate.of(1987, 01, 11);
        String emailFather = "joao@gmail.com";
        Ledger ledgerFather = Ledger.createLedger();
        LedgerID ledgerID_Father = ledgerFather.getLedgerID();

        //Birthplace
        String birthplace = "Braga";

        //Address
        String street = "rua da saudade";
        String doorNumber = "12";
        String postCode = "4000-121";
        String city = "Porto";
        String country = "Portugal";
        Address addressP = Address.createAddress(street, doorNumber, postCode, city, country);

        Person personMother = Person.createPersonWithoutParents(emailMother, nameMother, birthdateMother, birthplace, addressP, ledgerID_Mother);
        Person personFather = Person.createPersonWithoutParents(emailFather, nameFather, birthdateFather, birthplace, addressP, ledgerID_Father);

        //Act
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> Person.createPersonWithParents(emailJoana, name, birthdate, personMother.getPersonID(), personFather.getPersonID(), birthplace, addressP, ledgerID));

        //Assert
        assertEquals(thrown.getMessage(), "Person not created due to the fact that the ledgerID parameter hasn't a valid argument");
    }

    @Test
    @DisplayName("Test For Person- getNameException() | Name Empty")

    public void checkExceptionNameEmpty() {

        //Arrange

        //Person
        String name = "";
        LocalDate birthdate = LocalDate.of(1987, 01, 11);
        String emailJoana = "joana@gmail.com";
        Ledger ledger = Ledger.createLedger();
        LedgerID ledgerID = ledger.getLedgerID();

        //Person Mother
        String nameMother = "Ana";
        LocalDate birthdateMother = LocalDate.of(1987, 01, 11);
        String emailMother = "ana@gmail.com";
        Ledger ledgerMother = Ledger.createLedger();
        LedgerID ledgerID_Mother = ledgerMother.getLedgerID();

        //Person Father
        String nameFather = "João";
        LocalDate birthdateFather = LocalDate.of(1987, 01, 11);
        String emailFather = "joao@gmail.com";
        Ledger ledgerFather = Ledger.createLedger();
        LedgerID ledgerID_Father = ledgerFather.getLedgerID();

        //Birthplace
        String birthplace = "Braga";

        //Address
        String street = "rua da saudade";
        String doorNumber = "12";
        String postCode = "4000-121";
        String city = "Porto";
        String country = "Portugal";
        Address addressP = Address.createAddress(street, doorNumber, postCode, city, country);

        Person personMother = Person.createPersonWithoutParents(emailMother, nameMother, birthdateMother, birthplace, addressP, ledgerID_Mother);
        Person personFather = Person.createPersonWithoutParents(emailFather, nameFather, birthdateFather, birthplace, addressP, ledgerID_Father);

        //Act
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> Person.createPersonWithParents(emailJoana, name, birthdate, personMother.getPersonID(), personFather.getPersonID(), birthplace, addressP, ledgerID));

        //Assert
        assertEquals(thrown.getMessage(), "Person not created due to the fact that the name parameter hasn't a valid argument");
    }

    @Test
    @DisplayName("Test For Person- getNameException() | Name Null")

    public void checkExceptionNameNull() {

        //Arrange

        //Person
        String name = null;
        LocalDate birthdate = LocalDate.of(1987, 01, 11);
        String emailJoana = "joana@gmail.com";
        Ledger ledger = Ledger.createLedger();
        LedgerID ledgerID = ledger.getLedgerID();

        //Person Mother
        String nameMother = "Ana";
        LocalDate birthdateMother = LocalDate.of(1987, 01, 11);
        String emailMother = "ana@gmail.com";
        Ledger ledgerMother = Ledger.createLedger();
        LedgerID ledgerID_Mother = ledgerMother.getLedgerID();

        //Person Father
        String nameFather = "João";
        LocalDate birthdateFather = LocalDate.of(1987, 01, 11);
        String emailFather = "joao@gmail.com";
        Ledger ledgerFather = Ledger.createLedger();
        LedgerID ledgerID_Father = ledgerFather.getLedgerID();

        //Birthplace
        String birthplace = "Braga";

        //Address
        String street = "rua da saudade";
        String doorNumber = "12";
        String postCode = "4000-121";
        String city = "Porto";
        String country = "Portugal";
        Address addressP = Address.createAddress(street, doorNumber, postCode, city, country);

        Person personMother = Person.createPersonWithoutParents(emailMother, nameMother, birthdateMother, birthplace, addressP, ledgerID_Mother);
        Person personFather = Person.createPersonWithoutParents(emailFather, nameFather, birthdateFather, birthplace, addressP, ledgerID_Father);

        //Act
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> Person.createPersonWithParents(emailJoana, name, birthdate, personMother.getPersonID(), personFather.getPersonID(), birthplace, addressP, ledgerID));

        //Assert
        assertEquals(thrown.getMessage(), "Person not created due to the fact that the name parameter hasn't a valid argument");
    }


    @Test
    @DisplayName("Test For Person- getBirthplaceException() | Birthplace Empty")

    public void checkExceptionBirthplaceEmpty() {

        //Arrange

        //Person
        String name = "Joana";
        LocalDate birthdate = LocalDate.of(1987, 01, 11);
        String emailJoana = "joana@gmail.com";
        Ledger ledger = Ledger.createLedger();
        LedgerID ledgerID = ledger.getLedgerID();

        //Person Mother
        String nameMother = "Ana";
        LocalDate birthdateMother = LocalDate.of(1987, 01, 11);
        String emailMother = "ana@gmail.com";
        Ledger ledgerMother = Ledger.createLedger();
        LedgerID ledgerID_Mother = ledgerMother.getLedgerID();

        //Person Father
        String nameFather = "João";
        LocalDate birthdateFather = LocalDate.of(1987, 01, 11);
        String emailFather = "joao@gmail.com";
        Ledger ledgerFather = Ledger.createLedger();
        LedgerID ledgerID_Father = ledgerFather.getLedgerID();

        //Birthplace
        String birthplaceParents = "Braga";
        String birthplace = "";

        //Address
        String street = "rua da saudade";
        String doorNumber = "12";
        String postCode = "4000-121";
        String city = "Porto";
        String country = "Portugal";
        Address addressP = Address.createAddress(street, doorNumber, postCode, city, country);

        Person personMother = Person.createPersonWithoutParents(emailMother, nameMother, birthdateMother, birthplaceParents, addressP, ledgerID_Mother);
        Person personFather = Person.createPersonWithoutParents(emailFather, nameFather, birthdateFather, birthplaceParents, addressP, ledgerID_Father);

        //Act
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> Person.createPersonWithParents(emailJoana, name, birthdate, personMother.getPersonID(), personFather.getPersonID(), birthplace, addressP, ledgerID));

        //Assert
        assertEquals(thrown.getMessage(), "Person not created due to the fact that the birthplace parameter hasn't a valid argument");
    }

    @Test
    @DisplayName("Test For Person- getBirthplaceException() | Birthplace Null")

    public void checkExceptionBirthplaceNull() {

        //Arrange

        //Person
        String name = "Joana";
        LocalDate birthdate = LocalDate.of(1987, 01, 11);
        String emailJoana = "joana@gmail.com";
        Ledger ledger = Ledger.createLedger();
        LedgerID ledgerID = ledger.getLedgerID();

        //Person Mother
        String nameMother = "Ana";
        LocalDate birthdateMother = LocalDate.of(1987, 01, 11);
        String emailMother = "ana@gmail.com";
        Ledger ledgerMother = Ledger.createLedger();
        LedgerID ledgerID_Mother = ledgerMother.getLedgerID();

        //Person Father
        String nameFather = "João";
        LocalDate birthdateFather = LocalDate.of(1987, 01, 11);
        String emailFather = "joao@gmail.com";
        Ledger ledgerFather = Ledger.createLedger();
        LedgerID ledgerID_Father = ledgerFather.getLedgerID();

        //Birthplace
        String birthplaceParents = "Braga";
        String birthplace = null;

        //Address
        String street = "rua da saudade";
        String doorNumber = "12";
        String postCode = "4000-121";
        String city = "Porto";
        String country = "Portugal";
        Address addressP = Address.createAddress(street, doorNumber, postCode, city, country);

        Person personMother = Person.createPersonWithoutParents(emailMother, nameMother, birthdateMother, birthplaceParents, addressP, ledgerID_Mother);
        Person personFather = Person.createPersonWithoutParents(emailFather, nameFather, birthdateFather, birthplaceParents, addressP, ledgerID_Father);

        //Act
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> Person.createPersonWithParents(emailJoana, name, birthdate, personMother.getPersonID(), personFather.getPersonID(), birthplace, addressP, ledgerID));

        //Assert
        assertEquals(thrown.getMessage(), "Person not created due to the fact that the birthplace parameter hasn't a valid argument");
    }

    @Test
    @DisplayName("Test For Person- getEmailException() | Email Empty")

    public void checkExceptionEmailEmpty() {

        //Arrange

        //Person
        String name = "Joana";
        LocalDate birthdate = LocalDate.of(1987, 01, 11);
        String emailJoana = "";
        Ledger ledger = Ledger.createLedger();
        LedgerID ledgerID = ledger.getLedgerID();

        //Person Mother
        String nameMother = "Ana";
        LocalDate birthdateMother = LocalDate.of(1987, 01, 11);
        String emailMother = "ana@gmail.com";
        Ledger ledgerMother = Ledger.createLedger();
        LedgerID ledgerID_Mother = ledgerMother.getLedgerID();

        //Person Father
        String nameFather = "João";
        LocalDate birthdateFather = LocalDate.of(1987, 01, 11);
        String emailFather = "joao@gmail.com";
        Ledger ledgerFather = Ledger.createLedger();
        LedgerID ledgerID_Father = ledgerFather.getLedgerID();

        //Birthplace
        String birthplace = "Braga";

        //Address
        String street = "rua da saudade";
        String doorNumber = "12";
        String postCode = "4000-121";
        String city = "Porto";
        String country = "Portugal";
        Address addressP = Address.createAddress(street, doorNumber, postCode, city, country);

        Person personMother = Person.createPersonWithoutParents(emailMother, nameMother, birthdateMother, birthplace, addressP, ledgerID_Mother);
        Person personFather = Person.createPersonWithoutParents(emailFather, nameFather, birthdateFather, birthplace, addressP, ledgerID_Father);

        //Act
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> Person.createPersonWithParents(emailJoana, name, birthdate, personMother.getPersonID(), personFather.getPersonID(), birthplace, addressP, ledgerID));

        //Assert
        assertEquals(thrown.getMessage(), "Person not created due to the fact that the email parameter hasn't a valid argument");
    }

    @Test
    @DisplayName("Test For Person- getEmailException() | Email Null")

    public void checkExceptionEmailNull() {

        //Arrange

        //Person
        String name = "Joana";
        LocalDate birthdate = LocalDate.of(1987, 01, 11);
        String emailJoana = null;
        Ledger ledger = Ledger.createLedger();
        LedgerID ledgerID = ledger.getLedgerID();

        //Person Mother
        String nameMother = "Ana";
        LocalDate birthdateMother = LocalDate.of(1987, 01, 11);
        String emailMother = "ana@gmail.com";
        Ledger ledgerMother = Ledger.createLedger();
        LedgerID ledgerID_Mother = ledgerMother.getLedgerID();

        //Person Father
        String nameFather = "João";
        LocalDate birthdateFather = LocalDate.of(1987, 01, 11);
        String emailFather = "joao@gmail.com";
        Ledger ledgerFather = Ledger.createLedger();
        LedgerID ledgerID_Father = ledgerFather.getLedgerID();

        //Birthplace
        String birthplace = "Braga";

        //Address
        String street = "rua da saudade";
        String doorNumber = "12";
        String postCode = "4000-121";
        String city = "Porto";
        String country = "Portugal";
        Address addressP = Address.createAddress(street, doorNumber, postCode, city, country);

        Person personMother = Person.createPersonWithoutParents(emailMother, nameMother, birthdateMother, birthplace, addressP, ledgerID_Mother);
        Person personFather = Person.createPersonWithoutParents(emailFather, nameFather, birthdateFather, birthplace, addressP, ledgerID_Father);

        //Act
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> Person.createPersonWithParents(emailJoana, name, birthdate, personMother.getPersonID(), personFather.getPersonID(), birthplace, addressP, ledgerID));

        //Assert
        assertEquals(thrown.getMessage(), "Person not created due to the fact that the email parameter hasn't a valid argument");
    }

    @Test
    @DisplayName("Test For Person - getAddressException()")

    public void checkException_noAddress() {

        //Arrange

        //Person
        String name = "Joana";
        LocalDate birthdate = LocalDate.of(1987, 01, 11);
        String emailJoana = "joana@gmail.com";
        Ledger ledger = Ledger.createLedger();
        LedgerID ledgerID = ledger.getLedgerID();

        //Person Mother
        String nameMother = "Ana";
        LocalDate birthdateMother = LocalDate.of(1987, 01, 11);
        String emailMother = "ana@gmail.com";
        Ledger ledgerMother = Ledger.createLedger();
        LedgerID ledgerID_Mother = ledgerMother.getLedgerID();

        //Person Father
        String nameFather = "João";
        LocalDate birthdateFather = LocalDate.of(1987, 01, 11);
        String emailFather = "joao@gmail.com";
        Ledger ledgerFather = Ledger.createLedger();
        LedgerID ledgerID_Father = ledgerFather.getLedgerID();

        //Birthplace
        String birthplace = "Braga";

        //Address Parents
        String street = "rua da saudade";
        String doorNumber = "12";
        String postCode = "4000-121";
        String city = "Porto";
        String country = "Portugal";
        Address addressParents = Address.createAddress(street, doorNumber, postCode, city, country);

        //Address Person
        Address address = null;

        Person personMother = Person.createPersonWithoutParents(emailMother, nameMother, birthdateMother, birthplace, addressParents, ledgerID_Mother);
        Person personFather = Person.createPersonWithoutParents(emailFather, nameFather, birthdateFather, birthplace, addressParents, ledgerID_Father);

        //Act
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> Person.createPersonWithParents(emailJoana, name, birthdate, personMother.getPersonID(), personFather.getPersonID(), birthplace, address, ledgerID));

        //Assert
        assertEquals(thrown.getMessage(), "Person not created due to the fact that the address parameter hasn't a valid argument");
    }

    @Test
    @DisplayName("Test For Person- getBirthdateException()")

    public void checkException_noBirthdate() {

        //Arrange

        //Person
        String name = "Joana";
        LocalDate birthdate = null;
        String emailJoana = "joana@gmail.com";
        Ledger ledger = Ledger.createLedger();
        LedgerID ledgerID = ledger.getLedgerID();

        //Person Mother
        String nameMother = "Ana";
        LocalDate birthdateMother = LocalDate.of(1987, 01, 11);
        String emailMother = "ana@gmail.com";
        Ledger ledgerMother = Ledger.createLedger();
        LedgerID ledgerID_Mother = ledgerMother.getLedgerID();

        //Person Father
        String nameFather = "João";
        LocalDate birthdateFather = LocalDate.of(1987, 01, 11);
        String emailFather = "joao@gmail.com";
        Ledger ledgerFather = Ledger.createLedger();
        LedgerID ledgerID_Father = ledgerFather.getLedgerID();

        //Birthplace
        String birthplace = "Braga";

        //Address
        String street = "rua da saudade";
        String doorNumber = "12";
        String postCode = "4000-121";
        String city = "Porto";
        String country = "Portugal";
        Address addressP = Address.createAddress(street, doorNumber, postCode, city, country);

        Person personMother = Person.createPersonWithoutParents(emailMother, nameMother, birthdateMother, birthplace, addressP, ledgerID_Mother);
        Person personFather = Person.createPersonWithoutParents(emailFather, nameFather, birthdateFather, birthplace, addressP, ledgerID_Father);

        //Act
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> Person.createPersonWithParents(emailJoana, name, birthdate, personMother.getPersonID(), personFather.getPersonID(), birthplace, addressP, ledgerID));

        //Assert
        assertEquals(thrown.getMessage(), "Person not created due to the fact that the birthdate parameter hasn't a valid argument");
    }


    //test set and get Address

    @Test
    public void checkAddress() {

        //Arrange

        //Person
        String name = "Joana";
        LocalDate birthdate = LocalDate.of(1987, 01, 11);
        String emailJoana = "joana@gmail.com";
        Ledger ledger = Ledger.createLedger();
        LedgerID ledgerID = ledger.getLedgerID();

        //Birthplace
        String birthplace = "Braga";

        //Address
        String street = "Rua do Carmo";
        String number = "4";
        String zipCode = "2562";
        String city = "Porto";
        String country = "Portugal";
        Address address = Address.createAddress(street, number, zipCode, city, country);

        //New Address
        String newStreet = "rua da saudade";
        String newDoorNumber = "12";
        String newPostCode = "4000-121";
        String newCity = "Porto";
        String newCountry = "Portugal";
        Address newAddress = Address.createAddress(newStreet, newDoorNumber, newPostCode, newCity, newCountry);

        Person person = Person.createPersonWithoutParents(emailJoana, name, birthdate, birthplace, address, ledgerID);

        //Act
        person.setAddress(newAddress);
        Address result = person.getAddress();

        //Assert
        assertEquals(newAddress, result);
    }

    //check PersonID

    @Test
    @DisplayName("Test for checkPersonID() - True")
    public void checkPersonID_True() {

        //Arrange

        //Person
        String name = "Joana";
        LocalDate birthdate = LocalDate.of(1987, 01, 11);
        String emailJoana = "joana@gmail.com";
        Ledger ledger = Ledger.createLedger();
        LedgerID ledgerID = ledger.getLedgerID();

        //Birthplace
        String birthplace = "Braga";

        //Address
        String street = "Rua do Carmo";
        String number = "4";
        String zipCode = "2562";
        String city = "Porto";
        String country = "Portugal";
        Address address = Address.createAddress(street, number, zipCode, city, country);

        Person joana = Person.createPersonWithoutParents(emailJoana, name, birthdate, birthplace, address, ledgerID);

        PersonID expectedPersonID = PersonID.createPersonID(emailJoana);

        //Act
        boolean result = joana.checkPersonID(expectedPersonID);

        //Assert
        assertEquals(true, result);

    }

    @Test
    @DisplayName("Test for checkPersonID() - False")
    public void checkPersonID_False() {

        //Arrange

        //Person
        String name = "Joana";
        LocalDate birthdate = LocalDate.of(1987, 01, 11);
        String emailJoana = "joana@gmail.com";
        Ledger ledger = Ledger.createLedger();
        LedgerID ledgerID = ledger.getLedgerID();

        //Birthplace
        String birthplace = "Braga";

        //Address
        String street = "Rua do Carmo";
        String number = "4";
        String zipCode = "2562";
        String city = "Porto";
        String country = "Portugal";
        Address address = Address.createAddress(street, number, zipCode, city, country);

        Person joana = Person.createPersonWithoutParents(emailJoana, name, birthdate, birthplace, address, ledgerID);

        String emailJulio = "julio@gmail.com";
        PersonID expectedPersonID = PersonID.createPersonID(emailJulio);

        //Act
        boolean result = joana.checkPersonID(expectedPersonID);

        //Assert
        assertEquals(false, result);

    }

    //hashCode and equals

    @Test
    public void verifyEqualsAndHashCode() {

        //Arrange

        //Person
        String name = "Joana";
        LocalDate birthdate = LocalDate.of(1987, 01, 11);
        String emailJoana = "joana@gmail.com";
        Ledger ledger = Ledger.createLedger();
        LedgerID ledgerID = ledger.getLedgerID();

        //Birthplace
        String birthplace = "Braga";

        //Address
        String street = "Rua do Carmo";
        String number = "4";
        String zipCode = "2562";
        String city = "Porto";
        String country = "Portugal";
        Address address = Address.createAddress(street, number, zipCode, city, country);

        //Act
        Person x = Person.createPersonWithoutParents(emailJoana, name, birthdate, birthplace, address, ledgerID);
        Person y = Person.createPersonWithoutParents(emailJoana, name, birthdate, birthplace, address, ledgerID);

        //Assert
        assertTrue(x.equals(y) && y.equals(x));
        assertTrue(x.hashCode() == y.hashCode());
    }

    //hashCode

    @Test
    @DisplayName("Person - hashcode - SadCase")
    public void verifyHashCode_False() {

        //Arrange

        //Person x
        String name = "Joana";
        LocalDate birthdate = LocalDate.of(1987, 01, 11);
        String emailJoana = "joana@gmail.com";
        Ledger ledger = Ledger.createLedger();
        LedgerID ledgerID = ledger.getLedgerID();
        String birthplace = "Braga";

        //Person y
        String nameAna = "Ana";
        LocalDate birthdateAna = LocalDate.of(1987, 01, 11);
        String emailAna = "ana@gmail.com";
        Ledger ledgerAna = Ledger.createLedger();
        LedgerID ledgerID_Ana = ledgerAna.getLedgerID();
        String birthplaceAna = "Cuba";

        //Address Person x
        String street = "Rua do Carmo";
        String number = "4";
        String zipCode = "2562";
        String city = "Porto";
        String country = "Portugal";
        Address address = Address.createAddress(street, number, zipCode, city, country);

        //Address Person y
        String newStreet = "rua da saudade";
        String newDoorNumber = "12";
        String newPostCode = "4000-121";
        String newCity = "Porto";
        String newCountry = "Portugal";
        Address newAddress = Address.createAddress(newStreet, newDoorNumber, newPostCode, newCity, newCountry);

        //Act

        Person x = Person.createPersonWithoutParents(emailJoana, name, birthdate, birthplace, address, ledgerID);
        Person y = Person.createPersonWithoutParents(emailAna, nameAna, birthdateAna, birthplaceAna, newAddress, ledgerID_Ana);

        //Assert
        assertFalse(x.hashCode() == y.hashCode());
        assertFalse(y.hashCode() == x.hashCode());
        assertEquals(false, x.hashCode() == y.hashCode());
    }


    //Equals
    @Test
    public void verifyEquals_samePerson() {
        //Arrange

        //Person A
        String nameAnaA = "Ana";
        LocalDate birthdateAnaA = LocalDate.of(1985, 8, 19);
        String emailAnaA = "ana@gmail.com";
        Ledger ledgerAna = Ledger.createLedger();
        LedgerID ledgerID_Ana = ledgerAna.getLedgerID();
        String birthplaceAnaA = "Cuba";

        //Person B
        String nameAnaB = "Ana";
        LocalDate birthdateAnaB = LocalDate.of(1985, 8, 19);
        String emailAnaB = "ana@gmail.com";
        String birthplaceAnaB = "Cuba";

        //Person Mother
        String nameMother = "Joana";
        LocalDate birthdateMother = LocalDate.of(1987, 01, 11);
        String emailMother = "joana@gmail.com";
        Ledger ledgerMother = Ledger.createLedger();
        LedgerID ledgerID_Mother = ledgerMother.getLedgerID();

        //Person Father
        String nameFather = "João";
        LocalDate birthdateFather = LocalDate.of(1987, 01, 11);
        String emailFather = "joao@gmail.com";
        Ledger ledgerFather = Ledger.createLedger();
        LedgerID ledgerID_Father = ledgerFather.getLedgerID();

        //Birthplace
        String birthplace = "Braga";

        //Address
        String street = "Rua do Carmo";
        String number = "4";
        String zipCode = "2562";
        String city = "Porto";
        String country = "Portugal";
        Address address = Address.createAddress(street, number, zipCode, city, country);


        Person personMother = Person.createPersonWithoutParents(emailMother, nameMother, birthdateMother, birthplace, address, ledgerID_Mother);
        Person personFather = Person.createPersonWithoutParents(emailFather, nameFather, birthdateFather, birthplace, address, ledgerID_Father);

        //Act
        Person personA = Person.createPersonWithParents(emailAnaA, nameAnaA, birthdateAnaA, personMother.getPersonID(), personFather.getPersonID(), birthplaceAnaA, address, ledgerID_Ana);
        Person personB = Person.createPersonWithParents(emailAnaB, nameAnaB, birthdateAnaB, personMother.getPersonID(), personFather.getPersonID(), birthplaceAnaB, address, ledgerID_Ana);

        //Assert
        assertEquals(personA, personB);
    }

    @Test
    public void verifyEquals_DiffetentClass() {
        //Arrange

        //Person
        String nameAna = "Ana";
        LocalDate birthdateAna = LocalDate.of(1985, 8, 19);
        String emailAna = "ana@gmail.com";
        Ledger ledgerAna = Ledger.createLedger();
        LedgerID ledgerID_Ana = ledgerAna.getLedgerID();
        String birthplaceAna = "Cuba";

        //Person Mother
        String nameMother = "Joana";
        LocalDate birthdateMother = LocalDate.of(1987, 01, 11);
        String emailMother = "joana@gmail.com";
        Ledger ledgerMother = Ledger.createLedger();
        LedgerID ledgerID_Mother = ledgerMother.getLedgerID();

        //Person Father
        String nameFather = "João";
        LocalDate birthdateFather = LocalDate.of(1987, 01, 11);
        String emailFather = "joao@gmail.com";
        Ledger ledgerFather = Ledger.createLedger();
        LedgerID ledgerID_Father = ledgerFather.getLedgerID();

        //Birthplace
        String birthplace = "Braga";

        //Address
        String street = "Rua do Carmo";
        String number = "4";
        String zipCode = "2562";
        String city = "Porto";
        String country = "Portugal";
        Address address = Address.createAddress(street, number, zipCode, city, country);


        Person personMother = Person.createPersonWithoutParents(emailMother, nameMother, birthdateMother, birthplace, address, ledgerID_Mother);
        Person personFather = Person.createPersonWithoutParents(emailFather, nameFather, birthdateFather, birthplace, address, ledgerID_Father);

        //Other Class
        String string = "Bug killer";

        //Act
        Person person = Person.createPersonWithParents(emailAna, nameAna, birthdateAna, personMother.getPersonID(), personFather.getPersonID(), birthplaceAna, address, ledgerID_Ana);

        //Assert
        assertNotEquals(person, string);
    }

    @Test
    public void verifyEquals_sameObject() {
        //Arrange

        //Person A
        String nameAnaA = "Ana";
        LocalDate birthdateAnaA = LocalDate.of(1985, 8, 19);
        String emailAnaA = "ana@gmail.com";
        Ledger ledgerAna = Ledger.createLedger();
        LedgerID ledgerID_Ana = ledgerAna.getLedgerID();
        String birthplaceAnaA = "Cuba";

        //Person Mother
        String nameMother = "Joana";
        LocalDate birthdateMother = LocalDate.of(1987, 01, 11);
        String emailMother = "joana@gmail.com";
        Ledger ledgerMother = Ledger.createLedger();
        LedgerID ledgerID_Mother = ledgerMother.getLedgerID();

        //Person Father
        String nameFather = "João";
        LocalDate birthdateFather = LocalDate.of(1987, 01, 11);
        String emailFather = "joao@gmail.com";
        Ledger ledgerFather = Ledger.createLedger();
        LedgerID ledgerID_Father = ledgerFather.getLedgerID();

        //Birthplace
        String birthplace = "Braga";

        //Address
        String street = "Rua do Carmo";
        String number = "4";
        String zipCode = "2562";
        String city = "Porto";
        String country = "Portugal";
        Address address = Address.createAddress(street, number, zipCode, city, country);


        Person personMother = Person.createPersonWithoutParents(emailMother, nameMother, birthdateMother, birthplace, address, ledgerID_Mother);
        Person personFather = Person.createPersonWithoutParents(emailFather, nameFather, birthdateFather, birthplace, address, ledgerID_Father);

        //Act
        Person personA = Person.createPersonWithParents(emailAnaA, nameAnaA, birthdateAnaA, personMother.getPersonID(), personFather.getPersonID(), birthplaceAnaA, address, ledgerID_Ana);

        //Assert
        assertEquals(personA, personA);
    }

    @Test
    public void verifyEquals_personNull() {
        //Arrange

        //Person A
        String nameAnaA = "Ana";
        LocalDate birthdateAnaA = LocalDate.of(1985, 8, 19);
        String emailAnaA = "ana@gmail.com";
        Ledger ledgerAna = Ledger.createLedger();
        LedgerID ledgerID_Ana = ledgerAna.getLedgerID();
        String birthplaceAnaA = "Cuba";

        //Person Mother
        String nameMother = "Joana";
        LocalDate birthdateMother = LocalDate.of(1987, 01, 11);
        String emailMother = "joana@gmail.com";
        Ledger ledgerMother = Ledger.createLedger();
        LedgerID ledgerID_Mother = ledgerMother.getLedgerID();

        //Person Father
        String nameFather = "João";
        LocalDate birthdateFather = LocalDate.of(1987, 01, 11);
        String emailFather = "joao@gmail.com";
        Ledger ledgerFather = Ledger.createLedger();
        LedgerID ledgerID_Father = ledgerFather.getLedgerID();

        //Birthplace
        String birthplace = "Braga";

        //Address
        String street = "Rua do Carmo";
        String number = "4";
        String zipCode = "2562";
        String city = "Porto";
        String country = "Portugal";
        Address address = Address.createAddress(street, number, zipCode, city, country);


        Person personMother = Person.createPersonWithoutParents(emailMother, nameMother, birthdateMother, birthplace, address, ledgerID_Mother);
        Person personFather = Person.createPersonWithoutParents(emailFather, nameFather, birthdateFather, birthplace, address, ledgerID_Father);

        //Act
        Person personA = Person.createPersonWithParents(emailAnaA, nameAnaA, birthdateAnaA, personMother.getPersonID(), personFather.getPersonID(), birthplaceAnaA, address, ledgerID_Ana);
        Person personB = null;

        boolean result = personA.equals(personB);

        //Assert
        assertFalse(result);
    }

    @Test
    public void verifyEquals_differentPersonID() {
        //Arrange

        //Person A
        String nameAnaA = "Ana";
        LocalDate birthdateAnaA = LocalDate.of(1985, 8, 19);
        String emailAnaA = "ana@gmail.com";
        Ledger ledgerAna = Ledger.createLedger();
        LedgerID ledgerID_Ana = ledgerAna.getLedgerID();
        String birthplaceAnaA = "Cuba";

        //Person B
        String nameAnaB = "Ana";
        LocalDate birthdateAnaB = LocalDate.of(1985, 8, 19);
        String emailAnaB = "ona@gmail.com";
        String birthplaceAnaB = "Cuba";

        //Person Mother
        String nameMother = "Joana";
        LocalDate birthdateMother = LocalDate.of(1987, 01, 11);
        String emailMother = "joana@gmail.com";
        Ledger ledgerMother = Ledger.createLedger();
        LedgerID ledgerID_Mother = ledgerMother.getLedgerID();

        //Person Father
        String nameFather = "João";
        LocalDate birthdateFather = LocalDate.of(1987, 01, 11);
        String emailFather = "joao@gmail.com";
        Ledger ledgerFather = Ledger.createLedger();
        LedgerID ledgerID_Father = ledgerFather.getLedgerID();

        //Birthplace
        String birthplace = "Braga";

        //Address
        String street = "Rua do Carmo";
        String number = "4";
        String zipCode = "2562";
        String city = "Porto";
        String country = "Portugal";
        Address address = Address.createAddress(street, number, zipCode, city, country);


        Person personMother = Person.createPersonWithoutParents(emailMother, nameMother, birthdateMother, birthplace, address, ledgerID_Mother);
        Person personFather = Person.createPersonWithoutParents(emailFather, nameFather, birthdateFather, birthplace, address, ledgerID_Father);

        //Act
        Person personA = Person.createPersonWithParents(emailAnaA, nameAnaA, birthdateAnaA, personMother.getPersonID(), personFather.getPersonID(), birthplaceAnaA, address, ledgerID_Ana);
        Person personB = Person.createPersonWithParents(emailAnaB, nameAnaB, birthdateAnaB, personMother.getPersonID(), personFather.getPersonID(), birthplaceAnaB, address, ledgerID_Ana);

        //Assert
        assertNotEquals(personA, personB);
    }


    @Test
    public void verifyEquals_samePerson_withUpper_and_LowerCases() {
        //Arrange

        //Person A
        String nameAnaA = "ANA";
        LocalDate birthdateAnaA = LocalDate.of(1985, 8, 19);
        String emailAnaA = "ana@gmail.com";
        Ledger ledgerAna = Ledger.createLedger();
        LedgerID ledgerID_Ana = ledgerAna.getLedgerID();
        String birthplaceAnaA = "Cuba";

        //Person B
        String nameAnaB = "Ana";
        LocalDate birthdateAnaB = LocalDate.of(1985, 8, 19);
        String emailAnaB = "ana@gmail.com";
        String birthplaceAnaB = "Cuba";

        //Person Mother
        String nameMother = "Joana";
        LocalDate birthdateMother = LocalDate.of(1987, 01, 11);
        String emailMother = "joana@gmail.com";
        Ledger ledgerMother = Ledger.createLedger();
        LedgerID ledgerID_Mother = ledgerMother.getLedgerID();

        //Person Father
        String nameFather = "João";
        LocalDate birthdateFather = LocalDate.of(1987, 01, 11);
        String emailFather = "joao@gmail.com";
        Ledger ledgerFather = Ledger.createLedger();
        LedgerID ledgerID_Father = ledgerFather.getLedgerID();

        //Birthplace
        String birthplace = "Braga";

        //Address
        String street = "Rua do Carmo";
        String number = "4";
        String zipCode = "2562";
        String city = "Porto";
        String country = "Portugal";
        Address address = Address.createAddress(street, number, zipCode, city, country);

        Person personMother = Person.createPersonWithoutParents(emailMother, nameMother, birthdateMother, birthplace, address, ledgerID_Mother);
        Person personFather = Person.createPersonWithoutParents(emailFather, nameFather, birthdateFather, birthplace, address, ledgerID_Father);

        //Act
        Person personA = Person.createPersonWithParents(emailAnaA, nameAnaA, birthdateAnaA, personMother.getPersonID(), personFather.getPersonID(), birthplaceAnaA, address, ledgerID_Ana);
        Person personB = Person.createPersonWithParents(emailAnaB, nameAnaB, birthdateAnaB, personMother.getPersonID(), personFather.getPersonID(), birthplaceAnaB, address, ledgerID_Ana);

        //Assert
        assertEquals(personA, personB);
    }

    //Verify add siblings

    @Test
    @DisplayName("add siblings to a arrayList")
    public void addSiblings() {

        //Arrange

        //Person A
        String nameA = "ana";
        LocalDate birthdateA = LocalDate.of(1988, 9, 21);
        String emailAna = "ana@gmail.com";
        Ledger ledgerAna = Ledger.createLedger();
        LedgerID ledgerID_Ana = ledgerAna.getLedgerID();

        //Person B
        String nameB = "filipa";
        LocalDate birthdateB = LocalDate.of(1988, 9, 21);
        String emailFilipa = "filipa@gmail.com";
        Ledger ledgerFilipa = Ledger.createLedger();
        LedgerID ledgerID_Filipa = ledgerFilipa.getLedgerID();

        //Birthplace
        String birthplace = "Braga";

        //Address
        String street = "Rua do Carmo";
        String number = "4";
        String zipCode = "2562";
        String city = "Porto";
        String country = "Portugal";
        Address address = Address.createAddress(street, number, zipCode, city, country);

        //Act
        Person personA = Person.createPersonWithoutParents(emailAna, nameA, birthdateA, birthplace, address, ledgerID_Ana);
        Person personB = Person.createPersonWithoutParents(emailFilipa, nameB, birthdateB, birthplace, address, ledgerID_Filipa);
        PersonID personID_B = personB.getPersonID();

        boolean result = personA.addSibling(personID_B);

        //Expected
        ArrayList<PersonID> expectedSiblings = new ArrayList<>();
        expectedSiblings.add(personID_B);

        //Assert

        assertEquals(expectedSiblings, personA.getListOfSiblings());
        assertTrue(result);
    }

    @Test
    @DisplayName("add siblings to a arrayList, do not repeat siblings")
    public void addSiblings_siblingAlreadyExists() {

        //Arrange

        //Person A
        String nameA = "ana";
        LocalDate birthdateA = LocalDate.of(1988, 9, 21);
        String emailAna = "ana@gmail.com";
        Ledger ledgerAna = Ledger.createLedger();
        LedgerID ledgerID_Ana = ledgerAna.getLedgerID();

        //Person B
        String nameB = "filipa";
        LocalDate birthdateB = LocalDate.of(1988, 9, 21);
        String emailFilipa = "filipa@gmail.com";
        Ledger ledgerFilipa = Ledger.createLedger();
        LedgerID ledgerID_Filipa = ledgerFilipa.getLedgerID();

        //Birthplace
        String birthplace = "Braga";

        //Address
        String street = "Rua do Carmo";
        String number = "4";
        String zipCode = "2562";
        String city = "Porto";
        String country = "Portugal";
        Address address = Address.createAddress(street, number, zipCode, city, country);

        //Act
        Person personA = Person.createPersonWithoutParents(emailAna, nameA, birthdateA, birthplace, address, ledgerID_Ana);
        Person personB = Person.createPersonWithoutParents(emailFilipa, nameB, birthdateB, birthplace, address, ledgerID_Filipa);

        PersonID personID_B = personB.getPersonID();

        boolean firstResult = personA.addSibling(personID_B);
        boolean secondResult = personA.addSibling(personID_B);

        //Expected
        ArrayList<PersonID> expectedSiblings = new ArrayList<>();
        expectedSiblings.add(personID_B);

        //Assert
        assertEquals(expectedSiblings, personA.getListOfSiblings());
        assertTrue(firstResult);
        assertFalse(secondResult);

    }

    @Test
    @DisplayName("add siblings to a arrayList, add to differentSiblings")
    public void addSiblings_siblingAdd2Siblings() {

        //Arrange

        //Person A
        String nameA = "ana";
        LocalDate birthdateA = LocalDate.of(1988, 9, 21);
        String emailAna = "ana@gmail.com";
        Ledger ledgerAna = Ledger.createLedger();
        LedgerID ledgerID_Ana = ledgerAna.getLedgerID();

        //Person B
        String nameB = "filipa";
        LocalDate birthdateB = LocalDate.of(1988, 9, 21);
        String emailFilipa = "filipa@gmail.com";
        Ledger ledgerFilipa = Ledger.createLedger();
        LedgerID ledgerID_Filipa = ledgerFilipa.getLedgerID();

        //Person C
        String nameC = "Pedro";
        LocalDate birthdateC = LocalDate.of(1988, 9, 21);
        String emailPedro = "pedro@gmail.com";
        Ledger ledgerPedro = Ledger.createLedger();
        LedgerID ledgerID_Pedro = ledgerPedro.getLedgerID();

        //Birthplace
        String birthplace = "Braga";

        //Address
        String street = "Rua do Carmo";
        String number = "4";
        String zipCode = "2562";
        String city = "Porto";
        String country = "Portugal";
        Address address = Address.createAddress(street, number, zipCode, city, country);

        //Act
        Person personA = Person.createPersonWithoutParents(emailAna, nameA, birthdateA, birthplace, address, ledgerID_Ana);
        Person personB = Person.createPersonWithoutParents(emailFilipa, nameB, birthdateB, birthplace, address, ledgerID_Filipa);
        Person personC = Person.createPersonWithoutParents(emailPedro, nameC, birthdateC, birthplace, address, ledgerID_Pedro);

        PersonID personID_B = personB.getPersonID();
        PersonID personID_C = personC.getPersonID();

        boolean firstResult = personA.addSibling(personID_B);
        boolean secondResult = personA.addSibling(personID_C);

        //Expected
        ArrayList<PersonID> expectedSiblings = new ArrayList<>();
        expectedSiblings.add(personID_B);
        expectedSiblings.add(personID_C);

        //Assert
        assertEquals(expectedSiblings, personA.getListOfSiblings());
        assertTrue(firstResult);
        assertTrue(secondResult);
    }

    //verify siblings or half siblings

    @Test
    @DisplayName("verify if 2 person are siblings or half siblings")
    public void verifySiblingsOrHalfSiblings_false_samePerson() {

        //Arrange

        //Person
        String nameA = "ana";
        LocalDate birthdateA = LocalDate.of(1988, 9, 21);
        String emailAna = "ana@gmail.com";
        Ledger ledgerAna = Ledger.createLedger();
        LedgerID ledgerID_Ana = ledgerAna.getLedgerID();

        //Birhtplace
        String birthplace = "Braga";

        //Address
        String street = "Rua do Carmo";
        String number = "4";
        String zipCode = "2562";
        String city = "Porto";
        String country = "Portugal";
        Address address = Address.createAddress(street, number, zipCode, city, country);

        //Act
        Person personA = Person.createPersonWithoutParents(emailAna, nameA, birthdateA, birthplace, address, ledgerID_Ana);

        PersonID personID_A = personA.getPersonID();

        personA.addSibling(personID_A);

        boolean result = personA.verifySiblingsOrHalfSiblings(personA);

        //Assert
        assertFalse(result);

    }


    @Test
    @DisplayName("verify if 2 person are siblings or half siblings_same mother and father")
    public void verifySiblingsOrHalfSiblings() {

        //Arrange

        //Person A
        String nameA = "ana";
        LocalDate birthdateA = LocalDate.of(1988, 9, 21);
        String emailAna = "ana@gmail.com";
        Ledger ledgerAna = Ledger.createLedger();
        LedgerID ledgerID_Ana = ledgerAna.getLedgerID();

        //Person B
        String nameB = "filipa";
        LocalDate birthdateB = LocalDate.of(1988, 9, 21);
        String emailFilipa = "filipa@gmail.com";
        Ledger ledgerFilipa = Ledger.createLedger();
        LedgerID ledgerID_Filipa = ledgerFilipa.getLedgerID();

        //Person C
        String nameC = "Pedro";
        LocalDate birthdateC = LocalDate.of(1988, 9, 21);
        String emailPedro = "pedro@gmail.com";
        Ledger ledgerPedro = Ledger.createLedger();
        LedgerID ledgerID_Pedro = ledgerPedro.getLedgerID();

        //Person Mother
        String nameMother = "Joana";
        LocalDate birthdateMother = LocalDate.of(1987, 01, 11);
        String emailMother = "joana@gmail.com";
        Ledger ledgerMother = Ledger.createLedger();
        LedgerID ledgerID_Mother = ledgerMother.getLedgerID();

        //Person Father
        String nameFather = "João";
        LocalDate birthdateFather = LocalDate.of(1987, 01, 11);
        String emailFather = "joao@gmail.com";
        Ledger ledgerFather = Ledger.createLedger();
        LedgerID ledgerID_Father = ledgerFather.getLedgerID();

        //Birthplace
        String birthplace = "Braga";

        //Address
        String street = "Rua do Carmo";
        String number = "4";
        String zipCode = "2562";
        String city = "Porto";
        String country = "Portugal";
        Address address = Address.createAddress(street, number, zipCode, city, country);


        //Act
        Person mother = Person.createPersonWithoutParents(emailMother, nameMother, birthdateMother, birthplace, address, ledgerID_Mother);
        Person father = Person.createPersonWithoutParents(emailFather, nameFather, birthdateFather, birthplace, address, ledgerID_Father);
        Person personA = Person.createPersonWithParents(emailAna, nameA, birthdateA, mother.getPersonID(), father.getPersonID(), birthplace, address, ledgerID_Ana);
        Person personB = Person.createPersonWithParents(emailFilipa, nameB, birthdateB, mother.getPersonID(), father.getPersonID(), birthplace, address, ledgerID_Filipa);
        Person personC = Person.createPersonWithParents(emailPedro, nameC, birthdateC, mother.getPersonID(), father.getPersonID(), birthplace, address, ledgerID_Pedro);

        PersonID personID_C = personC.getPersonID();

        personA.addSibling(personID_C);
        personB.addSibling(personID_C);

        boolean result = personA.verifySiblingsOrHalfSiblings(personB);

        //Assert

        assertTrue(result);
    }

    @Test
    @DisplayName("verify if 2 person are siblings or half siblings only same mother")
    public void verifySiblingsOrHalfSiblings_sameMother() {

        //Arrange

        //Person A
        String nameA = "ana";
        LocalDate birthdateA = LocalDate.of(1988, 9, 21);
        String emailAna = "ana@gmail.com";
        Ledger ledgerAna = Ledger.createLedger();
        LedgerID ledgerID_Ana = ledgerAna.getLedgerID();

        //Person B
        String nameB = "filipa";
        LocalDate birthdateB = LocalDate.of(1988, 9, 21);
        String emailFilipa = "filipa@gmail.com";
        Ledger ledgerFilipa = Ledger.createLedger();
        LedgerID ledgerID_Filipa = ledgerFilipa.getLedgerID();

        //Person Mother
        String nameMother = "Joana";
        LocalDate birthdateMother = LocalDate.of(1987, 01, 11);
        String emailMother = "joana@gmail.com";
        Ledger ledgerMother = Ledger.createLedger();
        LedgerID ledgerID_Mother = ledgerMother.getLedgerID();

        //Person A Father
        String nameFatherA = "João";
        LocalDate birthdateFatherA = LocalDate.of(1987, 01, 11);
        String emailFatherA = "joao@gmail.com";
        Ledger ledgerFatherA = Ledger.createLedger();
        LedgerID ledgerID_FatherA = ledgerFatherA.getLedgerID();

        //Person B Father
        String nameFatherB = "Francisco";
        LocalDate birthdateFatherB = LocalDate.of(1988, 9, 21);
        String emailFatherB = "francisco@gmail.com";
        Ledger ledgerFatherB = Ledger.createLedger();
        LedgerID ledgerID_FatherB = ledgerFatherB.getLedgerID();

        //Birthplace
        String birthplace = "Braga";

        //Address
        String street = "Rua do Carmo";
        String number = "4";
        String zipCode = "2562";
        String city = "Porto";
        String country = "Portugal";
        Address address = Address.createAddress(street, number, zipCode, city, country);


        //Act
        Person mother = Person.createPersonWithoutParents(emailMother, nameMother, birthdateMother, birthplace, address, ledgerID_Mother);
        Person fatherA = Person.createPersonWithoutParents(emailFatherA, nameFatherA, birthdateFatherA, birthplace, address, ledgerID_FatherA);
        Person fatherB = Person.createPersonWithoutParents(emailFatherB, nameFatherB, birthdateFatherB, birthplace, address, ledgerID_FatherB);

        Person personA = Person.createPersonWithParents(emailAna, nameA, birthdateA, mother.getPersonID(), fatherA.getPersonID(), birthplace, address, ledgerID_Ana);
        Person personB = Person.createPersonWithParents(emailFilipa, nameB, birthdateB, mother.getPersonID(), fatherB.getPersonID(), birthplace, address, ledgerID_Filipa);

        boolean result = personA.verifySiblingsOrHalfSiblings(personB);

        //Assert

        assertTrue(result);
    }


    @Test
    @DisplayName("verify if 2 person are siblings or half siblings only same father")
    public void verifySiblingsOrHalfSiblings_sameFather() {

        //Arrange

        //Person A
        String nameA = "ana";
        LocalDate birthdateA = LocalDate.of(1988, 9, 21);
        String emailAna = "ana@gmail.com";
        Ledger ledgerAna = Ledger.createLedger();
        LedgerID ledgerID_Ana = ledgerAna.getLedgerID();

        //Person B
        String nameB = "filipa";
        LocalDate birthdateB = LocalDate.of(1988, 9, 21);
        String emailFilipa = "filipa@gmail.com";
        Ledger ledgerFilipa = Ledger.createLedger();
        LedgerID ledgerID_Filipa = ledgerFilipa.getLedgerID();

        //Person A Mother
        String nameMotherA = "Joana";
        LocalDate birthdateMotherA = LocalDate.of(1987, 01, 11);
        String emailMotherA = "joana@gmail.com";
        Ledger ledgerMotherA = Ledger.createLedger();
        LedgerID ledgerID_MotherA = ledgerMotherA.getLedgerID();

        //Person B Mother
        String nameMotherB = "Liana";
        LocalDate birthdateMotherB = LocalDate.of(1987, 01, 11);
        String emailMotherB = "liana@gmail.com";
        Ledger ledgerMotherB = Ledger.createLedger();
        LedgerID ledgerID_MotherB = ledgerMotherB.getLedgerID();

        //Person Father
        String nameFather = "Francisco";
        LocalDate birthdateFather = LocalDate.of(1988, 9, 21);
        String emailFather = "francisco@gmail.com";
        Ledger ledgerFather = Ledger.createLedger();
        LedgerID ledgerID_Father = ledgerFather.getLedgerID();

        //Birthplace
        String birthplace = "Braga";

        //Address
        String street = "Rua do Carmo";
        String number = "4";
        String zipCode = "2562";
        String city = "Porto";
        String country = "Portugal";
        Address address = Address.createAddress(street, number, zipCode, city, country);


        //Act
        Person motherA = Person.createPersonWithoutParents(emailMotherA, nameMotherA, birthdateMotherA, birthplace, address, ledgerID_MotherA);
        Person motherB = Person.createPersonWithoutParents(emailMotherB, nameMotherB, birthdateMotherB, birthplace, address, ledgerID_MotherB);
        Person father = Person.createPersonWithoutParents(emailFather, nameFather, birthdateFather, birthplace, address, ledgerID_Father);

        Person personA = Person.createPersonWithParents(emailAna, nameA, birthdateA, motherA.getPersonID(), father.getPersonID(), birthplace, address, ledgerID_Ana);
        Person personB = Person.createPersonWithParents(emailFilipa, nameB, birthdateB, motherB.getPersonID(), father.getPersonID(), birthplace, address, ledgerID_Filipa);

        boolean result = personA.verifySiblingsOrHalfSiblings(personB);

        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("verify if 2 person are siblings or half siblings be in the ArrayList")
    public void verifySiblingsOrHalfSiblings_Positive_siblings_ArrayList() {

        //Arrange

        //Person A
        String nameA = "ana";
        LocalDate birthdateA = LocalDate.of(1988, 9, 21);
        String emailAna = "ana@gmail.com";
        Ledger ledgerAna = Ledger.createLedger();
        LedgerID ledgerID_Ana = ledgerAna.getLedgerID();

        //Person B
        String nameB = "filipa";
        LocalDate birthdateB = LocalDate.of(1988, 9, 21);
        String emailFilipa = "filipa@gmail.com";
        Ledger ledgerFilipa = Ledger.createLedger();
        LedgerID ledgerID_Filipa = ledgerFilipa.getLedgerID();

        //Person C
        String nameC = "Pedro";
        LocalDate birthdateC = LocalDate.of(1988, 9, 21);
        String emailPedro = "pedro@gmail.com";
        Ledger ledgerPedro = Ledger.createLedger();
        LedgerID ledgerID_Pedro = ledgerPedro.getLedgerID();

        //Birhtplace
        String birthplace = "Braga";

        //Address
        String street = "Rua do Carmo";
        String number = "4";
        String zipCode = "2562";
        String city = "Porto";
        String country = "Portugal";
        Address address = Address.createAddress(street, number, zipCode, city, country);

        //Act
        Person personA = Person.createPersonWithoutParents(emailAna, nameA, birthdateA, birthplace, address, ledgerID_Ana);
        Person personB = Person.createPersonWithoutParents(emailFilipa, nameB, birthdateB, birthplace, address, ledgerID_Filipa);
        Person personC = Person.createPersonWithoutParents(emailPedro, nameC, birthdateC, birthplace, address, ledgerID_Pedro);

        PersonID personID_B = personB.getPersonID();
        PersonID personID_C = personC.getPersonID();

        personA.addSibling(personID_B);
        personA.addSibling(personID_C);

        boolean result = personA.verifySiblingsOrHalfSiblings(personB);

        //Assert

        assertTrue(result);
    }

    @Test
    @DisplayName("verify if 2 person are siblings or half siblings -no siblings")
    public void verifySiblingsOrHalfSiblings_False() {

        //Arrange

        //Person A
        String nameA = "ana";
        LocalDate birthdateA = LocalDate.of(1988, 9, 21);
        String emailAna = "ana@gmail.com";
        Ledger ledgerAna = Ledger.createLedger();
        LedgerID ledgerID_Ana = ledgerAna.getLedgerID();

        //Person B
        String nameB = "filipa";
        LocalDate birthdateB = LocalDate.of(1988, 9, 21);
        String emailFilipa = "filipa@gmail.com";
        Ledger ledgerFilipa = Ledger.createLedger();
        LedgerID ledgerID_Filipa = ledgerFilipa.getLedgerID();

        //Person C
        String nameC = "Pedro";
        LocalDate birthdateC = LocalDate.of(1988, 9, 21);
        String emailPedro = "pedro@gmail.com";
        Ledger ledgerPedro = Ledger.createLedger();
        LedgerID ledgerID_Pedro = ledgerPedro.getLedgerID();

        //Birthplace
        String birthplace = "Braga";

        //Address
        String street = "Rua do Carmo";
        String number = "4";
        String zipCode = "2562";
        String city = "Porto";
        String country = "Portugal";
        Address address = Address.createAddress(street, number, zipCode, city, country);

        //Act
        Person personA = Person.createPersonWithoutParents(emailAna, nameA, birthdateA, birthplace, address, ledgerID_Ana);
        Person personB = Person.createPersonWithoutParents(emailFilipa, nameB, birthdateB, birthplace, address, ledgerID_Filipa);
        Person personC = Person.createPersonWithoutParents(emailPedro, nameC, birthdateC, birthplace, address, ledgerID_Pedro);

        PersonID personID_C = personC.getPersonID();

        personA.addSibling(personID_C);

        boolean result = personA.verifySiblingsOrHalfSiblings(personB);

        //Assert
        assertFalse(result);
    }


    //add categories

    @Test
    @DisplayName("Add Categories - True | Person")
    public void addCategories_True() {

        //Arrange

        //Person
        String name = "ana";
        LocalDate birthdate = LocalDate.of(1988, 9, 21);
        String emailAna = "ana@gmail.com";
        Ledger ledgerAna = Ledger.createLedger();
        LedgerID ledgerID_Ana = ledgerAna.getLedgerID();

        //Person Mother
        String nameMother = "Joana";
        LocalDate birthdateMother = LocalDate.of(1987, 01, 11);
        String emailMother = "joana@gmail.com";
        Ledger ledgerMother = Ledger.createLedger();
        LedgerID ledgerID_Mother = ledgerMother.getLedgerID();

        //Person Father
        String nameFather = "Francisco";
        LocalDate birthdateFather = LocalDate.of(1988, 9, 21);
        String emailFather = "francisco@gmail.com";
        Ledger ledgerFather = Ledger.createLedger();
        LedgerID ledgerID_Father = ledgerFather.getLedgerID();

        //Birthplace
        String birthplace = "Braga";

        //Address
        String street = "Rua do Carmo";
        String number = "4";
        String zipCode = "2562";
        String city = "Porto";
        String country = "Portugal";
        Address address = Address.createAddress(street, number, zipCode, city, country);

        Person mother = Person.createPersonWithoutParents(emailMother, nameMother, birthdateMother, birthplace, address, ledgerID_Mother);
        Person father = Person.createPersonWithoutParents(emailFather, nameFather, birthdateFather, birthplace, address, ledgerID_Father);
        Person person = Person.createPersonWithParents(emailAna, name, birthdate, mother.getPersonID(), father.getPersonID(), birthplace, address, ledgerID_Ana);

        PersonID personID = person.getPersonID();

        //Category
        String denominationFood = "Food";

        //Act

        //Expected
        CategoryID categoryID = CategoryID.createCategoryID(denominationFood, personID);
        List<CategoryID> newCategories = new ArrayList<>();
        newCategories.add(categoryID);

        boolean result = person.addCategory(categoryID);

        //Assert

        assertEquals(true, result);

    }


    @Test
    @DisplayName("Add Categories | Person")
    public void addCategories() {

        //Arrange

        //Person
        String name = "ana";
        LocalDate birthdate = LocalDate.of(1988, 9, 21);
        String emailAna = "ana@gmail.com";
        Ledger ledgerAna = Ledger.createLedger();
        LedgerID ledgerID_Ana = ledgerAna.getLedgerID();

        //Person Mother
        String nameMother = "Joana";
        LocalDate birthdateMother = LocalDate.of(1987, 01, 11);
        String emailMother = "joana@gmail.com";
        Ledger ledgerMother = Ledger.createLedger();
        LedgerID ledgerID_Mother = ledgerMother.getLedgerID();

        //Person Father
        String nameFather = "Francisco";
        LocalDate birthdateFather = LocalDate.of(1988, 9, 21);
        String emailFather = "francisco@gmail.com";
        Ledger ledgerFather = Ledger.createLedger();
        LedgerID ledgerID_Father = ledgerFather.getLedgerID();

        //Birthplace
        String birthplace = "Braga";

        //Address
        String street = "Rua do Carmo";
        String number = "4";
        String zipCode = "2562";
        String city = "Porto";
        String country = "Portugal";
        Address address = Address.createAddress(street, number, zipCode, city, country);

        Person mother = Person.createPersonWithoutParents(emailMother, nameMother, birthdateMother, birthplace, address, ledgerID_Mother);
        Person father = Person.createPersonWithoutParents(emailFather, nameFather, birthdateFather, birthplace, address, ledgerID_Father);
        Person person = Person.createPersonWithParents(emailAna, name, birthdate, mother.getPersonID(), father.getPersonID(), birthplace, address, ledgerID_Ana);

        PersonID personID = person.getPersonID();

        //Category
        String denominationFood = "Food";

        //Act

        //Expected
        CategoryID categoryID = CategoryID.createCategoryID(denominationFood, personID);
        List<CategoryID> expectedCategories = new ArrayList<>();
        expectedCategories.add(categoryID);

        boolean result = person.addCategory(categoryID);

        //Assert
        assertEquals(expectedCategories, person.getListOfCategories());
        assertTrue(result);
    }

    @Test
    @DisplayName("Add Category Twice | Person")
    public void addSameCategoryTwice() {

        //Arrange

        //Person
        String name = "ana";
        LocalDate birthdate = LocalDate.of(1988, 9, 21);
        String emailAna = "ana@gmail.com";
        Ledger ledgerAna = Ledger.createLedger();
        LedgerID ledgerID_Ana = ledgerAna.getLedgerID();

        //Person Mother
        String nameMother = "Joana";
        LocalDate birthdateMother = LocalDate.of(1987, 01, 11);
        String emailMother = "joana@gmail.com";
        Ledger ledgerMother = Ledger.createLedger();
        LedgerID ledgerID_Mother = ledgerMother.getLedgerID();

        //Person Father
        String nameFather = "Francisco";
        LocalDate birthdateFather = LocalDate.of(1988, 9, 21);
        String emailFather = "francisco@gmail.com";
        Ledger ledgerFather = Ledger.createLedger();
        LedgerID ledgerID_Father = ledgerFather.getLedgerID();

        //Birthplace
        String birthplace = "Braga";

        //Address
        String street = "Rua do Carmo";
        String number = "4";
        String zipCode = "2562";
        String city = "Porto";
        String country = "Portugal";
        Address address = Address.createAddress(street, number, zipCode, city, country);

        Person mother = Person.createPersonWithoutParents(emailMother, nameMother, birthdateMother, birthplace, address, ledgerID_Mother);
        Person father = Person.createPersonWithoutParents(emailFather, nameFather, birthdateFather, birthplace, address, ledgerID_Father);
        Person person = Person.createPersonWithParents(emailAna, name, birthdate, mother.getPersonID(), father.getPersonID(), birthplace, address, ledgerID_Ana);

        PersonID personID = person.getPersonID();

        //Category
        String denominationFood = "Food";

        //Act

        //Expected
        CategoryID categoryID = CategoryID.createCategoryID(denominationFood, personID);
        List<CategoryID> expectedCategories = new ArrayList<>();
        expectedCategories.add(categoryID);

        boolean firstResult = person.addCategory(categoryID);
        boolean secondResult = person.addCategory(categoryID);


        //Assert
        assertEquals(expectedCategories, person.getListOfCategories());
        assertTrue(firstResult);
        assertFalse(secondResult);
    }

    @Test
    @DisplayName("Add Schedule | Person")
    public void addSchedule() {

        //Arrange

        //Person
        String name = "ana";
        LocalDate birthdate = LocalDate.of(1988, 9, 21);
        String emailAna = "ana@gmail.com";
        Ledger ledgerAna = Ledger.createLedger();
        LedgerID ledgerID_Ana = ledgerAna.getLedgerID();

        //Person Mother
        String nameMother = "Joana";
        LocalDate birthdateMother = LocalDate.of(1987, 01, 11);
        String emailMother = "joana@gmail.com";
        Ledger ledgerMother = Ledger.createLedger();
        LedgerID ledgerID_Mother = ledgerMother.getLedgerID();

        //Person Father
        String nameFather = "Francisco";
        LocalDate birthdateFather = LocalDate.of(1988, 9, 21);
        String emailFather = "francisco@gmail.com";
        Ledger ledgerFather = Ledger.createLedger();
        LedgerID ledgerID_Father = ledgerFather.getLedgerID();

        //Birthplace
        String birthplace = "Braga";

        //Address
        String street = "Rua do Carmo";
        String number = "4";
        String zipCode = "2562";
        String city = "Porto";
        String country = "Portugal";
        Address address = Address.createAddress(street, number, zipCode, city, country);


        Person mother = Person.createPersonWithoutParents(emailMother, nameMother, birthdateMother, birthplace, address, ledgerID_Mother);
        Person father = Person.createPersonWithoutParents(emailFather, nameFather, birthdateFather, birthplace, address, ledgerID_Father);
        Person person = Person.createPersonWithParents(emailAna, name, birthdate, mother.getPersonID(), father.getPersonID(), birthplace, address, ledgerID_Ana);

        //Schedule
        String description = "Mesada";
        LocalDate triggerDate = LocalDate.of(1985, 05, 14);
        String periodicity = "Working Days";
        String transactionType = "debit";

        //Act

        //Expected
        ScheduleID scheduleID = ScheduleID.createScheduleID(description, triggerDate, periodicity, transactionType);
        List<ScheduleID> expectedSchedules = new ArrayList<>();
        expectedSchedules.add(scheduleID);

        boolean result = person.addSchedule(scheduleID);

        //Assert
        assertEquals(expectedSchedules, person.getListOfSchedulings());
        assertTrue(result);
    }

    @Test
    @DisplayName("Add Same Schedule Twice | Person")
    public void addSameScheduleTwice() {

        //Arrange

        //Person
        String name = "ana";
        LocalDate birthdate = LocalDate.of(1988, 9, 21);
        String emailAna = "ana@gmail.com";
        Ledger ledgerAna = Ledger.createLedger();
        LedgerID ledgerID_Ana = ledgerAna.getLedgerID();

        //Person Mother
        String nameMother = "Joana";
        LocalDate birthdateMother = LocalDate.of(1987, 01, 11);
        String emailMother = "joana@gmail.com";
        Ledger ledgerMother = Ledger.createLedger();
        LedgerID ledgerID_Mother = ledgerMother.getLedgerID();

        //Person Father
        String nameFather = "Francisco";
        LocalDate birthdateFather = LocalDate.of(1988, 9, 21);
        String emailFather = "francisco@gmail.com";
        Ledger ledgerFather = Ledger.createLedger();
        LedgerID ledgerID_Father = ledgerFather.getLedgerID();

        //Birthplace
        String birthplace = "Braga";

        //Address
        String street = "Rua do Carmo";
        String number = "4";
        String zipCode = "2562";
        String city = "Porto";
        String country = "Portugal";
        Address address = Address.createAddress(street, number, zipCode, city, country);


        Person mother = Person.createPersonWithoutParents(emailMother, nameMother, birthdateMother, birthplace, address, ledgerID_Mother);
        Person father = Person.createPersonWithoutParents(emailFather, nameFather, birthdateFather, birthplace, address, ledgerID_Father);
        Person person = Person.createPersonWithParents(emailAna, name, birthdate, mother.getPersonID(), father.getPersonID(), birthplace, address, ledgerID_Ana);

        //Schedule
        String description = "Mesada";
        LocalDate triggerDate = LocalDate.of(1985, 05, 14);
        String periodicity = "Working Days";
        String transactionType = "debit";

        //Act

        //Expected
        ScheduleID scheduleID = ScheduleID.createScheduleID(description, triggerDate, periodicity, transactionType);
        List<ScheduleID> expectedSchedules = new ArrayList<>();
        expectedSchedules.add(scheduleID);

        boolean firstResult = person.addSchedule(scheduleID);
        boolean secondResult = person.addSchedule(scheduleID);


        //Assert
        assertEquals(expectedSchedules, person.getListOfSchedulings());
        assertTrue(firstResult);
        assertFalse(secondResult);
    }


    //add accounts

    @Test
    @DisplayName("Add Account | Person")
    public void addAccount() {

        //Arrange

        //Person
        String name = "ana";
        LocalDate birthdate = LocalDate.of(1988, 9, 21);
        String emailAna = "ana@gmail.com";
        Ledger ledgerAna = Ledger.createLedger();
        LedgerID ledgerID_Ana = ledgerAna.getLedgerID();

        //Person Mother
        String nameMother = "Joana";
        LocalDate birthdateMother = LocalDate.of(1987, 01, 11);
        String emailMother = "joana@gmail.com";
        Ledger ledgerMother = Ledger.createLedger();
        LedgerID ledgerID_Mother = ledgerMother.getLedgerID();

        //Person Father
        String nameFather = "Francisco";
        LocalDate birthdateFather = LocalDate.of(1988, 9, 21);
        String emailFather = "francisco@gmail.com";
        Ledger ledgerFather = Ledger.createLedger();
        LedgerID ledgerID_Father = ledgerFather.getLedgerID();

        //Birthplace
        String birthplace = "Braga";

        //Address
        String street = "Rua do Carmo";
        String number = "4";
        String zipCode = "2562";
        String city = "Porto";
        String country = "Portugal";
        Address address = Address.createAddress(street, number, zipCode, city, country);

        Person mother = Person.createPersonWithoutParents(emailMother, nameMother, birthdateMother, birthplace, address, ledgerID_Mother);
        Person father = Person.createPersonWithoutParents(emailFather, nameFather, birthdateFather, birthplace, address, ledgerID_Father);
        Person person = Person.createPersonWithParents(emailAna, name, birthdate, mother.getPersonID(), father.getPersonID(), birthplace, address, ledgerID_Ana);

        PersonID personID = person.getPersonID();

        //Account
        String denominationDomestic = "Domestic expenses";

        //Act

        //Expected
        AccountID accountID = AccountID.createAccountID(denominationDomestic, personID);

        List<AccountID> expectedAccounts = new ArrayList<>();
        expectedAccounts.add(accountID);

        boolean result = person.addAccount(accountID);

        //Assert
        assertEquals(person.getListOfAccounts(), expectedAccounts);
        assertTrue(result);

    }

    @Test
    @DisplayName("Add Same Account Twice | Person")
    public void addSameAccountTwice() {

        //Arrange

        //Person
        String name = "ana";
        LocalDate birthdate = LocalDate.of(1988, 9, 21);
        String emailAna = "ana@gmail.com";
        Ledger ledgerAna = Ledger.createLedger();
        LedgerID ledgerID_Ana = ledgerAna.getLedgerID();

        //Person Mother
        String nameMother = "Joana";
        LocalDate birthdateMother = LocalDate.of(1987, 01, 11);
        String emailMother = "joana@gmail.com";
        Ledger ledgerMother = Ledger.createLedger();
        LedgerID ledgerID_Mother = ledgerMother.getLedgerID();

        //Person Father
        String nameFather = "Francisco";
        LocalDate birthdateFather = LocalDate.of(1988, 9, 21);
        String emailFather = "francisco@gmail.com";
        Ledger ledgerFather = Ledger.createLedger();
        LedgerID ledgerID_Father = ledgerFather.getLedgerID();

        //Birthplace
        String birthplace = "Braga";

        //Address
        String street = "Rua do Carmo";
        String number = "4";
        String zipCode = "2562";
        String city = "Porto";
        String country = "Portugal";
        Address address = Address.createAddress(street, number, zipCode, city, country);

        Person mother = Person.createPersonWithoutParents(emailMother, nameMother, birthdateMother, birthplace, address, ledgerID_Mother);
        Person father = Person.createPersonWithoutParents(emailFather, nameFather, birthdateFather, birthplace, address, ledgerID_Father);
        Person person = Person.createPersonWithParents(emailAna, name, birthdate, mother.getPersonID(), father.getPersonID(), birthplace, address, ledgerID_Ana);

        PersonID personID = person.getPersonID();

        //Account
        String denominationDomestic = "Domestic expenses";

        //Act

        //Expected
        AccountID accountID = AccountID.createAccountID(denominationDomestic, personID);

        List<AccountID> expectedAccounts = new ArrayList<>();
        expectedAccounts.add(accountID);

        boolean firstResult = person.addAccount(accountID);
        boolean secondResult = person.addAccount(accountID);

        //Assert
        assertEquals(person.getListOfAccounts(), expectedAccounts);
        assertTrue(firstResult);
        assertFalse(secondResult);

    }

    //Verify same siblings

    @Test
    @DisplayName("verify if 2 person have the same siblings-samePerson -false")
    public void verifySameSiblings_false_samePerson() {

        //Arrange

        //Person A
        String nameA = "ana";
        LocalDate birthdateA = LocalDate.of(1988, 9, 21);
        String emailAna = "ana@gmail.com";
        Ledger ledgerAna = Ledger.createLedger();
        LedgerID ledgerID_Ana = ledgerAna.getLedgerID();

        //Person B
        String nameB = "filipa";
        LocalDate birthdateB = LocalDate.of(1988, 9, 21);
        String emailFilipa = "filipa@gmail.com";
        Ledger ledgerFilipa = Ledger.createLedger();
        LedgerID ledgerID_Filipa = ledgerFilipa.getLedgerID();

        //Person C
        String nameC = "Pedro";
        LocalDate birthdateC = LocalDate.of(1988, 9, 21);
        String emailPedro = "pedro@gmail.com";
        Ledger ledgerPedro = Ledger.createLedger();
        LedgerID ledgerID_Pedro = ledgerPedro.getLedgerID();

        //Birthplace
        String birthplace = "Braga";

        //Address
        String street = "Rua do Carmo";
        String number = "4";
        String zipCode = "2562";
        String city = "Porto";
        String country = "Portugal";
        Address address = Address.createAddress(street, number, zipCode, city, country);

        Person personA = Person.createPersonWithoutParents(emailAna, nameA, birthdateA, birthplace, address, ledgerID_Ana);

        PersonID personID_A = personA.getPersonID();

        //Act
        personA.addSibling(personID_A);
        boolean result = personA.verifySameSiblings(personA);

        //Assert

        assertFalse(result);

    }


    @Test
    @DisplayName("verify if 2 person have the same siblings-happyCase")
    public void verifySameSiblings() {

        //Arrange

        //Person A
        String nameA = "ana";
        LocalDate birthdateA = LocalDate.of(1988, 9, 21);
        String emailAna = "ana@gmail.com";
        Ledger ledgerAna = Ledger.createLedger();
        LedgerID ledgerID_Ana = ledgerAna.getLedgerID();

        //Person B
        String nameB = "filipa";
        LocalDate birthdateB = LocalDate.of(1988, 9, 21);
        String emailFilipa = "filipa@gmail.com";
        Ledger ledgerFilipa = Ledger.createLedger();
        LedgerID ledgerID_Filipa = ledgerFilipa.getLedgerID();

        //Person C
        String nameC = "Pedro";
        LocalDate birthdateC = LocalDate.of(1988, 9, 21);
        String emailPedro = "pedro@gmail.com";
        Ledger ledgerPedro = Ledger.createLedger();
        LedgerID ledgerID_Pedro = ledgerPedro.getLedgerID();

        //Birthplace
        String birthplace = "Braga";

        //Address
        String street = "Rua do Carmo";
        String number = "4";
        String zipCode = "2562";
        String city = "Porto";
        String country = "Portugal";
        Address address = Address.createAddress(street, number, zipCode, city, country);

        Person personA = Person.createPersonWithoutParents(emailAna, nameA, birthdateA, birthplace, address, ledgerID_Ana);
        Person personB = Person.createPersonWithoutParents(emailFilipa, nameB, birthdateB, birthplace, address, ledgerID_Filipa);
        Person personC = Person.createPersonWithoutParents(emailPedro, nameC, birthdateC, birthplace, address, ledgerID_Pedro);

        PersonID personID_B = personB.getPersonID();
        personA.addSibling(personID_B);
        personC.addSibling(personID_B);

        //Act
        boolean result = personA.verifySameSiblings(personC);

        //Assert
        assertTrue(result);

    }

    @Test
    @DisplayName("verify if 2 person have the same siblings they are Siblings-differentSize of Array List")
    public void verifySameSiblings_false() {

        //Arrange

        //Person A
        String nameA = "ana";
        LocalDate birthdateA = LocalDate.of(1988, 9, 21);
        String emailAna = "ana@gmail.com";
        Ledger ledgerAna = Ledger.createLedger();
        LedgerID ledgerID_Ana = ledgerAna.getLedgerID();

        //Person B
        String nameB = "filipa";
        LocalDate birthdateB = LocalDate.of(1988, 9, 21);
        String emailFilipa = "filipa@gmail.com";
        Ledger ledgerFilipa = Ledger.createLedger();
        LedgerID ledgerID_Filipa = ledgerFilipa.getLedgerID();

        //Person C
        String nameC = "Pedro";
        LocalDate birthdateC = LocalDate.of(1988, 9, 21);
        String emailPedro = "pedro@gmail.com";
        Ledger ledgerPedro = Ledger.createLedger();
        LedgerID ledgerID_Pedro = ledgerPedro.getLedgerID();

        //Person D
        String nameD = "Pedro";
        LocalDate birthdateD = LocalDate.of(1988, 9, 21);
        String emailJoao = "joao@gmail.com";
        Ledger ledgerJoao = Ledger.createLedger();
        LedgerID ledgerID_Joao = ledgerPedro.getLedgerID();

        //Birthplace
        String birthplace = "Braga";

        //Address
        String street = "Rua do Carmo";
        String number = "4";
        String zipCode = "2562";
        String city = "Porto";
        String country = "Portugal";
        Address address = Address.createAddress(street, number, zipCode, city, country);

        Person personA = Person.createPersonWithoutParents(emailAna, nameA, birthdateA, birthplace, address, ledgerID_Ana);
        Person personB = Person.createPersonWithoutParents(emailFilipa, nameB, birthdateB, birthplace, address, ledgerID_Filipa);
        Person personC = Person.createPersonWithoutParents(emailPedro, nameC, birthdateC, birthplace, address, ledgerID_Pedro);
        Person personD = Person.createPersonWithoutParents(emailJoao, nameD, birthdateD, birthplace, address, ledgerID_Joao);


        PersonID personID_B = personB.getPersonID();
        PersonID personID_D = personD.getPersonID();


        //Act
        personA.addSibling(personID_B);
        personA.addSibling(personID_D);
        personC.addSibling(personID_B);

        boolean result = personA.verifySameSiblings(personC);

        //Assert
        assertFalse(result);

    }

    @Test
    @DisplayName("verify if 2 person have the same siblings they are Siblings-happyCase")
    public void verifySameSiblings_siblings() {

        //Arrange

        //Person A
        String nameA = "ana";
        LocalDate birthdateA = LocalDate.of(1988, 9, 21);
        String emailAna = "ana@gmail.com";
        Ledger ledgerAna = Ledger.createLedger();
        LedgerID ledgerID_Ana = ledgerAna.getLedgerID();

        //Person B
        String nameB = "filipa";
        LocalDate birthdateB = LocalDate.of(1988, 9, 21);
        String emailFilipa = "filipa@gmail.com";
        Ledger ledgerFilipa = Ledger.createLedger();
        LedgerID ledgerID_Filipa = ledgerFilipa.getLedgerID();

        //Person C
        String nameC = "Pedro";
        LocalDate birthdateC = LocalDate.of(1988, 9, 21);
        String emailPedro = "pedro@gmail.com";
        Ledger ledgerPedro = Ledger.createLedger();
        LedgerID ledgerID_Pedro = ledgerPedro.getLedgerID();

        //Birthplace
        String birthplace = "Braga";

        //Address
        String street = "Rua do Carmo";
        String number = "4";
        String zipCode = "2562";
        String city = "Porto";
        String country = "Portugal";
        Address address = Address.createAddress(street, number, zipCode, city, country);

        Person personA = Person.createPersonWithoutParents(emailAna, nameA, birthdateA, birthplace, address, ledgerID_Ana);
        Person personB = Person.createPersonWithoutParents(emailFilipa, nameB, birthdateB, birthplace, address, ledgerID_Filipa);
        Person personC = Person.createPersonWithoutParents(emailPedro, nameC, birthdateC, birthplace, address, ledgerID_Pedro);

        PersonID personID_A = personA.getPersonID();
        PersonID personID_B = personB.getPersonID();
        PersonID personID_C = personC.getPersonID();


        //Act
        personA.addSibling(personID_B);
        personA.addSibling(personID_C);
        personC.addSibling(personID_B);
        personC.addSibling(personID_A);

        boolean result = personA.verifySameSiblings(personC);

        //Assert
        assertTrue(result);

    }

    @Test
    @DisplayName("verify if 2 person have the same siblings-False")
    public void verifySameSiblings_False() {

        //Arrange

        //Person A
        String nameA = "ana";
        LocalDate birthdateA = LocalDate.of(1988, 9, 21);
        String emailAna = "ana@gmail.com";
        Ledger ledgerAna = Ledger.createLedger();
        LedgerID ledgerID_Ana = ledgerAna.getLedgerID();

        //Person B
        String nameB = "filipa";
        LocalDate birthdateB = LocalDate.of(1988, 9, 21);
        String emailFilipa = "filipa@gmail.com";
        Ledger ledgerFilipa = Ledger.createLedger();
        LedgerID ledgerID_Filipa = ledgerFilipa.getLedgerID();

        //Person C
        String nameC = "Pedro";
        LocalDate birthdateC = LocalDate.of(1988, 9, 21);
        String emailPedro = "pedro@gmail.com";
        Ledger ledgerPedro = Ledger.createLedger();
        LedgerID ledgerID_Pedro = ledgerPedro.getLedgerID();

        //Birthplace
        String birthplace = "Braga";

        //Address
        String street = "Rua do Carmo";
        String number = "4";
        String zipCode = "2562";
        String city = "Porto";
        String country = "Portugal";
        Address address = Address.createAddress(street, number, zipCode, city, country);

        Person personA = Person.createPersonWithoutParents(emailAna, nameA, birthdateA, birthplace, address, ledgerID_Ana);
        Person personB = Person.createPersonWithoutParents(emailFilipa, nameB, birthdateB, birthplace, address, ledgerID_Filipa);
        Person personC = Person.createPersonWithoutParents(emailPedro, nameC, birthdateC, birthplace, address, ledgerID_Pedro);

        PersonID personID_B = personB.getPersonID();
        PersonID personID_C = personC.getPersonID();

        //Act
        personA.addSibling(personID_B);
        personB.addSibling(personID_C);

        boolean result = personA.verifySameSiblings(personB);

        //Assert
        assertFalse(result);
    }


    // check if Person has Account

    @Test
    @DisplayName("Verify checkIfPersonHasCategory() || Success")
    public void checkIfPersonHasAccount_Success() {

        // Arrange
        PersonID personID = PersonID.createPersonID("santi@gmail.com");

        AccountID accountID = AccountID.createAccountID("Denomination", personID);
        AccountID accountIDExtra = AccountID.createAccountID("Deno", personID);

        // Person
        String nameC = "Pedro";
        LocalDate birthdateC = LocalDate.of(1988, 9, 21);
        String emailPedro = "pedro@gmail.com";
        Ledger ledgerPedro = Ledger.createLedger();
        LedgerID ledgerID_Pedro = ledgerPedro.getLedgerID();

        //Birthplace
        String birthplace = "Braga";

        //Address
        String street = "Rua do Carmo";
        String number = "4";
        String zipCode = "2562";
        String city = "Porto";
        String country = "Portugal";
        Address address = Address.createAddress(street, number, zipCode, city, country);


        // Act
        Person person = Person.createPersonWithoutParents(emailPedro, nameC, birthdateC, birthplace, address, ledgerID_Pedro);
        person.addAccount(accountID);

        // Assert
        assertEquals(true, person.checkIfPersonHasAccount(accountID));
    }


    @Test
    @DisplayName("Verify checkIfPersonHasAccount() || Fail")
    public void checkIfPersonHasAccount_Fail() {

        // Arrange
        PersonID personID = PersonID.createPersonID("santi@gmail.com");

        AccountID accountID = AccountID.createAccountID("Denomination", personID);
        AccountID accountIDExtra = AccountID.createAccountID("Deno", personID);

        // Person
        String nameC = "Pedro";
        LocalDate birthdateC = LocalDate.of(1988, 9, 21);
        String emailPedro = "pedro@gmail.com";
        Ledger ledgerPedro = Ledger.createLedger();
        LedgerID ledgerID_Pedro = ledgerPedro.getLedgerID();

        //Birthplace
        String birthplace = "Braga";

        //Address
        String street = "Rua do Carmo";
        String number = "4";
        String zipCode = "2562";
        String city = "Porto";
        String country = "Portugal";
        Address address = Address.createAddress(street, number, zipCode, city, country);


        // Act
        Person person = Person.createPersonWithoutParents(emailPedro, nameC, birthdateC, birthplace, address, ledgerID_Pedro);
        person.addAccount(accountIDExtra);

        // Assert
        assertEquals(false, person.checkIfPersonHasAccount(accountID));
    }

    @Test
    @DisplayName("Verify checkIfPersonHasCategory() || Success")
    public void checkIfPersonHasCategory_Success() {

        // Arrange
        PersonID personID = PersonID.createPersonID("santi@gmail.com");

        String denomination = "HairStylist";

        CategoryID categoryID = CategoryID.createCategoryID(denomination, personID);

        // Person
        String nameC = "Pedro";
        LocalDate birthdateC = LocalDate.of(1988, 9, 21);
        String emailPedro = "pedro@gmail.com";
        Ledger ledgerPedro = Ledger.createLedger();
        LedgerID ledgerID_Pedro = ledgerPedro.getLedgerID();

        //Birthplace
        String birthplace = "Braga";

        //Address
        String street = "Rua do Carmo";
        String number = "4";
        String zipCode = "2562";
        String city = "Porto";
        String country = "Portugal";
        Address address = Address.createAddress(street, number, zipCode, city, country);


        // Act
        Person person = Person.createPersonWithoutParents(emailPedro, nameC, birthdateC, birthplace, address, ledgerID_Pedro);
        person.addCategory(categoryID);

        // Assert
        assertEquals(true, person.checkIfPersonHasCategory(categoryID));
    }

    @Test
    @DisplayName("Verify checkIfPersonHasCategory() || Fail")
    public void checkIfPersonHasCategory_Fail() {

        // Arrange
        PersonID personID = PersonID.createPersonID("santi@gmail.com");

        String denomination = "HairStylist";

        CategoryID categoryID = CategoryID.createCategoryID(denomination, personID);

        CategoryID newCategoryID = CategoryID.createCategoryID("Runners", personID);

        // Person
        String nameC = "Pedro";
        LocalDate birthdateC = LocalDate.of(1988, 9, 21);
        String emailPedro = "pedro@gmail.com";
        Ledger ledgerPedro = Ledger.createLedger();
        LedgerID ledgerID_Pedro = ledgerPedro.getLedgerID();

        //Birthplace
        String birthplace = "Braga";

        //Address
        String street = "Rua do Carmo";
        String number = "4";
        String zipCode = "2562";
        String city = "Porto";
        String country = "Portugal";
        Address address = Address.createAddress(street, number, zipCode, city, country);


        // Act
        Person person = Person.createPersonWithoutParents(emailPedro, nameC, birthdateC, birthplace, address, ledgerID_Pedro);
        person.addCategory(categoryID);

        boolean result = person.checkIfPersonHasCategory(newCategoryID);

        // Assert
        assertEquals(false, result);
    }

    @Test
    @DisplayName("Add address")
    public void checkAddAddress() {

        // Arrange
        PersonID personID = PersonID.createPersonID("santi@gmail.com");

        String denomination = "HairStylist";

        CategoryID categoryID = CategoryID.createCategoryID(denomination, personID);
        CategoryID categoryID_Extra = CategoryID.createCategoryID("Food", personID);

        // Person
        String nameC = "Pedro";
        LocalDate birthdateC = LocalDate.of(1988, 9, 21);
        String emailPedro = "pedro@gmail.com";
        Ledger ledgerPedro = Ledger.createLedger();
        LedgerID ledgerID_Pedro = ledgerPedro.getLedgerID();

        //Birthplace
        String birthplace = "Braga";

        //Address
        String street = "Rua do Carmo";
        String number = "4";
        String zipCode = "2562";
        String city = "Porto";
        String country = "Portugal";
        Address address = Address.createAddress(street, number, zipCode, city, country);



        // Act
        Person person = Person.createPersonWithoutParents(emailPedro, nameC, birthdateC, birthplace, address, ledgerID_Pedro);
        boolean result = person.addAddress(street, number, zipCode, city, country);

        // Assert
        assertEquals(false, result);
    }

    @Test
    @DisplayName("Add mother")
    public void checkAddMother() {

        // Arrange
        PersonID personID = PersonID.createPersonID("santi@gmail.com");

        String denomination = "HairStylist";

        CategoryID categoryID = CategoryID.createCategoryID(denomination, personID);
        CategoryID categoryID_Extra = CategoryID.createCategoryID("Food", personID);

        // Person
        String nameC = "Pedro";
        LocalDate birthdateC = LocalDate.of(1988, 9, 21);
        String emailPedro = "pedro@gmail.com";
        Ledger ledgerPedro = Ledger.createLedger();
        LedgerID ledgerID_Pedro = ledgerPedro.getLedgerID();

        //Create Mother

        PersonID personMother = PersonID.createPersonID("mm@gmail.com");

        //Create Father

        PersonID personFather = PersonID.createPersonID("ff@gmail.com");

        //Create Father

        //Birthplace
        String birthplace = "Braga";

        //Address
        String street = "Rua do Carmo";
        String number = "4";
        String zipCode = "2562";
        String city = "Porto";
        String country = "Portugal";
        Address address = Address.createAddress(street, number, zipCode, city, country);



        // Act
        Person person = Person.createPersonWithParents(emailPedro, nameC, birthdateC, personMother,personFather, birthplace, address, ledgerID_Pedro);
        boolean result = person.addMother(personMother);

        // Assert
        assertEquals(false, result);
    }

    @Test
    @DisplayName("Add father")
    public void checkAddFather() {

        // Arrange
        PersonID personID = PersonID.createPersonID("santi@gmail.com");

        String denomination = "HairStylist";

        CategoryID categoryID = CategoryID.createCategoryID(denomination, personID);
        CategoryID categoryID_Extra = CategoryID.createCategoryID("Food", personID);

        // Person
        String nameC = "Pedro";
        LocalDate birthdateC = LocalDate.of(1988, 9, 21);
        String emailPedro = "pedro@gmail.com";
        Ledger ledgerPedro = Ledger.createLedger();
        LedgerID ledgerID_Pedro = ledgerPedro.getLedgerID();

        //Create Mother

        PersonID personMother = PersonID.createPersonID("mm@gmail.com");

        //Create Father

        PersonID personFather = PersonID.createPersonID("ff@gmail.com");

        //Create Father

        //Birthplace
        String birthplace = "Braga";

        //Address
        String street = "Rua do Carmo";
        String number = "4";
        String zipCode = "2562";
        String city = "Porto";
        String country = "Portugal";
        Address address = Address.createAddress(street, number, zipCode, city, country);



        // Act
        Person person = Person.createPersonWithParents(emailPedro, nameC, birthdateC, personMother,personFather, birthplace, address, ledgerID_Pedro);
        boolean result = person.addFather(personFather);

        // Assert
        assertEquals(false, result);
    }

}



/* COISAS A APAGAR!

//test make a person

    @Test
    public void checkMakeAPerson() {

        //Arrange
        String name = "Joana";
        LocalDate birthdate = LocalDate.of(1987, 01, 11);

        //Act
        Person person = new Person(name, birthdate);
        Person personA = new Person(person);

        //Assert
        assertEquals(person, personA);
    }

    @Test
    public void checkMakeAPerson_motherAndFatherNotNull() {

        //Arrange
        String name = "Joana";
        LocalDate birthdate = LocalDate.of(1987, 01, 11);
        String nameB = "Maria";
        LocalDate birthdateB = LocalDate.of(1987, 01, 11);
        String nameC = "Filipe";
        LocalDate birthdateC = LocalDate.of(1987, 01, 11);

        //Act
        Person mother = new Person(nameB, birthdateB);
        Person father = new Person(nameC, birthdateC);
        Person person = new Person(name, birthdate, mother, father);
        Person personA = new Person(person);

        //Assert
        assertEquals(person, personA);
    }


    @Test
    public void checkMakeAPerson_FatherNull() {

        //Arrange
        String name = "Joana";
        LocalDate birthdate = LocalDate.of(1987, 01, 11);
        String nameB = "Maria";
        LocalDate birthdateB = LocalDate.of(1987, 01, 11);
        String nameC = "Filipe";
        LocalDate birthdateC = LocalDate.of(1987, 01, 11);

        //Act
        Person mother = new Person(nameB, birthdateB);
        Person father = null;
        Person person = new Person(name, birthdate, mother, father);
        Person personA = new Person(person);

        //Assert
        assertEquals(person, personA);
    }

    @Test
    public void checkMakeAPerson_MotherNull() {

        //Arrange
        String name = "Joana";
        LocalDate birthdate = LocalDate.of(1987, 01, 11);
        String nameB = "Maria";
        LocalDate birthdateB = LocalDate.of(1987, 01, 11);
        String nameC = "Filipe";
        LocalDate birthdateC = LocalDate.of(1987, 01, 11);

        //Act
        Person mother = null;
        Person father = new Person(nameC, birthdateC);
        Person person = new Person(name, birthdate, mother, father);
        Person personA = new Person(person);

        //Assert
        assertEquals(person, personA);
    }

//test setName and setBirthday

    @Test
    public void checkSetNameAndBirthday() {


        //Arrange
        String name = "Joana";
        LocalDate birthdate = LocalDate.of(1987, 01, 11);
        String nameA = "Ana";
        LocalDate birthdateA = LocalDate.of(1987, 01, 11);

        //Act
        Person person = new Person(name, birthdate);
        person.setName(nameA);
        person.setBirthdate(birthdateA);

        //Assert
        assertEquals(person.getName(), nameA);
        assertEquals(person.getBirthdate(), birthdateA);
    }

@Test
    public void checkMakeAclone() {
        //Arrange
        String nameA = "Ana";
        LocalDate dateA = LocalDate.of(1987, 01, 11);
        ;
        String nameB = "Ana";
        LocalDate dateB = LocalDate.of(1987, 01, 11);
        ;
        Person mother = new Person("Joana", LocalDate.of(1987, 01, 11));
        Person father = new Person("Joao", LocalDate.of(1987, 01, 11));
        //Act
        Person personA = new Person(nameA, dateA, mother, father);
        Person clonePerson = personA.clone();
        //Assert
        assertEquals(personA, clonePerson);
    }

    @Test
    public void checkMakeAclone_motherNull() {
        //Arrange
        String nameA = "Ana";
        LocalDate dateA = LocalDate.of(1987, 01, 11);
        Person mother = new Person("Joana", LocalDate.of(1987, 01, 11));
        Person father = null;
        //Act
        Person personA = new Person(nameA, dateA, mother, father);
        Person clonePerson = personA.clone();
        //Assert
        assertEquals(personA, clonePerson);
    }

    @Test
    public void checkMakeAclone_fatherNull() {
        //Arrange
        String nameA = "Ana";
        LocalDate dateA = LocalDate.of(1987, 01, 11);
        Person mother = null;
        Person father = new Person("Joao", LocalDate.of(1987, 01, 11));
        //Act
        Person personA = new Person(nameA, dateA, mother, father);
        Person clonePerson = personA.clone();
        //Assert
        assertEquals(personA, clonePerson);
    }

    @Test
    @DisplayName("Verify clone method with mother, father and siblings")
    public void verifyCloneMethod() {

        //Arrange
        String nameA = "ana", birthplaceA = "Porto";
        LocalDate birthdateA = LocalDate.of(1988, 9, 21);
        String nameB = "filipa", birthplaceB = "Porto";
        LocalDate birthdateB = LocalDate.of(1988, 9, 21);
        String nameC = "Pedro", birthplaceC = "Porto";
        LocalDate birthdateC = LocalDate.of(1988, 9, 21);
        String nameMother = "Catarina";
        LocalDate birthdateMother = LocalDate.of(1982, 8, 27);
        String nameFather = "Tiago";
        LocalDate birthdateFather = LocalDate.of(1983, 8, 19);
        String street = "Rua da Saudade", number = "5", zipCode = "400-121", city = "Porto", country = "Portugal";

        //Act
        Address address = new Address(street, number, zipCode, city, country);
        Person mother = new Person(nameMother, birthdateMother);
        Person father = new Person(nameFather, birthdateFather);
        Person personA = new Person(nameA, birthdateA, father, mother);
        Person personB = new Person(nameB, birthdateB, father, mother);
        Person personC = new Person(nameC, birthdateC, father, mother);
        personA.addSiblings(personC);
        personA.setAddress(address);
        Person clonePerson = personA.clone();


        //Assert

        assertEquals(personA, clonePerson);

    }

        //hashcode

    @Test
    @DisplayName("Person - hashcodeTrue")
    public void verifyHashCode() {
        Person x = new Person("Joana", LocalDate.of(1983, 8, 19));
        Person y = x.clone();

        assertTrue(x.hashCode() == y.hashCode());
        assertTrue(y.hashCode() == y.hashCode());
        assertEquals(true, y.hashCode() == y.hashCode());
    }

    @Test
    public void verifyEquals_True_same_Birthplace() {
        //Arrange
        String nameA = "Ana", birthplaceA = "PORTO";
        LocalDate birthdateA = LocalDate.of(1988, 9, 21);
        String nameB = "Ana", birthplaceB = "PORTO";
        LocalDate birthdateB = LocalDate.of(1988, 9, 21);
        Person mother = new Person("Joana", LocalDate.of(1983, 8, 19));
        Person father = new Person("Joao", LocalDate.of(1983, 8, 19));
        //Act
        Person personA = new Person(nameA, birthdateA, mother, father);
        personA.setBirthplace(birthplaceA);
        Person personB = new Person(nameB, birthdateB, mother, father);
        personB.setBirthplace(birthplaceB);
        //Assert
        assertEquals(personA, personB);
    }

        //test equals for address

    @Test
    @DisplayName("Verify Person Equals - HappyCase")
    public void verifyEquals_True_same_Address() {

        //Arrange
        String nameA = "Ana", birthplaceA = "PORTO";
        LocalDate birthdateA = LocalDate.of(1988, 9, 21);
        String nameB = "Ana", birthplaceB = "PORTO";
        LocalDate birthdateB = LocalDate.of(1988, 9, 21);
        Person mother = new Person("Joana", LocalDate.of(1983, 8, 19));
        Person father = new Person("Joao", LocalDate.of(1983, 8, 19));

        //Arrange address
        String streetA = "rua da saudade";
        String doorNumberA = "1";
        String postCodeA = "4000-222";
        String cityA = "Porto";
        String countryA = "Portugal";


        //Act
        Address address = new Address(streetA, doorNumberA, postCodeA, cityA, countryA);
        Person personA = new Person(nameA, birthdateA, mother, father, birthplaceA, address);
        Person personB = new Person(nameB, birthdateB, mother, father, birthplaceB, address);


        //Assert
        assertEquals(personA, personB);
    }

    @Test
    public void verifyEquals_False_Birthplace_null_personA() {
        //Arrange
        String nameAnaA = "Ana";
        LocalDate birthdateAnaA = LocalDate.of(1985, 8, 19);
        String emailAnaA = "ana@gmail.com";
        String birthplaceAnaA = null;

        String nameAnaB = "Ana";
        LocalDate birthdateAnaB = LocalDate.of(1985, 8, 19);
        String emailAnaB = "ana@gmail.com";
        String birthplaceAnaB = "Cuba";

        String nameMother = "Joana";
        LocalDate birthdateMother = LocalDate.of(1987, 01, 11);
        String emailMother = "joana@gmail.com";

        String nameFather = "João";
        LocalDate birthdateFather = LocalDate.of(1987, 01, 11);
        String emailFather = "joao@gmail.com";

        String birthplace = "Braga";
        String street = "Rua do Carmo";
        String number = "4";
        String zipCode = "2562";
        String city = "Porto";
        String country = "Portugal";
        Address address = Address.createAddress(street, number, zipCode, city, country);


        Person personMother = Person.createPersonWithoutParents(emailMother, nameMother, birthdateMother, birthplace, address);
        Person personFather = Person.createPersonWithoutParents(emailFather, nameFather, birthdateFather, birthplace, address);

        //Act
        Person personA = Person.createPersonWithParents(emailAnaA, nameAnaA, birthdateAnaA, personMother.getPersonID(), personFather.getPersonID(), birthplaceAnaA, address);
        Person personB = Person.createPersonWithParents(emailAnaB, nameAnaB, birthdateAnaB, personMother.getPersonID(), personFather.getPersonID(), birthplaceAnaB, address);

        //Assert
        assertNotEquals(personA, personB);
    }

    @Test
    public void verifyEquals_False_Birthplace_null_personB() {
        //Arrange
        String nameAnaA = "Ana";
        LocalDate birthdateAnaA = LocalDate.of(1985, 8, 19);
        String emailAnaA = "ana@gmail.com";
        String birthplaceAnaA = "Cuba";

        String nameAnaB = "Ana";
        LocalDate birthdateAnaB = LocalDate.of(1985, 8, 19);
        String emailAnaB = "ana@gmail.com";
        String birthplaceAnaB = null;

        String nameMother = "Joana";
        LocalDate birthdateMother = LocalDate.of(1987, 01, 11);
        String emailMother = "joana@gmail.com";

        String nameFather = "João";
        LocalDate birthdateFather = LocalDate.of(1987, 01, 11);
        String emailFather = "joao@gmail.com";

        String birthplace = "Braga";
        String street = "Rua do Carmo";
        String number = "4";
        String zipCode = "2562";
        String city = "Porto";
        String country = "Portugal";
        Address address = Address.createAddress(street, number, zipCode, city, country);


        Person personMother = Person.createPersonWithoutParents(emailMother, nameMother, birthdateMother, birthplace, address);
        Person personFather = Person.createPersonWithoutParents(emailFather, nameFather, birthdateFather, birthplace, address);

        //Act
        Person personA = Person.createPersonWithParents(emailAnaA, nameAnaA, birthdateAnaA, personMother.getPersonID(), personFather.getPersonID(), birthplaceAnaA, address);
        Person personB = Person.createPersonWithParents(emailAnaB, nameAnaB, birthdateAnaB, personMother.getPersonID(), personFather.getPersonID(), birthplaceAnaB, address);

        //Assert
        assertNotEquals(personA, personB);
    }

    //test verify the same name - two different objets - false result

    @Test
    public void verifySameName_False() {

        //Arrange
        String nameA = "Joana";
        LocalDate birthdateA = LocalDate.of(1988, 9, 21);
        String nameB = "Ana";
        LocalDate birthdateB = LocalDate.of(1988, 9, 21);


        //Act
        Person personA = new Person(nameA, birthdateA);
        Person personB = new Person(nameB, birthdateB);
        boolean result = personA.verifySameName(personB);

        //Assert
        assertEquals(false, result);
    }

    //test verify the same name - the same object - true result

    @Test
    public void verifySameName_SO_True() {

        //Arrange
        String nameA = "Joana";
        LocalDate birthdateA = LocalDate.of(1988, 9, 21);

        //Act
        Person personA = new Person(nameA, birthdateA);
        boolean result = personA.verifySameName(personA);

        //Assert
        assertEquals(true, result);
    }

    //test verify the same name - two different objects - true result

    @Test
    public void verifySameName_DO_True() {

        //Arrange
        String nameA = "Joana";
        LocalDate birthdateA = LocalDate.of(1988, 9, 21);
        String nameB = "Joana";
        LocalDate birthdateB = LocalDate.of(1988, 9, 21);


        //Act
        Person personA = new Person(nameA, birthdateA);
        Person personB = new Person(nameB, birthdateB);
        boolean result = personA.verifySameName(personB);

        //Assert
        assertEquals(true, result);
    }

    //test verify the same birthdate - two different objects - true result

    @Test
    public void verifySameBirthdate_DO_True() {

        //Arrange
        String nameA = "Joana";
        LocalDate birthdateA = LocalDate.of(1988, 9, 21);
        String nameB = "Ana";
        LocalDate birthdateB = LocalDate.of(1988, 9, 21);


        //Act
        Person personA = new Person(nameA, birthdateA);
        Person personB = new Person(nameB, birthdateB);
        boolean result = personA.verifySameBirthdate(personB);

        //Assert
        assertEquals(true, result);
    }

    //test verify the same birthdate - two different objets - false result

    @Test
    public void verifySameBirthdate_DO_False() {

        //Arrange
        String nameA = "Joana";
        LocalDate birthdateA = LocalDate.of(1988, 9, 21);
        String nameB = "Ana";
        LocalDate birthdateB = LocalDate.of(1987, 8, 11);


        //Act
        Person personA = new Person(nameA, birthdateA);
        Person personB = new Person(nameB, birthdateB);
        boolean result = personA.verifySameBirthdate(personB);

        //Assert
        assertEquals(false, result);
    }

    //test verify the same birthdate - the same object - true result

    @Test
    public void verifySameBirthdate_SO__True() {

        //Arrange
        String nameA = "Joana";
        LocalDate birthdateA = LocalDate.of(1988, 9, 21);

        //Act
        Person personA = new Person(nameA, birthdateA);
        boolean result = personA.verifySameBirthdate(personA);

        //Assert
        assertEquals(true, result);
    }

    //test verify the same birthplace - two different objects - true result

    @Test
    public void verifySameBirthplace_DO_True() {
        //Arrange
        String nameA = "Joana";
        LocalDate birthdateA = LocalDate.of(1988, 9, 21);
        String birthplaceA = "Porto";
        String nameB = "Ana";
        LocalDate birthdateB = LocalDate.of(1988, 9, 21);
        String birthplaceB = "Porto";
        //Act
        Person personA = new Person(nameA, birthdateA);
        Person personB = new Person(nameB, birthdateB);
        personA.setBirthplace(birthplaceA);
        personB.setBirthplace(birthplaceB);
        boolean result = personA.verifySameBirthplace(personB);
        //Assert
        assertEquals(true, result);
    }

    //test verify the same birthplace - two different objects - false result

    @Test
    public void verifySameBirthplace_DO_False() {
        //Arrange
        String nameA = "Joana";
        LocalDate birthdateA = LocalDate.of(1988, 9, 21);
        String birthplaceA = "Porto";
        String nameB = "Ana";
        LocalDate birthdateB = LocalDate.of(1988, 9, 21);
        String birthplaceB = "Braga";
        //Act
        Person personA = new Person(nameA, birthdateA);
        Person personB = new Person(nameB, birthdateB);
        personA.setBirthplace(birthplaceA);
        personB.setBirthplace(birthplaceB);
        boolean result = personA.verifySameBirthplace(personB);
        //Assert
        assertEquals(false, result);
    }

    //test verify the same birthplace - same object - true result

    @Test
    public void verifySameBirthplace_SO_True() {
        //Arrange
        String nameA = "Joana";
        LocalDate birthdateA = LocalDate.of(1988, 9, 21);
        String birthplaceA = "Porto";
        //Act
        Person personA = new Person(nameA, birthdateA);
        ;
        personA.setBirthplace(birthplaceA);
        boolean result = personA.verifySameBirthplace(personA);
        //Assert
        assertEquals(true, result);
    }

//test set Mother and Father

    @Test
    public void checkSetMotherAndFather() {


        //Arrange
        String nameA = "Joana";
        LocalDate birthdateA = LocalDate.of(1988, 9, 21);
        String nameB = "Ana";
        LocalDate birthdateB = LocalDate.of(1978, 2, 18);
        String nameC = "Joao";
        LocalDate birthdateC = LocalDate.of(1977, 4, 21);
        String nameD = "Ana";
        LocalDate birthdateD = LocalDate.of(1978, 3, 22);
        String nameE = "Joao";
        LocalDate birthdateE = LocalDate.of(1954, 4, 21);


        //Act
        Person motherA = new Person(nameB, birthdateB);
        Person fatherA = new Person(nameC, birthdateC);
        Person personA = new Person(nameA, birthdateA, motherA, fatherA);
        Person motherB = new Person(nameD, birthdateD);
        Person fatherB = new Person(nameE, birthdateE);


        personA.setMother(motherB);
        personA.setFather(fatherB);

        //Assert
        assertEquals(personA.getMother(), motherB);
        assertEquals(personA.getFather(), fatherB);
    }

    @Test
    @DisplayName("Test For Person- create category Exception")
    public void checkException() {

        //Arrange

        //Create Person

        String nameMaria = "Maria";
        LocalDate birthdateMaria = LocalDate.of(1988, 9, 21);
        String namePedro = "Pedro";
        LocalDate birthdatePedro = LocalDate.of(1988, 9, 21);
        String nameJoana = "Joana";
        LocalDate birthdateJoana = LocalDate.of(1988, 9, 21);

        Person personMother = new Person(nameMaria, birthdateMaria);
        Person personFather = new Person(namePedro, birthdatePedro);
        Person personJoana = new Person(nameJoana, birthdateJoana, personMother, personFather);

        String denomination = "";

        CategoryList categoryList = new CategoryList();

        //Act
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> personJoana.createCategory(denomination));

        //Assert
        assertEquals(thrown.getMessage(), "Denomination is Empty");
    }



        @Test
    public void verifyEquals_False_differentName() {
        //Arrange
        String nameAnaA = "Ana";
        LocalDate birthdateAnaA = LocalDate.of(1985, 8, 19);
        String emailAnaA = "ana@gmail.com";
        Ledger ledgerAna = Ledger.createLedger();
        LedgerID ledgerID_Ana = ledgerAna.getLedgerID();
        String birthplaceAnaA = "Cuba";

        String nameAnaB = "Liana";
        LocalDate birthdateAnaB = LocalDate.of(1985, 8, 19);
        String emailAnaB = "ana@gmail.com";
        String birthplaceAnaB = "Cuba";

        String nameMother = "Joana";
        LocalDate birthdateMother = LocalDate.of(1987, 01, 11);
        String emailMother = "joana@gmail.com";
        Ledger ledgerMother = Ledger.createLedger();
        LedgerID ledgerID_Mother = ledgerMother.getLedgerID();

        String nameFather = "João";
        LocalDate birthdateFather = LocalDate.of(1987, 01, 11);
        String emailFather = "joao@gmail.com";
        Ledger ledgerFather = Ledger.createLedger();
        LedgerID ledgerID_Father = ledgerFather.getLedgerID();

        String birthplace = "Braga";
        String street = "Rua do Carmo";
        String number = "4";
        String zipCode = "2562";
        String city = "Porto";
        String country = "Portugal";
        Address address = Address.createAddress(street, number, zipCode, city, country);


        Person personMother = Person.createPersonWithoutParents(emailMother, nameMother, birthdateMother, birthplace, address, ledgerID_Mother);
        Person personFather = Person.createPersonWithoutParents(emailFather, nameFather, birthdateFather, birthplace, address, ledgerID_Father);

        //Act
        Person personA = Person.createPersonWithParents(emailAnaA, nameAnaA, birthdateAnaA, personMother.getPersonID(), personFather.getPersonID(), birthplaceAnaA, address, ledgerID_Ana);
        Person personB = Person.createPersonWithParents(emailAnaB, nameAnaB, birthdateAnaB, personMother.getPersonID(), personFather.getPersonID(), birthplaceAnaB, address, ledgerID_Ana);

        //Assert
        assertNotEquals(personA, personB);
    }

    @Test
    public void verifyEquals_False_differentBirthdate() {
        //Arrange
        //Arrange
        String nameAnaA = "Ana";
        LocalDate birthdateAnaA = LocalDate.of(1985, 8, 19);
        String emailAnaA = "ana@gmail.com";
        Ledger ledgerAna = Ledger.createLedger();
        LedgerID ledgerID_Ana = ledgerAna.getLedgerID();
        String birthplaceAnaA = "Cuba";

        String nameAnaB = "Ana";
        LocalDate birthdateAnaB = LocalDate.of(1998, 8, 19);
        String emailAnaB = "ana@gmail.com";
        String birthplaceAnaB = "Cuba";

        String nameMother = "Joana";
        LocalDate birthdateMother = LocalDate.of(1987, 01, 11);
        String emailMother = "joana@gmail.com";
        Ledger ledgerMother = Ledger.createLedger();
        LedgerID ledgerID_Mother = ledgerMother.getLedgerID();

        String nameFather = "João";
        LocalDate birthdateFather = LocalDate.of(1987, 01, 11);
        String emailFather = "joao@gmail.com";
        Ledger ledgerFather = Ledger.createLedger();
        LedgerID ledgerID_Father = ledgerFather.getLedgerID();

        String birthplace = "Braga";
        String street = "Rua do Carmo";
        String number = "4";
        String zipCode = "2562";
        String city = "Porto";
        String country = "Portugal";
        Address address = Address.createAddress(street, number, zipCode, city, country);


        Person personMother = Person.createPersonWithoutParents(emailMother, nameMother, birthdateMother, birthplace, address, ledgerID_Mother);
        Person personFather = Person.createPersonWithoutParents(emailFather, nameFather, birthdateFather, birthplace, address, ledgerID_Father);

        //Act
        Person personA = Person.createPersonWithParents(emailAnaA, nameAnaA, birthdateAnaA, personMother.getPersonID(), personFather.getPersonID(), birthplaceAnaA, address, ledgerID_Ana);
        Person personB = Person.createPersonWithParents(emailAnaB, nameAnaB, birthdateAnaB, personMother.getPersonID(), personFather.getPersonID(), birthplaceAnaB, address, ledgerID_Ana);

        //Assert
        assertNotEquals(personA, personB);
    }


    @Test
    public void verifyEquals_False_different_Birthplace() {
        //Arrange
        String nameAnaA = "Ana";
        LocalDate birthdateAnaA = LocalDate.of(1985, 8, 19);
        String emailAnaA = "ana@gmail.com";
        Ledger ledgerAna = Ledger.createLedger();
        LedgerID ledgerID_Ana = ledgerAna.getLedgerID();
        String birthplaceAnaA = "Cuba";

        String nameAnaB = "Ana";
        LocalDate birthdateAnaB = LocalDate.of(1985, 8, 19);
        String emailAnaB = "ana@gmail.com";
        String birthplaceAnaB = "Ovar";

        String nameMother = "Joana";
        LocalDate birthdateMother = LocalDate.of(1987, 01, 11);
        String emailMother = "joana@gmail.com";
        Ledger ledgerMother = Ledger.createLedger();
        LedgerID ledgerID_Mother = ledgerMother.getLedgerID();

        String nameFather = "João";
        LocalDate birthdateFather = LocalDate.of(1987, 01, 11);
        String emailFather = "joao@gmail.com";
        Ledger ledgerFather = Ledger.createLedger();
        LedgerID ledgerID_Father = ledgerFather.getLedgerID();

        String birthplace = "Braga";
        String street = "Rua do Carmo";
        String number = "4";
        String zipCode = "2562";
        String city = "Porto";
        String country = "Portugal";
        Address address = Address.createAddress(street, number, zipCode, city, country);


        Person personMother = Person.createPersonWithoutParents(emailMother, nameMother, birthdateMother, birthplace, address, ledgerID_Mother);
        Person personFather = Person.createPersonWithoutParents(emailFather, nameFather, birthdateFather, birthplace, address, ledgerID_Father);

        //Act
        Person personA = Person.createPersonWithParents(emailAnaA, nameAnaA, birthdateAnaA, personMother.getPersonID(), personFather.getPersonID(), birthplaceAnaA, address, ledgerID_Ana);
        Person personB = Person.createPersonWithParents(emailAnaB, nameAnaB, birthdateAnaB, personMother.getPersonID(), personFather.getPersonID(), birthplaceAnaB, address, ledgerID_Ana);

        //Assert
        assertNotEquals(personA, personB);
    }

    @Test
    @DisplayName("Equals Person - SadCase")
    public void verifyEquals_False_different_Address() {


        //Arrange
        String nameAnaA = "Ana";
        LocalDate birthdateAnaA = LocalDate.of(1985, 8, 19);
        String emailAnaA = "ana@gmail.com";
        Ledger ledgerAna = Ledger.createLedger();
        LedgerID ledgerID_Ana = ledgerAna.getLedgerID();
        String birthplaceAnaA = "Cuba";

        String nameAnaB = "Ana";
        LocalDate birthdateAnaB = LocalDate.of(1985, 8, 19);
        String emailAnaB = "ana@gmail.com";
        String birthplaceAnaB = "Cuba";

        String nameMother = "Joana";
        LocalDate birthdateMother = LocalDate.of(1987, 01, 11);
        String emailMother = "joana@gmail.com";
        Ledger ledgerMother = Ledger.createLedger();
        LedgerID ledgerID_Mother = ledgerMother.getLedgerID();

        String nameFather = "João";
        LocalDate birthdateFather = LocalDate.of(1987, 01, 11);
        String emailFather = "joao@gmail.com";
        Ledger ledgerFather = Ledger.createLedger();
        LedgerID ledgerID_Father = ledgerFather.getLedgerID();

        String birthplace = "Braga";
        String street = "Rua do Carmo";
        String number = "4";
        String zipCode = "2562";
        String city = "Porto";
        String country = "Portugal";
        Address address = Address.createAddress(street, number, zipCode, city, country);

        String streetB = "rua da saudade";
        String doorNumberB = "12";
        String postCodeB = "4000-121";
        String cityB = "Porto";
        String countryB = "Portugal";
        Address addressB = Address.createAddress(streetB, doorNumberB, postCodeB, cityB, countryB);

        Person personMother = Person.createPersonWithoutParents(emailMother, nameMother, birthdateMother, birthplace, address, ledgerID_Mother);
        Person personFather = Person.createPersonWithoutParents(emailFather, nameFather, birthdateFather, birthplace, address, ledgerID_Father);

        //Act
        Person personA = Person.createPersonWithParents(emailAnaA, nameAnaA, birthdateAnaA, personMother.getPersonID(), personFather.getPersonID(), birthplaceAnaA, address, ledgerID_Ana);
        Person personB = Person.createPersonWithParents(emailAnaB, nameAnaB, birthdateAnaB, personMother.getPersonID(), personFather.getPersonID(), birthplaceAnaB, addressB, ledgerID_Ana);

        //Assert
        assertNotEquals(personA, personB);
    }


    @Test
    public void verifyEquals_false_different_father() {

        //Arrange
        String nameAnaA = "Ana";
        LocalDate birthdateAnaA = LocalDate.of(1985, 8, 19);
        String emailAnaA = "ana@gmail.com";
        Ledger ledgerAna = Ledger.createLedger();
        LedgerID ledgerID_Ana = ledgerAna.getLedgerID();
        String birthplaceAnaA = "Cuba";

        String nameAnaB = "Ana";
        LocalDate birthdateAnaB = LocalDate.of(1985, 8, 19);
        String emailAnaB = "ana@gmail.com";
        String birthplaceAnaB = "Cuba";

        String nameMother = "Joana";
        LocalDate birthdateMother = LocalDate.of(1987, 01, 11);
        String emailMother = "joana@gmail.com";
        Ledger ledgerMother = Ledger.createLedger();
        LedgerID ledgerID_Mother = ledgerMother.getLedgerID();

        String nameFatherA = "João";
        LocalDate birthdateFatherA = LocalDate.of(1987, 01, 11);
        String emailFatherA = "joao@gmail.com";
        Ledger ledgerFatherA = Ledger.createLedger();
        LedgerID ledgerID_FatherA = ledgerFatherA.getLedgerID();

        String nameFatherB = "Julio";
        LocalDate birthdateFatherB = LocalDate.of(1987, 01, 11);
        String emailFatherB = "julio@gmail.com";
        Ledger ledgerFatherB = Ledger.createLedger();
        LedgerID ledgerID_FatherB = ledgerFatherB.getLedgerID();

        String birthplace = "Braga";
        String street = "Rua do Carmo";
        String number = "4";
        String zipCode = "2562";
        String city = "Porto";
        String country = "Portugal";
        Address address = Address.createAddress(street, number, zipCode, city, country);


        Person personMother = Person.createPersonWithoutParents(emailMother, nameMother, birthdateMother, birthplace, address, ledgerID_Mother);
        Person personFatherA = Person.createPersonWithoutParents(emailFatherA, nameFatherA, birthdateFatherA, birthplace, address, ledgerID_FatherA);
        Person personFatherB = Person.createPersonWithoutParents(emailFatherB, nameFatherB, birthdateFatherB, birthplace, address, ledgerID_FatherB);

        //Act
        Person personA = Person.createPersonWithParents(emailAnaA, nameAnaA, birthdateAnaA, personMother.getPersonID(), personFatherA.getPersonID(), birthplaceAnaA, address, ledgerID_Ana);
        Person personB = Person.createPersonWithParents(emailAnaB, nameAnaB, birthdateAnaB, personMother.getPersonID(), personFatherB.getPersonID(), birthplaceAnaB, address, ledgerID_Ana);

        //Assert
        assertNotEquals(personA, personB);
    }

    @Test
    public void verifyEquals_truefather_Null() {
        String nameAnaA = "Ana";
        LocalDate birthdateAnaA = LocalDate.of(1985, 8, 19);
        String emailAnaA = "ana@gmail.com";
        Ledger ledgerAna = Ledger.createLedger();
        LedgerID ledgerID_Ana = ledgerAna.getLedgerID();
        String birthplaceAnaA = "Cuba";

        String nameAnaB = "Ana";
        LocalDate birthdateAnaB = LocalDate.of(1985, 8, 19);
        String emailAnaB = "ana@gmail.com";
        String birthplaceAnaB = "Cuba";

        String nameMother = "Joana";
        LocalDate birthdateMother = LocalDate.of(1987, 01, 11);
        String emailMother = "joana@gmail.com";
        Ledger ledgerMother = Ledger.createLedger();
        LedgerID ledgerID_Mother = ledgerMother.getLedgerID();

        String birthplace = "Braga";
        String street = "Rua do Carmo";
        String number = "4";
        String zipCode = "2562";
        String city = "Porto";
        String country = "Portugal";
        Address address = Address.createAddress(street, number, zipCode, city, country);


        Person personMother = Person.createPersonWithoutParents(emailMother, nameMother, birthdateMother, birthplace, address, ledgerID_Mother);

        //Act
        Person personA = Person.createPersonWithParents(emailAnaA, nameAnaA, birthdateAnaA, personMother.getPersonID(), null, birthplaceAnaA, address, ledgerID_Ana);
        Person personB = Person.createPersonWithParents(emailAnaB, nameAnaB, birthdateAnaB, personMother.getPersonID(), null, birthplaceAnaB, address, ledgerID_Ana);

        //Assert
        assertEquals(personA, personB);
    }

    @Test
    public void verifyEquals_false_personAfather_Null() {
        //Arrange
        String nameAnaA = "Ana";
        LocalDate birthdateAnaA = LocalDate.of(1985, 8, 19);
        String emailAnaA = "ana@gmail.com";
        Ledger ledgerAna = Ledger.createLedger();
        LedgerID ledgerID_Ana = ledgerAna.getLedgerID();
        String birthplaceAnaA = "Cuba";

        String nameAnaB = "Ana";
        LocalDate birthdateAnaB = LocalDate.of(1985, 8, 19);
        String emailAnaB = "ana@gmail.com";
        String birthplaceAnaB = "Cuba";

        String nameMother = "Joana";
        LocalDate birthdateMother = LocalDate.of(1987, 01, 11);
        String emailMother = "joana@gmail.com";
        Ledger ledgerMother = Ledger.createLedger();
        LedgerID ledgerID_Mother = ledgerMother.getLedgerID();

        String nameFatherB = "Julio";
        LocalDate birthdateFatherB = LocalDate.of(1987, 01, 11);
        String emailFatherB = "julio@gmail.com";
        Ledger ledgerFatherB = Ledger.createLedger();
        LedgerID ledgerID_FatherB = ledgerFatherB.getLedgerID();

        String birthplace = "Braga";
        String street = "Rua do Carmo";
        String number = "4";
        String zipCode = "2562";
        String city = "Porto";
        String country = "Portugal";
        Address address = Address.createAddress(street, number, zipCode, city, country);


        Person personMother = Person.createPersonWithoutParents(emailMother, nameMother, birthdateMother, birthplace, address, ledgerID_Mother);
        Person personFatherB = Person.createPersonWithoutParents(emailFatherB, nameFatherB, birthdateFatherB, birthplace, address, ledgerID_FatherB);

        //Act
        Person personA = Person.createPersonWithParents(emailAnaA, nameAnaA, birthdateAnaA, personMother.getPersonID(), null, birthplaceAnaA, address, ledgerID_Ana);
        Person personB = Person.createPersonWithParents(emailAnaB, nameAnaB, birthdateAnaB, personMother.getPersonID(), personFatherB.getPersonID(), birthplaceAnaB, address, ledgerID_Ana);

        //Assert
        assertNotEquals(personA, personB);
    }

    @Test
    public void verifyquals_false_personBfather_Null() {

        //Arrange
        String nameAnaA = "Ana";
        LocalDate birthdateAnaA = LocalDate.of(1985, 8, 19);
        String emailAnaA = "ana@gmail.com";
        Ledger ledgerAna = Ledger.createLedger();
        LedgerID ledgerID_Ana = ledgerAna.getLedgerID();
        String birthplaceAnaA = "Cuba";

        String nameAnaB = "Ana";
        LocalDate birthdateAnaB = LocalDate.of(1985, 8, 19);
        String emailAnaB = "ana@gmail.com";
        String birthplaceAnaB = "Cuba";

        String nameMother = "Joana";
        LocalDate birthdateMother = LocalDate.of(1987, 01, 11);
        String emailMother = "joana@gmail.com";
        Ledger ledgerMother = Ledger.createLedger();
        LedgerID ledgerID_Mother = ledgerMother.getLedgerID();

        String nameFatherA = "João";
        LocalDate birthdateFatherA = LocalDate.of(1987, 01, 11);
        String emailFatherA = "joao@gmail.com";
        Ledger ledgerFatherA = Ledger.createLedger();
        LedgerID ledgerID_FatherA = ledgerFatherA.getLedgerID();

        String birthplace = "Braga";
        String street = "Rua do Carmo";
        String number = "4";
        String zipCode = "2562";
        String city = "Porto";
        String country = "Portugal";
        Address address = Address.createAddress(street, number, zipCode, city, country);


        Person personMother = Person.createPersonWithoutParents(emailMother, nameMother, birthdateMother, birthplace, address, ledgerID_Mother);
        Person personFatherA = Person.createPersonWithoutParents(emailFatherA, nameFatherA, birthdateFatherA, birthplace, address, ledgerID_FatherA);

        //Act
        Person personA = Person.createPersonWithParents(emailAnaA, nameAnaA, birthdateAnaA, personMother.getPersonID(), personFatherA.getPersonID(), birthplaceAnaA, address, ledgerID_Ana);
        Person personB = Person.createPersonWithParents(emailAnaB, nameAnaB, birthdateAnaB, personMother.getPersonID(), null, birthplaceAnaB, address, ledgerID_Ana);

        //Assert
        assertNotEquals(personA, personB);
    }

    @Test
    public void verifyEquals_false_different_Mother() {

        //Arrange
        String nameAnaA = "Ana";
        LocalDate birthdateAnaA = LocalDate.of(1985, 8, 19);
        String emailAnaA = "ana@gmail.com";
        Ledger ledgerAna = Ledger.createLedger();
        LedgerID ledgerID_Ana = ledgerAna.getLedgerID();
        String birthplaceAnaA = "Cuba";

        String nameAnaB = "Ana";
        LocalDate birthdateAnaB = LocalDate.of(1985, 8, 19);
        String emailAnaB = "ana@gmail.com";
        String birthplaceAnaB = "Cuba";

        String nameMotherA = "Joana";
        LocalDate birthdateMotherA = LocalDate.of(1987, 01, 11);
        String emailMotherA = "joana@gmail.com";
        Ledger ledgerMotherA = Ledger.createLedger();
        LedgerID ledgerID_MotherA = ledgerMotherA.getLedgerID();

        String nameMotherB = "Julia";
        LocalDate birthdateMotherB = LocalDate.of(1987, 01, 11);
        String emailMotherB = "julia@gmail.com";
        Ledger ledgerMotherB = Ledger.createLedger();
        LedgerID ledgerID_MotherB = ledgerMotherB.getLedgerID();

        String nameFather = "João";
        LocalDate birthdateFather = LocalDate.of(1987, 01, 11);
        String emailFather = "joao@gmail.com";
        Ledger ledgerFather = Ledger.createLedger();
        LedgerID ledgerID_Father = ledgerFather.getLedgerID();

        String birthplace = "Braga";
        String street = "Rua do Carmo";
        String number = "4";
        String zipCode = "2562";
        String city = "Porto";
        String country = "Portugal";
        Address address = Address.createAddress(street, number, zipCode, city, country);


        Person personMotherA = Person.createPersonWithoutParents(emailMotherA, nameMotherA, birthdateMotherA, birthplace, address, ledgerID_MotherA);
        Person personMotherB = Person.createPersonWithoutParents(emailMotherB, nameMotherB, birthdateMotherB, birthplace, address, ledgerID_MotherB);
        Person personFather = Person.createPersonWithoutParents(emailFather, nameFather, birthdateFather, birthplace, address, ledgerID_Father);

        //Act
        Person personA = Person.createPersonWithParents(emailAnaA, nameAnaA, birthdateAnaA, personMotherA.getPersonID(), personFather.getPersonID(), birthplaceAnaA, address, ledgerID_Ana);
        Person personB = Person.createPersonWithParents(emailAnaB, nameAnaB, birthdateAnaB, personMotherB.getPersonID(), personFather.getPersonID(), birthplaceAnaB, address, ledgerID_Ana);

        //Assert
        assertNotEquals(personA, personB);
    }

    @Test
    public void verifyEquals_true_mother_Null() {
        //Arrange
        String nameAnaA = "Ana";
        LocalDate birthdateAnaA = LocalDate.of(1985, 8, 19);
        String emailAnaA = "ana@gmail.com";
        Ledger ledgerAna = Ledger.createLedger();
        LedgerID ledgerID_Ana = ledgerAna.getLedgerID();
        String birthplaceAnaA = "Cuba";

        String nameAnaB = "Ana";
        LocalDate birthdateAnaB = LocalDate.of(1985, 8, 19);
        String emailAnaB = "ana@gmail.com";
        String birthplaceAnaB = "Cuba";

        String nameFather = "João";
        LocalDate birthdateFather = LocalDate.of(1987, 01, 11);
        String emailFather = "joao@gmail.com";
        Ledger ledgerFather = Ledger.createLedger();
        LedgerID ledgerID_Father = ledgerFather.getLedgerID();

        String birthplace = "Braga";
        String street = "Rua do Carmo";
        String number = "4";
        String zipCode = "2562";
        String city = "Porto";
        String country = "Portugal";
        Address address = Address.createAddress(street, number, zipCode, city, country);

        Person personFather = Person.createPersonWithoutParents(emailFather, nameFather, birthdateFather, birthplace, address, ledgerID_Father);

        //Act
        Person personA = Person.createPersonWithParents(emailAnaA, nameAnaA, birthdateAnaA, null, personFather.getPersonID(), birthplaceAnaA, address, ledgerID_Ana);
        Person personB = Person.createPersonWithParents(emailAnaB, nameAnaB, birthdateAnaB, null, personFather.getPersonID(), birthplaceAnaB, address, ledgerID_Ana);

        //Assert
        assertEquals(personA, personB);
    }

    @Test
    public void verifyEquals_false_motherA_Null() {
        //Arrange
        String nameAnaA = "Ana";
        LocalDate birthdateAnaA = LocalDate.of(1985, 8, 19);
        String emailAnaA = "ana@gmail.com";
        Ledger ledgerAna = Ledger.createLedger();
        LedgerID ledgerID_Ana = ledgerAna.getLedgerID();
        String birthplaceAnaA = "Cuba";

        String nameAnaB = "Ana";
        LocalDate birthdateAnaB = LocalDate.of(1985, 8, 19);
        String emailAnaB = "ana@gmail.com";
        String birthplaceAnaB = "Cuba";

        String nameMotherB = "Julia";
        LocalDate birthdateMotherB = LocalDate.of(1987, 01, 11);
        String emailMotherB = "julia@gmail.com";
        Ledger ledgerMotherB = Ledger.createLedger();
        LedgerID ledgerID_MotherB = ledgerMotherB.getLedgerID();

        String nameFather = "João";
        LocalDate birthdateFather = LocalDate.of(1987, 01, 11);
        String emailFather = "joao@gmail.com";
        Ledger ledgerFather = Ledger.createLedger();
        LedgerID ledgerID_Father = ledgerFather.getLedgerID();

        String birthplace = "Braga";
        String street = "Rua do Carmo";
        String number = "4";
        String zipCode = "2562";
        String city = "Porto";
        String country = "Portugal";
        Address address = Address.createAddress(street, number, zipCode, city, country);

        Person personMotherB = Person.createPersonWithoutParents(emailMotherB, nameMotherB, birthdateMotherB, birthplace, address, ledgerID_MotherB);
        Person personFather = Person.createPersonWithoutParents(emailFather, nameFather, birthdateFather, birthplace, address, ledgerID_Father);

        //Act
        Person personA = Person.createPersonWithParents(emailAnaA, nameAnaA, birthdateAnaA, null, personFather.getPersonID(), birthplaceAnaA, address, ledgerID_Ana);
        Person personB = Person.createPersonWithParents(emailAnaB, nameAnaB, birthdateAnaB, personMotherB.getPersonID(), personFather.getPersonID(), birthplaceAnaB, address, ledgerID_Ana);

        //Assert
        assertNotEquals(personA, personB);
    }

    @Test
    public void verifyEquals_false_motherB_Null() {
        //Arrange
        String nameAnaA = "Ana";
        LocalDate birthdateAnaA = LocalDate.of(1985, 8, 19);
        String emailAnaA = "ana@gmail.com";
        Ledger ledgerAna = Ledger.createLedger();
        LedgerID ledgerID_Ana = ledgerAna.getLedgerID();
        String birthplaceAnaA = "Cuba";

        String nameAnaB = "Ana";
        LocalDate birthdateAnaB = LocalDate.of(1985, 8, 19);
        String emailAnaB = "ana@gmail.com";
        String birthplaceAnaB = "Cuba";

        String nameMotherA = "Joana";
        LocalDate birthdateMotherA = LocalDate.of(1987, 01, 11);
        String emailMotherA = "joana@gmail.com";
        Ledger ledgerMotherA = Ledger.createLedger();
        LedgerID ledgerID_MotherA = ledgerMotherA.getLedgerID();

        String nameFather = "João";
        LocalDate birthdateFather = LocalDate.of(1987, 01, 11);
        String emailFather = "joao@gmail.com";
        Ledger ledgerFather = Ledger.createLedger();
        LedgerID ledgerID_Father = ledgerFather.getLedgerID();

        String birthplace = "Braga";
        String street = "Rua do Carmo";
        String number = "4";
        String zipCode = "2562";
        String city = "Porto";
        String country = "Portugal";
        Address address = Address.createAddress(street, number, zipCode, city, country);


        Person personMotherA = Person.createPersonWithoutParents(emailMotherA, nameMotherA, birthdateMotherA, birthplace, address, ledgerID_MotherA);
        Person personFather = Person.createPersonWithoutParents(emailFather, nameFather, birthdateFather, birthplace, address, ledgerID_Father);

        //Act
        Person personA = Person.createPersonWithParents(emailAnaA, nameAnaA, birthdateAnaA, personMotherA.getPersonID(), personFather.getPersonID(), birthplaceAnaA, address, ledgerID_Ana);
        Person personB = Person.createPersonWithParents(emailAnaB, nameAnaB, birthdateAnaB, null, personFather.getPersonID(), birthplaceAnaB, address, ledgerID_Ana);

        //Assert
        assertNotEquals(personA, personB);
    }


    //Get ledger - Create and add Transaction Without date

    @Test
    @DisplayName("Get Ledger | Person")
    public void getLedgerTransactionWithoutDate() {

        //Arrange

        //Arrange of People
        String nameMaria = "Maria";
        LocalDate birthdateMaria = LocalDate.of(1988, 9, 21);
        String namePedro = "Pedro";
        LocalDate birthdatePedro = LocalDate.of(1988, 9, 21);
        String nameJoana = "Joana";
        LocalDate birthdateJoana = LocalDate.of(1988, 9, 21);

        Person personMother = new Person(nameMaria, birthdateMaria);
        Person personFather = new Person(namePedro, birthdatePedro);
        Person personJoana = new Person(nameJoana, birthdateJoana, personMother, personFather);


        //Arrange of Category
        String categoryDenomination = "Health";
        Category health = new Category(categoryDenomination);

        //Arrange of Transaction
        String transactionType = "Debit";
        String transactionDescription = "Teeth repair";
        double transactionAmount = 120.00;


        //Arrange of Debit Account
        Description debAccountDescription = new Description("Account of Joana");
        Denomination debAccountDenomination = new Denomination("BPI_Joana");
        Account debitAccount = new Account(debAccountDescription, debAccountDenomination);


        //Arrange of Credit Account
        Description credAccountDescription = new Description("DentalLife Account");
        Denomination credAccountDenomination = new Denomination("DentalLife");
        Account creditAccount = new Account(credAccountDescription, credAccountDenomination);

        //Create transaction
        Transaction transactionHealth = new Transaction(health, transactionType, transactionDescription, transactionAmount, debitAccount, creditAccount);

        //Act

        //Person
        boolean result = personJoana.createAndAddTransaction(health, transactionType, transactionDescription, transactionAmount, debitAccount, creditAccount);

        //Assert

        assertTrue(result);
        assertEquals(transactionHealth, personJoana.getLedger().getRecords().get(0));

    }

    //Get ledger - Create and add Transaction With date

    @Test
    @DisplayName("Get Ledger | Person")
    public void getLedgerTransactionWithDate() {

        //Arrange

        //Arrange of People
        String nameMaria = "Maria";
        LocalDate birthdateMaria = LocalDate.of(1988, 9, 21);
        String namePedro = "Pedro";
        LocalDate birthdatePedro = LocalDate.of(1988, 9, 21);
        String nameJoana = "Joana";
        LocalDate birthdateJoana = LocalDate.of(1988, 9, 21);

        Person personMother = new Person(nameMaria, birthdateMaria);
        Person personFather = new Person(namePedro, birthdatePedro);
        Person personJoana = new Person(nameJoana, birthdateJoana, personMother, personFather);


        //Arrange of Category
        String categoryDenomination = "Health";
        Category health = new Category(categoryDenomination);

        //Arrange of Transaction
        String transactionType = "Debit";
        String transactionDescription = "Teeth repair";
        double transactionAmount = 120.00;
        LocalDate transactionDate = LocalDate.of(2020, 1, 27);


        //Arrange of Debit Account
        Description debAccountDescription = new Description("Account of Joana");
        Denomination debAccountDenomination = new Denomination("BPI_Joana");
        Account debitAccount = new Account(debAccountDescription, debAccountDenomination);


        //Arrange of Credit Account
        Description credAccountDescription = new Description("DentalLife Account");
        Denomination credAccountDenomination = new Denomination("DentalLife");
        Account creditAccount = new Account(credAccountDescription, credAccountDenomination);

        //Create transaction
        Transaction transactionHealth = new Transaction(health, transactionType, transactionDescription, transactionAmount, transactionDate, debitAccount, creditAccount);

        //Act

        //Person
        boolean result = personJoana.createAndAddTransactionWithDate(health, transactionType, transactionDescription, transactionAmount, transactionDate, debitAccount, creditAccount);

        //Assert

        assertTrue(result);
        assertEquals(transactionHealth, personJoana.getLedger().getRecords().get(0));

    }

        @Test
    @DisplayName("verify my listOfGroups")
    public void verifyMyListOfGroups() {

        // Arrange people
        Person personMaria = new Person("Maria", LocalDate.of(1998, 9, 21));
        Person personManuel = new Person("Manuel", LocalDate.of(2000, 07, 17));
        Person personMiguel = new Person("Miguel", LocalDate.of(1999, 07, 17));

        //Arrange group
        GroupList expectedGroups = new GroupList();

        ArrayList<Person> footballOnMondayPeopleInCharge = new ArrayList<Person>();
        footballOnMondayPeopleInCharge.add(personMaria);

        ArrayList<Person> footballOnMondayMembers = new ArrayList<Person>();
        footballOnMondayMembers.add(personManuel);
        footballOnMondayMembers.add(personMiguel);

        Denomination footballDenomination = new Denomination("Football event");
        GroupID footballID = new GroupID(footballDenomination);
        Description footballDescription = new Description("Event - Play football at monday at 7pm");
        DateOfCreation footballDate = new DateOfCreation(LocalDate.of(2016, 5, 3));

        ArrayList<Person> tennisAtTuesdayPeopleInCharge = new ArrayList<Person>();
        tennisAtTuesdayPeopleInCharge.add(personMaria);
        tennisAtTuesdayPeopleInCharge.add(personManuel);

        ArrayList<Person> tennisAtTuesdayMembers = new ArrayList<Person>();
        tennisAtTuesdayMembers.add(personMiguel);

        Denomination tennisDenomination = new Denomination("Tennis event");
        GroupID tennisID = new GroupID(tennisDenomination);
        Description tennisDescription = new Description("Event - Play tennis at tuesday at 8pm");
        DateOfCreation tennisDate = new DateOfCreation(LocalDate.of(2016, 5, 3));


        // Act
        Group groupFootball = new Group(footballID, personMaria, footballDescription, footballDate);
        Group groupTennis = new Group(tennisID, personMaria, tennisDescription, tennisDate);

        personMaria.addGroup(groupFootball);
        personMaria.addGroup(groupTennis);

        expectedGroups.addGroup(groupFootball);
        expectedGroups.addGroup(groupTennis);

        // Assert
        assertEquals(personMaria.listOfMyGroups(), expectedGroups);
    }


    @Test
    @DisplayName("Test For createGroup()")
    public void createGroup() {

        //Arrange
        String name = "john";
        LocalDate birthdate = LocalDate.of(1993, 03, 15);
        String birthplace = "Porto";
        Address address = new Address("Rua das Flores", "190", "4415", "Porto", "Portugal");
        String description = "Food";
        String type = "Credit";
        Double amount = 100.02;

        Person person = new Person(name, birthdate, null, null, birthplace, address);

        Denomination footballDenomination = new Denomination("Football event");
        GroupID footballID = new GroupID(footballDenomination);
        Description footballDescription = new Description("Event - Play football at monday at 7pm");
        DateOfCreation footballDate = new DateOfCreation(LocalDate.of(2016, 5, 3));

        GroupList expectedGroupList = new GroupList();
        expectedGroupList.createGroupAsPeopleInCharge(footballID, person, footballDescription, footballDate);

        //Act

        boolean result = person.createGroupAsPeopleInCharge(footballID, person, footballDescription, footballDate);

        //Assert
        assertEquals(true, result);

    }


    @Test
    @DisplayName("Verify if person can be create a group with person in charge | Happy Case")
    void createGroupAsPersonInCharge() {

        // Arrange people
        Person personMaria = new Person("Maria", LocalDate.of(1998, 9, 21));

        //Arrange group list
        GroupList systemGroupsMaria = new GroupList();

        //Group1
        Denomination firstGroupDenomination = new Denomination("Friends");
        GroupID firstFGroupID = new GroupID(firstGroupDenomination);
        GroupID firstGroupID = new GroupID(firstGroupDenomination);
        Description firstGroupDescription = new Description("Old friends from school");
        DateOfCreation firstGroupDate = new DateOfCreation(LocalDate.of(2019, 12, 18));

        // Act
        personMaria.createGroupAsPeopleInCharge(firstGroupID, personMaria, firstGroupDescription, firstGroupDate);
        GroupList groupsOfMaria = personMaria.listOfMyGroups();

        //Expected
        systemGroupsMaria.createGroupAsPeopleInCharge(firstGroupID, personMaria, firstGroupDescription, firstGroupDate);
        GroupList expectedGroup = systemGroupsMaria;
        // Assert
        assertEquals(expectedGroup, groupsOfMaria);
    }

    @Test
    @DisplayName("Verify if person can be create a group with person in charge (different groups same person in charge) )| Sad Case")
    void createGroupAsPersonInChargeDifferentGroup() {

        // Arrange people
        Person personMaria = new Person("Maria", LocalDate.of(1998, 9, 21));

        //Arrange group list
        GroupList systemGroupsMaria = new GroupList();

        //Group1
        Denomination firstGroupDenomination = new Denomination("Friends");
        GroupID firstFGroupID = new GroupID(firstGroupDenomination);
        Description firstGroupDescription = new Description("Old friends from school");
        DateOfCreation firstGroupDate = new DateOfCreation(LocalDate.of(2019, 12, 18));

        //Group2

        Denomination secondGroupDenomination = new Denomination("Programming Group");
        GroupID secondGroupID = new GroupID(secondGroupDenomination);
        Description secondGroupDescription = new Description("Single prospects in programming");
        DateOfCreation secondGroupDate = new DateOfCreation(LocalDate.of(2019, 9, 16));

        // Act
        personMaria.createGroupAsPeopleInCharge(firstFGroupID, personMaria, firstGroupDescription, firstGroupDate);
        GroupList groupsOfMaria = personMaria.listOfMyGroups();

        //Expected
        systemGroupsMaria.createGroupAsPeopleInCharge(secondGroupID, personMaria, secondGroupDescription, secondGroupDate);
        GroupList expectedGroup = systemGroupsMaria;

        // Assert
        assertNotEquals(expectedGroup, groupsOfMaria);
    }

    @Test
    @DisplayName("Verify if person can be create a group with person in charge (different groups same person in charge) )| Sad Case")
    void createGroupAsPersonInChargeDifferentGroupAndPersonInCharge() {

        // Arrange people
        Person personMaria = new Person("Maria", LocalDate.of(1998, 9, 21));

        // Arrange another people
        Person personAquaman = new Person("Aquaman", LocalDate.of(1988, 2, 12));

        //Arrange group list
        GroupList systemGroupsMaria = new GroupList();

        //Group1
        Denomination firstGroupDenomination = new Denomination("Friends");
        GroupID firstFGroupID = new GroupID(firstGroupDenomination);
        Description firstGroupDescription = new Description("Old friends from school");
        DateOfCreation firstGroupDate = new DateOfCreation(LocalDate.of(2019, 12, 18));

        //Group2

        Denomination secondGroupDenomination = new Denomination("Programming Group");
        GroupID secondGroupID = new GroupID(secondGroupDenomination);
        Description secondGroupDescription = new Description("Single prospects in programming");
        DateOfCreation secondGroupDate = new DateOfCreation(LocalDate.of(2019, 9, 16));

        // Act
        personMaria.createGroupAsPeopleInCharge(firstFGroupID, personMaria, firstGroupDescription, firstGroupDate);
        GroupList groupsOfMaria = personMaria.listOfMyGroups();

        //Expected
        systemGroupsMaria.createGroupAsPeopleInCharge(secondGroupID, personAquaman, secondGroupDescription, secondGroupDate);
        GroupList expectedGroup = systemGroupsMaria;
        // Assert
        assertNotEquals(expectedGroup, groupsOfMaria);
    }

    //Get Records between 2 dates

    @Test
    @DisplayName("Get Records between 2 dates | Happy Case")
    public void testGetRecordBetween2Dates() {

        //Arrange of Persons
        Person personHenrique = new Person("Henrique", LocalDate.of(1987, 11, 19));

        //Arrange of Category
        String categoryDenomination = "Salary";
        Category categorySalary = new Category(categoryDenomination);

        //Arrange of Debit Account
        Description debAccountDescription = new Description("EmployerSA Account");
        Denomination debAccountDenomination = new Denomination("EmployerSA");

        Account accountDebit = new Account(debAccountDescription, debAccountDenomination);

        //Arrange of Credit Account
        Description credAccountDescription = new Description("Henrique Salary Account");
        Denomination credAccountDenomination = new Denomination("Salary");

        Account accountCredit = new Account(credAccountDescription, credAccountDenomination);

        //Arrange of Transaction

        //Transaction A
        String transactionType = "Credit";
        String transactionDescription = "Dez 2019 Salary";
        double transactionAmount = 1000.00;
        LocalDate date = LocalDate.of(2019, 12, 25);

        //TransactionB
        String transactionTypeB = "Credit";
        String transactionDescriptionB = "Jan 2020 Salary";
        double transactionAmountB = 1500.00;
        LocalDate dateB = LocalDate.of(2020, 01, 01);


        //Act

        boolean result = personHenrique.createAndAddTransactionWithDate(categorySalary, transactionType, transactionDescription, transactionAmount, date, accountDebit, accountCredit);
        boolean result1 = personHenrique.createAndAddTransactionWithDate(categorySalary, transactionTypeB, transactionDescriptionB, transactionAmountB, dateB, accountDebit, accountCredit);

        //Act
        ArrayList<Transaction> expectedRegists = new ArrayList<>();
        Transaction expectedTransaction1 = new Transaction(categorySalary, transactionType, transactionDescription, transactionAmount, date, accountDebit, accountCredit);
        Transaction expectedTransaction2 = new Transaction(categorySalary, transactionTypeB, transactionDescriptionB, transactionAmountB, dateB, accountDebit, accountCredit);
        expectedRegists.add(expectedTransaction1);
        expectedRegists.add(expectedTransaction2);

        ArrayList<Transaction> searchResult = personHenrique.getRecordsBetweenTwoDates(LocalDate.of(2019, 12, 24), LocalDate.of(2020, 01, 28));

        //Assert

        assertTrue(result);
        assertTrue(result1);
        assertEquals(expectedRegists, searchResult);
    }

    @Test
    @DisplayName("Get Records between 2 dates no Order | Happy Case")
    public void testGetRecordBetween2Dates_noOrder() {

        //Arrange of Persons
        Person personHenrique = new Person("Henrique", LocalDate.of(1987, 11, 19));

        //Arrange of Category
        String categoryDenomination = "Salary";
        Category categorySalary = new Category(categoryDenomination);

        //Arrange of Debit Account
        Description debAccountDescription = new Description("EmployerSA Account");
        Denomination debAccountDenomination = new Denomination("EmployerSA");

        Account accountDebit = new Account(debAccountDescription, debAccountDenomination);

        //Arrange of Credit Account
        Description credAccountDescription = new Description("Henrique Salary Account");
        Denomination credAccountDenomination = new Denomination("Salary");

        Account accountCredit = new Account(credAccountDescription, credAccountDenomination);

        //Arrange of Transaction

        //Transaction A
        String transactionType = "Credit";
        String transactionDescription = "Dez 2019 Salary";
        double transactionAmount = 1000.00;
        LocalDate date = LocalDate.of(2019, 12, 25);

        //TransactionB
        String transactionTypeB = "Credit";
        String transactionDescriptionB = "Jan 2020 Salary";
        double transactionAmountB = 1500.00;
        LocalDate dateB = LocalDate.of(2020, 01, 01);


        //Act

        boolean result = personHenrique.createAndAddTransactionWithDate(categorySalary, transactionType, transactionDescription, transactionAmount, date, accountDebit, accountCredit);
        boolean result1 = personHenrique.createAndAddTransactionWithDate(categorySalary, transactionTypeB, transactionDescriptionB, transactionAmountB, dateB, accountDebit, accountCredit);

        //Act
        ArrayList<Transaction> expectedRegists = new ArrayList<>();
        Transaction expectedTransaction1 = new Transaction(categorySalary, transactionType, transactionDescription, transactionAmount, date, accountDebit, accountCredit);
        Transaction expectedTransaction2 = new Transaction(categorySalary, transactionTypeB, transactionDescriptionB, transactionAmountB, dateB, accountDebit, accountCredit);
        expectedRegists.add(expectedTransaction1);
        expectedRegists.add(expectedTransaction2);

        ArrayList<Transaction> searchResult = personHenrique.getRecordsBetweenTwoDates(LocalDate.of(2020, 01, 31), LocalDate.of(2019, 01, 31));

        //Assert

        assertTrue(result);
        assertTrue(result1);
        assertEquals(expectedRegists, searchResult);
    }


    @Test
    @DisplayName("Get Amount between 2 dates | Happy Case")
    public void testGetAmountBetween2Dates() {

        // Arrange of Persons
        Person personMiguel = new Person("Miguel", LocalDate.of(2000, 11, 20));

        // Arrange of Category
        String categoryDenomination = "Salary";
        Category categorySalary = new Category(categoryDenomination);

        //Arrange of Debit Account
        Description debAccountDescription = new Description("EmployerSA Account");
        Denomination debAccountDenomination = new Denomination("EmployerSA");

        Account accountDebit = new Account(debAccountDescription, debAccountDenomination);

        //Arrange of Credit Account
        Description credAccountDescription = new Description("Henrique Salary Account");
        Denomination credAccountDenomination = new Denomination("Salary");

        Account accountCredit = new Account(credAccountDescription, credAccountDenomination);

        // Arrange of Transaction

        // Transaction A
        String transactionType = "Credit";
        String transactionDescription = "Dez 2019 Salary";
        double transactionAmount = 1000.00;
        LocalDate date = LocalDate.of(2019, 12, 25);

        // TransactionB
        String transactionTypeB = "Credit";
        String transactionDescriptionB = "Jan 2020 Salary";
        double transactionAmountB = 1500.00;
        LocalDate dateB = LocalDate.of(2020, 01, 01);


        // Act

        boolean result = personMiguel.createAndAddTransactionWithDate(categorySalary, transactionType, transactionDescription, transactionAmount, date, accountDebit, accountCredit);
        boolean result1 = personMiguel.createAndAddTransactionWithDate(categorySalary, transactionTypeB, transactionDescriptionB, transactionAmountB, dateB, accountDebit, accountCredit);

        //Act

        Double expectedAmount = 2500.00;

        Double amount = personMiguel.getAmountBetweenTwoDates(LocalDate.of(2019, 12, 24), LocalDate.of(2020, 01, 28));

        //Assert

        assertTrue(result);
        assertTrue(result1);
        assertEquals(expectedAmount, amount);
    }


    @Test
    @DisplayName("Get Amount between 2 dates | Happy Case")
    public void testGetAmountBetween2Dates_noOrderDate() {

        // Arrange of Persons
        Person personMiguel = new Person("Miguel", LocalDate.of(20001, 10, 25));

        // Arrange of Category
        String categoryDenomination = "Salary";
        Category categorySalary = new Category(categoryDenomination);

        //Arrange of Debit Account
        Description debAccountDescription = new Description("EmployerSA Account");
        Denomination debAccountDenomination = new Denomination("EmployerSA");

        Account accountDebit = new Account(debAccountDescription, debAccountDenomination);

        //Arrange of Credit Account
        Description credAccountDescription = new Description("Henrique Salary Account");
        Denomination credAccountDenomination = new Denomination("Salary");

        Account accountCredit = new Account(credAccountDescription, credAccountDenomination);

        // Arrange of Transaction

        // Transaction A
        String transactionType = "Credit";
        String transactionDescription = "Dez 2019 Salary";
        double transactionAmount = 1000.00;
        LocalDate date = LocalDate.of(2019, 12, 25);

        // TransactionB
        String transactionTypeB = "Credit";
        String transactionDescriptionB = "Jan 2020 Salary";
        double transactionAmountB = 1500.00;
        LocalDate dateB = LocalDate.of(2020, 01, 01);


        // Act

        boolean result = personMiguel.createAndAddTransactionWithDate(categorySalary, transactionType, transactionDescription, transactionAmount, date, accountDebit, accountCredit);
        boolean result1 = personMiguel.createAndAddTransactionWithDate(categorySalary, transactionTypeB, transactionDescriptionB, transactionAmountB, dateB, accountDebit, accountCredit);

        // Act

        Double expectedAmount = 2500.00;

        Double amount = personMiguel.getAmountBetweenTwoDates(LocalDate.of(2020, 01, 31), LocalDate.of(2019, 01, 01));

        // Assert

        assertTrue(result);
        assertTrue(result1);
        assertEquals(expectedAmount, amount);
    }

    @Test
    @DisplayName("Get transactions of all groups of one person")
    public void getPersonGroupsRecordsBetweenTwoDatesBetweenTwoDates() {

        //Arrange person
        Person personMaria = new Person("Maria", LocalDate.of(1998, 9, 21));

        //Arrange category
        Category categorySalary = new Category("Salary");
        Category categoryFun = new Category("Salary");

        //Arrange Account

        //Arrange Account Nr.1

        Description debAccountDescription = new Description("EmployerSA Account");
        Denomination debAccountDenomination = new Denomination("EmployerSA");
        Account accountDebit = new Account(debAccountDescription, debAccountDenomination);


        //Arrange Account Nr.2

        Description debAccountDescriptionA = new Description("John Allowance");
        Denomination debAccountDenominationA = new Denomination("Allowance Money");
        Account accountCredit = new Account(debAccountDescriptionA, debAccountDenominationA);

        //Arrange of Transaction

        //Transaction A
        String transactionType = "Credit";
        String transactionDescription = "Dez 2019 Salary";
        double transactionAmount = 1000.00;
        LocalDate date = LocalDate.of(2019, 12, 25);

        //TransactionB
        String transactionTypeB = "Debit";
        String transactionDescriptionB = "Friends Dinner of the month";
        double transactionAmountB = 15.00;
        LocalDate dateB = LocalDate.of(2020, 01, 01);

        // Arrange groups

        //Group 1
        Denomination denomination = new Denomination("Runners");
        GroupID groupID = new GroupID(denomination);
        Description description = new Description("People who come together to run");
        DateOfCreation firstGroupDate = new DateOfCreation(LocalDate.of(2019, 12, 18));

        personMaria.createGroupAsPeopleInCharge(groupID, personMaria, description, firstGroupDate);
        Group groupFamilyMaria = personMaria.listOfMyGroups().getListOfGroups().get(0);
        groupFamilyMaria.createAndAddTransactionWithDate(categorySalary, transactionType, transactionDescription, transactionAmount, date, accountDebit, accountCredit);

        //Group2

        Denomination denominationA = new Denomination("Friends");
        GroupID groupIDA = new GroupID(denomination);
        Description descriptionA = new Description("Old friends from school");
        DateOfCreation secondGroupDate = new DateOfCreation(LocalDate.of(1998, 9, 20));

        personMaria.createGroupAsPeopleInCharge(groupIDA, personMaria, descriptionA, secondGroupDate);
        Group groupFriendsMaria = personMaria.listOfMyGroups().getListOfGroups().get(1);
        groupFriendsMaria.createAndAddTransactionWithDate(categoryFun, transactionTypeB, transactionDescriptionB, transactionAmountB, dateB, accountDebit, accountCredit);

        //Expected
        List<Transaction> expectedRegists = new ArrayList<>();
        Transaction expectedTransaction1 = new Transaction(categorySalary, transactionType, transactionDescription, transactionAmount, date, accountDebit, accountCredit);
        Transaction expectedTransaction2 = new Transaction(categoryFun, transactionTypeB, transactionDescriptionB, transactionAmountB, dateB, accountDebit, accountCredit);
        expectedRegists.add(expectedTransaction1);
        expectedRegists.add(expectedTransaction2);

        //Act
        List<Transaction> searchResult = personMaria.getPersonGroupsRecordsBetweenTwoDatesBetweenTwoDates(LocalDate.of(2019, 10, 1), LocalDate.of(2020, 01, 29));

        //Assert
        assertEquals(expectedRegists, searchResult);
    }

    @Test
    @DisplayName("Get transactions of all groups of one person")
    public void getRecordsBetween2Dates() {

        //Arrange person
        Person personMaria = new Person("Maria", LocalDate.of(1998, 9, 21));

        //Arrange category
        Category categorySalary = new Category("Salary");
        Category categoryFun = new Category("Salary");

        //Arrange Account

        //Arrange Account Nr.1

        Description debAccountDescription = new Description("EmployerSA Account");
        Denomination debAccountDenomination = new Denomination("EmployerSA");
        Account accountDebit = new Account(debAccountDescription, debAccountDenomination);


        //Arrange Account Nr.2

        Description debAccountDescriptionA = new Description("John Allowance");
        Denomination debAccountDenominationA = new Denomination("Allowance Money");
        Account accountCredit = new Account(debAccountDescriptionA, debAccountDenominationA);

        //Arrange of Transaction

        //Transaction A
        String transactionType = "Credit";
        String transactionDescription = "Dez 2019 Salary";
        double transactionAmount = 1000.00;
        LocalDate date = LocalDate.of(2019, 12, 25);

        //TransactionB
        String transactionTypeB = "Debit";
        String transactionDescriptionB = "Friends Dinner of the month";
        double transactionAmountB = 15.00;
        LocalDate dateB = LocalDate.of(2020, 01, 01);

        // Arrange groups

        //Group 1
        Denomination denomination = new Denomination("Runners");
        GroupID groupID = new GroupID(denomination);
        Description description = new Description("People who come together to run");
        DateOfCreation firstGroupDate = new DateOfCreation(LocalDate.of(2019, 12, 18));

        personMaria.createGroupAsPeopleInCharge(groupID, personMaria, description, firstGroupDate);
        Group groupFamilyMaria = personMaria.listOfMyGroups().getListOfGroups().get(0);
        groupFamilyMaria.createAndAddTransactionWithDate(categorySalary, transactionType, transactionDescription, transactionAmount, date, accountDebit, accountCredit);

        //Group2

        Denomination denominationA = new Denomination("Friends");
        GroupID groupIDA = new GroupID(denomination);
        Description descriptionA = new Description("Old friends from school");
        DateOfCreation secondGroupDate = new DateOfCreation(LocalDate.of(1998, 9, 20));

        personMaria.createGroupAsPeopleInCharge(groupIDA, personMaria, descriptionA, secondGroupDate);
        Group groupFriendsMaria = personMaria.listOfMyGroups().getListOfGroups().get(1);
        groupFriendsMaria.createAndAddTransactionWithDate(categoryFun, transactionTypeB, transactionDescriptionB, transactionAmountB, dateB, accountDebit, accountCredit);

        //Expected
        List<Transaction> expectedRegists = new ArrayList<>();
        Transaction expectedTransaction1 = new Transaction(categorySalary, transactionType, transactionDescription, transactionAmount, date, accountDebit, accountCredit);
        Transaction expectedTransaction2 = new Transaction(categoryFun, transactionTypeB, transactionDescriptionB, transactionAmountB, dateB, accountDebit, accountCredit);
        expectedRegists.add(expectedTransaction1);
        expectedRegists.add(expectedTransaction2);

        //Act
        List<Transaction> searchResult = personMaria.getPersonGroupsRecordsBetweenTwoDatesBetweenTwoDates(LocalDate.of(2020, 01, 1), LocalDate.of(2014, 01, 29));

        //Assert
        assertEquals(expectedRegists, searchResult);
    }

    //Create Transaction with no date

    @Test
    @DisplayName("Create transaction with no date| Happy Case")
    public void testCreateTransaction() {

        //Arrange of Persons
        Person personHenrique = new Person("Henrique", LocalDate.of(1987, 11, 19));

        //Arrange of Category
        String categoryDenomination = "Salary";
        Category categorySalary = new Category(categoryDenomination);

        //Arrange of Debit Account
        Description debAccountDescription = new Description("EmployerSA Account");
        Denomination debAccountDenomination = new Denomination("EmployerSA");

        Account accountDebit = new Account(debAccountDescription, debAccountDenomination);

        //Arrange of Credit Account
        Description credAccountDescription = new Description("Henrique Salary Account");
        Denomination credAccountDenomination = new Denomination("Salary");

        Account accountCredit = new Account(credAccountDescription, credAccountDenomination);

        //Arrange of Transaction

        //Transaction A
        String transactionType = "Credit";
        String transactionDescription = "Dez 2019 Salary";
        double transactionAmount = 1000.00;


        //Act

        boolean result = personHenrique.createAndAddTransaction(categorySalary, transactionType, transactionDescription, transactionAmount, accountDebit, accountCredit);

        //Act
        ArrayList<Transaction> expectedRegists = new ArrayList<>();
        Transaction expectedTransaction1 = new Transaction(categorySalary, transactionType, transactionDescription, transactionAmount, accountDebit, accountCredit);
        expectedRegists.add(expectedTransaction1);

        ArrayList<Transaction> searchResult = personHenrique.getRecordsBetweenTwoDates(LocalDate.of(2019, 12, 24), LocalDate.of(2020, 01, 28));

        //Assert

        assertTrue(result);
    }

    //get records of a account in a determined period of time

    @Test
    @DisplayName("Get records of a account in a determined period of time (all the movements) | Happy Case")
    public void testGetRecordsBetweenTwoDatesOfADeterminedAccountAllTheTransactionsValid() {

        //Arrange person
        Person personMaria = new Person("Maria", LocalDate.of(1998, 9, 21));

        //Arrange Category
        String categoryDenomination = "Salary";
        Category categorySalary = new Category(categoryDenomination);

        //Arrange Accounts

        //DebitAccount
        Description debAccountDescription = new Description("EmployerSA Account");
        Denomination debAccountDenomination = new Denomination("EmployerSA");
        Account debitAccount = new Account(debAccountDescription, debAccountDenomination);

        //CreditAccount
        Description credAccountDescription = new Description("Margarida Salary Account");
        Denomination credAccountDenomination = new Denomination("Salary");
        Account creditAccount = new Account(credAccountDescription, credAccountDenomination);

        //Arrange Transaction

        //Transaction 1
        String firstTransactionType = "Credit";
        String firstTransactionDescription = "Dez 2019 Salary";
        double firstTransactionAmount = 1000.00;
        LocalDate firstDate = LocalDate.of(2019, 12, 25);

        //Transaction 2
        String secondTransactionType = "Credit";
        String secondTransactionDescription = "Jan 2020 Salary";
        double secondTransactionAmount = 1500.00;
        LocalDate secondDate = LocalDate.of(2020, 1, 8);

        //Transaction 3
        String thirdTransactionType = "Credit";
        String thirdTransactionDescription = "Jan 2018 Salary";
        double thirdTransactionAmount = 800.00;
        LocalDate thirddate = LocalDate.of(2018, 1, 22);

        //Transaction 4
        String fourthTransactionType = "Debit";
        String fourthTransactionDescription = "Mar 2017 Salary";
        double fourthTransactionAmount = 548.00;
        LocalDate fourthdate = LocalDate.of(2017, 3, 12);

        //Act

        //Ledger

        Ledger ledger = new Ledger();
        ledger.createAndAddTransactionWithDate(categorySalary, firstTransactionType,
                firstTransactionDescription, firstTransactionAmount, firstDate, debitAccount, creditAccount);
        ledger.createAndAddTransactionWithDate(categorySalary, secondTransactionType,
                secondTransactionDescription, secondTransactionAmount, secondDate, debitAccount, creditAccount);
        ledger.createAndAddTransactionWithDate(categorySalary, thirdTransactionType,
                thirdTransactionDescription, thirdTransactionAmount, thirddate, debitAccount, creditAccount);
        ledger.createAndAddTransactionWithDate(categorySalary, fourthTransactionType,
                fourthTransactionDescription, fourthTransactionAmount, fourthdate, debitAccount, creditAccount);

        //Expected
        ArrayList<Transaction> expectedTransaction = new ArrayList<>();
        Transaction firstTransaction = new Transaction(categorySalary, firstTransactionType,
                firstTransactionDescription, firstTransactionAmount, firstDate, debitAccount, creditAccount);
        Transaction secondTransaction = new Transaction(categorySalary, secondTransactionType,
                secondTransactionDescription, secondTransactionAmount, secondDate, debitAccount, creditAccount);
        Transaction thirdTransaction = new Transaction(categorySalary, thirdTransactionType,
                thirdTransactionDescription, thirdTransactionAmount, thirddate, debitAccount, creditAccount);
        Transaction fourthTransaction = new Transaction(categorySalary, fourthTransactionType,
                fourthTransactionDescription, fourthTransactionAmount, fourthdate, debitAccount, creditAccount);
        expectedTransaction.add(firstTransaction);
        expectedTransaction.add(secondTransaction);
        expectedTransaction.add(thirdTransaction);
        expectedTransaction.add(fourthTransaction);

        ArrayList<Transaction> RecordsBetweenTwoDatesOfADeterminedAccount = ledger.getRecordsBetweenTwoDatesOfADeterminedAccount(debitAccount,
                LocalDate.of(2016, 1, 1), LocalDate.of(2020, 1, 28));

        //Assert
        assertEquals(expectedTransaction, RecordsBetweenTwoDatesOfADeterminedAccount);
    }

    @Test
    @DisplayName("Get records of a account in a determined period of time (no movements)")
    public void testGetRecordsBetweenTwoDatesOfADeterminedAccountNoneTransactionsAreValid() {
        //Arrange Category
        String categoryDenomination = "Salary";
        Category categorySalary = new Category(categoryDenomination);

        //Arrange Accounts

        //DebitAccount
        Description debAccountDescription = new Description("EmployerSA Account");
        Denomination debAccountDenomination = new Denomination("EmployerSA");
        Account debitAccount = new Account(debAccountDescription, debAccountDenomination);

        //CreditAccount
        Description credAccountDescription = new Description("Margarida Salary Account");
        Denomination credAccountDenomination = new Denomination("Salary");
        Account creditAccount = new Account(credAccountDescription, credAccountDenomination);

        //Arrange Transaction

        //Transaction 1
        String firstTransactionType = "Credit";
        String firstTransactionDescription = "Dez 2019 Salary";
        double firstTransactionAmount = 1000.00;
        LocalDate firstDate = LocalDate.of(2019, 12, 25);

        //Transaction 2
        String secondTransactionType = "Credit";
        String secondTransactionDescription = "Jan 2020 Salary";
        double secondTransactionAmount = 1500.00;
        LocalDate secondDate = LocalDate.of(2020, 1, 8);

        //Transaction 3
        String thirdTransactionType = "Credit";
        String thirdTransactionDescription = "Jan 2018 Salary";
        double thirdTransactionAmount = 800.00;
        LocalDate thirddate = LocalDate.of(2018, 1, 22);

        //Transaction 4
        String fourthTransactionType = "Debit";
        String fourthTransactionDescription = "Mar 2017 Salary";
        double fourthTransactionAmount = 548.00;
        LocalDate fourthdate = LocalDate.of(2017, 3, 12);

        //Act

        //Ledger

        Ledger ledger = new Ledger();
        ledger.createAndAddTransactionWithDate(categorySalary, firstTransactionType,
                firstTransactionDescription, firstTransactionAmount, firstDate, debitAccount, creditAccount);
        ledger.createAndAddTransactionWithDate(categorySalary, secondTransactionType,
                secondTransactionDescription, secondTransactionAmount, secondDate, debitAccount, creditAccount);
        ledger.createAndAddTransactionWithDate(categorySalary, thirdTransactionType,
                thirdTransactionDescription, thirdTransactionAmount, thirddate, debitAccount, creditAccount);
        ledger.createAndAddTransactionWithDate(categorySalary, fourthTransactionType,
                fourthTransactionDescription, fourthTransactionAmount, fourthdate, debitAccount, creditAccount);

        //Expected
        ArrayList<Transaction> expectedTransaction = new ArrayList<>();
        Transaction firstTransaction = new Transaction(categorySalary, firstTransactionType,
                firstTransactionDescription, firstTransactionAmount, firstDate, debitAccount, creditAccount);
        Transaction secondTransaction = new Transaction(categorySalary, secondTransactionType,
                secondTransactionDescription, secondTransactionAmount, secondDate, debitAccount, creditAccount);
        Transaction thirdTransaction = new Transaction(categorySalary, thirdTransactionType,
                thirdTransactionDescription, thirdTransactionAmount, thirddate, debitAccount, creditAccount);
        Transaction fourthTransaction = new Transaction(categorySalary, fourthTransactionType,
                fourthTransactionDescription, fourthTransactionAmount, fourthdate, debitAccount, creditAccount);
//        expectedTransaction.add(firstTransaction);
//        expectedTransaction.add(secondTransaction);
//        expectedTransaction.add(thirdTransaction);


        ArrayList<Transaction> RecordsBetweenTwoDatesOfADeterminedAccount = ledger.getRecordsBetweenTwoDatesOfADeterminedAccount(debitAccount,
                LocalDate.of(2006, 1, 1), LocalDate.of(2010, 1, 28));

        //Assert
        assertEquals(expectedTransaction, RecordsBetweenTwoDatesOfADeterminedAccount);
    }

    @Test
    @DisplayName("Get records of a account in a determined period of time (all the movements) | Sad Case")
    public void testGetRecordsBetweenTwoDatesOfADeterminedAccountBeingAllTheTransactionsAreValid() {
        //Arrange Category
        String categoryDenomination = "Salary";
        Category categorySalary = new Category(categoryDenomination);

        //Arrange Accounts

        //DebitAccount
        Description debAccountDescription = new Description("EmployerSA Account");
        Denomination debAccountDenomination = new Denomination("EmployerSA");
        Account debitAccount = new Account(debAccountDescription, debAccountDenomination);

        //CreditAccount
        Description credAccountDescription = new Description("Margarida Salary Account");
        Denomination credAccountDenomination = new Denomination("Salary");
        Account creditAccount = new Account(credAccountDescription, credAccountDenomination);

        //Arrange Transaction

        //Transaction 1
        String firstTransactionType = "Credit";
        String firstTransactionDescription = "Dez 2019 Salary";
        double firstTransactionAmount = 1000.00;
        LocalDate firstDate = LocalDate.of(2019, 12, 25);

        //Transaction 2
        String secondTransactionType = "Credit";
        String secondTransactionDescription = "Jan 2020 Salary";
        double secondTransactionAmount = 1500.00;
        LocalDate secondDate = LocalDate.of(2020, 1, 8);

        //Transaction 3
        String thirdTransactionType = "Credit";
        String thirdTransactionDescription = "Jan 2018 Salary";
        double thirdTransactionAmount = 800.00;
        LocalDate thirddate = LocalDate.of(2018, 1, 22);

        //Transaction 4
        String fourthTransactionType = "Debit";
        String fourthTransactionDescription = "Mar 2017 Salary";
        double fourthTransactionAmount = 548.00;
        LocalDate fourthdate = LocalDate.of(2017, 3, 12);

        //Act

        //Ledger

        Ledger ledger = new Ledger();
        ledger.createAndAddTransactionWithDate(categorySalary, firstTransactionType,
                firstTransactionDescription, firstTransactionAmount, firstDate, debitAccount, creditAccount);
        ledger.createAndAddTransactionWithDate(categorySalary, secondTransactionType,
                secondTransactionDescription, secondTransactionAmount, secondDate, debitAccount, creditAccount);
        ledger.createAndAddTransactionWithDate(categorySalary, thirdTransactionType,
                thirdTransactionDescription, thirdTransactionAmount, thirddate, debitAccount, creditAccount);
        ledger.createAndAddTransactionWithDate(categorySalary, fourthTransactionType,
                fourthTransactionDescription, fourthTransactionAmount, fourthdate, debitAccount, creditAccount);

        //Expected
        ArrayList<Transaction> expectedTransaction = new ArrayList<>();
        Transaction firstTransaction = new Transaction(categorySalary, firstTransactionType,
                firstTransactionDescription, firstTransactionAmount, firstDate, debitAccount, creditAccount);
        Transaction secondTransaction = new Transaction(categorySalary, secondTransactionType,
                secondTransactionDescription, secondTransactionAmount, secondDate, debitAccount, creditAccount);
        Transaction thirdTransaction = new Transaction(categorySalary, thirdTransactionType,
                thirdTransactionDescription, thirdTransactionAmount, thirddate, debitAccount, creditAccount);

        expectedTransaction.add(firstTransaction);
        expectedTransaction.add(secondTransaction);
        expectedTransaction.add(thirdTransaction);


        ArrayList<Transaction> RecordsBetweenTwoDatesOfADeterminedAccount = ledger.getRecordsBetweenTwoDatesOfADeterminedAccount(debitAccount,
                LocalDate.of(2016, 1, 1), LocalDate.of(2020, 1, 28));

        //Assert
        assertNotEquals(expectedTransaction, RecordsBetweenTwoDatesOfADeterminedAccount);
    }

    @Test
    @DisplayName("Get records of a account in a determined period of time (only half the transactions are valid) | Happy Case")
    public void testGetRecordsBetweenTwoDatesOfADeterminedAccountOnlyHalfTheTransactionsAreValid() {
        //Arrange Category
        String categoryDenomination = "Salary";
        Category categorySalary = new Category(categoryDenomination);

        //Arrange Accounts

        //DebitAccount
        Description debAccountDescription = new Description("EmployerSA Account");
        Denomination debAccountDenomination = new Denomination("EmployerSA");
        Account debitAccount = new Account(debAccountDescription, debAccountDenomination);

        //CreditAccount
        Description credAccountDescription = new Description("Margarida Salary Account");
        Denomination credAccountDenomination = new Denomination("Salary");
        Account creditAccount = new Account(credAccountDescription, credAccountDenomination);

        //Arrange Transaction

        //Transaction 1
        String firstTransactionType = "Credit";
        String firstTransactionDescription = "Dez 2019 Salary";
        double firstTransactionAmount = 1000.00;
        LocalDate firstDate = LocalDate.of(2016, 12, 25);

        //Transaction 2
        String secondTransactionType = "Credit";
        String secondTransactionDescription = "Jan 2020 Salary";
        double secondTransactionAmount = 1500.00;
        LocalDate secondDate = LocalDate.of(2020, 1, 8);

        //Transaction 3
        String thirdTransactionType = "Credit";
        String thirdTransactionDescription = "Jan 2018 Salary";
        double thirdTransactionAmount = 800.00;
        LocalDate thirddate = LocalDate.of(2018, 1, 22);

        //Transaction 4
        String fourthTransactionType = "Debit";
        String fourthTransactionDescription = "Mar 2017 Salary";
        double fourthTransactionAmount = 548.00;
        LocalDate fourthdate = LocalDate.of(2017, 3, 12);

        //Act

        //Ledger

        Ledger ledger = new Ledger();
        ledger.createAndAddTransactionWithDate(categorySalary, firstTransactionType,
                firstTransactionDescription, firstTransactionAmount, firstDate, debitAccount, creditAccount);
        ledger.createAndAddTransactionWithDate(categorySalary, secondTransactionType,
                secondTransactionDescription, secondTransactionAmount, secondDate, debitAccount, creditAccount);
        ledger.createAndAddTransactionWithDate(categorySalary, thirdTransactionType,
                thirdTransactionDescription, thirdTransactionAmount, thirddate, debitAccount, creditAccount);
        ledger.createAndAddTransactionWithDate(categorySalary, fourthTransactionType,
                fourthTransactionDescription, fourthTransactionAmount, fourthdate, debitAccount, creditAccount);

        //Expected
        ArrayList<Transaction> expectedTransaction = new ArrayList<>();

        Transaction secondTransaction = new Transaction(categorySalary, secondTransactionType,
                secondTransactionDescription, secondTransactionAmount, secondDate, debitAccount, creditAccount);
        Transaction thirdTransaction = new Transaction(categorySalary, thirdTransactionType,
                thirdTransactionDescription, thirdTransactionAmount, thirddate, debitAccount, creditAccount);


        expectedTransaction.add(secondTransaction);
        expectedTransaction.add(thirdTransaction);


        ArrayList<Transaction> RecordsBetweenTwoDatesOfADeterminedAccount = ledger.getRecordsBetweenTwoDatesOfADeterminedAccount(debitAccount,
                LocalDate.of(2018, 1, 1), LocalDate.of(2020, 1, 28));

        //Assert
        assertEquals(expectedTransaction, RecordsBetweenTwoDatesOfADeterminedAccount);
    }

    @Test
    @DisplayName("Get records of a account in a determined period of time (only one movement are valid due to the use of a different account)| Happy Case (different account)")
    public void testGetRecordsBetweenTwoDatesOfADeterminedAccountSingleMovement() {
        //Arrange Category
        String categoryDenomination = "Salary";
        Category categorySalary = new Category(categoryDenomination);

        //Arrange Accounts

        //DebitAccount
        Description debAccountDescription = new Description("EmployerSA Account");
        Denomination debAccountDenomination = new Denomination("EmployerSA");
        Account debitAccount = new Account(debAccountDescription, debAccountDenomination);

        //CreditAccount
        Description credAccountDescription = new Description("Margarida Salary Account");
        Denomination credAccountDenomination = new Denomination("Salary");
        Account creditAccount = new Account(credAccountDescription, credAccountDenomination);

        //Another DebitAccount
        Description anotherDebAccountDescription = new Description("CompanySA Account");
        Denomination anotherDebAccountDenomination = new Denomination("CompanySA");
        Account anotherDebitAccount = new Account(anotherDebAccountDescription, anotherDebAccountDenomination);

        //Arrange Transaction

        //Transaction 1
        String firstTransactionType = "Credit";
        String firstTransactionDescription = "Dez 2019 Salary";
        double firstTransactionAmount = 1000.00;
        LocalDate firstDate = LocalDate.of(2019, 12, 25);

        //Transaction 2
        String secondTransactionType = "Credit";
        String secondTransactionDescription = "Jan 2020 Salary";
        double secondTransactionAmount = 1500.00;
        LocalDate secondDate = LocalDate.of(2020, 1, 8);

        //Transaction 3
        String thirdTransactionType = "Credit";
        String thirdTransactionDescription = "Jan 2018 Salary";
        double thirdTransactionAmount = 800.00;
        LocalDate thirddate = LocalDate.of(2018, 1, 22);

        //Transaction 4
        String fourthTransactionType = "Debit";
        String fourthTransactionDescription = "Mar 2017 Salary";
        double fourthTransactionAmount = 548.00;
        LocalDate fourthdate = LocalDate.of(2017, 3, 12);

        //Act

        //Ledger

        Ledger ledger = new Ledger();
        ledger.createAndAddTransactionWithDate(categorySalary, firstTransactionType,
                firstTransactionDescription, firstTransactionAmount, firstDate, anotherDebitAccount, creditAccount);
        ledger.createAndAddTransactionWithDate(categorySalary, secondTransactionType,
                secondTransactionDescription, secondTransactionAmount, secondDate, debitAccount, creditAccount);
        ledger.createAndAddTransactionWithDate(categorySalary, thirdTransactionType,
                thirdTransactionDescription, thirdTransactionAmount, thirddate, debitAccount, creditAccount);
        ledger.createAndAddTransactionWithDate(categorySalary, fourthTransactionType,
                fourthTransactionDescription, fourthTransactionAmount, fourthdate, debitAccount, creditAccount);

        //Expected
        ArrayList<Transaction> expectedTransaction = new ArrayList<>();
        Transaction firstTransaction = new Transaction(categorySalary, firstTransactionType,
                firstTransactionDescription, firstTransactionAmount, firstDate, debitAccount, creditAccount);
        Transaction secondTransaction = new Transaction(categorySalary, secondTransactionType,
                secondTransactionDescription, secondTransactionAmount, secondDate, debitAccount, creditAccount);
        Transaction thirdTransaction = new Transaction(categorySalary, thirdTransactionType,
                thirdTransactionDescription, thirdTransactionAmount, thirddate, debitAccount, creditAccount);
        Transaction fourthTransaction = new Transaction(categorySalary, fourthTransactionType,
                fourthTransactionDescription, fourthTransactionAmount, fourthdate, debitAccount, creditAccount);
        expectedTransaction.add(firstTransaction);

        ArrayList<Transaction> RecordsBetweenTwoDatesOfADeterminedAccount = ledger.getRecordsBetweenTwoDatesOfADeterminedAccount(anotherDebitAccount,
                LocalDate.of(2016, 1, 1), LocalDate.of(2020, 1, 28));

        //Assert
        assertEquals(expectedTransaction, RecordsBetweenTwoDatesOfADeterminedAccount);
    }


    //SCHEDULINGS

    @Test
    @DisplayName("Test For createScheduling() - Success Case")
    void createScheduling() {
        //Arrange Person Nr.1
        Person personMiguel = new Person("Miguel", LocalDate.of(2000, 10, 25));

        //Arrange Account Nr.1

        Description debAccountDescription = new Description("EmployerSA Account");
        Denomination debAccountDenomination = new Denomination("EmployerSA");
        Account accountBanco = new Account(debAccountDescription, debAccountDenomination);


        //Arrange Account Nr.2

        Description debAccountDescriptionA = new Description("John Allowance");
        Denomination debAccountDenominationA = new Denomination("Allowance Money");
        Account accountJohn = new Account(debAccountDescriptionA, debAccountDenominationA);

        //Arrange Category Nr.1
        Category category = new Category("Allowance");

        //Arrange Transaction
        LocalDate triggerDate = LocalDate.now();

        //Act
        boolean result = personMiguel.createScheduling("Credit", 50.0, accountBanco, accountJohn, category, "Allowance", triggerDate, "Daily");

        //Assert
        assertEquals(true, result);
        assertEquals(true, personMiguel.hasScheduling(new Scheduling("Credit", 50.0, accountBanco, accountJohn, category, "Allowance", triggerDate, "Daily")));
        assertEquals(1, personMiguel.schedulingListSize());
    }

    @Test
    @DisplayName("Test For createScheduling() - Create Two Schedules")
    void createSchedulingTwice() {
        //Arrange Person Nr.1
        Person personMiguel = new Person("Miguel", LocalDate.of(2000, 10, 25));

        //Arrange Account Nr.1

        Description debAccountDescription = new Description("EmployerSA Account");
        Denomination debAccountDenomination = new Denomination("EmployerSA");
        Account accountBanco = new Account(debAccountDescription, debAccountDenomination);


        //Arrange Account Nr.2

        Description debAccountDescriptionA = new Description("John Allowance");
        Denomination debAccountDenominationA = new Denomination("Allowance Money");
        Account accountJohn = new Account(debAccountDescriptionA, debAccountDenominationA);

        //Arrange Category Nr.1
        Category category = new Category("Allowance");

        //Arrange Transaction
        LocalDate triggerDate = LocalDate.now();

        //Act
        boolean result1 = personMiguel.createScheduling("Credit", 50.0, accountBanco, accountJohn, category, "Allowance", triggerDate, "Daily");
        boolean result2 = personMiguel.createScheduling("Credit", 50.0, accountBanco, accountJohn, category, "Allowance", triggerDate, "Weekly");


        //Assert
        assertEquals(true, result1);
        assertEquals(true, result2);
        assertEquals(true, personMiguel.hasScheduling(new Scheduling("Credit", 50.0, accountBanco, accountJohn, category, "Allowance", triggerDate, "Daily")));
        assertEquals(true, personMiguel.hasScheduling(new Scheduling("Credit", 50.0, accountBanco, accountJohn, category, "Allowance", triggerDate, "Weekly")));
        assertEquals(2, personMiguel.schedulingListSize());
    }

    @Test
    @DisplayName("Test For createScheduling() - Create Same Two Schedules")
    void createSameSchedulingTwice() {
        //Arrange Person Nr.1
        Person personMiguel = new Person("Miguel", LocalDate.of(2000, 10, 25));

        //Arrange Account Nr.1

        Description debAccountDescription = new Description("EmployerSA Account");
        Denomination debAccountDenomination = new Denomination("EmployerSA");
        Account accountBanco = new Account(debAccountDescription, debAccountDenomination);


        //Arrange Account Nr.2

        Description debAccountDescriptionA = new Description("John Allowance");
        Denomination debAccountDenominationA = new Denomination("Allowance Money");
        Account accountJohn = new Account(debAccountDescriptionA, debAccountDenominationA);

        //Arrange Category Nr.1
        Category category = new Category("Allowance");

        //Arrange Transaction
        LocalDate triggerDate = LocalDate.now();

        //Act
        boolean result1 = personMiguel.createScheduling("Credit", 50.0, accountBanco, accountJohn, category, "Allowance", triggerDate, "Daily");
        boolean result2 = personMiguel.createScheduling("Credit", 50.0, accountBanco, accountJohn, category, "Allowance", triggerDate, "Daily");

        //Assert
        assertEquals(true, result1);
        assertEquals(false, result2);
        assertEquals(true, personMiguel.hasScheduling(new Scheduling("Credit", 50.0, accountBanco, accountJohn, category, "Allowance", triggerDate, "Daily")));
        assertEquals(1, personMiguel.schedulingListSize());
    }

 */
