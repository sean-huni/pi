package io.home.pi.web.dto;

import io.home.pi.persistence.model.TokenLog;
import io.home.pi.persistence.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Locale;
import java.util.UUID;

import static java.lang.Boolean.TRUE;
import static junit.framework.TestCase.assertEquals;


/**
 * PROJECT   : pi
 * PACKAGE   : io.home.pi.web.dto
 * USER      : sean
 * DATE      : 07-July-2018
 * TIME      : 07:24
 */
@Slf4j
public class OnRegCompleteEventDTOTest {

    @BeforeClass
    public static void setUp() {
        log.info("Initialising {} class...", OnRegCompleteEventDTOTest.class.getSimpleName());
    }

    @AfterClass
    public static void tearDown() {
        log.info("Tearing down {} class...", OnRegCompleteEventDTOTest.class.getSimpleName());
    }

    @Test
    public void onRegCompleteEventDTOTest() {
        final User user = new User();
        final Locale locale = new Locale(Locale.UK.toString());
        final String appUrl = "https://app-test-url-test.pi.io";
        final String name = "Test-Name";
        final Boolean enabled = TRUE;
        final String password = new BCryptPasswordEncoder().encode("Random-Password");
        final String username = "user@test.pi.io";
        final Integer id = 1;
        final String uuid = UUID.randomUUID().toString();

        OnRegCompleteEventDTO onRegCompleteEventDTO;


        user.setName(name);
        user.setEnabled(enabled);
        user.setPassword(password);
        user.setUsername(username);
        user.setId(id);
        final TokenLog tokenLog = new TokenLog(uuid, user);
        user.setToken(tokenLog);

        onRegCompleteEventDTO = new OnRegCompleteEventDTO(user, locale, appUrl);

        assertEquals(locale, onRegCompleteEventDTO.getLocale());
        assertEquals(user.toString(), onRegCompleteEventDTO.getUser().toString());
        assertEquals("App-Url is not equal to the onRegCompleteEventDTO.appUrl.", appUrl, onRegCompleteEventDTO.getAppUrl());
    }
}