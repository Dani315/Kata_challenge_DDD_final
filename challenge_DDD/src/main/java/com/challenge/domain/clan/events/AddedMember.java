package com.challenge.domain.clan.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.challenge.domain.clan.values.MemberId;
import com.challenge.domain.externalValues.PersonId;
import com.challenge.domain.externalValues.MemberGit;

public class AddedMember extends DomainEvent {
    private final MemberId memberId;
    private final PersonId personId;
    private final MemberGit memberGit;
    private final boolean isOwner;

    public AddedMember(MemberId memberId, PersonId personId, MemberGit memberGit, boolean isOwner) {
        super("com.challenge.domain.clan.AddedMember");
        this.memberId = memberId;
        this.personId = personId;
        this.memberGit = memberGit;
        this.isOwner = isOwner;
    }

    public MemberId getMemberId() {
        return memberId;
    }

    public PersonId getPersonId() {
        return personId;
    }

    public MemberGit getMemberGit() {
        return memberGit;
    }

    public boolean isOwner() {
        return isOwner;
    }
}
