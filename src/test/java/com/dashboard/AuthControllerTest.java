package com.dashboard;

import com.dashboard.Controller.AuthController;
import com.dashboard.DTO.LoginRequest;
import com.dashboard.Service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthControllerTest {
    @Mock
    private UserService userService;
    @InjectMocks
    private AuthController authController;

    @BeforeEach
    void setUp() { MockitoAnnotations.openMocks(this); }

    @Test
    void testLogin() {
        LoginRequest loginRequest = new LoginRequest();
        when(userService.login(any(LoginRequest.class))).thenReturn(ResponseEntity.ok().build());
        ResponseEntity<?> response = authController.login(loginRequest);
        assertNotNull(response);
        verify(userService, times(1)).login(loginRequest);
    }
} 