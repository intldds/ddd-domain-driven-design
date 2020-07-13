package com.finance.project.domainLayer.repositoriesInterfaces;

import org.springframework.stereotype.Repository;
import com.finance.project.domainLayer.domainEntities.aggregates.account.Account;
import com.finance.project.domainLayer.domainEntities.vosShared.AccountID;

import java.util.List;
import java.util.Optional;


@Repository
public interface IAccountRepository {

    Account save(Account account);

    List<Account> findAll();

    Optional<Account> findById(String id, String denomination);

    boolean existsById(AccountID accountID);

    long count();

    void delete(Account account);

    List<Account> findAllById(String description, String denomination, String id);
}