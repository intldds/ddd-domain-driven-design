package com.finance.project.controllerLayer.integrationTests;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.finance.project.dtos.dtos.NewCreateGroupInfoDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class CreateGroupControllerTest extends AbstractTest {

    //US002.1 Como utilizador, quero criar grupo, tornando-me administrador do grupo.

    @Override
    @BeforeAll
    public void setUp() {
        super.setUp();
    }


    //--------------------------- Structure ----------------------//


    //Happy Case: Group Created!

    @Test
    public void createGroupAsPersonInCharge_HappyCase_StructureEndToEnd() throws Exception {

        //Arrange

        final String uri = "/groups";

        final String personEmail = "pedro@gmail.com";
        final String groupDenomination = "Monday FootPlayers";
        final String groupDescription = "All members from Monday Football Players group";

        //Json DTO (input)
        NewCreateGroupInfoDTO groupInfoDTO = new NewCreateGroupInfoDTO(personEmail, groupDenomination, groupDescription);
        String inputJson = super.mapToJson(groupInfoDTO);

//          Check DB
        final MvcResult mvcResultFirsTest = mvc.perform(MockMvcRequestBuilders.get(uri+ groupDenomination )
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

//          Assert HttpStatus

        final int status = mvcResultFirsTest.getResponse().getStatus();

        assertEquals(HttpStatus.NOT_FOUND.value(), status);

//          Assert HttpStatus

        final String statusContent = mvcResultFirsTest.getResponse().getContentAsString();


        //Structure
        assertTrue(statusContent.isEmpty());

        //Act

        final MvcResult mvcResultSecondTest = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        //Assert

        final int statusCreated = mvcResultSecondTest.getResponse().getStatus();
        assertEquals(HttpStatus.CREATED.value(), statusCreated);


        final String content = mvcResultSecondTest.getResponse().getContentAsString();
        JSONObject objPost = new JSONObject(content);

        //Structure
        assertTrue(objPost.has("denomination"));
        assertTrue(objPost.has("description"));
        assertTrue(objPost.has("dateOfCreation"));
        assertTrue(objPost.has("_links"));


    }

//    @Test
//    public void createGroupAsPersonInCharge_HappyCase_Structure() throws Exception {
//
//        //Arrange
//
//        final String uri = "/groups";
//
//        final String personEmail = "rita@gmail.com";
//        final String groupDenomination = "Monday FootPlayers";
//        final String groupDescription = "All members from Monday Football Players group";
//
//        //Json DTO (input)
//        NewCreateGroupInfoDTO groupInfoDTO = new NewCreateGroupInfoDTO(personEmail, groupDenomination, groupDescription);
//        String inputJson = super.mapToJson(groupInfoDTO);
//
//
//        //Act
//
//        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
//
//        //Assert
//
//        final int status = mvcResult.getResponse().getStatus();
//        assertEquals(HttpStatus.CREATED.value(), status);
//
//
//        final String content = mvcResult.getResponse().getContentAsString();
//        JSONObject obj = new JSONObject(content);
//
//        //Structure
//        assertTrue(obj.has("denomination"));
//        assertTrue(obj.has("description"));
//        assertTrue(obj.has("dateOfCreation"));
//        assertTrue(obj.has("_links"));
//    }
////
////
////
////    //Sad Case: Group already exist
////
////    @Test
////    public void createGroupAsPersonInCharge_GroupAlreadyExist_Structure() throws Exception {
////
////        //Arrange
////
////        final String uri = "/groups";
////
////        final String personEmail = "joao@gmail.com";
////        final String groupDenomination = "Silva Family";
////        final String groupDescription = "All members from Silva family";
////
////        //Json DTO (input)
////        NewCreateGroupInfoDTO groupInfoDTO = new NewCreateGroupInfoDTO(personEmail, groupDenomination, groupDescription);
////        String inputJson = super.mapToJson(groupInfoDTO);
////
////        //Act
////
////        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
////
////        //Assert
////
////        final int status = mvcResult.getResponse().getStatus();
////        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.value(), status);
////
////        final String content = mvcResult.getResponse().getContentAsString();
////        JSONObject obj = new JSONObject(content);
////
////        //Structure
////        assertTrue(obj.has("status"));
////        assertTrue(obj.has("message"));
////        assertTrue(obj.has("errors"));
////    }
////
////    //Sad Case: Person does not exist
////
////    @Test
////    public void createGroupAsPersonInCharge_PersonDoesNotExist_Structure() throws Exception {
////
////        //Arrange
////
////        final String uri = "/groups";
////
////        final String personEmail = "telmo@gmail.com";
////        String groupDenomination = "Amaral Family";
////        String groupDescription = "All members from Amaral family";
////
////        //Json DTO (input)
////        NewCreateGroupInfoDTO groupInfoDTO = new NewCreateGroupInfoDTO(personEmail, groupDenomination, groupDescription);
////        String inputJson = super.mapToJson(groupInfoDTO);
////
////        //Act
////
////        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
////
////        //Assert
////
////        final int status = mvcResult.getResponse().getStatus();
////        assertEquals(HttpStatus.NOT_FOUND.value(), status);
////
////        final String content = mvcResult.getResponse().getContentAsString();
////        JSONObject obj = new JSONObject(content);
////
////        //Structure
////        assertTrue(obj.has("status"));
////        assertTrue(obj.has("message"));
////        assertTrue(obj.has("errors"));
////    }
////
////
////    @Test
////    public void getGroupByDenomination_HappyCase_Structure() throws Exception {
////
////        //Arrange
////
////        final String personEmail = "manuel@gmail.com";
////        final String groupDenomination = "Fontes Family";
////        final String groupDescription = "All members from Fontes family";
////
////        final String uri = "/groups/" + groupDenomination;
////
////
////        //Json DTO (input)
////        NewCreateGroupInfoDTO groupInfoDTO = new NewCreateGroupInfoDTO(personEmail, groupDenomination, groupDescription);
////        String inputJson = super.mapToJson(groupInfoDTO);
////
////        //Act
////
////        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
////
////        //Assert
////
////        final int status = mvcResult.getResponse().getStatus();
////        assertEquals(HttpStatus.OK.value(), status);
////
////        final String content = mvcResult.getResponse().getContentAsString();
////        JSONObject obj = new JSONObject(content);
////
////        //Structure
////        assertTrue(obj.has("denomination"));
////        assertTrue(obj.has("description"));
////        assertTrue(obj.has("dateOfCreation"));
////        assertTrue(obj.has("_links"));
////    }
////
////
////    @Test
////    public void getGroupByDenomination_GroupDoesNotExist_Structure() throws Exception {
////
////        //Arrange
////
////        final String personEmail = "joao@gmail.com";
////        final String groupDenomination = "Vale Family";
////        final String groupDescription = "All members from Vale family";
////
////        final String uri = "/groups/" + groupDenomination;
////
////
////        //Json DTO (input)
////        NewCreateGroupInfoDTO groupInfoDTO = new NewCreateGroupInfoDTO(personEmail, groupDenomination, groupDescription);
////        String inputJson = super.mapToJson(groupInfoDTO);
////
////        //Act
////
////        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
////
////        //Assert
////
////        final int status = mvcResult.getResponse().getStatus();
////        assertEquals(HttpStatus.NOT_FOUND.value(), status);
////
////        final String content = mvcResult.getResponse().getContentAsString();
////        JSONObject obj = new JSONObject(content);
////
////        //Structure
////        assertTrue(obj.has("status"));
////        assertTrue(obj.has("message"));
////        assertTrue(obj.has("errors"));
////    }
////
////    @Test
////    public void getGroupAdmins_HappyCase_Structure() throws Exception {
////
////        //Arrange
////
////        final String personEmail = "paulo@gmail.com";
////        final String groupDenomination = "Sunday Runners";
////        final String groupDescription = "All members from Sunday Runners group";
////
////        final String uri = "/groups/" + groupDenomination + "/admins";
////
////
////        //Json DTO (input)
////        NewCreateGroupInfoDTO groupInfoDTO = new NewCreateGroupInfoDTO(personEmail, groupDenomination, groupDescription);
////        String inputJson = super.mapToJson(groupInfoDTO);
////
////        //Act
////
////        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
////
////        //Assert
////
////        final int status = mvcResult.getResponse().getStatus();
////        assertEquals(HttpStatus.OK.value(), status);
////
////        final String content = mvcResult.getResponse().getContentAsString();
////        JSONObject obj = new JSONObject(content);
////
////        //Structure
////        assertTrue(obj.has("peopleInCharge"));
////    }
////
////
////    @Test
////    public void getGroupAdmins_GroupDoesNotExist_Structure() throws Exception {
////
////        //Arrange
////
////        final String personEmail = "paulo@gmail.com";
////        final String groupDenomination = "Friday Runners";
////        final String groupDescription = "All members from Friday Runners group";
////
////        final String uri = "/groups/" + groupDenomination + "/admins";
////
////
////        //Json DTO (input)
////        NewCreateGroupInfoDTO groupInfoDTO = new NewCreateGroupInfoDTO(personEmail, groupDenomination, groupDescription);
////        String inputJson = super.mapToJson(groupInfoDTO);
////
////        //Act
////
////        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
////
////        //Assert
////
////        final int status = mvcResult.getResponse().getStatus();
////        assertEquals(HttpStatus.NOT_FOUND.value(), status);
////
////        final String content = mvcResult.getResponse().getContentAsString();
////        JSONObject obj = new JSONObject(content);
////
////        //Structure
////        assertTrue(obj.has("status"));
////        assertTrue(obj.has("message"));
////        assertTrue(obj.has("errors"));
////    }
////
////    @Test
////    public void getGroupMembers_HappyCase_Structure() throws Exception {
////
////        //Arrange
////
////        final String personEmail = "ana@gmail.com";
////        final String groupDenomination = "Pereira Family";
////        final String groupDescription = "All members from Pereira family";
////
////        final String uri = "/groups/" + groupDenomination + "/members";
////
////
////        //Json DTO (input)
////        NewCreateGroupInfoDTO groupInfoDTO = new NewCreateGroupInfoDTO(personEmail, groupDenomination, groupDescription);
////        String inputJson = super.mapToJson(groupInfoDTO);
////
////        //Act
////
////        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
////
////        //Assert
////
////        final int status = mvcResult.getResponse().getStatus();
////        assertEquals(HttpStatus.OK.value(), status);
////
////        final String content = mvcResult.getResponse().getContentAsString();
////        JSONObject obj = new JSONObject(content);
////
////        //Structure
////        assertTrue(obj.has("members"));
////    }
////
////
////    @Test
////    public void getGroupMembers_GroupDoesNotExist_Structure() throws Exception {
////
////        //Arrange
////
////        final String personEmail = "paulo@gmail.com";
////        final String groupDenomination = "Friday Runners";
////        final String groupDescription = "All members from Friday Runners group";
////
////        final String uri = "/groups/" + groupDenomination + "/members";
////
////
////        //Json DTO (input)
////        NewCreateGroupInfoDTO groupInfoDTO = new NewCreateGroupInfoDTO(personEmail, groupDenomination, groupDescription);
////        String inputJson = super.mapToJson(groupInfoDTO);
////
////        //Act
////
////        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
////
////        //Assert
////
////        final int status = mvcResult.getResponse().getStatus();
////        assertEquals(HttpStatus.NOT_FOUND.value(), status);
////
////        final String content = mvcResult.getResponse().getContentAsString();
////        JSONObject obj = new JSONObject(content);
////
////        //Structure
////        assertTrue(obj.has("status"));
////        assertTrue(obj.has("message"));
////        assertTrue(obj.has("errors"));
////    }
////
////    @Test
////    public void getGroupAccounts_HappyCase_Structure() throws Exception {
////
////        //Arrange
////
////        final String personEmail = "paulo@gmail.com";
////        final String groupDenomination = "Sunday Runners";
////        final String groupDescription = "All members from Sunday Runners group";
////
////        final String uri = "/groups/" + groupDenomination + "/accounts";
////
////
////        //Json DTO (input)
////        NewCreateGroupInfoDTO groupInfoDTO = new NewCreateGroupInfoDTO(personEmail, groupDenomination, groupDescription);
////        String inputJson = super.mapToJson(groupInfoDTO);
////
////        //Act
////
////        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
////
////        //Assert
////
////        final int status = mvcResult.getResponse().getStatus();
////        assertEquals(HttpStatus.OK.value(), status);
////
////        final String content = mvcResult.getResponse().getContentAsString();
////        JSONObject obj = new JSONObject(content);
////
////        //Structure
////        assertTrue(obj.has("accounts"));
////    }
////
////
////    @Test
////    public void getGroupAccounts_GroupDoesNotExist_Structure() throws Exception {
////
////        //Arrange
////
////        final String personEmail = "paulo@gmail.com";
////        final String groupDenomination = "Friday Runners";
////        final String groupDescription = "All members from Friday Runners group";
////
////        final String uri = "/groups/" + groupDenomination + "/accounts";
////
////
////        //Json DTO (input)
////        NewCreateGroupInfoDTO groupInfoDTO = new NewCreateGroupInfoDTO(personEmail, groupDenomination, groupDescription);
////        String inputJson = super.mapToJson(groupInfoDTO);
////
////        //Act
////
////        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
////
////        //Assert
////
////        final int status = mvcResult.getResponse().getStatus();
////        assertEquals(HttpStatus.NOT_FOUND.value(), status);
////
////        final String content = mvcResult.getResponse().getContentAsString();
////        JSONObject obj = new JSONObject(content);
////
////        //Structure
////        assertTrue(obj.has("status"));
////        assertTrue(obj.has("message"));
////        assertTrue(obj.has("errors"));
////    }
////
////    @Test
////    public void getGroupCategories_HappyCase_Structure() throws Exception {
////
////        //Arrange
////
////        final String personEmail = "paulo@gmail.com";
////        final String groupDenomination = "Sunday Runners";
////        final String groupDescription = "All members from Sunday Runners group";
////
////        final String uri = "/groups/" + groupDenomination + "/categories";
////
////
////        //Json DTO (input)
////        NewCreateGroupInfoDTO groupInfoDTO = new NewCreateGroupInfoDTO(personEmail, groupDenomination, groupDescription);
////        String inputJson = super.mapToJson(groupInfoDTO);
////
////        //Act
////
////        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
////
////        //Assert
////
////        final int status = mvcResult.getResponse().getStatus();
////        assertEquals(HttpStatus.OK.value(), status);
////
////        final String content = mvcResult.getResponse().getContentAsString();
////        JSONObject obj = new JSONObject(content);
////
////        //Structure
////        assertTrue(obj.has("categories"));
////    }
////
////
////
////
////    @Test
////    public void getGroupCategories_GroupDoesNotExist_Structure() throws Exception {
////
////        //Arrange
////
////        final String personEmail = "paulo@gmail.com";
////        final String groupDenomination = "Friday Runners";
////        final String groupDescription = "All members from Friday Runners group";
////
////        final String uri = "/groups/" + groupDenomination + "/categories";
////
////
////        //Json DTO (input)
////        NewCreateGroupInfoDTO groupInfoDTO = new NewCreateGroupInfoDTO(personEmail, groupDenomination, groupDescription);
////        String inputJson = super.mapToJson(groupInfoDTO);
////
////        //Act
////
////        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
////
////        //Assert
////
////        final int status = mvcResult.getResponse().getStatus();
////        assertEquals(HttpStatus.NOT_FOUND.value(), status);
////
////        final String content = mvcResult.getResponse().getContentAsString();
////        JSONObject obj = new JSONObject(content);
////
////        //Structure
////        assertTrue(obj.has("status"));
////        assertTrue(obj.has("message"));
////        assertTrue(obj.has("errors"));
////    }
////
//////    @Test
//////    public void getGroupLedger_HappyCase_Structure() throws Exception {
//////
//////        //Arrange
//////
//////        final String personEmail = "paulo@gmail.com";
//////        final String groupDenomination = "Sunday Runners";
//////        final String groupDescription = "All members from Sunday Runners group";
//////
//////        final String uri = "/groups/" + groupDenomination + "/ledger";
//////
//////
//////        //Json DTO (input)
//////        NewCreateGroupInfoDTO groupInfoDTO = new NewCreateGroupInfoDTO(personEmail, groupDenomination, groupDescription);
//////        String inputJson = super.mapToJson(groupInfoDTO);
//////
//////        //Act
//////
//////        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
//////
//////        //Assert
//////
//////        final int status = mvcResult.getResponse().getStatus();
//////        assertEquals(HttpStatus.OK.value(), status);
//////
//////        final String content = mvcResult.getResponse().getContentAsString();
//////        JSONObject obj = new JSONObject(content);
//////
//////        //Structure
//////        assertTrue(obj.has("transactions"));
//////    }
////
////    @Test
////    public void getGroupLedger_GroupDoesNotExist_Structure() throws Exception {
////
////        //Arrange
////
////        final String personEmail = "paulo@gmail.com";
////        final String groupDenomination = "Friday Runners";
////        final String groupDescription = "All members from Friday Runners group";
////
////        final String uri = "/groups/" + groupDenomination + "/ledger";
////
////
////        //Json DTO (input)
////        NewCreateGroupInfoDTO groupInfoDTO = new NewCreateGroupInfoDTO(personEmail, groupDenomination, groupDescription);
////        String inputJson = super.mapToJson(groupInfoDTO);
////
////        //Act
////
////        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
////
////        //Assert
////
////        final int status = mvcResult.getResponse().getStatus();
////        assertEquals(HttpStatus.NOT_FOUND.value(), status);
////
////        final String content = mvcResult.getResponse().getContentAsString();
////        JSONObject obj = new JSONObject(content);
////
////        //Structure
////        assertTrue(obj.has("status"));
////        assertTrue(obj.has("message"));
////        assertTrue(obj.has("errors"));
////    }
////
////
////    //--------------------------- Content ----------------------//
////
////
////    //Happy Case: Group Created!
////
////    @Test
////    public void createGroupAsPersonInCharge_HappyCase_Content() throws Exception {
////
////        //Arrange
////
////        final String uri = "/groups";
////
////        final String personEmail = "rita@gmail.com";
////        final String groupDenomination = "Friday FootPlayers";
////        final String groupDescription = "All members from Friday Football Players group";
////
////        //Json DTO (input)
////        NewCreateGroupInfoDTO groupInfoDTO = new NewCreateGroupInfoDTO(personEmail, groupDenomination, groupDescription);
////        String inputJson = super.mapToJson(groupInfoDTO);
////
////        //Act
////
////        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
////
////        //Assert
////
////        final int status = mvcResult.getResponse().getStatus();
////        assertEquals(HttpStatus.CREATED.value(), status);
////
////
////        final String content = mvcResult.getResponse().getContentAsString();
////        JSONObject obj = new JSONObject(content);
////
////        //Content
////        assertEquals(groupDenomination, obj.get("denomination"));
////        assertEquals(groupDescription, obj.get("description"));
////        assertEquals(LocalDate.now().toString(), obj.get("dateOfCreation"));
////    }
////
////
////    //Sad Case: Group already exist
////
////    @Test
////    public void createGroupAsPersonInCharge_GroupAlreadyExist_Content() throws Exception {
////
////        //Arrange
////
////        final String uri = "/groups";
////
////        final String personEmail = "joao@gmail.com";
////        final String groupDenomination = "Silva Family";
////        final String groupDescription = "All members from Silva family";
////
////        //Json DTO (input)
////        NewCreateGroupInfoDTO groupInfoDTO = new NewCreateGroupInfoDTO(personEmail, groupDenomination, groupDescription);
////        String inputJson = super.mapToJson(groupInfoDTO);
////
////        //Act
////
////        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
////
////        //Assert
////
////        final int status = mvcResult.getResponse().getStatus();
////        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.value(), status);
////
////        final String content = mvcResult.getResponse().getContentAsString();
////        JSONObject obj = new JSONObject(content);
////
////        //Content
////        assertEquals("UNPROCESSABLE_ENTITY", obj.get("status"));
////        assertEquals(US002_1CreateGroupService.GROUP_ALREADY_EXISTS, obj.get("message"));
////        assertEquals("[\"Status Code: " + HttpStatus.UNPROCESSABLE_ENTITY.value() + ", Exception: InvalidArgumentsBusinessException\"]", obj.get("errors").toString());
////    }
////
////    //Sad Case: Person does not exist
////
////    @Test
////    public void createGroupAsPersonInCharge_PersonDoesNotExist_Content() throws Exception {
////
////        //Arrange
////
////        final String uri = "/groups";
////
////        final String personEmail = "telmo@gmail.com";
////        String groupDenomination = "Amaral Family";
////        String groupDescription = "All members from Amaral family";
////
////        //Json DTO (input)
////        NewCreateGroupInfoDTO groupInfoDTO = new NewCreateGroupInfoDTO(personEmail, groupDenomination, groupDescription);
////        String inputJson = super.mapToJson(groupInfoDTO);
////
////        //Act
////
////        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
////
////        //Assert
////
////        final int status = mvcResult.getResponse().getStatus();
////        assertEquals(HttpStatus.NOT_FOUND.value(), status);
////
////        final String content = mvcResult.getResponse().getContentAsString();
////        JSONObject obj = new JSONObject(content);
////
////        //Content
////        assertEquals("NOT_FOUND", obj.get("status"));
////        assertEquals(US002_1CreateGroupService.PERSON_DOES_NOT_EXIST, obj.get("message"));
////        assertEquals("[\"Status Code: " + HttpStatus.NOT_FOUND.value() + ", Exception: NotFoundArgumentsBusinessException\"]", obj.get("errors").toString());
////    }
////
////    @Test
////    public void getGroupByDenomination_HappyCase_Content() throws Exception {
////
////        //Arrange
////
////        final String personEmail = "manuel@gmail.com";
////        final String groupDenomination = "Fontes Family";
////        final String groupDescription = "All members from Fontes family";
////
////        final String uri = "/groups/" + groupDenomination;
////
////
////        //Json DTO (input)
////        NewCreateGroupInfoDTO groupInfoDTO = new NewCreateGroupInfoDTO(personEmail, groupDenomination, groupDescription);
////        String inputJson = super.mapToJson(groupInfoDTO);
////
////        //Act
////
////        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
////
////        //Assert
////
////        final int status = mvcResult.getResponse().getStatus();
////        assertEquals(HttpStatus.OK.value(), status);
////
////        final String content = mvcResult.getResponse().getContentAsString();
////        JSONObject obj = new JSONObject(content);
////
////        //Content
////        assertEquals(groupDenomination, obj.get("denomination"));
////        assertEquals(groupDescription, obj.get("description"));
////        assertEquals(LocalDate.now().toString(), obj.get("dateOfCreation"));
////    }
////
////    @Test
////    public void getGroupByDenomination_GroupDoesNotExist_Content() throws Exception {
////
////        //Arrange
////
////        final String personEmail = "joao@gmail.com";
////        final String groupDenomination = "Vale Family";
////        final String groupDescription = "All members from Vale family";
////
////        final String uri = "/groups/" + groupDenomination;
////
////
////        //Json DTO (input)
////        NewCreateGroupInfoDTO groupInfoDTO = new NewCreateGroupInfoDTO(personEmail, groupDenomination, groupDescription);
////        String inputJson = super.mapToJson(groupInfoDTO);
////
////        //Act
////
////        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
////
////        //Assert
////
////        final int status = mvcResult.getResponse().getStatus();
////        assertEquals(HttpStatus.NOT_FOUND.value(), status);
////
////        final String content = mvcResult.getResponse().getContentAsString();
////        JSONObject obj = new JSONObject(content);
////
////        //Content
////        assertEquals("NOT_FOUND", obj.get("status"));
////        assertEquals(US002_1CreateGroupService.GROUP_DOES_NOT_EXISTS, obj.get("message"));
////        assertEquals("[\"Status Code: " + HttpStatus.NOT_FOUND.value() + ", Exception: NotFoundArgumentsBusinessException\"]", obj.get("errors").toString());
////    }
////
////
////    @Test
////    public void getGroupAdmins_GroupDoesNotExist_Content() throws Exception {
////
////        //Arrange
////
////        final String personEmail = "paulo@gmail.com";
////        final String groupDenomination = "Friday Runners";
////        final String groupDescription = "All members from Friday Runners group";
////
////        final String uri = "/groups/" + groupDenomination + "/admins";
////
////
////        //Json DTO (input)
////        NewCreateGroupInfoDTO groupInfoDTO = new NewCreateGroupInfoDTO(personEmail, groupDenomination, groupDescription);
////        String inputJson = super.mapToJson(groupInfoDTO);
////
////        //Act
////
////        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
////
////        //Assert
////
////        final int status = mvcResult.getResponse().getStatus();
////        assertEquals(HttpStatus.NOT_FOUND.value(), status);
////
////        final String content = mvcResult.getResponse().getContentAsString();
////        JSONObject obj = new JSONObject(content);
////
////        //Content
////        assertEquals("NOT_FOUND", obj.get("status"));
////        assertEquals(US002_1CreateGroupService.GROUP_DOES_NOT_EXISTS, obj.get("message"));
////        assertEquals("[\"Status Code: " + HttpStatus.NOT_FOUND.value() + ", Exception: NotFoundArgumentsBusinessException\"]", obj.get("errors").toString());
////    }
////
////
////    @Test
////    public void getGroupMembers_GroupDoesNotExist_Content() throws Exception {
////
////        //Arrange
////
////        final String personEmail = "paulo@gmail.com";
////        final String groupDenomination = "Friday Runners";
////        final String groupDescription = "All members from Friday Runners group";
////
////        final String uri = "/groups/" + groupDenomination + "/members";
////
////
////        //Json DTO (input)
////        NewCreateGroupInfoDTO groupInfoDTO = new NewCreateGroupInfoDTO(personEmail, groupDenomination, groupDescription);
////        String inputJson = super.mapToJson(groupInfoDTO);
////
////        //Act
////
////        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
////
////        //Assert
////
////        final int status = mvcResult.getResponse().getStatus();
////        assertEquals(HttpStatus.NOT_FOUND.value(), status);
////
////        final String content = mvcResult.getResponse().getContentAsString();
////        JSONObject obj = new JSONObject(content);
////
////        //Content
////        assertEquals("NOT_FOUND", obj.get("status"));
////        assertEquals(US002_1CreateGroupService.GROUP_DOES_NOT_EXISTS, obj.get("message"));
////        assertEquals("[\"Status Code: " + HttpStatus.NOT_FOUND.value() + ", Exception: NotFoundArgumentsBusinessException\"]", obj.get("errors").toString());
////    }
////
////
////    @Test
////    public void getGroupAccounts_GroupDoesNotExist_Content() throws Exception {
////
////        //Arrange
////
////        final String personEmail = "paulo@gmail.com";
////        final String groupDenomination = "Friday Runners";
////        final String groupDescription = "All members from Friday Runners group";
////
////        final String uri = "/groups/" + groupDenomination + "/accounts";
////
////
////        //Json DTO (input)
////        NewCreateGroupInfoDTO groupInfoDTO = new NewCreateGroupInfoDTO(personEmail, groupDenomination, groupDescription);
////        String inputJson = super.mapToJson(groupInfoDTO);
////
////        //Act
////
////        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
////
////        //Assert
////
////        final int status = mvcResult.getResponse().getStatus();
////        assertEquals(HttpStatus.NOT_FOUND.value(), status);
////
////        final String content = mvcResult.getResponse().getContentAsString();
////        JSONObject obj = new JSONObject(content);
////
////        //Content
////        assertEquals("NOT_FOUND", obj.get("status"));
////        assertEquals(US002_1CreateGroupService.GROUP_DOES_NOT_EXISTS, obj.get("message"));
////        assertEquals("[\"Status Code: " + HttpStatus.NOT_FOUND.value() + ", Exception: NotFoundArgumentsBusinessException\"]", obj.get("errors").toString());
////    }
////
////
////    @Test
////    public void getGroupCategories_GroupDoesNotExist_Content() throws Exception {
////
////        //Arrange
////
////        final String personEmail = "paulo@gmail.com";
////        final String groupDenomination = "Friday Runners";
////        final String groupDescription = "All members from Friday Runners group";
////
////        final String uri = "/groups/" + groupDenomination + "/categories";
////
////
////        //Json DTO (input)
////        NewCreateGroupInfoDTO groupInfoDTO = new NewCreateGroupInfoDTO(personEmail, groupDenomination, groupDescription);
////        String inputJson = super.mapToJson(groupInfoDTO);
////
////        //Act
////
////        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
////
////        //Assert
////
////        final int status = mvcResult.getResponse().getStatus();
////        assertEquals(HttpStatus.NOT_FOUND.value(), status);
////
////        final String content = mvcResult.getResponse().getContentAsString();
////        JSONObject obj = new JSONObject(content);
////
////        //Content
////        assertEquals("NOT_FOUND", obj.get("status"));
////        assertEquals(US002_1CreateGroupService.GROUP_DOES_NOT_EXISTS, obj.get("message"));
////        assertEquals("[\"Status Code: " + HttpStatus.NOT_FOUND.value() + ", Exception: NotFoundArgumentsBusinessException\"]", obj.get("errors").toString());
////    }
////
////
////    @Test
////    public void getGroupLedger_GroupDoesNotExist_Content() throws Exception {
////
////        //Arrange
////
////        final String personEmail = "paulo@gmail.com";
////        final String groupDenomination = "Friday Runners";
////        final String groupDescription = "All members from Friday Runners group";
////
////        final String uri = "/groups/" + groupDenomination + "/ledger";
////
////
////        //Json DTO (input)
////        NewCreateGroupInfoDTO groupInfoDTO = new NewCreateGroupInfoDTO(personEmail, groupDenomination, groupDescription);
////        String inputJson = super.mapToJson(groupInfoDTO);
////
////        //Act
////
////        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
////
////        //Assert
////
////        final int status = mvcResult.getResponse().getStatus();
////        assertEquals(HttpStatus.NOT_FOUND.value(), status);
////
////        final String content = mvcResult.getResponse().getContentAsString();
////        JSONObject obj = new JSONObject(content);
////
////        //Content
////        assertEquals("NOT_FOUND", obj.get("status"));
////        assertEquals(US002_1CreateGroupService.GROUP_DOES_NOT_EXISTS, obj.get("message"));
////        assertEquals("[\"Status Code: " + HttpStatus.NOT_FOUND.value() + ", Exception: NotFoundArgumentsBusinessException\"]", obj.get("errors").toString());
//    }
}
