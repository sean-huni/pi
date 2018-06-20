package io.home.pi.service;

import io.home.pi.domain.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface CRUDService<T> {
    Iterable<?> findAll();

    Optional<User> findById(Integer id);

    void delete(Integer id);

    T saveOrUpdate(T domainObject);
}