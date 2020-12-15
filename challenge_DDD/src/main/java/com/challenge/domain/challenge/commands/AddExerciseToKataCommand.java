package com.challenge.domain.challenge.commands;

import co.com.sofka.domain.generic.Command;
import com.challenge.domain.challenge.values.ChallengeId;
import com.challenge.domain.challenge.values.Exercise;
import com.challenge.domain.challenge.values.KataId;

import java.util.List;

public class AddExerciseToKataCommand implements Command {
    private List<Exercise> exercises;
    private ChallengeId challengeId;
    private KataId kataId;

    public AddExerciseToKataCommand(List<Exercise> exercises, ChallengeId challengeId, KataId kataId) {
        this.exercises = exercises;
        this.challengeId = challengeId;
        this.kataId = kataId;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public ChallengeId getChallengeId() {
        return challengeId;
    }

    public KataId getKataId() {
        return kataId;
    }
}
