package io.home.pi.persistence.service.impl;

import io.home.pi.persistence.model.TokenLog;
import io.home.pi.persistence.model.User;
import io.home.pi.persistence.repo.UserRepo;
import io.home.pi.persistence.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * PROJECT   : pi
 * PACKAGE   : io.home.pi.service.impl
 * USER      : sean
 * DATE      : 19-June-2018
 * TIME      : 20:49
 */
@Service
public class UserServiceImpl implements UserService {

    private UserRepo userRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public Optional<User> findByToken(TokenLog token) {
        return userRepo.findByToken(token);
    }

    @Override
    public Iterable<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public Optional<User> findById(Integer id) {
        return userRepo.findById(id);
    }

    @Override
    public void delete(Integer id) {
        userRepo.deleteById(id);
    }

    @Override
    public User saveOrUpdate(User domainObject) {
        return userRepo.save(domainObject);
    }
}
