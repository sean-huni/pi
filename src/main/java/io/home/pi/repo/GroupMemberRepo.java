package io.home.pi.repo;

import io.home.pi.domain.GrpMember;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * PROJECT   : pi
 * PACKAGE   : io.home.pi.repo
 * USER      : sean
 * DATE      : 19-June-2018
 * TIME      : 23:38
 */
public interface GroupMemberRepo extends CrudRepository<GrpMember, Integer> {
    List<GrpMember> findByUsername(String username);
}
