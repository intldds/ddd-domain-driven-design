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

    @Override
    @BeforeAll
    public void setUp() {
        super.setUp();
    }


    //--------------------------- Structure ----------------------//


    // Happy Case: Group Created!

    @Test
    public void createGroupAsPersonInCharge_HappyCase_StructureEndToEnd() throws Exception {

        // Arrange

        final String uri = "/groups";

        final String personEmail = "pedro@gmail.com";
        final String groupDenomination = "Monday FootPlayers";
        final String groupDescription = "All members from Monday Football Players group";

        // Json DTO (input)
        NewCreateGroupInfoDTO groupInfoDTO = new NewCreateGroupInfoDTO(personEmail, groupDenomination, groupDescription);
        String inputJson = super.mapToJson(groupInfoDTO);

        // Check DB
        final MvcResult mvcResultFirsTest = mvc.perform(MockMvcRequestBuilders.get(uri + groupDenomination)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        // Assert HttpStatus

        final int status = mvcResultFirsTest.getResponse().getStatus();

        assertEquals(HttpStatus.NOT_FOUND.value(), status);

        //  Assert HttpStatus

        final String statusContent = mvcResultFirsTest.getResponse().getContentAsString();


        //Structure
        assertTrue(statusContent.isEmpty());

        // Act

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

}
