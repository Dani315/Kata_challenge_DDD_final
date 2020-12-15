package com.challenge.domain.challenge.commands;

import co.com.sofka.domain.generic.Command;
import com.challenge.domain.challenge.values.Assesment;
import com.challenge.domain.challenge.values.ChallengeId;
import com.challenge.domain.dojo.values.DojoId;
import com.challenge.domain.externalValues.Name;

public class CreateChallengeCommand implements Command {

    private ChallengeId challengeId;
    private Name name;
    private DojoId dojoId;
    private Assesment assesment;
    private Integer durationDays;

    public CreateChallengeCommand(
            ChallengeId challengeId, Name name,
            DojoId dojoId, Assesment assesment,
            Integer durationDays) {
        this.challengeId = challengeId;
        this.name = name;
        this.dojoId = dojoId;
        this.assesment = assesment;
        this.durationDays = durationDays;
    }

    public ChallengeId getChallengeId() {
        return challengeId;
    }

    public Name getName() {
        return name;
    }

    public DojoId getDojoId() {
        return dojoId;
    }

    public Assesment getAssesment() {
        return assesment;
    }

    public Integer getDurationDays() {
        return durationDays;
    }
}
