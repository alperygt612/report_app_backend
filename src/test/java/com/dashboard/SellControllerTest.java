package com.dashboard;

import com.dashboard.Controller.SellController;
import com.dashboard.Model.Sell;
import com.dashboard.Service.SellService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SellControllerTest {
    @Mock
    private SellService sellService;
    @InjectMocks
    private SellController sellController;

    @BeforeEach
    void setUp() { MockitoAnnotations.openMocks(this); }

    @Test
    void testGetAll() {
        when(sellService.getAll(null, null, null)).thenReturn(Collections.singletonList(new Sell()));
        List<Sell> result = sellController.getAll(null, null, null);
        assertEquals(1, result.size());
    }

    @Test
    void testGetById() {
        Sell sell = new Sell();
        when(sellService.getById(1L)).thenReturn(sell);
        assertEquals(sell, sellController.getById(1L));
    }

    @Test
    void testCreate() {
        Sell sell = new Sell();
        when(sellService.save(sell)).thenReturn(sell);
        assertEquals(sell, sellController.create(sell));
    }

    @Test
    void testUpdate_Found() {
        Sell existing = new Sell();
        Sell updated = new Sell();
        when(sellService.getById(1L)).thenReturn(existing);
        when(sellService.save(updated)).thenReturn(updated);
        ResponseEntity<Sell> response = sellController.update(1L, updated);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updated, response.getBody());
    }

    @Test
    void testUpdate_NotFound() {
        when(sellService.getById(1L)).thenReturn(null);
        ResponseEntity<Sell> response = sellController.update(1L, new Sell());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testDelete() {
        ResponseEntity<?> response = sellController.delete(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(sellService, times(1)).delete(1L);
    }
} 