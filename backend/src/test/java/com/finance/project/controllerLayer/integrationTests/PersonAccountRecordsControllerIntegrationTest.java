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
public class PersonAccountRecordsControllerIntegrationTest extends AbstractTest {

    @Override
    @BeforeAll
    public void setUp() {
        super.setUp();
    }


    // One search parameter empty

    @Test
    @DisplayName("Integration test for searching a person records, for an account, within 2 dates - Empty account name (404 Not Found)")
    void searchPersonRecords_emptyAccountName() throws Exception {

        //ARRANGE
        //Person to search
        final String personEmail = "elsa@gmail.com";

        //Account to search - Wallet

        //Dates to search
        final String startDateString = "2020-01-10";
        final String endDateString = "2020-02-10";

        //Uri
        final String uri = "/persons/" + personEmail + "/ledgers/records?startDate=" + startDateString + "&endDate=" + endDateString;

        //ACT
        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
        final int status = mvcResult.getResponse().getStatus();
        final String content = mvcResult.getResponse().getContentAsString();
        JSONObject jsonObject = new JSONObject(content);

        //ASSERT
        assertTrue(jsonObject.has("errors"));
    }

    @Test
    @DisplayName("Integration test for searching a person records, for an account, within 2 dates - Empty start date (404 Not Found)")
    void searchPersonRecords_emptyStartDate() throws Exception {

        //ARRANGE
        //Person to search
        final String personEmail = "elsa@gmail.com";

        //Account to search - Wallet
        final String accountDenomination = "Wallet";

        //Dates to search
        final String endDateString = "2020-02-10";

        //Uri
        final String uri = "/persons/" + personEmail + "/ledgers/records?accountName=" + accountDenomination + "&endDate=" + endDateString;

        //ACT
        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
        final int status = mvcResult.getResponse().getStatus();
        final String content = mvcResult.getResponse().getContentAsString();
        JSONObject jsonObject = new JSONObject(content);

        //ASSERT
        assertTrue(jsonObject.has("errors"));
    }


    @Test
    @DisplayName("Integration test for searching a person records, for an account, within 2 dates - Empty end date (404 Not Found)")
    void searchPersonRecords_emptyEndDate() throws Exception {

        //ARRANGE
        //Person to search
        final String personEmail = "elsa@gmail.com";

        //Account to search - Wallet
        final String accountDenomination = "Wallet";

        //Dates to search
        final String startDateString = "2020-01-10";

        //Uri
        final String uri = "/persons/" + personEmail + "/ledgers/records?accountName=" + accountDenomination + "&startDate=" + startDateString;

        //ACT
        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
        final int status = mvcResult.getResponse().getStatus();
        final String content = mvcResult.getResponse().getContentAsString();
        JSONObject jsonObject = new JSONObject(content);

        //ASSERT
        assertTrue(jsonObject.has("errors"));
    }


    //Unprocessable entities

    @Test
    @DisplayName("Integration test for searching a person records, for an account, within 2 dates - Person does not exist (422 Unprocessable Entity)")
    void searchPersonRecords_personDoesntExist() throws Exception {

        //ARRANGE
        //Person to search
        final String personEmail = "isolina@gmail.com";

        //Account to search - Wallet
        final String accountDenomination = "Wallet";

        //Dates to search
        final String startDateString = "2020-01-10";
        final String endDateString = "2020-02-10";

        //Uri
        final String uri = "/persons/" + personEmail + "/ledgers/records?accountName=" + accountDenomination + "&startDate=" + startDateString + "&endDate=" + endDateString;

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
    @DisplayName("Integration test for searching a person records, for an account, within 2 dates - Account does not exist (422 Unprocessable Entity)")
    void searchPersonRecords_accountDoesntExist() throws Exception {

        //ARRANGE
        //Person to search
        final String personEmail = "elsa@gmail.com";

        //Account to search - Wallet
        final String accountDenomination = "Swimming";

        //Dates to search
        final String startDateString = "2020-01-10";
        final String endDateString = "2020-02-10";

        //Uri
        final String uri = "/persons/" + personEmail + "/ledgers/records?accountName=" + accountDenomination + "&startDate=" + startDateString + "&endDate=" + endDateString;

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
    @DisplayName("Integration test for searching a person records, for an account, within 2 dates - Incorrect date order (422 Unprocessable Entity)")
    void searchPersonRecords_incorrectDateOrder() throws Exception {

        //ARRANGE
        //Person to search
        final String personEmail = "elsa@gmail.com";

        //Account to search - Wallet
        final String accountDenomination = "Swimming";

        //Dates to search
        final String startDateString = "2020-02-10";
        final String endDateString = "2020-01-10";

        //Uri
        final String uri = "/persons/" + personEmail + "/ledgers/records?accountName=" + accountDenomination + "&startDate=" + startDateString + "&endDate=" + endDateString;

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
    @DisplayName("Integration test for searching a person records, for an account, within 2 dates - Search range after ledger records (404 Not Found)")
    void searchPersonRecords_searchRangeAfterLedger() throws Exception {

        //ARRANGE
        //Person to search
        final String personEmail = "elsa@gmail.com";

        //Account to search - Wallet
        final String accountDenomination = "Wallet";

        //Dates to search
        final String startDateString = "2020-05-01";
        final String endDateString = "2020-05-20";

        //Uri
        final String uri = "/persons/" + personEmail + "/ledgers/records?accountName=" + accountDenomination + "&startDate=" + startDateString + "&endDate=" + endDateString;

        //ACT
        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
        final int status = mvcResult.getResponse().getStatus();
        final String content = mvcResult.getResponse().getContentAsString();
        JSONObject jsonObject = new JSONObject(content);

        //ASSERT
        assertTrue(jsonObject.has("errors"));
    }


    @Test
    @DisplayName("Integration test for searching a person records, for an account, within 2 dates - Search range prior to ledger records (404 Not Found)")
    void searchPersonRecords_searchRangePriorToLedger() throws Exception {

        //ARRANGE
        //Person to search
        final String personEmail = "elsa@gmail.com";

        //Account to search - Wallet
        final String accountDenomination = "Wallet";

        //Dates to search
        final String startDateString = "2019-01-10";
        final String endDateString = "2019-02-10";

        //Uri
        final String uri = "/persons/" + personEmail + "/ledgers/records?accountName=" + accountDenomination + "&startDate=" + startDateString + "&endDate=" + endDateString;

        //ACT
        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
        final int status = mvcResult.getResponse().getStatus();
        final String content = mvcResult.getResponse().getContentAsString();
        JSONObject jsonObject = new JSONObject(content);

        //ASSERT
        assertTrue(jsonObject.has("errors"));
    }


    @Test
    @DisplayName("Integration test for searching a person records, for an account, within 2 dates - No transactions for the account (404 Not Found)")
    void searchPersonRecords_noRecordsForAccount() throws Exception {

        //ARRANGE
        //Person to search
        final String personEmail = "elsa@gmail.com";

        //Account to search - Wallet
        final String accountDenomination = "Wallet";

        //Dates to search
        final String startDateString = "2020-01-30";
        final String endDateString = "2020-02-22";

        //Uri
        final String uri = "/persons/" + personEmail + "/ledgers/records?accountName=" + accountDenomination + "&startDate=" + startDateString + "&endDate=" + endDateString;

        //ACT
        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
        final int status = mvcResult.getResponse().getStatus();
        final String content = mvcResult.getResponse().getContentAsString();
        JSONObject jsonObject = new JSONObject(content);

        //ASSERT
        assertTrue(jsonObject.has("errors"));
    }
}
