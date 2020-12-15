package com.challenge.usecase;

import co.com.sofka.business.generic.ServiceBuilder;
import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.TriggeredEvent;
import co.com.sofka.domain.generic.DomainEvent;
import com.challenge.domain.challenge.events.*;
import com.challenge.domain.challenge.events.UpdatedName;
import com.challenge.domain.challenge.values.ChallengeId;
import com.challenge.domain.clan.events.*;
import com.challenge.domain.clan.values.*;
import com.challenge.domain.dojo.values.DojoId;
import com.challenge.domain.externalValues.GroupGit;
import com.challenge.domain.externalValues.MemberGit;
import com.challenge.domain.externalValues.Name;
import com.challenge.domain.externalValues.PersonId;
import com.challenge.usecase.models.ClanEvaluation;
import com.challenge.usecase.services.ClanEvaluationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.List;

import static org.mockito.Mockito.*;


class EvaluateClansByChallengeUseCaseTest extends UseCaseHandleBaseTest {
    private static final String CHALLENGE_ID = "101";
    private static final String CLAN_ID1 = "INGLATERRAID";
    private static final String CLAN_ID2 = "BIELORUSIAID";

    @Mock
    private ClanEvaluationService clanEvaluationService;

    @Spy
    private UpdateScoreToMemberUseCase updateScoreToMemberUseCase;

    @BeforeEach
    public void setup(){
        var repository = mock(DomainEventRepository.class);

        CreatedClan createdClan1 = new CreatedClan(new GroupGit(1, "https://github.com/INGLATERRAGROUP", "INGLATERRAREPOSITORY"));
        createdClan1.setAggregateRootId(CLAN_ID1);
        UpdatedName updatedName1 = new UpdatedName(new Name("INGLATERRA"));
        updatedName1.setAggregateRootId(CLAN_ID1);
        AddedMember addedMember1 = new AddedMember(MemberId.of("123456"), PersonId.of("1001359866"),new MemberGit(1234, "sssfsdgdrgref", "@Dani315"),false);
        addedMember1.setAggregateRootId(CLAN_ID1);
        AddedMember addedMember2 = new AddedMember(MemberId.of("654321"),PersonId.of("1001359877"),new MemberGit(1235, "lknlfhiwenf", "@CrisZulu"),false);
        addedMember2.setAggregateRootId(CLAN_ID1);

        CreatedClan createdClan2 = new CreatedClan(new GroupGit(1, "https://github.com/BIELORUSIA", "BIELORUSIAREPOSITORY"));
        createdClan2.setAggregateRootId(CLAN_ID2);
        UpdatedName updatedName2 = new UpdatedName(new Name("BIELORUSIA"));
        updatedName2.setAggregateRootId(CLAN_ID2);
        AddedMember addedMember12 = new AddedMember(MemberId.of("1234567"), PersonId.of("1001359888"),new MemberGit(1236, "sssfsdgdrgref", "@Tani315"),false);
        addedMember12.setAggregateRootId(CLAN_ID2);
        AddedMember addedMember13 = new AddedMember(MemberId.of("7654321"),PersonId.of("1001359899"),new MemberGit(1237, "lknlfhiwenf", "@CrisZas"),false);
        addedMember13.setAggregateRootId(CLAN_ID2);

        when(repository.getEventsBy(anyString())).thenReturn(List.of(
                createdClan1,
                updatedName1,
                addedMember1,
                addedMember2,
                createdClan2,
                updatedName2,
                addedMember12,
                addedMember13
                ));

        updateScoreToMemberUseCase.addRepository(repository);
    }

    @Test
    void evaluateClansByChallengeUseCaseTest_happyPath() throws InterruptedException{
        RevokedChallenge revokedChallenge = new RevokedChallenge();
        revokedChallenge.setAggregateRootId(CHALLENGE_ID);

        when(clanEvaluationService.getEvaluationsClan(any())).thenReturn(List.of(
                new ClanEvaluation(ClanId.of("INGLATERRAID"), 100),
                new ClanEvaluation(ClanId.of("BIELORUSIAID"), 200)
        ));

        /*when(repository.getEventsBy(anyString())).thenAnswer(new Answer<List<DomainEvent>>() {
            @Override
            public List<DomainEvent> answer(InvocationOnMock invocationOnMock)  {
                return List.of(
                        new CreatedChallenge(DojoId.of("123")),
                        new ClanSubscribed(ClanId.of("INGLATERRAID")),
                        new ClanSubscribed(ClanId.of("BIELORUSIAID"))
                );
            }
        });*/

        EvaluateClansByChallengeUseCase useCase = new EvaluateClansByChallengeUseCase(updateScoreToMemberUseCase, subscriber);
        useCase.addRepository(repository);

        useCase.addServiceBuilder(
                new ServiceBuilder()
                        .addService(clanEvaluationService)
        );

        //accion
        UseCaseHandler.getInstance()
                .setIdentifyExecutor(CHALLENGE_ID)
                .asyncExecutor(useCase, new TriggeredEvent<>(revokedChallenge))
                .subscribe(subscriber);
        Thread.sleep(1000);

        verify(subscriber, times(4)).onNext(eventCaptor.capture());

        UpdateScoreOfMember updateScoreOfMember = (UpdateScoreOfMember)eventCaptor.getAllValues().get(0);
        Assertions.assertEquals(100, updateScoreOfMember.getScore().value().point());
        System.out.println("Id miembro "+updateScoreOfMember.getMemberId());
        System.out.println("Puntos "+updateScoreOfMember.getScore().value().point());

        updateScoreOfMember = (UpdateScoreOfMember)eventCaptor.getAllValues().get(1);
        Assertions.assertEquals(100, updateScoreOfMember.getScore().value().point());
        System.out.println("Id miembro "+updateScoreOfMember.getMemberId());
        System.out.println("Puntos "+updateScoreOfMember.getScore().value().point());

        updateScoreOfMember = (UpdateScoreOfMember)eventCaptor.getAllValues().get(2);
        Assertions.assertEquals(200, updateScoreOfMember.getScore().value().point());
        System.out.println("Id miembro "+updateScoreOfMember.getMemberId());
        System.out.println("Puntos "+updateScoreOfMember.getScore().value().point());

        updateScoreOfMember = (UpdateScoreOfMember)eventCaptor.getAllValues().get(3);
        Assertions.assertEquals(200, updateScoreOfMember.getScore().value().point());
        System.out.println("Id miembro "+updateScoreOfMember.getMemberId());
        System.out.println("Puntos "+updateScoreOfMember.getScore().value().point());

    }

}