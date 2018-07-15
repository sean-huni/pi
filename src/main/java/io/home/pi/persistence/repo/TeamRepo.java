package io.home.pi.persistence.repo;

import io.home.pi.persistence.model.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * PROJECT   : pi
 * PACKAGE   : io.home.pi.persistence.repo
 * USER      : sean
 * DATE      : 15-July-2018
 * TIME      : 04:00
 */
@Repository
public interface TeamRepo extends CrudRepository<Team, Integer> {
}
