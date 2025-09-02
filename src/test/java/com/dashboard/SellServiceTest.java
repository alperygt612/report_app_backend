package com.dashboard;

import com.dashboard.Model.Sell;
import com.dashboard.Service.SellService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SellServiceTest {
    @Mock
    private com.dashboard.Repository.SellRepository sellRepository;
    @InjectMocks
    private SellService sellService;

    @BeforeEach
    void setUp() { MockitoAnnotations.openMocks(this); }

    @Test
    void testGetById() {
        Sell s = new Sell();
        s.setProductId(1L);
        when(sellRepository.findById(1L)).thenReturn(java.util.Optional.of(s));
        Sell result = sellService.getById(1L);
        assertEquals(1L, result.getProductId());
    }

    @Test
    void testGetById_NotFound() {
        when(sellRepository.findById(2L)).thenReturn(java.util.Optional.empty());
        Sell result = sellService.getById(2L);
        assertNull(result);
    }
} 