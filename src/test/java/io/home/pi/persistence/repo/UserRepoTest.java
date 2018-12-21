package io.home.pi.persistence.repo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * PROJECT   : pi
 * PACKAGE   : io.home.pi.persistence.repo
 * USER      : sean
 * DATE      : 05-August-2018
 * TIME      : 20:25
 */
@DataJpaTest
@ExtendWith(SpringExtension.class)
public class UserRepoTest {

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void findByUsername() {
    }

    @Test
    public void findByTokenLog() {
    }
}