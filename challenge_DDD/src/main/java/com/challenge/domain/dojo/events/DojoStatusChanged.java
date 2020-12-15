package com.challenge.domain.dojo.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.challenge.domain.dojo.Enums.Status;

public class DojoStatusChanged extends DomainEvent {
    private final Status status;

    public DojoStatusChanged(Status status) {
        super("com.challenge.domain.dojo.DojoStatusChanged");
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }
}
