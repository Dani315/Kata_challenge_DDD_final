package com.challenge.domain.clan;

import co.com.sofka.domain.generic.EventChange;
import co.com.sofka.domain.generic.Identity;
import com.challenge.domain.clan.events.*;
import com.challenge.domain.clan.values.Color;
import com.challenge.domain.externalValues.GroupGit;
import com.challenge.domain.externalValues.Name;

import java.util.ArrayList;

public class ClanState extends EventChange {
    public ClanState(Clan clan) {
        apply((CreatedClan event) -> {
            clan.members = new ArrayList<>();
            clan.groupGit = new GroupGit(
                    event.getGroupGit().value().groupId(),
                    event.getGroupGit().value().path(),
                    event.getGroupGit().value().name());
        });

        apply((AppliedColor event) -> {
            clan.color = new Color(event.getColor().value());
        });

        apply((UpdatedName event) -> {
            clan.name = new Name(event.getName().value());
        });

        apply((AddedMember event) -> {
            clan.members.add(new Member(
                    event.getMemberId(),
                    event.getPersonId(),
                    event.getMemberGit(),
                    event.isOwner()
            ));
        });

        apply((UpdatedMember event) -> {
            Member member = getMember(clan, event.getMemberId());
            member.updateName(event.getName());
            member.updateEmail(event.getEmail());
            member.updateGender(event.getGender());
        });

        apply((UpdateScoreOfMember event) -> {
            Member member = getMember(clan, event.getMemberId());
            member.addScore(event.getScore());
        });

        apply((RevokedMember event) -> {
            Member member = getMember(clan, event.getMemberId());
            clan.members.removeIf(member1 -> member1.equals(member));
        });
    }

    private Member getMember(Clan clan, Identity id) {
        return clan.Members()
                .stream()
                .filter(member -> member.identity().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("El miembro no existe"));
    }
}
