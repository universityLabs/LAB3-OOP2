package com.tech.zootech.security.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.tech.zootech.security.DTO.CupcakeDto;
import com.tech.zootech.security.DTO.DroidDto;
import com.tech.zootech.security.domain.Cupcake;
import com.tech.zootech.security.domain.Droid;
import com.tech.zootech.security.domain.enums.Filling;
import com.tech.zootech.security.repo.CupcakeRepo;
import com.tech.zootech.security.repo.DroidRepo;
import com.tech.zootech.security.repo.UnicornRepo;
import com.tech.zootech.security.service.implementations.CupcakeServiceImpl;
import com.tech.zootech.security.utils.mapper.CupcakeMapper;
import com.tech.zootech.security.utils.mapper.DroidMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Ignore;
import org.junit.Test;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;

public class CupcakeResourceTest {

    /**
     * Method under test: {@link CupcakeResource#saveCupcake(CupcakeDto)}
     */
    @Test
    public void testSaveCupcake2() {
        CupcakeMapper cupcakeMapper = mock(CupcakeMapper.class);
        when(cupcakeMapper.toEntity((CupcakeDto) any())).thenReturn(new Cupcake(Filling.CHOCOLATE));
        CupcakeRepo cupcakeRepo = mock(CupcakeRepo.class);
        when(cupcakeRepo.save((Cupcake) any())).thenReturn(new Cupcake(Filling.CHOCOLATE));
        CupcakeResource cupcakeResource = new CupcakeResource(new CupcakeServiceImpl(cupcakeMapper, cupcakeRepo,
                new DroidMapper(new ModelMapper(), mock(UnicornRepo.class))));
        ResponseEntity<CupcakeDto> actualSaveCupcakeResult = cupcakeResource.saveCupcake(new CupcakeDto());
        assertTrue(actualSaveCupcakeResult.hasBody());
        assertTrue(actualSaveCupcakeResult.getHeaders().isEmpty());
        assertEquals(200, actualSaveCupcakeResult.getStatusCodeValue());
        verify(cupcakeMapper).toEntity((CupcakeDto) any());
        verify(cupcakeRepo).save((Cupcake) any());
    }

    /**
     * Method under test: {@link CupcakeResource#getCupcake(Long)}
     */
    @Test
    public void testGetCupcake2() {
        CupcakeMapper cupcakeMapper = mock(CupcakeMapper.class);
        when(cupcakeMapper.toDto((Cupcake) any())).thenReturn(new CupcakeDto());
        CupcakeRepo cupcakeRepo = mock(CupcakeRepo.class);
        when(cupcakeRepo.findById((Long) any())).thenReturn(Optional.of(new Cupcake(Filling.CHOCOLATE)));
        ResponseEntity<CupcakeDto> actualCupcake = (new CupcakeResource(new CupcakeServiceImpl(cupcakeMapper, cupcakeRepo,
                new DroidMapper(new ModelMapper(), mock(UnicornRepo.class))))).getCupcake(123L);
        assertTrue(actualCupcake.hasBody());
        assertTrue(actualCupcake.getHeaders().isEmpty());
        assertEquals(200, actualCupcake.getStatusCodeValue());
        verify(cupcakeMapper).toDto((Cupcake) any());
        verify(cupcakeRepo).findById((Long) any());
    }

    /**
     * Method under test: {@link CupcakeResource#getByDroid(DroidDto)}
     */
    @Test
    public void testGetByDroid2() {
        CupcakeRepo cupcakeRepo = mock(CupcakeRepo.class);
        when(cupcakeRepo.findByDroid((Droid) any())).thenReturn(Optional.of(new ArrayList<>()));
        DroidMapper droidMapper = mock(DroidMapper.class);
        when(droidMapper.toEntity((DroidDto) any())).thenReturn(new Droid());
        CupcakeResource cupcakeResource = new CupcakeResource(new CupcakeServiceImpl(
                new CupcakeMapper(new ModelMapper(), mock(DroidRepo.class)), cupcakeRepo, droidMapper));
        ResponseEntity<List<CupcakeDto>> actualByDroid = cupcakeResource.getByDroid(new DroidDto());
        assertTrue(actualByDroid.hasBody());
        assertEquals(200, actualByDroid.getStatusCodeValue());
        assertTrue(actualByDroid.getHeaders().isEmpty());
        verify(cupcakeRepo).findByDroid((Droid) any());
        verify(droidMapper).toEntity((DroidDto) any());
    }
}

