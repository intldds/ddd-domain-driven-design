package com.finance.project.controllerLayer.integrationTests;

import com.finance.project.applicationLayer.applicationServices.personServices.CreatePersonAccountService;
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
import com.finance.project.dtos.dtos.NewPersonAccountInfoDTO;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CreatePersonAccountControllerTest extends AbstractTest {

    @Override
    @BeforeAll
    public void setUp() {
        super.setUp();
    }

    //--------------------------- Structure ----------------------//

    // Success

    @Test
    public void createPersonAccount_Success_Structure() throws Exception {

        // Arrange
        final String personEmail = "pedro@gmail.com";
        final String accountDescription = "US Soccer League 2021";
        final String accountDenomination = "Soccer";

        final String uri = "/persons/" + personEmail + "/accounts";

        // Input JSON
        NewPersonAccountInfoDTO newPersonAccountInfoDTO = new NewPersonAccountInfoDTO(accountDescription, accountDenomination);
        String inputJson = super.mapToJson(newPersonAccountInfoDTO);

        // Act
        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        // Assert
        final int status = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.CREATED.value(), status);

        final String content = mvcResult.getResponse().getContentAsString();
        JSONObject obj = new JSONObject(content);

        // Structure
        assertTrue(obj.has("_links"));
        assertTrue(obj.has("name"));
        assertTrue(obj.has("email"));
        assertTrue(obj.has("birthdate"));
        assertTrue(obj.has("birthplace"));
        assertTrue(obj.has("father"));
        assertTrue(obj.has("mother"));
    }


    // Account already exists - using account denomination & description from "Bootstrapping"

    @Test
    public void createPersonAccount_AccountAlreadyExists_Structure() throws Exception {

        // Arrange
        final String personEmail = "pedro@gmail.com";
        final String accountDescription = "Personal bank account";
        final String accountDenomination = "Bank Account";

        final String uri = "/persons/" + personEmail + "/accounts";

        // Input JSON
        NewPersonAccountInfoDTO newPersonAccountInfoDTO = new NewPersonAccountInfoDTO(accountDescription, accountDenomination);
        String inputJson = super.mapToJson(newPersonAccountInfoDTO);

        // Act
        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        // Assert
        final int status = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.value(), status);

        final String content = mvcResult.getResponse().getContentAsString();
        JSONObject obj = new JSONObject(content);

        // Structure
        assertTrue(obj.has("status"));
        assertTrue(obj.has("message"));
        assertTrue(obj.has("errors"));
    }


    // Person does not exist

    @Test
    public void createPersonAccount_PersonDoesNotExists_Structure() throws Exception {

        // Arrange
        final String personEmail = "lemos@yahoo.com";
        final String accountDescription = "Lemos Family sports expenses";
        final String accountDenomination = "Tennis";

        final String uri = "/persons/" + personEmail + "/accounts";

        // Input JSON
        NewPersonAccountInfoDTO newPersonAccountInfoDTO = new NewPersonAccountInfoDTO(accountDescription, accountDenomination);
        String inputJson = super.mapToJson(newPersonAccountInfoDTO);

        // Act
        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        // Assert

        final int status = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.NOT_FOUND.value(), status);

        final String content = mvcResult.getResponse().getContentAsString();
        JSONObject obj = new JSONObject(content);

        // Structure
        assertTrue(obj.has("status"));
        assertTrue(obj.has("message"));
        assertTrue(obj.has("errors"));
    }


    //--------------------------- Content ----------------------//

    // Success

    @Test
    public void createPersonAccount_Success_Content() throws Exception {

        // Arrange Person
        final String personEmail = "pedro@gmail.com";
        final String personName = "Pedro Lemos";
        final String personBirthdate = LocalDate.of(1964, 01, 16).toString();
        final String personBirthplace = "Vila Nova de Gaia";
        final String IS_NOT_DEFINED = "Is Not Defined";
        final String personFather = IS_NOT_DEFINED;
        final String personMother = IS_NOT_DEFINED;

        // Arrange Account
        final String accountDescription = "Tickets for Roland Garros";
        final String accountDenomination = "Tennis";

        final String uri = "/persons/" + personEmail + "/accounts";

        // Input JSON
        NewPersonAccountInfoDTO newPersonAccountInfoDTO = new NewPersonAccountInfoDTO(accountDescription, accountDenomination);
        String inputJson = super.mapToJson(newPersonAccountInfoDTO);

        // Act
        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        // Assert
        final int status = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.CREATED.value(), status);

        final String content = mvcResult.getResponse().getContentAsString();
        JSONObject obj = new JSONObject(content);

        // Content
        assertEquals(personEmail, obj.get("email"));
        assertEquals(personName, obj.get("name"));
        assertEquals(personBirthdate, obj.get("birthdate"));
        assertEquals(personBirthplace, obj.get("birthplace"));
        assertEquals(personFather, obj.get("father"));
        assertEquals(personMother, obj.get("mother"));
    }


    // Account already exists - using account denomination & description from "Bootstrapping"

    @Test
    public void createPersonAccount_AccountAlreadyExists_Content() throws Exception {

        // Arrange
        final String personEmail = "pedro@gmail.com";
        final String accountDescription = "Personal bank account";
        final String accountDenomination = "Bank Account";

        final String uri = "/persons/" + personEmail + "/accounts";


        // Input JSON
        NewPersonAccountInfoDTO newPersonAccountInfoDTO = new NewPersonAccountInfoDTO(accountDescription, accountDenomination);
        String inputJson = super.mapToJson(newPersonAccountInfoDTO);

        // Act
        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        // Assert
        final int status = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.value(), status);

        final String content = mvcResult.getResponse().getContentAsString();
        JSONObject obj = new JSONObject(content);

        // Content
        assertEquals("UNPROCESSABLE_ENTITY", obj.get("status"));
        Assertions.assertEquals(CreatePersonAccountService.ACCOUNT_ALREADY_EXIST, obj.get("message"));
        assertEquals("[\"Status Code: " + HttpStatus.UNPROCESSABLE_ENTITY.value() + ", Exception: InvalidArgumentsBusinessException\"]", obj.get("errors").toString());
    }


    // Person does not exist

    @Test
    public void createPersonAccount_PersonDoesNotExists_Content() throws Exception {

        // Arrange
        final String personEmail = "lemos@yahoo.com";
        final String accountDescription = "Lemos Family sports expenses";
        final String accountDenomination = "Tennis";

        final String uri = "/persons/" + personEmail + "/accounts";

        // Input JSON
        NewPersonAccountInfoDTO newPersonAccountInfoDTO = new NewPersonAccountInfoDTO(accountDescription, accountDenomination);
        String inputJson = super.mapToJson(newPersonAccountInfoDTO);

        // Act
        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        // Assert

        final int status = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.NOT_FOUND.value(), status);

        final String content = mvcResult.getResponse().getContentAsString();
        JSONObject obj = new JSONObject(content);

        // Content
        assertEquals("NOT_FOUND", obj.get("status"));
        assertEquals(CreatePersonAccountService.PERSON_DOES_NOT_EXIST, obj.get("message"));
        assertEquals("[\"Status Code: " + HttpStatus.NOT_FOUND.value() + ", Exception: NotFoundArgumentsBusinessException\"]", obj.get("errors").toString());
    }


}
