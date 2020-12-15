package com.challenge.domain.challenge.values;

import co.com.sofka.domain.generic.ValueObject;

public class Assesment implements ValueObject<Assesment.Props> {
    private final int degreeOfDifficulty;
    private final String repoUrl;
    private final String summary;

    public Assesment(int degreeOfDifficulty, String repoUrl, String summary) {
        this.degreeOfDifficulty = degreeOfDifficulty;
        this.repoUrl = repoUrl;
        this.summary = summary;
    }

    @Override
    public Props value() {
        return new Props() {
            @Override
            public int degreeOfDifficulty() {
                return degreeOfDifficulty;
            }

            @Override
            public String repoUrl() {
                return repoUrl;
            }

            @Override
            public String summary() {
                return summary;
            }
        };
    }

    public interface Props {
        int degreeOfDifficulty();
        String repoUrl();
        String summary();
    }
}
