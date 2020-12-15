package com.challenge.domain.dojo.values;

import co.com.sofka.domain.generic.ValueObject;
import com.challenge.domain.dojo.Enums.Frequency;

public class OpeningHours implements ValueObject<OpeningHours.Props> {
    private final int hourStart;
    private final int hourEnd;
    private final Frequency frequency;

    public OpeningHours(int hourStart, int hourEnd, Frequency frequency) {
        this.hourStart = hourStart;
        this.hourEnd = hourEnd;
        this.frequency = frequency;
    }

    @Override
    public Props value() {
        return new Props() {
            @Override
            public int hourStart() {
                return hourStart;
            }

            @Override
            public int hourEnd() {
                return hourEnd;
            }

            @Override
            public Frequency frequency() {
                return frequency;
            }
        };
    }
    
    public interface Props {
        int hourStart();
        int hourEnd();
        Frequency frequency();
    }

}
