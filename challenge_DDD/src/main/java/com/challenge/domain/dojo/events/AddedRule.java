package com.challenge.domain.dojo.events;

import co.com.sofka.domain.generic.DomainEvent;

import java.util.UUID;

public class AddedRule extends DomainEvent {
    private final String rule;

    public AddedRule(String rule) {
        super("com.challenge.domain.dojo.AddedRule");
        this.rule = rule;
    }

    public String getRule() {
        return rule;
    }
}
