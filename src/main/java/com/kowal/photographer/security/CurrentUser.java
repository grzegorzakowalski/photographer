package com.kowal.photographer.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
@Getter
public class CurrentUser extends User {
    private final com.kowal.photographer.entitys.User user;
    public CurrentUser(String username, String password, Collection<? extends GrantedAuthority> authorities,
                       com.kowal.photographer.entitys.User user) {
        super(username, password, authorities);
        this.user = user;
    }

}
