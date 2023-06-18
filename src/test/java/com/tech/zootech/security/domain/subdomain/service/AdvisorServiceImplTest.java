package com.tech.zootech.security.domain.subdomain.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.tech.zootech.security.domain.subdomain.domain.Advisor;
import com.tech.zootech.security.domain.subdomain.domain.Application;
import com.tech.zootech.security.domain.subdomain.repos.AdvisorRepository;
import com.tech.zootech.security.domain.subdomain.repos.ApplicationRepository;

import java.util.Optional;

import org.junit.Ignore;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = {AdvisorServiceImpl.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class AdvisorServiceImplTest {
    @MockBean
    private AdvisorRepository advisorRepository;

    @Autowired
    private AdvisorServiceImpl advisorServiceImpl;

    @MockBean
    private ApplicationRepository applicationRepository;

    /**
     * Method under test: {@link AdvisorServiceImpl#assignNewApplicationByAdvisorId(Long)}
     */
    @Test
    public void testAssignNewApplicationByAdvisorId() {
        Advisor advisor = new Advisor();
        when(advisorRepository.findById((Long) any())).thenReturn(Optional.of(advisor));
        Application application = new Application();
        when(applicationRepository.findFirstByStatusOrderByCreatedAt((Application.Status) any())).thenReturn(application);
        Application actualAssignNewApplicationByAdvisorIdResult = advisorServiceImpl.assignNewApplicationByAdvisorId(123L);
        assertSame(application, actualAssignNewApplicationByAdvisorIdResult);
        Advisor advisor1 = actualAssignNewApplicationByAdvisorIdResult.getAdvisor();
        assertSame(advisor, advisor1);
        assertEquals(Application.Status.ASSIGNED, actualAssignNewApplicationByAdvisorIdResult.getStatus());
        assertEquals(1, advisor1.getApplications().size());
        verify(advisorRepository).findById((Long) any());
        verify(applicationRepository).findFirstByStatusOrderByCreatedAt((Application.Status) any());
    }

    /**
     * Method under test: {@link AdvisorServiceImpl#assignNewApplicationByAdvisorId(Long)}
     */
    @Test
    public void testAssignNewApplicationByAdvisorId2() {
        Advisor advisor = mock(Advisor.class);
        doNothing().when(advisor).assignApplication((Application) any());
        Optional<Advisor> ofResult = Optional.of(advisor);
        when(advisorRepository.findById((Long) any())).thenReturn(ofResult);
        Application application = new Application();
        when(applicationRepository.findFirstByStatusOrderByCreatedAt((Application.Status) any())).thenReturn(application);
        assertSame(application, advisorServiceImpl.assignNewApplicationByAdvisorId(123L));
        verify(advisorRepository).findById((Long) any());
        verify(advisor).assignApplication((Application) any());
        verify(applicationRepository).findFirstByStatusOrderByCreatedAt((Application.Status) any());
    }
}

