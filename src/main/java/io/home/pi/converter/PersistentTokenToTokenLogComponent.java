package io.home.pi.component.converter;

import io.home.pi.domain.TokenLog;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;

/**
 * PROJECT   : pi
 * PACKAGE   : io.home.pi.converter
 * USER      : sean
 * DATE      : 30-June-2018
 * TIME      : 14:32
 */
public interface PersistentTokenToTokenLogComponent {
    /**
     * Convert the source object of type {@code S} to target type {@code T}.
     *
     * @param source the source object to convert, which must be an instance of {@code S} (never {@code null})
     * @return the converted object, which must be an instance of {@code T} (potentially {@code null})
     * @throws IllegalArgumentException if the source cannot be converted to the desired target type
     */
    TokenLog convert(PersistentRememberMeToken source) throws IllegalArgumentException;
}
