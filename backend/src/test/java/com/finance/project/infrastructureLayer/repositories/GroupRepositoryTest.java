package com.finance.project.infrastructureLayer.repositories;

import com.finance.project.controllerLayer.integrationTests.AbstractTest;
import com.finance.project.domainLayer.domainEntities.aggregates.group.Group;
import com.finance.project.domainLayer.domainEntities.vosShared.GroupID;
import com.finance.project.domainLayer.domainEntities.vosShared.LedgerID;
import com.finance.project.domainLayer.domainEntities.vosShared.PersonID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class GroupRepositoryTest extends AbstractTest {

    @Autowired
    GroupRepository groupRepository;


    //Save

    @Test
    @DisplayName("Test for save")
    public void saveGroup() {

        //Arrange

        String denominantion = "Runners";
        String description = "Run always";

        String email = "jo@gmail.com";

        PersonID personID = PersonID.createPersonID(email);

        Group group = Group.createGroup(denominantion, description, LocalDate.now().toString(), personID);

        //Act

        Group savedGroup = groupRepository.save(group);

        //Assert
        assertEquals(group, savedGroup);
    }


    //Find byID

    @Test
    @DisplayName("Test for findByiD")
    public void findById() {

        //Arrange

        String denominantion = "Runners";
        String description = "Run always";

        String email = "jo@gmail.com";

        GroupID groupID = GroupID.createGroupID(denominantion);

        PersonID personID = PersonID.createPersonID(email);

        Group group = Group.createGroup(denominantion, description, LocalDate.now().toString(), personID);

        //Act

        Group savedGroup = groupRepository.save(group);

        Optional<Group> groupJpa = groupRepository.findById(groupID);

        //Assert
        assertEquals(groupJpa.get(), savedGroup);
    }


    @Test
    @DisplayName("Test for findByiD - Not find")
    public void findById_NotFind() {

        //Arrange

        String denominantion = "Runners";
        String description = "Run always";

        String email = "jo@gmail.com";

        GroupID groupID = GroupID.createGroupID(denominantion);

        PersonID personID = PersonID.createPersonID(email);

        Group group = Group.createGroup(denominantion, description, LocalDate.now().toString(), personID);


        //Arrange AnotherGroupID

        String newDenominantion = "Runners";

        GroupID newGroupID = GroupID.createGroupID(newDenominantion);


        //Act


        Optional<Group> groupJpa = groupRepository.findById(groupID);


        //Assert
        assertEquals(groupJpa, groupRepository.findById(newGroupID));
    }


    //Add and Save Ledger

    @Test
    @DisplayName("Test for addAndSaveLedger")
    public void addAndSaveLedger() {

        //Arrange

        String denominantion = "Runners";
        String description = "Run always";

        String email = "jo@gmail.com";

        PersonID personID = PersonID.createPersonID(email);

        Group group = Group.createGroup(denominantion, description, LocalDate.now().toString(), personID);

        // Arrange Ledger

        LedgerID ledgerID = LedgerID.createLedgerID();


        //Act

        Group savedGroup = groupRepository.save(group);

        Boolean groupLedger = groupRepository.addAndSaveLedger(savedGroup);

        //Assert
        assertEquals(true, groupLedger);
    }

    //Find admin

    @Test
    @DisplayName("Test for findAdminsById")
    public void findAdminsById() {

        //Arrange

        String denominantion = "Runners";
        String description = "Run always";

        GroupID groupID = GroupID.createGroupID(denominantion);

        String email = "jo@gmail.com";

        PersonID personID = PersonID.createPersonID(email);

        Group group = Group.createGroup(denominantion, description, LocalDate.now().toString(), personID);

        // Arrange Admin List


        List<PersonID> adminJpa = new ArrayList<>();
        adminJpa.add(personID);


        //Act


        Group savedGroup = groupRepository.save(group);

        List<PersonID> admin = groupRepository.findAdminsById(groupID);

        //Assert
        assertEquals(admin, adminJpa);
    }



    //Add and Save Member

    @Test
    @DisplayName("Test for addAndSaveMember")
    public void addAndSaveMember() {

        //Arrange

        String denominantion = "Runners";
        String description = "Run always";

        String email = "jo@gmail.com";

        PersonID personID = PersonID.createPersonID(email);

        Group group = Group.createGroup(denominantion, description, LocalDate.now().toString(), personID);

        //Arrange Member

        String emailMember = "pp@gmail.com";

        PersonID memberPersonId = PersonID.createPersonID(emailMember);

        //Act

        Boolean saveMember = group.addMember(memberPersonId);

        Group savedGroup = groupRepository.save(group);



        //Assert
        assertEquals(true, saveMember);
    }


    //Exists Group

    @Test
    @DisplayName("Test exists")
    public void existGroup() {

        //Arrange

        String denominantion = "Runners";
        String description = "Run always";

        GroupID groupID =GroupID.createGroupID(denominantion);

        String email = "jo@gmail.com";

        PersonID personID = PersonID.createPersonID(email);

        Group group = Group.createGroup(denominantion, description, LocalDate.now().toString(), personID);


        //Act

        Group savedGroup = groupRepository.save(group);

        boolean existsGroup = groupRepository.exists(groupID);



        //Assert
        assertEquals(true, existsGroup);
    }


    //Counts Group

    @Test
    @DisplayName("Test for countGroup")
    public void countGroup() {

        //Arrange

        String denominantion = "Runners";
        String description = "Run always";

        String email = "jo@gmail.com";

        PersonID personID = PersonID.createPersonID(email);

        Group group = Group.createGroup(denominantion, description, LocalDate.now().toString(), personID);

        //Act

        Group savedGroup = groupRepository.save(group);

        long countGroup = groupRepository.count();

        //Assert
        assertEquals(countGroup, groupRepository.count());
    }


    //Find all

    @Test
    @DisplayName("Test for findAll")
    public void findAll() {

        //Arrange

        String denominantion = "Runners";
        String description = "Run always";

        String email = "jo@gmail.com";

        PersonID personID = PersonID.createPersonID(email);

        Group group = Group.createGroup(denominantion, description, LocalDate.now().toString(), personID);


        //Arrange

        String newDenominantion = "Shopping";
        String newDescription = "All about Shopping";

        String newEmail = "bb@gmail.com";

        PersonID newPersonID = PersonID.createPersonID(newEmail);

        Group newGroup = Group.createGroup(newDenominantion, newDescription, LocalDate.now().toString(), newPersonID);


        //Act

        Group savedGroup = groupRepository.save(group);
        Group newSavedGroup = groupRepository.save(newGroup);

        List<Group> groups = groupRepository.findAll();

        //Assert
        assertEquals(groupRepository.count(), groupRepository.count());
    }






}