package com.challenge.usecase;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.support.RequestCommand;
import com.challenge.domain.challenge.commands.CreateChallengeCommand;
import com.challenge.domain.challenge.events.AssignedAssessment;
import com.challenge.domain.challenge.events.ChangedDurationDays;
import com.challenge.domain.challenge.events.CreatedChallenge;
import com.challenge.domain.challenge.values.Assesment;
import com.challenge.domain.challenge.values.ChallengeId;
import com.challenge.domain.clan.events.UpdateScoreOfMember;
import com.challenge.domain.clan.events.UpdatedName;
import com.challenge.domain.dojo.commands.CreateDojoCommand;
import com.challenge.domain.dojo.values.DojoId;
import com.challenge.domain.externalValues.Name;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class CreateChallengeUseCaseTest  extends UseCaseHandleBaseTest{

    @Test
    void createChallengeUseCaseTest_happyPath() throws InterruptedException{
        var useCase = new CreateChallengeUseCase();

        var request = new RequestCommand<>(new CreateChallengeCommand(
                ChallengeId.of("101"),
                new Name("ChallengeDDD"),
                DojoId.of("123"),
                new Assesment(5,"https://github.com/Sofka/DDD_Challenge","Resumen challenge DDD"),
                3
        ));

        UseCaseHandler.getInstance()
                .setIdentifyExecutor("101")
                .asyncExecutor(useCase, request)
                .subscribe(subscriber);
        Thread.sleep(1000);

        verify(subscriber, times(4)).onNext(eventCaptor.capture());

        CreatedChallenge createdChallenge =  (CreatedChallenge) eventCaptor.getAllValues().get(0);
        Assertions.assertEquals("123",createdChallenge.getDojoId().value());

        com.challenge.domain.challenge.events.UpdatedName updatedName =  (com.challenge.domain.challenge.events.UpdatedName) eventCaptor.getAllValues().get(1);
        Assertions.assertEquals("ChallengeDDD",updatedName.getName().value());

        AssignedAssessment assignedAssessment = (AssignedAssessment) eventCaptor.getAllValues().get(2);
        Assertions.assertEquals(5, assignedAssessment.getDegreeOfDifficulty());

        ChangedDurationDays changedDurationDays = (ChangedDurationDays)  eventCaptor.getAllValues().get(3);
        Assertions.assertEquals(3, changedDurationDays.getDurationDays());
    }
}