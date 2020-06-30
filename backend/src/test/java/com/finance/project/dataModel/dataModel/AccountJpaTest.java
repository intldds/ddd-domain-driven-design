package com.finance.project.dataModel.dataModel;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountJpaTest {

    //Constructor

    @Test
    @DisplayName("Test for createAccountJpa()")
    public void checkCreateAccountJpa() {

        //Arrange

        String ownerID = "alexandre@gmail.com";
        String denomination = "Company";
        String descritpion = "Company account";

        //Act

        AccountJpa accountJpa = new AccountJpa(ownerID, denomination, descritpion);

        //Assert

        assertEquals(ownerID, accountJpa.getId().getOwnerID());
        assertEquals(denomination, accountJpa.getId().getDenomination());
        assertEquals(descritpion, accountJpa.getDescription());
    }


    @Test
    @DisplayName("Test for createAccountJpaEmptyConstructor()")
    public void checkCreateAccountJpaEmptyConstructor() {

        //Arrange

        AccountJpa accountJpa = new AccountJpa();

        String ownerID = "alexandre@gmail.com";
        String denomination = "Company";
        String descritpion = "Company account";

        //Act

        AbstractIdJpa abstractIdJpa = new AbstractIdJpa(ownerID, denomination);

        accountJpa.setDescription(descritpion);
        accountJpa.setId(abstractIdJpa);

        //Assert
        assertEquals(ownerID, accountJpa.getId().getOwnerID());
        assertEquals(denomination, accountJpa.getId().getDenomination());
        assertEquals(descritpion, accountJpa.getDescription());
    }

    //ToString

    @Test
    @DisplayName("AccountJpa - ToString")
    public void accounJPA_toString() {

        //Arrange

        String ownerID = "alexandre@gmail.com";
        String denomination = "Company";
        String descritpion = "Company account";


        //Assert
        AccountJpa accountJpa = new AccountJpa(ownerID, denomination, descritpion);

        //Act

        assertEquals("AccountJpa{id=AbstractIdJpa{ownerID='alexandre@gmail.com', denomination='Company'}, description='Company account'}", accountJpa.toString());


    }

    //Equals

    @Test
    @DisplayName("Test AccountJpaEquals : Same Object")
    public void accountJpaEqual_SameObject(){

        //Arrange

        String ownerID = "alexandre@gmail.com";
        String denomination = "Company";
        String descritpion = "Company account";

        //Act

        AccountJpa accountJpa = new AccountJpa(ownerID, denomination, descritpion);

        boolean result = accountJpa.equals(accountJpa);

        //Assert

        assertEquals(true, result);

    }

    @Test
    @DisplayName("Test AccountJpaEquals : HappyCase")
    public void accountJpaEqual_HappyCase(){

        //Arrange

        String ownerID = "alexandre@gmail.com";
        String denomination = "Company";
        String descritpion = "Company account";

        //Act

        AccountJpa accountJpa = new AccountJpa(ownerID, denomination, descritpion);
        AccountJpa newAccountJpa = new AccountJpa(ownerID, denomination, descritpion);

        boolean result = accountJpa.equals(newAccountJpa);

        //Assert

        assertEquals(true, result);

    }

    @Test
    @DisplayName("Test AccountJpaEquals : null")
    public void accountJpaEqual_null(){

        //Arrange

        String ownerID = "alexandre@gmail.com";
        String denomination = "Company";
        String descritpion = "Company account";

        //Act

        AccountJpa accountJpa = new AccountJpa(ownerID, denomination, descritpion);
        AccountJpa newAccountJpa = null;

        boolean result = accountJpa.equals(newAccountJpa);

        //Assert

        assertEquals(false, result);

    }

    @Test
    @DisplayName("Test AccountJpaEquals : notInstanceOf")
    public void accountJpaEqual_notInstanceOf(){

        //Arrange

        String ownerID = "alexandre@gmail.com";
        String denomination = "Company";
        String descritpion = "Company account";

        //Act

        AccountJpa accountJpa = new AccountJpa(ownerID, denomination, descritpion);


        boolean result = accountJpa.equals(descritpion);

        //Assert

        assertEquals(false, result);

    }

    @Test
    @DisplayName("Test AccountJpaEquals : sadCase - different ownerID")
    public void accountJpaEqual_SadCase_differentOwnerID(){

        //Arrange

        String ownerID = "alexandre@gmail.com";
        String denomination = "Company";
        String descritpion = "Company account";

        //Arrange second ownerID

        String ownerIDB = "paulo@gmail.com";


        //Act

        AccountJpa accountJpa = new AccountJpa(ownerID, denomination, descritpion);

        AccountJpa newAccountJpa = new AccountJpa(ownerIDB, denomination, descritpion);


        boolean result = accountJpa.equals(newAccountJpa);

        //Assert

        assertEquals(false, result);

    }

    @Test
    @DisplayName("Test AccountJpaEquals : SadCase_DifferentDenomination")
    public void accountJpaEqual_DifferentDenomination(){

        //Arrange

        String ownerID = "alexandre@gmail.com";
        String denomination = "Company";
        String descritpion = "Company account";

        //Arrange Second Denomination

        String denominationB = "Salary";


        //Act

        AccountJpa accountJpa = new AccountJpa(ownerID, denomination, descritpion);
        AccountJpa newAccountJpa = new AccountJpa(ownerID, denominationB, descritpion);


        boolean result = accountJpa.equals(newAccountJpa);

        //Assert

        assertEquals(false, result);

    }

    //HashCode

    @Test
    @DisplayName("Test AccountJpaHashcode : HappyCase")
    public void accountJpaHashCode_HappyCase(){

        //Arrange

        String ownerID = "alexandre@gmail.com";
        String denomination = "Company";
        String descritpion = "Company account";

        //Act

        AccountJpa accountJpa = new AccountJpa(ownerID, denomination, descritpion);
        AccountJpa newAccountJpa = new AccountJpa(ownerID, denomination, descritpion);

        boolean result = accountJpa.hashCode()==newAccountJpa.hashCode();

        //Assert

        assertTrue(accountJpa.hashCode()==accountJpa.hashCode());
        assertTrue(accountJpa.hashCode()==newAccountJpa.hashCode());
        assertEquals(true, result);

    }

    @Test
    @DisplayName("Test AccountJpaHashcode : SadCase")
    public void accountJpaHashCode_SadCase(){

        //Arrange

        String ownerID = "alexandre@gmail.com";
        String denomination = "Company";
        String descritpion = "Company account";

        //Arrange anotherPerson

        String anothetOwnerID = "pp@gmail.com";
        String anotherDenomination = "Comp";
        String anotherDescritpion = "Comp account";


        //Act

        AccountJpa accountJpa = new AccountJpa(ownerID, denomination, descritpion);
        AccountJpa newAccountJpa = new AccountJpa(anothetOwnerID, anotherDenomination, anotherDescritpion);

        boolean result = accountJpa.hashCode()==newAccountJpa.hashCode();


        //Assert

        assertFalse(result);
        assertEquals(false, result);

    }


}

