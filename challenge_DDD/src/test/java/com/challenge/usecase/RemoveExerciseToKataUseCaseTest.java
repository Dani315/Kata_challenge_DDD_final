package com.challenge.usecase;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.support.RequestCommand;
import com.challenge.domain.challenge.commands.AddExerciseToKataCommand;
import com.challenge.domain.challenge.commands.RemoveExerciseToKataCommand;
import com.challenge.domain.challenge.events.*;
import com.challenge.domain.challenge.values.ChallengeId;
import com.challenge.domain.challenge.values.Exercise;
import com.challenge.domain.challenge.values.KataId;
import com.challenge.domain.dojo.values.DojoId;
import com.challenge.domain.externalValues.Name;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class RemoveExerciseToKataUseCaseTest extends UseCaseHandleBaseTest{

    @Test
    void removeExerciseToKataUseCaseTest_happyPath() throws InterruptedException{
        var useCase = new RemoveExerciseToKataUseCase();

        when(repository.getEventsBy(anyString())).thenReturn(List.of(
                new CreatedChallenge(DojoId.of("123")),
                new UpdatedName(new Name("ChallengeDDD")),
                new AssignedAssessment(5,"https://github.com/Sofka/DDD_Challenge","Resumen challenge DDD"),
                new AddedKata(new KataId("kata1"),
                        "Aprender diseño web",
                        "Diseño web con framework react",
                        24),
                new AddedExerciseOfKata(
                        KataId.of("kata1"),1,
                        Map.of(1, "Usarolecciones",
                                2, "IDE IntelliJ" ),
                        "Hacer CRUD usando solo colecciones" )
        ));

        useCase.addRepository(repository);

        Exercise exercise = new Exercise(1,Map.of(1, "Usarolecciones",
                                2, "IDE IntelliJ" ),
                        "Hacer CRUD usando solo colecciones");

        var request = new RequestCommand<>(new RemoveExerciseToKataCommand(
                exercise,
                KataId.of("kata1"),
                ChallengeId.of("101")
        ));

        UseCaseHandler.getInstance()
                .setIdentifyExecutor("123")
                .asyncExecutor(useCase, request)
                .subscribe(subscriber);
        Thread.sleep(1000);

        verify(subscriber, times(1)).onNext(eventCaptor.capture());

        ExerciseRemovedFromKata exerciseRemovedFromKata = (ExerciseRemovedFromKata) eventCaptor.getAllValues().get(0);
        Assertions.assertEquals("Hacer CRUD usando solo colecciones", exerciseRemovedFromKata.getExercise().value().goal());
    }

}