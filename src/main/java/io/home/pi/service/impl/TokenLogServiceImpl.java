package io.home.pi.service.impl;

import io.home.pi.domain.TokenLog;
import io.home.pi.repo.TokenLogRepo;
import io.home.pi.service.TokenLogService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static junit.framework.TestCase.assertNotNull;

/**
 * PROJECT   : pi
 * PACKAGE   : io.home.pi.service.impl
 * USER      : sean
 * DATE      : 29-June-2018
 * TIME      : 01:22
 */
public class TokenLogServiceImpl implements TokenLogService {

    private TokenLogRepo tokenLogRepo;

    @Autowired
    public TokenLogServiceImpl(TokenLogRepo tokenLogRepo) {
        this.tokenLogRepo = tokenLogRepo;
    }

    @Override
    public Optional<TokenLog> findByUsername(String username) {
        assertNotNull(username);
        return Optional.ofNullable(tokenLogRepo.findByUsername(username));
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
