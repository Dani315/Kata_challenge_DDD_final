package com.challenge.usecase;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.support.RequestCommand;
import com.challenge.domain.dojo.Enums.Frequency;
import com.challenge.domain.dojo.commands.ChangeDojoMeetUrlCommand;
import com.challenge.domain.dojo.events.*;
import com.challenge.domain.dojo.values.*;
import com.challenge.domain.externalValues.PersonId;
import com.challenge.domain.externalValues.GroupGit;
import com.challenge.domain.externalValues.MemberGit;
import com.challenge.domain.externalValues.Name;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class ChangeDojoMeetUrlUseCaseTest  extends UseCaseHandleBaseTest{
    @Test
    void changeDojoMeetUrlTest_happyPath() throws InterruptedException{
        var useCase = new ChangeDojoMeetUrlUseCase();

        when(repository.getEventsBy(anyString())).thenReturn(List.of(
                new CreatedDojo(
                        new GroupGit(1,"https://github.com/Dojo1","Group1"),
                        new Location(
                                "https://meet.google.com/vbe-wcuo-aur",
                                "Meet",
                                "Descripción Dojo",
                                new OpeningHours(2,6, Frequency.EACH_THREE_DAYS))),
                new ChangedDataInfo("Dojo1", "legenda dojo1"),
                new AssignedSensei(
                        SenseiId.of("1xxx"),
                        new PersonId("123456789"),
                        new Specialty("Lider técnico")),
                new UpdatedDataSensei( new Name("Raul"),
                        new MemberGit(1234, "sssfsdgdrgref", "@raul")),
                new AddedAccomplishmentToSensei(new Accomplishment("Label1",2500, new Date())),
                new AddedRule("No se puede hacer fraude")
        ));

        useCase.addRepository(repository);

        var request = new RequestCommand<>(new ChangeDojoMeetUrlCommand(
                "https://meet.google.com/mqa-ahrk-oek?hl=es&authuser=0",
                DojoId.of("123")
        ));

        UseCaseHandler.getInstance()
                .setIdentifyExecutor("123")
                .asyncExecutor(useCase, request)
                .subscribe(subscriber);
        Thread.sleep(1000);

        verify(subscriber, times(1)).onNext(eventCaptor.capture());

        ChangedLocation changedLocation = (ChangedLocation) eventCaptor.getAllValues().get(0);
        Assertions.assertEquals("https://meet.google.com/mqa-ahrk-oek?hl=es&authuser=0",changedLocation.getUrlMeet());
    }
}