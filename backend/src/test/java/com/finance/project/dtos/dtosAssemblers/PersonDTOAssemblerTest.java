package com.finance.project.dtos.dtosAssemblers;

import com.finance.project.domainLayer.domainEntities.aggregates.person.Birthdate;
import com.finance.project.domainLayer.domainEntities.aggregates.person.Birthplace;
import com.finance.project.domainLayer.domainEntities.aggregates.person.Name;
import com.finance.project.domainLayer.domainEntities.vosShared.Email;
import com.finance.project.domainLayer.domainEntities.vosShared.LedgerID;
import com.finance.project.domainLayer.domainEntities.vosShared.PersonID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.finance.project.dtos.dtos.PersonDTO;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonDTOAssemblerTest {

    @Test
    @DisplayName("PersonDTOAssembler - Test create data transfer objects from Domain Object || Happy case")
    void personDTOAssembler_CreateDTOFromDomainObjectTest() {

        //Arrange PersonDTO

            //Person DTOAssembler
        String mariaEmail = "maria@gmail.com";
        String mariaName = "Maria Silva";
        LocalDate mariaBirthDate = LocalDate.of(1973, 07, 25);
        String mariaBirthplace = "Braga";

        Email emailMaria = Email.createEmail(mariaEmail);
        Name nameMaria = Name.createName(mariaName);
        Birthdate birthateMaria = Birthdate.createBirthdate(mariaBirthDate);
        Birthplace birthplaceMaria = Birthplace.createBirthplace(mariaBirthplace);
        PersonID fatherID = null;
        PersonID motherID = null;

        String IS_NOT_DEFINED = "Is Not Defined";

        //PersonDTO
        String personMariaBirthdate = birthateMaria.getBirthdate().toString();

        //Expected
        PersonDTO personDTOExpected = new PersonDTO(mariaEmail, mariaName, personMariaBirthdate, mariaBirthplace, IS_NOT_DEFINED, IS_NOT_DEFINED);

        //Act
        PersonDTO personDTO = PersonDTOAssembler.createDTOFromDomainObject(emailMaria, nameMaria, birthateMaria, birthplaceMaria, fatherID, motherID);

        //Assert
        assertEquals(personDTOExpected, personDTO);
    }


    @Test
    @DisplayName("PersonDTOAssembler - Intantiates DTOAssembler")
    void personDTOAssembler_InstatiatesDTOAssembler() {

        //Arrange PersonDTO

        //Person DTOAssembler
        String mariaEmail = "maria@gmail.com";
        String mariaName = "Maria Silva";
        LocalDate mariaBirthDate = LocalDate.of(1973, 07, 25);
        String mariaBirthplace = "Braga";

        Email emailMaria = Email.createEmail(mariaEmail);
        Name nameMaria = Name.createName(mariaName);
        Birthdate birthateMaria = Birthdate.createBirthdate(mariaBirthDate);
        Birthplace birthplaceMaria = Birthplace.createBirthplace(mariaBirthplace);
        PersonID fatherID = null;
        PersonID motherID = null;

        String IS_NOT_DEFINED = "Is Not Defined";

        //PersonDTO
        String personMariaBirthdate = birthateMaria.getBirthdate().toString();

        //Expected
        PersonDTO personDTOExpected = new PersonDTO(mariaEmail, mariaName, personMariaBirthdate, mariaBirthplace, IS_NOT_DEFINED, IS_NOT_DEFINED);

        //Act
        PersonDTOAssembler personDTOAssembler = new PersonDTOAssembler();
        PersonDTO personDTO = personDTOAssembler.createDTOFromDomainObject(emailMaria, nameMaria, birthateMaria, birthplaceMaria, fatherID, motherID);

        //Assert
        assertEquals(personDTOExpected, personDTO);
    }

    @Test
    @DisplayName("PersonDTOAssembler - WithMotherAndFather")
    void personDTOAssembler_WithMotherAndFather() {

        //Arrange PersonDTO

        //Person DTOAssembler
        String mariaEmail = "maria@gmail.com";
        String mariaName = "Maria Silva";
        LocalDate mariaBirthDate = LocalDate.of(1973, 07, 25);
        String mariaBirthplace = "Braga";

        Email emailMaria = Email.createEmail(mariaEmail);
        Name nameMaria = Name.createName(mariaName);
        Birthdate birthateMaria = Birthdate.createBirthdate(mariaBirthDate);
        Birthplace birthplaceMaria = Birthplace.createBirthplace(mariaBirthplace);

        PersonID fatherID = PersonID.createPersonID("pp@gmail.com");
        String fatherEmail = fatherID.getEmail().getEmail();
        PersonID motherID = PersonID.createPersonID("mm@gmail.com");
        String motherEmail = motherID.getEmail().getEmail();



        //PersonDTO
        String personMariaBirthdate = birthateMaria.getBirthdate().toString();

        //Expected
        PersonDTO personDTOExpected = new PersonDTO(mariaEmail, mariaName, personMariaBirthdate, mariaBirthplace, fatherEmail, motherEmail);

        //Act
        PersonDTOAssembler personDTOAssembler = new PersonDTOAssembler();
        PersonDTO personDTO = personDTOAssembler.createDTOFromDomainObject(emailMaria, nameMaria, birthateMaria, birthplaceMaria, fatherID, motherID);

        //Assert
        assertEquals(personDTOExpected, personDTO);
    }

    @Test
    @DisplayName("PersonDTOAssembler - Intantiates DTOAssembler-SecondConstructor")
    void personDTOAssembler_InstatiatesDTOAssembler_SecondConstructor() {

        //Arrange PersonDTO

        //Person DTOAssembler
        String mariaEmail = "maria@gmail.com";
        String mariaName = "Maria Silva";
        LocalDate mariaBirthDate = LocalDate.of(1973, 07, 25);
        String mariaBirthplace = "Braga";

        Email emailMaria = Email.createEmail(mariaEmail);
        Name nameMaria = Name.createName(mariaName);
        Birthdate birthateMaria = Birthdate.createBirthdate(mariaBirthDate);
        Birthplace birthplaceMaria = Birthplace.createBirthplace(mariaBirthplace);

        String ledgerId = "123";
        LedgerID ledgerID = new LedgerID(ledgerId);


        PersonID fatherID = null;
        PersonID motherID = null;

        String IS_NOT_DEFINED = "Is Not Defined";

        //PersonDTO
        String personMariaBirthdate = birthateMaria.getBirthdate().toString();

        //Expected
        PersonDTO personDTOExpected = new PersonDTO(mariaEmail,ledgerId, mariaName, personMariaBirthdate, mariaBirthplace, IS_NOT_DEFINED, IS_NOT_DEFINED);

        //Act

        PersonDTO personDTO = PersonDTOAssembler.createDTOFromDomainObject(emailMaria, ledgerID, nameMaria, birthateMaria, birthplaceMaria, fatherID, motherID);



        //Assert
        assertEquals(personDTOExpected, personDTO);
    }

    @Test
    @DisplayName("PersonDTOAssembler - Intantiates DTOAssembler-ThirdConstructor")
    void personDTOAssembler_InstatiatesDTOAssembler_ThirdConstructor() {

        //Arrange PersonDTO

        //Person DTOAssembler
        String mariaEmail = "maria@gmail.com";
        String mariaName = "Maria Silva";
        LocalDate mariaBirthDate = LocalDate.of(1973, 07, 25);
        String mariaBirthplace = "Braga";

        Email emailMaria = Email.createEmail(mariaEmail);
        Name nameMaria = Name.createName(mariaName);
        Birthdate birthateMaria = Birthdate.createBirthdate(mariaBirthDate);
        Birthplace birthplaceMaria = Birthplace.createBirthplace(mariaBirthplace);

        LedgerID ledgerID = LedgerID.createLedgerID();
        String ledgerId = ledgerID.toString();

        PersonID fatherID = null;
        PersonID motherID = null;

        String IS_NOT_DEFINED = "Is Not Defined";

        //PersonDTO
        String personMariaBirthdate = birthateMaria.getBirthdate().toString();

        //Expected
        PersonDTO personDTOExpected = new PersonDTO(mariaEmail, mariaName, personMariaBirthdate, mariaBirthplace);

        //Act

        PersonDTOAssembler personDTOAssembler = new PersonDTOAssembler();
        PersonDTO personDTO = personDTOAssembler.createDTOFromPrimitiveTypes(mariaEmail, mariaName, mariaBirthDate.toString(), mariaBirthplace);


        //Assert
        assertEquals(personDTOExpected, personDTO);
    }

}