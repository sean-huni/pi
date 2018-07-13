package io.home.pi.web.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.*;

/**
 * PROJECT   : pi
 * PACKAGE   : io.home.pi.web.util
 * USER      : sean
 * DATE      : 11-July-2018
 * TIME      : 21:08
 */
@Slf4j
public class GenericResponseTest {

    @BeforeClass
    public static void setUp() {
        log.info("Executing {}...", GenericResponseTest.class.getSimpleName());
    }

    @AfterClass
    public static void tearDown() {
        log.info("Completed {}...", GenericResponseTest.class.getSimpleName());
    }

    @Test
    public void genericRespWith2Args() {
        final String message = "test-message";
        final Boolean isSuccessful = true;
        GenericResponse response = new GenericResponse(message, isSuccessful);

        assertNull("Error-Message is expected to be null.", response.getErrorMsg());
        assertNotNull("Response Message must not be null.", response.getMessage());
        assertTrue("GenericResponse, isSuccess must be true.", response.isSuccess());
        assertTrue(isSuccessful == response.isSuccess());
        assertTrue(message.equals(response.getMessage()));
    }

    @Test
    public void genericRespWith3Args() {
        final String message = "test-message", errorMsg = "test-error";
        final Boolean isSuccessful = false;
        GenericResponse response = new GenericResponse(message, errorMsg, isSuccessful);

        assertNull("Error-Message is expected to be null.", response.getErrorMsg());
        assertNotNull("Response Message must not be null.", response.getMessage());
        assertTrue("GenericResponse, isSuccess must be true.", response.isSuccess());
        assertTrue(isSuccessful == response.isSuccess());
        assertTrue(message.equals(response.getMessage()));
    }

    @Test
    public void genericRespWithErrorsArgs() {
        final String errorMsg = "test-errors-list";
        final Boolean isSuccessful = false;
        ObjectError error1 = new ObjectError("username", "Invalid email.");
        ObjectError error2 = new ObjectError("username", "Invalid email.");
        List<ObjectError> errors = new ArrayList<>();
        errors.add(error1);
        errors.add(error2);

        GenericResponse response = new GenericResponse(errors, errorMsg);

        assertNotNull(response.getMessage());
        assertNotNull("Response Message must not be null.", response.getErrorMsg());
        assertFalse("GenericResponse, isSuccess must be false.", response.isSuccess());
        assertEquals(isSuccessful, response.isSuccess());
        assertEquals(errorMsg, response.getErrorMsg());
    }
}