package io.home.pi.persistence.repo;

import io.home.pi.persistence.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * PROJECT   : pi
 * PACKAGE   : io.home.pi.repo
 * USER      : sean
 * DATE      : 19-June-2018
 * TIME      : 20:16
 */
@Repository
public interface UserRepo extends CrudRepository<User, Integer> {
    User findByUsername(String username);
}
