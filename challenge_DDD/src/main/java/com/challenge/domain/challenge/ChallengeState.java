package com.challenge.domain.challenge;

import co.com.sofka.domain.generic.EventChange;
import co.com.sofka.domain.generic.Identity;
import com.challenge.domain.challenge.events.*;
import com.challenge.domain.challenge.values.Assesment;
import com.challenge.domain.challenge.values.ClanIds;
import com.challenge.domain.clan.Clan;
import com.challenge.domain.clan.Member;
import com.challenge.domain.clan.values.ClanId;

import java.util.ArrayList;
import java.util.List;

public class ChallengeState extends EventChange {
    public ChallengeState(Challenge challenge) {

        apply((CreatedChallenge event) -> {
            challenge.dojoId = event.getDojoId();
            challenge.katas = new ArrayList<>();
            challenge.isRevoked = false;
            challenge.isVisible = true;
            challenge.clanIds = new ClanIds();
        });

        apply((AssignedAssessment event) -> {
            challenge.assesment = new Assesment(
                    event.getDegreeOfDifficulty(),
                    event.getRepoUrl(),
                    event.getSummary());
        });

        apply((UpdatedName event) -> {
            challenge.name = event.getName();
        });

        apply((ChangedDurationDays event) -> {
            challenge.durationDays = event.getDurationDays();
        });

        apply((ClanSubscribed event) -> {
            List<ClanId> aux = challenge.clanIds.value();
            aux.add(event.getClanId());
            challenge.clanIds = new ClanIds(aux);
        });

        apply((AddedKata event) -> {
            challenge.katas.add(
                    new Kata(event.getEntityId(),
                            event.getPurpose(),
                            event.getDescription(),
                            event.getLimitOfTime())
            );
        });

        apply((AddedExerciseOfKata event) -> {
            Kata kata = getKata(challenge, event.getKataId());
            kata.addNewExercise(event.getLevel(), event.getMetadata(), event.getGoal());
        });

        apply((ExerciseRemovedFromKata event) -> {
            Kata kata = getKata(challenge, event.getKataId());
            kata.removeExercise(event.getExercise());
        });

        apply((RevokedKata event) -> {
            Kata kata = getKata(challenge, event.getKataId());
            challenge.katas.removeIf(kata1 -> kata1.equals(kata));
        });

        apply((UpdatedKata event) -> {
            Kata kata = getKata(challenge, event.getKataId());
            kata.changeLimitOfTime(event.getLimitOfTime());
        });

        apply((RevokedChallenge event) -> {
            challenge.isRevoked = true;
            challenge.isVisible = false;
        });
    }

    private Kata getKata(Challenge challenge, Identity id) {
        return challenge.Katas()
                .stream()
                .filter(member -> member.identity().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("La kata no existe"));
    }
}
