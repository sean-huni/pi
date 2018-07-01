package io.home.pi.component.converter;

import io.home.pi.domain.TokenLog;
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
