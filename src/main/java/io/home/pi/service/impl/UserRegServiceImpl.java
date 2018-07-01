package io.home.pi.service.impl;

import io.home.pi.converter.UserDtoToUserDomComponent;
import io.home.pi.persistence.model.TokenLog;
import io.home.pi.persistence.model.User;
import io.home.pi.persistence.repo.TokenLogRepo;
import io.home.pi.persistence.service.UserService;
import io.home.pi.service.UserRegService;
import io.home.pi.web.dto.UserDTO;
import io.home.pi.web.exception.UserAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * PROJECT   : pi
 * PACKAGE   : io.home.pi.service.impl
 * USER      : sean
 * DATE      : 30-June-2018
 * TIME      : 13:49
 */
@Service
public class UserRegServiceImpl implements UserRegService {
    private final TokenLogRepo tokenLogRepo;
    private UserDtoToUserDomComponent userDtoToUserDomComponent;
    private UserService userService;


    @Autowired
    public UserRegServiceImpl(UserDtoToUserDomComponent userDtoToUserDomComponent, UserService userService, TokenLogRepo tokenLogRepo) {
        this.userDtoToUserDomComponent = userDtoToUserDomComponent;
        this.userService = userService;
        this.tokenLogRepo = tokenLogRepo;
    }

    @Override
    public User registerNewUserAccount(UserDTO userAccount) throws UserAlreadyExistException {
        if (emailExist(userAccount.getUsername())) {
            throw new UserAlreadyExistException("There is an account with that email address: " + userAccount.getUsername());
        }

        final User user = userDtoToUserDomComponent.convert(userAccount);

        return userService.saveOrUpdate(user);
    }

    @Override
    public void createVerificationTokenForUser(final User user, final String token) {
        final TokenLog myToken = new TokenLog(token, user);
        tokenLogRepo.save(myToken);
    }

    private boolean emailExist(final String email) {
        return userService.findByUsername(email) != null;
    }
}
