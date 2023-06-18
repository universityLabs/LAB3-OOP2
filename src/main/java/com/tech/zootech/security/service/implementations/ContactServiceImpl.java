package com.tech.zootech.security.service.implementations;

import com.tech.zootech.security.repo.ContactRepository;
import com.tech.zootech.security.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {
    private final ContactRepository contactRepository;

    @Override
    @Transactional
    public void updateNumberById(UUID id, String number) {
        var contact = contactRepository.findById(id)
                .orElseThrow();
        contact.setNumber(number);
        contactRepository.saveAndFlush(contact);
    }
}
