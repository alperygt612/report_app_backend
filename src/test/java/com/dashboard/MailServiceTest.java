package com.dashboard;

import com.dashboard.Service.MailService;
import com.dashboard.Model.Demand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;

import java.lang.reflect.Field;

import static org.mockito.Mockito.*;

class MailServiceTest {
    @Mock
    private JavaMailSender javaMailSender;
    @InjectMocks
    private MailService javaMailService;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        // Dummy mail ayarlarını set et
        Field usernameField = MailService.class.getDeclaredField("mailUsername");
        usernameField.setAccessible(true);
        usernameField.set(javaMailService, "dummy");
        Field passwordField = MailService.class.getDeclaredField("mailPassword");
        passwordField.setAccessible(true);
        passwordField.set(javaMailService, "dummy");
    }

    @Test
    void testSendSupportEmail() {
        Demand demand = new Demand();
        demand.setName("Test");
        demand.setEmail("test@example.com");
        demand.setSubject("Subject");
        demand.setMessage("Message");
        doNothing().when(javaMailSender).send(any(SimpleMailMessage.class));
        javaMailService.sendSupportEmail(demand);
        verify(javaMailSender, times(1)).send(any(SimpleMailMessage.class));
    }
} 