package com.tech.zootech.security.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.tech.zootech.security.DTO.DroidDto;
import com.tech.zootech.security.DTO.UnicornDto;
import com.tech.zootech.security.repo.DroidRepo;
import com.tech.zootech.security.repo.UnicornRepo;
import com.tech.zootech.security.service.implementations.DroidServiceImpl;
import com.tech.zootech.security.utils.mapper.DroidMapper;
import com.tech.zootech.security.utils.mapper.UnicornMapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.Ignore;

import org.junit.Test;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;

public class DroidResourceTest {
    /**
     * Method under test: {@link DroidResource#getDroidByUnicorn(UnicornDto)}
     */
    @Test
    public void testGetDroidByUnicorn2() {
        DroidServiceImpl droidServiceImpl = mock(DroidServiceImpl.class);
        when(droidServiceImpl.getDroidByUnicorn((UnicornDto) any())).thenReturn(new DroidDto());
        DroidResource droidResource = new DroidResource(droidServiceImpl);
        ResponseEntity<DroidDto> actualDroidByUnicorn = droidResource.getDroidByUnicorn(new UnicornDto());
        assertTrue(actualDroidByUnicorn.hasBody());
        assertTrue(actualDroidByUnicorn.getHeaders().isEmpty());
        assertEquals(200, actualDroidByUnicorn.getStatusCodeValue());
        verify(droidServiceImpl).getDroidByUnicorn((UnicornDto) any());
    }

    /**
     * Method under test: {@link DroidResource#getDroidByAlive(Boolean)}
     */
    @Test
    public void testGetDroidByAlive() {
        DroidRepo droidRepo = mock(DroidRepo.class);
        when(droidRepo.findDroidByAlive((Boolean) any())).thenReturn(Optional.of(new ArrayList<>()));
        DroidMapper droidMapper = new DroidMapper(new ModelMapper(), mock(UnicornRepo.class));

        ResponseEntity<List<DroidDto>> actualDroidByAlive = (new DroidResource(
                new DroidServiceImpl(droidMapper, droidRepo, new UnicornMapper()))).getDroidByAlive(true);
        assertTrue(actualDroidByAlive.hasBody());
        assertEquals(200, actualDroidByAlive.getStatusCodeValue());
        assertTrue(actualDroidByAlive.getHeaders().isEmpty());
        verify(droidRepo).findDroidByAlive((Boolean) any());
    }

    /**
     * Method under test: {@link DroidResource#getDroidByName(String)}
     */
    @Test
    public void testGetDroidByName() {
        DroidRepo droidRepo = mock(DroidRepo.class);
        when(droidRepo.findDroidByName((String) any())).thenReturn(Optional.of(new HashSet<>()));
        DroidMapper droidMapper = new DroidMapper(new ModelMapper(), mock(UnicornRepo.class));

        ResponseEntity<Set<DroidDto>> actualDroidByName = (new DroidResource(
                new DroidServiceImpl(droidMapper, droidRepo, new UnicornMapper()))).getDroidByName("Name");
        assertTrue(actualDroidByName.hasBody());
        assertEquals(200, actualDroidByName.getStatusCodeValue());
        assertTrue(actualDroidByName.getHeaders().isEmpty());
        verify(droidRepo).findDroidByName((String) any());
    }
}

