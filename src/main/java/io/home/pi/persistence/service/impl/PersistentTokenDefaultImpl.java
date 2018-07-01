package io.home.pi.persistence.service.impl;

import io.home.pi.converter.PersistentTokenToTokenLogComponent;
import io.home.pi.converter.TokenLogToPersistentTokenComponent;
import io.home.pi.persistence.model.TokenLog;
import io.home.pi.persistence.service.TokenLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

/**
 * PACKAGE : io.home.pi.service
 * USER    : Kudzai Sean Huni
 * TIME    : 11:48
 * DATE    : Friday-29-June-2018
 * E-MAIL  : kudzai@tangentsolutions.co.za
 * CELL    : +27-78-683-1982
 */
@Service
public class PersistentTokenDefaultImpl implements PersistentTokenRepository {
    private TokenLogToPersistentTokenComponent persistentToken;
    private TokenLogService tokenLogService;
    private PersistentTokenToTokenLogComponent tokenLogConverter;

    @Autowired
    public PersistentTokenDefaultImpl(PersistentTokenToTokenLogComponent tokenLogConverter, TokenLogService tokenLogService, TokenLogToPersistentTokenComponent persistentToken) {
        this.tokenLogService = tokenLogService;
        this.persistentToken = persistentToken;
        this.tokenLogConverter = tokenLogConverter;
    }

    @Override
    public void createNewToken(PersistentRememberMeToken token) {
        tokenLogService.saveOrUpdate(tokenLogConverter.convert(token));
    }

    @Override
    public void updateToken(String series, String tokenValue, Date lastUsed) {
        tokenLogService.saveOrUpdate(new TokenLog(null, series, tokenValue, new Timestamp(lastUsed.getTime())));
    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        return persistentToken.convert(tokenLogService.findBySeries(seriesId).get());
    }

    @Override
    public void removeUserTokens(String username) {
        tokenLogService.deleteByUsername(username);
    }
}
