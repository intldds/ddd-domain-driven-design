package com.finance.project.dataModel.dataModel;

import com.finance.project.domainLayer.domainEntities.vosShared.GroupID;
import com.finance.project.domainLayer.domainEntities.vosShared.PersonID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class MemberJpaTest {

    //Constructor

    @Test
    @DisplayName("MembersJpa |Contructor")
    public void membersJpaConstruntor() {

        //Arrange GroupJpa


        String denomination = "Sunday Runners";
        String description = "All members from Sunday Runners group";
        String dateOfCreation = LocalDate.now().toString();

        GroupID groupID = GroupID.createGroupID(denomination);

        GroupJpa groupJpa = new GroupJpa(groupID, description, dateOfCreation);

        //Arrange personID

        String personId = "alexandre@gmail.com";
        PersonID personID = PersonID.createPersonID(personId);

        //Act

        MemberJpa.GroupPersonIdJpa groupPersonIdJpa = new MemberJpa.GroupPersonIdJpa(groupJpa, personID);

        //Assert

        assertEquals(groupPersonIdJpa.getGroupJpa(), groupJpa);
        Assertions.assertEquals(groupPersonIdJpa.getPersonID(), personID);

    }

    //Get e Set

    @Test
    @DisplayName("MembersJpa | Get and Set")
    public void membersJpa_GetAndSet() {

        //Arrange GroupJpa


        String denomination = "Sunday Runners";
        String description = "All members from Sunday Runners group";
        String dateOfCreation = LocalDate.now().toString();

        GroupID groupID = GroupID.createGroupID(denomination);

        GroupJpa groupJpa = new GroupJpa(groupID, description, dateOfCreation);

        //Arrange personID

        String personId = "alexandre@gmail.com";
        PersonID personID = PersonID.createPersonID(personId);

        //Act

        MemberJpa.GroupPersonIdJpa groupPersonIdJpa = new MemberJpa.GroupPersonIdJpa();
        groupPersonIdJpa.setGroupJpa(groupJpa);
        groupPersonIdJpa.setPersonID(personID);

        //Assert

        assertEquals(groupPersonIdJpa.getGroupJpa(), groupJpa);
        Assertions.assertEquals(groupPersonIdJpa.getPersonID(), personID);
    }


    //ToString

    @Test
    @DisplayName("MembersJpa |ToString")
    public void membersJpaToString() {

        //Arrange GroupJpa


        String denomination = "Sunday Runners";
        String description = "All members from Sunday Runners group";
        String dateOfCreation = LocalDate.now().toString();

        GroupID groupID = GroupID.createGroupID(denomination);

        GroupJpa groupJpa = new GroupJpa(groupID, description, dateOfCreation);

        //Arrange personID

        String personId = "alexandre@gmail.com";
        PersonID personID = PersonID.createPersonID(personId);

        //Act

        MemberJpa.GroupPersonIdJpa groupPersonIdJpa = new MemberJpa.GroupPersonIdJpa(groupJpa, personID);

        //Assert

        assertEquals("GroupPersonIdJpa{groupID=GroupID{denomination=Denomination{denomination='Sunday Runners'}}, personID=PersonID{email=Email{email='alexandre@gmail.com'}}}", groupPersonIdJpa.toString());

    }

    //Equals

    @Test
    @DisplayName("MembersJpa |Equals: Same Object")
    public void membersJpaEqualsSameObject() {

        //Arrange GroupJpa


        String denomination = "Sunday Runners";
        String description = "All members from Sunday Runners group";
        String dateOfCreation = LocalDate.now().toString();

        GroupID groupID = GroupID.createGroupID(denomination);

        GroupJpa groupJpa = new GroupJpa(groupID, description, dateOfCreation);

        //Arrange personID

        String personId = "alexandre@gmail.com";
        PersonID personID = PersonID.createPersonID(personId);

        //Act

        MemberJpa.GroupPersonIdJpa groupPersonIdJpa = new MemberJpa.GroupPersonIdJpa(groupJpa, personID);

        boolean result = groupPersonIdJpa.equals(groupPersonIdJpa);
        //Assert

        assertEquals(true, result);

    }

    @Test
    @DisplayName("MembersJpa |Equals: HappyCase")
    public void membersJpaEqualsHappyCase() {

        //Arrange GroupJpa


        String denomination = "Sunday Runners";
        String description = "All members from Sunday Runners group";
        String dateOfCreation = LocalDate.now().toString();

        GroupID groupID = GroupID.createGroupID(denomination);

        GroupJpa groupJpa = new GroupJpa(groupID, description, dateOfCreation);

        //Arrange personID

        String personId = "alexandre@gmail.com";
        PersonID personID = PersonID.createPersonID(personId);

        //Act

        MemberJpa.GroupPersonIdJpa groupPersonIdJpa = new MemberJpa.GroupPersonIdJpa(groupJpa, personID);

        MemberJpa.GroupPersonIdJpa newGroupPersonIdJpa = new MemberJpa.GroupPersonIdJpa(groupJpa, personID);

        boolean result = groupPersonIdJpa.equals(newGroupPersonIdJpa);
        //Assert

        assertEquals(true, result);

    }

    @Test
    @DisplayName("MembersJpa |Equals: null")
    public void membersJpaEqualsNull() {

        //Arrange GroupJpa


        String denomination = "Sunday Runners";
        String description = "All members from Sunday Runners group";
        String dateOfCreation = LocalDate.now().toString();

        GroupID groupID = GroupID.createGroupID(denomination);

        GroupJpa groupJpa = new GroupJpa(groupID, description, dateOfCreation);

        //Arrange personID

        String personId = "alexandre@gmail.com";
        PersonID personID = PersonID.createPersonID(personId);

        //Act

        MemberJpa.GroupPersonIdJpa groupPersonIdJpa = new MemberJpa.GroupPersonIdJpa(groupJpa, personID);

        MemberJpa.GroupPersonIdJpa newGroupPersonIdJpa = null;

        boolean result = groupPersonIdJpa.equals(newGroupPersonIdJpa);
        //Assert

        assertEquals(false, result);

    }

    @Test
    @DisplayName("MembersJpa |Equals:No Instance Of")
    public void membersJpaEqualsNoInstanceOf() {

        //Arrange GroupJpa


        String denomination = "Sunday Runners";
        String description = "All members from Sunday Runners group";
        String dateOfCreation = LocalDate.now().toString();

        GroupID groupID = GroupID.createGroupID(denomination);

        GroupJpa groupJpa = new GroupJpa(groupID, description, dateOfCreation);

        //Arrange personID

        String personId = "alexandre@gmail.com";
        PersonID personID = PersonID.createPersonID(personId);

        //Act

        MemberJpa.GroupPersonIdJpa groupPersonIdJpa = new MemberJpa.GroupPersonIdJpa(groupJpa, personID);


        boolean result = groupPersonIdJpa.equals(denomination);
        //Assert

        assertEquals(false, result);

    }

    @Test
    @DisplayName("MembersJpa |Equals: SadCase - groupJpa")
    public void membersJpaEqualsSadCaseGroupJpa() {

        //Arrange GroupJpa


        String denomination = "Sunday Runners";
        String description = "All members from Sunday Runners group";
        String dateOfCreation = LocalDate.now().toString();

        GroupID groupID = GroupID.createGroupID(denomination);
        GroupJpa groupJpa = new GroupJpa(groupID, description, dateOfCreation);


        String denominationFontes = "Fontes Family";
        GroupID newGroupID = GroupID.createGroupID(denominationFontes);

        GroupJpa newGroupJpa = new GroupJpa(newGroupID, description, dateOfCreation);

        //Arrange personID

        String personId = "alexandre@gmail.com";
        PersonID personID = PersonID.createPersonID(personId);

        //Act

        MemberJpa.GroupPersonIdJpa groupPersonIdJpa = new MemberJpa.GroupPersonIdJpa(groupJpa, personID);
        MemberJpa.GroupPersonIdJpa newGroupPersonIdJpa = new MemberJpa.GroupPersonIdJpa(newGroupJpa, personID);

        boolean result = groupPersonIdJpa.equals(denomination);
        //Assert

        assertEquals(false, result);

    }

    @Test
    @DisplayName("MembersJpa |Equals: SadCase: GroupJpa")
    public void membersJpaEqualsSadCaseGroupJpa_() {

        //Arrange GroupJpa


        String denomination = "Sunday Runners";
        String description = "All members from Sunday Runners group";
        String dateOfCreation = LocalDate.now().toString();

        GroupID groupID = GroupID.createGroupID(denomination);

        GroupJpa groupJpa = new GroupJpa(groupID, description, dateOfCreation);

        //Arrange new GroupJpa


        String newDenomination = "Fontes Family";
        String newDescription = "All members from Sunday Runners group";

        GroupID newGroupID = GroupID.createGroupID(newDenomination);

        GroupJpa newGroupJpa = new GroupJpa(newGroupID, newDescription, LocalDate.now().toString());

        //Arrange personID

        String personId = "alexandre@gmail.com";
        PersonID personID = PersonID.createPersonID(personId);

        String newPersonId = "paulo@gmail.com";
        PersonID newPersonID = PersonID.createPersonID(newPersonId);

        //Act

        MemberJpa.GroupPersonIdJpa groupPersonIdJpa = new MemberJpa.GroupPersonIdJpa(groupJpa, personID);

        MemberJpa.GroupPersonIdJpa newGroupPersonIdJpa = new MemberJpa.GroupPersonIdJpa(newGroupJpa, personID);

        boolean result = groupPersonIdJpa.equals(newGroupPersonIdJpa);

        //Assert

        assertEquals(false, result);

    }

    @Test
    @DisplayName("MembersJpa |Equals: SadCase: PersonID")
    public void membersJpaEqualsSadCasePersonID() {

        //Arrange GroupJpa


        String denomination = "Sunday Runners";
        String description = "All members from Sunday Runners group";
        String dateOfCreation = LocalDate.now().toString();

        GroupID groupID = GroupID.createGroupID(denomination);

        GroupJpa groupJpa = new GroupJpa(groupID, description, dateOfCreation);

        //Arrange personID

        String personId = "alexandre@gmail.com";
        PersonID personID = PersonID.createPersonID(personId);

        String newPersonId = "paulo@gmail.com";
        PersonID newPersonID = PersonID.createPersonID(newPersonId);

        //Act

        MemberJpa.GroupPersonIdJpa groupPersonIdJpa = new MemberJpa.GroupPersonIdJpa(groupJpa, personID);

        MemberJpa.GroupPersonIdJpa newGroupPersonIdJpa = new MemberJpa.GroupPersonIdJpa(groupJpa, newPersonID);

        boolean result = groupPersonIdJpa.equals(newGroupPersonIdJpa);

        //Assert

        assertEquals(false, result);

    }

    //HashCode

    @Test
    @DisplayName("MembersJpa |HashCode")
    public void membersJpaHashCode() {

        //Arrange GroupJpa


        String denomination = "Sunday Runners";
        String description = "All members from Sunday Runners group";
        String dateOfCreation = LocalDate.now().toString();

        GroupID groupID = GroupID.createGroupID(denomination);

        GroupJpa groupJpa = new GroupJpa(groupID, description, dateOfCreation);

        //Arrange personID

        String personId = "alexandre@gmail.com";
        PersonID personID = PersonID.createPersonID(personId);

        //Act

        MemberJpa.GroupPersonIdJpa groupPersonIdJpa = new MemberJpa.GroupPersonIdJpa(groupJpa, personID);
        MemberJpa.GroupPersonIdJpa newGroupPersonIdJpa = new MemberJpa.GroupPersonIdJpa(groupJpa, personID);

        boolean result = groupPersonIdJpa.hashCode()==newGroupPersonIdJpa.hashCode();

        //Assert

        assertTrue(groupPersonIdJpa.hashCode()==newGroupPersonIdJpa.hashCode());
        assertTrue(groupPersonIdJpa.hashCode()==groupPersonIdJpa.hashCode());
        assertEquals(true, result);

    }


    //HashCode

    @Test
    @DisplayName("MembersJpa |HashCode : SadCase")
    public void membersJpaHashCode_SadCase() {

        //Arrange GroupJpa


        String denomination = "Sunday Runners";
        String description = "All members from Sunday Runners group";
        String dateOfCreation = LocalDate.now().toString();

        GroupID groupID = GroupID.createGroupID(denomination);

        GroupJpa groupJpa = new GroupJpa(groupID, description, dateOfCreation);

        //Arrange new GroupJpa


        String newDenomination = "Fontes Family";
        String newDescription = "All members from Sunday Runners group";

        GroupID newGroupID = GroupID.createGroupID(newDenomination);

        GroupJpa newGroupJpa = new GroupJpa(newGroupID, newDescription, LocalDate.now().toString());

        //Arrange personID

        String personId = "alexandre@gmail.com";
        PersonID personID = PersonID.createPersonID(personId);

        String newPersonId = "paulo@gmail.com";
        PersonID newPersonID = PersonID.createPersonID(newPersonId);

        //Act

        MemberJpa.GroupPersonIdJpa groupPersonIdJpa = new MemberJpa.GroupPersonIdJpa(groupJpa, personID);

        MemberJpa.GroupPersonIdJpa newGroupPersonIdJpa = new MemberJpa.GroupPersonIdJpa(newGroupJpa, newPersonID);

        boolean result = groupPersonIdJpa.hashCode()==newGroupPersonIdJpa.hashCode();

        //Assert

        assertFalse(groupPersonIdJpa.hashCode()==newGroupPersonIdJpa.hashCode());
        assertEquals(false, result);

    }

    //Get e Set members ID

    @Test
    @DisplayName("MembersJpa |Get e Set")
    public void membersJpaGetAndSet() {

        //Arrange GroupJpa


        String denomination = "Sunday Runners";
        String description = "All members from Sunday Runners group";
        String dateOfCreation = LocalDate.now().toString();

        GroupID groupID = GroupID.createGroupID(denomination);

        GroupJpa groupJpa = new GroupJpa(groupID, description, dateOfCreation);

        //Arrange personID

        String personId = "alexandre@gmail.com";
        PersonID personID = PersonID.createPersonID(personId);

        //Act

        MemberJpa memberJpa = new MemberJpa(groupJpa, personID);
        MemberJpa.GroupPersonIdJpa groupPersonIdJpa = new MemberJpa.GroupPersonIdJpa(groupJpa, personID);
        memberJpa.setId(groupPersonIdJpa);



        //Assert

        assertEquals(memberJpa.getId(), groupPersonIdJpa);

    }


    //ToString

    @Test
    @DisplayName("MembersJpa |ToString")
    public void members_JpaToString() {

        //Arrange GroupJpa


        String denomination = "Sunday Runners";
        String description = "All members from Sunday Runners group";
        String dateOfCreation = LocalDate.now().toString();

        GroupID groupID = GroupID.createGroupID(denomination);

        GroupJpa groupJpa = new GroupJpa(groupID, description, dateOfCreation);

        //Arrange personID

        String personId = "alexandre@gmail.com";
        PersonID personID = PersonID.createPersonID(personId);

        //Act

        MemberJpa memberJpa = new MemberJpa(groupJpa, personID);
        MemberJpa.GroupPersonIdJpa groupPersonIdJpa = new MemberJpa.GroupPersonIdJpa(groupJpa, personID);
        memberJpa.setId(groupPersonIdJpa);


        //Assert

        assertEquals("MemberJpa{id=GroupPersonIdJpa{groupID=GroupID{denomination=Denomination{denomination='Sunday Runners'}}, personID=PersonID{email=Email{email='alexandre@gmail.com'}}}}", memberJpa.toString());

    }

    //Equals Members

    @Test
    @DisplayName("MembersJpa |Equals: Same Object")
    public void members_JpaEqualsSameObject() {

        //Arrange GroupJpa


        String denomination = "Sunday Runners";
        String description = "All members from Sunday Runners group";
        String dateOfCreation = LocalDate.now().toString();

        GroupID groupID = GroupID.createGroupID(denomination);

        GroupJpa groupJpa = new GroupJpa(groupID, description, dateOfCreation);

        //Arrange personID

        String personId = "alexandre@gmail.com";
        PersonID personID = PersonID.createPersonID(personId);

        //Act

        MemberJpa memberJpa = new MemberJpa(groupJpa, personID);

        boolean result = memberJpa.equals(memberJpa);


        //Assert

        assertEquals(true, result);

    }

    @Test
    @DisplayName("MembersJpa |Equals: HappyCase")
    public void members_JpaEqualsHappyCase() {

        //Arrange GroupJpa


        String denomination = "Sunday Runners";
        String description = "All members from Sunday Runners group";
        String dateOfCreation = LocalDate.now().toString();

        GroupID groupID = GroupID.createGroupID(denomination);

        GroupJpa groupJpa = new GroupJpa(groupID, description, dateOfCreation);

        //Arrange personID

        String personId = "alexandre@gmail.com";
        PersonID personID = PersonID.createPersonID(personId);

        //Act

        MemberJpa memberJpa = new MemberJpa(groupJpa, personID);

        MemberJpa newMemberJpa = new MemberJpa(groupJpa, personID);

        boolean result = memberJpa.equals(newMemberJpa);


        //Assert

        assertEquals(true, result);

    }

    @Test
    @DisplayName("MembersJpa |Equals: null")
    public void members_JpaEqualsNull() {

        //Arrange GroupJpa


        String denomination = "Sunday Runners";
        String description = "All members from Sunday Runners group";
        String dateOfCreation = LocalDate.now().toString();

        GroupID groupID = GroupID.createGroupID(denomination);

        GroupJpa groupJpa = new GroupJpa(groupID, description, dateOfCreation);

        //Arrange personID

        String personId = "alexandre@gmail.com";
        PersonID personID = PersonID.createPersonID(personId);

        //Act

        MemberJpa memberJpa = new MemberJpa(groupJpa, personID);

        MemberJpa newMemberJpa = null;

        boolean result = memberJpa.equals(newMemberJpa);


        //Assert

        assertEquals(false, result);

    }

    @Test
    @DisplayName("MembersJpa |Equals: no Instance Of")
    public void members_JpaEqualsNoInstanceOf() {

        //Arrange GroupJpa


        String denomination = "Sunday Runners";
        String description = "All members from Sunday Runners group";
        String dateOfCreation = LocalDate.now().toString();

        GroupID groupID = GroupID.createGroupID(denomination);

        GroupJpa groupJpa = new GroupJpa(groupID, description, dateOfCreation);

        //Arrange personID

        String personId = "alexandre@gmail.com";
        PersonID personID = PersonID.createPersonID(personId);

        //Act

        MemberJpa memberJpa = new MemberJpa(groupJpa, personID);


        boolean result = memberJpa.equals(denomination);


        //Assert

        assertEquals(false, result);

    }

    @Test
    @DisplayName("MembersJpa |Equals: sadCase")
    public void members_JpaEqualsSadCase() {

        //Arrange GroupJpa

        String denomination = "Sunday Runners";
        String description = "All members from Sunday Runners group";
        String dateOfCreation = LocalDate.now().toString();

        GroupID groupID = GroupID.createGroupID(denomination);
        GroupJpa groupJpa = new GroupJpa(groupID, description, dateOfCreation);

        String denominationFontes = "Fontes Family";
        GroupID newGroupID = GroupID.createGroupID(denominationFontes);
        GroupJpa newGroupJpa = new GroupJpa(newGroupID, description, dateOfCreation);

        //Arrange personID

        String personId = "alexandre@gmail.com";
        PersonID personID = PersonID.createPersonID(personId);

        //Act

        MemberJpa memberJpa = new MemberJpa(groupJpa, personID);
        MemberJpa newMemberJpa = new MemberJpa(newGroupJpa, personID);




        boolean result = memberJpa.equals(newMemberJpa);


        //Assert

        assertEquals(false, result);

    }


    //HashCode

    @Test
    @DisplayName("MembersJpa |HashCode")
    public void members_JpaHashCode() {

        //Arrange GroupJpa


        String denomination = "Sunday Runners";
        String description = "All members from Sunday Runners group";
        String dateOfCreation = LocalDate.now().toString();

        GroupID groupID = GroupID.createGroupID(denomination);

        GroupJpa groupJpa = new GroupJpa(groupID, description, dateOfCreation);

        //Arrange personID

        String personId = "alexandre@gmail.com";
        PersonID personID = PersonID.createPersonID(personId);

        //Act

        MemberJpa memberJpa = new MemberJpa(groupJpa, personID);
        MemberJpa newMemberJpa = new MemberJpa(groupJpa, personID);

        boolean result = memberJpa.hashCode()==newMemberJpa.hashCode();


        //Assert

        assertTrue(memberJpa.hashCode()==memberJpa.hashCode());
        assertTrue(memberJpa.hashCode()==newMemberJpa.hashCode());
        assertEquals(true, result);

    }


    @Test
    @DisplayName("MembersJpa |HashCode:SadCase")
    public void members_JpaHashCode_SadCase() {

        //Arrange GroupJpa

        String denomination = "Sunday Runners";
        String description = "All members from Sunday Runners group";
        String dateOfCreation = LocalDate.now().toString();

        GroupID groupID = GroupID.createGroupID(denomination);
        GroupJpa groupJpa = new GroupJpa(groupID, description, dateOfCreation);

        String denominationFontes = "Fontes Family";
        GroupID newGroupID = GroupID.createGroupID(denominationFontes);
        GroupJpa newGroupJpa = new GroupJpa(newGroupID, description, dateOfCreation);

        //Arrange personID

        String personId = "alexandre@gmail.com";
        PersonID personID = PersonID.createPersonID(personId);

        String newPersonId = "paulo@gmail.com";
        PersonID newPersonID = PersonID.createPersonID(newPersonId);



        //Act

        MemberJpa memberJpa = new MemberJpa(groupJpa, personID);
        MemberJpa newMemberJpa = new MemberJpa(newGroupJpa, newPersonID);


        boolean result = memberJpa.hashCode()==newMemberJpa.hashCode();


        //Assert

        assertFalse(memberJpa.hashCode()==newMemberJpa.hashCode());
        assertEquals(false, result);

    }

}