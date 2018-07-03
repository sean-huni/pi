package io.home.pi.web.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.home.pi.validator.ValidEmail;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * PROJECT   : pi
 * PACKAGE   : io.home.pi.model
 * USER      : sean
 * DATE      : 30-June-2018
 * TIME      : 13:00
 * DESCR     : User registration model.
 */
@Getter
@Setter
@NoArgsConstructor
@JsonDeserialize
@JsonRootName(value = "userDTO")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class UserDTO {

    @NotNull
    @JsonProperty("firstName")
    private String firstName;

    @ValidEmail
    @NotNull
    @Size(min = 1, message = "{Size.userDto.email}")
    @JsonProperty("username")
    private String username;

    @NotNull
    @JsonProperty("pass")
    private String pass;

    @NotNull
    @JsonProperty("pass2")
    private String pass2;

    @Override
    public String toString() {
        return "UserDTO{" +
                "firstName='" + firstName + '\'' +
                ", username='" + username + '\'' +
                ", pass='" + pass + '\'' +
                ", pass2='" + pass2 + '\'' +
                '}';
    }
}
