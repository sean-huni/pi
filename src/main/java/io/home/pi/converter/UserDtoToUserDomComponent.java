package io.home.pi.component.converter;

import io.home.pi.domain.User;
import io.home.pi.model.UserDTO;

/**
 * PROJECT   : pi
 * PACKAGE   : io.home.pi.converter
 * USER      : sean
 * DATE      : 30-June-2018
 * TIME      : 14:19
 */
public interface UserDtoToUserDomComponent {
    User convert(UserDTO source) throws IllegalArgumentException;
}
