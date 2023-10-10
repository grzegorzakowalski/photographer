package com.kowal.photographer.security;

import com.kowal.photographer.entitys.User;
import com.kowal.photographer.repositorys.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service
public class AuthService implements UserDetailsService {
    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        if( user == null){
            throw new UsernameNotFoundException("Nie istnieje użytkownik o loginie:" + username);
        }
        return new CurrentUser(user.getUsername(), user.getPassword(), Set.of(new SimpleGrantedAuthority(user.getRole())), userRepository, user);
    }


}
