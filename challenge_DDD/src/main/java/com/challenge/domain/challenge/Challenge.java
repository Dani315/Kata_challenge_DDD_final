package com.challenge.domain.challenge;

import co.com.sofka.domain.generic.AggregateEvent;
import co.com.sofka.domain.generic.DomainEvent;
import com.challenge.domain.challenge.events.*;
import com.challenge.domain.challenge.values.*;
import com.challenge.domain.clan.values.ClanId;
import com.challenge.domain.dojo.Dojo;
import com.challenge.domain.dojo.DojoState;
import com.challenge.domain.dojo.events.CreatedDojo;
import com.challenge.domain.dojo.values.DojoId;
import com.challenge.domain.externalValues.Name;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Challenge extends AggregateEvent<ChallengeId> {
    protected Name name;
    protected ClanIds clanIds;
    protected DojoId dojoId;
    protected List<Kata> katas;
    protected Assesment assesment;
    protected boolean isRevoked;
    protected boolean isVisible;
    protected Integer durationDays;


    public Challenge(ChallengeId entityId,DojoId dojoId) {
        super(entityId);
        appendChange(new CreatedChallenge(dojoId)).apply();
    }

    public Challenge(ChallengeId entityId) {
        super(entityId);
        subscribe(new ChallengeState(this));
    }

    public static Challenge from(ChallengeId entityId, List<DomainEvent> eventList) {
        var challenge = new Challenge(entityId);
        eventList.forEach(challenge::applyEvent);
        return challenge;
    }

    public void addNewKata(KataId entityId, String purpose, String description, Integer limitOfTime) {
        appendChange(new AddedKata(entityId, purpose, description, limitOfTime)).apply();
    }

    public void subscriberClan(ClanId clanId) {
        appendChange(new ClanSubscribed(clanId)).apply();
    }

    public void unSubscriberClan(ClanId clanId) {

    }

    public void changeDurationDays(Integer durationDays) {
        appendChange(new ChangedDurationDays(durationDays)).apply();
    }

    public void updateName(Name name) {
        appendChange(new UpdatedName(name)).apply();
    }

    public void updateKata(KataId entityId,Integer limitOfTime) {
        appendChange(new UpdatedKata(limitOfTime, entityId)).apply();
    }

    public void addExerciseOfKata(KataId kataId, int level, Map<Integer, String> metadata, String goal) {
        appendChange(new AddedExerciseOfKata(kataId,level,metadata,goal)).apply();
    }

    public void removeExerciseOfKata(KataId kataId, Exercise exercise) {
        appendChange(new ExerciseRemovedFromKata(kataId,exercise)).apply();
    }

    public void assignAssessment(int degreeOfDifficulty, String repoUrl, String summary) {
        appendChange(new AssignedAssessment(degreeOfDifficulty,repoUrl,summary)).apply();
    }

    public void revokeChallenge() {
        appendChange(new RevokedChallenge()).apply();
    }

    public List<Kata> Katas() {
        return katas;
    }

    public ClanIds getClanIds() {
        return clanIds;
    }
}
