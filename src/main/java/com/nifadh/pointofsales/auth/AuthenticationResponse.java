package com.nifadh.pointofsales.auth;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Builder
@Getter
public class AuthenticationResponse {
    private final String token;
    private final Integer userId;
    private final String role;
}
