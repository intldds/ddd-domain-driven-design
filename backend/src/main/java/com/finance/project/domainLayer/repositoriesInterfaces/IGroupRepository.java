package com.finance.project.domainLayer.repositoriesInterfaces;

import org.springframework.stereotype.Repository;
import com.finance.project.domainLayer.domainEntities.aggregates.group.Group;
import com.finance.project.domainLayer.domainEntities.vosShared.GroupID;
import com.finance.project.domainLayer.domainEntities.vosShared.PersonID;

import java.util.List;
import java.util.Optional;


@Repository
public interface IGroupRepository {

    Group save(Group group);

    boolean addAndSaveAdmin(Group group, PersonID adminID);

    Optional<Group> findById(GroupID id);

    boolean addAndSaveLedger(Group group);

    List<PersonID> findAdminsById(GroupID id);

    boolean addAndSaveMember(Group group, PersonID memberID);

    boolean addAndSaveCategory(Group group);

    boolean addAndSaveAccount(Group group, String description);

    boolean exists(GroupID groupID);

    long count();

    List<Group> findAll();
}
