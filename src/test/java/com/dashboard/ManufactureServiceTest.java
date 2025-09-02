package com.dashboard;

import com.dashboard.Model.Manufacture;
import com.dashboard.Service.ManufactureService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ManufactureServiceTest {
    @Mock
    private com.dashboard.Repository.ManufactureRepository manufactureRepository;
    @InjectMocks
    private ManufactureService manufactureService;

    @BeforeEach
    void setUp() { MockitoAnnotations.openMocks(this); }

    @Test
    void testGetById() {
        Manufacture m = new Manufacture();
        m.setProductionId(1L);
        when(manufactureRepository.findById(1L)).thenReturn(java.util.Optional.of(m));
        Manufacture result = manufactureService.getById(1L);
        assertEquals(1L, result.getProductionId());
    }

    @Test
    void testGetById_NotFound() {
        when(manufactureRepository.findById(2L)).thenReturn(java.util.Optional.empty());
        Manufacture result = manufactureService.getById(2L);
        assertNull(result);
    }
} 