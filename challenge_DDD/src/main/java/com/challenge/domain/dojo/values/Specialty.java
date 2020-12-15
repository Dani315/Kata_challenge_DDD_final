package com.challenge.domain.dojo.values;

import co.com.sofka.domain.generic.ValueObject;

public class Specialty implements ValueObject {
    private final String specialty;

    public Specialty(String specialty) {
        this.specialty = specialty;
    }

    @Override
    public String value() {
        return specialty;
    }
}
