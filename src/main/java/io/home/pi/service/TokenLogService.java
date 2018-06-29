package io.home.pi.service;

import io.home.pi.domain.TokenLog;

import java.util.Optional;

/**
 * PROJECT   : pi
 * PACKAGE   : io.home.pi.service
 * USER      : sean
 * DATE      : 29-June-2018
 * TIME      : 01:21
 */
public interface TokenLogService extends CRUDService<TokenLog> {
    Optional<TokenLog> findByUsername(String username);
}
