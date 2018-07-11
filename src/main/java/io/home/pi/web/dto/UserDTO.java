package io.home.pi.web.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.home.pi.validator.PasswordMatch;
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
@PasswordMatch
public class UserDTO {

    @NotNull
    @JsonProperty("firstName")
    private String firstName;

    @ValidEmail
    @NotNull(message = "{NotNull.user.username}")
    @Size(min = 3, max = 45, message = "{Size.userDto.email}")
    @JsonProperty("username")
    private String username;

    @NotNull(message = "{NotNull.user.password}")
    @Size(min = 3, max = 25, message = "{Size.userDto.email}")
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDTO)) return false;

        UserDTO userDTO = (UserDTO) o;

        if (getFirstName() != null ? !getFirstName().equals(userDTO.getFirstName()) : userDTO.getFirstName() != null)
            return false;
        if (getUsername() != null ? !getUsername().equals(userDTO.getUsername()) : userDTO.getUsername() != null)
            return false;
        if (getPass() != null ? !getPass().equals(userDTO.getPass()) : userDTO.getPass() != null) return false;
        return getPass2() != null ? getPass2().equals(userDTO.getPass2()) : userDTO.getPass2() == null;
    }

    @Override
    public int hashCode() {
        int result = getFirstName() != null ? getFirstName().hashCode() : 0;
        result = 31 * result + (getUsername() != null ? getUsername().hashCode() : 0);
        result = 31 * result + (getPass() != null ? getPass().hashCode() : 0);
        result = 31 * result + (getPass2() != null ? getPass2().hashCode() : 0);
        return result;
    }
}
