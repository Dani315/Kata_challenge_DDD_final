package com.challenge.usecase;

import co.com.sofka.business.annotation.EventListener;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.support.ResponseEvents;
import co.com.sofka.business.support.TriggeredEvent;
import co.com.sofka.domain.generic.DomainEvent;
import com.challenge.domain.challenge.Challenge;
import com.challenge.domain.challenge.events.RevokedChallenge;
import com.challenge.domain.challenge.values.ChallengeId;
import com.challenge.domain.dojo.events.EvaluatedClan;
import com.challenge.domain.dojo.values.DojoId;
import com.challenge.usecase.services.ClanEvaluationService;

import java.util.List;
import java.util.concurrent.Flow;

@EventListener(eventType = "com.challenge.domain.challenge.RevokedChallenge")
public class EvaluateClansByChallengeUseCase extends UseCase<TriggeredEvent<RevokedChallenge>, ResponseEvents> {
    private final UpdateScoreToMemberUseCase updateScoreToMemberUseCase;
    private final Flow.Subscriber<? super DomainEvent> subscriber;

    public EvaluateClansByChallengeUseCase(UpdateScoreToMemberUseCase updateScoreToMemberUseCase, Flow.Subscriber<? super DomainEvent> subscriber) {
        this.updateScoreToMemberUseCase = updateScoreToMemberUseCase;
        this.subscriber = subscriber;
    }

    @Override
    public void executeUseCase(TriggeredEvent<RevokedChallenge> triggeredEvent) {
        var event = triggeredEvent.getDomainEvent();
        ChallengeId challengeId = ChallengeId.of(event.aggregateRootId());

        var service = getService(ClanEvaluationService.class).orElseThrow();
        var pointsByClanId = service.getEvaluationsClan(challengeId);

        if(!pointsByClanId.isEmpty()) {
                pointsByClanId.forEach(clanEvaluation -> {
                    EvaluatedClan evaluatedClan = new EvaluatedClan(clanEvaluation.getPoint(), DojoId.of("123"), clanEvaluation.getClanId());

                    UseCaseHandler.getInstance()
                            .setIdentifyExecutor(evaluatedClan.getClanId().value())
                            .asyncExecutor(updateScoreToMemberUseCase, new TriggeredEvent<>(evaluatedClan))
                            .subscribe(subscriber);
                    /*waitTwoSeconds();*/
                });
        }

        emit().onSuccess(new ResponseEvents(List.of()));
    }

    /*private void waitTwoSeconds() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }*/
}
