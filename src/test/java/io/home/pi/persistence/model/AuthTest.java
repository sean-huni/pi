package io.home.pi.persistence.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * PROJECT   : pi
 * PACKAGE   : io.home.pi.persistence.model
 * USER      : sean
 * DATE      : 17-July-2018
 * TIME      : 22:58
 */
class AuthTest {
    private static final Integer AUTH_ID = 1;
    private static final String AUTH_LEVEL = "CLASSIFIED";
    private Auth auth;

    @BeforeEach
    void setUp() {
        auth = new Auth();
    }

    @AfterEach
    void tearDown() {
        auth = null;
    }


    @Test
    public void settersAndGetters() {
        auth.setId(AUTH_ID);
        auth.setLevel(AUTH_LEVEL);

        assert AUTH_LEVEL.equals(auth.getLevel());
        assert AUTH_ID.equals(auth.getId());
    }

    @Test
    public void toStringTest() {
        auth.setId(AUTH_ID);
        auth.setLevel(AUTH_LEVEL);

        assertNotNull(auth.toString());
    }
}