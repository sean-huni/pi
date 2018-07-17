package io.home.pi.service;

import io.home.pi.persistence.model.TokenLog;
import io.home.pi.persistence.model.User;
import io.home.pi.persistence.service.TokenLogService;
import io.home.pi.persistence.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Optional;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNotNull;
import static org.springframework.test.util.AssertionErrors.assertTrue;

/**
 * PROJECT   : pi
 * PACKAGE   : io.home.pi
 * USER      : sean
 * DATE      : 13-June-2018
 * TIME      : 00:40
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableTransactionManagement
public class UserServiceTest {
    private UserService userService;
    private final String FAKE_TOKEN = "fake-token";
    private final String OG_TEST_TOKEN = "83AjDxYvEBxDgLMJLNzkaA==";
    private TokenLogService tokenLogService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setTokenLogService(TokenLogService tokenLogService) {
        this.tokenLogService = tokenLogService;
    }

    @Test
    public void findByUsernameTest() {
        final String username = "demo@email.com";
        User user = userService.findByUsername(username);

        assertNotNull("User Account doesn't exist", user);
        assertTrue("User Account not Active!", user.getEnabled());
        assertNotNull("User's password must not be null!", Optional.ofNullable(user.getPassword()));
        assertTrue("Username don't match!", username.equals(user.getUsername()));
    }

    /**
     * Test for tokens that don't exist.
     */
    @Test
    public void findByTokenTest() {
        final String nonExistingToken = FAKE_TOKEN;
        Optional<TokenLog> optionalUser = tokenLogService.findByToken(nonExistingToken);

        assertFalse("No user has that token: " + nonExistingToken, optionalUser.isPresent());
    }

    @Test
    public void findExistingTokenTest() {
        Optional<TokenLog> optionalUser = tokenLogService.findByToken(OG_TEST_TOKEN);
        assertTrue("Token not found...", optionalUser.isPresent());
    }

}
