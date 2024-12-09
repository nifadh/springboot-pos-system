package com.nifadh.pointofsales.modules.user;

import com.nifadh.pointofsales.exception.DuplicateResourceException;
import com.nifadh.pointofsales.exception.ResourceNotFoundException;
import com.nifadh.pointofsales.util.TimeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.relational.core.sql.In;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public UserRegisterResponse createUser(UserRegisterRequest registerRequest) {
        checkForDuplicateUser(registerRequest.getEmail());

        User user = userMapper.userRequestToUser(registerRequest);
        user.setPassword(
                passwordEncoder.encode(registerRequest.getPassword())
        );

        String currentTime = TimeUtil.getSriLankanDateTimeString();
        user.setCreatedOn(currentTime);
        user.setLastLogin(currentTime);

        user = userRepository.save(user);
        user.setEmail(user.getEmail());
        return userMapper.userToUserRegisterResponse(user);
    }

    public User findUserById(Integer userId) {
        checkIfUserIdIsValid(userId);
        return userRepository.findById(userId).get();
    }

    private void checkForDuplicateUser(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new DuplicateResourceException("A user with email: " + email + " already exists!");
        }
    }

    public void checkIfEmailIsValid(String email) {
        if (!userRepository.existsByEmail(email)) {
            throw new ResourceNotFoundException("Invalid email: " + email);
        }
    }

    public void checkIfUserIdIsValid(Integer userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("Invalid user ID: " + userId);
        }
    }


}
