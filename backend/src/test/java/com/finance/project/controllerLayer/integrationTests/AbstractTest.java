package com.finance.project.controllerLayer.integrationTests;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.finance.project.ProjectApplication;

import java.io.IOException;

/**
 * @author Ala Matos
 */
@ExtendWith(SpringExtension.class)
//Loads the actual application context
//This annotation helps in writing integration tests. It starts the embedded server and fully
// initializes the application context. We can inject the dependencies in test class using @Autowired annotation.
@SpringBootTest(classes = ProjectApplication.class)
//        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

//@SpringBootTest is saying “bootstrap with Spring Boot’s support” (e.g. load application.properties and give me
// all the Spring Boot goodness)
//----------------------------------------------------------------------------------------------------------------
//SpringBootTest - annotation which starts the embedded server, creates a web environment and then enables @Test
// methods to do integration testing. //Use @SpringBootTest for tests that cover the whole Spring Boot application
// from incoming request to database.
//----------------------------------------------------------------------------------------------------------------
//webEnvironment - to start the server with a random port (useful to avoid conflicts in test environments)
//----------------------------------------------------------------------------------------------------------------
@WebAppConfiguration //-  is a class-level annotation that is used to declare that the ApplicationContext
// loaded for an integration test should be a WebApplicationContext.
//----------------------------------------------------------------------------------------------------------------
public class AbstractTest {
    protected MockMvc mvc;
    //Main entry point for server-side Spring MVC test support.
    //MockMvc is the new addition which does all the magic of injecting the context and use the Fluent API
    // technique for running the integrated tests. In our example, setUp() method configures the MockMvc object
    // for the test execution before any tests are started.
    //----------------------------------------------------------------------------------------------------------------
    @Autowired
    WebApplicationContext webApplicationContext;
    //Interface to provide configuration for a web application. This is read-only while the application is running,
    // but may be reloaded if the implementation supports this.
    //This interface adds a getServletContext() method to the generic ApplicationContext interface, and defines a
    // well-known application attribute name that the root context must be bound to in the bootstrap process.
    //
    //Like generic application contexts, web application contexts are hierarchical. There is a single root context
    // per application, while each servlet in the application (including a dispatcher servlet in the MVC framework)
    // has its own child context.
//----------------------------------------------------------------------------------------------------------------
    protected void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        //Build a MockMvc using the given, fully initialized, i.e. refreshed, WebApplicationContext.
    }

    //ObjectMapper provides functionality for reading and writing JSON, either to and from basic
    // POJOs (Plain Old Java Objects), or to and from a general-purpose JSON Tree Model (JsonNode),
    // as well as related functionality for performing conversions. It is also highly customizable to work
    // both with different styles of JSON content, and to support more advanced Object concepts such as
    // polymorphism and Object identity. ObjectMapper also acts as a factory for more advanced ObjectReader
    // and ObjectWriter classes. Mapper (and ObjectReaders, ObjectWriters it constructs) will use instances of
    // JsonParser and JsonGenerator for implementing actual reading/writing of JSON.
    //----------------------------------------------------------------------------------------------------------------
    //JsonProcessingException - Intermediate base class for all problems encountered when processing (parsing, generating) JSON content
    // that are not pure I/O problems.



    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper= new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    ///The <T> part is declaring a generic type argument T. If you were to omit this part, the compiler
    // would likely complain that the type T doesn't exist.
    //In this case, T serves as a placeholder for an actual type, which will only be determined when the
    // method is actually called with non-generic type arguments.
    //----------------------------------------------------------------------------------------------------------------
    protected <T> T mapFromJson (String json, Class <T> something)
            throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper objectMapper= new ObjectMapper();
        return objectMapper.readValue(json, something);

        //JsonParseException - Exception type for parsing problems, used when non-well-formed content (content that does not conform to
        // JSON syntax as per specification) is encountered.
        //----------------------------------------------------------------------------------------------------------------
        //JsonMappingException - Checked exception used to signal fatal problems with mapping of content.
        //----------------------------------------------------------------------------------------------------------------
        //IOException - Signals that an I/O (INputStream/OutputStream) exception of some sort has occurred. This class is the general class of exceptions produced
        // by failed or interrupted I/O operations.
        //InputStream - This abstract class is the superclass of all classes representing an input stream of bytes.
        //OutputStream - This abstract class is the superclass of all classes representing an output stream of bytes. An output stream
        // accepts output bytes and sends them to some sink.

        //JsonProcessingException extends IOException
        //
        //JsonParseException and JsonMappingException both extend from JsonProcessingException
    }
}

