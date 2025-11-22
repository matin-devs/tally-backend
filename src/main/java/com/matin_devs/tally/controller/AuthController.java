package com.matin_devs.tally.controller;

import com.matin_devs.tally.dto.UserRequest;
import com.matin_devs.tally.exception.UserNotFoundException;
import com.matin_devs.tally.model.User;
import com.matin_devs.tally.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<String> login(@RequestParam String username){
        try {
            User user = userService.getUserByUsername(username);
            return ResponseEntity.ok(user.getUsername());
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Username not found");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestParam String username) {
        try {
            User user = userService.getUserByUsername(username);
            return ResponseEntity.ok(user.getUsername());
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Username not found");
        }
    }
}
