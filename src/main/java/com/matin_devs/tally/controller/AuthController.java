package com.matin_devs.tally.controller;

import com.matin_devs.tally.dto.AuthRequest;
import com.matin_devs.tally.exception.UserAlreadyExistsException;
import com.matin_devs.tally.exception.UserNotFoundException;
import com.matin_devs.tally.model.User;
import com.matin_devs.tally.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthRequest authRequest) throws UserAlreadyExistsException {
        //TODO: add password verification
        User user;
        try {
            user = userService.getUserByUsername(authRequest.getUsername());
            return ResponseEntity.ok(user.getId().toString());
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestParam UUID id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user.getId().toString());
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AuthRequest authRequest) {
        User user = userService.createUser(authRequest);
        return ResponseEntity.ok(user.toString());
    }
}
