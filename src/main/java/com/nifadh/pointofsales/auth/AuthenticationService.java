package com.nifadh.pointofsales.auth;

import com.nifadh.pointofsales.config.JwtService;
import com.nifadh.pointofsales.modules.user.User;
import com.nifadh.pointofsales.modules.user.UserRepository;
import com.nifadh.pointofsales.modules.user.UserService;
import com.nifadh.pointofsales.util.TimeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getEmail(),
                        authenticationRequest.getPassword())
        );
        userService.checkIfEmailIsValid(authenticationRequest.getEmail());

        User user = userRepository.findByEmail(authenticationRequest.getEmail()).get();

        user.setLastLogin(TimeUtil.getSriLankanDateTimeString());
        user = userRepository.save(user);

        String jwtToken = jwtService.generateToken(user, user.getId());

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .userId(user.getId())
                .role(user.getRole().name())
                .build();
    }
}
