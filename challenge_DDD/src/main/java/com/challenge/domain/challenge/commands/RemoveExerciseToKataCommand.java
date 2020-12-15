package com.challenge.domain.challenge.commands;

import co.com.sofka.domain.generic.Command;
import com.challenge.domain.challenge.values.ChallengeId;
import com.challenge.domain.challenge.values.Exercise;
import com.challenge.domain.challenge.values.KataId;

public class RemoveExerciseToKataCommand implements Command {
    private Exercise exercise;
    private KataId kataId;
    private ChallengeId challengeId;

    public RemoveExerciseToKataCommand(Exercise exercise, KataId kataId, ChallengeId challengeId) {
        this.exercise = exercise;
        this.kataId = kataId;
        this.challengeId = challengeId;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public KataId getKataId() {
        return kataId;
    }

    public ChallengeId getChallengeId() {
        return challengeId;
    }
}
