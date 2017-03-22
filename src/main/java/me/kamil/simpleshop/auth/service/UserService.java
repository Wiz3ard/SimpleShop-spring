package me.kamil.simpleshop.auth.service;

import me.kamil.simpleshop.auth.domain.User;

public interface UserService {

    void save(User user);

    User findByUserName(String username);

    void register(User user);
}
