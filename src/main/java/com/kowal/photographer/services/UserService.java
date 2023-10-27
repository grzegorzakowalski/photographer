package com.kowal.photographer.services;

import com.kowal.photographer.entities.Pictures;
import com.kowal.photographer.entities.User;
import com.kowal.photographer.repositorys.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    /**
     * Returns temp user to farther link with timetable.
     * @return user who is almost ready to save just needs phone number and email to be set.
     */
    public User getTempUser(){
        User user = new User();
        user.setFirstName("Anon");
        user.setRole("ROLE_TEMP");
        user.setActive(false);
        user.setPassword(passwordEncoder.encode("yolo"));
        return user;
    }

    /**
     * Returns list of admins who don't have relation with given picture
     * @param picture to exclude admins.
     * @return list of USER_ADMIN who aren't related to param.
     */
    public List<User> getAdminListWithoutPicture(Pictures picture){
        List<User> roleAdmin = userRepository.findAllByRole("ROLE_ADMIN");
        return roleAdmin.stream()
                .filter(admin -> !admin.getPictures().contains(picture))
                .toList();
    }
}
