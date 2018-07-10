package io.home.pi.service.impl;

import io.home.pi.persistence.model.GrpAuth;
import io.home.pi.persistence.model.Team;
import io.home.pi.persistence.model.User;
import io.home.pi.persistence.service.UserService;
import io.home.pi.service.UserAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static io.home.pi.constant.SpringConstants.AUTHORITY_USER;
import static io.home.pi.constant.SpringConstants.USER_ROLE_PREFIX;
import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

/**
 * PROJECT   : pi
 * PACKAGE   : io.home.pi.service.impl
 * USER      : sean
 * DATE      : 19-June-2018
 * TIME      : 21:25
 */

@Service
public class UserDetailServiceImpl implements UserDetailsService, UserAuthService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailServiceImpl.class);
    private final UserService userService;
    private HttpServletRequest request;
    private LoginAttemptService loginAttemptService;
    private Boolean isNewUser = false;

    @Autowired
    public UserDetailServiceImpl(HttpServletRequest request, LoginAttemptService loginAttemptService, UserService userService) {
        this.request = request;
        this.loginAttemptService = loginAttemptService;
        this.userService = userService;
    }

    /**
     * Locates the user based on the username. In the actual implementation, the search
     * may possibly be case sensitive, or case insensitive depending on how the
     * implementation instance is configured. In this case, the <code>UserDetails</code>
     * object that comes back may have a username that is of a different case than what
     * was actually requested..
     *
     * @param username the username identifying the user whose data is required.
     * @return a fully populated user record (never <code>null</code>)
     * @throws UsernameNotFoundException if the user could not be found or the user has no
     *                                   GrantedAuthority
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, SecurityException {
        List<GrantedAuthority> authorities;
        String ip = getClientIP(request);
        if (loginAttemptService.isBlocked(ip)) {
            throw new SecurityException("blocked");
        }

        Optional<User> userOptional = Optional.ofNullable(userService.findByUsername(username));

        if (!userOptional.isPresent()) {
            throw new UsernameNotFoundException("Username & Password doesn't exist!");
        }

        if (isNewUser) {
            authorities = authorizeNewUser();
        } else authorities = getGrantedAuthorities(userOptional.get());

        return new org.springframework.security.core.userdetails.User(userOptional.get().getUsername(), userOptional.get().getPassword(), authorities);
    }


    public void authWithoutPassword(String username, HttpSession session) {
        isNewUser = true;
        UserDetails userDetails = loadUserByUsername(username);

        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails, userDetails.getAuthorities());

        //ToDo: Test when the user is already registered.
        ((UsernamePasswordAuthenticationToken) authenticationToken).eraseCredentials();

        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authenticationToken);
        session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, securityContext);
    }

    /**
     * Returns a {@link List<GrantedAuthority>} of authorities that the principal belongs to.
     *
     * @param user
     * @return
     */
    private List<GrantedAuthority> getGrantedAuthorities(User user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        try {
            Optional<User> optionalUser = Optional.of(user);
            List<GrpAuth> groupAuthorities = new ArrayList<>();

            Optional<GrpAuth> optionalGrpAuth = optionalUser.map(User::getTeam).filter(Objects::nonNull).map(Team::getGrpAuth);
            optionalGrpAuth.ifPresent(groupAuthorities::add);

            for (GrpAuth userAuth : groupAuthorities) {
                LOGGER.info("User Auth: " + userAuth.toString());
                authorities.add(new SimpleGrantedAuthority(USER_ROLE_PREFIX + userAuth.getAuthorities().iterator().next().getLevel()));
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }

        authorityLog(authorities);

        return authorities;
    }

    private List<GrantedAuthority> authorizeNewUser() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(USER_ROLE_PREFIX + AUTHORITY_USER));

        return authorities;
    }

    private void authorityLog(List<GrantedAuthority> authorities) {
        if (null != authorities) {
            LOGGER.info("\n\n*********\nClass: LoginDetailsService.\nMethod: Granted-Authorities.\n*********\n\n");
            LOGGER.info("Authorities :" + authorities);
        }
    }

    private String getClientIP(HttpServletRequest request) {
        String clientIP;
        String xfHeader = request.getHeader("X-Fowarded-For");
        if (xfHeader == null) {
            String remoteIP = request.getRemoteAddr();
            LOGGER.info("Remote-IP: {}", remoteIP);
            return remoteIP;
        }

        clientIP = xfHeader.split(",")[0];
        LOGGER.info("Client-IP: {}", clientIP);
        return clientIP;
    }
}
