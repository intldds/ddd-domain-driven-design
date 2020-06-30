package com.finance.project.controllerLayer.integrationTests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CheckGroupsFamilyControllerTest extends AbstractTest {

    @Override
    @BeforeAll
    public void setUp() {
        super.setUp();
    }

//    @Test
//    @DisplayName("Test getMapping | True | groupsThatAreFamily")
//    public void groupsThatAreFamily() throws Exception {
//
//        //Arrange
//
//        final String uri = "/groups/areFamily";
//
//        //Act
//
//        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
//
//        //Expected
//
//        final String content = mvcResult.getResponse().getContentAsString();
//        JSONObject obj = new JSONObject(content);
//
//        boolean groupThatAreFamily = obj.has("groupThatAreFamily");
//        boolean links = obj.has("_links");
//
//        //Assert
//
//        final int status = mvcResult.getResponse().getStatus();
//        assertEquals(HttpStatus.OK.value(), status);
//
//        assertTrue(groupThatAreFamily);
//        assertTrue(links);
//    }

    /*
    @Test
    @DisplayName("Test getMapping | true | groupsThatAreFamily | PauloGroup")
    public void groupPaulo() throws Exception {

        //Arrange

        final String groupDenominationPauloFamily = "Fontes Family";

        final String uri = "/groups/areFamily/" + groupDenominationPauloFamily;

        //Act

        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();

        //Assert

        final int status = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), status);

        final String content = mvcResult.getResponse().getContentAsString();
        JSONObject obj = new JSONObject(content);

        assertTrue(obj.has("denomination"));
        assertTrue(obj.has("description"));
        assertTrue(obj.has("dateOfCreation"));
        assertTrue(obj.has("_links"));
    }

     */

    /*
    @Test
    @DisplayName("Test getMapping | true | groupsThatAreFamily | PauloGroup | members")
    public void groupPauloMembers() throws Exception {

        //Arrange

        final String groupDenominationPauloFamily = "Fontes Family";

        final String uri = "/groups/areFamily/" + groupDenominationPauloFamily + "/allMembers";

        //Act

        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();

        //Assert

        final int status = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), status);

        final String content = mvcResult.getResponse().getContentAsString();
        JSONObject obj = new JSONObject(content);

        assertTrue(obj.has("members"));
    }

     */
}
