package com.finance.project.dataModel.dataAssemblers;

import com.finance.project.dataModel.dataModel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.finance.project.domainLayer.domainEntities.aggregates.group.Group;
import com.finance.project.domainLayer.domainEntities.vosShared.AccountID;
import com.finance.project.domainLayer.domainEntities.vosShared.CategoryID;
import com.finance.project.domainLayer.domainEntities.vosShared.GroupID;
import com.finance.project.domainLayer.domainEntities.vosShared.PersonID;
import com.finance.project.persistenceLayer.repositoriesJPA.AccountJpaRepository;
import com.finance.project.persistenceLayer.repositoriesJPA.GroupJpaRepository;
import com.finance.project.persistenceLayer.repositoriesJPA.LedgerJpaRepository;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GroupDomainDataAssembler {

    @Autowired
    GroupJpaRepository groupJpaRepository;
    @Autowired
    LedgerJpaRepository ledgerJpaRepository;
    @Autowired
    AccountJpaRepository accountJpaRepository;

    public GroupDomainDataAssembler(GroupJpaRepository groupJpaRepository, LedgerJpaRepository ledgerJpaRepository, AccountJpaRepository accountJpaRepository) {
        this.groupJpaRepository = groupJpaRepository;
        this.ledgerJpaRepository = ledgerJpaRepository;
        this.accountJpaRepository = accountJpaRepository;
    }

    public GroupJpa toData(Group group) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        GroupJpa groupJpa = new GroupJpa(group.getGroupID(), group.getDescription().getDescription(), group.getDateOfCreation().getDateOfCreation().format(formatter));

        Optional<GroupJpa> optGroupJpa = groupJpaRepository.findById(group.getGroupID());

        //LEDGER
        if (group.getLedgerID() != null) {
            Optional<LedgerJpa> optLedgerJpa = ledgerJpaRepository.findById(group.getLedgerID());

            if (optLedgerJpa.isPresent()) {
                LedgerJpa ledgerJpa = optLedgerJpa.get();

                groupJpa.setLedger(ledgerJpa);
            }
        }

        //ADMINS
        if (optGroupJpa.isPresent()) {
            GroupJpa repoGroupJpa = optGroupJpa.get();
            List<AdminJpa> repoAdmins = repoGroupJpa.getAdministrators();
            groupJpa.setAdministrators(repoAdmins);
        }

        //MEMBERS
        if (optGroupJpa.isPresent()) {
            GroupJpa repoGroupJpa = optGroupJpa.get();
            List<MemberJpa> repoMembers = repoGroupJpa.getMembers();
            groupJpa.setMembers(repoMembers);
        }

        //CATEGORIES
        List<CategoryJpa> categoryJpa = new ArrayList<>();
        for (CategoryID categoryID : group.getCategories()) {
            categoryJpa.add(new CategoryJpa(group.getGroupID().getDenomination().getDenomination(), categoryID.getDenomination().getDenomination()));
            groupJpa.setCategories(categoryJpa);
        }

        //ACCOUNTS
        List<AccountJpa> accountJpa = new ArrayList<>();
        for (AccountID accountID : group.getAccounts()) {

            Optional<AccountJpa> optAccountJpa = accountJpaRepository.findById(new AbstractIdJpa(group.getGroupID().getDenomination().getDenomination(), accountID.getDenomination().getDenomination()));
            if (optAccountJpa.isPresent()) {
                AccountJpa repoAccountJpa = optAccountJpa.get();
                accountJpa.add(repoAccountJpa);
            }
            groupJpa.setAccounts(accountJpa);
        }

        return groupJpa;
    }

    public Group toDomain(GroupJpa groupJpa) {
        List<PersonID> adminsId = new ArrayList<PersonID>();

        Group group = Group.createGroup(groupJpa.getId(), groupJpa.getDescription(), groupJpa.getDateOfCreation(), adminsId);

        //LEDGER
        if (groupJpa.getLedger() != null) {
            group.addLedgerID(groupJpa.getLedger().getId());
        }

        //ADMINS
        for (AdminJpa adminJpa : groupJpa.getAdministrators()) {
            group.addPersonInCharge(adminJpa.getPersonID());
        }

        //MEMBERS
        for (MemberJpa memberJpa : groupJpa.getMembers()) {
            group.addMember(memberJpa.getPersonID());
        }

        //CATEGORIES
        for (CategoryJpa categoryJpa : groupJpa.getCategories()) {
            group.addCategory(CategoryID.createCategoryID(categoryJpa.getId().getDenomination(), GroupID.createGroupID(categoryJpa.getId().getOwnerID())));
        }

        //ACCOUNTS
        for (AccountJpa accountJpa : groupJpa.getAccounts()) {
            group.addAccount(AccountID.createAccountID(accountJpa.getId().getDenomination(), GroupID.createGroupID(accountJpa.getId().getOwnerID())));
        }

        return group;
    }
}