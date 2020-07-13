package com.finance.project.dtos.dtosAssemblers;

import com.finance.project.dtos.dtos.PersonDTO;
import com.finance.project.domainLayer.domainEntities.aggregates.person.Birthdate;
import com.finance.project.domainLayer.domainEntities.aggregates.person.Birthplace;
import com.finance.project.domainLayer.domainEntities.aggregates.person.Name;
import com.finance.project.domainLayer.domainEntities.vosShared.Email;
import com.finance.project.domainLayer.domainEntities.vosShared.LedgerID;
import com.finance.project.domainLayer.domainEntities.vosShared.PersonID;


public class PersonDTOAssembler {

    // without ledger
    public static PersonDTO createDTOFromDomainObject(Email email, Name name, Birthdate birthdate, Birthplace birthplace, PersonID father, PersonID mother) {
        String personEmail = email.getEmail();
        String personName = name.getName();
        String personBirthdate = birthdate.getBirthdate().toString();
        String personBirthplace = birthplace.getBirthplace();
        String fatherEmail;
        String motherEmail;

        String IS_NOT_DEFINED = "Is Not Defined";

        if (father != null) {
            fatherEmail = father.getEmail().getEmail();
        } else {
            fatherEmail = IS_NOT_DEFINED;
        }

        if (mother != null) {
            motherEmail = mother.getEmail().getEmail();
        } else {
            motherEmail = IS_NOT_DEFINED;
        }

        PersonDTO personDTO = new PersonDTO(personEmail, personName, personBirthdate, personBirthplace, fatherEmail, motherEmail);
        return personDTO;

    }

    // with ledger
    public static PersonDTO createDTOFromDomainObject(Email email, LedgerID ledgerID, Name name, Birthdate birthdate,
                                                      Birthplace birthplace, PersonID father, PersonID mother) {
        String personEmail = email.getEmail();
        String personLedgerID = ledgerID.getLedgerID();
        String personName = name.getName();
        String personBirthdate = birthdate.getBirthdate().toString();
        String personBirthplace = birthplace.getBirthplace();
        String fatherEmail;
        String motherEmail;

        String IS_NOT_DEFINED = "Is Not Defined";

        if (father != null) {
            fatherEmail = father.getEmail().getEmail();
        } else {
            fatherEmail = IS_NOT_DEFINED;
        }

        if (mother != null) {
            motherEmail = mother.getEmail().getEmail();
        } else {
            motherEmail = IS_NOT_DEFINED;
        }

        PersonDTO personDTO = new PersonDTO(personEmail, personLedgerID, personName, personBirthdate, personBirthplace, fatherEmail, motherEmail);
        return personDTO;
    }


    public static PersonDTO createDTOFromPrimitiveTypes(String email, String name, String birthdate, String birthplace) {

        PersonDTO personDTO = new PersonDTO(email, name, birthdate, birthplace);
        return personDTO;
    }
}