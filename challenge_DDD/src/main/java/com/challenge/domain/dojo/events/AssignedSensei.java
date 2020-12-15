package com.challenge.domain.dojo.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.challenge.domain.dojo.values.SenseiId;
import com.challenge.domain.dojo.values.Specialty;
import com.challenge.domain.externalValues.PersonId;

public class AssignedSensei extends DomainEvent {
    private final SenseiId senseiId;
    private final PersonId personId;
    private final Specialty specialty;


    public AssignedSensei(SenseiId senseiId, PersonId personId, Specialty specialty) {
        super("com.challenge.domain.dojo.AssignedSensei");
        this.senseiId = senseiId;
        this.personId = personId;
        this.specialty = specialty;
    }

    public SenseiId getSenseiId() {
        return senseiId;
    }

    public PersonId getPersonId() {
        return personId;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

}
