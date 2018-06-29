package io.home.pi.service.impl;

import io.home.pi.converter.PersistentTokenToTokenLog;
import io.home.pi.converter.TokenLogToPersistentToken;
import io.home.pi.domain.TokenLog;
import io.home.pi.service.TokenLogService;
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
public class PersistentTokenRepositoryImpl implements PersistentTokenRepository {

    private TokenLogService tokenLogService;

    @Autowired
    public PersistentTokenRepositoryImpl(TokenLogService tokenLogService) {
        this.tokenLogService = tokenLogService;
    }

    @Override
    public void createNewToken(PersistentRememberMeToken token) {
        tokenLogService.saveOrUpdate(new PersistentTokenToTokenLog().convert(token));
    }

    @Override
    public void updateToken(String series, String tokenValue, Date lastUsed) {
        tokenLogService.saveOrUpdate(new TokenLog(null, series, tokenValue, new Timestamp(lastUsed.getTime())));
    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        return new TokenLogToPersistentToken().convert(tokenLogService.findBySeries(seriesId).get());
    }

    @Override
    public void removeUserTokens(String username) {
        tokenLogService.deleteByUsername(username);
    }
}
