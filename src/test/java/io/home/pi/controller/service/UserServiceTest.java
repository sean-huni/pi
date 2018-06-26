package io.home.pi.controller.service;

import io.home.pi.domain.User;
import io.home.pi.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;
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
public class UserServiceTest {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Test
    public void dryTest() {
        User user = userService.findByUsername("demo@email.com");

        assertNotNull("User Account doesn't exist", user);
        assertTrue("User Account not Active!", user.getEnabled());
        assertNotNull("User's password must not be null!", user.getPassword());
    }
}
