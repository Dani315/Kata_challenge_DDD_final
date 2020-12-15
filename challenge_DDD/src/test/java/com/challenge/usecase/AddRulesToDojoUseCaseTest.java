package com.challenge.usecase;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.support.RequestCommand;
import com.challenge.domain.dojo.Enums.Frequency;
import com.challenge.domain.dojo.commands.AddRulesToDojoCommand;
import com.challenge.domain.dojo.events.*;
import com.challenge.domain.dojo.values.*;
import com.challenge.domain.externalValues.PersonId;
import com.challenge.domain.externalValues.GroupGit;
import com.challenge.domain.externalValues.MemberGit;
import com.challenge.domain.externalValues.Name;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.anyString;


class AddRulesToDojoUseCaseTest extends UseCaseHandleBaseTest{
    @Test
    void addRulesToDojoTest_happyPath() throws InterruptedException{
        var useCase = new AddRulesToDojoUseCase();

        List<String> rules = new ArrayList<>();
        rules.add("No se puede hacer fraude");
        rules.add("Se solicita ser puntual");

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
            new AddedAccomplishmentToSensei(new Accomplishment("Label1",2500, new Date()))
        ));

        useCase.addRepository(repository);

        var request = new RequestCommand<>(new AddRulesToDojoCommand(
                rules,
                DojoId.of("123")
        ));

        UseCaseHandler.getInstance()
                .setIdentifyExecutor("123")
                .asyncExecutor(useCase, request)
                .subscribe(subscriber);
        Thread.sleep(1000);

        verify(subscriber, times(2)).onNext(eventCaptor.capture());

        AddedRule addedRule1 = (AddedRule) eventCaptor.getAllValues().get(0);
        Assertions.assertEquals("No se puede hacer fraude", addedRule1.getRule());

        AddedRule addedRule2 = (AddedRule) eventCaptor.getAllValues().get(1);
        Assertions.assertEquals("Se solicita ser puntual", addedRule2.getRule());
    }
}