package com.finance.project.infrastructureLayer.repositories;

import com.finance.project.controllerLayer.integrationTests.AbstractTest;
import com.finance.project.domainLayer.domainEntities.aggregates.account.Account;
import com.finance.project.domainLayer.domainEntities.vosShared.AccountID;
import com.finance.project.domainLayer.domainEntities.vosShared.GroupID;
import com.finance.project.domainLayer.domainEntities.vosShared.PersonID;
import com.finance.project.domainLayer.repositoriesInterfaces.IAccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


public class AccountRepositoryTest extends AbstractTest {


    @Autowired
    private IAccountRepository accountRepository;

    private String hulkEmail;
    private PersonID hulkID;
    private Account hulkAccountToSave;
    private String hulkAccountDenomination;
    private String hulkAccountDescription;
    private AccountID hulkAccountID;
    private String wolverineEmail;
    private Account wolverineAccountToSave;
    private PersonID wolverineID;
    private String wolverineAccountDenomination;
    private String wolverineAccountDescription;
    private AccountID wolverineAccountID;

    @BeforeEach
    public void init() {

//        Arrange hulk
        hulkEmail = "hulk@marvel.com";

//        Create hulk PersonID
        hulkID = PersonID.createPersonID(hulkEmail);

//        Arrange hulk account
        hulkAccountDenomination = "Marvel Netflix";
        hulkAccountDescription = "Marvel account";

//        Create hulk accountID
        hulkAccountID = AccountID.createAccountID(hulkAccountDenomination, hulkID);

//        Create hulk accountToSave
        hulkAccountToSave = Account.createAccount(hulkAccountDescription, hulkAccountDenomination, hulkID);

//        Arrange wolverine
        wolverineEmail = "wolverine@marvel.com";

//        Create wolverine PersonID
        wolverineID = PersonID.createPersonID(wolverineEmail);

//        Arrange wolverine Account
        wolverineAccountDenomination = "Marvel Netflix";
        wolverineAccountDescription = "Marvel account";

//        Create wolverine accountID
        wolverineAccountID = AccountID.createAccountID(wolverineAccountDenomination, wolverineID);

//        Create hulk accountToSave
        wolverineAccountToSave = Account.createAccount(wolverineAccountDescription, wolverineAccountDenomination, wolverineID);

    }

    @Test
    @DisplayName("Verify account repository")
    public void testAccountRepositoryConstructor() {

//        Assert
        assertNotNull(accountRepository);

    }


    @Test
    @DisplayName("Verify if account is saved in account repository")
    public void testSaveAccountInAccountRepository() {

//        Count all the accounts available in the beginning of the test
        long initialCount = accountRepository.count();

//        Save account in the accountJpaRepository
        Account account = accountRepository.save(hulkAccountToSave);

//        Verify if the account exists in the accountJpaRepository
        boolean accountExists = accountRepository.existsById(hulkAccountID);

//        Count all the accounts available in the accountJpaRepository
        long count = accountRepository.count();

//        Expected results
        int expectedTotalNumberOfAccounts = (int) initialCount + 1;

        //Assert
        assertTrue(accountExists);
        assertEquals(expectedTotalNumberOfAccounts, count);

//        Delete the account added in this test
        accountRepository.delete(hulkAccountToSave);

//        Confirm if the elimination of the account was successful
        long countAfterDelete = accountRepository.count();

//        Assert after elimination of the account
        assertEquals(initialCount, countAfterDelete);

    }

