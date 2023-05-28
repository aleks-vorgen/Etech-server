package com.etech.service;

import com.etech.model.User;
import com.etech.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    public List<User> getAll() {
        return this.repository.findAll();
    }

    public User getByLogin(String login) {
        return (User) this.repository.findByEmail(login);
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User u = getByLogin(login);
        if (Objects.isNull(u)) {
            throw new UsernameNotFoundException(String.format("User %s is not found", login));
        }
        System.out.println(Collections.singleton(new SimpleGrantedAuthority(u.getPermissions()))); //TODO убрать строчку после дебага
        return new org.springframework.security.core.userdetails.User(
                u.getUsername(), u.getPassword(), true, true,
                true, true,
                new HashSet<>(Collections.singleton(new SimpleGrantedAuthority(u.getPermissions())))
        );
    }
}
