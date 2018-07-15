package io.home.pi.persistence.service.impl;

import io.home.pi.persistence.model.Team;
import io.home.pi.persistence.repo.TeamRepo;
import io.home.pi.persistence.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * PROJECT   : pi
 * PACKAGE   : io.home.pi.persistence.service.impl
 * USER      : sean
 * DATE      : 15-July-2018
 * TIME      : 04:03
 */
@Service
public class TeamServiceImpl implements TeamService {
    private TeamRepo teamRepo;

    @Autowired
    public void setTeamRepo(TeamRepo teamRepo) {
        this.teamRepo = teamRepo;
    }

    @Override
    public Iterable<Team> findAll() {
        return teamRepo.findAll();
    }

    @Override
    public Optional<Team> findById(Integer id) {
        return teamRepo.findById(id);
    }

    @Override
    public void delete(Integer id) {
        teamRepo.deleteById(id);
    }

    @Override
    public Team saveOrUpdate(Team domainObject) {
        return teamRepo.save(domainObject);
    }
}
