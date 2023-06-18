package com.tech.zootech.security.api;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.tech.zootech.security.DTO.DroidDto;
import com.tech.zootech.security.domain.Droid;
import com.tech.zootech.security.repo.DroidRepo;
import com.tech.zootech.security.service.WarfareService;
import com.tech.zootech.security.service.implementations.WarfareServiceImpl;

import java.util.ArrayList;

import java.util.Optional;

import org.junit.Ignore;
import org.junit.Test;

public class WarfareResourceTest {
    /**
     * Method under test: {@link WarfareResource#battle(DroidDto, DroidDto)}
     */
    @Test
    public void testBattle2() {
        Droid droid = mock(Droid.class);
        when(droid.getAttackHp()).thenReturn(1);
        when(droid.getHp()).thenReturn(1);
        doNothing().when(droid).setAlive((Boolean) any());
        doNothing().when(droid).setHp((Integer) any());
        DroidRepo droidRepo = mock(DroidRepo.class);
        when(droidRepo.findById((Long) any())).thenReturn(Optional.of(droid));
        WarfareResource warfareResource = new WarfareResource(new WarfareServiceImpl(droidRepo));
        DroidDto firstDroid = new DroidDto();
        assertEquals("Defender is dead((((", warfareResource.battle(firstDroid, new DroidDto()));
        verify(droidRepo, atLeast(1)).findById((Long) any());
        verify(droid).getAttackHp();
        verify(droid).getHp();
        verify(droid).setAlive((Boolean) any());
        verify(droid).setHp((Integer) any());
    }
}

