package io.home.pi.repo;

import io.home.pi.domain.Grp;
import org.springframework.data.repository.CrudRepository;

/**
 * PROJECT   : pi
 * PACKAGE   : io.home.pi.repo
 * USER      : sean
 * DATE      : 19-June-2018
 * TIME      : 23:38
 */
public interface GroupMemberRepo extends CrudRepository<Grp, Integer> {

}
