package com.finance.project.controllerLayer.integrationTests;

import com.finance.project.applicationLayer.applicationServices.groupServices.CreateGroupTransactionService;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.finance.project.dtos.dtos.NewGroupTransactionInfoDTO;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class CreateGroupTransactionControllerTest extends AbstractTest {

    @Override
    @BeforeAll
    public void setUp() {
        super.setUp();
    }

    /*

    //SUCCESS


    //Get Group Records before POST


    @Test
    public void test1_getGroupLedgerRecords_InitialState() throws Exception {

        //Arrange

        final String groupDenomination = "Fontes Family";

        final String date = "2020-02-21";
        final String denominationCategory = "Salary";
        final String type = "credit";
        final String description = "February salary";
        final double amount = 1500.0;
        final String denominationAccountDeb = "Company";
        final String denominationAccountCred = "Bank Account";

        final String uri = "/groups/" + groupDenomination + "/ledgers/records";


        //Act

        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();


        //Assert

        final int status = mvcResult.getResponse().getStatus();
       // assertEquals(HttpStatus.OK.value(), status);

        final String content = mvcResult.getResponse().getContentAsString();
        JSONObject obj = new JSONObject(content);
        int size = obj.getJSONArray("transactions").length();
        JSONObject item = obj.getJSONArray("transactions").getJSONObject(size - 1);


        //Structure
        assertTrue(obj.has("transactions"));

        //Records Size
        assertEquals(7, size);

        //Content
        assertEquals(date, item.get("date"));
        assertEquals(amount, item.get("amount"));
        assertEquals(denominationAccountDeb, item.get("debitAccount"));
        assertEquals(denominationAccountCred, item.get("creditAccount"));
        assertEquals(description, item.get("description"));
        assertEquals(denominationCategory, item.get("category"));
        assertEquals(type, item.get("type"));
    }


    //Create Transaction SUCCESS


    @Test
    public void test2_createGroupTransaction_Success() throws Exception {

        //Arrange

        final String personEmail = "manuel@gmail.com";
        final String groupDenomination = "Fontes Family";
        final String groupDescription = "All members from Fontes family";

        final String denominationCategory = "IRS";
        final String type = "debit";
        final String description = "May IRS";
        final double amount = 150.0;
        final String denominationAccountDeb = "Bank Account";
        final String denominationAccountCred = "State";
        final String transactionDate = "2020-06-18";

        final String uri = "/persons/" + personEmail + "/groups/" + groupDenomination + "/ledgers/records";

        //Input Json
        NewGroupTransactionInfoDTO newGroupTransactionInfoDTO = new NewGroupTransactionInfoDTO(denominationCategory, type, description, amount, denominationAccountDeb, denominationAccountCred, transactionDate);
        String inputJson = super.mapToJson(newGroupTransactionInfoDTO);


        //Act

        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();


        //Assert

        final int status = mvcResult.getResponse().getStatus();
       // assertEquals(HttpStatus.CREATED.value(), status);

        final String content = mvcResult.getResponse().getContentAsString();
        JSONObject obj = new JSONObject(content);

        //Structure
        assertTrue(obj.has("denomination"));
        assertTrue(obj.has("description"));
        assertTrue(obj.has("dateOfCreation"));
        assertTrue(obj.has("ledger"));
        assertTrue(obj.has("_links"));

        //Content
        assertEquals(groupDenomination, obj.get("denomination"));
        assertEquals(groupDescription, obj.get("description"));
        assertEquals(LocalDate.now().toString(), obj.get("dateOfCreation"));

    }


    //Get Group Records after POST


    @Test
    public void test3_getGroupLedgerRecords_PostState() throws Exception {

        //Arrange

        final String groupDenomination = "Fontes Family";

        final String denominationCategory = "IRS";
        final String type = "debit";
        final String description = "May IRS";
        final double amount = 150.0;
        final String denominationAccountDeb = "Bank Account";
        final String denominationAccountCred = "State";
        final String transactionDate = "2020-06-18";

        final String uri = "/groups/" + groupDenomination + "/ledgers/records";


        //Act

        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();


        //Assert

        final int status = mvcResult.getResponse().getStatus();
      //  assertEquals(HttpStatus.OK.value(), status);

        final String content = mvcResult.getResponse().getContentAsString();
        JSONObject obj = new JSONObject(content);
        int size = obj.getJSONArray("transactions").length();
        JSONObject item = obj.getJSONArray("transactions").getJSONObject(size - 1);


        //Structure
        assertTrue(obj.has("transactions"));

        //Records Size
        assertEquals(8, size);

        //Content
        assertEquals(transactionDate, item.get("date"));
        assertEquals(amount, item.get("amount"));
        assertEquals(denominationAccountDeb, item.get("debitAccount"));
        assertEquals(denominationAccountCred, item.get("creditAccount"));
        assertEquals(description, item.get("description"));
        assertEquals(denominationCategory, item.get("category"));
        assertEquals(type, item.get("type"));

    }


    //Update Transaction SUCCESS


    @Test
    public void test4_updateGroupTransaction_Success() throws Exception {

        //Arrange

        final String personEmail = "manuel@gmail.com";
        final String groupDenomination = "Fontes Family";
        final String groupDescription = "All members from Fontes family";

        final int transactionNumber = 8;

        final String denominationCategory = "Netflix";
        final String type = "debit";
        final String description = "Movies and TV Shows";
        final double amount = 25.68;
        final String denominationAccountDeb = "Bank Account";
        final String denominationAccountCred = "Streaming Services";
        final String transactionDate = "2020-06-18";

        final String uri = "/persons/" + personEmail + "/groups/" + groupDenomination + "/ledgers/records/" + transactionNumber;

        //Input Json
        NewGroupTransactionInfoDTO newGroupTransactionInfoDTO = new NewGroupTransactionInfoDTO(denominationCategory, type, description, amount, denominationAccountDeb, denominationAccountCred, transactionDate);
        String inputJson = super.mapToJson(newGroupTransactionInfoDTO);


        //Act

        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();


        //Assert

        final int status = mvcResult.getResponse().getStatus();
      //  assertEquals(HttpStatus.OK.value(), status);

        final String content = mvcResult.getResponse().getContentAsString();
        JSONObject obj = new JSONObject(content);

        //Structure
        assertTrue(obj.has("denomination"));
        assertTrue(obj.has("description"));
        assertTrue(obj.has("dateOfCreation"));
        assertTrue(obj.has("ledger"));
        assertTrue(obj.has("_links"));

        //Content
        assertEquals(groupDenomination, obj.get("denomination"));
        assertEquals(groupDescription, obj.get("description"));
        assertEquals(LocalDate.now().toString(), obj.get("dateOfCreation"));

    }


    //Get Group Records after PUT


    @Test
    public void test5_getGroupLedgerRecords_UpdateState() throws Exception {

        //Arrange

        final String groupDenomination = "Fontes Family";

        final String denominationCategory = "Netflix";
        final String type = "debit";
        final String description = "Movies and TV Shows";
        final double amount = 25.68;
        final String denominationAccountDeb = "Bank Account";
        final String denominationAccountCred = "Streaming Services";
        final String transactionDate = "2020-06-18";

        final String uri = "/groups/" + groupDenomination + "/ledgers/records";


        //Act

        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();


        //Assert

        final int status = mvcResult.getResponse().getStatus();
       // assertEquals(HttpStatus.OK.value(), status);

        final String content = mvcResult.getResponse().getContentAsString();
        JSONObject obj = new JSONObject(content);
        int size = obj.getJSONArray("transactions").length();
        JSONObject item = obj.getJSONArray("transactions").getJSONObject(size - 1);


        //Structure
        assertTrue(obj.has("transactions"));

        //Records Size
        assertEquals(8, size);

        //Content
        assertEquals(transactionDate, item.get("date"));
        assertEquals(amount, item.get("amount"));
        assertEquals(denominationAccountDeb, item.get("debitAccount"));
        assertEquals(denominationAccountCred, item.get("creditAccount"));
        assertEquals(description, item.get("description"));
        assertEquals(denominationCategory, item.get("category"));
        assertEquals(type, item.get("type"));

    }


    //Delete Transaction SUCCESS


    @Test
    public void test6_deleteGroupTransaction_Succsess() throws Exception {

        //Arrange

        final String personEmail = "manuel@gmail.com";
        final String groupDenomination = "Fontes Family";
        final String groupDescription = "All members from Fontes family";

        final int transactionNumber = 8;

        final String uri = "/persons/" + personEmail + "/groups/" + groupDenomination + "/ledgers/records/" + transactionNumber;


        //Act

        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();


        //Assert

        final int status = mvcResult.getResponse().getStatus();
       // assertEquals(HttpStatus.OK.value(), status);

        final String content = mvcResult.getResponse().getContentAsString();
        JSONObject obj = new JSONObject(content);

        //Structure
        assertTrue(obj.has("denomination"));
        assertTrue(obj.has("description"));
        assertTrue(obj.has("dateOfCreation"));
        assertTrue(obj.has("ledger"));
        assertTrue(obj.has("_links"));

        //Content
        assertEquals(groupDenomination, obj.get("denomination"));
        assertEquals(groupDescription, obj.get("description"));
        assertEquals(LocalDate.now().toString(), obj.get("dateOfCreation"));

    }


    //Get Group Records after DELETE


    @Test
    public void test7_getGroupLedgerRecords_DeleteState() throws Exception {

        //Arrange

        final String groupDenomination = "Fontes Family";

        final String date = "2020-02-21";
        final String denominationCategory = "Salary";
        final String type = "credit";
        final String description = "February salary";
        final double amount = 1500.0;
        final String denominationAccountDeb = "Company";
        final String denominationAccountCred = "Bank Account";


        final String uri = "/groups/" + groupDenomination + "/ledgers/records";


        //Act

        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();


        //Assert

        final int status = mvcResult.getResponse().getStatus();
       // assertEquals(HttpStatus.OK.value(), status);

        final String content = mvcResult.getResponse().getContentAsString();
        JSONObject obj = new JSONObject(content);
        int size = obj.getJSONArray("transactions").length();
        JSONObject item = obj.getJSONArray("transactions").getJSONObject(size - 1);


        //Structure
        assertTrue(obj.has("transactions"));

        //Records Size
        assertEquals(7, size);

        //Content
        assertEquals(date, item.get("date"));
        assertEquals(amount, item.get("amount"));
        assertEquals(denominationAccountDeb, item.get("debitAccount"));
        assertEquals(denominationAccountCred, item.get("creditAccount"));
        assertEquals(description, item.get("description"));
        assertEquals(denominationCategory, item.get("category"));
        assertEquals(type, item.get("type"));

    }


    //EXCEPTIONS


    //GROUP_DOES_NOT_EXIST


    @Test
    public void createGroupTransactionGroupDoesNotExist() throws Exception {

        //Arrange

        final String personEmail = "manuel@gmail.com";
        final String groupDenomination = "Vale Family";

        final String denominationCategory = "IRS";
        final String type = "debit";
        final String description = "May IRS";
        final double amount = 150.0;
        final String denominationAccountDeb = "Bank Account";
        final String denominationAccountCred = "State";
        final String transactionDate = "2020-06-18";

        final String uri = "/persons/" + personEmail + "/groups/" + groupDenomination + "/ledgers/records";

        //Input Json
        NewGroupTransactionInfoDTO newGroupTransactionInfoDTO = new NewGroupTransactionInfoDTO(denominationCategory, type, description, amount, denominationAccountDeb, denominationAccountCred, transactionDate);
        String inputJson = super.mapToJson(newGroupTransactionInfoDTO);


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

        //Content
        assertEquals("NOT_FOUND", obj.get("status"));
        Assertions.assertEquals(CreateGroupTransactionService.GROUP_DOES_NOT_EXIST, obj.get("message"));
        assertEquals("[\"Status Code: " + HttpStatus.NOT_FOUND.value() + ", Exception: NotFoundArgumentsBusinessException\"]", obj.get("errors").toString());

    }

    //PERSON_NOT_MEMBER

    @Test
    public void createGroupTransactionPersonNotMember() throws Exception {

        //Arrange

        final String personEmail = "ricardo@gmail.com";
        final String groupDenomination = "Fontes Family";

        final String denominationCategory = "IRS";
        final String type = "debit";
        final String description = "May IRS";
        final double amount = 150.0;
        final String denominationAccountDeb = "Bank Account";
        final String denominationAccountCred = "State";
        final String transactionDate = "2020-06-18";

        final String uri = "/persons/" + personEmail + "/groups/" + groupDenomination + "/ledgers/records";

        //Input Json
        NewGroupTransactionInfoDTO newGroupTransactionInfoDTO = new NewGroupTransactionInfoDTO(denominationCategory, type, description, amount, denominationAccountDeb, denominationAccountCred, transactionDate);
        String inputJson = super.mapToJson(newGroupTransactionInfoDTO);


        //Act

        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();


        //Assert

        final int status = mvcResult.getResponse().getStatus();
       //  assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.value(), status);

        final String content = mvcResult.getResponse().getContentAsString();
        JSONObject obj = new JSONObject(content);

        //Structure
        assertTrue(obj.has("status"));
        assertTrue(obj.has("message"));
        assertTrue(obj.has("errors"));

        //Content
        assertEquals("UNPROCESSABLE_ENTITY", obj.get("status"));
        assertEquals(CreateGroupTransactionService.PERSON_NOT_MEMBER, obj.get("message"));
        assertEquals("[\"Status Code: " + HttpStatus.UNPROCESSABLE_ENTITY.value() + ", Exception: InvalidArgumentsBusinessException\"]", obj.get("errors").toString());

    }

    //NEED_TO_CREATE_CATEGORY

    @Test
    public void createGroupTransactionNeedToCreateCategory() throws Exception {

        //Arrange

        final String personEmail = "manuel@gmail.com";
        final String groupDenomination = "Fontes Family";

        final String denominationCategory = "Pets";
        final String type = "debit";
        final String description = "May IRS";
        final double amount = 150.0;
        final String denominationAccountDeb = "Bank Account";
        final String denominationAccountCred = "State";
        final String transactionDate = "2020-06-18";

        final String uri = "/persons/" + personEmail + "/groups/" + groupDenomination + "/ledgers/records";

        //Input Json
        NewGroupTransactionInfoDTO newGroupTransactionInfoDTO = new NewGroupTransactionInfoDTO(denominationCategory, type, description, amount, denominationAccountDeb, denominationAccountCred, transactionDate);
        String inputJson = super.mapToJson(newGroupTransactionInfoDTO);


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

        //Content
        assertEquals("NOT_FOUND", obj.get("status"));
        // assertEquals(CreateGroupTransactionService.NEED_TO_CREATE_CATEGORY, obj.get("message"));
        assertEquals("[\"Status Code: " + HttpStatus.NOT_FOUND.value() + ", Exception: NotFoundArgumentsBusinessException\"]", obj.get("errors").toString());

    }

    //NEED_TO_CREATE_ACCOUNT_TO_DEBIT

    @Test
    public void createGroupTransactionNeedToCreateAccountToDebit() throws Exception {

        //Arrange

        final String personEmail = "manuel@gmail.com";
        final String groupDenomination = "Fontes Family";

        final String denominationCategory = "IRS";
        final String type = "debit";
        final String description = "May IRS";
        final double amount = 150.0;
        final String denominationAccountDeb = "Vet";
        final String denominationAccountCred = "State";
        final String transactionDate = "2020-06-18";

        final String uri = "/persons/" + personEmail + "/groups/" + groupDenomination + "/ledgers/records";

        //Input Json
        NewGroupTransactionInfoDTO newGroupTransactionInfoDTO = new NewGroupTransactionInfoDTO(denominationCategory, type, description, amount, denominationAccountDeb, denominationAccountCred, transactionDate);
        String inputJson = super.mapToJson(newGroupTransactionInfoDTO);


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

        //Content
        assertEquals("NOT_FOUND", obj.get("status"));
        // assertEquals(CreateGroupTransactionService.NEED_TO_CREATE_ACCOUNT_TO_DEBIT, obj.get("message"));
        assertEquals("[\"Status Code: " + HttpStatus.NOT_FOUND.value() + ", Exception: NotFoundArgumentsBusinessException\"]", obj.get("errors").toString());

    }

    //NEED_TO_CREATE_ACCOUNT_TO_CREDIT

    @Test
    public void createGroupTransactionNeedToCreateAccountToCredit() throws Exception {

        //Arrange

        final String personEmail = "manuel@gmail.com";
        final String groupDenomination = "Fontes Family";

        final String denominationCategory = "IRS";
        final String type = "debit";
        final String description = "May IRS";
        final double amount = 150.0;
        final String denominationAccountDeb = "Bank Account";
        final String denominationAccountCred = "Vet";
        final String transactionDate = "2020-06-18";

        final String uri = "/persons/" + personEmail + "/groups/" + groupDenomination + "/ledgers/records";

        //Input Json
        NewGroupTransactionInfoDTO newGroupTransactionInfoDTO = new NewGroupTransactionInfoDTO(denominationCategory, type, description, amount, denominationAccountDeb, denominationAccountCred, transactionDate);
        String inputJson = super.mapToJson(newGroupTransactionInfoDTO);


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

        //Content
        assertEquals("NOT_FOUND", obj.get("status"));
        // assertEquals(CreateGroupTransactionService.NEED_TO_CREATE_ACCOUNT_TO_CREDIT, obj.get("message"));
        assertEquals("[\"Status Code: " + HttpStatus.NOT_FOUND.value() + ", Exception: NotFoundArgumentsBusinessException\"]", obj.get("errors").toString());

    }

     */

}
