package com.finance.project.infrastructureLayer.repositories;

import com.finance.project.dataModel.dataAssemblers.AccountDomainDataAssembler;
import com.finance.project.dataModel.dataModel.AbstractIdJpa;
import com.finance.project.dataModel.dataModel.AccountJpa;
import com.finance.project.persistenceLayer.repositoriesJPA.AccountJpaRepository;
import com.finance.project.domainLayer.repositoriesInterfaces.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.finance.project.domainLayer.domainEntities.aggregates.account.Account;
import com.finance.project.domainLayer.domainEntities.vosShared.AccountID;
import com.finance.project.domainLayer.domainEntities.vosShared.GroupID;
import com.finance.project.domainLayer.domainEntities.vosShared.PersonID;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Repository
public class AccountRepository implements IAccountRepository {

    @Autowired
    AccountDomainDataAssembler accountAssembler;
    @Autowired
    AccountJpaRepository accountJpaRepository;

    // Constructor

    public AccountRepository() {
    }


    public Account save(Account account) {
        AccountJpa accountJpa = accountAssembler.toData(account);

        AccountJpa savedAccountJpa = accountJpaRepository.save(accountJpa);

        return accountAssembler.toDomain(savedAccountJpa);
    }

    public Optional<Account> findById(String id, String denomination) {

        AbstractIdJpa abstractIdJpa = new AbstractIdJpa(id, denomination);

        Optional<AccountJpa> opAccountJpa = accountJpaRepository.findById(abstractIdJpa);

        if (opAccountJpa.isPresent()) {
            AccountJpa accountJpa = opAccountJpa.get();

            Account account = accountAssembler.toDomain(accountJpa);
            return Optional.of(account);
        } else
            return Optional.empty();
    }

    public boolean existsById(AccountID accountID) {

        String id = "";

        if (accountID.getOwnerID() instanceof PersonID) {
            PersonID personID = (PersonID) accountID.getOwnerID();
            id = personID.getEmail().getEmail();
        }

        if (accountID.getOwnerID() instanceof GroupID) {
            GroupID groupID = (GroupID) accountID.getOwnerID();
            id = groupID.getDenomination().getDenomination();
        }

        AbstractIdJpa abstractIdJpa = new AbstractIdJpa(id, accountID.getDenomination().getDenomination());

        return accountJpaRepository.existsById(abstractIdJpa);
    }

    public long count() {
        return accountJpaRepository.count();
    }


    public List<Account> findAll() {

        List<Account> newAccountList = new ArrayList<>();

        List<AccountJpa> allAccountsInDataBase = accountJpaRepository.findAll();

//        Go through all AccountJPA in allAccountsInDataBase, and pass them toDomain
        for (AccountJpa accountJpa : allAccountsInDataBase) {
            Account accountFromDataBase = accountAssembler.toDomain(accountJpa);
            newAccountList.add(accountFromDataBase);
        }
        return newAccountList;

    }
    public void delete(Account account){

//        Pass account to accountJpa
        AccountJpa accountJpa = accountAssembler.toData(account);

//        Delete accountJpa from accountJpaRepository
        accountJpaRepository.delete(accountJpa);

    }

    public List<Account> findAllById(String description, String denomination, String id) {

//        Arrange information to create Account
        Account accountWanted = Account.createAccount(description, denomination, PersonID.createPersonID(id));

//        Arrange information to find Account by ID
        AbstractIdJpa abstractIdJpa = new AbstractIdJpa(id, denomination);
        List<AccountJpa> allAccountsSameIDInDataBase;
        List<Account> newAccountList = new ArrayList<>();

//        Go through all AccountJPA in allAccountsSameIDInDataBase, and pass them toDomain
        allAccountsSameIDInDataBase = accountJpaRepository.findAllById(abstractIdJpa);
        for (AccountJpa AccountIDToAnalyse : allAccountsSameIDInDataBase) {
            Account accountToAnalyze = accountAssembler.toDomain(AccountIDToAnalyse);

//        If the accountToAnalyze is equals to the accountWanted, will be added to the list
            if (accountWanted.equals(accountToAnalyze)) {
                newAccountList.add(accountToAnalyze);
            }
        }
        return newAccountList;

    }
}