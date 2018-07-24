package io.home.pi.persistence.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static java.lang.Boolean.TRUE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * PROJECT   : pi
 * PACKAGE   : io.home.pi.persistence.model
 * USER      : sean
 * DATE      : 07-July-2018
 * TIME      : 08:09
 */
public class UserTest {
    private static final String TEST_NAME = "Test-Name-TW0";
    private static final Boolean ENABLED = TRUE;
    private static final String PASSWORD = new BCryptPasswordEncoder().encode("Random-Password-THR33");
    private static final String USERNAME = "user505@test.pi.io";
    private static final Integer ID = 909;
    private static final String RANDOM_UUID = UUID.randomUUID().toString();
    private static final Timestamp LAST_UPDATED = new Timestamp(Instant.now().toEpochMilli());
    private static final Integer TEAM_ID = 101;
    private static final Integer FK_AUTH = 105;
    private static final Integer FK_GRP = 107;
    private static final Integer AUTH_ID = 707;
    private static final String AUTH_LEVEL = "CLASSIFIED";
    private static final Integer GRP_ID = 197;
    private static final String GRP_NAME = "DELTA-FORCE";
    private static final TokenLog TOKEN_LOG  = new TokenLog(RANDOM_UUID);
    private User user;
    private Team youngTeam;

    @BeforeEach
    public void setUp() {
        youngTeam = new Team();
        user = new User();
        Grp grp = new Grp();

        grp.setId(GRP_ID);
        grp.setName(GRP_NAME);

        Set<Grp> grpSet = new HashSet<>();
        grpSet.add(grp);

        Auth auth = new Auth();
        auth.setId(AUTH_ID);
        auth.setLevel(AUTH_LEVEL);
        Set<Auth> authSet = new HashSet<>();
        authSet.add(auth);

        youngTeam.setId(TEAM_ID);
        youngTeam.setFk_auth_id(FK_AUTH);
        youngTeam.setFk_grp_id(FK_GRP);
        youngTeam.setAuthorities(authSet);
        youngTeam.setGrps(grpSet);

        user.setName(TEST_NAME);
        user.setUsername(USERNAME);
        user.setId(ID);
        user.setPassword(PASSWORD);
        user.setEnabled(ENABLED);
        user.setTokenLog(TOKEN_LOG);
        user.setLastUpdated(LAST_UPDATED);
        user.setTeam(youngTeam);
    }

    @AfterEach
    public void tearDown() {
        user = null;
        youngTeam = null;
    }

    @Test
    public void userGettersAndSettersIntegrityTest() {
        assertNotNull(user);
        assertEquals(user.getName(), TEST_NAME);
        assertEquals(user.getUsername(), USERNAME);
        assertEquals(user.getPassword(), PASSWORD);
        assertEquals(user.getEnabled(), ENABLED);
        assertEquals(user.getId(), ID);
        assertEquals(user.getLastUpdated(), LAST_UPDATED);
        assertEquals(user.getTokenLog(), TOKEN_LOG);
        assertEquals(user.getTeam().hashCode(), youngTeam.hashCode());
    }
}