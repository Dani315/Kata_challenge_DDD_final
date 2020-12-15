package com.challenge.domain.challenge.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.challenge.domain.externalValues.Name;

public class UpdatedName extends DomainEvent {
    private final Name name;

    public UpdatedName(Name name) {
        super("com.challenge.domain.challenge.UpdatedName");
        this.name = name;
    }

    public Name getName() {
        return name;
    }
}