    @Test
    @DisplayName("Verify if account is saved in account repository | Save same Account twice")
    public void testSaveSameAccountInAccountRepositoryTwice() {

//        Count all the accounts available in the beginning of the test
        long initialCount = accountRepository.count();

//        Save account in the accountJpaRepository
        Account account = accountRepository.save(hulkAccountToSave);
        Account accountSaveTwice = accountRepository.save(hulkAccountToSave);

//        Verify if the account exists in the accountJpaRepository
        List<Account> allAccountWithSameID = accountRepository.findAllById(hulkAccountDescription, hulkAccountDenomination,
                hulkEmail);

//        Expected results
        int expectedTotalNumberOfAccountsWithSameID = 1;
        int expectedTotalNumberOfAccounts = (int) initialCount + 1;

        //Assert
        assertEquals(expectedTotalNumberOfAccountsWithSameID, allAccountWithSameID.size());
        assertEquals(expectedTotalNumberOfAccounts, accountRepository.count());

//        Delete the account added in this test
        accountRepository.delete(account);
        accountRepository.delete(accountSaveTwice);

//        Confirm if the elimination of the account was successful
        long countAfterDelete = accountRepository.count();

//        Assert after elimination of the accounts
        assertEquals(initialCount, countAfterDelete);

    }

    @Test
    @DisplayName("Verify findAccountByAccountID() | HappyCase")
    public void testFindAccountByAccountID() {

//        Count all the accounts available in the beginning of the test
        long initialCount = accountRepository.count();

//        Save account in the accountJpaRepository
        Account expectedAccount = accountRepository.save(hulkAccountToSave);

//        Verify if the account exists in the accountJpaRepository
        Optional<Account> opAccount = accountRepository.findById(hulkEmail, hulkAccountDenomination);
        Account accountFromDataBase = opAccount.get();

        //Assert
        assertEquals(expectedAccount, accountFromDataBase);

//        Delete the account added in this test
        accountRepository.delete(hulkAccountToSave);

//        Confirm if the elimination of the account was successful
        long countAfterDelete = accountRepository.count();

//        Assert after elimination of the account
        assertEquals(initialCount, countAfterDelete);

    }


    @Test
    @DisplayName("Test For findAccountByAccountID() | Doesn't exists")
    public void checkFindAccountByAccountIDNotExists() {

//        Verify if the account exists in the accountJpaRepository
        Optional<Account> opAccount = accountRepository.findById(hulkEmail, hulkAccountDenomination);
        List<Account> allAccountsWithSameId = accountRepository.findAllById(hulkAccountDescription, hulkAccountDenomination, hulkEmail);
        int numberOfAccounts = allAccountsWithSameId.size();

//        Expected result
        Optional<Account> expectedOpAccount = Optional.empty();
        int expectedNumberOfAccounts = 0;

//        Assert
        assertEquals(expectedOpAccount, opAccount);
        assertEquals(expectedNumberOfAccounts, numberOfAccounts);

    }


    @Test
    @DisplayName("Test For findAllAccounts | Data base with accounts")
    public void checkFindAllAccounts() {

//        Count all the accounts available in the beginning of the test
        long initialCount = accountRepository.count();

//        Save Accounts in Data Base
        accountRepository.save(hulkAccountToSave);
        accountRepository.save(wolverineAccountToSave);

//        Verify if the account exists in the accountJpaRepository
        List<Account> allAccountsWithSameId = accountRepository.findAll();
        int numberOfAccounts = allAccountsWithSameId.size();

//        Expected result
        long expectedNumberOfAccountsInDB = accountRepository.count();
        int expectedNumberOfAccounts = (int) initialCount + 2;

//        Assert
        assertEquals(expectedNumberOfAccounts, numberOfAccounts);
        assertEquals(expectedNumberOfAccounts, expectedNumberOfAccountsInDB);

//        Delete the account added in this test
        accountRepository.delete(hulkAccountToSave);
        accountRepository.delete(wolverineAccountToSave);

//        Confirm if the elimination of the account was successful
        long countAfterDelete = accountRepository.count();

//        Assert after elimination of the accounts
        assertEquals(initialCount, countAfterDelete);
    }

