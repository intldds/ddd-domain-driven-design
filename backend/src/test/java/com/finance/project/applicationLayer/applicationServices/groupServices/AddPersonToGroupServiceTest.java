package com.finance.project.applicationLayer.applicationServices.groupServices;

import com.finance.project.controllerLayer.integrationTests.AbstractTest;
import com.finance.project.domainLayer.domainEntities.aggregates.group.Group;
import com.finance.project.domainLayer.domainEntities.vosShared.GroupID;
import com.finance.project.domainLayer.domainEntities.vosShared.LedgerID;
import com.finance.project.domainLayer.domainEntities.vosShared.PersonID;
import com.finance.project.domainLayer.exceptions.InvalidArgumentsBusinessException;
import com.finance.project.domainLayer.exceptions.NotFoundArgumentsBusinessException;
import com.finance.project.domainLayer.repositoriesInterfaces.IGroupRepository;
import com.finance.project.domainLayer.repositoriesInterfaces.IPersonRepository;
import com.finance.project.dtos.dtosAssemblers.AddPersonToGroupDTOAssembler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import com.finance.project.dtos.dtos.AddPersonToGroupDTO;
import com.finance.project.dtos.dtos.GroupDTO;
import com.finance.project.dtos.dtosAssemblers.GroupDTOAssembler;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddPersonToGroupServiceTest extends AbstractTest {

    @Mock
    private IGroupRepository groupRepository;
    @Mock
    private IPersonRepository personRepository;


    private AddPersonToGroupService addPersonToGroupService;

    // Tests

    // Success

    @Test
    @DisplayName("Test Service_US03 -HappyCase - Person Exist, Group Exist, Person is not already a Member")
    void addPersonToGroup_Success() {

//      Instantiating an us003AddPersonToGroupService with personRepository and groupRepository as parameter
        addPersonToGroupService = new AddPersonToGroupService(personRepository, groupRepository);

        //Arrange persons
        String personEmailAlexandre = "alexandre@gmail.com";

        PersonID personIDAlexandre = PersonID.createPersonID(personEmailAlexandre);

        String personEmailJoana = "joana@gmail.com";

        PersonID personIDJoana = PersonID.createPersonID(personEmailJoana);

        // Arrange group and groupID
        String groupDenomination = "Sunday Runners";
        String groupDescription = "All members from Sunday Runners group";
        LocalDate groupDateOfCreation = LocalDate.of(2020, 05, 28);
        LedgerID groupLedgerID = LedgerID.createLedgerID();

        GroupID groupSundayRunnerID = GroupID.createGroupID(groupDenomination);

        Group groupSundayRunner = Group.createGroupAsPersonInCharge(groupDenomination, personIDJoana, groupDescription, groupDateOfCreation, groupLedgerID);

        //Arrange DTO in
        AddPersonToGroupDTO addPersonToGroupDTO = AddPersonToGroupDTOAssembler.createDataTransferObject_Primitives(personEmailAlexandre, groupDenomination);

//        Act
//        Mock the behaviour of personRepository
//        Returning a boolean response true - personIDAlexandre exists
        Mockito.when(personRepository.exists(personIDAlexandre))
                .thenReturn(true);

//        Returning an Optional<Group> groupSundayRunner
        Mockito.when(groupRepository.findById(groupSundayRunnerID))
                .thenReturn(Optional.of(groupSundayRunner));

//        Returning a boolean response true - person added to group
        Mockito.when(groupRepository.addAndSaveMember(groupSundayRunner, personIDAlexandre))
                .thenReturn(true);

        //Expected GroupDTO
        GroupDTO expectedGroupDTO = GroupDTOAssembler.createDTOFromDomainObject(groupSundayRunner.getGroupID().getDenomination(), groupSundayRunner.getDescription(), groupSundayRunner.getDateOfCreation());

        //Assert
        GroupDTO groupDTO = addPersonToGroupService.addPersonToGroup(addPersonToGroupDTO);

        assertEquals(expectedGroupDTO, groupDTO);
    }

    // Group does not exist - throw exception

    @Test
    @DisplayName("Test Service_US03 -SadCase - Group does not exist")
    void service_US03_false_GroupDoesNotExist() {

        //      Instantiating an us003AddPersonToGroupService with personRepository and groupRepository as parameter
        addPersonToGroupService = new AddPersonToGroupService(personRepository, groupRepository);

        // Arrange persons

        String personEmailAlexandre = "alexandre@gmail.com";

        PersonID personIDAlexandre = PersonID.createPersonID(personEmailAlexandre);

        String personEmailJoana = "joana@gmail.com";

        PersonID personIDJoana = PersonID.createPersonID(personEmailJoana);

        // Arrange group and groupID

        String groupDenomination = "Sunday";
        String groupDescription = "All members from Sunday Runners group";
        LocalDate groupDateOfCreation = LocalDate.of(2020, 05, 28);
        LedgerID groupLedgerID = LedgerID.createLedgerID();

        GroupID groupSundayRunnerID = GroupID.createGroupID(groupDenomination);

        //Arrange DTO in
        AddPersonToGroupDTO addPersonToGroupDTO = AddPersonToGroupDTOAssembler.createDataTransferObject_Primitives(personEmailAlexandre, groupDenomination);


//        Mock the behaviour of personRepository returning a boolean response true - personIDAlexandre exists
        Mockito.when(personRepository.exists(personIDAlexandre)).thenReturn(true);

//        Returning an Optional<Group> groupSunday
        Mockito.when(groupRepository.findById(groupSundayRunnerID)).thenReturn(Optional.empty());

        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () -> addPersonToGroupService.addPersonToGroup(addPersonToGroupDTO));

        //Assert
        assertEquals(thrown.getMessage(), AddPersonToGroupService.GROUP_DOES_NOT_EXIST);
    }


    // Person does not Exist - throw exception

    @Test
    @DisplayName("Test Service_US03- PersonID doesn't exist")
    void service_US03_false_PersonNotExist() {

        //      Instantiating an us003AddPersonToGroupService with personRepository and groupRepository as parameter
        addPersonToGroupService = new AddPersonToGroupService(personRepository, groupRepository);

        //Arrange persons

        String personEmailPedro = "pedro@gmail.com";

        PersonID personIDAlexandre = PersonID.createPersonID(personEmailPedro);

        String personEmailJoana = "joana@gmail.com";

        PersonID personIDJoana = PersonID.createPersonID(personEmailJoana);

        // Arrange group and groupID

        String groupDenomination = "Sunday";
        String groupDescription = "All members from Sunday Runners group";
        LocalDate groupDateOfCreation = LocalDate.of(2020, 05, 28);
        LedgerID groupLedgerID = LedgerID.createLedgerID();

        GroupID groupSundayRunnersID = GroupID.createGroupID(groupDenomination);

        Group groupSundayRunners = Group.createGroupAsPersonInCharge(groupDenomination, personIDJoana, groupDescription, groupDateOfCreation, groupLedgerID);

        //Arrange DTO in
        AddPersonToGroupDTO addPersonToGroupDTO = AddPersonToGroupDTOAssembler.createDataTransferObject_Primitives(personEmailPedro, groupDenomination);


        //        Mock the behaviour of personRepository, returning a boolean response true - personIDPedro exists
        Mockito.when(personRepository.exists(personIDAlexandre)).thenReturn(false);

        //        Returning an Optional<Group> groupSundayRunners
        Mockito.when(groupRepository.findById(groupSundayRunnersID)).thenReturn(Optional.of(groupSundayRunners));

        Throwable thrown = assertThrows(InvalidArgumentsBusinessException.class, () -> addPersonToGroupService.addPersonToGroup(addPersonToGroupDTO));

        //Assert
        assertEquals(thrown.getMessage(), AddPersonToGroupService.PERSON_DOES_NOT_EXIST);
    }


    // Person is already a group member

    @Test
    @DisplayName("Test Service_US03- Person is Already a Member")
    void service_US03_false_PersonAlreadyAMember() {

        //      Instantiating an us003AddPersonToGroupService with personRepository and groupRepository as parameter
        addPersonToGroupService = new AddPersonToGroupService(personRepository, groupRepository);

        // Arrange persons
        String personEmailAlexandre = "alexandre@gmail.com";

        PersonID personIDAlexandre = PersonID.createPersonID(personEmailAlexandre);

        String personEmailJoana = "joana@gmail.com";

        PersonID personIDJoana = PersonID.createPersonID(personEmailJoana);

        // Arrange group and groupID
        String groupDenomination = "Sunday Runners";
        String groupDescription = "All members from Sunday Runners group";
        LocalDate groupDateOfCreation = LocalDate.of(2020, 05, 28);
        LedgerID groupLedgerID = LedgerID.createLedgerID();

        GroupID groupSundayRunnerID = GroupID.createGroupID(groupDenomination);

        Group groupSundayRunner = Group.createGroupAsPersonInCharge(groupDenomination, personIDJoana, groupDescription, groupDateOfCreation, groupLedgerID);

        // Arrange DTO in
        AddPersonToGroupDTO addPersonToGroupDTO = AddPersonToGroupDTOAssembler.createDataTransferObject_Primitives(personEmailJoana, groupDenomination);


        //          Mock the behaviour of personRepository, returning a boolean response true - personIDAlexandre exists
        Mockito.when(personRepository.exists(personIDJoana)).thenReturn(true);

        //        Returning an Optional<Group> groupSundayRunner
        Mockito.when(groupRepository.findById(groupSundayRunnerID)).thenReturn(Optional.of(groupSundayRunner));

        //        Returning a boolean response false, person is already a member
        Mockito.when(groupRepository.addAndSaveMember(groupSundayRunner, personIDAlexandre)).thenReturn(false);

        // Act expected object
        Throwable thrown = assertThrows(InvalidArgumentsBusinessException.class, () -> addPersonToGroupService.addPersonToGroup(addPersonToGroupDTO));

        //Assert
        assertEquals(thrown.getMessage(), AddPersonToGroupService.PERSON_ALREADY_EXIST_IN_THE_GROUP);
    }

}