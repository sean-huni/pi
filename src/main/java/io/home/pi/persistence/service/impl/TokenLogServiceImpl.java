package io.home.pi.persistence.impl;

import io.home.pi.domain.TokenLog;
import io.home.pi.persistence.service.TokenLogService;
import io.home.pi.persistence.repo.TokenLogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static junit.framework.TestCase.assertNotNull;

/**
 * PROJECT   : pi
 * PACKAGE   : io.home.pi.service.impl
 * USER      : sean
 * DATE      : 29-June-2018
 * TIME      : 01:22
 */
@Service
public class TokenLogServiceImpl implements TokenLogService {

    private TokenLogRepo tokenLogRepo;

    @Autowired
    public TokenLogServiceImpl(TokenLogRepo tokenLogRepo) {
        this.tokenLogRepo = tokenLogRepo;
    }

    @Override
    public Optional<TokenLog> findByUsername(String username) {
        assertNotNull(username);
        return Optional.of(tokenLogRepo.findByUsername(username));
    }

    @Override
    public Optional<TokenLog> findBySeries(String series) {
        return Optional.of(tokenLogRepo.findBySeries(series));
    }

    @Override
    @Transactional
    public void deleteByUsername(String username) {
        tokenLogRepo.deleteByUsername(username);
    }

    @Override
    public Iterable<TokenLog> findAll() {
        return tokenLogRepo.findAll();
    }

    @Override
    public Optional<TokenLog> findById(Integer id) {
        return tokenLogRepo.findById(id);
    }

    @Override
    public void delete(Integer id) {
        tokenLogRepo.deleteById(id);
    }

    @Override
    public TokenLog saveOrUpdate(TokenLog domainObject) {
        return tokenLogRepo.save(domainObject);
    }
}
