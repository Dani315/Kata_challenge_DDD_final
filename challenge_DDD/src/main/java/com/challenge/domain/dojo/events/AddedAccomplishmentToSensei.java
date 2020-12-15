package com.challenge.domain.dojo.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.challenge.domain.dojo.values.Accomplishment;

public class AddedAccomplishmentToSensei extends DomainEvent {
    private final Accomplishment accomplishment;

    public AddedAccomplishmentToSensei(Accomplishment accomplishment) {
        super("com.challenge.domain.dojo.AddedAccomplishmentToSensei");
        this.accomplishment = accomplishment;
    }

    public Accomplishment getAccomplishment() {
        return accomplishment;
    }
}
