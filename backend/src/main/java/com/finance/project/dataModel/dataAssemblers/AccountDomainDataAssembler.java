package com.finance.project.dataModel.dataAssemblers;

import com.finance.project.domainLayer.entitiesInterfaces.OwnerID;
import org.springframework.stereotype.Service;
import com.finance.project.domainLayer.domainEntities.aggregates.account.Account;
import com.finance.project.domainLayer.domainEntities.vosShared.GroupID;
import com.finance.project.domainLayer.domainEntities.vosShared.PersonID;
import com.finance.project.dataModel.dataModel.AccountJpa;

@Service
public class AccountDomainDataAssembler {

    public AccountJpa toData(Account account) {

        String id = "";

        if (account.getAccountID().getOwnerID() instanceof PersonID) {
            PersonID personID = (PersonID) account.getAccountID().getOwnerID();
            id = personID.getEmail().getEmail();
        }

        if (account.getAccountID().getOwnerID() instanceof GroupID) {
            GroupID groupID = (GroupID) account.getAccountID().getOwnerID();
            id = groupID.getDenomination().getDenomination();
        }

        AccountJpa accountJpa = new AccountJpa(id, account.getAccountID().getDenomination().getDenomination(), account.getDescription().getDescription());

        return accountJpa;
    }

    public Account toDomain(AccountJpa accountJpa) {

        OwnerID ownerID;

        if (accountJpa.getId().getOwnerID().contains("@")) {
            ownerID = PersonID.createPersonID(accountJpa.getId().getOwnerID());
        } else {
            ownerID = GroupID.createGroupID(accountJpa.getId().getOwnerID());
        }

        Account account = Account.createAccount(accountJpa.getDescription(), accountJpa.getId().getDenomination(), ownerID);

        return account;
    }
}