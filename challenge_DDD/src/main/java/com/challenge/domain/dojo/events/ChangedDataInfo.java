package com.challenge.domain.dojo.events;

import co.com.sofka.domain.generic.DomainEvent;

public class ChangedDataInfo extends DomainEvent {
    private final String name;
    private final String legend;

    public ChangedDataInfo(String name, String legend) {
        super("com.challenge.domain.dojo.ChangedDataInfo");
        this.name = name;
        this.legend = legend;
    }

    public String getName() {
        return name;
    }

    public String getLegend() {
        return legend;
    }
}
