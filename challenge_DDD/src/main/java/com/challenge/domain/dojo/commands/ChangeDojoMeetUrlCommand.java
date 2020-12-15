package com.challenge.domain.dojo.commands;

import co.com.sofka.domain.generic.Command;
import com.challenge.domain.dojo.values.DojoId;

public class ChangeDojoMeetUrlCommand implements Command {
    private String urlMeet;
    private DojoId dojoId;

    public ChangeDojoMeetUrlCommand(String urlMeet, DojoId dojoId) {
        this.urlMeet = urlMeet;
        this.dojoId = dojoId;
    }

    public String getUrlMeet() {
        return urlMeet;
    }

    public DojoId getDojoId() {
        return dojoId;
    }
}
