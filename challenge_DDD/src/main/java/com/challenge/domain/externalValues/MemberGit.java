package com.challenge.domain.externalValues;

import co.com.sofka.domain.generic.ValueObject;

public class MemberGit implements ValueObject<MemberGit.Props> {
    private final Integer memberGitId;
    private final String imageAvatarUrl;
    private final String userName;

    public MemberGit(Integer memberGitId, String imageAvatarUrl, String userName) {
        this.memberGitId = memberGitId;
        this.imageAvatarUrl = imageAvatarUrl;
        this.userName = userName;
    }

    @Override
    public Props value() {
        return new Props() {
            @Override
            public Integer memberGitId() {
                return memberGitId;
            }

            @Override
            public String imageAvatarUrl() {
                return imageAvatarUrl;
            }

            @Override
            public String userName() {
                return userName;
            }
        };
    }

    public interface Props {
        Integer memberGitId();
        String imageAvatarUrl();
        String userName();
    }

}
