package io.home.pi.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.home.pi.domain.Grp;
import io.home.pi.domain.GrpAuthority;
import io.home.pi.repo.GroupAuthorityRepo;
import io.home.pi.repo.GroupMemberRepo;
import io.home.pi.repo.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static io.home.pi.constant.SpringConstants.USER_ROLE_PREFIX;

/**
 * PROJECT   : pi
 * PACKAGE   : io.home.pi.service.impl
 * USER      : sean
 * DATE      : 19-June-2018
 * TIME      : 21:25
 */

@Service
public class LoginServiceImpl implements UserDetailsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginServiceImpl.class);
    private UserRepo userRepo;
    private GroupMemberRepo groupMemberRepo;
    private GroupAuthorityRepo groupAuthorityRepo;

    @Autowired
    public LoginServiceImpl(UserRepo userRepo, GroupMemberRepo groupMemberRepo, GroupAuthorityRepo groupAuthorityRepo) {
        this.userRepo = userRepo;
        this.groupMemberRepo = groupMemberRepo;
        this.groupAuthorityRepo = groupAuthorityRepo;
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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<io.home.pi.domain.User> userOptional = Optional.ofNullable(userRepo.findByUsername(username));
        if (!userOptional.isPresent()) {
            throw new UsernameNotFoundException("Username & Password doesn't exist!");
        }
        return new User(userOptional.get().getUsername(), userOptional.get().getPassword(), getGrantedAuthorities(userOptional.get()));
    }


    private List<GrantedAuthority> getGrantedAuthorities(io.home.pi.domain.User user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        try {
            List<Grp> grps = groupMemberRepo.findByUsername(user.getUsername());
            List<GrpAuthority> groupAuthorities = new ArrayList<>();

            for (Grp grp : grps) {
                groupAuthorities.addAll(groupAuthorityRepo.findByGroupId(grp.getGrp_name()));
            }

            for (GrpAuthority userAuth : decodeHashMap(groupAuthorities)) {
                LOGGER.info("User Authority: " + userAuth.toString());
                authorities.add(new SimpleGrantedAuthority(USER_ROLE_PREFIX + userAuth.getAuthority()));
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }

        if (authorities != null) {
            LOGGER.info("\n\n*********\nClass: LoginDetailsService.\nMethod: getGrantedAuthorities.\n*********\n\n");
            LOGGER.info("Authorities :" + authorities);
        }

        return authorities;
    }


    private List<GrpAuthority> decodeHashMap(List<GrpAuthority> authorities) {
        List<GrpAuthority> authoritiesArrayList = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        try {
            List<GrpAuthority> groupAuthorities = mapper.convertValue(authorities,
                    new TypeReference<List<GrpAuthority>>() {
                    });

            authoritiesArrayList.addAll(groupAuthorities);
        } catch (IllegalArgumentException ex) {
            LOGGER.error("Cannot fetch groupAuthorities: ", ex);
        }
        return authoritiesArrayList;
    }
}
