package com.challenge.domain.clan;

import co.com.sofka.domain.generic.AggregateEvent;
import co.com.sofka.domain.generic.DomainEvent;
import com.challenge.domain.clan.events.*;
import com.challenge.domain.clan.values.*;
import com.challenge.domain.externalValues.PersonId;
import com.challenge.domain.externalValues.GroupGit;
import com.challenge.domain.externalValues.MemberGit;
import com.challenge.domain.externalValues.Name;

import java.util.List;

public class Clan extends AggregateEvent<ClanId> {
    protected List<Member> members;
    protected GroupGit groupGit;
    protected Color color;
    protected Name name;

    public Clan(ClanId entityId,GroupGit groupGit) {
        super(entityId);
        appendChange(new CreatedClan(groupGit)).apply();
    }

    private Clan(ClanId entityId) {
        super(entityId);
        subscribe(new ClanState(this));
    }
    public static Clan from(ClanId entityId, List<DomainEvent> eventList) {
        var clan = new Clan(entityId);
        eventList.forEach(clan::applyEvent);
        return clan;
    }

    public void addNewMember(MemberId memberId, PersonId personId, MemberGit memberGit, boolean isOwner) {
        appendChange(new AddedMember(memberId,
                personId,
                memberGit,
                isOwner)).apply();
    }

    public void revokeMember(MemberId memberId) {
        appendChange(new RevokedMember(memberId)).apply();
    }

    public void applyColor(Color color) {
        appendChange(new AppliedColor(color)).apply();
    }

    public void addScoreToMember(Score score, MemberId memberId) {
        appendChange(new UpdateScoreOfMember(score, memberId)).apply();
    }

    public void updateMember(Email email, Gender gender, Name name,  MemberId memberId) {
        appendChange(new UpdatedMember(email, gender, name,  memberId)).apply();
    }

    public void updateName(Name name) {
        appendChange(new UpdatedName(name));
    }

    public List<Member> Members() {
        return members;
    }

    public GroupGit GroupGit() {
        return groupGit;
    }

    public Color Color() {
        return color;
    }

    public Name Name() {
        return name;
    }
}
