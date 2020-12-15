package com.challenge.domain.clan;


import co.com.sofka.domain.generic.Entity;
import com.challenge.domain.clan.values.Email;
import com.challenge.domain.clan.values.Gender;
import com.challenge.domain.clan.values.MemberId;
import com.challenge.domain.clan.values.Score;
import com.challenge.domain.externalValues.PersonId;
import com.challenge.domain.externalValues.MemberGit;
import com.challenge.domain.externalValues.Name;

import java.util.ArrayList;
import java.util.List;

public class Member extends Entity<MemberId> {
    private  Name name;
    private  Gender gender;
    private  Email email;
    private final boolean isOwner;
    private final PersonId personId;
    private final MemberGit memberGit;
    private final List<Score> scoreList;

    public Member(MemberId memberId,
                  PersonId personId,
                  MemberGit memberGit,
                  boolean isOwner) {
        super(memberId);
        this.isOwner = isOwner;
        this.personId = personId;
        this.memberGit = memberGit;
        this.scoreList = new ArrayList<>();
    }

    public void addScore(Score score) {
        this.scoreList.add(score);
    }

    public void updateEmail(Email email) {
        this.email = new Email(email.value());
    }

    public void updateName(Name name) {
        this.name = new Name(name.value());
    }

    public void updateGender(Gender gender) {
        this.gender =  new Gender((char)gender.value());
    }

    public Name Name() {
        return name;
    }

    public Gender Gender() {
        return gender;
    }

    public boolean isOwner() {
        return isOwner;
    }

    public PersonId PersonId() {
        return personId;
    }

    public MemberGit MemberGit() {
        return memberGit;
    }

    public Email Email() {
        return email;
    }

    public List<Score> ScoreList() {
        return scoreList;
    }
}
