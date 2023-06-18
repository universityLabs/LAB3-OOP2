package com.tech.zootech.chat.repoitory;

import com.tech.zootech.chat.domain.entities.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
}
