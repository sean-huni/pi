package io.home.pi.persistence.repo;

import io.home.pi.persistence.model.TokenLog;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.stream.Stream;

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

    Optional<TokenLog> findByToken(String token);

    void deleteByUsername(String username);

    Stream<TokenLog> findAllByExpiryDateLessThan(Timestamp now);

    void deleteByExpiryDateLessThan(Timestamp now);

    @Modifying
    @Query("delete from TokenLog t where t.expiryDate <= ?1")
    void deleteAllExpiredSince(Timestamp now);
}
