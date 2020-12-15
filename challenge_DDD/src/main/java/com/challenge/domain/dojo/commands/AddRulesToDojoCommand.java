package com.challenge.domain.dojo.commands;

import co.com.sofka.domain.generic.Command;
import com.challenge.domain.dojo.values.DojoId;

import java.util.List;

public class AddRulesToDojoCommand implements Command {
    private List<String> rules;
    private DojoId dojoId;

    public AddRulesToDojoCommand(List<String> rules, DojoId dojoId) {
        this.rules = rules;
        this.dojoId = dojoId;
    }

    public AddRulesToDojoCommand() {
    }

    public List<String> getRules() {
        return rules;
    }

    public DojoId getDojoId() {
        return dojoId;
    }
}
