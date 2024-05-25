package org.sopt.spring.service.dto;

public record UserJoinResponse(
        String accessToken,
        String userId
) {

    public static UserJoinResponse of(
            String accessToken,
            String userId
    ) {
        return new UserJoinResponse(accessToken, userId);
    }
}

