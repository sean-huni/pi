package io.home.pi.persistence.repo;

import io.home.pi.persistence.model.TokenLog;
import io.home.pi.persistence.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * PROJECT   : pi
 * PACKAGE   : io.home.pi.repo
 * USER      : sean
 * DATE      : 19-June-2018
 * TIME      : 20:16
 */
@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    User findByUsername(String username);

    Optional<User> findByToken(TokenLog tokenLog);
}
