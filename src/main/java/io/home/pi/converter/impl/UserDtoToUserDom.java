package io.home.pi.converter.impl;

import io.home.pi.converter.UserDtoToUserDomComponent;
import io.home.pi.persistence.model.User;
import io.home.pi.web.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * PROJECT   : pi
 * PACKAGE   : io.home.pi.converter
 * USER      : sean
 * DATE      : 30-June-2018
 * TIME      : 14:01
 */
@Component
public class UserDtoToUserDom implements Converter<UserDTO, User>, UserDtoToUserDomComponent {

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserDtoToUserDom(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Convert the source object of type {@code S} to target type {@code T}.
     *
     * @param source the source object to convert, which must be an instance of {@code S} (never {@code null})
     * @return the converted object, which must be an instance of {@code T} (potentially {@code null})
     * @throws IllegalArgumentException if the source cannot be converted to the desired target type
     */
    @Override
    public User convert(final UserDTO source) throws IllegalArgumentException {
        final User user = new User();
        user.setName(source.getName());
        user.setUsername(source.getUsername());
        user.setPassword(passwordEncoder.encode(source.getPassword()));

        return user;
    }
}
