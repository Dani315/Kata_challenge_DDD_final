package com.challenge.usecase;

import co.com.sofka.business.annotation.EventListener;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.ResponseEvents;
import co.com.sofka.business.support.TriggeredEvent;
import com.challenge.domain.clan.Clan;
import com.challenge.domain.clan.values.Score;
import com.challenge.domain.dojo.events.EvaluatedClan;

import java.util.Date;
import java.util.stream.Collectors;

@EventListener(eventType = "com.challenge.domain.dojo.EvaluatedClan")
public class UpdateScoreToMemberUseCase extends UseCase<TriggeredEvent<EvaluatedClan>, ResponseEvents> {

    @Override
    public void executeUseCase(TriggeredEvent<EvaluatedClan> triggeredEvent) {
        var event = triggeredEvent.getDomainEvent();

        var clan = Clan.from(event.getClanId(), retrieveEvents().stream().filter(re -> {
            return re.aggregateRootId().equals(event.getClanId().value());
        }).collect(Collectors.toList()));

        clan.Members().forEach(member -> {
            clan.addScoreToMember(new Score(event.getPoint(), event.getDojoId(),new Date()), member.identity());
        });

        emit().onSuccess(new ResponseEvents(clan.getUncommittedChanges()));
    }
}
