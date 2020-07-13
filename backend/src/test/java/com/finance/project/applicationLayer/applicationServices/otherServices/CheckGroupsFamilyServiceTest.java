package com.finance.project.applicationLayer.applicationServices.otherServices;

import com.finance.project.controllerLayer.integrationTests.AbstractTest;
import com.finance.project.domainLayer.domainEntities.aggregates.group.Group;
import com.finance.project.domainLayer.domainEntities.aggregates.person.Address;
import com.finance.project.domainLayer.domainEntities.aggregates.person.Person;
import com.finance.project.domainLayer.domainEntities.vosShared.LedgerID;
import com.finance.project.domainLayer.domainEntities.vosShared.PersonID;
import com.finance.project.domainLayer.exceptions.NotFoundArgumentsBusinessException;
import com.finance.project.domainLayer.repositoriesInterfaces.IGroupRepository;
import com.finance.project.domainLayer.repositoriesInterfaces.IPersonRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.finance.project.dtos.dtos.GroupDTO;
import com.finance.project.dtos.dtos.GroupIDDTO;
import com.finance.project.dtos.dtos.GroupMembersDTO;
import com.finance.project.dtos.dtos.GroupsThatAreFamilyDTO;
import com.finance.project.dtos.dtosAssemblers.GroupDTOAssembler;
import com.finance.project.dtos.dtosAssemblers.GroupMembersDTOAssembler;
import com.finance.project.dtos.dtosAssemblers.GroupsThatAreFamilyDTOAssembler;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
class CheckGroupsFamilyServiceTest extends AbstractTest {

    @Mock
    private IPersonRepository personRepository;
    @Mock
    private IGroupRepository groupRepository;

    private CheckGroupsFamilyService checkGroupsFamilyService;

