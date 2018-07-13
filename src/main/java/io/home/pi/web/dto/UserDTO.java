package io.home.pi.web.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.home.pi.validator.PasswordMatch;
import io.home.pi.validator.ValidEmail;
import io.home.pi.validator.ValidPassword;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Locale;

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
    private MessageSource messageSource;
    final String constant = messageSource.getMessage("NotNull.user.firstName", null, Locale.UK);


    @NotNull(message = "NotNull.user.firstName")
    @JsonProperty("firstName")
    @Size(message = "Size.userDto.firstName.min", min = 3)
    @Size(message = "Size.userDto.firstName.max", max = 25)
    private String firstName;
    @ValidEmail
    @NotNull(message = "NotNull.user.username")
    @Size(min = 3, message = "Size.userDto.email.min")
    @Size(max = 50, message = "Size.userDto.email.max")
    @JsonProperty("username")
    private String username;
    @NotNull(message = "NotNull.user.password")
    @Size(min = 8, message = "size.userDto.password.min")
    @Size(max = 48, message = "size.userDto.password.max")
    @JsonProperty("pass")
    @ValidPassword
    private String pass;
    @NotNull(message = "NotNull.user.matchingPassword")
    @JsonProperty("pass2")
    private String pass2;

    @Autowired
    @Qualifier("english")
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

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
