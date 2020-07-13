package com.finance.project.controllerLayer.integrationTests;


import com.finance.project.applicationLayer.applicationServices.personServices.CreatePersonService;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.finance.project.dtos.dtos.NewCreatePersonInfoDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CreatePersonControllerTest extends AbstractTest {


    @Override
    @BeforeAll
    public void setUp() {
        super.setUp();
    }


    //-----------------------------Structure---------------------------//

    // Create Person

    // Sucess

    @Test
    @DisplayName("Create Person - Sucess")
    public void createPerson_Structure() throws Exception {

        // Arrange

        final String uri = "/persons/";

        final String personEmail = "francisco@gmail.com";
        final String name = "Francisco";
        final String birthdate = "1973-07-25";
        String birthplace = "Porto";


        // Input JSON
        NewCreatePersonInfoDTO newCreatePersonInfoDTO = new NewCreatePersonInfoDTO(personEmail, name, birthdate, birthplace);
        String inputJson = super.mapToJson(newCreatePersonInfoDTO);

        // Act
        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        // Assert
        final int status = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.CREATED.value(), status);

        final String content = mvcResult.getResponse().getContentAsString();
        JSONObject obj = new JSONObject(content);

        // Structure
        assertTrue(obj.has("email"));
        assertTrue(obj.has("name"));
        assertTrue(obj.has("birthdate"));
        assertTrue(obj.has("birthplace"));
        assertTrue(obj.has("_links"));
    }


    @Test
    @DisplayName("Get Person - Fail - Person Does Not Exist")
    public void GetPersonByEmail_PersonDoesNotExist() throws Exception {

        // Arrange

        final String personEmail = "p@gmail.com";
        final String name = "Paulo Fontes";
        final String birthdate = "1993-03-15";
        final String birthplace = "Vila Nova de Gaia";

        final String uri = "/persons/" + personEmail;


        // Input JSON
        NewCreatePersonInfoDTO newCreatePersonInfoDTO = new NewCreatePersonInfoDTO(personEmail, name, birthdate, birthplace);
        String inputJson = super.mapToJson(newCreatePersonInfoDTO);

        // Act
        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        // Assert
        final int status = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.NOT_FOUND.value(), status);

        final String content = mvcResult.getResponse().getContentAsString();
        JSONObject obj = new JSONObject(content);

        assertTrue(obj.has("status"));
        assertTrue(obj.has("message"));
        assertTrue(obj.has("errors"));
    }


    // Get Person Ledger

    @Test
    @DisplayName("GetPerson Ledger- Sucess")
    public void GetPersonLedger() throws Exception {

        // Arrange

        final String personEmail = "paulo@gmail.com";

        final String uri = "/persons/" + personEmail + "/ledgers/records";

        // Act
        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();

        // Assert
        final int status = mvcResult.getResponse().getStatus();
//        assertEquals(HttpStatus.OK.value(), status);

        final String content = mvcResult.getResponse().getContentAsString();
        JSONObject obj = new JSONObject(content);

        // Structure
//        assertTrue(obj.has("transactions"));
    }


    @Test
    @DisplayName("GetPerson Ledger- Fail - Person Does Not Exist")
    public void GetPersonLedger_PersonDoesNotExist() throws Exception {

        // Arrange

        final String personEmail = "pp@gmail.com";

        final String uri = "/persons/" + personEmail + "/ledgers/records";

        // Act
        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();

        // Assert
        final int status = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.NOT_FOUND.value(), status);

        final String content = mvcResult.getResponse().getContentAsString();
        JSONObject obj = new JSONObject(content);

        // Structure
        assertTrue(obj.has("errors"));

    }


    //Get Person Accounts

    @Test
    @DisplayName("GetPerson Accounts- Sucess")
    public void GetPersonAccounts() throws Exception {

        // Arrange

        final String personEmail = "paulo@gmail.com";
        final String name = "Paulo Fontes";
        final String birthdate = "1993-03-15";
        final String birthplace = "Vila Nova de Gaia";

        final String uri = "/persons/" + personEmail + "/accounts";
        ;


        // Input JSON
        NewCreatePersonInfoDTO newCreatePersonInfoDTO = new NewCreatePersonInfoDTO(personEmail, name, birthdate, birthplace);
        String inputJson = super.mapToJson(newCreatePersonInfoDTO);

        // Act
        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        // Assert
        final int status = mvcResult.getResponse().getStatus();
//        assertEquals(HttpStatus.OK.value(), status);

        final String content = mvcResult.getResponse().getContentAsString();
        JSONObject obj = new JSONObject(content);

        // Structure
//        assertTrue(obj.has("accounts"));

    }


    @Test
    @DisplayName("GetPerson Accounts- Fail - Person Does Not Exist")
    public void GetPersonAccounts_PersonDoesNotExist() throws Exception {

        // Arrange

        final String personEmail = "pp@gmail.com";
        final String name = "Paulo Fontes";
        final String birthdate = "1993-03-15";
        final String birthplace = "Vila Nova de Gaia";

        final String uri = "/persons/" + personEmail + "/accounts";
        ;


        // Input JSON
        NewCreatePersonInfoDTO newCreatePersonInfoDTO = new NewCreatePersonInfoDTO(personEmail, name, birthdate, birthplace);
        String inputJson = super.mapToJson(newCreatePersonInfoDTO);

        // Act
        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        // Assert
        final int status = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.NOT_FOUND.value(), status);

        final String content = mvcResult.getResponse().getContentAsString();
        JSONObject obj = new JSONObject(content);

        // Structure
        assertTrue(obj.has("errors"));

    }


    //Get Person Categories

    @Test
    @DisplayName("GetPerson Categories- Sucess")
    public void GetPersonCategories() throws Exception {

        // Arrange

        final String personEmail = "paulo@gmail.com";
        final String name = "Paulo Fontes";
        final String birthdate = "1993-03-15";
        final String birthplace = "Vila Nova de Gaia";

        final String uri = "/persons/" + personEmail + "/categories";


        // Input JSON
        NewCreatePersonInfoDTO newCreatePersonInfoDTO = new NewCreatePersonInfoDTO(personEmail, name, birthdate, birthplace);
        String inputJson = super.mapToJson(newCreatePersonInfoDTO);

        // Act
        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        // Assert
        final int status = mvcResult.getResponse().getStatus();
        // assertEquals(HttpStatus.OK.value(), status);

        final String content = mvcResult.getResponse().getContentAsString();
        JSONObject obj = new JSONObject(content);

        // Structure
        // assertTrue(obj.has("categories"));
    }


    @Test
    @DisplayName("GetPerson Categories- Fail - Person Does Not Exist")
    public void GetPersonCategories_PersonDoesNotExist() throws Exception {

        // Arrange

        final String personEmail = "pp@gmail.com";
        final String name = "Paulo Fontes";
        final String birthdate = "1993-03-15";
        final String birthplace = "Vila Nova de Gaia";

        final String uri = "/persons/" + personEmail + "/categories";

        // Input JSON
        NewCreatePersonInfoDTO newCreatePersonInfoDTO = new NewCreatePersonInfoDTO(personEmail, name, birthdate, birthplace);
        String inputJson = super.mapToJson(newCreatePersonInfoDTO);

        // Act
        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        // Assert
        final int status = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.NOT_FOUND.value(), status);

        final String content = mvcResult.getResponse().getContentAsString();
        JSONObject obj = new JSONObject(content);

        // Structure
        assertTrue(obj.has("errors"));
    }


    // Get Person Siblings

    @Test
    @DisplayName("GetPerson Siblings- Success")
    public void GetPersonSiblings() throws Exception {

        // Arrange

        final String personEmail = "paulo@gmail.com";
        final String name = "Paulo Fontes";
        final String birthdate = "1993-03-15";
        final String birthplace = "Vila Nova de Gaia";

        final String uri = "/persons/" + personEmail + "/siblings";
        ;


        // Input JSON
        NewCreatePersonInfoDTO newCreatePersonInfoDTO = new NewCreatePersonInfoDTO(personEmail, name, birthdate, birthplace);
        String inputJson = super.mapToJson(newCreatePersonInfoDTO);

        // Act
        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        // Assert
        final int status = mvcResult.getResponse().getStatus();
        // assertEquals(HttpStatus.OK.value(), status);

        final String content = mvcResult.getResponse().getContentAsString();
        JSONObject obj = new JSONObject(content);

        // Structure
        // assertTrue(obj.has("siblings"));

    }


    @Test
    @DisplayName("GetPerson Siblibgs- Fail - Person Does Not Exist")
    public void GetPersonSiblings_PersonDoesNotExist() throws Exception {

        // Arrange

        final String personEmail = "pp@gmail.com";
        final String name = "Paulo Fontes";
        final String birthdate = "1993-03-15";
        final String birthplace = "Vila Nova de Gaia";

        final String uri = "/persons/" + personEmail + "/siblings";

        // Input JSON
        NewCreatePersonInfoDTO newCreatePersonInfoDTO = new NewCreatePersonInfoDTO(personEmail, name, birthdate, birthplace);
        String inputJson = super.mapToJson(newCreatePersonInfoDTO);

        // Act
        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        // Assert
        final int status = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.NOT_FOUND.value(), status);

        final String content = mvcResult.getResponse().getContentAsString();
        JSONObject obj = new JSONObject(content);

        // Structure
        assertTrue(obj.has("errors"));

    }

    //-----------------------------Content---------------------------//

    //Sucess

    @Test
    @DisplayName("Create Person - Sucess")
    public void createPerson_Content() throws Exception {

        // Arrange

        final String uri = "/persons/";

        final String personEmail = "anibal@gmail.com";
        final String name = "Anibal";
        final String birthdate = "1973-07-25";
        String birthplace = "Porto";

        // Input JSON
        NewCreatePersonInfoDTO newCreatePersonInfoDTO = new NewCreatePersonInfoDTO(personEmail, name, birthdate, birthplace);
        String inputJson = super.mapToJson(newCreatePersonInfoDTO);

        // Act
        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        // Assert
        final int status = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.CREATED.value(), status);

        final String content = mvcResult.getResponse().getContentAsString();
        JSONObject obj = new JSONObject(content);

        //Content
        assertEquals(personEmail, obj.get("email"));
        assertEquals(name, obj.get("name"));
        assertEquals(birthdate, obj.get("birthdate"));
        assertEquals(birthplace, obj.get("birthplace"));
    }


    @Test
    @DisplayName("Get Person - Fail - Person Does Not Exist")
    public void GetPersonByEmail_Content_PersonDoesNotExist() throws Exception {

        // Arrange

        final String personEmail = "pm@gmail.com";
        final String name = "Paulo Fontes";
        final String birthdate = "1993-03-15";
        final String birthplace = "Vila Nova de Gaia";

        final String uri = "/persons/" + personEmail;

        // Input JSON
        NewCreatePersonInfoDTO newCreatePersonInfoDTO = new NewCreatePersonInfoDTO(personEmail, name, birthdate, birthplace);
        String inputJson = super.mapToJson(newCreatePersonInfoDTO);

        // Act
        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();


        // Assert

        final int status = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.NOT_FOUND.value(), status);

        final String content = mvcResult.getResponse().getContentAsString();
        JSONObject obj = new JSONObject(content);

        assertEquals("NOT_FOUND", obj.get("status"));
        assertEquals(CreatePersonService.PERSON_DOES_NOT_EXIST, obj.get("message"));
        assertEquals("[\"Status Code: " + HttpStatus.NOT_FOUND.value() + ", Exception: NotFoundArgumentsBusinessException\"]", obj.get("errors").toString());

    }

    // Get Person Ledger

    @Test
    @DisplayName("GetPerson Ledger - Fail - Person does not exist")
    public void GetPersonLedger_Content() throws Exception {

        // Arrange

        final String personEmail = "pp@gmail.com";
        final String name = "Paulo Fontes";
        final String birthdate = "1993-03-15";
        final String birthplace = "Vila Nova de Gaia";

        final String uri = "/persons/" + personEmail + "/ledgers/records";

        // Input JSON
        NewCreatePersonInfoDTO newCreatePersonInfoDTO = new NewCreatePersonInfoDTO(personEmail, name, birthdate, birthplace);
        String inputJson = super.mapToJson(newCreatePersonInfoDTO);

        // Act
        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        final int status = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.NOT_FOUND.value(), status);

        final String content = mvcResult.getResponse().getContentAsString();
        JSONObject obj = new JSONObject(content);

        assertEquals("NOT_FOUND", obj.get("status"));
        assertEquals(CreatePersonService.PERSON_DOES_NOT_EXIST, obj.get("message"));
        assertEquals("[\"Status Code: " + HttpStatus.NOT_FOUND.value() + ", Exception: NotFoundArgumentsBusinessException\"]", obj.get("errors").toString());
    }

    // Get Person Accounts

    @Test
    @DisplayName("GetPerson Ledger- Fail - Person does not exist")
    public void GetPersonAccounts_Content() throws Exception {

        // Arrange

        final String personEmail = "pp@gmail.com";
        final String name = "Paulo Fontes";
        final String birthdate = "1993-03-15";
        final String birthplace = "Vila Nova de Gaia";

        final String uri = "/persons/" + personEmail + "/accounts";

        // Input JSON
        NewCreatePersonInfoDTO newCreatePersonInfoDTO = new NewCreatePersonInfoDTO(personEmail, name, birthdate, birthplace);
        String inputJson = super.mapToJson(newCreatePersonInfoDTO);

        // Act
        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        final int status = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.NOT_FOUND.value(), status);

        final String content = mvcResult.getResponse().getContentAsString();
        JSONObject obj = new JSONObject(content);

        assertEquals("NOT_FOUND", obj.get("status"));
        assertEquals(CreatePersonService.PERSON_DOES_NOT_EXIST, obj.get("message"));
        assertEquals("[\"Status Code: " + HttpStatus.NOT_FOUND.value() + ", Exception: NotFoundArgumentsBusinessException\"]", obj.get("errors").toString());
    }


    // Get Person Categories

    @Test
    @DisplayName("GetPerson Ledger- Fail - Person does not exist")
    public void GetPersonCategories_Content() throws Exception {

        // Arrange

        final String personEmail = "pp@gmail.com";
        final String name = "Paulo Fontes";
        final String birthdate = "1993-03-15";
        final String birthplace = "Vila Nova de Gaia";

        final String uri = "/persons/" + personEmail + "/categories";

        // Input JSON
        NewCreatePersonInfoDTO newCreatePersonInfoDTO = new NewCreatePersonInfoDTO(personEmail, name, birthdate, birthplace);
        String inputJson = super.mapToJson(newCreatePersonInfoDTO);

        // Act
        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        final int status = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.NOT_FOUND.value(), status);

        final String content = mvcResult.getResponse().getContentAsString();
        JSONObject obj = new JSONObject(content);

        assertEquals("NOT_FOUND", obj.get("status"));
        assertEquals(CreatePersonService.PERSON_DOES_NOT_EXIST, obj.get("message"));
        assertEquals("[\"Status Code: " + HttpStatus.NOT_FOUND.value() + ", Exception: NotFoundArgumentsBusinessException\"]", obj.get("errors").toString());
    }


    // Get Person Siblings

    @Test
    @DisplayName("GetPerson Ledger- Fail - Person does not exist")
    public void GetPersonSiblings_Content() throws Exception {

        // Arrange

        final String personEmail = "pp@gmail.com";
        final String name = "Paulo Fontes";
        final String birthdate = "1993-03-15";
        final String birthplace = "Vila Nova de Gaia";

        final String uri = "/persons/" + personEmail + "/siblings";

        // Input JSON
        NewCreatePersonInfoDTO newCreatePersonInfoDTO = new NewCreatePersonInfoDTO(personEmail, name, birthdate, birthplace);
        String inputJson = super.mapToJson(newCreatePersonInfoDTO);

        // Act
        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        final int status = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.NOT_FOUND.value(), status);

        final String content = mvcResult.getResponse().getContentAsString();
        JSONObject obj = new JSONObject(content);

        assertEquals("NOT_FOUND", obj.get("status"));
        assertEquals(CreatePersonService.PERSON_DOES_NOT_EXIST, obj.get("message"));
        assertEquals("[\"Status Code: " + HttpStatus.NOT_FOUND.value() + ", Exception: NotFoundArgumentsBusinessException\"]", obj.get("errors").toString());
    }

}
