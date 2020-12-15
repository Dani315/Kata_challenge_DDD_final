package com.challenge.usecase.models;

import com.challenge.domain.clan.values.ClanId;

public class ClanEvaluation {
    private ClanId clanId;
    private Integer point;

    public ClanEvaluation(ClanId clanId, Integer point) {
        this.clanId = clanId;
        this.point = point;
    }

    public ClanId getClanId() {
        return clanId;
    }

    public Integer getPoint() {
        return point;
    }

}
