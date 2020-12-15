package com.challenge.domain.challenge.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.challenge.domain.dojo.values.DojoId;

public class CreatedChallenge extends DomainEvent {
    private final DojoId dojoId;

    public CreatedChallenge(DojoId dojoId) {
        super("com.challenge.domain.challenge.CreatedChallenge");
        this.dojoId = dojoId;
    }

    public DojoId getDojoId() {
        return dojoId;
    }
}
