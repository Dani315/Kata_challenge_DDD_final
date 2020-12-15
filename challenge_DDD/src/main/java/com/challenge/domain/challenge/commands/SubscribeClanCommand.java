package com.challenge.domain.challenge.commands;

import co.com.sofka.domain.generic.Command;
import com.challenge.domain.challenge.values.ChallengeId;
import com.challenge.domain.clan.values.ClanId;

public class SubscribeClanCommand implements Command {
    private ClanId clanId;
    private ChallengeId challengeId;

    public SubscribeClanCommand(ClanId clanId, ChallengeId challengeId) {
        this.clanId = clanId;
        this.challengeId = challengeId;
    }

    public ClanId getClanId() {
        return clanId;
    }

    public ChallengeId getChallengeId() {
        return challengeId;
    }
}
