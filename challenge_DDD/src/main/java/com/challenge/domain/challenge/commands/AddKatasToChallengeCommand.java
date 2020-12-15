package com.challenge.domain.challenge.commands;

import co.com.sofka.domain.generic.Command;
import com.challenge.domain.challenge.Kata;
import com.challenge.domain.challenge.values.ChallengeId;

import java.util.List;

public class AddKatasToChallengeCommand implements Command {
    private List<Kata> katas;
    private ChallengeId challengeId;

    public AddKatasToChallengeCommand(List<Kata> katas, ChallengeId challengeId) {
        this.katas = katas;
        this.challengeId = challengeId;
    }

    public List<Kata> getKatas() {
        return katas;
    }

    public ChallengeId getChallengeId() {
        return challengeId;
    }
}
