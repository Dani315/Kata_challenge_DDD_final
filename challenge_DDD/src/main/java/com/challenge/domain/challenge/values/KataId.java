package com.challenge.domain.challenge.values;

import co.com.sofka.domain.generic.Identity;
import com.challenge.domain.clan.values.MemberId;

public class KataId extends Identity {

    public KataId(String value) {
        super(value);
    }

    public KataId() {
    }

    public static KataId of(String value) {
        return new KataId(value);
    }
}
