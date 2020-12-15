package com.challenge.usecase.services;

import com.challenge.domain.challenge.values.ChallengeId;
import com.challenge.usecase.models.ClanEvaluation;

import java.util.ArrayList;
import java.util.List;

public interface ClanEvaluationService {
    List<ClanEvaluation> getEvaluationsClan(ChallengeId challengeId);
}
