package io.home.pi.converter;

import io.home.pi.persistence.model.TokenLog;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;

/**
 * PROJECT   : pi
 * PACKAGE   : io.home.pi.converter
 * USER      : sean
 * DATE      : 30-June-2018
 * TIME      : 14:25
 */
public interface TokenLogToPersistentTokenComponent {
    PersistentRememberMeToken convert(TokenLog source) throws IllegalArgumentException;
}
