package com.challenge.usecase;


import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import com.challenge.domain.clan.Clan;
import com.challenge.domain.clan.commands.AddNewMemberToClanCommand;

public class AddNewMemberToClanUseCase extends UseCase<RequestCommand<AddNewMemberToClanCommand>, ResponseEvents> {

    @Override
    public void executeUseCase(RequestCommand<AddNewMemberToClanCommand> requestCommand) {

        var command = requestCommand.getCommand();

        Clan clan = Clan.from(command.getClanId(), retrieveEvents());

        clan.addNewMember(command.getMemberId(),command.getPersonId(), command.getMemberGit(), command.isOwner());

        clan.updateMember(command.getEmail(), command.getGender(), command.getNameMember(), command.getMemberId());

        emit().onSuccess(new ResponseEvents(clan.getUncommittedChanges()));
    }
}
