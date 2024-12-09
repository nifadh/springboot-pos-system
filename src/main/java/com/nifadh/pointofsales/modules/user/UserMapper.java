package com.nifadh.pointofsales.modules.user;

import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User userRequestToUser(UserRegisterRequest registerRequest) {
        return User.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .role(Role.valueOf(registerRequest.getRole().toUpperCase()))
                .build();
    }

    public UserRegisterResponse userToUserRegisterResponse(User user) {
        return UserRegisterResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .role(user.getRole().name())
                .createdOn(user.getCreatedOn())
                .lastLogin(user.getLastLogin())
                .build();

    }
}
