package com.challenge.domain.challenge.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.challenge.domain.challenge.values.Exercise;
import com.challenge.domain.challenge.values.KataId;

public class ExerciseRemovedFromKata  extends DomainEvent {
    private final KataId kataId;
    private final Exercise exercise;


    public ExerciseRemovedFromKata(KataId kataId, Exercise exercise) {
        super("com.challenge.domain.challenge.ExerciseRemovedFromKata");
        this.kataId = kataId;
        this.exercise = exercise;
    }

    public KataId getKataId() {
        return kataId;
    }

    public Exercise getExercise() {
        return exercise;
    }
}
