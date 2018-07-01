package io.home.pi.repo;

import io.home.pi.domain.TokenLog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * PROJECT   : pi
 * PACKAGE   : io.home.pi.repo
 * USER      : sean
 * DATE      : 29-June-2018
 * TIME      : 01:19
 */
@Repository
public interface TokenLogRepo extends CrudRepository<TokenLog, Integer> {
    TokenLog findByUsername(String username);

    TokenLog findBySeries(String series);

    void deleteByUsername(String username);
}
