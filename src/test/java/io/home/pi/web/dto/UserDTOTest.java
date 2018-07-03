package io.home.pi.web.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

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
    private String jsonValue;

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
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName("Sean");
        userDTO.setUsername("sean2kay@gmail.com");
        userDTO.setPass("password1");
        userDTO.setPass2("password1");
        jsonValue = new ObjectMapper().writeValueAsString(userDTO);

        System.out.println(jsonValue);
        String jsonStr = "{\"firstName\":\"Sean\",\"username\":\"sean2kay@gmail.com\",\"pass\":\"password1\",\"pass2\":\"password1\"}";

        UserDTO userDTO2 = new ObjectMapper().readValue(jsonStr, UserDTO.class);
        System.out.println(userDTO2.toString());

    }

}
