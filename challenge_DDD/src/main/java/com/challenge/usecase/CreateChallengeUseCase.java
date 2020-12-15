package com.challenge.usecase;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import com.challenge.domain.challenge.Challenge;
import com.challenge.domain.challenge.commands.CreateChallengeCommand;

public class CreateChallengeUseCase  extends UseCase<RequestCommand<CreateChallengeCommand>, ResponseEvents> {

    @Override
    public void executeUseCase(RequestCommand<CreateChallengeCommand> requestCommand) {

        var command = requestCommand.getCommand();

        Challenge challenge = new Challenge(command.getChallengeId(), command.getDojoId());

        challenge.updateName(command.getName());

        challenge.assignAssessment(
                command.getAssesment().value().degreeOfDifficulty(),
                command.getAssesment().value().repoUrl(),
                command.getAssesment().value().summary());

        challenge.changeDurationDays(command.getDurationDays());

        emit().onSuccess(new ResponseEvents(challenge.getUncommittedChanges()));
    }
}
