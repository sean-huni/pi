package io.home.pi.repo;

import io.home.pi.domain.GroupMember;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * PROJECT   : pi
 * PACKAGE   : io.home.pi.repo
 * USER      : sean
 * DATE      : 19-June-2018
 * TIME      : 23:38
 */
public interface GroupMemberRepo extends CrudRepository<GroupMember, Integer> {
    List<GroupMember> findByUsername(String username);
}
