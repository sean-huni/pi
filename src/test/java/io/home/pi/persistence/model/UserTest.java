package io.home.pi.persistence.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.UUID;

import static java.lang.Boolean.TRUE;

/**
 * PROJECT   : pi
 * PACKAGE   : io.home.pi.persistence.model
 * USER      : sean
 * DATE      : 07-July-2018
 * TIME      : 08:09
 */
public class UserTest {
    private final String name = "Test-Name";
    private final Boolean enabled = TRUE;
    private final String password = new BCryptPasswordEncoder().encode("Random-Password");
    private final String username = "user@test.pi.io";
    private final Integer id = 1;
    private final String uuid = UUID.randomUUID().toString();
    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setName(name);
        user.setUsername(username);
        user.setId(id);
        user.setPassword(password);
        user.setEnabled(enabled);
        user.setToken(new TokenLog(uuid));
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void setId() {
    }
}