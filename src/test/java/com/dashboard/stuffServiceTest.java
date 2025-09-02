package com.dashboard;

import com.dashboard.Model.stuff;
import com.dashboard.Service.stuffService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class stuffServiceTest {
    @Mock
    private com.dashboard.Repository.stuffRepository stuffRepository;
    @InjectMocks
    private stuffService stuffService;

    @BeforeEach
    void setUp() { MockitoAnnotations.openMocks(this); }

    @Test
    void testGetById() {
        stuff s = new stuff();
        s.setMaterialsId(1L);
        when(stuffRepository.findById(1L)).thenReturn(java.util.Optional.of(s));
        stuff result = stuffService.getById(1L);
        assertEquals(1L, result.getMaterialsId());
    }

    @Test
    void testGetById_NotFound() {
        when(stuffRepository.findById(2L)).thenReturn(java.util.Optional.empty());
        stuff result = stuffService.getById(2L);
        assertNull(result);
    }
} 