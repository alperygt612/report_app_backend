package com.dashboard;

import com.dashboard.Controller.DemandController;
import com.dashboard.Model.Demand;
import com.dashboard.Repository.DemandRepository;
import com.dashboard.Service.MailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DemandControllerTest {
    @Mock
    private DemandRepository demandRepository;
    @Mock
    private MailService javaMailService;
    @InjectMocks
    private DemandController demandController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testReceiveSupportMessage_Success() {
        Demand demand = new Demand();
        demand.setName("Test User");
        demand.setEmail("test@example.com");
        demand.setSubject("Test Subject");
        demand.setMessage("Test message body");

        ResponseEntity<Map<String, String>> response = demandController.receiveSupportMessage(demand, "user1");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("success", response.getBody().get("status"));
        verify(demandRepository, times(1)).save(any(Demand.class));
        verify(javaMailService, times(1)).sendSupportEmail(any(Demand.class));
        assertEquals(1L, demand.getUserId());
    }

    @Test
    void testReceiveSupportMessage_MissingFields() {
        Demand demand = new Demand();
        demand.setName(null); // Eksik alan
        demand.setEmail("test@example.com");
        demand.setSubject("Test Subject");
        demand.setMessage("Test message body");

        ResponseEntity<Map<String, String>> response = demandController.receiveSupportMessage(demand, "user1");

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("error", response.getBody().get("status"));
        verify(demandRepository, never()).save(any());
        verify(javaMailService, never()).sendSupportEmail(any());
    }

    @Test
    void testReceiveSupportMessage_EmailException() {
        Demand demand = new Demand();
        demand.setName("Test User");
        demand.setEmail("test@example.com");
        demand.setSubject("Test Subject");
        demand.setMessage("Test message body");

        doThrow(new RuntimeException("Mail error")).when(javaMailService).sendSupportEmail(any(Demand.class));

        ResponseEntity<Map<String, String>> response = demandController.receiveSupportMessage(demand, "user1");

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("error", response.getBody().get("status"));
        verify(demandRepository, times(1)).save(any(Demand.class));
        verify(javaMailService, times(1)).sendSupportEmail(any(Demand.class));
    }
} 