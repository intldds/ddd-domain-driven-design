package com.finance.project.dataModel.dataModel;

import com.finance.project.domainLayer.domainEntities.vosShared.PersonID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SiblingJpaTest {

    //Constructor

    @Test
    @DisplayName("SiblingsJpa | Constructor and HashCode")
    public void SiblingsJpaConstructorAndHashCode() {

        //Arrange

        String email = "paulo@gmail.com";
        String name = "Paulo Fontes";
        String birthdate = "15-03-1993";
        String birthplace = "Vila Nova de Gaia";

        //Arrange Siblings

        String helderEmail = "helder@gmail.com";

        //Act

        PersonJpa personJpa = new PersonJpa(email, name, birthdate, birthplace);

        PersonID personID = PersonID.createPersonID(helderEmail);

        SiblingJpa.PersonPersonIdJpa personPersonIdJpa = new SiblingJpa.PersonPersonIdJpa(personJpa,personID);

        int personPersonIdJpaHashCode = personPersonIdJpa.hashCode();
        int expectedHashCode = -185256388;

        //Assert

        Assertions.assertEquals(personPersonIdJpa.getPersonID(), personID);
        assertEquals(personPersonIdJpa.getPersonJpa(), personJpa);
        assertEquals(expectedHashCode,personPersonIdJpaHashCode);
    }

    @Test
    @DisplayName("SiblingsJpa | Empty Constructor ")
    public void SiblingsJpaConstructorEmpty() {

        //Arrange

        String email = "paulo@gmail.com";
        String name = "Paulo Fontes";
        String birthdate = "15-03-1993";
        String birthplace = "Vila Nova de Gaia";

        //Arrange Siblings

        String helderEmail = "helder@gmail.com";

        //Act

        PersonJpa personJpa = new PersonJpa(email, name, birthdate, birthplace);

        PersonID personID = PersonID.createPersonID(helderEmail);

        SiblingJpa.PersonPersonIdJpa personPersonIdJpa = new SiblingJpa.PersonPersonIdJpa();
        personPersonIdJpa.setPersonID(personID);
        personPersonIdJpa.setPersonJpa(personJpa);

        //Assert

        Assertions.assertEquals(personPersonIdJpa.getPersonID(), personID);
        assertEquals(personPersonIdJpa.getPersonJpa(), personJpa);

    }

    //Equals

    @Test
    @DisplayName("SiblingsJpa | Equals - Same Object ")
    public void SiblingsJpaConstructorEqualsSameObject() {

        //Arrange

        String email = "paulo@gmail.com";
        String name = "Paulo Fontes";
        String birthdate = "15-03-1993";
        String birthplace = "Vila Nova de Gaia";

        //Arrange Siblings

        String helderEmail = "helder@gmail.com";

        //Act

        PersonJpa personJpa = new PersonJpa(email, name, birthdate, birthplace);

        PersonID personID = PersonID.createPersonID(helderEmail);

        SiblingJpa.PersonPersonIdJpa personPersonIdJpa = new SiblingJpa.PersonPersonIdJpa();
        personPersonIdJpa.setPersonID(personID);
        personPersonIdJpa.setPersonJpa(personJpa);

        //Assert

        assertTrue(personPersonIdJpa.equals(personPersonIdJpa));

    }

    @Test
    @DisplayName("SiblingsJpa | Equals - Different Object ")
    public void SiblingsJpaConstructorEqualsDifferentObject() {

        //Arrange

        String email = "paulo@gmail.com";
        String name = "Paulo Fontes";
        String birthdate = "15-03-1993";
        String birthplace = "Vila Nova de Gaia";

        //Arrange Siblings

        String helderEmail = "helder@gmail.com";

        //Act

        PersonJpa personJpa = new PersonJpa(email, name, birthdate, birthplace);

        PersonID personID = PersonID.createPersonID(helderEmail);

        SiblingJpa.PersonPersonIdJpa personPersonIdJpa = new SiblingJpa.PersonPersonIdJpa();
        personPersonIdJpa.setPersonID(personID);
        personPersonIdJpa.setPersonJpa(personJpa);

        //Assert

        assertFalse(personPersonIdJpa.equals(personID));

    }

    @Test
    @DisplayName("SiblingsJpa | Equals - Different Object - Same")
    public void SiblingsJpaConstructorEqualsDifferentObjectSame() {

        //Arrange

        String email = "paulo@gmail.com";
        String name = "Paulo Fontes";
        String birthdate = "15-03-1993";
        String birthplace = "Vila Nova de Gaia";

        //Arrange Siblings

        String helderEmail = "helder@gmail.com";

        //Act

        PersonJpa personJpa = new PersonJpa(email, name, birthdate, birthplace);

        PersonID personID = PersonID.createPersonID(helderEmail);

        SiblingJpa.PersonPersonIdJpa personPersonIdJpaA = new SiblingJpa.PersonPersonIdJpa();
        personPersonIdJpaA.setPersonID(personID);
        personPersonIdJpaA.setPersonJpa(personJpa);

        SiblingJpa.PersonPersonIdJpa personPersonIdJpaB = new SiblingJpa.PersonPersonIdJpa(personJpa, personID);

        //Assert

        assertTrue(personPersonIdJpaA.equals(personPersonIdJpaB));
    }


        @Test
        @DisplayName("SiblingsJpa | Equals - Different Object - Null")
        public void SiblingsJpaConstructorEqualsNull() {

            //Arrange

            String email = "paulo@gmail.com";
            String name = "Paulo Fontes";
            String birthdate = "15-03-1993";
            String birthplace = "Vila Nova de Gaia";

            //Arrange Siblings

            String helderEmail = "helder@gmail.com";

            //Act

            PersonJpa personJpa = new PersonJpa(email, name, birthdate, birthplace);

            PersonID personID = PersonID.createPersonID(helderEmail);

            SiblingJpa.PersonPersonIdJpa personPersonIdJpaA = new SiblingJpa.PersonPersonIdJpa();
            personPersonIdJpaA.setPersonID(personID);
            personPersonIdJpaA.setPersonJpa(personJpa);

            SiblingJpa.PersonPersonIdJpa personPersonIdJpaB = null;

            boolean result = personPersonIdJpaA.equals(personPersonIdJpaB);

            //Assert

            assertEquals(false, result);
            assertFalse(personPersonIdJpaA.equals(personPersonIdJpaB));

    }

    @Test
    @DisplayName("SiblingsJpa | Equals - Different Object - No Instance of")
    public void SiblingsJpaConstructorEqualsNoInstanceOf() {

        //Arrange

        String email = "paulo@gmail.com";
        String name = "Paulo Fontes";
        String birthdate = "15-03-1993";
        String birthplace = "Vila Nova de Gaia";

        //Arrange Siblings

        String helderEmail = "helder@gmail.com";

        //Act

        PersonJpa personJpa = new PersonJpa(email, name, birthdate, birthplace);

        PersonID personID = PersonID.createPersonID(helderEmail);

        SiblingJpa.PersonPersonIdJpa personPersonIdJpaA = new SiblingJpa.PersonPersonIdJpa();
        personPersonIdJpaA.setPersonID(personID);
        personPersonIdJpaA.setPersonJpa(personJpa);


        boolean result = personPersonIdJpaA.equals(helderEmail);

        //Assert

        assertEquals(false, result);
        assertFalse(personPersonIdJpaA.equals(helderEmail));

    }

    @Test
    @DisplayName("SiblingsJpa | Equals - Different Object - DifferentId")
    public void SiblingsJpaConstructorEqualsDifferentId() {

        //Arrange

        String email = "paulo@gmail.com";
        String name = "Paulo Fontes";
        String birthdate = "15-03-1993";
        String birthplace = "Vila Nova de Gaia";

        //Arrange Siblings

        String helderEmail = "helder@gmail.com";

        //Act

        PersonJpa personJpa = new PersonJpa(email, name, birthdate, birthplace);

        PersonID personID = PersonID.createPersonID(helderEmail);

        PersonID anotherPersonID = PersonID.createPersonID(email);

        SiblingJpa.PersonPersonIdJpa personPersonIdJpaA = new SiblingJpa.PersonPersonIdJpa();
        personPersonIdJpaA.setPersonID(personID);
        personPersonIdJpaA.setPersonJpa(personJpa);

        SiblingJpa.PersonPersonIdJpa personPersonIdJpaB = new SiblingJpa.PersonPersonIdJpa(personJpa, anotherPersonID);

        //Assert

        assertFalse(personPersonIdJpaA.equals(personPersonIdJpaB));
    }


    @Test
    @DisplayName("SiblingsJpa | Equals - Different Object - DifferentPersonJpa")
    public void SiblingsJpaConstructorEqualsDifferentPersonJpa() {

        //Arrange

        String email = "paulo@gmail.com";
        String name = "Paulo Fontes";
        String birthdate = "15-03-1993";
        String birthplace = "Vila Nova de Gaia";

        //Arrange Siblings

        String helderEmail = "helder@gmail.com";

        //Act

        PersonJpa personJpa = new PersonJpa(email, name, birthdate, birthplace);

        PersonJpa anotherPersonJpa = new PersonJpa(helderEmail, name, birthdate, birthplace);

        PersonID personID = PersonID.createPersonID(helderEmail);

        PersonID anotherPersonID = PersonID.createPersonID(email);

        SiblingJpa.PersonPersonIdJpa personPersonIdJpaA = new SiblingJpa.PersonPersonIdJpa();
        personPersonIdJpaA.setPersonID(personID);
        personPersonIdJpaA.setPersonJpa(personJpa);

        SiblingJpa.PersonPersonIdJpa personPersonIdJpaB = new SiblingJpa.PersonPersonIdJpa(anotherPersonJpa, anotherPersonID);

        //Assert

        assertFalse(personPersonIdJpaA.equals(personPersonIdJpaB));
    }

    //Constructors

    @Test
    @DisplayName("SiblingsJpa | Empty Constructor ")
    public void SiblingsJpaConstructorEmptyConsctructor() {

        //Arrange

        String email = "paulo@gmail.com";
        String name = "Paulo Fontes";
        String birthdate = "15-03-1993";
        String birthplace = "Vila Nova de Gaia";

        //Arrange Siblings

        String helderEmail = "helder@gmail.com";

        //Act

        PersonJpa personJpa = new PersonJpa(email, name, birthdate, birthplace);

        PersonID personID = PersonID.createPersonID(helderEmail);

        SiblingJpa siblingJpa = new SiblingJpa();

        SiblingJpa.PersonPersonIdJpa personPersonIdJpa = new SiblingJpa.PersonPersonIdJpa(personJpa,personID);

        siblingJpa.setId(personPersonIdJpa);

        //Assert

        assertEquals(personPersonIdJpa,siblingJpa.getId());

    }

    @Test
    @DisplayName("SiblingsJpa | Equals - Same Object ")
    public void SiblingsJpaConstructorEqualsSameObjectSiblings() {

        //Arrange

        String email = "paulo@gmail.com";
        String name = "Paulo Fontes";
        String birthdate = "15-03-1993";
        String birthplace = "Vila Nova de Gaia";

        //Arrange Siblings

        String helderEmail = "helder@gmail.com";

        //Act

        PersonJpa personJpa = new PersonJpa(email, name, birthdate, birthplace);

        PersonID personID = PersonID.createPersonID(helderEmail);

        SiblingJpa siblingJpa = new SiblingJpa();

        SiblingJpa.PersonPersonIdJpa personPersonIdJpa = new SiblingJpa.PersonPersonIdJpa(personJpa,personID);

        siblingJpa.setId(personPersonIdJpa);

        //Assert

        assertTrue(siblingJpa.equals(siblingJpa));

    }

    @Test
    @DisplayName("SiblingsJpa | Equals - Different Object - Same")
    public void SiblingsJpaConstructorEqualsDifferentObjectSiblingsSame() {

        //Arrange

        String email = "paulo@gmail.com";
        String name = "Paulo Fontes";
        String birthdate = "15-03-1993";
        String birthplace = "Vila Nova de Gaia";

        //Arrange Siblings

        String helderEmail = "helder@gmail.com";

        //Act

        PersonJpa personJpa = new PersonJpa(email, name, birthdate, birthplace);

        PersonID personID = PersonID.createPersonID(helderEmail);

        SiblingJpa siblingJpa = new SiblingJpa();

        SiblingJpa.PersonPersonIdJpa personPersonIdJpa = new SiblingJpa.PersonPersonIdJpa(personJpa,personID);

        siblingJpa.setId(personPersonIdJpa);

        int personPersonIdJpaHashCode = siblingJpa.hashCode();
        int expectedHashCode = -185256357;

        //Assert

        assertTrue(siblingJpa.equals(siblingJpa));
        assertEquals(personPersonIdJpaHashCode,expectedHashCode);

    }

    @Test
    @DisplayName("SiblingsJpa | Equals - Different Object ")
    public void SiblingsJpaConstructorEqualsSameObjectDifferentSiblings() {

        //Arrange

        String email = "paulo@gmail.com";
        String name = "Paulo Fontes";
        String birthdate = "15-03-1993";
        String birthplace = "Vila Nova de Gaia";

        //Arrange Siblings

        String helderEmail = "helder@gmail.com";

        //Act

        PersonJpa personJpa = new PersonJpa(email, name, birthdate, birthplace);

        PersonID personID = PersonID.createPersonID(helderEmail);

        SiblingJpa siblingJpa = new SiblingJpa();

        SiblingJpa siblingJpa1 = new SiblingJpa();

        SiblingJpa.PersonPersonIdJpa personPersonIdJpa = new SiblingJpa.PersonPersonIdJpa(personJpa,personID);

        SiblingJpa.PersonPersonIdJpa personPersonIdJpa1 = new SiblingJpa.PersonPersonIdJpa(personJpa,personID);

        siblingJpa.setId(personPersonIdJpa);

        siblingJpa1.setId(personPersonIdJpa1);

        //Assert

        assertTrue(siblingJpa.equals(siblingJpa1));
        assertEquals("AdminJpa{id=GroupPersonIdJpa{personID=PersonID{email=Email{email='paulo@gmail.com'}}, personID=PersonID{email=Email{email='helder@gmail.com'}}}}",siblingJpa.toString());

    }


}
