package com.challenge.domain.clan.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.challenge.domain.clan.values.MemberId;
import com.challenge.domain.clan.values.Score;

public class UpdateScoreOfMember extends DomainEvent {
    private final Score score;
    private final MemberId memberId;

    public UpdateScoreOfMember(Score score, MemberId memberId) {
        super("com.challenge.domain.clan.UpdateScoreOfMember");
        this.score = score;
        this.memberId = memberId;
    }

    public Score getScore() {
        return score;
    }

    public MemberId getMemberId() {
        return memberId;
    }
}
