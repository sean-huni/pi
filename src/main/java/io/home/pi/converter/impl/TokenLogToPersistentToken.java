package io.home.pi.component.converter.impl;

import io.home.pi.component.converter.TokenLogToPersistentTokenComponent;
import io.home.pi.domain.TokenLog;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.stereotype.Component;

/**
 * PACKAGE : io.home.pi.converter
 * USER    : Kudzai Sean Huni
 * TIME    : 11:55
 * DATE    : Friday-29-June-2018
 * E-MAIL  : kudzai@tangentsolutions.co.za
 * CELL    : +27-78-683-1982
 */
@Component
public class TokenLogToPersistentToken implements Converter<TokenLog, PersistentRememberMeToken>, TokenLogToPersistentTokenComponent {
    /**
     * Convert the source object of type {@code S} to target type {@code T}.
     *
     * @param source the source object to convert, which must be an instance of {@code S} (never {@code null})
     * @return the converted object, which must be an instance of {@code T} (potentially {@code null})
     * @throws IllegalArgumentException if the source cannot be converted to the desired target type
     */
    @Override
    public PersistentRememberMeToken convert(TokenLog source) throws IllegalArgumentException{
        return new PersistentRememberMeToken(source.getUsername(), source.getSeries(), source.getToken(), source.getTimestamp());
    }
}
