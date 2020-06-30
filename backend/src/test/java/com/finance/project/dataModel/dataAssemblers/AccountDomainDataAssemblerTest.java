package com.finance.project.dataModel.dataAssemblers;

import com.finance.project.dataModel.dataModel.AccountJpa;
import com.finance.project.domainLayer.domainEntities.aggregates.account.Account;
import com.finance.project.domainLayer.domainEntities.vosShared.PersonID;
import com.finance.project.domainLayer.entitiesInterfaces.OwnerID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class AccountDomainDataAssemblerTest {

    @Test
    @DisplayName("AccountDomainDataAssembler - Test create AccountJpa to data")

    public void accountDomainDataAssembler_toData () {

        //Arrange

        String id = "alexandre@gmail.com";
        OwnerID ownerID = PersonID.createPersonID(id);
        String denomination = "Company";
        String descritpion = "Company account";

        Account account = Account.createAccount(descritpion, denomination, ownerID);

        //Act

        AccountDomainDataAssembler accountDomainDataAssembler = new AccountDomainDataAssembler();
        AccountJpa accountJpa = accountDomainDataAssembler.toData(account);

        //Assert

        assertEquals(id, accountJpa.getId().getOwnerID());
        assertEquals(denomination, accountJpa.getId().getDenomination());
        assertEquals(descritpion, accountJpa.getDescription());
    }

    @Test
    @DisplayName("AccountDomainDataAssembler - Test create AccountJpa to domain")

    public void accountDomainDataAssembler_toDomain () {

        //Arrange

        String id = "alexandre@gmail.com";
        OwnerID ownerID = PersonID.createPersonID(id);
        String denomination = "Company";
        String descritpion = "Company account";

        Account account = Account.createAccount(descritpion, denomination, ownerID);

        //Act

        AccountDomainDataAssembler accountDomainDataAssembler = new AccountDomainDataAssembler();
        AccountJpa accountJpa = accountDomainDataAssembler.toData(account);
        Account newAccount = accountDomainDataAssembler.toDomain(accountJpa);

        //Assert
        Assertions.assertEquals(ownerID, newAccount.getAccountID().getOwnerID());
        assertEquals(denomination, newAccount.getAccountID().getDenomination().getDenomination());
        assertEquals(descritpion, newAccount.getDescription().getDescription());
    }

}