package com.challenge.domain.clan.values;

import co.com.sofka.domain.generic.ValueObject;

public class Email implements ValueObject {
    private final String email;

    public Email(String email) {
        this.email = email;
    }

    @Override
    public String value() {
        return email;
    }
}
