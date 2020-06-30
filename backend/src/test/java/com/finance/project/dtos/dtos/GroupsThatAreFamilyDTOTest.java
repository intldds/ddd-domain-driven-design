package com.finance.project.dtos.dtos;

import com.finance.project.domainLayer.domainEntities.aggregates.group.Group;
import com.finance.project.domainLayer.domainEntities.aggregates.ledger.Ledger;
import com.finance.project.domainLayer.domainEntities.aggregates.person.Address;
import com.finance.project.domainLayer.domainEntities.aggregates.person.Person;
import com.finance.project.dtos.dtosAssemblers.GroupIDDTOAssembler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GroupsThatAreFamilyDTOTest {

    @Test
    @DisplayName("Test GroupsThatAreFamily constructor || Happy case")
    void testUS02_1_DTConstructor(){

        //Arrange

        List<GroupIDDTO> families = new ArrayList<>();

        //Act

        GroupsThatAreFamilyDTO groupsThatAreFamilyDTO = new GroupsThatAreFamilyDTO(families);

        //Assert

        assertEquals(families, groupsThatAreFamilyDTO.getGroupThatAreFamily());
    }

    //Equals

    @Test
    @DisplayName("Test GroupsThatAreFamily - Same Object")
    void testEqualsSameObject(){

        //Arrange

        List<GroupIDDTO> families = new ArrayList<>();

        //Act

        GroupsThatAreFamilyDTO groupsThatAreFamilyDTO = new GroupsThatAreFamilyDTO(families);

        //Assert

        assertEquals(true,groupsThatAreFamilyDTO.equals(groupsThatAreFamilyDTO));
    }

    @Test
    @DisplayName("Test GroupsThatAreFamily - Not instance of")
    void testEqualsNotInstanceOf(){

        //Arrange

        List<GroupIDDTO> families = new ArrayList<>();

        //Act

        GroupsThatAreFamilyDTO groupsThatAreFamilyDTO = new GroupsThatAreFamilyDTO(families);

        //Assert

        assertEquals(false,groupsThatAreFamilyDTO.equals(families));
    }

    @Test
    @DisplayName("Test GroupsThatAreFamily - Different Object - same Info")
    void testEqualsDifferentObjectSameInfo(){

        //Arrange

        List<GroupIDDTO> families = new ArrayList<>();
        List<GroupIDDTO> familiesExpected = new ArrayList<>();

        //Act

        GroupsThatAreFamilyDTO groupsThatAreFamilyDTO = new GroupsThatAreFamilyDTO(families);
        GroupsThatAreFamilyDTO groupsThatAreFamilyDTOexpected = new GroupsThatAreFamilyDTO(familiesExpected);

        //Assert

        assertEquals(true,groupsThatAreFamilyDTO.equals(groupsThatAreFamilyDTOexpected));
    }

    @Test
    @DisplayName("Test GroupsThatAreFamily HashCode - True")
    void testHashCodeSameObject() {

        //Arrange

        List<GroupIDDTO> families = new ArrayList<>();

        //Act

        GroupsThatAreFamilyDTO groupsThatAreFamilyDTO = new GroupsThatAreFamilyDTO(families);

        boolean resultHashcode = groupsThatAreFamilyDTO.hashCode() == groupsThatAreFamilyDTO.hashCode();

        //Assert

        assertEquals(true, resultHashcode);
        assertTrue(resultHashcode);
        assertEquals(groupsThatAreFamilyDTO.hashCode(), groupsThatAreFamilyDTO.hashCode());
    }

    @Test
    @DisplayName("Test GroupsThatAreFamily HashCode - True")
    void testHashCodeDifferentObject() {

        //Arrange
        
        //Arrange Address

        Address addressAna = Address.createAddress("Rua São Martinho","522","4785","Trofa","Portugal");
        Address addressJoao = Address.createAddress("Rua São Martinho","522","4785","Trofa","Portugal");
        Address addressJoana = Address.createAddress("Rua São Martinho","522","4785","Trofa","Portugal");
        Address addressElisabete = Address.createAddress("Rua São Martinho","522","4785","Trofa","Portugal");

        //Arrange Ledgers

        Ledger ledgerAna = Ledger.createLedger();
        Ledger ledgerJoao = Ledger.createLedger();
        Ledger ledgerJoana = Ledger.createLedger();
        Ledger ledgerElisabate = Ledger.createLedger();
        Ledger ledgerFamiliaAna = Ledger.createLedger();

        //Arrange Persons
        Person personJoao = Person.createPersonWithoutParents("joao@gmail.com","João", LocalDate.of(1965,10,11),"Porto",addressJoao,ledgerJoao.getLedgerID());
        Person personJoana = Person.createPersonWithoutParents("joana@gmail.com","Joana",LocalDate.of(1958,12,10),"Lisboa",addressJoana,ledgerJoana.getLedgerID());
        Person personAna = Person.createPersonWithParents("ana@gmail.com","Ana",LocalDate.of(1985,11,10),personJoana.getPersonID(),personJoao.getPersonID(),"Lisboa",addressAna,ledgerAna.getLedgerID());
        Person personElisabete = Person.createPersonWithParents("elisabete@gmail.com","Elisabete",LocalDate.of(1985,11,10),personJoana.getPersonID(),personJoao.getPersonID(),"Lisboa",addressElisabete,ledgerElisabate.getLedgerID());

        //Arrange Group

        Group groupFamiliaAna = Group.createGroupAsPersonInCharge("Familia da Ana",personAna.getPersonID(),"Familia da Ana",LocalDate.of(2020,01,12),ledgerFamiliaAna.getLedgerID());

        //Group Family Ana

        boolean result = personAna.addSibling(personElisabete.getPersonID());
        groupFamiliaAna.addMember(personJoao.getPersonID());
        groupFamiliaAna.addMember(personJoana.getPersonID());
        groupFamiliaAna.addMember(personElisabete.getPersonID());

        List<GroupIDDTO> families = new ArrayList<>();
        families.add(GroupIDDTOAssembler.createDTOFromDomainObject(groupFamiliaAna.getGroupID()));

        List<GroupIDDTO> notFamilies = new ArrayList<>();

        //Act

        GroupsThatAreFamilyDTO groupsThatAreFamilyDTO = new GroupsThatAreFamilyDTO(families);
        GroupsThatAreFamilyDTO groupsThatAreFamilyDTO1 = new GroupsThatAreFamilyDTO(notFamilies);

        boolean resultHashcode = groupsThatAreFamilyDTO.hashCode() == groupsThatAreFamilyDTO1.hashCode();

        //Assert

        assertEquals(false, resultHashcode);
        assertFalse(resultHashcode);
        assertNotEquals(groupsThatAreFamilyDTO.hashCode(), groupsThatAreFamilyDTO1.hashCode());
    }


}
