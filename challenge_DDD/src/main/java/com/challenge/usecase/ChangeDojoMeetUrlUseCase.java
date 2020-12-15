package com.challenge.usecase;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import com.challenge.domain.dojo.Dojo;
import com.challenge.domain.dojo.commands.ChangeDojoMeetUrlCommand;

public class ChangeDojoMeetUrlUseCase extends UseCase<RequestCommand<ChangeDojoMeetUrlCommand>, ResponseEvents> {
    @Override
    public void executeUseCase(RequestCommand<ChangeDojoMeetUrlCommand> requestCommand) {
        var command = requestCommand.getCommand();

        Dojo dojo = Dojo.from(command.getDojoId(), retrieveEvents());

        dojo.changeLocation(command.getUrlMeet());

        emit().onSuccess(new ResponseEvents(dojo.getUncommittedChanges()));
    }
}
