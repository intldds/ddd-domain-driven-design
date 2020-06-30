package com.finance.project.controllerLayer.integrationTests;

import com.finance.project.applicationLayer.applicationServices.groupServices.CreateGroupCategoryService;
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
import com.finance.project.dtos.dtos.NewGroupCategoryInfoDTO;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CreateGroupCategoryControllerTest extends AbstractTest {
    @Override
    @BeforeAll
    public void setUp() {
        super.setUp();
    }

    //--------------------------- Structure ----------------------//


    /*

    //SUCCESS

    @Test
    public void createGroupCategorySuccsess_Structure() throws Exception {

        //Arrange

        final String personEmail = "pedro@gmail.com";
        final String groupDenomination = "Fontes Family";
        final String groupDescription = "All members from Fontes family";
        final String categoryDenomination = "Allowance";

        final String uri = "/persons/" + personEmail + "/groups/" + groupDenomination + "/categories";

        //Input Json
        NewGroupCategoryInfoDTO newGroupCategoryInfoDTO = new NewGroupCategoryInfoDTO(categoryDenomination);
        String inputJson = super.mapToJson(newGroupCategoryInfoDTO);

        //Act

        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        //Assert

        final int status = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.CREATED.value(), status);

        final String content = mvcResult.getResponse().getContentAsString();
        JSONObject obj = new JSONObject(content);

        //Structure
        assertTrue(obj.has("denomination"));
        assertTrue(obj.has("description"));
        assertTrue(obj.has("dateOfCreation"));
        assertTrue(obj.has("_links"));
    }


    //PERSON_NOT_IN_CHARGE


    @Test
    public void createGroupCategoryPersonNotInCharge_Structure() throws Exception {

        //Arrange

        final String personEmail = "francis@gmail.com";
        final String groupDenomination = "Fontes Family";
        final String categoryDenomination = "Allowance";

        final String uri = "/persons/" + personEmail + "/groups/" + groupDenomination + "/categories";

        //Input Json
        NewGroupCategoryInfoDTO newGroupCategoryInfoDTO = new NewGroupCategoryInfoDTO(categoryDenomination);
        String inputJson = super.mapToJson(newGroupCategoryInfoDTO);

        //Act

        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        //Assert

        final int status = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.value(), status);

        final String content = mvcResult.getResponse().getContentAsString();
        JSONObject obj = new JSONObject(content);

        //Structure
        assertTrue(obj.has("status"));
        assertTrue(obj.has("message"));
        assertTrue(obj.has("errors"));
    }

    //CATEGORY_ALREADY_EXIST

    @Test
    public void createGroupCategoryCategoryAlreadyExists_Structure() throws Exception {

        //Arrange

        final String personEmail = "pedro@gmail.com";
        final String groupDenomination = "Fontes Family";
        final String categoryDenomination = "Salary";

        final String uri = "/persons/" + personEmail + "/groups/" + groupDenomination + "/categories";


        //Input Json
        NewGroupCategoryInfoDTO newGroupCategoryInfoDTO = new NewGroupCategoryInfoDTO(categoryDenomination);
        String inputJson = super.mapToJson(newGroupCategoryInfoDTO);

        //Act

        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        //Assert

        final int status = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.value(), status);

        final String content = mvcResult.getResponse().getContentAsString();
        JSONObject obj = new JSONObject(content);

        //Structure
        assertTrue(obj.has("status"));
        assertTrue(obj.has("message"));
        assertTrue(obj.has("errors"));
    }

    //GROUP_DOES_NOT_EXIST

    @Test
    public void createGroupCategoryGroupDoesNotExists_Structure() throws Exception {

        //Arrange

        final String personEmail = "manuel@gmail.com";
        final String groupDenomination = "Santos Family";
        final String categoryDenomination = "Allowance";

        final String uri = "/persons/" + personEmail + "/groups/" + groupDenomination + "/categories";

        //Input Json
        NewGroupCategoryInfoDTO newGroupCategoryInfoDTO = new NewGroupCategoryInfoDTO(categoryDenomination);
        String inputJson = super.mapToJson(newGroupCategoryInfoDTO);

        //Act

        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        //Assert

        final int status = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.NOT_FOUND.value(), status);

        final String content = mvcResult.getResponse().getContentAsString();
        JSONObject obj = new JSONObject(content);

        //Structure
        assertTrue(obj.has("status"));
        assertTrue(obj.has("message"));
        assertTrue(obj.has("errors"));
    }


    //--------------------------- CONTENT ----------------------//


    //SUCCESS

    @Test
    public void createGroupCategorySuccsess_Content() throws Exception {

        //Arrange

        final String personEmail = "pedro@gmail.com";
        final String groupDenomination = "Fontes Family";
        final String groupDescription = "All members from Fontes family";
        final String categoryDenomination = "School";

        final String uri = "/persons/" + personEmail + "/groups/" + groupDenomination + "/categories";

        //Input Json
        NewGroupCategoryInfoDTO newGroupCategoryInfoDTO = new NewGroupCategoryInfoDTO(categoryDenomination);
        String inputJson = super.mapToJson(newGroupCategoryInfoDTO);

        //Act

        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        //Assert

        final int status = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.CREATED.value(), status);

        final String content = mvcResult.getResponse().getContentAsString();
        JSONObject obj = new JSONObject(content);

        //Content
        assertEquals(groupDenomination, obj.get("denomination"));
        assertEquals(groupDescription, obj.get("description"));
        assertEquals(LocalDate.now().toString(), obj.get("dateOfCreation"));
    }


    //PERSON_NOT_IN_CHARGE


    @Test
    public void createGroupCategoryPersonNotInCharge_Content() throws Exception {

        //Arrange

        final String personEmail = "pedro@gmail.com";
        final String groupDenomination = "Fontes Family";
        final String categoryDenomination = "Allowance";

        final String uri = "/persons/" + personEmail + "/groups/" + groupDenomination + "/categories";

        //Input Json
        NewGroupCategoryInfoDTO newGroupCategoryInfoDTO = new NewGroupCategoryInfoDTO(categoryDenomination);
        String inputJson = super.mapToJson(newGroupCategoryInfoDTO);

        //Act

        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        //Assert

        final int status = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.value(), status);

        final String content = mvcResult.getResponse().getContentAsString();
        JSONObject obj = new JSONObject(content);

        //Content
        assertEquals("UNPROCESSABLE_ENTITY", obj.get("status"));
        Assertions.assertEquals(CreateGroupCategoryService.PERSON_NOT_IN_CHARGE, obj.get("message"));
        assertEquals("[\"Status Code: " + HttpStatus.UNPROCESSABLE_ENTITY.value() + ", Exception: InvalidArgumentsBusinessException\"]", obj.get("errors").toString());
    }

    //CATEGORY_ALREADY_EXIST

    @Test
    public void createGroupCategoryCategoryAlreadyExists_Content() throws Exception {

        //Arrange

        final String personEmail = "pedro@gmail.com";
        final String groupDenomination = "Fontes Family";
        final String categoryDenomination = "Salary";

        final String uri = "/persons/" + personEmail + "/groups/" + groupDenomination + "/categories";


        //Input Json
        NewGroupCategoryInfoDTO newGroupCategoryInfoDTO = new NewGroupCategoryInfoDTO(categoryDenomination);
        String inputJson = super.mapToJson(newGroupCategoryInfoDTO);

        //Act

        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        //Assert

        final int status = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.value(), status);

        final String content = mvcResult.getResponse().getContentAsString();
        JSONObject obj = new JSONObject(content);

        //Content
        assertEquals("UNPROCESSABLE_ENTITY", obj.get("status"));
        assertEquals(CreateGroupCategoryService.CATEGORY_ALREADY_EXIST, obj.get("message"));
        assertEquals("[\"Status Code: " + HttpStatus.UNPROCESSABLE_ENTITY.value() + ", Exception: InvalidArgumentsBusinessException\"]", obj.get("errors").toString());
    }

    //GROUP_DOES_NOT_EXIST

    @Test
    public void createGroupCategoryGroupDoesNotExists_Content() throws Exception {

        //Arrange

        final String personEmail = "manuel@gmail.com";
        final String groupDenomination = "Santos Family";
        final String categoryDenomination = "Allowance";

        final String uri = "/persons/" + personEmail + "/groups/" + groupDenomination + "/categories";

        //Input Json
        NewGroupCategoryInfoDTO newGroupCategoryInfoDTO = new NewGroupCategoryInfoDTO(categoryDenomination);
        String inputJson = super.mapToJson(newGroupCategoryInfoDTO);

        //Act

        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        //Assert

        final int status = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.NOT_FOUND.value(), status);

        final String content = mvcResult.getResponse().getContentAsString();
        JSONObject obj = new JSONObject(content);

        //Content
        assertEquals("NOT_FOUND", obj.get("status"));
        assertEquals(CreateGroupCategoryService.GROUP_DOES_NOT_EXIST, obj.get("message"));
        assertEquals("[\"Status Code: " + HttpStatus.NOT_FOUND.value() + ", Exception: NotFoundArgumentsBusinessException\"]", obj.get("errors").toString());
    }


     */
}