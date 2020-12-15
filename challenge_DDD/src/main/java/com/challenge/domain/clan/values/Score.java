package com.challenge.domain.clan.values;

import co.com.sofka.domain.generic.ValueObject;
import com.challenge.domain.dojo.values.DojoId;

import java.util.Date;

public class Score implements ValueObject<Score.Props> {
    private final int point;
    private final DojoId dojoId;
    private final Date date;

    public Score(int point, DojoId dojoId, Date date) {
        this.point = point;
        this.dojoId = dojoId;
        this.date = date;
    }

    @Override
    public Props value() {
        return new Props() {
            @Override
            public int point() {
                return point;
            }

            @Override
            public DojoId dojoId() {
                return dojoId;
            }

            @Override
            public Date date() {
                return date;
            }
        };
    }

    public interface Props {
        int point();
        DojoId dojoId();
        Date date();
    }
}
