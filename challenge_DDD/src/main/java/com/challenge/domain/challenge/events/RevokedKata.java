package com.challenge.domain.challenge.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.challenge.domain.challenge.values.KataId;

public class RevokedKata extends DomainEvent {
    private final KataId kataId;

    public RevokedKata(KataId kataId) {
        super("com.challenge.domain.challenge.RevokedKata");
        this.kataId = kataId;
    }

    public KataId getKataId() {
        return kataId;
    }
}
