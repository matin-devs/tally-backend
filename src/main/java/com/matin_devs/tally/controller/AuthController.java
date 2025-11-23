package com.matin_devs.tally.controller;

import com.matin_devs.tally.dto.UserRequest;
import com.matin_devs.tally.exception.UserAlreadyExistsException;
import com.matin_devs.tally.exception.UserNotFoundException;
import com.matin_devs.tally.model.User;
import com.matin_devs.tally.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username) throws UserAlreadyExistsException {
        User user;
        try {
            user = userService.getUserByUsername(username);
        } catch (UserNotFoundException e) {
            // TODO: This should return created not ok as well
            user = userService.createUser(new UserRequest(username));
        }
        return ResponseEntity.ok(user.getId().toString());
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestParam Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user.getId().toString());
    }
}
