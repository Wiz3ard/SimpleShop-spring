package me.kamil.simpleshop.auth.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import me.kamil.simpleshop.auth.domain.Role;
import me.kamil.simpleshop.auth.domain.User;
import me.kamil.simpleshop.auth.repository.RoleRepository;
import me.kamil.simpleshop.auth.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @Override
    public void save(User user) {
        if (user == null) {
            return;
        }
        userRepository.save(user);
    }

    @Override
    public User findByUserName(String username) {
        if (username == null) {
            return null;
        }

        User user = userRepository.findByUsername(username);

        return user;
    }

    @Override
    public void register(User user) {

        Role role = roleRepository.findByName("ROLE_USER");
        Set<Role> roles = new HashSet<Role>();
        roles.add(role);

        user.setRoles(roles);

        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        save(user);
    }

}
