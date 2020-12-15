package com.challenge.usecase;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.support.TriggeredEvent;
import com.challenge.domain.clan.events.*;
import com.challenge.domain.clan.values.*;
import com.challenge.domain.dojo.Dojo;
import com.challenge.domain.dojo.Enums.Frequency;
import com.challenge.domain.dojo.Enums.Status;
import com.challenge.domain.dojo.events.*;
import com.challenge.domain.dojo.values.*;
import com.challenge.domain.externalValues.GroupGit;
import com.challenge.domain.externalValues.MemberGit;
import com.challenge.domain.externalValues.Name;
import com.challenge.domain.externalValues.PersonId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class UpdateScoreToMemberUseCaseTest extends UseCaseHandleBaseTest {
    @Test
    void updateScoreToMemberUseCaseTest_happyPath() throws InterruptedException{
        var useCase = new UpdateScoreToMemberUseCase();

        CreatedDojo createdDojo =new CreatedDojo(
                new GroupGit(1,"https://github.com/Dojo1","Group1"),
                new Location(
                        "https://meet.google.com/vbe-wcuo-aur",
                        "Meet",
                        "Descripción Dojo",
                        new OpeningHours(2,6, Frequency.EACH_THREE_DAYS)));
        createdDojo.setAggregateRootId("123");

        DojoStatusChanged dojoStatusChanged = new DojoStatusChanged(Status.OPEN);
        dojoStatusChanged.setAggregateRootId("123");

        CreatedClan createdClan = new CreatedClan(new GroupGit(1, "https://github.com/INGLATERRAGROUP", "INGLATERRAREPOSITORY"));
        createdClan.setAggregateRootId("INGLATERRAID");
        AppliedColor appliedColor = new AppliedColor(new Color("FFFFFF"));
        appliedColor.setAggregateRootId("INGLATERRAID");
        UpdatedName updatedName = new UpdatedName(new Name("INGLATERRA"));
        updatedName.setAggregateRootId("INGLATERRAID");

        AddedMember addedMember1 = new AddedMember(MemberId.of("123456"),PersonId.of("1001359866"),new MemberGit(1234, "sssfsdgdrgref", "@Dani315"),false);
        addedMember1.setAggregateRootId("INGLATERRAID");
        UpdatedMember updatedMember1 = new UpdatedMember(new Email("dgaviriamena@gmail.com"),new Gender('F'),new Name("Daniela Gaviria Mena"),MemberId.of("123456"));
        updatedMember1.setAggregateRootId("INGLATERRAID");

        AddedMember addedMember2 = new AddedMember(MemberId.of("654321"),PersonId.of("1001359877"),new MemberGit(1235, "lknlfhiwenf", "@CrisZulu"),false);
        addedMember2.setAggregateRootId("INGLATERRAID");
        UpdatedMember updatedMember2 = new UpdatedMember(new Email("cristina@gmail.com"),new Gender('F'),new Name("Cristina Zuluaga"),MemberId.of("654321"));
        updatedMember2.setAggregateRootId("INGLATERRAID");

        when(repository.getEventsBy(anyString())).thenReturn(List.of(
                createdDojo,
                dojoStatusChanged,
                createdClan,
                appliedColor,
                updatedName,
                addedMember1,
                updatedMember1,
                addedMember2,
                updatedMember2
        ));

        useCase.addRepository(repository);

        EvaluatedClan evaluatedClan = new EvaluatedClan(80, DojoId.of("123"),ClanId.of("INGLATERRAID"));

        UseCaseHandler.getInstance()
                .setIdentifyExecutor("123")
                .asyncExecutor(useCase, new TriggeredEvent<>(evaluatedClan))
                .subscribe(subscriber);
        Thread.sleep(1000);

        verify(subscriber, times(2)).onNext(eventCaptor.capture());

        UpdateScoreOfMember updateScoreOfMember = (UpdateScoreOfMember) eventCaptor.getAllValues().get(0);
        Assertions.assertEquals("123456",updateScoreOfMember.getMemberId().value());

        updateScoreOfMember =  (UpdateScoreOfMember) eventCaptor.getAllValues().get(1);
        Assertions.assertEquals("654321",updateScoreOfMember.getMemberId().value());
    }
}