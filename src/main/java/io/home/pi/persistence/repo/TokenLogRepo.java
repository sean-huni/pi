package io.home.pi.persistence.repo;

import io.home.pi.persistence.model.TokenLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
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
public interface TokenLogRepo extends JpaRepository<TokenLog, Integer> {

    TokenLog findBySeries(String series);

    Optional<TokenLog> findByToken(String token);

    Stream<TokenLog> findAllByExpiryLessThan(Timestamp now);

    void deleteByExpiryLessThan(Timestamp now);

    @Modifying
    @Query("delete from TokenLog t where t.expiry <= ?1")
    void deleteAllExpiredSince(Timestamp now);
}
