package com.challenge.domain.dojo.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.challenge.domain.dojo.values.Rule;

public class RuleRemoved extends DomainEvent {
    private final Rule rule;

    public RuleRemoved(Rule rule) {
        super("com.challenge.domain.dojo.RuleRemoved");
        this.rule = rule;
    }

    public Rule getRule() {
        return rule;
    }
}
