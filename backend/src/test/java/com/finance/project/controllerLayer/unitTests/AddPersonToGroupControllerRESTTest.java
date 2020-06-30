package com.finance.project.controllerLayer.unitTests;

import com.finance.project.applicationLayer.applicationServices.groupServices.AddPersonToGroupService;
import com.finance.project.controllerLayer.controllersREST.groupControllers.AddPersonToGroupControllerREST;
import com.finance.project.controllerLayer.integrationTests.AbstractTest;
import com.finance.project.domainLayer.exceptions.InvalidArgumentsBusinessException;
import com.finance.project.domainLayer.exceptions.NotFoundArgumentsBusinessException;
import com.finance.project.dtos.dtosAssemblers.AddPersonToGroupDTOAssembler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import com.finance.project.dtos.dtos.AddPersonToGroupDTO;
import com.finance.project.dtos.dtos.NewAddPersonToGroupInfoDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


public class AddPersonToGroupControllerRESTTest extends AbstractTest {

    @Mock
    private AddPersonToGroupService serviceUS003;

    @Autowired
    private AddPersonToGroupControllerREST controllerRESTUS003;


/*    @Test
    @DisplayName("PostMapping - Success")
    public void whenPersonIsAddedTogroupMsgIsSucess() {

        //Arrange

        String email = "alexandre@gmail.com";
        String gDenomination = "Sunday Runners";
        String gDescription = "All members from Sunday Runners group";

        //Arrange expected result

        Denomination denomination = Denomination.createDenomination(gDenomination);
        Description description = Description.createDescription(gDescription);
        DateOfCreation dateOfCreation = DateOfCreation.createDateOfCreation(LocalDate.now());
        GroupDTO personIsAddedToGroup= GroupDTOAssembler.createDTOFromDomainObject(denomination, description, dateOfCreation);

        // Arrange info to DTO

        NewAddPersonToGroupInfoDTO newAddPersonToGroupInfoDTO = new NewAddPersonToGroupInfoDTO(email);

        // Arrange addPersonToGroupDTO

        AddPersonToGroupDTO addPersonToGroupDTO = AddPersonToGroupDTOAssembler.createDataTransferObject_Primitives(newAddPersonToGroupInfoDTO.getEmail(), gDenomination);

        // Mock the behaviour of the service's createCategoryAsPersonInCharge method,
        // so it does not depend on other parts (e.g. DB)

        Mockito.when(serviceUS003.addPersonToGroup(addPersonToGroupDTO)).thenReturn(personIsAddedToGroup);

        //Act Expected object

        ResponseEntity<Object> expectedResponse = new ResponseEntity<>(personIsAddedToGroup, HttpStatus.CREATED);

        // Act actual result

        ResponseEntity<Object> isPersonAddedToGroup = controllerRESTUS003.addPersonToGroupP(newAddPersonToGroupInfoDTO, gDenomination);

        //Assert
        assertEquals(expectedResponse, isPersonAddedToGroup);
    }*/


    @Test
    @DisplayName("PostMapping : Person does not exist")
    public void whenPersonIsNotAddedTogroupMsgISPERSON_DOES_NOT_EXIST() {

        //Arrange

        String email = "crispim@gmail.com";
        String gDenomination = "Sunday Runners";

        NewAddPersonToGroupInfoDTO newAddPersonToGroupInfoDTO = new NewAddPersonToGroupInfoDTO(email);

        AddPersonToGroupDTO addPersonToGroupDTO = AddPersonToGroupDTOAssembler.createDataTransferObject_Primitives(newAddPersonToGroupInfoDTO.getEmail(), gDenomination);

        // Mock the behaviour of the service's createCategoryAsPersonInCharge method,
        // so it does not depend on other parts (e.g. DB)
        Mockito.when(serviceUS003.addPersonToGroup(addPersonToGroupDTO)).thenThrow(new InvalidArgumentsBusinessException(AddPersonToGroupService.PERSON_DOES_NOT_EXIST));

        //Act

        Throwable thrown = assertThrows(InvalidArgumentsBusinessException.class, () -> controllerRESTUS003.addPersonToGroupP(newAddPersonToGroupInfoDTO, gDenomination));

        //Assert
        assertEquals(thrown.getMessage(), AddPersonToGroupService.PERSON_DOES_NOT_EXIST);
    }

    @Test
    @DisplayName("PostMapping : Group does not exist")
    public void whenPersonIsNotAddedTogroupMsgISGROUP_DOES_NOT_EXIST() {

        //Arrange

        String email = "alexandre@gmail.com";
        String gDenomination = "Sunday";

        NewAddPersonToGroupInfoDTO newAddPersonToGroupInfoDTO = new NewAddPersonToGroupInfoDTO(email);

        AddPersonToGroupDTO addPersonToGroupDTO = AddPersonToGroupDTOAssembler.createDataTransferObject_Primitives(newAddPersonToGroupInfoDTO.getEmail(), gDenomination);

        // Mock the behaviour of the service's createCategoryAsPersonInCharge method,
        // so it does not depend on other parts (e.g. DB)
        Mockito.when(serviceUS003.addPersonToGroup(addPersonToGroupDTO)).thenThrow(new NotFoundArgumentsBusinessException(AddPersonToGroupService.GROUP_DOES_NOT_EXIST));

        //Act

//        Throwable thrown = assertThrows(NotFoundArgumentsBusinessException.class, () -> controllerRESTUS003.addPersonToGroupP(newAddPersonToGroupInfoDTO, gDenomination));

        //Assert
  //      assertEquals(thrown.getMessage(), AddPersonToGroupService.GROUP_DOES_NOT_EXIST);
    }

//    @Test
//    @DisplayName("PostMapping : Person is already a member")
//    public void whenPersonIsNotAddedTogroupMsgISPERSON_ALREADY_EXIST_IN_THE_GROUP() {
//
//        //Arrange
//
//        String email = "paulo@gmail.com";
//        String gDenomination = "Sunday Runners";
//
//        NewAddPersonToGroupInfoDTO newAddPersonToGroupInfoDTO = new NewAddPersonToGroupInfoDTO(email);
//
//        AddPersonToGroupDTO addPersonToGroupDTO = AddPersonToGroupDTOAssembler.createDataTransferObject_Primitives(newAddPersonToGroupInfoDTO.getEmail(), gDenomination);
//
//        // Mock the behaviour of the service's createCategoryAsPersonInCharge method,
//        // so it does not depend on other parts (e.g. DB)
//        Mockito.when(serviceUS003.addPersonToGroup(addPersonToGroupDTO)).thenThrow(new InvalidArgumentsBusinessException(US003AddPersonToGroupService.PERSON_ALREADY_EXIST_IN_THE_GROUP));
//
//        //Act
//
//        Throwable thrown = assertThrows(InvalidArgumentsBusinessException.class, () -> controllerRESTUS003.addPersonToGroupP(newAddPersonToGroupInfoDTO, gDenomination));
//
//        //Assert
//        assertEquals(thrown.getMessage(), US003AddPersonToGroupService.PERSON_ALREADY_EXIST_IN_THE_GROUP);
//
//    }


}
