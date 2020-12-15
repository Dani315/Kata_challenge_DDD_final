package com.challenge.domain.challenge.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.HashMap;
import java.util.Map;

public class Exercise implements ValueObject<Exercise.Props> {
    private final int level;
    private final Map<Integer,String> metadata;
    private final String goal;

    public Exercise(int level, Map<Integer, String> metadata, String goal) {
        this.level = level;
        this.metadata = metadata;
        this.goal = goal;
    }

    @Override
    public Props value() {
        return new Props() {
            @Override
            public int level() {
                return level;
            }

            @Override
            public Map<Integer, String> metadata() {
                return metadata;
            }

            @Override
            public String goal() {
                return goal;
            }
        };
    }

    public interface Props {
        int level();
        Map<Integer,String> metadata();
        String goal();
    }
}
