package com.challenge.domain.dojo.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Date;

public class Accomplishment implements ValueObject<Accomplishment.Props> {
    private final String label;
    private final int point;
    private final Date date;

    public Accomplishment(String label, int point, Date date) {
        this.label = label;
        this.point = point;
        this.date = date;
    }

    @Override
    public Props value() {
        return new Props() {
            @Override
            public String label() {
                return label;
            }

            @Override
            public int point() {
                return point;
            }

            @Override
            public Date date() {
                return date;
            }
        };
    }

    public interface Props{
        String label();
        int point();
        Date date();
    }
}
