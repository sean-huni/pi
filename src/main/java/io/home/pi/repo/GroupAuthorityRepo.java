package io.home.pi.repo;

import io.home.pi.domain.GrpAuthority;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * PROJECT   : pi
 * PACKAGE   : io.home.pi.repo
 * USER      : sean
 * DATE      : 19-June-2018
 * TIME      : 23:24
 */
public interface GroupAuthorityRepo extends CrudRepository<GrpAuthority, Integer> {
    List<GrpAuthority> findByGroupId(Integer groupId);
}
