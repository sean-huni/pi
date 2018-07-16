package io.home.pi.persistence.service;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface CRUDService<T> {
    Iterable<T> findAll();

    Optional<T> findById(Integer id);

    void delete(Integer id);

    T saveOrUpdate(T domainObject);
}