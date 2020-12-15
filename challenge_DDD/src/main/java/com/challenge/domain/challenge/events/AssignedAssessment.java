package com.challenge.domain.challenge.events;

import co.com.sofka.domain.generic.DomainEvent;

public class AssignedAssessment extends DomainEvent {
    private final int degreeOfDifficulty;
    private final String repoUrl;
    private final String summary;

    public AssignedAssessment(int degreeOfDifficulty, String repoUrl, String summary) {
        super("com.challenge.domain.challenge.AssignedAssessment");
        this.degreeOfDifficulty = degreeOfDifficulty;
        this.repoUrl = repoUrl;
        this.summary = summary;
    }

    public int getDegreeOfDifficulty() {
        return degreeOfDifficulty;
    }

    public String getRepoUrl() {
        return repoUrl;
    }

    public String getSummary() {
        return summary;
    }
}
