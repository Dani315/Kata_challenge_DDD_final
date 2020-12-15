package com.challenge.domain.clan.values;

import co.com.sofka.domain.generic.Identity;
import com.challenge.domain.dojo.values.DojoId;

public class ClanId extends Identity {
    public ClanId(String value) {
        super(value);
    }

    public ClanId() {
    }

    public static ClanId of(String value) {
        return new ClanId(value);
    }
}
