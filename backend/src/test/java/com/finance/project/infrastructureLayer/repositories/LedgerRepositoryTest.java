package com.finance.project.infrastructureLayer.repositories;

import com.finance.project.controllerLayer.integrationTests.AbstractTest;
import com.finance.project.domainLayer.domainEntities.aggregates.ledger.Ledger;
import com.finance.project.domainLayer.domainEntities.aggregates.ledger.Transaction;
import com.finance.project.domainLayer.domainEntities.aggregates.person.Person;
import com.finance.project.domainLayer.domainEntities.vosShared.AccountID;
import com.finance.project.domainLayer.domainEntities.vosShared.CategoryID;
import com.finance.project.domainLayer.domainEntities.vosShared.LedgerID;
import com.finance.project.domainLayer.domainEntities.vosShared.PersonID;
import com.finance.project.dtos.dtosAssemblers.DeletePersonTransactionDTOAssembler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import com.finance.project.dtos.dtos.DeletePersonTransactionDTO;
import com.finance.project.dtos.dtos.UpdatePersonTransactionDTO;
import com.finance.project.dtos.dtosAssemblers.UpdatePersonTransactionDTOAssembler;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class LedgerRepositoryTest extends AbstractTest {

    @Autowired
    LedgerRepository ledgerRepository;

    // Save

    @Test
    @DisplayName("Test for save")
    public void saveLedger() {

        //Arrange

        Ledger ledger = Ledger.createLedger();

        //Act

        Ledger savedLedger = ledgerRepository.save(ledger);

        //Assert
        assertEquals(ledger, savedLedger);
    }

    //Find by ID

    @Test
    @DisplayName("Test for FindByID : Sucess")
    public void findByID_Sucess() {

        //Arrange

        LedgerID id = LedgerID.createLedgerID();

        Ledger ledger = new Ledger(id);

        //Act

        Ledger savedLedger = ledgerRepository.save(ledger);

        Optional<Ledger> ledgerJpa = ledgerRepository.findById(id);

        //Assert

        assertEquals(ledgerJpa.get(), savedLedger);


    }

    @Test
    @DisplayName("Test for FindByID : Not find")
    public void findByID_NotFind() {

        //Arrange

        LedgerID id = LedgerID.createLedgerID();

        LedgerID newId = LedgerID.createLedgerID();

        //Act

        Optional<Ledger> ledgerJpa = ledgerRepository.findById(id);

        //Assert

        assertEquals(ledgerJpa, ledgerRepository.findById(newId));

    }


    // Exists

    @Test
    @DisplayName("Test for exist")
    public void existLedger() {

        //Arrange

        LedgerID ledgerID = LedgerID.createLedgerID();
        Ledger ledger = new Ledger(ledgerID);

        //Act

        Ledger savedLedger = ledgerRepository.save(ledger);

        Boolean existLedger = ledgerRepository.exists(ledgerID);

        //Assert
        assertEquals(true, existLedger);
    }

    // Count

    @Test
    @DisplayName("Test for count")
    public void countLedger() {

        //Arrange

        LedgerID ledgerID = LedgerID.createLedgerID();
        Ledger ledger = new Ledger(ledgerID);

        //Act

        Ledger savedLedger = ledgerRepository.save(ledger);
        long countLedger = ledgerRepository.count();

        //Assert
        assertEquals(countLedger, countLedger);
    }



    //Update Person Transaction

    @Test
    @DisplayName("Test for updatePersonTransaction() | Success")
    void updatePersonTransaction_Success() {

        //Arrange

        //Person info
        String pauloEmail = "francis@gmail.com";
        String pauloName = "Paulo Fontes";
        LocalDate pauloBirthdate = LocalDate.of(1993, 03, 15);
        String pauloBirthplace = "Vila Nova de Gaia";

        PersonID personId = PersonID.createPersonID(pauloEmail);
        Person person = Person.createPerson(pauloEmail, pauloName, pauloBirthdate, pauloBirthplace);

        //Transaction info
        final String denominationCategory = "Salary";
        final String type = "debit";
        final String description = "May Salary";
        final double amount = 1500.00;
        final String denominationAccountDeb = "Company";
        final String denominationAccountCred = "Bank Account";

        final double amount2 = 500.00;
        final double amount3 = 1000.00;


        // Create Ledger
        LedgerID ledgerID = LedgerID.createLedgerID();
        Ledger ledger = new Ledger(ledgerID);


        //To Search
        CategoryID categoryID = CategoryID.createCategoryID(denominationCategory, personId);
        AccountID debAccountID = AccountID.createAccountID(denominationAccountDeb, personId);
        AccountID credAccountID = AccountID.createAccountID(denominationAccountCred, personId);

        Transaction transaction = Transaction.createTransaction(categoryID, type, description, amount, LocalDate.now(), debAccountID, credAccountID);
        Transaction transaction1 = Transaction.createTransaction(categoryID, type, description, amount2, LocalDate.now(), debAccountID, credAccountID);
        Transaction transaction2 = Transaction.createTransaction(categoryID, type, description, amount3, LocalDate.now(), debAccountID, credAccountID);

        ledger.createAndAddTransaction(categoryID, type, description, amount2, debAccountID, credAccountID);
        ledger.createAndAddTransaction(categoryID, type, description, amount3, debAccountID, credAccountID);

        List<Transaction> transactions = ledger.getRecords();

        ledgerRepository.addAndSaveTransaction(ledger);

        //DTO
        UpdatePersonTransactionDTO updatePersonTransactionDTO = UpdatePersonTransactionDTOAssembler.createDTOFromPrimitiveTypes(1, pauloEmail, denominationCategory, type, description, amount, denominationAccountDeb, denominationAccountCred);
        ledgerRepository.updatePersonTransaction(ledger, updatePersonTransactionDTO);

        assertEquals(transactions.size(), 2);
    }

    //Delete Person Transaction

    @Test
    @DisplayName("Test for deletePersonTransaction() | Success")
    void deletePersonTransaction_Success() {

        //Arrange

        //Person info
        String pauloEmail = "paulo@gmail.com";
        String pauloName = "Paulo Fontes";
        LocalDate pauloBirthdate = LocalDate.of(1993, 03, 15);
        String pauloBirthplace = "Vila Nova de Gaia";

        PersonID personId = PersonID.createPersonID(pauloEmail);
        Person person = Person.createPerson(pauloEmail, pauloName, pauloBirthdate, pauloBirthplace);

        //Transaction info
        final String denominationCategory = "Salary";
        final String type = "debit";
        final String description = "May Salary";
        final double amount = 1500.00;
        final String denominationAccountDeb = "Company";
        final String denominationAccountCred = "Bank Account";

        final double amount2 = 500.00;
        final double amount3 = 1000.00;


        //Create Ledger

        LedgerID ledgerID = LedgerID.createLedgerID();
        Ledger ledger = new Ledger(ledgerID);


        //To Search
        CategoryID categoryID = CategoryID.createCategoryID(denominationCategory, personId);
        AccountID debAccountID = AccountID.createAccountID(denominationAccountDeb, personId);
        AccountID credAccountID = AccountID.createAccountID(denominationAccountCred, personId);

        Transaction transaction = Transaction.createTransaction(categoryID, type, description, amount, LocalDate.now(), debAccountID, credAccountID);
        Transaction transaction1 = Transaction.createTransaction(categoryID, type, description, amount2, LocalDate.now(), debAccountID, credAccountID);
        Transaction transaction2 = Transaction.createTransaction(categoryID, type, description, amount3, LocalDate.now(), debAccountID, credAccountID);

        ledger.createAndAddTransaction(categoryID, type, description, amount2, debAccountID, credAccountID);
        ledger.createAndAddTransaction(categoryID, type, description, amount3, debAccountID, credAccountID);

        List<Transaction> transactions = ledger.getRecords();

        ledgerRepository.addAndSaveTransaction(ledger);

        //DTO
        DeletePersonTransactionDTO deletePersonTransactionDTO = DeletePersonTransactionDTOAssembler.createDTOFromPrimitiveTypes(1, pauloEmail);


        ledgerRepository.deletePersonTransaction(ledger, deletePersonTransactionDTO);


        assertEquals(transactions.size(), 2);


    }

}