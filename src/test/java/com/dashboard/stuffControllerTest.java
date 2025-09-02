package com.dashboard;

import com.dashboard.Controller.stuffController;
import com.dashboard.Model.stuff;
import com.dashboard.Service.stuffService;
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

class stuffControllerTest {
    @Mock
    private stuffService stuffService;
    @InjectMocks
    private stuffController stuffController;

    @BeforeEach
    void setUp() { MockitoAnnotations.openMocks(this); }

    @Test
    void testGetAll() {
        when(stuffService.getAll(null, null, null)).thenReturn(Collections.singletonList(new stuff()));
        List<stuff> result = stuffController.getAll(null, null, null);
        assertEquals(1, result.size());
    }

    @Test
    void testGetById() {
        stuff s = new stuff();
        when(stuffService.getById(1L)).thenReturn(s);
        assertEquals(s, stuffController.getById(1L));
    }

    @Test
    void testCreate() {
        stuff s = new stuff();
        when(stuffService.save(s)).thenReturn(s);
        assertEquals(s, stuffController.create(s));
    }

    @Test
    void testUpdate_Found() {
        stuff existing = new stuff();
        stuff updated = new stuff();
        when(stuffService.getById(1L)).thenReturn(existing);
        when(stuffService.save(updated)).thenReturn(updated);
        ResponseEntity<stuff> response = stuffController.update(1L, updated);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updated, response.getBody());
    }

    @Test
    void testUpdate_NotFound() {
        when(stuffService.getById(1L)).thenReturn(null);
        ResponseEntity<stuff> response = stuffController.update(1L, new stuff());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testDelete() {
        ResponseEntity<?> response = stuffController.delete(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(stuffService, times(1)).delete(1L);
    }
} 