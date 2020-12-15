package com.challenge.domain.clan.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.challenge.domain.clan.values.Email;
import com.challenge.domain.clan.values.Gender;
import com.challenge.domain.clan.values.MemberId;
import com.challenge.domain.externalValues.Name;

public class UpdatedMember extends DomainEvent {
    private final Email email;
    private final Gender gender;
    private final Name name;
    private final MemberId memberId;


    public UpdatedMember(Email email, Gender gender, Name name, MemberId memberId) {
        super("com.challenge.domain.clan.UpdatedMember");
        this.email = email;
        this.gender = gender;
        this.name = name;
        this.memberId = memberId;
    }

    public Email getEmail() {
        return email;
    }

    public Gender getGender() {
        return gender;
    }

    public Name getName() {
        return name;
    }

    public MemberId getMemberId() {
        return memberId;
    }
}
