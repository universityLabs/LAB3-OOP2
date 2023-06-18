package com.tech.zootech.security.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.tech.zootech.security.DTO.UnicornDto;
import com.tech.zootech.security.domain.Unicorn;
import com.tech.zootech.security.domain.enums.Color;
import com.tech.zootech.security.repo.UnicornRepo;
import com.tech.zootech.security.service.implementations.UnicornServiceImpl;
import com.tech.zootech.security.utils.mapper.UnicornMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

public class UnicornResourceTest {
    /**
     * Method under test: {@link UnicornResource#get(UnicornDto)}
     */
    @Test
    public void testGet2() {
        UnicornRepo unicornRepo = mock(UnicornRepo.class);
        when(unicornRepo.save((Unicorn) any())).thenReturn(new Unicorn("Name", Color.BLACK));
        UnicornMapper unicornMapper = mock(UnicornMapper.class);
        when(unicornMapper.toDto((Unicorn) any())).thenReturn(new UnicornDto());
        when(unicornMapper.toEntity((UnicornDto) any())).thenReturn(new Unicorn("Name", Color.BLACK));
        UnicornResource unicornResource = new UnicornResource(new UnicornServiceImpl(unicornRepo, unicornMapper));
        UnicornDto unicornDto = new UnicornDto();
        ResponseEntity<UnicornDto> actualGetResult = unicornResource.get(unicornDto);
        assertEquals(unicornDto, actualGetResult.getBody());
        assertTrue(actualGetResult.getHeaders().isEmpty());
        assertEquals(200, actualGetResult.getStatusCodeValue());
        verify(unicornRepo).save((Unicorn) any());
        verify(unicornMapper).toDto((Unicorn) any());
        verify(unicornMapper).toEntity((UnicornDto) any());
    }


    /**
     * Method under test: {@link UnicornResource#save(UnicornDto)}
     */
    @Test
    public void testSave2() {
        UnicornRepo unicornRepo = mock(UnicornRepo.class);
        when(unicornRepo.save((Unicorn) any())).thenReturn(new Unicorn("Name", Color.BLACK));
        UnicornMapper unicornMapper = mock(UnicornMapper.class);
        when(unicornMapper.toDto((Unicorn) any())).thenReturn(new UnicornDto());
        when(unicornMapper.toEntity((UnicornDto) any())).thenReturn(new Unicorn("Name", Color.BLACK));
        UnicornResource unicornResource = new UnicornResource(new UnicornServiceImpl(unicornRepo, unicornMapper));
        UnicornDto unicornDto = new UnicornDto();
        ResponseEntity<UnicornDto> actualSaveResult = unicornResource.save(unicornDto);
        assertEquals(unicornDto, actualSaveResult.getBody());
        assertTrue(actualSaveResult.getHeaders().isEmpty());
        assertEquals(200, actualSaveResult.getStatusCodeValue());
        verify(unicornRepo).save((Unicorn) any());
        verify(unicornMapper).toDto((Unicorn) any());
        verify(unicornMapper).toEntity((UnicornDto) any());
    }


    /**
     * Method under test: {@link UnicornResource#getByName(String)}
     */
    @Test
    public void testGetByName2() {
        UnicornRepo unicornRepo = mock(UnicornRepo.class);
        when(unicornRepo.findByName((String) any())).thenReturn(Optional.of(new Unicorn("Name", Color.BLACK)));
        UnicornMapper unicornMapper = mock(UnicornMapper.class);
        when(unicornMapper.toDto((Unicorn) any())).thenReturn(new UnicornDto());
        ResponseEntity<UnicornDto> actualByName = (new UnicornResource(
                new UnicornServiceImpl(unicornRepo, unicornMapper))).getByName("Name");
        assertTrue(actualByName.hasBody());
        assertTrue(actualByName.getHeaders().isEmpty());
        assertEquals(200, actualByName.getStatusCodeValue());
        verify(unicornRepo).findByName((String) any());
        verify(unicornMapper).toDto((Unicorn) any());
    }