    @Test
    @DisplayName("Test - GroupsThatAreFamily - 1 group")
    void controller_US04_OneGroupFamily() {

        checkGroupsFamilyService = new CheckGroupsFamilyService(groupRepository, personRepository);

        //        Arrange address
        String street = "Rua do molhe";
        String doorNumber = "2265";
        String postCode = "4569";
        String city = "Póvoa";
        String country = "Portugal";
        Address address = Address.createAddress(street, doorNumber, postCode, city, country);

        //        Arrange person Manuel
        String manuelEmail = "manuel@gmail.com";
        String manuelName = "Manuel Fontes";
        LocalDate manuelBirthdateLD = LocalDate.of(1964, 1, 16);
        String manuelBirthplace = "Vila Nova de Gaia";
        Person manuel = Person.createPerson(manuelEmail, manuelName, manuelBirthdateLD, manuelBirthplace);

        //        Arrange person Ilda
        String ildaEmail = "ilda@gmail.com";
        String ildaName = "Ilda Fontes";
        LocalDate ildaBirthdateLD = LocalDate.of(1963, 1, 9);
        String ildaBirthplace = "Vila Nova de Gaia";
        Person ilda = Person.createPerson(ildaEmail, ildaName, ildaBirthdateLD, ildaBirthplace);

        //        Arrange person Paulo
        String pauloEmail = "paulo@gmail.com";
        String pauloName = "Paulo Fontes";
        LocalDate pauloBirthdateLD = LocalDate.of(1993, 3, 15);
        String pauloBirthplace = "Vila Nova de Gaia";
        LedgerID pauloLedgerID = LedgerID.createLedgerID();
        Person paulo = Person.createPersonWithParents(pauloEmail, pauloName, pauloBirthdateLD, ilda.getPersonID(), manuel.getPersonID(), pauloBirthplace, address, pauloLedgerID);

        //        Arrange person Helder
        String helderEmail = "helder@gmail.com";
        String helderName = "Helder Fontes";
        LocalDate helderBirthdateLD = LocalDate.of(1983, 1, 30);
        String helderBirthplace = "Vila Nova de Gaia";
        LedgerID helderLedgerID = LedgerID.createLedgerID();
        Person helder = Person.createPersonWithParents(helderEmail, helderName, helderBirthdateLD, ilda.getPersonID(), manuel.getPersonID(), helderBirthplace, address, helderLedgerID);

        //        Add Helder to Paulo list of siblings
        paulo.addSibling(helder.getPersonID());

        //        Arrange Group

        String groupDenomination = "Fontes Family";
        LocalDate groupDateOfCreation = LocalDate.of(2020, 05, 27);
        String groupDescription = "All members from Fontes family";
        LedgerID groupLedgerID = LedgerID.createLedgerID();

        Group fontesFamily = Group.createGroupAsPersonInCharge(groupDenomination, ilda.getPersonID(), groupDescription, groupDateOfCreation, groupLedgerID);
        fontesFamily.addPersonInCharge(manuel.getPersonID());
        fontesFamily.addMember(paulo.getPersonID());
        fontesFamily.addMember(helder.getPersonID());

        List<Group> listOfGroups = new ArrayList<>();
        listOfGroups.add(fontesFamily);

        //        Act
        //          Mock the behaviour of groupRepository, returning an Optional<Group> pauloFamily
        Mockito.when(groupRepository.findAll()).thenReturn(listOfGroups);

        //          Mock the behaviour of personRepository, returning an Optional<Person> ilda
        Mockito.when(personRepository.findById(ilda.getPersonID())).thenReturn(Optional.of(ilda));

        //          Mock the behaviour of personRepository, returning an Optional<Person> manuel
        Mockito.when(personRepository.findById(manuel.getPersonID())).thenReturn(Optional.of(manuel));

        //          Mock the behaviour of personRepository, returning an Optional<Person> paulo
        Mockito.when(personRepository.findById(paulo.getPersonID())).thenReturn(Optional.of(paulo));

        //          Mock the behaviour of personRepository, returning an Optional<Person> helder
        Mockito.when(personRepository.findById(helder.getPersonID())).thenReturn(Optional.of(helder));

        //        Expected GroupsThatAreFamily
        GroupIDDTO groupIDDTOFontesFamily = new GroupIDDTO(fontesFamily.getGroupID().getDenomination().getDenomination());
        List<GroupIDDTO> listOfGroupIDDTO = new ArrayList<>();
        listOfGroupIDDTO.add(groupIDDTOFontesFamily);

        GroupsThatAreFamilyDTO expectedGroupsThatAreFamily = GroupsThatAreFamilyDTOAssembler.createDTOFromDomainObject(listOfGroupIDDTO);

//      Assert
        GroupsThatAreFamilyDTO groupsThatAreFamilyDTO = checkGroupsFamilyService.groupsThatAreFamily();

        assertEquals(expectedGroupsThatAreFamily, groupsThatAreFamilyDTO);
    }


    // No groups are family

