package io.home.pi.persistence.repo;

import io.home.pi.persistence.model.GrpAuth;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * PROJECT   : pi
 * PACKAGE   : io.home.pi.repo
 * USER      : sean
 * DATE      : 19-June-2018
 * TIME      : 23:24
 */
public interface GroupAuthorityRepo extends JpaRepository<GrpAuth, Integer> {

}
