package io.home.pi.repo;

import io.home.pi.domain.TokenLog;
import org.springframework.data.repository.CrudRepository;

/**
 * PROJECT   : pi
 * PACKAGE   : io.home.pi.repo
 * USER      : sean
 * DATE      : 29-June-2018
 * TIME      : 01:19
 */
public interface TokenLogRepo extends CrudRepository<TokenLog, Integer> {
    TokenLog findByUsername(String username);
}
