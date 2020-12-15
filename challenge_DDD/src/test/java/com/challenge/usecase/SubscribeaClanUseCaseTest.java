package com.challenge.usecase;


import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.support.RequestCommand;
import com.challenge.domain.challenge.commands.SubscribeClanCommand;
import com.challenge.domain.challenge.events.ClanSubscribed;
import com.challenge.domain.challenge.events.CreatedChallenge;
import com.challenge.domain.challenge.values.ChallengeId;
import com.challenge.domain.clan.values.ClanId;
import com.challenge.domain.dojo.values.DojoId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class SubscribeaClanUseCaseTest extends UseCaseHandleBaseTest{

    @Test
    void subscribeaClanUseCaseTest_happyPath() throws InterruptedException{
        var useCase = new SubscribeaClanUseCase();

        when(repository.getEventsBy(anyString())).thenReturn(List.of(
            new CreatedChallenge(DojoId.of("123"))
        ));

        useCase.addRepository(repository);

        var request = new RequestCommand<>(new SubscribeClanCommand(
                ClanId.of("INGLATERRAID"),
                ChallengeId.of("101")
        ));

        UseCaseHandler.getInstance()
                .setIdentifyExecutor("123")
                .asyncExecutor(useCase, request)
                .subscribe(subscriber);
        Thread.sleep(1000);

        verify(subscriber, times(1)).onNext(eventCaptor.capture());

        ClanSubscribed clanSubscribed =  (ClanSubscribed) eventCaptor.getAllValues().get(0);
        Assertions.assertEquals("INGLATERRAID",clanSubscribed.getClanId().value());

    }
}