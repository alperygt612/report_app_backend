package com.dashboard;

import com.dashboard.Controller.ManufactureController;
import com.dashboard.Model.Manufacture;
import com.dashboard.Service.ManufactureService;
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

class ManufactureControllerTest {
    @Mock
    private ManufactureService manufactureService;
    @InjectMocks
    private ManufactureController manufactureController;

    @BeforeEach
    void setUp() { MockitoAnnotations.openMocks(this); }

    @Test
    void testGetAll() {
        when(manufactureService.getAll(null, null, null)).thenReturn(Collections.singletonList(new Manufacture()));
        ResponseEntity<List<Manufacture>> response = manufactureController.getAll(null, null, null);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void testGetById() {
        Manufacture m = new Manufacture();
        when(manufactureService.getById(1L)).thenReturn(m);
        assertEquals(m, manufactureController.getById(1L));
    }

    @Test
    void testCreate() {
        Manufacture m = new Manufacture();
        when(manufactureService.save(m)).thenReturn(m);
        assertEquals(m, manufactureController.create(m));
    }

    @Test
    void testUpdate_Found() {
        Manufacture existing = new Manufacture();
        Manufacture updated = new Manufacture();
        when(manufactureService.getById(1L)).thenReturn(existing);
        when(manufactureService.save(updated)).thenReturn(updated);
        ResponseEntity<Manufacture> response = manufactureController.update(1L, updated);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updated, response.getBody());
    }

    @Test
    void testUpdate_NotFound() {
        when(manufactureService.getById(1L)).thenReturn(null);
        ResponseEntity<Manufacture> response = manufactureController.update(1L, new Manufacture());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testDelete() {
        ResponseEntity<?> response = manufactureController.delete(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(manufactureService, times(1)).delete(1L);
    }
} 