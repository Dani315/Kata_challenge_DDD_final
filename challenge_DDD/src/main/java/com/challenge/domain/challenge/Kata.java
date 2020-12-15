package com.challenge.domain.challenge;

import co.com.sofka.domain.generic.Entity;
import com.challenge.domain.challenge.values.Exercise;
import com.challenge.domain.challenge.values.KataId;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Kata extends Entity<KataId> {
    private final String purpose;
    private final List<Exercise> exercises;
    private final String description;
    private Integer limitOfTime;

    public Kata(KataId entityId, String purpose,String description, Integer limitOfTime) {
        super(entityId);
        this.purpose = purpose;
        this.exercises = new ArrayList<>();
        this.description = description;
        this.limitOfTime = limitOfTime;
    }

    public void changeLimitOfTime(Integer limitOfTime) {
        this.limitOfTime = limitOfTime;
    }

    public void removeExercise(Exercise exe) {
        this.exercises.removeIf(exercise -> exercise.value().level() == exe.value().level() &&
                exercise.value().metadata().equals(exe.value().metadata()) &&
                exercise.value().goal().equals(exe.value().goal()));
    }

    public void addNewExercise(int level, Map<Integer,String>metadata, String goal) {
        this.exercises.add(new Exercise(level, metadata,goal));
    }

    public String Purpose() {
        return purpose;
    }

    public List<Exercise> Exercises() {
        return exercises;
    }

    public String Description() {
        return description;
    }

    public Integer LimitOfTime() {
        return limitOfTime;
    }
}
