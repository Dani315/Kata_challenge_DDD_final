package com.challenge.domain.dojo.values;

import co.com.sofka.domain.generic.Identity;

public class SenseiId extends Identity {

    public SenseiId(String value) {
        super(value);
    }

    public SenseiId() {
    }

    public static SenseiId of(String value) {
        return new SenseiId(value);
    }
}
