package com.kowal.photographer.services;

import com.kowal.photographer.entities.User;
import com.kowal.photographer.repositorys.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {
    @Mock
    private PasswordEncoder mockPasswordEncoder;

    @Mock
    private UserRepository mockUserRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void willItReturnCorrectUser(){
        Mockito.when(mockPasswordEncoder.encode("yolo")).thenReturn("yolo");
        User correct = User.builder().firstName("Anon").role("ROLE_TEMP").active(false).password("yolo").pictures(new ArrayList<>()).build();
        assertEquals(correct, userService.getTempUser());
    }

    @Test
    public void givenNullAsPicture_WillReturnAllAdmins(){
        Mockito.when(mockUserRepository.findAllByRole("ROLE_ADMIN")).thenReturn(List.of(User.builder().role("ROLE_ADMIN").build()));
        assertEquals(1, userService.getAdminListWithoutPicture(null).size());
    }
}
