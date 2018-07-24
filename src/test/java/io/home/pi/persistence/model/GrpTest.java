package io.home.pi.persistence.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * PROJECT   : pi
 * PACKAGE   : io.home.pi.persistence.model
 * USER      : sean
 * DATE      : 18-July-2018
 * TIME      : 00:15
 */
class GrpTest {
    private static final Integer GRP_ID = 130;
    private static final String NAME = "DELTA-FORCE";
    private Grp grp;

    @BeforeEach
    public void setUp() {
        grp = new Grp();
        grp.setId(GRP_ID);
        grp.setName(NAME);
    }

    @AfterEach
    public void tearDown() {
        grp = null;
    }

    @Test
    public void settersAndGetters() {
        assertNotNull(grp);
        assertEquals(GRP_ID, grp.getId());
        assertEquals(NAME, grp.getName());
    }
}