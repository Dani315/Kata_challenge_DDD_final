package com.challenge.domain.externalValues;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class GroupGit implements ValueObject<GroupGit.Props> {
    private final Integer groupId;
    private final String path;
    private final String name;

    public GroupGit(Integer groupId, String path, String name) {
        this.groupId = Objects.requireNonNull(groupId, "El id del grupo no puede ser nulo");
        this.path = Objects.requireNonNull(path, "La url del repositorio no puede ser nulo");
        this.name = name;
    }

    @Override
    public Props value() {
        return new Props() {
            @Override
            public Integer groupId() {
                return groupId;
            }

            @Override
            public String path() {
                return path;
            }

            @Override
            public String name() {
                return name;
            }
        };
    }

    public interface Props {
        Integer groupId();
        String path();
        String name();
    }
}
