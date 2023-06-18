package com.tech.zootech.security.service.implementations;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.tech.zootech.security.DTO.CupcakeDto;
import com.tech.zootech.security.DTO.DroidDto;
import com.tech.zootech.security.domain.Cupcake;
import com.tech.zootech.security.domain.Droid;
import com.tech.zootech.security.domain.enums.Filling;
import com.tech.zootech.security.exceptions.CupcakeDroidNotFound;
import com.tech.zootech.security.exceptions.CupcakeIdNotFound;
import com.tech.zootech.security.repo.CupcakeRepo;
import com.tech.zootech.security.utils.mapper.CupcakeMapper;
import com.tech.zootech.security.utils.mapper.DroidMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Ignore;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = {CupcakeServiceImpl.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class CupcakeServiceImplTest {
    @MockBean
    private CupcakeMapper cupcakeMapper;

    @MockBean
    private CupcakeRepo cupcakeRepo;

    @Autowired
    private CupcakeServiceImpl cupcakeServiceImpl;

    @MockBean
    private DroidMapper droidMapper;

    /**
     * Method under test: {@link CupcakeServiceImpl#saveCupcake(CupcakeDto)}
     */
    @Test
    public void testSaveCupcake() {
        when(cupcakeMapper.toEntity((CupcakeDto) any())).thenReturn(new Cupcake(Filling.CHOCOLATE));
        when(cupcakeRepo.save((Cupcake) any())).thenReturn(new Cupcake(Filling.CHOCOLATE));
        CupcakeDto cupcakeDto = new CupcakeDto();
        assertSame(cupcakeDto, cupcakeServiceImpl.saveCupcake(cupcakeDto));
        verify(cupcakeMapper).toEntity((CupcakeDto) any());
        verify(cupcakeRepo).save((Cupcake) any());
    }

    /**
     * Method under test: {@link CupcakeServiceImpl#saveCupcake(CupcakeDto)}
     */
    @Test
    public void testSaveCupcake2() {
        when(cupcakeMapper.toEntity((CupcakeDto) any())).thenReturn(new Cupcake(Filling.CHOCOLATE));
        when(cupcakeRepo.save((Cupcake) any())).thenThrow(new CupcakeDroidNotFound("Not all who wander are lost"));
        assertThrows(CupcakeDroidNotFound.class, () -> cupcakeServiceImpl.saveCupcake(new CupcakeDto()));
        verify(cupcakeMapper).toEntity((CupcakeDto) any());
        verify(cupcakeRepo).save((Cupcake) any());
    }

    /**
     * Method under test: {@link CupcakeServiceImpl#getCupcakeById(Long)}
     */
    @Test
    public void testGetCupcakeById() {
        CupcakeDto cupcakeDto = new CupcakeDto();
        when(cupcakeMapper.toDto((Cupcake) any())).thenReturn(cupcakeDto);
        when(cupcakeRepo.findById((Long) any())).thenReturn(Optional.of(new Cupcake(Filling.CHOCOLATE)));
        assertSame(cupcakeDto, cupcakeServiceImpl.getCupcakeById(123L));
        verify(cupcakeMapper).toDto((Cupcake) any());
        verify(cupcakeRepo).findById((Long) any());
    }

    /**
     * Method under test: {@link CupcakeServiceImpl#getCupcakeById(Long)}
     */
    @Test
    public void testGetCupcakeById2() {
        when(cupcakeMapper.toDto((Cupcake) any())).thenThrow(new CupcakeDroidNotFound("Not all who wander are lost"));
        when(cupcakeRepo.findById((Long) any())).thenReturn(Optional.of(new Cupcake(Filling.CHOCOLATE)));
        assertThrows(CupcakeDroidNotFound.class, () -> cupcakeServiceImpl.getCupcakeById(123L));
        verify(cupcakeMapper).toDto((Cupcake) any());
        verify(cupcakeRepo).findById((Long) any());
    }

    /**
     * Method under test: {@link CupcakeServiceImpl#getCupcakeById(Long)}
     */
    @Test
    public void testGetCupcakeById3() {
        when(cupcakeMapper.toDto((Cupcake) any())).thenReturn(new CupcakeDto());
        when(cupcakeRepo.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(CupcakeIdNotFound.class, () -> cupcakeServiceImpl.getCupcakeById(123L));
        verify(cupcakeRepo).findById((Long) any());
    }

    /**
     * Method under test: {@link CupcakeServiceImpl#getByDroid(DroidDto)}
     */
    @Test
    public void testGetByDroid() {
        when(cupcakeRepo.findByDroid((Droid) any())).thenReturn(Optional.of(new ArrayList<>()));
        when(droidMapper.toEntity((DroidDto) any())).thenReturn(new Droid());
        assertTrue(cupcakeServiceImpl.getByDroid(new DroidDto()).isEmpty());
        verify(cupcakeRepo).findByDroid((Droid) any());
        verify(droidMapper).toEntity((DroidDto) any());
    }

    /**
     * Method under test: {@link CupcakeServiceImpl#getByDroid(DroidDto)}
     */
    @Test
    public void testGetByDroid2() {
        when(cupcakeRepo.findByDroid((Droid) any())).thenReturn(Optional.of(new ArrayList<>()));
        when(droidMapper.toEntity((DroidDto) any())).thenThrow(new CupcakeDroidNotFound("Not all who wander are lost"));
        assertThrows(CupcakeDroidNotFound.class, () -> cupcakeServiceImpl.getByDroid(new DroidDto()));
        verify(droidMapper).toEntity((DroidDto) any());
    }


    /**
     * Method under test: {@link CupcakeServiceImpl#getByDroid(DroidDto)}
     */
    @Test
    public void testGetByDroid4() {
        when(cupcakeMapper.toDto((Cupcake) any())).thenThrow(new CupcakeDroidNotFound("Not all who wander are lost"));
        when(cupcakeRepo.findByDroid((Droid) any())).thenReturn(Optional.empty());
        when(droidMapper.toEntity((DroidDto) any())).thenReturn(new Droid());
        assertThrows(CupcakeDroidNotFound.class, () -> cupcakeServiceImpl.getByDroid(new DroidDto()));
        verify(cupcakeRepo).findByDroid((Droid) any());
        verify(droidMapper).toEntity((DroidDto) any());
    }
}

