package com.challenge.usecase;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.support.RequestCommand;
import com.challenge.domain.clan.commands.CreateClanCommand;
import com.challenge.domain.clan.events.*;
import com.challenge.domain.clan.values.ClanId;
import com.challenge.domain.clan.values.Email;
import com.challenge.domain.clan.values.Gender;
import com.challenge.domain.clan.values.MemberId;
import com.challenge.domain.externalValues.PersonId;
import com.challenge.domain.externalValues.GroupGit;
import com.challenge.domain.externalValues.MemberGit;
import com.challenge.domain.externalValues.Name;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class CreateClanUseCaseTest extends UseCaseHandleBaseTest{
    @Test
    void createClanUseCaseTest_happyPath() throws InterruptedException{
        var useCase = new CreateClanUseCase();
        var request = new RequestCommand<>(new CreateClanCommand(
                ClanId.of("INGLATERRAID"),
                new GroupGit(1, "https://github.com/INGLATERRAGROUP", "INGLATERRAREPOSITORY"),
                new Name("INGLATERRA"),
                new Name("Daniela Gaviria Mena"),
                new Gender('F'),
                new Email("dgaviriamena@gmail.com"),
                true,
                new PersonId("1001359866"),
                new MemberGit(1, "safefdsfsdf", "@Dani315"),
                new MemberId("123456")
        ));

        UseCaseHandler.getInstance()
                .setIdentifyExecutor("INGLATERRAID")
                .asyncExecutor(useCase, request)
                .subscribe(subscriber);
        Thread.sleep(1000);

        verify(subscriber, times(5)).onNext(eventCaptor.capture());

        CreatedClan createdClan = (CreatedClan) eventCaptor.getAllValues().get(0);
        Assertions.assertEquals("INGLATERRAREPOSITORY", createdClan.getGroupGit().value().name());

        AppliedColor appliedColor = (AppliedColor) eventCaptor.getAllValues().get(1);
        Assertions.assertEquals("#FFFFFF", appliedColor.getColor().value());

        UpdatedName updatedName = (UpdatedName) eventCaptor.getAllValues().get(2);
        Assertions.assertEquals("INGLATERRA", updatedName.getName().value());

        AddedMember addedMember = (AddedMember) eventCaptor.getAllValues().get(3);
        Assertions.assertEquals(true, addedMember.isOwner());

        UpdatedMember updatedMember = (UpdatedMember) eventCaptor.getAllValues().get(4);
        Assertions.assertEquals("Daniela Gaviria Mena", updatedMember.getName().value());

    }
}