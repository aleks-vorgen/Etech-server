package com.etech.security.jwt;

import com.etech.model.Permission;
import com.etech.model.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public final class JwtUserFactory {
    public JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                mapToGrantedAuthorities(user.getPermission()),
                user.getCreateDate()
        );
    }

    private static SimpleGrantedAuthority mapToGrantedAuthorities(Permission permission) {
        return new SimpleGrantedAuthority(permission.getPermission());
    }
}
