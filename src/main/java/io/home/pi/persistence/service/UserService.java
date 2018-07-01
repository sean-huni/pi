package io.home.pi.persistence.service;

import io.home.pi.persistence.model.User;

/**
 * PROJECT   : pi
 * PACKAGE   : io.home.pi.service
 * USER      : sean
 * DATE      : 19-June-2018
 * TIME      : 20:46
 */

public interface UserService extends CRUDService<User> {
    User findByUsername(String username);
}
