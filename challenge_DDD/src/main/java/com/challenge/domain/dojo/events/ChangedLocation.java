package com.challenge.domain.dojo.events;

import co.com.sofka.domain.generic.DomainEvent;

public class ChangedLocation extends DomainEvent {
    private final String urlMeet;

    public ChangedLocation(String urlMeet) {
        super("com.challenge.domain.dojo.ChangedLocation");
        this.urlMeet = urlMeet;
    }

    public String getUrlMeet() {
        return urlMeet;
    }
}
