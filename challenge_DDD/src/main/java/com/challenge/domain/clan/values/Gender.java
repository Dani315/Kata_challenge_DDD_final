package com.challenge.domain.clan.values;

import co.com.sofka.domain.generic.ValueObject;

public class Gender implements ValueObject {
    private final char gender;

    public Gender(char gender) {
        this.gender = gender;
    }

    @Override
    public Object value() {
        return gender;
    }
}
