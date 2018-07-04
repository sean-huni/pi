package io.home.pi.service;

import io.home.pi.persistence.model.User;
import io.home.pi.web.dto.UserDTO;
import io.home.pi.web.exception.UserAlreadyExistException;

/**
 * PROJECT   : pi
 * PACKAGE   : io.home.pi.service
 * USER      : sean
 * DATE      : 30-June-2018
 * TIME      : 13:58
 */
public interface UserRegService {
    User registerNewUserAccount(UserDTO userAccount) throws UserAlreadyExistException;

    void createVerificationTokenForUser(final User user, final String token);

    String validateVerificationToken(String token);
}
