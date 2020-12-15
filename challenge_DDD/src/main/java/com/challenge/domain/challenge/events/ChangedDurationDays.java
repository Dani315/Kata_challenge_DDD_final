package com.challenge.domain.challenge.events;

import co.com.sofka.domain.generic.DomainEvent;

public class ChangedDurationDays extends DomainEvent {
    private final Integer durationDays;

    public ChangedDurationDays(Integer durationDays) {
        super("com.challenge.domain.challenge.ChangedDurationDays");
        this.durationDays = durationDays;
    }

    public Integer getDurationDays() {
        return durationDays;
    }
}
