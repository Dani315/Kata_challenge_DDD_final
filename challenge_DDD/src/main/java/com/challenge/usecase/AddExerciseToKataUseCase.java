package com.challenge.usecase;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import com.challenge.domain.challenge.Challenge;
import com.challenge.domain.challenge.commands.AddExerciseToKataCommand;

public class AddExerciseToKataUseCase extends UseCase<RequestCommand<AddExerciseToKataCommand>, ResponseEvents> {
    @Override
    public void executeUseCase(RequestCommand<AddExerciseToKataCommand> requestCommand) {
        var command = requestCommand.getCommand();
        Challenge challenge = Challenge.from(command.getChallengeId(), retrieveEvents());

        command.getExercises().forEach(exercise -> {
            challenge.addExerciseOfKata(
                    command.getKataId(),
                    exercise.value().level(),
                    exercise.value().metadata(),
                    exercise.value().goal());
        });

        emit().onSuccess(new ResponseEvents(challenge.getUncommittedChanges()));
    }
}
