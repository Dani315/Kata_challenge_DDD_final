package com.challenge.usecase;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.support.RequestCommand;
import com.challenge.domain.dojo.Enums.Frequency;
import com.challenge.domain.dojo.commands.CreateDojoCommand;
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

class CreateDojoUseCaseTest extends UseCaseHandleBaseTest{

    @Test
    void assignSenseiToDojoTest_happyPath() throws InterruptedException{
        var useCase = new CreateDojoUseCase();

        List<Accomplishment> accomplishmentList = new ArrayList<>();
        accomplishmentList.add(new Accomplishment("Label1",2500, new Date()));
        accomplishmentList.add(new Accomplishment("Label2",1500, new Date()));

        var request = new RequestCommand<>(new CreateDojoCommand(
                DojoId.of("123"),
                new GroupGit(1,"https://github.com/Dojo1","GroupDojo1"),
                new Location(
                        "https://meet.google.com/vbe-wcuo-aur",
                        "Meet",
                        "Descripción Dojo",
                        new OpeningHours(2,6, Frequency.EACH_THREE_DAYS)),
                new DataInfo("Dojo1", "legenda dojo1"),

                SenseiId.of("1xxx"),
                new Name("Raul"),
                new PersonId("123456789"),
                new Specialty("Lider técnico"),
                new MemberGit(1234, "sssfsdgdrgref", "@raul"),
                accomplishmentList
        ));

        UseCaseHandler.getInstance()
                .setIdentifyExecutor("123")
                .asyncExecutor(useCase, request)
                .subscribe(subscriber);
        Thread.sleep(1000);

        verify(subscriber, times(6)).onNext(eventCaptor.capture());

        CreatedDojo createdDojo = (CreatedDojo) eventCaptor.getAllValues().get(0);
        Assertions.assertEquals("Descripción Dojo", createdDojo.getLocation().value().description());

        ChangedDataInfo changedDataInfo = (ChangedDataInfo) eventCaptor.getAllValues().get(1);
        Assertions.assertEquals("Dojo1", changedDataInfo.getName());

        AssignedSensei assignedSensei = (AssignedSensei) eventCaptor.getAllValues().get(2);
        Assertions.assertEquals("123456789",assignedSensei.getPersonId().value());

        UpdatedDataSensei updatedDataSensei = (UpdatedDataSensei) eventCaptor.getAllValues().get(3);
        Assertions.assertEquals("Raul",updatedDataSensei.getName().value());

        AddedAccomplishmentToSensei addedAccomplishmentToSensei = (AddedAccomplishmentToSensei) eventCaptor.getAllValues().get(4);
        Assertions.assertEquals(2500,addedAccomplishmentToSensei.getAccomplishment().value().point());

    }

}