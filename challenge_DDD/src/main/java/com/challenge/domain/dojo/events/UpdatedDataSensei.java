package com.challenge.domain.dojo.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.challenge.domain.externalValues.MemberGit;
import com.challenge.domain.externalValues.Name;

public class UpdatedDataSensei extends DomainEvent {
    private final Name name;
    private final MemberGit memberGit;

    public UpdatedDataSensei(Name name, MemberGit memberGit) {
        super("com.challenge.domain.dojo.UpdatedDataSensei");
        this.name = name;
        this.memberGit = memberGit;
    }

    public Name getName() {
        return name;
    }

    public MemberGit getMemberGit() {
        return memberGit;
    }
}
