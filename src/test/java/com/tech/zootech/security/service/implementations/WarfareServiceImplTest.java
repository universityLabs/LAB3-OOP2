package com.tech.zootech.security.service.implementations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.tech.zootech.security.domain.Droid;
import com.tech.zootech.security.exceptions.DroidNotFound;
import com.tech.zootech.security.repo.DroidRepo;

import java.util.List;

import java.util.Optional;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = {WarfareServiceImpl.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class WarfareServiceImplTest {
    @MockBean
    private DroidRepo droidRepo;

    @Autowired
    private WarfareServiceImpl warfareServiceImpl;

    /**
     * Method under test: {@link WarfareServiceImpl#battle(Long, Long)}
     */
    @Test
    public void testBattle3() {
        Droid droid = mock(Droid.class);
        when(droid.getAttackHp()).thenReturn(1);
        when(droid.getHp()).thenReturn(1);
        doNothing().when(droid).setAlive((Boolean) any());
        doNothing().when(droid).setHp((Integer) any());
        droid.setHp(0);
        Optional<Droid> ofResult = Optional.of(droid);
        when(droidRepo.findById((Long) any())).thenReturn(ofResult);
        assertEquals(WarfareServiceImpl.DEFENDER_IS_DEAD, warfareServiceImpl.battle(1L, 1L));
        verify(droidRepo, atLeast(1)).findById((Long) any());
        verify(droid).getAttackHp();
        verify(droid).getHp();
        verify(droid).setAlive((Boolean) any());
        verify(droid, atLeast(1)).setHp((Integer) any());
    }

    /**
     * Method under test: {@link WarfareServiceImpl#battle(Long, Long)}
     */
    @Test
    public void testBattle4() {
        Droid droid = mock(Droid.class);
        when(droid.getAttackHp()).thenReturn(0);
        when(droid.getHp()).thenReturn(1);
        doNothing().when(droid).setAlive((Boolean) any());
        doNothing().when(droid).setHp((Integer) any());
        droid.setHp(0);
        Optional<Droid> ofResult = Optional.of(droid);
        when(droidRepo.findById((Long) any())).thenReturn(ofResult);
        assertEquals(WarfareServiceImpl.DEFENDER_IS_STILL_ALIVE_FOR_NOW, warfareServiceImpl.battle(1L, 1L));
        verify(droidRepo, atLeast(1)).findById((Long) any());
        verify(droid).getAttackHp();
        verify(droid).getHp();
        verify(droid, atLeast(1)).setHp((Integer) any());
    }

    /**
     * Method under test: {@link WarfareServiceImpl#battle(Long, Long)}
     */
    @Test
    public void testBattle5() {
        when(droidRepo.findById((Long) any())).thenReturn(Optional.empty());
        Droid droid = mock(Droid.class);
        when(droid.getAttackHp()).thenReturn(1);
        when(droid.getHp()).thenReturn(1);
        doNothing().when(droid).setAlive((Boolean) any());
        doNothing().when(droid).setHp((Integer) any());
        droid.setHp(0);
        assertThrows(DroidNotFound.class, () -> warfareServiceImpl.battle(1L, 1L));
        verify(droidRepo).findById((Long) any());
        verify(droid).setHp((Integer) any());
    }

    /**
     * Method under test: {@link WarfareServiceImpl#decideWhoAttacks(Long, Long)}
     */
    @Test
    public void testDecideWhoAttacks() {
        List<Long> actualDecideWhoAttacksResult = warfareServiceImpl.decideWhoAttacks(1L, 1L);
        assertEquals(2, actualDecideWhoAttacksResult.size());
        assertEquals(1L, (long) actualDecideWhoAttacksResult.get(0));
        assertEquals(1L, (long) actualDecideWhoAttacksResult.get(1));
    }
}

