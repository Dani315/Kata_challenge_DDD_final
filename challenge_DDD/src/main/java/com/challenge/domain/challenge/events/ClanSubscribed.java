package com.challenge.domain.challenge.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.challenge.domain.clan.values.ClanId;

public class ClanSubscribed extends DomainEvent {
    private  final ClanId clanId;

    public ClanSubscribed(ClanId clanId) {
        super("com.challenge.domain.challenge.ClanSubscribed");
        this.clanId = clanId;
    }

    public ClanId getClanId() {
        return clanId;
    }
}
