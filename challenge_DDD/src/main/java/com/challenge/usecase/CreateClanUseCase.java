package com.challenge.usecase;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import com.challenge.domain.clan.Clan;
import com.challenge.domain.clan.commands.CreateClanCommand;
import com.challenge.domain.clan.values.ClanId;
import com.challenge.domain.clan.values.Color;
import com.challenge.domain.externalValues.Name;

import java.util.Map;

public class CreateClanUseCase  extends UseCase<RequestCommand<CreateClanCommand>, ResponseEvents> {
    private final Map<Integer, String> colores = Map.of(
            1, "#000000",
            2, "#FFFFFF",
            3, "#FF0000",
            4, "#00FF00",
            5, "#0000FF",
            6, "#FFFF00",
            7, "#00FFFF",
            9, "#FF00FF",
            10, "#C2C2C2"
    );

    @Override
    public void executeUseCase(RequestCommand<CreateClanCommand> requestCommand) {
        var command = requestCommand.getCommand();

        Clan clan = new Clan(new ClanId(command.getClanId().value()), command.getGroupGit());

        clan.applyColor(new Color(colores.get(2)));

        clan.updateName(new Name(command.getName().value()));

        clan.addNewMember(command.getMemberId(),command.getPersonId(),command.getMemberGit(), command.isOwner());

        clan.updateMember(command.getEmail(), command.getGender(), command.getNameMember(), command.getMemberId());

        emit().onSuccess(new ResponseEvents(clan.getUncommittedChanges()));
    }

}
