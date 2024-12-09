package com.nifadh.pointofsales.modules.user;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Builder
@Getter
public class UserRegisterResponse {
    private final Integer id;

    private final String firstName;

    private final String lastName;

    private final String email;

    private final String role;

    private final String createdOn;

    private final String lastLogin;
}
