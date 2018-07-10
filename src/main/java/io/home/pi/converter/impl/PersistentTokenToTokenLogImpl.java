package io.home.pi.converter.impl;

import io.home.pi.converter.PersistentTokenToTokenLogComponent;
import io.home.pi.persistence.model.TokenLog;
import io.home.pi.persistence.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

/**
 * PACKAGE : io.home.pi.converter
 * USER    : Kudzai Sean Huni
 * TIME    : 11:59
 * DATE    : Friday-29-June-2018
 * E-MAIL  : kudzai@tangentsolutions.co.za
 * CELL    : +27-78-683-1982
 */
@Component
public class PersistentTokenToTokenLogImpl implements Converter<PersistentRememberMeToken, TokenLog>, PersistentTokenToTokenLogComponent {
    private UserService userService;

    @Autowired
    public PersistentTokenToTokenLogImpl(UserService userService) {
        this.userService = userService;
    }

    /**
     * Convert the source object of type {@code S} to target type {@code T}.
     *
     * @param source the source object to convert, which must be an instance of {@code S} (never {@code null})
     * @return the converted object, which must be an instance of {@code T} (potentially {@code null})
     * @throws IllegalArgumentException if the source cannot be converted to the desired target type
     */
    @Override
    public TokenLog convert(PersistentRememberMeToken source) throws IllegalArgumentException{
        Timestamp timestamp = new Timestamp(source.getDate().getTime());
        TokenLog tokenLog = new TokenLog(source.getUsername(), source.getSeries(), source.getTokenValue(), timestamp);
        tokenLog.setUser(userService.findByUsername(source.getUsername()));
        return tokenLog;
    }
}
