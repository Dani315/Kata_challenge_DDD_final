package com.challenge.domain.clan.values;

import co.com.sofka.domain.generic.ValueObject;

public class Color implements ValueObject {
    private final String color;

    public Color(String color) {
        this.color = color;
    }

    @Override
    public String value() {
        return color;
    }
}
