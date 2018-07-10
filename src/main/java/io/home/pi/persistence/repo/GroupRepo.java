package io.home.pi.persistence.repo;

import io.home.pi.persistence.model.Team;
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
public interface GroupRepo extends CrudRepository<Team, Integer> {

}