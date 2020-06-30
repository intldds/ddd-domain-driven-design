package com.finance.project.controllerLayer.integrationTests;


import com.finance.project.applicationLayer.applicationServices.groupServices.CreateGroupAccountService;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.finance.project.dtos.dtos.NewGroupAccountInfoDTO;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CreateGroupAccountControllerTest extends AbstractTest {

    @Override
    @BeforeAll
    public void setUp() {
        super.setUp();
    }

    //STRUCTURE

    //SUCCESS

    /*

    @Test
    public void createGroupAccount_Success() throws Exception {

        //Arrange
        final String personEmail = "ilda@gmail.com";
        final String groupDenomination = "Fontes Family";
        final String groupDescription = "All members from Fontes family";
        final String accountDenomination = "LakersAccount";
        final String accountDescription = "Lakers Expenses";

        final String uri = "/persons/" + personEmail + "/groups/" + groupDenomination + "/accounts";

        //Input Json
        NewGroupAccountInfoDTO newGroupAccountInfoDTO = new NewGroupAccountInfoDTO(accountDescription, accountDenomination);
        String inputJson = super.mapToJson(newGroupAccountInfoDTO);

        //Act
        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        //Assert
        final int status = mvcResult.getResponse().getStatus();
//        assertEquals(HttpStatus.CREATED.value(), status);

        final String content = mvcResult.getResponse().getContentAsString();
        JSONObject object = new JSONObject(content);

        //Structure
        assertTrue(object.has("description"));
        assertTrue(object.has("denomination"));
        assertTrue(object.has("dateOfCreation"));
        assertTrue(object.has("_links"));
    }

    //PERSON_NOT_IN_CHARGE

    @Test
    public void createGroupAccount_PersonNotInCharge() throws Exception {

        //Arrange
        final String personEmail = "paulo@gmail.com";
        final String groupDenomination = "Fontes Family";
        final String accountDenomination = "LakersAccount";
        final String accountDescription = "Lakers Expenses";

        final String uri = "/persons/" + personEmail + "/groups/" + groupDenomination + "/accounts";

        //Input Json
        NewGroupAccountInfoDTO newGroupAccountInfoDTO = new NewGroupAccountInfoDTO(accountDescription, accountDenomination);
        String inputJson = super.mapToJson(newGroupAccountInfoDTO);

        //Act
        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        //Assert
        final int status = mvcResult.getResponse().getStatus();
//        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.value(), status);

        final String content = mvcResult.getResponse().getContentAsString();
        JSONObject object = new JSONObject(content);

        //Structure
        assertTrue(object.has("status"));
        assertTrue(object.has("message"));
        assertTrue(object.has("errors"));

    }

    //ACCOUNT_ALREADY_EXIST

    @Test
    public void createGroupAccount_AccountAlreadyExists() throws Exception {

        //Arrange
        final String personEmail = "ilda@gmail.com";
        final String groupDenomination = "Fontes Family";
        final String accountDenomination = "Company";
        final String accountDescription = "Company Expenses";

        final String uri = "/persons/" + personEmail + "/groups/" + groupDenomination + "/accounts";

        //Input Json
        NewGroupAccountInfoDTO newGroupAccountInfoDTO = new NewGroupAccountInfoDTO(accountDescription, accountDenomination);
        String inputJson = super.mapToJson(newGroupAccountInfoDTO);

        //Act
        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        //Assert
        final int status = mvcResult.getResponse().getStatus();
//        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.value(), status);

        final String content = mvcResult.getResponse().getContentAsString();
        JSONObject object = new JSONObject(content);

        //Structure
        assertTrue(object.has("status"));
        assertTrue(object.has("message"));
        assertTrue(object.has("errors"));
    }

    //GROUP_DOES_NOT_EXIST

    @Test
    public void createGroupAccount_GroupDoesNotExist() throws Exception {

        //Arrange
        final String personEmail = "ilda@gmail.com";
        final String groupDenomination = "Lakers Family";
        final String accountDenomination = "LakersAccount";
        final String accountDescription = "Lakers Expenses";

        final String uri = "/persons/" + personEmail + "/groups/" + groupDenomination + "/accounts";

        //Input Json
        NewGroupAccountInfoDTO newGroupAccountInfoDTO = new NewGroupAccountInfoDTO(accountDescription, accountDenomination);
        String inputJson = super.mapToJson(newGroupAccountInfoDTO);

        //Act
        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        //Assert
        final int status = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.NOT_FOUND.value(), status);

        final String content = mvcResult.getResponse().getContentAsString();
        JSONObject object = new JSONObject(content);

        //Structure
        assertTrue(object.has("status"));
        assertTrue(object.has("message"));
        assertTrue(object.has("errors"));
    }

    // CONTENT

    //SUCCESS

    @Test
    public void createGroupAccountSuccess_Content() throws Exception {

        //Arrange
        final String personEmail = "ilda@gmail.com";
        final String groupDenomination = "Fontes Family";
        final String groupDescription = "All members from Fontes family";
        final String accountDenomination = "LakersAccount2";
        final String accountDescription = "Lakers Expenses2";

        final String uri = "/persons/" + personEmail + "/groups/" + groupDenomination + "/accounts";

        //Input Json
        NewGroupAccountInfoDTO newGroupAccountInfoDTO = new NewGroupAccountInfoDTO(accountDescription, accountDenomination);
        String inputJson = super.mapToJson(newGroupAccountInfoDTO);

        //Act
        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        //Assert
        final int status = mvcResult.getResponse().getStatus();
//        assertEquals(HttpStatus.CREATED.value(), status);

        final String content = mvcResult.getResponse().getContentAsString();
        JSONObject object = new JSONObject(content);

        //Content
        assertEquals(groupDenomination, object.get("denomination"));
        assertEquals(groupDescription, object.get("description"));
        assertEquals(LocalDate.now().toString(), object.get("dateOfCreation"));
    }

    //PERSON_NOT_IN_CHARGE

    @Test
    public void createGroupAccountPersonNotInCharge_Content() throws Exception {

        //Arrange
        final String personEmail = "paulo@gmail.com";
        final String groupDenomination = "Fontes Family";
        final String accountDenomination = "LakersAccount";
        final String accountDescription = "Lakers Expenses";

        final String uri = "/persons/" + personEmail + "/groups/" + groupDenomination + "/accounts";

        //Input Json
        NewGroupAccountInfoDTO newGroupAccountInfoDTO = new NewGroupAccountInfoDTO(accountDescription, accountDenomination);
        String inputJson = super.mapToJson(newGroupAccountInfoDTO);

        //Act
        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        //Assert
        final int status = mvcResult.getResponse().getStatus();
//        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.value(), status);

        final String content = mvcResult.getResponse().getContentAsString();
        JSONObject object = new JSONObject(content);

        //Content
        assertEquals("UNPROCESSABLE_ENTITY", object.get("status"));
        Assertions.assertEquals(CreateGroupAccountService.PERSON_NOT_IN_CHARGE, object.get("message"));
        assertEquals("[\"Status Code: " + HttpStatus.UNPROCESSABLE_ENTITY.value() + ", Exception: InvalidArgumentsBusinessException\"]", object.get("errors").toString());
    }

    //ACCOUNT_ALREADY_EXIST

    @Test
    public void createGroupAccountAccountAlreadyExists_Content() throws Exception {

        //Arrange
        final String personEmail = "ilda@gmail.com";
        final String groupDenomination = "Fontes Family";
        final String accountDenomination = "Company";
        final String accountDescription = "Company Expenses";

        final String uri = "/persons/" + personEmail + "/groups/" + groupDenomination + "/accounts";

        //Input Json
        NewGroupAccountInfoDTO newGroupAccountInfoDTO = new NewGroupAccountInfoDTO(accountDescription, accountDenomination);
        String inputJson = super.mapToJson(newGroupAccountInfoDTO);

        //Act
        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        //Assert
        final int status = mvcResult.getResponse().getStatus();
//        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.value(), status);

        final String content = mvcResult.getResponse().getContentAsString();
        JSONObject object = new JSONObject(content);

        //Content
        assertEquals("UNPROCESSABLE_ENTITY", object.get("status"));
        assertEquals(CreateGroupAccountService.ACCOUNT_ALREADY_EXIST, object.get("message"));
        assertEquals("[\"Status Code: " + HttpStatus.UNPROCESSABLE_ENTITY.value() + ", Exception: InvalidArgumentsBusinessException\"]", object.get("errors").toString());
    }

    //GROUP_DOES_NOT_EXIST

    @Test
    public void createGroupAccountGroupDoesNotExist_Content() throws Exception {

        //Arrange
        final String personEmail = "ilda@gmail.com";
        final String groupDenomination = "Lakers Family";
        final String accountDenomination = "LakersAccount";
        final String accountDescription = "Lakers Expenses";

        final String uri = "/persons/" + personEmail + "/groups/" + groupDenomination + "/accounts";

        //Input Json
        NewGroupAccountInfoDTO newGroupAccountInfoDTO = new NewGroupAccountInfoDTO(accountDescription, accountDenomination);
        String inputJson = super.mapToJson(newGroupAccountInfoDTO);

        //Act
        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        //Assert
        final int status = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.NOT_FOUND.value(), status);

        final String content = mvcResult.getResponse().getContentAsString();
        JSONObject object = new JSONObject(content);

        //Content
        assertEquals("NOT_FOUND", object.get("status"));
        assertEquals(CreateGroupAccountService.GROUP_DOES_NOT_EXIST, object.get("message"));
        assertEquals("[\"Status Code: " + HttpStatus.NOT_FOUND.value() + ", Exception: NotFoundArgumentsBusinessException\"]", object.get("errors").toString());
    }

     */
}