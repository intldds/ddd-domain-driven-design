package com.finance.project.controllerLayer.integrationTests;

import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.finance.project.dtos.dtos.NewPersonTransactionInfoDTO;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class CreatePersonTransactionControllerTest extends AbstractTest {

    @Override
    @BeforeAll
    public void setUp() {
        super.setUp();
    }

    /*

    //Success

    //Get Person Records Before POST
    @Test
    public void test1_getPersonLedgerRecords_InitialState() throws Exception {

        //Arrange
        String email = "paulo@gmail.com";

        final String date = "2020-02-21";
        String denominationCategory = "Salary";
        String type = "credit";
        String description = "February salary";
        double amount = 1500.0;
        String denominationAccountDeb = "Company";
        String denominationAccountCred = "Bank Account";

        final String uri = "/persons/" + email + "/ledgers/records";

        //Act
        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();

        //Assert
        final int status = mvcResult.getResponse().getStatus();
//        assertEquals(HttpStatus.OK.value(), status);

        final String content = mvcResult.getResponse().getContentAsString();
        JSONObject obj = new JSONObject(content);
        int size = obj.getJSONArray("transactions").length();
        JSONObject item = obj.getJSONArray("transactions").getJSONObject(size - 1);

        //Structure
        assertTrue(obj.has("transactions"));

        //Records Size
        assertEquals(8, size);

        //Content
        assertEquals(date, item.get("date"));
        assertEquals(denominationCategory, item.get("category"));
        assertEquals(type, item.get("type"));
        assertEquals(description, item.get("description"));
        assertEquals(amount, item.get("amount"));
        assertEquals(denominationAccountDeb, item.get("debitAccount"));
        assertEquals(denominationAccountCred, item.get("creditAccount"));

    }

    //Create Transaction Success
    @Test
    public void test2_createPersonTransaction_Success() throws Exception {

        // Arrange
        String email = "paulo@gmail.com";
        String name = "Paulo Fontes";
        final String birthdate = LocalDate.of(1993, 03, 15).toString();
        final String birthplace = "Vila Nova de Gaia";
        final String father = "manuel@gmail.com";
        final String mother = "ilda@gmail.com";

        String denominationCategory = "IRS";
        String type = "debit";
        String description = "May IRS";
        double amount = 150.0;
        String denominationAccountDeb = "Bank Account";
        String denominationAccountCred = "State";
        String date = "2020-05-27";

        final String uri = "/persons/" + email + "/ledgers/records";

        //Input Json
        NewPersonTransactionInfoDTO newPersonTransactionInfoDTO = new NewPersonTransactionInfoDTO(denominationCategory, type, description, amount, denominationAccountDeb, denominationAccountCred, date);
        String inputJson = super.mapToJson(newPersonTransactionInfoDTO);

        //Act
        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        //Assert
        final int status = mvcResult.getResponse().getStatus();
//        assertEquals(HttpStatus.CREATED.value(), status);

        final String content = mvcResult.getResponse().getContentAsString();
        JSONObject obj = new JSONObject(content);

        //Structure
        assertTrue(obj.has("_links"));
        assertTrue(obj.has("name"));
        assertTrue(obj.has("email"));
        assertTrue(obj.has("ledger"));
        assertTrue(obj.has("birthdate"));
        assertTrue(obj.has("birthplace"));
        assertTrue(obj.has("father"));
        assertTrue(obj.has("mother"));

        //Content
        assertEquals(email, obj.get("email"));
        assertEquals(name, obj.get("name"));
        assertEquals(birthdate, obj.get("birthdate"));
        assertEquals(birthplace, obj.get("birthplace"));
        assertEquals(father, obj.get("father"));
        assertEquals(mother, obj.get("mother"));

    }

    //Get Person Records after Post
//    @Test
//    public void test3_getPersonRecords_PostState() throws Exception {
//
//        //Arrange
//        // Arrange
//        String email = "paulo@gmail.com";
//
//        String denominationCategory = "IRS";
//        String type = "debit";
//        String description = "May IRS";
//        double amount = 150.0;
//        String denominationAccountDeb = "Bank Account";
//        String denominationAccountCred = "State";
//        String date = "2020-05-27";
//
//        final String uri = "/persons/" + email + "/ledgers/records";
//
//        //Act
//        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
//
//        //Assert
//        final int status = mvcResult.getResponse().getStatus();
//        assertEquals(HttpStatus.OK.value(), status);
//
//        final String content = mvcResult.getResponse().getContentAsString();
//        JSONObject obj = new JSONObject(content);
//        int size = obj.getJSONArray("transactions").length();
//        JSONObject item = obj.getJSONArray("transactions").getJSONObject(size - 1);
//
//        //Structure
//        assertTrue(obj.has("transactions"));
//
//        //Records Size
//        assertEquals(9, size);
//
//        //Content
//        assertEquals(date, item.get("date"));
//        assertEquals(denominationCategory, item.get("category"));
//        assertEquals(type, item.get("type"));
//        assertEquals(description, item.get("description"));
//        assertEquals(amount, item.get("amount"));
//        assertEquals(denominationAccountDeb, item.get("debitAccount"));
//        assertEquals(denominationAccountCred, item.get("creditAccount"));
//
//    }

    //Update Transaction Success
//    @Test
//    public void test4_updatePersonTransaction_Success() throws Exception {
//
//        // Arrange
//        String email = "paulo@gmail.com";
//        String name = "Paulo Fontes";
//        final String birthdate = LocalDate.of(1993, 03, 15).toString();
//        final String birthplace = "Vila Nova de Gaia";
//        final String father = "manuel@gmail.com";
//        final String mother = "ilda@gmail.com";
//
//        final int transactionNumber = 9;
//
//        String denominationCategory = "IRS";
//        String type = "debit";
//        String description = "May IRS";
//        double amount = 150.0;
//        String denominationAccountDeb = "Bank Account";
//        String denominationAccountCred = "State";
//        String date = "2020-05-27";
//
//        final String uri = "/persons/" + email + "/ledgers/records" + transactionNumber;
//
//        //Input Json
//        NewPersonTransactionInfoDTO newPersonTransactionInfoDTO = new NewPersonTransactionInfoDTO(denominationCategory, type, description, amount, denominationAccountDeb, denominationAccountCred, date);
//        String inputJson = super.mapToJson(newPersonTransactionInfoDTO);
//
//        //Act
//        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
//
//        //Assert
//        final int status = mvcResult.getResponse().getStatus();
//        assertEquals(HttpStatus.OK.value(), status);
//
//        final String content = mvcResult.getResponse().getContentAsString();
//        JSONObject obj = new JSONObject(content);
//
//        //Structure
//        assertTrue(obj.has("_links"));
//        assertTrue(obj.has("name"));
//        assertTrue(obj.has("email"));
//        assertTrue(obj.has("ledger"));
//        assertTrue(obj.has("birthdate"));
//        assertTrue(obj.has("birthplace"));
//        assertTrue(obj.has("father"));
//        assertTrue(obj.has("mother"));
//
//        //Content
//        assertEquals(email, obj.get("email"));
//        assertEquals(name, obj.get("name"));
//        assertEquals(birthdate, obj.get("birthdate"));
//        assertEquals(birthplace, obj.get("birthplace"));
//        assertEquals(father, obj.get("father"));
//        assertEquals(mother, obj.get("mother"));
//
//    }

     */

}
