package com.tech.zootech.security.service.implementations;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.tech.zootech.security.domain.Contact;
import com.tech.zootech.security.repo.ContactRepository;

import java.util.Optional;
import java.util.UUID;

import org.junit.Ignore;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = {ContactServiceImpl.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class ContactServiceImplTest {
    @MockBean
    private ContactRepository contactRepository;

    @Autowired
    private ContactServiceImpl contactServiceImpl;

    /**
     * Method under test: {@link ContactServiceImpl#updateNumberById(UUID, String)}
     */
    @Test
    public void testUpdateNumberById() {
        when(contactRepository.saveAndFlush((Contact) any())).thenReturn(new Contact());
        when(contactRepository.findById((UUID) any())).thenReturn(Optional.of(new Contact()));
        contactServiceImpl.updateNumberById(UUID.randomUUID(), "42");
        verify(contactRepository).saveAndFlush((Contact) any());
        verify(contactRepository).findById((UUID) any());
    }
}

