package com.challenge.domain.dojo.values;

import co.com.sofka.domain.generic.ValueObject;

public class DataInfo implements ValueObject<DataInfo.Props> {
    private final String name;
    private final String legend;

    public DataInfo(String name, String legend) {
        this.name = name;
        this.legend = legend;
    }

    @Override
    public Props value() {
        return new Props() {
            @Override
            public String name() {
                return name;
            }

            @Override
            public String legend() {
                return legend;
            }
        };
    }

    public interface Props{
        String name();
        String legend();
    }
}
