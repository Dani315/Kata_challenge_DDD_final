package com.challenge.domain.challenge.values;

import co.com.sofka.domain.generic.Identity;
import com.challenge.domain.dojo.values.DojoId;

public class ChallengeId extends Identity {

    public ChallengeId(String value) {
        super(value);
    }

    public ChallengeId() {
    }

    public static ChallengeId of(String value) {
        return new ChallengeId(value);
    }
}
