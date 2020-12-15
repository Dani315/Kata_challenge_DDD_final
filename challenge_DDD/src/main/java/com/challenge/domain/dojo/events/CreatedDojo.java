package com.challenge.domain.dojo.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.challenge.domain.dojo.values.DojoId;
import com.challenge.domain.dojo.values.Location;
import com.challenge.domain.externalValues.GroupGit;


public class CreatedDojo extends DomainEvent {

    private final GroupGit groupGit;
    private final Location location;

    public CreatedDojo(GroupGit groupGit, Location location) {
        super("com.challenge.domain.dojo.CreatedDojo");
        this.groupGit = groupGit;
        this.location = location;
    }

    public GroupGit getGroupGit() {
        return groupGit;
    }

    public Location getLocation() {
        return location;
    }
}
