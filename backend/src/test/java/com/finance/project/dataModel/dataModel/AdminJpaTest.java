package com.finance.project.dataModel.dataModel;

import com.finance.project.domainLayer.domainEntities.vosShared.GroupID;
import com.finance.project.domainLayer.domainEntities.vosShared.PersonID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AdminJpaTest {

   @Test
   @DisplayName("Test for createAdminJpa()")
    public void checkCreateAdminJpa() {

       //Arrange personID

       String personId = "alexandre@gmail.com";
       PersonID personID = PersonID.createPersonID(personId);

       //Arrange GroupJpa

       String denomination = "Sunday Runners";
       String description = "All members from Sunday Runners group";
       String dateOfCreation = LocalDate.now().toString();

       GroupID groupID = GroupID.createGroupID(denomination);

       GroupJpa groupJpa = new GroupJpa(groupID, description, dateOfCreation);

       //Act

       AdminJpa.GroupPersonIdJpa groupPersonIdJpa = new AdminJpa.GroupPersonIdJpa(groupJpa, personID);

       //Assert

       assertEquals(groupPersonIdJpa.getGroupJpa(), groupJpa);
       Assertions.assertEquals(groupPersonIdJpa.getPersonID(), personID);


    }

    @Test
    @DisplayName("Test for createAdminJpa()_Empty Constructor")
    public void checkCreateAdminJpa_EmptyConstructor() {

        //Arrange personID

        String personId = "alexandre@gmail.com";
        PersonID personID = PersonID.createPersonID(personId);

        //Arrange GroupJpa

        String denomination = "Sunday Runners";
        String description = "All members from Sunday Runners group";
        String dateOfCreation = LocalDate.now().toString();

        GroupID groupID = GroupID.createGroupID(denomination);

        GroupJpa groupJpa = new GroupJpa(groupID, description, dateOfCreation);

        //Act

        AdminJpa.GroupPersonIdJpa groupPersonIdJpa = new AdminJpa.GroupPersonIdJpa();
        groupPersonIdJpa.setGroupJpa(groupJpa);
        groupPersonIdJpa.setPersonID(personID);

        //Assert

        assertEquals(groupPersonIdJpa.getGroupJpa(), groupJpa);
        Assertions.assertEquals(groupPersonIdJpa.getPersonID(), personID);


    }

    //ToString

    @Test
    @DisplayName("Test for createAdminJpa : ToString")
    public void checkCreateAdminJpa_ToString() {

        //Arrange personID

        String personId = "alexandre@gmail.com";
        PersonID personID = PersonID.createPersonID(personId);

        //Arrange GroupJpa

        String denomination = "Sunday Runners";
        String description = "All members from Sunday Runners group";
        String dateOfCreation = LocalDate.now().toString();

        GroupID groupID = GroupID.createGroupID(denomination);

        GroupJpa groupJpa = new GroupJpa(groupID, description, dateOfCreation);

        //Act

        AdminJpa.GroupPersonIdJpa groupPersonIdJpa = new AdminJpa.GroupPersonIdJpa(groupJpa, personID);

        //Assert

        assertEquals("GroupPersonIdJpa{groupID=GroupID{denomination=Denomination{denomination='Sunday Runners'}}, personID=PersonID{email=Email{email='alexandre@gmail.com'}}}", groupPersonIdJpa.toString());



    }

    //Equals

    @Test
    @DisplayName("Test for EqualsAdminJpa: SameObject")
    public void AdminJpa_Equals_SameObject() {

        //Arrange personID

        String personId = "alexandre@gmail.com";
        PersonID personID = PersonID.createPersonID(personId);

        //Arrange GroupJpa

        String denomination = "Sunday Runners";
        String description = "All members from Sunday Runners group";
        String dateOfCreation = LocalDate.now().toString();

        GroupID groupID = GroupID.createGroupID(denomination);

        GroupJpa groupJpa = new GroupJpa(groupID, description, dateOfCreation);

        //Act

        AdminJpa.GroupPersonIdJpa groupPersonIdJpa = new AdminJpa.GroupPersonIdJpa(groupJpa, personID);


        boolean result = groupPersonIdJpa.equals(groupPersonIdJpa);

        //Assert

        assertEquals(true, result);


    }


    @Test
    @DisplayName("Test for EqualsAdminJpa: HappyCase")
    public void AdminJpa_Equals_HappyCase() {

        //Arrange personID

        String personId = "alexandre@gmail.com";
        PersonID personID = PersonID.createPersonID(personId);

        //Arrange GroupJpa

        String denomination = "Sunday Runners";
        String description = "All members from Sunday Runners group";
        String dateOfCreation = LocalDate.now().toString();

        GroupID groupID = GroupID.createGroupID(denomination);

        GroupJpa groupJpa = new GroupJpa(groupID, description, dateOfCreation);

        //Act

        AdminJpa.GroupPersonIdJpa groupPersonIdJpa = new AdminJpa.GroupPersonIdJpa(groupJpa, personID);
        AdminJpa.GroupPersonIdJpa newGroupPersonIdJpa = new AdminJpa.GroupPersonIdJpa(groupJpa, personID);

        boolean result = groupPersonIdJpa.equals(newGroupPersonIdJpa);

        //Assert

        assertEquals(true, result);


    }

    @DisplayName("Test for EqualsAdminJpa: null")
    public void AdminJpa_Equals_Null() {

        //Arrange personID

        String personId = "alexandre@gmail.com";
        PersonID personID = PersonID.createPersonID(personId);

        //Arrange GroupJpa

        String denomination = "Sunday Runners";
        String description = "All members from Sunday Runners group";
        String dateOfCreation = LocalDate.now().toString();

        GroupID groupID = GroupID.createGroupID(denomination);

        GroupJpa groupJpa = new GroupJpa(groupID, description, dateOfCreation);

        //Act

        AdminJpa.GroupPersonIdJpa groupPersonIdJpa = new AdminJpa.GroupPersonIdJpa(groupJpa, personID);
        AdminJpa.GroupPersonIdJpa newGroupPersonIdJpa = null;

        boolean result = groupPersonIdJpa.equals(newGroupPersonIdJpa);

        //Assert

        assertEquals(false, result);
        assertFalse(groupPersonIdJpa.equals(newGroupPersonIdJpa));

    }

    @DisplayName("Test for EqualsAdminJpa: not Instance of")
    public void AdminJpa_Equals_NotInstanceOf() {

        //Arrange personID

        String personId = "alexandre@gmail.com";
        PersonID personID = PersonID.createPersonID(personId);

        //Arrange GroupJpa

        String denomination = "Sunday Runners";
        String description = "All members from Sunday Runners group";
        String dateOfCreation = LocalDate.now().toString();

        GroupID groupID = GroupID.createGroupID(denomination);

        GroupJpa groupJpa = new GroupJpa(groupID, description, dateOfCreation);

        //Act

        AdminJpa.GroupPersonIdJpa groupPersonIdJpa = new AdminJpa.GroupPersonIdJpa(groupJpa, personID);

        boolean result = groupPersonIdJpa.equals(groupJpa);

        //Assert

        assertEquals(false, result);
        assertFalse(groupPersonIdJpa.equals(denomination));

    }

    @Test
    @DisplayName("Test for EqualsAdminJpa: Different GroupId")
    public void AdminJpa_Equals_DifferentGroupId() {

        //Arrange personID

        String personId = "alexandre@gmail.com";
        PersonID personID = PersonID.createPersonID(personId);

        //Arrange GroupJpa

        String denomination = "Sunday Runners";
        String denominationB = "Fontes Family";
        String description = "All members from Sunday Runners group";
        String dateOfCreation = LocalDate.now().toString();

        GroupID groupID = GroupID.createGroupID(denomination);
        GroupID newGroupID = GroupID.createGroupID(denominationB);

        GroupJpa groupJpa = new GroupJpa(groupID, description, dateOfCreation);
        GroupJpa newGroupJpa = new GroupJpa(newGroupID, description, dateOfCreation);


        //Act

        AdminJpa.GroupPersonIdJpa groupPersonIdJpa = new AdminJpa.GroupPersonIdJpa(groupJpa, personID);
        AdminJpa.GroupPersonIdJpa newGroupPersonIdJpa = new AdminJpa.GroupPersonIdJpa(newGroupJpa, personID);

        boolean result = groupPersonIdJpa.equals(newGroupPersonIdJpa);

        //Assert

        assertEquals(false, result);

    }


    @Test
    @DisplayName("Test for EqualsAdminJpa: Different PersonId")
    public void AdminJpa_Equals_DifferentPersonId() {

        //Arrange personID

        String personId = "alexandre@gmail.com";
        PersonID personID = PersonID.createPersonID(personId);

        String newPersonId = "paulo@gmail.com";
        PersonID newPersonID = PersonID.createPersonID(newPersonId);

        //Arrange GroupJpa

        String denomination = "Sunday Runners";
        String description = "All members from Sunday Runners group";
        String dateOfCreation = LocalDate.now().toString();

        GroupID groupID = GroupID.createGroupID(denomination);


        GroupJpa groupJpa = new GroupJpa(groupID, description, dateOfCreation);


        //Act

        AdminJpa.GroupPersonIdJpa groupPersonIdJpa = new AdminJpa.GroupPersonIdJpa(groupJpa, personID);
        AdminJpa.GroupPersonIdJpa newGroupPersonIdJpa = new AdminJpa.GroupPersonIdJpa(groupJpa, newPersonID);

        boolean result = groupPersonIdJpa.equals(newGroupPersonIdJpa);

        //Assert

        assertEquals(false, result);

    }



    //Hashcode

    @Test
    @DisplayName("Test for HashCodeAdminJpa: True")
    public void AdminJpa_HashCode_True() {

        //Arrange personID

        String personId = "alexandre@gmail.com";
        PersonID personID = PersonID.createPersonID(personId);

        //Arrange GroupJpa

        String denomination = "Sunday Runners";
        String description = "All members from Sunday Runners group";
        String dateOfCreation = LocalDate.now().toString();

        GroupID groupID = GroupID.createGroupID(denomination);

        GroupJpa groupJpa = new GroupJpa(groupID, description, dateOfCreation);

        //Act

        AdminJpa.GroupPersonIdJpa groupPersonIdJpa = new AdminJpa.GroupPersonIdJpa(groupJpa, personID);
        AdminJpa.GroupPersonIdJpa newGroupPersonIdJpa = new AdminJpa.GroupPersonIdJpa(groupJpa, personID);

        boolean result = groupPersonIdJpa.hashCode()==groupPersonIdJpa.hashCode();

        //Assert

        assertTrue(groupPersonIdJpa.hashCode()==newGroupPersonIdJpa.hashCode());
        assertEquals(true, result);


    }

    @Test
    @DisplayName("Test for HashCodeAdminJpa: SadCase")
    public void AdminJpa_HashCode_SadCase() {

        //Arrange personID

        String personId = "alexandre@gmail.com";
        PersonID personID = PersonID.createPersonID(personId);

        String newPersonId = "paulo@gmail.com";
        PersonID newPersonID = PersonID.createPersonID(newPersonId);

        //Arrange GroupJpa

        String denomination = "Sunday Runners";
        String denominationB = "Fontes Family";
        String description = "All members from Sunday Runners group";
        String dateOfCreation = LocalDate.now().toString();

        GroupID groupID = GroupID.createGroupID(denomination);
        GroupID newGroupID = GroupID.createGroupID(denominationB);

        GroupJpa groupJpa = new GroupJpa(groupID, description, dateOfCreation);
        GroupJpa newGroupJpa = new GroupJpa(newGroupID, description, dateOfCreation);

        //Act

        AdminJpa.GroupPersonIdJpa groupPersonIdJpa = new AdminJpa.GroupPersonIdJpa(groupJpa, personID);

        AdminJpa.GroupPersonIdJpa newGroupPersonIdJpa = new AdminJpa.GroupPersonIdJpa(newGroupJpa, newPersonID);

        boolean result = groupPersonIdJpa.hashCode()==newGroupPersonIdJpa.hashCode();


        //Assert

        assertFalse(groupPersonIdJpa.hashCode()==newGroupPersonIdJpa.hashCode());
        assertEquals(false, result);


    }


    //GroupPersonIdJpa Get ID

    @Test
    @DisplayName("Test for AdminJpa : Get ID")
    public void AdminJpa_GetID() {

        //Arrange personID

        String personId = "alexandre@gmail.com";
        PersonID personID = PersonID.createPersonID(personId);

        //Arrange GroupJpa

        String denomination = "Sunday Runners";
        String description = "All members from Sunday Runners group";
        String dateOfCreation = LocalDate.now().toString();

        GroupID groupID = GroupID.createGroupID(denomination);

        GroupJpa groupJpa = new GroupJpa(groupID, description, dateOfCreation);

        //Act

        AdminJpa adminJpa = new AdminJpa(groupJpa, personID);

        AdminJpa.GroupPersonIdJpa groupPersonIdJpa = new AdminJpa.GroupPersonIdJpa(groupJpa, personID);


        //Assert

        assertEquals(adminJpa.getId(),groupPersonIdJpa );

    }

    //GroupPersonIdJpa Set ID

    @Test
    @DisplayName("Test for AdminJpa: Set ID")
    public void dminJpa() {

        //Arrange personID

        String personId = "alexandre@gmail.com";
        PersonID personID = PersonID.createPersonID(personId);

        //Arrange GroupJpa

        String denomination = "Sunday Runners";
        String description = "All members from Sunday Runners group";
        String dateOfCreation = LocalDate.now().toString();

        GroupID groupID = GroupID.createGroupID(denomination);

        GroupJpa groupJpa = new GroupJpa(groupID, description, dateOfCreation);

        //Act

        AdminJpa adminJpa = new AdminJpa(groupJpa, personID);

        AdminJpa.GroupPersonIdJpa groupPersonIdJpa = new AdminJpa.GroupPersonIdJpa(groupJpa, personID);

        adminJpa.setId(groupPersonIdJpa);

        //Assert

        assertEquals(adminJpa.getId(), groupPersonIdJpa );

    }

    //To String Admin


    @Test
    @DisplayName("Test for AdminJpa : To String")
    public void AdminJpa_ToString() {

        //Arrange personID

        String personId = "alexandre@gmail.com";
        PersonID personID = PersonID.createPersonID(personId);

        //Arrange GroupJpa

        String denomination = "Sunday Runners";
        String description = "All members from Sunday Runners group";
        String dateOfCreation = LocalDate.now().toString();

        GroupID groupID = GroupID.createGroupID(denomination);

        GroupJpa groupJpa = new GroupJpa(groupID, description, dateOfCreation);

        //Act

        AdminJpa adminJpa = new AdminJpa(groupJpa, personID);

        AdminJpa.GroupPersonIdJpa groupPersonIdJpa = new AdminJpa.GroupPersonIdJpa(groupJpa, personID);


        //Assert

        assertEquals("AdminJpa{id=GroupPersonIdJpa{groupID=GroupID{denomination=Denomination{denomination='Sunday Runners'}}, personID=PersonID{email=Email{email='alexandre@gmail.com'}}}}", adminJpa.toString());


    }


    //Equals

    @Test
    @DisplayName("Test for Equals AdminJpa : Same Object")
    public void EqualsAdminJpa_SameObject() {

        //Arrange personID

        String personId = "alexandre@gmail.com";
        PersonID personID = PersonID.createPersonID(personId);

        //Arrange GroupJpa

        String denomination = "Sunday Runners";
        String description = "All members from Sunday Runners group";
        String dateOfCreation = LocalDate.now().toString();

        GroupID groupID = GroupID.createGroupID(denomination);

        GroupJpa groupJpa = new GroupJpa(groupID, description, dateOfCreation);

        //Act

        AdminJpa adminJpa = new AdminJpa(groupJpa, personID);

        boolean result = adminJpa.equals(adminJpa);

        //Assert

        assertEquals(true, result );

    }

    @Test
    @DisplayName("Test for Equals AdminJpa : Happy Case")
    public void EqualsAdminJpa_HappyCase() {

        //Arrange personID

        String personId = "alexandre@gmail.com";
        PersonID personID = PersonID.createPersonID(personId);

        //Arrange GroupJpa

        String denomination = "Sunday Runners";
        String description = "All members from Sunday Runners group";
        String dateOfCreation = LocalDate.now().toString();

        GroupID groupID = GroupID.createGroupID(denomination);

        GroupJpa groupJpa = new GroupJpa(groupID, description, dateOfCreation);

        //Act

        AdminJpa adminJpa = new AdminJpa(groupJpa, personID);
        AdminJpa newAdminJpa = new AdminJpa(groupJpa, personID);

        boolean result = adminJpa.equals(newAdminJpa);

        //Assert

        assertEquals(true, result );

    }


    @Test
    @DisplayName("Test for Equals AdminJpa : null")
    public void EqualsAdminJpa_Null() {

        //Arrange personID

        String personId = "alexandre@gmail.com";
        PersonID personID = PersonID.createPersonID(personId);

        //Arrange GroupJpa

        String denomination = "Sunday Runners";
        String description = "All members from Sunday Runners group";
        String dateOfCreation = LocalDate.now().toString();

        GroupID groupID = GroupID.createGroupID(denomination);

        GroupJpa groupJpa = new GroupJpa(groupID, description, dateOfCreation);

        //Act

        AdminJpa adminJpa = new AdminJpa(groupJpa, personID);
        AdminJpa newAdminJpa = null;

        boolean result = adminJpa.equals(newAdminJpa);

        //Assert

        assertEquals(false, result );
        assertFalse(adminJpa.equals(newAdminJpa));

    }

    @Test
    @DisplayName("Test for Equals AdminJpa : no Instance Of")
    public void EqualsAdminJpa_NoInstanceOf() {

        //Arrange personID

        String personId = "alexandre@gmail.com";
        PersonID personID = PersonID.createPersonID(personId);

        //Arrange GroupJpa

        String denomination = "Sunday Runners";
        String description = "All members from Sunday Runners group";
        String dateOfCreation = LocalDate.now().toString();

        GroupID groupID = GroupID.createGroupID(denomination);

        GroupJpa groupJpa = new GroupJpa(groupID, description, dateOfCreation);

        //Act

        AdminJpa adminJpa = new AdminJpa(groupJpa, personID);
        AdminJpa newAdminJpa = null;

        boolean result = adminJpa.equals(denomination);

        //Assert

        assertEquals(false, result );
        assertFalse(adminJpa.equals(denomination));

    }

    @Test
    @DisplayName("Test for Equals AdminJpa : SadCase")
    public void EqualsAdminJpa_SadCase() {

        //Arrange personID

        String personId = "alexandre@gmail.com";
        PersonID personID = PersonID.createPersonID(personId);

        String newPersonId = "paulo@gmail.com";
        PersonID newPersonID = PersonID.createPersonID(newPersonId);


        //Arrange GroupJpa

        String denomination = "Sunday Runners";
        String description = "All members from Sunday Runners group";
        String dateOfCreation = LocalDate.now().toString();

        GroupID groupID = GroupID.createGroupID(denomination);

        GroupJpa groupJpa = new GroupJpa(groupID, description, dateOfCreation);

        //Act

        AdminJpa adminJpa = new AdminJpa(groupJpa, personID);
        AdminJpa newAdminJpa = new AdminJpa(groupJpa, newPersonID);


        boolean result = adminJpa.equals(newAdminJpa);

        //Assert

        assertEquals(false, result );

    }

    //HashCode

    @Test
    @DisplayName("Test for HashCode AdminJpa : HappyCase")
    public void HashCodesAdminJpa_HappyCase() {

        //Arrange personID

        String personId = "alexandre@gmail.com";
        PersonID personID = PersonID.createPersonID(personId);

        //Arrange GroupJpa

        String denomination = "Sunday Runners";
        String description = "All members from Sunday Runners group";
        String dateOfCreation = LocalDate.now().toString();

        GroupID groupID = GroupID.createGroupID(denomination);

        GroupJpa groupJpa = new GroupJpa(groupID, description, dateOfCreation);

        //Act

        AdminJpa adminJpa = new AdminJpa(groupJpa, personID);

        boolean result = adminJpa.hashCode()==adminJpa.hashCode();

        //Assert

        assertEquals(true, result );

    }

    @Test
    @DisplayName("Test for HashCode AdminJpa : SadCase")
    public void HashCodeAdminJpa_SadCase() {

        //Arrange personID

        String personId = "alexandre@gmail.com";
        PersonID personID = PersonID.createPersonID(personId);

        String newPersonId = "paulo@gmail.com";
        PersonID newPersonID = PersonID.createPersonID(newPersonId);


        //Arrange GroupJpa

        String denomination = "Sunday Runners";
        String description = "All members from Sunday Runners group";
        String dateOfCreation = LocalDate.now().toString();

        GroupID groupID = GroupID.createGroupID(denomination);

        GroupJpa groupJpa = new GroupJpa(groupID, description, dateOfCreation);

        //Act

        AdminJpa adminJpa = new AdminJpa(groupJpa, personID);
        AdminJpa newAdminJpa = new AdminJpa(groupJpa, newPersonID);


        boolean result = adminJpa.hashCode()==newAdminJpa.hashCode();

        //Assert

        assertFalse(result);
        assertEquals(false, result );

    }


}