package com.challenge.domain.clan.values;

import co.com.sofka.domain.generic.Identity;

public class MemberId extends Identity {
    public MemberId(String value) {
        super(value);
    }

    public MemberId() {
    }

    public static MemberId of(String value) {
        return new MemberId(value);
    }

}
