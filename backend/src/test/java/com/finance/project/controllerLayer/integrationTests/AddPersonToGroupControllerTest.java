package com.finance.project.controllerLayer.integrationTests;


import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AddPersonToGroupControllerTest extends AbstractTest {

    //Sucess Case : Person is added - Confirm structure

//    @Override
//    @BeforeAll
//    public void setUp() {
//        super.setUp();
//    }
//
//    @Test
//    @DisplayName("Test postMapping | True | addPersonToGroup")
//    public void addPersonToGroup() throws Exception {
//
//        //Arrange
//
//
//        final String email = "elsa@gmail.com";
//        String denomination = "Sunday Runners";
//
//        final String uri = "/groups/" + denomination + "/members";
//
//        // Input Json
//
//        NewAddPersonToGroupInfoDTO newAddPersonToGroupInfoDTO = new NewAddPersonToGroupInfoDTO(email);
//        String inputJson = super.mapToJson(newAddPersonToGroupInfoDTO);
//
//
//        //Act
//
//        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
//
//
//        //Assert
//
//        final int status = mvcResult.getResponse().getStatus();
//        assertEquals(HttpStatus.CREATED.value(), status);
//
//        final String content = mvcResult.getResponse().getContentAsString();
//        JSONObject obj = new JSONObject(content);
//
//        assertTrue(obj.has("denomination"));
//        assertTrue(obj.has("description"));
//        assertTrue(obj.has("dateOfCreation"));
//        assertTrue(obj.has("_links"));
//
//    }
//
//    //SUCCESS - Content
//
//    @Test
//    @DisplayName("Test postMapping | True | addPersonToGroup")
//    public void addPersonToGroup_Content() throws Exception {
//
//        //Arrange
//
//        final String email = "alexandre@gmail.com";
//        String denomination = "Sunday Runners";
//        String description = "All members from Sunday Runners group";
//
//        final String uri = "/groups/" + denomination + "/members";
//
//        //Input Json
//        NewAddPersonToGroupInfoDTO newAddPersonToGroupInfoDTO = new NewAddPersonToGroupInfoDTO(email);
//        String inputJson = super.mapToJson(newAddPersonToGroupInfoDTO);
//        ;
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
//        final String content = mvcResult.getResponse().getContentAsString();
//        JSONObject obj = new JSONObject(content);
//
//
//        assertEquals(denomination, obj.get("denomination"));
//        assertEquals(description, obj.get("description"));
//        assertEquals(LocalDate.now().toString(), obj.get("dateOfCreation"));
//    }
//
//    // Person does not Exist
//
//    @Test
//    @DisplayName("Test postMapping | False | Person does not exist")
//    public void addPersonToGroup_2_PersonDoesNotExist() throws Exception {
//
//        final String email = "crispim@gmail.com";
//        String denomination = "Sunday Runners";
//        String description = "All members from Sunday Runners group";
//
//        final String uri = "/groups/" + denomination + "/members";
//
//        // Input Json
//
//        NewAddPersonToGroupInfoDTO newAddPersonToGroupInfoDTO = new NewAddPersonToGroupInfoDTO(email);
//        String inputJson = super.mapToJson(newAddPersonToGroupInfoDTO);
//
//        //Act
//
//        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
//
//        //Assert
//
//        final int status = mvcResult.getResponse().getStatus();
//        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.value(), status);
//
//        final String content = mvcResult.getResponse().getContentAsString();
//        JSONObject obj = new JSONObject(content);
//
//        assertTrue(obj.has("status"));
//        assertTrue(obj.has("message"));
//        assertTrue(obj.has("errors"));
//
//
//    }
//
//
//    // Person does not Exist - Content
//
//    @Test
//    @DisplayName("Test postMapping | False | Person does not exist")
//    public void addPersonToGroup_2_PersonDoesNotExist_Content() throws Exception {
//
//        final String email = "crispim@gmail.com";
//        String denomination = "Sunday Runners";
//
//        final String uri = "/groups/" + denomination + "/members";
//
//        // Input Json
//
//        NewAddPersonToGroupInfoDTO newAddPersonToGroupInfoDTO = new NewAddPersonToGroupInfoDTO(email);
//        String inputJson = super.mapToJson(newAddPersonToGroupInfoDTO);
//
//        //Act
//
//        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
//
//        //Assert
//
//        final int status = mvcResult.getResponse().getStatus();
//        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.value(), status);
//
//        final String content = mvcResult.getResponse().getContentAsString();
//        JSONObject obj = new JSONObject(content);
//
//        //Content
//        assertEquals("UNPROCESSABLE_ENTITY", obj.get("status"));
//        assertEquals(US003AddPersonToGroupService.PERSON_DOES_NOT_EXIST, obj.get("message"));
//        assertEquals("[\"Status Code: " + HttpStatus.UNPROCESSABLE_ENTITY.value() + ", Exception: InvalidArgumentsBusinessException\"]", obj.get("errors").toString());
//    }
//
//
//    //Group does not exist
//
//    @Test
//    @DisplayName("Test postMapping | False | Group does not exist")
//    public void addPersonToGroup_GroupDoesNotExist() throws Exception {
//
//        final String email = "ilda@gmail.com";
//        String denomination = "Sunday";
//
//        final String uri = "/groups/" + denomination + "/members";
//
//        // Input Json
//
//        NewAddPersonToGroupInfoDTO newAddPersonToGroupInfoDTO = new NewAddPersonToGroupInfoDTO(email);
//        String inputJson = super.mapToJson(newAddPersonToGroupInfoDTO);
//
//        //Act
//
//        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
//
//        //Assert
//
//        final int status = mvcResult.getResponse().getStatus();
//        assertEquals(HttpStatus.NOT_FOUND.value(), status);
//
//        final String content = mvcResult.getResponse().getContentAsString();
//        JSONObject obj = new JSONObject(content);
//
//        assertTrue(obj.has("status"));
//        assertTrue(obj.has("message"));
//        assertTrue(obj.has("errors"));
//    }
//
//
//    //Group does not exist - Content
//
//    @Test
//    @DisplayName("Test postMapping | False | Group does not exist")
//    public void addPersonToGroup_GroupDoesNotExist_Content() throws Exception {
//
//        final String email = "ilda@gmail.com";
//        String denomination = "Sunday";
//
//        final String uri = "/groups/" + denomination + "/members";
//
//        // Input Json
//
//        NewAddPersonToGroupInfoDTO newAddPersonToGroupInfoDTO = new NewAddPersonToGroupInfoDTO(email);
//        String inputJson = super.mapToJson(newAddPersonToGroupInfoDTO);
//
//        //Act
//
//        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
//
//        //Assert
//
//        final int status = mvcResult.getResponse().getStatus();
//        assertEquals(HttpStatus.NOT_FOUND.value(), status);
//
//        final String content = mvcResult.getResponse().getContentAsString();
//        JSONObject obj = new JSONObject(content);
//
//        //Content
//        assertEquals("NOT_FOUND", obj.get("status"));
//        assertEquals(US003AddPersonToGroupService.GROUP_DOES_NOT_EXIST, obj.get("message"));
//        assertEquals("[\"Status Code: " + HttpStatus.NOT_FOUND.value() + ", Exception: NotFoundArgumentsBusinessException\"]", obj.get("errors").toString());
//    }
//
//    //Person is already a member
//
//    @Test
//    @DisplayName("Test postMapping | False | Person already Exist")
//    public void addPersonToGroup_PersonAlreadyExist() throws Exception {
//
//        final String email = "paulo@gmail.com";
//        String denomination = "Sunday Runners";
//
//        final String uri = "/groups/" + denomination + "/members";
//
//        // Input Json
//
//        NewAddPersonToGroupInfoDTO newAddPersonToGroupInfoDTO = new NewAddPersonToGroupInfoDTO(email);
//        String inputJson = super.mapToJson(newAddPersonToGroupInfoDTO);
//
//
//        //Act
//
//        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
//
//        //Assert
//
//        final int status = mvcResult.getResponse().getStatus();
//        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.value(), status);
//
//        final String content = mvcResult.getResponse().getContentAsString();
//        JSONObject obj = new JSONObject(content);
//
//        assertTrue(obj.has("status"));
//        assertTrue(obj.has("message"));
//        assertTrue(obj.has("errors"));
//    }
//
//    //Person is already a member
//
//    @Test
//    @DisplayName("Test postMapping | False | Person already Exist")
//    public void addPersonToGroup_PersonAlreadyExist_Content() throws Exception {
//
//        final String email = "paulo@gmail.com";
//        String denomination = "Sunday Runners";
//
//        final String uri = "/groups/" + denomination + "/members";
//
//        // Input Json
//
//        NewAddPersonToGroupInfoDTO newAddPersonToGroupInfoDTO = new NewAddPersonToGroupInfoDTO(email);
//        String inputJson = super.mapToJson(newAddPersonToGroupInfoDTO);
//
//
//        //Act
//
//        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
//
//        //Assert
//
//        final int status = mvcResult.getResponse().getStatus();
//        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.value(), status);
//
//        final String content = mvcResult.getResponse().getContentAsString();
//        JSONObject obj = new JSONObject(content);
//
//        assertEquals("UNPROCESSABLE_ENTITY", obj.get("status"));
//        assertEquals(US003AddPersonToGroupService.PERSON_ALREADY_EXIST_IN_THE_GROUP, obj.get("message"));
//        assertEquals("[\"Status Code: " + HttpStatus.UNPROCESSABLE_ENTITY.value() + ", Exception: InvalidArgumentsBusinessException\"]", obj.get("errors").toString());
//    }
}
