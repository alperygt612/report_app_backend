package com.dashboard;

import com.dashboard.Model.User;
import com.dashboard.Service.UserService;
import com.dashboard.DTO.LoginRequest;
import com.dashboard.Config.PasswordUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {
    @Mock
    private com.dashboard.Repository.UserRepository userRepository;
    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() { MockitoAnnotations.openMocks(this); }

    @Test
    void testLogin_Success() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("testuser");
        loginRequest.setPassword("password");
        User user = new User();
        user.setId(1L);
        user.setUsername("testuser");
        user.setPassword(PasswordUtil.hashPassword("password"));
        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(user));
        ResponseEntity<?> response = userService.login(loginRequest);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testLogin_UserNotFound() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("nouser");
        loginRequest.setPassword("password");
        when(userRepository.findByUsername("nouser")).thenReturn(Optional.empty());
        ResponseEntity<?> response = userService.login(loginRequest);
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    @Test
    void testLogin_WrongPassword() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("testuser");
        loginRequest.setPassword("wrongpass");
        User user = new User();
        user.setId(1L);
        user.setUsername("testuser");
        user.setPassword(PasswordUtil.hashPassword("password"));
        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(user));
        ResponseEntity<?> response = userService.login(loginRequest);
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }
} 