    @Test
    @DisplayName("Test GroupsThatAreFamily - no groups - mother and father null")
    void controller_US04_NoGroupFamily() {

        checkGroupsFamilyService = new CheckGroupsFamilyService(groupRepository, personRepository);

        //        Arrange address
        String street = "Rua do molhe";
        String doorNumber = "2265";
        String postCode = "4569";
        String city = "Póvoa";
        String country = "Portugal";
        Address address = Address.createAddress(street, doorNumber, postCode, city, country);

        //        Arrange person Paulo
        String pauloEmail = "paulo@gmail.com";
        String pauloName = "Paulo Fontes";
        LocalDate pauloBirthdateLD = LocalDate.of(1993, 3, 15);
        String pauloBirthplace = "Vila Nova de Gaia";
        LedgerID pauloLedgerID = LedgerID.createLedgerID();
        Person paulo = Person.createPersonWithoutParents(pauloEmail, pauloName, pauloBirthdateLD, pauloBirthplace, address, pauloLedgerID);

        //        Arrange person Helder
        String helderEmail = "helder@gmail.com";
        String helderName = "Helder Fontes";
        LocalDate helderBirthdateLD = LocalDate.of(1983, 1, 30);
        String helderBirthplace = "Vila Nova de Gaia";
        LedgerID helderLedgerID = LedgerID.createLedgerID();
        Person helder = Person.createPersonWithoutParents(helderEmail, helderName, helderBirthdateLD, helderBirthplace, address, helderLedgerID);

        //        Add Helder to Paulo list of siblings
        paulo.addSibling(helder.getPersonID());

        //        Arrange Group
        String groupDenomination = "Fontes Group";
        LocalDate groupDateOfCreation = LocalDate.of(2020, 05, 27);
        String groupDescription = "All members from Fontes Group";
        LedgerID groupLedgerID = LedgerID.createLedgerID();

        Group fontesGroup = Group.createGroupAsPersonInCharge(groupDenomination, paulo.getPersonID(), groupDescription, groupDateOfCreation, groupLedgerID);
        fontesGroup.addMember(helder.getPersonID());

        List<Group> listOfGroups = new ArrayList<>();
        listOfGroups.add(fontesGroup);

        //        Act
        //        Mock the behaviour of groupRepository, returning an Optional<Group> pauloFamily
        Mockito.when(groupRepository.findAll()).thenReturn(listOfGroups);

        //          Mock the behaviour of personRepository, returning an Optional<Person> paulo
        Mockito.when(personRepository.findById(paulo.getPersonID())).thenReturn(Optional.of(paulo));

        //          Mock the behaviour of personRepository, returning an Optional<Person> helder
        Mockito.when(personRepository.findById(helder.getPersonID())).thenReturn(Optional.of(helder));

        //        Expected GroupsThatAreFamily
        List<GroupIDDTO> listOfGroupIDDTO = new ArrayList<>();

        GroupsThatAreFamilyDTO expectedGroupsThatAreFamily = GroupsThatAreFamilyDTOAssembler.createDTOFromDomainObject(listOfGroupIDDTO);

        //      Assert
        GroupsThatAreFamilyDTO groupsThatAreFamilyDTO = checkGroupsFamilyService.groupsThatAreFamily();

        assertEquals(expectedGroupsThatAreFamily, groupsThatAreFamilyDTO);
    }

    // no group

