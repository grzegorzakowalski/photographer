package com.kowal.photographer.services;

import com.kowal.photographer.entitys.Pictures;
import com.kowal.photographer.entitys.User;
import com.kowal.photographer.repositorys.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    /**
     * Metoda tworząca tymczasowego użytkownika. Encja timetable musi posiadać użytkownika nie ważne, czy jest się zalogowanym, czy nie.
     * @return użytkownika, który wymaga dodania numeru telefonu i emaila.
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
     * Zwraca listę adminów, którzy nie posiadają powiązania z podanym elementem bazy danych pictures.
     * @param picture element z bazy danych pictures.
     * @return Listę użytkowników z rolą admin bez powiązania z parametrem.
     */
    public List<User> getAdminListWithoutPicture(Pictures picture){
        List<User> roleAdmin = userRepository.findAllByRole("ROLE_ADMIN");
        return roleAdmin.stream()
                .filter(admin -> !admin.getPictures().contains(picture))
                .toList();
    }
}
