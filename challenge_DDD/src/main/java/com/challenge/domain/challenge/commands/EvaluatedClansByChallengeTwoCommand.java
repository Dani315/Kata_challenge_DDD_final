package com.challenge.domain.challenge.commands;

import co.com.sofka.domain.generic.Command;
import com.challenge.domain.challenge.values.ChallengeId;
import com.challenge.usecase.models.ClanEvaluation;

import java.util.List;

public class EvaluatedClansByChallengeTwoCommand implements Command {
    private List<ClanEvaluation> clanEvaluations;
    private ChallengeId challengeId;

    public EvaluatedClansByChallengeTwoCommand(List<ClanEvaluation> clanEvaluations, ChallengeId challengeId) {
        this.clanEvaluations = clanEvaluations;
        this.challengeId = challengeId;
    }

    public List<ClanEvaluation> getClanEvaluations() {
        return clanEvaluations;
    }

    public ChallengeId getChallengeId() {
        return challengeId;
    }
}