    @Test
    @DisplayName("Test - GroupsThatAreFamily - 0 group")
    void controller_US04_NoGroupFamilyDifferentList() {

        checkGroupsFamilyService = new CheckGroupsFamilyService(groupRepository, personRepository);

        //        Arrange address
        String street = "Rua do molhe";
        String doorNumber = "2265";
        String postCode = "4569";
        String city = "Póvoa";
        String country = "Portugal";
        Address address = Address.createAddress(street, doorNumber, postCode, city, country);

        //        Arrange person Manuel
        String manuelEmail = "manuel@gmail.com";
        String manuelName = "Manuel Fontes";
        LocalDate manuelBirthdateLD = LocalDate.of(1964, 1, 16);
        String manuelBirthplace = "Vila Nova de Gaia";
        Person manuel = Person.createPerson(manuelEmail, manuelName, manuelBirthdateLD, manuelBirthplace);

        //        Arrange person Ilda
        String ildaEmail = "ilda@gmail.com";
        String ildaName = "Ilda Fontes";
        LocalDate ildaBirthdateLD = LocalDate.of(1963, 1, 9);
        String ildaBirthplace = "Vila Nova de Gaia";
        Person ilda = Person.createPerson(ildaEmail, ildaName, ildaBirthdateLD, ildaBirthplace);

        //        Arrange person Paulo
        String pauloEmail = "paulo@gmail.com";
        String pauloName = "Paulo Fontes";
        LocalDate pauloBirthdateLD = LocalDate.of(1993, 3, 15);
        String pauloBirthplace = "Vila Nova de Gaia";
        LedgerID pauloLedgerID = LedgerID.createLedgerID();
        Person paulo = Person.createPersonWithParents(pauloEmail, pauloName, pauloBirthdateLD, ilda.getPersonID(), manuel.getPersonID(), pauloBirthplace, address, pauloLedgerID);

        //        Arrange person Helder
        String helderEmail = "helder@gmail.com";
        String helderName = "Helder Fontes";
        LocalDate helderBirthdateLD = LocalDate.of(1983, 1, 30);
        String helderBirthplace = "Vila Nova de Gaia";
        LedgerID helderLedgerID = LedgerID.createLedgerID();
        Person helder = Person.createPersonWithParents(helderEmail, helderName, helderBirthdateLD, ilda.getPersonID(), manuel.getPersonID(), helderBirthplace, address, helderLedgerID);

        //        Arrange person Joana
        String joanaEmail = "joana@gmail.com";
        String joanaName = "Joana Silva";
        LocalDate joanaBirthdateLD = LocalDate.of(1983, 1, 30);
        String joanaBirthplace = "Vila Nova de Gaia";
        LedgerID joanaLedgerID = LedgerID.createLedgerID();
        Person joana = Person.createPersonWithoutParents(joanaEmail, joanaName, joanaBirthdateLD, joanaBirthplace, address, joanaLedgerID);

        //        Add Helder to Paulo list of siblings
        paulo.addSibling(helder.getPersonID());

        //        Arrange Group
        String groupDenomination = "Fontes Friends";
        LocalDate groupDateOfCreation = LocalDate.of(2020, 05, 27);
        String groupDescription = "All members from Fontes friends";
        LedgerID groupLedgerID = LedgerID.createLedgerID();

        Group fontesFriends = Group.createGroupAsPersonInCharge(groupDenomination, ilda.getPersonID(), groupDescription, groupDateOfCreation, groupLedgerID);
        fontesFriends.addPersonInCharge(manuel.getPersonID());
        fontesFriends.addMember(paulo.getPersonID());
        fontesFriends.addMember(helder.getPersonID());
        fontesFriends.addMember(joana.getPersonID());

        List<Group> listOfGroups = new ArrayList<>();
        listOfGroups.add(fontesFriends);

        //        Act
        //          Mock the behaviour of groupRepository
        //        Returning an Optional<Group> pauloFamily
        Mockito.when(groupRepository.findAll())
                .thenReturn(listOfGroups);

        //          Mock the behaviour of personRepository
        //        Returning an Optional<Person> ilda
        Mockito.when(personRepository.findById(ilda.getPersonID()))
                .thenReturn(Optional.of(ilda));

        //          Mock the behaviour of personRepository
        //        Returning an Optional<Person> manuel
        Mockito.when(personRepository.findById(manuel.getPersonID()))
                .thenReturn(Optional.of(manuel));

        //          Mock the behaviour of personRepository
        //        Returning an Optional<Person> paulo
        Mockito.when(personRepository.findById(paulo.getPersonID()))
                .thenReturn(Optional.of(paulo));

        //          Mock the behaviour of personRepository
        //        Returning an Optional<Person> helder
        Mockito.when(personRepository.findById(helder.getPersonID()))
                .thenReturn(Optional.of(helder));

        //          Mock the behaviour of personRepository
        //        Returning an Optional<Person> helder
        Mockito.when(personRepository.findById(joana.getPersonID()))
                .thenReturn(Optional.of(joana));

        //        Expected GroupsThatAreFamily

        List<GroupIDDTO> listOfGroupIDDTO = new ArrayList<>();

        GroupsThatAreFamilyDTO expectedGroupsThatAreFamily = GroupsThatAreFamilyDTOAssembler.createDTOFromDomainObject(listOfGroupIDDTO);

        //      Assert
        GroupsThatAreFamilyDTO groupsThatAreFamilyDTO = checkGroupsFamilyService.groupsThatAreFamily();

        assertEquals(expectedGroupsThatAreFamily, groupsThatAreFamilyDTO);
    }


}


