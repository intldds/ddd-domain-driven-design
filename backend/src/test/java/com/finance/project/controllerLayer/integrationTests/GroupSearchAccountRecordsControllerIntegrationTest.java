package com.finance.project.controllerLayer.integrationTests;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class GroupSearchAccountRecordsControllerIntegrationTest extends AbstractTest {

    @Override
    @BeforeAll
    public void setUp() {
        super.setUp();
    }

    /*

    //SUCCESS - search with search parameters: account name + start dat + end date
    @Test
    @DisplayName("Integration test for searching group records by a member, for an account, within 2 dates - Success (200 OK)")
    void searchPersonRecords_success() throws Exception {

        //ARRANGE
        String personEmail = "paulo@gmail.com";
        String groupDenomination = "Fontes Family";
        String accountDenomination = "Bank Account";
        String startDate = "2020-01-21";
        String endDate = "2020-01-29";

        //Uri
        final String uri = "/persons/" + personEmail + "/groups/" + groupDenomination + "/ledgers/records?accountName=" + accountDenomination + "&startDate=" + startDate + "&endDate=" + endDate;

        //ACT
        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
        final int status = mvcResult.getResponse().getStatus();
        final String content = mvcResult.getResponse().getContentAsString();
        JSONObject jsonObject = new JSONObject(content);

        //ASSERT
//        assertEquals(HttpStatus.OK.value(), status);
        assertTrue(jsonObject.has("transactions"));
    }


    //SUCCESS - search without search parameters: account name + start dat + end date
    @Test
    @DisplayName("Integration test for for searching group records - Success (200 OK)")
    void searchPersonRecords_success_noParameters() throws Exception {

        //ARRANGE
        String personEmail = "paulo@gmail.com";
        String groupDenomination = "Fontes Family";

        //Uri
        final String uri = "/persons/" + personEmail + "/groups/" + groupDenomination + "/ledgers/records";

        //ACT
        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
        final int status = mvcResult.getResponse().getStatus();
        final String content = mvcResult.getResponse().getContentAsString();
        JSONObject jsonObject = new JSONObject(content);

        //ASSERT
//        assertEquals(HttpStatus.OK.value(), status);
        assertTrue(jsonObject.has("transactions"));
    }


    //One search parameter empty
    @Test
    @DisplayName("Integration test for searching group records by a member, for an account, within 2 dates - Empty account name (404 Not Found)")
    void searchPersonRecords_emptyAccountName() throws Exception {

        //ARRANGE
        String personEmail = "paulo@gmail.com";
        String groupDenomination = "Fontes Family";
        String accountDenomination = "";
        String startDate = "2020-01-21";
        String endDate = "2020-01-29";

        //Uri
        final String uri = "/persons/" + personEmail + "/groups/" + groupDenomination + "/ledgers/records" + "?" + "&startDate=" + startDate + "&endDate=" + endDate;

        //ACT
        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
        final int status = mvcResult.getResponse().getStatus();
        final String content = mvcResult.getResponse().getContentAsString();
        JSONObject jsonObject = new JSONObject(content);

        //ASSERT
//        assertEquals(HttpStatus.NOT_FOUND.value(), status);
        assertTrue(jsonObject.has("errors"));
    }

    @Test
    @DisplayName("Integration test for searching group records by a member, for an account, within 2 dates - Empty start date (404 Not Found)")
    void searchPersonRecords_emptyStartDate() throws Exception {

        //ARRANGE
        String personEmail = "paulo@gmail.com";
        String groupDenomination = "Fontes Family";
        String accountDenomination = "Bank Account";
        String startDate = "";
        String endDate = "2020-01-29";

        //Uri
        final String uri = "/persons/" + personEmail + "/groups/" + groupDenomination + "/ledgers/records" + "?" + "accountName=" + accountDenomination + "&endDate=" + endDate;

        //ACT
        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
        final int status = mvcResult.getResponse().getStatus();
        final String content = mvcResult.getResponse().getContentAsString();
        JSONObject jsonObject = new JSONObject(content);

        //ASSERT
//        assertEquals(HttpStatus.NOT_FOUND.value(), status);
        assertTrue(jsonObject.has("errors"));
    }

    @Test
    @DisplayName("Integration test for searching group records by a member, for an account, within 2 dates - Empty end date (404 Not Found)")
    void searchPersonRecords_emptyEndDate() throws Exception {

        //ARRANGE
        String personEmail = "paulo@gmail.com";
        String groupDenomination = "Fontes Family";
        String accountDenomination = "Bank Account";
        String startDate = "2020-01-21";
        String endDate = "";

        //Uri
        final String uri = "/persons/" + personEmail + "/groups/" + groupDenomination + "/ledgers/records" + "?" + "accountName=" + accountDenomination + "&startDate=" + startDate;

        //ACT
        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
        final int status = mvcResult.getResponse().getStatus();
        final String content = mvcResult.getResponse().getContentAsString();
        JSONObject jsonObject = new JSONObject(content);

        //ASSERT
//        assertEquals(HttpStatus.NOT_FOUND.value(), status);
        assertTrue(jsonObject.has("errors"));
    }

    //Unprocessable entities
    @Test
    @DisplayName("Integration test for searching group records by a member, for an account, within 2 dates - Person does not exist (422 Unprocessable Entity)")
    void searchPersonRecords_personDoesntExist() throws Exception {

        //ARRANGE
        String personEmail = "joaquina@gmail.com";
        String groupDenomination = "Fontes Family";
        String accountDenomination = "Bank Account";
        String startDate = "2020-01-21";
        String endDate = "2020-01-29";

        //Uri
        final String uri = "/persons/" + personEmail + "/groups/" + groupDenomination + "/ledgers/records" + "?" + "accountName=" + accountDenomination + "&startDate=" + startDate + "&endDate=" + endDate;

        //ACT
        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
        final int status = mvcResult.getResponse().getStatus();
        final String content = mvcResult.getResponse().getContentAsString();
        JSONObject jsonObject = new JSONObject(content);

        //ASSERT
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.value(), status);
        assertTrue(jsonObject.has("errors"));
    }

    @Test
    @DisplayName("Integration test for searching group records by a member, for an account, within 2 dates - Account does not exist (422 Unprocessable Entity)")
    void searchPersonRecords_accountDoesntExist() throws Exception {

        //ARRANGE
        String personEmail = "paulo@gmail.com";
        String groupDenomination = "Fontes Family";
        String accountDenomination = "Pool";
        String startDate = "2020-01-21";
        String endDate = "2020-01-29";

        //Uri
        final String uri = "/persons/" + personEmail + "/groups/" + groupDenomination + "/ledgers/records" + "?" + "accountName=" + accountDenomination + "&startDate=" + startDate + "&endDate=" + endDate;

        //ACT
        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
        final int status = mvcResult.getResponse().getStatus();
        final String content = mvcResult.getResponse().getContentAsString();
        JSONObject jsonObject = new JSONObject(content);

        //ASSERT
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.value(), status);
        assertTrue(jsonObject.has("errors"));
    }

    @Test
    @DisplayName("Integration test for searching group records by a member, for an account, within 2 dates - Incorrect date order (422 Unprocessable Entity)")
    void searchPersonRecords_incorrectDateOrder() throws Exception {

        //ARRANGE
        String personEmail = "paulo@gmail.com";
        String groupDenomination = "Fontes Family";
        String accountDenomination = "Bank Account";
        String startDate = "2020-02-21";
        String endDate = "2020-01-29";

        //Uri
        final String uri = "/persons/" + personEmail + "/groups/" + groupDenomination + "/ledgers/records" + "?" + "accountName=" + accountDenomination + "&startDate=" + startDate + "&endDate=" + endDate;

        //ACT
        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
        final int status = mvcResult.getResponse().getStatus();
        final String content = mvcResult.getResponse().getContentAsString();
        JSONObject jsonObject = new JSONObject(content);

        //ASSERT
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.value(), status);
        assertTrue(jsonObject.has("errors"));
    }

    //Search out of ledger range
    @Test
    @DisplayName("Integration test for searching group records by a member, for an account, within 2 dates - Search range after ledger records (404 Not Found)")
    void searchPersonRecords_searchRangeAfterLedger() throws Exception {

        //ARRANGE
        String personEmail = "paulo@gmail.com";
        String groupDenomination = "Fontes Family";
        String accountDenomination = "Bank Account";
        String startDate = "2020-03-21";
        String endDate = "2020-04-29";

        //Uri
        final String uri = "/persons/" + personEmail + "/groups/" + groupDenomination + "/ledgers/records" + "?" + "accountName=" + accountDenomination + "&startDate=" + startDate + "&endDate=" + endDate;

        //ACT
        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
        final int status = mvcResult.getResponse().getStatus();
        final String content = mvcResult.getResponse().getContentAsString();
        JSONObject jsonObject = new JSONObject(content);

        //ASSERT
//        assertEquals(HttpStatus.NOT_FOUND.value(), status);
        assertTrue(jsonObject.has("errors"));
    }

    @Test
    @DisplayName("Integration test for searching group records by a member, for an account, within 2 dates - Search range prior to ledger records (404 Not Found)")
    void searchPersonRecords_searchRangePriorToLedger() throws Exception {

        //ARRANGE
        String personEmail = "paulo@gmail.com";
        String groupDenomination = "Fontes Family";
        String accountDenomination = "Bank Account";
        String startDate = "2019-01-21";
        String endDate = "2019-01-29";

        //Uri
        final String uri = "/persons/" + personEmail + "/groups/" + groupDenomination + "/ledgers/records" + "?" + "accountName=" + accountDenomination + "&startDate=" + startDate + "&endDate=" + endDate;

        //ACT
        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
        final int status = mvcResult.getResponse().getStatus();
        final String content = mvcResult.getResponse().getContentAsString();
        JSONObject jsonObject = new JSONObject(content);

        //ASSERT
       // assertEquals(HttpStatus.NOT_FOUND.value(), status);
        assertTrue(jsonObject.has("errors"));
    }

    @Test
    @DisplayName("Integration test for searching group records by a member, for an account, within 2 dates - No transactions for the account (404 Not Found)")
    void searchPersonRecords_noRecordsForAccount() throws Exception {

        //ARRANGE
        String personEmail = "paulo@gmail.com";
        String groupDenomination = "Fontes Family";
        String accountDenomination = "Bank Account";
        String startDate = "2020-01-29";
        String endDate = "2020-01-29";

        //Uri
        final String uri = "/persons/" + personEmail + "/groups/" + groupDenomination + "/ledgers/records" + "?" + "accountName=" + accountDenomination + "&startDate=" + startDate + "&endDate=" + endDate;

        //ACT
        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
        final int status = mvcResult.getResponse().getStatus();
        final String content = mvcResult.getResponse().getContentAsString();
        JSONObject jsonObject = new JSONObject(content);

        //ASSERT
//   //     assertEquals(HttpStatus.NOT_FOUND.value(), status);
        assertTrue(jsonObject.has("errors"));
    }

     */

}
