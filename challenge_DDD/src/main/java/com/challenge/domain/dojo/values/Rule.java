package com.challenge.domain.dojo.values;

import co.com.sofka.domain.generic.ValueObject;

public class Rule implements ValueObject {
    private final String rule;

    public Rule(String rule) {
        this.rule = rule;
    }

    @Override
    public String value() {
        return rule;
    }
}
