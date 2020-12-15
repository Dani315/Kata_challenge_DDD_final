package com.challenge.usecase;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import com.challenge.domain.challenge.Challenge;
import com.challenge.domain.challenge.commands.SubscribeClanCommand;

public class SubscribeaClanUseCase  extends UseCase<RequestCommand<SubscribeClanCommand>, ResponseEvents> {
    @Override
    public void executeUseCase(RequestCommand<SubscribeClanCommand> requestCommand) {
        var command = requestCommand.getCommand();

        Challenge challenge = Challenge.from(command.getChallengeId(), retrieveEvents());

        challenge.subscriberClan(command.getClanId());

        emit().onSuccess(new ResponseEvents(challenge.getUncommittedChanges()));
    }
}
