package com.matin_devs.tally.service;

import com.matin_devs.tally.dto.UserRequest;
import com.matin_devs.tally.exception.UserAlreadyExistsException;
import com.matin_devs.tally.exception.UserNotFoundException;
import com.matin_devs.tally.model.User;
import com.matin_devs.tally.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public User createUser(UserRequest request) throws UserAlreadyExistsException {

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new UserAlreadyExistsException();
        }

        User user = User.builder()
                .username(request.getUsername())
                .build();

        userRepository.save(user);
        return user;
    }

    public User getUserByUsername(String username) throws UserNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(UserNotFoundException::new);
    }

    public User getUserById(Long id) throws UserNotFoundException {
        return userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }
}
