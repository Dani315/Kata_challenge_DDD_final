package com.challenge.domain.dojo;

import co.com.sofka.domain.generic.Entity;
import com.challenge.domain.dojo.values.Accomplishment;
import com.challenge.domain.dojo.values.SenseiId;
import com.challenge.domain.dojo.values.Specialty;
import com.challenge.domain.externalValues.PersonId;
import com.challenge.domain.externalValues.MemberGit;
import com.challenge.domain.externalValues.Name;

import java.util.ArrayList;
import java.util.List;

public class Sensei extends Entity<SenseiId> {
    private Name name;
    private Specialty specialty;
    private MemberGit memberGit;
    private final PersonId personId;
    private final List<Accomplishment> accomplishmentList;


    public Sensei(SenseiId senseiId,
                  PersonId personId) {
        super(senseiId);
        this.name = name;
        this.personId = personId;
        this.accomplishmentList = new ArrayList<>();
    }

    public void update(Name name, MemberGit memberGit) {
        this.name = new Name(name.value());
        this.memberGit = new MemberGit(
                memberGit.value().memberGitId(),
                memberGit.value().imageAvatarUrl(),
                memberGit.value().userName());
    }

    public void changeSpecialty(Specialty specialty) {
        this.specialty = new Specialty(specialty.value());
    }

    public void addAccomplishment(Accomplishment accomplishment) {
        this.accomplishmentList.add(accomplishment);
    }

    public Name Name() {
        return name;
    }

    public PersonId PersonId() {
        return personId;
    }

    public Specialty Specialty() {
        return specialty;
    }

    public MemberGit MemberGit() {
        return memberGit;
    }

    public List<Accomplishment> AccomplishmentList() {
        return accomplishmentList;
    }
}
