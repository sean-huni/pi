package io.home.pi.converter;

import io.home.pi.domain.TokenLog;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;

import java.sql.Timestamp;

/**
 * PACKAGE : io.home.pi.converter
 * USER    : Kudzai Sean Huni
 * TIME    : 11:59
 * DATE    : Friday-29-June-2018
 * E-MAIL  : kudzai@tangentsolutions.co.za
 * CELL    : +27-78-683-1982
 */
public class PersistentTokenToTokenLog implements Converter<PersistentRememberMeToken, TokenLog> {
    /**
     * Convert the source object of type {@code S} to target type {@code T}.
     *
     * @param source the source object to convert, which must be an instance of {@code S} (never {@code null})
     * @return the converted object, which must be an instance of {@code T} (potentially {@code null})
     * @throws IllegalArgumentException if the source cannot be converted to the desired target type
     */
    @Override
    public TokenLog convert(PersistentRememberMeToken source) {
        Timestamp timestamp = new Timestamp(source.getDate().getTime());
        return new TokenLog(source.getUsername(), source.getSeries(), source.getTokenValue(), timestamp);
    }
}
