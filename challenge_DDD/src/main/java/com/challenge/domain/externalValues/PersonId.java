package com.challenge.domain.externalValues;

import co.com.sofka.domain.generic.Identity;

public class PersonId extends Identity {
    public PersonId(String value) {
        super(value);
    }

    public PersonId() {
    }

    public static PersonId of(String value) {
        return new PersonId(value);
    }
}
