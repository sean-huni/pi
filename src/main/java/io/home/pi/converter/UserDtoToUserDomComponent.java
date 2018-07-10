package io.home.pi.converter;

import io.home.pi.persistence.model.User;
import io.home.pi.web.dto.UserDTO;

/**
 * PROJECT   : pi
 * PACKAGE   : io.home.pi.converter
 * USER      : sean
 * DATE      : 30-June-2018
 * TIME      : 14:19
 */
public interface UserDtoToUserDomComponent {
    User convert(final UserDTO source) throws IllegalArgumentException;
}
