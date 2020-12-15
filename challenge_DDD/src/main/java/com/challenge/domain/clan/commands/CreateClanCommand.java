package com.challenge.domain.clan.commands;

import co.com.sofka.domain.generic.Command;
import com.challenge.domain.clan.values.ClanId;
import com.challenge.domain.clan.values.Email;
import com.challenge.domain.clan.values.Gender;
import com.challenge.domain.clan.values.MemberId;
import com.challenge.domain.externalValues.PersonId;
import com.challenge.domain.externalValues.GroupGit;
import com.challenge.domain.externalValues.MemberGit;
import com.challenge.domain.externalValues.Name;

public class CreateClanCommand implements Command {
    private ClanId clanId;
    private GroupGit groupGit;
    private Name name;
    private Name nameMember;
    private Gender gender;
    private Email email;
    private boolean isOwner;
    private PersonId personId;
    private MemberGit memberGit;
    private MemberId memberId;


    public CreateClanCommand(
            ClanId clanId,
            GroupGit groupGit, Name name,Name nameMember,
            Gender gender, Email email,
            boolean isOwner, PersonId personId,
            MemberGit memberGit, MemberId memberId) {
        this.clanId = clanId;
        this.groupGit = groupGit;
        this.name = name;
        this.nameMember = nameMember;
        this.gender = gender;
        this.email = email;
        this.isOwner = isOwner;
        this.personId = personId;
        this.memberGit = memberGit;
        this.memberId = memberId;
    }

    public GroupGit getGroupGit() {
        return groupGit;
    }

    public Name getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public Email getEmail() {
        return email;
    }

    public boolean isOwner() {
        return isOwner;
    }

    public PersonId getPersonId() {
        return personId;
    }

    public MemberGit getMemberGit() {
        return memberGit;
    }

    public MemberId getMemberId() {
        return memberId;
    }

    public ClanId getClanId() {
        return clanId;
    }

    public Name getNameMember() {
        return nameMember;
    }
}