    @Test
    @DisplayName("Test For findAllAccounts | No Accounts saved into DB")
    public void checkFindAllAccountsDBEmpty() {

//        Count all the accounts available in the beginning of the test
        long initialCount = accountRepository.count();

//        Verify if the account exists in the accountJpaRepository
        List<Account> allAccountsWithSameId = accountRepository.findAll();
        int numberOfAccounts = allAccountsWithSameId.size();

//        Expected result
        long expectedNumberOfAccountsInDB = accountRepository.count();
        int expectedNumberOfAccounts = (int) initialCount;

//        Assert
        assertEquals(expectedNumberOfAccounts, numberOfAccounts);
        assertEquals(expectedNumberOfAccounts, expectedNumberOfAccountsInDB);

    }

    @Test
    @DisplayName("Verify count() | HappyCase")
    public void testCountAccount() {

//        Count all the accounts available in the beginning of the test
        long initialCount = accountRepository.count();

//        Save Accounts in Data Base
        accountRepository.save(hulkAccountToSave);
        accountRepository.save(wolverineAccountToSave);

//        Verify the number of accounts in the account repository
        long countOfAccountsInRepository = accountRepository.count();

//        Expected result
        int expectedNumberOfAccounts = (int) initialCount + 2;

//        Assert
        assertEquals(expectedNumberOfAccounts, countOfAccountsInRepository);

//        Delete the account added in this test
        accountRepository.delete(hulkAccountToSave);
        accountRepository.delete(wolverineAccountToSave);

//        Confirm if the elimination of the account was successful
        long countAfterDelete = accountRepository.count();

//        Assert after elimination of the accounts
        assertEquals(initialCount, countAfterDelete);

    }


    @Test
    @DisplayName("Verify exists() | Happy Case")
    public void testAccountExistsInAccountRepository() {

//        Count all the accounts available in the beginning of the test
        long initialCount = accountRepository.count();

//        Save Accounts in Data Base
        accountRepository.save(hulkAccountToSave);

//        Verify if the account exists in the accountJpaRepository
        boolean verifyIfAccountExists = accountRepository.existsById(hulkAccountID);
        Optional<Account> opAccountInJpaRepository = accountRepository.findById(hulkEmail, hulkAccountDenomination);
        Account accountFromDataBase = opAccountInJpaRepository.get();

//        Assert
        assertTrue(verifyIfAccountExists);
        assertEquals(hulkAccountToSave, accountFromDataBase);

//        Delete the account added in this test
        accountRepository.delete(hulkAccountToSave);

//        Confirm if the elimination of the account was successful
        long countAfterDelete = accountRepository.count();

//        Assert after elimination of the account
        assertEquals(initialCount, countAfterDelete);
    }


    @Test
    @DisplayName("Verify exists() | Not exists")
    public void testAccountExistsInAccountRepositoryNotExists() {

//        Count all the accounts available in the beginning of the test
        long initialCount = accountRepository.count();

//        Create hulk accountToSave
        hulkAccountToSave = Account.createAccount(hulkAccountDescription, hulkAccountDenomination, hulkID);

//        Create wolverine accountToSave
        Account wolverineAccountToSave = Account.createAccount(wolverineAccountDescription, wolverineAccountDenomination, wolverineID);

//        Save Accounts in Data Base
        accountRepository.save(hulkAccountToSave);

//        Verify if the Wolverine account exists in the accountJpaRepository
        boolean verifyIfAccountExists = accountRepository.existsById(wolverineAccountID);
        Optional<Account> opAccountInJpaRepository = accountRepository.findById(wolverineEmail, wolverineAccountDenomination);

//        Expected result
        Optional<Account> expectedOpAccount = Optional.empty();

//        Assert
        assertFalse(verifyIfAccountExists);
        assertEquals(expectedOpAccount, opAccountInJpaRepository);

//        Delete the account added in this test
        accountRepository.delete(hulkAccountToSave);

//        Confirm if the elimination of the account was successful
        long countAfterDelete = accountRepository.count();

//        Assert after elimination of the account
        assertEquals(initialCount, countAfterDelete);
    }

