package com.matin_devs.tally.service;

import com.matin_devs.tally.dto.AuthRequest;
import com.matin_devs.tally.dto.CategoryRequest;
import com.matin_devs.tally.exception.UserAlreadyExistsException;
import com.matin_devs.tally.exception.UserNotFoundException;
import com.matin_devs.tally.model.User;
import com.matin_devs.tally.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.matin_devs.tally.common.CommonConstants.TRANSACTION_CATEGORIES;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final TransactionCategoryService categoryService;

    public User createUser(AuthRequest request) throws UserAlreadyExistsException {

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new UserAlreadyExistsException(request.getUsername());
        }

        User user = User.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .build();

        userRepository.save(user);

        TRANSACTION_CATEGORIES
                .forEach(category -> categoryService.createCategory(
                        CategoryRequest.builder()
                                .name(category)
                                .user(user)
                                .build()
                ));

        return user;
    }

    public User getUserByUsername(String username) throws UserNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));
    }

    public User getUserById(UUID id) {
        return userRepository.getReferenceById(id);
    }
}
