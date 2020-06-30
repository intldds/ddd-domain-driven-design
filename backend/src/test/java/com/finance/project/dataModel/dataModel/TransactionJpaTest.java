package com.finance.project.dataModel.dataModel;

import com.finance.project.domainLayer.domainEntities.vosShared.LedgerID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransactionJpaTest {

    //Constructors

    @Test
    @DisplayName("TransactionJpa | constructor")
    public void transactionJpaConstructor(){

        //Arrange

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

        //Assert

        assertEquals(transactionJpa.getCategoryID(),categoryID);
        assertEquals(transactionJpa.getType(),type);
        assertEquals(transactionJpa.getDescription(),salaryJanuaryDescription);
        assertEquals(transactionJpa.getAmount(),amout);
        assertEquals(transactionJpa.getDate(),date);
        assertEquals(transactionJpa.getDebitAccountID(),accountJpaDebit);
        assertEquals(transactionJpa.getCreditAccountID(),accountJpaCredit);
        assertEquals(transactionJpa.getLedger(),ledgerJpa);
    }

    @Test
    @DisplayName("TransactionJpa | constructorNr2")
    public void transactionJpaConstructorNr2(){

        //Arrange

        //Arrange TransactionID

        long id = 1234567;

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

        TransactionJpa transactionJpa = new TransactionJpa(id, categoryID,type,salaryJanuaryDescription,amout,date,accountJpaDebit,accountJpaCredit,ledgerJpa);

        //Assert

        assertEquals(transactionJpa.getId(), id);
        assertEquals(transactionJpa.getCategoryID(),categoryID);
        assertEquals(transactionJpa.getType(),type);
        assertEquals(transactionJpa.getDescription(),salaryJanuaryDescription);
        assertEquals(transactionJpa.getAmount(),amout);
        assertEquals(transactionJpa.getDate(),date);
        assertEquals(transactionJpa.getDebitAccountID(),accountJpaDebit);
        assertEquals(transactionJpa.getCreditAccountID(),accountJpaCredit);
        assertEquals(transactionJpa.getLedger(),ledgerJpa);
    }


    //Sets

    @Test
    @DisplayName("TransactionJpa | sets")
    public void transactionJpaConstructorSets(){

        //Arrange

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

        //Id

        Long id = 25l;

        //Transaction

        TransactionJpa transactionJpa = new TransactionJpa();
        transactionJpa.setId(id);
        transactionJpa.setCategoryID(categoryID);
        transactionJpa.setType(type);
        transactionJpa.setDescription(salaryJanuaryDescription);
        transactionJpa.setAmount(amout);
        transactionJpa.setDate(date);
        transactionJpa.setDebitAccountID(accountJpaDebit);
        transactionJpa.setCreditAccountID(accountJpaCredit);
        transactionJpa.setLedger(ledgerJpa);

        //Assert

        assertEquals(transactionJpa.getId(),id);
        assertEquals(transactionJpa.getCategoryID(),categoryID);
        assertEquals(transactionJpa.getType(),type);
        assertEquals(transactionJpa.getDescription(),salaryJanuaryDescription);
        assertEquals(transactionJpa.getAmount(),amout);
        assertEquals(transactionJpa.getDate(),date);
        assertEquals(transactionJpa.getDebitAccountID(),accountJpaDebit);
        assertEquals(transactionJpa.getCreditAccountID(),accountJpaCredit);
        assertEquals(transactionJpa.getLedger(),ledgerJpa);
    }


    //Equals

    @Test
    @DisplayName("TransactionJpa | Equals - SameObject")
    public void transactionJpaEqualsSameObject(){

        //Arrange

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

        //Assert

        assertTrue(transactionJpa.equals(transactionJpa));
    }

    @Test
    @DisplayName("TransactionJpa | Equals - DifferentObject")
    public void transactionJpaEqualsDifferentObject(){

        //Arrange

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

        //Assert

        assertFalse(transactionJpa.equals(ledgerJpa));
    }


    @Test
    @DisplayName("TransactionJpa | Equals - DifferentObject - Same")
    public void transactionJpaEqualsDifferentObjectSame(){

        //Arrange

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

        TransactionJpa transactionJpaA = new TransactionJpa(categoryID,type,salaryJanuaryDescription,amout,date,accountJpaDebit,accountJpaCredit,ledgerJpa);
        TransactionJpa transactionJpaB = new TransactionJpa(categoryID,type,salaryJanuaryDescription,amout,date,accountJpaDebit,accountJpaCredit,ledgerJpa);

        //Assert

        assertTrue(transactionJpaA.equals(transactionJpaB));
    }

    @Test
    @DisplayName("TransactionJpa | Equals HappyPath")
    public void transactionJpaEqualsHappyPath(){

        //Arrange

        //Arrange TransactionID

        long id = 1234567;

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

        TransactionJpa transactionJpa = new TransactionJpa(id, categoryID,type,salaryJanuaryDescription,amout,date,accountJpaDebit,accountJpaCredit,ledgerJpa);

        TransactionJpa newTransactionJpa = new TransactionJpa(id, categoryID, type, salaryJanuaryDescription, amout, date, accountJpaDebit, accountJpaDebit, ledgerJpa);

        boolean result = transactionJpa.equals(newTransactionJpa);

        //Assert

        assertTrue(transactionJpa.equals(newTransactionJpa));
        assertEquals(true,transactionJpa.equals(newTransactionJpa) );


    }

    @Test
    @DisplayName("TransactionJpa | Equals - Null")
    public void transactionJpaEqualsNull(){

        //Arrange

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

        TransactionJpa transactionJpaA = new TransactionJpa(categoryID,type,salaryJanuaryDescription,amout,date,accountJpaDebit,accountJpaCredit,ledgerJpa);
        TransactionJpa transactionJpaB = null;

        //Assert

        assertFalse(transactionJpaA.equals(transactionJpaB));
    }

    @Test
    @DisplayName("TransactionJpa | Equals - No Instance Of")
    public void transactionJpaEqualsNoInstanceOf(){

        //Arrange

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

        TransactionJpa transactionJpaA = new TransactionJpa(categoryID,type,salaryJanuaryDescription,amout,date,accountJpaDebit,accountJpaCredit,ledgerJpa);


        //Assert

        assertFalse(transactionJpaA.equals(accountJpaCredit));
    }



    @Test
    @DisplayName("TransactionJpa | Equals - HashCode")
    public void transactionJpaHashCode(){

        //Arrange

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

        TransactionJpa transactionJpaA = new TransactionJpa(categoryID,type,salaryJanuaryDescription,amout,date,accountJpaDebit,accountJpaCredit,ledgerJpa);

        int transactionJpaBHashCode = transactionJpaA.hashCode();
        int expectedHashCode = 31;

        //Assert

        assertEquals(transactionJpaBHashCode,expectedHashCode);
    }

    @Test
    @DisplayName("TransactionJpa | ToString")
    public void transactionJpaToString(){

        //Arrange

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
        String id = "8bee89c4-9647-4863-abc8-0e5bafabe047";
        ledgerId.setLedgerID(id);

        LedgerJpa ledgerJpa = new LedgerJpa();
        ledgerJpa.setId(ledgerId);

        //Transaction

        TransactionJpa transactionJpaA = new TransactionJpa(categoryID,type,salaryJanuaryDescription,amout,date,accountJpaDebit,accountJpaCredit,ledgerJpa);

        //Expected

        String expected ="TransactionJpa{id=0, type='Continental Mabor', description='Salary from my January from Continental Mabor', amount=2546.23, date='27-05-2020', categoryID=CategoryJpa{id=AbstractIdJpa{ownerID='alexandre@gmail.com', denomination='Salary'}}, debitAccountID=AccountJpa{id=AbstractIdJpa{ownerID='mabor@gmail.com', denomination='Company'}, description='Company account'}, creditAccountID=AccountJpa{id=AbstractIdJpa{ownerID='alexandre@gmail.com', denomination='Salary'}, description='Salary from my January from Continental Mabor'}, ledger=Ledger {id='LedgerID{id='8bee89c4-9647-4863-abc8-0e5bafabe047'}'}}";

        //Assert

        assertEquals(expected,transactionJpaA.toString());
    }

}