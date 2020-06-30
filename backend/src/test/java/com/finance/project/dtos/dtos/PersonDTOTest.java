package com.finance.project.dtos.dtos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PersonDTOTest {

    @Test
    @DisplayName("PersonDTO - test constructor with parameters")
    void personDTO_ConstructorWithParametersTest() {

        // Arrange
        String personName = "Santiago";
        String personEmail = "santi@gmail.com";
        String birthdate = LocalDate.of(2000, 2, 26).toString();
        String birthplace = "Porto";
        String father = "kiko@gmail.com";
        String mother = "kika@gmail.com";

        // Act
        PersonDTO personDTO = new PersonDTO(personEmail, personName, birthdate, birthplace, father, mother);

        // Assert
        assertEquals(personName, personDTO.getName());
        assertEquals(personEmail, personDTO.getEmail());
        assertEquals(birthdate, personDTO.getBirthdate());
        assertEquals(birthplace, personDTO.getBirthplace());
        assertEquals(father, personDTO.getFather());
        assertEquals(mother, personDTO.getMother());
    }

    @Test
    @DisplayName("PersonDTO - test constructor 2 with parameters")
    void personDTO_Constructor2WithParametersTest() {

        // Arrange
        String personName = "Santiago";
        String personEmail = "santi@gmail.com";
        String birthdate = LocalDate.of(2000, 2, 26).toString();
        String birthplace = "Porto";
        String ledger = "";

        // Act
        PersonDTO personDTO = new PersonDTO(personEmail, personName, birthdate, birthplace);
        personDTO.setLedger(ledger);

        // Assert
        assertEquals(personName, personDTO.getName());
        assertEquals(personEmail, personDTO.getEmail());
        assertEquals(birthdate, personDTO.getBirthdate());
        assertEquals(birthplace, personDTO.getBirthplace());
        assertEquals(ledger, personDTO.getLedger());
    }

    @Test
    @DisplayName("PersonDTO - test constructor 3 with parameters")
    void personDTO_Constructor3WithParametersTest() {

        // Arrange
        String personName = "Santiago";
        String personEmail = "santi@gmail.com";
        String birthdate = LocalDate.of(2000, 2, 26).toString();
        String birthplace = "Porto";
        String father = "kiko@gmail.com";
        String mother = "kika@gmail.com";
        String ledger = "";

        // Act
        PersonDTO personDTO = new PersonDTO(personEmail, ledger, personName, birthdate, birthplace, father, mother);

        // Assert
        assertEquals(personName, personDTO.getName());
        assertEquals(ledger, personDTO.getLedger());
        assertEquals(personEmail, personDTO.getEmail());
        assertEquals(birthdate, personDTO.getBirthdate());
        assertEquals(birthplace, personDTO.getBirthplace());
        assertEquals(father, personDTO.getFather());
        assertEquals(mother, personDTO.getMother());
    }

    @Test
    @DisplayName("PersonDTO - test constructor 4 with parameters")
    void personDTO_Constructor4WithParametersTest() {

        // Arrange
        String personName = "Santiago";
        String personEmail = "santi@gmail.com";
        String birthdate = LocalDate.of(2000, 2, 26).toString();
        String birthplace = "Porto";
        String ledger = "";

        // Act
        PersonDTO personDTO = new PersonDTO(personEmail, ledger, personName, birthdate, birthplace);

        // Assert
        assertEquals(personName, personDTO.getName());
        assertEquals(ledger, personDTO.getLedger());
        assertEquals(personEmail, personDTO.getEmail());
        assertEquals(birthdate, personDTO.getBirthdate());
        assertEquals(birthplace, personDTO.getBirthplace());
    }


    @Test
    @DisplayName("PersonDTO - test constructor without parameters")
    void personDTO_ConstructorWithoutParametersTest() {

        // Arrange
        String personName = "Santiago";
        String personEmail = "santi@gmail.com";
        String birthdate = LocalDate.of(2000, 2, 26).toString();
        String birthplace = "Porto";
        String father = "kiko@gmail.com";
        String mother = "kika@gmail.com";

        // Act
        PersonDTO personDTO = new PersonDTO();

        personDTO.setName(personName);
        personDTO.setEmail(personEmail);
        personDTO.setBirthdate(birthdate);
        personDTO.setBirthplace(birthplace);
        personDTO.setFather(father);
        personDTO.setMother(mother);

        // Assert
        assertEquals(personName, personDTO.getName());
        assertEquals(personEmail, personDTO.getEmail());
        assertEquals(birthdate, personDTO.getBirthdate());
        assertEquals(birthplace, personDTO.getBirthplace());
        assertEquals(father, personDTO.getFather());
        assertEquals(mother, personDTO.getMother());
    }


    @Test
    @DisplayName("PersonDTO - Test Equals || Same Object")
    void personDTO_EqualsTest_SameObject() {

        // Arrange
        String personName = "Santiago";
        String ledger = "";
        String personEmail = "santi@gmail.com";
        String birthdate = LocalDate.of(2000, 2, 26).toString();
        String birthplace = "Porto";
        String father = "kiko@gmail.com";
        String mother = "kika@gmail.com";

        // Act
        PersonDTO personDTO1 = new PersonDTO(personEmail,ledger,personName,birthdate,birthplace,father,mother);

        // Assert
        assertTrue(personDTO1.equals(personDTO1));

    }

    @Test
    @DisplayName("PersonDTO - Test Equals || Different Object - Same information")
    void personDTO_EqualsTest_DifferentObjectSameinformation() {

        // Arrange
        String personName = "Santiago";
        String ledger = "";
        String personEmail = "santi@gmail.com";
        String birthdate = LocalDate.of(2000, 2, 26).toString();
        String birthplace = "Porto";
        String father = "kiko@gmail.com";
        String mother = "kika@gmail.com";

        // Act
        PersonDTO personDTO1 = new PersonDTO(personEmail,ledger,personName,birthdate,birthplace,father,mother);
        PersonDTO personDTO2 = new PersonDTO(personEmail,ledger,personName,birthdate,birthplace,father,mother);

        // Assert
        assertTrue(personDTO1.equals(personDTO2));

    }


    @Test
    @DisplayName("PersonDTO - Test Equals || Different Object")
    void personDTO_EqualsTest_DifferentObject() {

        // Arrange

        // Person Santi
        String personSanti = "Santiago";
        String emailSanti = "santi@gmail.com";
        String birthdateSanti = LocalDate.of(2000, 2, 26).toString();
        String birthplaceSanti = "Porto";
        String fatherSanti = "kiko@gmail.com";
        String motherSanti = "kika@gmail.com";

        // Person Leo
        String personLeo = "Leo";
        String emailLeo = "leo@gmail.com";
        String birthdateLeo = LocalDate.of(2002, 1, 23).toString();
        String birthplaceLeo = "Lisbon";
        String fatherLeo = "zuko@gmail.com";
        String motherLeo = "zuka@gmail.com";

        String bugKiller = "Bug Killer";

        // Act
        PersonDTO personDTO1 = new PersonDTO(personSanti, emailSanti, birthdateSanti, birthplaceSanti, fatherSanti, motherSanti);
        PersonDTO personDTO2 = new PersonDTO(personLeo, emailLeo, birthdateLeo, birthplaceLeo, fatherLeo, motherLeo);
        PersonDTO personDTO3 = null;

        boolean result = personDTO1.equals(personDTO2);

        //Assert
        assertEquals(false, result);
        assertFalse(personDTO1.equals(personDTO2));
        assertFalse(personDTO1.equals(bugKiller));
        assertFalse(personDTO1.equals(personDTO3));
    }


    @Test
    @DisplayName("PersonDTO - Test Equals || Different ledger")
    void personDTO_EqualsTest_DifferentLedger() {

        // Arrange

        // Person Santi
        String personSanti = "Santiago";
        String ledger = "";
        String emailSanti = "santi@gmail.com";
        String birthdateSanti = LocalDate.of(2000, 2, 26).toString();
        String birthplaceSanti = "Porto";
        String fatherSanti = "kiko@gmail.com";
        String motherSanti = "kika@gmail.com";

        // Person Leo
        String newLedger = "L";


        // Act
        PersonDTO personDTO1 = new PersonDTO(personSanti, emailSanti, birthdateSanti, birthplaceSanti, fatherSanti, motherSanti);
        personDTO1.setLedger(ledger);
        PersonDTO personDTO2 = new PersonDTO(personSanti, emailSanti, birthdateSanti, birthplaceSanti, fatherSanti, motherSanti);
        personDTO2.setLedger(newLedger);


        boolean result = personDTO1.equals(personDTO2);

        //Assert
        assertEquals(false, result);
        assertFalse(personDTO1.equals(personDTO2));

    }

    @Test
    @DisplayName("PersonDTO - Test Equals || Different name")
    void personDTO_EqualsTest_DifferentName() {

        // Arrange

        // Person Santi
        String personSanti = "Santiago";
        String emailSanti = "santi@gmail.com";
        String birthdateSanti = LocalDate.of(2000, 2, 26).toString();
        String birthplaceSanti = "Porto";
        String fatherSanti = "kiko@gmail.com";
        String motherSanti = "kika@gmail.com";

        // Person Leo
        String personLeo = "Leo";


        // Act
        PersonDTO personDTO1 = new PersonDTO(personSanti, emailSanti, birthdateSanti, birthplaceSanti, fatherSanti, motherSanti);
        PersonDTO personDTO2 = new PersonDTO(personLeo, emailSanti, birthdateSanti, birthplaceSanti, fatherSanti, motherSanti);


        boolean result = personDTO1.equals(personDTO2);

        //Assert
        assertEquals(false, result);
        assertFalse(personDTO1.equals(personDTO2));

    }
    @Test
    @DisplayName("PersonDTO - Test Equals || Different email")
    void personDTO_EqualsTest_DifferentEmail() {

        // Arrange

        // Person Santi
        String personSanti = "Santiago";
        String emailSanti = "santi@gmail.com";
        String birthdateSanti = LocalDate.of(2000, 2, 26).toString();
        String birthplaceSanti = "Porto";
        String fatherSanti = "kiko@gmail.com";
        String motherSanti = "kika@gmail.com";

        // Person Leo
        String emailLeo = "leo@gmail.com";




        // Act
        PersonDTO personDTO1 = new PersonDTO(personSanti, emailSanti, birthdateSanti, birthplaceSanti, fatherSanti, motherSanti);
        PersonDTO personDTO2 = new PersonDTO(personSanti, emailLeo, birthdateSanti, birthplaceSanti, fatherSanti, motherSanti);


        boolean result = personDTO1.equals(personDTO2);

        //Assert
        assertEquals(false, result);
        assertFalse(personDTO1.equals(personDTO2));

    }

    @Test
    @DisplayName("PersonDTO - Test Equals || Different bithdate")
    void personDTO_EqualsTest_DifferentBirthdate() {

        // Arrange

        // Person Santi
        String personSanti = "Santiago";
        String emailSanti = "santi@gmail.com";
        String birthdateSanti = LocalDate.of(2000, 2, 26).toString();
        String birthplaceSanti = "Porto";
        String fatherSanti = "kiko@gmail.com";
        String motherSanti = "kika@gmail.com";

        // Person Leo

        String birthdateLeo = LocalDate.of(2002, 1, 23).toString();


        // Act
        PersonDTO personDTO1 = new PersonDTO(personSanti, emailSanti, birthdateSanti, birthplaceSanti, fatherSanti, motherSanti);
        PersonDTO personDTO2 = new PersonDTO(personSanti, emailSanti, birthdateLeo, birthplaceSanti, fatherSanti, motherSanti);


        boolean result = personDTO1.equals(personDTO2);

        //Assert
        assertEquals(false, result);
        assertFalse(personDTO1.equals(personDTO2));

    }

    @Test
    @DisplayName("PersonDTO - Test Equals || Different bithplace")
    void personDTO_EqualsTest_DifferentBirtplace() {

        // Arrange

        // Person Santi
        String personSanti = "Santiago";
        String emailSanti = "santi@gmail.com";
        String birthdateSanti = LocalDate.of(2000, 2, 26).toString();
        String birthplaceSanti = "Porto";
        String fatherSanti = "kiko@gmail.com";
        String motherSanti = "kika@gmail.com";

        // Person Leo
        String birthplaceLeo = "Lisbon";


        // Act
        PersonDTO personDTO1 = new PersonDTO(personSanti, emailSanti, birthdateSanti, birthplaceSanti, fatherSanti, motherSanti);
        PersonDTO personDTO2 = new PersonDTO(personSanti, emailSanti, birthdateSanti, birthplaceLeo, fatherSanti, motherSanti);


        boolean result = personDTO1.equals(personDTO2);

        //Assert
        assertEquals(false, result);
        assertFalse(personDTO1.equals(personDTO2));

    }

    @Test
    @DisplayName("PersonDTO - Test Equals || Different father")
    void personDTO_EqualsTest_DifferentFather() {

        // Arrange

        // Person Santi
        String personSanti = "Santiago";
        String emailSanti = "santi@gmail.com";
        String birthdateSanti = LocalDate.of(2000, 2, 26).toString();
        String birthplaceSanti = "Porto";
        String fatherSanti = "kiko@gmail.com";
        String motherSanti = "kika@gmail.com";

        // Person Leo
        String fatherLeo = "zuko@gmail.com";


        // Act
        PersonDTO personDTO1 = new PersonDTO(personSanti, emailSanti, birthdateSanti, birthplaceSanti, fatherSanti, motherSanti);
        PersonDTO personDTO2 = new PersonDTO(personSanti, emailSanti, birthdateSanti, birthplaceSanti, fatherLeo, motherSanti);


        boolean result = personDTO1.equals(personDTO2);

        //Assert
        assertEquals(false, result);
        assertFalse(personDTO1.equals(personDTO2));

    }

    @Test
    @DisplayName("PersonDTO - Test Equals || Different mother")
    void personDTO_EqualsTest_DifferentMother() {

        // Arrange

        // Person Santi
        String personSanti = "Santiago";
        String emailSanti = "santi@gmail.com";
        String birthdateSanti = LocalDate.of(2000, 2, 26).toString();
        String birthplaceSanti = "Porto";
        String fatherSanti = "kiko@gmail.com";
        String motherSanti = "kika@gmail.com";

        // Person Leo
        String motherLeo = "zuka@gmail.com";



        // Act
        PersonDTO personDTO1 = new PersonDTO(personSanti, emailSanti, birthdateSanti, birthplaceSanti, fatherSanti, motherSanti);
        PersonDTO personDTO2 = new PersonDTO(personSanti, emailSanti, birthdateSanti, birthplaceSanti, fatherSanti, motherLeo);


        boolean result = personDTO1.equals(personDTO2);

        //Assert
        assertEquals(false, result);
        assertFalse(personDTO1.equals(personDTO2));

    }


    @Test
    @DisplayName("PersonDTO - Test Hash Code")
    void personDTO_HashCodeTest() {

        // Arrange
        String personName = "Santiago";
        String personEmail = "santi@gmail.com";
        String birthdate = LocalDate.of(2000, 2, 26).toString();
        String birthplace = "Porto";
        String father = "kiko@gmail.com";
        String mother = "kika@gmail.com";

        // Act
        PersonDTO personDTO = new PersonDTO(personName, personEmail, birthdate, birthplace, father, mother);

        int personDTOHashCode = personDTO.hashCode();
        int expectedHashCode = 1246504634;

        // Assert
        assertEquals(expectedHashCode, personDTOHashCode);
    }


}