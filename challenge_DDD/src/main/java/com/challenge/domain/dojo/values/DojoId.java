package com.challenge.domain.dojo.values;

import co.com.sofka.domain.generic.Identity;

public class DojoId extends Identity {

    public DojoId(String value) {
        super(value);
    }

    public DojoId() {
    }

    public static DojoId of(String value) {
        return new DojoId(value);
    }
}
