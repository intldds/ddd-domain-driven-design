package com.finance.project.infrastructureLayer.repositories;

import com.finance.project.dataModel.dataAssemblers.GroupDomainDataAssembler;
import com.finance.project.dataModel.dataModel.AdminJpa;
import com.finance.project.dataModel.dataModel.GroupJpa;
import com.finance.project.persistenceLayer.repositoriesJPA.GroupJpaRepository;
import com.finance.project.domainLayer.repositoriesInterfaces.IGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.finance.project.domainLayer.domainEntities.aggregates.group.Group;
import com.finance.project.domainLayer.domainEntities.vosShared.AccountID;
import com.finance.project.domainLayer.domainEntities.vosShared.GroupID;
import com.finance.project.domainLayer.domainEntities.vosShared.PersonID;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Repository
public class GroupRepository implements IGroupRepository {

    @Autowired
    GroupJpaRepository groupJpaRepository;


    @Autowired
    GroupDomainDataAssembler groupAssembler;

    // Constructor
    public GroupRepository() {
    }


@Transactional
    public Group save( Group group ) {
        GroupJpa groupJpa = groupAssembler.toData(group);

        groupJpa = groupJpaRepository.save( groupJpa );

        group.addLedgerID(groupJpa.getLedger().getId());
        PersonID personID = group.getPeopleInCharge().get(0);
        addAndSaveAdmin( group, personID);

        return groupAssembler.toDomain(groupJpa);
    }

    public boolean addAndSaveAdmin(Group group, PersonID adminID) {
        GroupJpa groupJpa = groupAssembler.toData(group);

        groupJpa.addAdministrator( adminID );

        groupJpaRepository.save( groupJpa );

        return true; // it does not reflect problems that may occur
    }

    @Transactional
    public Optional<Group> findById(GroupID id) {
        Optional<GroupJpa> opGroupJpa = groupJpaRepository.findById(id);

        if(opGroupJpa.isPresent()) {
            GroupJpa groupJpa = opGroupJpa.get();

            Group group = groupAssembler.toDomain(groupJpa);
            return Optional.of(group);
        }
        else
            return Optional.empty();
    }

    public boolean addAndSaveLedger(Group group) {
        GroupJpa groupJpa = groupAssembler.toData(group);

        groupJpaRepository.save(groupJpa);

        return true;
    }

    @Transactional
    public List<PersonID> findAdminsById( GroupID id ) {
        Optional<GroupJpa> opGroupJpa = groupJpaRepository.findById(id);

        if(opGroupJpa.isPresent()) {
            GroupJpa groupJpa = opGroupJpa.get();

            List<AdminJpa> adminsJpa = groupJpa.getAdministrators();
            List<PersonID> adminsId = new ArrayList<PersonID>();
            for( AdminJpa adminJpa : adminsJpa ) {
                adminsId.add(adminJpa.getPersonID());
            }
            return adminsId;
        }
        else
            return null; // it should throw a descriptive exception
    }

    public boolean addAndSaveMember(Group group, PersonID memberID) {
        GroupJpa groupJpa = groupAssembler.toData(group);

        groupJpa.addMember( memberID );

        groupJpaRepository.save( groupJpa );

        return true; // it does not reflect problems that may occur
    }

    public boolean addAndSaveCategory(Group group) {
        GroupJpa groupJpa = groupAssembler.toData(group);

        groupJpaRepository.save(groupJpa);

        return true; // it does not reflect problems that may occur
    }

    public boolean addAndSaveAccount(Group group, String description) {
        GroupJpa groupJpa = groupAssembler.toData(group);

        AccountID accountID = group.getAccounts().get(group.getAccounts().size() - 1);

        groupJpa.addAccount(group.getGroupID().getDenomination().getDenomination(), accountID.getDenomination().getDenomination(), description);

        groupJpaRepository.save(groupJpa);

        return true; // it does not reflect problems that may occur
    }

    public boolean exists(GroupID groupID) {
        return groupJpaRepository.existsById(groupID);
    }

    public long count() {
        return groupJpaRepository.count();
    }

    @Transactional
    public List<Group> findAll () {
        List<GroupJpa> groupJpaList = groupJpaRepository.findAll();
        List<Group> groupListToReturn = new ArrayList<>();
        for(GroupJpa groupJpa : groupJpaList) {
            Group group = groupAssembler.toDomain(groupJpa);
            groupListToReturn.add(group);
        }
        return groupListToReturn;
    }
}