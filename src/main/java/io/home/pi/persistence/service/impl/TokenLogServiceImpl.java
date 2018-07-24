package io.home.pi.persistence.service.impl;

import io.home.pi.persistence.model.TokenLog;
import io.home.pi.persistence.model.User;
import io.home.pi.persistence.repo.TokenLogRepo;
import io.home.pi.persistence.repo.UserRepo;
import io.home.pi.persistence.service.TokenLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


/**
 * PROJECT   : pi
 * PACKAGE   : io.home.pi.service.impl
 * USER      : sean
 * DATE      : 29-June-2018
 * TIME      : 01:22
 */
@Service
public class TokenLogServiceImpl implements TokenLogService {
    private UserRepo userRepo;
    private TokenLogRepo tokenLogRepo;

    @Autowired
    public TokenLogServiceImpl(TokenLogRepo tokenLogRepo, UserRepo userRepo) {
        this.tokenLogRepo = tokenLogRepo;
        this.userRepo = userRepo;
    }

    @Override
    public Optional<TokenLog> findByUsername(String username) {
        if (!Optional.of(username).isPresent()) {
            throw new NullPointerException("Username cannot be null.");
        }

        User user = userRepo.findByUsername(username);
        return Optional.of(user.getTokenLog());
    }

    @Override
    public Optional<TokenLog> findBySeries(String series) {
        return Optional.of(tokenLogRepo.findBySeries(series));
    }

    @Override
    public Optional<TokenLog> findByTokenLog(String token) {
        return tokenLogRepo.findByToken(token);
    }

    @Override
    @Transactional
    public void deleteByUsername(String username) {
        User user = userRepo.findByUsername(username);
        user.setTokenLog(null);
        userRepo.save(user);
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