    @Test
    @DisplayName("Verify exists() account Group| Happy Case")
    public void testGroupAccountExistsInAccountRepository() {

//        Count all the accounts available in the beginning of the test
        long initialCount = accountRepository.count();

//        Arrange hulk group account
        String hulkGroupAccountDenomination = "Marvel Netflix";
        String hulkGroupAccountDescription = "Marvel account";

//        Arrange hulk Group
        String hulkFamilyDenomination = "Hulk Family";
        String hulkFamilyDescription = "All members from Hulk family";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String groupCreation = LocalDate.of(1980, 11, 15).format(formatter);

//        Create hulk GroupID
        GroupID hulkGroupID = GroupID.createGroupID(hulkFamilyDenomination);

//        Create hulk Group accountID
        AccountID accountID = AccountID.createAccountID(hulkGroupAccountDenomination, hulkGroupID);

//        Create hulk accountToSave
        Account expectedAccountToSave = Account.createAccount(hulkGroupAccountDescription, hulkGroupAccountDenomination, hulkGroupID);

//        Save Accounts in Data Base
        accountRepository.save(expectedAccountToSave);

//        Verify if the account exists in the accountJpaRepository
        boolean verifyIfAccountExists = accountRepository.existsById(accountID);
        Optional<Account> opAccountInJpaRepository = accountRepository.findById(hulkFamilyDenomination, hulkGroupAccountDenomination);
        Account accountFromDataBase = opAccountInJpaRepository.get();

//        Assert
        assertTrue(verifyIfAccountExists);
        assertEquals(expectedAccountToSave, accountFromDataBase);

//        Delete the account added in this test
        accountRepository.delete(expectedAccountToSave);

//        Confirm if the elimination of the account was successful
        long countAfterDelete = accountRepository.count();

//        Assert after elimination of the account
        assertEquals(initialCount, countAfterDelete);
    }

    @Test
    @DisplayName("Verify exists() account Group| Doesn't exists")
    public void testGroupAccountDoesntExistsInAccountRepository() {

//        Count all the accounts available in the beginning of the test
        long initialCount = accountRepository.count();

//        Arrange hulk group account
        String hulkGroupAccountDenomination = "Marvel Netflix";
        String hulkGroupAccountDescription = "Marvel account";

//        Arrange another account for Hulk Group
        String anotherDenomination = "Gym";

//        Arrange hulk Group
        String hulkFamilyDenomination = "Hulk Family";
        String hulkFamilyDescription = "All members from Hulk family";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String groupCreation = LocalDate.of(1980, 11, 15).format(formatter);

//        Create hulk GroupID
        GroupID hulkGroupID = GroupID.createGroupID(hulkFamilyDenomination);

//        Create hulk Group accountID
        AccountID accountID = AccountID.createAccountID(hulkGroupAccountDenomination, hulkGroupID);

//        Create hulk accountToSave
        Account expectedAccountToSave = Account.createAccount(hulkGroupAccountDescription, hulkGroupAccountDenomination, hulkGroupID);

//        Create hulk Group accountIDNotToSave
        AccountID accountIDNotSaved = AccountID.createAccountID(anotherDenomination, hulkGroupID);

//        Save Accounts in Data Base
        accountRepository.save(expectedAccountToSave);

//        Verify if the account exists in the accountJpaRepository
        boolean verifyIfAccountExists = accountRepository.existsById(accountIDNotSaved);
        Optional<Account> opAccountInJpaRepository = accountRepository.findById(hulkFamilyDenomination, anotherDenomination);

//        Expected Option
        Optional<Object> expectedOpAccount = Optional.empty();

//        Assert
        assertFalse(verifyIfAccountExists);
        assertEquals(expectedOpAccount, opAccountInJpaRepository);

//        Delete the account added in this test
        accountRepository.delete(expectedAccountToSave);

//        Confirm if the elimination of the account was successful
        long countAfterDelete = accountRepository.count();

//        Assert after elimination of the account
        assertEquals(initialCount, countAfterDelete);
    }

}