    /**
     * Method under test: {@link UnicornResource#getByColor(Color)}
     */
    @Test
    public void testGetByColor() {
        UnicornRepo unicornRepo = mock(UnicornRepo.class);
        when(unicornRepo.findByColor((Color) any())).thenReturn(Optional.of(new ArrayList<>()));
        ResponseEntity<List<UnicornDto>> actualByColor = (new UnicornResource(
                new UnicornServiceImpl(unicornRepo, new UnicornMapper()))).getByColor(Color.BLACK);
        assertTrue(actualByColor.hasBody());
        assertEquals(200, actualByColor.getStatusCodeValue());
        assertTrue(actualByColor.getHeaders().isEmpty());
        verify(unicornRepo).findByColor((Color) any());
    }


    /**
     * Method under test: {@link UnicornResource#getByColor(Color)}
     */
    @Test
    public void testGetByColor3() {
        UnicornRepo unicornRepo = mock(UnicornRepo.class);
        when(unicornRepo.findByColor((Color) any())).thenReturn(Optional.of(new ArrayList<>()));
        ResponseEntity<List<UnicornDto>> actualByColor = (new UnicornResource(
                new UnicornServiceImpl(unicornRepo, new UnicornMapper()))).getByColor(Color.RED);
        assertTrue(actualByColor.hasBody());
        assertEquals(200, actualByColor.getStatusCodeValue());
        assertTrue(actualByColor.getHeaders().isEmpty());
        verify(unicornRepo).findByColor((Color) any());
    }

    /**
     * Method under test: {@link UnicornResource#getByColor(Color)}
     */
    @Test
    public void testGetByColor4() {
        UnicornRepo unicornRepo = mock(UnicornRepo.class);
        when(unicornRepo.findByColor((Color) any())).thenReturn(Optional.of(new ArrayList<>()));
        ResponseEntity<List<UnicornDto>> actualByColor = (new UnicornResource(
                new UnicornServiceImpl(unicornRepo, new UnicornMapper()))).getByColor(Color.WHITE);
        assertTrue(actualByColor.hasBody());
        assertEquals(200, actualByColor.getStatusCodeValue());
        assertTrue(actualByColor.getHeaders().isEmpty());
        verify(unicornRepo).findByColor((Color) any());
    }

    /**
     * Method under test: {@link UnicornResource#getByColor(Color)}
     */
    @Test
    public void testGetByColor5() {
        UnicornRepo unicornRepo = mock(UnicornRepo.class);
        when(unicornRepo.findByColor((Color) any())).thenReturn(Optional.of(new ArrayList<>()));
        ResponseEntity<List<UnicornDto>> actualByColor = (new UnicornResource(
                new UnicornServiceImpl(unicornRepo, new UnicornMapper()))).getByColor(Color.BLUE);
        assertTrue(actualByColor.hasBody());
        assertEquals(200, actualByColor.getStatusCodeValue());
        assertTrue(actualByColor.getHeaders().isEmpty());
        verify(unicornRepo).findByColor((Color) any());
    }

    /**
     * Method under test: {@link UnicornResource#getByColor(Color)}
     */
    @Test
    public void testGetByColor6() {
        UnicornRepo unicornRepo = mock(UnicornRepo.class);
        when(unicornRepo.findByColor((Color) any())).thenReturn(Optional.of(new ArrayList<>()));
        ResponseEntity<List<UnicornDto>> actualByColor = (new UnicornResource(
                new UnicornServiceImpl(unicornRepo, new UnicornMapper()))).getByColor(Color.GREEN);
        assertTrue(actualByColor.hasBody());
        assertEquals(200, actualByColor.getStatusCodeValue());
        assertTrue(actualByColor.getHeaders().isEmpty());
        verify(unicornRepo).findByColor((Color) any());
    }
}

