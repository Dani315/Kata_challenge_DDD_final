package com.challenge.usecase;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import com.challenge.domain.dojo.Dojo;
import com.challenge.domain.dojo.commands.CreateDojoCommand;

public class CreateDojoUseCase extends UseCase<RequestCommand<CreateDojoCommand>, ResponseEvents> {
    @Override
    public void executeUseCase(RequestCommand<CreateDojoCommand> requestCommand) {
        var command = requestCommand.getCommand();

        Dojo dojo = new Dojo(command.getDojoId(), command.getGroupGit(), command.getLocation());

        dojo.changeDataInfo(command.getDataInfo().value().name(), command.getDataInfo().value().legend());

        dojo.assignSensei(command.getSenseiId(),command.getPersonId(),command.getSpecialty());

        dojo.updateDataToSensei(command.getName(), command.getMemberGit());

        command.getAccomplishmentList().forEach(accomplishment -> {
            dojo.addAccomplishmentToSensei(accomplishment);
        });

        emit().onSuccess(new ResponseEvents(dojo.getUncommittedChanges()));

    }
}
