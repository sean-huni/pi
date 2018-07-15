package io.home.pi.web.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static org.springframework.test.util.AssertionErrors.assertTrue;

/**
 * PACKAGE : io.home.pi.web.dto
 * USER    : Kudzai Sean Huni
 * TIME    : 13:56
 * DATE    : Tuesday-03-July-2018
 * E-MAIL  : kudzai@tangentsolutions.co.za
 * CELL    : +27-78-683-1982
 */

@Slf4j
public class UserDTOTest {

    @BeforeClass
    public static void init() {
        log.info("userDTO Test...");
    }

    @AfterClass
    public static void cleanup() {
        log.info("userDTO Cleanup...");
    }


    @Test
    public void testJsonUserDTO() throws IOException {
        final String firstName = "Sean";
        final String username = "sean2kay@gmail.com";
        final String password1 = "password1";
        final String password2 = "password2";

        UserDTO userDTO = new UserDTO();

        userDTO.setFirstName(firstName);
        userDTO.setUsername(username);
        userDTO.setPass(password1);
        userDTO.setPass2(password2);

        final String jsonValue = new ObjectMapper().writeValueAsString(userDTO);

        log.debug(jsonValue);
        final String jsonStr = "{\"firstName\":\"" + firstName + "\",\"username\":\"" + username + "\",\"pass\":\"" + password1 + "\",\"pass2\":\"" + password2 + "\"}";

        UserDTO userDTO2 = new ObjectMapper().readValue(jsonStr, UserDTO.class);

        assertTrue("UserDTO Objects-Contents are not Equal!", userDTO.equals(userDTO2));
        assertTrue("UserDTO hash-codes are not Equal!", userDTO.hashCode() == userDTO2.hashCode());
    }
}
