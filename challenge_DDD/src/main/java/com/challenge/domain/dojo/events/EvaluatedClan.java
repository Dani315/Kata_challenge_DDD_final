package com.challenge.domain.dojo.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.challenge.domain.clan.values.ClanId;
import com.challenge.domain.dojo.values.DojoId;

public class EvaluatedClan extends DomainEvent {
    private final int point;
    private final DojoId dojoId;
    private final ClanId clanId;

    public EvaluatedClan(int point, DojoId dojoId, ClanId clanId) {
        super("com.challenge.domain.dojo.EvaluatedClan");
        this.point = point;
        this.dojoId = dojoId;
        this.clanId = clanId;
    }

    public int getPoint() {
        return point;
    }

    public DojoId getDojoId() {
        return dojoId;
    }

    public ClanId getClanId() {
        return clanId;
    }
}
