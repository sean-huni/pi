package io.home.pi.persistence.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * PROJECT   : pi
 * PACKAGE   : io.home.pi.persistence.model
 * USER      : sean
 * DATE      : 18-July-2018
 * TIME      : 00:23
 */
class TeamTest {
    private static final Integer ID = 101;
    private static final Integer FK_AUTH = 102;
    private static final Integer FK_GRP = 103;
    private static final Integer AUTH_ID = 1;
    private static final String AUTH_LEVEL = "CLASSIFIED";
    private static final Integer GRP_ID = 130;
    private static final String GRP_NAME = "DELTA-FORCE";

    private Team team;

    @BeforeEach
    void setUp() {
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

        team = new Team();
        team.setId(ID);
        team.setFk_auth_id(FK_AUTH);
        team.setFk_grp_id(FK_GRP);
        team.setAuthorities(authSet);
        team.setGrps(grpSet);
    }

    @AfterEach
    void tearDown() {
        team = null;
    }

    @Test
    void getterAndSetters() {
        assertEquals(ID, team.getId());
        assertEquals(FK_AUTH, team.getFk_auth_id());
        assertEquals(FK_GRP, team.getFk_grp_id());
    }
}