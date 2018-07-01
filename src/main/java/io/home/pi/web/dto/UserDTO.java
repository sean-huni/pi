package io.home.pi.dto;

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
public class UserDTO {

    @NotNull
    private String name;

    @ValidEmail
    @NotNull
    @Size(min = 1, message = "{Size.userDto.email}")
    private String username;

    @NotNull
    private String password;

    @Override
    public String toString() {
        return "UserDTO{" +
                "name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
