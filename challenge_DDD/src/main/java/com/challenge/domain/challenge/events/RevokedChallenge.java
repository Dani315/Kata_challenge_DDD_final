package com.challenge.domain.challenge.events;

import co.com.sofka.domain.generic.DomainEvent;

public class RevokedChallenge extends DomainEvent {
    public RevokedChallenge() {
        super("com.challenge.domain.challenge.RevokedChallenge");
    }
}
