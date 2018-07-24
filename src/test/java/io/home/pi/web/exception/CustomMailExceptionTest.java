package io.home.pi.web.exception;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * PROJECT   : pi
 * PACKAGE   : io.home.pi.web.exception
 * USER      : sean
 * DATE      : 24-July-2018
 * TIME      : 20:54
 */

@Slf4j
public class CustomMailExceptionTest {

    @BeforeEach
    public void setUp() {
        log.info("Initialising Exception testing...");
    }

    @AfterEach
    public void tearDown() {
        log.info("Tearing-down Exception testing...");
    }

    @DisplayName("Testing the custom mail exception with a constructor with a single parameter.")
    @Test
    public void customMailException() {
        Executable cme = () -> {
            throw new CustomMailException("Host unreachable.");
        };

        assertThrows(CustomMailException.class, cme, "Host unreachable.");
    }


    @DisplayName("Testing the custom mail exception with two parameters in constructor.")
    @Test
    public void customMailConstructorWith2ParamsException() {
        Executable cme = () -> {
            throw new CustomMailException(null, new Throwable("Host unreachable."));
        };

        assertThrows(CustomMailException.class, cme, "Host unreachable.");
    }
}