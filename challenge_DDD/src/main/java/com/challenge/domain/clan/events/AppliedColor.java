package com.challenge.domain.clan.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.challenge.domain.clan.values.Color;

public class AppliedColor extends DomainEvent {
    private final Color color;

    public AppliedColor(Color color) {
        super("com.challenge.domain.clan.AppliedColor");
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
