package com.challenge.usecase;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import com.challenge.domain.challenge.Challenge;
import com.challenge.domain.challenge.commands.RemoveExerciseToKataCommand;

public class RemoveExerciseToKataUseCase extends UseCase<RequestCommand<RemoveExerciseToKataCommand>, ResponseEvents> {
    @Override
    public void executeUseCase(RequestCommand<RemoveExerciseToKataCommand> requestCommand) {
        var command = requestCommand.getCommand();
        Challenge challenge = Challenge.from(command.getChallengeId(), retrieveEvents());

        challenge.removeExerciseOfKata(command.getKataId(),command.getExercise());

        emit().onSuccess(new ResponseEvents(challenge.getUncommittedChanges()));
    }
}
