package com.challenge.domain.challenge.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.challenge.domain.challenge.values.KataId;

public class UpdatedKata extends DomainEvent {
    private final Integer limitOfTime;
    private final KataId kataId;

    public UpdatedKata(Integer limitOfTime, KataId kataId) {
        super("com.challenge.domain.challenge.UpdatedKata");
        this.limitOfTime = limitOfTime;
        this.kataId = kataId;
    }

    public Integer getLimitOfTime() {
        return limitOfTime;
    }

    public KataId getKataId() {
        return kataId;
    }
}
