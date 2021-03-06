package io.home.pi.service.impl;

import io.home.pi.converter.UserDtoToUserDomComponent;
import io.home.pi.persistence.model.Team;
import io.home.pi.persistence.model.TokenLog;
import io.home.pi.persistence.model.User;
import io.home.pi.persistence.repo.TokenLogRepo;
import io.home.pi.persistence.service.TeamService;
import io.home.pi.persistence.service.UserService;
import io.home.pi.service.UserRegService;
import io.home.pi.web.dto.UserDTO;
import io.home.pi.web.exception.UserAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Optional;

import static java.lang.Boolean.FALSE;

/**
 * PROJECT   : pi
 * PACKAGE   : io.home.pi.service.impl
 * USER      : sean
 * DATE      : 30-June-2018
 * TIME      : 13:49
 */
@Service
public class UserRegServiceImpl implements UserRegService {
    private static final String TOKEN_INVALID = "invalidToken";
    private static final String TOKEN_EXPIRED = "expired";
    private static final String TOKEN_VALID = "valid";
    private static final Integer DEFAULT_USER_PROFILE_ID = 1;

    private TeamService teamService;
    private TokenLogRepo tokenLogRepo;
    private UserDtoToUserDomComponent userDtoToUserDomComponent;
    private UserService userService;


    @Autowired
    public UserRegServiceImpl(UserDtoToUserDomComponent userDtoToUserDomComponent, UserService userService, TokenLogRepo tokenLogRepo) {
        this.userDtoToUserDomComponent = userDtoToUserDomComponent;
        this.userService = userService;
        this.tokenLogRepo = tokenLogRepo;
    }

    @Autowired
    public void setTeamService(TeamService teamService) {
        this.teamService = teamService;
    }

    @Override
    public User registerNewUserAccount(UserDTO userAccount) throws UserAlreadyExistException {
        if (emailExist(userAccount.getUsername())) {
            throw new UserAlreadyExistException("There is an account with that email address: " + userAccount.getUsername());
        }

        User user = userDtoToUserDomComponent.convert(userAccount);
        Optional<Team> team = (Optional<Team>) teamService.findById(DEFAULT_USER_PROFILE_ID);
        team.ifPresent(user::setTeam);

        return userService.saveOrUpdate(user);
    }

    /**
     * Sets the account-state to Disabled & saves the new User-Account.
     *
     * @param user  {@link User} to be saved.
     * @param token {@link String} token generated by the system.
     * @return Saved {@link User} object.
     */
    @Override
    public User createVerificationTokenForUser(final User user, final String token) {
        user.setEnabled(FALSE); //set the User account to disabled.
        final TokenLog myToken = new TokenLog(token, user);

        user.setEnabled(FALSE);
        user.setToken(myToken);
        return userService.saveOrUpdate(user);
    }

    @Override
    public String validateVerificationToken(String token) {
        final Optional<TokenLog> verifyToken = tokenLogRepo.findByToken(token);
        if (!verifyToken.isPresent()) {
            return TOKEN_INVALID;
        }

        final User user = verifyToken.map(TokenLog::getUser).orElse(new User());
        final Calendar cal = Calendar.getInstance();
        if ((verifyToken.get().getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            tokenLogRepo.delete(verifyToken.get());
            return TOKEN_EXPIRED;
        }

        user.setEnabled(true);
        // tokenRepository.delete(verifyToken);
        userService.saveOrUpdate(user);
        return TOKEN_VALID;
    }

    private boolean emailExist(final String email) {
        return userService.findByUsername(email) != null;
    }
}
