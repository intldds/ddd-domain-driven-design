package com.finance.project.dataModel.dataModel;

import com.finance.project.domainLayer.domainEntities.vosShared.LedgerID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class LedgerJpaTest {

    @Test
    @DisplayName("LedgerJpa constructor and Set")
    public void constructorIsEmptyAndSet(){

        //Arrange
        LedgerID ledgerId = LedgerID.createLedgerID();

        //Act
        LedgerJpa ledgerJpa = new LedgerJpa();
        ledgerJpa.setId(ledgerId);

        //Assert

        Assertions.assertEquals(ledgerJpa.getId().getLedgerID(), ledgerId.getLedgerID());

    }

    @Test
    @DisplayName("LedgerJpa constructor")
    public void constructorLedgerJpaString(){

        //Arrange
        String ledgerId = UUID.randomUUID().toString();


        //Act
        LedgerJpa ledgerJpa = new LedgerJpa(ledgerId);

        //Assert

        Assertions.assertEquals(ledgerJpa.getId().getLedgerID(), ledgerId);
    }

    @Test
    @DisplayName("LedgerJpa constructor")
    public void constructorLedgerJpaLedgerID(){

        //Arrange
        LedgerID ledgerId = LedgerID.createLedgerID();

        //Act
        LedgerJpa ledgerJpa = new LedgerJpa(ledgerId);

        //Assert

        Assertions.assertEquals(ledgerJpa.getId().getLedgerID(), ledgerId.getLedgerID());
    }

    @Test
    @DisplayName("LedgerId - ToString and HashCode" )
    public void LedgerIDToStringAndHashCode() {

        //Arrange
        LedgerID ledgerId = LedgerID.createLedgerID();
        String id = "8bee89c4-9647-4863-abc8-0e5bafabe047";
        ledgerId.setLedgerID(id);


        //Act
        LedgerJpa ledgerJpa = new LedgerJpa();
        ledgerJpa.setId(ledgerId);

        int ledgerJpaHashCode = ledgerJpa.hashCode();
        int expectedHashCode = 1164029691;

        //Assert

        assertEquals("Ledger {id='LedgerID{id='8bee89c4-9647-4863-abc8-0e5bafabe047'}'}", ledgerJpa.toString());
        assertEquals(expectedHashCode,ledgerJpaHashCode);
    }

    @Test
    @DisplayName("LedgerId - Equals - Same Object")
    public void LedgerIDEqualsSameObject() {

        //Arrange
        LedgerID ledgerId = LedgerID.createLedgerID();


        //Act
        LedgerJpa ledgerJpa = new LedgerJpa(ledgerId);

        //Assert

        assertTrue(ledgerJpa.equals(ledgerJpa));
    }

    @Test
    @DisplayName("LedgerId - Equals - null")
    public void LedgerIDEqualsNull() {

        //Arrange
        LedgerID ledgerIdA = LedgerID.createLedgerID();
        LedgerID ledgerIDB = LedgerID.createLedgerID();


        //Act
        LedgerJpa ledgerJpaA = new LedgerJpa(ledgerIdA);
        LedgerJpa ledgerJpaB = null;

        //Assert

        assertFalse(ledgerJpaA.equals(ledgerJpaB));
    }

    @Test
    @DisplayName("LedgerId - Equals - No Instance Of")
    public void LedgerIDEqualsNoInstanceOf() {

        //Arrange
        LedgerID ledgerIdA = LedgerID.createLedgerID();
        LedgerID ledgerIDB = LedgerID.createLedgerID();


        //Act
        LedgerJpa ledgerJpaA = new LedgerJpa(ledgerIdA);


        //Assert

        assertFalse(ledgerJpaA.equals(ledgerIdA));
    }

    @Test
    @DisplayName("LedgerId - Equals - Different Object")
    public void LedgerIDEqualsDifferentObject() {

        //Arrange
        LedgerID ledgerIdA = LedgerID.createLedgerID();
        LedgerID ledgerIDB = LedgerID.createLedgerID();


        //Act
        LedgerJpa ledgerJpaA = new LedgerJpa(ledgerIdA);
        LedgerJpa ledgerJpaB = new LedgerJpa(ledgerIDB);

        //Assert

        assertFalse(ledgerJpaA.equals(ledgerJpaB));
    }

    @Test
    @DisplayName("LedgerJpa setTransaction")
    public void LedgerJpaSetTransaction(){

        //CategoryJpa

        String ownerID = "alexandre@gmail.com";
        String salaryDenomination = "Salary";

        CategoryJpa categoryID = new CategoryJpa(ownerID, salaryDenomination);

        //type

        String type = "Continental Mabor";

        //Description

        String salaryJanuaryDescription = "Salary from my January from Continental Mabor";

        //Amount

        Double amout = 2546.23;

        //Date

        String date = "27-05-2020";

        //AccountsJPA

        String ownerIDMabor = "mabor@gmail.com";
        String denominationMabor = "Company";
        String descriptionMabor = "Company account";

        //DebitAccount

        AccountJpa accountJpaDebit = new AccountJpa(ownerIDMabor, denominationMabor, descriptionMabor);

        //CreditAccount

        AccountJpa accountJpaCredit = new AccountJpa(ownerID,salaryDenomination,salaryJanuaryDescription);

        //LedgerJPA

        LedgerID ledgerId = LedgerID.createLedgerID();
        LedgerJpa ledgerJpa = new LedgerJpa(ledgerId);

        //Transaction

        TransactionJpa transactionJpa = new TransactionJpa(categoryID,type,salaryJanuaryDescription,amout,date,accountJpaDebit,accountJpaCredit,ledgerJpa);

        List<TransactionJpa> transactionJpaList = new ArrayList<>();
        transactionJpaList.add(transactionJpa);

        ledgerJpa.setTransactions(transactionJpaList);

        //Assert

        assertEquals(ledgerJpa.getTransactions(),transactionJpaList);
    }

}