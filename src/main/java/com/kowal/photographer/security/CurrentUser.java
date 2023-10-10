package com.kowal.photographer.security;

import com.kowal.photographer.repositorys.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CurrentUser extends User {
    private final UserRepository userRepository;
    private final com.kowal.photographer.entitys.User user;
    public CurrentUser(String username, String password, Collection<? extends GrantedAuthority> authorities,
                       UserRepository userRepository, com.kowal.photographer.entitys.User user) {
        super(username, password, authorities);
        this.userRepository = userRepository;
        this.user = user;
    }

    public com.kowal.photographer.entitys.User getUser() {
        return userRepository.getWithPicturesByUsername(user.getUsername());
    }
}
