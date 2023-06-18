package com.tech.zootech.security.service.implementations;

import com.tech.zootech.security.domain.Droid;
import com.tech.zootech.security.exceptions.DroidNotFound;
import com.tech.zootech.security.repo.DroidRepo;
import com.tech.zootech.security.service.WarfareService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@AllArgsConstructor
public class WarfareServiceImpl implements WarfareService {
    public static final String DEFENDER_IS_STILL_ALIVE_FOR_NOW = "Defender is still alive!!! For now...";
    public static final String DEFENDER_IS_DEAD = "Defender is dead((((";
    private final DroidRepo droidRepo;

    @Override
    public String battle(Long idOfDroidThatAttacks, Long idOfDroidThatDefends) {
        var attacker = droidRepo.findById(idOfDroidThatAttacks)
                .orElseThrow(() -> new DroidNotFound("Droid not found"));
        var defender = droidRepo.findById(idOfDroidThatDefends)
                .orElseThrow(() -> new DroidNotFound("Droid not found"));

        int result = defender.getHp() - attacker.getAttackHp();
        return getDefenderStatus(defender, result);
    }

    /**
     * this method decides who will attack;
     */
    @Override
    public List<Long> decideWhoAttacks(Long firstCandidate, Long secondCandidate) {
        Random random = new Random();
        int max = 10;
        int min = 0;
        int randomNum = random.nextInt((max - min) + 1) + min;
        List<Long> list = new ArrayList<>();
        if (randomNum >= 5) {
            list.add(firstCandidate);
            list.add(secondCandidate);
        } else {
            list.add(secondCandidate);
            list.add(firstCandidate);
        }
        return list;
    }

    private static String getDefenderStatus(Droid defender, int result) {
        if (result > 0) {
            defender.setHp(result);
            return DEFENDER_IS_STILL_ALIVE_FOR_NOW;
        } else {
            defender.setHp(result);
            defender.setAlive(false);
            return DEFENDER_IS_DEAD;
        }
    }
}
