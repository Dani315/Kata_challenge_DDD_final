package com.challenge.domain.clan.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.challenge.domain.clan.values.MemberId;

public class RevokedMember extends DomainEvent {
    private final MemberId memberId;

    public RevokedMember(MemberId memberId) {
        super("com.challenge.domain.clan.RevokedMember");
        this.memberId = memberId;
    }

    public MemberId getMemberId() {
        return memberId;
    }
}
