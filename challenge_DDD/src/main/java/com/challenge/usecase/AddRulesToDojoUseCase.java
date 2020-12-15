package com.challenge.usecase;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import com.challenge.domain.dojo.Dojo;
import com.challenge.domain.dojo.commands.AddRulesToDojoCommand;

public class AddRulesToDojoUseCase  extends UseCase<RequestCommand<AddRulesToDojoCommand>, ResponseEvents> {
    @Override
    public void executeUseCase(RequestCommand<AddRulesToDojoCommand> requestCommand) {
        var command = requestCommand.getCommand();

        Dojo dojo = Dojo.from(command.getDojoId(), retrieveEvents());

        command.getRules().forEach(rule -> {
            dojo.addRule(rule);
        });

        emit().onSuccess(new ResponseEvents(dojo.getUncommittedChanges()));
    }
}
