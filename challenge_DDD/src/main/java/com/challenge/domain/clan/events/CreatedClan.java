package com.challenge.domain.clan.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.challenge.domain.externalValues.GroupGit;

public class CreatedClan extends DomainEvent {
    private final GroupGit groupGit;

    public CreatedClan(GroupGit groupGit) {
        super("com.challenge.domain.clan.CreatedClan");
        this.groupGit = groupGit;
    }

    public GroupGit getGroupGit() {
        return groupGit;
    }
}
