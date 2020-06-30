package com.finance.project.dataModel.dataModel;

import com.finance.project.domainLayer.domainEntities.vosShared.PersonID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonJpaTest {

    @Test
    @DisplayName("PersonJpa | constructor")
    public void personJpaConstructor() {

        //Arrange

        String email = "paulo@gmail.com";
        String name = "Paulo Fontes";
        String birthdate = "15-03-1993";
        String birthplace = "Vila Nova de Gaia";

        PersonID personID = PersonID.createPersonID(email);

        //Act

        PersonJpa personJpa = new PersonJpa(email, name, birthdate, birthplace);

        //Assert

        Assertions.assertEquals(personJpa.getId(), personID);
        assertEquals(personJpa.getName(), name);
        assertEquals(personJpa.getBirthdate(), birthdate);
        assertEquals(personJpa.getBirthplace(), birthplace);
    }

    //Sets e Gets

    @Test
    @DisplayName("PersonJpa | sets")
    public void personJpaSets() {

        //Arrange

        String email = "paulo@gmail.com";
        String name = "Paulo Fontes";
        String birthdate = "15-03-1993";
        String birthplace = "Vila Nova de Gaia";

        PersonID personID = PersonID.createPersonID(email);

        //Act

        PersonJpa personJpa = new PersonJpa();
        personJpa.setId(personID);
        personJpa.setName(name);
        personJpa.setBirthdate(birthdate);
        personJpa.setBirthplace(birthplace);

        //Assert

        Assertions.assertEquals(personJpa.getId(), personID);
        assertEquals(personJpa.getName(), name);
        assertEquals(personJpa.getBirthdate(), birthdate);
        assertEquals(personJpa.getBirthplace(), birthplace);
    }



    //ToString

    @Test
    @DisplayName("PersonJpa | ToString")
    public void personJpaToString() {

        //Arrange

        String email = "paulo@gmail.com";
        String name = "Paulo Fontes";
        String birthdate = "15-03-1993";
        String birthplace = "Vila Nova de Gaia";

        PersonID personID = PersonID.createPersonID(email);

        //Act

        PersonJpa personJpa = new PersonJpa(email, name, birthdate, birthplace);

        //Assert

        assertEquals("Person {id='PersonID{email=Email{email='paulo@gmail.com'}}'}", personJpa.toString());

    }

    //Equals

    @Test
    @DisplayName("PersonJpa | Equals: Same Object")
    public void personJpaSameObject() {

        //Arrange

        String email = "paulo@gmail.com";
        String name = "Paulo Fontes";
        String birthdate = "15-03-1993";
        String birthplace = "Vila Nova de Gaia";

        PersonID personID = PersonID.createPersonID(email);

        //Act

        PersonJpa personJpa = new PersonJpa(email, name, birthdate, birthplace);

        boolean result = personJpa.equals(personJpa);

        //Assert
        assertEquals(true, result);

    }

    @Test
    @DisplayName("PersonJpa | Equals: HappyCase")
    public void personJpaHappyCase() {

        //Arrange

        String email = "paulo@gmail.com";
        String name = "Paulo Fontes";
        String birthdate = "15-03-1993";
        String birthplace = "Vila Nova de Gaia";

        PersonID personID = PersonID.createPersonID(email);

        //Act

        PersonJpa personJpa = new PersonJpa(email, name, birthdate, birthplace);

        PersonJpa newPersonJpa = new PersonJpa(email, name, birthdate, birthplace);

        boolean result = personJpa.equals(newPersonJpa);

        //Assert
        assertEquals(true, result);

    }

    @Test
    @DisplayName("PersonJpa | Equals: null")
    public void personJpaNull() {

        //Arrange

        String email = "paulo@gmail.com";
        String name = "Paulo Fontes";
        String birthdate = "15-03-1993";
        String birthplace = "Vila Nova de Gaia";

        PersonID personID = PersonID.createPersonID(email);

        //Act

        PersonJpa personJpa = new PersonJpa(email, name, birthdate, birthplace);

        PersonJpa newPersonJpa = null;

        boolean result = personJpa.equals(newPersonJpa);

        //Assert
        assertEquals(false, result);

    }

    @Test
    @DisplayName("PersonJpa | Equals: No Instance Of")
    public void personJpaNoInstanceOf() {

        //Arrange

        String email = "paulo@gmail.com";
        String name = "Paulo Fontes";
        String birthdate = "15-03-1993";
        String birthplace = "Vila Nova de Gaia";

        PersonID personID = PersonID.createPersonID(email);

        //Act

        PersonJpa personJpa = new PersonJpa(email, name, birthdate, birthplace);


        boolean result = personJpa.equals(email);

        //Assert
        assertEquals(false, result);

    }

    @Test
    @DisplayName("PersonJpa | Equals: SadCase")
    public void personJpaSadCase() {

        //Arrange

        String email = "paulo@gmail.com";
        String name = "Paulo Fontes";
        String birthdate = "15-03-1993";
        String birthplace = "Vila Nova de Gaia";

        PersonID personID = PersonID.createPersonID(email);

        String anotherEmail = "alexandre@gmail.com";

        //Act

        PersonJpa personJpa = new PersonJpa(email, name, birthdate, birthplace);

        PersonJpa newPersonJpa = new PersonJpa(anotherEmail, name, birthdate, birthplace);

        boolean result = personJpa.equals(newPersonJpa);

        //Assert
        assertEquals(false, result);

    }

    //HashCode

    @Test
    @DisplayName("PersonJpa | HashCode")
    public void personJpaHashCode() {

        //Arrange

        String email = "paulo@gmail.com";
        String name = "Paulo Fontes";
        String birthdate = "15-03-1993";
        String birthplace = "Vila Nova de Gaia";

        PersonID personID = PersonID.createPersonID(email);

        //Act

        PersonJpa personJpa = new PersonJpa(email, name, birthdate, birthplace);

        PersonJpa newPersonJpa = new PersonJpa(email, name, birthdate, birthplace);

        boolean result = personJpa.hashCode() == newPersonJpa.hashCode();

        //Assert

        assertTrue(personJpa.hashCode()==personJpa.hashCode());
        assertTrue(personJpa.hashCode() == newPersonJpa.hashCode());
        assertEquals(true, result);

    }

    @Test
    @DisplayName("PersonJpa | HashCode:SadCase")
    public void personJpaHashCode_SadCase() {

        //Arrange

        String email = "paulo@gmail.com";
        String name = "Paulo Fontes";
        String birthdate = "15-03-1993";
        String birthplace = "Vila Nova de Gaia";

        //Arrange Another Person

        String newEmail = "jonas@gmail.com";
        String newName = "Jonas";
        String newBirthdate = "15-03-1996";
        String newBirthplace = "Porto";



        //Act

        PersonJpa personJpa = new PersonJpa(email, name, birthdate, birthplace);
        PersonJpa newPersonJpa = new PersonJpa(newEmail, newName, newBirthdate, newBirthplace);


        boolean result = personJpa.hashCode() == newPersonJpa.hashCode();

        //Assert

        assertFalse(personJpa.hashCode() == newPersonJpa.hashCode());
        assertEquals(false, result);

    }

}
