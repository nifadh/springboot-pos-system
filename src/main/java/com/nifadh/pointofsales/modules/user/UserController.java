package com.nifadh.pointofsales.modules.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/user")
    public UserRegisterResponse addUser(@Valid @RequestBody UserRegisterRequest userRegisterRequest) {
        return userService.createUser(userRegisterRequest);
    }
}
