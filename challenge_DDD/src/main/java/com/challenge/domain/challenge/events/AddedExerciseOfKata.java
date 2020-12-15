package com.challenge.domain.challenge.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.challenge.domain.challenge.values.KataId;

import java.util.Map;

public class AddedExerciseOfKata extends DomainEvent {
    private final KataId kataId;
    private final int level;
    private final Map<Integer, String> metadata;
    private final String goal;

    public AddedExerciseOfKata(KataId entityId, int level, Map<Integer, String> metadata, String goal) {
        super("com.challenge.domain.challenge.AddedExerciseOfKata");
        this.kataId = entityId;
        this.level = level;
        this.metadata = metadata;
        this.goal = goal;
    }

    public KataId getKataId() {
        return kataId;
    }

    public int getLevel() {
        return level;
    }

    public Map<Integer, String> getMetadata() {
        return metadata;
    }

    public String getGoal() {
        return goal;
    }
}
