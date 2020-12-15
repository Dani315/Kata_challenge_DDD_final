package com.challenge.usecase;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.support.RequestCommand;
import com.challenge.domain.clan.commands.AddNewMemberToClanCommand;
import com.challenge.domain.clan.events.*;
import com.challenge.domain.clan.values.*;
import com.challenge.domain.externalValues.PersonId;
import com.challenge.domain.externalValues.GroupGit;
import com.challenge.domain.externalValues.MemberGit;
import com.challenge.domain.externalValues.Name;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class AddNewMemberToClanUseCaseTest extends UseCaseHandleBaseTest{
    @Test
    void addNewMemberToClanUseCaseTest_happyPath() throws InterruptedException{
        var useCase = new AddNewMemberToClanUseCase();
        when(repository.getEventsBy(anyString())).thenReturn(List.of(
                new CreatedClan(new GroupGit(1, "https://github.com/INGLATERRAGROUP", "INGLATERRAREPOSITORY")),
                new AppliedColor(new Color("FFFFFF")),
                new UpdatedName(new Name("INGLATERRA"))
        ));

        useCase.addRepository(repository);

        var request = new RequestCommand<>(new AddNewMemberToClanCommand(
                new Name("Daniela Gaviria Mena"),
                new Gender('F'),
                new Email("dgaviriamena@gmail.com"),
                false,
                PersonId.of("1001359866"),
                new MemberGit(1234, "sssfsdgdrgref", "@Dani315"),
                MemberId.of("123456"),
                ClanId.of("INGLATERRAID")
        ));

        UseCaseHandler.getInstance()
                .setIdentifyExecutor("INGLATERRAID")
                .asyncExecutor(useCase, request)
                .subscribe(subscriber);
        Thread.sleep(1000);

        verify(subscriber, times(2)).onNext(eventCaptor.capture());

        AddedMember addedMember = (AddedMember) eventCaptor.getAllValues().get(0);
        Assertions.assertEquals("1001359866", addedMember.getPersonId().value());

        UpdatedMember updatedMember = (UpdatedMember) eventCaptor.getAllValues().get(1);
        Assertions.assertEquals("dgaviriamena@gmail.com", updatedMember.getEmail().value());
    }
}