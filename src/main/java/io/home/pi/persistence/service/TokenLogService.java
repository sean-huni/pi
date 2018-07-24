package io.home.pi.persistence.service;

import io.home.pi.persistence.model.TokenLog;

import java.util.Optional;

/**
 * PROJECT   : pi
 * PACKAGE   : io.home.pi.service
 * USER      : sean
 * DATE      : 29-June-2018
 * TIME      : 01:21
 */
public interface TokenLogService extends JPAService<TokenLog> {
    Optional<TokenLog> findByUsername(String username);

    Optional<TokenLog> findBySeries(String series);

    Optional<TokenLog> findByTokenLog(String token);

    void deleteByUsername(String username);
}
