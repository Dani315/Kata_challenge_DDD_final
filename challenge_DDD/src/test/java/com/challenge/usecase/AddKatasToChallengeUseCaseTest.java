package com.challenge.usecase;


import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.support.RequestCommand;
import com.challenge.domain.challenge.Kata;
import com.challenge.domain.challenge.commands.AddKatasToChallengeCommand;
import com.challenge.domain.challenge.events.AddedKata;
import com.challenge.domain.challenge.events.AssignedAssessment;
import com.challenge.domain.challenge.events.CreatedChallenge;
import com.challenge.domain.challenge.events.UpdatedName;
import com.challenge.domain.challenge.values.Assesment;
import com.challenge.domain.challenge.values.ChallengeId;
import com.challenge.domain.challenge.values.KataId;
import com.challenge.domain.clan.commands.CreateClanCommand;
import com.challenge.domain.clan.events.AppliedColor;
import com.challenge.domain.clan.events.CreatedClan;
import com.challenge.domain.clan.values.*;
import com.challenge.domain.dojo.values.DojoId;
import com.challenge.domain.externalValues.GroupGit;
import com.challenge.domain.externalValues.MemberGit;
import com.challenge.domain.externalValues.Name;
import com.challenge.domain.externalValues.PersonId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class AddKatasToChallengeUseCaseTest  extends UseCaseHandleBaseTest{
    @Test
    void addKatasToChallengeUseCaseTest_happyPath()  throws InterruptedException{
        var useCase = new AddKatasToChallengeUseCase();

        List<Kata> katas  = new ArrayList<>();
        katas.add(new Kata(
                new KataId("kata1"),
                "Aprender diseño web",
                "Diseño web con framework react",
                24));
        katas.add(new Kata(
                new KataId("kata2"),
                "Aprender DDD",
                "Aprender a diseñar modelos de dominio",
                20));

        when(repository.getEventsBy(anyString())).thenReturn(List.of(
            new CreatedChallenge( DojoId.of("123")),
            new UpdatedName(new Name("ChallengeDDD")),
            new AssignedAssessment(5,"https://github.com/Sofka/DDD_Challenge","Resumen challenge DDD")
        ));

        useCase.addRepository(repository);

        var request = new RequestCommand<>(new AddKatasToChallengeCommand(
            katas,
            ChallengeId.of("101")
        ));

        UseCaseHandler.getInstance()
                .setIdentifyExecutor("101")
                .asyncExecutor(useCase, request)
                .subscribe(subscriber);
        Thread.sleep(1000);

        verify(subscriber, times(2)).onNext(eventCaptor.capture());

        AddedKata addedKata = (AddedKata) eventCaptor.getAllValues().get(0);
        Assertions.assertEquals("Aprender diseño web", addedKata.getPurpose());

        addedKata = (AddedKata) eventCaptor.getAllValues().get(1);
        Assertions.assertEquals("Aprender DDD", addedKata.getPurpose());
    }
}