package com.finance.project.controllerLayer.integrationTests;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Ala Matos
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//----------------------------------------------------------------------------------------------------------------

//Adding @TestInstance(TestInstance.Lifecycle.PER_CLASS) to your test class will avoid that a
// new instance of your class is created for every test in the class. This is particularly useful
// when you have a lot of tests in the same test class and the instantiation of this class is expensive.
//If you would prefer that JUnit Jupiter execute all test methods on the same test instance, annotate
// your test class with @TestInstance(Lifecycle.PER_CLASS). When using this mode, a new test instance
// will be created once per test class.

//----------------------------------------------------------------------------------------------------------------

public class CheckSiblingsControllerTest extends AbstractTest {

    //extends - A derived class can extend a base class. You may redefine the behaviour of an established relation.
    // Derived class "is a" base class type

    @Override
    @BeforeAll
    public void setUp() {
        super.setUp();
    }
    //The super keyword refers to superclass (parent) objects.
    //It is used to call superclass methods, and to access the superclass constructor.


    /*

    @Test
    @DisplayName("Test getMapping | True | Siblings")
    public void getMappingCheckIfSiblings() throws Exception {

        //Arrange

        String uriPersons = "/persons/";
        String uriSiblings = "/siblings/";
        final String personEmail = "paulo@gmail.com";
        final String otherPersonEmail = "helder@gmail.com";

        //Act

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                .get(uriPersons + personEmail + uriSiblings + otherPersonEmail)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

//        Assert HttpStatus

        final int status = mvcResult.getResponse().getStatus();

//        assertEquals(HttpStatus.OK.value(), status);

//        Create a JSON object with the content returned

        final String content = mvcResult.getResponse().getContentAsString();
        JSONObject contentObject = new JSONObject(content);

//        Assert existence and content of result

        String resultContent = contentObject.get("result").toString();
        assertTrue(contentObject.has("result"));
        assertEquals("true", resultContent);

//        Assert existence and content of msg

        String msgContent= contentObject.get("msg").toString();
        assertTrue(contentObject.has("msg"));
        assertEquals("Siblings", msgContent);

//        Assert existence of _links

        assertTrue(contentObject.has("_links"));
    }

    @Test
    @DisplayName("Test getMapping | False | First person doesn't exists")
    public void getMappingCheckIfSiblingsFirstPersonDoesntExists() throws Exception {

        //Arrange

        String uriPersons = "/persons/";
        String uriSiblings = "/siblings/";
        final String personEmail = "hulk@gmail.com";
        final String otherPersonEmail = "henrique@gmail.com";

//        Act
        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                .get(uriPersons + personEmail + uriSiblings + otherPersonEmail)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

//        Assert HttpStatus

        final int status = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.NOT_FOUND.value(),status);

//        Create a JSON object with the content returned

        final String content = mvcResult.getResponse().getContentAsString();
        JSONObject contentObject = new JSONObject(content);

//        Assert existence and content of msg

        String msgContent = contentObject.get("message").toString();
        assertTrue(contentObject.has("message"));
        assertEquals("First person does not exist", msgContent);

//        Assert existence of errors

        assertTrue(contentObject.has("errors"));

    }

    @Test
    @DisplayName("Test getMapping | False | Second person doesn't exists")
    public void getMappingCheckIfSiblingsSecondPersonDoesntExists() throws Exception {

        //Arrange

        String uriPersons = "/persons/";
        String uriSiblings = "/siblings/";
        final String personEmail = "paulo@gmail.com";
        final String otherPersonEmail = "hulk@gmail.com";

//        Act
        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                .get(uriPersons + personEmail + uriSiblings + otherPersonEmail)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

//        Assert HttpStatus

        final int status = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.NOT_FOUND.value(),status);

//        Create a JSON object with the content returned

        final String content = mvcResult.getResponse().getContentAsString();
        JSONObject contentObject = new JSONObject(content);

//        Assert existence and content of msg

        String msgContent = contentObject.get("message").toString();
        assertTrue(contentObject.has("message"));
//        assertEquals("Second person does not exist", msgContent);

//        Assert existence of errors

        assertTrue(contentObject.has("errors"));

    }


    /*
    @Test
    @DisplayName("Test getMapping | False | Not Siblings")
    public void getMappingCheckIfSiblingsNotSiblings() throws Exception {

        //Arrange

        String uriPersons = "/persons/";
        String uriSiblings = "/siblings/";
        final String personEmail = "paulo@gmail.com";
        final String otherPersonEmail = "henrique@gmail.com";

//        Act
        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                .get(uriPersons + personEmail + uriSiblings + otherPersonEmail)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

//        Assert HttpStatus

        final int status = mvcResult.getResponse().getStatus();
//        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.value(),status);

//        Create a JSON object with the content returned

        final String content = mvcResult.getResponse().getContentAsString();
        JSONObject contentObject = new JSONObject(content);

//        Assert existence and content of msg

        String msgContent = contentObject.get("message").toString();
        assertTrue(contentObject.has("message"));
        assertEquals("Not Siblings", msgContent);

//        Assert existence of errors

        assertTrue(contentObject.has("errors"));

    }

    @Test
    @DisplayName("Test getMapping | False | Not supported method")
    public void getMappingCheckIfSiblingsNotSupportedMethodSiblings() throws Exception {

        //Arrange

        String uriPersons = "/persons/";
        String uriSiblings = "/siblings/";
        final String personEmail = "paulo@gmail.com";
        final String otherPersonEmail = "henrique@gmail.com";

//        Act
        final MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                .post(uriPersons + personEmail + uriSiblings + otherPersonEmail)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

//        Assert HttpStatus

        final int status = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.METHOD_NOT_ALLOWED.value(),status);

//        Create a JSON object with the content returned

        final String content = mvcResult.getResponse().getContentAsString();
        JSONObject contentObject = new JSONObject(content);

//        Assert existence and content of msg

        String msgContent = contentObject.get("message").toString();
        assertTrue(contentObject.has("message"));
        assertEquals("Request method 'POST' not supported", msgContent);

//        Assert existence of errors
        String errorsContent= contentObject.get("errors").toString();
        assertTrue(contentObject.has("errors"));
    }


    //MvcResult - Provides access to the result of an executed request.
//        //----------------------------------------------------------------------------------------------------------------
//        //MockMvcRequestBuilders - extends Object : Static factory methods for RequestBuilders.
//        //----------------------------------------------------------------------------------------------------------------
//        // RequestBuilders - Builds a MockHttpServletRequest
//        //----------------------------------------------------------------------------------------------------------------
//        //MockHttpServletRequest extends Object implements HttpServletRequest : Mock implementation of the
//        // HttpServletRequest interface.
//        //----------------------------------------------------------------------------------------------------------------
//        //HttpServletRequest  extends ServletRequest: to provide request information for HTTP servlets.
//        //The servlet container creates an HttpServletRequest object and passes it as an argument to the servlet's service methods (doGet, doPost, etc).
//
//        //----------------------------------------------------------------------------------------------------------------
//        //ServletRequest - Defines an object to provide client request information to a servlet. The servlet container
//        // creates a ServletRequest object and passes it as an argument to the servlet's service method.
//        //A ServletRequest object provides data including parameter name and values, attributes, and an input stream.
//        // Interfaces that extend ServletRequest can provide additional protocol-specific data (for example, HTTP data is provided by HttpServletRequest.
//        //----------------------------------------------------------------------------------------------------------------
//        //MediaType.APPLICATION_JSON_VALUE - public class MediaType extends MimeType implements Serializable:
//        // A subclass of MimeType that adds support for quality parameters as defined in
//        // the HTTP specification.
//        //public class MimeType extends Object implements Comparable<MimeType>, Serializable:
//        // Represents a MIME Type. Consists of a type and a subtype. Also has functionality to parse MIME Type
//        // values from a String using valueOf(String).
//        //For more parsing options see MimeTypeUtils.


     */
}