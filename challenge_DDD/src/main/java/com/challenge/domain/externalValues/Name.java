package com.challenge.domain.externalValues;

import co.com.sofka.domain.generic.ValueObject;

public class Name implements ValueObject {
    private final String name;

    public Name(String name) {
        this.name = name;
    }

    @Override
    public String value() {
        return name;
    }
}
