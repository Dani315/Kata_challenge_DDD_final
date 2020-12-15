package com.challenge.domain.dojo;

import co.com.sofka.domain.generic.AggregateEvent;
import co.com.sofka.domain.generic.DomainEvent;
import com.challenge.domain.dojo.Enums.Status;
import com.challenge.domain.dojo.events.*;
import com.challenge.domain.dojo.values.*;
import com.challenge.domain.externalValues.PersonId;
import com.challenge.domain.externalValues.GroupGit;
import com.challenge.domain.externalValues.MemberGit;
import com.challenge.domain.externalValues.Name;

import java.util.List;

public class Dojo extends AggregateEvent<DojoId> {
    protected Sensei sensei;
    protected DataInfo dataInfo;
    protected Status status;
    protected List<Rule> rules;
    protected GroupGit groupGit;
    protected Location location;

    public Dojo(DojoId entityId,GroupGit groupGit, Location location) {
        super(entityId);
        appendChange(new CreatedDojo(groupGit, location)).apply();
    }

    public Dojo(DojoId entityId) {
        super(entityId);
        subscribe(new DojoState(this));
    }

    public static Dojo from(DojoId entityId, List<DomainEvent> eventList) {
        var dojo = new Dojo(entityId);
        eventList.forEach(dojo::applyEvent);
        return dojo;
    }

    public void changeLocation(String urlMeet) {
        appendChange(new ChangedLocation(urlMeet)).apply();
    }

    public void addRule(String rule) {
        appendChange(new AddedRule(rule)).apply();
    }

    public void assignSensei(SenseiId senseiId, PersonId personId, Specialty specialty) {
        appendChange(new AssignedSensei(senseiId, personId,specialty)).apply();
    }

    public void removeRule(Rule rule) {
        appendChange(new RuleRemoved(rule)).apply();
    }

    public void cancelDojo() {
        appendChange(new DojoStatusChanged(Status.CANCELLED)).apply();
    }

    public void openDojo(Status status) {
        appendChange(new DojoStatusChanged(Status.OPEN)).apply();
    }

    public void addAccomplishmentToSensei(Accomplishment accomplishment) {
        appendChange(new AddedAccomplishmentToSensei(accomplishment)).apply();
    }

    public void updateDataToSensei(Name name,MemberGit memberGit) {
        appendChange(new UpdatedDataSensei(name,memberGit)).apply();
    }

    public void closeDojo() {
        appendChange(new DojoStatusChanged(Status.CLOSED)).apply();
    }

    public void changeDataInfo(String name, String legend) {
        appendChange(new ChangedDataInfo(name, legend)).apply();
    }

}
