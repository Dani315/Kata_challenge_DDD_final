package com.challenge.usecase;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import com.challenge.domain.challenge.Challenge;
import com.challenge.domain.challenge.commands.AddKatasToChallengeCommand;

public class AddKatasToChallengeUseCase extends UseCase<RequestCommand<AddKatasToChallengeCommand>, ResponseEvents> {
    @Override
    public void executeUseCase(RequestCommand<AddKatasToChallengeCommand> requestCommand) {
        var command = requestCommand.getCommand();

        Challenge challenge = Challenge.from(command.getChallengeId(), retrieveEvents());

        command.getKatas().forEach(kata -> {
            challenge.addNewKata(kata.identity(), kata.Purpose(), kata.Description(), kata.LimitOfTime());
        });

        emit().onSuccess(new ResponseEvents(challenge.getUncommittedChanges()));
    }
}
