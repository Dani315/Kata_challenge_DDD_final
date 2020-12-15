package com.challenge.domain.challenge.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.challenge.domain.challenge.values.KataId;

public class AddedKata extends DomainEvent {
    private final KataId entityId;
    private final String purpose;
    private final String description;
    private final Integer limitOfTime;

    public AddedKata(KataId entityId, String purpose, String description, Integer limitOfTime) {
        super("com.challenge.domain.challenge.AddedKata");
        this.entityId = entityId;
        this.purpose = purpose;
        this.description = description;
        this.limitOfTime = limitOfTime;
    }

    public KataId getEntityId() {
        return entityId;
    }

    public String getPurpose() {
        return purpose;
    }

    public String getDescription() {
        return description;
    }

    public Integer getLimitOfTime() {
        return limitOfTime;
    }
}
