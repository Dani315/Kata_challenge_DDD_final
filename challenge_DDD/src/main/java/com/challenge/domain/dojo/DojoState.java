package com.challenge.domain.dojo;

import co.com.sofka.domain.generic.EventChange;
import com.challenge.domain.dojo.Enums.Status;
import com.challenge.domain.dojo.events.*;
import com.challenge.domain.dojo.values.DataInfo;
import com.challenge.domain.dojo.values.Location;
import com.challenge.domain.dojo.values.OpeningHours;
import com.challenge.domain.dojo.values.Rule;
import com.challenge.domain.externalValues.GroupGit;

import java.util.ArrayList;

public class DojoState extends EventChange {
    public DojoState(Dojo dojo) {
        apply((CreatedDojo event) -> {
            dojo.status = Status.CLOSED;
            dojo.rules =new ArrayList<>();
            dojo.groupGit = new GroupGit(
                    event.getGroupGit().value().groupId(),
                    event.getGroupGit().value().path(),
                    event.getGroupGit().value().name());
            dojo.location = new Location(
                    event.getLocation().value().urlMeet(),
                    event.getLocation().value().location(),
                    event.getLocation().value().description(),
                    new OpeningHours(
                            event.getLocation().value().openingHours().value().hourStart(),
                            event.getLocation().value().openingHours().value().hourEnd(),
                            event.getLocation().value().openingHours().value().frequency()));
        });

        apply((ChangedDataInfo event) -> {
            dojo.dataInfo = new DataInfo(event.getName(), event.getLegend());
        });

        apply((AssignedSensei event) -> {
            dojo.sensei = new Sensei(
                    event.getSenseiId(),
                    event.getPersonId());
            dojo.sensei.changeSpecialty(event.getSpecialty());
        });

        apply((UpdatedDataSensei event) -> {
            dojo.sensei.update(event.getName(), event.getMemberGit());
        });

        apply((AddedAccomplishmentToSensei event) -> {
            dojo.sensei.addAccomplishment(event.getAccomplishment());
        } );


        apply((AddedRule event) -> {
            dojo.rules.add(new Rule(event.getRule()));
        });

        apply((ChangedLocation event) -> {
            dojo.location = new Location(
                    event.getUrlMeet(),
                    dojo.location.value().location(),
                    dojo.location.value().description(),
                    new OpeningHours(
                            dojo.location.value().openingHours().value().hourStart(),
                            dojo.location.value().openingHours().value().hourEnd(),
                            dojo.location.value().openingHours().value().frequency()));
        });

        apply((DojoStatusChanged event) -> {
            dojo.status = event.getStatus();
        });

        apply((RuleRemoved event) -> {
            dojo.rules.removeIf(rule -> rule.value().equals(event.getRule().value()));
        });

    }
}
