package com.etech.security.jwt;

import com.etech.model.Permissions;
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
                mapToGrantedAuthorities(user.getPermissions()),
                user.getCreateDate()
        );
    }

    private static SimpleGrantedAuthority mapToGrantedAuthorities(Permissions permissions) {
        return new SimpleGrantedAuthority(permissions.getPermission());
    }
}
